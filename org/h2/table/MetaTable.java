package org.h2.table;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.channels.FileChannel;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import org.h2.command.Command;
import org.h2.constraint.Constraint;
import org.h2.constraint.ConstraintCheck;
import org.h2.constraint.ConstraintReferential;
import org.h2.constraint.ConstraintUnique;
import org.h2.engine.Constants;
import org.h2.engine.Database;
import org.h2.engine.DbObject;
import org.h2.engine.DbSettings;
import org.h2.engine.FunctionAlias;
import org.h2.engine.FunctionAlias.JavaMethod;
import org.h2.engine.Mode;
import org.h2.engine.QueryStatisticsData;
import org.h2.engine.QueryStatisticsData.QueryEntry;
import org.h2.engine.Right;
import org.h2.engine.Role;
import org.h2.engine.Session;
import org.h2.engine.Setting;
import org.h2.engine.User;
import org.h2.engine.UserAggregate;
import org.h2.engine.UserDataType;
import org.h2.expression.Expression;
import org.h2.expression.ValueExpression;
import org.h2.index.Index;
import org.h2.index.IndexType;
import org.h2.index.MetaIndex;
import org.h2.index.MultiVersionIndex;
import org.h2.message.DbException;
import org.h2.mvstore.FileStore;
import org.h2.mvstore.MVStore;
import org.h2.mvstore.db.MVTableEngine.Store;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.schema.Constant;
import org.h2.schema.Schema;
import org.h2.schema.SchemaObject;
import org.h2.schema.Sequence;
import org.h2.schema.TriggerObject;
import org.h2.store.InDoubtTransaction;
import org.h2.store.PageStore;
import org.h2.tools.Csv;
import org.h2.util.Cache;
import org.h2.util.MathUtils;
import org.h2.util.New;
import org.h2.util.StatementBuilder;
import org.h2.util.StringUtils;
import org.h2.util.Utils;
import org.h2.value.CompareMode;
import org.h2.value.DataType;
import org.h2.value.Value;
import org.h2.value.ValueNull;
import org.h2.value.ValueString;
import org.h2.value.ValueStringIgnoreCase;

public class MetaTable
  extends Table
{
  public static final long ROW_COUNT_APPROXIMATION = 1000L;
  private static final String CHARACTER_SET_NAME = "Unicode";
  private static final int TABLES = 0;
  private static final int COLUMNS = 1;
  private static final int INDEXES = 2;
  private static final int TABLE_TYPES = 3;
  private static final int TYPE_INFO = 4;
  private static final int CATALOGS = 5;
  private static final int SETTINGS = 6;
  private static final int HELP = 7;
  private static final int SEQUENCES = 8;
  private static final int USERS = 9;
  private static final int ROLES = 10;
  private static final int RIGHTS = 11;
  private static final int FUNCTION_ALIASES = 12;
  private static final int SCHEMATA = 13;
  private static final int TABLE_PRIVILEGES = 14;
  private static final int COLUMN_PRIVILEGES = 15;
  private static final int COLLATIONS = 16;
  private static final int VIEWS = 17;
  private static final int IN_DOUBT = 18;
  private static final int CROSS_REFERENCES = 19;
  private static final int CONSTRAINTS = 20;
  private static final int FUNCTION_COLUMNS = 21;
  private static final int CONSTANTS = 22;
  private static final int DOMAINS = 23;
  private static final int TRIGGERS = 24;
  private static final int SESSIONS = 25;
  private static final int LOCKS = 26;
  private static final int SESSION_STATE = 27;
  private static final int QUERY_STATISTICS = 28;
  private static final int META_TABLE_TYPE_COUNT = 29;
  private final int type;
  private final int indexColumn;
  private final MetaIndex metaIndex;
  
  public MetaTable(Schema schema, int id, int type)
  {
    super(schema, id, null, true, true);
    this.type = type;
    
    String indexColumnName = null;
    Column[] cols;
    switch (type)
    {
    case 0: 
      setObjectName("TABLES");
      cols = createColumns(new String[] { "TABLE_CATALOG", "TABLE_SCHEMA", "TABLE_NAME", "TABLE_TYPE", "STORAGE_TYPE", "SQL", "REMARKS", "LAST_MODIFICATION BIGINT", "ID INT", "TYPE_NAME", "TABLE_CLASS", "ROW_COUNT_ESTIMATE BIGINT" });
      
      indexColumnName = "TABLE_NAME";
      break;
    case 1: 
      setObjectName("COLUMNS");
      cols = createColumns(new String[] { "TABLE_CATALOG", "TABLE_SCHEMA", "TABLE_NAME", "COLUMN_NAME", "ORDINAL_POSITION INT", "COLUMN_DEFAULT", "IS_NULLABLE", "DATA_TYPE INT", "CHARACTER_MAXIMUM_LENGTH INT", "CHARACTER_OCTET_LENGTH INT", "NUMERIC_PRECISION INT", "NUMERIC_PRECISION_RADIX INT", "NUMERIC_SCALE INT", "CHARACTER_SET_NAME", "COLLATION_NAME", "TYPE_NAME", "NULLABLE INT", "IS_COMPUTED BIT", "SELECTIVITY INT", "CHECK_CONSTRAINT", "SEQUENCE_NAME", "REMARKS", "SOURCE_DATA_TYPE SMALLINT" });
      
      indexColumnName = "TABLE_NAME";
      break;
    case 2: 
      setObjectName("INDEXES");
      cols = createColumns(new String[] { "TABLE_CATALOG", "TABLE_SCHEMA", "TABLE_NAME", "NON_UNIQUE BIT", "INDEX_NAME", "ORDINAL_POSITION SMALLINT", "COLUMN_NAME", "CARDINALITY INT", "PRIMARY_KEY BIT", "INDEX_TYPE_NAME", "IS_GENERATED BIT", "INDEX_TYPE SMALLINT", "ASC_OR_DESC", "PAGES INT", "FILTER_CONDITION", "REMARKS", "SQL", "ID INT", "SORT_TYPE INT", "CONSTRAINT_NAME", "INDEX_CLASS" });
      
      indexColumnName = "TABLE_NAME";
      break;
    case 3: 
      setObjectName("TABLE_TYPES");
      cols = createColumns(new String[] { "TYPE" });
      break;
    case 4: 
      setObjectName("TYPE_INFO");
      cols = createColumns(new String[] { "TYPE_NAME", "DATA_TYPE INT", "PRECISION INT", "PREFIX", "SUFFIX", "PARAMS", "AUTO_INCREMENT BIT", "MINIMUM_SCALE SMALLINT", "MAXIMUM_SCALE SMALLINT", "RADIX INT", "POS INT", "CASE_SENSITIVE BIT", "NULLABLE SMALLINT", "SEARCHABLE SMALLINT" });
      
      break;
    case 5: 
      setObjectName("CATALOGS");
      cols = createColumns(new String[] { "CATALOG_NAME" });
      break;
    case 6: 
      setObjectName("SETTINGS");
      cols = createColumns(new String[] { "NAME", "VALUE" });
      break;
    case 7: 
      setObjectName("HELP");
      cols = createColumns(new String[] { "ID INT", "SECTION", "TOPIC", "SYNTAX", "TEXT" });
      
      break;
    case 8: 
      setObjectName("SEQUENCES");
      cols = createColumns(new String[] { "SEQUENCE_CATALOG", "SEQUENCE_SCHEMA", "SEQUENCE_NAME", "CURRENT_VALUE BIGINT", "INCREMENT BIGINT", "IS_GENERATED BIT", "REMARKS", "CACHE BIGINT", "MIN_VALUE BIGINT", "MAX_VALUE BIGINT", "IS_CYCLE BIT", "ID INT" });
      
      break;
    case 9: 
      setObjectName("USERS");
      cols = createColumns(new String[] { "NAME", "ADMIN", "REMARKS", "ID INT" });
      
      break;
    case 10: 
      setObjectName("ROLES");
      cols = createColumns(new String[] { "NAME", "REMARKS", "ID INT" });
      
      break;
    case 11: 
      setObjectName("RIGHTS");
      cols = createColumns(new String[] { "GRANTEE", "GRANTEETYPE", "GRANTEDROLE", "RIGHTS", "TABLE_SCHEMA", "TABLE_NAME", "ID INT" });
      
      indexColumnName = "TABLE_NAME";
      break;
    case 12: 
      setObjectName("FUNCTION_ALIASES");
      cols = createColumns(new String[] { "ALIAS_CATALOG", "ALIAS_SCHEMA", "ALIAS_NAME", "JAVA_CLASS", "JAVA_METHOD", "DATA_TYPE INT", "TYPE_NAME", "COLUMN_COUNT INT", "RETURNS_RESULT SMALLINT", "REMARKS", "ID INT", "SOURCE" });
      
      break;
    case 21: 
      setObjectName("FUNCTION_COLUMNS");
      cols = createColumns(new String[] { "ALIAS_CATALOG", "ALIAS_SCHEMA", "ALIAS_NAME", "JAVA_CLASS", "JAVA_METHOD", "COLUMN_COUNT INT", "POS INT", "COLUMN_NAME", "DATA_TYPE INT", "TYPE_NAME", "PRECISION INT", "SCALE SMALLINT", "RADIX SMALLINT", "NULLABLE SMALLINT", "COLUMN_TYPE SMALLINT", "REMARKS", "COLUMN_DEFAULT" });
      
      break;
    case 13: 
      setObjectName("SCHEMATA");
      cols = createColumns(new String[] { "CATALOG_NAME", "SCHEMA_NAME", "SCHEMA_OWNER", "DEFAULT_CHARACTER_SET_NAME", "DEFAULT_COLLATION_NAME", "IS_DEFAULT BIT", "REMARKS", "ID INT" });
      
      break;
    case 14: 
      setObjectName("TABLE_PRIVILEGES");
      cols = createColumns(new String[] { "GRANTOR", "GRANTEE", "TABLE_CATALOG", "TABLE_SCHEMA", "TABLE_NAME", "PRIVILEGE_TYPE", "IS_GRANTABLE" });
      
      indexColumnName = "TABLE_NAME";
      break;
    case 15: 
      setObjectName("COLUMN_PRIVILEGES");
      cols = createColumns(new String[] { "GRANTOR", "GRANTEE", "TABLE_CATALOG", "TABLE_SCHEMA", "TABLE_NAME", "COLUMN_NAME", "PRIVILEGE_TYPE", "IS_GRANTABLE" });
      
      indexColumnName = "TABLE_NAME";
      break;
    case 16: 
      setObjectName("COLLATIONS");
      cols = createColumns(new String[] { "NAME", "KEY" });
      
      break;
    case 17: 
      setObjectName("VIEWS");
      cols = createColumns(new String[] { "TABLE_CATALOG", "TABLE_SCHEMA", "TABLE_NAME", "VIEW_DEFINITION", "CHECK_OPTION", "IS_UPDATABLE", "STATUS", "REMARKS", "ID INT" });
      
      indexColumnName = "TABLE_NAME";
      break;
    case 18: 
      setObjectName("IN_DOUBT");
      cols = createColumns(new String[] { "TRANSACTION", "STATE" });
      
      break;
    case 19: 
      setObjectName("CROSS_REFERENCES");
      cols = createColumns(new String[] { "PKTABLE_CATALOG", "PKTABLE_SCHEMA", "PKTABLE_NAME", "PKCOLUMN_NAME", "FKTABLE_CATALOG", "FKTABLE_SCHEMA", "FKTABLE_NAME", "FKCOLUMN_NAME", "ORDINAL_POSITION SMALLINT", "UPDATE_RULE SMALLINT", "DELETE_RULE SMALLINT", "FK_NAME", "PK_NAME", "DEFERRABILITY SMALLINT" });
      
      indexColumnName = "PKTABLE_NAME";
      break;
    case 20: 
      setObjectName("CONSTRAINTS");
      cols = createColumns(new String[] { "CONSTRAINT_CATALOG", "CONSTRAINT_SCHEMA", "CONSTRAINT_NAME", "CONSTRAINT_TYPE", "TABLE_CATALOG", "TABLE_SCHEMA", "TABLE_NAME", "UNIQUE_INDEX_NAME", "CHECK_EXPRESSION", "COLUMN_LIST", "REMARKS", "SQL", "ID INT" });
      
      indexColumnName = "TABLE_NAME";
      break;
    case 22: 
      setObjectName("CONSTANTS");
      cols = createColumns(new String[] { "CONSTANT_CATALOG", "CONSTANT_SCHEMA", "CONSTANT_NAME", "DATA_TYPE INT", "REMARKS", "SQL", "ID INT" });
      
      break;
    case 23: 
      setObjectName("DOMAINS");
      cols = createColumns(new String[] { "DOMAIN_CATALOG", "DOMAIN_SCHEMA", "DOMAIN_NAME", "COLUMN_DEFAULT", "IS_NULLABLE", "DATA_TYPE INT", "PRECISION INT", "SCALE INT", "TYPE_NAME", "SELECTIVITY INT", "CHECK_CONSTRAINT", "REMARKS", "SQL", "ID INT" });
      
      break;
    case 24: 
      setObjectName("TRIGGERS");
      cols = createColumns(new String[] { "TRIGGER_CATALOG", "TRIGGER_SCHEMA", "TRIGGER_NAME", "TRIGGER_TYPE", "TABLE_CATALOG", "TABLE_SCHEMA", "TABLE_NAME", "BEFORE BIT", "JAVA_CLASS", "QUEUE_SIZE INT", "NO_WAIT BIT", "REMARKS", "SQL", "ID INT" });
      
      break;
    case 25: 
      setObjectName("SESSIONS");
      cols = createColumns(new String[] { "ID INT", "USER_NAME", "SESSION_START", "STATEMENT", "STATEMENT_START", "CONTAINS_UNCOMMITTED" });
      
      break;
    case 26: 
      setObjectName("LOCKS");
      cols = createColumns(new String[] { "TABLE_SCHEMA", "TABLE_NAME", "SESSION_ID INT", "LOCK_TYPE" });
      
      break;
    case 27: 
      setObjectName("SESSION_STATE");
      cols = createColumns(new String[] { "KEY", "SQL" });
      
      break;
    case 28: 
      setObjectName("QUERY_STATISTICS");
      cols = createColumns(new String[] { "SQL_STATEMENT", "EXECUTION_COUNT INT", "MIN_EXECUTION_TIME LONG", "MAX_EXECUTION_TIME LONG", "CUMULATIVE_EXECUTION_TIME LONG", "AVERAGE_EXECUTION_TIME DOUBLE", "STD_DEV_EXECUTION_TIME DOUBLE", "MIN_ROW_COUNT INT", "MAX_ROW_COUNT INT", "CUMULATIVE_ROW_COUNT LONG", "AVERAGE_ROW_COUNT DOUBLE", "STD_DEV_ROW_COUNT DOUBLE" });
      
      break;
    default: 
      throw DbException.throwInternalError("type=" + type);
    }
    setColumns(cols);
    if (indexColumnName == null)
    {
      this.indexColumn = -1;
      this.metaIndex = null;
    }
    else
    {
      this.indexColumn = getColumn(indexColumnName).getColumnId();
      IndexColumn[] indexCols = IndexColumn.wrap(new Column[] { cols[this.indexColumn] });
      
      this.metaIndex = new MetaIndex(this, indexCols, false);
    }
  }
  
  private Column[] createColumns(String... names)
  {
    Column[] cols = new Column[names.length];
    for (int i = 0; i < names.length; i++)
    {
      String nameType = names[i];
      int idx = nameType.indexOf(' ');
      String name;
      int dataType;
      String name;
      if (idx < 0)
      {
        int dataType = this.database.getMode().lowerCaseIdentifiers ? 14 : 13;
        
        name = nameType;
      }
      else
      {
        dataType = DataType.getTypeByName(nameType.substring(idx + 1)).type;
        name = nameType.substring(0, idx);
      }
      cols[i] = new Column(name, dataType);
    }
    return cols;
  }
  
  public String getDropSQL()
  {
    return null;
  }
  
  public String getCreateSQL()
  {
    return null;
  }
  
  public Index addIndex(Session session, String indexName, int indexId, IndexColumn[] cols, IndexType indexType, boolean create, String indexComment)
  {
    throw DbException.getUnsupportedException("META");
  }
  
  public boolean lock(Session session, boolean exclusive, boolean forceLockEvenInMvcc)
  {
    return false;
  }
  
  public boolean isLockedExclusively()
  {
    return false;
  }
  
  private String identifier(String s)
  {
    if (this.database.getMode().lowerCaseIdentifiers) {
      s = s == null ? null : StringUtils.toLowerEnglish(s);
    }
    return s;
  }
  
  private ArrayList<Table> getAllTables(Session session)
  {
    ArrayList<Table> tables = this.database.getAllTablesAndViews(true);
    ArrayList<Table> tempTables = session.getLocalTempTables();
    tables.addAll(tempTables);
    return tables;
  }
  
  private boolean checkIndex(Session session, String value, Value indexFrom, Value indexTo)
  {
    if ((value == null) || ((indexFrom == null) && (indexTo == null))) {
      return true;
    }
    Database db = session.getDatabase();
    Value v;
    Value v;
    if (this.database.getMode().lowerCaseIdentifiers) {
      v = ValueStringIgnoreCase.get(value);
    } else {
      v = ValueString.get(value);
    }
    if ((indexFrom != null) && (db.compare(v, indexFrom) < 0)) {
      return false;
    }
    if ((indexTo != null) && (db.compare(v, indexTo) > 0)) {
      return false;
    }
    return true;
  }
  
  private static String replaceNullWithEmpty(String s)
  {
    return s == null ? "" : s;
  }
  
  private boolean hideTable(Table table, Session session)
  {
    return (table.isHidden()) && (session != this.database.getSystemSession());
  }
  
  public ArrayList<Row> generateRows(Session session, SearchRow first, SearchRow last)
  {
    Value indexFrom = null;Value indexTo = null;
    if (this.indexColumn >= 0)
    {
      if (first != null) {
        indexFrom = first.getValue(this.indexColumn);
      }
      if (last != null) {
        indexTo = last.getValue(this.indexColumn);
      }
    }
    ArrayList<Row> rows = New.arrayList();
    String catalog = identifier(this.database.getShortName());
    boolean admin = session.getUser().isAdmin();
    label1099:
    String collation;
    switch (this.type)
    {
    case 0: 
      for (Table table : getAllTables(session))
      {
        String tableName = identifier(table.getName());
        if ((checkIndex(session, tableName, indexFrom, indexTo)) && 
        
          (!hideTable(table, session)))
        {
          String storageType;
          String storageType;
          if (table.isTemporary())
          {
            String storageType;
            if (table.isGlobalTemporary()) {
              storageType = "GLOBAL TEMPORARY";
            } else {
              storageType = "LOCAL TEMPORARY";
            }
          }
          else
          {
            storageType = table.isPersistIndexes() ? "CACHED" : "MEMORY";
          }
          String sql = table.getCreateSQL();
          if ((!admin) && 
            (sql != null) && (sql.contains("--hide--"))) {
            sql = "-";
          }
          add(rows, new String[] { catalog, identifier(table.getSchema().getName()), tableName, table.getTableType(), storageType, sql, replaceNullWithEmpty(table.getComment()), "" + table.getMaxDataModificationId(), "" + table.getId(), null, table.getClass().getName(), "" + table.getRowCountApproximation() });
        }
      }
      break;
    case 1: 
      for (Table table : getAllTables(session))
      {
        String tableName = identifier(table.getName());
        if ((checkIndex(session, tableName, indexFrom, indexTo)) && 
        
          (!hideTable(table, session)))
        {
          Column[] cols = table.getColumns();
          String collation = this.database.getCompareMode().getName();
          for (int j = 0; j < cols.length; j++)
          {
            Column c = cols[j];
            Sequence sequence = c.getSequence();
            add(rows, new String[] { catalog, identifier(table.getSchema().getName()), tableName, identifier(c.getName()), String.valueOf(j + 1), c.getDefaultSQL(), c.isNullable() ? "YES" : "NO", "" + DataType.convertTypeToSQLType(c.getType()), "" + c.getPrecisionAsInt(), "" + c.getPrecisionAsInt(), "" + c.getPrecisionAsInt(), "10", "" + c.getScale(), "Unicode", collation, identifier(DataType.getDataType(c.getType()).name), "" + (c.isNullable() ? 1 : 0), "" + (c.getComputed() ? "TRUE" : "FALSE"), "" + c.getSelectivity(), c.getCheckConstraintSQL(session, c.getName()), sequence == null ? null : sequence.getName(), replaceNullWithEmpty(c.getComment()), null });
          }
        }
      }
      break;
    case 2: 
      for (Iterator i$ = getAllTables(session).iterator(); i$.hasNext(); goto 1179)
      {
        Table table = (Table)i$.next();
        String tableName = identifier(table.getName());
        if ((!checkIndex(session, tableName, indexFrom, indexTo)) || 
        
          (hideTable(table, session))) {
          break label1099;
        }
        ArrayList<Index> indexes = table.getIndexes();
        ArrayList<Constraint> constraints = table.getConstraints();
        int j = 0;
        if ((indexes != null) && (j < indexes.size()))
        {
          Index index = (Index)indexes.get(j);
          if (index.getCreateSQL() != null)
          {
            String constraintName = null;
            for (int k = 0; (constraints != null) && (k < constraints.size()); k++)
            {
              Constraint constraint = (Constraint)constraints.get(k);
              if (constraint.usesIndex(index)) {
                if (index.getIndexType().isPrimaryKey())
                {
                  if (constraint.getConstraintType().equals("PRIMARY KEY")) {
                    constraintName = constraint.getName();
                  }
                }
                else {
                  constraintName = constraint.getName();
                }
              }
            }
            IndexColumn[] cols = index.getIndexColumns();
            String indexClass;
            String indexClass;
            if ((index instanceof MultiVersionIndex)) {
              indexClass = ((MultiVersionIndex)index).getBaseIndex().getClass().getName();
            } else {
              indexClass = index.getClass().getName();
            }
            for (int k = 0; k < cols.length; k++)
            {
              IndexColumn idxCol = cols[k];
              Column column = idxCol.column;
              add(rows, new String[] { catalog, identifier(table.getSchema().getName()), tableName, index.getIndexType().isUnique() ? "FALSE" : "TRUE", identifier(index.getName()), "" + (k + 1), identifier(column.getName()), "0", index.getIndexType().isPrimaryKey() ? "TRUE" : "FALSE", index.getIndexType().getSQL(), index.getIndexType().getBelongsToConstraint() ? "TRUE" : "FALSE", "3", (idxCol.sortType & 0x1) != 0 ? "D" : "A", "0", "", replaceNullWithEmpty(index.getComment()), index.getCreateSQL(), "" + index.getId(), "" + idxCol.sortType, constraintName, indexClass });
            }
          }
          j++;
        }
      }
      break;
    case 3: 
      add(rows, new String[] { "TABLE" });
      add(rows, new String[] { "TABLE LINK" });
      add(rows, new String[] { "SYSTEM TABLE" });
      add(rows, new String[] { "VIEW" });
      break;
    case 5: 
      add(rows, new String[] { catalog });
      break;
    case 6: 
      for (Setting s : this.database.getAllSettings())
      {
        String value = s.getStringValue();
        if (value == null) {
          value = "" + s.getIntValue();
        }
        add(rows, new String[] { identifier(s.getName()), value });
      }
      add(rows, new String[] { "info.BUILD_ID", "187" });
      add(rows, new String[] { "info.VERSION_MAJOR", "1" });
      add(rows, new String[] { "info.VERSION_MINOR", "4" });
      add(rows, new String[] { "info.VERSION", "" + Constants.getFullVersion() });
      if (admin)
      {
        String[] settings = { "java.runtime.version", "java.vm.name", "java.vendor", "os.name", "os.arch", "os.version", "sun.os.patch.level", "file.separator", "path.separator", "line.separator", "user.country", "user.language", "user.variant", "file.encoding" };
        for (String s : settings) {
          add(rows, new String[] { "property." + s, Utils.getProperty(s, "") });
        }
      }
      add(rows, new String[] { "EXCLUSIVE", this.database.getExclusiveSession() == null ? "FALSE" : "TRUE" });
      
      add(rows, new String[] { "MODE", this.database.getMode().getName() });
      add(rows, new String[] { "MULTI_THREADED", this.database.isMultiThreaded() ? "1" : "0" });
      add(rows, new String[] { "MVCC", this.database.isMultiVersion() ? "TRUE" : "FALSE" });
      add(rows, new String[] { "QUERY_TIMEOUT", "" + session.getQueryTimeout() });
      add(rows, new String[] { "RETENTION_TIME", "" + this.database.getRetentionTime() });
      add(rows, new String[] { "LOG", "" + this.database.getLogMode() });
      
      ArrayList<String> settingNames = New.arrayList();
      HashMap<String, String> s = this.database.getSettings().getSettings();
      for (String k : s.keySet()) {
        settingNames.add(k);
      }
      Collections.sort(settingNames);
      for (String k : settingNames) {
        add(rows, new String[] { k, (String)s.get(k) });
      }
      if (this.database.isPersistent())
      {
        PageStore store = this.database.getPageStore();
        if (store != null)
        {
          add(rows, new String[] { "info.FILE_WRITE_TOTAL", "" + store.getWriteCountTotal() });
          
          add(rows, new String[] { "info.FILE_WRITE", "" + store.getWriteCount() });
          
          add(rows, new String[] { "info.FILE_READ", "" + store.getReadCount() });
          
          add(rows, new String[] { "info.PAGE_COUNT", "" + store.getPageCount() });
          
          add(rows, new String[] { "info.PAGE_SIZE", "" + store.getPageSize() });
          
          add(rows, new String[] { "info.CACHE_MAX_SIZE", "" + store.getCache().getMaxMemory() });
          
          add(rows, new String[] { "info.CACHE_SIZE", "" + store.getCache().getMemory() });
        }
        MVTableEngine.Store mvStore = this.database.getMvStore();
        if (mvStore != null)
        {
          FileStore fs = mvStore.getStore().getFileStore();
          add(rows, new String[] { "info.FILE_WRITE", "" + fs.getWriteCount() });
          
          add(rows, new String[] { "info.FILE_READ", "" + fs.getReadCount() });
          long size;
          try
          {
            size = fs.getFile().size();
          }
          catch (IOException e)
          {
            throw DbException.convertIOException(e, "Can not get size");
          }
          int pageSize = 4096;
          long pageCount = size / pageSize;
          add(rows, new String[] { "info.PAGE_COUNT", "" + pageCount });
          
          add(rows, new String[] { "info.PAGE_SIZE", "" + pageSize });
          
          add(rows, new String[] { "info.CACHE_MAX_SIZE", "" + mvStore.getStore().getCacheSize() });
          
          add(rows, new String[] { "info.CACHE_SIZE", "" + mvStore.getStore().getCacheSizeUsed() });
        }
      }
      break;
    case 4: 
      for (DataType t : DataType.getTypes()) {
        if ((!t.hidden) && (t.sqlType != 0)) {
          add(rows, new String[] { t.name, String.valueOf(t.sqlType), String.valueOf(MathUtils.convertLongToInt(t.maxPrecision)), t.prefix, t.suffix, t.params, String.valueOf(t.autoIncrement), String.valueOf(t.minScale), String.valueOf(t.maxScale), t.decimal ? "10" : null, String.valueOf(t.sqlTypePos), String.valueOf(t.caseSensitive), "1", "3" });
        }
      }
      break;
    case 7: 
      String resource = "/org/h2/res/help.csv";
      try
      {
        byte[] data = Utils.getResource(resource);
        Reader reader = new InputStreamReader(new ByteArrayInputStream(data));
        
        Csv csv = new Csv();
        csv.setLineCommentCharacter('#');
        ResultSet rs = csv.read(reader, null);
        for (int i = 0; rs.next(); i++) {
          add(rows, new String[] { String.valueOf(i), rs.getString(1).trim(), rs.getString(2).trim(), rs.getString(3).trim(), rs.getString(4).trim() });
        }
      }
      catch (Exception e)
      {
        throw DbException.convert(e);
      }
    case 8: 
      for (SchemaObject obj : this.database.getAllSchemaObjects(3))
      {
        Sequence s = (Sequence)obj;
        add(rows, new String[] { catalog, identifier(s.getSchema().getName()), identifier(s.getName()), String.valueOf(s.getCurrentValue()), String.valueOf(s.getIncrement()), s.getBelongsToTable() ? "TRUE" : "FALSE", replaceNullWithEmpty(s.getComment()), String.valueOf(s.getCacheSize()), String.valueOf(s.getMinValue()), String.valueOf(s.getMaxValue()), s.getCycle() ? "TRUE" : "FALSE", "" + s.getId() });
      }
      break;
    case 9: 
      for (User u : this.database.getAllUsers()) {
        if ((admin) || (session.getUser() == u)) {
          add(rows, new String[] { identifier(u.getName()), String.valueOf(u.isAdmin()), replaceNullWithEmpty(u.getComment()), "" + u.getId() });
        }
      }
      break;
    case 10: 
      for (Role r : this.database.getAllRoles()) {
        if ((admin) || (session.getUser().isRoleGranted(r))) {
          add(rows, new String[] { identifier(r.getName()), replaceNullWithEmpty(r.getComment()), "" + r.getId() });
        }
      }
      break;
    case 11: 
      if (admin) {
        for (Right r : this.database.getAllRights())
        {
          Role role = r.getGrantedRole();
          DbObject grantee = r.getGrantee();
          String rightType = grantee.getType() == 2 ? "USER" : "ROLE";
          if (role == null)
          {
            Table granted = r.getGrantedTable();
            String tableName = identifier(granted.getName());
            if (checkIndex(session, tableName, indexFrom, indexTo)) {
              add(rows, new String[] { identifier(grantee.getName()), rightType, "", r.getRights(), identifier(granted.getSchema().getName()), identifier(granted.getName()), "" + r.getId() });
            }
          }
          else
          {
            add(rows, new String[] { identifier(grantee.getName()), rightType, identifier(role.getName()), "", "", "", "" + r.getId() });
          }
        }
      }
      break;
    case 12: 
      for (SchemaObject aliasAsSchemaObject : this.database.getAllSchemaObjects(9))
      {
        FunctionAlias alias = (FunctionAlias)aliasAsSchemaObject;
        FunctionAlias.JavaMethod[] methods;
        try
        {
          methods = alias.getJavaMethods();
        }
        catch (DbException e)
        {
          methods = new FunctionAlias.JavaMethod[0];
        }
        for (FunctionAlias.JavaMethod method : methods)
        {
          int returnsResult = method.getDataType() == 0 ? 1 : 2;
          
          add(rows, new String[] { catalog, alias.getSchema().getName(), identifier(alias.getName()), alias.getJavaClassName(), alias.getJavaMethodName(), "" + DataType.convertTypeToSQLType(method.getDataType()), DataType.getDataType(method.getDataType()).name, "" + method.getParameterCount(), "" + returnsResult, replaceNullWithEmpty(alias.getComment()), "" + alias.getId(), alias.getSource() });
        }
      }
      for (UserAggregate agg : this.database.getAllAggregates())
      {
        int returnsResult = 2;
        add(rows, new String[] { catalog, "PUBLIC", identifier(agg.getName()), agg.getJavaClassName(), "", "" + DataType.convertTypeToSQLType(0), DataType.getDataType(0).name, "1", "" + returnsResult, replaceNullWithEmpty(agg.getComment()), "" + agg.getId(), "" });
      }
      break;
    case 21: 
      for (SchemaObject aliasAsSchemaObject : this.database.getAllSchemaObjects(9))
      {
        FunctionAlias alias = (FunctionAlias)aliasAsSchemaObject;
        FunctionAlias.JavaMethod[] methods;
        try
        {
          methods = alias.getJavaMethods();
        }
        catch (DbException e)
        {
          methods = new FunctionAlias.JavaMethod[0];
        }
        for (FunctionAlias.JavaMethod method : methods)
        {
          if (method.getDataType() != 0)
          {
            DataType dt = DataType.getDataType(method.getDataType());
            add(rows, new String[] { catalog, alias.getSchema().getName(), identifier(alias.getName()), alias.getJavaClassName(), alias.getJavaMethodName(), "" + method.getParameterCount(), "0", "P0", "" + DataType.convertTypeToSQLType(method.getDataType()), dt.name, "" + MathUtils.convertLongToInt(dt.defaultPrecision), "" + dt.defaultScale, "10", "2", "5", "", null });
          }
          Class<?>[] columnList = method.getColumnClasses();
          for (int k = 0; k < columnList.length; k++) {
            if ((!method.hasConnectionParam()) || (k != 0))
            {
              Class<?> clazz = columnList[k];
              int dataType = DataType.getTypeFromClass(clazz);
              DataType dt = DataType.getDataType(dataType);
              int nullable = clazz.isPrimitive() ? 0 : 1;
              
              add(rows, new String[] { catalog, alias.getSchema().getName(), identifier(alias.getName()), alias.getJavaClassName(), alias.getJavaMethodName(), "" + method.getParameterCount(), "" + (k + (method.hasConnectionParam() ? 0 : 1)), "P" + (k + 1), "" + DataType.convertTypeToSQLType(dt.type), dt.name, "" + MathUtils.convertLongToInt(dt.defaultPrecision), "" + dt.defaultScale, "10", "" + nullable, "1", "", null });
            }
          }
        }
      }
      break;
    case 13: 
      collation = this.database.getCompareMode().getName();
      for (Schema schema : this.database.getAllSchemas()) {
        add(rows, new String[] { catalog, identifier(schema.getName()), identifier(schema.getOwner().getName()), "Unicode", collation, "PUBLIC".equals(schema.getName()) ? "TRUE" : "FALSE", replaceNullWithEmpty(schema.getComment()), "" + schema.getId() });
      }
      break;
    case 14: 
      for (Right r : this.database.getAllRights())
      {
        Table table = r.getGrantedTable();
        if ((table != null) && (!hideTable(table, session)))
        {
          String tableName = identifier(table.getName());
          if (checkIndex(session, tableName, indexFrom, indexTo)) {
            addPrivileges(rows, r.getGrantee(), catalog, table, null, r.getRightMask());
          }
        }
      }
      break;
    case 15: 
      for (Right r : this.database.getAllRights())
      {
        Table table = r.getGrantedTable();
        if ((table != null) && (!hideTable(table, session)))
        {
          String tableName = identifier(table.getName());
          if (checkIndex(session, tableName, indexFrom, indexTo))
          {
            DbObject grantee = r.getGrantee();
            int mask = r.getRightMask();
            for (Column column : table.getColumns()) {
              addPrivileges(rows, grantee, catalog, table, column.getName(), mask);
            }
          }
        }
      }
      break;
    case 16: 
      for (Locale l : Collator.getAvailableLocales()) {
        add(rows, new String[] { CompareMode.getName(l), l.toString() });
      }
      break;
    case 17: 
      for (Table table : getAllTables(session)) {
        if (table.getTableType().equals("VIEW"))
        {
          String tableName = identifier(table.getName());
          if (checkIndex(session, tableName, indexFrom, indexTo))
          {
            TableView view = (TableView)table;
            add(rows, new String[] { catalog, identifier(table.getSchema().getName()), tableName, table.getCreateSQL(), "NONE", "NO", view.isInvalid() ? "INVALID" : "VALID", replaceNullWithEmpty(view.getComment()), "" + view.getId() });
          }
        }
      }
      break;
    case 18: 
      ArrayList<InDoubtTransaction> prepared = this.database.getInDoubtTransactions();
      if ((prepared != null) && (admin)) {
        for (InDoubtTransaction prep : prepared) {
          add(rows, new String[] { prep.getTransactionName(), prep.getState() });
        }
      }
      break;
    case 19: 
      for (SchemaObject obj : this.database.getAllSchemaObjects(5))
      {
        Constraint constraint = (Constraint)obj;
        if (constraint.getConstraintType().equals("REFERENTIAL"))
        {
          ConstraintReferential ref = (ConstraintReferential)constraint;
          IndexColumn[] cols = ref.getColumns();
          IndexColumn[] refCols = ref.getRefColumns();
          Table tab = ref.getTable();
          Table refTab = ref.getRefTable();
          String tableName = identifier(refTab.getName());
          if (checkIndex(session, tableName, indexFrom, indexTo))
          {
            int update = getRefAction(ref.getUpdateAction());
            int delete = getRefAction(ref.getDeleteAction());
            for (int j = 0; j < cols.length; j++) {
              add(rows, new String[] { catalog, identifier(refTab.getSchema().getName()), identifier(refTab.getName()), identifier(refCols[j].column.getName()), catalog, identifier(tab.getSchema().getName()), identifier(tab.getName()), identifier(cols[j].column.getName()), String.valueOf(j + 1), String.valueOf(update), String.valueOf(delete), identifier(ref.getName()), identifier(ref.getUniqueIndex().getName()), "7" });
            }
          }
        }
      }
      break;
    case 20: 
      for (SchemaObject obj : this.database.getAllSchemaObjects(5))
      {
        Constraint constraint = (Constraint)obj;
        String constraintType = constraint.getConstraintType();
        String checkExpression = null;
        IndexColumn[] indexColumns = null;
        Table table = constraint.getTable();
        if (!hideTable(table, session))
        {
          Index index = constraint.getUniqueIndex();
          String uniqueIndexName = null;
          if (index != null) {
            uniqueIndexName = index.getName();
          }
          String tableName = identifier(table.getName());
          if (checkIndex(session, tableName, indexFrom, indexTo))
          {
            if (constraintType.equals("CHECK")) {
              checkExpression = ((ConstraintCheck)constraint).getExpression().getSQL();
            } else if ((constraintType.equals("UNIQUE")) || (constraintType.equals("PRIMARY KEY"))) {
              indexColumns = ((ConstraintUnique)constraint).getColumns();
            } else if (constraintType.equals("REFERENTIAL")) {
              indexColumns = ((ConstraintReferential)constraint).getColumns();
            }
            String columnList = null;
            if (indexColumns != null)
            {
              StatementBuilder buff = new StatementBuilder();
              for (IndexColumn col : indexColumns)
              {
                buff.appendExceptFirst(",");
                buff.append(col.column.getName());
              }
              columnList = buff.toString();
            }
            add(rows, new String[] { catalog, identifier(constraint.getSchema().getName()), identifier(constraint.getName()), constraintType, catalog, identifier(table.getSchema().getName()), tableName, uniqueIndexName, checkExpression, columnList, replaceNullWithEmpty(constraint.getComment()), constraint.getCreateSQL(), "" + constraint.getId() });
          }
        }
      }
      break;
    case 22: 
      for (SchemaObject obj : this.database.getAllSchemaObjects(11))
      {
        Constant constant = (Constant)obj;
        ValueExpression expr = constant.getValue();
        add(rows, new String[] { catalog, identifier(constant.getSchema().getName()), identifier(constant.getName()), "" + DataType.convertTypeToSQLType(expr.getType()), replaceNullWithEmpty(constant.getComment()), expr.getSQL(), "" + constant.getId() });
      }
      break;
    case 23: 
      for (UserDataType dt : this.database.getAllUserDataTypes())
      {
        Column col = dt.getColumn();
        add(rows, new String[] { catalog, "PUBLIC", identifier(dt.getName()), col.getDefaultSQL(), col.isNullable() ? "YES" : "NO", "" + col.getDataType().sqlType, "" + col.getPrecisionAsInt(), "" + col.getScale(), col.getDataType().name, "" + col.getSelectivity(), "" + col.getCheckConstraintSQL(session, "VALUE"), replaceNullWithEmpty(dt.getComment()), "" + dt.getCreateSQL(), "" + dt.getId() });
      }
      break;
    case 24: 
      for (SchemaObject obj : this.database.getAllSchemaObjects(4))
      {
        TriggerObject trigger = (TriggerObject)obj;
        Table table = trigger.getTable();
        add(rows, new String[] { catalog, identifier(trigger.getSchema().getName()), identifier(trigger.getName()), trigger.getTypeNameList(), catalog, identifier(table.getSchema().getName()), identifier(table.getName()), "" + trigger.isBefore(), trigger.getTriggerClassName(), "" + trigger.getQueueSize(), "" + trigger.isNoWait(), replaceNullWithEmpty(trigger.getComment()), trigger.getCreateSQL(), "" + trigger.getId() });
      }
      break;
    case 25: 
      long now = System.currentTimeMillis();
      for (Session s : this.database.getSessions(false)) {
        if ((admin) || (s == session))
        {
          Command command = s.getCurrentCommand();
          long start = s.getCurrentCommandStart();
          if (start == 0L) {
            start = now;
          }
          add(rows, new String[] { "" + s.getId(), s.getUser().getName(), new Timestamp(s.getSessionStart()).toString(), command == null ? null : command.toString(), new Timestamp(start).toString(), "" + s.containsUncommitted() });
        }
      }
      break;
    case 26: 
      for (Session s : this.database.getSessions(false)) {
        if ((admin) || (s == session)) {
          for (Table table : s.getLocks()) {
            add(rows, new String[] { table.getSchema().getName(), table.getName(), "" + s.getId(), table.isLockedExclusivelyBy(s) ? "WRITE" : "READ" });
          }
        }
      }
      break;
    case 27: 
      for (String name : session.getVariableNames())
      {
        Value v = session.getVariable(name);
        add(rows, new String[] { "@" + name, "SET @" + name + " " + v.getSQL() });
      }
      for (Table table : session.getLocalTempTables()) {
        add(rows, new String[] { "TABLE " + table.getName(), table.getCreateSQL() });
      }
      String[] path = session.getSchemaSearchPath();
      if ((path != null) && (path.length > 0))
      {
        StatementBuilder buff = new StatementBuilder("SET SCHEMA_SEARCH_PATH ");
        for (String p : path)
        {
          buff.appendExceptFirst(", ");
          buff.append(StringUtils.quoteIdentifier(p));
        }
        add(rows, new String[] { "SCHEMA_SEARCH_PATH", buff.toString() });
      }
      String schema = session.getCurrentSchemaName();
      if (schema != null) {
        add(rows, new String[] { "SCHEMA", "SET SCHEMA " + StringUtils.quoteIdentifier(schema) });
      }
      break;
    case 28: 
      QueryStatisticsData control = this.database.getQueryStatisticsData();
      if (control != null) {
        for (QueryStatisticsData.QueryEntry entry : control.getQueries()) {
          add(rows, new String[] { entry.sqlStatement, "" + entry.count, "" + entry.executionTimeMin, "" + entry.executionTimeMax, "" + entry.executionTimeCumulative, "" + entry.executionTimeMean, "" + entry.getExecutionTimeStandardDeviation(), "" + entry.rowCountMin, "" + entry.rowCountMax, "" + entry.rowCountCumulative, "" + entry.rowCountMean, "" + entry.getRowCountStandardDeviation() });
        }
      }
      break;
    default: 
      DbException.throwInternalError("type=" + this.type);
    }
    return rows;
  }
  
  private static int getRefAction(int action)
  {
    switch (action)
    {
    case 1: 
      return 0;
    case 0: 
      return 1;
    case 2: 
      return 4;
    case 3: 
      return 2;
    }
    throw DbException.throwInternalError("action=" + action);
  }
  
  public void removeRow(Session session, Row row)
  {
    throw DbException.getUnsupportedException("META");
  }
  
  public void addRow(Session session, Row row)
  {
    throw DbException.getUnsupportedException("META");
  }
  
  public void removeChildrenAndResources(Session session)
  {
    throw DbException.getUnsupportedException("META");
  }
  
  public void close(Session session) {}
  
  public void unlock(Session s) {}
  
  private void addPrivileges(ArrayList<Row> rows, DbObject grantee, String catalog, Table table, String column, int rightMask)
  {
    if ((rightMask & 0x1) != 0) {
      addPrivilege(rows, grantee, catalog, table, column, "SELECT");
    }
    if ((rightMask & 0x4) != 0) {
      addPrivilege(rows, grantee, catalog, table, column, "INSERT");
    }
    if ((rightMask & 0x8) != 0) {
      addPrivilege(rows, grantee, catalog, table, column, "UPDATE");
    }
    if ((rightMask & 0x2) != 0) {
      addPrivilege(rows, grantee, catalog, table, column, "DELETE");
    }
  }
  
  private void addPrivilege(ArrayList<Row> rows, DbObject grantee, String catalog, Table table, String column, String right)
  {
    String isGrantable = "NO";
    if (grantee.getType() == 2)
    {
      User user = (User)grantee;
      if (user.isAdmin()) {
        isGrantable = "YES";
      }
    }
    if (column == null) {
      add(rows, new String[] { null, identifier(grantee.getName()), catalog, identifier(table.getSchema().getName()), identifier(table.getName()), right, isGrantable });
    } else {
      add(rows, new String[] { null, identifier(grantee.getName()), catalog, identifier(table.getSchema().getName()), identifier(table.getName()), identifier(column), right, isGrantable });
    }
  }
  
  private void add(ArrayList<Row> rows, String... strings)
  {
    Value[] values = new Value[strings.length];
    for (int i = 0; i < strings.length; i++)
    {
      String s = strings[i];
      Value v = s == null ? ValueNull.INSTANCE : ValueString.get(s);
      Column col = this.columns[i];
      v = col.convert(v);
      values[i] = v;
    }
    Row row = new Row(values, 1);
    row.setKey(rows.size());
    rows.add(row);
  }
  
  public void checkRename()
  {
    throw DbException.getUnsupportedException("META");
  }
  
  public void checkSupportAlter()
  {
    throw DbException.getUnsupportedException("META");
  }
  
  public void truncate(Session session)
  {
    throw DbException.getUnsupportedException("META");
  }
  
  public long getRowCount(Session session)
  {
    throw DbException.throwInternalError();
  }
  
  public boolean canGetRowCount()
  {
    return false;
  }
  
  public boolean canDrop()
  {
    return false;
  }
  
  public String getTableType()
  {
    return "SYSTEM TABLE";
  }
  
  public Index getScanIndex(Session session)
  {
    return new MetaIndex(this, IndexColumn.wrap(this.columns), true);
  }
  
  public ArrayList<Index> getIndexes()
  {
    ArrayList<Index> list = New.arrayList();
    if (this.metaIndex == null) {
      return list;
    }
    list.add(new MetaIndex(this, IndexColumn.wrap(this.columns), true));
    
    list.add(this.metaIndex);
    return list;
  }
  
  public long getMaxDataModificationId()
  {
    switch (this.type)
    {
    case 6: 
    case 18: 
    case 25: 
    case 26: 
    case 27: 
      return Long.MAX_VALUE;
    }
    return this.database.getModificationDataId();
  }
  
  public Index getUniqueIndex()
  {
    return null;
  }
  
  public static int getMetaTableTypeCount()
  {
    return 29;
  }
  
  public long getRowCountApproximation()
  {
    return 1000L;
  }
  
  public long getDiskSpaceUsed()
  {
    return 0L;
  }
  
  public boolean isDeterministic()
  {
    return true;
  }
  
  public boolean canReference()
  {
    return false;
  }
}
