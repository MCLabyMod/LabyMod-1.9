package org.h2.table;

import java.util.ArrayList;
import org.h2.command.Parser;
import org.h2.command.dml.Select;
import org.h2.engine.Database;
import org.h2.engine.DbSettings;
import org.h2.engine.Mode;
import org.h2.engine.Session;
import org.h2.engine.SysProperties;
import org.h2.engine.User;
import org.h2.expression.ConditionAndOr;
import org.h2.expression.Expression;
import org.h2.expression.ExpressionColumn;
import org.h2.index.Index;
import org.h2.index.IndexCondition;
import org.h2.index.IndexCursor;
import org.h2.message.DbException;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.result.SortOrder;
import org.h2.schema.Schema;
import org.h2.util.New;
import org.h2.util.StatementBuilder;
import org.h2.util.StringUtils;
import org.h2.value.Value;
import org.h2.value.ValueLong;
import org.h2.value.ValueNull;

public class TableFilter
  implements ColumnResolver
{
  private static final int BEFORE_FIRST = 0;
  private static final int FOUND = 1;
  private static final int AFTER_LAST = 2;
  private static final int NULL_ROW = 3;
  protected boolean joinOuterIndirect;
  private Session session;
  private final Table table;
  private final Select select;
  private String alias;
  private Index index;
  private int scanCount;
  private boolean evaluatable;
  private boolean used;
  private final IndexCursor cursor;
  private final ArrayList<IndexCondition> indexConditions = New.arrayList();
  private Expression filterCondition;
  private Expression joinCondition;
  private SearchRow currentSearchRow;
  private Row current;
  private int state;
  private TableFilter join;
  private boolean joinOuter;
  private TableFilter nestedJoin;
  private ArrayList<Column> naturalJoinColumns;
  private boolean foundOne;
  private Expression fullCondition;
  private final int hashCode;
  
  public TableFilter(Session session, Table table, String alias, boolean rightsChecked, Select select)
  {
    this.session = session;
    this.table = table;
    this.alias = alias;
    this.select = select;
    this.cursor = new IndexCursor(this);
    if (!rightsChecked) {
      session.getUser().checkRight(table, 1);
    }
    this.hashCode = session.nextObjectId();
  }
  
  public Select getSelect()
  {
    return this.select;
  }
  
  public Table getTable()
  {
    return this.table;
  }
  
  public void lock(Session s, boolean exclusive, boolean forceLockEvenInMvcc)
  {
    this.table.lock(s, exclusive, forceLockEvenInMvcc);
    if (this.join != null) {
      this.join.lock(s, exclusive, forceLockEvenInMvcc);
    }
  }
  
  public PlanItem getBestPlanItem(Session s, int level)
  {
    PlanItem item;
    if (this.indexConditions.size() == 0)
    {
      PlanItem item = new PlanItem();
      item.setIndex(this.table.getScanIndex(s));
      item.cost = item.getIndex().getCost(s, null, null, null);
    }
    else
    {
      int len = this.table.getColumns().length;
      int[] masks = new int[len];
      for (IndexCondition condition : this.indexConditions) {
        if (condition.isEvaluatable())
        {
          if (condition.isAlwaysFalse())
          {
            masks = null;
            break;
          }
          int id = condition.getColumn().getColumnId();
          if (id >= 0) {
            masks[id] |= condition.getMask(this.indexConditions);
          }
        }
      }
      SortOrder sortOrder = null;
      if (this.select != null) {
        sortOrder = this.select.getSortOrder();
      }
      item = this.table.getBestPlanItem(s, masks, this, sortOrder);
      
      item.cost -= item.cost * this.indexConditions.size() / 100.0D / level;
    }
    if (this.nestedJoin != null)
    {
      setEvaluatable(this.nestedJoin);
      item.setNestedJoinPlan(this.nestedJoin.getBestPlanItem(s, level));
      
      item.cost += item.cost * item.getNestedJoinPlan().cost;
    }
    if (this.join != null)
    {
      setEvaluatable(this.join);
      item.setJoinPlan(this.join.getBestPlanItem(s, level));
      
      item.cost += item.cost * item.getJoinPlan().cost;
    }
    return item;
  }
  
  private void setEvaluatable(TableFilter join)
  {
    if (this.session.getDatabase().getSettings().nestedJoins) {
      setEvaluatable(true);
    } else {
      do
      {
        Expression e = join.getJoinCondition();
        if (e != null) {
          e.setEvaluatable(this, true);
        }
        TableFilter n = join.getNestedJoin();
        if (n != null) {
          setEvaluatable(n);
        }
        join = join.getJoin();
      } while (join != null);
    }
  }
  
  public void setPlanItem(PlanItem item)
  {
    if (item == null) {
      return;
    }
    setIndex(item.getIndex());
    if ((this.nestedJoin != null) && 
      (item.getNestedJoinPlan() != null)) {
      this.nestedJoin.setPlanItem(item.getNestedJoinPlan());
    }
    if ((this.join != null) && 
      (item.getJoinPlan() != null)) {
      this.join.setPlanItem(item.getJoinPlan());
    }
  }
  
  public void prepare()
  {
    for (int i = 0; i < this.indexConditions.size(); i++)
    {
      IndexCondition condition = (IndexCondition)this.indexConditions.get(i);
      if (!condition.isAlwaysFalse())
      {
        Column col = condition.getColumn();
        if ((col.getColumnId() >= 0) && 
          (this.index.getColumnIndex(col) < 0))
        {
          this.indexConditions.remove(i);
          i--;
        }
      }
    }
    if (this.nestedJoin != null)
    {
      if ((SysProperties.CHECK) && (this.nestedJoin == this)) {
        DbException.throwInternalError("self join");
      }
      this.nestedJoin.prepare();
    }
    if (this.join != null)
    {
      if ((SysProperties.CHECK) && (this.join == this)) {
        DbException.throwInternalError("self join");
      }
      this.join.prepare();
    }
    if (this.filterCondition != null) {
      this.filterCondition = this.filterCondition.optimize(this.session);
    }
    if (this.joinCondition != null) {
      this.joinCondition = this.joinCondition.optimize(this.session);
    }
  }
  
  public void startQuery(Session s)
  {
    this.session = s;
    this.scanCount = 0;
    if (this.nestedJoin != null) {
      this.nestedJoin.startQuery(s);
    }
    if (this.join != null) {
      this.join.startQuery(s);
    }
  }
  
  public void reset()
  {
    if (this.nestedJoin != null) {
      this.nestedJoin.reset();
    }
    if (this.join != null) {
      this.join.reset();
    }
    this.state = 0;
    this.foundOne = false;
  }
  
  public boolean next()
  {
    if (this.state == 2) {
      return false;
    }
    if (this.state == 0)
    {
      this.cursor.find(this.session, this.indexConditions);
      if (!this.cursor.isAlwaysFalse())
      {
        if (this.nestedJoin != null) {
          this.nestedJoin.reset();
        }
        if (this.join != null) {
          this.join.reset();
        }
      }
    }
    else if ((this.join != null) && (this.join.next()))
    {
      return true;
    }
    while (this.state != 3)
    {
      if (this.cursor.isAlwaysFalse())
      {
        this.state = 2;
      }
      else if (this.nestedJoin != null)
      {
        if (this.state == 0) {
          this.state = 1;
        }
      }
      else
      {
        if ((++this.scanCount & 0xFFF) == 0) {
          checkTimeout();
        }
        if (this.cursor.next())
        {
          this.currentSearchRow = this.cursor.getSearchRow();
          this.current = null;
          this.state = 1;
        }
        else
        {
          this.state = 2;
        }
      }
      if ((this.nestedJoin != null) && (this.state == 1) && 
        (!this.nestedJoin.next()))
      {
        this.state = 2;
        if ((!this.joinOuter) || (this.foundOne)) {}
      }
      else
      {
        if (this.state == 2)
        {
          if ((!this.joinOuter) || (this.foundOne)) {
            break;
          }
          setNullRow();
        }
        if (isOk(this.filterCondition))
        {
          boolean joinConditionOk = isOk(this.joinCondition);
          if (this.state == 1)
          {
            if (joinConditionOk) {
              this.foundOne = true;
            }
          }
          else if (this.join != null)
          {
            this.join.reset();
            if (!this.join.next()) {}
          }
          else if ((this.state == 3) || (joinConditionOk))
          {
            return true;
          }
        }
      }
    }
    this.state = 2;
    return false;
  }
  
  protected void setNullRow()
  {
    this.state = 3;
    this.current = this.table.getNullRow();
    this.currentSearchRow = this.current;
    if (this.nestedJoin != null) {
      this.nestedJoin.visit(new TableFilterVisitor()
      {
        public void accept(TableFilter f)
        {
          f.setNullRow();
        }
      });
    }
  }
  
  private void checkTimeout()
  {
    this.session.checkCanceled();
  }
  
  private boolean isOk(Expression condition)
  {
    if (condition == null) {
      return true;
    }
    return Boolean.TRUE.equals(condition.getBooleanValue(this.session));
  }
  
  public Row get()
  {
    if ((this.current == null) && (this.currentSearchRow != null)) {
      this.current = this.cursor.get();
    }
    return this.current;
  }
  
  public void set(Row current)
  {
    this.current = current;
    this.currentSearchRow = current;
  }
  
  public String getTableAlias()
  {
    if (this.alias != null) {
      return this.alias;
    }
    return this.table.getName();
  }
  
  public void addIndexCondition(IndexCondition condition)
  {
    this.indexConditions.add(condition);
  }
  
  public ArrayList<IndexCondition> getIndexConditionsForColumn(Column column)
  {
    ArrayList<IndexCondition> conditions = New.arrayList(this.indexConditions.size());
    for (IndexCondition condition : this.indexConditions) {
      if (column.equals(condition.getColumn())) {
        conditions.add(condition);
      }
    }
    return conditions;
  }
  
  public void addFilterCondition(Expression condition, boolean isJoin)
  {
    if (isJoin)
    {
      if (this.joinCondition == null) {
        this.joinCondition = condition;
      } else {
        this.joinCondition = new ConditionAndOr(0, this.joinCondition, condition);
      }
    }
    else if (this.filterCondition == null) {
      this.filterCondition = condition;
    } else {
      this.filterCondition = new ConditionAndOr(0, this.filterCondition, condition);
    }
  }
  
  public void addJoin(TableFilter filter, boolean outer, boolean nested, final Expression on)
  {
    if (on != null)
    {
      on.mapColumns(this, 0);
      if (this.session.getDatabase().getSettings().nestedJoins)
      {
        visit(new TableFilterVisitor()
        {
          public void accept(TableFilter f)
          {
            on.mapColumns(f, 0);
          }
        });
        filter.visit(new TableFilterVisitor()
        {
          public void accept(TableFilter f)
          {
            on.mapColumns(f, 0);
          }
        });
      }
    }
    if ((nested) && (this.session.getDatabase().getSettings().nestedJoins))
    {
      if (this.nestedJoin != null) {
        throw DbException.throwInternalError();
      }
      this.nestedJoin = filter;
      filter.joinOuter = outer;
      if (outer) {
        visit(new TableFilterVisitor()
        {
          public void accept(TableFilter f)
          {
            f.joinOuterIndirect = true;
          }
        });
      }
      if (on != null) {
        filter.mapAndAddFilter(on);
      }
    }
    else if (this.join == null)
    {
      this.join = filter;
      filter.joinOuter = outer;
      if (this.session.getDatabase().getSettings().nestedJoins)
      {
        if (outer) {
          filter.visit(new TableFilterVisitor()
          {
            public void accept(TableFilter f)
            {
              f.joinOuterIndirect = true;
            }
          });
        }
      }
      else if (outer)
      {
        TableFilter f = filter.join;
        while (f != null)
        {
          f.joinOuter = true;
          f = f.join;
        }
      }
      if (on != null) {
        filter.mapAndAddFilter(on);
      }
    }
    else
    {
      this.join.addJoin(filter, outer, nested, on);
    }
  }
  
  public void mapAndAddFilter(Expression on)
  {
    on.mapColumns(this, 0);
    addFilterCondition(on, true);
    on.createIndexConditions(this.session, this);
    if (this.nestedJoin != null)
    {
      on.mapColumns(this.nestedJoin, 0);
      on.createIndexConditions(this.session, this.nestedJoin);
    }
    if (this.join != null) {
      this.join.mapAndAddFilter(on);
    }
  }
  
  public TableFilter getJoin()
  {
    return this.join;
  }
  
  public boolean isJoinOuter()
  {
    return this.joinOuter;
  }
  
  public boolean isJoinOuterIndirect()
  {
    return this.joinOuterIndirect;
  }
  
  public String getPlanSQL(boolean isJoin)
  {
    StringBuilder buff = new StringBuilder();
    if (isJoin) {
      if (this.joinOuter) {
        buff.append("LEFT OUTER JOIN ");
      } else {
        buff.append("INNER JOIN ");
      }
    }
    if (this.nestedJoin != null)
    {
      StringBuffer buffNested = new StringBuffer();
      TableFilter n = this.nestedJoin;
      do
      {
        buffNested.append(n.getPlanSQL(n != this.nestedJoin));
        buffNested.append('\n');
        n = n.getJoin();
      } while (n != null);
      String nested = buffNested.toString();
      boolean enclose = !nested.startsWith("(");
      if (enclose) {
        buff.append("(\n");
      }
      buff.append(StringUtils.indent(nested, 4, false));
      if (enclose) {
        buff.append(')');
      }
      if (isJoin)
      {
        buff.append(" ON ");
        if (this.joinCondition == null) {
          buff.append("1=1");
        } else {
          buff.append(StringUtils.unEnclose(this.joinCondition.getSQL()));
        }
      }
      return buff.toString();
    }
    buff.append(this.table.getSQL());
    if (this.alias != null) {
      buff.append(' ').append(Parser.quoteIdentifier(this.alias));
    }
    if (this.index != null)
    {
      buff.append('\n');
      StatementBuilder planBuff = new StatementBuilder();
      planBuff.append(this.index.getPlanSQL());
      if (this.indexConditions.size() > 0)
      {
        planBuff.append(": ");
        for (IndexCondition condition : this.indexConditions)
        {
          planBuff.appendExceptFirst("\n    AND ");
          planBuff.append(condition.getSQL());
        }
      }
      String plan = StringUtils.quoteRemarkSQL(planBuff.toString());
      if (plan.indexOf('\n') >= 0) {
        plan = plan + "\n";
      }
      buff.append(StringUtils.indent("/* " + plan + " */", 4, false));
    }
    if (isJoin)
    {
      buff.append("\n    ON ");
      if (this.joinCondition == null) {
        buff.append("1=1");
      } else {
        buff.append(StringUtils.unEnclose(this.joinCondition.getSQL()));
      }
    }
    if (this.filterCondition != null)
    {
      buff.append('\n');
      String condition = StringUtils.unEnclose(this.filterCondition.getSQL());
      condition = "/* WHERE " + StringUtils.quoteRemarkSQL(condition) + "\n*/";
      buff.append(StringUtils.indent(condition, 4, false));
    }
    if (this.scanCount > 0) {
      buff.append("\n    /* scanCount: ").append(this.scanCount).append(" */");
    }
    return buff.toString();
  }
  
  void removeUnusableIndexConditions()
  {
    for (int i = 0; i < this.indexConditions.size(); i++)
    {
      IndexCondition cond = (IndexCondition)this.indexConditions.get(i);
      if (!cond.isEvaluatable()) {
        this.indexConditions.remove(i--);
      }
    }
  }
  
  public Index getIndex()
  {
    return this.index;
  }
  
  public void setIndex(Index index)
  {
    this.index = index;
    this.cursor.setIndex(index);
  }
  
  public void setUsed(boolean used)
  {
    this.used = used;
  }
  
  public boolean isUsed()
  {
    return this.used;
  }
  
  void setSession(Session session)
  {
    this.session = session;
  }
  
  public void removeJoin()
  {
    this.join = null;
  }
  
  public Expression getJoinCondition()
  {
    return this.joinCondition;
  }
  
  public void removeJoinCondition()
  {
    this.joinCondition = null;
  }
  
  public Expression getFilterCondition()
  {
    return this.filterCondition;
  }
  
  public void removeFilterCondition()
  {
    this.filterCondition = null;
  }
  
  public void setFullCondition(Expression condition)
  {
    this.fullCondition = condition;
    if (this.join != null) {
      this.join.setFullCondition(condition);
    }
  }
  
  void optimizeFullCondition(boolean fromOuterJoin)
  {
    if (this.fullCondition != null)
    {
      this.fullCondition.addFilterConditions(this, (fromOuterJoin) || (this.joinOuter));
      if (this.nestedJoin != null) {
        this.nestedJoin.optimizeFullCondition((fromOuterJoin) || (this.joinOuter));
      }
      if (this.join != null) {
        this.join.optimizeFullCondition((fromOuterJoin) || (this.joinOuter));
      }
    }
  }
  
  public void setEvaluatable(TableFilter filter, boolean b)
  {
    filter.setEvaluatable(b);
    if (this.filterCondition != null) {
      this.filterCondition.setEvaluatable(filter, b);
    }
    if (this.joinCondition != null) {
      this.joinCondition.setEvaluatable(filter, b);
    }
    if (this.nestedJoin != null) {
      if (this == filter) {
        this.nestedJoin.setEvaluatable(this.nestedJoin, b);
      }
    }
    if (this.join != null) {
      this.join.setEvaluatable(filter, b);
    }
  }
  
  public void setEvaluatable(boolean evaluatable)
  {
    this.evaluatable = evaluatable;
  }
  
  public String getSchemaName()
  {
    return this.table.getSchema().getName();
  }
  
  public Column[] getColumns()
  {
    return this.table.getColumns();
  }
  
  public Column[] getSystemColumns()
  {
    if (!this.session.getDatabase().getMode().systemColumns) {
      return null;
    }
    Column[] sys = new Column[3];
    sys[0] = new Column("oid", 4);
    sys[0].setTable(this.table, 0);
    sys[1] = new Column("ctid", 13);
    sys[1].setTable(this.table, 0);
    sys[2] = new Column("CTID", 13);
    sys[2].setTable(this.table, 0);
    return sys;
  }
  
  public Column getRowIdColumn()
  {
    if (this.session.getDatabase().getSettings().rowId) {
      return this.table.getRowIdColumn();
    }
    return null;
  }
  
  public Value getValue(Column column)
  {
    if (this.currentSearchRow == null) {
      return null;
    }
    int columnId = column.getColumnId();
    if (columnId == -1) {
      return ValueLong.get(this.currentSearchRow.getKey());
    }
    if (this.current == null)
    {
      Value v = this.currentSearchRow.getValue(columnId);
      if (v != null) {
        return v;
      }
      this.current = this.cursor.get();
      if (this.current == null) {
        return ValueNull.INSTANCE;
      }
    }
    return this.current.getValue(columnId);
  }
  
  public TableFilter getTableFilter()
  {
    return this;
  }
  
  public void setAlias(String alias)
  {
    this.alias = alias;
  }
  
  public Expression optimize(ExpressionColumn expressionColumn, Column column)
  {
    return expressionColumn;
  }
  
  public String toString()
  {
    return this.alias != null ? this.alias : this.table.toString();
  }
  
  public void addNaturalJoinColumn(Column c)
  {
    if (this.naturalJoinColumns == null) {
      this.naturalJoinColumns = New.arrayList();
    }
    this.naturalJoinColumns.add(c);
  }
  
  public boolean isNaturalJoinColumn(Column c)
  {
    return (this.naturalJoinColumns != null) && (this.naturalJoinColumns.contains(c));
  }
  
  public int hashCode()
  {
    return this.hashCode;
  }
  
  public boolean hasInComparisons()
  {
    for (IndexCondition cond : this.indexConditions)
    {
      int compareType = cond.getCompareType();
      if ((compareType == 10) || (compareType == 9)) {
        return true;
      }
    }
    return false;
  }
  
  public void lockRowAdd(ArrayList<Row> rows)
  {
    if (this.state == 1) {
      rows.add(get());
    }
  }
  
  public void lockRows(ArrayList<Row> forUpdateRows)
  {
    for (Row row : forUpdateRows)
    {
      Row newRow = row.getCopy();
      this.table.removeRow(this.session, row);
      this.session.log(this.table, (short)1, row);
      this.table.addRow(this.session, newRow);
      this.session.log(this.table, (short)0, newRow);
    }
  }
  
  public TableFilter getNestedJoin()
  {
    return this.nestedJoin;
  }
  
  public void visit(TableFilterVisitor visitor)
  {
    TableFilter f = this;
    do
    {
      visitor.accept(f);
      TableFilter n = f.nestedJoin;
      if (n != null) {
        n.visit(visitor);
      }
      f = f.join;
    } while (f != null);
  }
  
  public boolean isEvaluatable()
  {
    return this.evaluatable;
  }
  
  public Session getSession()
  {
    return this.session;
  }
  
  public static abstract interface TableFilterVisitor
  {
    public abstract void accept(TableFilter paramTableFilter);
  }
}
