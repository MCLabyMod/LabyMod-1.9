package org.h2.schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import org.h2.api.TableEngine;
import org.h2.command.ddl.CreateTableData;
import org.h2.constraint.Constraint;
import org.h2.engine.Database;
import org.h2.engine.DbObject;
import org.h2.engine.DbObjectBase;
import org.h2.engine.DbSettings;
import org.h2.engine.FunctionAlias;
import org.h2.engine.Session;
import org.h2.engine.SysProperties;
import org.h2.engine.User;
import org.h2.index.Index;
import org.h2.message.DbException;
import org.h2.mvstore.db.MVTableEngine;
import org.h2.table.RegularTable;
import org.h2.table.Table;
import org.h2.util.JdbcUtils;
import org.h2.util.New;

public class Schema
  extends DbObjectBase
{
  private User owner;
  private final boolean system;
  private final HashMap<String, Table> tablesAndViews;
  private final HashMap<String, Index> indexes;
  private final HashMap<String, Sequence> sequences;
  private final HashMap<String, TriggerObject> triggers;
  private final HashMap<String, Constraint> constraints;
  private final HashMap<String, Constant> constants;
  private final HashMap<String, FunctionAlias> functions;
  private final HashSet<String> temporaryUniqueNames = New.hashSet();
  
  public Schema(Database database, int id, String schemaName, User owner, boolean system)
  {
    this.tablesAndViews = database.newStringMap();
    this.indexes = database.newStringMap();
    this.sequences = database.newStringMap();
    this.triggers = database.newStringMap();
    this.constraints = database.newStringMap();
    this.constants = database.newStringMap();
    this.functions = database.newStringMap();
    initDbObjectBase(database, id, schemaName, "schema");
    this.owner = owner;
    this.system = system;
  }
  
  public boolean canDrop()
  {
    return !this.system;
  }
  
  public String getCreateSQLForCopy(Table table, String quotedName)
  {
    throw DbException.throwInternalError();
  }
  
  public String getDropSQL()
  {
    return null;
  }
  
  public String getCreateSQL()
  {
    if (this.system) {
      return null;
    }
    return "CREATE SCHEMA IF NOT EXISTS " + getSQL() + " AUTHORIZATION " + this.owner.getSQL();
  }
  
  public int getType()
  {
    return 10;
  }
  
  public void removeChildrenAndResources(Session session)
  {
    while ((this.triggers != null) && (this.triggers.size() > 0))
    {
      TriggerObject obj = (TriggerObject)this.triggers.values().toArray()[0];
      this.database.removeSchemaObject(session, obj);
    }
    while ((this.constraints != null) && (this.constraints.size() > 0))
    {
      Constraint obj = (Constraint)this.constraints.values().toArray()[0];
      this.database.removeSchemaObject(session, obj);
    }
    boolean runLoopAgain = false;
    do
    {
      runLoopAgain = false;
      if (this.tablesAndViews != null) {
        for (Table obj : New.arrayList(this.tablesAndViews.values())) {
          if (obj.getName() != null) {
            if (this.database.getDependentTable(obj, obj) == null) {
              this.database.removeSchemaObject(session, obj);
            } else {
              runLoopAgain = true;
            }
          }
        }
      }
    } while (runLoopAgain);
    while ((this.indexes != null) && (this.indexes.size() > 0))
    {
      Index obj = (Index)this.indexes.values().toArray()[0];
      this.database.removeSchemaObject(session, obj);
    }
    while ((this.sequences != null) && (this.sequences.size() > 0))
    {
      Sequence obj = (Sequence)this.sequences.values().toArray()[0];
      this.database.removeSchemaObject(session, obj);
    }
    while ((this.constants != null) && (this.constants.size() > 0))
    {
      Constant obj = (Constant)this.constants.values().toArray()[0];
      this.database.removeSchemaObject(session, obj);
    }
    while ((this.functions != null) && (this.functions.size() > 0))
    {
      FunctionAlias obj = (FunctionAlias)this.functions.values().toArray()[0];
      this.database.removeSchemaObject(session, obj);
    }
    this.database.removeMeta(session, getId());
    this.owner = null;
    invalidate();
  }
  
  public void checkRename() {}
  
  public User getOwner()
  {
    return this.owner;
  }
  
  private HashMap<String, SchemaObject> getMap(int type)
  {
    HashMap<String, ? extends SchemaObject> result;
    switch (type)
    {
    case 0: 
      result = this.tablesAndViews;
      break;
    case 3: 
      result = this.sequences;
      break;
    case 1: 
      result = this.indexes;
      break;
    case 4: 
      result = this.triggers;
      break;
    case 5: 
      result = this.constraints;
      break;
    case 11: 
      result = this.constants;
      break;
    case 9: 
      result = this.functions;
      break;
    case 2: 
    case 6: 
    case 7: 
    case 8: 
    case 10: 
    default: 
      throw DbException.throwInternalError("type=" + type);
    }
    return result;
  }
  
  public void add(SchemaObject obj)
  {
    if ((SysProperties.CHECK) && (obj.getSchema() != this)) {
      DbException.throwInternalError("wrong schema");
    }
    String name = obj.getName();
    HashMap<String, SchemaObject> map = getMap(obj.getType());
    if ((SysProperties.CHECK) && (map.get(name) != null)) {
      DbException.throwInternalError("object already exists: " + name);
    }
    map.put(name, obj);
    freeUniqueName(name);
  }
  
  public void rename(SchemaObject obj, String newName)
  {
    int type = obj.getType();
    HashMap<String, SchemaObject> map = getMap(type);
    if (SysProperties.CHECK)
    {
      if (!map.containsKey(obj.getName())) {
        DbException.throwInternalError("not found: " + obj.getName());
      }
      if ((obj.getName().equals(newName)) || (map.containsKey(newName))) {
        DbException.throwInternalError("object already exists: " + newName);
      }
    }
    obj.checkRename();
    map.remove(obj.getName());
    freeUniqueName(obj.getName());
    obj.rename(newName);
    map.put(newName, obj);
    freeUniqueName(newName);
  }
  
  public Table findTableOrView(Session session, String name)
  {
    Table table = (Table)this.tablesAndViews.get(name);
    if ((table == null) && (session != null)) {
      table = session.findLocalTempTable(name);
    }
    return table;
  }
  
  public Index findIndex(Session session, String name)
  {
    Index index = (Index)this.indexes.get(name);
    if (index == null) {
      index = session.findLocalTempTableIndex(name);
    }
    return index;
  }
  
  public TriggerObject findTrigger(String name)
  {
    return (TriggerObject)this.triggers.get(name);
  }
  
  public Sequence findSequence(String sequenceName)
  {
    return (Sequence)this.sequences.get(sequenceName);
  }
  
  public Constraint findConstraint(Session session, String name)
  {
    Constraint constraint = (Constraint)this.constraints.get(name);
    if (constraint == null) {
      constraint = session.findLocalTempTableConstraint(name);
    }
    return constraint;
  }
  
  public Constant findConstant(String constantName)
  {
    return (Constant)this.constants.get(constantName);
  }
  
  public FunctionAlias findFunction(String functionAlias)
  {
    return (FunctionAlias)this.functions.get(functionAlias);
  }
  
  public void freeUniqueName(String name)
  {
    if (name != null) {
      synchronized (this.temporaryUniqueNames)
      {
        this.temporaryUniqueNames.remove(name);
      }
    }
  }
  
  private String getUniqueName(DbObject obj, HashMap<String, ? extends SchemaObject> map, String prefix)
  {
    String hash = Integer.toHexString(obj.getName().hashCode()).toUpperCase();
    String name = null;
    synchronized (this.temporaryUniqueNames)
    {
      int i = 1;
      for (int len = hash.length(); i < len; i++)
      {
        name = prefix + hash.substring(0, i);
        if ((!map.containsKey(name)) && (!this.temporaryUniqueNames.contains(name))) {
          break;
        }
        name = null;
      }
      if (name == null)
      {
        prefix = prefix + hash + "_";
        for (int i = 0;; i++)
        {
          name = prefix + i;
          if ((!map.containsKey(name)) && (!this.temporaryUniqueNames.contains(name))) {
            break;
          }
        }
      }
      this.temporaryUniqueNames.add(name);
    }
    return name;
  }
  
  public String getUniqueConstraintName(Session session, Table table)
  {
    HashMap<String, Constraint> tableConstraints;
    HashMap<String, Constraint> tableConstraints;
    if ((table.isTemporary()) && (!table.isGlobalTemporary())) {
      tableConstraints = session.getLocalTempTableConstraints();
    } else {
      tableConstraints = this.constraints;
    }
    return getUniqueName(table, tableConstraints, "CONSTRAINT_");
  }
  
  public String getUniqueIndexName(Session session, Table table, String prefix)
  {
    HashMap<String, Index> tableIndexes;
    HashMap<String, Index> tableIndexes;
    if ((table.isTemporary()) && (!table.isGlobalTemporary())) {
      tableIndexes = session.getLocalTempTableIndexes();
    } else {
      tableIndexes = this.indexes;
    }
    return getUniqueName(table, tableIndexes, prefix);
  }
  
  public Table getTableOrView(Session session, String name)
  {
    Table table = (Table)this.tablesAndViews.get(name);
    if (table == null)
    {
      if (session != null) {
        table = session.findLocalTempTable(name);
      }
      if (table == null) {
        throw DbException.get(42102, name);
      }
    }
    return table;
  }
  
  public Index getIndex(String name)
  {
    Index index = (Index)this.indexes.get(name);
    if (index == null) {
      throw DbException.get(42112, name);
    }
    return index;
  }
  
  public Constraint getConstraint(String name)
  {
    Constraint constraint = (Constraint)this.constraints.get(name);
    if (constraint == null) {
      throw DbException.get(90057, name);
    }
    return constraint;
  }
  
  public Constant getConstant(String constantName)
  {
    Constant constant = (Constant)this.constants.get(constantName);
    if (constant == null) {
      throw DbException.get(90115, constantName);
    }
    return constant;
  }
  
  public Sequence getSequence(String sequenceName)
  {
    Sequence sequence = (Sequence)this.sequences.get(sequenceName);
    if (sequence == null) {
      throw DbException.get(90036, sequenceName);
    }
    return sequence;
  }
  
  public ArrayList<SchemaObject> getAll()
  {
    ArrayList<SchemaObject> all = New.arrayList();
    all.addAll(getMap(0).values());
    all.addAll(getMap(3).values());
    all.addAll(getMap(1).values());
    all.addAll(getMap(4).values());
    all.addAll(getMap(5).values());
    all.addAll(getMap(11).values());
    all.addAll(getMap(9).values());
    return all;
  }
  
  public ArrayList<SchemaObject> getAll(int type)
  {
    HashMap<String, SchemaObject> map = getMap(type);
    return New.arrayList(map.values());
  }
  
  /* Error */
  public ArrayList<Table> getAllTablesAndViews()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 137	org/h2/schema/Schema:database	Lorg/h2/engine/Database;
    //   4: dup
    //   5: astore_1
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 48	org/h2/schema/Schema:tablesAndViews	Ljava/util/HashMap;
    //   11: invokevirtual 127	java/util/HashMap:values	()Ljava/util/Collection;
    //   14: invokestatic 147	org/h2/util/New:arrayList	(Ljava/util/Collection;)Ljava/util/ArrayList;
    //   17: aload_1
    //   18: monitorexit
    //   19: areturn
    //   20: astore_2
    //   21: aload_1
    //   22: monitorexit
    //   23: aload_2
    //   24: athrow
    // Line number table:
    //   Java source line #538	-> byte code offset #0
    //   Java source line #539	-> byte code offset #7
    //   Java source line #540	-> byte code offset #20
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	25	0	this	Schema
    //   5	17	1	Ljava/lang/Object;	Object
    //   20	4	2	localObject1	Object
    // Exception table:
    //   from	to	target	type
    //   7	19	20	finally
    //   20	23	20	finally
  }
  
  public void remove(SchemaObject obj)
  {
    String objName = obj.getName();
    HashMap<String, SchemaObject> map = getMap(obj.getType());
    if ((SysProperties.CHECK) && (!map.containsKey(objName))) {
      DbException.throwInternalError("not found: " + objName);
    }
    map.remove(objName);
    freeUniqueName(objName);
  }
  
  public Table createTable(CreateTableData data)
  {
    synchronized (this.database)
    {
      if ((!data.temporary) || (data.globalTemporary)) {
        this.database.lockMeta(data.session);
      }
      data.schema = this;
      if ((data.tableEngine == null) && 
        (this.database.getSettings().mvStore)) {
        data.tableEngine = MVTableEngine.class.getName();
      }
      if (data.tableEngine != null)
      {
        TableEngine engine;
        try
        {
          engine = (TableEngine)JdbcUtils.loadUserClass(data.tableEngine).newInstance();
        }
        catch (Exception e)
        {
          throw DbException.convert(e);
        }
        return engine.createTable(data);
      }
      return new RegularTable(data);
    }
  }
  
  /* Error */
  public org.h2.table.TableLink createTableLink(int id, String tableName, String driver, String url, String user, String password, String originalSchema, String originalTable, boolean emitUpdates, boolean force)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 137	org/h2/schema/Schema:database	Lorg/h2/engine/Database;
    //   4: dup
    //   5: astore 11
    //   7: monitorenter
    //   8: new 473	org/h2/table/TableLink
    //   11: dup
    //   12: aload_0
    //   13: iload_1
    //   14: aload_2
    //   15: aload_3
    //   16: aload 4
    //   18: aload 5
    //   20: aload 6
    //   22: aload 7
    //   24: aload 8
    //   26: iload 9
    //   28: iload 10
    //   30: invokespecial 476	org/h2/table/TableLink:<init>	(Lorg/h2/schema/Schema;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)V
    //   33: aload 11
    //   35: monitorexit
    //   36: areturn
    //   37: astore 12
    //   39: aload 11
    //   41: monitorexit
    //   42: aload 12
    //   44: athrow
    // Line number table:
    //   Java source line #606	-> byte code offset #0
    //   Java source line #607	-> byte code offset #8
    //   Java source line #610	-> byte code offset #37
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	45	0	this	Schema
    //   0	45	1	id	int
    //   0	45	2	tableName	String
    //   0	45	3	driver	String
    //   0	45	4	url	String
    //   0	45	5	user	String
    //   0	45	6	password	String
    //   0	45	7	originalSchema	String
    //   0	45	8	originalTable	String
    //   0	45	9	emitUpdates	boolean
    //   0	45	10	force	boolean
    //   5	35	11	Ljava/lang/Object;	Object
    //   37	6	12	localObject1	Object
    // Exception table:
    //   from	to	target	type
    //   8	36	37	finally
    //   37	42	37	finally
  }
}
