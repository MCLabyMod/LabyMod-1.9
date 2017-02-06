import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class aqq
  extends apv
  implements ky
{
  private static final Logger a = ;
  private long f = 0L;
  private int g = 0;
  private cj h;
  private boolean i;
  
  public void b(dn ☃)
  {
    super.b(☃);
    ☃.a("Age", this.f);
    if (this.h != null) {
      ☃.a("ExitPortal", dy.a(this.h));
    }
    if (this.i) {
      ☃.a("ExactTeleport", this.i);
    }
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    this.f = ☃.i("Age");
    if (☃.b("ExitPortal", 10)) {
      this.h = dy.c(☃.o("ExitPortal"));
    }
    this.i = ☃.p("ExactTeleport");
  }
  
  public double s()
  {
    return 65536.0D;
  }
  
  public void c()
  {
    boolean ☃ = b();
    boolean ☃ = d();
    this.f += 1L;
    if (☃)
    {
      this.g -= 1;
    }
    else if (!this.b.E)
    {
      List<rr> ☃ = this.b.a(rr.class, new bbh(v()));
      if (!☃.isEmpty()) {
        a((rr)☃.get(0));
      }
    }
    if ((☃ != b()) || (☃ != d())) {
      v_();
    }
  }
  
  public boolean b()
  {
    return this.f < 200L;
  }
  
  public boolean d()
  {
    return this.g > 0;
  }
  
  public float e()
  {
    return on.a((float)this.f / 200.0F, 0.0F, 1.0F);
  }
  
  public float g()
  {
    return 1.0F - on.a(this.g / 20.0F, 0.0F, 1.0F);
  }
  
  public ff<?> D_()
  {
    dn ☃ = new dn();
    b(☃);
    return new fs(this.c, 8, ☃);
  }
  
  public void h()
  {
    if (!this.b.E)
    {
      this.g = 20;
      this.b.c(v(), w(), 1, 0);
      v_();
    }
  }
  
  public boolean c(int ☃, int ☃)
  {
    if (☃ == 1)
    {
      this.g = 20;
      return true;
    }
    return super.c(☃, ☃);
  }
  
  public void a(rr ☃)
  {
    if ((this.b.E) || (d())) {
      return;
    }
    this.g = 100;
    if ((this.h == null) && ((this.b.s instanceof atb))) {
      k();
    }
    if (this.h != null)
    {
      cj ☃ = this.i ? this.h : j();
      ☃.a(☃.p() + 0.5D, ☃.q() + 0.5D, ☃.r() + 0.5D);
    }
    h();
  }
  
  private cj j()
  {
    cj ☃ = a(this.b, this.h, 5, false);
    a.debug("Best exit position for portal at " + this.h + " is " + ☃);
    return ☃.a();
  }
  
  private void k()
  {
    bbj ☃ = new bbj(v().p(), 0.0D, v().r()).a();
    bbj ☃ = ☃.a(1024.0D);
    
    int ☃ = 16;
    while ((a(this.b, ☃).g() > 0) && (☃-- > 0))
    {
      a.debug("Skipping backwards past nonempty chunk at " + ☃);
      ☃ = ☃.e(☃.a(-16.0D));
    }
    ☃ = 16;
    while ((a(this.b, ☃).g() == 0) && (☃-- > 0))
    {
      a.debug("Skipping forward past empty chunk at " + ☃);
      ☃ = ☃.e(☃.a(16.0D));
    }
    a.debug("Found chunk at " + ☃);
    
    ase ☃ = a(this.b, ☃);
    
    this.h = a(☃);
    if (this.h == null)
    {
      this.h = new cj(☃.b + 0.5D, 75.0D, ☃.d + 0.5D);
      a.debug("Failed to find suitable block, settling on " + this.h);
      new aub().b(this.b, new Random(this.h.g()), this.h);
    }
    else
    {
      a.debug("Found block at " + this.h);
    }
    this.h = a(this.b, this.h, 16, true);
    a.debug("Creating portal at " + this.h);
    
    this.h = this.h.b(10);
    b(this.h);
    v_();
  }
  
  private static cj a(aht ☃, cj ☃, int ☃, boolean ☃)
  {
    cj ☃ = null;
    for (int ☃ = -☃; ☃ <= ☃; ☃++) {
      for (int ☃ = -☃; ☃ <= ☃; ☃++) {
        if ((☃ != 0) || (☃ != 0) || (☃)) {
          for (int ☃ = 255; ☃ > (☃ == null ? 0 : ☃.q()); ☃--)
          {
            cj ☃ = new cj(☃.p() + ☃, ☃, ☃.r() + ☃);
            arc ☃ = ☃.o(☃);
            if ((☃.k()) && ((☃) || (☃.t() != aju.h)))
            {
              ☃ = ☃;
              break;
            }
          }
        }
      }
    }
    return ☃ == null ? ☃ : ☃;
  }
  
  private static ase a(aht ☃, bbj ☃)
  {
    return ☃.a(on.c(☃.b / 16.0D), on.c(☃.d / 16.0D));
  }
  
  private static cj a(ase ☃)
  {
    cj ☃ = new cj(☃.b * 16, 30, ☃.c * 16);
    int ☃ = ☃.g() + 16 - 1;
    cj ☃ = new cj(☃.b * 16 + 16 - 1, ☃, ☃.c * 16 + 16 - 1);
    cj ☃ = null;
    double ☃ = 0.0D;
    for (cj ☃ : cj.a(☃, ☃))
    {
      arc ☃ = ☃.a(☃);
      if ((☃.t() == aju.bH) && (!☃.a(☃.b(1)).k()) && (!☃.a(☃.b(2)).k()))
      {
        double ☃ = ☃.f(0.0D, 0.0D, 0.0D);
        if ((☃ == null) || (☃ < ☃))
        {
          ☃ = ☃;
          ☃ = ☃;
        }
      }
    }
    return ☃;
  }
  
  private void b(cj ☃)
  {
    new aua().b(this.b, new Random(), ☃);
    apv ☃ = this.b.r(☃);
    if ((☃ instanceof aqq))
    {
      aqq ☃ = (aqq)☃;
      ☃.h = new cj(v());
      ☃.v_();
    }
    else
    {
      a.warn("Couldn't save exit portal at " + ☃);
    }
  }
  
  public boolean a(cq ☃)
  {
    return w().u().c(this.b, v(), ☃);
  }
  
  public int i()
  {
    int ☃ = 0;
    for (cq ☃ : cq.values()) {
      ☃ += (a(☃) ? 1 : 0);
    }
    return ☃;
  }
}
