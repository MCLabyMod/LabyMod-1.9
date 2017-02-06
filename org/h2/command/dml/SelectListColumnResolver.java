package org.h2.command.dml;

import java.util.ArrayList;
import org.h2.expression.Expression;
import org.h2.expression.ExpressionColumn;
import org.h2.table.Column;
import org.h2.table.ColumnResolver;
import org.h2.table.TableFilter;
import org.h2.value.Value;

public class SelectListColumnResolver
  implements ColumnResolver
{
  private final Select select;
  private final Expression[] expressions;
  private final Column[] columns;
  
  SelectListColumnResolver(Select select)
  {
    this.select = select;
    int columnCount = select.getColumnCount();
    this.columns = new Column[columnCount];
    this.expressions = new Expression[columnCount];
    ArrayList<Expression> columnList = select.getExpressions();
    for (int i = 0; i < columnCount; i++)
    {
      Expression expr = (Expression)columnList.get(i);
      Column column = new Column(expr.getAlias(), 0);
      column.setTable(null, i);
      this.columns[i] = column;
      this.expressions[i] = expr.getNonAliasExpression();
    }
  }
  
  public Column[] getColumns()
  {
    return this.columns;
  }
  
  public String getSchemaName()
  {
    return null;
  }
  
  public Select getSelect()
  {
    return this.select;
  }
  
  public Column[] getSystemColumns()
  {
    return null;
  }
  
  public Column getRowIdColumn()
  {
    return null;
  }
  
  public String getTableAlias()
  {
    return null;
  }
  
  public TableFilter getTableFilter()
  {
    return null;
  }
  
  public Value getValue(Column column)
  {
    return null;
  }
  
  public Expression optimize(ExpressionColumn expressionColumn, Column column)
  {
    return this.expressions[column.getColumnId()];
  }
}
