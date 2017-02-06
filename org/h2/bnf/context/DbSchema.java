package org.h2.bnf.context;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.h2.engine.SysProperties;
import org.h2.util.New;
import org.h2.util.StringUtils;

public class DbSchema
{
  public final String name;
  public final boolean isDefault;
  public final boolean isSystem;
  public final String quotedName;
  private final DbContents contents;
  private DbTableOrView[] tables;
  private DbProcedure[] procedures;
  
  DbSchema(DbContents contents, String name, boolean isDefault)
  {
    this.contents = contents;
    this.name = name;
    this.quotedName = contents.quoteIdentifier(name);
    this.isDefault = isDefault;
    if (name == null) {
      this.isSystem = true;
    } else if ("INFORMATION_SCHEMA".equals(name)) {
      this.isSystem = true;
    } else if ((!contents.isH2()) && (StringUtils.toUpperEnglish(name).startsWith("INFO"))) {
      this.isSystem = true;
    } else if ((contents.isPostgreSQL()) && (StringUtils.toUpperEnglish(name).startsWith("PG_"))) {
      this.isSystem = true;
    } else if ((contents.isDerby()) && (name.startsWith("SYS"))) {
      this.isSystem = true;
    } else {
      this.isSystem = false;
    }
  }
  
  public DbContents getContents()
  {
    return this.contents;
  }
  
  public DbTableOrView[] getTables()
  {
    return this.tables;
  }
  
  public DbProcedure[] getProcedures()
  {
    return this.procedures;
  }
  
  public void readTables(DatabaseMetaData meta, String[] tableTypes)
    throws SQLException
  {
    ResultSet rs = meta.getTables(null, this.name, null, tableTypes);
    ArrayList<DbTableOrView> list = New.arrayList();
    while (rs.next())
    {
      DbTableOrView table = new DbTableOrView(this, rs);
      if ((!this.contents.isOracle()) || (table.getName().indexOf('$') <= 0)) {
        list.add(table);
      }
    }
    rs.close();
    this.tables = new DbTableOrView[list.size()];
    list.toArray(this.tables);
    if (this.tables.length < SysProperties.CONSOLE_MAX_TABLES_LIST_COLUMNS) {
      for (DbTableOrView tab : this.tables) {
        try
        {
          tab.readColumns(meta);
        }
        catch (SQLException e) {}
      }
    }
  }
  
  public void readProcedures(DatabaseMetaData meta)
    throws SQLException
  {
    ResultSet rs = meta.getProcedures(null, this.name, null);
    ArrayList<DbProcedure> list = New.arrayList();
    while (rs.next()) {
      list.add(new DbProcedure(this, rs));
    }
    rs.close();
    this.procedures = new DbProcedure[list.size()];
    list.toArray(this.procedures);
    if (this.procedures.length < SysProperties.CONSOLE_MAX_PROCEDURES_LIST_COLUMNS) {
      for (DbProcedure procedure : this.procedures) {
        procedure.readParameters(meta);
      }
    }
  }
}
