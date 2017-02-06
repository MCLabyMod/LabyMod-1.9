import com.mojang.authlib.GameProfile;
import java.io.PrintStream;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class kn
{
  public static final PrintStream a = System.out;
  private static boolean b = false;
  private static final Logger c = LogManager.getLogger();
  
  public static boolean a()
  {
    return b;
  }
  
  static void b()
  {
    aku.c.a(ads.g, new km()
    {
      protected zs a(aht ☃, cz ☃, adq ☃)
      {
        aad ☃ = new aad(☃, ☃.a(), ☃.b(), ☃.c());
        ☃.c = zm.a.b;
        
        return ☃;
      }
    });
    aku.c.a(ads.i, new km()
    {
      protected zs a(aht ☃, cz ☃, adq ☃)
      {
        aad ☃ = new aad(☃, ☃.a(), ☃.b(), ☃.c());
        ☃.a(☃);
        ☃.c = zm.a.b;
        
        return ☃;
      }
    });
    aku.c.a(ads.h, new km()
    {
      protected zs a(aht ☃, cz ☃, adq ☃)
      {
        zm ☃ = new zx(☃, ☃.a(), ☃.b(), ☃.c());
        ☃.c = zm.a.b;
        
        return ☃;
      }
    });
    aku.c.a(ads.aW, new km()
    {
      protected zs a(aht ☃, cz ☃, adq ☃)
      {
        return new zz(☃, ☃.a(), ☃.b(), ☃.c());
      }
    });
    aku.c.a(ads.aF, new km()
    {
      protected zs a(aht ☃, cz ☃, adq ☃)
      {
        return new zw(☃, ☃.a(), ☃.b(), ☃.c());
      }
    });
    aku.c.a(ads.bU, new km()
    {
      protected zs a(aht ☃, cz ☃, adq ☃)
      {
        return new aab(☃, ☃.a(), ☃.b(), ☃.c());
      }
      
      protected float a()
      {
        return super.a() * 0.5F;
      }
      
      protected float b()
      {
        return super.b() * 1.25F;
      }
    });
    aku.c.a(ads.bH, new cr()
    {
      public adq a(ck ☃, final adq ☃)
      {
        new km()
        {
          protected zs a(aht ☃, cz ☃, adq ☃)
          {
            return new aac(☃, ☃.a(), ☃.b(), ☃.c(), ☃.k());
          }
          
          protected float a()
          {
            return super.a() * 0.5F;
          }
          
          protected float b()
          {
            return super.b() * 1.25F;
          }
        }.a(☃, ☃);
      }
    });
    aku.c.a(ads.bI, new cr()
    {
      public adq a(ck ☃, final adq ☃)
      {
        new km()
        {
          protected zs a(aht ☃, cz ☃, adq ☃)
          {
            return new aac(☃, ☃.a(), ☃.b(), ☃.c(), ☃.k());
          }
          
          protected float a()
          {
            return super.a() * 0.5F;
          }
          
          protected float b()
          {
            return super.b() * 1.25F;
          }
        }.a(☃, ☃);
      }
    });
    aku.c.a(ads.bT, new cn()
    {
      public adq b(ck ☃, adq ☃)
      {
        cq ☃ = aku.e(☃.f());
        
        double ☃ = ☃.a() + ☃.g();
        double ☃ = ☃.d().q() + 0.2F;
        double ☃ = ☃.c() + ☃.i();
        
        rr ☃ = aeu.a(☃.i(), aeu.h(☃), ☃, ☃, ☃);
        if (((☃ instanceof sa)) && (☃.s())) {
          ☃.c(☃.q());
        }
        aeu.a(☃.i(), null, ☃, ☃);
        ☃.a(1);
        return ☃;
      }
    });
    aku.c.a(ads.cl, new cn()
    {
      public adq b(ck ☃, adq ☃)
      {
        cq ☃ = aku.e(☃.f());
        
        double ☃ = ☃.a() + ☃.g();
        double ☃ = ☃.d().q() + 0.2F;
        double ☃ = ☃.c() + ☃.i();
        
        zq ☃ = new zq(☃.i(), ☃, ☃, ☃, ☃);
        ☃.i().a(☃);
        
        ☃.a(1);
        return ☃;
      }
      
      protected void a(ck ☃)
      {
        ☃.i().b(1004, ☃.d(), 0);
      }
    });
    aku.c.a(ads.bV, new cn()
    {
      public adq b(ck ☃, adq ☃)
      {
        cq ☃ = aku.e(☃.f());
        
        cz ☃ = aku.a(☃);
        double ☃ = ☃.a() + ☃.g() * 0.3F;
        double ☃ = ☃.b() + ☃.h() * 0.3F;
        double ☃ = ☃.c() + ☃.i() * 0.3F;
        
        aht ☃ = ☃.i();
        Random ☃ = ☃.r;
        
        double ☃ = ☃.nextGaussian() * 0.05D + ☃.g();
        double ☃ = ☃.nextGaussian() * 0.05D + ☃.h();
        double ☃ = ☃.nextGaussian() * 0.05D + ☃.i();
        
        ☃.a(new zv(☃, ☃, ☃, ☃, ☃, ☃, ☃));
        
        ☃.a(1);
        return ☃;
      }
      
      protected void a(ck ☃)
      {
        ☃.i().b(1018, ☃.d(), 0);
      }
    });
    aku.c.a(ads.aG, new kn.a(aag.b.a));
    aku.c.a(ads.aH, new kn.a(aag.b.b));
    aku.c.a(ads.aI, new kn.a(aag.b.c));
    aku.c.a(ads.aJ, new kn.a(aag.b.d));
    aku.c.a(ads.aL, new kn.a(aag.b.f));
    aku.c.a(ads.aK, new kn.a(aag.b.e));
    
    cr ☃ = new cn()
    {
      private final cn b = new cn();
      
      public adq b(ck ☃, adq ☃)
      {
        acj ☃ = (acj)☃.b();
        cj ☃ = ☃.d().a(aku.e(☃.f()));
        if (☃.a(null, ☃.i(), ☃))
        {
          ☃.a(ads.ay);
          ☃.b = 1;
          
          return ☃;
        }
        return this.b.a(☃, ☃);
      }
    };
    aku.c.a(ads.aA, ☃);
    aku.c.a(ads.az, ☃);
    
    aku.c.a(ads.ay, new cn()
    {
      private final cn b = new cn();
      
      public adq b(ck ☃, adq ☃)
      {
        aht ☃ = ☃.i();
        
        cj ☃ = ☃.d().a(aku.e(☃.f()));
        
        arc ☃ = ☃.o(☃);
        ajt ☃ = ☃.t();
        axe ☃ = ☃.a();
        ado ☃;
        if ((axe.h.equals(☃)) && ((☃ instanceof amo)) && (((Integer)☃.c(amo.b)).intValue() == 0))
        {
          ☃ = ads.az;
        }
        else
        {
          ado ☃;
          if ((axe.i.equals(☃)) && ((☃ instanceof amo)) && (((Integer)☃.c(amo.b)).intValue() == 0)) {
            ☃ = ads.aA;
          } else {
            return super.b(☃, ☃);
          }
        }
        ado ☃;
        ☃.g(☃);
        if (--☃.b == 0)
        {
          ☃.a(☃);
          ☃.b = 1;
        }
        else if (((aqb)☃.h()).a(new adq(☃)) < 0)
        {
          this.b.a(☃, new adq(☃));
        }
        return ☃;
      }
    });
    aku.c.a(ads.d, new cn()
    {
      private boolean b = true;
      
      protected adq b(ck ☃, adq ☃)
      {
        aht ☃ = ☃.i();
        
        cj ☃ = ☃.d().a(aku.e(☃.f()));
        if (☃.d(☃))
        {
          ☃.a(☃, aju.ab.u());
          if (☃.a(1, ☃.r)) {
            ☃.b = 0;
          }
        }
        else if (☃.o(☃).t() == aju.W)
        {
          aju.W.d(☃, ☃, aju.W.u().a(ape.a, Boolean.valueOf(true)));
          ☃.g(☃);
        }
        else
        {
          this.b = false;
        }
        return ☃;
      }
      
      protected void a(ck ☃)
      {
        if (this.b) {
          ☃.i().b(1000, ☃.d(), 0);
        } else {
          ☃.i().b(1001, ☃.d(), 0);
        }
      }
    });
    aku.c.a(ads.bd, new cn()
    {
      private boolean b = true;
      
      protected adq b(ck ☃, adq ☃)
      {
        if (act.a == act.a(☃.i()))
        {
          aht ☃ = ☃.i();
          
          cj ☃ = ☃.d().a(aku.e(☃.f()));
          if (acu.a(☃, ☃, ☃))
          {
            if (!☃.E) {
              ☃.b(2005, ☃, 0);
            }
          }
          else {
            this.b = false;
          }
          return ☃;
        }
        return super.b(☃, ☃);
      }
      
      protected void a(ck ☃)
      {
        if (this.b) {
          ☃.i().b(1000, ☃.d(), 0);
        } else {
          ☃.i().b(1001, ☃.d(), 0);
        }
      }
    });
    aku.c.a(ado.a(aju.W), new cn()
    {
      protected adq b(ck ☃, adq ☃)
      {
        aht ☃ = ☃.i();
        cj ☃ = ☃.d().a(aku.e(☃.f()));
        
        ye ☃ = new ye(☃, ☃.p() + 0.5D, ☃.q(), ☃.r() + 0.5D, null);
        ☃.a(☃);
        ☃.a(null, ☃.p, ☃.q, ☃.r, ng.gj, nh.e, 1.0F, 1.0F);
        
        ☃.b -= 1;
        return ☃;
      }
    });
    aku.c.a(ads.ch, new cn()
    {
      private boolean b = true;
      
      protected adq b(ck ☃, adq ☃)
      {
        aht ☃ = ☃.i();
        cq ☃ = aku.e(☃.f());
        cj ☃ = ☃.d().a(☃);
        aok ☃ = aju.ce;
        if ((☃.d(☃)) && (☃.b(☃, ☃, ☃)))
        {
          if (!☃.E)
          {
            ☃.a(☃, ☃.u().a(aok.a, cq.b), 3);
            apv ☃ = ☃.r(☃);
            if ((☃ instanceof aqo))
            {
              if (☃.i() == 3)
              {
                GameProfile ☃ = null;
                if (☃.n())
                {
                  dn ☃ = ☃.o();
                  if (☃.b("SkullOwner", 10))
                  {
                    ☃ = dy.a(☃.o("SkullOwner"));
                  }
                  else if (☃.b("SkullOwner", 8))
                  {
                    String ☃ = ☃.l("SkullOwner");
                    if (!os.b(☃)) {
                      ☃ = new GameProfile(null, ☃);
                    }
                  }
                }
                ((aqo)☃).a(☃);
              }
              else
              {
                ((aqo)☃).a(☃.i());
              }
              ((aqo)☃).b(☃.d().b() * 4);
              aju.ce.a(☃, ☃, (aqo)☃);
            }
            ☃.b -= 1;
          }
        }
        else if (abw.a(☃, ☃) == null) {
          this.b = false;
        }
        return ☃;
      }
      
      protected void a(ck ☃)
      {
        if (this.b) {
          ☃.i().b(1000, ☃.d(), 0);
        } else {
          ☃.i().b(1001, ☃.d(), 0);
        }
      }
    });
    aku.c.a(ado.a(aju.aU), new cn()
    {
      private boolean b = true;
      
      protected adq b(ck ☃, adq ☃)
      {
        aht ☃ = ☃.i();
        cj ☃ = ☃.d().a(aku.e(☃.f()));
        anq ☃ = (anq)aju.aU;
        if ((☃.d(☃)) && (☃.b(☃, ☃)))
        {
          if (!☃.E) {
            ☃.a(☃, ☃.u(), 3);
          }
          ☃.b -= 1;
        }
        else
        {
          adq ☃ = abw.a(☃, ☃);
          if (☃ == null) {
            this.b = false;
          }
        }
        return ☃;
      }
      
      protected void a(ck ☃)
      {
        if (this.b) {
          ☃.i().b(1000, ☃.d(), 0);
        } else {
          ☃.i().b(1001, ☃.d(), 0);
        }
      }
    });
  }
  
  public static void c()
  {
    if (b) {
      return;
    }
    b = true;
    if (c.isDebugEnabled()) {
      d();
    }
    nf.b();
    
    ajt.x();
    all.e();
    
    rk.k();
    agm.f();
    
    ado.t();
    
    afe.b();
    aff.a();
    
    nt.a();
    
    aig.q();
    
    b();
  }
  
  private static void d()
  {
    System.setErr(new ks("STDERR", System.err));
    System.setOut(new ks("STDOUT", a));
  }
  
  public static void a(String ☃)
  {
    a.println(☃);
  }
  
  public static class a
    extends cn
  {
    private final cn b = new cn();
    private final aag.b c;
    
    public a(aag.b ☃)
    {
      this.c = ☃;
    }
    
    public adq b(ck ☃, adq ☃)
    {
      cq ☃ = aku.e(☃.f());
      aht ☃ = ☃.i();
      
      double ☃ = ☃.a() + ☃.g() * 1.125F;
      double ☃ = ☃.b() + ☃.h() * 1.125F;
      double ☃ = ☃.c() + ☃.i() * 1.125F;
      
      cj ☃ = ☃.d().a(☃);
      axe ☃ = ☃.o(☃).a();
      double ☃;
      if (axe.h.equals(☃))
      {
        ☃ = 1.0D;
      }
      else
      {
        double ☃;
        if ((axe.a.equals(☃)) && (axe.h.equals(☃.o(☃.b()).a()))) {
          ☃ = 0.0D;
        } else {
          return this.b.a(☃, ☃);
        }
      }
      double ☃;
      aag ☃ = new aag(☃, ☃, ☃ + ☃, ☃);
      ☃.a(this.c);
      ☃.v = ☃.d().l();
      ☃.a(☃);
      
      ☃.a(1);
      return ☃;
    }
    
    protected void a(ck ☃)
    {
      ☃.i().b(1000, ☃.d(), 0);
    }
  }
}
