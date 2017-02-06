package org.h2.index;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.h2.engine.Session;
import org.h2.message.DbException;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.result.SortOrder;
import org.h2.table.Column;
import org.h2.table.IndexColumn;
import org.h2.table.Table;
import org.h2.table.TableFilter;
import org.h2.table.TableLink;
import org.h2.util.New;
import org.h2.util.StatementBuilder;
import org.h2.value.Value;
import org.h2.value.ValueNull;

public class LinkedIndex
  extends BaseIndex
{
  private final TableLink link;
  private final String targetTableName;
  private long rowCount;
  
  public LinkedIndex(TableLink table, int id, IndexColumn[] columns, IndexType indexType)
  {
    initBaseIndex(table, id, null, columns, indexType);
    this.link = table;
    this.targetTableName = this.link.getQualifiedTable();
  }
  
  public String getCreateSQL()
  {
    return null;
  }
  
  public void close(Session session) {}
  
  private static boolean isNull(Value v)
  {
    return (v == null) || (v == ValueNull.INSTANCE);
  }
  
  public void add(Session session, Row row)
  {
    ArrayList<Value> params = New.arrayList();
    StatementBuilder buff = new StatementBuilder("INSERT INTO ");
    buff.append(this.targetTableName).append(" VALUES(");
    for (int i = 0; i < row.getColumnCount(); i++)
    {
      Value v = row.getValue(i);
      buff.appendExceptFirst(", ");
      if (v == null)
      {
        buff.append("DEFAULT");
      }
      else if (isNull(v))
      {
        buff.append("NULL");
      }
      else
      {
        buff.append('?');
        params.add(v);
      }
    }
    buff.append(')');
    String sql = buff.toString();
    try
    {
      this.link.execute(sql, params, true);
      this.rowCount += 1L;
    }
    catch (Exception e)
    {
      throw TableLink.wrapException(sql, e);
    }
  }
  
  public Cursor find(Session session, SearchRow first, SearchRow last)
  {
    ArrayList<Value> params = New.arrayList();
    StatementBuilder buff = new StatementBuilder("SELECT * FROM ");
    buff.append(this.targetTableName).append(" T");
    for (int i = 0; (first != null) && (i < first.getColumnCount()); i++)
    {
      Value v = first.getValue(i);
      if (v != null)
      {
        buff.appendOnlyFirst(" WHERE ");
        buff.appendExceptFirst(" AND ");
        Column col = this.table.getColumn(i);
        buff.append(col.getSQL());
        if (v == ValueNull.INSTANCE)
        {
          buff.append(" IS NULL");
        }
        else
        {
          buff.append(">=");
          addParameter(buff, col);
          params.add(v);
        }
      }
    }
    for (int i = 0; (last != null) && (i < last.getColumnCount()); i++)
    {
      Value v = last.getValue(i);
      if (v != null)
      {
        buff.appendOnlyFirst(" WHERE ");
        buff.appendExceptFirst(" AND ");
        Column col = this.table.getColumn(i);
        buff.append(col.getSQL());
        if (v == ValueNull.INSTANCE)
        {
          buff.append(" IS NULL");
        }
        else
        {
          buff.append("<=");
          addParameter(buff, col);
          params.add(v);
        }
      }
    }
    String sql = buff.toString();
    try
    {
      PreparedStatement prep = this.link.execute(sql, params, false);
      ResultSet rs = prep.getResultSet();
      return new LinkedCursor(this.link, rs, session, sql, prep);
    }
    catch (Exception e)
    {
      throw TableLink.wrapException(sql, e);
    }
  }
  
  private void addParameter(StatementBuilder buff, Column col)
  {
    if ((col.getType() == 21) && (this.link.isOracle())) {
      buff.append("CAST(? AS CHAR(").append(col.getPrecision()).append("))");
    } else {
      buff.append('?');
    }
  }
  
  public double getCost(Session session, int[] masks, TableFilter filter, SortOrder sortOrder)
  {
    return 100L + getCostRangeIndex(masks, this.rowCount + 1000L, filter, sortOrder);
  }
  
  public void remove(Session session) {}
  
  public void truncate(Session session) {}
  
  public void checkRename()
  {
    throw DbException.getUnsupportedException("LINKED");
  }
  
  public boolean needRebuild()
  {
    return false;
  }
  
  public boolean canGetFirstOrLast()
  {
    return false;
  }
  
  public Cursor findFirstOrLast(Session session, boolean first)
  {
    throw DbException.getUnsupportedException("LINKED");
  }
  
  public void remove(Session session, Row row)
  {
    ArrayList<Value> params = New.arrayList();
    StatementBuilder buff = new StatementBuilder("DELETE FROM ");
    buff.append(this.targetTableName).append(" WHERE ");
    for (int i = 0; i < row.getColumnCount(); i++)
    {
      buff.appendExceptFirst("AND ");
      Column col = this.table.getColumn(i);
      buff.append(col.getSQL());
      Value v = row.getValue(i);
      if (isNull(v))
      {
        buff.append(" IS NULL ");
      }
      else
      {
        buff.append('=');
        addParameter(buff, col);
        params.add(v);
        buff.append(' ');
      }
    }
    String sql = buff.toString();
    try
    {
      PreparedStatement prep = this.link.execute(sql, params, false);
      int count = prep.executeUpdate();
      this.link.reusePreparedStatement(prep, sql);
      this.rowCount -= count;
    }
    catch (Exception e)
    {
      throw TableLink.wrapException(sql, e);
    }
  }
  
  public void update(Row oldRow, Row newRow)
  {
    ArrayList<Value> params = New.arrayList();
    StatementBuilder buff = new StatementBuilder("UPDATE ");
    buff.append(this.targetTableName).append(" SET ");
    for (int i = 0; i < newRow.getColumnCount(); i++)
    {
      buff.appendExceptFirst(", ");
      buff.append(this.table.getColumn(i).getSQL()).append('=');
      Value v = newRow.getValue(i);
      if (v == null)
      {
        buff.append("DEFAULT");
      }
      else
      {
        buff.append('?');
        params.add(v);
      }
    }
    buff.append(" WHERE ");
    buff.resetCount();
    for (int i = 0; i < oldRow.getColumnCount(); i++)
    {
      Column col = this.table.getColumn(i);
      buff.appendExceptFirst(" AND ");
      buff.append(col.getSQL());
      Value v = oldRow.getValue(i);
      if (isNull(v))
      {
        buff.append(" IS NULL");
      }
      else
      {
        buff.append('=');
        params.add(v);
        addParameter(buff, col);
      }
    }
    String sql = buff.toString();
    try
    {
      this.link.execute(sql, params, true);
    }
    catch (Exception e)
    {
      throw TableLink.wrapException(sql, e);
    }
  }
  
  public long getRowCount(Session session)
  {
    return this.rowCount;
  }
  
  public long getRowCountApproximation()
  {
    return this.rowCount;
  }
  
  public long getDiskSpaceUsed()
  {
    return 0L;
  }
}
