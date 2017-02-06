package org.h2.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.security.SecureRandom;
import java.util.Properties;
import java.util.Random;

public class MathUtils
{
  static SecureRandom cachedSecureRandom;
  static volatile boolean seeded;
  private static final Random RANDOM = new Random();
  
  public static int roundUpInt(int x, int blockSizePowerOf2)
  {
    return x + blockSizePowerOf2 - 1 & -blockSizePowerOf2;
  }
  
  public static long roundUpLong(long x, long blockSizePowerOf2)
  {
    return x + blockSizePowerOf2 - 1L & -blockSizePowerOf2;
  }
  
  private static synchronized SecureRandom getSecureRandom()
  {
    if (cachedSecureRandom != null) {
      return cachedSecureRandom;
    }
    try
    {
      cachedSecureRandom = SecureRandom.getInstance("SHA1PRNG");
      
      Runnable runnable = new Runnable()
      {
        public void run()
        {
          try
          {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            byte[] seed = sr.generateSeed(20);
            synchronized (MathUtils.cachedSecureRandom)
            {
              MathUtils.cachedSecureRandom.setSeed(seed);
              MathUtils.seeded = true;
            }
          }
          catch (Exception e)
          {
            MathUtils.warn("SecureRandom", e);
          }
        }
      };
      try
      {
        Thread t = new Thread(runnable, "Generate Seed");
        
        t.setDaemon(true);
        t.start();
        Thread.yield();
        try
        {
          t.join(400L);
        }
        catch (InterruptedException e)
        {
          warn("InterruptedException", e);
        }
        if (!seeded)
        {
          byte[] seed = generateAlternativeSeed();
          synchronized (cachedSecureRandom)
          {
            cachedSecureRandom.setSeed(seed);
          }
        }
      }
      catch (SecurityException e)
      {
        runnable.run();
        generateAlternativeSeed();
      }
    }
    catch (Exception e)
    {
      warn("SecureRandom", e);
      cachedSecureRandom = new SecureRandom();
    }
    return cachedSecureRandom;
  }
  
  public static byte[] generateAlternativeSeed()
  {
    try
    {
      ByteArrayOutputStream bout = new ByteArrayOutputStream();
      DataOutputStream out = new DataOutputStream(bout);
      
      out.writeLong(System.currentTimeMillis());
      out.writeLong(System.nanoTime());
      
      out.writeInt(new Object().hashCode());
      Runtime runtime = Runtime.getRuntime();
      out.writeLong(runtime.freeMemory());
      out.writeLong(runtime.maxMemory());
      out.writeLong(runtime.totalMemory());
      try
      {
        String s = System.getProperties().toString();
        
        out.writeInt(s.length());
        out.write(s.getBytes("UTF-8"));
      }
      catch (Exception e)
      {
        warn("generateAlternativeSeed", e);
      }
      try
      {
        Class<?> inetAddressClass = Class.forName("java.net.InetAddress");
        
        Object localHost = inetAddressClass.getMethod("getLocalHost", new Class[0]).invoke(null, new Object[0]);
        
        String hostName = inetAddressClass.getMethod("getHostName", new Class[0]).invoke(localHost, new Object[0]).toString();
        
        out.writeUTF(hostName);
        Object[] list = (Object[])inetAddressClass.getMethod("getAllByName", new Class[] { String.class }).invoke(null, new Object[] { hostName });
        
        Method getAddress = inetAddressClass.getMethod("getAddress", new Class[0]);
        for (Object o : list) {
          out.write((byte[])getAddress.invoke(o, new Object[0]));
        }
      }
      catch (Throwable e) {}
      for (int j = 0; j < 16; j++)
      {
        int i = 0;
        long end = System.currentTimeMillis();
        while (end == System.currentTimeMillis()) {
          i++;
        }
        out.writeInt(i);
      }
      out.close();
      return bout.toByteArray();
    }
    catch (IOException e)
    {
      warn("generateAlternativeSeed", e);
    }
    return new byte[1];
  }
  
  static void warn(String s, Throwable t)
  {
    System.out.println("Warning: " + s);
    if (t != null) {
      t.printStackTrace();
    }
  }
  
  public static int nextPowerOf2(int x)
  {
    long i = 1L;
    while ((i < x) && (i < 1073741823L)) {
      i += i;
    }
    return (int)i;
  }
  
  public static int convertLongToInt(long l)
  {
    if (l <= -2147483648L) {
      return Integer.MIN_VALUE;
    }
    if (l >= 2147483647L) {
      return Integer.MAX_VALUE;
    }
    return (int)l;
  }
  
  public static int compareInt(int a, int b)
  {
    return a < b ? -1 : a == b ? 0 : 1;
  }
  
  public static int compareLong(long a, long b)
  {
    return a < b ? -1 : a == b ? 0 : 1;
  }
  
  /* Error */
  public static long secureRandomLong()
  {
    // Byte code:
    //   0: invokestatic 282	org/h2/util/MathUtils:getSecureRandom	()Ljava/security/SecureRandom;
    //   3: astore_0
    //   4: aload_0
    //   5: dup
    //   6: astore_1
    //   7: monitorenter
    //   8: aload_0
    //   9: invokevirtual 285	java/security/SecureRandom:nextLong	()J
    //   12: aload_1
    //   13: monitorexit
    //   14: lreturn
    //   15: astore_2
    //   16: aload_1
    //   17: monitorexit
    //   18: aload_2
    //   19: athrow
    // Line number table:
    //   Java source line #280	-> byte code offset #0
    //   Java source line #281	-> byte code offset #4
    //   Java source line #282	-> byte code offset #8
    //   Java source line #283	-> byte code offset #15
    // Local variable table:
    //   start	length	slot	name	signature
    //   3	6	0	sr	SecureRandom
    //   6	11	1	Ljava/lang/Object;	Object
    //   15	4	2	localObject1	Object
    // Exception table:
    //   from	to	target	type
    //   8	14	15	finally
    //   15	18	15	finally
  }
  
  public static void randomBytes(byte[] bytes)
  {
    RANDOM.nextBytes(bytes);
  }
  
  public static byte[] secureRandomBytes(int len)
  {
    if (len <= 0) {
      len = 1;
    }
    byte[] buff = new byte[len];
    SecureRandom sr = getSecureRandom();
    synchronized (sr)
    {
      sr.nextBytes(buff);
    }
    return buff;
  }
  
  public static int randomInt(int lowerThan)
  {
    return RANDOM.nextInt(lowerThan);
  }
  
  /* Error */
  public static int secureRandomInt(int lowerThan)
  {
    // Byte code:
    //   0: invokestatic 282	org/h2/util/MathUtils:getSecureRandom	()Ljava/security/SecureRandom;
    //   3: astore_1
    //   4: aload_1
    //   5: dup
    //   6: astore_2
    //   7: monitorenter
    //   8: aload_1
    //   9: iload_0
    //   10: invokevirtual 307	java/security/SecureRandom:nextInt	(I)I
    //   13: aload_2
    //   14: monitorexit
    //   15: ireturn
    //   16: astore_3
    //   17: aload_2
    //   18: monitorexit
    //   19: aload_3
    //   20: athrow
    // Line number table:
    //   Java source line #332	-> byte code offset #0
    //   Java source line #333	-> byte code offset #4
    //   Java source line #334	-> byte code offset #8
    //   Java source line #335	-> byte code offset #16
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	21	0	lowerThan	int
    //   3	6	1	sr	SecureRandom
    //   6	12	2	Ljava/lang/Object;	Object
    //   16	4	3	localObject1	Object
    // Exception table:
    //   from	to	target	type
    //   8	15	16	finally
    //   16	19	16	finally
  }
}
