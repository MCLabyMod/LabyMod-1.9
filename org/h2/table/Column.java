package org.h2.table;

import java.util.HashSet;
import org.h2.command.Parser;
import org.h2.engine.Database;
import org.h2.engine.Mode;
import org.h2.engine.Session;
import org.h2.expression.ConditionAndOr;
import org.h2.expression.Expression;
import org.h2.expression.ExpressionVisitor;
import org.h2.expression.SequenceValue;
import org.h2.expression.ValueExpression;
import org.h2.message.DbException;
import org.h2.result.Row;
import org.h2.schema.Schema;
import org.h2.schema.Sequence;
import org.h2.util.MathUtils;
import org.h2.util.StringUtils;
import org.h2.value.DataType;
import org.h2.value.Value;
import org.h2.value.ValueDate;
import org.h2.value.ValueInt;
import org.h2.value.ValueLong;
import org.h2.value.ValueNull;
import org.h2.value.ValueString;
import org.h2.value.ValueTime;
import org.h2.value.ValueTimestamp;
import org.h2.value.ValueUuid;

public class Column
{
  public static final String ROWID = "_ROWID_";
  public static final int NOT_NULLABLE = 0;
  public static final int NULLABLE = 1;
  public static final int NULLABLE_UNKNOWN = 2;
  private final int type;
  private long precision;
  private int scale;
  private int displaySize;
  private Table table;
  private String name;
  private int columnId;
  private boolean nullable = true;
  private Expression defaultExpression;
  private Expression checkConstraint;
  private String checkConstraintSQL;
  private String originalSQL;
  private boolean autoIncrement;
  private long start;
  private long increment;
  private boolean convertNullToDefault;
  private Sequence sequence;
  private boolean isComputed;
  private TableFilter computeTableFilter;
  private int selectivity;
  private SingleColumnResolver resolver;
  private String comment;
  private boolean primaryKey;
  
  public Column(String name, int type)
  {
    this(name, type, -1L, -1, -1);
  }
  
  public Column(String name, int type, long precision, int scale, int displaySize)
  {
    this.name = name;
    this.type = type;
    if ((precision == -1L) && (scale == -1) && (displaySize == -1))
    {
      DataType dt = DataType.getDataType(type);
      precision = dt.defaultPrecision;
      scale = dt.defaultScale;
      displaySize = dt.defaultDisplaySize;
    }
    this.precision = precision;
    this.scale = scale;
    this.displaySize = displaySize;
  }
  
  public boolean equals(Object o)
  {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Column)) {
      return false;
    }
    Column other = (Column)o;
    if ((this.table == null) || (other.table == null) || (this.name == null) || (other.name == null)) {
      return false;
    }
    if (this.table != other.table) {
      return false;
    }
    return this.name.equals(other.name);
  }
  
  public int hashCode()
  {
    if ((this.table == null) || (this.name == null)) {
      return 0;
    }
    return this.table.getId() ^ this.name.hashCode();
  }
  
  public Column getClone()
  {
    Column newColumn = new Column(this.name, this.type, this.precision, this.scale, this.displaySize);
    newColumn.copy(this);
    return newColumn;
  }
  
  public Value convert(Value v)
  {
    try
    {
      return v.convertTo(this.type);
    }
    catch (DbException e)
    {
      if (e.getErrorCode() == 22018)
      {
        String target = (this.table == null ? "" : new StringBuilder().append(this.table.getName()).append(": ").toString()) + getCreateSQL();
        
        throw DbException.get(22018, v.getSQL() + " (" + target + ")");
      }
      throw e;
    }
  }
  
  boolean getComputed()
  {
    return this.isComputed;
  }
  
  synchronized Value computeValue(Session session, Row row)
  {
    this.computeTableFilter.setSession(session);
    this.computeTableFilter.set(row);
    return this.defaultExpression.getValue(session);
  }
  
  public void setComputedExpression(Expression expression)
  {
    this.isComputed = true;
    this.defaultExpression = expression;
  }
  
  public void setTable(Table table, int columnId)
  {
    this.table = table;
    this.columnId = columnId;
  }
  
  public Table getTable()
  {
    return this.table;
  }
  
  public void setDefaultExpression(Session session, Expression defaultExpression)
  {
    if (defaultExpression != null)
    {
      defaultExpression = defaultExpression.optimize(session);
      if (defaultExpression.isConstant()) {
        defaultExpression = ValueExpression.get(defaultExpression.getValue(session));
      }
    }
    this.defaultExpression = defaultExpression;
  }
  
  public int getColumnId()
  {
    return this.columnId;
  }
  
  public String getSQL()
  {
    return Parser.quoteIdentifier(this.name);
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public int getType()
  {
    return this.type;
  }
  
  public long getPrecision()
  {
    return this.precision;
  }
  
  public void setPrecision(long p)
  {
    this.precision = p;
  }
  
  public int getDisplaySize()
  {
    return this.displaySize;
  }
  
  public int getScale()
  {
    return this.scale;
  }
  
  public void setNullable(boolean b)
  {
    this.nullable = b;
  }
  
  public Value validateConvertUpdateSequence(Session session, Value value)
  {
    if (value == null) {
      if (this.defaultExpression == null)
      {
        value = ValueNull.INSTANCE;
      }
      else
      {
        synchronized (this)
        {
          value = this.defaultExpression.getValue(session).convertTo(this.type);
        }
        if (this.primaryKey) {
          session.setLastIdentity(value);
        }
      }
    }
    Mode mode = session.getDatabase().getMode();
    if (value == ValueNull.INSTANCE)
    {
      if (this.convertNullToDefault) {
        synchronized (this)
        {
          value = this.defaultExpression.getValue(session).convertTo(this.type);
        }
      }
      if ((value == ValueNull.INSTANCE) && (!this.nullable)) {
        if (mode.convertInsertNullToZero)
        {
          DataType dt = DataType.getDataType(this.type);
          if (dt.decimal) {
            value = ValueInt.get(0).convertTo(this.type);
          } else if (dt.type == 11) {
            value = ValueTimestamp.fromMillis(session.getTransactionStart());
          } else if (dt.type == 9) {
            value = ValueTime.fromNanos(0L);
          } else if (dt.type == 10) {
            value = ValueDate.fromMillis(session.getTransactionStart());
          } else {
            value = ValueString.get("").convertTo(this.type);
          }
        }
        else
        {
          throw DbException.get(23502, this.name);
        }
      }
    }
    if (this.checkConstraint != null)
    {
      this.resolver.setValue(value);
      Value v;
      synchronized (this)
      {
        v = this.checkConstraint.getValue(session);
      }
      if (Boolean.FALSE.equals(v.getBoolean())) {
        throw DbException.get(23513, this.checkConstraint.getSQL());
      }
    }
    value = value.convertScale(mode.convertOnlyToSmallerScale, this.scale);
    if ((this.precision > 0L) && 
      (!value.checkPrecision(this.precision)))
    {
      String s = value.getTraceSQL();
      if (s.length() > 127) {
        s = s.substring(0, 128) + "...";
      }
      throw DbException.get(22001, new String[] { getCreateSQL(), s + " (" + value.getPrecision() + ")" });
    }
    updateSequenceIfRequired(session, value);
    return value;
  }
  
  private void updateSequenceIfRequired(Session session, Value value)
  {
    if (this.sequence != null)
    {
      long current = this.sequence.getCurrentValue();
      long inc = this.sequence.getIncrement();
      long now = value.getLong();
      boolean update = false;
      if ((inc > 0L) && (now > current)) {
        update = true;
      } else if ((inc < 0L) && (now < current)) {
        update = true;
      }
      if (update)
      {
        this.sequence.modify(Long.valueOf(now + inc), null, null, null);
        session.setLastIdentity(ValueLong.get(now));
        this.sequence.flush(session);
      }
    }
  }
  
  public void convertAutoIncrementToSequence(Session session, Schema schema, int id, boolean temporary)
  {
    if (!this.autoIncrement) {
      DbException.throwInternalError();
    }
    if ("IDENTITY".equals(this.originalSQL)) {
      this.originalSQL = "BIGINT";
    } else if ("SERIAL".equals(this.originalSQL)) {
      this.originalSQL = "INT";
    }
    String sequenceName;
    for (;;)
    {
      ValueUuid uuid = ValueUuid.getNewRandom();
      String s = uuid.getString();
      s = s.replace('-', '_').toUpperCase();
      sequenceName = "SYSTEM_SEQUENCE_" + s;
      if (schema.findSequence(sequenceName) == null) {
        break;
      }
    }
    Sequence seq = new Sequence(schema, id, sequenceName, this.start, this.increment);
    if (temporary) {
      seq.setTemporary(true);
    } else {
      session.getDatabase().addSchemaObject(session, seq);
    }
    setAutoIncrement(false, 0L, 0L);
    SequenceValue seqValue = new SequenceValue(seq);
    setDefaultExpression(session, seqValue);
    setSequence(seq);
  }
  
  public void prepareExpression(Session session)
  {
    if (this.defaultExpression != null)
    {
      this.computeTableFilter = new TableFilter(session, this.table, null, false, null);
      this.defaultExpression.mapColumns(this.computeTableFilter, 0);
      this.defaultExpression = this.defaultExpression.optimize(session);
    }
  }
  
  public String getCreateSQL()
  {
    StringBuilder buff = new StringBuilder();
    if (this.name != null) {
      buff.append(Parser.quoteIdentifier(this.name)).append(' ');
    }
    if (this.originalSQL != null)
    {
      buff.append(this.originalSQL);
    }
    else
    {
      buff.append(DataType.getDataType(this.type).name);
      switch (this.type)
      {
      case 6: 
        buff.append('(').append(this.precision).append(", ").append(this.scale).append(')');
        break;
      case 12: 
      case 13: 
      case 14: 
      case 21: 
        if (this.precision < 2147483647L) {
          buff.append('(').append(this.precision).append(')');
        }
        break;
      }
    }
    if (this.defaultExpression != null)
    {
      String sql = this.defaultExpression.getSQL();
      if (sql != null) {
        if (this.isComputed) {
          buff.append(" AS ").append(sql);
        } else if (this.defaultExpression != null) {
          buff.append(" DEFAULT ").append(sql);
        }
      }
    }
    if (!this.nullable) {
      buff.append(" NOT NULL");
    }
    if (this.convertNullToDefault) {
      buff.append(" NULL_TO_DEFAULT");
    }
    if (this.sequence != null) {
      buff.append(" SEQUENCE ").append(this.sequence.getSQL());
    }
    if (this.selectivity != 0) {
      buff.append(" SELECTIVITY ").append(this.selectivity);
    }
    if (this.comment != null) {
      buff.append(" COMMENT ").append(StringUtils.quoteStringSQL(this.comment));
    }
    if (this.checkConstraint != null) {
      buff.append(" CHECK ").append(this.checkConstraintSQL);
    }
    return buff.toString();
  }
  
  public boolean isNullable()
  {
    return this.nullable;
  }
  
  public void setOriginalSQL(String original)
  {
    this.originalSQL = original;
  }
  
  public String getOriginalSQL()
  {
    return this.originalSQL;
  }
  
  public Expression getDefaultExpression()
  {
    return this.defaultExpression;
  }
  
  public boolean isAutoIncrement()
  {
    return this.autoIncrement;
  }
  
  public void setAutoIncrement(boolean autoInc, long start, long increment)
  {
    this.autoIncrement = autoInc;
    this.start = start;
    this.increment = increment;
    this.nullable = false;
    if (autoInc) {
      this.convertNullToDefault = true;
    }
  }
  
  public void setConvertNullToDefault(boolean convert)
  {
    this.convertNullToDefault = convert;
  }
  
  public void rename(String newName)
  {
    this.name = newName;
  }
  
  public void setSequence(Sequence sequence)
  {
    this.sequence = sequence;
  }
  
  public Sequence getSequence()
  {
    return this.sequence;
  }
  
  public int getSelectivity()
  {
    return this.selectivity == 0 ? 50 : this.selectivity;
  }
  
  public void setSelectivity(int selectivity)
  {
    selectivity = selectivity > 100 ? 100 : selectivity < 0 ? 0 : selectivity;
    this.selectivity = selectivity;
  }
  
  public void addCheckConstraint(Session session, Expression expr)
  {
    if (expr == null) {
      return;
    }
    this.resolver = new SingleColumnResolver(this);
    synchronized (this)
    {
      String oldName = this.name;
      if (this.name == null) {
        this.name = "VALUE";
      }
      expr.mapColumns(this.resolver, 0);
      this.name = oldName;
    }
    expr = expr.optimize(session);
    this.resolver.setValue(ValueNull.INSTANCE);
    synchronized (this)
    {
      expr.getValue(session);
    }
    if (this.checkConstraint == null) {
      this.checkConstraint = expr;
    } else {
      this.checkConstraint = new ConditionAndOr(0, this.checkConstraint, expr);
    }
    this.checkConstraintSQL = getCheckConstraintSQL(session, this.name);
  }
  
  public void removeCheckConstraint()
  {
    this.checkConstraint = null;
    this.checkConstraintSQL = null;
  }
  
  public Expression getCheckConstraint(Session session, String asColumnName)
  {
    if (this.checkConstraint == null) {
      return null;
    }
    Parser parser = new Parser(session);
    String sql;
    synchronized (this)
    {
      String oldName = this.name;
      this.name = asColumnName;
      sql = this.checkConstraint.getSQL();
      this.name = oldName;
    }
    Expression expr = parser.parseExpression(sql);
    return expr;
  }
  
  String getDefaultSQL()
  {
    return this.defaultExpression == null ? null : this.defaultExpression.getSQL();
  }
  
  int getPrecisionAsInt()
  {
    return MathUtils.convertLongToInt(this.precision);
  }
  
  DataType getDataType()
  {
    return DataType.getDataType(this.type);
  }
  
  String getCheckConstraintSQL(Session session, String asColumnName)
  {
    Expression constraint = getCheckConstraint(session, asColumnName);
    return constraint == null ? "" : constraint.getSQL();
  }
  
  public void setComment(String comment)
  {
    this.comment = comment;
  }
  
  public String getComment()
  {
    return this.comment;
  }
  
  public void setPrimaryKey(boolean primaryKey)
  {
    this.primaryKey = primaryKey;
  }
  
  boolean isEverything(ExpressionVisitor visitor)
  {
    if ((visitor.getType() == 7) && 
      (this.sequence != null)) {
      visitor.getDependencies().add(this.sequence);
    }
    if ((this.defaultExpression != null) && (!this.defaultExpression.isEverything(visitor))) {
      return false;
    }
    if ((this.checkConstraint != null) && (!this.checkConstraint.isEverything(visitor))) {
      return false;
    }
    return true;
  }
  
  public boolean isPrimaryKey()
  {
    return this.primaryKey;
  }
  
  public String toString()
  {
    return this.name;
  }
  
  public boolean isWideningConversion(Column newColumn)
  {
    if (this.type != newColumn.type) {
      return false;
    }
    if (this.precision > newColumn.precision) {
      return false;
    }
    if (this.scale != newColumn.scale) {
      return false;
    }
    if ((this.nullable) && (!newColumn.nullable)) {
      return false;
    }
    if (this.convertNullToDefault != newColumn.convertNullToDefault) {
      return false;
    }
    if (this.primaryKey != newColumn.primaryKey) {
      return false;
    }
    if ((this.autoIncrement) || (newColumn.autoIncrement)) {
      return false;
    }
    if ((this.checkConstraint != null) || (newColumn.checkConstraint != null)) {
      return false;
    }
    if ((this.convertNullToDefault) || (newColumn.convertNullToDefault)) {
      return false;
    }
    if ((this.defaultExpression != null) || (newColumn.defaultExpression != null)) {
      return false;
    }
    if ((this.isComputed) || (newColumn.isComputed)) {
      return false;
    }
    return true;
  }
  
  public void copy(Column source)
  {
    this.checkConstraint = source.checkConstraint;
    this.checkConstraintSQL = source.checkConstraintSQL;
    this.displaySize = source.displaySize;
    this.name = source.name;
    this.precision = source.precision;
    this.scale = source.scale;
    
    this.nullable = source.nullable;
    this.defaultExpression = source.defaultExpression;
    this.originalSQL = source.originalSQL;
    
    this.convertNullToDefault = source.convertNullToDefault;
    this.sequence = source.sequence;
    this.comment = source.comment;
    this.computeTableFilter = source.computeTableFilter;
    this.isComputed = source.isComputed;
    this.selectivity = source.selectivity;
    this.primaryKey = source.primaryKey;
  }
}
