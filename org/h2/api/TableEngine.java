package org.h2.api;

import org.h2.command.ddl.CreateTableData;
import org.h2.table.Table;

public abstract interface TableEngine
{
  public abstract Table createTable(CreateTableData paramCreateTableData);
}
