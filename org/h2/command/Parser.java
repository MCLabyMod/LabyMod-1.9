package org.h2.command;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.text.Collator;
import java.util.ArrayList;
import java.util.HashSet;
import org.h2.command.ddl.AlterIndexRename;
import org.h2.command.ddl.AlterSchemaRename;
import org.h2.command.ddl.AlterTableAddConstraint;
import org.h2.command.ddl.AlterTableAlterColumn;
import org.h2.command.ddl.AlterTableDropConstraint;
import org.h2.command.ddl.AlterTableRename;
import org.h2.command.ddl.AlterTableRenameColumn;
import org.h2.command.ddl.AlterUser;
import org.h2.command.ddl.AlterView;
import org.h2.command.ddl.Analyze;
import org.h2.command.ddl.CreateAggregate;
import org.h2.command.ddl.CreateConstant;
import org.h2.command.ddl.CreateFunctionAlias;
import org.h2.command.ddl.CreateIndex;
import org.h2.command.ddl.CreateLinkedTable;
import org.h2.command.ddl.CreateRole;
import org.h2.command.ddl.CreateSchema;
import org.h2.command.ddl.CreateSequence;
import org.h2.command.ddl.CreateTable;
import org.h2.command.ddl.CreateTableData;
import org.h2.command.ddl.CreateTrigger;
import org.h2.command.ddl.CreateUser;
import org.h2.command.ddl.CreateUserDataType;
import org.h2.command.ddl.CreateView;
import org.h2.command.ddl.DeallocateProcedure;
import org.h2.command.ddl.DefineCommand;
import org.h2.command.ddl.DropAggregate;
import org.h2.command.ddl.DropConstant;
import org.h2.command.ddl.DropDatabase;
import org.h2.command.ddl.DropFunctionAlias;
import org.h2.command.ddl.DropIndex;
import org.h2.command.ddl.DropRole;
import org.h2.command.ddl.DropSchema;
import org.h2.command.ddl.DropSequence;
import org.h2.command.ddl.DropTable;
import org.h2.command.ddl.DropTrigger;
import org.h2.command.ddl.DropUser;
import org.h2.command.ddl.DropUserDataType;
import org.h2.command.ddl.DropView;
import org.h2.command.ddl.GrantRevoke;
import org.h2.command.ddl.PrepareProcedure;
import org.h2.command.ddl.SetComment;
import org.h2.command.ddl.TruncateTable;
import org.h2.command.dml.AlterSequence;
import org.h2.command.dml.AlterTableSet;
import org.h2.command.dml.BackupCommand;
import org.h2.command.dml.Call;
import org.h2.command.dml.Delete;
import org.h2.command.dml.ExecuteProcedure;
import org.h2.command.dml.Explain;
import org.h2.command.dml.Insert;
import org.h2.command.dml.Merge;
import org.h2.command.dml.NoOperation;
import org.h2.command.dml.Query;
import org.h2.command.dml.Replace;
import org.h2.command.dml.RunScriptCommand;
import org.h2.command.dml.ScriptCommand;
import org.h2.command.dml.Select;
import org.h2.command.dml.SelectOrderBy;
import org.h2.command.dml.SelectUnion;
import org.h2.command.dml.Set;
import org.h2.command.dml.SetTypes;
import org.h2.command.dml.TransactionCommand;
import org.h2.command.dml.Update;
import org.h2.engine.Database;
import org.h2.engine.DbSettings;
import org.h2.engine.FunctionAlias;
import org.h2.engine.Mode;
import org.h2.engine.Procedure;
import org.h2.engine.Session;
import org.h2.engine.SysProperties;
import org.h2.engine.User;
import org.h2.engine.UserAggregate;
import org.h2.engine.UserDataType;
import org.h2.expression.Aggregate;
import org.h2.expression.Alias;
import org.h2.expression.CompareLike;
import org.h2.expression.Comparison;
import org.h2.expression.ConditionAndOr;
import org.h2.expression.ConditionExists;
import org.h2.expression.ConditionIn;
import org.h2.expression.ConditionInSelect;
import org.h2.expression.ConditionNot;
import org.h2.expression.Expression;
import org.h2.expression.ExpressionColumn;
import org.h2.expression.ExpressionList;
import org.h2.expression.Function;
import org.h2.expression.FunctionCall;
import org.h2.expression.JavaAggregate;
import org.h2.expression.JavaFunction;
import org.h2.expression.Operation;
import org.h2.expression.Parameter;
import org.h2.expression.Rownum;
import org.h2.expression.SequenceValue;
import org.h2.expression.Subquery;
import org.h2.expression.TableFunction;
import org.h2.expression.ValueExpression;
import org.h2.expression.Variable;
import org.h2.expression.Wildcard;
import org.h2.index.Index;
import org.h2.message.DbException;
import org.h2.schema.Schema;
import org.h2.schema.Sequence;
import org.h2.table.Column;
import org.h2.table.FunctionTable;
import org.h2.table.IndexColumn;
import org.h2.table.RangeTable;
import org.h2.table.Table;
import org.h2.table.TableFilter;
import org.h2.table.TableFilter.TableFilterVisitor;
import org.h2.table.TableView;
import org.h2.util.MathUtils;
import org.h2.util.New;
import org.h2.util.StatementBuilder;
import org.h2.util.StringUtils;
import org.h2.value.CompareMode;
import org.h2.value.DataType;
import org.h2.value.Value;
import org.h2.value.ValueBoolean;
import org.h2.value.ValueBytes;
import org.h2.value.ValueDate;
import org.h2.value.ValueDecimal;
import org.h2.value.ValueInt;
import org.h2.value.ValueLong;
import org.h2.value.ValueNull;
import org.h2.value.ValueString;
import org.h2.value.ValueTime;
import org.h2.value.ValueTimestamp;

public class Parser
{
  private static final int CHAR_END = 1;
  private static final int CHAR_VALUE = 2;
  private static final int CHAR_QUOTED = 3;
  private static final int CHAR_NAME = 4;
  private static final int CHAR_SPECIAL_1 = 5;
  private static final int CHAR_SPECIAL_2 = 6;
  private static final int CHAR_STRING = 7;
  private static final int CHAR_DOT = 8;
  private static final int CHAR_DOLLAR_QUOTED_STRING = 9;
  private static final int KEYWORD = 1;
  private static final int IDENTIFIER = 2;
  private static final int PARAMETER = 3;
  private static final int END = 4;
  private static final int VALUE = 5;
  private static final int EQUAL = 6;
  private static final int BIGGER_EQUAL = 7;
  private static final int BIGGER = 8;
  private static final int SMALLER = 9;
  private static final int SMALLER_EQUAL = 10;
  private static final int NOT_EQUAL = 11;
  private static final int AT = 12;
  private static final int MINUS = 13;
  private static final int PLUS = 14;
  private static final int STRING_CONCAT = 15;
  private static final int OPEN = 16;
  private static final int CLOSE = 17;
  private static final int NULL = 18;
  private static final int TRUE = 19;
  private static final int FALSE = 20;
  private static final int CURRENT_TIMESTAMP = 21;
  private static final int CURRENT_DATE = 22;
  private static final int CURRENT_TIME = 23;
  private static final int ROWNUM = 24;
  private static final int SPATIAL_INTERSECTS = 25;
  private final Database database;
  private final Session session;
  private final boolean identifiersToUpper;
  private int[] characterTypes;
  private int currentTokenType;
  private String currentToken;
  private boolean currentTokenQuoted;
  private Value currentValue;
  private String originalSQL;
  private String sqlCommand;
  private char[] sqlCommandChars;
  private int lastParseIndex;
  private int parseIndex;
  private CreateView createView;
  private Prepared currentPrepared;
  private Select currentSelect;
  private ArrayList<Parameter> parameters;
  private String schemaName;
  private ArrayList<String> expectedList;
  private boolean rightsChecked;
  private boolean recompileAlways;
  private ArrayList<Parameter> indexedParameterList;
  
  public Parser(Session session)
  {
    this.database = session.getDatabase();
    this.identifiersToUpper = this.database.getSettings().databaseToUpper;
    this.session = session;
  }
  
  public Prepared prepare(String sql)
  {
    Prepared p = parse(sql);
    p.prepare();
    if (this.currentTokenType != 4) {
      throw getSyntaxError();
    }
    return p;
  }
  
  public Command prepareCommand(String sql)
  {
    try
    {
      Prepared p = parse(sql);
      boolean hasMore = isToken(";");
      if ((!hasMore) && (this.currentTokenType != 4)) {
        throw getSyntaxError();
      }
      p.prepare();
      Command c = new CommandContainer(this, sql, p);
      CommandList list;
      if (hasMore)
      {
        String remaining = this.originalSQL.substring(this.parseIndex);
        if (remaining.trim().length() != 0) {
          list = new CommandList(this, sql, c, remaining);
        }
      }
      return list;
    }
    catch (DbException e)
    {
      throw e.addSQL(this.originalSQL);
    }
  }
  
  Prepared parse(String sql)
  {
    try
    {
      p = parse(sql, false);
    }
    catch (DbException e)
    {
      Prepared p;
      if (e.getErrorCode() == 42000) {
        p = parse(sql, true);
      } else {
        throw e.addSQL(sql);
      }
    }
    Prepared p;
    p.setPrepareAlways(this.recompileAlways);
    p.setParameterList(this.parameters);
    return p;
  }
  
  private Prepared parse(String sql, boolean withExpectedList)
  {
    initialize(sql);
    if (withExpectedList) {
      this.expectedList = New.arrayList();
    } else {
      this.expectedList = null;
    }
    this.parameters = New.arrayList();
    this.currentSelect = null;
    this.currentPrepared = null;
    this.createView = null;
    this.recompileAlways = false;
    this.indexedParameterList = null;
    read();
    return parsePrepared();
  }
  
  private Prepared parsePrepared()
  {
    int start = this.lastParseIndex;
    Prepared c = null;
    String token = this.currentToken;
    if (token.length() == 0)
    {
      c = new NoOperation(this.session);
    }
    else
    {
      char first = token.charAt(0);
      switch (first)
      {
      case '?': 
        readTerm();
        
        ((Parameter)this.parameters.get(0)).setValue(ValueNull.INSTANCE);
        read("=");
        read("CALL");
        c = parseCall();
        break;
      case '(': 
        c = parseSelect();
        break;
      case 'A': 
      case 'a': 
        if (readIf("ALTER")) {
          c = parseAlter();
        } else if (readIf("ANALYZE")) {
          c = parseAnalyze();
        }
        break;
      case 'B': 
      case 'b': 
        if (readIf("BACKUP")) {
          c = parseBackup();
        } else if (readIf("BEGIN")) {
          c = parseBegin();
        }
        break;
      case 'C': 
      case 'c': 
        if (readIf("COMMIT")) {
          c = parseCommit();
        } else if (readIf("CREATE")) {
          c = parseCreate();
        } else if (readIf("CALL")) {
          c = parseCall();
        } else if (readIf("CHECKPOINT")) {
          c = parseCheckpoint();
        } else if (readIf("COMMENT")) {
          c = parseComment();
        }
        break;
      case 'D': 
      case 'd': 
        if (readIf("DELETE")) {
          c = parseDelete();
        } else if (readIf("DROP")) {
          c = parseDrop();
        } else if (readIf("DECLARE")) {
          c = parseCreate();
        } else if (readIf("DEALLOCATE")) {
          c = parseDeallocate();
        }
        break;
      case 'E': 
      case 'e': 
        if (readIf("EXPLAIN")) {
          c = parseExplain();
        } else if (readIf("EXECUTE")) {
          c = parseExecute();
        }
        break;
      case 'F': 
      case 'f': 
        if (isToken("FROM")) {
          c = parseSelect();
        }
        break;
      case 'G': 
      case 'g': 
        if (readIf("GRANT")) {
          c = parseGrantRevoke(49);
        }
        break;
      case 'H': 
      case 'h': 
        if (readIf("HELP")) {
          c = parseHelp();
        }
        break;
      case 'I': 
      case 'i': 
        if (readIf("INSERT")) {
          c = parseInsert();
        }
        break;
      case 'M': 
      case 'm': 
        if (readIf("MERGE")) {
          c = parseMerge();
        }
        break;
      case 'P': 
      case 'p': 
        if (readIf("PREPARE")) {
          c = parsePrepare();
        }
        break;
      case 'R': 
      case 'r': 
        if (readIf("ROLLBACK")) {
          c = parseRollback();
        } else if (readIf("REVOKE")) {
          c = parseGrantRevoke(50);
        } else if (readIf("RUNSCRIPT")) {
          c = parseRunScript();
        } else if (readIf("RELEASE")) {
          c = parseReleaseSavepoint();
        } else if (readIf("REPLACE")) {
          c = parseReplace();
        }
        break;
      case 'S': 
      case 's': 
        if (isToken("SELECT")) {
          c = parseSelect();
        } else if (readIf("SET")) {
          c = parseSet();
        } else if (readIf("SAVEPOINT")) {
          c = parseSavepoint();
        } else if (readIf("SCRIPT")) {
          c = parseScript();
        } else if (readIf("SHUTDOWN")) {
          c = parseShutdown();
        } else if (readIf("SHOW")) {
          c = parseShow();
        }
        break;
      case 'T': 
      case 't': 
        if (readIf("TRUNCATE")) {
          c = parseTruncate();
        }
        break;
      case 'U': 
      case 'u': 
        if (readIf("UPDATE")) {
          c = parseUpdate();
        } else if (readIf("USE")) {
          c = parseUse();
        }
        break;
      case 'V': 
      case 'v': 
        if (readIf("VALUES")) {
          c = parseValues();
        }
        break;
      case 'W': 
      case 'w': 
        if (readIf("WITH")) {
          c = parseWith();
        }
        break;
      case ';': 
        c = new NoOperation(this.session);
        break;
      case ')': 
      case '*': 
      case '+': 
      case ',': 
      case '-': 
      case '.': 
      case '/': 
      case '0': 
      case '1': 
      case '2': 
      case '3': 
      case '4': 
      case '5': 
      case '6': 
      case '7': 
      case '8': 
      case '9': 
      case ':': 
      case '<': 
      case '=': 
      case '>': 
      case '@': 
      case 'J': 
      case 'K': 
      case 'L': 
      case 'N': 
      case 'O': 
      case 'Q': 
      case 'X': 
      case 'Y': 
      case 'Z': 
      case '[': 
      case '\\': 
      case ']': 
      case '^': 
      case '_': 
      case '`': 
      case 'j': 
      case 'k': 
      case 'l': 
      case 'n': 
      case 'o': 
      case 'q': 
      default: 
        throw getSyntaxError();
      }
      if (this.indexedParameterList != null)
      {
        int i = 0;int size = this.indexedParameterList.size();
        for (; i < size; i++) {
          if (this.indexedParameterList.get(i) == null) {
            this.indexedParameterList.set(i, new Parameter(i));
          }
        }
        this.parameters = this.indexedParameterList;
      }
      if (readIf("{"))
      {
        do
        {
          int index = (int)readLong() - 1;
          if ((index < 0) || (index >= this.parameters.size())) {
            throw getSyntaxError();
          }
          Parameter p = (Parameter)this.parameters.get(index);
          if (p == null) {
            throw getSyntaxError();
          }
          read(":");
          Expression expr = readExpression();
          expr = expr.optimize(this.session);
          p.setValue(expr.getValue(this.session));
        } while (readIf(","));
        read("}");
        for (Parameter p : this.parameters) {
          p.checkSet();
        }
        this.parameters.clear();
      }
    }
    if (c == null) {
      throw getSyntaxError();
    }
    setSQL(c, null, start);
    return c;
  }
  
  private DbException getSyntaxError()
  {
    if ((this.expectedList == null) || (this.expectedList.size() == 0)) {
      return DbException.getSyntaxError(this.sqlCommand, this.parseIndex);
    }
    StatementBuilder buff = new StatementBuilder();
    for (String e : this.expectedList)
    {
      buff.appendExceptFirst(", ");
      buff.append(e);
    }
    return DbException.getSyntaxError(this.sqlCommand, this.parseIndex, buff.toString());
  }
  
  private Prepared parseBackup()
  {
    BackupCommand command = new BackupCommand(this.session);
    read("TO");
    command.setFileName(readExpression());
    return command;
  }
  
  private Prepared parseAnalyze()
  {
    Analyze command = new Analyze(this.session);
    if (readIf("SAMPLE_SIZE")) {
      command.setTop(readPositiveInt());
    }
    return command;
  }
  
  private TransactionCommand parseBegin()
  {
    if (!readIf("WORK")) {
      readIf("TRANSACTION");
    }
    TransactionCommand command = new TransactionCommand(this.session, 83);
    return command;
  }
  
  private TransactionCommand parseCommit()
  {
    if (readIf("TRANSACTION"))
    {
      TransactionCommand command = new TransactionCommand(this.session, 78);
      
      command.setTransactionName(readUniqueIdentifier());
      return command;
    }
    TransactionCommand command = new TransactionCommand(this.session, 71);
    
    readIf("WORK");
    return command;
  }
  
  private TransactionCommand parseShutdown()
  {
    int type = 80;
    if (readIf("IMMEDIATELY")) {
      type = 81;
    } else if (readIf("COMPACT")) {
      type = 82;
    } else if (readIf("DEFRAG")) {
      type = 84;
    } else {
      readIf("SCRIPT");
    }
    return new TransactionCommand(this.session, type);
  }
  
  private TransactionCommand parseRollback()
  {
    if (readIf("TRANSACTION"))
    {
      TransactionCommand command = new TransactionCommand(this.session, 79);
      
      command.setTransactionName(readUniqueIdentifier());
      return command;
    }
    TransactionCommand command;
    if (readIf("TO"))
    {
      read("SAVEPOINT");
      TransactionCommand command = new TransactionCommand(this.session, 75);
      
      command.setSavepointName(readUniqueIdentifier());
    }
    else
    {
      readIf("WORK");
      command = new TransactionCommand(this.session, 72);
    }
    return command;
  }
  
  private Prepared parsePrepare()
  {
    if (readIf("COMMIT"))
    {
      TransactionCommand command = new TransactionCommand(this.session, 77);
      
      command.setTransactionName(readUniqueIdentifier());
      return command;
    }
    String procedureName = readAliasIdentifier();
    if (readIf("("))
    {
      ArrayList<Column> list = New.arrayList();
      for (int i = 0;; i++)
      {
        Column column = parseColumnForTable("C" + i, true);
        list.add(column);
        if (readIf(")")) {
          break;
        }
        read(",");
      }
    }
    read("AS");
    Prepared prep = parsePrepared();
    PrepareProcedure command = new PrepareProcedure(this.session);
    command.setProcedureName(procedureName);
    command.setPrepared(prep);
    return command;
  }
  
  private TransactionCommand parseSavepoint()
  {
    TransactionCommand command = new TransactionCommand(this.session, 74);
    
    command.setSavepointName(readUniqueIdentifier());
    return command;
  }
  
  private Prepared parseReleaseSavepoint()
  {
    Prepared command = new NoOperation(this.session);
    readIf("SAVEPOINT");
    readUniqueIdentifier();
    return command;
  }
  
  private Schema getSchema(String schemaName)
  {
    if (schemaName == null) {
      return null;
    }
    Schema schema = this.database.findSchema(schemaName);
    if (schema == null) {
      if (equalsToken("SESSION", schemaName)) {
        schema = this.database.getSchema(this.session.getCurrentSchemaName());
      } else if ((!this.database.getMode().sysDummy1) || (!"SYSIBM".equals(schemaName))) {
        throw DbException.get(90079, schemaName);
      }
    }
    return schema;
  }
  
  private Schema getSchema()
  {
    return getSchema(this.schemaName);
  }
  
  private Column readTableColumn(TableFilter filter)
  {
    String tableAlias = null;
    String columnName = readColumnIdentifier();
    if (readIf("."))
    {
      tableAlias = columnName;
      columnName = readColumnIdentifier();
      if (readIf("."))
      {
        String schema = tableAlias;
        tableAlias = columnName;
        columnName = readColumnIdentifier();
        if (readIf("."))
        {
          String catalogName = schema;
          schema = tableAlias;
          tableAlias = columnName;
          columnName = readColumnIdentifier();
          if (!equalsToken(catalogName, this.database.getShortName())) {
            throw DbException.get(90013, catalogName);
          }
        }
        if (!equalsToken(schema, filter.getTable().getSchema().getName())) {
          throw DbException.get(90079, schema);
        }
      }
      if (!equalsToken(tableAlias, filter.getTableAlias())) {
        throw DbException.get(42102, tableAlias);
      }
    }
    if ((this.database.getSettings().rowId) && 
      ("_ROWID_".equals(columnName))) {
      return filter.getRowIdColumn();
    }
    return filter.getTable().getColumn(columnName);
  }
  
  private Update parseUpdate()
  {
    Update command = new Update(this.session);
    this.currentPrepared = command;
    int start = this.lastParseIndex;
    TableFilter filter = readSimpleTableFilter();
    command.setTableFilter(filter);
    read("SET");
    if (readIf("("))
    {
      ArrayList<Column> columns = New.arrayList();
      do
      {
        Column column = readTableColumn(filter);
        columns.add(column);
      } while (readIf(","));
      read(")");
      read("=");
      Expression expression = readExpression();
      if (columns.size() == 1)
      {
        command.setAssignment((Column)columns.get(0), expression);
      }
      else
      {
        int i = 0;
        for (int size = columns.size(); i < size; i++)
        {
          Column column = (Column)columns.get(i);
          Function f = Function.getFunction(this.database, "ARRAY_GET");
          f.setParameter(0, expression);
          f.setParameter(1, ValueExpression.get(ValueInt.get(i + 1)));
          f.doneWithParameters();
          command.setAssignment(column, f);
        }
      }
    }
    else
    {
      do
      {
        Column column = readTableColumn(filter);
        read("=");
        Expression expression;
        Expression expression;
        if (readIf("DEFAULT")) {
          expression = ValueExpression.getDefault();
        } else {
          expression = readExpression();
        }
        command.setAssignment(column, expression);
      } while (readIf(","));
    }
    if (readIf("WHERE"))
    {
      Expression condition = readExpression();
      command.setCondition(condition);
    }
    if (readIf("ORDER"))
    {
      read("BY");
      parseSimpleOrderList();
    }
    if (readIf("LIMIT"))
    {
      Expression limit = readTerm().optimize(this.session);
      command.setLimit(limit);
    }
    setSQL(command, "UPDATE", start);
    return command;
  }
  
  private TableFilter readSimpleTableFilter()
  {
    Table table = readTableOrView();
    String alias = null;
    if (readIf("AS")) {
      alias = readAliasIdentifier();
    } else if ((this.currentTokenType == 2) && 
      (!equalsToken("SET", this.currentToken))) {
      alias = readAliasIdentifier();
    }
    return new TableFilter(this.session, table, alias, this.rightsChecked, this.currentSelect);
  }
  
  private Delete parseDelete()
  {
    Delete command = new Delete(this.session);
    Expression limit = null;
    if (readIf("TOP")) {
      limit = readTerm().optimize(this.session);
    }
    this.currentPrepared = command;
    int start = this.lastParseIndex;
    readIf("FROM");
    TableFilter filter = readSimpleTableFilter();
    command.setTableFilter(filter);
    if (readIf("WHERE"))
    {
      Expression condition = readExpression();
      command.setCondition(condition);
    }
    if ((readIf("LIMIT")) && (limit == null)) {
      limit = readTerm().optimize(this.session);
    }
    command.setLimit(limit);
    setSQL(command, "DELETE", start);
    return command;
  }
  
  private IndexColumn[] parseIndexColumnList()
  {
    ArrayList<IndexColumn> columns = New.arrayList();
    do
    {
      IndexColumn column = new IndexColumn();
      column.columnName = readColumnIdentifier();
      columns.add(column);
      if (!readIf("ASC")) {
        if (readIf("DESC")) {
          column.sortType = 1;
        }
      }
      if (readIf("NULLS")) {
        if (readIf("FIRST"))
        {
          column.sortType |= 0x2;
        }
        else
        {
          read("LAST");
          column.sortType |= 0x4;
        }
      }
    } while (readIf(","));
    read(")");
    return (IndexColumn[])columns.toArray(new IndexColumn[columns.size()]);
  }
  
  private String[] parseColumnList()
  {
    ArrayList<String> columns = New.arrayList();
    do
    {
      String columnName = readColumnIdentifier();
      columns.add(columnName);
    } while (readIfMore());
    return (String[])columns.toArray(new String[columns.size()]);
  }
  
  private Column[] parseColumnList(Table table)
  {
    ArrayList<Column> columns = New.arrayList();
    HashSet<Column> set = New.hashSet();
    if (!readIf(")")) {
      do
      {
        Column column = parseColumn(table);
        if (!set.add(column)) {
          throw DbException.get(42121, column.getSQL());
        }
        columns.add(column);
      } while (readIfMore());
    }
    return (Column[])columns.toArray(new Column[columns.size()]);
  }
  
  private Column parseColumn(Table table)
  {
    String id = readColumnIdentifier();
    if ((this.database.getSettings().rowId) && ("_ROWID_".equals(id))) {
      return table.getRowIdColumn();
    }
    return table.getColumn(id);
  }
  
  private boolean readIfMore()
  {
    if (readIf(",")) {
      return !readIf(")");
    }
    read(")");
    return false;
  }
  
  private Prepared parseHelp()
  {
    StringBuilder buff = new StringBuilder("SELECT * FROM INFORMATION_SCHEMA.HELP");
    
    int i = 0;
    ArrayList<Value> paramValues = New.arrayList();
    while (this.currentTokenType != 4)
    {
      String s = this.currentToken;
      read();
      if (i == 0) {
        buff.append(" WHERE ");
      } else {
        buff.append(" AND ");
      }
      i++;
      buff.append("UPPER(TOPIC) LIKE ?");
      paramValues.add(ValueString.get("%" + s + "%"));
    }
    return prepare(this.session, buff.toString(), paramValues);
  }
  
  private Prepared parseShow()
  {
    ArrayList<Value> paramValues = New.arrayList();
    StringBuilder buff = new StringBuilder("SELECT ");
    String schemaName;
    if (readIf("CLIENT_ENCODING"))
    {
      buff.append("'UNICODE' AS CLIENT_ENCODING FROM DUAL");
    }
    else if (readIf("DEFAULT_TRANSACTION_ISOLATION"))
    {
      buff.append("'read committed' AS DEFAULT_TRANSACTION_ISOLATION FROM DUAL");
    }
    else if (readIf("TRANSACTION"))
    {
      read("ISOLATION");
      read("LEVEL");
      buff.append("'read committed' AS TRANSACTION_ISOLATION FROM DUAL");
    }
    else if (readIf("DATESTYLE"))
    {
      buff.append("'ISO' AS DATESTYLE FROM DUAL");
    }
    else if (readIf("SERVER_VERSION"))
    {
      buff.append("'8.1.4' AS SERVER_VERSION FROM DUAL");
    }
    else if (readIf("SERVER_ENCODING"))
    {
      buff.append("'UTF8' AS SERVER_ENCODING FROM DUAL");
    }
    else if (readIf("TABLES"))
    {
      String schema = "PUBLIC";
      if (readIf("FROM")) {
        schema = readUniqueIdentifier();
      }
      buff.append("TABLE_NAME, TABLE_SCHEMA FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA=? ORDER BY TABLE_NAME");
      
      paramValues.add(ValueString.get(schema));
    }
    else if (readIf("COLUMNS"))
    {
      read("FROM");
      String tableName = readIdentifierWithSchema();
      schemaName = getSchema().getName();
      paramValues.add(ValueString.get(tableName));
      if (readIf("FROM")) {
        schemaName = readUniqueIdentifier();
      }
      buff.append("C.COLUMN_NAME FIELD, C.TYPE_NAME || '(' || C.NUMERIC_PRECISION || ')' TYPE, C.IS_NULLABLE \"NULL\", CASE (SELECT MAX(I.INDEX_TYPE_NAME) FROM INFORMATION_SCHEMA.INDEXES I WHERE I.TABLE_SCHEMA=C.TABLE_SCHEMA AND I.TABLE_NAME=C.TABLE_NAME AND I.COLUMN_NAME=C.COLUMN_NAME)WHEN 'PRIMARY KEY' THEN 'PRI' WHEN 'UNIQUE INDEX' THEN 'UNI' ELSE '' END KEY, IFNULL(COLUMN_DEFAULT, 'NULL') DEFAULT FROM INFORMATION_SCHEMA.COLUMNS C WHERE C.TABLE_NAME=? AND C.TABLE_SCHEMA=? ORDER BY C.ORDINAL_POSITION");
      
      paramValues.add(ValueString.get(schemaName));
    }
    else if ((readIf("DATABASES")) || (readIf("SCHEMAS")))
    {
      buff.append("SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA");
    }
    boolean b = this.session.getAllowLiterals();
    try
    {
      this.session.setAllowLiterals(true);
      return prepare(this.session, buff.toString(), paramValues);
    }
    finally
    {
      this.session.setAllowLiterals(b);
    }
  }
  
  private static Prepared prepare(Session s, String sql, ArrayList<Value> paramValues)
  {
    Prepared prep = s.prepare(sql);
    ArrayList<Parameter> params = prep.getParameters();
    if (params != null)
    {
      int i = 0;
      for (int size = params.size(); i < size; i++)
      {
        Parameter p = (Parameter)params.get(i);
        p.setValue((Value)paramValues.get(i));
      }
    }
    return prep;
  }
  
  private boolean isSelect()
  {
    int start = this.lastParseIndex;
    while (readIf("(")) {}
    boolean select = (isToken("SELECT")) || (isToken("FROM"));
    this.parseIndex = start;
    read();
    return select;
  }
  
  private Merge parseMerge()
  {
    Merge command = new Merge(this.session);
    this.currentPrepared = command;
    read("INTO");
    Table table = readTableOrView();
    command.setTable(table);
    if (readIf("("))
    {
      if (isSelect())
      {
        command.setQuery(parseSelect());
        read(")");
        return command;
      }
      Column[] columns = parseColumnList(table);
      command.setColumns(columns);
    }
    if (readIf("KEY"))
    {
      read("(");
      Column[] keys = parseColumnList(table);
      command.setKeys(keys);
    }
    if (readIf("VALUES")) {
      do
      {
        ArrayList<Expression> values = New.arrayList();
        read("(");
        if (!readIf(")")) {
          do
          {
            if (readIf("DEFAULT")) {
              values.add(null);
            } else {
              values.add(readExpression());
            }
          } while (readIfMore());
        }
        command.addRow((Expression[])values.toArray(new Expression[values.size()]));
      } while (readIf(","));
    } else {
      command.setQuery(parseSelect());
    }
    return command;
  }
  
  private Insert parseInsert()
  {
    Insert command = new Insert(this.session);
    this.currentPrepared = command;
    read("INTO");
    Table table = readTableOrView();
    command.setTable(table);
    Column[] columns = null;
    if (readIf("("))
    {
      if (isSelect())
      {
        command.setQuery(parseSelect());
        read(")");
        return command;
      }
      columns = parseColumnList(table);
      command.setColumns(columns);
    }
    if (readIf("DIRECT")) {
      command.setInsertFromSelect(true);
    }
    if (readIf("SORTED")) {
      command.setSortedInsertMode(true);
    }
    if (readIf("DEFAULT"))
    {
      read("VALUES");
      Expression[] expr = new Expression[0];
      command.addRow(expr);
    }
    else if (readIf("VALUES"))
    {
      read("(");
      do
      {
        ArrayList<Expression> values = New.arrayList();
        if (!readIf(")")) {
          do
          {
            if (readIf("DEFAULT")) {
              values.add(null);
            } else {
              values.add(readExpression());
            }
          } while (readIfMore());
        }
        command.addRow((Expression[])values.toArray(new Expression[values.size()]));
        if (!readIf(",")) {
          break;
        }
      } while (readIf("("));
    }
    else if (readIf("SET"))
    {
      if (columns != null) {
        throw getSyntaxError();
      }
      ArrayList<Column> columnList = New.arrayList();
      ArrayList<Expression> values = New.arrayList();
      do
      {
        columnList.add(parseColumn(table));
        read("=");
        Expression expression;
        Expression expression;
        if (readIf("DEFAULT")) {
          expression = ValueExpression.getDefault();
        } else {
          expression = readExpression();
        }
        values.add(expression);
      } while (readIf(","));
      command.setColumns((Column[])columnList.toArray(new Column[columnList.size()]));
      command.addRow((Expression[])values.toArray(new Expression[values.size()]));
    }
    else
    {
      command.setQuery(parseSelect());
    }
    if ((this.database.getMode().onDuplicateKeyUpdate) && 
      (readIf("ON")))
    {
      read("DUPLICATE");
      read("KEY");
      read("UPDATE");
      do
      {
        Column column = parseColumn(table);
        read("=");
        Expression expression;
        Expression expression;
        if (readIf("DEFAULT")) {
          expression = ValueExpression.getDefault();
        } else {
          expression = readExpression();
        }
        command.addAssignmentForDuplicate(column, expression);
      } while (readIf(","));
    }
    if (this.database.getMode().isolationLevelInSelectOrInsertStatement) {
      parseIsolationClause();
    }
    return command;
  }
  
  private Replace parseReplace()
  {
    Replace command = new Replace(this.session);
    this.currentPrepared = command;
    read("INTO");
    Table table = readTableOrView();
    command.setTable(table);
    if (readIf("("))
    {
      if (isSelect())
      {
        command.setQuery(parseSelect());
        read(")");
        return command;
      }
      Column[] columns = parseColumnList(table);
      command.setColumns(columns);
    }
    if (readIf("VALUES")) {
      do
      {
        ArrayList<Expression> values = New.arrayList();
        read("(");
        if (!readIf(")")) {
          do
          {
            if (readIf("DEFAULT")) {
              values.add(null);
            } else {
              values.add(readExpression());
            }
          } while (readIfMore());
        }
        command.addRow((Expression[])values.toArray(new Expression[values.size()]));
      } while (readIf(","));
    } else {
      command.setQuery(parseSelect());
    }
    return command;
  }
  
  private TableFilter readTableFilter(boolean fromOuter)
  {
    String alias = null;
    Table table;
    if (readIf("("))
    {
      Table table;
      if (isSelect())
      {
        Query query = parseSelectUnion();
        read(")");
        query.setParameterList(New.arrayList(this.parameters));
        query.init();
        Session s;
        Session s;
        if (this.createView != null) {
          s = this.database.getSystemSession();
        } else {
          s = this.session;
        }
        alias = this.session.getNextSystemIdentifier(this.sqlCommand);
        table = TableView.createTempView(s, this.session.getUser(), alias, query, this.currentSelect);
      }
      else
      {
        TableFilter top;
        if (this.database.getSettings().nestedJoins)
        {
          TableFilter top = readTableFilter(false);
          top = readJoin(top, this.currentSelect, false, false);
          top = getNested(top);
        }
        else
        {
          top = readTableFilter(fromOuter);
          top = readJoin(top, this.currentSelect, false, fromOuter);
        }
        read(")");
        alias = readFromAlias(null);
        if (alias != null) {
          top.setAlias(alias);
        }
        return top;
      }
    }
    else
    {
      Table table;
      if (readIf("VALUES"))
      {
        table = parseValuesTable().getTable();
      }
      else
      {
        String tableName = readIdentifierWithSchema(null);
        Schema schema = getSchema();
        boolean foundLeftBracket = readIf("(");
        if ((foundLeftBracket) && (readIf("INDEX")))
        {
          readIdentifierWithSchema(null);
          read(")");
          foundLeftBracket = false;
        }
        Table table;
        if (foundLeftBracket)
        {
          Schema mainSchema = this.database.getSchema("PUBLIC");
          Table table;
          if ((equalsToken(tableName, "SYSTEM_RANGE")) || (equalsToken(tableName, "GENERATE_SERIES")))
          {
            Expression min = readExpression();
            read(",");
            Expression max = readExpression();
            Table table;
            if (readIf(","))
            {
              Expression step = readExpression();
              read(")");
              table = new RangeTable(mainSchema, min, max, step, false);
            }
            else
            {
              read(")");
              table = new RangeTable(mainSchema, min, max, false);
            }
          }
          else
          {
            Expression expr = readFunction(schema, tableName);
            if (!(expr instanceof FunctionCall)) {
              throw getSyntaxError();
            }
            FunctionCall call = (FunctionCall)expr;
            if (!call.isDeterministic()) {
              this.recompileAlways = true;
            }
            table = new FunctionTable(mainSchema, this.session, expr, call);
          }
        }
        else
        {
          Table table;
          if (equalsToken("DUAL", tableName))
          {
            table = getDualTable(false);
          }
          else
          {
            Table table;
            if ((this.database.getMode().sysDummy1) && (equalsToken("SYSDUMMY1", tableName))) {
              table = getDualTable(false);
            } else {
              table = readTableOrView(tableName);
            }
          }
        }
      }
    }
    alias = readFromAlias(alias);
    return new TableFilter(this.session, table, alias, this.rightsChecked, this.currentSelect);
  }
  
  private String readFromAlias(String alias)
  {
    if (readIf("AS")) {
      alias = readAliasIdentifier();
    } else if (this.currentTokenType == 2) {
      if ((!isToken("LEFT")) && (!isToken("RIGHT")) && (!isToken("FULL"))) {
        alias = readAliasIdentifier();
      }
    }
    return alias;
  }
  
  private Prepared parseTruncate()
  {
    read("TABLE");
    Table table = readTableOrView();
    TruncateTable command = new TruncateTable(this.session);
    command.setTable(table);
    return command;
  }
  
  private boolean readIfExists(boolean ifExists)
  {
    if (readIf("IF"))
    {
      read("EXISTS");
      ifExists = true;
    }
    return ifExists;
  }
  
  private Prepared parseComment()
  {
    int type = 0;
    read("ON");
    boolean column = false;
    if ((readIf("TABLE")) || (readIf("VIEW")))
    {
      type = 0;
    }
    else if (readIf("COLUMN"))
    {
      column = true;
      type = 0;
    }
    else if (readIf("CONSTANT"))
    {
      type = 11;
    }
    else if (readIf("CONSTRAINT"))
    {
      type = 5;
    }
    else if (readIf("ALIAS"))
    {
      type = 9;
    }
    else if (readIf("INDEX"))
    {
      type = 1;
    }
    else if (readIf("ROLE"))
    {
      type = 7;
    }
    else if (readIf("SCHEMA"))
    {
      type = 10;
    }
    else if (readIf("SEQUENCE"))
    {
      type = 3;
    }
    else if (readIf("TRIGGER"))
    {
      type = 4;
    }
    else if (readIf("USER"))
    {
      type = 2;
    }
    else if (readIf("DOMAIN"))
    {
      type = 12;
    }
    else
    {
      throw getSyntaxError();
    }
    SetComment command = new SetComment(this.session);
    String objectName;
    if (column)
    {
      ArrayList<String> list = New.arrayList();
      do
      {
        list.add(readUniqueIdentifier());
      } while (readIf("."));
      this.schemaName = this.session.getCurrentSchemaName();
      if (list.size() == 4)
      {
        if (!equalsToken(this.database.getShortName(), (String)list.get(0))) {
          throw DbException.getSyntaxError(this.sqlCommand, this.parseIndex, "database name");
        }
        list.remove(0);
      }
      if (list.size() == 3)
      {
        this.schemaName = ((String)list.get(0));
        list.remove(0);
      }
      if (list.size() != 2) {
        throw DbException.getSyntaxError(this.sqlCommand, this.parseIndex, "table.column");
      }
      String objectName = (String)list.get(0);
      command.setColumn(true);
      command.setColumnName((String)list.get(1));
    }
    else
    {
      objectName = readIdentifierWithSchema();
    }
    command.setSchemaName(this.schemaName);
    command.setObjectName(objectName);
    command.setObjectType(type);
    read("IS");
    command.setCommentExpression(readExpression());
    return command;
  }
  
  private Prepared parseDrop()
  {
    if (readIf("TABLE"))
    {
      boolean ifExists = readIfExists(false);
      String tableName = readIdentifierWithSchema();
      DropTable command = new DropTable(this.session, getSchema());
      command.setTableName(tableName);
      while (readIf(","))
      {
        tableName = readIdentifierWithSchema();
        DropTable next = new DropTable(this.session, getSchema());
        next.setTableName(tableName);
        command.addNextDropTable(next);
      }
      ifExists = readIfExists(ifExists);
      command.setIfExists(ifExists);
      if (readIf("CASCADE"))
      {
        command.setDropAction(1);
        readIf("CONSTRAINTS");
      }
      else if (readIf("RESTRICT"))
      {
        command.setDropAction(0);
      }
      else if (readIf("IGNORE"))
      {
        command.setDropAction(2);
      }
      return command;
    }
    if (readIf("INDEX"))
    {
      boolean ifExists = readIfExists(false);
      String indexName = readIdentifierWithSchema();
      DropIndex command = new DropIndex(this.session, getSchema());
      command.setIndexName(indexName);
      ifExists = readIfExists(ifExists);
      command.setIfExists(ifExists);
      return command;
    }
    if (readIf("USER"))
    {
      boolean ifExists = readIfExists(false);
      DropUser command = new DropUser(this.session);
      command.setUserName(readUniqueIdentifier());
      ifExists = readIfExists(ifExists);
      readIf("CASCADE");
      command.setIfExists(ifExists);
      return command;
    }
    if (readIf("SEQUENCE"))
    {
      boolean ifExists = readIfExists(false);
      String sequenceName = readIdentifierWithSchema();
      DropSequence command = new DropSequence(this.session, getSchema());
      command.setSequenceName(sequenceName);
      ifExists = readIfExists(ifExists);
      command.setIfExists(ifExists);
      return command;
    }
    if (readIf("CONSTANT"))
    {
      boolean ifExists = readIfExists(false);
      String constantName = readIdentifierWithSchema();
      DropConstant command = new DropConstant(this.session, getSchema());
      command.setConstantName(constantName);
      ifExists = readIfExists(ifExists);
      command.setIfExists(ifExists);
      return command;
    }
    if (readIf("TRIGGER"))
    {
      boolean ifExists = readIfExists(false);
      String triggerName = readIdentifierWithSchema();
      DropTrigger command = new DropTrigger(this.session, getSchema());
      command.setTriggerName(triggerName);
      ifExists = readIfExists(ifExists);
      command.setIfExists(ifExists);
      return command;
    }
    if (readIf("VIEW"))
    {
      boolean ifExists = readIfExists(false);
      String viewName = readIdentifierWithSchema();
      DropView command = new DropView(this.session, getSchema());
      command.setViewName(viewName);
      ifExists = readIfExists(ifExists);
      command.setIfExists(ifExists);
      Integer dropAction = parseCascadeOrRestrict();
      if (dropAction != null) {
        command.setDropAction(dropAction.intValue());
      }
      return command;
    }
    if (readIf("ROLE"))
    {
      boolean ifExists = readIfExists(false);
      DropRole command = new DropRole(this.session);
      command.setRoleName(readUniqueIdentifier());
      ifExists = readIfExists(ifExists);
      command.setIfExists(ifExists);
      return command;
    }
    if (readIf("ALIAS"))
    {
      boolean ifExists = readIfExists(false);
      String aliasName = readIdentifierWithSchema();
      DropFunctionAlias command = new DropFunctionAlias(this.session, getSchema());
      
      command.setAliasName(aliasName);
      ifExists = readIfExists(ifExists);
      command.setIfExists(ifExists);
      return command;
    }
    if (readIf("SCHEMA"))
    {
      boolean ifExists = readIfExists(false);
      DropSchema command = new DropSchema(this.session);
      command.setSchemaName(readUniqueIdentifier());
      ifExists = readIfExists(ifExists);
      command.setIfExists(ifExists);
      return command;
    }
    if (readIf("ALL"))
    {
      read("OBJECTS");
      DropDatabase command = new DropDatabase(this.session);
      command.setDropAllObjects(true);
      if (readIf("DELETE"))
      {
        read("FILES");
        command.setDeleteFiles(true);
      }
      return command;
    }
    if (readIf("DOMAIN")) {
      return parseDropUserDataType();
    }
    if (readIf("TYPE")) {
      return parseDropUserDataType();
    }
    if (readIf("DATATYPE")) {
      return parseDropUserDataType();
    }
    if (readIf("AGGREGATE")) {
      return parseDropAggregate();
    }
    throw getSyntaxError();
  }
  
  private DropUserDataType parseDropUserDataType()
  {
    boolean ifExists = readIfExists(false);
    DropUserDataType command = new DropUserDataType(this.session);
    command.setTypeName(readUniqueIdentifier());
    ifExists = readIfExists(ifExists);
    command.setIfExists(ifExists);
    return command;
  }
  
  private DropAggregate parseDropAggregate()
  {
    boolean ifExists = readIfExists(false);
    DropAggregate command = new DropAggregate(this.session);
    command.setName(readUniqueIdentifier());
    ifExists = readIfExists(ifExists);
    command.setIfExists(ifExists);
    return command;
  }
  
  private TableFilter readJoin(TableFilter top, Select command, boolean nested, boolean fromOuter)
  {
    boolean joined = false;
    TableFilter last = top;
    boolean nestedJoins = this.database.getSettings().nestedJoins;
    for (;;)
    {
      if (readIf("RIGHT"))
      {
        readIf("OUTER");
        read("JOIN");
        joined = true;
        
        TableFilter newTop = readTableFilter(fromOuter);
        newTop = readJoin(newTop, command, nested, true);
        Expression on = null;
        if (readIf("ON")) {
          on = readExpression();
        }
        if (nestedJoins)
        {
          top = getNested(top);
          newTop.addJoin(top, true, false, on);
        }
        else
        {
          newTop.addJoin(top, true, false, on);
        }
        top = newTop;
        last = newTop;
      }
      else if (readIf("LEFT"))
      {
        readIf("OUTER");
        read("JOIN");
        joined = true;
        TableFilter join = readTableFilter(true);
        if (nestedJoins) {
          join = readJoin(join, command, true, true);
        } else {
          top = readJoin(top, command, false, true);
        }
        Expression on = null;
        if (readIf("ON")) {
          on = readExpression();
        }
        top.addJoin(join, true, false, on);
        last = join;
      }
      else
      {
        if (readIf("FULL")) {
          throw getSyntaxError();
        }
        if (readIf("INNER"))
        {
          read("JOIN");
          joined = true;
          TableFilter join = readTableFilter(fromOuter);
          top = readJoin(top, command, false, false);
          Expression on = null;
          if (readIf("ON")) {
            on = readExpression();
          }
          if (nestedJoins) {
            top.addJoin(join, false, false, on);
          } else {
            top.addJoin(join, fromOuter, false, on);
          }
          last = join;
        }
        else if (readIf("JOIN"))
        {
          joined = true;
          TableFilter join = readTableFilter(fromOuter);
          top = readJoin(top, command, false, false);
          Expression on = null;
          if (readIf("ON")) {
            on = readExpression();
          }
          if (nestedJoins) {
            top.addJoin(join, false, false, on);
          } else {
            top.addJoin(join, fromOuter, false, on);
          }
          last = join;
        }
        else if (readIf("CROSS"))
        {
          read("JOIN");
          joined = true;
          TableFilter join = readTableFilter(fromOuter);
          if (nestedJoins) {
            top.addJoin(join, false, false, null);
          } else {
            top.addJoin(join, fromOuter, false, null);
          }
          last = join;
        }
        else
        {
          if (!readIf("NATURAL")) {
            break;
          }
          read("JOIN");
          joined = true;
          TableFilter join = readTableFilter(fromOuter);
          Column[] tableCols = last.getTable().getColumns();
          Column[] joinCols = join.getTable().getColumns();
          String tableSchema = last.getTable().getSchema().getName();
          String joinSchema = join.getTable().getSchema().getName();
          Expression on = null;
          for (Column tc : tableCols)
          {
            String tableColumnName = tc.getName();
            for (Column c : joinCols)
            {
              String joinColumnName = c.getName();
              if (equalsToken(tableColumnName, joinColumnName))
              {
                join.addNaturalJoinColumn(c);
                Expression tableExpr = new ExpressionColumn(this.database, tableSchema, last.getTableAlias(), tableColumnName);
                
                Expression joinExpr = new ExpressionColumn(this.database, joinSchema, join.getTableAlias(), joinColumnName);
                
                Expression equal = new Comparison(this.session, 0, tableExpr, joinExpr);
                if (on == null) {
                  on = equal;
                } else {
                  on = new ConditionAndOr(0, on, equal);
                }
              }
            }
          }
          if (nestedJoins) {
            top.addJoin(join, false, nested, on);
          } else {
            top.addJoin(join, fromOuter, false, on);
          }
          last = join;
        }
      }
    }
    if ((nested) && (joined)) {
      top = getNested(top);
    }
    return top;
  }
  
  private TableFilter getNested(TableFilter n)
  {
    String joinTable = "SYSTEM_JOIN_" + this.parseIndex;
    TableFilter top = new TableFilter(this.session, getDualTable(true), joinTable, this.rightsChecked, this.currentSelect);
    
    top.addJoin(n, false, true, null);
    return top;
  }
  
  private Prepared parseExecute()
  {
    ExecuteProcedure command = new ExecuteProcedure(this.session);
    String procedureName = readAliasIdentifier();
    Procedure p = this.session.getProcedure(procedureName);
    if (p == null) {
      throw DbException.get(90077, procedureName);
    }
    command.setProcedure(p);
    if (readIf("(")) {
      for (int i = 0;; i++)
      {
        command.setExpression(i, readExpression());
        if (readIf(")")) {
          break;
        }
        read(",");
      }
    }
    return command;
  }
  
  private DeallocateProcedure parseDeallocate()
  {
    readIf("PLAN");
    String procedureName = readAliasIdentifier();
    DeallocateProcedure command = new DeallocateProcedure(this.session);
    command.setProcedureName(procedureName);
    return command;
  }
  
  private Explain parseExplain()
  {
    Explain command = new Explain(this.session);
    if (readIf("ANALYZE")) {
      command.setExecuteCommand(true);
    } else if (readIf("PLAN")) {
      readIf("FOR");
    }
    if ((isToken("SELECT")) || (isToken("FROM")) || (isToken("("))) {
      command.setCommand(parseSelect());
    } else if (readIf("DELETE")) {
      command.setCommand(parseDelete());
    } else if (readIf("UPDATE")) {
      command.setCommand(parseUpdate());
    } else if (readIf("INSERT")) {
      command.setCommand(parseInsert());
    } else if (readIf("MERGE")) {
      command.setCommand(parseMerge());
    } else if (readIf("WITH")) {
      command.setCommand(parseWith());
    } else {
      throw getSyntaxError();
    }
    return command;
  }
  
  private Query parseSelect()
  {
    int paramIndex = this.parameters.size();
    Query command = parseSelectUnion();
    ArrayList<Parameter> params = New.arrayList();
    int i = paramIndex;
    for (int size = this.parameters.size(); i < size; i++) {
      params.add(this.parameters.get(i));
    }
    command.setParameterList(params);
    command.init();
    return command;
  }
  
  private Query parseSelectUnion()
  {
    int start = this.lastParseIndex;
    Query command = parseSelectSub();
    return parseSelectUnionExtension(command, start, false);
  }
  
  private Query parseSelectUnionExtension(Query command, int start, boolean unionOnly)
  {
    for (;;)
    {
      if (readIf("UNION"))
      {
        SelectUnion union = new SelectUnion(this.session, command);
        if (readIf("ALL"))
        {
          union.setUnionType(1);
        }
        else
        {
          readIf("DISTINCT");
          union.setUnionType(0);
        }
        union.setRight(parseSelectSub());
        command = union;
      }
      else if ((readIf("MINUS")) || (readIf("EXCEPT")))
      {
        SelectUnion union = new SelectUnion(this.session, command);
        union.setUnionType(2);
        union.setRight(parseSelectSub());
        command = union;
      }
      else
      {
        if (!readIf("INTERSECT")) {
          break;
        }
        SelectUnion union = new SelectUnion(this.session, command);
        union.setUnionType(3);
        union.setRight(parseSelectSub());
        command = union;
      }
    }
    if (!unionOnly) {
      parseEndOfQuery(command);
    }
    setSQL(command, null, start);
    return command;
  }
  
  private void parseEndOfQuery(Query command)
  {
    if (readIf("ORDER"))
    {
      read("BY");
      Select oldSelect = this.currentSelect;
      if ((command instanceof Select)) {
        this.currentSelect = ((Select)command);
      }
      ArrayList<SelectOrderBy> orderList = New.arrayList();
      do
      {
        boolean canBeNumber = true;
        if (readIf("=")) {
          canBeNumber = false;
        }
        SelectOrderBy order = new SelectOrderBy();
        Expression expr = readExpression();
        if ((canBeNumber) && ((expr instanceof ValueExpression)) && (expr.getType() == 4))
        {
          order.columnIndexExpr = expr;
        }
        else if ((expr instanceof Parameter))
        {
          this.recompileAlways = true;
          order.columnIndexExpr = expr;
        }
        else
        {
          order.expression = expr;
        }
        if (readIf("DESC")) {
          order.descending = true;
        } else {
          readIf("ASC");
        }
        if (readIf("NULLS")) {
          if (readIf("FIRST"))
          {
            order.nullsFirst = true;
          }
          else
          {
            read("LAST");
            order.nullsLast = true;
          }
        }
        orderList.add(order);
      } while (readIf(","));
      command.setOrder(orderList);
      this.currentSelect = oldSelect;
    }
    if (this.database.getMode().supportOffsetFetch)
    {
      Select temp = this.currentSelect;
      this.currentSelect = null;
      if (readIf("OFFSET"))
      {
        command.setOffset(readExpression().optimize(this.session));
        if (!readIf("ROW")) {
          read("ROWS");
        }
      }
      if (readIf("FETCH"))
      {
        if (!readIf("FIRST")) {
          read("NEXT");
        }
        if (readIf("ROW"))
        {
          command.setLimit(ValueExpression.get(ValueInt.get(1)));
        }
        else
        {
          Expression limit = readExpression().optimize(this.session);
          command.setLimit(limit);
          if (!readIf("ROW")) {
            read("ROWS");
          }
        }
        read("ONLY");
      }
      this.currentSelect = temp;
    }
    if (readIf("LIMIT"))
    {
      Select temp = this.currentSelect;
      
      this.currentSelect = null;
      Expression limit = readExpression().optimize(this.session);
      command.setLimit(limit);
      if (readIf("OFFSET"))
      {
        Expression offset = readExpression().optimize(this.session);
        command.setOffset(offset);
      }
      else if (readIf(","))
      {
        Expression offset = limit;
        limit = readExpression().optimize(this.session);
        command.setOffset(offset);
        command.setLimit(limit);
      }
      if (readIf("SAMPLE_SIZE"))
      {
        Expression sampleSize = readExpression().optimize(this.session);
        command.setSampleSize(sampleSize);
      }
      this.currentSelect = temp;
    }
    if (readIf("FOR")) {
      if (readIf("UPDATE"))
      {
        if (readIf("OF")) {
          do
          {
            readIdentifierWithSchema();
          } while (readIf(","));
        } else if (!readIf("NOWAIT")) {}
        command.setForUpdate(true);
      }
      else if ((readIf("READ")) || (readIf("FETCH")))
      {
        read("ONLY");
      }
    }
    if (this.database.getMode().isolationLevelInSelectOrInsertStatement) {
      parseIsolationClause();
    }
  }
  
  private void parseIsolationClause()
  {
    if (readIf("WITH")) {
      if ((readIf("RR")) || (readIf("RS")))
      {
        if (readIf("USE"))
        {
          read("AND");
          read("KEEP");
          if ((!readIf("SHARE")) && (!readIf("UPDATE")) && (readIf("EXCLUSIVE"))) {}
          read("LOCKS");
        }
      }
      else if ((readIf("CS")) || (!readIf("UR"))) {}
    }
  }
  
  private Query parseSelectSub()
  {
    if (readIf("("))
    {
      Query command = parseSelectUnion();
      read(")");
      return command;
    }
    Select select = parseSelectSimple();
    return select;
  }
  
  private void parseSelectSimpleFromPart(Select command)
  {
    do
    {
      TableFilter filter = readTableFilter(false);
      parseJoinTableFilter(filter, command);
    } while (readIf(","));
  }
  
  private void parseJoinTableFilter(TableFilter top, final Select command)
  {
    top = readJoin(top, command, false, top.isJoinOuter());
    command.addTableFilter(top, true);
    boolean isOuter = false;
    for (;;)
    {
      TableFilter n = top.getNestedJoin();
      if (n != null) {
        n.visit(new TableFilter.TableFilterVisitor()
        {
          public void accept(TableFilter f)
          {
            command.addTableFilter(f, false);
          }
        });
      }
      TableFilter join = top.getJoin();
      if (join == null) {
        break;
      }
      isOuter |= join.isJoinOuter();
      if (isOuter)
      {
        command.addTableFilter(join, false);
      }
      else
      {
        Expression on = join.getJoinCondition();
        if (on != null) {
          command.addCondition(on);
        }
        join.removeJoinCondition();
        top.removeJoin();
        command.addTableFilter(join, true);
      }
      top = join;
    }
  }
  
  private void parseSelectSimpleSelectPart(Select command)
  {
    Select temp = this.currentSelect;
    
    this.currentSelect = null;
    if (readIf("TOP"))
    {
      Expression limit = readTerm().optimize(this.session);
      command.setLimit(limit);
    }
    else if (readIf("LIMIT"))
    {
      Expression offset = readTerm().optimize(this.session);
      command.setOffset(offset);
      Expression limit = readTerm().optimize(this.session);
      command.setLimit(limit);
    }
    this.currentSelect = temp;
    if (readIf("DISTINCT")) {
      command.setDistinct(true);
    } else {
      readIf("ALL");
    }
    ArrayList<Expression> expressions = New.arrayList();
    do
    {
      if (readIf("*"))
      {
        expressions.add(new Wildcard(null, null));
      }
      else
      {
        Expression expr = readExpression();
        if ((readIf("AS")) || (this.currentTokenType == 2))
        {
          String alias = readAliasIdentifier();
          boolean aliasColumnName = this.database.getSettings().aliasColumnName;
          aliasColumnName |= this.database.getMode().aliasColumnName;
          expr = new Alias(expr, alias, aliasColumnName);
        }
        expressions.add(expr);
      }
    } while (readIf(","));
    command.setExpressions(expressions);
  }
  
  private Select parseSelectSimple()
  {
    boolean fromFirst;
    if (readIf("SELECT"))
    {
      fromFirst = false;
    }
    else
    {
      boolean fromFirst;
      if (readIf("FROM")) {
        fromFirst = true;
      } else {
        throw getSyntaxError();
      }
    }
    boolean fromFirst;
    Select command = new Select(this.session);
    int start = this.lastParseIndex;
    Select oldSelect = this.currentSelect;
    this.currentSelect = command;
    this.currentPrepared = command;
    if (fromFirst)
    {
      parseSelectSimpleFromPart(command);
      read("SELECT");
      parseSelectSimpleSelectPart(command);
    }
    else
    {
      parseSelectSimpleSelectPart(command);
      if (!readIf("FROM"))
      {
        Table dual = getDualTable(false);
        TableFilter filter = new TableFilter(this.session, dual, null, this.rightsChecked, this.currentSelect);
        
        command.addTableFilter(filter, true);
      }
      else
      {
        parseSelectSimpleFromPart(command);
      }
    }
    if (readIf("WHERE"))
    {
      Expression condition = readExpression();
      command.addCondition(condition);
    }
    this.currentSelect = oldSelect;
    if (readIf("GROUP"))
    {
      read("BY");
      command.setGroupQuery();
      ArrayList<Expression> list = New.arrayList();
      do
      {
        Expression expr = readExpression();
        list.add(expr);
      } while (readIf(","));
      command.setGroupBy(list);
    }
    this.currentSelect = command;
    if (readIf("HAVING"))
    {
      command.setGroupQuery();
      Expression condition = readExpression();
      command.setHaving(condition);
    }
    command.setParameterList(this.parameters);
    this.currentSelect = oldSelect;
    setSQL(command, "SELECT", start);
    return command;
  }
  
  private Table getDualTable(boolean noColumns)
  {
    Schema main = this.database.findSchema("PUBLIC");
    Expression one = ValueExpression.get(ValueLong.get(1L));
    return new RangeTable(main, one, one, noColumns);
  }
  
  private void setSQL(Prepared command, String start, int startIndex)
  {
    String sql = this.originalSQL.substring(startIndex, this.lastParseIndex).trim();
    if (start != null) {
      sql = start + " " + sql;
    }
    command.setSQL(sql);
  }
  
  private Expression readExpression()
  {
    Expression r = readAnd();
    while (readIf("OR")) {
      r = new ConditionAndOr(1, r, readAnd());
    }
    return r;
  }
  
  private Expression readAnd()
  {
    Expression r = readCondition();
    while (readIf("AND")) {
      r = new ConditionAndOr(0, r, readCondition());
    }
    return r;
  }
  
  private Expression readCondition()
  {
    if (readIf("NOT")) {
      return new ConditionNot(readCondition());
    }
    if (readIf("EXISTS"))
    {
      read("(");
      Query query = parseSelect();
      
      read(")");
      return new ConditionExists(query);
    }
    if (readIf("INTERSECTS"))
    {
      read("(");
      Expression r1 = readConcat();
      read(",");
      Expression r2 = readConcat();
      read(")");
      return new Comparison(this.session, 11, r1, r2);
    }
    Expression r = readConcat();
    for (;;)
    {
      int backup = this.parseIndex;
      boolean not = false;
      if (readIf("NOT"))
      {
        not = true;
        if (isToken("NULL"))
        {
          this.parseIndex = backup;
          this.currentToken = "NOT";
          break;
        }
      }
      if (readIf("LIKE"))
      {
        Expression b = readConcat();
        Expression esc = null;
        if (readIf("ESCAPE")) {
          esc = readConcat();
        }
        this.recompileAlways = true;
        r = new CompareLike(this.database, r, b, esc, false);
      }
      else if (readIf("REGEXP"))
      {
        Expression b = readConcat();
        r = new CompareLike(this.database, r, b, null, true);
      }
      else if (readIf("IS"))
      {
        if (readIf("NOT"))
        {
          if (readIf("NULL"))
          {
            r = new Comparison(this.session, 7, r, null);
          }
          else if (readIf("DISTINCT"))
          {
            read("FROM");
            r = new Comparison(this.session, 16, r, readConcat());
          }
          else
          {
            r = new Comparison(this.session, 21, r, readConcat());
          }
        }
        else if (readIf("NULL"))
        {
          r = new Comparison(this.session, 6, r, null);
        }
        else if (readIf("DISTINCT"))
        {
          read("FROM");
          r = new Comparison(this.session, 21, r, readConcat());
        }
        else
        {
          r = new Comparison(this.session, 16, r, readConcat());
        }
      }
      else if (readIf("IN"))
      {
        read("(");
        if (readIf(")"))
        {
          r = ValueExpression.get(ValueBoolean.get(false));
        }
        else
        {
          if (isSelect())
          {
            Query query = parseSelect();
            r = new ConditionInSelect(this.database, r, query, false, 0);
          }
          else
          {
            ArrayList<Expression> v = New.arrayList();
            Expression last;
            do
            {
              last = readExpression();
              v.add(last);
            } while (readIf(","));
            if ((v.size() == 1) && ((last instanceof Subquery)))
            {
              Subquery s = (Subquery)last;
              Query q = s.getQuery();
              r = new ConditionInSelect(this.database, r, q, false, 0);
            }
            else
            {
              r = new ConditionIn(this.database, r, v);
            }
          }
          read(")");
        }
      }
      else if (readIf("BETWEEN"))
      {
        Expression low = readConcat();
        read("AND");
        Expression high = readConcat();
        Expression condLow = new Comparison(this.session, 3, low, r);
        
        Expression condHigh = new Comparison(this.session, 1, high, r);
        
        r = new ConditionAndOr(0, condLow, condHigh);
      }
      else
      {
        int compareType = getCompareType(this.currentTokenType);
        if (compareType < 0) {
          break;
        }
        read();
        if (readIf("ALL"))
        {
          read("(");
          Query query = parseSelect();
          r = new ConditionInSelect(this.database, r, query, true, compareType);
          
          read(")");
        }
        else if ((readIf("ANY")) || (readIf("SOME")))
        {
          read("(");
          Query query = parseSelect();
          r = new ConditionInSelect(this.database, r, query, false, compareType);
          
          read(")");
        }
        else
        {
          Expression right = readConcat();
          if ((SysProperties.OLD_STYLE_OUTER_JOIN) && (readIf("(")) && (readIf("+")) && (readIf(")")))
          {
            if (((r instanceof ExpressionColumn)) && ((right instanceof ExpressionColumn)))
            {
              ExpressionColumn leftCol = (ExpressionColumn)r;
              ExpressionColumn rightCol = (ExpressionColumn)right;
              ArrayList<TableFilter> filters = this.currentSelect.getTopFilters();
              for (TableFilter f : filters) {
                while (f != null)
                {
                  leftCol.mapColumns(f, 0);
                  rightCol.mapColumns(f, 0);
                  f = f.getJoin();
                }
              }
              TableFilter leftFilter = leftCol.getTableFilter();
              TableFilter rightFilter = rightCol.getTableFilter();
              r = new Comparison(this.session, compareType, r, right);
              if ((leftFilter != null) && (rightFilter != null))
              {
                int idx = filters.indexOf(rightFilter);
                if (idx >= 0)
                {
                  filters.remove(idx);
                  leftFilter.addJoin(rightFilter, true, false, r);
                }
                else
                {
                  rightFilter.mapAndAddFilter(r);
                }
                r = ValueExpression.get(ValueBoolean.get(true));
              }
            }
          }
          else {
            r = new Comparison(this.session, compareType, r, right);
          }
        }
      }
      if (not) {
        r = new ConditionNot(r);
      }
    }
    return r;
  }
  
  private Expression readConcat()
  {
    Expression r = readSum();
    for (;;)
    {
      if (readIf("||"))
      {
        r = new Operation(0, r, readSum());
      }
      else if (readIf("~"))
      {
        if (readIf("*"))
        {
          Function function = Function.getFunction(this.database, "CAST");
          function.setDataType(new Column("X", 14));
          
          function.setParameter(0, r);
          r = function;
        }
        r = new CompareLike(this.database, r, readSum(), null, true);
      }
      else
      {
        if (!readIf("!~")) {
          break;
        }
        if (readIf("*"))
        {
          Function function = Function.getFunction(this.database, "CAST");
          function.setDataType(new Column("X", 14));
          
          function.setParameter(0, r);
          r = function;
        }
        r = new ConditionNot(new CompareLike(this.database, r, readSum(), null, true));
      }
    }
    return r;
  }
  
  private Expression readSum()
  {
    Expression r = readFactor();
    for (;;)
    {
      if (readIf("+"))
      {
        r = new Operation(1, r, readFactor());
      }
      else
      {
        if (!readIf("-")) {
          break;
        }
        r = new Operation(2, r, readFactor());
      }
    }
    return r;
  }
  
  private Expression readFactor()
  {
    Expression r = readTerm();
    for (;;)
    {
      if (readIf("*"))
      {
        r = new Operation(3, r, readTerm());
      }
      else if (readIf("/"))
      {
        r = new Operation(4, r, readTerm());
      }
      else
      {
        if (!readIf("%")) {
          break;
        }
        r = new Operation(6, r, readTerm());
      }
    }
    return r;
  }
  
  private Expression readAggregate(int aggregateType, String aggregateName)
  {
    if (this.currentSelect == null) {
      throw getSyntaxError();
    }
    this.currentSelect.setGroupQuery();
    Expression r;
    Expression r;
    if (aggregateType == 1)
    {
      Expression r;
      if (readIf("*"))
      {
        r = new Aggregate(0, null, this.currentSelect, false);
      }
      else
      {
        boolean distinct = readIf("DISTINCT");
        Expression on = readExpression();
        Expression r;
        if (((on instanceof Wildcard)) && (!distinct)) {
          r = new Aggregate(0, null, this.currentSelect, false);
        } else {
          r = new Aggregate(1, on, this.currentSelect, distinct);
        }
      }
    }
    else
    {
      Expression r;
      if (aggregateType == 2)
      {
        Aggregate agg = null;
        if (equalsToken("GROUP_CONCAT", aggregateName))
        {
          boolean distinct = readIf("DISTINCT");
          agg = new Aggregate(2, readExpression(), this.currentSelect, distinct);
          if (readIf("ORDER"))
          {
            read("BY");
            agg.setGroupConcatOrder(parseSimpleOrderList());
          }
          if (readIf("SEPARATOR")) {
            agg.setGroupConcatSeparator(readExpression());
          }
        }
        else if (equalsToken("STRING_AGG", aggregateName))
        {
          agg = new Aggregate(2, readExpression(), this.currentSelect, false);
          
          read(",");
          agg.setGroupConcatSeparator(readExpression());
        }
        r = agg;
      }
      else
      {
        boolean distinct = readIf("DISTINCT");
        r = new Aggregate(aggregateType, readExpression(), this.currentSelect, distinct);
      }
    }
    read(")");
    return r;
  }
  
  private ArrayList<SelectOrderBy> parseSimpleOrderList()
  {
    ArrayList<SelectOrderBy> orderList = New.arrayList();
    do
    {
      SelectOrderBy order = new SelectOrderBy();
      Expression expr = readExpression();
      order.expression = expr;
      if (readIf("DESC")) {
        order.descending = true;
      } else {
        readIf("ASC");
      }
      orderList.add(order);
    } while (readIf(","));
    return orderList;
  }
  
  private JavaFunction readJavaFunction(Schema schema, String functionName)
  {
    FunctionAlias functionAlias = null;
    if (schema != null) {
      functionAlias = schema.findFunction(functionName);
    } else {
      functionAlias = findFunctionAlias(this.session.getCurrentSchemaName(), functionName);
    }
    if (functionAlias == null) {
      throw DbException.get(90022, functionName);
    }
    ArrayList<Expression> argList = New.arrayList();
    int numArgs = 0;
    while (!readIf(")"))
    {
      if (numArgs++ > 0) {
        read(",");
      }
      argList.add(readExpression());
    }
    Expression[] args = new Expression[numArgs];
    argList.toArray(args);
    JavaFunction func = new JavaFunction(functionAlias, args);
    return func;
  }
  
  private JavaAggregate readJavaAggregate(UserAggregate aggregate)
  {
    ArrayList<Expression> params = New.arrayList();
    do
    {
      params.add(readExpression());
    } while (readIf(","));
    read(")");
    Expression[] list = new Expression[params.size()];
    params.toArray(list);
    JavaAggregate agg = new JavaAggregate(aggregate, list, this.currentSelect);
    this.currentSelect.setGroupQuery();
    return agg;
  }
  
  private int getAggregateType(String name)
  {
    if (!this.identifiersToUpper) {
      name = StringUtils.toUpperEnglish(name);
    }
    return Aggregate.getAggregateType(name);
  }
  
  private Expression readFunction(Schema schema, String name)
  {
    if (schema != null) {
      return readJavaFunction(schema, name);
    }
    int agg = getAggregateType(name);
    if (agg >= 0) {
      return readAggregate(agg, name);
    }
    Function function = Function.getFunction(this.database, name);
    if (function == null)
    {
      UserAggregate aggregate = this.database.findAggregate(name);
      if (aggregate != null) {
        return readJavaAggregate(aggregate);
      }
      return readJavaFunction(null, name);
    }
    switch (function.getFunctionType())
    {
    case 203: 
      function.setParameter(0, readExpression());
      read("AS");
      Column type = parseColumnWithType(null);
      function.setDataType(type);
      read(")");
      break;
    case 202: 
      if (this.database.getMode().swapConvertFunctionParameters)
      {
        Column type = parseColumnWithType(null);
        function.setDataType(type);
        read(",");
        function.setParameter(0, readExpression());
        read(")");
      }
      else
      {
        function.setParameter(0, readExpression());
        read(",");
        Column type = parseColumnWithType(null);
        function.setDataType(type);
        read(")");
      }
      break;
    case 120: 
      function.setParameter(0, ValueExpression.get(ValueString.get(this.currentToken)));
      
      read();
      read("FROM");
      function.setParameter(1, readExpression());
      read(")");
      break;
    case 102: 
    case 103: 
      if (Function.isDatePart(this.currentToken))
      {
        function.setParameter(0, ValueExpression.get(ValueString.get(this.currentToken)));
        
        read();
      }
      else
      {
        function.setParameter(0, readExpression());
      }
      read(",");
      function.setParameter(1, readExpression());
      read(",");
      function.setParameter(2, readExpression());
      read(")");
      break;
    case 73: 
      function.setParameter(0, readExpression());
      if (readIf("FROM"))
      {
        function.setParameter(1, readExpression());
        if (readIf("FOR")) {
          function.setParameter(2, readExpression());
        }
      }
      else if (readIf("FOR"))
      {
        function.setParameter(1, ValueExpression.get(ValueInt.get(0)));
        function.setParameter(2, readExpression());
      }
      else
      {
        read(",");
        function.setParameter(1, readExpression());
        if (readIf(",")) {
          function.setParameter(2, readExpression());
        }
      }
      read(")");
      break;
    case 77: 
      function.setParameter(0, readConcat());
      if (!readIf(",")) {
        read("IN");
      }
      function.setParameter(1, readExpression());
      read(")");
      break;
    case 78: 
      Expression space = null;
      if (readIf("LEADING"))
      {
        function = Function.getFunction(this.database, "LTRIM");
        if (!readIf("FROM"))
        {
          space = readExpression();
          read("FROM");
        }
      }
      else if (readIf("TRAILING"))
      {
        function = Function.getFunction(this.database, "RTRIM");
        if (!readIf("FROM"))
        {
          space = readExpression();
          read("FROM");
        }
      }
      else if ((readIf("BOTH")) && 
        (!readIf("FROM")))
      {
        space = readExpression();
        read("FROM");
      }
      Expression p0 = readExpression();
      if (readIf(","))
      {
        space = readExpression();
      }
      else if (readIf("FROM"))
      {
        space = p0;
        p0 = readExpression();
      }
      function.setParameter(0, p0);
      if (space != null) {
        function.setParameter(1, space);
      }
      read(")");
      break;
    case 223: 
    case 224: 
      int i = 0;
      ArrayList<Column> columns = New.arrayList();
      do
      {
        String columnName = readAliasIdentifier();
        Column column = parseColumnWithType(columnName);
        columns.add(column);
        read("=");
        function.setParameter(i, readExpression());
        i++;
      } while (readIf(","));
      read(")");
      TableFunction tf = (TableFunction)function;
      tf.setColumns(columns);
      break;
    case 300: 
      read(")");
      read("OVER");
      read("(");
      read(")");
      return new Rownum(this.currentSelect == null ? this.currentPrepared : this.currentSelect);
    default: 
      if (!readIf(")"))
      {
        int i = 0;
        do
        {
          function.setParameter(i++, readExpression());
        } while (readIf(","));
        read(")");
      }
      break;
    }
    function.doneWithParameters();
    return function;
  }
  
  private Function readFunctionWithoutParameters(String name)
  {
    if (readIf("(")) {
      read(")");
    }
    Function function = Function.getFunction(this.database, name);
    function.doneWithParameters();
    return function;
  }
  
  private Expression readWildcardOrSequenceValue(String schema, String objectName)
  {
    if (readIf("*")) {
      return new Wildcard(schema, objectName);
    }
    if (schema == null) {
      schema = this.session.getCurrentSchemaName();
    }
    if (readIf("NEXTVAL"))
    {
      Sequence sequence = findSequence(schema, objectName);
      if (sequence != null) {
        return new SequenceValue(sequence);
      }
    }
    else if (readIf("CURRVAL"))
    {
      Sequence sequence = findSequence(schema, objectName);
      if (sequence != null)
      {
        Function function = Function.getFunction(this.database, "CURRVAL");
        function.setParameter(0, ValueExpression.get(ValueString.get(sequence.getSchema().getName())));
        
        function.setParameter(1, ValueExpression.get(ValueString.get(sequence.getName())));
        
        function.doneWithParameters();
        return function;
      }
    }
    return null;
  }
  
  private Expression readTermObjectDot(String objectName)
  {
    Expression expr = readWildcardOrSequenceValue(null, objectName);
    if (expr != null) {
      return expr;
    }
    String name = readColumnIdentifier();
    Schema s = this.database.findSchema(objectName);
    if (((!SysProperties.OLD_STYLE_OUTER_JOIN) || (s != null)) && (readIf("("))) {
      return readFunction(s, name);
    }
    if (readIf("."))
    {
      String schema = objectName;
      objectName = name;
      expr = readWildcardOrSequenceValue(schema, objectName);
      if (expr != null) {
        return expr;
      }
      name = readColumnIdentifier();
      if (readIf("("))
      {
        String databaseName = schema;
        if (!equalsToken(this.database.getShortName(), databaseName)) {
          throw DbException.get(90013, databaseName);
        }
        schema = objectName;
        return readFunction(this.database.getSchema(schema), name);
      }
      if (readIf("."))
      {
        String databaseName = schema;
        if (!equalsToken(this.database.getShortName(), databaseName)) {
          throw DbException.get(90013, databaseName);
        }
        schema = objectName;
        objectName = name;
        expr = readWildcardOrSequenceValue(schema, objectName);
        if (expr != null) {
          return expr;
        }
        name = readColumnIdentifier();
        return new ExpressionColumn(this.database, schema, objectName, name);
      }
      return new ExpressionColumn(this.database, schema, objectName, name);
    }
    return new ExpressionColumn(this.database, null, objectName, name);
  }
  
  private Expression readTerm()
  {
    Expression r;
    Expression r;
    Expression r;
    Expression r;
    switch (this.currentTokenType)
    {
    case 12: 
      read();
      r = new Variable(this.session, readAliasIdentifier());
      if (readIf(":="))
      {
        Expression value = readExpression();
        Function function = Function.getFunction(this.database, "SET");
        function.setParameter(0, r);
        function.setParameter(1, value);
        r = function;
      }
      break;
    case 3: 
      boolean indexed = Character.isDigit(this.sqlCommandChars[this.parseIndex]);
      read();
      Parameter p;
      if ((indexed) && (this.currentTokenType == 5) && (this.currentValue.getType() == 4))
      {
        if (this.indexedParameterList == null)
        {
          if (this.parameters == null) {
            throw getSyntaxError();
          }
          if (this.parameters.size() > 0) {
            throw DbException.get(90123);
          }
          this.indexedParameterList = New.arrayList();
        }
        int index = this.currentValue.getInt() - 1;
        if ((index < 0) || (index >= 100000)) {
          throw DbException.getInvalidValueException("parameter index", Integer.valueOf(index));
        }
        if (this.indexedParameterList.size() <= index)
        {
          this.indexedParameterList.ensureCapacity(index + 1);
          while (this.indexedParameterList.size() <= index) {
            this.indexedParameterList.add(null);
          }
        }
        Parameter p = (Parameter)this.indexedParameterList.get(index);
        if (p == null)
        {
          p = new Parameter(index);
          this.indexedParameterList.set(index, p);
        }
        read();
      }
      else
      {
        if (this.indexedParameterList != null) {
          throw DbException.get(90123);
        }
        p = new Parameter(this.parameters.size());
      }
      this.parameters.add(p);
      r = p;
      break;
    case 1: 
      if ((isToken("SELECT")) || (isToken("FROM")))
      {
        Query query = parseSelect();
        r = new Subquery(query);
      }
      else
      {
        throw getSyntaxError();
      }
      break;
    case 2: 
      String name = this.currentToken;
      Expression r;
      if (this.currentTokenQuoted)
      {
        read();
        Expression r;
        if (readIf("("))
        {
          r = readFunction(null, name);
        }
        else
        {
          Expression r;
          if (readIf(".")) {
            r = readTermObjectDot(name);
          } else {
            r = new ExpressionColumn(this.database, null, null, name);
          }
        }
      }
      else
      {
        read();
        Expression r;
        if (readIf("."))
        {
          r = readTermObjectDot(name);
        }
        else
        {
          Expression r;
          if (equalsToken("CASE", name))
          {
            r = readCase();
          }
          else
          {
            Expression r;
            if (readIf("("))
            {
              r = readFunction(null, name);
            }
            else
            {
              Expression r;
              if (equalsToken("CURRENT_USER", name))
              {
                r = readFunctionWithoutParameters("USER");
              }
              else
              {
                Expression r;
                if (equalsToken("CURRENT", name))
                {
                  Expression r;
                  if (readIf("TIMESTAMP"))
                  {
                    r = readFunctionWithoutParameters("CURRENT_TIMESTAMP");
                  }
                  else
                  {
                    Expression r;
                    if (readIf("TIME"))
                    {
                      r = readFunctionWithoutParameters("CURRENT_TIME");
                    }
                    else
                    {
                      Expression r;
                      if (readIf("DATE")) {
                        r = readFunctionWithoutParameters("CURRENT_DATE");
                      } else {
                        r = new ExpressionColumn(this.database, null, null, name);
                      }
                    }
                  }
                }
                else
                {
                  Expression r;
                  if ((equalsToken("NEXT", name)) && (readIf("VALUE")))
                  {
                    read("FOR");
                    Sequence sequence = readSequence();
                    r = new SequenceValue(sequence);
                  }
                  else
                  {
                    Expression r;
                    if ((this.currentTokenType == 5) && (this.currentValue.getType() == 13))
                    {
                      Expression r;
                      if ((equalsToken("DATE", name)) || (equalsToken("D", name)))
                      {
                        String date = this.currentValue.getString();
                        read();
                        r = ValueExpression.get(ValueDate.parse(date));
                      }
                      else
                      {
                        Expression r;
                        if ((equalsToken("TIME", name)) || (equalsToken("T", name)))
                        {
                          String time = this.currentValue.getString();
                          read();
                          r = ValueExpression.get(ValueTime.parse(time));
                        }
                        else
                        {
                          Expression r;
                          if ((equalsToken("TIMESTAMP", name)) || (equalsToken("TS", name)))
                          {
                            String timestamp = this.currentValue.getString();
                            read();
                            r = ValueExpression.get(ValueTimestamp.parse(timestamp));
                          }
                          else
                          {
                            Expression r;
                            if (equalsToken("X", name))
                            {
                              read();
                              byte[] buffer = StringUtils.convertHexToBytes(this.currentValue.getString());
                              
                              r = ValueExpression.get(ValueBytes.getNoCopy(buffer));
                            }
                            else
                            {
                              Expression r;
                              if (equalsToken("E", name))
                              {
                                String text = this.currentValue.getString();
                                
                                text = StringUtils.replaceAll(text, "\\\\", "\\");
                                read();
                                r = ValueExpression.get(ValueString.get(text));
                              }
                              else
                              {
                                Expression r;
                                if (equalsToken("N", name))
                                {
                                  String text = this.currentValue.getString();
                                  read();
                                  r = ValueExpression.get(ValueString.get(text));
                                }
                                else
                                {
                                  r = new ExpressionColumn(this.database, null, null, name);
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                    else
                    {
                      r = new ExpressionColumn(this.database, null, null, name);
                    }
                  }
                }
              }
            }
          }
        }
      }
      break;
    case 13: 
      read();
      if (this.currentTokenType == 5)
      {
        r = ValueExpression.get(this.currentValue.negate());
        if ((r.getType() == 5) && (r.getValue(this.session).getLong() == -2147483648L)) {
          r = ValueExpression.get(ValueInt.get(Integer.MIN_VALUE));
        } else if ((r.getType() == 6) && (r.getValue(this.session).getBigDecimal().compareTo(ValueLong.MIN_BD) == 0)) {
          r = ValueExpression.get(ValueLong.get(Long.MIN_VALUE));
        }
        read();
      }
      else
      {
        r = new Operation(5, readTerm(), null);
      }
      break;
    case 14: 
      read();
      r = readTerm();
      break;
    case 16: 
      read();
      if (readIf(")"))
      {
        r = new ExpressionList(new Expression[0]);
      }
      else
      {
        r = readExpression();
        if (readIf(","))
        {
          ArrayList<Expression> list = New.arrayList();
          list.add(r);
          while (!readIf(")"))
          {
            r = readExpression();
            list.add(r);
            if (!readIf(",")) {
              read(")");
            }
          }
          Expression[] array = new Expression[list.size()];
          list.toArray(array);
          r = new ExpressionList(array);
        }
        else
        {
          read(")");
        }
      }
      break;
    case 19: 
      read();
      r = ValueExpression.get(ValueBoolean.get(true));
      break;
    case 20: 
      read();
      r = ValueExpression.get(ValueBoolean.get(false));
      break;
    case 23: 
      read();
      r = readFunctionWithoutParameters("CURRENT_TIME");
      break;
    case 22: 
      read();
      r = readFunctionWithoutParameters("CURRENT_DATE");
      break;
    case 21: 
      Function function = Function.getFunction(this.database, "CURRENT_TIMESTAMP");
      
      read();
      if ((readIf("(")) && 
        (!readIf(")")))
      {
        function.setParameter(0, readExpression());
        read(")");
      }
      function.doneWithParameters();
      r = function;
      break;
    case 24: 
      read();
      if (readIf("(")) {
        read(")");
      }
      r = new Rownum(this.currentSelect == null ? this.currentPrepared : this.currentSelect);
      
      break;
    case 18: 
      read();
      r = ValueExpression.getNull();
      break;
    case 5: 
      r = ValueExpression.get(this.currentValue);
      read();
      break;
    case 4: 
    case 6: 
    case 7: 
    case 8: 
    case 9: 
    case 10: 
    case 11: 
    case 15: 
    case 17: 
    default: 
      throw getSyntaxError();
    }
    if (readIf("["))
    {
      Function function = Function.getFunction(this.database, "ARRAY_GET");
      function.setParameter(0, r);
      r = readExpression();
      r = new Operation(1, r, ValueExpression.get(ValueInt.get(1)));
      
      function.setParameter(1, r);
      r = function;
      read("]");
    }
    if (readIf("::"))
    {
      if (isToken("PG_CATALOG"))
      {
        read("PG_CATALOG");
        read(".");
      }
      if (readIf("REGCLASS"))
      {
        FunctionAlias f = findFunctionAlias("PUBLIC", "PG_GET_OID");
        if (f == null) {
          throw getSyntaxError();
        }
        Expression[] args = { r };
        JavaFunction func = new JavaFunction(f, args);
        r = func;
      }
      else
      {
        Column col = parseColumnWithType(null);
        Function function = Function.getFunction(this.database, "CAST");
        function.setDataType(col);
        function.setParameter(0, r);
        r = function;
      }
    }
    return r;
  }
  
  private Expression readCase()
  {
    if (readIf("END"))
    {
      readIf("CASE");
      return ValueExpression.getNull();
    }
    if (readIf("ELSE"))
    {
      Expression elsePart = readExpression().optimize(this.session);
      read("END");
      readIf("CASE");
      return elsePart;
    }
    Function function;
    int i;
    if (readIf("WHEN"))
    {
      Function function = Function.getFunction(this.database, "CASE");
      function.setParameter(0, null);
      int i = 1;
      do
      {
        function.setParameter(i++, readExpression());
        read("THEN");
        function.setParameter(i++, readExpression());
      } while (readIf("WHEN"));
    }
    else
    {
      Expression expr = readExpression();
      if (readIf("END"))
      {
        readIf("CASE");
        return ValueExpression.getNull();
      }
      if (readIf("ELSE"))
      {
        Expression elsePart = readExpression().optimize(this.session);
        read("END");
        readIf("CASE");
        return elsePart;
      }
      function = Function.getFunction(this.database, "CASE");
      function.setParameter(0, expr);
      i = 1;
      read("WHEN");
      do
      {
        function.setParameter(i++, readExpression());
        read("THEN");
        function.setParameter(i++, readExpression());
      } while (readIf("WHEN"));
    }
    if (readIf("ELSE")) {
      function.setParameter(i, readExpression());
    }
    read("END");
    readIf("CASE");
    function.doneWithParameters();
    return function;
  }
  
  private int readPositiveInt()
  {
    int v = readInt();
    if (v < 0) {
      throw DbException.getInvalidValueException("positive integer", Integer.valueOf(v));
    }
    return v;
  }
  
  private int readInt()
  {
    boolean minus = false;
    if (this.currentTokenType == 13)
    {
      minus = true;
      read();
    }
    else if (this.currentTokenType == 14)
    {
      read();
    }
    if (this.currentTokenType != 5) {
      throw DbException.getSyntaxError(this.sqlCommand, this.parseIndex, "integer");
    }
    if (minus) {
      this.currentValue = this.currentValue.negate();
    }
    int i = this.currentValue.getInt();
    read();
    return i;
  }
  
  private long readLong()
  {
    boolean minus = false;
    if (this.currentTokenType == 13)
    {
      minus = true;
      read();
    }
    else if (this.currentTokenType == 14)
    {
      read();
    }
    if (this.currentTokenType != 5) {
      throw DbException.getSyntaxError(this.sqlCommand, this.parseIndex, "long");
    }
    if (minus) {
      this.currentValue = this.currentValue.negate();
    }
    long i = this.currentValue.getLong();
    read();
    return i;
  }
  
  private boolean readBooleanSetting()
  {
    if (this.currentTokenType == 5)
    {
      boolean result = this.currentValue.getBoolean().booleanValue();
      read();
      return result;
    }
    if ((readIf("TRUE")) || (readIf("ON"))) {
      return true;
    }
    if ((readIf("FALSE")) || (readIf("OFF"))) {
      return false;
    }
    throw getSyntaxError();
  }
  
  private String readString()
  {
    Expression expr = readExpression().optimize(this.session);
    if (!(expr instanceof ValueExpression)) {
      throw DbException.getSyntaxError(this.sqlCommand, this.parseIndex, "string");
    }
    String s = expr.getValue(this.session).getString();
    return s;
  }
  
  private String readIdentifierWithSchema(String defaultSchemaName)
  {
    if (this.currentTokenType != 2) {
      throw DbException.getSyntaxError(this.sqlCommand, this.parseIndex, "identifier");
    }
    String s = this.currentToken;
    read();
    this.schemaName = defaultSchemaName;
    if (readIf("."))
    {
      this.schemaName = s;
      if (this.currentTokenType != 2) {
        throw DbException.getSyntaxError(this.sqlCommand, this.parseIndex, "identifier");
      }
      s = this.currentToken;
      read();
    }
    if ((equalsToken(".", this.currentToken)) && 
      (equalsToken(this.schemaName, this.database.getShortName())))
    {
      read(".");
      this.schemaName = s;
      if (this.currentTokenType != 2) {
        throw DbException.getSyntaxError(this.sqlCommand, this.parseIndex, "identifier");
      }
      s = this.currentToken;
      read();
    }
    return s;
  }
  
  private String readIdentifierWithSchema()
  {
    return readIdentifierWithSchema(this.session.getCurrentSchemaName());
  }
  
  private String readAliasIdentifier()
  {
    return readColumnIdentifier();
  }
  
  private String readUniqueIdentifier()
  {
    return readColumnIdentifier();
  }
  
  private String readColumnIdentifier()
  {
    if (this.currentTokenType != 2) {
      throw DbException.getSyntaxError(this.sqlCommand, this.parseIndex, "identifier");
    }
    String s = this.currentToken;
    read();
    return s;
  }
  
  private void read(String expected)
  {
    if ((this.currentTokenQuoted) || (!equalsToken(expected, this.currentToken)))
    {
      addExpected(expected);
      throw getSyntaxError();
    }
    read();
  }
  
  private boolean readIf(String token)
  {
    if ((!this.currentTokenQuoted) && (equalsToken(token, this.currentToken)))
    {
      read();
      return true;
    }
    addExpected(token);
    return false;
  }
  
  private boolean isToken(String token)
  {
    boolean result = (equalsToken(token, this.currentToken)) && (!this.currentTokenQuoted);
    if (result) {
      return true;
    }
    addExpected(token);
    return false;
  }
  
  private boolean equalsToken(String a, String b)
  {
    if (a == null) {
      return b == null;
    }
    if (a.equals(b)) {
      return true;
    }
    if ((!this.identifiersToUpper) && (a.equalsIgnoreCase(b))) {
      return true;
    }
    return false;
  }
  
  private void addExpected(String token)
  {
    if (this.expectedList != null) {
      this.expectedList.add(token);
    }
  }
  
  private void read()
  {
    this.currentTokenQuoted = false;
    if (this.expectedList != null) {
      this.expectedList.clear();
    }
    int[] types = this.characterTypes;
    this.lastParseIndex = this.parseIndex;
    int i = this.parseIndex;
    int type = types[i];
    while (type == 0) {
      type = types[(++i)];
    }
    int start = i;
    char[] chars = this.sqlCommandChars;
    char c = chars[(i++)];
    this.currentToken = "";
    switch (type)
    {
    case 4: 
      for (;;)
      {
        type = types[i];
        if ((type != 4) && (type != 2)) {
          break;
        }
        i++;
      }
      this.currentToken = StringUtils.fromCacheOrNew(this.sqlCommand.substring(start, i));
      
      this.currentTokenType = getTokenType(this.currentToken);
      this.parseIndex = i;
      return;
    case 3: 
      String result = null;
      for (;;)
      {
        for (int begin = i;; i++) {
          if (chars[i] == '"')
          {
            if (result == null)
            {
              result = this.sqlCommand.substring(begin, i); break;
            }
            result = result + this.sqlCommand.substring(begin - 1, i);
            
            break;
          }
        }
        if (chars[(++i)] != '"') {
          break;
        }
        i++;
      }
      this.currentToken = StringUtils.fromCacheOrNew(result);
      this.parseIndex = i;
      this.currentTokenQuoted = true;
      this.currentTokenType = 2;
      return;
    case 6: 
      if (types[i] == 6) {
        i++;
      }
      this.currentToken = this.sqlCommand.substring(start, i);
      this.currentTokenType = getSpecialType(this.currentToken);
      this.parseIndex = i;
      return;
    case 5: 
      this.currentToken = this.sqlCommand.substring(start, i);
      this.currentTokenType = getSpecialType(this.currentToken);
      this.parseIndex = i;
      return;
    case 2: 
      if ((c == '0') && (chars[i] == 'X'))
      {
        long number = 0L;
        start += 2;
        i++;
        for (;;)
        {
          c = chars[i];
          if (((c < '0') || (c > '9')) && ((c < 'A') || (c > 'F')))
          {
            checkLiterals(false);
            this.currentValue = ValueInt.get((int)number);
            this.currentTokenType = 5;
            this.currentToken = "0";
            this.parseIndex = i;
            return;
          }
          number = (number << 4) + c - (c >= 'A' ? 55 : 48);
          if (number > 2147483647L)
          {
            readHexDecimal(start, i);
            return;
          }
          i++;
        }
      }
      long number = c - '0';
      for (;;)
      {
        c = chars[i];
        if ((c < '0') || (c > '9'))
        {
          if ((c == '.') || (c == 'E') || (c == 'L'))
          {
            readDecimal(start, i);
            break;
          }
          checkLiterals(false);
          this.currentValue = ValueInt.get((int)number);
          this.currentTokenType = 5;
          this.currentToken = "0";
          this.parseIndex = i;
          break;
        }
        number = number * 10L + (c - '0');
        if (number > 2147483647L)
        {
          readDecimal(start, i);
          break;
        }
        i++;
      }
      return;
    case 8: 
      if (types[i] != 2)
      {
        this.currentTokenType = 1;
        this.currentToken = ".";
        this.parseIndex = i;
        return;
      }
      readDecimal(i - 1, i);
      return;
    case 7: 
      String result = null;
      for (;;)
      {
        for (int begin = i;; i++) {
          if (chars[i] == '\'')
          {
            if (result == null)
            {
              result = this.sqlCommand.substring(begin, i); break;
            }
            result = result + this.sqlCommand.substring(begin - 1, i);
            
            break;
          }
        }
        if (chars[(++i)] != '\'') {
          break;
        }
        i++;
      }
      this.currentToken = "'";
      checkLiterals(true);
      this.currentValue = ValueString.get(StringUtils.fromCacheOrNew(result), this.database.getMode().treatEmptyStringsAsNull);
      
      this.parseIndex = i;
      this.currentTokenType = 5;
      return;
    case 9: 
      String result = null;
      int begin = i - 1;
      while (types[i] == 9) {
        i++;
      }
      result = this.sqlCommand.substring(begin, i);
      this.currentToken = "'";
      checkLiterals(true);
      this.currentValue = ValueString.get(StringUtils.fromCacheOrNew(result), this.database.getMode().treatEmptyStringsAsNull);
      
      this.parseIndex = i;
      this.currentTokenType = 5;
      return;
    case 1: 
      this.currentToken = "";
      this.currentTokenType = 4;
      this.parseIndex = i;
      return;
    }
    throw getSyntaxError();
  }
  
  private void checkLiterals(boolean text)
  {
    if (!this.session.getAllowLiterals())
    {
      int allowed = this.database.getAllowLiterals();
      if ((allowed == 0) || ((text) && (allowed != 2))) {
        throw DbException.get(90116);
      }
    }
  }
  
  private void readHexDecimal(int start, int i)
  {
    char[] chars = this.sqlCommandChars;
    char c;
    do
    {
      c = chars[(++i)];
    } while (((c >= '0') && (c <= '9')) || ((c >= 'A') && (c <= 'F')));
    this.parseIndex = i;
    String sub = this.sqlCommand.substring(start, i);
    BigDecimal bd = new BigDecimal(new BigInteger(sub, 16));
    checkLiterals(false);
    this.currentValue = ValueDecimal.get(bd);
    this.currentTokenType = 5;
  }
  
  private void readDecimal(int start, int i)
  {
    char[] chars = this.sqlCommandChars;
    int[] types = this.characterTypes;
    for (;;)
    {
      int t = types[i];
      if ((t != 8) && (t != 2)) {
        break;
      }
      i++;
    }
    boolean containsE = false;
    if ((chars[i] == 'E') || (chars[i] == 'e'))
    {
      containsE = true;
      i++;
      if ((chars[i] == '+') || (chars[i] == '-')) {
        i++;
      }
      if (types[i] != 2) {
        throw getSyntaxError();
      }
      while (types[(++i)] == 2) {}
    }
    this.parseIndex = i;
    String sub = this.sqlCommand.substring(start, i);
    checkLiterals(false);
    if ((!containsE) && (sub.indexOf('.') < 0))
    {
      BigInteger bi = new BigInteger(sub);
      if (bi.compareTo(ValueLong.MAX) <= 0)
      {
        if (chars[i] == 'L') {
          this.parseIndex += 1;
        }
        this.currentValue = ValueLong.get(bi.longValue());
        this.currentTokenType = 5; return;
      }
    }
    BigDecimal bd;
    try
    {
      bd = new BigDecimal(sub);
    }
    catch (NumberFormatException e)
    {
      throw DbException.get(22018, e, new String[] { sub });
    }
    this.currentValue = ValueDecimal.get(bd);
    this.currentTokenType = 5;
  }
  
  public Session getSession()
  {
    return this.session;
  }
  
  private void initialize(String sql)
  {
    if (sql == null) {
      sql = "";
    }
    this.originalSQL = sql;
    this.sqlCommand = sql;
    int len = sql.length() + 1;
    char[] command = new char[len];
    int[] types = new int[len];
    len--;
    sql.getChars(0, len, command, 0);
    boolean changed = false;
    command[len] = ' ';
    int startLoop = 0;
    int lastType = 0;
    for (int i = 0; i < len; i++)
    {
      char c = command[i];
      int type = 0;
      switch (c)
      {
      case '/': 
        if (command[(i + 1)] == '*')
        {
          changed = true;
          command[i] = ' ';
          command[(i + 1)] = ' ';
          startLoop = i;
          i += 2;
          checkRunOver(i, len, startLoop);
          while ((command[i] != '*') || (command[(i + 1)] != '/'))
          {
            command[(i++)] = ' ';
            checkRunOver(i, len, startLoop);
          }
          command[i] = ' ';
          command[(i + 1)] = ' ';
          i++;
        }
        else
        {
          if (command[(i + 1)] == '/')
          {
            changed = true;
            startLoop = i;
            for (;;)
            {
              c = command[i];
              if ((c == '\n') || (c == '\r') || (i >= len - 1)) {
                break;
              }
              command[(i++)] = ' ';
              checkRunOver(i, len, startLoop);
            }
          }
          type = 5;
        }
        break;
      case '-': 
        if (command[(i + 1)] == '-')
        {
          changed = true;
          startLoop = i;
          for (;;)
          {
            c = command[i];
            if ((c == '\n') || (c == '\r') || (i >= len - 1)) {
              break;
            }
            command[(i++)] = ' ';
            checkRunOver(i, len, startLoop);
          }
        }
        type = 5;
        
        break;
      case '$': 
        if ((command[(i + 1)] == '$') && ((i == 0) || (command[(i - 1)] <= ' ')))
        {
          changed = true;
          command[i] = ' ';
          command[(i + 1)] = ' ';
          startLoop = i;
          i += 2;
          checkRunOver(i, len, startLoop);
          while ((command[i] != '$') || (command[(i + 1)] != '$'))
          {
            types[(i++)] = 9;
            checkRunOver(i, len, startLoop);
          }
          command[i] = ' ';
          command[(i + 1)] = ' ';
          i++;
        }
        else if ((lastType == 4) || (lastType == 2))
        {
          type = 4;
        }
        else
        {
          type = 5;
        }
        break;
      case '%': 
      case '(': 
      case ')': 
      case '*': 
      case '+': 
      case ',': 
      case ';': 
      case '?': 
      case '@': 
      case ']': 
      case '{': 
      case '}': 
        type = 5;
        break;
      case '!': 
      case '&': 
      case ':': 
      case '<': 
      case '=': 
      case '>': 
      case '|': 
      case '~': 
        type = 6;
        break;
      case '.': 
        type = 8;
        break;
      case '\'': 
        type = types[i] = 7;
        startLoop = i;
      case '[': 
      case '`': 
      case '"': 
      case '_': 
      case '#': 
      case '0': 
      case '1': 
      case '2': 
      case '3': 
      case '4': 
      case '5': 
      case '6': 
      case '7': 
      case '8': 
      case '9': 
      case 'A': 
      case 'B': 
      case 'C': 
      case 'D': 
      case 'E': 
      case 'F': 
      case 'G': 
      case 'H': 
      case 'I': 
      case 'J': 
      case 'K': 
      case 'L': 
      case 'M': 
      case 'N': 
      case 'O': 
      case 'P': 
      case 'Q': 
      case 'R': 
      case 'S': 
      case 'T': 
      case 'U': 
      case 'V': 
      case 'W': 
      case 'X': 
      case 'Y': 
      case 'Z': 
      case '\\': 
      case '^': 
      case 'a': 
      case 'b': 
      case 'c': 
      case 'd': 
      case 'e': 
      case 'f': 
      case 'g': 
      case 'h': 
      case 'i': 
      case 'j': 
      case 'k': 
      case 'l': 
      case 'm': 
      case 'n': 
      case 'o': 
      case 'p': 
      case 'q': 
      case 'r': 
      case 's': 
      case 't': 
      case 'u': 
      case 'v': 
      case 'w': 
      case 'x': 
      case 'y': 
      case 'z': 
      default: 
        while (command[(++i)] != '\'')
        {
          checkRunOver(i, len, startLoop); continue;
          if (this.database.getMode().squareBracketQuotedNames)
          {
            command[i] = '"';
            changed = true;
            type = types[i] = 3;
            startLoop = i;
            while (command[(++i)] != ']') {
              checkRunOver(i, len, startLoop);
            }
            command[i] = '"';
          }
          else
          {
            type = 5;
            
            break;
            
            command[i] = '"';
            changed = true;
            type = types[i] = 3;
            startLoop = i;
            while (command[(++i)] != '`')
            {
              checkRunOver(i, len, startLoop);
              c = command[i];
              command[i] = Character.toUpperCase(c);
            }
            command[i] = '"';
            break;
            
            type = types[i] = 3;
            startLoop = i;
            while (command[(++i)] != '"')
            {
              checkRunOver(i, len, startLoop); continue;
              
              type = 4;
              break;
              if ((c >= 'a') && (c <= 'z'))
              {
                if (this.identifiersToUpper)
                {
                  command[i] = ((char)(c - ' '));
                  changed = true;
                }
                type = 4;
              }
              else if ((c >= 'A') && (c <= 'Z'))
              {
                type = 4;
              }
              else if ((c >= '0') && (c <= '9'))
              {
                type = 2;
              }
              else if ((c > ' ') && (!Character.isSpaceChar(c)))
              {
                if (Character.isJavaIdentifierPart(c))
                {
                  type = 4;
                  if (this.identifiersToUpper)
                  {
                    char u = Character.toUpperCase(c);
                    if (u != c)
                    {
                      command[i] = u;
                      changed = true;
                    }
                  }
                }
                else
                {
                  type = 5;
                }
              }
            }
          }
        }
      }
      types[i] = type;
      lastType = type;
    }
    this.sqlCommandChars = command;
    types[len] = 1;
    this.characterTypes = types;
    if (changed) {
      this.sqlCommand = new String(command);
    }
    this.parseIndex = 0;
  }
  
  private void checkRunOver(int i, int len, int startLoop)
  {
    if (i >= len)
    {
      this.parseIndex = startLoop;
      throw getSyntaxError();
    }
  }
  
  private int getSpecialType(String s)
  {
    char c0 = s.charAt(0);
    if (s.length() == 1) {
      switch (c0)
      {
      case '$': 
      case '?': 
        return 3;
      case '@': 
        return 12;
      case '+': 
        return 14;
      case '-': 
        return 13;
      case '%': 
      case '*': 
      case ',': 
      case '/': 
      case ':': 
      case ';': 
      case '[': 
      case ']': 
      case '{': 
      case '}': 
      case '~': 
        return 1;
      case '(': 
        return 16;
      case ')': 
        return 17;
      case '<': 
        return 9;
      case '>': 
        return 8;
      case '=': 
        return 6;
      }
    } else if (s.length() == 2) {
      switch (c0)
      {
      case ':': 
        if ("::".equals(s)) {
          return 1;
        }
        if (":=".equals(s)) {
          return 1;
        }
        break;
      case '>': 
        if (">=".equals(s)) {
          return 7;
        }
        break;
      case '<': 
        if ("<=".equals(s)) {
          return 10;
        }
        if ("<>".equals(s)) {
          return 11;
        }
        break;
      case '!': 
        if ("!=".equals(s)) {
          return 11;
        }
        if ("!~".equals(s)) {
          return 1;
        }
        break;
      case '|': 
        if ("||".equals(s)) {
          return 15;
        }
        break;
      case '&': 
        if ("&&".equals(s)) {
          return 25;
        }
        break;
      }
    }
    throw getSyntaxError();
  }
  
  private int getTokenType(String s)
  {
    int len = s.length();
    if (len == 0) {
      throw getSyntaxError();
    }
    if (!this.identifiersToUpper) {
      s = StringUtils.toUpperEnglish(s);
    }
    return getSaveTokenType(s, this.database.getMode().supportOffsetFetch);
  }
  
  private boolean isKeyword(String s)
  {
    if (!this.identifiersToUpper) {
      s = StringUtils.toUpperEnglish(s);
    }
    return isKeyword(s, false);
  }
  
  public static boolean isKeyword(String s, boolean supportOffsetFetch)
  {
    if ((s == null) || (s.length() == 0)) {
      return false;
    }
    return getSaveTokenType(s, supportOffsetFetch) != 2;
  }
  
  private static int getSaveTokenType(String s, boolean supportOffsetFetch)
  {
    switch (s.charAt(0))
    {
    case 'C': 
      if (s.equals("CURRENT_TIMESTAMP")) {
        return 21;
      }
      if (s.equals("CURRENT_TIME")) {
        return 23;
      }
      if (s.equals("CURRENT_DATE")) {
        return 22;
      }
      return getKeywordOrIdentifier(s, "CROSS", 1);
    case 'D': 
      return getKeywordOrIdentifier(s, "DISTINCT", 1);
    case 'E': 
      if ("EXCEPT".equals(s)) {
        return 1;
      }
      return getKeywordOrIdentifier(s, "EXISTS", 1);
    case 'F': 
      if ("FROM".equals(s)) {
        return 1;
      }
      if ("FOR".equals(s)) {
        return 1;
      }
      if ("FULL".equals(s)) {
        return 1;
      }
      if ((supportOffsetFetch) && ("FETCH".equals(s))) {
        return 1;
      }
      return getKeywordOrIdentifier(s, "FALSE", 20);
    case 'G': 
      return getKeywordOrIdentifier(s, "GROUP", 1);
    case 'H': 
      return getKeywordOrIdentifier(s, "HAVING", 1);
    case 'I': 
      if ("INNER".equals(s)) {
        return 1;
      }
      if ("INTERSECT".equals(s)) {
        return 1;
      }
      return getKeywordOrIdentifier(s, "IS", 1);
    case 'J': 
      return getKeywordOrIdentifier(s, "JOIN", 1);
    case 'L': 
      if ("LIMIT".equals(s)) {
        return 1;
      }
      return getKeywordOrIdentifier(s, "LIKE", 1);
    case 'M': 
      return getKeywordOrIdentifier(s, "MINUS", 1);
    case 'N': 
      if ("NOT".equals(s)) {
        return 1;
      }
      if ("NATURAL".equals(s)) {
        return 1;
      }
      return getKeywordOrIdentifier(s, "NULL", 18);
    case 'O': 
      if ("ON".equals(s)) {
        return 1;
      }
      if ((supportOffsetFetch) && ("OFFSET".equals(s))) {
        return 1;
      }
      return getKeywordOrIdentifier(s, "ORDER", 1);
    case 'P': 
      return getKeywordOrIdentifier(s, "PRIMARY", 1);
    case 'R': 
      return getKeywordOrIdentifier(s, "ROWNUM", 24);
    case 'S': 
      if (s.equals("SYSTIMESTAMP")) {
        return 21;
      }
      if (s.equals("SYSTIME")) {
        return 23;
      }
      if (s.equals("SYSDATE")) {
        return 21;
      }
      return getKeywordOrIdentifier(s, "SELECT", 1);
    case 'T': 
      if ("TODAY".equals(s)) {
        return 22;
      }
      return getKeywordOrIdentifier(s, "TRUE", 19);
    case 'U': 
      if ("UNIQUE".equals(s)) {
        return 1;
      }
      return getKeywordOrIdentifier(s, "UNION", 1);
    case 'W': 
      if ("WITH".equals(s)) {
        return 1;
      }
      return getKeywordOrIdentifier(s, "WHERE", 1);
    }
    return 2;
  }
  
  private static int getKeywordOrIdentifier(String s1, String s2, int keywordType)
  {
    if (s1.equals(s2)) {
      return keywordType;
    }
    return 2;
  }
  
  private Column parseColumnForTable(String columnName, boolean defaultNullable)
  {
    boolean isIdentity = false;
    Column column;
    if ((readIf("IDENTITY")) || (readIf("BIGSERIAL")))
    {
      Column column = new Column(columnName, 5);
      column.setOriginalSQL("IDENTITY");
      parseAutoIncrement(column);
      if (!this.database.getMode().serialColumnIsNotPK) {
        column.setPrimaryKey(true);
      }
    }
    else if (readIf("SERIAL"))
    {
      Column column = new Column(columnName, 4);
      column.setOriginalSQL("SERIAL");
      parseAutoIncrement(column);
      if (!this.database.getMode().serialColumnIsNotPK) {
        column.setPrimaryKey(true);
      }
    }
    else
    {
      column = parseColumnWithType(columnName);
    }
    if (readIf("NOT"))
    {
      read("NULL");
      column.setNullable(false);
    }
    else if (readIf("NULL"))
    {
      column.setNullable(true);
    }
    else
    {
      column.setNullable(defaultNullable & column.isNullable());
    }
    if (readIf("AS"))
    {
      if (isIdentity) {
        getSyntaxError();
      }
      Expression expr = readExpression();
      column.setComputedExpression(expr);
    }
    else if (readIf("DEFAULT"))
    {
      Expression defaultExpression = readExpression();
      column.setDefaultExpression(this.session, defaultExpression);
    }
    else if (readIf("GENERATED"))
    {
      if (!readIf("ALWAYS"))
      {
        read("BY");
        read("DEFAULT");
      }
      read("AS");
      read("IDENTITY");
      long start = 1L;long increment = 1L;
      if (readIf("("))
      {
        read("START");
        readIf("WITH");
        start = readLong();
        readIf(",");
        if (readIf("INCREMENT"))
        {
          readIf("BY");
          increment = readLong();
        }
        read(")");
      }
      column.setPrimaryKey(true);
      column.setAutoIncrement(true, start, increment);
    }
    if (readIf("NOT"))
    {
      read("NULL");
      column.setNullable(false);
    }
    else
    {
      readIf("NULL");
    }
    if ((readIf("AUTO_INCREMENT")) || (readIf("BIGSERIAL")) || (readIf("SERIAL")))
    {
      parseAutoIncrement(column);
      if (readIf("NOT")) {
        read("NULL");
      }
    }
    else if (readIf("IDENTITY"))
    {
      parseAutoIncrement(column);
      column.setPrimaryKey(true);
      if (readIf("NOT")) {
        read("NULL");
      }
    }
    if (readIf("NULL_TO_DEFAULT")) {
      column.setConvertNullToDefault(true);
    }
    if (readIf("SEQUENCE"))
    {
      Sequence sequence = readSequence();
      column.setSequence(sequence);
    }
    if (readIf("SELECTIVITY"))
    {
      int value = readPositiveInt();
      column.setSelectivity(value);
    }
    String comment = readCommentIf();
    if (comment != null) {
      column.setComment(comment);
    }
    return column;
  }
  
  private void parseAutoIncrement(Column column)
  {
    long start = 1L;long increment = 1L;
    if (readIf("("))
    {
      start = readLong();
      if (readIf(",")) {
        increment = readLong();
      }
      read(")");
    }
    column.setAutoIncrement(true, start, increment);
  }
  
  private String readCommentIf()
  {
    if (readIf("COMMENT"))
    {
      readIf("IS");
      return readString();
    }
    return null;
  }
  
  private Column parseColumnWithType(String columnName)
  {
    String original = this.currentToken;
    boolean regular = false;
    if (readIf("LONG"))
    {
      if (readIf("RAW")) {
        original = original + " RAW";
      }
    }
    else if (readIf("DOUBLE"))
    {
      if (readIf("PRECISION")) {
        original = original + " PRECISION";
      }
    }
    else if (readIf("CHARACTER"))
    {
      if (readIf("VARYING")) {
        original = original + " VARYING";
      }
    }
    else {
      regular = true;
    }
    long precision = -1L;
    int displaySize = -1;
    int scale = -1;
    String comment = null;
    Column templateColumn = null;
    if (!this.identifiersToUpper) {
      original = StringUtils.toUpperEnglish(original);
    }
    UserDataType userDataType = this.database.findUserDataType(original);
    DataType dataType;
    if (userDataType != null)
    {
      templateColumn = userDataType.getColumn();
      DataType dataType = DataType.getDataType(templateColumn.getType());
      comment = templateColumn.getComment();
      original = templateColumn.getOriginalSQL();
      precision = templateColumn.getPrecision();
      displaySize = templateColumn.getDisplaySize();
      scale = templateColumn.getScale();
    }
    else
    {
      dataType = DataType.getTypeByName(original);
      if (dataType == null) {
        throw DbException.get(50004, this.currentToken);
      }
    }
    if ((this.database.getIgnoreCase()) && (dataType.type == 13) && (!equalsToken("VARCHAR_CASESENSITIVE", original)))
    {
      original = "VARCHAR_IGNORECASE";
      dataType = DataType.getTypeByName(original);
    }
    if (regular) {
      read();
    }
    precision = precision == -1L ? dataType.defaultPrecision : precision;
    displaySize = displaySize == -1 ? dataType.defaultDisplaySize : displaySize;
    
    scale = scale == -1 ? dataType.defaultScale : scale;
    if ((dataType.supportsPrecision) || (dataType.supportsScale))
    {
      if (readIf("("))
      {
        if (!readIf("MAX"))
        {
          long p = readLong();
          if (readIf("K")) {
            p *= 1024L;
          } else if (readIf("M")) {
            p *= 1048576L;
          } else if (readIf("G")) {
            p *= 1073741824L;
          }
          if (p > Long.MAX_VALUE) {
            p = Long.MAX_VALUE;
          }
          original = original + "(" + p;
          
          readIf("CHAR");
          if (dataType.supportsScale) {
            if (readIf(","))
            {
              scale = readInt();
              original = original + ", " + scale;
            }
            else if (dataType.type == 11)
            {
              scale = MathUtils.convertLongToInt(p);
              p = precision;
            }
            else
            {
              scale = 0;
            }
          }
          precision = p;
          displaySize = MathUtils.convertLongToInt(precision);
          original = original + ")";
        }
        read(")");
      }
    }
    else if (readIf("("))
    {
      readPositiveInt();
      read(")");
    }
    if (readIf("FOR"))
    {
      read("BIT");
      read("DATA");
      if (dataType.type == 13) {
        dataType = DataType.getTypeByName("BINARY");
      }
    }
    readIf("UNSIGNED");
    int type = dataType.type;
    if (scale > precision) {
      throw DbException.get(90008, new String[] { Integer.toString(scale), "scale (precision = " + precision + ")" });
    }
    Column column = new Column(columnName, type, precision, scale, displaySize);
    if (templateColumn != null)
    {
      column.setNullable(templateColumn.isNullable());
      column.setDefaultExpression(this.session, templateColumn.getDefaultExpression());
      
      int selectivity = templateColumn.getSelectivity();
      if (selectivity != 50) {
        column.setSelectivity(selectivity);
      }
      Expression checkConstraint = templateColumn.getCheckConstraint(this.session, columnName);
      
      column.addCheckConstraint(this.session, checkConstraint);
    }
    column.setComment(comment);
    column.setOriginalSQL(original);
    return column;
  }
  
  private Prepared parseCreate()
  {
    boolean orReplace = false;
    if (readIf("OR"))
    {
      read("REPLACE");
      orReplace = true;
    }
    boolean force = readIf("FORCE");
    if (readIf("VIEW")) {
      return parseCreateView(force, orReplace);
    }
    if (readIf("ALIAS")) {
      return parseCreateFunctionAlias(force);
    }
    if (readIf("SEQUENCE")) {
      return parseCreateSequence();
    }
    if (readIf("USER")) {
      return parseCreateUser();
    }
    if (readIf("TRIGGER")) {
      return parseCreateTrigger(force);
    }
    if (readIf("ROLE")) {
      return parseCreateRole();
    }
    if (readIf("SCHEMA")) {
      return parseCreateSchema();
    }
    if (readIf("CONSTANT")) {
      return parseCreateConstant();
    }
    if (readIf("DOMAIN")) {
      return parseCreateUserDataType();
    }
    if (readIf("TYPE")) {
      return parseCreateUserDataType();
    }
    if (readIf("DATATYPE")) {
      return parseCreateUserDataType();
    }
    if (readIf("AGGREGATE")) {
      return parseCreateAggregate(force);
    }
    if (readIf("LINKED")) {
      return parseCreateLinkedTable(false, false, force);
    }
    boolean memory = false;boolean cached = false;
    if (readIf("MEMORY")) {
      memory = true;
    } else if (readIf("CACHED")) {
      cached = true;
    }
    if (readIf("LOCAL"))
    {
      read("TEMPORARY");
      if (readIf("LINKED")) {
        return parseCreateLinkedTable(true, false, force);
      }
      read("TABLE");
      return parseCreateTable(true, false, cached);
    }
    if (readIf("GLOBAL"))
    {
      read("TEMPORARY");
      if (readIf("LINKED")) {
        return parseCreateLinkedTable(true, true, force);
      }
      read("TABLE");
      return parseCreateTable(true, true, cached);
    }
    if ((readIf("TEMP")) || (readIf("TEMPORARY")))
    {
      if (readIf("LINKED")) {
        return parseCreateLinkedTable(true, true, force);
      }
      read("TABLE");
      return parseCreateTable(true, true, cached);
    }
    if (readIf("TABLE"))
    {
      if ((!cached) && (!memory)) {
        cached = this.database.getDefaultTableType() == 0;
      }
      return parseCreateTable(false, false, cached);
    }
    boolean hash = false;boolean primaryKey = false;
    boolean unique = false;boolean spatial = false;
    String indexName = null;
    Schema oldSchema = null;
    boolean ifNotExists = false;
    if (readIf("PRIMARY"))
    {
      read("KEY");
      if (readIf("HASH")) {
        hash = true;
      }
      primaryKey = true;
      if (!isToken("ON"))
      {
        ifNotExists = readIfNoExists();
        indexName = readIdentifierWithSchema(null);
        oldSchema = getSchema();
      }
    }
    else
    {
      if (readIf("UNIQUE")) {
        unique = true;
      }
      if (readIf("HASH")) {
        hash = true;
      }
      if (readIf("SPATIAL")) {
        spatial = true;
      }
      if (readIf("INDEX"))
      {
        if (!isToken("ON"))
        {
          ifNotExists = readIfNoExists();
          indexName = readIdentifierWithSchema(null);
          oldSchema = getSchema();
        }
      }
      else {
        throw getSyntaxError();
      }
    }
    read("ON");
    String tableName = readIdentifierWithSchema();
    checkSchema(oldSchema);
    CreateIndex command = new CreateIndex(this.session, getSchema());
    command.setIfNotExists(ifNotExists);
    command.setHash(hash);
    command.setSpatial(spatial);
    command.setPrimaryKey(primaryKey);
    command.setTableName(tableName);
    command.setUnique(unique);
    command.setIndexName(indexName);
    command.setComment(readCommentIf());
    read("(");
    command.setIndexColumns(parseIndexColumnList());
    return command;
  }
  
  private boolean addRoleOrRight(GrantRevoke command)
  {
    if (readIf("SELECT"))
    {
      command.addRight(1);
      return true;
    }
    if (readIf("DELETE"))
    {
      command.addRight(2);
      return true;
    }
    if (readIf("INSERT"))
    {
      command.addRight(4);
      return true;
    }
    if (readIf("UPDATE"))
    {
      command.addRight(8);
      return true;
    }
    if (readIf("ALL"))
    {
      command.addRight(15);
      return true;
    }
    if (readIf("ALTER"))
    {
      read("ANY");
      read("SCHEMA");
      command.addRight(16);
      command.addTable(null);
      return false;
    }
    if (readIf("CONNECT")) {
      return true;
    }
    if (readIf("RESOURCE")) {
      return true;
    }
    command.addRoleName(readUniqueIdentifier());
    return false;
  }
  
  private GrantRevoke parseGrantRevoke(int operationType)
  {
    GrantRevoke command = new GrantRevoke(this.session);
    command.setOperationType(operationType);
    boolean tableClauseExpected = addRoleOrRight(command);
    while (readIf(","))
    {
      addRoleOrRight(command);
      if ((command.isRightMode()) && (command.isRoleMode())) {
        throw DbException.get(90072);
      }
    }
    if ((tableClauseExpected) && 
      (readIf("ON"))) {
      do
      {
        Table table = readTableOrView();
        command.addTable(table);
      } while (readIf(","));
    }
    if (operationType == 49) {
      read("TO");
    } else {
      read("FROM");
    }
    command.setGranteeName(readUniqueIdentifier());
    return command;
  }
  
  private Select parseValues()
  {
    Select command = new Select(this.session);
    this.currentSelect = command;
    TableFilter filter = parseValuesTable();
    ArrayList<Expression> list = New.arrayList();
    list.add(new Wildcard(null, null));
    command.setExpressions(list);
    command.addTableFilter(filter, true);
    command.init();
    return command;
  }
  
  private TableFilter parseValuesTable()
  {
    Schema mainSchema = this.database.getSchema("PUBLIC");
    TableFunction tf = (TableFunction)Function.getFunction(this.database, "TABLE");
    
    ArrayList<Column> columns = New.arrayList();
    ArrayList<ArrayList<Expression>> rows = New.arrayList();
    do
    {
      int i = 0;
      ArrayList<Expression> row = New.arrayList();
      boolean multiColumn = readIf("(");
      do
      {
        Expression expr = readExpression();
        expr = expr.optimize(this.session);
        int type = expr.getType();
        
        String columnName = "C" + (i + 1);
        if (rows.size() == 0)
        {
          if (type == -1) {
            type = 13;
          }
          DataType dt = DataType.getDataType(type);
          long prec = dt.defaultPrecision;
          int scale = dt.defaultScale;
          int displaySize = dt.defaultDisplaySize;
          Column column = new Column(columnName, type, prec, scale, displaySize);
          
          columns.add(column);
        }
        long prec = expr.getPrecision();
        int scale = expr.getScale();
        int displaySize = expr.getDisplaySize();
        if (i >= columns.size()) {
          throw DbException.get(21002);
        }
        Column c = (Column)columns.get(i);
        type = Value.getHigherOrder(c.getType(), type);
        prec = Math.max(c.getPrecision(), prec);
        scale = Math.max(c.getScale(), scale);
        displaySize = Math.max(c.getDisplaySize(), displaySize);
        Column column = new Column(columnName, type, prec, scale, displaySize);
        columns.set(i, column);
        row.add(expr);
        i++;
      } while ((multiColumn) && (readIf(",")));
      if (multiColumn) {
        read(")");
      }
      rows.add(row);
    } while (readIf(","));
    int columnCount = columns.size();
    int rowCount = rows.size();
    for (int i = 0; i < rowCount; i++) {
      if (((ArrayList)rows.get(i)).size() != columnCount) {
        throw DbException.get(21002);
      }
    }
    for (int i = 0; i < columnCount; i++)
    {
      Column c = (Column)columns.get(i);
      if (c.getType() == -1)
      {
        c = new Column(c.getName(), 13, 0L, 0, 0);
        columns.set(i, c);
      }
      Expression[] array = new Expression[rowCount];
      for (int j = 0; j < rowCount; j++) {
        array[j] = ((Expression)((ArrayList)rows.get(j)).get(i));
      }
      ExpressionList list = new ExpressionList(array);
      tf.setParameter(i, list);
    }
    tf.setColumns(columns);
    tf.doneWithParameters();
    Table table = new FunctionTable(mainSchema, this.session, tf, tf);
    TableFilter filter = new TableFilter(this.session, table, null, this.rightsChecked, this.currentSelect);
    
    return filter;
  }
  
  private Call parseCall()
  {
    Call command = new Call(this.session);
    this.currentPrepared = command;
    command.setExpression(readExpression());
    return command;
  }
  
  private CreateRole parseCreateRole()
  {
    CreateRole command = new CreateRole(this.session);
    command.setIfNotExists(readIfNoExists());
    command.setRoleName(readUniqueIdentifier());
    return command;
  }
  
  private CreateSchema parseCreateSchema()
  {
    CreateSchema command = new CreateSchema(this.session);
    command.setIfNotExists(readIfNoExists());
    command.setSchemaName(readUniqueIdentifier());
    if (readIf("AUTHORIZATION")) {
      command.setAuthorization(readUniqueIdentifier());
    } else {
      command.setAuthorization(this.session.getUser().getName());
    }
    return command;
  }
  
  private CreateSequence parseCreateSequence()
  {
    boolean ifNotExists = readIfNoExists();
    String sequenceName = readIdentifierWithSchema();
    CreateSequence command = new CreateSequence(this.session, getSchema());
    command.setIfNotExists(ifNotExists);
    command.setSequenceName(sequenceName);
    for (;;)
    {
      if (readIf("START"))
      {
        readIf("WITH");
        command.setStartWith(readExpression());
      }
      else if (readIf("INCREMENT"))
      {
        readIf("BY");
        command.setIncrement(readExpression());
      }
      else if (readIf("MINVALUE"))
      {
        command.setMinValue(readExpression());
      }
      else if (readIf("NOMINVALUE"))
      {
        command.setMinValue(null);
      }
      else if (readIf("MAXVALUE"))
      {
        command.setMaxValue(readExpression());
      }
      else if (readIf("NOMAXVALUE"))
      {
        command.setMaxValue(null);
      }
      else if (readIf("CYCLE"))
      {
        command.setCycle(true);
      }
      else if (readIf("NOCYCLE"))
      {
        command.setCycle(false);
      }
      else if (readIf("NO"))
      {
        if (readIf("MINVALUE"))
        {
          command.setMinValue(null);
        }
        else if (readIf("MAXVALUE"))
        {
          command.setMaxValue(null);
        }
        else if (readIf("CYCLE"))
        {
          command.setCycle(false);
        }
        else
        {
          if (!readIf("CACHE")) {
            break;
          }
          command.setCacheSize(ValueExpression.get(ValueLong.get(1L)));
        }
      }
      else if (readIf("CACHE"))
      {
        command.setCacheSize(readExpression());
      }
      else if (readIf("NOCACHE"))
      {
        command.setCacheSize(ValueExpression.get(ValueLong.get(1L)));
      }
      else
      {
        if (!readIf("BELONGS_TO_TABLE")) {
          break;
        }
        command.setBelongsToTable(true);
      }
    }
    return command;
  }
  
  private boolean readIfNoExists()
  {
    if (readIf("IF"))
    {
      read("NOT");
      read("EXISTS");
      return true;
    }
    return false;
  }
  
  private CreateConstant parseCreateConstant()
  {
    boolean ifNotExists = readIfNoExists();
    String constantName = readIdentifierWithSchema();
    Schema schema = getSchema();
    if (isKeyword(constantName)) {
      throw DbException.get(90114, constantName);
    }
    read("VALUE");
    Expression expr = readExpression();
    CreateConstant command = new CreateConstant(this.session, schema);
    command.setConstantName(constantName);
    command.setExpression(expr);
    command.setIfNotExists(ifNotExists);
    return command;
  }
  
  private CreateAggregate parseCreateAggregate(boolean force)
  {
    boolean ifNotExists = readIfNoExists();
    CreateAggregate command = new CreateAggregate(this.session);
    command.setForce(force);
    String name = readIdentifierWithSchema();
    if ((isKeyword(name)) || (Function.getFunction(this.database, name) != null) || (getAggregateType(name) >= 0)) {
      throw DbException.get(90076, name);
    }
    command.setName(name);
    command.setSchema(getSchema());
    command.setIfNotExists(ifNotExists);
    read("FOR");
    command.setJavaClassMethod(readUniqueIdentifier());
    return command;
  }
  
  private CreateUserDataType parseCreateUserDataType()
  {
    boolean ifNotExists = readIfNoExists();
    CreateUserDataType command = new CreateUserDataType(this.session);
    command.setTypeName(readUniqueIdentifier());
    read("AS");
    Column col = parseColumnForTable("VALUE", true);
    if (readIf("CHECK"))
    {
      Expression expr = readExpression();
      col.addCheckConstraint(this.session, expr);
    }
    col.rename(null);
    command.setColumn(col);
    command.setIfNotExists(ifNotExists);
    return command;
  }
  
  private CreateTrigger parseCreateTrigger(boolean force)
  {
    boolean ifNotExists = readIfNoExists();
    String triggerName = readIdentifierWithSchema(null);
    Schema schema = getSchema();
    boolean insteadOf;
    boolean insteadOf;
    boolean isBefore;
    if (readIf("INSTEAD"))
    {
      read("OF");
      boolean isBefore = true;
      insteadOf = true;
    }
    else
    {
      boolean isBefore;
      if (readIf("BEFORE"))
      {
        boolean insteadOf = false;
        isBefore = true;
      }
      else
      {
        read("AFTER");
        insteadOf = false;
        isBefore = false;
      }
    }
    int typeMask = 0;
    boolean onRollback = false;
    do
    {
      if (readIf("INSERT")) {
        typeMask |= 0x1;
      } else if (readIf("UPDATE")) {
        typeMask |= 0x2;
      } else if (readIf("DELETE")) {
        typeMask |= 0x4;
      } else if (readIf("SELECT")) {
        typeMask |= 0x8;
      } else if (readIf("ROLLBACK")) {
        onRollback = true;
      } else {
        throw getSyntaxError();
      }
    } while (readIf(","));
    read("ON");
    String tableName = readIdentifierWithSchema();
    checkSchema(schema);
    CreateTrigger command = new CreateTrigger(this.session, getSchema());
    command.setForce(force);
    command.setTriggerName(triggerName);
    command.setIfNotExists(ifNotExists);
    command.setInsteadOf(insteadOf);
    command.setBefore(isBefore);
    command.setOnRollback(onRollback);
    command.setTypeMask(typeMask);
    command.setTableName(tableName);
    if (readIf("FOR"))
    {
      read("EACH");
      read("ROW");
      command.setRowBased(true);
    }
    else
    {
      command.setRowBased(false);
    }
    if (readIf("QUEUE")) {
      command.setQueueSize(readPositiveInt());
    }
    command.setNoWait(readIf("NOWAIT"));
    if (readIf("AS"))
    {
      command.setTriggerSource(readString());
    }
    else
    {
      read("CALL");
      command.setTriggerClassName(readUniqueIdentifier());
    }
    return command;
  }
  
  private CreateUser parseCreateUser()
  {
    CreateUser command = new CreateUser(this.session);
    command.setIfNotExists(readIfNoExists());
    command.setUserName(readUniqueIdentifier());
    command.setComment(readCommentIf());
    if (readIf("PASSWORD"))
    {
      command.setPassword(readExpression());
    }
    else if (readIf("SALT"))
    {
      command.setSalt(readExpression());
      read("HASH");
      command.setHash(readExpression());
    }
    else if (readIf("IDENTIFIED"))
    {
      read("BY");
      
      command.setPassword(ValueExpression.get(ValueString.get(readColumnIdentifier())));
    }
    else
    {
      throw getSyntaxError();
    }
    if (readIf("ADMIN")) {
      command.setAdmin(true);
    }
    return command;
  }
  
  private CreateFunctionAlias parseCreateFunctionAlias(boolean force)
  {
    boolean ifNotExists = readIfNoExists();
    String aliasName = readIdentifierWithSchema();
    if ((isKeyword(aliasName)) || (Function.getFunction(this.database, aliasName) != null) || (getAggregateType(aliasName) >= 0)) {
      throw DbException.get(90076, aliasName);
    }
    CreateFunctionAlias command = new CreateFunctionAlias(this.session, getSchema());
    
    command.setForce(force);
    command.setAliasName(aliasName);
    command.setIfNotExists(ifNotExists);
    command.setDeterministic(readIf("DETERMINISTIC"));
    command.setBufferResultSetToLocalTemp(!readIf("NOBUFFER"));
    if (readIf("AS"))
    {
      command.setSource(readString());
    }
    else
    {
      read("FOR");
      command.setJavaClassMethod(readUniqueIdentifier());
    }
    return command;
  }
  
  private Query parseWith()
  {
    readIf("RECURSIVE");
    String tempViewName = readIdentifierWithSchema();
    Schema schema = getSchema();
    
    read("(");
    ArrayList<Column> columns = New.arrayList();
    String[] cols = parseColumnList();
    for (String c : cols) {
      columns.add(new Column(c, 13));
    }
    Table old = this.session.findLocalTempTable(tempViewName);
    if (old != null)
    {
      if (!(old instanceof TableView)) {
        throw DbException.get(42101, tempViewName);
      }
      TableView tv = (TableView)old;
      if (!tv.isTableExpression()) {
        throw DbException.get(42101, tempViewName);
      }
      this.session.removeLocalTempTable(old);
    }
    CreateTableData data = new CreateTableData();
    data.id = this.database.allocateObjectId();
    data.columns = columns;
    data.tableName = tempViewName;
    data.temporary = true;
    data.persistData = true;
    data.persistIndexes = false;
    data.create = true;
    data.session = this.session;
    Table recursiveTable = schema.createTable(data);
    this.session.addLocalTempTable(recursiveTable);
    String querySQL;
    try
    {
      read("AS");
      read("(");
      Query withQuery = parseSelect();
      read(")");
      withQuery.prepare();
      querySQL = StringUtils.fromCacheOrNew(withQuery.getPlanSQL());
    }
    finally
    {
      this.session.removeLocalTempTable(recursiveTable);
    }
    int id = this.database.allocateObjectId();
    TableView view = new TableView(schema, id, tempViewName, querySQL, null, cols, this.session, true);
    
    view.setTableExpression(true);
    view.setTemporary(true);
    this.session.addLocalTempTable(view);
    view.setOnCommitDrop(true);
    Query q = parseSelect();
    q.setPrepareAlways(true);
    return q;
  }
  
  private CreateView parseCreateView(boolean force, boolean orReplace)
  {
    boolean ifNotExists = readIfNoExists();
    String viewName = readIdentifierWithSchema();
    CreateView command = new CreateView(this.session, getSchema());
    this.createView = command;
    command.setViewName(viewName);
    command.setIfNotExists(ifNotExists);
    command.setComment(readCommentIf());
    command.setOrReplace(orReplace);
    command.setForce(force);
    if (readIf("("))
    {
      String[] cols = parseColumnList();
      command.setColumnNames(cols);
    }
    String select = StringUtils.fromCacheOrNew(this.sqlCommand.substring(this.parseIndex));
    
    read("AS");
    try
    {
      Query query = parseSelect();
      query.prepare();
      command.setSelect(query);
    }
    catch (DbException e)
    {
      if (force)
      {
        command.setSelectSQL(select);
        while (this.currentTokenType != 4) {
          read();
        }
      }
      throw e;
    }
    return command;
  }
  
  private TransactionCommand parseCheckpoint()
  {
    TransactionCommand command;
    TransactionCommand command;
    if (readIf("SYNC")) {
      command = new TransactionCommand(this.session, 76);
    } else {
      command = new TransactionCommand(this.session, 73);
    }
    return command;
  }
  
  private Prepared parseAlter()
  {
    if (readIf("TABLE")) {
      return parseAlterTable();
    }
    if (readIf("USER")) {
      return parseAlterUser();
    }
    if (readIf("INDEX")) {
      return parseAlterIndex();
    }
    if (readIf("SCHEMA")) {
      return parseAlterSchema();
    }
    if (readIf("SEQUENCE")) {
      return parseAlterSequence();
    }
    if (readIf("VIEW")) {
      return parseAlterView();
    }
    throw getSyntaxError();
  }
  
  private void checkSchema(Schema old)
  {
    if ((old != null) && (getSchema() != old)) {
      throw DbException.get(90080);
    }
  }
  
  private AlterIndexRename parseAlterIndex()
  {
    String indexName = readIdentifierWithSchema();
    Schema old = getSchema();
    AlterIndexRename command = new AlterIndexRename(this.session);
    command.setOldIndex(getSchema().getIndex(indexName));
    read("RENAME");
    read("TO");
    String newName = readIdentifierWithSchema(old.getName());
    checkSchema(old);
    command.setNewName(newName);
    return command;
  }
  
  private AlterView parseAlterView()
  {
    AlterView command = new AlterView(this.session);
    String viewName = readIdentifierWithSchema();
    Table tableView = getSchema().findTableOrView(this.session, viewName);
    if (!(tableView instanceof TableView)) {
      throw DbException.get(90037, viewName);
    }
    TableView view = (TableView)tableView;
    command.setView(view);
    read("RECOMPILE");
    return command;
  }
  
  private AlterSchemaRename parseAlterSchema()
  {
    String schemaName = readIdentifierWithSchema();
    Schema old = getSchema();
    AlterSchemaRename command = new AlterSchemaRename(this.session);
    command.setOldSchema(getSchema(schemaName));
    read("RENAME");
    read("TO");
    String newName = readIdentifierWithSchema(old.getName());
    checkSchema(old);
    command.setNewName(newName);
    return command;
  }
  
  private AlterSequence parseAlterSequence()
  {
    String sequenceName = readIdentifierWithSchema();
    Sequence sequence = getSchema().getSequence(sequenceName);
    AlterSequence command = new AlterSequence(this.session, sequence.getSchema());
    command.setSequence(sequence);
    for (;;)
    {
      if (readIf("RESTART"))
      {
        read("WITH");
        command.setStartWith(readExpression());
      }
      else if (readIf("INCREMENT"))
      {
        read("BY");
        command.setIncrement(readExpression());
      }
      else if (readIf("MINVALUE"))
      {
        command.setMinValue(readExpression());
      }
      else if (readIf("NOMINVALUE"))
      {
        command.setMinValue(null);
      }
      else if (readIf("MAXVALUE"))
      {
        command.setMaxValue(readExpression());
      }
      else if (readIf("NOMAXVALUE"))
      {
        command.setMaxValue(null);
      }
      else if (readIf("CYCLE"))
      {
        command.setCycle(Boolean.valueOf(true));
      }
      else if (readIf("NOCYCLE"))
      {
        command.setCycle(Boolean.valueOf(false));
      }
      else if (readIf("NO"))
      {
        if (readIf("MINVALUE"))
        {
          command.setMinValue(null);
        }
        else if (readIf("MAXVALUE"))
        {
          command.setMaxValue(null);
        }
        else if (readIf("CYCLE"))
        {
          command.setCycle(Boolean.valueOf(false));
        }
        else
        {
          if (!readIf("CACHE")) {
            break;
          }
          command.setCacheSize(ValueExpression.get(ValueLong.get(1L)));
        }
      }
      else if (readIf("CACHE"))
      {
        command.setCacheSize(readExpression());
      }
      else
      {
        if (!readIf("NOCACHE")) {
          break;
        }
        command.setCacheSize(ValueExpression.get(ValueLong.get(1L)));
      }
    }
    return command;
  }
  
  private AlterUser parseAlterUser()
  {
    String userName = readUniqueIdentifier();
    if (readIf("SET"))
    {
      AlterUser command = new AlterUser(this.session);
      command.setType(19);
      command.setUser(this.database.getUser(userName));
      if (readIf("PASSWORD"))
      {
        command.setPassword(readExpression());
      }
      else if (readIf("SALT"))
      {
        command.setSalt(readExpression());
        read("HASH");
        command.setHash(readExpression());
      }
      else
      {
        throw getSyntaxError();
      }
      return command;
    }
    if (readIf("RENAME"))
    {
      read("TO");
      AlterUser command = new AlterUser(this.session);
      command.setType(18);
      command.setUser(this.database.getUser(userName));
      String newName = readUniqueIdentifier();
      command.setNewName(newName);
      return command;
    }
    if (readIf("ADMIN"))
    {
      AlterUser command = new AlterUser(this.session);
      command.setType(17);
      User user = this.database.getUser(userName);
      command.setUser(user);
      if (readIf("TRUE")) {
        command.setAdmin(true);
      } else if (readIf("FALSE")) {
        command.setAdmin(false);
      } else {
        throw getSyntaxError();
      }
      return command;
    }
    throw getSyntaxError();
  }
  
  private void readIfEqualOrTo()
  {
    if (!readIf("=")) {
      readIf("TO");
    }
  }
  
  private Prepared parseSet()
  {
    if (readIf("@"))
    {
      Set command = new Set(this.session, 35);
      command.setString(readAliasIdentifier());
      readIfEqualOrTo();
      command.setExpression(readExpression());
      return command;
    }
    if (readIf("AUTOCOMMIT"))
    {
      readIfEqualOrTo();
      boolean value = readBooleanSetting();
      int setting = value ? 69 : 70;
      
      return new TransactionCommand(this.session, setting);
    }
    if (readIf("MVCC"))
    {
      readIfEqualOrTo();
      boolean value = readBooleanSetting();
      Set command = new Set(this.session, 31);
      command.setInt(value ? 1 : 0);
      return command;
    }
    if (readIf("EXCLUSIVE"))
    {
      readIfEqualOrTo();
      Set command = new Set(this.session, 33);
      command.setExpression(readExpression());
      return command;
    }
    if (readIf("IGNORECASE"))
    {
      readIfEqualOrTo();
      boolean value = readBooleanSetting();
      Set command = new Set(this.session, 1);
      command.setInt(value ? 1 : 0);
      return command;
    }
    if (readIf("PASSWORD"))
    {
      readIfEqualOrTo();
      AlterUser command = new AlterUser(this.session);
      command.setType(19);
      command.setUser(this.session.getUser());
      command.setPassword(readExpression());
      return command;
    }
    if (readIf("SALT"))
    {
      readIfEqualOrTo();
      AlterUser command = new AlterUser(this.session);
      command.setType(19);
      command.setUser(this.session.getUser());
      command.setSalt(readExpression());
      read("HASH");
      command.setHash(readExpression());
      return command;
    }
    if (readIf("MODE"))
    {
      readIfEqualOrTo();
      Set command = new Set(this.session, 3);
      command.setString(readAliasIdentifier());
      return command;
    }
    if (readIf("COMPRESS_LOB"))
    {
      readIfEqualOrTo();
      Set command = new Set(this.session, 23);
      if (this.currentTokenType == 5) {
        command.setString(readString());
      } else {
        command.setString(readUniqueIdentifier());
      }
      return command;
    }
    if (readIf("DATABASE"))
    {
      readIfEqualOrTo();
      read("COLLATION");
      return parseSetCollation();
    }
    if (readIf("COLLATION"))
    {
      readIfEqualOrTo();
      return parseSetCollation();
    }
    if (readIf("BINARY_COLLATION"))
    {
      readIfEqualOrTo();
      return parseSetBinaryCollation();
    }
    if (readIf("CLUSTER"))
    {
      readIfEqualOrTo();
      Set command = new Set(this.session, 13);
      command.setString(readString());
      return command;
    }
    if (readIf("DATABASE_EVENT_LISTENER"))
    {
      readIfEqualOrTo();
      Set command = new Set(this.session, 15);
      command.setString(readString());
      return command;
    }
    if (readIf("ALLOW_LITERALS"))
    {
      readIfEqualOrTo();
      Set command = new Set(this.session, 24);
      if (readIf("NONE")) {
        command.setInt(0);
      } else if (readIf("ALL")) {
        command.setInt(2);
      } else if (readIf("NUMBERS")) {
        command.setInt(1);
      } else {
        command.setInt(readPositiveInt());
      }
      return command;
    }
    if (readIf("DEFAULT_TABLE_TYPE"))
    {
      readIfEqualOrTo();
      Set command = new Set(this.session, 7);
      if (readIf("MEMORY")) {
        command.setInt(1);
      } else if (readIf("CACHED")) {
        command.setInt(0);
      } else {
        command.setInt(readPositiveInt());
      }
      return command;
    }
    if (readIf("CREATE"))
    {
      readIfEqualOrTo();
      
      read();
      return new NoOperation(this.session);
    }
    if (readIf("HSQLDB.DEFAULT_TABLE_TYPE"))
    {
      readIfEqualOrTo();
      read();
      return new NoOperation(this.session);
    }
    if (readIf("PAGE_STORE"))
    {
      readIfEqualOrTo();
      read();
      return new NoOperation(this.session);
    }
    if (readIf("CACHE_TYPE"))
    {
      readIfEqualOrTo();
      read();
      return new NoOperation(this.session);
    }
    if (readIf("FILE_LOCK"))
    {
      readIfEqualOrTo();
      read();
      return new NoOperation(this.session);
    }
    if (readIf("DB_CLOSE_ON_EXIT"))
    {
      readIfEqualOrTo();
      read();
      return new NoOperation(this.session);
    }
    if (readIf("AUTO_SERVER"))
    {
      readIfEqualOrTo();
      read();
      return new NoOperation(this.session);
    }
    if (readIf("AUTO_SERVER_PORT"))
    {
      readIfEqualOrTo();
      read();
      return new NoOperation(this.session);
    }
    if (readIf("AUTO_RECONNECT"))
    {
      readIfEqualOrTo();
      read();
      return new NoOperation(this.session);
    }
    if (readIf("ASSERT"))
    {
      readIfEqualOrTo();
      read();
      return new NoOperation(this.session);
    }
    if (readIf("ACCESS_MODE_DATA"))
    {
      readIfEqualOrTo();
      read();
      return new NoOperation(this.session);
    }
    if (readIf("OPEN_NEW"))
    {
      readIfEqualOrTo();
      read();
      return new NoOperation(this.session);
    }
    if (readIf("JMX"))
    {
      readIfEqualOrTo();
      read();
      return new NoOperation(this.session);
    }
    if (readIf("PAGE_SIZE"))
    {
      readIfEqualOrTo();
      read();
      return new NoOperation(this.session);
    }
    if (readIf("RECOVER"))
    {
      readIfEqualOrTo();
      read();
      return new NoOperation(this.session);
    }
    if (readIf("NAMES"))
    {
      readIfEqualOrTo();
      read();
      return new NoOperation(this.session);
    }
    if (readIf("SCHEMA"))
    {
      readIfEqualOrTo();
      Set command = new Set(this.session, 26);
      command.setString(readAliasIdentifier());
      return command;
    }
    if (readIf("DATESTYLE"))
    {
      readIfEqualOrTo();
      if (!readIf("ISO"))
      {
        String s = readString();
        if (!equalsToken(s, "ISO")) {
          throw getSyntaxError();
        }
      }
      return new NoOperation(this.session);
    }
    if ((readIf("SEARCH_PATH")) || (readIf(SetTypes.getTypeName(28))))
    {
      readIfEqualOrTo();
      Set command = new Set(this.session, 28);
      ArrayList<String> list = New.arrayList();
      list.add(readAliasIdentifier());
      while (readIf(",")) {
        list.add(readAliasIdentifier());
      }
      String[] schemaNames = new String[list.size()];
      list.toArray(schemaNames);
      command.setStringArray(schemaNames);
      return command;
    }
    if (readIf("JAVA_OBJECT_SERIALIZER"))
    {
      readIfEqualOrTo();
      return parseSetJavaObjectSerializer();
    }
    if (isToken("LOGSIZE")) {
      this.currentToken = SetTypes.getTypeName(2);
    }
    if (isToken("FOREIGN_KEY_CHECKS")) {
      this.currentToken = SetTypes.getTypeName(30);
    }
    int type = SetTypes.getType(this.currentToken);
    if (type < 0) {
      throw getSyntaxError();
    }
    read();
    readIfEqualOrTo();
    Set command = new Set(this.session, type);
    command.setExpression(readExpression());
    return command;
  }
  
  private Prepared parseUse()
  {
    readIfEqualOrTo();
    Set command = new Set(this.session, 26);
    command.setString(readAliasIdentifier());
    return command;
  }
  
  private Set parseSetCollation()
  {
    Set command = new Set(this.session, 12);
    String name = readAliasIdentifier();
    command.setString(name);
    if (equalsToken(name, "OFF")) {
      return command;
    }
    Collator coll = CompareMode.getCollator(name);
    if (coll == null) {
      throw DbException.getInvalidValueException("collation", name);
    }
    if (readIf("STRENGTH"))
    {
      if (readIf("PRIMARY")) {
        command.setInt(0);
      } else if (readIf("SECONDARY")) {
        command.setInt(1);
      } else if (readIf("TERTIARY")) {
        command.setInt(2);
      } else if (readIf("IDENTICAL")) {
        command.setInt(3);
      }
    }
    else {
      command.setInt(coll.getStrength());
    }
    return command;
  }
  
  private Set parseSetBinaryCollation()
  {
    Set command = new Set(this.session, 38);
    String name = readAliasIdentifier();
    command.setString(name);
    if ((equalsToken(name, "UNSIGNED")) || (equalsToken(name, "SIGNED"))) {
      return command;
    }
    throw DbException.getInvalidValueException("BINARY_COLLATION", name);
  }
  
  private Set parseSetJavaObjectSerializer()
  {
    Set command = new Set(this.session, 39);
    String name = readString();
    command.setString(name);
    return command;
  }
  
  private RunScriptCommand parseRunScript()
  {
    RunScriptCommand command = new RunScriptCommand(this.session);
    read("FROM");
    command.setFileNameExpr(readExpression());
    if (readIf("COMPRESSION")) {
      command.setCompressionAlgorithm(readUniqueIdentifier());
    }
    if (readIf("CIPHER"))
    {
      command.setCipher(readUniqueIdentifier());
      if (readIf("PASSWORD")) {
        command.setPassword(readExpression());
      }
    }
    if (readIf("CHARSET")) {
      command.setCharset(Charset.forName(readString()));
    }
    return command;
  }
  
  private ScriptCommand parseScript()
  {
    ScriptCommand command = new ScriptCommand(this.session);
    boolean data = true;boolean passwords = true;boolean settings = true;
    boolean dropTables = false;boolean simple = false;
    if (readIf("SIMPLE")) {
      simple = true;
    }
    if (readIf("NODATA")) {
      data = false;
    }
    if (readIf("NOPASSWORDS")) {
      passwords = false;
    }
    if (readIf("NOSETTINGS")) {
      settings = false;
    }
    if (readIf("DROP")) {
      dropTables = true;
    }
    if (readIf("BLOCKSIZE"))
    {
      long blockSize = readLong();
      command.setLobBlockSize(blockSize);
    }
    command.setData(data);
    command.setPasswords(passwords);
    command.setSettings(settings);
    command.setDrop(dropTables);
    command.setSimple(simple);
    if (readIf("TO"))
    {
      command.setFileNameExpr(readExpression());
      if (readIf("COMPRESSION")) {
        command.setCompressionAlgorithm(readUniqueIdentifier());
      }
      if (readIf("CIPHER"))
      {
        command.setCipher(readUniqueIdentifier());
        if (readIf("PASSWORD")) {
          command.setPassword(readExpression());
        }
      }
      if (readIf("CHARSET")) {
        command.setCharset(Charset.forName(readString()));
      }
    }
    if (readIf("SCHEMA"))
    {
      HashSet<String> schemaNames = New.hashSet();
      do
      {
        schemaNames.add(readUniqueIdentifier());
      } while (readIf(","));
      command.setSchemaNames(schemaNames);
    }
    else if (readIf("TABLE"))
    {
      ArrayList<Table> tables = New.arrayList();
      do
      {
        tables.add(readTableOrView());
      } while (readIf(","));
      command.setTables(tables);
    }
    return command;
  }
  
  private Table readTableOrView()
  {
    return readTableOrView(readIdentifierWithSchema(null));
  }
  
  private Table readTableOrView(String tableName)
  {
    if (this.schemaName != null) {
      return getSchema().getTableOrView(this.session, tableName);
    }
    Table table = this.database.getSchema(this.session.getCurrentSchemaName()).findTableOrView(this.session, tableName);
    if (table != null) {
      return table;
    }
    String[] schemaNames = this.session.getSchemaSearchPath();
    if (schemaNames != null) {
      for (String name : schemaNames)
      {
        Schema s = this.database.getSchema(name);
        table = s.findTableOrView(this.session, tableName);
        if (table != null) {
          return table;
        }
      }
    }
    throw DbException.get(42102, tableName);
  }
  
  private FunctionAlias findFunctionAlias(String schema, String aliasName)
  {
    FunctionAlias functionAlias = this.database.getSchema(schema).findFunction(aliasName);
    if (functionAlias != null) {
      return functionAlias;
    }
    String[] schemaNames = this.session.getSchemaSearchPath();
    if (schemaNames != null) {
      for (String n : schemaNames)
      {
        functionAlias = this.database.getSchema(n).findFunction(aliasName);
        if (functionAlias != null) {
          return functionAlias;
        }
      }
    }
    return null;
  }
  
  private Sequence findSequence(String schema, String sequenceName)
  {
    Sequence sequence = this.database.getSchema(schema).findSequence(sequenceName);
    if (sequence != null) {
      return sequence;
    }
    String[] schemaNames = this.session.getSchemaSearchPath();
    if (schemaNames != null) {
      for (String n : schemaNames)
      {
        sequence = this.database.getSchema(n).findSequence(sequenceName);
        if (sequence != null) {
          return sequence;
        }
      }
    }
    return null;
  }
  
  private Sequence readSequence()
  {
    String sequenceName = readIdentifierWithSchema(null);
    if (this.schemaName != null) {
      return getSchema().getSequence(sequenceName);
    }
    Sequence sequence = findSequence(this.session.getCurrentSchemaName(), sequenceName);
    if (sequence != null) {
      return sequence;
    }
    throw DbException.get(90036, sequenceName);
  }
  
  private Prepared parseAlterTable()
  {
    Table table = readTableOrView();
    if (readIf("ADD"))
    {
      Prepared command = parseAlterTableAddConstraintIf(table.getName(), table.getSchema());
      if (command != null) {
        return command;
      }
      return parseAlterTableAddColumn(table);
    }
    if (readIf("SET"))
    {
      read("REFERENTIAL_INTEGRITY");
      int type = 55;
      boolean value = readBooleanSetting();
      AlterTableSet command = new AlterTableSet(this.session, table.getSchema(), type, value);
      
      command.setTableName(table.getName());
      if (readIf("CHECK")) {
        command.setCheckExisting(true);
      } else if (readIf("NOCHECK")) {
        command.setCheckExisting(false);
      }
      return command;
    }
    if (readIf("RENAME"))
    {
      read("TO");
      String newName = readIdentifierWithSchema(table.getSchema().getName());
      
      checkSchema(table.getSchema());
      AlterTableRename command = new AlterTableRename(this.session, getSchema());
      
      command.setOldTable(table);
      command.setNewTableName(newName);
      command.setHidden(readIf("HIDDEN"));
      return command;
    }
    if (readIf("DROP"))
    {
      if (readIf("CONSTRAINT"))
      {
        boolean ifExists = readIfExists(false);
        String constraintName = readIdentifierWithSchema(table.getSchema().getName());
        
        ifExists = readIfExists(ifExists);
        checkSchema(table.getSchema());
        AlterTableDropConstraint command = new AlterTableDropConstraint(this.session, getSchema(), ifExists);
        
        command.setConstraintName(constraintName);
        return command;
      }
      if (readIf("FOREIGN"))
      {
        read("KEY");
        String constraintName = readIdentifierWithSchema(table.getSchema().getName());
        
        checkSchema(table.getSchema());
        AlterTableDropConstraint command = new AlterTableDropConstraint(this.session, getSchema(), false);
        
        command.setConstraintName(constraintName);
        return command;
      }
      if (readIf("INDEX"))
      {
        String indexName = readIdentifierWithSchema();
        DropIndex command = new DropIndex(this.session, getSchema());
        command.setIndexName(indexName);
        return command;
      }
      if (readIf("PRIMARY"))
      {
        read("KEY");
        Index idx = table.getPrimaryKey();
        DropIndex command = new DropIndex(this.session, table.getSchema());
        command.setIndexName(idx.getName());
        return command;
      }
      readIf("COLUMN");
      boolean ifExists = readIfExists(false);
      AlterTableAlterColumn command = new AlterTableAlterColumn(this.session, table.getSchema());
      
      command.setType(12);
      String columnName = readColumnIdentifier();
      command.setTable(table);
      if ((ifExists) && (!table.doesColumnExist(columnName))) {
        return new NoOperation(this.session);
      }
      command.setOldColumn(table.getColumn(columnName));
      return command;
    }
    if (readIf("CHANGE"))
    {
      readIf("COLUMN");
      String columnName = readColumnIdentifier();
      Column column = table.getColumn(columnName);
      String newColumnName = readColumnIdentifier();
      
      parseColumnForTable(newColumnName, column.isNullable());
      AlterTableRenameColumn command = new AlterTableRenameColumn(this.session);
      command.setTable(table);
      command.setColumn(column);
      command.setNewColumnName(newColumnName);
      return command;
    }
    if (readIf("MODIFY"))
    {
      readIf("COLUMN");
      String columnName = readColumnIdentifier();
      Column column = table.getColumn(columnName);
      return parseAlterTableAlterColumnType(table, columnName, column);
    }
    if (readIf("ALTER"))
    {
      readIf("COLUMN");
      String columnName = readColumnIdentifier();
      Column column = table.getColumn(columnName);
      if (readIf("RENAME"))
      {
        read("TO");
        AlterTableRenameColumn command = new AlterTableRenameColumn(this.session);
        
        command.setTable(table);
        command.setColumn(column);
        String newName = readColumnIdentifier();
        command.setNewColumnName(newName);
        return command;
      }
      if (readIf("DROP"))
      {
        if (readIf("DEFAULT"))
        {
          AlterTableAlterColumn command = new AlterTableAlterColumn(this.session, table.getSchema());
          
          command.setTable(table);
          command.setOldColumn(column);
          command.setType(10);
          command.setDefaultExpression(null);
          return command;
        }
        read("NOT");
        read("NULL");
        AlterTableAlterColumn command = new AlterTableAlterColumn(this.session, table.getSchema());
        
        command.setTable(table);
        command.setOldColumn(column);
        command.setType(9);
        return command;
      }
      if (readIf("TYPE")) {
        return parseAlterTableAlterColumnType(table, columnName, column);
      }
      if (readIf("SET"))
      {
        if (readIf("DATA"))
        {
          read("TYPE");
          return parseAlterTableAlterColumnType(table, columnName, column);
        }
        AlterTableAlterColumn command = new AlterTableAlterColumn(this.session, table.getSchema());
        
        command.setTable(table);
        command.setOldColumn(column);
        if (readIf("NULL"))
        {
          command.setType(9);
          return command;
        }
        if (readIf("NOT"))
        {
          read("NULL");
          command.setType(8);
          return command;
        }
        if (readIf("DEFAULT"))
        {
          Expression defaultExpression = readExpression();
          command.setType(10);
          command.setDefaultExpression(defaultExpression);
          return command;
        }
      }
      else
      {
        if (readIf("RESTART"))
        {
          readIf("WITH");
          Expression start = readExpression();
          AlterSequence command = new AlterSequence(this.session, table.getSchema());
          
          command.setColumn(column);
          command.setStartWith(start);
          return command;
        }
        if (readIf("SELECTIVITY"))
        {
          AlterTableAlterColumn command = new AlterTableAlterColumn(this.session, table.getSchema());
          
          command.setTable(table);
          command.setType(13);
          command.setOldColumn(column);
          command.setSelectivity(readExpression());
          return command;
        }
        return parseAlterTableAlterColumnType(table, columnName, column);
      }
    }
    throw getSyntaxError();
  }
  
  private AlterTableAlterColumn parseAlterTableAlterColumnType(Table table, String columnName, Column column)
  {
    Column newColumn = parseColumnForTable(columnName, column.isNullable());
    AlterTableAlterColumn command = new AlterTableAlterColumn(this.session, table.getSchema());
    
    command.setTable(table);
    command.setType(11);
    command.setOldColumn(column);
    command.setNewColumn(newColumn);
    return command;
  }
  
  private AlterTableAlterColumn parseAlterTableAddColumn(Table table)
  {
    readIf("COLUMN");
    Schema schema = table.getSchema();
    AlterTableAlterColumn command = new AlterTableAlterColumn(this.session, schema);
    
    command.setType(7);
    command.setTable(table);
    ArrayList<Column> columnsToAdd = New.arrayList();
    if (readIf("("))
    {
      command.setIfNotExists(false);
      do
      {
        String columnName = readColumnIdentifier();
        Column column = parseColumnForTable(columnName, true);
        columnsToAdd.add(column);
      } while (readIf(","));
      read(")");
      command.setNewColumns(columnsToAdd);
    }
    else
    {
      boolean ifNotExists = readIfNoExists();
      command.setIfNotExists(ifNotExists);
      String columnName = readColumnIdentifier();
      Column column = parseColumnForTable(columnName, true);
      columnsToAdd.add(column);
      if (readIf("BEFORE")) {
        command.setAddBefore(readColumnIdentifier());
      } else if (readIf("AFTER")) {
        command.setAddAfter(readColumnIdentifier());
      }
    }
    command.setNewColumns(columnsToAdd);
    return command;
  }
  
  private int parseAction()
  {
    Integer result = parseCascadeOrRestrict();
    if (result != null) {
      return result.intValue();
    }
    if (readIf("NO"))
    {
      read("ACTION");
      return 0;
    }
    read("SET");
    if (readIf("NULL")) {
      return 3;
    }
    read("DEFAULT");
    return 2;
  }
  
  private Integer parseCascadeOrRestrict()
  {
    if (readIf("CASCADE")) {
      return Integer.valueOf(1);
    }
    if (readIf("RESTRICT")) {
      return Integer.valueOf(0);
    }
    return null;
  }
  
  private DefineCommand parseAlterTableAddConstraintIf(String tableName, Schema schema)
  {
    String constraintName = null;String comment = null;
    boolean ifNotExists = false;
    boolean allowIndexDefinition = this.database.getMode().indexDefinitionInCreateTable;
    if (readIf("CONSTRAINT"))
    {
      ifNotExists = readIfNoExists();
      constraintName = readIdentifierWithSchema(schema.getName());
      checkSchema(schema);
      comment = readCommentIf();
      allowIndexDefinition = true;
    }
    if (readIf("PRIMARY"))
    {
      read("KEY");
      AlterTableAddConstraint command = new AlterTableAddConstraint(this.session, schema, ifNotExists);
      
      command.setType(6);
      command.setComment(comment);
      command.setConstraintName(constraintName);
      command.setTableName(tableName);
      if (readIf("HASH")) {
        command.setPrimaryKeyHash(true);
      }
      read("(");
      command.setIndexColumns(parseIndexColumnList());
      if (readIf("INDEX"))
      {
        String indexName = readIdentifierWithSchema();
        command.setIndex(getSchema().findIndex(this.session, indexName));
      }
      return command;
    }
    if ((allowIndexDefinition) && ((isToken("INDEX")) || (isToken("KEY"))))
    {
      int start = this.lastParseIndex;
      read();
      if (DataType.getTypeByName(this.currentToken) != null)
      {
        this.parseIndex = start;
        read();
        return null;
      }
      CreateIndex command = new CreateIndex(this.session, schema);
      command.setComment(comment);
      command.setTableName(tableName);
      if (!readIf("("))
      {
        command.setIndexName(readUniqueIdentifier());
        read("(");
      }
      command.setIndexColumns(parseIndexColumnList());
      if (readIf("USING")) {
        read("BTREE");
      }
      return command;
    }
    if (readIf("CHECK"))
    {
      AlterTableAddConstraint command = new AlterTableAddConstraint(this.session, schema, ifNotExists);
      command.setType(3);
      command.setCheckExpression(readExpression());
    }
    else if (readIf("UNIQUE"))
    {
      readIf("KEY");
      readIf("INDEX");
      AlterTableAddConstraint command = new AlterTableAddConstraint(this.session, schema, ifNotExists);
      command.setType(4);
      if (!readIf("("))
      {
        constraintName = readUniqueIdentifier();
        read("(");
      }
      command.setIndexColumns(parseIndexColumnList());
      if (readIf("INDEX"))
      {
        String indexName = readIdentifierWithSchema();
        command.setIndex(getSchema().findIndex(this.session, indexName));
      }
      if (readIf("USING")) {
        read("BTREE");
      }
    }
    else if (readIf("FOREIGN"))
    {
      AlterTableAddConstraint command = new AlterTableAddConstraint(this.session, schema, ifNotExists);
      command.setType(5);
      read("KEY");
      read("(");
      command.setIndexColumns(parseIndexColumnList());
      if (readIf("INDEX"))
      {
        String indexName = readIdentifierWithSchema();
        command.setIndex(schema.findIndex(this.session, indexName));
      }
      read("REFERENCES");
      parseReferences(command, schema, tableName);
    }
    else
    {
      if (constraintName != null) {
        throw getSyntaxError();
      }
      return null;
    }
    AlterTableAddConstraint command;
    if (readIf("NOCHECK"))
    {
      command.setCheckExisting(false);
    }
    else
    {
      readIf("CHECK");
      command.setCheckExisting(true);
    }
    command.setTableName(tableName);
    command.setConstraintName(constraintName);
    command.setComment(comment);
    return command;
  }
  
  private void parseReferences(AlterTableAddConstraint command, Schema schema, String tableName)
  {
    if (readIf("("))
    {
      command.setRefTableName(schema, tableName);
      command.setRefIndexColumns(parseIndexColumnList());
    }
    else
    {
      String refTableName = readIdentifierWithSchema(schema.getName());
      command.setRefTableName(getSchema(), refTableName);
      if (readIf("(")) {
        command.setRefIndexColumns(parseIndexColumnList());
      }
    }
    if (readIf("INDEX"))
    {
      String indexName = readIdentifierWithSchema();
      command.setRefIndex(getSchema().findIndex(this.session, indexName));
    }
    while (readIf("ON")) {
      if (readIf("DELETE"))
      {
        command.setDeleteAction(parseAction());
      }
      else
      {
        read("UPDATE");
        command.setUpdateAction(parseAction());
      }
    }
    if (readIf("NOT")) {
      read("DEFERRABLE");
    } else {
      readIf("DEFERRABLE");
    }
  }
  
  private CreateLinkedTable parseCreateLinkedTable(boolean temp, boolean globalTemp, boolean force)
  {
    read("TABLE");
    boolean ifNotExists = readIfNoExists();
    String tableName = readIdentifierWithSchema();
    CreateLinkedTable command = new CreateLinkedTable(this.session, getSchema());
    command.setTemporary(temp);
    command.setGlobalTemporary(globalTemp);
    command.setForce(force);
    command.setIfNotExists(ifNotExists);
    command.setTableName(tableName);
    command.setComment(readCommentIf());
    read("(");
    command.setDriver(readString());
    read(",");
    command.setUrl(readString());
    read(",");
    command.setUser(readString());
    read(",");
    command.setPassword(readString());
    read(",");
    String originalTable = readString();
    if (readIf(","))
    {
      command.setOriginalSchema(originalTable);
      originalTable = readString();
    }
    command.setOriginalTable(originalTable);
    read(")");
    if (readIf("EMIT"))
    {
      read("UPDATES");
      command.setEmitUpdates(true);
    }
    else if (readIf("READONLY"))
    {
      command.setReadOnly(true);
    }
    return command;
  }
  
  private CreateTable parseCreateTable(boolean temp, boolean globalTemp, boolean persistIndexes)
  {
    boolean ifNotExists = readIfNoExists();
    String tableName = readIdentifierWithSchema();
    if ((temp) && (globalTemp) && (equalsToken("SESSION", this.schemaName)))
    {
      this.schemaName = this.session.getCurrentSchemaName();
      globalTemp = false;
    }
    Schema schema = getSchema();
    CreateTable command = new CreateTable(this.session, schema);
    command.setPersistIndexes(persistIndexes);
    command.setTemporary(temp);
    command.setGlobalTemporary(globalTemp);
    command.setIfNotExists(ifNotExists);
    command.setTableName(tableName);
    command.setComment(readCommentIf());
    if ((readIf("(")) && 
      (!readIf(")"))) {
      do
      {
        DefineCommand c = parseAlterTableAddConstraintIf(tableName, schema);
        if (c != null)
        {
          command.addConstraintCommand(c);
        }
        else
        {
          String columnName = readColumnIdentifier();
          Column column = parseColumnForTable(columnName, true);
          if ((column.isAutoIncrement()) && (column.isPrimaryKey()))
          {
            column.setPrimaryKey(false);
            IndexColumn[] cols = { new IndexColumn() };
            cols[0].columnName = column.getName();
            AlterTableAddConstraint pk = new AlterTableAddConstraint(this.session, schema, false);
            
            pk.setType(6);
            pk.setTableName(tableName);
            pk.setIndexColumns(cols);
            command.addConstraintCommand(pk);
          }
          command.addColumn(column);
          String constraintName = null;
          if (readIf("CONSTRAINT")) {
            constraintName = readColumnIdentifier();
          }
          if (readIf("PRIMARY"))
          {
            read("KEY");
            boolean hash = readIf("HASH");
            IndexColumn[] cols = { new IndexColumn() };
            cols[0].columnName = column.getName();
            AlterTableAddConstraint pk = new AlterTableAddConstraint(this.session, schema, false);
            
            pk.setPrimaryKeyHash(hash);
            pk.setType(6);
            pk.setTableName(tableName);
            pk.setIndexColumns(cols);
            command.addConstraintCommand(pk);
            if (readIf("AUTO_INCREMENT")) {
              parseAutoIncrement(column);
            }
          }
          else if (readIf("UNIQUE"))
          {
            AlterTableAddConstraint unique = new AlterTableAddConstraint(this.session, schema, false);
            
            unique.setConstraintName(constraintName);
            unique.setType(4);
            IndexColumn[] cols = { new IndexColumn() };
            cols[0].columnName = columnName;
            unique.setIndexColumns(cols);
            unique.setTableName(tableName);
            command.addConstraintCommand(unique);
          }
          if (readIf("NOT"))
          {
            read("NULL");
            column.setNullable(false);
          }
          else
          {
            readIf("NULL");
          }
          if (readIf("CHECK"))
          {
            Expression expr = readExpression();
            column.addCheckConstraint(this.session, expr);
          }
          if (readIf("REFERENCES"))
          {
            AlterTableAddConstraint ref = new AlterTableAddConstraint(this.session, schema, false);
            
            ref.setConstraintName(constraintName);
            ref.setType(5);
            IndexColumn[] cols = { new IndexColumn() };
            cols[0].columnName = columnName;
            ref.setIndexColumns(cols);
            ref.setTableName(tableName);
            parseReferences(ref, schema, tableName);
            command.addConstraintCommand(ref);
          }
        }
      } while (readIfMore());
    }
    if ((readIf("COMMENT")) && 
      (readIf("="))) {
      readString();
    }
    if (readIf("ENGINE"))
    {
      if (readIf("="))
      {
        String tableEngine = readUniqueIdentifier();
        if (!"InnoDb".equalsIgnoreCase(tableEngine)) {
          if (!"MyISAM".equalsIgnoreCase(tableEngine)) {
            throw DbException.getUnsupportedException(tableEngine);
          }
        }
      }
      else
      {
        command.setTableEngine(readUniqueIdentifier());
        if (readIf("WITH"))
        {
          ArrayList<String> tableEngineParams = New.arrayList();
          do
          {
            tableEngineParams.add(readUniqueIdentifier());
          } while (readIf(","));
          command.setTableEngineParams(tableEngineParams);
        }
      }
    }
    else if (this.database.getSettings().defaultTableEngine != null) {
      command.setTableEngine(this.database.getSettings().defaultTableEngine);
    }
    if (readIf("AUTO_INCREMENT"))
    {
      read("=");
      if ((this.currentTokenType != 5) || (this.currentValue.getType() != 4)) {
        throw DbException.getSyntaxError(this.sqlCommand, this.parseIndex, "integer");
      }
      read();
    }
    readIf("DEFAULT");
    if (readIf("CHARSET"))
    {
      read("=");
      read("UTF8");
    }
    if (temp)
    {
      if (readIf("ON"))
      {
        read("COMMIT");
        if (readIf("DROP"))
        {
          command.setOnCommitDrop();
        }
        else if (readIf("DELETE"))
        {
          read("ROWS");
          command.setOnCommitTruncate();
        }
      }
      else if (readIf("NOT"))
      {
        if (readIf("PERSISTENT")) {
          command.setPersistData(false);
        } else {
          read("LOGGED");
        }
      }
      if (readIf("TRANSACTIONAL")) {
        command.setTransactional(true);
      }
    }
    else if ((!persistIndexes) && (readIf("NOT")))
    {
      read("PERSISTENT");
      command.setPersistData(false);
    }
    if (readIf("HIDDEN")) {
      command.setHidden(true);
    }
    if (readIf("AS"))
    {
      if (readIf("SORTED")) {
        command.setSortedInsertMode(true);
      }
      command.setQuery(parseSelect());
    }
    if ((readIf("ROW_FORMAT")) && 
      (readIf("="))) {
      readColumnIdentifier();
    }
    return command;
  }
  
  private static int getCompareType(int tokenType)
  {
    switch (tokenType)
    {
    case 6: 
      return 0;
    case 7: 
      return 1;
    case 8: 
      return 2;
    case 9: 
      return 4;
    case 10: 
      return 3;
    case 11: 
      return 5;
    case 25: 
      return 11;
    }
    return -1;
  }
  
  public static String quoteIdentifier(String s)
  {
    if ((s == null) || (s.length() == 0)) {
      return "\"\"";
    }
    char c = s.charAt(0);
    if (((!Character.isLetter(c)) && (c != '_')) || (Character.isLowerCase(c))) {
      return StringUtils.quoteIdentifier(s);
    }
    int i = 1;
    for (int length = s.length(); i < length; i++)
    {
      c = s.charAt(i);
      if (((!Character.isLetterOrDigit(c)) && (c != '_')) || (Character.isLowerCase(c))) {
        return StringUtils.quoteIdentifier(s);
      }
    }
    if (isKeyword(s, true)) {
      return StringUtils.quoteIdentifier(s);
    }
    return s;
  }
  
  public void setRightsChecked(boolean rightsChecked)
  {
    this.rightsChecked = rightsChecked;
  }
  
  public Expression parseExpression(String sql)
  {
    this.parameters = New.arrayList();
    initialize(sql);
    read();
    return readExpression();
  }
  
  public Table parseTableName(String sql)
  {
    this.parameters = New.arrayList();
    initialize(sql);
    read();
    return readTableOrView();
  }
}
