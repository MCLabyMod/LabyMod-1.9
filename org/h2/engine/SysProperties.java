package org.h2.engine;

import org.h2.util.MathUtils;
import org.h2.util.Utils;

public class SysProperties
{
  public static final String H2_SCRIPT_DIRECTORY = "h2.scriptDirectory";
  public static final String H2_BROWSER = "h2.browser";
  public static final String FILE_ENCODING = Utils.getProperty("file.encoding", "Cp1252");
  public static final String FILE_SEPARATOR = Utils.getProperty("file.separator", "/");
  public static final String JAVA_SPECIFICATION_VERSION = Utils.getProperty("java.specification.version", "1.4");
  public static final String LINE_SEPARATOR = Utils.getProperty("line.separator", "\n");
  public static final String USER_HOME = Utils.getProperty("user.home", "");
  public static final String ALLOWED_CLASSES = Utils.getProperty("h2.allowedClasses", "*");
  public static final String BROWSER = Utils.getProperty("h2.browser", null);
  public static final boolean ENABLE_ANONYMOUS_TLS = Utils.getProperty("h2.enableAnonymousTLS", true);
  public static final String BIND_ADDRESS = Utils.getProperty("h2.bindAddress", null);
  public static final boolean CHECK = Utils.getProperty("h2.check", true);
  public static final boolean CHECK2 = Utils.getProperty("h2.check2", false);
  public static final String CLIENT_TRACE_DIRECTORY = Utils.getProperty("h2.clientTraceDirectory", "trace.db/");
  public static final int COLLATOR_CACHE_SIZE = Utils.getProperty("h2.collatorCacheSize", 32000);
  public static final int CONSOLE_MAX_TABLES_LIST_INDEXES = Utils.getProperty("h2.consoleTableIndexes", 100);
  public static final int CONSOLE_MAX_TABLES_LIST_COLUMNS = Utils.getProperty("h2.consoleTableColumns", 300);
  public static final int CONSOLE_MAX_PROCEDURES_LIST_COLUMNS = Utils.getProperty("h2.consoleProcedureColumns", 300);
  public static final boolean CONSOLE_STREAM = Utils.getProperty("h2.consoleStream", true);
  public static final int CONSOLE_TIMEOUT = Utils.getProperty("h2.consoleTimeout", 1800000);
  public static final int DATASOURCE_TRACE_LEVEL = Utils.getProperty("h2.dataSourceTraceLevel", 1);
  public static final int DELAY_WRONG_PASSWORD_MIN = Utils.getProperty("h2.delayWrongPasswordMin", 250);
  public static final int DELAY_WRONG_PASSWORD_MAX = Utils.getProperty("h2.delayWrongPasswordMax", 4000);
  public static final boolean JAVA_SYSTEM_COMPILER = Utils.getProperty("h2.javaSystemCompiler", true);
  public static boolean lobCloseBetweenReads = Utils.getProperty("h2.lobCloseBetweenReads", false);
  public static final int LOB_FILES_PER_DIRECTORY = Utils.getProperty("h2.lobFilesPerDirectory", 256);
  public static final int LOB_CLIENT_MAX_SIZE_MEMORY = Utils.getProperty("h2.lobClientMaxSizeMemory", 1048576);
  public static final int MAX_FILE_RETRY = Math.max(1, Utils.getProperty("h2.maxFileRetry", 16));
  public static final int MAX_RECONNECT = Utils.getProperty("h2.maxReconnect", 3);
  public static final int MAX_MEMORY_ROWS = getAutoScaledForMemoryProperty("h2.maxMemoryRows", 40000);
  public static final long MAX_TRACE_DATA_LENGTH = Utils.getProperty("h2.maxTraceDataLength", 65535);
  public static final boolean MODIFY_ON_WRITE = Utils.getProperty("h2.modifyOnWrite", false);
  public static final boolean NIO_LOAD_MAPPED = Utils.getProperty("h2.nioLoadMapped", false);
  public static final boolean NIO_CLEANER_HACK = Utils.getProperty("h2.nioCleanerHack", false);
  public static final boolean OBJECT_CACHE = Utils.getProperty("h2.objectCache", true);
  public static final int OBJECT_CACHE_MAX_PER_ELEMENT_SIZE = Utils.getProperty("h2.objectCacheMaxPerElementSize", 4096);
  public static final int OBJECT_CACHE_SIZE = MathUtils.nextPowerOf2(Utils.getProperty("h2.objectCacheSize", 1024));
  public static final boolean OLD_STYLE_OUTER_JOIN = Utils.getProperty("h2.oldStyleOuterJoin", false);
  public static final String PG_DEFAULT_CLIENT_ENCODING = Utils.getProperty("h2.pgClientEncoding", "UTF-8");
  public static final String PREFIX_TEMP_FILE = Utils.getProperty("h2.prefixTempFile", "h2.temp");
  public static final int SERVER_CACHED_OBJECTS = Utils.getProperty("h2.serverCachedObjects", 64);
  public static final int SERVER_RESULT_SET_FETCH_SIZE = Utils.getProperty("h2.serverResultSetFetchSize", 100);
  public static final int SOCKET_CONNECT_RETRY = Utils.getProperty("h2.socketConnectRetry", 16);
  public static final int SOCKET_CONNECT_TIMEOUT = Utils.getProperty("h2.socketConnectTimeout", 2000);
  public static final boolean SORT_BINARY_UNSIGNED = Utils.getProperty("h2.sortBinaryUnsigned", true);
  public static final boolean SORT_NULLS_HIGH = Utils.getProperty("h2.sortNullsHigh", false);
  public static final long SPLIT_FILE_SIZE_SHIFT = Utils.getProperty("h2.splitFileSizeShift", 30);
  public static final boolean STORE_LOCAL_TIME = Utils.getProperty("h2.storeLocalTime", true);
  public static final String SYNC_METHOD = Utils.getProperty("h2.syncMethod", "sync");
  public static final boolean TRACE_IO = Utils.getProperty("h2.traceIO", false);
  public static final boolean IMPLICIT_RELATIVE_PATH = Utils.getProperty("h2.implicitRelativePath", false);
  public static final String URL_MAP = Utils.getProperty("h2.urlMap", null);
  public static final boolean USE_THREAD_CONTEXT_CLASS_LOADER = Utils.getProperty("h2.useThreadContextClassLoader", false);
  public static boolean serializeJavaObject = Utils.getProperty("h2.serializeJavaObject", true);
  public static final String JAVA_OBJECT_SERIALIZER = Utils.getProperty("h2.javaObjectSerializer", null);
  private static final String H2_BASE_DIR = "h2.baseDir";
  
  public static void setBaseDir(String dir)
  {
    if (!dir.endsWith("/")) {
      dir = dir + "/";
    }
    System.setProperty("h2.baseDir", dir);
  }
  
  public static String getBaseDir()
  {
    return Utils.getProperty("h2.baseDir", null);
  }
  
  public static String getScriptDirectory()
  {
    return Utils.getProperty("h2.scriptDirectory", "");
  }
  
  private static int getAutoScaledForMemoryProperty(String key, int defaultValue)
  {
    String s = Utils.getProperty(key, null);
    if (s != null) {
      try
      {
        return Integer.decode(s).intValue();
      }
      catch (NumberFormatException e) {}
    }
    return Utils.scaleForAvailableMemory(defaultValue);
  }
}
