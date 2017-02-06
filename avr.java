import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

public class avr
{
  public static final awm a = new awm();
  private static final awn b = new awn().a(true);
  private static final awn c = new awn().a(true).a(aju.a);
  
  public static void a()
  {
    awe.a(avr.a.class, "ECP");
  }
  
  private static avr.a b(avr.a ☃, cj ☃, String ☃, aoe ☃, boolean ☃)
  {
    avr.a ☃ = new avr.a(☃, ☃.c, ☃, ☃);
    cj ☃ = ☃.a.a(☃.b, ☃, ☃.b, cj.a);
    ☃.a(☃.p(), ☃.q(), ☃.r());
    
    return ☃;
  }
  
  static abstract interface b
  {
    public abstract void a();
    
    public abstract boolean a(int paramInt, avr.a parama, cj paramcj, List<awg> paramList, Random paramRandom);
  }
  
  public static class a
    extends awi
  {
    private String d;
    private aoe e;
    private boolean f;
    
    public a() {}
    
    public a(String ☃, cj ☃, aoe ☃, boolean ☃)
    {
      super();
      
      this.d = ☃;
      this.e = ☃;
      this.f = ☃;
      
      a(☃);
    }
    
    private void a(cj ☃)
    {
      awo ☃ = avr.a.a(null, new kk("endcity/" + this.d));
      awn ☃;
      awn ☃;
      if (this.f) {
        ☃ = avr.b().a().a(this.e);
      } else {
        ☃ = avr.c().a().a(this.e);
      }
      a(☃, ☃, ☃);
    }
    
    protected void a(dn ☃)
    {
      super.a(☃);
      
      ☃.a("Template", this.d);
      ☃.a("Rot", this.e.name());
      ☃.a("OW", this.f);
    }
    
    protected void b(dn ☃)
    {
      super.b(☃);
      
      this.d = ☃.l("Template");
      this.e = aoe.valueOf(☃.l("Rot"));
      this.f = ☃.p("OW");
      
      a(this.c);
    }
    
    protected void a(String ☃, cj ☃, aht ☃, Random ☃, avp ☃)
    {
      if (☃.startsWith("Chest"))
      {
        cj ☃ = ☃.b();
        if (☃.b(☃))
        {
          apv ☃ = ☃.r(☃);
          if ((☃ instanceof apx)) {
            ((apx)☃).a(azt.c, ☃.nextLong());
          }
        }
      }
      else if (☃.startsWith("Sentry"))
      {
        yu ☃ = new yu(☃);
        ☃.b(☃.p() + 0.5D, ☃.q() + 0.5D, ☃.r() + 0.5D);
        ☃.g(☃);
        ☃.a(☃);
      }
      else if (☃.startsWith("Elytra"))
      {
        xs ☃ = new xs(☃, ☃, this.e.a(cq.d));
        ☃.a(new adq(ads.cR));
        ☃.a(☃);
      }
    }
  }
  
  public static void a(cj ☃, aoe ☃, List<awg> ☃, Random ☃)
  {
    i.a();
    d.a();
    g.a();
    f.a();
    
    avr.a ☃ = new avr.a("base_floor", ☃, ☃, true);
    ☃.add(☃);
    avr.a ☃;
    ☃.add(☃ = b(☃, new cj(-1, 0, -1), "second_floor", ☃, false));
    ☃.add(☃ = b(☃, new cj(-1, 4, -1), "third_floor", ☃, false));
    ☃.add(☃ = b(☃, new cj(-1, 8, -1), "third_roof", ☃, true));
    
    b(f, 1, ☃, null, ☃, ☃);
  }
  
  private static boolean b(avr.b ☃, int ☃, avr.a ☃, cj ☃, List<awg> ☃, Random ☃)
  {
    if (☃ > 8) {
      return false;
    }
    List<awg> ☃ = Lists.newArrayList();
    if (☃.a(☃, ☃, ☃, ☃, ☃))
    {
      boolean ☃ = false;
      int ☃ = ☃.nextInt();
      for (awg ☃ : ☃)
      {
        ☃.m = ☃;
        awg ☃ = awg.a(☃, ☃.c());
        if ((☃ != null) && (☃.m != ☃.m))
        {
          ☃ = true;
          break;
        }
      }
      if (!☃)
      {
        ☃.addAll(☃);
        return true;
      }
    }
    return false;
  }
  
  private static final avr.b d = new avr.b()
  {
    public void a() {}
    
    public boolean a(int ☃, avr.a ☃, cj ☃, List<awg> ☃, Random ☃)
    {
      if (☃ > 8) {
        return false;
      }
      aoe ☃ = ☃.b.c();
      avr.a ☃;
      ☃.add(☃ = avr.a(☃, ☃, "base_floor", ☃, true));
      
      int ☃ = ☃.nextInt(3);
      if (☃ == 0)
      {
        ☃.add(avr.a(☃, new cj(-1, 4, -1), "base_roof", ☃, true));
      }
      else if (☃ == 1)
      {
        ☃.add(☃ = avr.a(☃, new cj(-1, 0, -1), "second_floor_2", ☃, false));
        ☃.add(☃ = avr.a(☃, new cj(-1, 8, -1), "second_roof", ☃, false));
        
        avr.a(avr.d(), ☃ + 1, ☃, null, ☃, ☃);
      }
      else if (☃ == 2)
      {
        ☃.add(☃ = avr.a(☃, new cj(-1, 0, -1), "second_floor_2", ☃, false));
        ☃.add(☃ = avr.a(☃, new cj(-1, 4, -1), "third_floor_c", ☃, false));
        ☃.add(☃ = avr.a(☃, new cj(-1, 8, -1), "third_roof", ☃, true));
        
        avr.a(avr.d(), ☃ + 1, ☃, null, ☃, ☃);
      }
      return true;
    }
  };
  private static final List<ou<aoe, cj>> e = Lists.newArrayList(new ou[] { new ou(aoe.a, new cj(1, -1, 0)), new ou(aoe.b, new cj(6, -1, 1)), new ou(aoe.d, new cj(0, -1, 5)), new ou(aoe.c, new cj(5, -1, 6)) });
  private static final avr.b f = new avr.b()
  {
    public void a() {}
    
    public boolean a(int ☃, avr.a ☃, cj ☃, List<awg> ☃, Random ☃)
    {
      aoe ☃ = ☃.b.c();
      avr.a ☃ = ☃;
      
      ☃.add(☃ = avr.a(☃, new cj(3 + ☃.nextInt(2), -3, 3 + ☃.nextInt(2)), "tower_base", ☃, true));
      ☃.add(☃ = avr.a(☃, new cj(0, 7, 0), "tower_piece", ☃, true));
      
      avr.a ☃ = ☃.nextInt(3) == 0 ? ☃ : null;
      
      int ☃ = 1 + ☃.nextInt(3);
      for (int ☃ = 0; ☃ < ☃; ☃++)
      {
        ☃.add(☃ = avr.a(☃, new cj(0, 4, 0), "tower_piece", ☃, true));
        if ((☃ < ☃ - 1) && (☃.nextBoolean())) {
          ☃ = ☃;
        }
      }
      if (☃ != null)
      {
        for (ou<aoe, cj> ☃ : avr.e()) {
          if (☃.nextBoolean())
          {
            avr.a ☃;
            ☃.add(☃ = avr.a(☃, (cj)☃.b(), "bridge_end", ☃.a((aoe)☃.a()), true));
            avr.a(avr.f(), ☃ + 1, ☃, null, ☃, ☃);
          }
        }
        ☃.add(☃ = avr.a(☃, new cj(-1, 4, -1), "tower_top", ☃, true));
      }
      else if (☃ == 7)
      {
        ☃.add(☃ = avr.a(☃, new cj(-1, 4, -1), "tower_top", ☃, true));
      }
      else
      {
        return avr.a(avr.g(), ☃ + 1, ☃, null, ☃, ☃);
      }
      return true;
    }
  };
  private static final avr.b g = new avr.b()
  {
    public boolean a;
    
    public void a()
    {
      this.a = false;
    }
    
    public boolean a(int ☃, avr.a ☃, cj ☃, List<awg> ☃, Random ☃)
    {
      aoe ☃ = ☃.b.c();
      int ☃ = ☃.nextInt(4) + 1;
      avr.a ☃;
      ☃.add(☃ = avr.a(☃, new cj(0, 0, -4), "bridge_piece", ☃, true));
      ☃.m = -1;
      int ☃ = 0;
      for (int ☃ = 0; ☃ < ☃; ☃++) {
        if (☃.nextBoolean())
        {
          ☃.add(☃ = avr.a(☃, new cj(0, ☃, -4), "bridge_piece", ☃, true));
          ☃ = 0;
        }
        else
        {
          if (☃.nextBoolean()) {
            ☃.add(☃ = avr.a(☃, new cj(0, ☃, -4), "bridge_steep_stairs", ☃, true));
          } else {
            ☃.add(☃ = avr.a(☃, new cj(0, ☃, -8), "bridge_gentle_stairs", ☃, true));
          }
          ☃ = 4;
        }
      }
      if ((this.a) || (☃.nextInt(10 - ☃) != 0))
      {
        if (!avr.a(avr.h(), ☃ + 1, ☃, new cj(-3, ☃ + 1, -11), ☃, ☃)) {
          return false;
        }
      }
      else
      {
        ☃.add(avr.a(☃, new cj(-8 + ☃.nextInt(8), ☃, -70 + ☃.nextInt(10)), "ship", ☃, true));
        this.a = true;
      }
      ☃.add(☃ = avr.a(☃, new cj(4, ☃, 0), "bridge_end", ☃.a(aoe.c), true));
      ☃.m = -1;
      
      return true;
    }
  };
  private static final List<ou<aoe, cj>> h = Lists.newArrayList(new ou[] { new ou(aoe.a, new cj(4, -1, 0)), new ou(aoe.b, new cj(12, -1, 4)), new ou(aoe.d, new cj(0, -1, 8)), new ou(aoe.c, new cj(8, -1, 12)) });
  private static final avr.b i = new avr.b()
  {
    public void a() {}
    
    public boolean a(int ☃, avr.a ☃, cj ☃, List<awg> ☃, Random ☃)
    {
      aoe ☃ = ☃.b.c();
      avr.a ☃;
      ☃.add(☃ = avr.a(☃, new cj(-3, 4, -3), "fat_tower_base", ☃, true));
      ☃.add(☃ = avr.a(☃, new cj(0, 4, 0), "fat_tower_middle", ☃, true));
      for (int ☃ = 0; ☃ < 2; ☃++)
      {
        if (☃.nextInt(3) == 0) {
          break;
        }
        ☃.add(☃ = avr.a(☃, new cj(0, 8, 0), "fat_tower_middle", ☃, true));
        for (ou<aoe, cj> ☃ : avr.i()) {
          if (☃.nextBoolean())
          {
            avr.a ☃;
            ☃.add(☃ = avr.a(☃, (cj)☃.b(), "bridge_end", ☃.a((aoe)☃.a()), true));
            avr.a(avr.f(), ☃ + 1, ☃, null, ☃, ☃);
          }
        }
      }
      ☃.add(☃ = avr.a(☃, new cj(-2, 8, -2), "fat_tower_top", ☃, true));
      return true;
    }
  };
}
