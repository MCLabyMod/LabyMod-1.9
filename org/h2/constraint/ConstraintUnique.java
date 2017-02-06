package org.h2.constraint;

import java.util.HashSet;
import org.h2.command.Parser;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.index.Index;
import org.h2.result.Row;
import org.h2.schema.Schema;
import org.h2.table.Column;
import org.h2.table.IndexColumn;
import org.h2.table.Table;
import org.h2.util.New;
import org.h2.util.StatementBuilder;
import org.h2.util.StringUtils;

public class ConstraintUnique
  extends Constraint
{
  private Index index;
  private boolean indexOwner;
  private IndexColumn[] columns;
  private final boolean primaryKey;
  
  public ConstraintUnique(Schema schema, int id, String name, Table table, boolean primaryKey)
  {
    super(schema, id, name, table);
    this.primaryKey = primaryKey;
  }
  
  public String getConstraintType()
  {
    return this.primaryKey ? "PRIMARY KEY" : "UNIQUE";
  }
  
  public String getCreateSQLForCopy(Table forTable, String quotedName)
  {
    return getCreateSQLForCopy(forTable, quotedName, true);
  }
  
  private String getCreateSQLForCopy(Table forTable, String quotedName, boolean internalIndex)
  {
    StatementBuilder buff = new StatementBuilder("ALTER TABLE ");
    buff.append(forTable.getSQL()).append(" ADD CONSTRAINT ");
    if (forTable.isHidden()) {
      buff.append("IF NOT EXISTS ");
    }
    buff.append(quotedName);
    if (this.comment != null) {
      buff.append(" COMMENT ").append(StringUtils.quoteStringSQL(this.comment));
    }
    buff.append(' ').append(getTypeName()).append('(');
    for (IndexColumn c : this.columns)
    {
      buff.appendExceptFirst(", ");
      buff.append(Parser.quoteIdentifier(c.column.getName()));
    }
    buff.append(')');
    if ((internalIndex) && (this.indexOwner) && (forTable == this.table)) {
      buff.append(" INDEX ").append(this.index.getSQL());
    }
    return buff.toString();
  }
  
  private String getTypeName()
  {
    if (this.primaryKey) {
      return "PRIMARY KEY";
    }
    return "UNIQUE";
  }
  
  public String getCreateSQLWithoutIndexes()
  {
    return getCreateSQLForCopy(this.table, getSQL(), false);
  }
  
  public String getCreateSQL()
  {
    return getCreateSQLForCopy(this.table, getSQL());
  }
  
  public void setColumns(IndexColumn[] columns)
  {
    this.columns = columns;
  }
  
  public IndexColumn[] getColumns()
  {
    return this.columns;
  }
  
  public void setIndex(Index index, boolean isOwner)
  {
    this.index = index;
    this.indexOwner = isOwner;
  }
  
  public void removeChildrenAndResources(Session session)
  {
    this.table.removeConstraint(this);
    if (this.indexOwner) {
      this.table.removeIndexOrTransferOwnership(session, this.index);
    }
    this.database.removeMeta(session, getId());
    this.index = null;
    this.columns = null;
    this.table = null;
    invalidate();
  }
  
  public void checkRow(Session session, Table t, Row oldRow, Row newRow) {}
  
  public boolean usesIndex(Index idx)
  {
    return idx == this.index;
  }
  
  public void setIndexOwner(Index index)
  {
    this.indexOwner = true;
  }
  
  public HashSet<Column> getReferencedColumns(Table table)
  {
    HashSet<Column> result = New.hashSet();
    for (IndexColumn c : this.columns) {
      result.add(c.column);
    }
    return result;
  }
  
  public boolean isBefore()
  {
    return true;
  }
  
  public void checkExistingData(Session session) {}
  
  public Index getUniqueIndex()
  {
    return this.index;
  }
  
  public void rebuild() {}
}
