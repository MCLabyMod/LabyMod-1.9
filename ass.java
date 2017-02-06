import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ass
  implements asm, bbe
{
  private static final Logger a = ;
  private Map<ahn, dn> b = new ConcurrentHashMap();
  private Set<ahn> c = Collections.newSetFromMap(new ConcurrentHashMap());
  private final File d;
  private final pb e;
  private boolean f = false;
  
  public ass(File ☃, pb ☃)
  {
    this.d = ☃;
    this.e = ☃;
  }
  
  public ase a(aht ☃, int ☃, int ☃)
    throws IOException
  {
    ahn ☃ = new ahn(☃, ☃);
    
    dn ☃ = (dn)this.b.get(☃);
    if (☃ == null)
    {
      DataInputStream ☃ = asr.c(this.d, ☃, ☃);
      if (☃ != null) {
        ☃ = this.e.a(oz.c, dx.a(☃));
      } else {
        return null;
      }
    }
    return a(☃, ☃, ☃, ☃);
  }
  
  protected ase a(aht ☃, int ☃, int ☃, dn ☃)
  {
    if (!☃.b("Level", 10))
    {
      a.error("Chunk file at " + ☃ + "," + ☃ + " is missing level data, skipping");
      return null;
    }
    dn ☃ = ☃.o("Level");
    if (!☃.b("Sections", 9))
    {
      a.error("Chunk file at " + ☃ + "," + ☃ + " is missing block data, skipping");
      return null;
    }
    ase ☃ = a(☃, ☃);
    if (!☃.a(☃, ☃))
    {
      a.error("Chunk file at " + ☃ + "," + ☃ + " is in the wrong location; relocating. (Expected " + ☃ + ", " + ☃ + ", got " + ☃.b + ", " + ☃.c + ")");
      ☃.a("xPos", ☃);
      ☃.a("zPos", ☃);
      ☃ = a(☃, ☃);
    }
    return ☃;
  }
  
  public void a(aht ☃, ase ☃)
    throws IOException, ahu
  {
    ☃.N();
    try
    {
      dn ☃ = new dn();
      dn ☃ = new dn();
      ☃.a("Level", ☃);
      ☃.a("DataVersion", 169);
      a(☃, ☃, ☃);
      a(☃.k(), ☃);
    }
    catch (Exception ☃)
    {
      a.error("Failed to save chunk", ☃);
    }
  }
  
  protected void a(ahn ☃, dn ☃)
  {
    if (!this.c.contains(☃)) {
      this.b.put(☃, ☃);
    }
    bbd.a().a(this);
  }
  
  public boolean c()
  {
    if (this.b.isEmpty())
    {
      if (this.f) {
        a.info("ThreadedAnvilChunkStorage ({}): All chunks are saved", new Object[] { this.d.getName() });
      }
      return false;
    }
    ahn ☃ = (ahn)this.b.keySet().iterator().next();
    try
    {
      this.c.add(☃);
      dn ☃ = (dn)this.b.remove(☃);
      if (☃ != null) {
        try
        {
          b(☃, ☃);
        }
        catch (Exception ☃)
        {
          a.error("Failed to save chunk", ☃);
        }
      }
      return 1;
    }
    finally
    {
      this.c.remove(☃);
    }
  }
  
  private void b(ahn ☃, dn ☃)
    throws IOException
  {
    DataOutputStream ☃ = asr.d(this.d, ☃.a, ☃.b);
    dx.a(☃, ☃);
    ☃.close();
  }
  
  public void b(aht ☃, ase ☃)
    throws IOException
  {}
  
  public void a() {}
  
  public void b()
  {
    try
    {
      this.f = true;
      while (c()) {}
    }
    finally
    {
      this.f = false;
    }
  }
  
  private void a(ase ☃, aht ☃, dn ☃)
  {
    ☃.a("xPos", ☃.b);
    ☃.a("zPos", ☃.c);
    ☃.a("LastUpdate", ☃.P());
    ☃.a("HeightMap", ☃.r());
    ☃.a("TerrainPopulated", ☃.u());
    ☃.a("LightPopulated", ☃.v());
    ☃.a("InhabitedTime", ☃.x());
    
    asf[] ☃ = ☃.h();
    du ☃ = new du();
    
    boolean ☃ = !☃.s.m();
    for (asf ☃ : ☃) {
      if (☃ != ase.a)
      {
        dn ☃ = new dn();
        
        ☃.a("Y", (byte)(☃.d() >> 4 & 0xFF));
        
        byte[] ☃ = new byte['က'];
        asa ☃ = new asa();
        asa ☃ = ☃.g().a(☃, ☃);
        ☃.a("Blocks", ☃);
        ☃.a("Data", ☃.a());
        if (☃ != null) {
          ☃.a("Add", ☃.a());
        }
        ☃.a("BlockLight", ☃.h().a());
        if (☃) {
          ☃.a("SkyLight", ☃.i().a());
        } else {
          ☃.a("SkyLight", new byte[☃.h().a().length]);
        }
        ☃.a(☃);
      }
    }
    ☃.a("Sections", ☃);
    ☃.a("Biomes", ☃.l());
    
    ☃.g(false);
    du ☃ = new du();
    for (int ☃ = 0; ☃ < ☃.t().length; ☃++) {
      for (rr ☃ : ☃.t()[☃])
      {
        dn ☃ = new dn();
        if (☃.d(☃))
        {
          ☃.g(true);
          ☃.a(☃);
        }
      }
    }
    ☃.a("Entities", ☃);
    
    du ☃ = new du();
    for (apv ☃ : ☃.s().values())
    {
      dn ☃ = new dn();
      ☃.b(☃);
      ☃.a(☃);
    }
    ☃.a("TileEntities", ☃);
    
    List<aie> ☃ = ☃.a(☃, false);
    if (☃ != null)
    {
      long ☃ = ☃.P();
      
      du ☃ = new du();
      for (aie ☃ : ☃)
      {
        dn ☃ = new dn();
        kk ☃ = (kk)ajt.h.b(☃.a());
        ☃.a("i", ☃ == null ? "" : ☃.toString());
        ☃.a("x", ☃.a.p());
        ☃.a("y", ☃.a.q());
        ☃.a("z", ☃.a.r());
        ☃.a("t", (int)(☃.b - ☃));
        ☃.a("p", ☃.c);
        
        ☃.a(☃);
      }
      ☃.a("TileTicks", ☃);
    }
  }
  
  private ase a(aht ☃, dn ☃)
  {
    int ☃ = ☃.h("xPos");
    int ☃ = ☃.h("zPos");
    
    ase ☃ = new ase(☃, ☃, ☃);
    ☃.a(☃.n("HeightMap"));
    ☃.d(☃.p("TerrainPopulated"));
    ☃.e(☃.p("LightPopulated"));
    ☃.c(☃.i("InhabitedTime"));
    
    du ☃ = ☃.c("Sections", 10);
    int ☃ = 16;
    asf[] ☃ = new asf[☃];
    
    boolean ☃ = !☃.s.m();
    for (int ☃ = 0; ☃ < ☃.c(); ☃++)
    {
      dn ☃ = ☃.b(☃);
      
      int ☃ = ☃.f("Y");
      asf ☃ = new asf(☃ << 4, ☃);
      byte[] ☃ = ☃.m("Blocks");
      asa ☃ = new asa(☃.m("Data"));
      asa ☃ = ☃.b("Add", 7) ? new asa(☃.m("Add")) : null;
      ☃.g().a(☃, ☃, ☃);
      
      ☃.a(new asa(☃.m("BlockLight")));
      if (☃) {
        ☃.b(new asa(☃.m("SkyLight")));
      }
      ☃.e();
      
      ☃[☃] = ☃;
    }
    ☃.a(☃);
    if (☃.b("Biomes", 7)) {
      ☃.a(☃.m("Biomes"));
    }
    du ☃ = ☃.c("Entities", 10);
    if (☃ != null) {
      for (int ☃ = 0; ☃ < ☃.c(); ☃++)
      {
        dn ☃ = ☃.b(☃);
        a(☃, ☃, ☃);
        ☃.g(true);
      }
    }
    du ☃ = ☃.c("TileEntities", 10);
    if (☃ != null) {
      for (int ☃ = 0; ☃ < ☃.c(); ☃++)
      {
        dn ☃ = ☃.b(☃);
        apv ☃ = apv.a(☃.u(), ☃);
        if (☃ != null) {
          ☃.a(☃);
        }
      }
    }
    if (☃.b("TileTicks", 9))
    {
      du ☃ = ☃.c("TileTicks", 10);
      if (☃ != null) {
        for (int ☃ = 0; ☃ < ☃.c(); ☃++)
        {
          dn ☃ = ☃.b(☃);
          ajt ☃;
          ajt ☃;
          if (☃.b("i", 8)) {
            ☃ = ajt.b(☃.l("i"));
          } else {
            ☃ = ajt.b(☃.h("i"));
          }
          ☃.b(new cj(☃.h("x"), ☃.h("y"), ☃.h("z")), ☃, ☃.h("t"), ☃.h("p"));
        }
      }
    }
    return ☃;
  }
  
  public static rr a(dn ☃, aht ☃, ase ☃)
  {
    rr ☃ = a(☃, ☃);
    if (☃ == null) {
      return null;
    }
    ☃.a(☃);
    if (☃.b("Passengers", 9))
    {
      du ☃ = ☃.c("Passengers", 10);
      for (int ☃ = 0; ☃ < ☃.c(); ☃++)
      {
        rr ☃ = a(☃.b(☃), ☃, ☃);
        if (☃ != null) {
          ☃.a(☃, true);
        }
      }
    }
    return ☃;
  }
  
  public static rr a(dn ☃, aht ☃, double ☃, double ☃, double ☃, boolean ☃)
  {
    rr ☃ = a(☃, ☃);
    if (☃ == null) {
      return null;
    }
    ☃.b(☃, ☃, ☃, ☃.v, ☃.w);
    if ((☃) && (!☃.a(☃))) {
      return null;
    }
    if (☃.b("Passengers", 9))
    {
      du ☃ = ☃.c("Passengers", 10);
      for (int ☃ = 0; ☃ < ☃.c(); ☃++)
      {
        rr ☃ = a(☃.b(☃), ☃, ☃, ☃, ☃, ☃);
        if (☃ != null) {
          ☃.a(☃, true);
        }
      }
    }
    return ☃;
  }
  
  protected static rr a(dn ☃, aht ☃)
  {
    try
    {
      return rt.a(☃, ☃);
    }
    catch (RuntimeException ☃) {}
    return null;
  }
  
  public static void a(rr ☃, aht ☃)
  {
    if ((☃.a(☃)) && 
      (☃.aJ())) {
      for (rr ☃ : ☃.bu()) {
        a(☃, ☃);
      }
    }
  }
  
  public static rr a(dn ☃, aht ☃, boolean ☃)
  {
    rr ☃ = a(☃, ☃);
    if (☃ == null) {
      return null;
    }
    if ((☃) && (!☃.a(☃))) {
      return null;
    }
    if (☃.b("Passengers", 9))
    {
      du ☃ = ☃.c("Passengers", 10);
      for (int ☃ = 0; ☃ < ☃.c(); ☃++)
      {
        rr ☃ = a(☃.b(☃), ☃, ☃);
        if (☃ != null) {
          ☃.a(☃, true);
        }
      }
    }
    return ☃;
  }
}
