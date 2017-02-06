package org.h2.expression;

import java.util.HashMap;
import org.h2.command.Parser;
import org.h2.command.dml.Select;
import org.h2.command.dml.SelectListColumnResolver;
import org.h2.engine.Database;
import org.h2.engine.DbSettings;
import org.h2.engine.Session;
import org.h2.index.IndexCondition;
import org.h2.message.DbException;
import org.h2.schema.Constant;
import org.h2.schema.Schema;
import org.h2.table.Column;
import org.h2.table.ColumnResolver;
import org.h2.table.Table;
import org.h2.table.TableFilter;
import org.h2.value.Value;
import org.h2.value.ValueBoolean;

public class ExpressionColumn
  extends Expression
{
  private final Database database;
  private final String schemaName;
  private final String tableAlias;
  private final String columnName;
  private ColumnResolver columnResolver;
  private int queryLevel;
  private Column column;
  private boolean evaluatable;
  
  public ExpressionColumn(Database database, Column column)
  {
    this.database = database;
    this.column = column;
    this.schemaName = null;
    this.tableAlias = null;
    this.columnName = null;
  }
  
  public ExpressionColumn(Database database, String schemaName, String tableAlias, String columnName)
  {
    this.database = database;
    this.schemaName = schemaName;
    this.tableAlias = tableAlias;
    this.columnName = columnName;
  }
  
  public String getSQL()
  {
    boolean quote = this.database.getSettings().databaseToUpper;
    String sql;
    String sql;
    if (this.column != null) {
      sql = this.column.getSQL();
    } else {
      sql = quote ? Parser.quoteIdentifier(this.columnName) : this.columnName;
    }
    if (this.tableAlias != null)
    {
      String a = quote ? Parser.quoteIdentifier(this.tableAlias) : this.tableAlias;
      sql = a + "." + sql;
    }
    if (this.schemaName != null)
    {
      String s = quote ? Parser.quoteIdentifier(this.schemaName) : this.schemaName;
      sql = s + "." + sql;
    }
    return sql;
  }
  
  public TableFilter getTableFilter()
  {
    return this.columnResolver == null ? null : this.columnResolver.getTableFilter();
  }
  
  public void mapColumns(ColumnResolver resolver, int level)
  {
    if ((this.tableAlias != null) && (!this.database.equalsIdentifiers(this.tableAlias, resolver.getTableAlias()))) {
      return;
    }
    if ((this.schemaName != null) && (!this.database.equalsIdentifiers(this.schemaName, resolver.getSchemaName()))) {
      return;
    }
    for (Column col : resolver.getColumns())
    {
      String n = col.getName();
      if (this.database.equalsIdentifiers(this.columnName, n))
      {
        mapColumn(resolver, col, level);
        return;
      }
    }
    if (this.database.equalsIdentifiers("_ROWID_", this.columnName))
    {
      Column col = resolver.getRowIdColumn();
      if (col != null)
      {
        mapColumn(resolver, col, level);
        return;
      }
    }
    Column[] columns = resolver.getSystemColumns();
    for (int i = 0; (columns != null) && (i < columns.length); i++)
    {
      Column col = columns[i];
      if (this.database.equalsIdentifiers(this.columnName, col.getName()))
      {
        mapColumn(resolver, col, level);
        return;
      }
    }
  }
  
  private void mapColumn(ColumnResolver resolver, Column col, int level)
  {
    if (this.columnResolver == null)
    {
      this.queryLevel = level;
      this.column = col;
      this.columnResolver = resolver;
    }
    else if ((this.queryLevel == level) && (this.columnResolver != resolver) && 
      (!(resolver instanceof SelectListColumnResolver)))
    {
      throw DbException.get(90059, this.columnName);
    }
  }
  
  public Expression optimize(Session session)
  {
    if (this.columnResolver == null)
    {
      Schema schema = session.getDatabase().findSchema(this.tableAlias == null ? session.getCurrentSchemaName() : this.tableAlias);
      if (schema != null)
      {
        Constant constant = schema.findConstant(this.columnName);
        if (constant != null) {
          return constant.getValue();
        }
      }
      String name = this.columnName;
      if (this.tableAlias != null)
      {
        name = this.tableAlias + "." + name;
        if (this.schemaName != null) {
          name = this.schemaName + "." + name;
        }
      }
      throw DbException.get(42122, name);
    }
    return this.columnResolver.optimize(this, this.column);
  }
  
  public void updateAggregate(Session session)
  {
    Value now = this.columnResolver.getValue(this.column);
    Select select = this.columnResolver.getSelect();
    if (select == null) {
      throw DbException.get(90016, getSQL());
    }
    HashMap<Expression, Object> values = select.getCurrentGroup();
    if (values == null) {
      return;
    }
    Value v = (Value)values.get(this);
    if (v == null) {
      values.put(this, now);
    } else if (!this.database.areEqual(now, v)) {
      throw DbException.get(90016, getSQL());
    }
  }
  
  public Value getValue(Session session)
  {
    Select select = this.columnResolver.getSelect();
    if (select != null)
    {
      HashMap<Expression, Object> values = select.getCurrentGroup();
      if (values != null)
      {
        Value v = (Value)values.get(this);
        if (v != null) {
          return v;
        }
      }
    }
    Value value = this.columnResolver.getValue(this.column);
    if (value == null)
    {
      this.columnResolver.getValue(this.column);
      throw DbException.get(90016, getSQL());
    }
    return value;
  }
  
  public int getType()
  {
    return this.column.getType();
  }
  
  public void setEvaluatable(TableFilter tableFilter, boolean b)
  {
    if ((this.columnResolver != null) && (tableFilter == this.columnResolver.getTableFilter())) {
      this.evaluatable = b;
    }
  }
  
  public Column getColumn()
  {
    return this.column;
  }
  
  public int getScale()
  {
    return this.column.getScale();
  }
  
  public long getPrecision()
  {
    return this.column.getPrecision();
  }
  
  public int getDisplaySize()
  {
    return this.column.getDisplaySize();
  }
  
  public String getOriginalColumnName()
  {
    return this.columnName;
  }
  
  public String getOriginalTableAliasName()
  {
    return this.tableAlias;
  }
  
  public String getColumnName()
  {
    return this.columnName != null ? this.columnName : this.column.getName();
  }
  
  public String getSchemaName()
  {
    Table table = this.column.getTable();
    return table == null ? null : table.getSchema().getName();
  }
  
  public String getTableName()
  {
    Table table = this.column.getTable();
    return table == null ? null : table.getName();
  }
  
  public String getAlias()
  {
    if (this.column != null) {
      return this.column.getName();
    }
    if (this.tableAlias != null) {
      return this.tableAlias + "." + this.columnName;
    }
    return this.columnName;
  }
  
  public boolean isAutoIncrement()
  {
    return this.column.getSequence() != null;
  }
  
  public int getNullable()
  {
    return this.column.isNullable() ? 1 : 0;
  }
  
  public boolean isEverything(ExpressionVisitor visitor)
  {
    switch (visitor.getType())
    {
    case 1: 
      return false;
    case 2: 
    case 5: 
    case 8: 
      return true;
    case 0: 
      return this.queryLevel < visitor.getQueryLevel();
    case 3: 
      if (this.database.getSettings().nestedJoins)
      {
        if (visitor.getQueryLevel() < this.queryLevel) {
          return true;
        }
        if (getTableFilter() == null) {
          return false;
        }
        return getTableFilter().isEvaluatable();
      }
      return (this.evaluatable) || (visitor.getQueryLevel() < this.queryLevel);
    case 4: 
      visitor.addDataModificationId(this.column.getTable().getMaxDataModificationId());
      return true;
    case 6: 
      return this.columnResolver != visitor.getResolver();
    case 7: 
      if (this.column != null) {
        visitor.addDependency(this.column.getTable());
      }
      return true;
    case 9: 
      visitor.addColumn(this.column);
      return true;
    }
    throw DbException.throwInternalError("type=" + visitor.getType());
  }
  
  public int getCost()
  {
    return 2;
  }
  
  public void createIndexConditions(Session session, TableFilter filter)
  {
    TableFilter tf = getTableFilter();
    if ((filter == tf) && (this.column.getType() == 1))
    {
      IndexCondition cond = IndexCondition.get(0, this, ValueExpression.get(ValueBoolean.get(true)));
      
      filter.addIndexCondition(cond);
    }
  }
  
  public Expression getNotIfPossible(Session session)
  {
    return new Comparison(session, 0, this, ValueExpression.get(ValueBoolean.get(false)));
  }
}
