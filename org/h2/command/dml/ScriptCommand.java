package org.h2.command.dml;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import org.h2.command.Parser;
import org.h2.constraint.Constraint;
import org.h2.engine.Comment;
import org.h2.engine.Constants;
import org.h2.engine.Database;
import org.h2.engine.Right;
import org.h2.engine.Role;
import org.h2.engine.Session;
import org.h2.engine.Setting;
import org.h2.engine.SysProperties;
import org.h2.engine.User;
import org.h2.engine.UserAggregate;
import org.h2.engine.UserDataType;
import org.h2.expression.Expression;
import org.h2.expression.ExpressionColumn;
import org.h2.index.Cursor;
import org.h2.index.Index;
import org.h2.index.IndexType;
import org.h2.message.DbException;
import org.h2.result.LocalResult;
import org.h2.result.ResultInterface;
import org.h2.result.Row;
import org.h2.schema.Constant;
import org.h2.schema.Schema;
import org.h2.schema.SchemaObject;
import org.h2.schema.Sequence;
import org.h2.schema.TriggerObject;
import org.h2.table.Column;
import org.h2.table.PlanItem;
import org.h2.table.Table;
import org.h2.util.IOUtils;
import org.h2.util.MathUtils;
import org.h2.util.StatementBuilder;
import org.h2.util.StringUtils;
import org.h2.util.Utils;
import org.h2.value.Value;
import org.h2.value.ValueString;

public class ScriptCommand
  extends ScriptBase
{
  private Charset charset = Constants.UTF8;
  private Set<String> schemaNames;
  private Collection<Table> tables;
  private boolean passwords;
  private boolean data;
  private boolean settings;
  private boolean drop;
  private boolean simple;
  private LocalResult result;
  private String lineSeparatorString;
  private byte[] lineSeparator;
  private byte[] buffer;
  private boolean tempLobTableCreated;
  private int nextLobId;
  private int lobBlockSize = 4096;
  
  public ScriptCommand(Session session)
  {
    super(session);
  }
  
  public boolean isQuery()
  {
    return true;
  }
  
  public void setSchemaNames(Set<String> schemaNames)
  {
    this.schemaNames = schemaNames;
  }
  
  public void setTables(Collection<Table> tables)
  {
    this.tables = tables;
  }
  
  public void setData(boolean data)
  {
    this.data = data;
  }
  
  public void setPasswords(boolean passwords)
  {
    this.passwords = passwords;
  }
  
  public void setSettings(boolean settings)
  {
    this.settings = settings;
  }
  
  public void setLobBlockSize(long blockSize)
  {
    this.lobBlockSize = MathUtils.convertLongToInt(blockSize);
  }
  
  public void setDrop(boolean drop)
  {
    this.drop = drop;
  }
  
  public ResultInterface queryMeta()
  {
    LocalResult r = createResult();
    r.done();
    return r;
  }
  
  private LocalResult createResult()
  {
    Expression[] expressions = { new ExpressionColumn(this.session.getDatabase(), new Column("SCRIPT", 13)) };
    
    return new LocalResult(this.session, expressions, 1);
  }
  
  public ResultInterface query(int maxrows)
  {
    this.session.getUser().checkAdmin();
    reset();
    Database db = this.session.getDatabase();
    if (this.schemaNames != null) {
      for (String schemaName : this.schemaNames)
      {
        Schema schema = db.findSchema(schemaName);
        if (schema == null) {
          throw DbException.get(90079, schemaName);
        }
      }
    }
    try
    {
      this.result = createResult();
      deleteStore();
      openOutput();
      if (this.out != null) {
        this.buffer = new byte['က'];
      }
      if (this.settings) {
        for (Setting setting : db.getAllSettings()) {
          if (!setting.getName().equals(SetTypes.getTypeName(34))) {
            add(setting.getCreateSQL(), false);
          }
        }
      }
      if (this.out != null) {
        add("", true);
      }
      for (User user : db.getAllUsers()) {
        add(user.getCreateSQL(this.passwords), false);
      }
      for (Role role : db.getAllRoles()) {
        add(role.getCreateSQL(true), false);
      }
      for (Schema schema : db.getAllSchemas()) {
        if (!excludeSchema(schema)) {
          add(schema.getCreateSQL(), false);
        }
      }
      for (UserDataType datatype : db.getAllUserDataTypes())
      {
        if (this.drop) {
          add(datatype.getDropSQL(), false);
        }
        add(datatype.getCreateSQL(), false);
      }
      for (SchemaObject obj : db.getAllSchemaObjects(11)) {
        if (!excludeSchema(obj.getSchema()))
        {
          Constant constant = (Constant)obj;
          add(constant.getCreateSQL(), false);
        }
      }
      ArrayList<Table> tables = db.getAllTablesAndViews(false);
      
      Collections.sort(tables, new Comparator()
      {
        public int compare(Table t1, Table t2)
        {
          return t1.getId() - t2.getId();
        }
      });
      for (Table table : tables) {
        if ((!excludeSchema(table.getSchema())) && 
        
          (!excludeTable(table)) && 
          
          (!table.isHidden()))
        {
          table.lock(this.session, false, false);
          String sql = table.getCreateSQL();
          if (sql != null) {
            if (this.drop) {
              add(table.getDropSQL(), false);
            }
          }
        }
      }
      for (SchemaObject obj : db.getAllSchemaObjects(9)) {
        if (!excludeSchema(obj.getSchema()))
        {
          if (this.drop) {
            add(obj.getDropSQL(), false);
          }
          add(obj.getCreateSQL(), false);
        }
      }
      for (UserAggregate agg : db.getAllAggregates())
      {
        if (this.drop) {
          add(agg.getDropSQL(), false);
        }
        add(agg.getCreateSQL(), false);
      }
      for (SchemaObject obj : db.getAllSchemaObjects(3)) {
        if (!excludeSchema(obj.getSchema()))
        {
          Sequence sequence = (Sequence)obj;
          if (this.drop) {
            add(sequence.getDropSQL(), false);
          }
          add(sequence.getCreateSQL(), false);
        }
      }
      int count = 0;
      for (Table table : tables) {
        if ((!excludeSchema(table.getSchema())) && 
        
          (!excludeTable(table)) && 
          
          (!table.isHidden()))
        {
          table.lock(this.session, false, false);
          String createTableSql = table.getCreateSQL();
          if (createTableSql != null)
          {
            String tableType = table.getTableType();
            add(createTableSql, false);
            ArrayList<Constraint> constraints = table.getConstraints();
            if (constraints != null) {
              for (Constraint constraint : constraints) {
                if ("PRIMARY KEY".equals(constraint.getConstraintType())) {
                  add(constraint.getCreateSQLWithoutIndexes(), false);
                }
              }
            }
            if ("TABLE".equals(tableType))
            {
              if (table.canGetRowCount())
              {
                String rowcount = "-- " + table.getRowCountApproximation() + " +/- SELECT COUNT(*) FROM " + table.getSQL();
                
                add(rowcount, false);
              }
              if (this.data) {
                count = generateInsertValues(count, table);
              }
            }
            ArrayList<Index> indexes = table.getIndexes();
            for (int j = 0; (indexes != null) && (j < indexes.size()); j++)
            {
              Index index = (Index)indexes.get(j);
              if (!index.getIndexType().getBelongsToConstraint()) {
                add(index.getCreateSQL(), false);
              }
            }
          }
        }
      }
      if (this.tempLobTableCreated)
      {
        add("DROP TABLE IF EXISTS SYSTEM_LOB_STREAM", true);
        add("CALL SYSTEM_COMBINE_BLOB(-1)", true);
        add("DROP ALIAS IF EXISTS SYSTEM_COMBINE_CLOB", true);
        add("DROP ALIAS IF EXISTS SYSTEM_COMBINE_BLOB", true);
        this.tempLobTableCreated = false;
      }
      ArrayList<SchemaObject> constraints = db.getAllSchemaObjects(5);
      
      Collections.sort(constraints, new Comparator()
      {
        public int compare(SchemaObject c1, SchemaObject c2)
        {
          return ((Constraint)c1).compareTo((Constraint)c2);
        }
      });
      for (SchemaObject obj : constraints) {
        if (!excludeSchema(obj.getSchema()))
        {
          Constraint constraint = (Constraint)obj;
          if ((!excludeTable(constraint.getTable())) && 
          
            (!constraint.getTable().isHidden())) {
            if (!"PRIMARY KEY".equals(constraint.getConstraintType())) {
              add(constraint.getCreateSQLWithoutIndexes(), false);
            }
          }
        }
      }
      for (SchemaObject obj : db.getAllSchemaObjects(4)) {
        if (!excludeSchema(obj.getSchema()))
        {
          TriggerObject trigger = (TriggerObject)obj;
          if (!excludeTable(trigger.getTable())) {
            add(trigger.getCreateSQL(), false);
          }
        }
      }
      for (Right right : db.getAllRights())
      {
        Table table = right.getGrantedTable();
        if ((table == null) || (
          (!excludeSchema(table.getSchema())) && 
          
          (!excludeTable(table)))) {
          add(right.getCreateSQL(), false);
        }
      }
      for (Comment comment : db.getAllComments()) {
        add(comment.getCreateSQL(), false);
      }
      if (this.out != null) {
        this.out.close();
      }
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, getFileName());
    }
    finally
    {
      closeIO();
    }
    this.result.done();
    LocalResult r = this.result;
    reset();
    return r;
  }
  
  private int generateInsertValues(int count, Table table)
    throws IOException
  {
    PlanItem plan = table.getBestPlanItem(this.session, null, null, null);
    Index index = plan.getIndex();
    Cursor cursor = index.find(this.session, null, null);
    Column[] columns = table.getColumns();
    StatementBuilder buff = new StatementBuilder("INSERT INTO ");
    buff.append(table.getSQL()).append('(');
    for (Column col : columns)
    {
      buff.appendExceptFirst(", ");
      buff.append(Parser.quoteIdentifier(col.getName()));
    }
    buff.append(") VALUES");
    if (!this.simple) {
      buff.append('\n');
    }
    buff.append('(');
    String ins = buff.toString();
    buff = null;
    while (cursor.next())
    {
      Row row = cursor.get();
      if (buff == null) {
        buff = new StatementBuilder(ins);
      } else {
        buff.append(",\n(");
      }
      for (int j = 0; j < row.getColumnCount(); j++)
      {
        if (j > 0) {
          buff.append(", ");
        }
        Value v = row.getValue(j);
        if (v.getPrecision() > this.lobBlockSize)
        {
          if (v.getType() == 16)
          {
            int id = writeLobStream(v);
            buff.append("SYSTEM_COMBINE_CLOB(" + id + ")");
          }
          else if (v.getType() == 15)
          {
            int id = writeLobStream(v);
            buff.append("SYSTEM_COMBINE_BLOB(" + id + ")");
          }
          else
          {
            buff.append(v.getSQL());
          }
        }
        else {
          buff.append(v.getSQL());
        }
      }
      buff.append(')');
      count++;
      if ((count & 0x7F) == 0) {
        checkCanceled();
      }
      if ((this.simple) || (buff.length() > 4096))
      {
        add(buff.toString(), true);
        buff = null;
      }
    }
    if (buff != null) {
      add(buff.toString(), true);
    }
    return count;
  }
  
  private int writeLobStream(Value v)
    throws IOException
  {
    if (!this.tempLobTableCreated)
    {
      add("CREATE TABLE IF NOT EXISTS SYSTEM_LOB_STREAM(ID INT NOT NULL, PART INT NOT NULL, CDATA VARCHAR, BDATA BINARY)", true);
      
      add("CREATE PRIMARY KEY SYSTEM_LOB_STREAM_PRIMARY_KEY ON SYSTEM_LOB_STREAM(ID, PART)", true);
      
      add("CREATE ALIAS IF NOT EXISTS SYSTEM_COMBINE_CLOB FOR \"" + getClass().getName() + ".combineClob\"", true);
      
      add("CREATE ALIAS IF NOT EXISTS SYSTEM_COMBINE_BLOB FOR \"" + getClass().getName() + ".combineBlob\"", true);
      
      this.tempLobTableCreated = true;
    }
    int id = this.nextLobId++;
    switch (v.getType())
    {
    case 15: 
      byte[] bytes = new byte[this.lobBlockSize];
      InputStream input = v.getInputStream();
      try
      {
        for (int i = 0;; i++)
        {
          StringBuilder buff = new StringBuilder(this.lobBlockSize * 2);
          buff.append("INSERT INTO SYSTEM_LOB_STREAM VALUES(" + id + ", " + i + ", NULL, '");
          
          int len = IOUtils.readFully(input, bytes, this.lobBlockSize);
          if (len <= 0) {
            break;
          }
          buff.append(StringUtils.convertBytesToHex(bytes, len)).append("')");
          String sql = buff.toString();
          add(sql, true);
        }
      }
      finally
      {
        IOUtils.closeSilently(input);
      }
      break;
    case 16: 
      char[] chars = new char[this.lobBlockSize];
      Reader reader = v.getReader();
      try
      {
        for (int i = 0;; i++)
        {
          StringBuilder buff = new StringBuilder(this.lobBlockSize * 2);
          buff.append("INSERT INTO SYSTEM_LOB_STREAM VALUES(" + id + ", " + i + ", ");
          int len = IOUtils.readFully(reader, chars, this.lobBlockSize);
          if (len == 0) {
            break;
          }
          buff.append(StringUtils.quoteStringSQL(new String(chars, 0, len))).append(", NULL)");
          
          String sql = buff.toString();
          add(sql, true);
        }
      }
      finally
      {
        IOUtils.closeSilently(reader);
      }
      break;
    default: 
      DbException.throwInternalError("type:" + v.getType());
    }
    return id;
  }
  
  public static InputStream combineBlob(Connection conn, int id)
    throws SQLException
  {
    if (id < 0) {
      return null;
    }
    ResultSet rs = getLobStream(conn, "BDATA", id);
    new InputStream()
    {
      private InputStream current;
      private boolean closed;
      
      /* Error */
      public int read()
        throws IOException
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 33	org/h2/command/dml/ScriptCommand$3:current	Ljava/io/InputStream;
        //   4: ifnonnull +59 -> 63
        //   7: aload_0
        //   8: getfield 35	org/h2/command/dml/ScriptCommand$3:closed	Z
        //   11: ifeq +5 -> 16
        //   14: iconst_m1
        //   15: ireturn
        //   16: aload_0
        //   17: getfield 20	org/h2/command/dml/ScriptCommand$3:val$rs	Ljava/sql/ResultSet;
        //   20: invokeinterface 41 1 0
        //   25: ifne +9 -> 34
        //   28: aload_0
        //   29: invokevirtual 44	org/h2/command/dml/ScriptCommand$3:close	()V
        //   32: iconst_m1
        //   33: ireturn
        //   34: aload_0
        //   35: aload_0
        //   36: getfield 20	org/h2/command/dml/ScriptCommand$3:val$rs	Ljava/sql/ResultSet;
        //   39: iconst_1
        //   40: invokeinterface 48 2 0
        //   45: putfield 33	org/h2/command/dml/ScriptCommand$3:current	Ljava/io/InputStream;
        //   48: aload_0
        //   49: new 50	java/io/BufferedInputStream
        //   52: dup
        //   53: aload_0
        //   54: getfield 33	org/h2/command/dml/ScriptCommand$3:current	Ljava/io/InputStream;
        //   57: invokespecial 53	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
        //   60: putfield 33	org/h2/command/dml/ScriptCommand$3:current	Ljava/io/InputStream;
        //   63: aload_0
        //   64: getfield 33	org/h2/command/dml/ScriptCommand$3:current	Ljava/io/InputStream;
        //   67: invokevirtual 55	java/io/InputStream:read	()I
        //   70: istore_1
        //   71: iload_1
        //   72: iflt +5 -> 77
        //   75: iload_1
        //   76: ireturn
        //   77: aload_0
        //   78: aconst_null
        //   79: putfield 33	org/h2/command/dml/ScriptCommand$3:current	Ljava/io/InputStream;
        //   82: goto -82 -> 0
        //   85: astore_1
        //   86: aload_1
        //   87: invokestatic 61	org/h2/message/DbException:convertToIOException	(Ljava/lang/Throwable;)Ljava/io/IOException;
        //   90: athrow
        // Line number table:
        //   Java source line #531	-> byte code offset #0
        //   Java source line #532	-> byte code offset #7
        //   Java source line #533	-> byte code offset #14
        //   Java source line #535	-> byte code offset #16
        //   Java source line #536	-> byte code offset #28
        //   Java source line #537	-> byte code offset #32
        //   Java source line #539	-> byte code offset #34
        //   Java source line #540	-> byte code offset #48
        //   Java source line #542	-> byte code offset #63
        //   Java source line #543	-> byte code offset #71
        //   Java source line #544	-> byte code offset #75
        //   Java source line #546	-> byte code offset #77
        //   Java source line #549	-> byte code offset #82
        //   Java source line #547	-> byte code offset #85
        //   Java source line #548	-> byte code offset #86
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	91	0	this	3
        //   70	6	1	x	int
        //   85	2	1	e	SQLException
        // Exception table:
        //   from	to	target	type
        //   0	15	85	java/sql/SQLException
        //   16	33	85	java/sql/SQLException
        //   34	76	85	java/sql/SQLException
        //   77	82	85	java/sql/SQLException
      }
      
      public void close()
        throws IOException
      {
        if (this.closed) {
          return;
        }
        this.closed = true;
        try
        {
          this.val$rs.close();
        }
        catch (SQLException e)
        {
          throw DbException.convertToIOException(e);
        }
      }
    };
  }
  
  public static Reader combineClob(Connection conn, int id)
    throws SQLException
  {
    if (id < 0) {
      return null;
    }
    ResultSet rs = getLobStream(conn, "CDATA", id);
    new Reader()
    {
      private Reader current;
      private boolean closed;
      
      /* Error */
      public int read()
        throws IOException
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 33	org/h2/command/dml/ScriptCommand$4:current	Ljava/io/Reader;
        //   4: ifnonnull +59 -> 63
        //   7: aload_0
        //   8: getfield 35	org/h2/command/dml/ScriptCommand$4:closed	Z
        //   11: ifeq +5 -> 16
        //   14: iconst_m1
        //   15: ireturn
        //   16: aload_0
        //   17: getfield 20	org/h2/command/dml/ScriptCommand$4:val$rs	Ljava/sql/ResultSet;
        //   20: invokeinterface 41 1 0
        //   25: ifne +9 -> 34
        //   28: aload_0
        //   29: invokevirtual 44	org/h2/command/dml/ScriptCommand$4:close	()V
        //   32: iconst_m1
        //   33: ireturn
        //   34: aload_0
        //   35: aload_0
        //   36: getfield 20	org/h2/command/dml/ScriptCommand$4:val$rs	Ljava/sql/ResultSet;
        //   39: iconst_1
        //   40: invokeinterface 48 2 0
        //   45: putfield 33	org/h2/command/dml/ScriptCommand$4:current	Ljava/io/Reader;
        //   48: aload_0
        //   49: new 50	java/io/BufferedReader
        //   52: dup
        //   53: aload_0
        //   54: getfield 33	org/h2/command/dml/ScriptCommand$4:current	Ljava/io/Reader;
        //   57: invokespecial 53	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
        //   60: putfield 33	org/h2/command/dml/ScriptCommand$4:current	Ljava/io/Reader;
        //   63: aload_0
        //   64: getfield 33	org/h2/command/dml/ScriptCommand$4:current	Ljava/io/Reader;
        //   67: invokevirtual 55	java/io/Reader:read	()I
        //   70: istore_1
        //   71: iload_1
        //   72: iflt +5 -> 77
        //   75: iload_1
        //   76: ireturn
        //   77: aload_0
        //   78: aconst_null
        //   79: putfield 33	org/h2/command/dml/ScriptCommand$4:current	Ljava/io/Reader;
        //   82: goto -82 -> 0
        //   85: astore_1
        //   86: aload_1
        //   87: invokestatic 61	org/h2/message/DbException:convertToIOException	(Ljava/lang/Throwable;)Ljava/io/IOException;
        //   90: athrow
        // Line number table:
        //   Java source line #587	-> byte code offset #0
        //   Java source line #588	-> byte code offset #7
        //   Java source line #589	-> byte code offset #14
        //   Java source line #591	-> byte code offset #16
        //   Java source line #592	-> byte code offset #28
        //   Java source line #593	-> byte code offset #32
        //   Java source line #595	-> byte code offset #34
        //   Java source line #596	-> byte code offset #48
        //   Java source line #598	-> byte code offset #63
        //   Java source line #599	-> byte code offset #71
        //   Java source line #600	-> byte code offset #75
        //   Java source line #602	-> byte code offset #77
        //   Java source line #605	-> byte code offset #82
        //   Java source line #603	-> byte code offset #85
        //   Java source line #604	-> byte code offset #86
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	91	0	this	4
        //   70	6	1	x	int
        //   85	2	1	e	SQLException
        // Exception table:
        //   from	to	target	type
        //   0	15	85	java/sql/SQLException
        //   16	33	85	java/sql/SQLException
        //   34	76	85	java/sql/SQLException
        //   77	82	85	java/sql/SQLException
      }
      
      public void close()
        throws IOException
      {
        if (this.closed) {
          return;
        }
        this.closed = true;
        try
        {
          this.val$rs.close();
        }
        catch (SQLException e)
        {
          throw DbException.convertToIOException(e);
        }
      }
      
      public int read(char[] buffer, int off, int len)
        throws IOException
      {
        if (len == 0) {
          return 0;
        }
        int c = read();
        if (c == -1) {
          return -1;
        }
        buffer[off] = ((char)c);
        for (int i = 1; i < len; i++)
        {
          c = read();
          if (c == -1) {
            break;
          }
          buffer[(off + i)] = ((char)c);
        }
        return i;
      }
    };
  }
  
  private static ResultSet getLobStream(Connection conn, String column, int id)
    throws SQLException
  {
    PreparedStatement prep = conn.prepareStatement("SELECT " + column + " FROM SYSTEM_LOB_STREAM WHERE ID=? ORDER BY PART");
    
    prep.setInt(1, id);
    return prep.executeQuery();
  }
  
  private void reset()
  {
    this.result = null;
    this.buffer = null;
    this.lineSeparatorString = SysProperties.LINE_SEPARATOR;
    this.lineSeparator = this.lineSeparatorString.getBytes(this.charset);
  }
  
  private boolean excludeSchema(Schema schema)
  {
    if ((this.schemaNames != null) && (!this.schemaNames.contains(schema.getName()))) {
      return true;
    }
    if (this.tables != null)
    {
      for (Table table : schema.getAllTablesAndViews()) {
        if (this.tables.contains(table)) {
          return false;
        }
      }
      return true;
    }
    return false;
  }
  
  private boolean excludeTable(Table table)
  {
    return (this.tables != null) && (!this.tables.contains(table));
  }
  
  private void add(String s, boolean insert)
    throws IOException
  {
    if (s == null) {
      return;
    }
    if ((this.lineSeparator.length > 1) || (this.lineSeparator[0] != 10)) {
      s = StringUtils.replaceAll(s, "\n", this.lineSeparatorString);
    }
    s = s + ";";
    if (this.out != null)
    {
      byte[] buff = s.getBytes(this.charset);
      int len = MathUtils.roundUpInt(buff.length + this.lineSeparator.length, 16);
      
      this.buffer = Utils.copy(buff, this.buffer);
      if (len > this.buffer.length) {
        this.buffer = new byte[len];
      }
      System.arraycopy(buff, 0, this.buffer, 0, buff.length);
      for (int i = buff.length; i < len - this.lineSeparator.length; i++) {
        this.buffer[i] = 32;
      }
      int j = 0;
      for (int i = len - this.lineSeparator.length; i < len; j++)
      {
        this.buffer[i] = this.lineSeparator[j];i++;
      }
      this.out.write(this.buffer, 0, len);
      if (!insert)
      {
        Value[] row = { ValueString.get(s) };
        this.result.addRow(row);
      }
    }
    else
    {
      Value[] row = { ValueString.get(s) };
      this.result.addRow(row);
    }
  }
  
  public void setSimple(boolean simple)
  {
    this.simple = simple;
  }
  
  public void setCharset(Charset charset)
  {
    this.charset = charset;
  }
  
  public int getType()
  {
    return 65;
  }
}
