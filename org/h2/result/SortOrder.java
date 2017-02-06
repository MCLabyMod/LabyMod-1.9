package org.h2.result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.h2.command.dml.SelectOrderBy;
import org.h2.engine.Database;
import org.h2.engine.SysProperties;
import org.h2.expression.Expression;
import org.h2.expression.ExpressionColumn;
import org.h2.table.Column;
import org.h2.table.TableFilter;
import org.h2.util.StatementBuilder;
import org.h2.util.StringUtils;
import org.h2.util.Utils;
import org.h2.value.Value;
import org.h2.value.ValueNull;

public class SortOrder
  implements Comparator<Value[]>
{
  public static final int ASCENDING = 0;
  public static final int DESCENDING = 1;
  public static final int NULLS_FIRST = 2;
  public static final int NULLS_LAST = 4;
  private static final int DEFAULT_NULL_SORT = SysProperties.SORT_NULLS_HIGH ? 1 : -1;
  private final Database database;
  private final int[] queryColumnIndexes;
  private final int[] sortTypes;
  private final ArrayList<SelectOrderBy> orderList;
  
  public SortOrder(Database database, int[] queryColumnIndexes, int[] sortType, ArrayList<SelectOrderBy> orderList)
  {
    this.database = database;
    this.queryColumnIndexes = queryColumnIndexes;
    this.sortTypes = sortType;
    this.orderList = orderList;
  }
  
  public String getSQL(Expression[] list, int visible)
  {
    StatementBuilder buff = new StatementBuilder();
    int i = 0;
    for (int idx : this.queryColumnIndexes)
    {
      buff.appendExceptFirst(", ");
      if (idx < visible) {
        buff.append(idx + 1);
      } else {
        buff.append('=').append(StringUtils.unEnclose(list[idx].getSQL()));
      }
      int type = this.sortTypes[(i++)];
      if ((type & 0x1) != 0) {
        buff.append(" DESC");
      }
      if ((type & 0x2) != 0) {
        buff.append(" NULLS FIRST");
      } else if ((type & 0x4) != 0) {
        buff.append(" NULLS LAST");
      }
    }
    return buff.toString();
  }
  
  public static int compareNull(boolean aNull, int sortType)
  {
    if ((sortType & 0x2) != 0) {
      return aNull ? -1 : 1;
    }
    if ((sortType & 0x4) != 0) {
      return aNull ? 1 : -1;
    }
    int comp = aNull ? DEFAULT_NULL_SORT : -DEFAULT_NULL_SORT;
    return (sortType & 0x1) == 0 ? comp : -comp;
  }
  
  public int compare(Value[] a, Value[] b)
  {
    int i = 0;
    for (int len = this.queryColumnIndexes.length; i < len; i++)
    {
      int idx = this.queryColumnIndexes[i];
      int type = this.sortTypes[i];
      Value ao = a[idx];
      Value bo = b[idx];
      boolean aNull = ao == ValueNull.INSTANCE;boolean bNull = bo == ValueNull.INSTANCE;
      if ((aNull) || (bNull))
      {
        if (aNull != bNull) {
          return compareNull(aNull, type);
        }
      }
      else
      {
        int comp = this.database.compare(ao, bo);
        if (comp != 0) {
          return (type & 0x1) == 0 ? comp : -comp;
        }
      }
    }
    return 0;
  }
  
  public void sort(ArrayList<Value[]> rows)
  {
    Collections.sort(rows, this);
  }
  
  public void sort(ArrayList<Value[]> rows, int offset, int limit)
  {
    int rowsSize = rows.size();
    if ((rows.isEmpty()) || (offset >= rowsSize) || (limit == 0)) {
      return;
    }
    if (offset < 0) {
      offset = 0;
    }
    if (offset + limit > rowsSize) {
      limit = rowsSize - offset;
    }
    if ((limit == 1) && (offset == 0))
    {
      rows.set(0, Collections.min(rows, this));
      return;
    }
    Value[][] arr = (Value[][])rows.toArray(new Value[rowsSize][]);
    Utils.sortTopN(arr, offset, limit, this);
    int i = 0;
    for (int end = Math.min(offset + limit, rowsSize); i < end; i++) {
      rows.set(i, arr[i]);
    }
  }
  
  public int[] getQueryColumnIndexes()
  {
    return this.queryColumnIndexes;
  }
  
  public Column getColumn(int index, TableFilter filter)
  {
    if (this.orderList == null) {
      return null;
    }
    SelectOrderBy order = (SelectOrderBy)this.orderList.get(index);
    Expression expr = order.expression;
    if (expr == null) {
      return null;
    }
    expr = expr.getNonAliasExpression();
    if (expr.isConstant()) {
      return null;
    }
    if (!(expr instanceof ExpressionColumn)) {
      return null;
    }
    ExpressionColumn exprCol = (ExpressionColumn)expr;
    if (exprCol.getTableFilter() != filter) {
      return null;
    }
    return exprCol.getColumn();
  }
  
  public int[] getSortTypes()
  {
    return this.sortTypes;
  }
}
