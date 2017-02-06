import com.google.common.base.Optional;
import java.util.Random;

public class wt
  extends rr
{
  private static final ke<Optional<cj>> b = kh.a(wt.class, kg.k);
  private static final ke<Boolean> c = kh.a(wt.class, kg.h);
  public int a;
  
  public wt(aht ☃)
  {
    super(☃);
    this.i = true;
    a(2.0F, 2.0F);
    
    this.a = this.S.nextInt(100000);
  }
  
  public wt(aht ☃, double ☃, double ☃, double ☃)
  {
    this(☃);
    b(☃, ☃, ☃);
  }
  
  protected boolean ae()
  {
    return false;
  }
  
  protected void i()
  {
    R().a(b, Optional.absent());
    R().a(c, Boolean.valueOf(true));
  }
  
  public void m()
  {
    this.m = this.p;
    this.n = this.q;
    this.o = this.r;
    this.a += 1;
    if (!this.l.E)
    {
      cj ☃ = new cj(this);
      if (((this.l.s instanceof atb)) && (this.l.o(☃).t() != aju.ab)) {
        this.l.a(☃, aju.ab.u());
      }
    }
  }
  
  protected void b(dn ☃)
  {
    if (j() != null) {
      ☃.a("BeamTarget", dy.a(j()));
    }
    ☃.a("ShowBottom", k());
  }
  
  protected void a(dn ☃)
  {
    if (☃.b("BeamTarget", 10)) {
      a(dy.c(☃.o("BeamTarget")));
    }
    if (☃.b("ShowBottom", 1)) {
      a(☃.p("ShowBottom"));
    }
  }
  
  public boolean ap()
  {
    return true;
  }
  
  public boolean a(rc ☃, float ☃)
  {
    if (b(☃)) {
      return false;
    }
    if ((☃.j() instanceof wu)) {
      return false;
    }
    if ((!this.F) && (!this.l.E))
    {
      T();
      if (!this.l.E)
      {
        this.l.a(null, this.p, this.q, this.r, 6.0F, true);
        a(☃);
      }
    }
    return true;
  }
  
  public void Q()
  {
    a(rc.l);
    super.Q();
  }
  
  private void a(rc ☃)
  {
    if ((this.l.s instanceof atb))
    {
      atb ☃ = (atb)this.l.s;
      ata ☃ = ☃.s();
      if (☃ != null) {
        ☃.a(this, ☃);
      }
    }
  }
  
  public void a(cj ☃)
  {
    R().b(b, Optional.fromNullable(☃));
  }
  
  public cj j()
  {
    return (cj)((Optional)R().a(b)).orNull();
  }
  
  public void a(boolean ☃)
  {
    R().b(c, Boolean.valueOf(☃));
  }
  
  public boolean k()
  {
    return ((Boolean)R().a(c)).booleanValue();
  }
  
  public boolean a(double ☃)
  {
    return (super.a(☃)) || (j() != null);
  }
}
