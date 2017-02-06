package org.h2.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import org.h2.command.dml.SetTypes;
import org.h2.message.DbException;
import org.h2.security.SHA256;
import org.h2.store.fs.FilePathEncrypt;
import org.h2.store.fs.FilePathRec;
import org.h2.store.fs.FileUtils;
import org.h2.util.New;
import org.h2.util.SortedProperties;
import org.h2.util.StringUtils;
import org.h2.util.Utils;

public class ConnectionInfo
  implements Cloneable
{
  private static final HashSet<String> KNOWN_SETTINGS = ;
  private Properties prop = new Properties();
  private String originalURL;
  private String url;
  private String user;
  private byte[] filePasswordHash;
  private byte[] fileEncryptionKey;
  private byte[] userPasswordHash;
  private String name;
  private String nameNormalized;
  private boolean remote;
  private boolean ssl;
  private boolean persistent;
  private boolean unnamed;
  
  public ConnectionInfo(String name)
  {
    this.name = name;
    this.url = ("jdbc:h2:" + name);
    parseName();
  }
  
  public ConnectionInfo(String u, Properties info)
  {
    u = remapURL(u);
    this.originalURL = u;
    if (!u.startsWith("jdbc:h2:")) {
      throw DbException.getInvalidValueException("url", u);
    }
    this.url = u;
    readProperties(info);
    readSettingsFromURL();
    setUserName(removeProperty("USER", ""));
    convertPasswords();
    this.name = this.url.substring("jdbc:h2:".length());
    parseName();
    String recoverTest = removeProperty("RECOVER_TEST", null);
    if (recoverTest != null)
    {
      FilePathRec.register();
      try
      {
        Utils.callStaticMethod("org.h2.store.RecoverTester.init", new Object[] { recoverTest });
      }
      catch (Exception e)
      {
        throw DbException.convert(e);
      }
      this.name = ("rec:" + this.name);
    }
  }
  
  static
  {
    ArrayList<String> list = SetTypes.getTypes();
    HashSet<String> set = KNOWN_SETTINGS;
    set.addAll(list);
    String[] connectionTime = { "ACCESS_MODE_DATA", "AUTOCOMMIT", "CIPHER", "CREATE", "CACHE_TYPE", "FILE_LOCK", "IGNORE_UNKNOWN_SETTINGS", "IFEXISTS", "INIT", "PASSWORD", "RECOVER", "RECOVER_TEST", "USER", "AUTO_SERVER", "AUTO_SERVER_PORT", "NO_UPGRADE", "AUTO_RECONNECT", "OPEN_NEW", "PAGE_SIZE", "PASSWORD_HASH", "JMX" };
    for (String key : connectionTime)
    {
      if ((SysProperties.CHECK) && (set.contains(key))) {
        DbException.throwInternalError(key);
      }
      set.add(key);
    }
  }
  
  private static boolean isKnownSetting(String s)
  {
    return KNOWN_SETTINGS.contains(s);
  }
  
  public ConnectionInfo clone()
    throws CloneNotSupportedException
  {
    ConnectionInfo clone = (ConnectionInfo)super.clone();
    clone.prop = ((Properties)this.prop.clone());
    clone.filePasswordHash = Utils.cloneByteArray(this.filePasswordHash);
    clone.fileEncryptionKey = Utils.cloneByteArray(this.fileEncryptionKey);
    clone.userPasswordHash = Utils.cloneByteArray(this.userPasswordHash);
    return clone;
  }
  
  private void parseName()
  {
    if (".".equals(this.name)) {
      this.name = "mem:";
    }
    if (this.name.startsWith("tcp:"))
    {
      this.remote = true;
      this.name = this.name.substring("tcp:".length());
    }
    else if (this.name.startsWith("ssl:"))
    {
      this.remote = true;
      this.ssl = true;
      this.name = this.name.substring("ssl:".length());
    }
    else if (this.name.startsWith("mem:"))
    {
      this.persistent = false;
      if ("mem:".equals(this.name)) {
        this.unnamed = true;
      }
    }
    else if (this.name.startsWith("file:"))
    {
      this.name = this.name.substring("file:".length());
      this.persistent = true;
    }
    else
    {
      this.persistent = true;
    }
    if ((this.persistent) && (!this.remote)) {
      if ("/".equals(SysProperties.FILE_SEPARATOR)) {
        this.name = this.name.replace('\\', '/');
      } else {
        this.name = this.name.replace('/', '\\');
      }
    }
  }
  
  public void setBaseDir(String dir)
  {
    if (this.persistent)
    {
      String absDir = FileUtils.unwrap(FileUtils.toRealPath(dir));
      boolean absolute = FileUtils.isAbsolute(this.name);
      
      String prefix = null;
      if (dir.endsWith(SysProperties.FILE_SEPARATOR)) {
        dir = dir.substring(0, dir.length() - 1);
      }
      String n;
      String n;
      if (absolute)
      {
        n = this.name;
      }
      else
      {
        n = FileUtils.unwrap(this.name);
        prefix = this.name.substring(0, this.name.length() - n.length());
        n = dir + SysProperties.FILE_SEPARATOR + n;
      }
      String normalizedName = FileUtils.unwrap(FileUtils.toRealPath(n));
      if ((normalizedName.equals(absDir)) || (!normalizedName.startsWith(absDir))) {
        throw DbException.get(90028, normalizedName + " outside " + absDir);
      }
      if ((!absDir.endsWith("/")) && (!absDir.endsWith("\\"))) {
        if (normalizedName.charAt(absDir.length()) != '/') {
          throw DbException.get(90028, normalizedName + " outside " + absDir);
        }
      }
      if (!absolute) {
        this.name = (prefix + dir + SysProperties.FILE_SEPARATOR + FileUtils.unwrap(this.name));
      }
    }
  }
  
  public boolean isRemote()
  {
    return this.remote;
  }
  
  public boolean isPersistent()
  {
    return this.persistent;
  }
  
  boolean isUnnamedInMemory()
  {
    return this.unnamed;
  }
  
  private void readProperties(Properties info)
  {
    Object[] list = new Object[info.size()];
    info.keySet().toArray(list);
    DbSettings s = null;
    for (Object k : list)
    {
      String key = StringUtils.toUpperEnglish(k.toString());
      if (this.prop.containsKey(key)) {
        throw DbException.get(90066, key);
      }
      Object value = info.get(k);
      if (isKnownSetting(key))
      {
        this.prop.put(key, value);
      }
      else
      {
        if (s == null) {
          s = getDbSettings();
        }
        if (s.containsKey(key)) {
          this.prop.put(key, value);
        }
      }
    }
  }
  
  private void readSettingsFromURL()
  {
    DbSettings defaultSettings = DbSettings.getDefaultSettings();
    int idx = this.url.indexOf(';');
    if (idx >= 0)
    {
      String settings = this.url.substring(idx + 1);
      this.url = this.url.substring(0, idx);
      String[] list = StringUtils.arraySplit(settings, ';', false);
      for (String setting : list) {
        if (setting.length() != 0)
        {
          int equal = setting.indexOf('=');
          if (equal < 0) {
            throw getFormatException();
          }
          String value = setting.substring(equal + 1);
          String key = setting.substring(0, equal);
          key = StringUtils.toUpperEnglish(key);
          if ((!isKnownSetting(key)) && (!defaultSettings.containsKey(key))) {
            throw DbException.get(90113, key);
          }
          String old = this.prop.getProperty(key);
          if ((old != null) && (!old.equals(value))) {
            throw DbException.get(90066, key);
          }
          this.prop.setProperty(key, value);
        }
      }
    }
  }
  
  private char[] removePassword()
  {
    Object p = this.prop.remove("PASSWORD");
    if (p == null) {
      return new char[0];
    }
    if ((p instanceof char[])) {
      return (char[])p;
    }
    return p.toString().toCharArray();
  }
  
  private void convertPasswords()
  {
    char[] password = removePassword();
    boolean passwordHash = removeProperty("PASSWORD_HASH", false);
    if (getProperty("CIPHER", null) != null)
    {
      int space = -1;
      int i = 0;
      for (int len = password.length; i < len; i++) {
        if (password[i] == ' ')
        {
          space = i;
          break;
        }
      }
      if (space < 0) {
        throw DbException.get(90050);
      }
      char[] np = new char[password.length - space - 1];
      char[] filePassword = new char[space];
      System.arraycopy(password, space + 1, np, 0, np.length);
      System.arraycopy(password, 0, filePassword, 0, space);
      Arrays.fill(password, '\000');
      password = np;
      this.fileEncryptionKey = FilePathEncrypt.getPasswordBytes(filePassword);
      this.filePasswordHash = hashPassword(passwordHash, "file", filePassword);
    }
    this.userPasswordHash = hashPassword(passwordHash, this.user, password);
  }
  
  private static byte[] hashPassword(boolean passwordHash, String userName, char[] password)
  {
    if (passwordHash) {
      return StringUtils.convertHexToBytes(new String(password));
    }
    if ((userName.length() == 0) && (password.length == 0)) {
      return new byte[0];
    }
    return SHA256.getKeyPasswordHash(userName, password);
  }
  
  boolean getProperty(String key, boolean defaultValue)
  {
    String x = getProperty(key, null);
    if (x == null) {
      return defaultValue;
    }
    if ((x.length() == 1) && (Character.isDigit(x.charAt(0)))) {
      return Integer.parseInt(x) != 0;
    }
    return Boolean.parseBoolean(x);
  }
  
  public boolean removeProperty(String key, boolean defaultValue)
  {
    String x = removeProperty(key, null);
    return x == null ? defaultValue : Boolean.parseBoolean(x);
  }
  
  String removeProperty(String key, String defaultValue)
  {
    if ((SysProperties.CHECK) && (!isKnownSetting(key))) {
      DbException.throwInternalError(key);
    }
    Object x = this.prop.remove(key);
    return x == null ? defaultValue : x.toString();
  }
  
  public String getName()
  {
    if (this.persistent)
    {
      if (this.nameNormalized == null)
      {
        if ((!SysProperties.IMPLICIT_RELATIVE_PATH) && 
          (!FileUtils.isAbsolute(this.name)) && 
          (this.name.indexOf("./") < 0) && (this.name.indexOf(".\\") < 0) && (this.name.indexOf(":/") < 0) && (this.name.indexOf(":\\") < 0)) {
          throw DbException.get(90011, this.originalURL);
        }
        String suffix = ".h2.db";
        String n;
        String n;
        if (FileUtils.exists(this.name + suffix))
        {
          n = FileUtils.toRealPath(this.name + suffix);
        }
        else
        {
          suffix = ".mv.db";
          n = FileUtils.toRealPath(this.name + suffix);
        }
        String fileName = FileUtils.getName(n);
        if (fileName.length() < suffix.length() + 1) {
          throw DbException.get(90138, this.name);
        }
        this.nameNormalized = n.substring(0, n.length() - suffix.length());
      }
      return this.nameNormalized;
    }
    return this.name;
  }
  
  public byte[] getFilePasswordHash()
  {
    return this.filePasswordHash;
  }
  
  byte[] getFileEncryptionKey()
  {
    return this.fileEncryptionKey;
  }
  
  public String getUserName()
  {
    return this.user;
  }
  
  byte[] getUserPasswordHash()
  {
    return this.userPasswordHash;
  }
  
  String[] getKeys()
  {
    String[] keys = new String[this.prop.size()];
    this.prop.keySet().toArray(keys);
    return keys;
  }
  
  String getProperty(String key)
  {
    Object value = this.prop.get(key);
    if ((value == null) || (!(value instanceof String))) {
      return null;
    }
    return value.toString();
  }
  
  int getProperty(String key, int defaultValue)
  {
    if ((SysProperties.CHECK) && (!isKnownSetting(key))) {
      DbException.throwInternalError(key);
    }
    String s = getProperty(key);
    return s == null ? defaultValue : Integer.parseInt(s);
  }
  
  public String getProperty(String key, String defaultValue)
  {
    if ((SysProperties.CHECK) && (!isKnownSetting(key))) {
      DbException.throwInternalError(key);
    }
    String s = getProperty(key);
    return s == null ? defaultValue : s;
  }
  
  String getProperty(int setting, String defaultValue)
  {
    String key = SetTypes.getTypeName(setting);
    String s = getProperty(key);
    return s == null ? defaultValue : s;
  }
  
  int getIntProperty(int setting, int defaultValue)
  {
    String key = SetTypes.getTypeName(setting);
    String s = getProperty(key, null);
    try
    {
      return s == null ? defaultValue : Integer.decode(s).intValue();
    }
    catch (NumberFormatException e) {}
    return defaultValue;
  }
  
  boolean isSSL()
  {
    return this.ssl;
  }
  
  public void setUserName(String name)
  {
    this.user = StringUtils.toUpperEnglish(name);
  }
  
  public void setUserPasswordHash(byte[] hash)
  {
    this.userPasswordHash = hash;
  }
  
  public void setFilePasswordHash(byte[] hash)
  {
    this.filePasswordHash = hash;
  }
  
  public void setFileEncryptionKey(byte[] key)
  {
    this.fileEncryptionKey = key;
  }
  
  public void setProperty(String key, String value)
  {
    if (value != null) {
      this.prop.setProperty(key, value);
    }
  }
  
  public String getURL()
  {
    return this.url;
  }
  
  public String getOriginalURL()
  {
    return this.originalURL;
  }
  
  public void setOriginalURL(String url)
  {
    this.originalURL = url;
  }
  
  DbException getFormatException()
  {
    String format = "jdbc:h2:{ {.|mem:}[name] | [file:]fileName | {tcp|ssl}:[//]server[:port][,server2[:port]]/name }[;key=value...]";
    return DbException.get(90046, new String[] { format, this.url });
  }
  
  public void setServerKey(String serverKey)
  {
    this.remote = true;
    this.persistent = false;
    this.name = serverKey;
  }
  
  public DbSettings getDbSettings()
  {
    DbSettings defaultSettings = DbSettings.getDefaultSettings();
    HashMap<String, String> s = New.hashMap();
    for (Object k : this.prop.keySet())
    {
      String key = k.toString();
      if ((!isKnownSetting(key)) && (defaultSettings.containsKey(key))) {
        s.put(key, this.prop.getProperty(key));
      }
    }
    return DbSettings.getInstance(s);
  }
  
  private static String remapURL(String url)
  {
    String urlMap = SysProperties.URL_MAP;
    if ((urlMap != null) && (urlMap.length() > 0)) {
      try
      {
        SortedProperties prop = SortedProperties.loadProperties(urlMap);
        String url2 = prop.getProperty(url);
        if (url2 == null)
        {
          prop.put(url, "");
          prop.store(urlMap);
        }
        else
        {
          url2 = url2.trim();
          if (url2.length() > 0) {
            return url2;
          }
        }
      }
      catch (IOException e)
      {
        throw DbException.convert(e);
      }
    }
    return url;
  }
}
