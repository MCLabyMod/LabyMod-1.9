import com.google.common.collect.Maps;
import java.util.Map;
import java.util.concurrent.Callable;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class apv
{
  private static final Logger a = ;
  private static Map<String, Class<? extends apv>> f = Maps.newHashMap();
  private static Map<Class<? extends apv>, String> g = Maps.newHashMap();
  protected aht b;
  
  private static void a(Class<? extends apv> ☃, String ☃)
  {
    if (f.containsKey(☃)) {
      throw new IllegalArgumentException("Duplicate id: " + ☃);
    }
    f.put(☃, ☃);
    g.put(☃, ☃);
  }
  
  static
  {
    a(aqg.class, "Furnace");
    a(apx.class, "Chest");
    a(aqe.class, "EnderChest");
    a(amj.a.class, "RecordPlayer");
    a(aqb.class, "Trap");
    a(aqc.class, "Dropper");
    a(aqn.class, "Sign");
    a(aqk.class, "MobSpawner");
    a(aql.class, "Music");
    a(aqx.class, "Piston");
    a(apw.class, "Cauldron");
    a(aqd.class, "EnchantTable");
    a(aqr.class, "Airportal");
    a(apu.class, "Beacon");
    a(aqo.class, "Skull");
    a(aqa.class, "DLDetector");
    a(aqi.class, "Hopper");
    a(apz.class, "Comparator");
    a(aqf.class, "FlowerPot");
    a(apt.class, "Banner");
    a(aqp.class, "Structure");
    a(aqq.class, "EndGateway");
    a(apy.class, "Control");
  }
  
  protected cj c = cj.a;
  protected boolean d;
  private int h = -1;
  protected ajt e;
  
  public aht D()
  {
    return this.b;
  }
  
  public void a(aht ☃)
  {
    this.b = ☃;
  }
  
  public boolean t()
  {
    return this.b != null;
  }
  
  public void a(dn ☃)
  {
    this.c = new cj(☃.h("x"), ☃.h("y"), ☃.h("z"));
  }
  
  public void b(dn ☃)
  {
    String ☃ = (String)g.get(getClass());
    if (☃ == null) {
      throw new RuntimeException(getClass() + " is missing a mapping! This is a bug!");
    }
    ☃.a("id", ☃);
    ☃.a("x", this.c.p());
    ☃.a("y", this.c.q());
    ☃.a("z", this.c.r());
  }
  
  public static apv a(MinecraftServer ☃, dn ☃)
  {
    apv ☃ = null;
    String ☃ = ☃.l("id");
    try
    {
      Class<? extends apv> ☃ = (Class)f.get(☃);
      if (☃ != null) {
        ☃ = (apv)☃.newInstance();
      }
    }
    catch (Throwable ☃)
    {
      a.error("Failed to create block entity " + ☃, ☃);
    }
    if (☃ != null) {
      try
      {
        ☃.a(☃);
      }
      catch (Throwable ☃)
      {
        a.error("Failed to load data for block entity " + ☃, ☃);
        ☃ = null;
      }
    } else {
      a.warn("Skipping BlockEntity with id " + ☃);
    }
    return ☃;
  }
  
  public int u()
  {
    if (this.h == -1)
    {
      arc ☃ = this.b.o(this.c);
      this.h = ☃.t().e(☃);
    }
    return this.h;
  }
  
  public void v_()
  {
    if (this.b != null)
    {
      arc ☃ = this.b.o(this.c);
      this.h = ☃.t().e(☃);
      this.b.b(this.c, this);
      if (w() != aju.a) {
        this.b.f(this.c, w());
      }
    }
  }
  
  public double a(double ☃, double ☃, double ☃)
  {
    double ☃ = this.c.p() + 0.5D - ☃;
    double ☃ = this.c.q() + 0.5D - ☃;
    double ☃ = this.c.r() + 0.5D - ☃;
    return ☃ * ☃ + ☃ * ☃ + ☃ * ☃;
  }
  
  public double s()
  {
    return 4096.0D;
  }
  
  public cj v()
  {
    return this.c;
  }
  
  public ajt w()
  {
    if ((this.e == null) && (this.b != null)) {
      this.e = this.b.o(this.c).t();
    }
    return this.e;
  }
  
  public ff<?> D_()
  {
    return null;
  }
  
  public boolean x()
  {
    return this.d;
  }
  
  public void y()
  {
    this.d = true;
  }
  
  public void z()
  {
    this.d = false;
  }
  
  public boolean c(int ☃, int ☃)
  {
    return false;
  }
  
  public void A()
  {
    this.e = null;
    this.h = -1;
  }
  
  public void a(c ☃)
  {
    ☃.a("Name", new Callable()
    {
      public String a()
        throws Exception
      {
        return (String)apv.C().get(apv.this.getClass()) + " // " + apv.this.getClass().getCanonicalName();
      }
    });
    if (this.b == null) {
      return;
    }
    c.a(☃, this.c, w(), u());
    
    ☃.a("Actual block type", new Callable()
    {
      public String a()
        throws Exception
      {
        int ☃ = ajt.a(apv.this.b.o(apv.this.c).t());
        try
        {
          return String.format("ID #%d (%s // %s)", new Object[] { Integer.valueOf(☃), ajt.b(☃).a(), ajt.b(☃).getClass().getCanonicalName() });
        }
        catch (Throwable ☃) {}
        return "ID #" + ☃;
      }
    });
    ☃.a("Actual block data value", new Callable()
    {
      public String a()
        throws Exception
      {
        arc ☃ = apv.this.b.o(apv.this.c);
        int ☃ = ☃.t().e(☃);
        if (☃ < 0) {
          return "Unknown? (Got " + ☃ + ")";
        }
        String ☃ = String.format("%4s", new Object[] { Integer.toBinaryString(☃) }).replace(" ", "0");
        
        return String.format("%1$d / 0x%1$X / 0b%2$s", new Object[] { Integer.valueOf(☃), ☃ });
      }
    });
  }
  
  public void a(cj ☃)
  {
    if (((☃ instanceof cj.a)) || ((☃ instanceof cj.b)))
    {
      a.warn("Tried to assign a mutable BlockPos to a block entity...", new Error(☃.getClass().toString()));
      ☃ = new cj(☃);
    }
    this.c = ☃;
  }
  
  public boolean B()
  {
    return false;
  }
}
