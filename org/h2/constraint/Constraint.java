package org.h2.constraint;

import java.util.HashSet;
import org.h2.engine.Session;
import org.h2.expression.ExpressionVisitor;
import org.h2.index.Index;
import org.h2.message.DbException;
import org.h2.result.Row;
import org.h2.schema.Schema;
import org.h2.schema.SchemaObjectBase;
import org.h2.table.Column;
import org.h2.table.Table;

public abstract class Constraint
  extends SchemaObjectBase
  implements Comparable<Constraint>
{
  public static final String CHECK = "CHECK";
  public static final String REFERENTIAL = "REFERENTIAL";
  public static final String UNIQUE = "UNIQUE";
  public static final String PRIMARY_KEY = "PRIMARY KEY";
  protected Table table;
  
  Constraint(Schema schema, int id, String name, Table table)
  {
    initSchemaObjectBase(schema, id, name, "constraint");
    this.table = table;
    setTemporary(table.isTemporary());
  }
  
  public abstract String getConstraintType();
  
  public abstract void checkRow(Session paramSession, Table paramTable, Row paramRow1, Row paramRow2);
  
  public abstract boolean usesIndex(Index paramIndex);
  
  public abstract void setIndexOwner(Index paramIndex);
  
  public abstract HashSet<Column> getReferencedColumns(Table paramTable);
  
  public abstract String getCreateSQLWithoutIndexes();
  
  public abstract boolean isBefore();
  
  public abstract void checkExistingData(Session paramSession);
  
  public abstract void rebuild();
  
  public abstract Index getUniqueIndex();
  
  public void checkRename() {}
  
  public int getType()
  {
    return 5;
  }
  
  public Table getTable()
  {
    return this.table;
  }
  
  public Table getRefTable()
  {
    return this.table;
  }
  
  public String getDropSQL()
  {
    return null;
  }
  
  private int getConstraintTypeOrder()
  {
    String constraintType = getConstraintType();
    if ("CHECK".equals(constraintType)) {
      return 0;
    }
    if ("PRIMARY KEY".equals(constraintType)) {
      return 1;
    }
    if ("UNIQUE".equals(constraintType)) {
      return 2;
    }
    if ("REFERENTIAL".equals(constraintType)) {
      return 3;
    }
    throw DbException.throwInternalError("type: " + constraintType);
  }
  
  public int compareTo(Constraint other)
  {
    if (this == other) {
      return 0;
    }
    int thisType = getConstraintTypeOrder();
    int otherType = other.getConstraintTypeOrder();
    return thisType - otherType;
  }
  
  public boolean isHidden()
  {
    return this.table.isHidden();
  }
  
  public boolean isEverything(ExpressionVisitor visitor)
  {
    return true;
  }
}
