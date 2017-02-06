package org.h2.fulltext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import org.h2.util.New;
import org.h2.util.SoftHashMap;

class FullTextSettings
{
  private static final HashMap<String, FullTextSettings> SETTINGS = ;
  private boolean initialized;
  private final HashSet<String> ignoreList = New.hashSet();
  private final HashMap<String, Integer> words = New.hashMap();
  private final HashMap<Integer, IndexInfo> indexes = New.hashMap();
  private final SoftHashMap<Connection, SoftHashMap<String, PreparedStatement>> cache = new SoftHashMap();
  private String whitespaceChars = " \t\n\r\f+\"*%&/()=?'!,.;:-_#@|^~`{}[]<>\\";
  
  protected HashSet<String> getIgnoreList()
  {
    return this.ignoreList;
  }
  
  protected HashMap<String, Integer> getWordList()
  {
    return this.words;
  }
  
  protected IndexInfo getIndexInfo(int indexId)
  {
    return (IndexInfo)this.indexes.get(Integer.valueOf(indexId));
  }
  
  protected void addIndexInfo(IndexInfo index)
  {
    this.indexes.put(Integer.valueOf(index.id), index);
  }
  
  protected String convertWord(String word)
  {
    word = word.toUpperCase();
    if (this.ignoreList.contains(word)) {
      return null;
    }
    return word;
  }
  
  protected static FullTextSettings getInstance(Connection conn)
    throws SQLException
  {
    String path = getIndexPath(conn);
    FullTextSettings setting = (FullTextSettings)SETTINGS.get(path);
    if (setting == null)
    {
      setting = new FullTextSettings();
      SETTINGS.put(path, setting);
    }
    return setting;
  }
  
  protected static String getIndexPath(Connection conn)
    throws SQLException
  {
    Statement stat = conn.createStatement();
    ResultSet rs = stat.executeQuery("CALL IFNULL(DATABASE_PATH(), 'MEM:' || DATABASE())");
    
    rs.next();
    String path = rs.getString(1);
    if ("MEM:UNNAMED".equals(path)) {
      throw FullText.throwException("Fulltext search for private (unnamed) in-memory databases is not supported.");
    }
    rs.close();
    return path;
  }
  
  protected synchronized PreparedStatement prepare(Connection conn, String sql)
    throws SQLException
  {
    SoftHashMap<String, PreparedStatement> c = (SoftHashMap)this.cache.get(conn);
    if (c == null)
    {
      c = new SoftHashMap();
      this.cache.put(conn, c);
    }
    PreparedStatement prep = (PreparedStatement)c.get(sql);
    if ((prep != null) && (prep.getConnection().isClosed())) {
      prep = null;
    }
    if (prep == null)
    {
      prep = conn.prepareStatement(sql);
      c.put(sql, prep);
    }
    return prep;
  }
  
  protected void removeAllIndexes()
  {
    this.indexes.clear();
  }
  
  protected void removeIndexInfo(IndexInfo index)
  {
    this.indexes.remove(Integer.valueOf(index.id));
  }
  
  protected void setInitialized(boolean b)
  {
    this.initialized = b;
  }
  
  protected boolean isInitialized()
  {
    return this.initialized;
  }
  
  protected static void closeAll()
  {
    SETTINGS.clear();
  }
  
  protected void setWhitespaceChars(String whitespaceChars)
  {
    this.whitespaceChars = whitespaceChars;
  }
  
  protected String getWhitespaceChars()
  {
    return this.whitespaceChars;
  }
}
