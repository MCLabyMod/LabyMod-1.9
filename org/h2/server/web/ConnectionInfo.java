package org.h2.server.web;

import org.h2.util.MathUtils;
import org.h2.util.StringUtils;

public class ConnectionInfo
  implements Comparable<ConnectionInfo>
{
  public String driver;
  public String url;
  public String user;
  String name;
  int lastAccess;
  
  ConnectionInfo() {}
  
  public ConnectionInfo(String data)
  {
    String[] array = StringUtils.arraySplit(data, '|', false);
    this.name = get(array, 0);
    this.driver = get(array, 1);
    this.url = get(array, 2);
    this.user = get(array, 3);
  }
  
  private static String get(String[] array, int i)
  {
    return (array != null) && (array.length > i) ? array[i] : "";
  }
  
  String getString()
  {
    return StringUtils.arrayCombine(new String[] { this.name, this.driver, this.url, this.user }, '|');
  }
  
  public int compareTo(ConnectionInfo o)
  {
    return -MathUtils.compareInt(this.lastAccess, o.lastAccess);
  }
}
