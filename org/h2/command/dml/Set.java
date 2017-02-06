package org.h2.command.dml;

import org.h2.command.Prepared;
import org.h2.engine.Database;
import org.h2.engine.Mode;
import org.h2.engine.Session;
import org.h2.engine.Setting;
import org.h2.engine.User;
import org.h2.expression.Expression;
import org.h2.expression.ValueExpression;
import org.h2.message.DbException;
import org.h2.message.TraceSystem;
import org.h2.result.ResultInterface;
import org.h2.schema.Schema;
import org.h2.table.Table;
import org.h2.tools.CompressTool;
import org.h2.util.StringUtils;
import org.h2.value.CompareMode;
import org.h2.value.Value;
import org.h2.value.ValueInt;

public class Set
  extends Prepared
{
  private final int type;
  private Expression expression;
  private String stringValue;
  private String[] stringValueList;
  
  public Set(Session session, int type)
  {
    super(session);
    this.type = type;
  }
  
  public void setString(String v)
  {
    this.stringValue = v;
  }
  
  public boolean isTransactional()
  {
    switch (this.type)
    {
    case 5: 
    case 9: 
    case 10: 
    case 13: 
    case 20: 
    case 26: 
    case 28: 
    case 35: 
    case 36: 
    case 40: 
      return true;
    }
    return false;
  }
  
  public int update()
  {
    Database database = this.session.getDatabase();
    String name = SetTypes.getTypeName(this.type);
    switch (this.type)
    {
    case 24: 
      this.session.getUser().checkAdmin();
      int value = getIntValue();
      if ((value < 0) || (value > 2)) {
        throw DbException.getInvalidValueException("ALLOW_LITERALS", Integer.valueOf(getIntValue()));
      }
      database.setAllowLiterals(value);
      addOrUpdateSetting(name, null, value);
      break;
    case 8: 
      if (getIntValue() < 0) {
        throw DbException.getInvalidValueException("CACHE_SIZE", Integer.valueOf(getIntValue()));
      }
      this.session.getUser().checkAdmin();
      database.setCacheSize(getIntValue());
      addOrUpdateSetting(name, null, getIntValue());
      break;
    case 13: 
      if (!"TRUE".equals(this.stringValue))
      {
        String value = StringUtils.quoteStringSQL(this.stringValue);
        if (!value.equals(database.getCluster()))
        {
          if (!value.equals("''")) {
            this.session.getUser().checkAdmin();
          }
          database.setCluster(value);
          
          Session sysSession = database.getSystemSession();
          synchronized (sysSession)
          {
            synchronized (database)
            {
              addOrUpdateSetting(sysSession, name, value, 0);
              sysSession.commit(true);
            }
          }
        }
      }
      break;
    case 12: 
      this.session.getUser().checkAdmin();
      boolean binaryUnsigned = database.getCompareMode().isBinaryUnsigned();
      
      StringBuilder buff = new StringBuilder(this.stringValue);
      CompareMode compareMode;
      CompareMode compareMode;
      if (this.stringValue.equals("OFF"))
      {
        compareMode = CompareMode.getInstance(null, 0, binaryUnsigned);
      }
      else
      {
        int strength = getIntValue();
        buff.append(" STRENGTH ");
        if (strength == 3) {
          buff.append("IDENTICAL");
        } else if (strength == 0) {
          buff.append("PRIMARY");
        } else if (strength == 1) {
          buff.append("SECONDARY");
        } else if (strength == 2) {
          buff.append("TERTIARY");
        }
        compareMode = CompareMode.getInstance(this.stringValue, strength, binaryUnsigned);
      }
      CompareMode old = database.getCompareMode();
      if (!old.equals(compareMode))
      {
        Table table = database.getFirstUserTable();
        if (table != null) {
          throw DbException.get(90089, table.getSQL());
        }
        addOrUpdateSetting(name, buff.toString(), 0);
        database.setCompareMode(compareMode);
      }
      break;
    case 38: 
      this.session.getUser().checkAdmin();
      Table table = database.getFirstUserTable();
      if (table != null) {
        throw DbException.get(90089, table.getSQL());
      }
      CompareMode currentMode = database.getCompareMode();
      CompareMode newMode;
      if (this.stringValue.equals("SIGNED"))
      {
        newMode = CompareMode.getInstance(currentMode.getName(), currentMode.getStrength(), false);
      }
      else
      {
        CompareMode newMode;
        if (this.stringValue.equals("UNSIGNED")) {
          newMode = CompareMode.getInstance(currentMode.getName(), currentMode.getStrength(), true);
        } else {
          throw DbException.getInvalidValueException("BINARY_COLLATION", this.stringValue);
        }
      }
      CompareMode newMode;
      addOrUpdateSetting(name, this.stringValue, 0);
      database.setCompareMode(newMode);
      break;
    case 23: 
      this.session.getUser().checkAdmin();
      int algo = CompressTool.getCompressAlgorithm(this.stringValue);
      database.setLobCompressionAlgorithm(algo == 0 ? null : this.stringValue);
      
      addOrUpdateSetting(name, this.stringValue, 0);
      break;
    case 34: 
      this.session.getUser().checkAdmin();
      if (database.isStarting())
      {
        int value = getIntValue();
        addOrUpdateSetting(name, null, value);
      }
      break;
    case 15: 
      this.session.getUser().checkAdmin();
      database.setEventListenerClass(this.stringValue);
      break;
    case 18: 
      int x = getIntValue();
      if (x != -1) {
        if (x < 0) {
          throw DbException.getInvalidValueException("DB_CLOSE_DELAY", Integer.valueOf(x));
        }
      }
      this.session.getUser().checkAdmin();
      database.setCloseDelay(getIntValue());
      addOrUpdateSetting(name, null, getIntValue());
      break;
    case 6: 
      if (getIntValue() < 0) {
        throw DbException.getInvalidValueException("DEFAULT_LOCK_TIMEOUT", Integer.valueOf(getIntValue()));
      }
      this.session.getUser().checkAdmin();
      addOrUpdateSetting(name, null, getIntValue());
      break;
    case 7: 
      this.session.getUser().checkAdmin();
      database.setDefaultTableType(getIntValue());
      addOrUpdateSetting(name, null, getIntValue());
      break;
    case 33: 
      this.session.getUser().checkAdmin();
      int value = getIntValue();
      switch (value)
      {
      case 0: 
        database.setExclusiveSession(null, false);
        break;
      case 1: 
        database.setExclusiveSession(this.session, false);
        break;
      case 2: 
        database.setExclusiveSession(this.session, true);
        break;
      default: 
        throw DbException.getInvalidValueException("EXCLUSIVE", Integer.valueOf(value));
      }
      break;
    case 39: 
      this.session.getUser().checkAdmin();
      Table table = database.getFirstUserTable();
      if (table != null) {
        throw DbException.get(90141, table.getSQL());
      }
      database.setJavaObjectSerializerName(this.stringValue);
      addOrUpdateSetting(name, this.stringValue, 0);
      break;
    case 1: 
      this.session.getUser().checkAdmin();
      database.setIgnoreCase(getIntValue() == 1);
      addOrUpdateSetting(name, null, getIntValue());
      break;
    case 17: 
      this.session.getUser().checkAdmin();
      database.setLockMode(getIntValue());
      addOrUpdateSetting(name, null, getIntValue());
      break;
    case 5: 
      if (getIntValue() < 0) {
        throw DbException.getInvalidValueException("LOCK_TIMEOUT", Integer.valueOf(getIntValue()));
      }
      this.session.setLockTimeout(getIntValue());
      break;
    case 19: 
      int value = getIntValue();
      if ((database.isPersistent()) && (value != database.getLogMode()))
      {
        this.session.getUser().checkAdmin();
        database.setLogMode(value);
      }
      break;
    case 22: 
      if (getIntValue() < 0) {
        throw DbException.getInvalidValueException("MAX_LENGTH_INPLACE_LOB", Integer.valueOf(getIntValue()));
      }
      this.session.getUser().checkAdmin();
      database.setMaxLengthInplaceLob(getIntValue());
      addOrUpdateSetting(name, null, getIntValue());
      break;
    case 2: 
      if (getIntValue() < 0) {
        throw DbException.getInvalidValueException("MAX_LOG_SIZE", Integer.valueOf(getIntValue()));
      }
      this.session.getUser().checkAdmin();
      database.setMaxLogSize(getIntValue() * 1024L * 1024L);
      addOrUpdateSetting(name, null, getIntValue());
      break;
    case 16: 
      if (getIntValue() < 0) {
        throw DbException.getInvalidValueException("MAX_MEMORY_ROWS", Integer.valueOf(getIntValue()));
      }
      this.session.getUser().checkAdmin();
      database.setMaxMemoryRows(getIntValue());
      addOrUpdateSetting(name, null, getIntValue());
      break;
    case 21: 
      if (getIntValue() < 0) {
        throw DbException.getInvalidValueException("MAX_MEMORY_UNDO", Integer.valueOf(getIntValue()));
      }
      this.session.getUser().checkAdmin();
      database.setMaxMemoryUndo(getIntValue());
      addOrUpdateSetting(name, null, getIntValue());
      break;
    case 32: 
      if (getIntValue() < 0) {
        throw DbException.getInvalidValueException("MAX_OPERATION_MEMORY", Integer.valueOf(getIntValue()));
      }
      this.session.getUser().checkAdmin();
      int value = getIntValue();
      database.setMaxOperationMemory(value);
      break;
    case 3: 
      Mode mode = Mode.getInstance(this.stringValue);
      if (mode == null) {
        throw DbException.get(90088, this.stringValue);
      }
      if (database.getMode() != mode)
      {
        this.session.getUser().checkAdmin();
        database.setMode(mode);
      }
      break;
    case 25: 
      this.session.getUser().checkAdmin();
      database.setMultiThreaded(getIntValue() == 1);
      break;
    case 31: 
      if (database.isMultiVersion() != (getIntValue() == 1)) {
        throw DbException.get(90133, "MVCC");
      }
      break;
    case 27: 
      this.session.getUser().checkAdmin();
      database.setOptimizeReuseResults(getIntValue() != 0);
      break;
    case 36: 
      if (getIntValue() < 0) {
        throw DbException.getInvalidValueException("QUERY_TIMEOUT", Integer.valueOf(getIntValue()));
      }
      int value = getIntValue();
      this.session.setQueryTimeout(value);
      break;
    case 37: 
      int value = getIntValue();
      this.session.setRedoLogBinary(value == 1);
      break;
    case 30: 
      this.session.getUser().checkAdmin();
      int value = getIntValue();
      if ((value < 0) || (value > 1)) {
        throw DbException.getInvalidValueException("REFERENTIAL_INTEGRITY", Integer.valueOf(getIntValue()));
      }
      database.setReferentialIntegrity(value == 1);
      break;
    case 41: 
      this.session.getUser().checkAdmin();
      int value = getIntValue();
      if ((value < 0) || (value > 1)) {
        throw DbException.getInvalidValueException("QUERY_STATISTICS", Integer.valueOf(getIntValue()));
      }
      database.setQueryStatistics(value == 1);
      break;
    case 26: 
      Schema schema = database.getSchema(this.stringValue);
      this.session.setCurrentSchema(schema);
      break;
    case 28: 
      this.session.setSchemaSearchPath(this.stringValueList);
      break;
    case 10: 
      this.session.getUser().checkAdmin();
      if (getCurrentObjectId() == 0) {
        database.getTraceSystem().setLevelFile(getIntValue());
      }
      break;
    case 9: 
      this.session.getUser().checkAdmin();
      if (getCurrentObjectId() == 0) {
        database.getTraceSystem().setLevelSystemOut(getIntValue());
      }
      break;
    case 11: 
      if (getIntValue() < 0) {
        throw DbException.getInvalidValueException("TRACE_MAX_FILE_SIZE", Integer.valueOf(getIntValue()));
      }
      this.session.getUser().checkAdmin();
      int size = getIntValue() * 1024 * 1024;
      database.getTraceSystem().setMaxFileSize(size);
      addOrUpdateSetting(name, null, getIntValue());
      break;
    case 20: 
      if (getIntValue() < 0) {
        throw DbException.getInvalidValueException("THROTTLE", Integer.valueOf(getIntValue()));
      }
      this.session.setThrottle(getIntValue());
      break;
    case 29: 
      int value = getIntValue();
      if ((value < 0) || (value > 1)) {
        throw DbException.getInvalidValueException("UNDO_LOG", Integer.valueOf(getIntValue()));
      }
      this.session.setUndoLogEnabled(value == 1);
      break;
    case 35: 
      Expression expr = this.expression.optimize(this.session);
      this.session.setVariable(this.stringValue, expr.getValue(this.session));
      break;
    case 14: 
      if (getIntValue() < 0) {
        throw DbException.getInvalidValueException("WRITE_DELAY", Integer.valueOf(getIntValue()));
      }
      this.session.getUser().checkAdmin();
      database.setWriteDelay(getIntValue());
      addOrUpdateSetting(name, null, getIntValue());
      break;
    case 40: 
      if (getIntValue() < 0) {
        throw DbException.getInvalidValueException("RETENTION_TIME", Integer.valueOf(getIntValue()));
      }
      this.session.getUser().checkAdmin();
      database.setRetentionTime(getIntValue());
      addOrUpdateSetting(name, null, getIntValue());
      break;
    case 4: 
    default: 
      DbException.throwInternalError("type=" + this.type);
    }
    database.getNextModificationDataId();
    
    database.getNextModificationMetaId();
    return 0;
  }
  
  private int getIntValue()
  {
    this.expression = this.expression.optimize(this.session);
    return this.expression.getValue(this.session).getInt();
  }
  
  public void setInt(int value)
  {
    this.expression = ValueExpression.get(ValueInt.get(value));
  }
  
  public void setExpression(Expression expression)
  {
    this.expression = expression;
  }
  
  private void addOrUpdateSetting(String name, String s, int v)
  {
    addOrUpdateSetting(this.session, name, s, v);
  }
  
  private void addOrUpdateSetting(Session session, String name, String s, int v)
  {
    Database database = session.getDatabase();
    if (database.isReadOnly()) {
      return;
    }
    Setting setting = database.findSetting(name);
    boolean addNew = false;
    if (setting == null)
    {
      addNew = true;
      int id = getObjectId();
      setting = new Setting(database, id, name);
    }
    if (s != null)
    {
      if ((!addNew) && (setting.getStringValue().equals(s))) {
        return;
      }
      setting.setStringValue(s);
    }
    else
    {
      if ((!addNew) && (setting.getIntValue() == v)) {
        return;
      }
      setting.setIntValue(v);
    }
    if (addNew) {
      database.addDatabaseObject(session, setting);
    } else {
      database.updateMeta(session, setting);
    }
  }
  
  public boolean needRecompile()
  {
    return false;
  }
  
  public ResultInterface queryMeta()
  {
    return null;
  }
  
  public void setStringArray(String[] list)
  {
    this.stringValueList = list;
  }
  
  public int getType()
  {
    return 67;
  }
}
