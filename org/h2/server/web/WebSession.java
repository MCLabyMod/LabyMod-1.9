package org.h2.server.web;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import org.h2.bnf.Bnf;
import org.h2.bnf.context.DbContents;
import org.h2.bnf.context.DbContextRule;
import org.h2.message.DbException;
import org.h2.util.New;

class WebSession
{
  private static final int MAX_HISTORY = 1000;
  long lastAccess;
  final HashMap<String, Object> map = New.hashMap();
  Locale locale;
  Statement executingStatement;
  ResultSet result;
  private final WebServer server;
  private final ArrayList<String> commandHistory;
  private Connection conn;
  private DatabaseMetaData meta;
  private DbContents contents = new DbContents();
  private Bnf bnf;
  private boolean shutdownServerOnDisconnect;
  
  WebSession(WebServer server)
  {
    this.server = server;
    
    this.commandHistory = server.getCommandHistoryList();
  }
  
  void put(String key, Object value)
  {
    this.map.put(key, value);
  }
  
  Object get(String key)
  {
    if ("sessions".equals(key)) {
      return this.server.getSessions();
    }
    return this.map.get(key);
  }
  
  void remove(String key)
  {
    this.map.remove(key);
  }
  
  Bnf getBnf()
  {
    return this.bnf;
  }
  
  void loadBnf()
  {
    try
    {
      Bnf newBnf = Bnf.getInstance(null);
      DbContextRule columnRule = new DbContextRule(this.contents, 0);
      
      DbContextRule newAliasRule = new DbContextRule(this.contents, 3);
      
      DbContextRule aliasRule = new DbContextRule(this.contents, 2);
      
      DbContextRule tableRule = new DbContextRule(this.contents, 1);
      
      DbContextRule schemaRule = new DbContextRule(this.contents, 5);
      
      DbContextRule columnAliasRule = new DbContextRule(this.contents, 4);
      
      newBnf.updateTopic("column_name", columnRule);
      newBnf.updateTopic("new_table_alias", newAliasRule);
      newBnf.updateTopic("table_alias", aliasRule);
      newBnf.updateTopic("column_alias", columnAliasRule);
      newBnf.updateTopic("table_name", tableRule);
      newBnf.updateTopic("schema_name", schemaRule);
      newBnf.linkStatements();
      this.bnf = newBnf;
    }
    catch (Exception e)
    {
      this.server.traceError(e);
    }
  }
  
  String getCommand(int id)
  {
    return (String)this.commandHistory.get(id);
  }
  
  void addCommand(String sql)
  {
    if (sql == null) {
      return;
    }
    sql = sql.trim();
    if (sql.length() == 0) {
      return;
    }
    if (this.commandHistory.size() > 1000) {
      this.commandHistory.remove(0);
    }
    int idx = this.commandHistory.indexOf(sql);
    if (idx >= 0) {
      this.commandHistory.remove(idx);
    }
    this.commandHistory.add(sql);
    if (this.server.isCommandHistoryAllowed()) {
      this.server.saveCommandHistoryList(this.commandHistory);
    }
  }
  
  ArrayList<String> getCommandHistory()
  {
    return this.commandHistory;
  }
  
  HashMap<String, Object> getInfo()
  {
    HashMap<String, Object> m = New.hashMap();
    m.putAll(this.map);
    m.put("lastAccess", new Timestamp(this.lastAccess).toString());
    try
    {
      m.put("url", this.conn == null ? "${text.admin.notConnected}" : this.conn.getMetaData().getURL());
      
      m.put("user", this.conn == null ? "-" : this.conn.getMetaData().getUserName());
      
      m.put("lastQuery", this.commandHistory.size() == 0 ? "" : (String)this.commandHistory.get(0));
      
      m.put("executing", this.executingStatement == null ? "${text.admin.no}" : "${text.admin.yes}");
    }
    catch (SQLException e)
    {
      DbException.traceThrowable(e);
    }
    return m;
  }
  
  void setConnection(Connection conn)
    throws SQLException
  {
    this.conn = conn;
    if (conn == null) {
      this.meta = null;
    } else {
      this.meta = conn.getMetaData();
    }
    this.contents = new DbContents();
  }
  
  DatabaseMetaData getMetaData()
  {
    return this.meta;
  }
  
  Connection getConnection()
  {
    return this.conn;
  }
  
  DbContents getContents()
  {
    return this.contents;
  }
  
  void setShutdownServerOnDisconnect()
  {
    this.shutdownServerOnDisconnect = true;
  }
  
  boolean getShutdownServerOnDisconnect()
  {
    return this.shutdownServerOnDisconnect;
  }
  
  void close()
  {
    if (this.executingStatement != null) {
      try
      {
        this.executingStatement.cancel();
      }
      catch (Exception e) {}
    }
    if (this.conn != null) {
      try
      {
        this.conn.close();
      }
      catch (Exception e) {}
    }
  }
}
