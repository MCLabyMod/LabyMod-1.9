package org.h2.command.ddl;

import java.util.ArrayList;
import org.h2.engine.Session;
import org.h2.schema.Schema;
import org.h2.table.Column;
import org.h2.util.New;

public class CreateTableData
{
  public Schema schema;
  public String tableName;
  public int id;
  public ArrayList<Column> columns = New.arrayList();
  public boolean temporary;
  public boolean globalTemporary;
  public boolean persistIndexes;
  public boolean persistData;
  public boolean create;
  public Session session;
  public String tableEngine;
  public ArrayList<String> tableEngineParams;
  public boolean isHidden;
}
