package org.h2.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Utils
{
  public static final byte[] EMPTY_BYTES = new byte[0];
  public static final int[] EMPTY_INT_ARRAY = new int[0];
  private static final long[] EMPTY_LONG_ARRAY = new long[0];
  private static final int GC_DELAY = 50;
  private static final int MAX_GC = 8;
  private static long lastGC;
  private static final HashMap<String, byte[]> RESOURCES = New.hashMap();
  
  private static int readInt(byte[] buff, int pos)
  {
    return (buff[(pos++)] << 24) + ((buff[(pos++)] & 0xFF) << 16) + ((buff[(pos++)] & 0xFF) << 8) + (buff[pos] & 0xFF);
  }
  
  public static void writeLong(byte[] buff, int pos, long x)
  {
    writeInt(buff, pos, (int)(x >> 32));
    writeInt(buff, pos + 4, (int)x);
  }
  
  private static void writeInt(byte[] buff, int pos, int x)
  {
    buff[(pos++)] = ((byte)(x >> 24));
    buff[(pos++)] = ((byte)(x >> 16));
    buff[(pos++)] = ((byte)(x >> 8));
    buff[(pos++)] = ((byte)x);
  }
  
  public static long readLong(byte[] buff, int pos)
  {
    return (readInt(buff, pos) << 32) + (readInt(buff, pos + 4) & 0xFFFFFFFF);
  }
  
  public static int indexOf(byte[] bytes, byte[] pattern, int start)
  {
    if (pattern.length == 0) {
      return start;
    }
    if (start > bytes.length) {
      return -1;
    }
    int last = bytes.length - pattern.length + 1;
    int patternLen = pattern.length;
    label66:
    for (; start < last; start++)
    {
      for (int i = 0; i < patternLen; i++) {
        if (bytes[(start + i)] != pattern[i]) {
          break label66;
        }
      }
      return start;
    }
    return -1;
  }
  
  public static int getByteArrayHash(byte[] value)
  {
    int len = value.length;
    int h = len;
    if (len < 50)
    {
      for (int i = 0; i < len; i++) {
        h = 31 * h + value[i];
      }
    }
    else
    {
      int step = len / 16;
      for (int i = 0; i < 4; i++)
      {
        h = 31 * h + value[i];
        h = 31 * h + value[(--len)];
      }
      for (int i = 4 + step; i < len; i += step) {
        h = 31 * h + value[i];
      }
    }
    return h;
  }
  
  public static boolean compareSecure(byte[] test, byte[] good)
  {
    if ((test == null) || (good == null)) {
      return (test == null) && (good == null);
    }
    int len = test.length;
    if (len != good.length) {
      return false;
    }
    if (len == 0) {
      return true;
    }
    int bits = 0;
    for (int i = 0; i < len; i++) {
      bits |= test[i] ^ good[i];
    }
    return bits == 0;
  }
  
  public static int compareNotNullSigned(byte[] data1, byte[] data2)
  {
    if (data1 == data2) {
      return 0;
    }
    int len = Math.min(data1.length, data2.length);
    for (int i = 0; i < len; i++)
    {
      byte b = data1[i];
      byte b2 = data2[i];
      if (b != b2) {
        return b > b2 ? 1 : -1;
      }
    }
    return Integer.signum(data1.length - data2.length);
  }
  
  public static int compareNotNullUnsigned(byte[] data1, byte[] data2)
  {
    if (data1 == data2) {
      return 0;
    }
    int len = Math.min(data1.length, data2.length);
    for (int i = 0; i < len; i++)
    {
      int b = data1[i] & 0xFF;
      int b2 = data2[i] & 0xFF;
      if (b != b2) {
        return b > b2 ? 1 : -1;
      }
    }
    return Integer.signum(data1.length - data2.length);
  }
  
  public static byte[] copy(byte[] source, byte[] target)
  {
    int len = source.length;
    if (len > target.length) {
      target = new byte[len];
    }
    System.arraycopy(source, 0, target, 0, len);
    return target;
  }
  
  public static byte[] cloneByteArray(byte[] b)
  {
    if (b == null) {
      return null;
    }
    int len = b.length;
    if (len == 0) {
      return EMPTY_BYTES;
    }
    byte[] copy = new byte[len];
    System.arraycopy(b, 0, copy, 0, len);
    return copy;
  }
  
  public static int hashCode(Object o)
  {
    return o == null ? 0 : o.hashCode();
  }
  
  public static int getMemoryUsed()
  {
    collectGarbage();
    Runtime rt = Runtime.getRuntime();
    long mem = rt.totalMemory() - rt.freeMemory();
    return (int)(mem >> 10);
  }
  
  public static int getMemoryFree()
  {
    collectGarbage();
    Runtime rt = Runtime.getRuntime();
    long mem = rt.freeMemory();
    return (int)(mem >> 10);
  }
  
  public static long getMemoryMax()
  {
    long max = Runtime.getRuntime().maxMemory();
    return max / 1024L;
  }
  
  private static synchronized void collectGarbage()
  {
    Runtime runtime = Runtime.getRuntime();
    long total = runtime.totalMemory();
    long time = System.currentTimeMillis();
    if (lastGC + 50L < time) {
      for (int i = 0; i < 8; i++)
      {
        runtime.gc();
        long now = runtime.totalMemory();
        if (now == total)
        {
          lastGC = System.currentTimeMillis();
          break;
        }
        total = now;
      }
    }
  }
  
  public static int[] newIntArray(int len)
  {
    if (len == 0) {
      return EMPTY_INT_ARRAY;
    }
    return new int[len];
  }
  
  public static long[] newLongArray(int len)
  {
    if (len == 0) {
      return EMPTY_LONG_ARRAY;
    }
    return new long[len];
  }
  
  public static <X> void sortTopN(X[] array, int offset, int limit, Comparator<? super X> comp)
  {
    partitionTopN(array, offset, limit, comp);
    Arrays.sort(array, offset, (int)Math.min(offset + limit, array.length), comp);
  }
  
  private static <X> void partitionTopN(X[] array, int offset, int limit, Comparator<? super X> comp)
  {
    partialQuickSort(array, 0, array.length - 1, comp, offset, offset + limit - 1);
  }
  
  private static <X> void partialQuickSort(X[] array, int low, int high, Comparator<? super X> comp, int start, int end)
  {
    if ((low > end) || (high < start) || ((low > start) && (high < end))) {
      return;
    }
    if (low == high) {
      return;
    }
    int i = low;int j = high;
    
    int p = low + MathUtils.randomInt(high - low);
    X pivot = array[p];
    int m = low + high >>> 1;
    X temp = array[m];
    array[m] = pivot;
    array[p] = temp;
    while (i <= j)
    {
      while (comp.compare(array[i], pivot) < 0) {
        i++;
      }
      while (comp.compare(array[j], pivot) > 0) {
        j--;
      }
      if (i <= j)
      {
        temp = array[i];
        array[(i++)] = array[j];
        array[(j--)] = temp;
      }
    }
    if (low < j) {
      partialQuickSort(array, low, j, comp, start, end);
    }
    if (i < high) {
      partialQuickSort(array, i, high, comp, start, end);
    }
  }
  
  public static boolean haveCommonComparableSuperclass(Class<?> c1, Class<?> c2)
  {
    if ((c1 == c2) || (c1.isAssignableFrom(c2)) || (c2.isAssignableFrom(c1))) {
      return true;
    }
    Class<?> top1;
    do
    {
      top1 = c1;
      c1 = c1.getSuperclass();
    } while (Comparable.class.isAssignableFrom(c1));
    Class<?> top2;
    do
    {
      top2 = c2;
      c2 = c2.getSuperclass();
    } while (Comparable.class.isAssignableFrom(c2));
    return top1 == top2;
  }
  
  public static byte[] getResource(String name)
    throws IOException
  {
    byte[] data = (byte[])RESOURCES.get(name);
    if (data == null)
    {
      data = loadResource(name);
      if (data != null) {
        RESOURCES.put(name, data);
      }
    }
    return data;
  }
  
  private static byte[] loadResource(String name)
    throws IOException
  {
    InputStream in = Utils.class.getResourceAsStream("data.zip");
    if (in == null)
    {
      in = Utils.class.getResourceAsStream(name);
      if (in == null) {
        return null;
      }
      return IOUtils.readBytesAndClose(in, 0);
    }
    ZipInputStream zipIn = new ZipInputStream(in);
    try
    {
      for (;;)
      {
        ZipEntry entry = zipIn.getNextEntry();
        if (entry == null) {
          break;
        }
        String entryName = entry.getName();
        if (!entryName.startsWith("/")) {
          entryName = "/" + entryName;
        }
        if (entryName.equals(name))
        {
          ByteArrayOutputStream out = new ByteArrayOutputStream();
          IOUtils.copy(zipIn, out);
          zipIn.closeEntry();
          return out.toByteArray();
        }
        zipIn.closeEntry();
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    finally
    {
      zipIn.close();
    }
    return null;
  }
  
  public static Object callStaticMethod(String classAndMethod, Object... params)
    throws Exception
  {
    int lastDot = classAndMethod.lastIndexOf('.');
    String className = classAndMethod.substring(0, lastDot);
    String methodName = classAndMethod.substring(lastDot + 1);
    return callMethod(null, Class.forName(className), methodName, params);
  }
  
  public static Object callMethod(Object instance, String methodName, Object... params)
    throws Exception
  {
    return callMethod(instance, instance.getClass(), methodName, params);
  }
  
  private static Object callMethod(Object instance, Class<?> clazz, String methodName, Object... params)
    throws Exception
  {
    Method best = null;
    int bestMatch = 0;
    boolean isStatic = instance == null;
    for (Method m : clazz.getMethods()) {
      if ((Modifier.isStatic(m.getModifiers()) == isStatic) && (m.getName().equals(methodName)))
      {
        int p = match(m.getParameterTypes(), params);
        if (p > bestMatch)
        {
          bestMatch = p;
          best = m;
        }
      }
    }
    if (best == null) {
      throw new NoSuchMethodException(methodName);
    }
    return best.invoke(instance, params);
  }
  
  public static Object newInstance(String className, Object... params)
    throws Exception
  {
    Constructor<?> best = null;
    int bestMatch = 0;
    for (Constructor<?> c : Class.forName(className).getConstructors())
    {
      int p = match(c.getParameterTypes(), params);
      if (p > bestMatch)
      {
        bestMatch = p;
        best = c;
      }
    }
    if (best == null) {
      throw new NoSuchMethodException(className);
    }
    return best.newInstance(params);
  }
  
  private static int match(Class<?>[] params, Object[] values)
  {
    int len = params.length;
    if (len == values.length)
    {
      int points = 1;
      for (int i = 0; i < len; i++)
      {
        Class<?> pc = getNonPrimitiveClass(params[i]);
        Object v = values[i];
        Class<?> vc = v == null ? null : v.getClass();
        if (pc == vc) {
          points++;
        } else if (vc != null) {
          if (!pc.isAssignableFrom(vc)) {
            return 0;
          }
        }
      }
      return points;
    }
    return 0;
  }
  
  public static Object getStaticField(String classAndField)
    throws Exception
  {
    int lastDot = classAndField.lastIndexOf('.');
    String className = classAndField.substring(0, lastDot);
    String fieldName = classAndField.substring(lastDot + 1);
    return Class.forName(className).getField(fieldName).get(null);
  }
  
  public static Object getField(Object instance, String fieldName)
    throws Exception
  {
    return instance.getClass().getField(fieldName).get(instance);
  }
  
  public static boolean isClassPresent(String fullyQualifiedClassName)
  {
    try
    {
      Class.forName(fullyQualifiedClassName);
      return true;
    }
    catch (ClassNotFoundException e) {}
    return false;
  }
  
  public static Class<?> getNonPrimitiveClass(Class<?> clazz)
  {
    if (!clazz.isPrimitive()) {
      return clazz;
    }
    if (clazz == Boolean.TYPE) {
      return Boolean.class;
    }
    if (clazz == Byte.TYPE) {
      return Byte.class;
    }
    if (clazz == Character.TYPE) {
      return Character.class;
    }
    if (clazz == Double.TYPE) {
      return Double.class;
    }
    if (clazz == Float.TYPE) {
      return Float.class;
    }
    if (clazz == Integer.TYPE) {
      return Integer.class;
    }
    if (clazz == Long.TYPE) {
      return Long.class;
    }
    if (clazz == Short.TYPE) {
      return Short.class;
    }
    if (clazz == Void.TYPE) {
      return Void.class;
    }
    return clazz;
  }
  
  public static String getProperty(String key, String defaultValue)
  {
    try
    {
      return System.getProperty(key, defaultValue);
    }
    catch (SecurityException se) {}
    return defaultValue;
  }
  
  public static int getProperty(String key, int defaultValue)
  {
    String s = getProperty(key, null);
    if (s != null) {
      try
      {
        return Integer.decode(s).intValue();
      }
      catch (NumberFormatException e) {}
    }
    return defaultValue;
  }
  
  public static boolean getProperty(String key, boolean defaultValue)
  {
    String s = getProperty(key, null);
    if (s != null) {
      try
      {
        return Boolean.parseBoolean(s);
      }
      catch (NumberFormatException e) {}
    }
    return defaultValue;
  }
  
  public static int scaleForAvailableMemory(int value)
  {
    long maxMemory = Runtime.getRuntime().maxMemory();
    if (maxMemory != Long.MAX_VALUE) {
      return (int)(value * maxMemory / 1073741824L);
    }
    try
    {
      OperatingSystemMXBean mxBean = ManagementFactory.getOperatingSystemMXBean();
      
      Method method = Class.forName("com.sun.management.OperatingSystemMXBean").getMethod("getTotalPhysicalMemorySize", new Class[0]);
      
      long physicalMemorySize = ((Number)method.invoke(mxBean, new Object[0])).longValue();
      return (int)(value * physicalMemorySize / 1073741824L);
    }
    catch (Exception e) {}
    return value;
  }
  
  public static abstract interface ClassFactory
  {
    public abstract boolean match(String paramString);
    
    public abstract Class<?> loadClass(String paramString)
      throws ClassNotFoundException;
  }
}
