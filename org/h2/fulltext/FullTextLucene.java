package org.h2.fulltext;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import org.h2.api.Trigger;
import org.h2.store.fs.FileUtils;
import org.h2.tools.SimpleResultSet;
import org.h2.util.New;
import org.h2.util.StatementBuilder;
import org.h2.util.StringUtils;
import org.h2.util.Utils;

public class FullTextLucene
  extends FullText
{
  protected static final boolean STORE_DOCUMENT_TEXT_IN_INDEX = Utils.getProperty("h2.storeDocumentTextInIndex", false);
  private static final HashMap<String, IndexAccess> INDEX_ACCESS = New.hashMap();
  private static final String TRIGGER_PREFIX = "FTL_";
  private static final String SCHEMA = "FTL";
  private static final String LUCENE_FIELD_DATA = "_DATA";
  private static final String LUCENE_FIELD_QUERY = "_QUERY";
  private static final String LUCENE_FIELD_MODIFIED = "_modified";
  private static final String LUCENE_FIELD_COLUMN_PREFIX = "_";
  private static final String IN_MEMORY_PREFIX = "mem:";
  
  public static void init(Connection conn)
    throws SQLException
  {
    Statement stat = conn.createStatement();
    stat.execute("CREATE SCHEMA IF NOT EXISTS FTL");
    stat.execute("CREATE TABLE IF NOT EXISTS FTL.INDEXES(SCHEMA VARCHAR, TABLE VARCHAR, COLUMNS VARCHAR, PRIMARY KEY(SCHEMA, TABLE))");
    
    stat.execute("CREATE ALIAS IF NOT EXISTS FTL_CREATE_INDEX FOR \"" + FullTextLucene.class.getName() + ".createIndex\"");
    
    stat.execute("CREATE ALIAS IF NOT EXISTS FTL_DROP_INDEX FOR \"" + FullTextLucene.class.getName() + ".dropIndex\"");
    
    stat.execute("CREATE ALIAS IF NOT EXISTS FTL_SEARCH FOR \"" + FullTextLucene.class.getName() + ".search\"");
    
    stat.execute("CREATE ALIAS IF NOT EXISTS FTL_SEARCH_DATA FOR \"" + FullTextLucene.class.getName() + ".searchData\"");
    
    stat.execute("CREATE ALIAS IF NOT EXISTS FTL_REINDEX FOR \"" + FullTextLucene.class.getName() + ".reindex\"");
    
    stat.execute("CREATE ALIAS IF NOT EXISTS FTL_DROP_ALL FOR \"" + FullTextLucene.class.getName() + ".dropAll\"");
    try
    {
      getIndexAccess(conn);
    }
    catch (SQLException e)
    {
      throw convertException(e);
    }
  }
  
  public static void createIndex(Connection conn, String schema, String table, String columnList)
    throws SQLException
  {
    init(conn);
    PreparedStatement prep = conn.prepareStatement("INSERT INTO FTL.INDEXES(SCHEMA, TABLE, COLUMNS) VALUES(?, ?, ?)");
    
    prep.setString(1, schema);
    prep.setString(2, table);
    prep.setString(3, columnList);
    prep.execute();
    createTrigger(conn, schema, table);
    indexExistingRows(conn, schema, table);
  }
  
  public static void dropIndex(Connection conn, String schema, String table)
    throws SQLException
  {
    init(conn);
    
    PreparedStatement prep = conn.prepareStatement("DELETE FROM FTL.INDEXES WHERE SCHEMA=? AND TABLE=?");
    
    prep.setString(1, schema);
    prep.setString(2, table);
    int rowCount = prep.executeUpdate();
    if (rowCount == 0) {
      return;
    }
    reindex(conn);
  }
  
  public static void reindex(Connection conn)
    throws SQLException
  {
    init(conn);
    removeAllTriggers(conn, "FTL_");
    removeIndexFiles(conn);
    Statement stat = conn.createStatement();
    ResultSet rs = stat.executeQuery("SELECT * FROM FTL.INDEXES");
    while (rs.next())
    {
      String schema = rs.getString("SCHEMA");
      String table = rs.getString("TABLE");
      createTrigger(conn, schema, table);
      indexExistingRows(conn, schema, table);
    }
  }
  
  public static void dropAll(Connection conn)
    throws SQLException
  {
    Statement stat = conn.createStatement();
    stat.execute("DROP SCHEMA IF EXISTS FTL");
    removeAllTriggers(conn, "FTL_");
    removeIndexFiles(conn);
  }
  
  public static ResultSet search(Connection conn, String text, int limit, int offset)
    throws SQLException
  {
    return search(conn, text, limit, offset, false);
  }
  
  public static ResultSet searchData(Connection conn, String text, int limit, int offset)
    throws SQLException
  {
    return search(conn, text, limit, offset, true);
  }
  
  protected static SQLException convertException(Exception e)
  {
    SQLException e2 = new SQLException("Error while indexing document", "FULLTEXT");
    
    e2.initCause(e);
    return e2;
  }
  
  protected static void createTrigger(Connection conn, String schema, String table)
    throws SQLException
  {
    createOrDropTrigger(conn, schema, table, true);
  }
  
  private static void createOrDropTrigger(Connection conn, String schema, String table, boolean create)
    throws SQLException
  {
    Statement stat = conn.createStatement();
    String trigger = StringUtils.quoteIdentifier(schema) + "." + StringUtils.quoteIdentifier(new StringBuilder().append("FTL_").append(table).toString());
    
    stat.execute("DROP TRIGGER IF EXISTS " + trigger);
    if (create)
    {
      StringBuilder buff = new StringBuilder("CREATE TRIGGER IF NOT EXISTS ");
      
      buff.append(trigger).append(" AFTER INSERT, UPDATE, DELETE, ROLLBACK ON ").append(StringUtils.quoteIdentifier(schema)).append('.').append(StringUtils.quoteIdentifier(table)).append(" FOR EACH ROW CALL \"").append(FullTextTrigger.class.getName()).append('"');
      
      stat.execute(buff.toString());
    }
  }
  
  protected static IndexAccess getIndexAccess(Connection conn)
    throws SQLException
  {
    String path = getIndexPath(conn);
    synchronized (INDEX_ACCESS)
    {
      IndexAccess access = (IndexAccess)INDEX_ACCESS.get(path);
      if (access == null) {
        INDEX_ACCESS.put(path, access);
      }
      return access;
    }
  }
  
  protected static String getIndexPath(Connection conn)
    throws SQLException
  {
    Statement stat = conn.createStatement();
    ResultSet rs = stat.executeQuery("CALL DATABASE_PATH()");
    rs.next();
    String path = rs.getString(1);
    if (path == null) {
      return "mem:" + conn.getCatalog();
    }
    int index = path.lastIndexOf(':');
    if (index > 1) {
      path = path.substring(index + 1);
    }
    rs.close();
    return path;
  }
  
  protected static void indexExistingRows(Connection conn, String schema, String table)
    throws SQLException
  {
    FullTextTrigger existing = new FullTextTrigger();
    existing.init(conn, schema, null, table, false, 1);
    String sql = "SELECT * FROM " + StringUtils.quoteIdentifier(schema) + "." + StringUtils.quoteIdentifier(table);
    
    ResultSet rs = conn.createStatement().executeQuery(sql);
    int columnCount = rs.getMetaData().getColumnCount();
    while (rs.next())
    {
      Object[] row = new Object[columnCount];
      for (int i = 0; i < columnCount; i++) {
        row[i] = rs.getObject(i + 1);
      }
      existing.insert(row, false);
    }
    existing.commitIndex();
  }
  
  private static void removeIndexFiles(Connection conn)
    throws SQLException
  {
    String path = getIndexPath(conn);
    IndexAccess access = (IndexAccess)INDEX_ACCESS.get(path);
    if (access != null) {
      removeIndexAccess(access, path);
    }
    if (!path.startsWith("mem:")) {
      FileUtils.deleteRecursive(path, false);
    }
  }
  
  protected static void removeIndexAccess(IndexAccess access, String indexPath)
    throws SQLException
  {
    synchronized (INDEX_ACCESS)
    {
      try
      {
        INDEX_ACCESS.remove(indexPath);
      }
      catch (Exception e)
      {
        throw convertException(e);
      }
    }
  }
  
  protected static ResultSet search(Connection conn, String text, int limit, int offset, boolean data)
    throws SQLException
  {
    SimpleResultSet result = createResultSet(data);
    if (conn.getMetaData().getURL().startsWith("jdbc:columnlist:")) {
      return result;
    }
    if ((text == null) || (text.trim().length() == 0)) {
      return result;
    }
    return result;
  }
  
  static class IndexAccess {}
  
  public static class FullTextTrigger
    implements Trigger
  {
    protected String schema;
    protected String table;
    protected int[] keys;
    protected int[] indexColumns;
    protected String[] columns;
    protected int[] columnTypes;
    protected String indexPath;
    protected FullTextLucene.IndexAccess indexAccess;
    
    public void init(Connection conn, String schemaName, String triggerName, String tableName, boolean before, int type)
      throws SQLException
    {
      this.schema = schemaName;
      this.table = tableName;
      this.indexPath = FullTextLucene.getIndexPath(conn);
      this.indexAccess = FullTextLucene.getIndexAccess(conn);
      ArrayList<String> keyList = New.arrayList();
      DatabaseMetaData meta = conn.getMetaData();
      ResultSet rs = meta.getColumns(null, StringUtils.escapeMetaDataPattern(schemaName), StringUtils.escapeMetaDataPattern(tableName), null);
      
      ArrayList<String> columnList = New.arrayList();
      while (rs.next()) {
        columnList.add(rs.getString("COLUMN_NAME"));
      }
      this.columnTypes = new int[columnList.size()];
      this.columns = new String[columnList.size()];
      columnList.toArray(this.columns);
      rs = meta.getColumns(null, StringUtils.escapeMetaDataPattern(schemaName), StringUtils.escapeMetaDataPattern(tableName), null);
      for (int i = 0; rs.next(); i++) {
        this.columnTypes[i] = rs.getInt("DATA_TYPE");
      }
      if (keyList.size() == 0)
      {
        rs = meta.getPrimaryKeys(null, StringUtils.escapeMetaDataPattern(schemaName), tableName);
        while (rs.next()) {
          keyList.add(rs.getString("COLUMN_NAME"));
        }
      }
      if (keyList.size() == 0) {
        throw FullText.throwException("No primary key for table " + tableName);
      }
      ArrayList<String> indexList = New.arrayList();
      PreparedStatement prep = conn.prepareStatement("SELECT COLUMNS FROM FTL.INDEXES WHERE SCHEMA=? AND TABLE=?");
      
      prep.setString(1, schemaName);
      prep.setString(2, tableName);
      rs = prep.executeQuery();
      if (rs.next())
      {
        String cols = rs.getString(1);
        if (cols != null) {
          for (String s : StringUtils.arraySplit(cols, ',', true)) {
            indexList.add(s);
          }
        }
      }
      if (indexList.size() == 0) {
        indexList.addAll(columnList);
      }
      this.keys = new int[keyList.size()];
      FullText.setColumns(this.keys, keyList, columnList);
      this.indexColumns = new int[indexList.size()];
      FullText.setColumns(this.indexColumns, indexList, columnList);
    }
    
    public void fire(Connection conn, Object[] oldRow, Object[] newRow)
      throws SQLException
    {
      if (oldRow != null)
      {
        if (newRow != null)
        {
          if (FullText.hasChanged(oldRow, newRow, this.indexColumns))
          {
            delete(oldRow, false);
            insert(newRow, true);
          }
        }
        else {
          delete(oldRow, true);
        }
      }
      else if (newRow != null) {
        insert(newRow, true);
      }
    }
    
    public void close()
      throws SQLException
    {
      if (this.indexAccess != null)
      {
        FullTextLucene.removeIndexAccess(this.indexAccess, this.indexPath);
        this.indexAccess = null;
      }
    }
    
    public void remove() {}
    
    void commitIndex()
      throws SQLException
    {}
    
    protected void insert(Object[] row, boolean commitIndex)
      throws SQLException
    {}
    
    protected void delete(Object[] row, boolean commitIndex)
      throws SQLException
    {
      String query = getQuery(row);
    }
    
    private String getQuery(Object[] row)
      throws SQLException
    {
      StatementBuilder buff = new StatementBuilder();
      if (this.schema != null) {
        buff.append(StringUtils.quoteIdentifier(this.schema)).append('.');
      }
      buff.append(StringUtils.quoteIdentifier(this.table)).append(" WHERE ");
      for (int columnIndex : this.keys)
      {
        buff.appendExceptFirst(" AND ");
        buff.append(StringUtils.quoteIdentifier(this.columns[columnIndex]));
        Object o = row[columnIndex];
        if (o == null) {
          buff.append(" IS NULL");
        } else {
          buff.append('=').append(FullText.quoteSQL(o, this.columnTypes[columnIndex]));
        }
      }
      return buff.toString();
    }
  }
}
