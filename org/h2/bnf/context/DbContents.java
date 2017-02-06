package org.h2.bnf.context;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.h2.command.Parser;
import org.h2.util.New;
import org.h2.util.StringUtils;

public class DbContents
{
  private DbSchema[] schemas;
  private DbSchema defaultSchema;
  private boolean isOracle;
  private boolean isH2;
  private boolean isPostgreSQL;
  private boolean isDerby;
  private boolean isSQLite;
  private boolean isH2ModeMySQL;
  private boolean isMySQL;
  private boolean isFirebird;
  private boolean isMSSQLServer;
  
  public DbSchema getDefaultSchema()
  {
    return this.defaultSchema;
  }
  
  public boolean isDerby()
  {
    return this.isDerby;
  }
  
  public boolean isFirebird()
  {
    return this.isFirebird;
  }
  
  public boolean isH2()
  {
    return this.isH2;
  }
  
  public boolean isH2ModeMySQL()
  {
    return this.isH2ModeMySQL;
  }
  
  public boolean isMSSQLServer()
  {
    return this.isMSSQLServer;
  }
  
  public boolean isMySQL()
  {
    return this.isMySQL;
  }
  
  public boolean isOracle()
  {
    return this.isOracle;
  }
  
  public boolean isPostgreSQL()
  {
    return this.isPostgreSQL;
  }
  
  public boolean isSQLite()
  {
    return this.isSQLite;
  }
  
  public DbSchema[] getSchemas()
  {
    return this.schemas;
  }
  
  public synchronized void readContents(String url, Connection conn)
    throws SQLException
  {
    this.isH2 = url.startsWith("jdbc:h2:");
    if (this.isH2)
    {
      PreparedStatement prep = conn.prepareStatement("SELECT UPPER(VALUE) FROM INFORMATION_SCHEMA.SETTINGS WHERE NAME=?");
      
      prep.setString(1, "MODE");
      ResultSet rs = prep.executeQuery();
      rs.next();
      if ("MYSQL".equals(rs.getString(1))) {
        this.isH2ModeMySQL = true;
      }
      rs.close();
      prep.close();
    }
    this.isSQLite = url.startsWith("jdbc:sqlite:");
    this.isOracle = url.startsWith("jdbc:oracle:");
    
    this.isPostgreSQL = ((url.startsWith("jdbc:postgresql:")) || (url.startsWith("jdbc:vertica:")));
    
    this.isMySQL = url.startsWith("jdbc:mysql:");
    this.isDerby = url.startsWith("jdbc:derby:");
    this.isFirebird = url.startsWith("jdbc:firebirdsql:");
    this.isMSSQLServer = url.startsWith("jdbc:sqlserver:");
    DatabaseMetaData meta = conn.getMetaData();
    String defaultSchemaName = getDefaultSchemaName(meta);
    String[] schemaNames = getSchemaNames(meta);
    this.schemas = new DbSchema[schemaNames.length];
    for (int i = 0; i < schemaNames.length; i++)
    {
      String schemaName = schemaNames[i];
      boolean isDefault = (defaultSchemaName == null) || (defaultSchemaName.equals(schemaName));
      
      DbSchema schema = new DbSchema(this, schemaName, isDefault);
      if (isDefault) {
        this.defaultSchema = schema;
      }
      this.schemas[i] = schema;
      String[] tableTypes = { "TABLE", "SYSTEM TABLE", "VIEW", "SYSTEM VIEW", "TABLE LINK", "SYNONYM", "EXTERNAL" };
      
      schema.readTables(meta, tableTypes);
      if (!this.isPostgreSQL) {
        schema.readProcedures(meta);
      }
    }
    if (this.defaultSchema == null)
    {
      String best = null;
      for (DbSchema schema : this.schemas)
      {
        if ("dbo".equals(schema.name))
        {
          this.defaultSchema = schema;
          break;
        }
        if ((this.defaultSchema == null) || (best == null) || (schema.name.length() < best.length()))
        {
          best = schema.name;
          this.defaultSchema = schema;
        }
      }
    }
  }
  
  private String[] getSchemaNames(DatabaseMetaData meta)
    throws SQLException
  {
    if ((this.isMySQL) || (this.isSQLite)) {
      return new String[] { "" };
    }
    if (this.isFirebird) {
      return new String[] { null };
    }
    ResultSet rs = meta.getSchemas();
    ArrayList<String> schemaList = New.arrayList();
    while (rs.next())
    {
      String schema = rs.getString("TABLE_SCHEM");
      String[] ignoreNames = null;
      if (this.isOracle) {
        ignoreNames = new String[] { "CTXSYS", "DIP", "DBSNMP", "DMSYS", "EXFSYS", "FLOWS_020100", "FLOWS_FILES", "MDDATA", "MDSYS", "MGMT_VIEW", "OLAPSYS", "ORDSYS", "ORDPLUGINS", "OUTLN", "SI_INFORMTN_SCHEMA", "SYS", "SYSMAN", "SYSTEM", "TSMSYS", "WMSYS", "XDB" };
      } else if (this.isMSSQLServer) {
        ignoreNames = new String[] { "sys", "db_accessadmin", "db_backupoperator", "db_datareader", "db_datawriter", "db_ddladmin", "db_denydatareader", "db_denydatawriter", "db_owner", "db_securityadmin" };
      }
      if (ignoreNames != null) {
        for (String ignore : ignoreNames) {
          if (ignore.equals(schema))
          {
            schema = null;
            break;
          }
        }
      }
      if (schema != null) {
        schemaList.add(schema);
      }
    }
    rs.close();
    String[] list = new String[schemaList.size()];
    schemaList.toArray(list);
    return list;
  }
  
  private String getDefaultSchemaName(DatabaseMetaData meta)
  {
    String defaultSchemaName = "";
    try
    {
      if (this.isOracle) {
        return meta.getUserName();
      }
      if (this.isPostgreSQL) {
        return "public";
      }
      if (this.isMySQL) {
        return "";
      }
      if (this.isDerby) {
        return StringUtils.toUpperEnglish(meta.getUserName());
      }
      if (this.isFirebird) {
        return null;
      }
      ResultSet rs = meta.getSchemas();
      int index = rs.findColumn("IS_DEFAULT");
      while (rs.next()) {
        if (rs.getBoolean(index)) {
          defaultSchemaName = rs.getString("TABLE_SCHEM");
        }
      }
    }
    catch (SQLException e) {}
    return defaultSchemaName;
  }
  
  public String quoteIdentifier(String identifier)
  {
    if (identifier == null) {
      return null;
    }
    if ((this.isH2) && (!this.isH2ModeMySQL)) {
      return Parser.quoteIdentifier(identifier);
    }
    return StringUtils.toUpperEnglish(identifier);
  }
}
