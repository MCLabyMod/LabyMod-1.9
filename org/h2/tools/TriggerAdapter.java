package org.h2.tools;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.h2.api.Trigger;

public abstract class TriggerAdapter
  implements Trigger
{
  protected String schemaName;
  protected String triggerName;
  protected String tableName;
  protected boolean before;
  protected int type;
  private SimpleResultSet oldResultSet;
  private SimpleResultSet newResultSet;
  private TriggerRowSource oldSource;
  private TriggerRowSource newSource;
  
  public void init(Connection conn, String schemaName, String triggerName, String tableName, boolean before, int type)
    throws SQLException
  {
    ResultSet rs = conn.getMetaData().getColumns(null, schemaName, tableName, null);
    
    this.oldSource = new TriggerRowSource();
    this.newSource = new TriggerRowSource();
    this.oldResultSet = new SimpleResultSet(this.oldSource);
    this.newResultSet = new SimpleResultSet(this.newSource);
    while (rs.next())
    {
      String column = rs.getString("COLUMN_NAME");
      int dataType = rs.getInt("DATA_TYPE");
      int precision = rs.getInt("COLUMN_SIZE");
      int scale = rs.getInt("DECIMAL_DIGITS");
      this.oldResultSet.addColumn(column, dataType, precision, scale);
      this.newResultSet.addColumn(column, dataType, precision, scale);
    }
    this.schemaName = schemaName;
    this.triggerName = triggerName;
    this.tableName = tableName;
    this.before = before;
    this.type = type;
  }
  
  static class TriggerRowSource
    implements SimpleRowSource
  {
    private Object[] row;
    
    void setRow(Object[] row)
    {
      this.row = row;
    }
    
    public Object[] readRow()
    {
      return this.row;
    }
    
    public void close() {}
    
    public void reset() {}
  }
  
  public void fire(Connection conn, Object[] oldRow, Object[] newRow)
    throws SQLException
  {
    fire(conn, wrap(this.oldResultSet, this.oldSource, oldRow), wrap(this.newResultSet, this.newSource, newRow));
  }
  
  public abstract void fire(Connection paramConnection, ResultSet paramResultSet1, ResultSet paramResultSet2)
    throws SQLException;
  
  private static SimpleResultSet wrap(SimpleResultSet rs, TriggerRowSource source, Object[] row)
    throws SQLException
  {
    if (row == null) {
      return null;
    }
    source.setRow(row);
    rs.next();
    return rs;
  }
  
  public void remove()
    throws SQLException
  {}
  
  public void close()
    throws SQLException
  {}
}
