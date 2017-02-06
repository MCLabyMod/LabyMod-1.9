package org.h2.command.dml;

import org.h2.expression.Expression;

public class SelectOrderBy
{
  public Expression expression;
  public Expression columnIndexExpr;
  public boolean descending;
  public boolean nullsFirst;
  public boolean nullsLast;
  
  public String getSQL()
  {
    StringBuilder buff = new StringBuilder();
    if (this.expression != null) {
      buff.append('=').append(this.expression.getSQL());
    } else {
      buff.append(this.columnIndexExpr.getSQL());
    }
    if (this.descending) {
      buff.append(" DESC");
    }
    if (this.nullsFirst) {
      buff.append(" NULLS FIRST");
    } else if (this.nullsLast) {
      buff.append(" NULLS LAST");
    }
    return buff.toString();
  }
}
