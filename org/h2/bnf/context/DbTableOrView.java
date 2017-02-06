package org.h2.bnf.context;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.h2.util.New;

public class DbTableOrView
{
  private final DbSchema schema;
  private final String name;
  private final String quotedName;
  private final boolean isView;
  private DbColumn[] columns;
  
  public DbTableOrView(DbSchema schema, ResultSet rs)
    throws SQLException
  {
    this.schema = schema;
    this.name = rs.getString("TABLE_NAME");
    String type = rs.getString("TABLE_TYPE");
    this.isView = "VIEW".equals(type);
    this.quotedName = schema.getContents().quoteIdentifier(this.name);
  }
  
  public DbSchema getSchema()
  {
    return this.schema;
  }
  
  public DbColumn[] getColumns()
  {
    return this.columns;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public boolean isView()
  {
    return this.isView;
  }
  
  public String getQuotedName()
  {
    return this.quotedName;
  }
  
  public void readColumns(DatabaseMetaData meta)
    throws SQLException
  {
    ResultSet rs = meta.getColumns(null, this.schema.name, this.name, null);
    ArrayList<DbColumn> list = New.arrayList();
    while (rs.next())
    {
      DbColumn column = DbColumn.getColumn(this.schema.getContents(), rs);
      list.add(column);
    }
    rs.close();
    this.columns = new DbColumn[list.size()];
    list.toArray(this.columns);
  }
}
