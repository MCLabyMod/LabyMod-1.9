package org.h2.engine;

import java.util.HashMap;

public class DbSettings
  extends SettingsBase
{
  private static DbSettings defaultSettings;
  public final boolean aliasColumnName = get("ALIAS_COLUMN_NAME", false);
  public final int analyzeAuto = get("ANALYZE_AUTO", 2000);
  public final int analyzeSample = get("ANALYZE_SAMPLE", 10000);
  public final boolean databaseToUpper = get("DATABASE_TO_UPPER", true);
  public final boolean dbCloseOnExit = get("DB_CLOSE_ON_EXIT", true);
  public boolean defaultConnection = get("DEFAULT_CONNECTION", false);
  public final String defaultEscape = get("DEFAULT_ESCAPE", "\\");
  public final boolean defragAlways = get("DEFRAG_ALWAYS", false);
  public final boolean dropRestrict = get("DROP_RESTRICT", true);
  public final boolean earlyFilter = get("EARLY_FILTER", false);
  public final int estimatedFunctionTableRows = get("ESTIMATED_FUNCTION_TABLE_ROWS", 1000);
  public final boolean functionsInSchema = get("FUNCTIONS_IN_SCHEMA", true);
  public final boolean largeTransactions = get("LARGE_TRANSACTIONS", true);
  public final int lobTimeout = get("LOB_TIMEOUT", 300000);
  public final int maxCompactCount = get("MAX_COMPACT_COUNT", Integer.MAX_VALUE);
  public final int maxCompactTime = get("MAX_COMPACT_TIME", 200);
  public int maxQueryTimeout = get("MAX_QUERY_TIMEOUT", 0);
  public final boolean nestedJoins = get("NESTED_JOINS", true);
  public final boolean optimizeDistinct = get("OPTIMIZE_DISTINCT", true);
  public final boolean optimizeEvaluatableSubqueries = get("OPTIMIZE_EVALUATABLE_SUBQUERIES", true);
  public final boolean optimizeInsertFromSelect = get("OPTIMIZE_INSERT_FROM_SELECT", true);
  public final boolean optimizeInList = get("OPTIMIZE_IN_LIST", true);
  public final boolean optimizeInSelect = get("OPTIMIZE_IN_SELECT", true);
  public final boolean optimizeIsNull = get("OPTIMIZE_IS_NULL", true);
  public final boolean optimizeOr = get("OPTIMIZE_OR", true);
  public final boolean optimizeTwoEquals = get("OPTIMIZE_TWO_EQUALS", true);
  public final boolean optimizeUpdate = get("OPTIMIZE_UPDATE", true);
  public final int pageStoreMaxGrowth = get("PAGE_STORE_MAX_GROWTH", 131072);
  public final boolean pageStoreInternalCount = get("PAGE_STORE_INTERNAL_COUNT", false);
  public final boolean pageStoreTrim = get("PAGE_STORE_TRIM", true);
  public final int queryCacheSize = get("QUERY_CACHE_SIZE", 8);
  public final boolean recompileAlways = get("RECOMPILE_ALWAYS", false);
  public final int reconnectCheckDelay = get("RECONNECT_CHECK_DELAY", 200);
  public final boolean reuseSpace = get("REUSE_SPACE", true);
  public final boolean rowId = get("ROWID", true);
  public final boolean selectForUpdateMvcc = get("SELECT_FOR_UPDATE_MVCC", true);
  public final boolean shareLinkedConnections = get("SHARE_LINKED_CONNECTIONS", true);
  public String defaultTableEngine = get("DEFAULT_TABLE_ENGINE", null);
  public boolean mvStore = get("MV_STORE", true);
  public final boolean compressData = get("COMPRESS", false);
  
  private DbSettings(HashMap<String, String> s)
  {
    super(s);
  }
  
  public static DbSettings getInstance(HashMap<String, String> s)
  {
    return new DbSettings(s);
  }
  
  public static DbSettings getDefaultSettings()
  {
    if (defaultSettings == null) {
      defaultSettings = new DbSettings(new HashMap());
    }
    return defaultSettings;
  }
}
