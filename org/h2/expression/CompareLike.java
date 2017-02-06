package org.h2.expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.h2.engine.Database;
import org.h2.engine.DbSettings;
import org.h2.engine.Session;
import org.h2.index.IndexCondition;
import org.h2.message.DbException;
import org.h2.table.Column;
import org.h2.table.ColumnResolver;
import org.h2.table.TableFilter;
import org.h2.value.CompareMode;
import org.h2.value.Value;
import org.h2.value.ValueBoolean;
import org.h2.value.ValueNull;
import org.h2.value.ValueString;

public class CompareLike
  extends Condition
{
  private static final int MATCH = 0;
  private static final int ONE = 1;
  private static final int ANY = 2;
  private final CompareMode compareMode;
  private final String defaultEscape;
  private Expression left;
  private Expression right;
  private Expression escape;
  private boolean isInit;
  private char[] patternChars;
  private String patternString;
  private int[] patternTypes;
  private int patternLength;
  private final boolean regexp;
  private Pattern patternRegexp;
  private boolean ignoreCase;
  private boolean fastCompare;
  private boolean invalidPattern;
  
  public CompareLike(Database db, Expression left, Expression right, Expression escape, boolean regexp)
  {
    this(db.getCompareMode(), db.getSettings().defaultEscape, left, right, escape, regexp);
  }
  
  public CompareLike(CompareMode compareMode, String defaultEscape, Expression left, Expression right, Expression escape, boolean regexp)
  {
    this.compareMode = compareMode;
    this.defaultEscape = defaultEscape;
    this.regexp = regexp;
    this.left = left;
    this.right = right;
    this.escape = escape;
  }
  
  private static Character getEscapeChar(String s)
  {
    return (s == null) || (s.length() == 0) ? null : Character.valueOf(s.charAt(0));
  }
  
  public String getSQL()
  {
    String sql;
    String sql;
    if (this.regexp)
    {
      sql = this.left.getSQL() + " REGEXP " + this.right.getSQL();
    }
    else
    {
      sql = this.left.getSQL() + " LIKE " + this.right.getSQL();
      if (this.escape != null) {
        sql = sql + " ESCAPE " + this.escape.getSQL();
      }
    }
    return "(" + sql + ")";
  }
  
  public Expression optimize(Session session)
  {
    this.left = this.left.optimize(session);
    this.right = this.right.optimize(session);
    if (this.left.getType() == 14) {
      this.ignoreCase = true;
    }
    if (this.left.isValueSet())
    {
      Value l = this.left.getValue(session);
      if (l == ValueNull.INSTANCE) {
        return ValueExpression.getNull();
      }
    }
    if (this.escape != null) {
      this.escape = this.escape.optimize(session);
    }
    if ((this.right.isValueSet()) && ((this.escape == null) || (this.escape.isValueSet())))
    {
      if (this.left.isValueSet()) {
        return ValueExpression.get(getValue(session));
      }
      Value r = this.right.getValue(session);
      if (r == ValueNull.INSTANCE) {
        return ValueExpression.getNull();
      }
      Value e = this.escape == null ? null : this.escape.getValue(session);
      if (e == ValueNull.INSTANCE) {
        return ValueExpression.getNull();
      }
      String p = r.getString();
      initPattern(p, getEscapeChar(e));
      if (this.invalidPattern) {
        return ValueExpression.getNull();
      }
      if ("%".equals(p)) {
        return new Comparison(session, 7, this.left, null).optimize(session);
      }
      if (isFullMatch())
      {
        Value value = ValueString.get(this.patternString);
        Expression expr = ValueExpression.get(value);
        return new Comparison(session, 0, this.left, expr).optimize(session);
      }
      this.isInit = true;
    }
    return this;
  }
  
  private Character getEscapeChar(Value e)
  {
    if (e == null) {
      return getEscapeChar(this.defaultEscape);
    }
    String es = e.getString();
    Character esc;
    Character esc;
    if (es == null)
    {
      esc = getEscapeChar(this.defaultEscape);
    }
    else
    {
      Character esc;
      if (es.length() == 0)
      {
        esc = null;
      }
      else
      {
        if (es.length() > 1) {
          throw DbException.get(22025, es);
        }
        esc = Character.valueOf(es.charAt(0));
      }
    }
    return esc;
  }
  
  public void createIndexConditions(Session session, TableFilter filter)
  {
    if (this.regexp) {
      return;
    }
    if (!(this.left instanceof ExpressionColumn)) {
      return;
    }
    ExpressionColumn l = (ExpressionColumn)this.left;
    if (filter != l.getTableFilter()) {
      return;
    }
    if (!this.right.isEverything(ExpressionVisitor.INDEPENDENT_VISITOR)) {
      return;
    }
    if ((this.escape != null) && (!this.escape.isEverything(ExpressionVisitor.INDEPENDENT_VISITOR))) {
      return;
    }
    String p = this.right.getValue(session).getString();
    Value e = this.escape == null ? null : this.escape.getValue(session);
    if (e == ValueNull.INSTANCE) {
      DbException.throwInternalError();
    }
    initPattern(p, getEscapeChar(e));
    if (this.invalidPattern) {
      return;
    }
    if ((this.patternLength <= 0) || (this.patternTypes[0] != 0)) {
      return;
    }
    int dataType = l.getColumn().getType();
    if ((dataType != 13) && (dataType != 14) && (dataType != 21)) {
      return;
    }
    int maxMatch = 0;
    StringBuilder buff = new StringBuilder();
    while ((maxMatch < this.patternLength) && (this.patternTypes[maxMatch] == 0)) {
      buff.append(this.patternChars[(maxMatch++)]);
    }
    String begin = buff.toString();
    if (maxMatch == this.patternLength)
    {
      filter.addIndexCondition(IndexCondition.get(0, l, ValueExpression.get(ValueString.get(begin))));
    }
    else if (begin.length() > 0)
    {
      filter.addIndexCondition(IndexCondition.get(1, l, ValueExpression.get(ValueString.get(begin))));
      
      char next = begin.charAt(begin.length() - 1);
      for (int i = 1; i < 2000; i++)
      {
        String end = begin.substring(0, begin.length() - 1) + (char)(next + i);
        if (this.compareMode.compareString(begin, end, this.ignoreCase) == -1)
        {
          filter.addIndexCondition(IndexCondition.get(4, l, ValueExpression.get(ValueString.get(end))));
          
          break;
        }
      }
    }
  }
  
  public Value getValue(Session session)
  {
    Value l = this.left.getValue(session);
    if (l == ValueNull.INSTANCE) {
      return l;
    }
    if (!this.isInit)
    {
      Value r = this.right.getValue(session);
      if (r == ValueNull.INSTANCE) {
        return r;
      }
      String p = r.getString();
      Value e = this.escape == null ? null : this.escape.getValue(session);
      if (e == ValueNull.INSTANCE) {
        return ValueNull.INSTANCE;
      }
      initPattern(p, getEscapeChar(e));
    }
    if (this.invalidPattern) {
      return ValueNull.INSTANCE;
    }
    String value = l.getString();
    boolean result;
    boolean result;
    if (this.regexp) {
      result = this.patternRegexp.matcher(value).find();
    } else {
      result = compareAt(value, 0, 0, value.length(), this.patternChars, this.patternTypes);
    }
    return ValueBoolean.get(result);
  }
  
  private boolean compare(char[] pattern, String s, int pi, int si)
  {
    return (pattern[pi] == s.charAt(si)) || ((!this.fastCompare) && (this.compareMode.equalsChars(this.patternString, pi, s, si, this.ignoreCase)));
  }
  
  private boolean compareAt(String s, int pi, int si, int sLen, char[] pattern, int[] types)
  {
    for (; pi < this.patternLength; pi++) {
      switch (types[pi])
      {
      case 0: 
        if ((si >= sLen) || (!compare(pattern, s, pi, si++))) {
          return false;
        }
        break;
      case 1: 
        if (si++ >= sLen) {
          return false;
        }
        break;
      case 2: 
        pi++;
        if (pi >= this.patternLength) {
          return true;
        }
        while (si < sLen)
        {
          if ((compare(pattern, s, pi, si)) && (compareAt(s, pi, si, sLen, pattern, types))) {
            return true;
          }
          si++;
        }
        return false;
      default: 
        DbException.throwInternalError();
      }
    }
    return si == sLen;
  }
  
  public boolean test(String testPattern, String value, char escapeChar)
  {
    initPattern(testPattern, Character.valueOf(escapeChar));
    if (this.invalidPattern) {
      return false;
    }
    return compareAt(value, 0, 0, value.length(), this.patternChars, this.patternTypes);
  }
  
  private void initPattern(String p, Character escapeChar)
  {
    if ((this.compareMode.getName().equals("OFF")) && (!this.ignoreCase)) {
      this.fastCompare = true;
    }
    if (this.regexp)
    {
      this.patternString = p;
      try
      {
        if (this.ignoreCase) {
          this.patternRegexp = Pattern.compile(p, 2);
        } else {
          this.patternRegexp = Pattern.compile(p);
        }
      }
      catch (PatternSyntaxException e)
      {
        throw DbException.get(22025, e, new String[] { p });
      }
      return;
    }
    this.patternLength = 0;
    if (p == null)
    {
      this.patternTypes = null;
      this.patternChars = null;
      return;
    }
    int len = p.length();
    this.patternChars = new char[len];
    this.patternTypes = new int[len];
    boolean lastAny = false;
    for (int i = 0; i < len; i++)
    {
      char c = p.charAt(i);
      int type;
      if ((escapeChar != null) && (escapeChar.charValue() == c))
      {
        if (i >= len - 1)
        {
          this.invalidPattern = true;
          return;
        }
        c = p.charAt(++i);
        int type = 0;
        lastAny = false;
      }
      else if (c == '%')
      {
        if (lastAny) {
          continue;
        }
        int type = 2;
        lastAny = true;
      }
      else
      {
        int type;
        if (c == '_')
        {
          type = 1;
        }
        else
        {
          type = 0;
          lastAny = false;
        }
      }
      this.patternTypes[this.patternLength] = type;
      this.patternChars[(this.patternLength++)] = c;
    }
    for (int i = 0; i < this.patternLength - 1; i++) {
      if ((this.patternTypes[i] == 2) && (this.patternTypes[(i + 1)] == 1))
      {
        this.patternTypes[i] = 1;
        this.patternTypes[(i + 1)] = 2;
      }
    }
    this.patternString = new String(this.patternChars, 0, this.patternLength);
  }
  
  private boolean isFullMatch()
  {
    if (this.patternTypes == null) {
      return false;
    }
    for (int type : this.patternTypes) {
      if (type != 0) {
        return false;
      }
    }
    return true;
  }
  
  public void mapColumns(ColumnResolver resolver, int level)
  {
    this.left.mapColumns(resolver, level);
    this.right.mapColumns(resolver, level);
    if (this.escape != null) {
      this.escape.mapColumns(resolver, level);
    }
  }
  
  public void setEvaluatable(TableFilter tableFilter, boolean b)
  {
    this.left.setEvaluatable(tableFilter, b);
    this.right.setEvaluatable(tableFilter, b);
    if (this.escape != null) {
      this.escape.setEvaluatable(tableFilter, b);
    }
  }
  
  public void updateAggregate(Session session)
  {
    this.left.updateAggregate(session);
    this.right.updateAggregate(session);
    if (this.escape != null) {
      this.escape.updateAggregate(session);
    }
  }
  
  public boolean isEverything(ExpressionVisitor visitor)
  {
    return (this.left.isEverything(visitor)) && (this.right.isEverything(visitor)) && ((this.escape == null) || (this.escape.isEverything(visitor)));
  }
  
  public int getCost()
  {
    return this.left.getCost() + this.right.getCost() + 3;
  }
}
