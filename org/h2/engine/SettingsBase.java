package org.h2.engine;

import java.util.HashMap;
import org.h2.message.DbException;
import org.h2.util.Utils;

public class SettingsBase
{
  private final HashMap<String, String> settings;
  
  protected SettingsBase(HashMap<String, String> s)
  {
    this.settings = s;
  }
  
  protected boolean get(String key, boolean defaultValue)
  {
    String s = get(key, "" + defaultValue);
    try
    {
      return Boolean.parseBoolean(s);
    }
    catch (NumberFormatException e)
    {
      throw DbException.get(22018, e, new String[] { "key:" + key + " value:" + s });
    }
  }
  
  protected int get(String key, int defaultValue)
  {
    String s = get(key, "" + defaultValue);
    try
    {
      return Integer.decode(s).intValue();
    }
    catch (NumberFormatException e)
    {
      throw DbException.get(22018, e, new String[] { "key:" + key + " value:" + s });
    }
  }
  
  protected String get(String key, String defaultValue)
  {
    StringBuilder buff = new StringBuilder("h2.");
    boolean nextUpper = false;
    for (char c : key.toCharArray()) {
      if (c == '_')
      {
        nextUpper = true;
      }
      else
      {
        buff.append(nextUpper ? Character.toUpperCase(c) : Character.toLowerCase(c));
        nextUpper = false;
      }
    }
    String sysProperty = buff.toString();
    String v = (String)this.settings.get(key);
    if (v == null)
    {
      v = Utils.getProperty(sysProperty, defaultValue);
      this.settings.put(key, v);
    }
    return v;
  }
  
  protected boolean containsKey(String k)
  {
    return this.settings.containsKey(k);
  }
  
  public HashMap<String, String> getSettings()
  {
    return this.settings;
  }
}
