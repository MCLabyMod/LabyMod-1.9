package org.h2.expression;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.PatternSyntaxException;
import org.h2.command.Command;
import org.h2.command.Parser;
import org.h2.engine.Constants;
import org.h2.engine.Database;
import org.h2.engine.DbSettings;
import org.h2.engine.Mode;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.message.DbException;
import org.h2.mvstore.DataUtils;
import org.h2.schema.Schema;
import org.h2.schema.Sequence;
import org.h2.security.BlockCipher;
import org.h2.security.CipherFactory;
import org.h2.security.SHA256;
import org.h2.store.LobStorageInterface;
import org.h2.store.fs.FileUtils;
import org.h2.table.Column;
import org.h2.table.ColumnResolver;
import org.h2.table.LinkSchema;
import org.h2.table.Table;
import org.h2.table.TableFilter;
import org.h2.tools.CompressTool;
import org.h2.tools.Csv;
import org.h2.util.AutoCloseInputStream;
import org.h2.util.DateTimeUtils;
import org.h2.util.JdbcUtils;
import org.h2.util.MathUtils;
import org.h2.util.New;
import org.h2.util.StatementBuilder;
import org.h2.util.StringUtils;
import org.h2.util.ToChar;
import org.h2.util.Utils;
import org.h2.value.DataType;
import org.h2.value.Value;
import org.h2.value.ValueArray;
import org.h2.value.ValueBoolean;
import org.h2.value.ValueBytes;
import org.h2.value.ValueDate;
import org.h2.value.ValueDouble;
import org.h2.value.ValueInt;
import org.h2.value.ValueLong;
import org.h2.value.ValueNull;
import org.h2.value.ValueResultSet;
import org.h2.value.ValueString;
import org.h2.value.ValueTime;
import org.h2.value.ValueTimestamp;
import org.h2.value.ValueUuid;

public class Function
  extends Expression
  implements FunctionCall
{
  public static final int ABS = 0;
  public static final int ACOS = 1;
  public static final int ASIN = 2;
  public static final int ATAN = 3;
  public static final int ATAN2 = 4;
  public static final int BITAND = 5;
  public static final int BITOR = 6;
  public static final int BITXOR = 7;
  public static final int CEILING = 8;
  public static final int COS = 9;
  public static final int COT = 10;
  public static final int DEGREES = 11;
  public static final int EXP = 12;
  public static final int FLOOR = 13;
  public static final int LOG = 14;
  public static final int LOG10 = 15;
  public static final int MOD = 16;
  public static final int PI = 17;
  public static final int POWER = 18;
  public static final int RADIANS = 19;
  public static final int RAND = 20;
  public static final int ROUND = 21;
  public static final int ROUNDMAGIC = 22;
  public static final int SIGN = 23;
  public static final int SIN = 24;
  public static final int SQRT = 25;
  public static final int TAN = 26;
  public static final int TRUNCATE = 27;
  public static final int SECURE_RAND = 28;
  public static final int HASH = 29;
  public static final int ENCRYPT = 30;
  public static final int DECRYPT = 31;
  public static final int COMPRESS = 32;
  public static final int EXPAND = 33;
  public static final int ZERO = 34;
  public static final int RANDOM_UUID = 35;
  public static final int COSH = 36;
  public static final int SINH = 37;
  public static final int TANH = 38;
  public static final int LN = 39;
  public static final int ASCII = 50;
  public static final int BIT_LENGTH = 51;
  public static final int CHAR = 52;
  public static final int CHAR_LENGTH = 53;
  public static final int CONCAT = 54;
  public static final int DIFFERENCE = 55;
  public static final int HEXTORAW = 56;
  public static final int INSERT = 57;
  public static final int INSTR = 58;
  public static final int LCASE = 59;
  public static final int LEFT = 60;
  public static final int LENGTH = 61;
  public static final int LOCATE = 62;
  public static final int LTRIM = 63;
  public static final int OCTET_LENGTH = 64;
  public static final int RAWTOHEX = 65;
  public static final int REPEAT = 66;
  public static final int REPLACE = 67;
  public static final int RIGHT = 68;
  public static final int RTRIM = 69;
  public static final int SOUNDEX = 70;
  public static final int SPACE = 71;
  public static final int SUBSTR = 72;
  public static final int SUBSTRING = 73;
  public static final int UCASE = 74;
  public static final int LOWER = 75;
  public static final int UPPER = 76;
  public static final int POSITION = 77;
  public static final int TRIM = 78;
  public static final int STRINGENCODE = 79;
  public static final int STRINGDECODE = 80;
  public static final int STRINGTOUTF8 = 81;
  public static final int UTF8TOSTRING = 82;
  public static final int XMLATTR = 83;
  public static final int XMLNODE = 84;
  public static final int XMLCOMMENT = 85;
  public static final int XMLCDATA = 86;
  public static final int XMLSTARTDOC = 87;
  public static final int XMLTEXT = 88;
  public static final int REGEXP_REPLACE = 89;
  public static final int RPAD = 90;
  public static final int LPAD = 91;
  public static final int CONCAT_WS = 92;
  public static final int TO_CHAR = 93;
  public static final int TRANSLATE = 94;
  public static final int CURDATE = 100;
  public static final int CURTIME = 101;
  public static final int DATE_ADD = 102;
  public static final int DATE_DIFF = 103;
  public static final int DAY_NAME = 104;
  public static final int DAY_OF_MONTH = 105;
  public static final int DAY_OF_WEEK = 106;
  public static final int DAY_OF_YEAR = 107;
  public static final int HOUR = 108;
  public static final int MINUTE = 109;
  public static final int MONTH = 110;
  public static final int MONTH_NAME = 111;
  public static final int NOW = 112;
  public static final int QUARTER = 113;
  public static final int SECOND = 114;
  public static final int WEEK = 115;
  public static final int YEAR = 116;
  public static final int CURRENT_DATE = 117;
  public static final int CURRENT_TIME = 118;
  public static final int CURRENT_TIMESTAMP = 119;
  public static final int EXTRACT = 120;
  public static final int FORMATDATETIME = 121;
  public static final int PARSEDATETIME = 122;
  public static final int ISO_YEAR = 123;
  public static final int ISO_WEEK = 124;
  public static final int ISO_DAY_OF_WEEK = 125;
  public static final int DATABASE = 150;
  public static final int USER = 151;
  public static final int CURRENT_USER = 152;
  public static final int IDENTITY = 153;
  public static final int SCOPE_IDENTITY = 154;
  public static final int AUTOCOMMIT = 155;
  public static final int READONLY = 156;
  public static final int DATABASE_PATH = 157;
  public static final int LOCK_TIMEOUT = 158;
  public static final int DISK_SPACE_USED = 159;
  public static final int IFNULL = 200;
  public static final int CASEWHEN = 201;
  public static final int CONVERT = 202;
  public static final int CAST = 203;
  public static final int COALESCE = 204;
  public static final int NULLIF = 205;
  public static final int CASE = 206;
  public static final int NEXTVAL = 207;
  public static final int CURRVAL = 208;
  public static final int ARRAY_GET = 209;
  public static final int CSVREAD = 210;
  public static final int CSVWRITE = 211;
  public static final int MEMORY_FREE = 212;
  public static final int MEMORY_USED = 213;
  public static final int LOCK_MODE = 214;
  public static final int SCHEMA = 215;
  public static final int SESSION_ID = 216;
  public static final int ARRAY_LENGTH = 217;
  public static final int LINK_SCHEMA = 218;
  public static final int GREATEST = 219;
  public static final int LEAST = 220;
  public static final int CANCEL_SESSION = 221;
  public static final int SET = 222;
  public static final int TABLE = 223;
  public static final int TABLE_DISTINCT = 224;
  public static final int FILE_READ = 225;
  public static final int TRANSACTION_ID = 226;
  public static final int TRUNCATE_VALUE = 227;
  public static final int NVL2 = 228;
  public static final int DECODE = 229;
  public static final int ARRAY_CONTAINS = 230;
  public static final int VALUES = 250;
  public static final int H2VERSION = 231;
  public static final int ROW_NUMBER = 300;
  private static final int VAR_ARGS = -1;
  private static final long PRECISION_UNKNOWN = -1L;
  private static final HashMap<String, FunctionInfo> FUNCTIONS = ;
  private static final HashMap<String, Integer> DATE_PART = New.hashMap();
  private static final char[] SOUNDEX_INDEX = new char[''];
  protected Expression[] args;
  private final FunctionInfo info;
  private ArrayList<Expression> varArgs;
  private int dataType;
  private int scale;
  private long precision = -1L;
  private int displaySize;
  private final Database database;
  
  static
  {
    DATE_PART.put("SQL_TSI_YEAR", Integer.valueOf(1));
    DATE_PART.put("YEAR", Integer.valueOf(1));
    DATE_PART.put("YYYY", Integer.valueOf(1));
    DATE_PART.put("YY", Integer.valueOf(1));
    DATE_PART.put("SQL_TSI_MONTH", Integer.valueOf(2));
    DATE_PART.put("MONTH", Integer.valueOf(2));
    DATE_PART.put("MM", Integer.valueOf(2));
    DATE_PART.put("M", Integer.valueOf(2));
    DATE_PART.put("SQL_TSI_WEEK", Integer.valueOf(3));
    DATE_PART.put("WW", Integer.valueOf(3));
    DATE_PART.put("WK", Integer.valueOf(3));
    DATE_PART.put("WEEK", Integer.valueOf(3));
    DATE_PART.put("DAY", Integer.valueOf(5));
    DATE_PART.put("DD", Integer.valueOf(5));
    DATE_PART.put("D", Integer.valueOf(5));
    DATE_PART.put("SQL_TSI_DAY", Integer.valueOf(5));
    DATE_PART.put("DAYOFYEAR", Integer.valueOf(6));
    DATE_PART.put("DAY_OF_YEAR", Integer.valueOf(6));
    DATE_PART.put("DY", Integer.valueOf(6));
    DATE_PART.put("DOY", Integer.valueOf(6));
    DATE_PART.put("SQL_TSI_HOUR", Integer.valueOf(11));
    DATE_PART.put("HOUR", Integer.valueOf(11));
    DATE_PART.put("HH", Integer.valueOf(11));
    DATE_PART.put("SQL_TSI_MINUTE", Integer.valueOf(12));
    DATE_PART.put("MINUTE", Integer.valueOf(12));
    DATE_PART.put("MI", Integer.valueOf(12));
    DATE_PART.put("N", Integer.valueOf(12));
    DATE_PART.put("SQL_TSI_SECOND", Integer.valueOf(13));
    DATE_PART.put("SECOND", Integer.valueOf(13));
    DATE_PART.put("SS", Integer.valueOf(13));
    DATE_PART.put("S", Integer.valueOf(13));
    DATE_PART.put("MILLISECOND", Integer.valueOf(14));
    DATE_PART.put("MS", Integer.valueOf(14));
    
    String index = "7AEIOUY8HW1BFPV2CGJKQSXZ3DT4L5MN6R";
    char number = '\000';
    int i = 0;
    for (int length = index.length(); i < length; i++)
    {
      char c = index.charAt(i);
      if (c < '9')
      {
        number = c;
      }
      else
      {
        SOUNDEX_INDEX[c] = number;
        SOUNDEX_INDEX[Character.toLowerCase(c)] = number;
      }
    }
    addFunction("ABS", 0, 1, 0);
    addFunction("ACOS", 1, 1, 7);
    addFunction("ASIN", 2, 1, 7);
    addFunction("ATAN", 3, 1, 7);
    addFunction("ATAN2", 4, 2, 7);
    addFunction("BITAND", 5, 2, 5);
    addFunction("BITOR", 6, 2, 5);
    addFunction("BITXOR", 7, 2, 5);
    addFunction("CEILING", 8, 1, 7);
    addFunction("CEIL", 8, 1, 7);
    addFunction("COS", 9, 1, 7);
    addFunction("COSH", 36, 1, 7);
    addFunction("COT", 10, 1, 7);
    addFunction("DEGREES", 11, 1, 7);
    addFunction("EXP", 12, 1, 7);
    addFunction("FLOOR", 13, 1, 7);
    addFunction("LOG", 14, 1, 7);
    addFunction("LN", 39, 1, 7);
    addFunction("LOG10", 15, 1, 7);
    addFunction("MOD", 16, 2, 5);
    addFunction("PI", 17, 0, 7);
    addFunction("POWER", 18, 2, 7);
    addFunction("RADIANS", 19, 1, 7);
    
    addFunctionNotDeterministic("RAND", 20, -1, 7);
    addFunctionNotDeterministic("RANDOM", 20, -1, 7);
    addFunction("ROUND", 21, -1, 7);
    addFunction("ROUNDMAGIC", 22, 1, 7);
    addFunction("SIGN", 23, 1, 4);
    addFunction("SIN", 24, 1, 7);
    addFunction("SINH", 37, 1, 7);
    addFunction("SQRT", 25, 1, 7);
    addFunction("TAN", 26, 1, 7);
    addFunction("TANH", 38, 1, 7);
    addFunction("TRUNCATE", 27, -1, 0);
    
    addFunction("TRUNC", 27, -1, 0);
    addFunction("HASH", 29, 3, 12);
    addFunction("ENCRYPT", 30, 3, 12);
    addFunction("DECRYPT", 31, 3, 12);
    addFunctionNotDeterministic("SECURE_RAND", 28, 1, 12);
    addFunction("COMPRESS", 32, -1, 12);
    addFunction("EXPAND", 33, 1, 12);
    addFunction("ZERO", 34, 0, 4);
    addFunctionNotDeterministic("RANDOM_UUID", 35, 0, 20);
    addFunctionNotDeterministic("SYS_GUID", 35, 0, 20);
    
    addFunction("ASCII", 50, 1, 4);
    addFunction("BIT_LENGTH", 51, 1, 5);
    addFunction("CHAR", 52, 1, 13);
    addFunction("CHR", 52, 1, 13);
    addFunction("CHAR_LENGTH", 53, 1, 4);
    
    addFunction("CHARACTER_LENGTH", 53, 1, 4);
    addFunctionWithNull("CONCAT", 54, -1, 13);
    addFunctionWithNull("CONCAT_WS", 92, -1, 13);
    addFunction("DIFFERENCE", 55, 2, 4);
    addFunction("HEXTORAW", 56, 1, 13);
    addFunctionWithNull("INSERT", 57, 4, 13);
    addFunction("LCASE", 59, 1, 13);
    addFunction("LEFT", 60, 2, 13);
    addFunction("LENGTH", 61, 1, 5);
    
    addFunction("LOCATE", 62, -1, 4);
    
    addFunction("CHARINDEX", 62, -1, 4);
    
    addFunction("POSITION", 62, 2, 4);
    addFunction("INSTR", 58, -1, 4);
    addFunction("LTRIM", 63, -1, 13);
    addFunction("OCTET_LENGTH", 64, 1, 5);
    addFunction("RAWTOHEX", 65, 1, 13);
    addFunction("REPEAT", 66, 2, 13);
    addFunction("REPLACE", 67, -1, 13);
    addFunction("RIGHT", 68, 2, 13);
    addFunction("RTRIM", 69, -1, 13);
    addFunction("SOUNDEX", 70, 1, 13);
    addFunction("SPACE", 71, 1, 13);
    addFunction("SUBSTR", 72, -1, 13);
    addFunction("SUBSTRING", 73, -1, 13);
    addFunction("UCASE", 74, 1, 13);
    addFunction("LOWER", 75, 1, 13);
    addFunction("UPPER", 76, 1, 13);
    addFunction("POSITION", 77, 2, 4);
    addFunction("TRIM", 78, -1, 13);
    addFunction("STRINGENCODE", 79, 1, 13);
    addFunction("STRINGDECODE", 80, 1, 13);
    addFunction("STRINGTOUTF8", 81, 1, 12);
    addFunction("UTF8TOSTRING", 82, 1, 13);
    addFunction("XMLATTR", 83, 2, 13);
    addFunctionWithNull("XMLNODE", 84, -1, 13);
    addFunction("XMLCOMMENT", 85, 1, 13);
    addFunction("XMLCDATA", 86, 1, 13);
    addFunction("XMLSTARTDOC", 87, 0, 13);
    addFunction("XMLTEXT", 88, -1, 13);
    addFunction("REGEXP_REPLACE", 89, 3, 13);
    addFunction("RPAD", 90, -1, 13);
    addFunction("LPAD", 91, -1, 13);
    addFunction("TO_CHAR", 93, -1, 13);
    addFunction("TRANSLATE", 94, 3, 13);
    
    addFunctionNotDeterministic("CURRENT_DATE", 117, 0, 10);
    
    addFunctionNotDeterministic("CURDATE", 100, 0, 10);
    
    addFunctionNotDeterministic("GETDATE", 100, 0, 10);
    
    addFunctionNotDeterministic("CURRENT_TIME", 118, 0, 9);
    
    addFunctionNotDeterministic("CURTIME", 101, 0, 9);
    
    addFunctionNotDeterministic("CURRENT_TIMESTAMP", 119, -1, 11);
    
    addFunctionNotDeterministic("NOW", 112, -1, 11);
    
    addFunction("DATEADD", 102, 3, 11);
    
    addFunction("TIMESTAMPADD", 102, 3, 5);
    
    addFunction("DATEDIFF", 103, 3, 5);
    
    addFunction("TIMESTAMPDIFF", 103, 3, 5);
    
    addFunction("DAYNAME", 104, 1, 13);
    
    addFunction("DAYNAME", 104, 1, 13);
    
    addFunction("DAY", 105, 1, 4);
    
    addFunction("DAY_OF_MONTH", 105, 1, 4);
    
    addFunction("DAY_OF_WEEK", 106, 1, 4);
    
    addFunction("DAY_OF_YEAR", 107, 1, 4);
    
    addFunction("DAYOFMONTH", 105, 1, 4);
    
    addFunction("DAYOFWEEK", 106, 1, 4);
    
    addFunction("DAYOFYEAR", 107, 1, 4);
    
    addFunction("HOUR", 108, 1, 4);
    
    addFunction("MINUTE", 109, 1, 4);
    
    addFunction("MONTH", 110, 1, 4);
    
    addFunction("MONTHNAME", 111, 1, 13);
    
    addFunction("QUARTER", 113, 1, 4);
    
    addFunction("SECOND", 114, 1, 4);
    
    addFunction("WEEK", 115, 1, 4);
    
    addFunction("YEAR", 116, 1, 4);
    
    addFunction("EXTRACT", 120, 2, 4);
    
    addFunctionWithNull("FORMATDATETIME", 121, -1, 13);
    
    addFunctionWithNull("PARSEDATETIME", 122, -1, 11);
    
    addFunction("ISO_YEAR", 123, 1, 4);
    
    addFunction("ISO_WEEK", 124, 1, 4);
    
    addFunction("ISO_DAY_OF_WEEK", 125, 1, 4);
    
    addFunctionNotDeterministic("DATABASE", 150, 0, 13);
    
    addFunctionNotDeterministic("USER", 151, 0, 13);
    
    addFunctionNotDeterministic("CURRENT_USER", 152, 0, 13);
    
    addFunctionNotDeterministic("IDENTITY", 153, 0, 5);
    
    addFunctionNotDeterministic("SCOPE_IDENTITY", 154, 0, 5);
    
    addFunctionNotDeterministic("IDENTITY_VAL_LOCAL", 153, 0, 5);
    
    addFunctionNotDeterministic("LAST_INSERT_ID", 153, 0, 5);
    
    addFunctionNotDeterministic("LASTVAL", 153, 0, 5);
    
    addFunctionNotDeterministic("AUTOCOMMIT", 155, 0, 1);
    
    addFunctionNotDeterministic("READONLY", 156, 0, 1);
    
    addFunction("DATABASE_PATH", 157, 0, 13);
    
    addFunctionNotDeterministic("LOCK_TIMEOUT", 158, 0, 4);
    
    addFunctionWithNull("IFNULL", 200, 2, 0);
    
    addFunctionWithNull("ISNULL", 200, 2, 0);
    
    addFunctionWithNull("CASEWHEN", 201, 3, 0);
    
    addFunctionWithNull("CONVERT", 202, 1, 0);
    
    addFunctionWithNull("CAST", 203, 1, 0);
    
    addFunctionWithNull("TRUNCATE_VALUE", 227, 3, 0);
    
    addFunctionWithNull("COALESCE", 204, -1, 0);
    
    addFunctionWithNull("NVL", 204, -1, 0);
    
    addFunctionWithNull("NVL2", 228, 3, 0);
    
    addFunctionWithNull("NULLIF", 205, 2, 0);
    
    addFunctionWithNull("CASE", 206, -1, 0);
    
    addFunctionNotDeterministic("NEXTVAL", 207, -1, 5);
    
    addFunctionNotDeterministic("CURRVAL", 208, -1, 5);
    
    addFunction("ARRAY_GET", 209, 2, 13);
    
    addFunction("ARRAY_CONTAINS", 230, 2, 1, false, true, true);
    
    addFunction("CSVREAD", 210, -1, 18, false, false, false);
    
    addFunction("CSVWRITE", 211, -1, 4, false, false, true);
    
    addFunctionNotDeterministic("MEMORY_FREE", 212, 0, 4);
    
    addFunctionNotDeterministic("MEMORY_USED", 213, 0, 4);
    
    addFunctionNotDeterministic("LOCK_MODE", 214, 0, 4);
    
    addFunctionNotDeterministic("SCHEMA", 215, 0, 13);
    
    addFunctionNotDeterministic("SESSION_ID", 216, 0, 4);
    
    addFunction("ARRAY_LENGTH", 217, 1, 4);
    
    addFunctionNotDeterministic("LINK_SCHEMA", 218, 6, 18);
    
    addFunctionWithNull("LEAST", 220, -1, 0);
    
    addFunctionWithNull("GREATEST", 219, -1, 0);
    
    addFunctionNotDeterministic("CANCEL_SESSION", 221, 1, 1);
    
    addFunction("SET", 222, 2, 0, false, false, true);
    
    addFunction("FILE_READ", 225, -1, 0, false, false, true);
    
    addFunctionNotDeterministic("TRANSACTION_ID", 226, 0, 13);
    
    addFunctionWithNull("DECODE", 229, -1, 0);
    
    addFunctionNotDeterministic("DISK_SPACE_USED", 159, 1, 5);
    
    addFunction("H2VERSION", 231, 0, 13);
    
    addFunctionWithNull("TABLE", 223, -1, 18);
    
    addFunctionWithNull("TABLE_DISTINCT", 224, -1, 18);
    
    addFunctionWithNull("ROW_NUMBER", 300, 0, 5);
    
    addFunction("VALUES", 250, 1, 0, false, true, false);
  }
  
  protected Function(Database database, FunctionInfo info)
  {
    this.database = database;
    this.info = info;
    if (info.parameterCount == -1) {
      this.varArgs = New.arrayList();
    } else {
      this.args = new Expression[info.parameterCount];
    }
  }
  
  private static void addFunction(String name, int type, int parameterCount, int dataType, boolean nullIfParameterIsNull, boolean deterministic, boolean bufferResultSetToLocalTemp)
  {
    FunctionInfo info = new FunctionInfo();
    info.name = name;
    info.type = type;
    info.parameterCount = parameterCount;
    info.dataType = dataType;
    info.nullIfParameterIsNull = nullIfParameterIsNull;
    info.deterministic = deterministic;
    info.bufferResultSetToLocalTemp = bufferResultSetToLocalTemp;
    FUNCTIONS.put(name, info);
  }
  
  private static void addFunctionNotDeterministic(String name, int type, int parameterCount, int dataType)
  {
    addFunction(name, type, parameterCount, dataType, true, false, true);
  }
  
  private static void addFunction(String name, int type, int parameterCount, int dataType)
  {
    addFunction(name, type, parameterCount, dataType, true, true, true);
  }
  
  private static void addFunctionWithNull(String name, int type, int parameterCount, int dataType)
  {
    addFunction(name, type, parameterCount, dataType, false, true, true);
  }
  
  private static FunctionInfo getFunctionInfo(String name)
  {
    return (FunctionInfo)FUNCTIONS.get(name);
  }
  
  public static Function getFunction(Database database, String name)
  {
    if (!database.getSettings().databaseToUpper) {
      name = StringUtils.toUpperEnglish(name);
    }
    FunctionInfo info = getFunctionInfo(name);
    if (info == null) {
      return null;
    }
    switch (info.type)
    {
    case 223: 
    case 224: 
      return new TableFunction(database, info, Long.MAX_VALUE);
    }
    return new Function(database, info);
  }
  
  public void setParameter(int index, Expression param)
  {
    if (this.varArgs != null)
    {
      this.varArgs.add(param);
    }
    else
    {
      if (index >= this.args.length) {
        throw DbException.get(7001, new String[] { this.info.name, "" + this.args.length });
      }
      this.args[index] = param;
    }
  }
  
  private static strictfp double log10(double value)
  {
    return roundMagic(StrictMath.log(value) / StrictMath.log(10.0D));
  }
  
  public Value getValue(Session session)
  {
    return getValueWithArgs(session, this.args);
  }
  
  private Value getSimpleValue(Session session, Value v0, Expression[] args, Value[] values)
  {
    Value result;
    Value result;
    Value result;
    Value result;
    Value result;
    switch (this.info.type)
    {
    case 0: 
      result = v0.getSignum() > 0 ? v0 : v0.negate();
      break;
    case 1: 
      result = ValueDouble.get(Math.acos(v0.getDouble()));
      break;
    case 2: 
      result = ValueDouble.get(Math.asin(v0.getDouble()));
      break;
    case 3: 
      result = ValueDouble.get(Math.atan(v0.getDouble()));
      break;
    case 8: 
      result = ValueDouble.get(Math.ceil(v0.getDouble()));
      break;
    case 9: 
      result = ValueDouble.get(Math.cos(v0.getDouble()));
      break;
    case 36: 
      result = ValueDouble.get(Math.cosh(v0.getDouble()));
      break;
    case 10: 
      double d = Math.tan(v0.getDouble());
      if (d == 0.0D) {
        throw DbException.get(22012, getSQL());
      }
      result = ValueDouble.get(1.0D / d);
      break;
    case 11: 
      result = ValueDouble.get(Math.toDegrees(v0.getDouble()));
      break;
    case 12: 
      result = ValueDouble.get(Math.exp(v0.getDouble()));
      break;
    case 13: 
      result = ValueDouble.get(Math.floor(v0.getDouble()));
      break;
    case 39: 
      result = ValueDouble.get(Math.log(v0.getDouble()));
      break;
    case 14: 
      if (this.database.getMode().logIsLogBase10) {
        result = ValueDouble.get(Math.log10(v0.getDouble()));
      } else {
        result = ValueDouble.get(Math.log(v0.getDouble()));
      }
      break;
    case 15: 
      result = ValueDouble.get(log10(v0.getDouble()));
      break;
    case 17: 
      result = ValueDouble.get(3.141592653589793D);
      break;
    case 19: 
      result = ValueDouble.get(Math.toRadians(v0.getDouble()));
      break;
    case 20: 
      if (v0 != null) {
        session.getRandom().setSeed(v0.getInt());
      }
      result = ValueDouble.get(session.getRandom().nextDouble());
      break;
    case 22: 
      result = ValueDouble.get(roundMagic(v0.getDouble()));
      break;
    case 23: 
      result = ValueInt.get(v0.getSignum());
      break;
    case 24: 
      result = ValueDouble.get(Math.sin(v0.getDouble()));
      break;
    case 37: 
      result = ValueDouble.get(Math.sinh(v0.getDouble()));
      break;
    case 25: 
      result = ValueDouble.get(Math.sqrt(v0.getDouble()));
      break;
    case 26: 
      result = ValueDouble.get(Math.tan(v0.getDouble()));
      break;
    case 38: 
      result = ValueDouble.get(Math.tanh(v0.getDouble()));
      break;
    case 28: 
      result = ValueBytes.getNoCopy(MathUtils.secureRandomBytes(v0.getInt()));
      
      break;
    case 33: 
      result = ValueBytes.getNoCopy(CompressTool.getInstance().expand(v0.getBytesNoCopy()));
      
      break;
    case 34: 
      result = ValueInt.get(0);
      break;
    case 35: 
      result = ValueUuid.getNewRandom();
      break;
    case 50: 
      String s = v0.getString();
      if (s.length() == 0) {
        result = ValueNull.INSTANCE;
      } else {
        result = ValueInt.get(s.charAt(0));
      }
      break;
    case 51: 
      result = ValueLong.get(16L * length(v0));
      break;
    case 52: 
      result = ValueString.get(String.valueOf((char)v0.getInt()), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 53: 
    case 61: 
      result = ValueLong.get(length(v0));
      break;
    case 64: 
      result = ValueLong.get(2L * length(v0));
      break;
    case 54: 
    case 92: 
      result = ValueNull.INSTANCE;
      int start = 0;
      String separator = "";
      if (this.info.type == 92)
      {
        start = 1;
        separator = getNullOrValue(session, args, values, 0).getString();
      }
      for (int i = start; i < args.length; i++)
      {
        Value v = getNullOrValue(session, args, values, i);
        if (v != ValueNull.INSTANCE) {
          if (result == ValueNull.INSTANCE)
          {
            result = v;
          }
          else
          {
            String tmp = v.getString();
            if ((!StringUtils.isNullOrEmpty(separator)) && (!StringUtils.isNullOrEmpty(tmp))) {
              tmp = separator.concat(tmp);
            }
            result = ValueString.get(result.getString().concat(tmp), this.database.getMode().treatEmptyStringsAsNull);
          }
        }
      }
      if ((this.info.type == 92) && 
        (separator != null) && (result == ValueNull.INSTANCE)) {
        result = ValueString.get("", this.database.getMode().treatEmptyStringsAsNull);
      }
      break;
    case 56: 
      result = ValueString.get(hexToRaw(v0.getString()), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 59: 
    case 75: 
      result = ValueString.get(v0.getString().toLowerCase(), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 65: 
      result = ValueString.get(rawToHex(v0.getString()), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 70: 
      result = ValueString.get(getSoundex(v0.getString()), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 71: 
      int len = Math.max(0, v0.getInt());
      char[] chars = new char[len];
      for (int i = len - 1; i >= 0; i--) {
        chars[i] = ' ';
      }
      result = ValueString.get(new String(chars), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 74: 
    case 76: 
      result = ValueString.get(v0.getString().toUpperCase(), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 79: 
      result = ValueString.get(StringUtils.javaEncode(v0.getString()), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 80: 
      result = ValueString.get(StringUtils.javaDecode(v0.getString()), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 81: 
      result = ValueBytes.getNoCopy(v0.getString().getBytes(Constants.UTF8));
      
      break;
    case 82: 
      result = ValueString.get(new String(v0.getBytesNoCopy(), Constants.UTF8), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 85: 
      result = ValueString.get(StringUtils.xmlComment(v0.getString()), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 86: 
      result = ValueString.get(StringUtils.xmlCData(v0.getString()), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 87: 
      result = ValueString.get(StringUtils.xmlStartDoc(), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 104: 
      SimpleDateFormat dayName = new SimpleDateFormat("EEEE", Locale.ENGLISH);
      
      result = ValueString.get(dayName.format(v0.getDate()), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 105: 
      result = ValueInt.get(DateTimeUtils.getDatePart(v0.getDate(), 5));
      
      break;
    case 106: 
      result = ValueInt.get(DateTimeUtils.getDatePart(v0.getDate(), 7));
      
      break;
    case 107: 
      result = ValueInt.get(DateTimeUtils.getDatePart(v0.getDate(), 6));
      
      break;
    case 108: 
      result = ValueInt.get(DateTimeUtils.getDatePart(v0.getTimestamp(), 11));
      
      break;
    case 109: 
      result = ValueInt.get(DateTimeUtils.getDatePart(v0.getTimestamp(), 12));
      
      break;
    case 110: 
      result = ValueInt.get(DateTimeUtils.getDatePart(v0.getDate(), 2));
      
      break;
    case 111: 
      SimpleDateFormat monthName = new SimpleDateFormat("MMMM", Locale.ENGLISH);
      
      result = ValueString.get(monthName.format(v0.getDate()), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 113: 
      result = ValueInt.get((DateTimeUtils.getDatePart(v0.getDate(), 2) - 1) / 3 + 1);
      
      break;
    case 114: 
      result = ValueInt.get(DateTimeUtils.getDatePart(v0.getTimestamp(), 13));
      
      break;
    case 115: 
      result = ValueInt.get(DateTimeUtils.getDatePart(v0.getDate(), 3));
      
      break;
    case 116: 
      result = ValueInt.get(DateTimeUtils.getDatePart(v0.getDate(), 1));
      
      break;
    case 123: 
      result = ValueInt.get(DateTimeUtils.getIsoYear(v0.getDate()));
      break;
    case 124: 
      result = ValueInt.get(DateTimeUtils.getIsoWeek(v0.getDate()));
      break;
    case 125: 
      result = ValueInt.get(DateTimeUtils.getIsoDayOfWeek(v0.getDate()));
      break;
    case 100: 
    case 117: 
      long now = session.getTransactionStart();
      
      result = ValueDate.fromMillis(now);
      break;
    case 101: 
    case 118: 
      long now = session.getTransactionStart();
      
      result = ValueTime.fromMillis(now);
      break;
    case 112: 
    case 119: 
      long now = session.getTransactionStart();
      ValueTimestamp vt = ValueTimestamp.fromMillis(now);
      if (v0 != null)
      {
        Mode mode = this.database.getMode();
        vt = (ValueTimestamp)vt.convertScale(mode.convertOnlyToSmallerScale, v0.getInt());
      }
      result = vt;
      break;
    case 150: 
      result = ValueString.get(this.database.getShortName(), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 151: 
    case 152: 
      result = ValueString.get(session.getUser().getName(), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 153: 
      result = session.getLastIdentity();
      break;
    case 154: 
      result = session.getLastScopeIdentity();
      break;
    case 155: 
      result = ValueBoolean.get(session.getAutoCommit());
      break;
    case 156: 
      result = ValueBoolean.get(this.database.isReadOnly());
      break;
    case 157: 
      String path = this.database.getDatabasePath();
      result = path == null ? ValueNull.INSTANCE : ValueString.get(path, this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 158: 
      result = ValueInt.get(session.getLockTimeout());
      break;
    case 159: 
      result = ValueLong.get(getDiskSpaceUsed(session, v0));
      break;
    case 202: 
    case 203: 
      v0 = v0.convertTo(this.dataType);
      Mode mode = this.database.getMode();
      v0 = v0.convertScale(mode.convertOnlyToSmallerScale, this.scale);
      v0 = v0.convertPrecision(getPrecision(), false);
      result = v0;
      break;
    case 212: 
      session.getUser().checkAdmin();
      result = ValueInt.get(Utils.getMemoryFree());
      break;
    case 213: 
      session.getUser().checkAdmin();
      result = ValueInt.get(Utils.getMemoryUsed());
      break;
    case 214: 
      result = ValueInt.get(this.database.getLockMode());
      break;
    case 215: 
      result = ValueString.get(session.getCurrentSchemaName(), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 216: 
      result = ValueInt.get(session.getId());
      break;
    case 200: 
      result = v0;
      if (v0 == ValueNull.INSTANCE) {
        result = getNullOrValue(session, args, values, 1);
      }
      break;
    case 201: 
      Value v;
      Value v;
      if ((v0 == ValueNull.INSTANCE) || (!v0.getBoolean().booleanValue())) {
        v = getNullOrValue(session, args, values, 2);
      } else {
        v = getNullOrValue(session, args, values, 1);
      }
      result = v.convertTo(this.dataType);
      break;
    case 229: 
      int index = -1;
      int i = 1;
      for (int len = args.length - 1; i < len; i += 2) {
        if (this.database.areEqual(v0, getNullOrValue(session, args, values, i)))
        {
          index = i + 1;
          break;
        }
      }
      if ((index < 0) && (args.length % 2 == 0)) {
        index = args.length - 1;
      }
      Value v = index < 0 ? ValueNull.INSTANCE : getNullOrValue(session, args, values, index);
      
      result = v.convertTo(this.dataType);
      break;
    case 228: 
      Value v;
      Value v;
      if (v0 == ValueNull.INSTANCE) {
        v = getNullOrValue(session, args, values, 2);
      } else {
        v = getNullOrValue(session, args, values, 1);
      }
      result = v.convertTo(this.dataType);
      break;
    case 204: 
      result = v0;
      for (int i = 0; i < args.length; i++)
      {
        Value v = getNullOrValue(session, args, values, i);
        if (v != ValueNull.INSTANCE)
        {
          result = v.convertTo(this.dataType);
          break;
        }
      }
      break;
    case 219: 
    case 220: 
      result = ValueNull.INSTANCE;
      for (int i = 0; i < args.length; i++)
      {
        Value v = getNullOrValue(session, args, values, i);
        if (v != ValueNull.INSTANCE)
        {
          v = v.convertTo(this.dataType);
          if (result == ValueNull.INSTANCE)
          {
            result = v;
          }
          else
          {
            int comp = this.database.compareTypeSave(result, v);
            if ((this.info.type == 219) && (comp < 0)) {
              result = v;
            } else if ((this.info.type == 220) && (comp > 0)) {
              result = v;
            }
          }
        }
      }
      break;
    case 206: 
      Expression then = null;
      if (v0 == null)
      {
        int i = 1;
        for (int len = args.length - 1; i < len; i += 2)
        {
          Value when = args[i].getValue(session);
          if ((when != ValueNull.INSTANCE) && (when.getBoolean().booleanValue()))
          {
            then = args[(i + 1)];
            break;
          }
        }
      }
      else if (v0 != ValueNull.INSTANCE)
      {
        int i = 1;
        for (int len = args.length - 1; i < len; i += 2)
        {
          Value when = args[i].getValue(session);
          if (this.database.areEqual(v0, when))
          {
            then = args[(i + 1)];
            break;
          }
        }
      }
      if ((then == null) && (args.length % 2 == 0)) {
        then = args[(args.length - 1)];
      }
      Value v = then == null ? ValueNull.INSTANCE : then.getValue(session);
      result = v.convertTo(this.dataType);
      break;
    case 209: 
      Value result;
      if (v0.getType() == 17)
      {
        Value v1 = getNullOrValue(session, args, values, 1);
        int element = v1.getInt();
        Value[] list = ((ValueArray)v0).getList();
        if ((element < 1) || (element > list.length)) {
          result = ValueNull.INSTANCE;
        } else {
          result = list[(element - 1)];
        }
      }
      else
      {
        result = ValueNull.INSTANCE;
      }
      break;
    case 217: 
      if (v0.getType() == 17)
      {
        Value[] list = ((ValueArray)v0).getList();
        result = ValueInt.get(list.length);
      }
      else
      {
        result = ValueNull.INSTANCE;
      }
      break;
    case 230: 
      result = ValueBoolean.get(false);
      if (v0.getType() == 17)
      {
        Value v1 = getNullOrValue(session, args, values, 1);
        Value[] list = ((ValueArray)v0).getList();
        for (Value v : list) {
          if (v.equals(v1))
          {
            result = ValueBoolean.get(true);
            break;
          }
        }
      }
      break;
    case 221: 
      result = ValueBoolean.get(cancelStatement(session, v0.getInt()));
      break;
    case 226: 
      result = session.getTransactionId();
      break;
    case 4: 
    case 5: 
    case 6: 
    case 7: 
    case 16: 
    case 18: 
    case 21: 
    case 27: 
    case 29: 
    case 30: 
    case 31: 
    case 32: 
    case 40: 
    case 41: 
    case 42: 
    case 43: 
    case 44: 
    case 45: 
    case 46: 
    case 47: 
    case 48: 
    case 49: 
    case 55: 
    case 57: 
    case 58: 
    case 60: 
    case 62: 
    case 63: 
    case 66: 
    case 67: 
    case 68: 
    case 69: 
    case 72: 
    case 73: 
    case 77: 
    case 78: 
    case 83: 
    case 84: 
    case 88: 
    case 89: 
    case 90: 
    case 91: 
    case 93: 
    case 94: 
    case 95: 
    case 96: 
    case 97: 
    case 98: 
    case 99: 
    case 102: 
    case 103: 
    case 120: 
    case 121: 
    case 122: 
    case 126: 
    case 127: 
    case 128: 
    case 129: 
    case 130: 
    case 131: 
    case 132: 
    case 133: 
    case 134: 
    case 135: 
    case 136: 
    case 137: 
    case 138: 
    case 139: 
    case 140: 
    case 141: 
    case 142: 
    case 143: 
    case 144: 
    case 145: 
    case 146: 
    case 147: 
    case 148: 
    case 149: 
    case 160: 
    case 161: 
    case 162: 
    case 163: 
    case 164: 
    case 165: 
    case 166: 
    case 167: 
    case 168: 
    case 169: 
    case 170: 
    case 171: 
    case 172: 
    case 173: 
    case 174: 
    case 175: 
    case 176: 
    case 177: 
    case 178: 
    case 179: 
    case 180: 
    case 181: 
    case 182: 
    case 183: 
    case 184: 
    case 185: 
    case 186: 
    case 187: 
    case 188: 
    case 189: 
    case 190: 
    case 191: 
    case 192: 
    case 193: 
    case 194: 
    case 195: 
    case 196: 
    case 197: 
    case 198: 
    case 199: 
    case 205: 
    case 207: 
    case 208: 
    case 210: 
    case 211: 
    case 218: 
    case 222: 
    case 223: 
    case 224: 
    case 225: 
    case 227: 
    default: 
      result = null;
    }
    return result;
  }
  
  private static boolean cancelStatement(Session session, int targetSessionId)
  {
    session.getUser().checkAdmin();
    Session[] sessions = session.getDatabase().getSessions(false);
    for (Session s : sessions) {
      if (s.getId() == targetSessionId)
      {
        Command c = s.getCurrentCommand();
        if (c == null) {
          return false;
        }
        c.cancel();
        return true;
      }
    }
    return false;
  }
  
  private static long getDiskSpaceUsed(Session session, Value v0)
  {
    Parser p = new Parser(session);
    String sql = v0.getString();
    Table table = p.parseTableName(sql);
    return table.getDiskSpaceUsed();
  }
  
  private static Value getNullOrValue(Session session, Expression[] args, Value[] values, int i)
  {
    if (i >= args.length) {
      return null;
    }
    Value v = values[i];
    if (v == null)
    {
      Expression e = args[i];
      if (e == null) {
        return null;
      }
      v = values[i] = e.getValue(session);
    }
    return v;
  }
  
  private Value getValueWithArgs(Session session, Expression[] args)
  {
    Value[] values = new Value[args.length];
    if (this.info.nullIfParameterIsNull) {
      for (int i = 0; i < args.length; i++)
      {
        Expression e = args[i];
        Value v = e.getValue(session);
        if (v == ValueNull.INSTANCE) {
          return ValueNull.INSTANCE;
        }
        values[i] = v;
      }
    }
    Value v0 = getNullOrValue(session, args, values, 0);
    Value resultSimple = getSimpleValue(session, v0, args, values);
    if (resultSimple != null) {
      return resultSimple;
    }
    Value v1 = getNullOrValue(session, args, values, 1);
    Value v2 = getNullOrValue(session, args, values, 2);
    Value v3 = getNullOrValue(session, args, values, 3);
    Value v4 = getNullOrValue(session, args, values, 4);
    Value v5 = getNullOrValue(session, args, values, 5);
    Value result;
    Value result;
    Value result;
    Value result;
    Value result;
    Value result;
    Value result;
    switch (this.info.type)
    {
    case 4: 
      result = ValueDouble.get(Math.atan2(v0.getDouble(), v1.getDouble()));
      
      break;
    case 5: 
      result = ValueLong.get(v0.getLong() & v1.getLong());
      break;
    case 6: 
      result = ValueLong.get(v0.getLong() | v1.getLong());
      break;
    case 7: 
      result = ValueLong.get(v0.getLong() ^ v1.getLong());
      break;
    case 16: 
      long x = v1.getLong();
      if (x == 0L) {
        throw DbException.get(22012, getSQL());
      }
      result = ValueLong.get(v0.getLong() % x);
      break;
    case 18: 
      result = ValueDouble.get(Math.pow(v0.getDouble(), v1.getDouble()));
      
      break;
    case 21: 
      double f = v1 == null ? 1.0D : Math.pow(10.0D, v1.getDouble());
      result = ValueDouble.get(Math.round(v0.getDouble() * f) / f);
      break;
    case 27: 
      if (v0.getType() == 11)
      {
        Timestamp d = v0.getTimestamp();
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(11, 0);
        c.set(12, 0);
        c.set(13, 0);
        c.set(14, 0);
        result = ValueTimestamp.fromMillis(c.getTimeInMillis());
      }
      else
      {
        double d = v0.getDouble();
        int p = v1 == null ? 0 : v1.getInt();
        double f = Math.pow(10.0D, p);
        double g = d * f;
        result = ValueDouble.get((d < 0.0D ? Math.ceil(g) : Math.floor(g)) / f);
      }
      break;
    case 29: 
      result = ValueBytes.getNoCopy(getHash(v0.getString(), v1.getBytesNoCopy(), v2.getInt()));
      
      break;
    case 30: 
      result = ValueBytes.getNoCopy(encrypt(v0.getString(), v1.getBytesNoCopy(), v2.getBytesNoCopy()));
      
      break;
    case 31: 
      result = ValueBytes.getNoCopy(decrypt(v0.getString(), v1.getBytesNoCopy(), v2.getBytesNoCopy()));
      
      break;
    case 32: 
      String algorithm = null;
      if (v1 != null) {
        algorithm = v1.getString();
      }
      result = ValueBytes.getNoCopy(CompressTool.getInstance().compress(v0.getBytesNoCopy(), algorithm));
      
      break;
    case 55: 
      result = ValueInt.get(getDifference(v0.getString(), v1.getString()));
      
      break;
    case 57: 
      if ((v1 == ValueNull.INSTANCE) || (v2 == ValueNull.INSTANCE)) {
        result = v1;
      } else {
        result = ValueString.get(insert(v0.getString(), v1.getInt(), v2.getInt(), v3.getString()), this.database.getMode().treatEmptyStringsAsNull);
      }
      break;
    case 60: 
      result = ValueString.get(left(v0.getString(), v1.getInt()), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 62: 
      int start = v2 == null ? 0 : v2.getInt();
      result = ValueInt.get(locate(v0.getString(), v1.getString(), start));
      break;
    case 58: 
      int start = v2 == null ? 0 : v2.getInt();
      result = ValueInt.get(locate(v1.getString(), v0.getString(), start));
      break;
    case 66: 
      int count = Math.max(0, v1.getInt());
      result = ValueString.get(repeat(v0.getString(), count), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 67: 
      String s0 = v0.getString();
      String s1 = v1.getString();
      String s2 = v2 == null ? "" : v2.getString();
      result = ValueString.get(replace(s0, s1, s2), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 68: 
      result = ValueString.get(right(v0.getString(), v1.getInt()), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 63: 
      result = ValueString.get(StringUtils.trim(v0.getString(), true, false, v1 == null ? " " : v1.getString()), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 78: 
      result = ValueString.get(StringUtils.trim(v0.getString(), true, true, v1 == null ? " " : v1.getString()), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 69: 
      result = ValueString.get(StringUtils.trim(v0.getString(), false, true, v1 == null ? " " : v1.getString()), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 72: 
    case 73: 
      String s = v0.getString();
      int offset = v1.getInt();
      if (offset < 0) {
        offset = s.length() + offset + 1;
      }
      int length = v2 == null ? s.length() : v2.getInt();
      result = ValueString.get(substring(s, offset, length), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 77: 
      result = ValueInt.get(locate(v0.getString(), v1.getString(), 0));
      break;
    case 83: 
      result = ValueString.get(StringUtils.xmlAttr(v0.getString(), v1.getString()), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 84: 
      String attr = v1 == ValueNull.INSTANCE ? null : v1 == null ? null : v1.getString();
      
      String content = v2 == ValueNull.INSTANCE ? null : v2 == null ? null : v2.getString();
      
      boolean indent = v3 == null ? true : v3.getBoolean().booleanValue();
      
      result = ValueString.get(StringUtils.xmlNode(v0.getString(), attr, content, indent), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 89: 
      String regexp = v1.getString();
      String replacement = v2.getString();
      try
      {
        result = ValueString.get(v0.getString().replaceAll(regexp, replacement), this.database.getMode().treatEmptyStringsAsNull);
      }
      catch (StringIndexOutOfBoundsException e)
      {
        throw DbException.get(22025, e, new String[] { replacement });
      }
      catch (PatternSyntaxException e)
      {
        throw DbException.get(22025, e, new String[] { regexp });
      }
    case 90: 
      result = ValueString.get(StringUtils.pad(v0.getString(), v1.getInt(), v2 == null ? null : v2.getString(), true), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 91: 
      result = ValueString.get(StringUtils.pad(v0.getString(), v1.getInt(), v2 == null ? null : v2.getString(), false), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 93: 
      switch (v0.getType())
      {
      case 9: 
      case 10: 
      case 11: 
        result = ValueString.get(ToChar.toChar(v0.getTimestamp(), v1 == null ? null : v1.getString(), v2 == null ? null : v2.getString()), this.database.getMode().treatEmptyStringsAsNull);
        
        break;
      case 3: 
      case 4: 
      case 5: 
      case 6: 
      case 7: 
      case 8: 
        result = ValueString.get(ToChar.toChar(v0.getBigDecimal(), v1 == null ? null : v1.getString(), v2 == null ? null : v2.getString()), this.database.getMode().treatEmptyStringsAsNull);
        
        break;
      default: 
        result = ValueString.get(v0.getString(), this.database.getMode().treatEmptyStringsAsNull);
      }
      break;
    case 94: 
      String matching = v1.getString();
      String replacement = v2.getString();
      result = ValueString.get(translate(v0.getString(), matching, replacement), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 231: 
      result = ValueString.get(Constants.getVersion(), this.database.getMode().treatEmptyStringsAsNull);
      
      break;
    case 102: 
      result = ValueTimestamp.get(dateadd(v0.getString(), v1.getLong(), v2.getTimestamp()));
      
      break;
    case 103: 
      result = ValueLong.get(datediff(v0.getString(), v1.getTimestamp(), v2.getTimestamp()));
      
      break;
    case 120: 
      int field = getDatePart(v0.getString());
      result = ValueInt.get(DateTimeUtils.getDatePart(v1.getTimestamp(), field));
      
      break;
    case 121: 
      if ((v0 == ValueNull.INSTANCE) || (v1 == ValueNull.INSTANCE))
      {
        result = ValueNull.INSTANCE;
      }
      else
      {
        String locale = v2 == ValueNull.INSTANCE ? null : v2 == null ? null : v2.getString();
        
        String tz = v3 == ValueNull.INSTANCE ? null : v3 == null ? null : v3.getString();
        
        result = ValueString.get(DateTimeUtils.formatDateTime(v0.getTimestamp(), v1.getString(), locale, tz), this.database.getMode().treatEmptyStringsAsNull);
      }
      break;
    case 122: 
      if ((v0 == ValueNull.INSTANCE) || (v1 == ValueNull.INSTANCE))
      {
        result = ValueNull.INSTANCE;
      }
      else
      {
        String locale = v2 == ValueNull.INSTANCE ? null : v2 == null ? null : v2.getString();
        
        String tz = v3 == ValueNull.INSTANCE ? null : v3 == null ? null : v3.getString();
        
        Date d = DateTimeUtils.parseDateTime(v0.getString(), v1.getString(), locale, tz);
        
        result = ValueTimestamp.fromMillis(d.getTime());
      }
      break;
    case 205: 
      result = this.database.areEqual(v0, v1) ? ValueNull.INSTANCE : v0;
      break;
    case 207: 
      Sequence sequence = getSequence(session, v0, v1);
      SequenceValue value = new SequenceValue(sequence);
      result = value.getValue(session);
      break;
    case 208: 
      Sequence sequence = getSequence(session, v0, v1);
      result = ValueLong.get(sequence.getCurrentValue());
      break;
    case 210: 
      String fileName = v0.getString();
      String columnList = v1 == null ? null : v1.getString();
      Csv csv = new Csv();
      String options = v2 == null ? null : v2.getString();
      String charset = null;
      if ((options != null) && (options.indexOf('=') >= 0))
      {
        charset = csv.setOptions(options);
      }
      else
      {
        charset = options;
        String fieldSeparatorRead = v3 == null ? null : v3.getString();
        String fieldDelimiter = v4 == null ? null : v4.getString();
        String escapeCharacter = v5 == null ? null : v5.getString();
        Value v6 = getNullOrValue(session, args, values, 6);
        String nullString = v6 == null ? null : v6.getString();
        setCsvDelimiterEscape(csv, fieldSeparatorRead, fieldDelimiter, escapeCharacter);
        
        csv.setNullString(nullString);
      }
      char fieldSeparator = csv.getFieldSeparatorRead();
      String[] columns = StringUtils.arraySplit(columnList, fieldSeparator, true);
      try
      {
        ValueResultSet vr = ValueResultSet.get(csv.read(fileName, columns, charset));
        
        result = vr;
      }
      catch (SQLException e)
      {
        throw DbException.convert(e);
      }
    case 218: 
      session.getUser().checkAdmin();
      Connection conn = session.createConnection(false);
      ResultSet rs = LinkSchema.linkSchema(conn, v0.getString(), v1.getString(), v2.getString(), v3.getString(), v4.getString(), v5.getString());
      
      result = ValueResultSet.get(rs);
      break;
    case 211: 
      session.getUser().checkAdmin();
      Connection conn = session.createConnection(false);
      Csv csv = new Csv();
      String options = v2 == null ? null : v2.getString();
      String charset = null;
      if ((options != null) && (options.indexOf('=') >= 0))
      {
        charset = csv.setOptions(options);
      }
      else
      {
        charset = options;
        String fieldSeparatorWrite = v3 == null ? null : v3.getString();
        String fieldDelimiter = v4 == null ? null : v4.getString();
        String escapeCharacter = v5 == null ? null : v5.getString();
        Value v6 = getNullOrValue(session, args, values, 6);
        String nullString = v6 == null ? null : v6.getString();
        Value v7 = getNullOrValue(session, args, values, 7);
        String lineSeparator = v7 == null ? null : v7.getString();
        setCsvDelimiterEscape(csv, fieldSeparatorWrite, fieldDelimiter, escapeCharacter);
        
        csv.setNullString(nullString);
        if (lineSeparator != null) {
          csv.setLineSeparator(lineSeparator);
        }
      }
      try
      {
        int rows = csv.write(conn, v0.getString(), v1.getString(), charset);
        
        result = ValueInt.get(rows);
      }
      catch (SQLException e)
      {
        throw DbException.convert(e);
      }
    case 222: 
      Variable var = (Variable)args[0];
      session.setVariable(var.getName(), v1);
      result = v1;
      break;
    case 225: 
      session.getUser().checkAdmin();
      String fileName = v0.getString();
      boolean blob = args.length == 1;
      try
      {
        InputStream in = new AutoCloseInputStream(FileUtils.newInputStream(fileName));
        if (blob)
        {
          result = this.database.getLobStorage().createBlob(in, -1L);
        }
        else
        {
          Reader reader;
          Reader reader;
          if (v1 == ValueNull.INSTANCE) {
            reader = new InputStreamReader(in);
          } else {
            reader = new InputStreamReader(in, v1.getString());
          }
          result = this.database.getLobStorage().createClob(reader, -1L);
        }
      }
      catch (IOException e)
      {
        throw DbException.convertIOException(e, fileName);
      }
    case 227: 
      result = v0.convertPrecision(v1.getLong(), v2.getBoolean().booleanValue());
      break;
    case 88: 
      if (v1 == null) {
        result = ValueString.get(StringUtils.xmlText(v0.getString()), this.database.getMode().treatEmptyStringsAsNull);
      } else {
        result = ValueString.get(StringUtils.xmlText(v0.getString(), v1.getBoolean().booleanValue()), this.database.getMode().treatEmptyStringsAsNull);
      }
      break;
    case 250: 
      result = session.getVariable(args[0].getSchemaName() + "." + args[0].getTableName() + "." + args[0].getColumnName());
      
      break;
    default: 
      throw DbException.throwInternalError("type=" + this.info.type);
    }
    return result;
  }
  
  private Sequence getSequence(Session session, Value v0, Value v1)
  {
    String sequenceName;
    String schemaName;
    String schemaName;
    String sequenceName;
    if (v1 == null)
    {
      Parser p = new Parser(session);
      String sql = v0.getString();
      Expression expr = p.parseExpression(sql);
      String sequenceName;
      if ((expr instanceof ExpressionColumn))
      {
        ExpressionColumn seq = (ExpressionColumn)expr;
        String schemaName = seq.getOriginalTableAliasName();
        String sequenceName;
        if (schemaName == null)
        {
          schemaName = session.getCurrentSchemaName();
          sequenceName = sql;
        }
        else
        {
          sequenceName = seq.getColumnName();
        }
      }
      else
      {
        throw DbException.getSyntaxError(sql, 1);
      }
    }
    else
    {
      schemaName = v0.getString();
      sequenceName = v1.getString();
    }
    Schema s = this.database.findSchema(schemaName);
    if (s == null)
    {
      schemaName = StringUtils.toUpperEnglish(schemaName);
      s = this.database.getSchema(schemaName);
    }
    Sequence seq = s.findSequence(sequenceName);
    if (seq == null)
    {
      sequenceName = StringUtils.toUpperEnglish(sequenceName);
      seq = s.getSequence(sequenceName);
    }
    return seq;
  }
  
  private static long length(Value v)
  {
    switch (v.getType())
    {
    case 12: 
    case 15: 
    case 16: 
    case 19: 
      return v.getPrecision();
    }
    return v.getString().length();
  }
  
  private static byte[] getPaddedArrayCopy(byte[] data, int blockSize)
  {
    int size = MathUtils.roundUpInt(data.length, blockSize);
    byte[] newData = DataUtils.newBytes(size);
    System.arraycopy(data, 0, newData, 0, data.length);
    return newData;
  }
  
  private static byte[] decrypt(String algorithm, byte[] key, byte[] data)
  {
    BlockCipher cipher = CipherFactory.getBlockCipher(algorithm);
    byte[] newKey = getPaddedArrayCopy(key, cipher.getKeyLength());
    cipher.setKey(newKey);
    byte[] newData = getPaddedArrayCopy(data, 16);
    cipher.decrypt(newData, 0, newData.length);
    return newData;
  }
  
  private static byte[] encrypt(String algorithm, byte[] key, byte[] data)
  {
    BlockCipher cipher = CipherFactory.getBlockCipher(algorithm);
    byte[] newKey = getPaddedArrayCopy(key, cipher.getKeyLength());
    cipher.setKey(newKey);
    byte[] newData = getPaddedArrayCopy(data, 16);
    cipher.encrypt(newData, 0, newData.length);
    return newData;
  }
  
  private static byte[] getHash(String algorithm, byte[] bytes, int iterations)
  {
    if (!"SHA256".equalsIgnoreCase(algorithm)) {
      throw DbException.getInvalidValueException("algorithm", algorithm);
    }
    for (int i = 0; i < iterations; i++) {
      bytes = SHA256.getHash(bytes, false);
    }
    return bytes;
  }
  
  public static boolean isDatePart(String part)
  {
    Integer p = (Integer)DATE_PART.get(StringUtils.toUpperEnglish(part));
    return p != null;
  }
  
  private static int getDatePart(String part)
  {
    Integer p = (Integer)DATE_PART.get(StringUtils.toUpperEnglish(part));
    if (p == null) {
      throw DbException.getInvalidValueException("date part", part);
    }
    return p.intValue();
  }
  
  private static Timestamp dateadd(String part, long count, Timestamp d)
  {
    int field = getDatePart(part);
    if (field == 14)
    {
      Timestamp ts = new Timestamp(d.getTime() + count);
      ts.setNanos(ts.getNanos() + d.getNanos() % 1000000);
      return ts;
    }
    if (count > 2147483647L) {
      throw DbException.getInvalidValueException("DATEADD count", Long.valueOf(count));
    }
    Calendar calendar = Calendar.getInstance();
    int nanos = d.getNanos() % 1000000;
    calendar.setTime(d);
    calendar.add(field, (int)count);
    long t = calendar.getTime().getTime();
    Timestamp ts = new Timestamp(t);
    ts.setNanos(ts.getNanos() + nanos);
    return ts;
  }
  
  private static long datediff(String part, Timestamp d1, Timestamp d2)
  {
    int field = getDatePart(part);
    Calendar calendar = Calendar.getInstance();
    long t1 = d1.getTime();long t2 = d2.getTime();
    
    TimeZone zone = calendar.getTimeZone();
    calendar.setTime(d1);
    t1 += zone.getOffset(calendar.get(0), calendar.get(1), calendar.get(2), calendar.get(5), calendar.get(7), calendar.get(14));
    
    calendar.setTime(d2);
    t2 += zone.getOffset(calendar.get(0), calendar.get(1), calendar.get(2), calendar.get(5), calendar.get(7), calendar.get(14));
    switch (field)
    {
    case 14: 
      return t2 - t1;
    case 11: 
    case 12: 
    case 13: 
      long hour = 3600000L;
      long add = Math.min(t1 / hour * hour, t2 / hour * hour);
      t1 -= add;
      t2 -= add;
      switch (field)
      {
      case 13: 
        return t2 / 1000L - t1 / 1000L;
      case 12: 
        return t2 / 60000L - t1 / 60000L;
      case 11: 
        return t2 / hour - t1 / hour;
      }
      throw DbException.throwInternalError("field:" + field);
    case 5: 
      return t2 / 86400000L - t1 / 86400000L;
    }
    calendar.setTimeInMillis(t1);
    int year1 = calendar.get(1);
    int month1 = calendar.get(2);
    calendar.setTimeInMillis(t2);
    int year2 = calendar.get(1);
    int month2 = calendar.get(2);
    int result = year2 - year1;
    if (field == 2) {
      result = 12 * result + (month2 - month1);
    }
    return result;
  }
  
  private static String substring(String s, int start, int length)
  {
    int len = s.length();
    start--;
    if (start < 0) {
      start = 0;
    }
    if (length < 0) {
      length = 0;
    }
    start = start > len ? len : start;
    if (start + length > len) {
      length = len - start;
    }
    return s.substring(start, start + length);
  }
  
  private static String replace(String s, String replace, String with)
  {
    if ((s == null) || (replace == null) || (with == null)) {
      return null;
    }
    if (replace.length() == 0) {
      return s;
    }
    StringBuilder buff = new StringBuilder(s.length());
    int start = 0;
    int len = replace.length();
    for (;;)
    {
      int i = s.indexOf(replace, start);
      if (i == -1) {
        break;
      }
      buff.append(s.substring(start, i)).append(with);
      start = i + len;
    }
    buff.append(s.substring(start));
    return buff.toString();
  }
  
  private static String repeat(String s, int count)
  {
    StringBuilder buff = new StringBuilder(s.length() * count);
    while (count-- > 0) {
      buff.append(s);
    }
    return buff.toString();
  }
  
  private static String rawToHex(String s)
  {
    int length = s.length();
    StringBuilder buff = new StringBuilder(4 * length);
    for (int i = 0; i < length; i++)
    {
      String hex = Integer.toHexString(s.charAt(i) & 0xFFFF);
      for (int j = hex.length(); j < 4; j++) {
        buff.append('0');
      }
      buff.append(hex);
    }
    return buff.toString();
  }
  
  private static int locate(String search, String s, int start)
  {
    if (start < 0)
    {
      int i = s.length() + start;
      return s.lastIndexOf(search, i) + 1;
    }
    int i = start == 0 ? 0 : start - 1;
    return s.indexOf(search, i) + 1;
  }
  
  private static String right(String s, int count)
  {
    if (count < 0) {
      count = 0;
    } else if (count > s.length()) {
      count = s.length();
    }
    return s.substring(s.length() - count);
  }
  
  private static String left(String s, int count)
  {
    if (count < 0) {
      count = 0;
    } else if (count > s.length()) {
      count = s.length();
    }
    return s.substring(0, count);
  }
  
  private static String insert(String s1, int start, int length, String s2)
  {
    if (s1 == null) {
      return s2;
    }
    if (s2 == null) {
      return s1;
    }
    int len1 = s1.length();
    int len2 = s2.length();
    start--;
    if ((start < 0) || (length <= 0) || (len2 == 0) || (start > len1)) {
      return s1;
    }
    if (start + length > len1) {
      length = len1 - start;
    }
    return s1.substring(0, start) + s2 + s1.substring(start + length);
  }
  
  private static String hexToRaw(String s)
  {
    int len = s.length();
    if (len % 4 != 0) {
      throw DbException.get(22018, s);
    }
    StringBuilder buff = new StringBuilder(len / 4);
    for (int i = 0; i < len; i += 4) {
      try
      {
        char raw = (char)Integer.parseInt(s.substring(i, i + 4), 16);
        buff.append(raw);
      }
      catch (NumberFormatException e)
      {
        throw DbException.get(22018, s);
      }
    }
    return buff.toString();
  }
  
  private static int getDifference(String s1, String s2)
  {
    s1 = getSoundex(s1);
    s2 = getSoundex(s2);
    int e = 0;
    for (int i = 0; i < 4; i++) {
      if (s1.charAt(i) == s2.charAt(i)) {
        e++;
      }
    }
    return e;
  }
  
  private static String translate(String original, String findChars, String replaceChars)
  {
    if ((StringUtils.isNullOrEmpty(original)) || (StringUtils.isNullOrEmpty(findChars))) {
      return original;
    }
    StringBuilder buff = null;
    
    int replaceSize = replaceChars == null ? 0 : replaceChars.length();
    int i = 0;
    for (int size = original.length(); i < size; i++)
    {
      char ch = original.charAt(i);
      int index = findChars.indexOf(ch);
      if (index >= 0)
      {
        if (buff == null)
        {
          buff = new StringBuilder(size);
          if (i > 0) {
            buff.append(original.substring(0, i));
          }
        }
        if (index < replaceSize) {
          ch = replaceChars.charAt(index);
        }
      }
      if (buff != null) {
        buff.append(ch);
      }
    }
    return buff == null ? original : buff.toString();
  }
  
  private static double roundMagic(double d)
  {
    if ((d < 1.0E-13D) && (d > -1.0E-13D)) {
      return 0.0D;
    }
    if ((d > 1.0E12D) || (d < -1.0E12D)) {
      return d;
    }
    StringBuilder s = new StringBuilder();
    s.append(d);
    if (s.toString().indexOf('E') >= 0) {
      return d;
    }
    int len = s.length();
    if (len < 16) {
      return d;
    }
    if (s.toString().indexOf('.') > len - 3) {
      return d;
    }
    s.delete(len - 2, len);
    len -= 2;
    char c1 = s.charAt(len - 2);
    char c2 = s.charAt(len - 3);
    char c3 = s.charAt(len - 4);
    if ((c1 == '0') && (c2 == '0') && (c3 == '0'))
    {
      s.setCharAt(len - 1, '0');
    }
    else if ((c1 == '9') && (c2 == '9') && (c3 == '9'))
    {
      s.setCharAt(len - 1, '9');
      s.append('9');
      s.append('9');
      s.append('9');
    }
    return Double.parseDouble(s.toString());
  }
  
  private static String getSoundex(String s)
  {
    int len = s.length();
    char[] chars = { '0', '0', '0', '0' };
    char lastDigit = '0';
    int i = 0;
    for (int j = 0; (i < len) && (j < 4); i++)
    {
      char c = s.charAt(i);
      char newDigit = c > SOUNDEX_INDEX.length ? '\000' : SOUNDEX_INDEX[c];
      if (newDigit != 0) {
        if (j == 0)
        {
          chars[(j++)] = c;
          lastDigit = newDigit;
        }
        else if (newDigit <= '6')
        {
          if (newDigit != lastDigit)
          {
            chars[(j++)] = newDigit;
            lastDigit = newDigit;
          }
        }
        else if (newDigit == '7')
        {
          lastDigit = newDigit;
        }
      }
    }
    return new String(chars);
  }
  
  public int getType()
  {
    return this.dataType;
  }
  
  public void mapColumns(ColumnResolver resolver, int level)
  {
    for (Expression e : this.args) {
      if (e != null) {
        e.mapColumns(resolver, level);
      }
    }
  }
  
  protected void checkParameterCount(int len)
  {
    int min = 0;int max = Integer.MAX_VALUE;
    switch (this.info.type)
    {
    case 204: 
    case 210: 
    case 219: 
    case 220: 
      min = 1;
      break;
    case 20: 
    case 112: 
    case 119: 
      max = 1;
      break;
    case 21: 
    case 27: 
    case 32: 
    case 63: 
    case 69: 
    case 78: 
    case 88: 
    case 225: 
      min = 1;
      max = 2;
      break;
    case 93: 
      min = 1;
      max = 3;
      break;
    case 58: 
    case 62: 
    case 67: 
    case 72: 
    case 73: 
    case 90: 
    case 91: 
      min = 2;
      max = 3;
      break;
    case 54: 
    case 92: 
    case 211: 
      min = 2;
      break;
    case 84: 
      min = 1;
      max = 4;
      break;
    case 121: 
    case 122: 
      min = 2;
      max = 4;
      break;
    case 207: 
    case 208: 
      min = 1;
      max = 2;
      break;
    case 206: 
    case 229: 
      min = 3;
      break;
    default: 
      DbException.throwInternalError("type=" + this.info.type);
    }
    boolean ok = (len >= min) && (len <= max);
    if (!ok) {
      throw DbException.get(7001, new String[] { this.info.name, min + ".." + max });
    }
  }
  
  public void doneWithParameters()
  {
    if (this.info.parameterCount == -1)
    {
      int len = this.varArgs.size();
      checkParameterCount(len);
      this.args = new Expression[len];
      this.varArgs.toArray(this.args);
      this.varArgs = null;
    }
    else
    {
      int len = this.args.length;
      if ((len > 0) && (this.args[(len - 1)] == null)) {
        throw DbException.get(7001, new String[] { this.info.name, "" + len });
      }
    }
  }
  
  public void setDataType(Column col)
  {
    this.dataType = col.getType();
    this.precision = col.getPrecision();
    this.displaySize = col.getDisplaySize();
    this.scale = col.getScale();
  }
  
  public Expression optimize(Session session)
  {
    boolean allConst = this.info.deterministic;
    for (int i = 0; i < this.args.length; i++)
    {
      Expression e = this.args[i];
      if (e != null)
      {
        e = e.optimize(session);
        this.args[i] = e;
        if (!e.isConstant()) {
          allConst = false;
        }
      }
    }
    Expression p0 = this.args.length < 1 ? null : this.args[0];
    int t;
    int s;
    long p;
    int d;
    int t;
    switch (this.info.type)
    {
    case 200: 
    case 204: 
    case 205: 
    case 219: 
    case 220: 
      t = -1;
      s = 0;
      p = 0L;
      d = 0;
      for (Expression e : this.args) {
        if (e != ValueExpression.getNull())
        {
          int type = e.getType();
          if ((type != -1) && (type != 0))
          {
            t = Value.getHigherOrder(t, type);
            s = Math.max(s, e.getScale());
            p = Math.max(p, e.getPrecision());
            d = Math.max(d, e.getDisplaySize());
          }
        }
      }
      if (t == -1)
      {
        t = 13;
        s = 0;
        p = 2147483647L;
        d = Integer.MAX_VALUE;
      }
      break;
    case 206: 
    case 229: 
      t = -1;
      s = 0;
      p = 0L;
      d = 0;
      
      int i = 2;
      for (int len = this.args.length; i < len; i += 2)
      {
        Expression then = this.args[i];
        if (then != ValueExpression.getNull())
        {
          int type = then.getType();
          if ((type != -1) && (type != 0))
          {
            t = Value.getHigherOrder(t, type);
            s = Math.max(s, then.getScale());
            p = Math.max(p, then.getPrecision());
            d = Math.max(d, then.getDisplaySize());
          }
        }
      }
      if (this.args.length % 2 == 0)
      {
        Expression elsePart = this.args[(this.args.length - 1)];
        if (elsePart != ValueExpression.getNull())
        {
          int type = elsePart.getType();
          if ((type != -1) && (type != 0))
          {
            t = Value.getHigherOrder(t, type);
            s = Math.max(s, elsePart.getScale());
            p = Math.max(p, elsePart.getPrecision());
            d = Math.max(d, elsePart.getDisplaySize());
          }
        }
      }
      if (t == -1)
      {
        t = 13;
        s = 0;
        p = 2147483647L;
        d = Integer.MAX_VALUE;
      }
      break;
    case 201: 
      t = Value.getHigherOrder(this.args[1].getType(), this.args[2].getType());
      p = Math.max(this.args[1].getPrecision(), this.args[2].getPrecision());
      d = Math.max(this.args[1].getDisplaySize(), this.args[2].getDisplaySize());
      s = Math.max(this.args[1].getScale(), this.args[2].getScale());
      break;
    case 228: 
      switch (this.args[1].getType())
      {
      case 13: 
      case 14: 
      case 16: 
      case 21: 
        t = this.args[1].getType();
        break;
      case 15: 
      case 17: 
      case 18: 
      case 19: 
      case 20: 
      default: 
        t = Value.getHigherOrder(this.args[1].getType(), this.args[2].getType());
      }
      p = Math.max(this.args[1].getPrecision(), this.args[2].getPrecision());
      d = Math.max(this.args[1].getDisplaySize(), this.args[2].getDisplaySize());
      s = Math.max(this.args[1].getScale(), this.args[2].getScale());
      break;
    case 202: 
    case 203: 
    case 227: 
      t = this.dataType;
      p = this.precision;
      s = this.scale;
      d = this.displaySize;
      break;
    case 27: 
      t = p0.getType();
      s = p0.getScale();
      p = p0.getPrecision();
      d = p0.getDisplaySize();
      if (t == 0)
      {
        t = 4;
        p = 10L;
        d = 11;
        s = 0;
      }
      else if (t == 11)
      {
        t = 10;
        p = 8L;
        s = 0;
        d = 10;
      }
      break;
    case 0: 
    case 13: 
    case 21: 
      t = p0.getType();
      s = p0.getScale();
      p = p0.getPrecision();
      d = p0.getDisplaySize();
      if (t == 0)
      {
        t = 4;
        p = 10L;
        d = 11;
        s = 0;
      }
      break;
    case 222: 
      Expression p1 = this.args[1];
      t = p1.getType();
      p = p1.getPrecision();
      s = p1.getScale();
      d = p1.getDisplaySize();
      if (!(p0 instanceof Variable)) {
        throw DbException.get(90137, p0.getSQL());
      }
      break;
    case 225: 
      if (this.args.length == 1) {
        t = 15;
      } else {
        t = 16;
      }
      p = 2147483647L;
      s = 0;
      d = Integer.MAX_VALUE;
      break;
    case 72: 
    case 73: 
      t = this.info.dataType;
      p = this.args[0].getPrecision();
      s = 0;
      if (this.args[1].isConstant()) {
        p -= this.args[1].getValue(session).getLong() - 1L;
      }
      if ((this.args.length == 3) && (this.args[2].isConstant())) {
        p = Math.min(p, this.args[2].getValue(session).getLong());
      }
      p = Math.max(0L, p);
      d = MathUtils.convertLongToInt(p);
      break;
    default: 
      t = this.info.dataType;
      DataType type = DataType.getDataType(t);
      p = -1L;
      d = 0;
      s = type.defaultScale;
    }
    this.dataType = t;
    this.precision = p;
    this.scale = s;
    this.displaySize = d;
    if (allConst)
    {
      Value v = getValue(session);
      if ((v == ValueNull.INSTANCE) && (
        (this.info.type == 203) || (this.info.type == 202))) {
        return this;
      }
      return ValueExpression.get(v);
    }
    return this;
  }
  
  public void setEvaluatable(TableFilter tableFilter, boolean b)
  {
    for (Expression e : this.args) {
      if (e != null) {
        e.setEvaluatable(tableFilter, b);
      }
    }
  }
  
  public int getScale()
  {
    return this.scale;
  }
  
  public long getPrecision()
  {
    if (this.precision == -1L) {
      calculatePrecisionAndDisplaySize();
    }
    return this.precision;
  }
  
  public int getDisplaySize()
  {
    if (this.precision == -1L) {
      calculatePrecisionAndDisplaySize();
    }
    return this.displaySize;
  }
  
  private void calculatePrecisionAndDisplaySize()
  {
    switch (this.info.type)
    {
    case 30: 
    case 31: 
      this.precision = this.args[2].getPrecision();
      this.displaySize = this.args[2].getDisplaySize();
      break;
    case 32: 
      this.precision = this.args[0].getPrecision();
      this.displaySize = this.args[0].getDisplaySize();
      break;
    case 52: 
      this.precision = 1L;
      this.displaySize = 1;
      break;
    case 54: 
      this.precision = 0L;
      this.displaySize = 0;
      for (Expression e : this.args)
      {
        this.precision += e.getPrecision();
        this.displaySize = MathUtils.convertLongToInt(this.displaySize + e.getDisplaySize());
        if (this.precision < 0L) {
          this.precision = Long.MAX_VALUE;
        }
      }
      break;
    case 56: 
      this.precision = ((this.args[0].getPrecision() + 3L) / 4L);
      this.displaySize = MathUtils.convertLongToInt(this.precision);
      break;
    case 27: 
    case 59: 
    case 63: 
    case 68: 
    case 69: 
    case 74: 
    case 75: 
    case 76: 
    case 78: 
    case 80: 
    case 82: 
      this.precision = this.args[0].getPrecision();
      this.displaySize = this.args[0].getDisplaySize();
      break;
    case 65: 
      this.precision = (this.args[0].getPrecision() * 4L);
      this.displaySize = MathUtils.convertLongToInt(this.precision);
      break;
    case 70: 
      this.precision = 4L;
      this.displaySize = ((int)this.precision);
      break;
    case 104: 
    case 111: 
      this.precision = 20L;
      this.displaySize = ((int)this.precision);
      break;
    case 28: 
    case 29: 
    case 33: 
    case 34: 
    case 35: 
    case 36: 
    case 37: 
    case 38: 
    case 39: 
    case 40: 
    case 41: 
    case 42: 
    case 43: 
    case 44: 
    case 45: 
    case 46: 
    case 47: 
    case 48: 
    case 49: 
    case 50: 
    case 51: 
    case 53: 
    case 55: 
    case 57: 
    case 58: 
    case 60: 
    case 61: 
    case 62: 
    case 64: 
    case 66: 
    case 67: 
    case 71: 
    case 72: 
    case 73: 
    case 77: 
    case 79: 
    case 81: 
    case 83: 
    case 84: 
    case 85: 
    case 86: 
    case 87: 
    case 88: 
    case 89: 
    case 90: 
    case 91: 
    case 92: 
    case 93: 
    case 94: 
    case 95: 
    case 96: 
    case 97: 
    case 98: 
    case 99: 
    case 100: 
    case 101: 
    case 102: 
    case 103: 
    case 105: 
    case 106: 
    case 107: 
    case 108: 
    case 109: 
    case 110: 
    default: 
      DataType type = DataType.getDataType(this.dataType);
      this.precision = type.defaultPrecision;
      this.displaySize = type.defaultDisplaySize;
    }
  }
  
  public String getSQL()
  {
    StatementBuilder buff = new StatementBuilder(this.info.name);
    if (this.info.type == 206)
    {
      if (this.args[0] != null) {
        buff.append(" ").append(this.args[0].getSQL());
      }
      int i = 1;
      for (int len = this.args.length - 1; i < len; i += 2)
      {
        buff.append(" WHEN ").append(this.args[i].getSQL());
        buff.append(" THEN ").append(this.args[(i + 1)].getSQL());
      }
      if (this.args.length % 2 == 0) {
        buff.append(" ELSE ").append(this.args[(this.args.length - 1)].getSQL());
      }
      return buff.append(" END").toString();
    }
    buff.append('(');
    switch (this.info.type)
    {
    case 203: 
      buff.append(this.args[0].getSQL()).append(" AS ").append(new Column(null, this.dataType, this.precision, this.scale, this.displaySize).getCreateSQL());
      
      break;
    case 202: 
      buff.append(this.args[0].getSQL()).append(',').append(new Column(null, this.dataType, this.precision, this.scale, this.displaySize).getCreateSQL());
      
      break;
    case 120: 
      ValueString v = (ValueString)((ValueExpression)this.args[0]).getValue(null);
      buff.append(v.getString()).append(" FROM ").append(this.args[1].getSQL());
      break;
    default: 
      for (Expression e : this.args)
      {
        buff.appendExceptFirst(", ");
        buff.append(e.getSQL());
      }
    }
    return buff.append(')').toString();
  }
  
  public void updateAggregate(Session session)
  {
    for (Expression e : this.args) {
      if (e != null) {
        e.updateAggregate(session);
      }
    }
  }
  
  public int getFunctionType()
  {
    return this.info.type;
  }
  
  public String getName()
  {
    return this.info.name;
  }
  
  public ValueResultSet getValueForColumnList(Session session, Expression[] argList)
  {
    switch (this.info.type)
    {
    case 210: 
      String fileName = argList[0].getValue(session).getString();
      if (fileName == null) {
        throw DbException.get(90012, "fileName");
      }
      String columnList = argList.length < 2 ? null : argList[1].getValue(session).getString();
      
      Csv csv = new Csv();
      String options = argList.length < 3 ? null : argList[2].getValue(session).getString();
      
      String charset = null;
      if ((options != null) && (options.indexOf('=') >= 0))
      {
        charset = csv.setOptions(options);
      }
      else
      {
        charset = options;
        String fieldSeparatorRead = argList.length < 4 ? null : argList[3].getValue(session).getString();
        
        String fieldDelimiter = argList.length < 5 ? null : argList[4].getValue(session).getString();
        
        String escapeCharacter = argList.length < 6 ? null : argList[5].getValue(session).getString();
        
        setCsvDelimiterEscape(csv, fieldSeparatorRead, fieldDelimiter, escapeCharacter);
      }
      char fieldSeparator = csv.getFieldSeparatorRead();
      String[] columns = StringUtils.arraySplit(columnList, fieldSeparator, true);
      ResultSet rs = null;
      ValueResultSet x;
      try
      {
        rs = csv.read(fileName, columns, charset);
        x = ValueResultSet.getCopy(rs, 0);
      }
      catch (SQLException e)
      {
        throw DbException.convert(e);
      }
      finally
      {
        csv.close();
        JdbcUtils.closeSilently(rs);
      }
      return x;
    }
    return (ValueResultSet)getValueWithArgs(session, argList);
  }
  
  private static void setCsvDelimiterEscape(Csv csv, String fieldSeparator, String fieldDelimiter, String escapeCharacter)
  {
    if (fieldSeparator != null)
    {
      csv.setFieldSeparatorWrite(fieldSeparator);
      if (fieldSeparator.length() > 0)
      {
        char fs = fieldSeparator.charAt(0);
        csv.setFieldSeparatorRead(fs);
      }
    }
    if (fieldDelimiter != null)
    {
      char fd = fieldDelimiter.length() == 0 ? '\000' : fieldDelimiter.charAt(0);
      
      csv.setFieldDelimiter(fd);
    }
    if (escapeCharacter != null)
    {
      char ec = escapeCharacter.length() == 0 ? '\000' : escapeCharacter.charAt(0);
      
      csv.setEscapeCharacter(ec);
    }
  }
  
  public Expression[] getArgs()
  {
    return this.args;
  }
  
  public boolean isEverything(ExpressionVisitor visitor)
  {
    for (Expression e : this.args) {
      if ((e != null) && (!e.isEverything(visitor))) {
        return false;
      }
    }
    switch (visitor.getType())
    {
    case 2: 
    case 5: 
    case 8: 
      return this.info.deterministic;
    case 0: 
    case 1: 
    case 3: 
    case 4: 
    case 6: 
    case 7: 
    case 9: 
      return true;
    }
    throw DbException.throwInternalError("type=" + visitor.getType());
  }
  
  public int getCost()
  {
    int cost = 3;
    for (Expression e : this.args) {
      if (e != null) {
        cost += e.getCost();
      }
    }
    return cost;
  }
  
  public boolean isDeterministic()
  {
    return this.info.deterministic;
  }
  
  public boolean isBufferResultSetToLocalTemp()
  {
    return this.info.bufferResultSetToLocalTemp;
  }
}
