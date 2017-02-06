import com.google.common.collect.Lists;
import java.util.List;
import java.util.concurrent.Callable;

public class c
{
  private final b a;
  private final String b;
  private final List<c.a> c = Lists.newArrayList();
  private StackTraceElement[] d = new StackTraceElement[0];
  
  public c(b ☃, String ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public static String a(double ☃, double ☃, double ☃)
  {
    return String.format("%.2f,%.2f,%.2f - %s", new Object[] { Double.valueOf(☃), Double.valueOf(☃), Double.valueOf(☃), a(new cj(☃, ☃, ☃)) });
  }
  
  public static String a(cj ☃)
  {
    return a(☃.p(), ☃.q(), ☃.r());
  }
  
  public static String a(int ☃, int ☃, int ☃)
  {
    StringBuilder ☃ = new StringBuilder();
    try
    {
      ☃.append(String.format("World: (%d,%d,%d)", new Object[] { Integer.valueOf(☃), Integer.valueOf(☃), Integer.valueOf(☃) }));
    }
    catch (Throwable ☃)
    {
      ☃.append("(Error finding world loc)");
    }
    ☃.append(", ");
    try
    {
      int ☃ = ☃ >> 4;
      int ☃ = ☃ >> 4;
      int ☃ = ☃ & 0xF;
      int ☃ = ☃ >> 4;
      int ☃ = ☃ & 0xF;
      int ☃ = ☃ << 4;
      int ☃ = ☃ << 4;
      int ☃ = (☃ + 1 << 4) - 1;
      int ☃ = (☃ + 1 << 4) - 1;
      ☃.append(String.format("Chunk: (at %d,%d,%d in %d,%d; contains blocks %d,0,%d to %d,255,%d)", new Object[] { Integer.valueOf(☃), Integer.valueOf(☃), Integer.valueOf(☃), Integer.valueOf(☃), Integer.valueOf(☃), Integer.valueOf(☃), Integer.valueOf(☃), Integer.valueOf(☃), Integer.valueOf(☃) }));
    }
    catch (Throwable ☃)
    {
      ☃.append("(Error finding chunk loc)");
    }
    ☃.append(", ");
    try
    {
      int ☃ = ☃ >> 9;
      int ☃ = ☃ >> 9;
      int ☃ = ☃ << 5;
      int ☃ = ☃ << 5;
      int ☃ = (☃ + 1 << 5) - 1;
      int ☃ = (☃ + 1 << 5) - 1;
      int ☃ = ☃ << 9;
      int ☃ = ☃ << 9;
      int ☃ = (☃ + 1 << 9) - 1;
      int ☃ = (☃ + 1 << 9) - 1;
      ☃.append(String.format("Region: (%d,%d; contains chunks %d,%d to %d,%d, blocks %d,0,%d to %d,255,%d)", new Object[] { Integer.valueOf(☃), Integer.valueOf(☃), Integer.valueOf(☃), Integer.valueOf(☃), Integer.valueOf(☃), Integer.valueOf(☃), Integer.valueOf(☃), Integer.valueOf(☃), Integer.valueOf(☃), Integer.valueOf(☃) }));
    }
    catch (Throwable ☃)
    {
      ☃.append("(Error finding world loc)");
    }
    return ☃.toString();
  }
  
  public void a(String ☃, Callable<String> ☃)
  {
    try
    {
      a(☃, ☃.call());
    }
    catch (Throwable ☃)
    {
      a(☃, ☃);
    }
  }
  
  public void a(String ☃, Object ☃)
  {
    this.c.add(new c.a(☃, ☃));
  }
  
  public void a(String ☃, Throwable ☃)
  {
    a(☃, ☃);
  }
  
  public int a(int ☃)
  {
    StackTraceElement[] ☃ = Thread.currentThread().getStackTrace();
    if (☃.length <= 0) {
      return 0;
    }
    this.d = new StackTraceElement[☃.length - 3 - ☃];
    System.arraycopy(☃, 3 + ☃, this.d, 0, this.d.length);
    return this.d.length;
  }
  
  public boolean a(StackTraceElement ☃, StackTraceElement ☃)
  {
    if ((this.d.length == 0) || (☃ == null)) {
      return false;
    }
    StackTraceElement ☃ = this.d[0];
    if ((☃.isNativeMethod() != ☃.isNativeMethod()) || (!☃.getClassName().equals(☃.getClassName())) || (!☃.getFileName().equals(☃.getFileName())) || (!☃.getMethodName().equals(☃.getMethodName()))) {
      return false;
    }
    if ((☃ != null ? 1 : 0) != (this.d.length > 1 ? 1 : 0)) {
      return false;
    }
    if ((☃ != null) && (!this.d[1].equals(☃))) {
      return false;
    }
    this.d[0] = ☃;
    
    return true;
  }
  
  public void b(int ☃)
  {
    StackTraceElement[] ☃ = new StackTraceElement[this.d.length - ☃];
    System.arraycopy(this.d, 0, ☃, 0, ☃.length);
    this.d = ☃;
  }
  
  public void a(StringBuilder ☃)
  {
    ☃.append("-- ").append(this.b).append(" --\n");
    ☃.append("Details:");
    for (c.a ☃ : this.c)
    {
      ☃.append("\n\t");
      ☃.append(☃.a());
      ☃.append(": ");
      ☃.append(☃.b());
    }
    if ((this.d != null) && (this.d.length > 0))
    {
      ☃.append("\nStacktrace:");
      for (StackTraceElement ☃ : this.d)
      {
        ☃.append("\n\tat ");
        ☃.append(☃.toString());
      }
    }
  }
  
  public StackTraceElement[] a()
  {
    return this.d;
  }
  
  public static void a(c ☃, cj ☃, final ajt ☃, int ☃)
  {
    int ☃ = ajt.a(☃);
    ☃.a("Block type", new Callable()
    {
      public String a()
        throws Exception
      {
        try
        {
          return String.format("ID #%d (%s // %s)", new Object[] { Integer.valueOf(this.a), ☃.a(), ☃.getClass().getCanonicalName() });
        }
        catch (Throwable ☃) {}
        return "ID #" + this.a;
      }
    });
    ☃.a("Block data value", new Callable()
    {
      public String a()
        throws Exception
      {
        if (this.a < 0) {
          return "Unknown? (Got " + this.a + ")";
        }
        String ☃ = String.format("%4s", new Object[] { Integer.toBinaryString(this.a) }).replace(" ", "0");
        
        return String.format("%1$d / 0x%1$X / 0b%2$s", new Object[] { Integer.valueOf(this.a), ☃ });
      }
    });
    ☃.a("Block location", new Callable()
    {
      public String a()
        throws Exception
      {
        return c.a(this.a);
      }
    });
  }
  
  public static void a(c ☃, cj ☃, arc ☃)
  {
    ☃.a("Block", new Callable()
    {
      public String a()
        throws Exception
      {
        return this.a.toString();
      }
    });
    ☃.a("Block location", new Callable()
    {
      public String a()
        throws Exception
      {
        return c.a(this.a);
      }
    });
  }
  
  static class a
  {
    private final String a;
    private final String b;
    
    public a(String ☃, Object ☃)
    {
      this.a = ☃;
      if (☃ == null)
      {
        this.b = "~~NULL~~";
      }
      else if ((☃ instanceof Throwable))
      {
        Throwable ☃ = (Throwable)☃;
        this.b = ("~~ERROR~~ " + ☃.getClass().getSimpleName() + ": " + ☃.getMessage());
      }
      else
      {
        this.b = ☃.toString();
      }
    }
    
    public String a()
    {
      return this.a;
    }
    
    public String b()
    {
      return this.b;
    }
  }
}
