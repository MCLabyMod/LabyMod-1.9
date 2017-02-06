package org.h2.table;

import java.util.ArrayList;
import java.util.List;
import org.h2.command.ddl.CreateTableData;
import org.h2.engine.Database;
import org.h2.engine.DbSettings;
import org.h2.mvstore.db.MVTableEngine;
import org.h2.util.StatementBuilder;
import org.h2.util.StringUtils;

public abstract class TableBase
  extends Table
{
  private final String tableEngine;
  private List<String> tableEngineParams = new ArrayList();
  private final boolean globalTemporary;
  
  public TableBase(CreateTableData data)
  {
    super(data.schema, data.id, data.tableName, data.persistIndexes, data.persistData);
    
    this.tableEngine = data.tableEngine;
    this.globalTemporary = data.globalTemporary;
    if (data.tableEngineParams != null) {
      this.tableEngineParams = data.tableEngineParams;
    }
    setTemporary(data.temporary);
    Column[] cols = new Column[data.columns.size()];
    data.columns.toArray(cols);
    setColumns(cols);
  }
  
  public String getDropSQL()
  {
    return "DROP TABLE IF EXISTS " + getSQL() + " CASCADE";
  }
  
  public String getCreateSQL()
  {
    Database db = getDatabase();
    if (db == null) {
      return null;
    }
    StatementBuilder buff = new StatementBuilder("CREATE ");
    if (isTemporary())
    {
      if (isGlobalTemporary()) {
        buff.append("GLOBAL ");
      } else {
        buff.append("LOCAL ");
      }
      buff.append("TEMPORARY ");
    }
    else if (isPersistIndexes())
    {
      buff.append("CACHED ");
    }
    else
    {
      buff.append("MEMORY ");
    }
    buff.append("TABLE ");
    if (this.isHidden) {
      buff.append("IF NOT EXISTS ");
    }
    buff.append(getSQL());
    if (this.comment != null) {
      buff.append(" COMMENT ").append(StringUtils.quoteStringSQL(this.comment));
    }
    buff.append("(\n    ");
    for (Column column : this.columns)
    {
      buff.appendExceptFirst(",\n    ");
      buff.append(column.getCreateSQL());
    }
    buff.append("\n)");
    if (this.tableEngine != null)
    {
      DbSettings s = db.getSettings();
      String d = s.defaultTableEngine;
      if ((d == null) && (s.mvStore)) {
        d = MVTableEngine.class.getName();
      }
      if ((d == null) || (!this.tableEngine.endsWith(d)))
      {
        buff.append("\nENGINE ");
        buff.append(StringUtils.quoteIdentifier(this.tableEngine));
      }
    }
    if (!this.tableEngineParams.isEmpty())
    {
      buff.append("\nWITH ");
      buff.resetCount();
      for (String parameter : this.tableEngineParams)
      {
        buff.appendExceptFirst(", ");
        buff.append(StringUtils.quoteIdentifier(parameter));
      }
    }
    if ((!isPersistIndexes()) && (!isPersistData())) {
      buff.append("\nNOT PERSISTENT");
    }
    if (this.isHidden) {
      buff.append("\nHIDDEN");
    }
    return buff.toString();
  }
  
  public boolean isGlobalTemporary()
  {
    return this.globalTemporary;
  }
}
