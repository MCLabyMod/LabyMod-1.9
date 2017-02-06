package org.h2.table;

import org.h2.command.dml.Select;
import org.h2.expression.Expression;
import org.h2.expression.ExpressionColumn;
import org.h2.value.Value;

public abstract interface ColumnResolver
{
  public abstract String getTableAlias();
  
  public abstract Column[] getColumns();
  
  public abstract Column[] getSystemColumns();
  
  public abstract Column getRowIdColumn();
  
  public abstract String getSchemaName();
  
  public abstract Value getValue(Column paramColumn);
  
  public abstract TableFilter getTableFilter();
  
  public abstract Select getSelect();
  
  public abstract Expression optimize(ExpressionColumn paramExpressionColumn, Column paramColumn);
}
