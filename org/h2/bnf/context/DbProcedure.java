package org.h2.bnf.context;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.h2.util.New;

public class DbProcedure
{
  private final DbSchema schema;
  private final String name;
  private final String quotedName;
  private boolean returnsResult;
  private DbColumn[] parameters;
  
  public DbProcedure(DbSchema schema, ResultSet rs)
    throws SQLException
  {
    this.schema = schema;
    this.name = rs.getString("PROCEDURE_NAME");
    this.returnsResult = (rs.getShort("PROCEDURE_TYPE") == 2);
    
    this.quotedName = schema.getContents().quoteIdentifier(this.name);
  }
  
  public DbSchema getSchema()
  {
    return this.schema;
  }
  
  public DbColumn[] getParameters()
  {
    return this.parameters;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getQuotedName()
  {
    return this.quotedName;
  }
  
  public boolean isReturnsResult()
  {
    return this.returnsResult;
  }
  
  void readParameters(DatabaseMetaData meta)
    throws SQLException
  {
    ResultSet rs = meta.getProcedureColumns(null, this.schema.name, this.name, null);
    ArrayList<DbColumn> list = New.arrayList();
    while (rs.next())
    {
      DbColumn column = DbColumn.getProcedureColumn(this.schema.getContents(), rs);
      if (column.getPosition() > 0) {
        list.add(column);
      }
    }
    rs.close();
    this.parameters = new DbColumn[list.size()];
    for (int i = 0; i < this.parameters.length; i++)
    {
      DbColumn column = (DbColumn)list.get(i);
      if ((column.getPosition() > 0) && (column.getPosition() <= this.parameters.length)) {
        this.parameters[(column.getPosition() - 1)] = column;
      }
    }
  }
}
