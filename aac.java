import com.google.common.base.Optional;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class aac
  extends zy
{
  private static final ke<Optional<adq>> d = kh.a(yd.class, kg.f);
  private static final Logger e = LogManager.getLogger();
  
  public aac(aht ☃)
  {
    super(☃);
  }
  
  public aac(aht ☃, sa ☃, adq ☃)
  {
    super(☃, ☃);
    
    a(☃);
  }
  
  public aac(aht ☃, double ☃, double ☃, double ☃, adq ☃)
  {
    super(☃, ☃, ☃, ☃);
    if (☃ != null) {
      a(☃);
    }
  }
  
  protected void i()
  {
    R().a(d, Optional.absent());
  }
  
  public adq l()
  {
    adq ☃ = (adq)((Optional)R().a(d)).orNull();
    if ((☃ == null) || ((☃.b() != ads.bH) && (☃.b() != ads.bI)))
    {
      if (this.l != null) {
        e.error("ThrownPotion entity " + O() + " has no item?!");
      }
      return new adq(ads.bH);
    }
    return ☃;
  }
  
  public void a(adq ☃)
  {
    R().b(d, Optional.fromNullable(☃));
    R().b(d);
  }
  
  protected float j()
  {
    return 0.05F;
  }
  
  protected void a(bbi ☃)
  {
    if (this.l.E) {
      return;
    }
    adq ☃ = l();
    
    afe ☃ = afg.c(☃);
    List<rl> ☃ = afg.a(☃);
    if ((☃.a == bbi.a.b) && (☃ == afh.b) && (☃.isEmpty()))
    {
      cj ☃ = ☃.a().a(☃.b);
      a(☃);
      for (cq ☃ : cq.c.a) {
        a(☃.a(☃));
      }
      this.l.b(2002, new cj(this), afe.a(☃));
      T(); return;
    }
    Iterator ☃;
    if (!☃.isEmpty()) {
      if (n())
      {
        rp ☃ = new rp(this.l, this.p, this.q, this.r);
        ☃.a(k());
        ☃.a(3.0F);
        ☃.b(-0.5F);
        ☃.e(10);
        ☃.c(-☃.j() / ☃.o());
        ☃.a(☃);
        for (rl ☃ : afg.b(☃)) {
          ☃.a(new rl(☃.a(), ☃.b(), ☃.c()));
        }
        this.l.a(☃);
      }
      else
      {
        bbh ☃ = bl().b(4.0D, 2.0D, 4.0D);
        List<sa> ☃ = this.l.a(sa.class, ☃);
        if (!☃.isEmpty()) {
          for (☃ = ☃.iterator(); ☃.hasNext();)
          {
            ☃ = (sa)☃.next();
            if (☃.cD())
            {
              double ☃ = h(☃);
              if (☃ < 16.0D)
              {
                ☃ = 1.0D - Math.sqrt(☃) / 4.0D;
                if (☃ == ☃.d) {
                  ☃ = 1.0D;
                }
                for (rl ☃ : ☃)
                {
                  rk ☃ = ☃.a();
                  if (☃.b())
                  {
                    ☃.a(this, k(), ☃, ☃.c(), ☃);
                  }
                  else
                  {
                    int ☃ = (int)(☃ * ☃.b() + 0.5D);
                    if (☃ > 20) {
                      ☃.c(new rl(☃, ☃, ☃.c()));
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    sa ☃;
    double ☃;
    this.l.b(2002, new cj(this), afe.a(☃));
    
    T();
  }
  
  private boolean n()
  {
    return l().b() == ads.bI;
  }
  
  private void a(cj ☃)
  {
    if (this.l.o(☃).t() == aju.ab) {
      this.l.a(☃, aju.a.u(), 2);
    }
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    
    adq ☃ = adq.a(☃.o("Potion"));
    if (☃ == null) {
      T();
    } else {
      a(☃);
    }
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    adq ☃ = l();
    if (☃ != null) {
      ☃.a("Potion", ☃.b(new dn()));
    }
  }
}
