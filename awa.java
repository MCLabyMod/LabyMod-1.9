import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import net.minecraft.server.MinecraftServer;

public class awa
{
  public static void a()
  {
    awe.a(awa.a.class, "TeDP");
    awe.a(awa.c.class, "TeJP");
    awe.a(awa.e.class, "TeSH");
    awe.a(awa.b.class, "Iglu");
  }
  
  static abstract class d
    extends awg
  {
    protected int a;
    protected int b;
    protected int c;
    protected int d = -1;
    
    public d() {}
    
    protected d(Random ☃, int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
    {
      super();
      
      this.a = ☃;
      this.b = ☃;
      this.c = ☃;
      
      a(cq.c.a.a(☃));
      if (e().k() == cq.a.c) {
        this.l = new avp(☃, ☃, ☃, ☃ + ☃ - 1, ☃ + ☃ - 1, ☃ + ☃ - 1);
      } else {
        this.l = new avp(☃, ☃, ☃, ☃ + ☃ - 1, ☃ + ☃ - 1, ☃ + ☃ - 1);
      }
    }
    
    protected void a(dn ☃)
    {
      ☃.a("Width", this.a);
      ☃.a("Height", this.b);
      ☃.a("Depth", this.c);
      ☃.a("HPos", this.d);
    }
    
    protected void b(dn ☃)
    {
      this.a = ☃.h("Width");
      this.b = ☃.h("Height");
      this.c = ☃.h("Depth");
      this.d = ☃.h("HPos");
    }
    
    protected boolean a(aht ☃, avp ☃, int ☃)
    {
      if (this.d >= 0) {
        return true;
      }
      int ☃ = 0;
      int ☃ = 0;
      cj.a ☃ = new cj.a();
      for (int ☃ = this.l.c; ☃ <= this.l.f; ☃++) {
        for (int ☃ = this.l.a; ☃ <= this.l.d; ☃++)
        {
          ☃.c(☃, 64, ☃);
          if (☃.b(☃))
          {
            ☃ += Math.max(☃.q(☃).q(), ☃.s.i());
            ☃++;
          }
        }
      }
      if (☃ == 0) {
        return false;
      }
      this.d = (☃ / ☃);
      this.l.a(0, this.d - this.l.b + ☃, 0);
      return true;
    }
  }
  
  public static class a
    extends awa.d
  {
    private boolean[] e = new boolean[4];
    
    public a() {}
    
    public a(Random ☃, int ☃, int ☃)
    {
      super(☃, 64, ☃, 21, 15, 21);
    }
    
    protected void a(dn ☃)
    {
      super.a(☃);
      ☃.a("hasPlacedChest0", this.e[0]);
      ☃.a("hasPlacedChest1", this.e[1]);
      ☃.a("hasPlacedChest2", this.e[2]);
      ☃.a("hasPlacedChest3", this.e[3]);
    }
    
    protected void b(dn ☃)
    {
      super.b(☃);
      this.e[0] = ☃.p("hasPlacedChest0");
      this.e[1] = ☃.p("hasPlacedChest1");
      this.e[2] = ☃.p("hasPlacedChest2");
      this.e[3] = ☃.p("hasPlacedChest3");
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      a(☃, ☃, 0, -4, 0, this.a - 1, 0, this.c - 1, aju.A.u(), aju.A.u(), false);
      for (int ☃ = 1; ☃ <= 9; ☃++)
      {
        a(☃, ☃, ☃, ☃, ☃, this.a - 1 - ☃, ☃, this.c - 1 - ☃, aju.A.u(), aju.A.u(), false);
        a(☃, ☃, ☃ + 1, ☃, ☃ + 1, this.a - 2 - ☃, ☃, this.c - 2 - ☃, aju.a.u(), aju.a.u(), false);
      }
      for (int ☃ = 0; ☃ < this.a; ☃++) {
        for (int ☃ = 0; ☃ < this.c; ☃++)
        {
          int ☃ = -5;
          b(☃, aju.A.u(), ☃, ☃, ☃, ☃);
        }
      }
      arc ☃ = aju.bO.u().a(aot.a, cq.c);
      arc ☃ = aju.bO.u().a(aot.a, cq.d);
      arc ☃ = aju.bO.u().a(aot.a, cq.f);
      arc ☃ = aju.bO.u().a(aot.a, cq.e);
      int ☃ = (act.b.b() ^ 0xFFFFFFFF) & 0xF;
      int ☃ = (act.l.b() ^ 0xFFFFFFFF) & 0xF;
      
      a(☃, ☃, 0, 0, 0, 4, 9, 4, aju.A.u(), aju.a.u(), false);
      a(☃, ☃, 1, 10, 1, 3, 10, 3, aju.A.u(), aju.A.u(), false);
      a(☃, ☃, 2, 10, 0, ☃);
      a(☃, ☃, 2, 10, 4, ☃);
      a(☃, ☃, 0, 10, 2, ☃);
      a(☃, ☃, 4, 10, 2, ☃);
      a(☃, ☃, this.a - 5, 0, 0, this.a - 1, 9, 4, aju.A.u(), aju.a.u(), false);
      a(☃, ☃, this.a - 4, 10, 1, this.a - 2, 10, 3, aju.A.u(), aju.A.u(), false);
      a(☃, ☃, this.a - 3, 10, 0, ☃);
      a(☃, ☃, this.a - 3, 10, 4, ☃);
      a(☃, ☃, this.a - 5, 10, 2, ☃);
      a(☃, ☃, this.a - 1, 10, 2, ☃);
      
      a(☃, ☃, 8, 0, 0, 12, 4, 4, aju.A.u(), aju.a.u(), false);
      a(☃, ☃, 9, 1, 0, 11, 3, 4, aju.a.u(), aju.a.u(), false);
      a(☃, aju.A.a(aog.a.c.a()), 9, 1, 1, ☃);
      a(☃, aju.A.a(aog.a.c.a()), 9, 2, 1, ☃);
      a(☃, aju.A.a(aog.a.c.a()), 9, 3, 1, ☃);
      a(☃, aju.A.a(aog.a.c.a()), 10, 3, 1, ☃);
      a(☃, aju.A.a(aog.a.c.a()), 11, 3, 1, ☃);
      a(☃, aju.A.a(aog.a.c.a()), 11, 2, 1, ☃);
      a(☃, aju.A.a(aog.a.c.a()), 11, 1, 1, ☃);
      
      a(☃, ☃, 4, 1, 1, 8, 3, 3, aju.A.u(), aju.a.u(), false);
      a(☃, ☃, 4, 1, 2, 8, 2, 2, aju.a.u(), aju.a.u(), false);
      a(☃, ☃, 12, 1, 1, 16, 3, 3, aju.A.u(), aju.a.u(), false);
      a(☃, ☃, 12, 1, 2, 16, 2, 2, aju.a.u(), aju.a.u(), false);
      
      a(☃, ☃, 5, 4, 5, this.a - 6, 4, this.c - 6, aju.A.u(), aju.A.u(), false);
      a(☃, ☃, 9, 4, 9, 11, 4, 11, aju.a.u(), aju.a.u(), false);
      a(☃, ☃, 8, 1, 8, 8, 3, 8, aju.A.a(aog.a.c.a()), aju.A.a(aog.a.c.a()), false);
      a(☃, ☃, 12, 1, 8, 12, 3, 8, aju.A.a(aog.a.c.a()), aju.A.a(aog.a.c.a()), false);
      a(☃, ☃, 8, 1, 12, 8, 3, 12, aju.A.a(aog.a.c.a()), aju.A.a(aog.a.c.a()), false);
      a(☃, ☃, 12, 1, 12, 12, 3, 12, aju.A.a(aog.a.c.a()), aju.A.a(aog.a.c.a()), false);
      
      a(☃, ☃, 1, 1, 5, 4, 4, 11, aju.A.u(), aju.A.u(), false);
      a(☃, ☃, this.a - 5, 1, 5, this.a - 2, 4, 11, aju.A.u(), aju.A.u(), false);
      a(☃, ☃, 6, 7, 9, 6, 7, 11, aju.A.u(), aju.A.u(), false);
      a(☃, ☃, this.a - 7, 7, 9, this.a - 7, 7, 11, aju.A.u(), aju.A.u(), false);
      a(☃, ☃, 5, 5, 9, 5, 7, 11, aju.A.a(aog.a.c.a()), aju.A.a(aog.a.c.a()), false);
      a(☃, ☃, this.a - 6, 5, 9, this.a - 6, 7, 11, aju.A.a(aog.a.c.a()), aju.A.a(aog.a.c.a()), false);
      a(☃, aju.a.u(), 5, 5, 10, ☃);
      a(☃, aju.a.u(), 5, 6, 10, ☃);
      a(☃, aju.a.u(), 6, 6, 10, ☃);
      a(☃, aju.a.u(), this.a - 6, 5, 10, ☃);
      a(☃, aju.a.u(), this.a - 6, 6, 10, ☃);
      a(☃, aju.a.u(), this.a - 7, 6, 10, ☃);
      
      a(☃, ☃, 2, 4, 4, 2, 6, 4, aju.a.u(), aju.a.u(), false);
      a(☃, ☃, this.a - 3, 4, 4, this.a - 3, 6, 4, aju.a.u(), aju.a.u(), false);
      a(☃, ☃, 2, 4, 5, ☃);
      a(☃, ☃, 2, 3, 4, ☃);
      a(☃, ☃, this.a - 3, 4, 5, ☃);
      a(☃, ☃, this.a - 3, 3, 4, ☃);
      a(☃, ☃, 1, 1, 3, 2, 2, 3, aju.A.u(), aju.A.u(), false);
      a(☃, ☃, this.a - 3, 1, 3, this.a - 2, 2, 3, aju.A.u(), aju.A.u(), false);
      a(☃, aju.A.u(), 1, 1, 2, ☃);
      a(☃, aju.A.u(), this.a - 2, 1, 2, ☃);
      a(☃, aju.U.a(apa.a.b.a()), 1, 2, 2, ☃);
      a(☃, aju.U.a(apa.a.b.a()), this.a - 2, 2, 2, ☃);
      a(☃, ☃, 2, 1, 2, ☃);
      a(☃, ☃, this.a - 3, 1, 2, ☃);
      
      a(☃, ☃, 4, 3, 5, 4, 3, 18, aju.A.u(), aju.A.u(), false);
      a(☃, ☃, this.a - 5, 3, 5, this.a - 5, 3, 17, aju.A.u(), aju.A.u(), false);
      a(☃, ☃, 3, 1, 5, 4, 2, 16, aju.a.u(), aju.a.u(), false);
      a(☃, ☃, this.a - 6, 1, 5, this.a - 5, 2, 16, aju.a.u(), aju.a.u(), false);
      for (int ☃ = 5; ☃ <= 17; ☃ += 2)
      {
        a(☃, aju.A.a(aog.a.c.a()), 4, 1, ☃, ☃);
        a(☃, aju.A.a(aog.a.b.a()), 4, 2, ☃, ☃);
        a(☃, aju.A.a(aog.a.c.a()), this.a - 5, 1, ☃, ☃);
        a(☃, aju.A.a(aog.a.b.a()), this.a - 5, 2, ☃, ☃);
      }
      a(☃, aju.cu.a(☃), 10, 0, 7, ☃);
      a(☃, aju.cu.a(☃), 10, 0, 8, ☃);
      a(☃, aju.cu.a(☃), 9, 0, 9, ☃);
      a(☃, aju.cu.a(☃), 11, 0, 9, ☃);
      a(☃, aju.cu.a(☃), 8, 0, 10, ☃);
      a(☃, aju.cu.a(☃), 12, 0, 10, ☃);
      a(☃, aju.cu.a(☃), 7, 0, 10, ☃);
      a(☃, aju.cu.a(☃), 13, 0, 10, ☃);
      a(☃, aju.cu.a(☃), 9, 0, 11, ☃);
      a(☃, aju.cu.a(☃), 11, 0, 11, ☃);
      a(☃, aju.cu.a(☃), 10, 0, 12, ☃);
      a(☃, aju.cu.a(☃), 10, 0, 13, ☃);
      a(☃, aju.cu.a(☃), 10, 0, 10, ☃);
      for (int ☃ = 0; ☃ <= this.a - 1; ☃ += this.a - 1)
      {
        a(☃, aju.A.a(aog.a.c.a()), ☃, 2, 1, ☃);
        a(☃, aju.cu.a(☃), ☃, 2, 2, ☃);
        a(☃, aju.A.a(aog.a.c.a()), ☃, 2, 3, ☃);
        a(☃, aju.A.a(aog.a.c.a()), ☃, 3, 1, ☃);
        a(☃, aju.cu.a(☃), ☃, 3, 2, ☃);
        a(☃, aju.A.a(aog.a.c.a()), ☃, 3, 3, ☃);
        a(☃, aju.cu.a(☃), ☃, 4, 1, ☃);
        a(☃, aju.A.a(aog.a.b.a()), ☃, 4, 2, ☃);
        a(☃, aju.cu.a(☃), ☃, 4, 3, ☃);
        a(☃, aju.A.a(aog.a.c.a()), ☃, 5, 1, ☃);
        a(☃, aju.cu.a(☃), ☃, 5, 2, ☃);
        a(☃, aju.A.a(aog.a.c.a()), ☃, 5, 3, ☃);
        a(☃, aju.cu.a(☃), ☃, 6, 1, ☃);
        a(☃, aju.A.a(aog.a.b.a()), ☃, 6, 2, ☃);
        a(☃, aju.cu.a(☃), ☃, 6, 3, ☃);
        a(☃, aju.cu.a(☃), ☃, 7, 1, ☃);
        a(☃, aju.cu.a(☃), ☃, 7, 2, ☃);
        a(☃, aju.cu.a(☃), ☃, 7, 3, ☃);
        a(☃, aju.A.a(aog.a.c.a()), ☃, 8, 1, ☃);
        a(☃, aju.A.a(aog.a.c.a()), ☃, 8, 2, ☃);
        a(☃, aju.A.a(aog.a.c.a()), ☃, 8, 3, ☃);
      }
      for (int ☃ = 2; ☃ <= this.a - 3; ☃ += this.a - 3 - 2)
      {
        a(☃, aju.A.a(aog.a.c.a()), ☃ - 1, 2, 0, ☃);
        a(☃, aju.cu.a(☃), ☃, 2, 0, ☃);
        a(☃, aju.A.a(aog.a.c.a()), ☃ + 1, 2, 0, ☃);
        a(☃, aju.A.a(aog.a.c.a()), ☃ - 1, 3, 0, ☃);
        a(☃, aju.cu.a(☃), ☃, 3, 0, ☃);
        a(☃, aju.A.a(aog.a.c.a()), ☃ + 1, 3, 0, ☃);
        a(☃, aju.cu.a(☃), ☃ - 1, 4, 0, ☃);
        a(☃, aju.A.a(aog.a.b.a()), ☃, 4, 0, ☃);
        a(☃, aju.cu.a(☃), ☃ + 1, 4, 0, ☃);
        a(☃, aju.A.a(aog.a.c.a()), ☃ - 1, 5, 0, ☃);
        a(☃, aju.cu.a(☃), ☃, 5, 0, ☃);
        a(☃, aju.A.a(aog.a.c.a()), ☃ + 1, 5, 0, ☃);
        a(☃, aju.cu.a(☃), ☃ - 1, 6, 0, ☃);
        a(☃, aju.A.a(aog.a.b.a()), ☃, 6, 0, ☃);
        a(☃, aju.cu.a(☃), ☃ + 1, 6, 0, ☃);
        a(☃, aju.cu.a(☃), ☃ - 1, 7, 0, ☃);
        a(☃, aju.cu.a(☃), ☃, 7, 0, ☃);
        a(☃, aju.cu.a(☃), ☃ + 1, 7, 0, ☃);
        a(☃, aju.A.a(aog.a.c.a()), ☃ - 1, 8, 0, ☃);
        a(☃, aju.A.a(aog.a.c.a()), ☃, 8, 0, ☃);
        a(☃, aju.A.a(aog.a.c.a()), ☃ + 1, 8, 0, ☃);
      }
      a(☃, ☃, 8, 4, 0, 12, 6, 0, aju.A.a(aog.a.c.a()), aju.A.a(aog.a.c.a()), false);
      a(☃, aju.a.u(), 8, 6, 0, ☃);
      a(☃, aju.a.u(), 12, 6, 0, ☃);
      a(☃, aju.cu.a(☃), 9, 5, 0, ☃);
      a(☃, aju.A.a(aog.a.b.a()), 10, 5, 0, ☃);
      a(☃, aju.cu.a(☃), 11, 5, 0, ☃);
      
      a(☃, ☃, 8, -14, 8, 12, -11, 12, aju.A.a(aog.a.c.a()), aju.A.a(aog.a.c.a()), false);
      a(☃, ☃, 8, -10, 8, 12, -10, 12, aju.A.a(aog.a.b.a()), aju.A.a(aog.a.b.a()), false);
      a(☃, ☃, 8, -9, 8, 12, -9, 12, aju.A.a(aog.a.c.a()), aju.A.a(aog.a.c.a()), false);
      a(☃, ☃, 8, -8, 8, 12, -1, 12, aju.A.u(), aju.A.u(), false);
      a(☃, ☃, 9, -11, 9, 11, -1, 11, aju.a.u(), aju.a.u(), false);
      a(☃, aju.az.u(), 10, -11, 10, ☃);
      a(☃, ☃, 9, -13, 9, 11, -13, 11, aju.W.u(), aju.a.u(), false);
      a(☃, aju.a.u(), 8, -11, 10, ☃);
      a(☃, aju.a.u(), 8, -10, 10, ☃);
      a(☃, aju.A.a(aog.a.b.a()), 7, -10, 10, ☃);
      a(☃, aju.A.a(aog.a.c.a()), 7, -11, 10, ☃);
      a(☃, aju.a.u(), 12, -11, 10, ☃);
      a(☃, aju.a.u(), 12, -10, 10, ☃);
      a(☃, aju.A.a(aog.a.b.a()), 13, -10, 10, ☃);
      a(☃, aju.A.a(aog.a.c.a()), 13, -11, 10, ☃);
      a(☃, aju.a.u(), 10, -11, 8, ☃);
      a(☃, aju.a.u(), 10, -10, 8, ☃);
      a(☃, aju.A.a(aog.a.b.a()), 10, -10, 7, ☃);
      a(☃, aju.A.a(aog.a.c.a()), 10, -11, 7, ☃);
      a(☃, aju.a.u(), 10, -11, 12, ☃);
      a(☃, aju.a.u(), 10, -10, 12, ☃);
      a(☃, aju.A.a(aog.a.b.a()), 10, -10, 13, ☃);
      a(☃, aju.A.a(aog.a.c.a()), 10, -11, 13, ☃);
      for (cq ☃ : cq.c.a) {
        if (this.e[☃.b()] == 0)
        {
          int ☃ = ☃.g() * 2;
          int ☃ = ☃.i() * 2;
          this.e[☃.b()] = a(☃, ☃, ☃, 10 + ☃, -11, 10 + ☃, azt.k);
        }
      }
      return true;
    }
  }
  
  public static class c
    extends awa.d
  {
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private static final List<ow> i = Lists.newArrayList(new ow[] { new ow(ads.g, 0, 2, 7, 30) });
    
    public c() {}
    
    public c(Random ☃, int ☃, int ☃)
    {
      super(☃, 64, ☃, 12, 10, 15);
    }
    
    protected void a(dn ☃)
    {
      super.a(☃);
      ☃.a("placedMainChest", this.e);
      ☃.a("placedHiddenChest", this.f);
      ☃.a("placedTrap1", this.g);
      ☃.a("placedTrap2", this.h);
    }
    
    protected void b(dn ☃)
    {
      super.b(☃);
      this.e = ☃.p("placedMainChest");
      this.f = ☃.p("placedHiddenChest");
      this.g = ☃.p("placedTrap1");
      this.h = ☃.p("placedTrap2");
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (!a(☃, ☃, 0)) {
        return false;
      }
      a(☃, ☃, 0, -4, 0, this.a - 1, 0, this.c - 1, false, ☃, j);
      
      a(☃, ☃, 2, 1, 2, 9, 2, 2, false, ☃, j);
      a(☃, ☃, 2, 1, 12, 9, 2, 12, false, ☃, j);
      a(☃, ☃, 2, 1, 3, 2, 2, 11, false, ☃, j);
      a(☃, ☃, 9, 1, 3, 9, 2, 11, false, ☃, j);
      
      a(☃, ☃, 1, 3, 1, 10, 6, 1, false, ☃, j);
      a(☃, ☃, 1, 3, 13, 10, 6, 13, false, ☃, j);
      a(☃, ☃, 1, 3, 2, 1, 6, 12, false, ☃, j);
      a(☃, ☃, 10, 3, 2, 10, 6, 12, false, ☃, j);
      
      a(☃, ☃, 2, 3, 2, 9, 3, 12, false, ☃, j);
      a(☃, ☃, 2, 6, 2, 9, 6, 12, false, ☃, j);
      a(☃, ☃, 3, 7, 3, 8, 7, 11, false, ☃, j);
      a(☃, ☃, 4, 8, 4, 7, 8, 10, false, ☃, j);
      
      a(☃, ☃, 3, 1, 3, 8, 2, 11);
      a(☃, ☃, 4, 3, 6, 7, 3, 9);
      a(☃, ☃, 2, 4, 2, 9, 5, 12);
      a(☃, ☃, 4, 6, 5, 7, 6, 9);
      a(☃, ☃, 5, 7, 6, 6, 7, 8);
      
      a(☃, ☃, 5, 1, 2, 6, 2, 2);
      a(☃, ☃, 5, 2, 12, 6, 2, 12);
      a(☃, ☃, 5, 5, 1, 6, 5, 1);
      a(☃, ☃, 5, 5, 13, 6, 5, 13);
      a(☃, aju.a.u(), 1, 5, 5, ☃);
      a(☃, aju.a.u(), 10, 5, 5, ☃);
      a(☃, aju.a.u(), 1, 5, 9, ☃);
      a(☃, aju.a.u(), 10, 5, 9, ☃);
      for (int ☃ = 0; ☃ <= 14; ☃ += 14)
      {
        a(☃, ☃, 2, 4, ☃, 2, 5, ☃, false, ☃, j);
        a(☃, ☃, 4, 4, ☃, 4, 5, ☃, false, ☃, j);
        a(☃, ☃, 7, 4, ☃, 7, 5, ☃, false, ☃, j);
        a(☃, ☃, 9, 4, ☃, 9, 5, ☃, false, ☃, j);
      }
      a(☃, ☃, 5, 6, 0, 6, 6, 0, false, ☃, j);
      for (int ☃ = 0; ☃ <= 11; ☃ += 11)
      {
        for (int ☃ = 2; ☃ <= 12; ☃ += 2) {
          a(☃, ☃, ☃, 4, ☃, ☃, 5, ☃, false, ☃, j);
        }
        a(☃, ☃, ☃, 6, 5, ☃, 6, 5, false, ☃, j);
        a(☃, ☃, ☃, 6, 9, ☃, 6, 9, false, ☃, j);
      }
      a(☃, ☃, 2, 7, 2, 2, 9, 2, false, ☃, j);
      a(☃, ☃, 9, 7, 2, 9, 9, 2, false, ☃, j);
      a(☃, ☃, 2, 7, 12, 2, 9, 12, false, ☃, j);
      a(☃, ☃, 9, 7, 12, 9, 9, 12, false, ☃, j);
      a(☃, ☃, 4, 9, 4, 4, 9, 4, false, ☃, j);
      a(☃, ☃, 7, 9, 4, 7, 9, 4, false, ☃, j);
      a(☃, ☃, 4, 9, 10, 4, 9, 10, false, ☃, j);
      a(☃, ☃, 7, 9, 10, 7, 9, 10, false, ☃, j);
      a(☃, ☃, 5, 9, 7, 6, 9, 7, false, ☃, j);
      
      arc ☃ = aju.aw.u().a(aot.a, cq.f);
      arc ☃ = aju.aw.u().a(aot.a, cq.e);
      arc ☃ = aju.aw.u().a(aot.a, cq.d);
      arc ☃ = aju.aw.u().a(aot.a, cq.c);
      
      a(☃, ☃, 5, 9, 6, ☃);
      a(☃, ☃, 6, 9, 6, ☃);
      a(☃, ☃, 5, 9, 8, ☃);
      a(☃, ☃, 6, 9, 8, ☃);
      
      a(☃, ☃, 4, 0, 0, ☃);
      a(☃, ☃, 5, 0, 0, ☃);
      a(☃, ☃, 6, 0, 0, ☃);
      a(☃, ☃, 7, 0, 0, ☃);
      
      a(☃, ☃, 4, 1, 8, ☃);
      a(☃, ☃, 4, 2, 9, ☃);
      a(☃, ☃, 4, 3, 10, ☃);
      a(☃, ☃, 7, 1, 8, ☃);
      a(☃, ☃, 7, 2, 9, ☃);
      a(☃, ☃, 7, 3, 10, ☃);
      a(☃, ☃, 4, 1, 9, 4, 1, 9, false, ☃, j);
      a(☃, ☃, 7, 1, 9, 7, 1, 9, false, ☃, j);
      a(☃, ☃, 4, 1, 10, 7, 2, 10, false, ☃, j);
      
      a(☃, ☃, 5, 4, 5, 6, 4, 5, false, ☃, j);
      a(☃, ☃, 4, 4, 5, ☃);
      a(☃, ☃, 7, 4, 5, ☃);
      for (int ☃ = 0; ☃ < 4; ☃++)
      {
        a(☃, ☃, 5, 0 - ☃, 6 + ☃, ☃);
        a(☃, ☃, 6, 0 - ☃, 6 + ☃, ☃);
        a(☃, ☃, 5, 0 - ☃, 7 + ☃, 6, 0 - ☃, 9 + ☃);
      }
      a(☃, ☃, 1, -3, 12, 10, -1, 13);
      a(☃, ☃, 1, -3, 1, 3, -1, 13);
      a(☃, ☃, 1, -3, 1, 9, -1, 5);
      for (int ☃ = 1; ☃ <= 13; ☃ += 2) {
        a(☃, ☃, 1, -3, ☃, 1, -2, ☃, false, ☃, j);
      }
      for (int ☃ = 2; ☃ <= 12; ☃ += 2) {
        a(☃, ☃, 1, -1, ☃, 3, -1, ☃, false, ☃, j);
      }
      a(☃, ☃, 2, -2, 1, 5, -2, 1, false, ☃, j);
      a(☃, ☃, 7, -2, 1, 9, -2, 1, false, ☃, j);
      a(☃, ☃, 6, -3, 1, 6, -3, 1, false, ☃, j);
      a(☃, ☃, 6, -1, 1, 6, -1, 1, false, ☃, j);
      
      a(☃, aju.bR.u().a(api.a, cq.f).a(api.c, Boolean.valueOf(true)), 1, -3, 8, ☃);
      a(☃, aju.bR.u().a(api.a, cq.e).a(api.c, Boolean.valueOf(true)), 4, -3, 8, ☃);
      a(☃, aju.bS.u().a(aph.b, Boolean.valueOf(true)), 2, -3, 8, ☃);
      a(☃, aju.bS.u().a(aph.b, Boolean.valueOf(true)), 3, -3, 8, ☃);
      a(☃, aju.af.u(), 5, -3, 7, ☃);
      a(☃, aju.af.u(), 5, -3, 6, ☃);
      a(☃, aju.af.u(), 5, -3, 5, ☃);
      a(☃, aju.af.u(), 5, -3, 4, ☃);
      a(☃, aju.af.u(), 5, -3, 3, ☃);
      a(☃, aju.af.u(), 5, -3, 2, ☃);
      a(☃, aju.af.u(), 5, -3, 1, ☃);
      a(☃, aju.af.u(), 4, -3, 1, ☃);
      a(☃, aju.Y.u(), 3, -3, 1, ☃);
      if (!this.g) {
        this.g = a(☃, ☃, ☃, 3, -2, 1, cq.c, i, 2);
      }
      a(☃, aju.bn.u().a(apj.d, Boolean.valueOf(true)), 3, -2, 2, ☃);
      
      a(☃, aju.bR.u().a(api.a, cq.c).a(api.c, Boolean.valueOf(true)), 7, -3, 1, ☃);
      a(☃, aju.bR.u().a(api.a, cq.d).a(api.c, Boolean.valueOf(true)), 7, -3, 5, ☃);
      a(☃, aju.bS.u().a(aph.b, Boolean.valueOf(true)), 7, -3, 2, ☃);
      a(☃, aju.bS.u().a(aph.b, Boolean.valueOf(true)), 7, -3, 3, ☃);
      a(☃, aju.bS.u().a(aph.b, Boolean.valueOf(true)), 7, -3, 4, ☃);
      a(☃, aju.af.u(), 8, -3, 6, ☃);
      a(☃, aju.af.u(), 9, -3, 6, ☃);
      a(☃, aju.af.u(), 9, -3, 5, ☃);
      a(☃, aju.Y.u(), 9, -3, 4, ☃);
      a(☃, aju.af.u(), 9, -2, 4, ☃);
      if (!this.h) {
        this.h = a(☃, ☃, ☃, 9, -2, 3, cq.e, i, 2);
      }
      a(☃, aju.bn.u().a(apj.c, Boolean.valueOf(true)), 8, -1, 3, ☃);
      a(☃, aju.bn.u().a(apj.c, Boolean.valueOf(true)), 8, -2, 3, ☃);
      if (!this.e) {
        this.e = a(☃, ☃, ☃, 8, -3, 3, azt.l);
      }
      a(☃, aju.Y.u(), 9, -3, 2, ☃);
      a(☃, aju.Y.u(), 8, -3, 1, ☃);
      a(☃, aju.Y.u(), 4, -3, 5, ☃);
      a(☃, aju.Y.u(), 5, -2, 5, ☃);
      a(☃, aju.Y.u(), 5, -1, 5, ☃);
      a(☃, aju.Y.u(), 6, -3, 5, ☃);
      a(☃, aju.Y.u(), 7, -2, 5, ☃);
      a(☃, aju.Y.u(), 7, -1, 5, ☃);
      a(☃, aju.Y.u(), 8, -3, 5, ☃);
      a(☃, ☃, 9, -1, 1, 9, -1, 5, false, ☃, j);
      
      a(☃, ☃, 8, -3, 8, 10, -1, 10);
      a(☃, aju.bf.a(aoy.e), 8, -2, 11, ☃);
      a(☃, aju.bf.a(aoy.e), 9, -2, 11, ☃);
      a(☃, aju.bf.a(aoy.e), 10, -2, 11, ☃);
      arc ☃ = aju.ay.u().a(amn.a, amn.a.e);
      a(☃, ☃, 8, -2, 12, ☃);
      a(☃, ☃, 9, -2, 12, ☃);
      a(☃, ☃, 10, -2, 12, ☃);
      a(☃, ☃, 8, -3, 8, 8, -3, 10, false, ☃, j);
      a(☃, ☃, 10, -3, 8, 10, -3, 10, false, ☃, j);
      a(☃, aju.Y.u(), 10, -2, 9, ☃);
      a(☃, aju.af.u(), 8, -2, 9, ☃);
      a(☃, aju.af.u(), 8, -2, 10, ☃);
      a(☃, aju.af.u(), 10, -1, 9, ☃);
      a(☃, aju.F.u().a(aqu.H, cq.b), 9, -2, 8, ☃);
      a(☃, aju.F.u().a(aqu.H, cq.e), 10, -2, 8, ☃);
      a(☃, aju.F.u().a(aqu.H, cq.e), 10, -1, 8, ☃);
      a(☃, aju.bb.u().a(aoc.D, cq.c), 10, -2, 10, ☃);
      if (!this.f) {
        this.f = a(☃, ☃, ☃, 9, -3, 10, azt.l);
      }
      return true;
    }
    
    static class a
      extends awg.a
    {
      public void a(Random ☃, int ☃, int ☃, int ☃, boolean ☃)
      {
        if (☃.nextFloat() < 0.4F) {
          this.a = aju.e.u();
        } else {
          this.a = aju.Y.u();
        }
      }
    }
    
    private static awa.c.a j = new awa.c.a(null);
  }
  
  public static class e
    extends awa.d
  {
    private boolean e;
    
    public e() {}
    
    public e(Random ☃, int ☃, int ☃)
    {
      super(☃, 64, ☃, 7, 7, 9);
    }
    
    protected void a(dn ☃)
    {
      super.a(☃);
      ☃.a("Witch", this.e);
    }
    
    protected void b(dn ☃)
    {
      super.b(☃);
      this.e = ☃.p("Witch");
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (!a(☃, ☃, 0)) {
        return false;
      }
      a(☃, ☃, 1, 1, 1, 5, 1, 7, aju.f.a(anj.a.b.a()), aju.f.a(anj.a.b.a()), false);
      a(☃, ☃, 1, 4, 2, 5, 4, 7, aju.f.a(anj.a.b.a()), aju.f.a(anj.a.b.a()), false);
      a(☃, ☃, 2, 1, 0, 4, 1, 0, aju.f.a(anj.a.b.a()), aju.f.a(anj.a.b.a()), false);
      
      a(☃, ☃, 2, 2, 2, 3, 3, 2, aju.f.a(anj.a.b.a()), aju.f.a(anj.a.b.a()), false);
      a(☃, ☃, 1, 2, 3, 1, 3, 6, aju.f.a(anj.a.b.a()), aju.f.a(anj.a.b.a()), false);
      a(☃, ☃, 5, 2, 3, 5, 3, 6, aju.f.a(anj.a.b.a()), aju.f.a(anj.a.b.a()), false);
      a(☃, ☃, 2, 2, 7, 4, 3, 7, aju.f.a(anj.a.b.a()), aju.f.a(anj.a.b.a()), false);
      
      a(☃, ☃, 1, 0, 2, 1, 3, 2, aju.r.u(), aju.r.u(), false);
      a(☃, ☃, 5, 0, 2, 5, 3, 2, aju.r.u(), aju.r.u(), false);
      a(☃, ☃, 1, 0, 7, 1, 3, 7, aju.r.u(), aju.r.u(), false);
      a(☃, ☃, 5, 0, 7, 5, 3, 7, aju.r.u(), aju.r.u(), false);
      
      a(☃, aju.aO.u(), 2, 3, 2, ☃);
      a(☃, aju.aO.u(), 3, 3, 7, ☃);
      a(☃, aju.a.u(), 1, 3, 4, ☃);
      a(☃, aju.a.u(), 5, 3, 4, ☃);
      a(☃, aju.a.u(), 5, 3, 5, ☃);
      a(☃, aju.ca.u().a(aln.b, aln.a.r), 1, 3, 5, ☃);
      
      a(☃, aju.ai.u(), 3, 2, 6, ☃);
      a(☃, aju.bE.u(), 4, 2, 6, ☃);
      
      a(☃, aju.aO.u(), 1, 2, 1, ☃);
      a(☃, aju.aO.u(), 5, 2, 1, ☃);
      
      arc ☃ = aju.bU.u().a(aot.a, cq.c);
      arc ☃ = aju.bU.u().a(aot.a, cq.f);
      arc ☃ = aju.bU.u().a(aot.a, cq.e);
      arc ☃ = aju.bU.u().a(aot.a, cq.d);
      
      a(☃, ☃, 0, 4, 1, 6, 4, 1, ☃, ☃, false);
      a(☃, ☃, 0, 4, 2, 0, 4, 7, ☃, ☃, false);
      a(☃, ☃, 6, 4, 2, 6, 4, 7, ☃, ☃, false);
      a(☃, ☃, 0, 4, 8, 6, 4, 8, ☃, ☃, false);
      for (int ☃ = 2; ☃ <= 7; ☃ += 5) {
        for (int ☃ = 1; ☃ <= 5; ☃ += 4) {
          b(☃, aju.r.u(), ☃, -1, ☃, ☃);
        }
      }
      if (!this.e)
      {
        int ☃ = a(2, 5);
        int ☃ = d(2);
        int ☃ = b(2, 5);
        if (☃.b(new cj(☃, ☃, ☃)))
        {
          this.e = true;
          
          yz ☃ = new yz(☃);
          ☃.b(☃ + 0.5D, ☃, ☃ + 0.5D, 0.0F, 0.0F);
          ☃.a(☃.D(new cj(☃, ☃, ☃)), null);
          ☃.a(☃);
        }
      }
      return true;
    }
  }
  
  public static class b
    extends awa.d
  {
    private static final kk e = new kk("igloo/igloo_top");
    private static final kk f = new kk("igloo/igloo_middle");
    private static final kk g = new kk("igloo/igloo_bottom");
    
    public b() {}
    
    public b(Random ☃, int ☃, int ☃)
    {
      super(☃, 64, ☃, 7, 5, 8);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (!a(☃, ☃, -1)) {
        return false;
      }
      avp ☃ = c();
      cj ☃ = new cj(☃.a, ☃.b, ☃.c);
      aoe[] ☃ = aoe.values();
      MinecraftServer ☃ = ☃.u();
      awm ☃ = ☃.S().h();
      awn ☃ = new awn(amr.a, ☃[☃.nextInt(☃.length)], false, aju.cv, ☃);
      
      awo ☃ = ☃.a(☃, e);
      ☃.a(☃, ☃, ☃);
      if (☃.nextDouble() < 0.5D)
      {
        awo ☃ = ☃.a(☃, f);
        awo ☃ = ☃.a(☃, g);
        
        int ☃ = ☃.nextInt(8) + 4;
        for (int ☃ = 0; ☃ < ☃; ☃++)
        {
          cj ☃ = ☃.a(☃, new cj(3, -1 - ☃ * 3, 5), ☃, new cj(1, 2, 1));
          ☃.a(☃, ☃.a(☃), ☃);
        }
        cj ☃ = ☃.a(☃.a(☃, new cj(3, -1 - ☃ * 3, 5), ☃, new cj(3, 5, 7)));
        ☃.a(☃, ☃, ☃);
        
        Map<cj, String> ☃ = ☃.a(☃, ☃);
        for (Map.Entry<cj, String> ☃ : ☃.entrySet()) {
          if ("chest".equals(☃.getValue()))
          {
            cj ☃ = (cj)☃.getKey();
            ☃.a(☃, aju.a.u(), 3);
            apv ☃ = ☃.r(☃.b());
            if ((☃ instanceof apx)) {
              ((apx)☃).a(azt.m, ☃.nextLong());
            }
          }
        }
      }
      else
      {
        cj ☃ = awo.a(☃, new cj(3, 0, 5));
        ☃.a(☃.a(☃), aju.aJ.u(), 3);
      }
      return true;
    }
  }
}
