package org.h2.bnf;

import java.util.HashMap;
import java.util.HashSet;
import org.h2.bnf.context.DbSchema;
import org.h2.bnf.context.DbTableOrView;
import org.h2.util.New;
import org.h2.util.StringUtils;

public class Sentence
{
  public static final int CONTEXT = 0;
  public static final int KEYWORD = 1;
  public static final int FUNCTION = 2;
  private static final long MAX_PROCESSING_TIME = 100L;
  private final HashMap<String, String> next = New.hashMap();
  private String query;
  private String queryUpper;
  private long stopAt;
  private DbSchema lastMatchedSchema;
  private DbTableOrView lastMatchedTable;
  private DbTableOrView lastTable;
  private HashSet<DbTableOrView> tables;
  private HashMap<String, DbTableOrView> aliases;
  
  public void start()
  {
    this.stopAt = (System.currentTimeMillis() + 100L);
  }
  
  public void stopIfRequired()
  {
    if (System.currentTimeMillis() > this.stopAt) {
      throw new IllegalStateException();
    }
  }
  
  public void add(String n, String string, int type)
  {
    this.next.put(type + "#" + n, string);
  }
  
  public void addAlias(String alias, DbTableOrView table)
  {
    if (this.aliases == null) {
      this.aliases = New.hashMap();
    }
    this.aliases.put(alias, table);
  }
  
  public void addTable(DbTableOrView table)
  {
    this.lastTable = table;
    if (this.tables == null) {
      this.tables = New.hashSet();
    }
    this.tables.add(table);
  }
  
  public HashSet<DbTableOrView> getTables()
  {
    return this.tables;
  }
  
  public HashMap<String, DbTableOrView> getAliases()
  {
    return this.aliases;
  }
  
  public DbTableOrView getLastTable()
  {
    return this.lastTable;
  }
  
  public DbSchema getLastMatchedSchema()
  {
    return this.lastMatchedSchema;
  }
  
  public void setLastMatchedSchema(DbSchema schema)
  {
    this.lastMatchedSchema = schema;
  }
  
  public void setLastMatchedTable(DbTableOrView table)
  {
    this.lastMatchedTable = table;
  }
  
  public DbTableOrView getLastMatchedTable()
  {
    return this.lastMatchedTable;
  }
  
  public void setQuery(String query)
  {
    if (!StringUtils.equals(this.query, query))
    {
      this.query = query;
      this.queryUpper = StringUtils.toUpperEnglish(query);
    }
  }
  
  public String getQuery()
  {
    return this.query;
  }
  
  public String getQueryUpper()
  {
    return this.queryUpper;
  }
  
  public HashMap<String, String> getNext()
  {
    return this.next;
  }
}
