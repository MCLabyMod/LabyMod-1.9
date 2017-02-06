package org.h2.expression;

import java.util.ArrayList;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.message.DbException;
import org.h2.result.LocalResult;
import org.h2.result.ResultInterface;
import org.h2.table.Column;
import org.h2.tools.SimpleResultSet;
import org.h2.util.MathUtils;
import org.h2.util.StatementBuilder;
import org.h2.value.DataType;
import org.h2.value.Value;
import org.h2.value.ValueArray;
import org.h2.value.ValueNull;
import org.h2.value.ValueResultSet;

public class TableFunction
  extends Function
{
  private final boolean distinct;
  private final long rowCount;
  private Column[] columnList;
  
  TableFunction(Database database, FunctionInfo info, long rowCount)
  {
    super(database, info);
    this.distinct = (info.type == 224);
    this.rowCount = rowCount;
  }
  
  public Value getValue(Session session)
  {
    return getTable(session, this.args, false, this.distinct);
  }
  
  protected void checkParameterCount(int len)
  {
    if (len < 1) {
      throw DbException.get(7001, new String[] { getName(), ">0" });
    }
  }
  
  public String getSQL()
  {
    StatementBuilder buff = new StatementBuilder(getName());
    buff.append('(');
    int i = 0;
    for (Expression e : this.args)
    {
      buff.appendExceptFirst(", ");
      buff.append(this.columnList[(i++)].getCreateSQL()).append('=').append(e.getSQL());
    }
    return buff.append(')').toString();
  }
  
  public String getName()
  {
    return this.distinct ? "TABLE_DISTINCT" : "TABLE";
  }
  
  public ValueResultSet getValueForColumnList(Session session, Expression[] nullArgs)
  {
    return getTable(session, this.args, true, false);
  }
  
  public void setColumns(ArrayList<Column> columns)
  {
    this.columnList = new Column[columns.size()];
    columns.toArray(this.columnList);
  }
  
  private ValueResultSet getTable(Session session, Expression[] argList, boolean onlyColumnList, boolean distinctRows)
  {
    int len = this.columnList.length;
    Expression[] header = new Expression[len];
    Database db = session.getDatabase();
    for (int i = 0; i < len; i++)
    {
      Column c = this.columnList[i];
      ExpressionColumn col = new ExpressionColumn(db, c);
      header[i] = col;
    }
    LocalResult result = new LocalResult(session, header, len);
    if (distinctRows) {
      result.setDistinct();
    }
    if (!onlyColumnList)
    {
      Value[][] list = new Value[len][];
      int rows = 0;
      for (int i = 0; i < len; i++)
      {
        Value v = argList[i].getValue(session);
        if (v == ValueNull.INSTANCE)
        {
          list[i] = new Value[0];
        }
        else
        {
          ValueArray array = (ValueArray)v.convertTo(17);
          Value[] l = array.getList();
          list[i] = l;
          rows = Math.max(rows, l.length);
        }
      }
      for (int row = 0; row < rows; row++)
      {
        Value[] r = new Value[len];
        for (int j = 0; j < len; j++)
        {
          Value[] l = list[j];
          Value v;
          Value v;
          if (l.length <= row)
          {
            v = ValueNull.INSTANCE;
          }
          else
          {
            Column c = this.columnList[j];
            v = l[row];
            v = c.convert(v);
            v = v.convertPrecision(c.getPrecision(), false);
            v = v.convertScale(true, c.getScale());
          }
          r[j] = v;
        }
        result.addRow(r);
      }
    }
    result.done();
    ValueResultSet vr = ValueResultSet.get(getSimpleResultSet(result, Integer.MAX_VALUE));
    
    return vr;
  }
  
  private static SimpleResultSet getSimpleResultSet(ResultInterface rs, int maxrows)
  {
    int columnCount = rs.getVisibleColumnCount();
    SimpleResultSet simple = new SimpleResultSet();
    simple.setAutoClose(false);
    for (int i = 0; i < columnCount; i++)
    {
      String name = rs.getColumnName(i);
      int sqlType = DataType.convertTypeToSQLType(rs.getColumnType(i));
      int precision = MathUtils.convertLongToInt(rs.getColumnPrecision(i));
      int scale = rs.getColumnScale(i);
      simple.addColumn(name, sqlType, precision, scale);
    }
    rs.reset();
    for (int i = 0; (i < maxrows) && (rs.next()); i++)
    {
      Object[] list = new Object[columnCount];
      for (int j = 0; j < columnCount; j++) {
        list[j] = rs.currentRow()[j].getObject();
      }
      simple.addRow(list);
    }
    return simple;
  }
  
  public long getRowCount()
  {
    return this.rowCount;
  }
  
  public Expression[] getExpressionColumns(Session session)
  {
    return getExpressionColumns(session, getTable(session, getArgs(), true, false).getResultSet());
  }
}
