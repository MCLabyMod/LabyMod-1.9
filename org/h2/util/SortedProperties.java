package org.h2.util;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.TreeMap;
import java.util.Vector;
import org.h2.store.fs.FileUtils;

public class SortedProperties
  extends Properties
{
  private static final long serialVersionUID = 1L;
  
  public synchronized Enumeration<Object> keys()
  {
    Vector<String> v = new Vector();
    for (Object o : keySet()) {
      v.add(o.toString());
    }
    Collections.sort(v);
    return new Vector(v).elements();
  }
  
  public static boolean getBooleanProperty(Properties prop, String key, boolean def)
  {
    String value = prop.getProperty(key, "" + def);
    try
    {
      return Boolean.parseBoolean(value);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return def;
  }
  
  public static int getIntProperty(Properties prop, String key, int def)
  {
    String value = prop.getProperty(key, "" + def);
    try
    {
      return Integer.decode(value).intValue();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return def;
  }
  
  public static synchronized SortedProperties loadProperties(String fileName)
    throws IOException
  {
    SortedProperties prop = new SortedProperties();
    if (FileUtils.exists(fileName))
    {
      InputStream in = null;
      try
      {
        in = FileUtils.newInputStream(fileName);
        prop.load(in);
      }
      finally
      {
        if (in != null) {
          in.close();
        }
      }
    }
    return prop;
  }
  
  public synchronized void store(String fileName)
    throws IOException
  {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    store(out, null);
    ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
    InputStreamReader reader = new InputStreamReader(in, "ISO8859-1");
    LineNumberReader r = new LineNumberReader(reader);
    Writer w;
    try
    {
      w = new OutputStreamWriter(FileUtils.newOutputStream(fileName, false));
    }
    catch (Exception e)
    {
      throw new IOException(e.toString(), e);
    }
    PrintWriter writer = new PrintWriter(new BufferedWriter(w));
    for (;;)
    {
      String line = r.readLine();
      if (line == null) {
        break;
      }
      if (!line.startsWith("#")) {
        writer.print(line + "\n");
      }
    }
    writer.close();
  }
  
  public synchronized String toLines()
  {
    StringBuilder buff = new StringBuilder();
    for (Map.Entry<Object, Object> e : new TreeMap(this).entrySet()) {
      buff.append(e.getKey()).append('=').append(e.getValue()).append('\n');
    }
    return buff.toString();
  }
  
  public static SortedProperties fromLines(String s)
  {
    SortedProperties p = new SortedProperties();
    for (String line : StringUtils.arraySplit(s, '\n', true))
    {
      int idx = line.indexOf('=');
      if (idx > 0) {
        p.put(line.substring(0, idx), line.substring(idx + 1));
      }
    }
    return p;
  }
}
