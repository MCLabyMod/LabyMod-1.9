package org.h2.fulltext;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.UUID;
import org.h2.api.Trigger;
import org.h2.command.Parser;
import org.h2.engine.Session;
import org.h2.expression.Comparison;
import org.h2.expression.ConditionAndOr;
import org.h2.expression.Expression;
import org.h2.expression.ExpressionColumn;
import org.h2.expression.ValueExpression;
import org.h2.jdbc.JdbcConnection;
import org.h2.message.DbException;
import org.h2.tools.SimpleResultSet;
import org.h2.util.IOUtils;
import org.h2.util.New;
import org.h2.util.StatementBuilder;
import org.h2.util.StringUtils;
import org.h2.value.Value;

public class FullText
{
  private static final String FIELD_SCHEMA = "SCHEMA";
  private static final String FIELD_TABLE = "TABLE";
  private static final String FIELD_COLUMNS = "COLUMNS";
  private static final String FIELD_KEYS = "KEYS";
  private static final String FIELD_SCORE = "SCORE";
  private static final String TRIGGER_PREFIX = "FT_";
  private static final String SCHEMA = "FT";
  private static final String SELECT_MAP_BY_WORD_ID = "SELECT ROWID FROM FT.MAP WHERE WORDID=?";
  private static final String SELECT_ROW_BY_ID = "SELECT KEY, INDEXID FROM FT.ROWS WHERE ID=?";
  private static final String FIELD_QUERY = "QUERY";
  
  public static void init(Connection conn)
    throws SQLException
  {
    Statement stat = conn.createStatement();
    stat.execute("CREATE SCHEMA IF NOT EXISTS FT");
    stat.execute("CREATE TABLE IF NOT EXISTS FT.INDEXES(ID INT AUTO_INCREMENT PRIMARY KEY, SCHEMA VARCHAR, TABLE VARCHAR, COLUMNS VARCHAR, UNIQUE(SCHEMA, TABLE))");
    
    stat.execute("CREATE TABLE IF NOT EXISTS FT.WORDS(ID INT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR, UNIQUE(NAME))");
    
    stat.execute("CREATE TABLE IF NOT EXISTS FT.ROWS(ID IDENTITY, HASH INT, INDEXID INT, KEY VARCHAR, UNIQUE(HASH, INDEXID, KEY))");
    
    stat.execute("CREATE TABLE IF NOT EXISTS FT.MAP(ROWID INT, WORDID INT, PRIMARY KEY(WORDID, ROWID))");
    
    stat.execute("CREATE TABLE IF NOT EXISTS FT.IGNORELIST(LIST VARCHAR)");
    
    stat.execute("CREATE TABLE IF NOT EXISTS FT.SETTINGS(KEY VARCHAR PRIMARY KEY, VALUE VARCHAR)");
    
    stat.execute("CREATE ALIAS IF NOT EXISTS FT_CREATE_INDEX FOR \"" + FullText.class.getName() + ".createIndex\"");
    
    stat.execute("CREATE ALIAS IF NOT EXISTS FT_DROP_INDEX FOR \"" + FullText.class.getName() + ".dropIndex\"");
    
    stat.execute("CREATE ALIAS IF NOT EXISTS FT_SEARCH FOR \"" + FullText.class.getName() + ".search\"");
    
    stat.execute("CREATE ALIAS IF NOT EXISTS FT_SEARCH_DATA FOR \"" + FullText.class.getName() + ".searchData\"");
    
    stat.execute("CREATE ALIAS IF NOT EXISTS FT_REINDEX FOR \"" + FullText.class.getName() + ".reindex\"");
    
    stat.execute("CREATE ALIAS IF NOT EXISTS FT_DROP_ALL FOR \"" + FullText.class.getName() + ".dropAll\"");
    
    FullTextSettings setting = FullTextSettings.getInstance(conn);
    ResultSet rs = stat.executeQuery("SELECT * FROM FT.IGNORELIST");
    while (rs.next())
    {
      String commaSeparatedList = rs.getString(1);
      setIgnoreList(setting, commaSeparatedList);
    }
    rs = stat.executeQuery("SELECT * FROM FT.SETTINGS");
    while (rs.next())
    {
      String key = rs.getString(1);
      if ("whitespaceChars".equals(key))
      {
        String value = rs.getString(2);
        setting.setWhitespaceChars(value);
      }
    }
    rs = stat.executeQuery("SELECT * FROM FT.WORDS");
    HashMap<String, Integer> map = setting.getWordList();
    while (rs.next())
    {
      String word = rs.getString("NAME");
      int id = rs.getInt("ID");
      word = setting.convertWord(word);
      if (word != null) {
        map.put(word, Integer.valueOf(id));
      }
    }
    setting.setInitialized(true);
  }
  
  public static void createIndex(Connection conn, String schema, String table, String columnList)
    throws SQLException
  {
    init(conn);
    PreparedStatement prep = conn.prepareStatement("INSERT INTO FT.INDEXES(SCHEMA, TABLE, COLUMNS) VALUES(?, ?, ?)");
    
    prep.setString(1, schema);
    prep.setString(2, table);
    prep.setString(3, columnList);
    prep.execute();
    createTrigger(conn, schema, table);
    indexExistingRows(conn, schema, table);
  }
  
  public static void reindex(Connection conn)
    throws SQLException
  {
    init(conn);
    removeAllTriggers(conn, "FT_");
    FullTextSettings setting = FullTextSettings.getInstance(conn);
    setting.getWordList().clear();
    Statement stat = conn.createStatement();
    stat.execute("TRUNCATE TABLE FT.WORDS");
    stat.execute("TRUNCATE TABLE FT.ROWS");
    stat.execute("TRUNCATE TABLE FT.MAP");
    ResultSet rs = stat.executeQuery("SELECT * FROM FT.INDEXES");
    while (rs.next())
    {
      String schema = rs.getString("SCHEMA");
      String table = rs.getString("TABLE");
      createTrigger(conn, schema, table);
      indexExistingRows(conn, schema, table);
    }
  }
  
  public static void dropIndex(Connection conn, String schema, String table)
    throws SQLException
  {
    init(conn);
    PreparedStatement prep = conn.prepareStatement("SELECT ID FROM FT.INDEXES WHERE SCHEMA=? AND TABLE=?");
    
    prep.setString(1, schema);
    prep.setString(2, table);
    ResultSet rs = prep.executeQuery();
    if (!rs.next()) {
      return;
    }
    int indexId = rs.getInt(1);
    prep = conn.prepareStatement("DELETE FROM FT.INDEXES WHERE ID=?");
    
    prep.setInt(1, indexId);
    prep.execute();
    createOrDropTrigger(conn, schema, table, false);
    prep = conn.prepareStatement("DELETE FROM FT.ROWS WHERE INDEXID=? AND ROWNUM<10000");
    for (;;)
    {
      prep.setInt(1, indexId);
      int deleted = prep.executeUpdate();
      if (deleted == 0) {
        break;
      }
    }
    prep = conn.prepareStatement("DELETE FROM FT.MAP M WHERE NOT EXISTS (SELECT * FROM FT.ROWS R WHERE R.ID=M.ROWID) AND ROWID<10000");
    for (;;)
    {
      int deleted = prep.executeUpdate();
      if (deleted == 0) {
        break;
      }
    }
  }
  
  public static void dropAll(Connection conn)
    throws SQLException
  {
    init(conn);
    Statement stat = conn.createStatement();
    stat.execute("DROP SCHEMA IF EXISTS FT");
    removeAllTriggers(conn, "FT_");
    FullTextSettings setting = FullTextSettings.getInstance(conn);
    setting.removeAllIndexes();
    setting.getIgnoreList().clear();
    setting.getWordList().clear();
  }
  
  public static ResultSet search(Connection conn, String text, int limit, int offset)
    throws SQLException
  {
    try
    {
      return search(conn, text, limit, offset, false);
    }
    catch (DbException e)
    {
      throw DbException.toSQLException(e);
    }
  }
  
  public static ResultSet searchData(Connection conn, String text, int limit, int offset)
    throws SQLException
  {
    try
    {
      return search(conn, text, limit, offset, true);
    }
    catch (DbException e)
    {
      throw DbException.toSQLException(e);
    }
  }
  
  public static void setIgnoreList(Connection conn, String commaSeparatedList)
    throws SQLException
  {
    try
    {
      init(conn);
      FullTextSettings setting = FullTextSettings.getInstance(conn);
      setIgnoreList(setting, commaSeparatedList);
      Statement stat = conn.createStatement();
      stat.execute("TRUNCATE TABLE FT.IGNORELIST");
      PreparedStatement prep = conn.prepareStatement("INSERT INTO FT.IGNORELIST VALUES(?)");
      
      prep.setString(1, commaSeparatedList);
      prep.execute();
    }
    catch (DbException e)
    {
      throw DbException.toSQLException(e);
    }
  }
  
  public static void setWhitespaceChars(Connection conn, String whitespaceChars)
    throws SQLException
  {
    try
    {
      init(conn);
      FullTextSettings setting = FullTextSettings.getInstance(conn);
      setting.setWhitespaceChars(whitespaceChars);
      PreparedStatement prep = conn.prepareStatement("MERGE INTO FT.SETTINGS VALUES(?, ?)");
      
      prep.setString(1, "whitespaceChars");
      prep.setString(2, whitespaceChars);
      prep.execute();
    }
    catch (DbException e)
    {
      throw DbException.toSQLException(e);
    }
  }
  
  protected static String asString(Object data, int type)
    throws SQLException
  {
    if (data == null) {
      return "NULL";
    }
    switch (type)
    {
    case -7: 
    case -6: 
    case -5: 
    case -1: 
    case 1: 
    case 2: 
    case 3: 
    case 4: 
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    case 12: 
    case 16: 
    case 91: 
    case 92: 
    case 93: 
      return data.toString();
    case 2005: 
      try
      {
        if ((data instanceof Clob)) {
          data = ((Clob)data).getCharacterStream();
        }
        return IOUtils.readStringAndClose((Reader)data, -1);
      }
      catch (IOException e)
      {
        throw DbException.toSQLException(e);
      }
    case -4: 
    case -3: 
    case -2: 
    case 0: 
    case 70: 
    case 1111: 
    case 2000: 
    case 2001: 
    case 2002: 
    case 2003: 
    case 2004: 
    case 2006: 
      throw throwException("Unsupported column data type: " + type);
    }
    return "";
  }
  
  protected static SimpleResultSet createResultSet(boolean data)
  {
    SimpleResultSet result = new SimpleResultSet();
    if (data)
    {
      result.addColumn("SCHEMA", 12, 0, 0);
      result.addColumn("TABLE", 12, 0, 0);
      result.addColumn("COLUMNS", 2003, 0, 0);
      result.addColumn("KEYS", 2003, 0, 0);
    }
    else
    {
      result.addColumn("QUERY", 12, 0, 0);
    }
    result.addColumn("SCORE", 6, 0, 0);
    return result;
  }
  
  protected static Object[][] parseKey(Connection conn, String key)
  {
    ArrayList<String> columns = New.arrayList();
    ArrayList<String> data = New.arrayList();
    JdbcConnection c = (JdbcConnection)conn;
    Session session = (Session)c.getSession();
    Parser p = new Parser(session);
    Expression expr = p.parseExpression(key);
    addColumnData(columns, data, expr);
    Object[] col = new Object[columns.size()];
    columns.toArray(col);
    Object[] dat = new Object[columns.size()];
    data.toArray(dat);
    Object[][] columnData = { col, dat };
    return columnData;
  }
  
  protected static String quoteSQL(Object data, int type)
    throws SQLException
  {
    if (data == null) {
      return "NULL";
    }
    switch (type)
    {
    case -7: 
    case -6: 
    case -5: 
    case 2: 
    case 3: 
    case 4: 
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    case 16: 
      return data.toString();
    case -1: 
    case 1: 
    case 12: 
    case 91: 
    case 92: 
    case 93: 
      return quoteString(data.toString());
    case -4: 
    case -3: 
    case -2: 
      if ((data instanceof UUID)) {
        return "'" + data.toString() + "'";
      }
      return "'" + StringUtils.convertBytesToHex((byte[])data) + "'";
    case 0: 
    case 70: 
    case 1111: 
    case 2000: 
    case 2001: 
    case 2002: 
    case 2003: 
    case 2004: 
    case 2005: 
    case 2006: 
      throw throwException("Unsupported key data type: " + type);
    }
    return "";
  }
  
  protected static void removeAllTriggers(Connection conn, String prefix)
    throws SQLException
  {
    Statement stat = conn.createStatement();
    ResultSet rs = stat.executeQuery("SELECT * FROM INFORMATION_SCHEMA.TRIGGERS");
    Statement stat2 = conn.createStatement();
    while (rs.next())
    {
      String schema = rs.getString("TRIGGER_SCHEMA");
      String name = rs.getString("TRIGGER_NAME");
      if (name.startsWith(prefix))
      {
        name = StringUtils.quoteIdentifier(schema) + "." + StringUtils.quoteIdentifier(name);
        
        stat2.execute("DROP TRIGGER " + name);
      }
    }
  }
  
  protected static void setColumns(int[] index, ArrayList<String> keys, ArrayList<String> columns)
    throws SQLException
  {
    int i = 0;
    for (int keySize = keys.size(); i < keySize; i++)
    {
      String key = (String)keys.get(i);
      int found = -1;
      int columnsSize = columns.size();
      for (int j = 0; (found == -1) && (j < columnsSize); j++)
      {
        String column = (String)columns.get(j);
        if (column.equals(key)) {
          found = j;
        }
      }
      if (found < 0) {
        throw throwException("Column not found: " + key);
      }
      index[i] = found;
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
    FullTextSettings setting = FullTextSettings.getInstance(conn);
    if (!setting.isInitialized()) {
      init(conn);
    }
    HashSet<String> words = New.hashSet();
    addWords(setting, words, text);
    HashSet<Integer> rIds = null;HashSet<Integer> lastRowIds = null;
    HashMap<String, Integer> allWords = setting.getWordList();
    
    PreparedStatement prepSelectMapByWordId = setting.prepare(conn, "SELECT ROWID FROM FT.MAP WHERE WORDID=?");
    for (String word : words)
    {
      lastRowIds = rIds;
      rIds = New.hashSet();
      Integer wId = (Integer)allWords.get(word);
      if (wId != null)
      {
        prepSelectMapByWordId.setInt(1, wId.intValue());
        ResultSet rs = prepSelectMapByWordId.executeQuery();
        while (rs.next())
        {
          Integer rId = Integer.valueOf(rs.getInt(1));
          if ((lastRowIds == null) || (lastRowIds.contains(rId))) {
            rIds.add(rId);
          }
        }
      }
    }
    if ((rIds == null) || (rIds.size() == 0)) {
      return result;
    }
    PreparedStatement prepSelectRowById = setting.prepare(conn, "SELECT KEY, INDEXID FROM FT.ROWS WHERE ID=?");
    int rowCount = 0;
    for (Iterator i$ = rIds.iterator(); i$.hasNext();)
    {
      int rowId = ((Integer)i$.next()).intValue();
      prepSelectRowById.setInt(1, rowId);
      ResultSet rs = prepSelectRowById.executeQuery();
      if (rs.next()) {
        if (offset > 0)
        {
          offset--;
        }
        else
        {
          String key = rs.getString(1);
          int indexId = rs.getInt(2);
          IndexInfo index = setting.getIndexInfo(indexId);
          if (data)
          {
            Object[][] columnData = parseKey(conn, key);
            result.addRow(new Object[] { index.schema, index.table, columnData[0], columnData[1], Double.valueOf(1.0D) });
          }
          else
          {
            String query = StringUtils.quoteIdentifier(index.schema) + "." + StringUtils.quoteIdentifier(index.table) + " WHERE " + key;
            
            result.addRow(new Object[] { query, Double.valueOf(1.0D) });
          }
          rowCount++;
          if ((limit > 0) && (rowCount >= limit)) {
            break;
          }
        }
      }
    }
    return result;
  }
  
  private static void addColumnData(ArrayList<String> columns, ArrayList<String> data, Expression expr)
  {
    if ((expr instanceof ConditionAndOr))
    {
      ConditionAndOr and = (ConditionAndOr)expr;
      Expression left = and.getExpression(true);
      Expression right = and.getExpression(false);
      addColumnData(columns, data, left);
      addColumnData(columns, data, right);
    }
    else
    {
      Comparison comp = (Comparison)expr;
      ExpressionColumn ec = (ExpressionColumn)comp.getExpression(true);
      ValueExpression ev = (ValueExpression)comp.getExpression(false);
      String columnName = ec.getColumnName();
      columns.add(columnName);
      if (ev == null) {
        data.add(null);
      } else {
        data.add(ev.getValue(null).getString());
      }
    }
  }
  
  protected static void addWords(FullTextSettings setting, HashSet<String> set, Reader reader)
  {
    StreamTokenizer tokenizer = new StreamTokenizer(reader);
    tokenizer.resetSyntax();
    tokenizer.wordChars(33, 255);
    char[] whitespaceChars = setting.getWhitespaceChars().toCharArray();
    for (char ch : whitespaceChars) {
      tokenizer.whitespaceChars(ch, ch);
    }
    try
    {
      for (;;)
      {
        int token = tokenizer.nextToken();
        if (token == -1) {
          break;
        }
        if (token == -3)
        {
          String word = tokenizer.sval;
          word = setting.convertWord(word);
          if (word != null) {
            set.add(word);
          }
        }
      }
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, "Tokenizer error");
    }
  }
  
  protected static void addWords(FullTextSettings setting, HashSet<String> set, String text)
  {
    String whitespaceChars = setting.getWhitespaceChars();
    StringTokenizer tokenizer = new StringTokenizer(text, whitespaceChars);
    while (tokenizer.hasMoreTokens())
    {
      String word = tokenizer.nextToken();
      word = setting.convertWord(word);
      if (word != null) {
        set.add(word);
      }
    }
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
    String trigger = StringUtils.quoteIdentifier(schema) + "." + StringUtils.quoteIdentifier(new StringBuilder().append("FT_").append(table).toString());
    
    stat.execute("DROP TRIGGER IF EXISTS " + trigger);
    if (create)
    {
      StringBuilder buff = new StringBuilder("CREATE TRIGGER IF NOT EXISTS ");
      
      buff.append(trigger).append(" AFTER INSERT, UPDATE, DELETE, ROLLBACK ON ").append(StringUtils.quoteIdentifier(schema)).append('.').append(StringUtils.quoteIdentifier(table)).append(" FOR EACH ROW CALL \"").append(FullTextTrigger.class.getName()).append('"');
      
      stat.execute(buff.toString());
    }
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
      existing.fire(conn, null, row);
    }
  }
  
  private static String quoteString(String data)
  {
    if (data.indexOf('\'') < 0) {
      return "'" + data + "'";
    }
    int len = data.length();
    StringBuilder buff = new StringBuilder(len + 2);
    buff.append('\'');
    for (int i = 0; i < len; i++)
    {
      char ch = data.charAt(i);
      if (ch == '\'') {
        buff.append(ch);
      }
      buff.append(ch);
    }
    buff.append('\'');
    return buff.toString();
  }
  
  private static void setIgnoreList(FullTextSettings setting, String commaSeparatedList)
  {
    String[] list = StringUtils.arraySplit(commaSeparatedList, ',', true);
    HashSet<String> set = setting.getIgnoreList();
    for (String word : list)
    {
      String converted = setting.convertWord(word);
      if (converted != null) {
        set.add(converted);
      }
    }
  }
  
  protected static boolean hasChanged(Object[] oldRow, Object[] newRow, int[] indexColumns)
  {
    for (int c : indexColumns)
    {
      Object o = oldRow[c];Object n = newRow[c];
      if (o == null)
      {
        if (n != null) {
          return true;
        }
      }
      else if (!o.equals(n)) {
        return true;
      }
    }
    return false;
  }
  
  public static void closeAll() {}
  
  public static class FullTextTrigger
    implements Trigger
  {
    protected FullTextSettings setting;
    protected IndexInfo index;
    protected int[] columnTypes;
    protected PreparedStatement prepInsertWord;
    protected PreparedStatement prepInsertRow;
    protected PreparedStatement prepInsertMap;
    protected PreparedStatement prepDeleteRow;
    protected PreparedStatement prepDeleteMap;
    protected PreparedStatement prepSelectRow;
    
    public void init(Connection conn, String schemaName, String triggerName, String tableName, boolean before, int type)
      throws SQLException
    {
      this.setting = FullTextSettings.getInstance(conn);
      if (!this.setting.isInitialized()) {
        FullText.init(conn);
      }
      ArrayList<String> keyList = New.arrayList();
      DatabaseMetaData meta = conn.getMetaData();
      ResultSet rs = meta.getColumns(null, StringUtils.escapeMetaDataPattern(schemaName), StringUtils.escapeMetaDataPattern(tableName), null);
      
      ArrayList<String> columnList = New.arrayList();
      while (rs.next()) {
        columnList.add(rs.getString("COLUMN_NAME"));
      }
      this.columnTypes = new int[columnList.size()];
      this.index = new IndexInfo();
      this.index.schema = schemaName;
      this.index.table = tableName;
      this.index.columns = new String[columnList.size()];
      columnList.toArray(this.index.columns);
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
      PreparedStatement prep = conn.prepareStatement("SELECT ID, COLUMNS FROM FT.INDEXES WHERE SCHEMA=? AND TABLE=?");
      
      prep.setString(1, schemaName);
      prep.setString(2, tableName);
      rs = prep.executeQuery();
      if (rs.next())
      {
        this.index.id = rs.getInt(1);
        String columns = rs.getString(2);
        if (columns != null) {
          for (String s : StringUtils.arraySplit(columns, ',', true)) {
            indexList.add(s);
          }
        }
      }
      if (indexList.size() == 0) {
        indexList.addAll(columnList);
      }
      this.index.keys = new int[keyList.size()];
      FullText.setColumns(this.index.keys, keyList, columnList);
      this.index.indexColumns = new int[indexList.size()];
      FullText.setColumns(this.index.indexColumns, indexList, columnList);
      this.setting.addIndexInfo(this.index);
      this.prepInsertWord = conn.prepareStatement("INSERT INTO FT.WORDS(NAME) VALUES(?)");
      
      this.prepInsertRow = conn.prepareStatement("INSERT INTO FT.ROWS(HASH, INDEXID, KEY) VALUES(?, ?, ?)");
      
      this.prepInsertMap = conn.prepareStatement("INSERT INTO FT.MAP(ROWID, WORDID) VALUES(?, ?)");
      
      this.prepDeleteRow = conn.prepareStatement("DELETE FROM FT.ROWS WHERE HASH=? AND INDEXID=? AND KEY=?");
      
      this.prepDeleteMap = conn.prepareStatement("DELETE FROM FT.MAP WHERE ROWID=? AND WORDID=?");
      
      this.prepSelectRow = conn.prepareStatement("SELECT ID FROM FT.ROWS WHERE HASH=? AND INDEXID=? AND KEY=?");
    }
    
    public void fire(Connection conn, Object[] oldRow, Object[] newRow)
      throws SQLException
    {
      if (oldRow != null)
      {
        if (newRow != null)
        {
          if (FullText.hasChanged(oldRow, newRow, this.index.indexColumns))
          {
            delete(oldRow);
            insert(newRow);
          }
        }
        else {
          delete(oldRow);
        }
      }
      else if (newRow != null) {
        insert(newRow);
      }
    }
    
    public void close()
    {
      this.setting.removeIndexInfo(this.index);
    }
    
    public void remove()
    {
      this.setting.removeIndexInfo(this.index);
    }
    
    protected void insert(Object[] row)
      throws SQLException
    {
      String key = getKey(row);
      int hash = key.hashCode();
      this.prepInsertRow.setInt(1, hash);
      this.prepInsertRow.setInt(2, this.index.id);
      this.prepInsertRow.setString(3, key);
      this.prepInsertRow.execute();
      ResultSet rs = this.prepInsertRow.getGeneratedKeys();
      rs.next();
      int rowId = rs.getInt(1);
      this.prepInsertMap.setInt(1, rowId);
      int[] wordIds = getWordIds(row);
      for (int id : wordIds)
      {
        this.prepInsertMap.setInt(2, id);
        this.prepInsertMap.execute();
      }
    }
    
    protected void delete(Object[] row)
      throws SQLException
    {
      String key = getKey(row);
      int hash = key.hashCode();
      this.prepSelectRow.setInt(1, hash);
      this.prepSelectRow.setInt(2, this.index.id);
      this.prepSelectRow.setString(3, key);
      ResultSet rs = this.prepSelectRow.executeQuery();
      if (rs.next())
      {
        int rowId = rs.getInt(1);
        this.prepDeleteMap.setInt(1, rowId);
        int[] wordIds = getWordIds(row);
        for (int id : wordIds)
        {
          this.prepDeleteMap.setInt(2, id);
          this.prepDeleteMap.executeUpdate();
        }
        this.prepDeleteRow.setInt(1, hash);
        this.prepDeleteRow.setInt(2, this.index.id);
        this.prepDeleteRow.setString(3, key);
        this.prepDeleteRow.executeUpdate();
      }
    }
    
    private int[] getWordIds(Object[] row)
      throws SQLException
    {
      HashSet<String> words = New.hashSet();
      for (int idx : this.index.indexColumns)
      {
        int type = this.columnTypes[idx];
        Object data = row[idx];
        if ((type == 2005) && (data != null))
        {
          Reader reader;
          Reader reader;
          if ((data instanceof Reader)) {
            reader = (Reader)data;
          } else {
            reader = ((Clob)data).getCharacterStream();
          }
          FullText.addWords(this.setting, words, reader);
        }
        else
        {
          String string = FullText.asString(data, type);
          FullText.addWords(this.setting, words, string);
        }
      }
      HashMap<String, Integer> allWords = this.setting.getWordList();
      int[] wordIds = new int[words.size()];
      Iterator<String> it = words.iterator();
      for (int i = 0; it.hasNext(); i++)
      {
        String word = (String)it.next();
        Integer wId = (Integer)allWords.get(word);
        int wordId;
        if (wId == null)
        {
          this.prepInsertWord.setString(1, word);
          this.prepInsertWord.execute();
          ResultSet rs = this.prepInsertWord.getGeneratedKeys();
          rs.next();
          int wordId = rs.getInt(1);
          allWords.put(word, Integer.valueOf(wordId));
        }
        else
        {
          wordId = wId.intValue();
        }
        wordIds[i] = wordId;
      }
      Arrays.sort(wordIds);
      return wordIds;
    }
    
    private String getKey(Object[] row)
      throws SQLException
    {
      StatementBuilder buff = new StatementBuilder();
      for (int columnIndex : this.index.keys)
      {
        buff.appendExceptFirst(" AND ");
        buff.append(StringUtils.quoteIdentifier(this.index.columns[columnIndex]));
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
  
  protected static SQLException throwException(String message)
    throws SQLException
  {
    throw new SQLException(message, "FULLTEXT");
  }
}
