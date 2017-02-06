import com.google.common.collect.Maps;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import net.minecraft.server.MinecraftServer;

public class qw
{
  private final Map<String, Object> a = Maps.newHashMap();
  private final Map<String, Object> b = Maps.newHashMap();
  private final String c = UUID.randomUUID().toString();
  private final URL d;
  private final qx e;
  private final Timer f = new Timer("Snooper Timer", true);
  private final Object g = new Object();
  private final long h;
  private boolean i;
  private int j;
  
  public qw(String ☃, qx ☃, long ☃)
  {
    try
    {
      this.d = new URL("http://snoop.minecraft.net/" + ☃ + "?version=" + 2);
    }
    catch (MalformedURLException ☃)
    {
      throw new IllegalArgumentException();
    }
    this.e = ☃;
    this.h = ☃;
  }
  
  public void a()
  {
    if (this.i) {
      return;
    }
    this.i = true;
    
    h();
    
    this.f.schedule(new TimerTask()
    {
      public void run()
      {
        if (!qw.a(qw.this).Z()) {
          return;
        }
        Map<String, Object> ☃;
        synchronized (qw.b(qw.this))
        {
          ☃ = Maps.newHashMap(qw.c(qw.this));
          if (qw.d(qw.this) == 0) {
            ☃.putAll(qw.e(qw.this));
          }
          ☃.put("snooper_count", Integer.valueOf(qw.f(qw.this)));
          ☃.put("snooper_token", qw.g(qw.this));
        }
        MinecraftServer ☃ = (qw.a(qw.this) instanceof MinecraftServer) ? (MinecraftServer)qw.a(qw.this) : null;
        oe.a(qw.h(qw.this), ☃, true, ☃ == null ? null : ☃.au());
      }
    }, 0L, 900000L);
  }
  
  private void h()
  {
    i();
    
    a("snooper_token", this.c);
    b("snooper_token", this.c);
    b("os_name", System.getProperty("os.name"));
    b("os_version", System.getProperty("os.version"));
    b("os_architecture", System.getProperty("os.arch"));
    b("java_version", System.getProperty("java.version"));
    a("version", "1.9");
    
    this.e.b(this);
  }
  
  private void i()
  {
    RuntimeMXBean ☃ = ManagementFactory.getRuntimeMXBean();
    List<String> ☃ = ☃.getInputArguments();
    int ☃ = 0;
    for (String ☃ : ☃) {
      if (☃.startsWith("-X")) {
        a("jvm_arg[" + ☃++ + "]", ☃);
      }
    }
    a("jvm_args", Integer.valueOf(☃));
  }
  
  public void b()
  {
    b("memory_total", Long.valueOf(Runtime.getRuntime().totalMemory()));
    b("memory_max", Long.valueOf(Runtime.getRuntime().maxMemory()));
    b("memory_free", Long.valueOf(Runtime.getRuntime().freeMemory()));
    b("cpu_cores", Integer.valueOf(Runtime.getRuntime().availableProcessors()));
    
    this.e.a(this);
  }
  
  public void a(String ☃, Object ☃)
  {
    synchronized (this.g)
    {
      this.b.put(☃, ☃);
    }
  }
  
  public void b(String ☃, Object ☃)
  {
    synchronized (this.g)
    {
      this.a.put(☃, ☃);
    }
  }
  
  public Map<String, String> c()
  {
    Map<String, String> ☃ = Maps.newLinkedHashMap();
    synchronized (this.g)
    {
      b();
      for (Map.Entry<String, Object> ☃ : this.a.entrySet()) {
        ☃.put(☃.getKey(), ☃.getValue().toString());
      }
      for (Map.Entry<String, Object> ☃ : this.b.entrySet()) {
        ☃.put(☃.getKey(), ☃.getValue().toString());
      }
    }
    return ☃;
  }
  
  public boolean d()
  {
    return this.i;
  }
  
  public void e()
  {
    this.f.cancel();
  }
  
  public String f()
  {
    return this.c;
  }
  
  public long g()
  {
    return this.h;
  }
}
