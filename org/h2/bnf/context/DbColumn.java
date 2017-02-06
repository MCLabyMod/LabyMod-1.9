package org.h2.bnf.context;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbColumn
{
  private final String name;
  private final String quotedName;
  private final String dataType;
  private int position;
  
  private DbColumn(DbContents contents, ResultSet rs, boolean procedureColumn)
    throws SQLException
  {
    this.name = rs.getString("COLUMN_NAME");
    this.quotedName = contents.quoteIdentifier(this.name);
    String type = rs.getString("TYPE_NAME");
    String precisionColumnName;
    String precisionColumnName;
    if (procedureColumn) {
      precisionColumnName = "PRECISION";
    } else {
      precisionColumnName = "COLUMN_SIZE";
    }
    int precision = rs.getInt(precisionColumnName);
    this.position = rs.getInt("ORDINAL_POSITION");
    boolean isSQLite = contents.isSQLite();
    if ((precision > 0) && (!isSQLite))
    {
      type = type + "(" + precision;
      String scaleColumnName;
      String scaleColumnName;
      if (procedureColumn) {
        scaleColumnName = "SCALE";
      } else {
        scaleColumnName = "DECIMAL_DIGITS";
      }
      int prec = rs.getInt(scaleColumnName);
      if (prec > 0) {
        type = type + ", " + prec;
      }
      type = type + ")";
    }
    if (rs.getInt("NULLABLE") == 0) {
      type = type + " NOT NULL";
    }
    this.dataType = type;
  }
  
  public static DbColumn getProcedureColumn(DbContents contents, ResultSet rs)
    throws SQLException
  {
    return new DbColumn(contents, rs, true);
  }
  
  public static DbColumn getColumn(DbContents contents, ResultSet rs)
    throws SQLException
  {
    return new DbColumn(contents, rs, false);
  }
  
  public String getDataType()
  {
    return this.dataType;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getQuotedName()
  {
    return this.quotedName;
  }
  
  public int getPosition()
  {
    return this.position;
  }
}
