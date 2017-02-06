import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import net.minecraft.server.MinecraftServer;

public class bi
  extends i
{
  public String c()
  {
    return "spreadplayers";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.spreadplayers.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length < 6) {
      throw new cf("commands.spreadplayers.usage", new Object[0]);
    }
    int ☃ = 0;
    cj ☃ = ☃.c();
    double ☃ = b(☃.p(), ☃[(☃++)], true);
    double ☃ = b(☃.r(), ☃[(☃++)], true);
    double ☃ = a(☃[(☃++)], 0.0D);
    double ☃ = a(☃[(☃++)], ☃ + 1.0D);
    boolean ☃ = d(☃[(☃++)]);
    
    List<rr> ☃ = Lists.newArrayList();
    while (☃ < ☃.length)
    {
      String ☃ = ☃[(☃++)];
      if (o.b(☃))
      {
        List<rr> ☃ = o.b(☃, ☃, rr.class);
        if (☃.isEmpty()) {
          throw new ca();
        }
        ☃.addAll(☃);
      }
      else
      {
        zj ☃ = ☃.al().a(☃);
        if (☃ == null) {
          throw new cd();
        }
        ☃.add(☃);
      }
    }
    ☃.a(n.a.c, ☃.size());
    if (☃.isEmpty()) {
      throw new ca();
    }
    ☃.a(new fb("commands.spreadplayers.spreading." + (☃ ? "teams" : "players"), new Object[] { Integer.valueOf(☃.size()), Double.valueOf(☃), Double.valueOf(☃), Double.valueOf(☃), Double.valueOf(☃) }));
    
    a(☃, ☃, new bi.a(☃, ☃), ☃, ☃, ((rr)☃.get(0)).l, ☃);
  }
  
  private void a(m ☃, List<rr> ☃, bi.a ☃, double ☃, double ☃, aht ☃, boolean ☃)
    throws bz
  {
    Random ☃ = new Random();
    double ☃ = ☃.a - ☃;
    double ☃ = ☃.b - ☃;
    double ☃ = ☃.a + ☃;
    double ☃ = ☃.b + ☃;
    
    bi.a[] ☃ = a(☃, ☃ ? b(☃) : ☃.size(), ☃, ☃, ☃, ☃);
    int ☃ = a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
    double ☃ = a(☃, ☃, ☃, ☃);
    
    a(☃, this, "commands.spreadplayers.success." + (☃ ? "teams" : "players"), new Object[] { Integer.valueOf(☃.length), Double.valueOf(☃.a), Double.valueOf(☃.b) });
    if (☃.length > 1) {
      ☃.a(new fb("commands.spreadplayers.info." + (☃ ? "teams" : "players"), new Object[] { String.format("%.2f", new Object[] { Double.valueOf(☃) }), Integer.valueOf(☃) }));
    }
  }
  
  private int b(List<rr> ☃)
  {
    Set<bbr> ☃ = Sets.newHashSet();
    for (rr ☃ : ☃) {
      if ((☃ instanceof zj)) {
        ☃.add(((zj)☃).aO());
      } else {
        ☃.add(null);
      }
    }
    return ☃.size();
  }
  
  private int a(bi.a ☃, double ☃, aht ☃, Random ☃, double ☃, double ☃, double ☃, double ☃, bi.a[] ☃, boolean ☃)
    throws bz
  {
    boolean ☃ = true;
    
    double ☃ = 3.4028234663852886E38D;
    for (int ☃ = 0; (☃ < 10000) && (☃); ☃++)
    {
      ☃ = false;
      ☃ = 3.4028234663852886E38D;
      for (int ☃ = 0; ☃ < ☃.length; ☃++)
      {
        bi.a ☃ = ☃[☃];
        int ☃ = 0;
        bi.a ☃ = new bi.a();
        for (int ☃ = 0; ☃ < ☃.length; ☃++) {
          if (☃ != ☃)
          {
            bi.a ☃ = ☃[☃];
            
            double ☃ = ☃.a(☃);
            ☃ = Math.min(☃, ☃);
            if (☃ < ☃)
            {
              ☃++;
              ☃.a += ☃.a - ☃.a;
              ☃.b += ☃.b - ☃.b;
            }
          }
        }
        if (☃ > 0)
        {
          ☃.a /= ☃;
          ☃.b /= ☃;
          double ☃ = ☃.b();
          if (☃ > 0.0D)
          {
            ☃.a();
            
            ☃.b(☃);
          }
          else
          {
            ☃.a(☃, ☃, ☃, ☃, ☃);
          }
          ☃ = true;
        }
        if (☃.a(☃, ☃, ☃, ☃)) {
          ☃ = true;
        }
      }
      if (!☃) {
        for (bi.a ☃ : ☃) {
          if (!☃.b(☃))
          {
            ☃.a(☃, ☃, ☃, ☃, ☃);
            ☃ = true;
          }
        }
      }
    }
    if (☃ >= 10000) {
      throw new bz("commands.spreadplayers.failure." + (☃ ? "teams" : "players"), new Object[] { Integer.valueOf(☃.length), Double.valueOf(☃.a), Double.valueOf(☃.b), String.format("%.2f", new Object[] { Double.valueOf(☃) }) });
    }
    return ☃;
  }
  
  private double a(List<rr> ☃, aht ☃, bi.a[] ☃, boolean ☃)
  {
    double ☃ = 0.0D;
    int ☃ = 0;
    Map<bbr, bi.a> ☃ = Maps.newHashMap();
    for (int ☃ = 0; ☃ < ☃.size(); ☃++)
    {
      rr ☃ = (rr)☃.get(☃);
      bi.a ☃;
      bi.a ☃;
      if (☃)
      {
        bbr ☃ = (☃ instanceof zj) ? ((zj)☃).aO() : null;
        if (!☃.containsKey(☃)) {
          ☃.put(☃, ☃[(☃++)]);
        }
        ☃ = (bi.a)☃.get(☃);
      }
      else
      {
        ☃ = ☃[(☃++)];
      }
      ☃.a(on.c(☃.a) + 0.5F, ☃.a(☃), on.c(☃.b) + 0.5D);
      
      double ☃ = Double.MAX_VALUE;
      for (int ☃ = 0; ☃ < ☃.length; ☃++) {
        if (☃ != ☃[☃])
        {
          double ☃ = ☃.a(☃[☃]);
          ☃ = Math.min(☃, ☃);
        }
      }
      ☃ += ☃;
    }
    ☃ /= ☃.size();
    return ☃;
  }
  
  private bi.a[] a(Random ☃, int ☃, double ☃, double ☃, double ☃, double ☃)
  {
    bi.a[] ☃ = new bi.a[☃];
    for (int ☃ = 0; ☃ < ☃.length; ☃++)
    {
      bi.a ☃ = new bi.a();
      ☃.a(☃, ☃, ☃, ☃, ☃);
      ☃[☃] = ☃;
    }
    return ☃;
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if ((☃.length >= 1) && (☃.length <= 2)) {
      return b(☃, 0, ☃);
    }
    return Collections.emptyList();
  }
  
  static class a
  {
    double a;
    double b;
    
    a() {}
    
    a(double ☃, double ☃)
    {
      this.a = ☃;
      this.b = ☃;
    }
    
    double a(a ☃)
    {
      double ☃ = this.a - ☃.a;
      double ☃ = this.b - ☃.b;
      
      return Math.sqrt(☃ * ☃ + ☃ * ☃);
    }
    
    void a()
    {
      double ☃ = b();
      this.a /= ☃;
      this.b /= ☃;
    }
    
    float b()
    {
      return on.a(this.a * this.a + this.b * this.b);
    }
    
    public void b(a ☃)
    {
      this.a -= ☃.a;
      this.b -= ☃.b;
    }
    
    public boolean a(double ☃, double ☃, double ☃, double ☃)
    {
      boolean ☃ = false;
      if (this.a < ☃)
      {
        this.a = ☃;
        ☃ = true;
      }
      else if (this.a > ☃)
      {
        this.a = ☃;
        ☃ = true;
      }
      if (this.b < ☃)
      {
        this.b = ☃;
        ☃ = true;
      }
      else if (this.b > ☃)
      {
        this.b = ☃;
        ☃ = true;
      }
      return ☃;
    }
    
    public int a(aht ☃)
    {
      cj ☃ = new cj(this.a, 256.0D, this.b);
      while (☃.q() > 0)
      {
        ☃ = ☃.b();
        if (☃.o(☃).a() != axe.a) {
          return ☃.q() + 1;
        }
      }
      return 257;
    }
    
    public boolean b(aht ☃)
    {
      cj ☃ = new cj(this.a, 256.0D, this.b);
      while (☃.q() > 0)
      {
        ☃ = ☃.b();
        
        axe ☃ = ☃.o(☃).a();
        if (☃ != axe.a) {
          return (!☃.d()) && (☃ != axe.o);
        }
      }
      return false;
    }
    
    public void a(Random ☃, double ☃, double ☃, double ☃, double ☃)
    {
      this.a = on.a(☃, ☃, ☃);
      this.b = on.a(☃, ☃, ☃);
    }
  }
}
