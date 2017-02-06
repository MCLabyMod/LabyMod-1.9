package org.h2.constraint;

import java.util.HashSet;
import java.util.Iterator;
import org.h2.command.Prepared;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.expression.Expression;
import org.h2.expression.ExpressionVisitor;
import org.h2.index.Index;
import org.h2.message.DbException;
import org.h2.result.ResultInterface;
import org.h2.result.Row;
import org.h2.schema.Schema;
import org.h2.table.Column;
import org.h2.table.Table;
import org.h2.table.TableFilter;
import org.h2.util.New;
import org.h2.util.StringUtils;
import org.h2.value.Value;

public class ConstraintCheck
  extends Constraint
{
  private TableFilter filter;
  private Expression expr;
  
  public ConstraintCheck(Schema schema, int id, String name, Table table)
  {
    super(schema, id, name, table);
  }
  
  public String getConstraintType()
  {
    return "CHECK";
  }
  
  public void setTableFilter(TableFilter filter)
  {
    this.filter = filter;
  }
  
  public void setExpression(Expression expr)
  {
    this.expr = expr;
  }
  
  public String getCreateSQLForCopy(Table forTable, String quotedName)
  {
    StringBuilder buff = new StringBuilder("ALTER TABLE ");
    buff.append(forTable.getSQL()).append(" ADD CONSTRAINT ");
    if (forTable.isHidden()) {
      buff.append("IF NOT EXISTS ");
    }
    buff.append(quotedName);
    if (this.comment != null) {
      buff.append(" COMMENT ").append(StringUtils.quoteStringSQL(this.comment));
    }
    buff.append(" CHECK").append(StringUtils.enclose(this.expr.getSQL())).append(" NOCHECK");
    
    return buff.toString();
  }
  
  private String getShortDescription()
  {
    return getName() + ": " + this.expr.getSQL();
  }
  
  public String getCreateSQLWithoutIndexes()
  {
    return getCreateSQL();
  }
  
  public String getCreateSQL()
  {
    return getCreateSQLForCopy(this.table, getSQL());
  }
  
  public void removeChildrenAndResources(Session session)
  {
    this.table.removeConstraint(this);
    this.database.removeMeta(session, getId());
    this.filter = null;
    this.expr = null;
    this.table = null;
    invalidate();
  }
  
  public void checkRow(Session session, Table t, Row oldRow, Row newRow)
  {
    if (newRow == null) {
      return;
    }
    this.filter.set(newRow);
    Boolean b;
    try
    {
      b = this.expr.getValue(session).getBoolean();
    }
    catch (DbException ex)
    {
      throw DbException.get(23514, ex, new String[] { getShortDescription() });
    }
    if (Boolean.FALSE.equals(b)) {
      throw DbException.get(23513, getShortDescription());
    }
  }
  
  public boolean usesIndex(Index index)
  {
    return false;
  }
  
  public void setIndexOwner(Index index)
  {
    DbException.throwInternalError();
  }
  
  public HashSet<Column> getReferencedColumns(Table table)
  {
    HashSet<Column> columns = New.hashSet();
    this.expr.isEverything(ExpressionVisitor.getColumnsVisitor(columns));
    for (Iterator<Column> it = columns.iterator(); it.hasNext();) {
      if (((Column)it.next()).getTable() != table) {
        it.remove();
      }
    }
    return columns;
  }
  
  public Expression getExpression()
  {
    return this.expr;
  }
  
  public boolean isBefore()
  {
    return true;
  }
  
  public void checkExistingData(Session session)
  {
    if (session.getDatabase().isStarting()) {
      return;
    }
    String sql = "SELECT 1 FROM " + this.filter.getTable().getSQL() + " WHERE NOT(" + this.expr.getSQL() + ")";
    
    ResultInterface r = session.prepare(sql).query(1);
    if (r.next()) {
      throw DbException.get(23513, getName());
    }
  }
  
  public Index getUniqueIndex()
  {
    return null;
  }
  
  public void rebuild() {}
  
  public boolean isEverything(ExpressionVisitor visitor)
  {
    return this.expr.isEverything(visitor);
  }
}
