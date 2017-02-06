package org.h2.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.RowIdLifetime;
import java.sql.SQLException;
import org.h2.engine.Constants;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.message.Trace;
import org.h2.message.TraceObject;
import org.h2.tools.SimpleResultSet;
import org.h2.util.StatementBuilder;
import org.h2.util.StringUtils;

public class JdbcDatabaseMetaData
  extends TraceObject
  implements DatabaseMetaData
{
  private final JdbcConnection conn;
  private String mode;
  
  JdbcDatabaseMetaData(JdbcConnection conn, Trace trace, int id)
  {
    setTrace(trace, 2, id);
    this.conn = conn;
  }
  
  public int getDriverMajorVersion()
  {
    debugCodeCall("getDriverMajorVersion");
    return 1;
  }
  
  public int getDriverMinorVersion()
  {
    debugCodeCall("getDriverMinorVersion");
    return 4;
  }
  
  public String getDatabaseProductName()
  {
    debugCodeCall("getDatabaseProductName");
    
    return "H2";
  }
  
  public String getDatabaseProductVersion()
  {
    debugCodeCall("getDatabaseProductVersion");
    return Constants.getFullVersion();
  }
  
  public String getDriverName()
  {
    debugCodeCall("getDriverName");
    return "H2 JDBC Driver";
  }
  
  public String getDriverVersion()
  {
    debugCodeCall("getDriverVersion");
    return Constants.getFullVersion();
  }
  
  public ResultSet getTables(String catalogPattern, String schemaPattern, String tableNamePattern, String[] types)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("getTables(" + quote(catalogPattern) + ", " + quote(schemaPattern) + ", " + quote(tableNamePattern) + ", " + quoteArray(types) + ");");
      }
      checkClosed();
      String tableType;
      String tableType;
      if ((types != null) && (types.length > 0))
      {
        StatementBuilder buff = new StatementBuilder("TABLE_TYPE IN(");
        for (int i = 0; i < types.length; i++)
        {
          buff.appendExceptFirst(", ");
          buff.append('?');
        }
        tableType = buff.append(')').toString();
      }
      else
      {
        tableType = "TRUE";
      }
      PreparedStatement prep = this.conn.prepareAutoCloseStatement("SELECT TABLE_CATALOG TABLE_CAT, TABLE_SCHEMA TABLE_SCHEM, TABLE_NAME, TABLE_TYPE, REMARKS, TYPE_NAME TYPE_CAT, TYPE_NAME TYPE_SCHEM, TYPE_NAME, TYPE_NAME SELF_REFERENCING_COL_NAME, TYPE_NAME REF_GENERATION, SQL FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_CATALOG LIKE ? ESCAPE ? AND TABLE_SCHEMA LIKE ? ESCAPE ? AND TABLE_NAME LIKE ? ESCAPE ? AND (" + tableType + ") " + "ORDER BY TABLE_TYPE, TABLE_SCHEMA, TABLE_NAME");
      
      prep.setString(1, getCatalogPattern(catalogPattern));
      prep.setString(2, "\\");
      prep.setString(3, getSchemaPattern(schemaPattern));
      prep.setString(4, "\\");
      prep.setString(5, getPattern(tableNamePattern));
      prep.setString(6, "\\");
      for (int i = 0; (types != null) && (i < types.length); i++) {
        prep.setString(7 + i, types[i]);
      }
      return prep.executeQuery();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public ResultSet getColumns(String catalogPattern, String schemaPattern, String tableNamePattern, String columnNamePattern)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("getColumns(" + quote(catalogPattern) + ", " + quote(schemaPattern) + ", " + quote(tableNamePattern) + ", " + quote(columnNamePattern) + ");");
      }
      checkClosed();
      PreparedStatement prep = this.conn.prepareAutoCloseStatement("SELECT TABLE_CATALOG TABLE_CAT, TABLE_SCHEMA TABLE_SCHEM, TABLE_NAME, COLUMN_NAME, DATA_TYPE, TYPE_NAME, CHARACTER_MAXIMUM_LENGTH COLUMN_SIZE, CHARACTER_MAXIMUM_LENGTH BUFFER_LENGTH, NUMERIC_SCALE DECIMAL_DIGITS, NUMERIC_PRECISION_RADIX NUM_PREC_RADIX, NULLABLE, REMARKS, COLUMN_DEFAULT COLUMN_DEF, DATA_TYPE SQL_DATA_TYPE, ZERO() SQL_DATETIME_SUB, CHARACTER_OCTET_LENGTH CHAR_OCTET_LENGTH, ORDINAL_POSITION, IS_NULLABLE IS_NULLABLE, CAST(SOURCE_DATA_TYPE AS VARCHAR) SCOPE_CATALOG, CAST(SOURCE_DATA_TYPE AS VARCHAR) SCOPE_SCHEMA, CAST(SOURCE_DATA_TYPE AS VARCHAR) SCOPE_TABLE, SOURCE_DATA_TYPE, CASE WHEN SEQUENCE_NAME IS NULL THEN CAST(? AS VARCHAR) ELSE CAST(? AS VARCHAR) END IS_AUTOINCREMENT, CAST(SOURCE_DATA_TYPE AS VARCHAR) SCOPE_CATLOG FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_CATALOG LIKE ? ESCAPE ? AND TABLE_SCHEMA LIKE ? ESCAPE ? AND TABLE_NAME LIKE ? ESCAPE ? AND COLUMN_NAME LIKE ? ESCAPE ? ORDER BY TABLE_SCHEM, TABLE_NAME, ORDINAL_POSITION");
      
      prep.setString(1, "NO");
      prep.setString(2, "YES");
      prep.setString(3, getCatalogPattern(catalogPattern));
      prep.setString(4, "\\");
      prep.setString(5, getSchemaPattern(schemaPattern));
      prep.setString(6, "\\");
      prep.setString(7, getPattern(tableNamePattern));
      prep.setString(8, "\\");
      prep.setString(9, getPattern(columnNamePattern));
      prep.setString(10, "\\");
      return prep.executeQuery();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public ResultSet getIndexInfo(String catalogPattern, String schemaPattern, String tableName, boolean unique, boolean approximate)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("getIndexInfo(" + quote(catalogPattern) + ", " + quote(schemaPattern) + ", " + quote(tableName) + ", " + unique + ", " + approximate + ");");
      }
      String uniqueCondition;
      String uniqueCondition;
      if (unique) {
        uniqueCondition = "NON_UNIQUE=FALSE";
      } else {
        uniqueCondition = "TRUE";
      }
      checkClosed();
      PreparedStatement prep = this.conn.prepareAutoCloseStatement("SELECT TABLE_CATALOG TABLE_CAT, TABLE_SCHEMA TABLE_SCHEM, TABLE_NAME, NON_UNIQUE, TABLE_CATALOG INDEX_QUALIFIER, INDEX_NAME, INDEX_TYPE TYPE, ORDINAL_POSITION, COLUMN_NAME, ASC_OR_DESC, CARDINALITY, PAGES, FILTER_CONDITION, SORT_TYPE FROM INFORMATION_SCHEMA.INDEXES WHERE TABLE_CATALOG LIKE ? ESCAPE ? AND TABLE_SCHEMA LIKE ? ESCAPE ? AND (" + uniqueCondition + ") " + "AND TABLE_NAME = ? " + "ORDER BY NON_UNIQUE, TYPE, TABLE_SCHEM, INDEX_NAME, ORDINAL_POSITION");
      
      prep.setString(1, getCatalogPattern(catalogPattern));
      prep.setString(2, "\\");
      prep.setString(3, getSchemaPattern(schemaPattern));
      prep.setString(4, "\\");
      prep.setString(5, tableName);
      return prep.executeQuery();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public ResultSet getPrimaryKeys(String catalogPattern, String schemaPattern, String tableName)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("getPrimaryKeys(" + quote(catalogPattern) + ", " + quote(schemaPattern) + ", " + quote(tableName) + ");");
      }
      checkClosed();
      PreparedStatement prep = this.conn.prepareAutoCloseStatement("SELECT TABLE_CATALOG TABLE_CAT, TABLE_SCHEMA TABLE_SCHEM, TABLE_NAME, COLUMN_NAME, ORDINAL_POSITION KEY_SEQ, IFNULL(CONSTRAINT_NAME, INDEX_NAME) PK_NAME FROM INFORMATION_SCHEMA.INDEXES WHERE TABLE_CATALOG LIKE ? ESCAPE ? AND TABLE_SCHEMA LIKE ? ESCAPE ? AND TABLE_NAME = ? AND PRIMARY_KEY = TRUE ORDER BY COLUMN_NAME");
      
      prep.setString(1, getCatalogPattern(catalogPattern));
      prep.setString(2, "\\");
      prep.setString(3, getSchemaPattern(schemaPattern));
      prep.setString(4, "\\");
      prep.setString(5, tableName);
      return prep.executeQuery();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean allProceduresAreCallable()
  {
    debugCodeCall("allProceduresAreCallable");
    return true;
  }
  
  public boolean allTablesAreSelectable()
  {
    debugCodeCall("allTablesAreSelectable");
    return true;
  }
  
  public String getURL()
    throws SQLException
  {
    try
    {
      debugCodeCall("getURL");
      return this.conn.getURL();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public String getUserName()
    throws SQLException
  {
    try
    {
      debugCodeCall("getUserName");
      return this.conn.getUser();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean isReadOnly()
    throws SQLException
  {
    try
    {
      debugCodeCall("isReadOnly");
      return this.conn.isReadOnly();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean nullsAreSortedHigh()
  {
    debugCodeCall("nullsAreSortedHigh");
    return SysProperties.SORT_NULLS_HIGH;
  }
  
  public boolean nullsAreSortedLow()
  {
    debugCodeCall("nullsAreSortedLow");
    return !SysProperties.SORT_NULLS_HIGH;
  }
  
  public boolean nullsAreSortedAtStart()
  {
    debugCodeCall("nullsAreSortedAtStart");
    return false;
  }
  
  public boolean nullsAreSortedAtEnd()
  {
    debugCodeCall("nullsAreSortedAtEnd");
    return false;
  }
  
  public Connection getConnection()
  {
    debugCodeCall("getConnection");
    return this.conn;
  }
  
  public ResultSet getProcedures(String catalogPattern, String schemaPattern, String procedureNamePattern)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("getProcedures(" + quote(catalogPattern) + ", " + quote(schemaPattern) + ", " + quote(procedureNamePattern) + ");");
      }
      checkClosed();
      PreparedStatement prep = this.conn.prepareAutoCloseStatement("SELECT ALIAS_CATALOG PROCEDURE_CAT, ALIAS_SCHEMA PROCEDURE_SCHEM, ALIAS_NAME PROCEDURE_NAME, COLUMN_COUNT NUM_INPUT_PARAMS, ZERO() NUM_OUTPUT_PARAMS, ZERO() NUM_RESULT_SETS, REMARKS, RETURNS_RESULT PROCEDURE_TYPE, ALIAS_NAME SPECIFIC_NAME FROM INFORMATION_SCHEMA.FUNCTION_ALIASES WHERE ALIAS_CATALOG LIKE ? ESCAPE ? AND ALIAS_SCHEMA LIKE ? ESCAPE ? AND ALIAS_NAME LIKE ? ESCAPE ? ORDER BY PROCEDURE_SCHEM, PROCEDURE_NAME, NUM_INPUT_PARAMS");
      
      prep.setString(1, getCatalogPattern(catalogPattern));
      prep.setString(2, "\\");
      prep.setString(3, getSchemaPattern(schemaPattern));
      prep.setString(4, "\\");
      prep.setString(5, getPattern(procedureNamePattern));
      prep.setString(6, "\\");
      return prep.executeQuery();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public ResultSet getProcedureColumns(String catalogPattern, String schemaPattern, String procedureNamePattern, String columnNamePattern)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("getProcedureColumns(" + quote(catalogPattern) + ", " + quote(schemaPattern) + ", " + quote(procedureNamePattern) + ", " + quote(columnNamePattern) + ");");
      }
      checkClosed();
      PreparedStatement prep = this.conn.prepareAutoCloseStatement("SELECT ALIAS_CATALOG PROCEDURE_CAT, ALIAS_SCHEMA PROCEDURE_SCHEM, ALIAS_NAME PROCEDURE_NAME, COLUMN_NAME, COLUMN_TYPE, DATA_TYPE, TYPE_NAME, PRECISION, PRECISION LENGTH, SCALE, RADIX, NULLABLE, REMARKS, COLUMN_DEFAULT COLUMN_DEF, ZERO() SQL_DATA_TYPE, ZERO() SQL_DATETIME_SUB, ZERO() CHAR_OCTET_LENGTH, POS ORDINAL_POSITION, ? IS_NULLABLE, ALIAS_NAME SPECIFIC_NAME FROM INFORMATION_SCHEMA.FUNCTION_COLUMNS WHERE ALIAS_CATALOG LIKE ? ESCAPE ? AND ALIAS_SCHEMA LIKE ? ESCAPE ? AND ALIAS_NAME LIKE ? ESCAPE ? AND COLUMN_NAME LIKE ? ESCAPE ? ORDER BY PROCEDURE_SCHEM, PROCEDURE_NAME, ORDINAL_POSITION");
      
      prep.setString(1, "YES");
      prep.setString(2, getCatalogPattern(catalogPattern));
      prep.setString(3, "\\");
      prep.setString(4, getSchemaPattern(schemaPattern));
      prep.setString(5, "\\");
      prep.setString(6, getPattern(procedureNamePattern));
      prep.setString(7, "\\");
      prep.setString(8, getPattern(columnNamePattern));
      prep.setString(9, "\\");
      return prep.executeQuery();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public ResultSet getSchemas()
    throws SQLException
  {
    try
    {
      debugCodeCall("getSchemas");
      checkClosed();
      PreparedStatement prep = this.conn.prepareAutoCloseStatement("SELECT SCHEMA_NAME TABLE_SCHEM, CATALOG_NAME TABLE_CATALOG,  IS_DEFAULT FROM INFORMATION_SCHEMA.SCHEMATA ORDER BY SCHEMA_NAME");
      
      return prep.executeQuery();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public ResultSet getCatalogs()
    throws SQLException
  {
    try
    {
      debugCodeCall("getCatalogs");
      checkClosed();
      PreparedStatement prep = this.conn.prepareAutoCloseStatement("SELECT CATALOG_NAME TABLE_CAT FROM INFORMATION_SCHEMA.CATALOGS");
      
      return prep.executeQuery();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public ResultSet getTableTypes()
    throws SQLException
  {
    try
    {
      debugCodeCall("getTableTypes");
      checkClosed();
      PreparedStatement prep = this.conn.prepareAutoCloseStatement("SELECT TYPE TABLE_TYPE FROM INFORMATION_SCHEMA.TABLE_TYPES ORDER BY TABLE_TYPE");
      
      return prep.executeQuery();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public ResultSet getColumnPrivileges(String catalogPattern, String schemaPattern, String table, String columnNamePattern)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("getColumnPrivileges(" + quote(catalogPattern) + ", " + quote(schemaPattern) + ", " + quote(table) + ", " + quote(columnNamePattern) + ");");
      }
      checkClosed();
      PreparedStatement prep = this.conn.prepareAutoCloseStatement("SELECT TABLE_CATALOG TABLE_CAT, TABLE_SCHEMA TABLE_SCHEM, TABLE_NAME, COLUMN_NAME, GRANTOR, GRANTEE, PRIVILEGE_TYPE PRIVILEGE, IS_GRANTABLE FROM INFORMATION_SCHEMA.COLUMN_PRIVILEGES WHERE TABLE_CATALOG LIKE ? ESCAPE ? AND TABLE_SCHEMA LIKE ? ESCAPE ? AND TABLE_NAME = ? AND COLUMN_NAME LIKE ? ESCAPE ? ORDER BY COLUMN_NAME, PRIVILEGE");
      
      prep.setString(1, getCatalogPattern(catalogPattern));
      prep.setString(2, "\\");
      prep.setString(3, getSchemaPattern(schemaPattern));
      prep.setString(4, "\\");
      prep.setString(5, table);
      prep.setString(6, getPattern(columnNamePattern));
      prep.setString(7, "\\");
      return prep.executeQuery();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public ResultSet getTablePrivileges(String catalogPattern, String schemaPattern, String tableNamePattern)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("getTablePrivileges(" + quote(catalogPattern) + ", " + quote(schemaPattern) + ", " + quote(tableNamePattern) + ");");
      }
      checkClosed();
      PreparedStatement prep = this.conn.prepareAutoCloseStatement("SELECT TABLE_CATALOG TABLE_CAT, TABLE_SCHEMA TABLE_SCHEM, TABLE_NAME, GRANTOR, GRANTEE, PRIVILEGE_TYPE PRIVILEGE, IS_GRANTABLE FROM INFORMATION_SCHEMA.TABLE_PRIVILEGES WHERE TABLE_CATALOG LIKE ? ESCAPE ? AND TABLE_SCHEMA LIKE ? ESCAPE ? AND TABLE_NAME LIKE ? ESCAPE ? ORDER BY TABLE_SCHEM, TABLE_NAME, PRIVILEGE");
      
      prep.setString(1, getCatalogPattern(catalogPattern));
      prep.setString(2, "\\");
      prep.setString(3, getSchemaPattern(schemaPattern));
      prep.setString(4, "\\");
      prep.setString(5, getPattern(tableNamePattern));
      prep.setString(6, "\\");
      return prep.executeQuery();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public ResultSet getBestRowIdentifier(String catalogPattern, String schemaPattern, String tableName, int scope, boolean nullable)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("getBestRowIdentifier(" + quote(catalogPattern) + ", " + quote(schemaPattern) + ", " + quote(tableName) + ", " + scope + ", " + nullable + ");");
      }
      checkClosed();
      PreparedStatement prep = this.conn.prepareAutoCloseStatement("SELECT CAST(? AS SMALLINT) SCOPE, C.COLUMN_NAME, C.DATA_TYPE, C.TYPE_NAME, C.CHARACTER_MAXIMUM_LENGTH COLUMN_SIZE, C.CHARACTER_MAXIMUM_LENGTH BUFFER_LENGTH, CAST(C.NUMERIC_SCALE AS SMALLINT) DECIMAL_DIGITS, CAST(? AS SMALLINT) PSEUDO_COLUMN FROM INFORMATION_SCHEMA.INDEXES I,  INFORMATION_SCHEMA.COLUMNS C WHERE C.TABLE_NAME = I.TABLE_NAME AND C.COLUMN_NAME = I.COLUMN_NAME AND C.TABLE_CATALOG LIKE ? ESCAPE ? AND C.TABLE_SCHEMA LIKE ? ESCAPE ? AND C.TABLE_NAME = ? AND I.PRIMARY_KEY = TRUE ORDER BY SCOPE");
      
      prep.setInt(1, 2);
      
      prep.setInt(2, 1);
      prep.setString(3, getCatalogPattern(catalogPattern));
      prep.setString(4, "\\");
      prep.setString(5, getSchemaPattern(schemaPattern));
      prep.setString(6, "\\");
      prep.setString(7, tableName);
      return prep.executeQuery();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public ResultSet getVersionColumns(String catalog, String schema, String tableName)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("getVersionColumns(" + quote(catalog) + ", " + quote(schema) + ", " + quote(tableName) + ");");
      }
      checkClosed();
      PreparedStatement prep = this.conn.prepareAutoCloseStatement("SELECT ZERO() SCOPE, COLUMN_NAME, CAST(DATA_TYPE AS INT) DATA_TYPE, TYPE_NAME, NUMERIC_PRECISION COLUMN_SIZE, NUMERIC_PRECISION BUFFER_LENGTH, NUMERIC_PRECISION DECIMAL_DIGITS, ZERO() PSEUDO_COLUMN FROM INFORMATION_SCHEMA.COLUMNS WHERE FALSE");
      
      return prep.executeQuery();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public ResultSet getImportedKeys(String catalogPattern, String schemaPattern, String tableName)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("getImportedKeys(" + quote(catalogPattern) + ", " + quote(schemaPattern) + ", " + quote(tableName) + ");");
      }
      checkClosed();
      PreparedStatement prep = this.conn.prepareAutoCloseStatement("SELECT PKTABLE_CATALOG PKTABLE_CAT, PKTABLE_SCHEMA PKTABLE_SCHEM, PKTABLE_NAME PKTABLE_NAME, PKCOLUMN_NAME, FKTABLE_CATALOG FKTABLE_CAT, FKTABLE_SCHEMA FKTABLE_SCHEM, FKTABLE_NAME, FKCOLUMN_NAME, ORDINAL_POSITION KEY_SEQ, UPDATE_RULE, DELETE_RULE, FK_NAME, PK_NAME, DEFERRABILITY FROM INFORMATION_SCHEMA.CROSS_REFERENCES WHERE FKTABLE_CATALOG LIKE ? ESCAPE ? AND FKTABLE_SCHEMA LIKE ? ESCAPE ? AND FKTABLE_NAME = ? ORDER BY PKTABLE_CAT, PKTABLE_SCHEM, PKTABLE_NAME, FK_NAME, KEY_SEQ");
      
      prep.setString(1, getCatalogPattern(catalogPattern));
      prep.setString(2, "\\");
      prep.setString(3, getSchemaPattern(schemaPattern));
      prep.setString(4, "\\");
      prep.setString(5, tableName);
      return prep.executeQuery();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public ResultSet getExportedKeys(String catalogPattern, String schemaPattern, String tableName)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("getExportedKeys(" + quote(catalogPattern) + ", " + quote(schemaPattern) + ", " + quote(tableName) + ");");
      }
      checkClosed();
      PreparedStatement prep = this.conn.prepareAutoCloseStatement("SELECT PKTABLE_CATALOG PKTABLE_CAT, PKTABLE_SCHEMA PKTABLE_SCHEM, PKTABLE_NAME PKTABLE_NAME, PKCOLUMN_NAME, FKTABLE_CATALOG FKTABLE_CAT, FKTABLE_SCHEMA FKTABLE_SCHEM, FKTABLE_NAME, FKCOLUMN_NAME, ORDINAL_POSITION KEY_SEQ, UPDATE_RULE, DELETE_RULE, FK_NAME, PK_NAME, DEFERRABILITY FROM INFORMATION_SCHEMA.CROSS_REFERENCES WHERE PKTABLE_CATALOG LIKE ? ESCAPE ? AND PKTABLE_SCHEMA LIKE ? ESCAPE ? AND PKTABLE_NAME = ? ORDER BY FKTABLE_CAT, FKTABLE_SCHEM, FKTABLE_NAME, FK_NAME, KEY_SEQ");
      
      prep.setString(1, getCatalogPattern(catalogPattern));
      prep.setString(2, "\\");
      prep.setString(3, getSchemaPattern(schemaPattern));
      prep.setString(4, "\\");
      prep.setString(5, tableName);
      return prep.executeQuery();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public ResultSet getCrossReference(String primaryCatalogPattern, String primarySchemaPattern, String primaryTable, String foreignCatalogPattern, String foreignSchemaPattern, String foreignTable)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("getCrossReference(" + quote(primaryCatalogPattern) + ", " + quote(primarySchemaPattern) + ", " + quote(primaryTable) + ", " + quote(foreignCatalogPattern) + ", " + quote(foreignSchemaPattern) + ", " + quote(foreignTable) + ");");
      }
      checkClosed();
      PreparedStatement prep = this.conn.prepareAutoCloseStatement("SELECT PKTABLE_CATALOG PKTABLE_CAT, PKTABLE_SCHEMA PKTABLE_SCHEM, PKTABLE_NAME PKTABLE_NAME, PKCOLUMN_NAME, FKTABLE_CATALOG FKTABLE_CAT, FKTABLE_SCHEMA FKTABLE_SCHEM, FKTABLE_NAME, FKCOLUMN_NAME, ORDINAL_POSITION KEY_SEQ, UPDATE_RULE, DELETE_RULE, FK_NAME, PK_NAME, DEFERRABILITY FROM INFORMATION_SCHEMA.CROSS_REFERENCES WHERE PKTABLE_CATALOG LIKE ? ESCAPE ? AND PKTABLE_SCHEMA LIKE ? ESCAPE ? AND PKTABLE_NAME = ? AND FKTABLE_CATALOG LIKE ? ESCAPE ? AND FKTABLE_SCHEMA LIKE ? ESCAPE ? AND FKTABLE_NAME = ? ORDER BY FKTABLE_CAT, FKTABLE_SCHEM, FKTABLE_NAME, FK_NAME, KEY_SEQ");
      
      prep.setString(1, getCatalogPattern(primaryCatalogPattern));
      prep.setString(2, "\\");
      prep.setString(3, getSchemaPattern(primarySchemaPattern));
      prep.setString(4, "\\");
      prep.setString(5, primaryTable);
      prep.setString(6, getCatalogPattern(foreignCatalogPattern));
      prep.setString(7, "\\");
      prep.setString(8, getSchemaPattern(foreignSchemaPattern));
      prep.setString(9, "\\");
      prep.setString(10, foreignTable);
      return prep.executeQuery();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public ResultSet getUDTs(String catalog, String schemaPattern, String typeNamePattern, int[] types)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("getUDTs(" + quote(catalog) + ", " + quote(schemaPattern) + ", " + quote(typeNamePattern) + ", " + quoteIntArray(types) + ");");
      }
      checkClosed();
      PreparedStatement prep = this.conn.prepareAutoCloseStatement("SELECT CAST(NULL AS VARCHAR) TYPE_CAT, CAST(NULL AS VARCHAR) TYPE_SCHEM, CAST(NULL AS VARCHAR) TYPE_NAME, CAST(NULL AS VARCHAR) CLASS_NAME, CAST(NULL AS SMALLINT) DATA_TYPE, CAST(NULL AS VARCHAR) REMARKS, CAST(NULL AS SMALLINT) BASE_TYPE FROM DUAL WHERE FALSE");
      
      return prep.executeQuery();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public ResultSet getTypeInfo()
    throws SQLException
  {
    try
    {
      debugCodeCall("getTypeInfo");
      checkClosed();
      PreparedStatement prep = this.conn.prepareAutoCloseStatement("SELECT TYPE_NAME, DATA_TYPE, PRECISION, PREFIX LITERAL_PREFIX, SUFFIX LITERAL_SUFFIX, PARAMS CREATE_PARAMS, NULLABLE, CASE_SENSITIVE, SEARCHABLE, FALSE UNSIGNED_ATTRIBUTE, FALSE FIXED_PREC_SCALE, AUTO_INCREMENT, TYPE_NAME LOCAL_TYPE_NAME, MINIMUM_SCALE, MAXIMUM_SCALE, DATA_TYPE SQL_DATA_TYPE, ZERO() SQL_DATETIME_SUB, RADIX NUM_PREC_RADIX FROM INFORMATION_SCHEMA.TYPE_INFO ORDER BY DATA_TYPE, POS");
      
      return prep.executeQuery();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean usesLocalFiles()
  {
    debugCodeCall("usesLocalFiles");
    return true;
  }
  
  public boolean usesLocalFilePerTable()
  {
    debugCodeCall("usesLocalFilePerTable");
    return false;
  }
  
  public String getIdentifierQuoteString()
  {
    debugCodeCall("getIdentifierQuoteString");
    return "\"";
  }
  
  public String getSQLKeywords()
  {
    debugCodeCall("getSQLKeywords");
    return "LIMIT,MINUS,ROWNUM,SYSDATE,SYSTIME,SYSTIMESTAMP,TODAY";
  }
  
  public String getNumericFunctions()
    throws SQLException
  {
    debugCodeCall("getNumericFunctions");
    return getFunctions("Functions (Numeric)");
  }
  
  public String getStringFunctions()
    throws SQLException
  {
    debugCodeCall("getStringFunctions");
    return getFunctions("Functions (String)");
  }
  
  public String getSystemFunctions()
    throws SQLException
  {
    debugCodeCall("getSystemFunctions");
    return getFunctions("Functions (System)");
  }
  
  public String getTimeDateFunctions()
    throws SQLException
  {
    debugCodeCall("getTimeDateFunctions");
    return getFunctions("Functions (Time and Date)");
  }
  
  private String getFunctions(String section)
    throws SQLException
  {
    try
    {
      checkClosed();
      PreparedStatement prep = this.conn.prepareAutoCloseStatement("SELECT TOPIC FROM INFORMATION_SCHEMA.HELP WHERE SECTION = ?");
      
      prep.setString(1, section);
      ResultSet rs = prep.executeQuery();
      StatementBuilder buff = new StatementBuilder();
      while (rs.next())
      {
        String s = rs.getString(1).trim();
        String[] array = StringUtils.arraySplit(s, ',', true);
        for (String a : array)
        {
          buff.appendExceptFirst(",");
          String f = a.trim();
          if (f.indexOf(' ') >= 0) {
            f = f.substring(0, f.indexOf(' ')).trim();
          }
          buff.append(f);
        }
      }
      rs.close();
      prep.close();
      return buff.toString();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public String getSearchStringEscape()
  {
    debugCodeCall("getSearchStringEscape");
    return "\\";
  }
  
  public String getExtraNameCharacters()
  {
    debugCodeCall("getExtraNameCharacters");
    return "";
  }
  
  public boolean supportsAlterTableWithAddColumn()
  {
    debugCodeCall("supportsAlterTableWithAddColumn");
    return true;
  }
  
  public boolean supportsAlterTableWithDropColumn()
  {
    debugCodeCall("supportsAlterTableWithDropColumn");
    return true;
  }
  
  public boolean supportsColumnAliasing()
  {
    debugCodeCall("supportsColumnAliasing");
    return true;
  }
  
  public boolean nullPlusNonNullIsNull()
  {
    debugCodeCall("nullPlusNonNullIsNull");
    return true;
  }
  
  public boolean supportsConvert()
  {
    debugCodeCall("supportsConvert");
    return true;
  }
  
  public boolean supportsConvert(int fromType, int toType)
  {
    if (isDebugEnabled()) {
      debugCode("supportsConvert(" + fromType + ", " + fromType + ");");
    }
    return true;
  }
  
  public boolean supportsTableCorrelationNames()
  {
    debugCodeCall("supportsTableCorrelationNames");
    return true;
  }
  
  public boolean supportsDifferentTableCorrelationNames()
  {
    debugCodeCall("supportsDifferentTableCorrelationNames");
    return false;
  }
  
  public boolean supportsExpressionsInOrderBy()
  {
    debugCodeCall("supportsExpressionsInOrderBy");
    return true;
  }
  
  public boolean supportsOrderByUnrelated()
  {
    debugCodeCall("supportsOrderByUnrelated");
    return true;
  }
  
  public boolean supportsGroupBy()
  {
    debugCodeCall("supportsGroupBy");
    return true;
  }
  
  public boolean supportsGroupByUnrelated()
  {
    debugCodeCall("supportsGroupByUnrelated");
    return true;
  }
  
  public boolean supportsGroupByBeyondSelect()
  {
    debugCodeCall("supportsGroupByBeyondSelect");
    return true;
  }
  
  public boolean supportsLikeEscapeClause()
  {
    debugCodeCall("supportsLikeEscapeClause");
    return true;
  }
  
  public boolean supportsMultipleResultSets()
  {
    debugCodeCall("supportsMultipleResultSets");
    return false;
  }
  
  public boolean supportsMultipleTransactions()
  {
    debugCodeCall("supportsMultipleTransactions");
    return true;
  }
  
  public boolean supportsNonNullableColumns()
  {
    debugCodeCall("supportsNonNullableColumns");
    return true;
  }
  
  public boolean supportsMinimumSQLGrammar()
  {
    debugCodeCall("supportsMinimumSQLGrammar");
    return true;
  }
  
  public boolean supportsCoreSQLGrammar()
  {
    debugCodeCall("supportsCoreSQLGrammar");
    return true;
  }
  
  public boolean supportsExtendedSQLGrammar()
  {
    debugCodeCall("supportsExtendedSQLGrammar");
    return false;
  }
  
  public boolean supportsANSI92EntryLevelSQL()
  {
    debugCodeCall("supportsANSI92EntryLevelSQL");
    return true;
  }
  
  public boolean supportsANSI92IntermediateSQL()
  {
    debugCodeCall("supportsANSI92IntermediateSQL");
    return false;
  }
  
  public boolean supportsANSI92FullSQL()
  {
    debugCodeCall("supportsANSI92FullSQL");
    return false;
  }
  
  public boolean supportsIntegrityEnhancementFacility()
  {
    debugCodeCall("supportsIntegrityEnhancementFacility");
    return true;
  }
  
  public boolean supportsOuterJoins()
  {
    debugCodeCall("supportsOuterJoins");
    return true;
  }
  
  public boolean supportsFullOuterJoins()
  {
    debugCodeCall("supportsFullOuterJoins");
    return false;
  }
  
  public boolean supportsLimitedOuterJoins()
  {
    debugCodeCall("supportsLimitedOuterJoins");
    return true;
  }
  
  public String getSchemaTerm()
  {
    debugCodeCall("getSchemaTerm");
    return "schema";
  }
  
  public String getProcedureTerm()
  {
    debugCodeCall("getProcedureTerm");
    return "procedure";
  }
  
  public String getCatalogTerm()
  {
    debugCodeCall("getCatalogTerm");
    return "catalog";
  }
  
  public boolean isCatalogAtStart()
  {
    debugCodeCall("isCatalogAtStart");
    return true;
  }
  
  public String getCatalogSeparator()
  {
    debugCodeCall("getCatalogSeparator");
    return ".";
  }
  
  public boolean supportsSchemasInDataManipulation()
  {
    debugCodeCall("supportsSchemasInDataManipulation");
    return true;
  }
  
  public boolean supportsSchemasInProcedureCalls()
  {
    debugCodeCall("supportsSchemasInProcedureCalls");
    return true;
  }
  
  public boolean supportsSchemasInTableDefinitions()
  {
    debugCodeCall("supportsSchemasInTableDefinitions");
    return true;
  }
  
  public boolean supportsSchemasInIndexDefinitions()
  {
    debugCodeCall("supportsSchemasInIndexDefinitions");
    return true;
  }
  
  public boolean supportsSchemasInPrivilegeDefinitions()
  {
    debugCodeCall("supportsSchemasInPrivilegeDefinitions");
    return true;
  }
  
  public boolean supportsCatalogsInDataManipulation()
  {
    debugCodeCall("supportsCatalogsInDataManipulation");
    return true;
  }
  
  public boolean supportsCatalogsInProcedureCalls()
  {
    debugCodeCall("supportsCatalogsInProcedureCalls");
    return false;
  }
  
  public boolean supportsCatalogsInTableDefinitions()
  {
    debugCodeCall("supportsCatalogsInTableDefinitions");
    return true;
  }
  
  public boolean supportsCatalogsInIndexDefinitions()
  {
    debugCodeCall("supportsCatalogsInIndexDefinitions");
    return true;
  }
  
  public boolean supportsCatalogsInPrivilegeDefinitions()
  {
    debugCodeCall("supportsCatalogsInPrivilegeDefinitions");
    return true;
  }
  
  public boolean supportsPositionedDelete()
  {
    debugCodeCall("supportsPositionedDelete");
    return true;
  }
  
  public boolean supportsPositionedUpdate()
  {
    debugCodeCall("supportsPositionedUpdate");
    return true;
  }
  
  public boolean supportsSelectForUpdate()
  {
    debugCodeCall("supportsSelectForUpdate");
    return true;
  }
  
  public boolean supportsStoredProcedures()
  {
    debugCodeCall("supportsStoredProcedures");
    return false;
  }
  
  public boolean supportsSubqueriesInComparisons()
  {
    debugCodeCall("supportsSubqueriesInComparisons");
    return true;
  }
  
  public boolean supportsSubqueriesInExists()
  {
    debugCodeCall("supportsSubqueriesInExists");
    return true;
  }
  
  public boolean supportsSubqueriesInIns()
  {
    debugCodeCall("supportsSubqueriesInIns");
    return true;
  }
  
  public boolean supportsSubqueriesInQuantifieds()
  {
    debugCodeCall("supportsSubqueriesInQuantifieds");
    return true;
  }
  
  public boolean supportsCorrelatedSubqueries()
  {
    debugCodeCall("supportsCorrelatedSubqueries");
    return true;
  }
  
  public boolean supportsUnion()
  {
    debugCodeCall("supportsUnion");
    return true;
  }
  
  public boolean supportsUnionAll()
  {
    debugCodeCall("supportsUnionAll");
    return true;
  }
  
  public boolean supportsOpenCursorsAcrossCommit()
  {
    debugCodeCall("supportsOpenCursorsAcrossCommit");
    return false;
  }
  
  public boolean supportsOpenCursorsAcrossRollback()
  {
    debugCodeCall("supportsOpenCursorsAcrossRollback");
    return false;
  }
  
  public boolean supportsOpenStatementsAcrossCommit()
  {
    debugCodeCall("supportsOpenStatementsAcrossCommit");
    return true;
  }
  
  public boolean supportsOpenStatementsAcrossRollback()
  {
    debugCodeCall("supportsOpenStatementsAcrossRollback");
    return true;
  }
  
  public boolean supportsTransactions()
  {
    debugCodeCall("supportsTransactions");
    return true;
  }
  
  public boolean supportsTransactionIsolationLevel(int level)
    throws SQLException
  {
    debugCodeCall("supportsTransactionIsolationLevel");
    if (level == 1)
    {
      PreparedStatement prep = this.conn.prepareAutoCloseStatement("SELECT VALUE FROM INFORMATION_SCHEMA.SETTINGS WHERE NAME=?");
      
      prep.setString(1, "MULTI_THREADED");
      ResultSet rs = prep.executeQuery();
      if ((rs.next()) && (rs.getString(1).equals("1"))) {
        return false;
      }
    }
    return true;
  }
  
  public boolean supportsDataDefinitionAndDataManipulationTransactions()
  {
    debugCodeCall("supportsDataDefinitionAndDataManipulationTransactions");
    return false;
  }
  
  public boolean supportsDataManipulationTransactionsOnly()
  {
    debugCodeCall("supportsDataManipulationTransactionsOnly");
    return true;
  }
  
  public boolean dataDefinitionCausesTransactionCommit()
  {
    debugCodeCall("dataDefinitionCausesTransactionCommit");
    return true;
  }
  
  public boolean dataDefinitionIgnoredInTransactions()
  {
    debugCodeCall("dataDefinitionIgnoredInTransactions");
    return false;
  }
  
  public boolean supportsResultSetType(int type)
  {
    debugCodeCall("supportsResultSetType", type);
    return type != 1005;
  }
  
  public boolean supportsResultSetConcurrency(int type, int concurrency)
  {
    if (isDebugEnabled()) {
      debugCode("supportsResultSetConcurrency(" + type + ", " + concurrency + ");");
    }
    return type != 1005;
  }
  
  public boolean ownUpdatesAreVisible(int type)
  {
    debugCodeCall("ownUpdatesAreVisible", type);
    return true;
  }
  
  public boolean ownDeletesAreVisible(int type)
  {
    debugCodeCall("ownDeletesAreVisible", type);
    return false;
  }
  
  public boolean ownInsertsAreVisible(int type)
  {
    debugCodeCall("ownInsertsAreVisible", type);
    return false;
  }
  
  public boolean othersUpdatesAreVisible(int type)
  {
    debugCodeCall("othersUpdatesAreVisible", type);
    return false;
  }
  
  public boolean othersDeletesAreVisible(int type)
  {
    debugCodeCall("othersDeletesAreVisible", type);
    return false;
  }
  
  public boolean othersInsertsAreVisible(int type)
  {
    debugCodeCall("othersInsertsAreVisible", type);
    return false;
  }
  
  public boolean updatesAreDetected(int type)
  {
    debugCodeCall("updatesAreDetected", type);
    return false;
  }
  
  public boolean deletesAreDetected(int type)
  {
    debugCodeCall("deletesAreDetected", type);
    return false;
  }
  
  public boolean insertsAreDetected(int type)
  {
    debugCodeCall("insertsAreDetected", type);
    return false;
  }
  
  public boolean supportsBatchUpdates()
  {
    debugCodeCall("supportsBatchUpdates");
    return true;
  }
  
  public boolean doesMaxRowSizeIncludeBlobs()
  {
    debugCodeCall("doesMaxRowSizeIncludeBlobs");
    return false;
  }
  
  public int getDefaultTransactionIsolation()
  {
    debugCodeCall("getDefaultTransactionIsolation");
    return 2;
  }
  
  public boolean supportsMixedCaseIdentifiers()
  {
    debugCodeCall("supportsMixedCaseIdentifiers");
    return false;
  }
  
  public boolean supportsMixedCaseQuotedIdentifiers()
    throws SQLException
  {
    debugCodeCall("supportsMixedCaseQuotedIdentifiers");
    String m = getMode();
    if (m.equals("MySQL")) {
      return false;
    }
    return true;
  }
  
  public boolean storesUpperCaseIdentifiers()
    throws SQLException
  {
    debugCodeCall("storesUpperCaseIdentifiers");
    String m = getMode();
    if (m.equals("MySQL")) {
      return false;
    }
    return true;
  }
  
  public boolean storesLowerCaseIdentifiers()
    throws SQLException
  {
    debugCodeCall("storesLowerCaseIdentifiers");
    String m = getMode();
    if (m.equals("MySQL")) {
      return true;
    }
    return false;
  }
  
  public boolean storesMixedCaseIdentifiers()
  {
    debugCodeCall("storesMixedCaseIdentifiers");
    return false;
  }
  
  public boolean storesUpperCaseQuotedIdentifiers()
    throws SQLException
  {
    debugCodeCall("storesUpperCaseQuotedIdentifiers");
    String m = getMode();
    if (m.equals("MySQL")) {
      return true;
    }
    return false;
  }
  
  public boolean storesLowerCaseQuotedIdentifiers()
    throws SQLException
  {
    debugCodeCall("storesLowerCaseQuotedIdentifiers");
    String m = getMode();
    if (m.equals("MySQL")) {
      return true;
    }
    return false;
  }
  
  public boolean storesMixedCaseQuotedIdentifiers()
    throws SQLException
  {
    debugCodeCall("storesMixedCaseQuotedIdentifiers");
    String m = getMode();
    if (m.equals("MySQL")) {
      return false;
    }
    return true;
  }
  
  public int getMaxBinaryLiteralLength()
  {
    debugCodeCall("getMaxBinaryLiteralLength");
    return 0;
  }
  
  public int getMaxCharLiteralLength()
  {
    debugCodeCall("getMaxCharLiteralLength");
    return 0;
  }
  
  public int getMaxColumnNameLength()
  {
    debugCodeCall("getMaxColumnNameLength");
    return 0;
  }
  
  public int getMaxColumnsInGroupBy()
  {
    debugCodeCall("getMaxColumnsInGroupBy");
    return 0;
  }
  
  public int getMaxColumnsInIndex()
  {
    debugCodeCall("getMaxColumnsInIndex");
    return 0;
  }
  
  public int getMaxColumnsInOrderBy()
  {
    debugCodeCall("getMaxColumnsInOrderBy");
    return 0;
  }
  
  public int getMaxColumnsInSelect()
  {
    debugCodeCall("getMaxColumnsInSelect");
    return 0;
  }
  
  public int getMaxColumnsInTable()
  {
    debugCodeCall("getMaxColumnsInTable");
    return 0;
  }
  
  public int getMaxConnections()
  {
    debugCodeCall("getMaxConnections");
    return 0;
  }
  
  public int getMaxCursorNameLength()
  {
    debugCodeCall("getMaxCursorNameLength");
    return 0;
  }
  
  public int getMaxIndexLength()
  {
    debugCodeCall("getMaxIndexLength");
    return 0;
  }
  
  public int getMaxSchemaNameLength()
  {
    debugCodeCall("getMaxSchemaNameLength");
    return 0;
  }
  
  public int getMaxProcedureNameLength()
  {
    debugCodeCall("getMaxProcedureNameLength");
    return 0;
  }
  
  public int getMaxCatalogNameLength()
  {
    debugCodeCall("getMaxCatalogNameLength");
    return 0;
  }
  
  public int getMaxRowSize()
  {
    debugCodeCall("getMaxRowSize");
    return 0;
  }
  
  public int getMaxStatementLength()
  {
    debugCodeCall("getMaxStatementLength");
    return 0;
  }
  
  public int getMaxStatements()
  {
    debugCodeCall("getMaxStatements");
    return 0;
  }
  
  public int getMaxTableNameLength()
  {
    debugCodeCall("getMaxTableNameLength");
    return 0;
  }
  
  public int getMaxTablesInSelect()
  {
    debugCodeCall("getMaxTablesInSelect");
    return 0;
  }
  
  public int getMaxUserNameLength()
  {
    debugCodeCall("getMaxUserNameLength");
    return 0;
  }
  
  public boolean supportsSavepoints()
  {
    debugCodeCall("supportsSavepoints");
    return true;
  }
  
  public boolean supportsNamedParameters()
  {
    debugCodeCall("supportsNamedParameters");
    return false;
  }
  
  public boolean supportsMultipleOpenResults()
  {
    debugCodeCall("supportsMultipleOpenResults");
    return true;
  }
  
  public boolean supportsGetGeneratedKeys()
  {
    debugCodeCall("supportsGetGeneratedKeys");
    return true;
  }
  
  public ResultSet getSuperTypes(String catalog, String schemaPattern, String typeNamePattern)
    throws SQLException
  {
    throw unsupported("superTypes");
  }
  
  public ResultSet getSuperTables(String catalog, String schemaPattern, String tableNamePattern)
    throws SQLException
  {
    try
    {
      if (isDebugEnabled()) {
        debugCode("getSuperTables(" + quote(catalog) + ", " + quote(schemaPattern) + ", " + quote(tableNamePattern) + ");");
      }
      checkClosed();
      PreparedStatement prep = this.conn.prepareAutoCloseStatement("SELECT CATALOG_NAME TABLE_CAT, CATALOG_NAME TABLE_SCHEM, CATALOG_NAME TABLE_NAME, CATALOG_NAME SUPERTABLE_NAME FROM INFORMATION_SCHEMA.CATALOGS WHERE FALSE");
      
      return prep.executeQuery();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public ResultSet getAttributes(String catalog, String schemaPattern, String typeNamePattern, String attributeNamePattern)
    throws SQLException
  {
    throw unsupported("attributes");
  }
  
  public boolean supportsResultSetHoldability(int holdability)
  {
    debugCodeCall("supportsResultSetHoldability", holdability);
    return holdability == 2;
  }
  
  public int getResultSetHoldability()
  {
    debugCodeCall("getResultSetHoldability");
    return 2;
  }
  
  public int getDatabaseMajorVersion()
  {
    debugCodeCall("getDatabaseMajorVersion");
    return 1;
  }
  
  public int getDatabaseMinorVersion()
  {
    debugCodeCall("getDatabaseMinorVersion");
    return 4;
  }
  
  public int getJDBCMajorVersion()
  {
    debugCodeCall("getJDBCMajorVersion");
    return 4;
  }
  
  public int getJDBCMinorVersion()
  {
    debugCodeCall("getJDBCMinorVersion");
    return 0;
  }
  
  public int getSQLStateType()
  {
    debugCodeCall("getSQLStateType");
    return 2;
  }
  
  public boolean locatorsUpdateCopy()
  {
    debugCodeCall("locatorsUpdateCopy");
    return false;
  }
  
  public boolean supportsStatementPooling()
  {
    debugCodeCall("supportsStatementPooling");
    return false;
  }
  
  private void checkClosed()
  {
    this.conn.checkClosed();
  }
  
  private static String getPattern(String pattern)
  {
    return pattern == null ? "%" : pattern;
  }
  
  private static String getSchemaPattern(String pattern)
  {
    return pattern.length() == 0 ? "PUBLIC" : pattern == null ? "%" : pattern;
  }
  
  private static String getCatalogPattern(String catalogPattern)
  {
    return (catalogPattern == null) || (catalogPattern.length() == 0) ? "%" : catalogPattern;
  }
  
  public RowIdLifetime getRowIdLifetime()
  {
    debugCodeCall("getRowIdLifetime");
    return RowIdLifetime.ROWID_UNSUPPORTED;
  }
  
  public ResultSet getSchemas(String catalogPattern, String schemaPattern)
    throws SQLException
  {
    try
    {
      debugCodeCall("getSchemas(String,String)");
      checkClosed();
      PreparedStatement prep = this.conn.prepareAutoCloseStatement("SELECT SCHEMA_NAME TABLE_SCHEM, CATALOG_NAME TABLE_CATALOG,  IS_DEFAULT FROM INFORMATION_SCHEMA.SCHEMATA WHERE CATALOG_NAME LIKE ? ESCAPE ? AND SCHEMA_NAME LIKE ? ESCAPE ? ORDER BY SCHEMA_NAME");
      
      prep.setString(1, getCatalogPattern(catalogPattern));
      prep.setString(2, "\\");
      prep.setString(3, getSchemaPattern(schemaPattern));
      prep.setString(4, "\\");
      return prep.executeQuery();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean supportsStoredFunctionsUsingCallSyntax()
  {
    debugCodeCall("supportsStoredFunctionsUsingCallSyntax");
    return true;
  }
  
  public boolean autoCommitFailureClosesAllResultSets()
  {
    debugCodeCall("autoCommitFailureClosesAllResultSets");
    return false;
  }
  
  public ResultSet getClientInfoProperties()
    throws SQLException
  {
    return new SimpleResultSet();
  }
  
  public <T> T unwrap(Class<T> iface)
    throws SQLException
  {
    if (isWrapperFor(iface)) {
      return this;
    }
    throw DbException.getInvalidValueException("iface", iface);
  }
  
  public boolean isWrapperFor(Class<?> iface)
    throws SQLException
  {
    return (iface != null) && (iface.isAssignableFrom(getClass()));
  }
  
  public ResultSet getFunctionColumns(String catalog, String schemaPattern, String functionNamePattern, String columnNamePattern)
    throws SQLException
  {
    throw unsupported("getFunctionColumns");
  }
  
  public ResultSet getFunctions(String catalog, String schemaPattern, String functionNamePattern)
    throws SQLException
  {
    throw unsupported("getFunctions");
  }
  
  public String toString()
  {
    return getTraceObjectName() + ": " + this.conn;
  }
  
  private String getMode()
    throws SQLException
  {
    if (this.mode == null)
    {
      PreparedStatement prep = this.conn.prepareStatement("SELECT VALUE FROM INFORMATION_SCHEMA.SETTINGS WHERE NAME=?");
      
      prep.setString(1, "MODE");
      ResultSet rs = prep.executeQuery();
      rs.next();
      this.mode = rs.getString(1);
      prep.close();
    }
    return this.mode;
  }
  
  public ResultSet getPseudoColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern)
    throws SQLException
  {
    return null;
  }
  
  public boolean generatedKeyAlwaysReturned()
    throws SQLException
  {
    return false;
  }
}
