package org.h2.expression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import org.h2.command.dml.Select;
import org.h2.command.dml.SelectOrderBy;
import org.h2.engine.Session;
import org.h2.index.Cursor;
import org.h2.index.Index;
import org.h2.message.DbException;
import org.h2.result.SearchRow;
import org.h2.result.SortOrder;
import org.h2.table.Column;
import org.h2.table.ColumnResolver;
import org.h2.table.IndexColumn;
import org.h2.table.Table;
import org.h2.table.TableFilter;
import org.h2.util.New;
import org.h2.util.StatementBuilder;
import org.h2.util.StringUtils;
import org.h2.value.DataType;
import org.h2.value.Value;
import org.h2.value.ValueArray;
import org.h2.value.ValueLong;
import org.h2.value.ValueNull;
import org.h2.value.ValueString;

public class Aggregate
  extends Expression
{
  public static final int COUNT_ALL = 0;
  public static final int COUNT = 1;
  public static final int GROUP_CONCAT = 2;
  static final int SUM = 3;
  static final int MIN = 4;
  static final int MAX = 5;
  static final int AVG = 6;
  static final int STDDEV_POP = 7;
  static final int STDDEV_SAMP = 8;
  static final int VAR_POP = 9;
  static final int VAR_SAMP = 10;
  static final int BOOL_OR = 11;
  static final int BOOL_AND = 12;
  static final int BIT_OR = 13;
  static final int BIT_AND = 14;
  static final int SELECTIVITY = 15;
  static final int HISTOGRAM = 16;
  private static final HashMap<String, Integer> AGGREGATES = ;
  private final int type;
  private final Select select;
  private final boolean distinct;
  private Expression on;
  private Expression groupConcatSeparator;
  private ArrayList<SelectOrderBy> groupConcatOrderList;
  private SortOrder groupConcatSort;
  private int dataType;
  private int scale;
  private long precision;
  private int displaySize;
  private int lastGroupRowId;
  
  public Aggregate(int type, Expression on, Select select, boolean distinct)
  {
    this.type = type;
    this.on = on;
    this.select = select;
    this.distinct = distinct;
  }
  
  static
  {
    addAggregate("COUNT", 1);
    addAggregate("SUM", 3);
    addAggregate("MIN", 4);
    addAggregate("MAX", 5);
    addAggregate("AVG", 6);
    addAggregate("GROUP_CONCAT", 2);
    
    addAggregate("STRING_AGG", 2);
    addAggregate("STDDEV_SAMP", 8);
    addAggregate("STDDEV", 8);
    addAggregate("STDDEV_POP", 7);
    addAggregate("STDDEVP", 7);
    addAggregate("VAR_POP", 9);
    addAggregate("VARP", 9);
    addAggregate("VAR_SAMP", 10);
    addAggregate("VAR", 10);
    addAggregate("VARIANCE", 10);
    addAggregate("BOOL_OR", 11);
    
    addAggregate("SOME", 11);
    addAggregate("BOOL_AND", 12);
    
    addAggregate("EVERY", 12);
    addAggregate("SELECTIVITY", 15);
    addAggregate("HISTOGRAM", 16);
    addAggregate("BIT_OR", 13);
    addAggregate("BIT_AND", 14);
  }
  
  private static void addAggregate(String name, int type)
  {
    AGGREGATES.put(name, Integer.valueOf(type));
  }
  
  public static int getAggregateType(String name)
  {
    Integer type = (Integer)AGGREGATES.get(name);
    return type == null ? -1 : type.intValue();
  }
  
  public void setGroupConcatOrder(ArrayList<SelectOrderBy> orderBy)
  {
    this.groupConcatOrderList = orderBy;
  }
  
  public void setGroupConcatSeparator(Expression separator)
  {
    this.groupConcatSeparator = separator;
  }
  
  private SortOrder initOrder(Session session)
  {
    int size = this.groupConcatOrderList.size();
    int[] index = new int[size];
    int[] sortType = new int[size];
    for (int i = 0; i < size; i++)
    {
      SelectOrderBy o = (SelectOrderBy)this.groupConcatOrderList.get(i);
      index[i] = (i + 1);
      int order = o.descending ? 1 : 0;
      sortType[i] = order;
    }
    return new SortOrder(session.getDatabase(), index, sortType, null);
  }
  
  public void updateAggregate(Session session)
  {
    HashMap<Expression, Object> group = this.select.getCurrentGroup();
    if (group == null) {
      return;
    }
    int groupRowId = this.select.getCurrentGroupRowId();
    if (this.lastGroupRowId == groupRowId) {
      return;
    }
    this.lastGroupRowId = groupRowId;
    
    AggregateData data = (AggregateData)group.get(this);
    if (data == null)
    {
      data = AggregateData.create(this.type);
      group.put(this, data);
    }
    Value v = this.on == null ? null : this.on.getValue(session);
    if ((this.type == 2) && 
      (v != ValueNull.INSTANCE))
    {
      v = v.convertTo(13);
      if (this.groupConcatOrderList != null)
      {
        int size = this.groupConcatOrderList.size();
        Value[] array = new Value[1 + size];
        array[0] = v;
        for (int i = 0; i < size; i++)
        {
          SelectOrderBy o = (SelectOrderBy)this.groupConcatOrderList.get(i);
          array[(i + 1)] = o.expression.getValue(session);
        }
        v = ValueArray.get(array);
      }
    }
    data.add(session.getDatabase(), this.dataType, this.distinct, v);
  }
  
  public Value getValue(Session session)
  {
    if (this.select.isQuickAggregateQuery())
    {
      switch (this.type)
      {
      case 0: 
      case 1: 
        Table table = this.select.getTopTableFilter().getTable();
        return ValueLong.get(table.getRowCount(session));
      case 4: 
      case 5: 
        boolean first = this.type == 4;
        Index index = getColumnIndex();
        int sortType = index.getIndexColumns()[0].sortType;
        if ((sortType & 0x1) != 0) {
          first = !first;
        }
        Cursor cursor = index.findFirstOrLast(session, first);
        SearchRow row = cursor.getSearchRow();
        Value v;
        Value v;
        if (row == null) {
          v = ValueNull.INSTANCE;
        } else {
          v = row.getValue(index.getColumns()[0].getColumnId());
        }
        return v;
      }
      DbException.throwInternalError("type=" + this.type);
    }
    HashMap<Expression, Object> group = this.select.getCurrentGroup();
    if (group == null) {
      throw DbException.get(90054, getSQL());
    }
    AggregateData data = (AggregateData)group.get(this);
    if (data == null) {
      data = AggregateData.create(this.type);
    }
    Value v = data.getValue(session.getDatabase(), this.dataType, this.distinct);
    if (this.type == 2)
    {
      ArrayList<Value> list = ((AggregateDataGroupConcat)data).getList();
      if ((list == null) || (list.size() == 0)) {
        return ValueNull.INSTANCE;
      }
      if (this.groupConcatOrderList != null)
      {
        final SortOrder sortOrder = this.groupConcatSort;
        Collections.sort(list, new Comparator()
        {
          public int compare(Value v1, Value v2)
          {
            Value[] a1 = ((ValueArray)v1).getList();
            Value[] a2 = ((ValueArray)v2).getList();
            return sortOrder.compare(a1, a2);
          }
        });
      }
      StatementBuilder buff = new StatementBuilder();
      String sep = this.groupConcatSeparator == null ? "," : this.groupConcatSeparator.getValue(session).getString();
      for (Value val : list)
      {
        String s;
        String s;
        if (val.getType() == 17) {
          s = ((ValueArray)val).getList()[0].getString();
        } else {
          s = val.getString();
        }
        if (s != null)
        {
          if (sep != null) {
            buff.appendExceptFirst(sep);
          }
          buff.append(s);
        }
      }
      v = ValueString.get(buff.toString());
    }
    return v;
  }
  
  public int getType()
  {
    return this.dataType;
  }
  
  public void mapColumns(ColumnResolver resolver, int level)
  {
    if (this.on != null) {
      this.on.mapColumns(resolver, level);
    }
    if (this.groupConcatOrderList != null) {
      for (SelectOrderBy o : this.groupConcatOrderList) {
        o.expression.mapColumns(resolver, level);
      }
    }
    if (this.groupConcatSeparator != null) {
      this.groupConcatSeparator.mapColumns(resolver, level);
    }
  }
  
  public Expression optimize(Session session)
  {
    if (this.on != null)
    {
      this.on = this.on.optimize(session);
      this.dataType = this.on.getType();
      this.scale = this.on.getScale();
      this.precision = this.on.getPrecision();
      this.displaySize = this.on.getDisplaySize();
    }
    if (this.groupConcatOrderList != null)
    {
      for (SelectOrderBy o : this.groupConcatOrderList) {
        o.expression = o.expression.optimize(session);
      }
      this.groupConcatSort = initOrder(session);
    }
    if (this.groupConcatSeparator != null) {
      this.groupConcatSeparator = this.groupConcatSeparator.optimize(session);
    }
    switch (this.type)
    {
    case 2: 
      this.dataType = 13;
      this.scale = 0;
      this.precision = (this.displaySize = Integer.MAX_VALUE);
      break;
    case 0: 
    case 1: 
      this.dataType = 5;
      this.scale = 0;
      this.precision = 19L;
      this.displaySize = 20;
      break;
    case 15: 
      this.dataType = 4;
      this.scale = 0;
      this.precision = 10L;
      this.displaySize = 11;
      break;
    case 16: 
      this.dataType = 17;
      this.scale = 0;
      this.precision = (this.displaySize = Integer.MAX_VALUE);
      break;
    case 3: 
      if (this.dataType == 1)
      {
        this.dataType = 5;
      }
      else
      {
        if (!DataType.supportsAdd(this.dataType)) {
          throw DbException.get(90015, getSQL());
        }
        this.dataType = DataType.getAddProofType(this.dataType);
      }
      break;
    case 6: 
      if (!DataType.supportsAdd(this.dataType)) {
        throw DbException.get(90015, getSQL());
      }
      break;
    case 4: 
    case 5: 
      break;
    case 7: 
    case 8: 
    case 9: 
    case 10: 
      this.dataType = 7;
      this.precision = 17L;
      this.displaySize = 24;
      this.scale = 0;
      break;
    case 11: 
    case 12: 
      this.dataType = 1;
      this.precision = 1L;
      this.displaySize = 5;
      this.scale = 0;
      break;
    case 13: 
    case 14: 
      if (!DataType.supportsAdd(this.dataType)) {
        throw DbException.get(90015, getSQL());
      }
      break;
    default: 
      DbException.throwInternalError("type=" + this.type);
    }
    return this;
  }
  
  public void setEvaluatable(TableFilter tableFilter, boolean b)
  {
    if (this.on != null) {
      this.on.setEvaluatable(tableFilter, b);
    }
    if (this.groupConcatOrderList != null) {
      for (SelectOrderBy o : this.groupConcatOrderList) {
        o.expression.setEvaluatable(tableFilter, b);
      }
    }
    if (this.groupConcatSeparator != null) {
      this.groupConcatSeparator.setEvaluatable(tableFilter, b);
    }
  }
  
  public int getScale()
  {
    return this.scale;
  }
  
  public long getPrecision()
  {
    return this.precision;
  }
  
  public int getDisplaySize()
  {
    return this.displaySize;
  }
  
  private String getSQLGroupConcat()
  {
    StatementBuilder buff = new StatementBuilder("GROUP_CONCAT(");
    if (this.distinct) {
      buff.append("DISTINCT ");
    }
    buff.append(this.on.getSQL());
    if (this.groupConcatOrderList != null)
    {
      buff.append(" ORDER BY ");
      for (SelectOrderBy o : this.groupConcatOrderList)
      {
        buff.appendExceptFirst(", ");
        buff.append(o.expression.getSQL());
        if (o.descending) {
          buff.append(" DESC");
        }
      }
    }
    if (this.groupConcatSeparator != null) {
      buff.append(" SEPARATOR ").append(this.groupConcatSeparator.getSQL());
    }
    return buff.append(')').toString();
  }
  
  public String getSQL()
  {
    String text;
    switch (this.type)
    {
    case 2: 
      return getSQLGroupConcat();
    case 0: 
      return "COUNT(*)";
    case 1: 
      text = "COUNT";
      break;
    case 15: 
      text = "SELECTIVITY";
      break;
    case 16: 
      text = "HISTOGRAM";
      break;
    case 3: 
      text = "SUM";
      break;
    case 4: 
      text = "MIN";
      break;
    case 5: 
      text = "MAX";
      break;
    case 6: 
      text = "AVG";
      break;
    case 7: 
      text = "STDDEV_POP";
      break;
    case 8: 
      text = "STDDEV_SAMP";
      break;
    case 9: 
      text = "VAR_POP";
      break;
    case 10: 
      text = "VAR_SAMP";
      break;
    case 12: 
      text = "BOOL_AND";
      break;
    case 11: 
      text = "BOOL_OR";
      break;
    case 14: 
      text = "BIT_AND";
      break;
    case 13: 
      text = "BIT_OR";
      break;
    default: 
      throw DbException.throwInternalError("type=" + this.type);
    }
    if (this.distinct) {
      return text + "(DISTINCT " + this.on.getSQL() + ")";
    }
    return text + StringUtils.enclose(this.on.getSQL());
  }
  
  private Index getColumnIndex()
  {
    if ((this.on instanceof ExpressionColumn))
    {
      ExpressionColumn col = (ExpressionColumn)this.on;
      Column column = col.getColumn();
      TableFilter filter = col.getTableFilter();
      if (filter != null)
      {
        Table table = filter.getTable();
        Index index = table.getIndexForColumn(column);
        return index;
      }
    }
    return null;
  }
  
  public boolean isEverything(ExpressionVisitor visitor)
  {
    if (visitor.getType() == 1)
    {
      switch (this.type)
      {
      case 1: 
        if ((!this.distinct) && (this.on.getNullable() == 0)) {
          return visitor.getTable().canGetRowCount();
        }
        return false;
      case 0: 
        return visitor.getTable().canGetRowCount();
      case 4: 
      case 5: 
        Index index = getColumnIndex();
        return index != null;
      }
      return false;
    }
    if ((this.on != null) && (!this.on.isEverything(visitor))) {
      return false;
    }
    if ((this.groupConcatSeparator != null) && (!this.groupConcatSeparator.isEverything(visitor))) {
      return false;
    }
    if (this.groupConcatOrderList != null)
    {
      int i = 0;
      for (int size = this.groupConcatOrderList.size(); i < size; i++)
      {
        SelectOrderBy o = (SelectOrderBy)this.groupConcatOrderList.get(i);
        if (!o.expression.isEverything(visitor)) {
          return false;
        }
      }
    }
    return true;
  }
  
  public int getCost()
  {
    return this.on == null ? 1 : this.on.getCost() + 1;
  }
}
