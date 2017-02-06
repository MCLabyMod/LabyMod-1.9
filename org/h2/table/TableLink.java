package org.h2.table;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.h2.command.Prepared;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.index.Index;
import org.h2.index.IndexType;
import org.h2.index.LinkedIndex;
import org.h2.message.DbException;
import org.h2.message.Trace;
import org.h2.result.Row;
import org.h2.result.RowList;
import org.h2.schema.Schema;
import org.h2.util.JdbcUtils;
import org.h2.util.MathUtils;
import org.h2.util.New;
import org.h2.util.StatementBuilder;
import org.h2.util.StringUtils;
import org.h2.value.DataType;
import org.h2.value.Value;

public class TableLink
  extends Table
{
  private static final int MAX_RETRY = 2;
  private static final long ROW_COUNT_APPROXIMATION = 100000L;
  private final String originalSchema;
  private String driver;
  private String url;
  private String user;
  private String password;
  private String originalTable;
  private String qualifiedTableName;
  private TableLinkConnection conn;
  private HashMap<String, PreparedStatement> preparedMap = New.hashMap();
  private final ArrayList<Index> indexes = New.arrayList();
  private final boolean emitUpdates;
  private LinkedIndex linkedIndex;
  private DbException connectException;
  private boolean storesLowerCase;
  private boolean storesMixedCase;
  private boolean storesMixedCaseQuoted;
  private boolean supportsMixedCaseIdentifiers;
  private boolean globalTemporary;
  private boolean readOnly;
  
  public TableLink(Schema schema, int id, String name, String driver, String url, String user, String password, String originalSchema, String originalTable, boolean emitUpdates, boolean force)
  {
    super(schema, id, name, false, true);
    this.driver = driver;
    this.url = url;
    this.user = user;
    this.password = password;
    this.originalSchema = originalSchema;
    this.originalTable = originalTable;
    this.emitUpdates = emitUpdates;
    try
    {
      connect();
    }
    catch (DbException e)
    {
      if (!force) {
        throw e;
      }
      Column[] cols = new Column[0];
      setColumns(cols);
      this.linkedIndex = new LinkedIndex(this, id, IndexColumn.wrap(cols), IndexType.createNonUnique(false));
      
      this.indexes.add(this.linkedIndex);
    }
  }
  
  private void connect()
  {
    this.connectException = null;
    int retry = 0;
    for (;;)
    {
      try
      {
        this.conn = this.database.getLinkConnection(this.driver, this.url, this.user, this.password);
        synchronized (this.conn)
        {
          try
          {
            readMetaData();
            return;
          }
          catch (Exception e)
          {
            this.conn.close(true);
            this.conn = null;
            throw DbException.convert(e);
          }
        }
        retry++;
      }
      catch (DbException e)
      {
        if (retry >= 2)
        {
          this.connectException = e;
          throw e;
        }
      }
    }
  }
  
  private void readMetaData()
    throws SQLException
  {
    DatabaseMetaData meta = this.conn.getConnection().getMetaData();
    this.storesLowerCase = meta.storesLowerCaseIdentifiers();
    this.storesMixedCase = meta.storesMixedCaseIdentifiers();
    this.storesMixedCaseQuoted = meta.storesMixedCaseQuotedIdentifiers();
    this.supportsMixedCaseIdentifiers = meta.supportsMixedCaseIdentifiers();
    ResultSet rs = meta.getTables(null, this.originalSchema, this.originalTable, null);
    if ((rs.next()) && (rs.next())) {
      throw DbException.get(90080, this.originalTable);
    }
    rs.close();
    rs = meta.getColumns(null, this.originalSchema, this.originalTable, null);
    int i = 0;
    ArrayList<Column> columnList = New.arrayList();
    HashMap<String, Column> columnMap = New.hashMap();
    String catalog = null;String schema = null;
    while (rs.next())
    {
      String thisCatalog = rs.getString("TABLE_CAT");
      if (catalog == null) {
        catalog = thisCatalog;
      }
      String thisSchema = rs.getString("TABLE_SCHEM");
      if (schema == null) {
        schema = thisSchema;
      }
      if ((!StringUtils.equals(catalog, thisCatalog)) || (!StringUtils.equals(schema, thisSchema)))
      {
        columnMap.clear();
        columnList.clear();
        break;
      }
      String n = rs.getString("COLUMN_NAME");
      n = convertColumnName(n);
      int sqlType = rs.getInt("DATA_TYPE");
      long precision = rs.getInt("COLUMN_SIZE");
      precision = convertPrecision(sqlType, precision);
      int scale = rs.getInt("DECIMAL_DIGITS");
      scale = convertScale(sqlType, scale);
      int displaySize = MathUtils.convertLongToInt(precision);
      int type = DataType.convertSQLTypeToValueType(sqlType);
      Column col = new Column(n, type, precision, scale, displaySize);
      col.setTable(this, i++);
      columnList.add(col);
      columnMap.put(n, col);
    }
    rs.close();
    if ((this.originalTable.indexOf('.') < 0) && (!StringUtils.isNullOrEmpty(schema))) {
      this.qualifiedTableName = (schema + "." + this.originalTable);
    } else {
      this.qualifiedTableName = this.originalTable;
    }
    Statement stat = null;
    try
    {
      stat = this.conn.getConnection().createStatement();
      rs = stat.executeQuery("SELECT * FROM " + this.qualifiedTableName + " T WHERE 1=0");
      ResultSetMetaData rsMeta;
      if (columnList.size() == 0)
      {
        rsMeta = rs.getMetaData();
        for (i = 0; i < rsMeta.getColumnCount();)
        {
          String n = rsMeta.getColumnName(i + 1);
          n = convertColumnName(n);
          int sqlType = rsMeta.getColumnType(i + 1);
          long precision = rsMeta.getPrecision(i + 1);
          precision = convertPrecision(sqlType, precision);
          int scale = rsMeta.getScale(i + 1);
          scale = convertScale(sqlType, scale);
          int displaySize = rsMeta.getColumnDisplaySize(i + 1);
          int type = DataType.getValueTypeFromResultSet(rsMeta, i + 1);
          Column col = new Column(n, type, precision, scale, displaySize);
          col.setTable(this, i++);
          columnList.add(col);
          columnMap.put(n, col);
        }
      }
      rs.close();
    }
    catch (Exception e)
    {
      throw DbException.get(42102, e, new String[] { this.originalTable + "(" + e.toString() + ")" });
    }
    finally
    {
      JdbcUtils.closeSilently(stat);
    }
    Column[] cols = new Column[columnList.size()];
    columnList.toArray(cols);
    setColumns(cols);
    int id = getId();
    this.linkedIndex = new LinkedIndex(this, id, IndexColumn.wrap(cols), IndexType.createNonUnique(false));
    
    this.indexes.add(this.linkedIndex);
    try
    {
      rs = meta.getPrimaryKeys(null, this.originalSchema, this.originalTable);
    }
    catch (Exception e)
    {
      rs = null;
    }
    String pkName = "";
    if ((rs != null) && (rs.next()))
    {
      ArrayList<Column> list = New.arrayList();
      do
      {
        int idx = rs.getInt("KEY_SEQ");
        if (pkName == null) {
          pkName = rs.getString("PK_NAME");
        }
        while (list.size() < idx) {
          list.add(null);
        }
        String col = rs.getString("COLUMN_NAME");
        col = convertColumnName(col);
        Column column = (Column)columnMap.get(col);
        if (idx == 0) {
          list.add(column);
        } else {
          list.set(idx - 1, column);
        }
      } while (rs.next());
      addIndex(list, IndexType.createPrimaryKey(false, false));
      rs.close();
    }
    try
    {
      rs = meta.getIndexInfo(null, this.originalSchema, this.originalTable, false, true);
    }
    catch (Exception e)
    {
      rs = null;
    }
    String indexName = null;
    ArrayList<Column> list = New.arrayList();
    IndexType indexType = null;
    if (rs != null)
    {
      while (rs.next()) {
        if (rs.getShort("TYPE") != 0)
        {
          String newIndex = rs.getString("INDEX_NAME");
          if (!pkName.equals(newIndex))
          {
            if ((indexName != null) && (!indexName.equals(newIndex)))
            {
              addIndex(list, indexType);
              indexName = null;
            }
            if (indexName == null)
            {
              indexName = newIndex;
              list.clear();
            }
            boolean unique = !rs.getBoolean("NON_UNIQUE");
            indexType = unique ? IndexType.createUnique(false, false) : IndexType.createNonUnique(false);
            
            String col = rs.getString("COLUMN_NAME");
            col = convertColumnName(col);
            Column column = (Column)columnMap.get(col);
            list.add(column);
          }
        }
      }
      rs.close();
    }
    if (indexName != null) {
      addIndex(list, indexType);
    }
  }
  
  private static long convertPrecision(int sqlType, long precision)
  {
    switch (sqlType)
    {
    case 2: 
    case 3: 
      if (precision == 0L) {
        precision = 65535L;
      }
      break;
    case 91: 
      precision = Math.max(8L, precision);
      break;
    case 93: 
      precision = Math.max(23L, precision);
      break;
    case 92: 
      precision = Math.max(6L, precision);
    }
    return precision;
  }
  
  private static int convertScale(int sqlType, int scale)
  {
    switch (sqlType)
    {
    case 2: 
    case 3: 
      if (scale < 0) {
        scale = 32767;
      }
      break;
    }
    return scale;
  }
  
  private String convertColumnName(String columnName)
  {
    if (((this.storesMixedCase) || (this.storesLowerCase)) && (columnName.equals(StringUtils.toLowerEnglish(columnName)))) {
      columnName = StringUtils.toUpperEnglish(columnName);
    } else if ((this.storesMixedCase) && (!this.supportsMixedCaseIdentifiers)) {
      columnName = StringUtils.toUpperEnglish(columnName);
    } else if ((this.storesMixedCase) && (this.storesMixedCaseQuoted)) {
      columnName = StringUtils.toUpperEnglish(columnName);
    }
    return columnName;
  }
  
  private void addIndex(ArrayList<Column> list, IndexType indexType)
  {
    Column[] cols = new Column[list.size()];
    list.toArray(cols);
    Index index = new LinkedIndex(this, 0, IndexColumn.wrap(cols), indexType);
    this.indexes.add(index);
  }
  
  public String getDropSQL()
  {
    return "DROP TABLE IF EXISTS " + getSQL();
  }
  
  public String getCreateSQL()
  {
    StringBuilder buff = new StringBuilder("CREATE FORCE ");
    if (isTemporary())
    {
      if (this.globalTemporary) {
        buff.append("GLOBAL ");
      } else {
        buff.append("LOCAL ");
      }
      buff.append("TEMPORARY ");
    }
    buff.append("LINKED TABLE ").append(getSQL());
    if (this.comment != null) {
      buff.append(" COMMENT ").append(StringUtils.quoteStringSQL(this.comment));
    }
    buff.append('(').append(StringUtils.quoteStringSQL(this.driver)).append(", ").append(StringUtils.quoteStringSQL(this.url)).append(", ").append(StringUtils.quoteStringSQL(this.user)).append(", ").append(StringUtils.quoteStringSQL(this.password)).append(", ").append(StringUtils.quoteStringSQL(this.originalTable)).append(')');
    if (this.emitUpdates) {
      buff.append(" EMIT UPDATES");
    }
    if (this.readOnly) {
      buff.append(" READONLY");
    }
    buff.append(" /*--hide--*/");
    return buff.toString();
  }
  
  public Index addIndex(Session session, String indexName, int indexId, IndexColumn[] cols, IndexType indexType, boolean create, String indexComment)
  {
    throw DbException.getUnsupportedException("LINK");
  }
  
  public boolean lock(Session session, boolean exclusive, boolean forceLockEvenInMvcc)
  {
    return false;
  }
  
  public boolean isLockedExclusively()
  {
    return false;
  }
  
  public Index getScanIndex(Session session)
  {
    return this.linkedIndex;
  }
  
  private void checkReadOnly()
  {
    if (this.readOnly) {
      throw DbException.get(90097);
    }
  }
  
  public void removeRow(Session session, Row row)
  {
    checkReadOnly();
    getScanIndex(session).remove(session, row);
  }
  
  public void addRow(Session session, Row row)
  {
    checkReadOnly();
    getScanIndex(session).add(session, row);
  }
  
  public void close(Session session)
  {
    if (this.conn != null) {
      try
      {
        this.conn.close(false);
      }
      finally
      {
        this.conn = null;
      }
    }
  }
  
  public synchronized long getRowCount(Session session)
  {
    String sql = "SELECT COUNT(*) FROM " + this.qualifiedTableName;
    try
    {
      PreparedStatement prep = execute(sql, null, false);
      ResultSet rs = prep.getResultSet();
      rs.next();
      long count = rs.getLong(1);
      rs.close();
      reusePreparedStatement(prep, sql);
      return count;
    }
    catch (Exception e)
    {
      throw wrapException(sql, e);
    }
  }
  
  public static DbException wrapException(String sql, Exception ex)
  {
    SQLException e = DbException.toSQLException(ex);
    return DbException.get(90111, e, new String[] { sql, e.toString() });
  }
  
  public String getQualifiedTable()
  {
    return this.qualifiedTableName;
  }
  
  public PreparedStatement execute(String sql, ArrayList<Value> params, boolean reusePrepared)
  {
    if (this.conn == null) {
      throw this.connectException;
    }
    int retry = 0;
    for (;;)
    {
      try
      {
        synchronized (this.conn)
        {
          PreparedStatement prep = (PreparedStatement)this.preparedMap.remove(sql);
          if (prep == null) {
            prep = this.conn.getConnection().prepareStatement(sql);
          }
          if (this.trace.isDebugEnabled())
          {
            StatementBuilder buff = new StatementBuilder();
            buff.append(getName()).append(":\n").append(sql);
            if ((params != null) && (params.size() > 0))
            {
              buff.append(" {");
              int i = 1;
              Iterator i$ = params.iterator();
              if (i$.hasNext())
              {
                Value v = (Value)i$.next();
                buff.appendExceptFirst(", ");
                buff.append(i++).append(": ").append(v.getSQL());
                continue;
              }
              buff.append('}');
            }
            buff.append(';');
            this.trace.debug(buff.toString());
          }
          if (params != null)
          {
            int i = 0;int size = params.size();
            if (i < size)
            {
              Value v = (Value)params.get(i);
              v.set(prep, i + 1);i++; continue;
            }
          }
          prep.execute();
          if (reusePrepared)
          {
            reusePreparedStatement(prep, sql);
            return null;
          }
          return prep;
        }
        retry++;
      }
      catch (SQLException e)
      {
        if (retry >= 2) {
          throw DbException.convert(e);
        }
        this.conn.close(true);
        connect();
      }
    }
  }
  
  public void unlock(Session s) {}
  
  public void checkRename() {}
  
  public void checkSupportAlter()
  {
    throw DbException.getUnsupportedException("LINK");
  }
  
  public void truncate(Session session)
  {
    throw DbException.getUnsupportedException("LINK");
  }
  
  public boolean canGetRowCount()
  {
    return true;
  }
  
  public boolean canDrop()
  {
    return true;
  }
  
  public String getTableType()
  {
    return "TABLE LINK";
  }
  
  public void removeChildrenAndResources(Session session)
  {
    super.removeChildrenAndResources(session);
    close(session);
    this.database.removeMeta(session, getId());
    this.driver = null;
    this.url = (this.user = this.password = this.originalTable = null);
    this.preparedMap = null;
    invalidate();
  }
  
  public boolean isOracle()
  {
    return this.url.startsWith("jdbc:oracle:");
  }
  
  public ArrayList<Index> getIndexes()
  {
    return this.indexes;
  }
  
  public long getMaxDataModificationId()
  {
    return Long.MAX_VALUE;
  }
  
  public Index getUniqueIndex()
  {
    for (Index idx : this.indexes) {
      if (idx.getIndexType().isUnique()) {
        return idx;
      }
    }
    return null;
  }
  
  public void updateRows(Prepared prepared, Session session, RowList rows)
  {
    checkReadOnly();
    boolean deleteInsert;
    boolean deleteInsert;
    if (this.emitUpdates)
    {
      for (rows.reset(); rows.hasNext();)
      {
        prepared.checkCanceled();
        Row oldRow = rows.next();
        Row newRow = rows.next();
        this.linkedIndex.update(oldRow, newRow);
        session.log(this, (short)1, oldRow);
        session.log(this, (short)0, newRow);
      }
      deleteInsert = false;
    }
    else
    {
      deleteInsert = true;
    }
    if (deleteInsert) {
      super.updateRows(prepared, session, rows);
    }
  }
  
  public void setGlobalTemporary(boolean globalTemporary)
  {
    this.globalTemporary = globalTemporary;
  }
  
  public void setReadOnly(boolean readOnly)
  {
    this.readOnly = readOnly;
  }
  
  public long getRowCountApproximation()
  {
    return 100000L;
  }
  
  public long getDiskSpaceUsed()
  {
    return 0L;
  }
  
  public void reusePreparedStatement(PreparedStatement prep, String sql)
  {
    synchronized (this.conn)
    {
      this.preparedMap.put(sql, prep);
    }
  }
  
  public boolean isDeterministic()
  {
    return false;
  }
  
  public void checkWritingAllowed() {}
  
  public void validateConvertUpdateSequence(Session session, Row row)
  {
    for (int i = 0; i < this.columns.length; i++)
    {
      Value value = row.getValue(i);
      if (value != null)
      {
        Column column = this.columns[i];
        Value v2 = column.validateConvertUpdateSequence(session, value);
        if (v2 != value) {
          row.setValue(i, v2);
        }
      }
    }
  }
  
  public Value getDefaultValue(Session session, Column column)
  {
    return null;
  }
}
