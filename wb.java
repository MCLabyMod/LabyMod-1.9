import java.util.List;
import java.util.Random;

public class wb
  extends sk
{
  private static final ke<Integer> bz = kh.a(wb.class, kg.b);
  private ta<zj> bA;
  private up bB;
  
  public wb(aht ☃)
  {
    super(☃);
    a(0.6F, 0.7F);
  }
  
  protected void r()
  {
    this.bp.a(1, new th(this));
    this.bp.a(2, this.bx = new um(this));
    this.bp.a(3, this.bB = new up(this, 0.6D, ads.bb, true));
    this.bp.a(5, new ti(this, 1.0D, 10.0F, 5.0F));
    this.bp.a(6, new tz(this, 0.8D));
    this.bp.a(7, new to(this, 0.3F));
    this.bp.a(8, new ty(this));
    this.bp.a(9, new td(this, 0.8D));
    this.bp.a(10, new ug(this, 0.8D));
    this.bp.a(11, new tp(this, zj.class, 10.0F));
    
    this.bq.a(1, new uz(this, vx.class, false, null));
  }
  
  protected void i()
  {
    super.i();
    
    this.Z.a(bz, Integer.valueOf(0));
  }
  
  public void M()
  {
    if (u().a())
    {
      double ☃ = u().b();
      if (☃ == 0.6D)
      {
        d(true);
        e(false);
      }
      else if (☃ == 1.33D)
      {
        d(false);
        e(true);
      }
      else
      {
        d(false);
        e(false);
      }
    }
    else
    {
      d(false);
      e(false);
    }
  }
  
  protected boolean K()
  {
    return (!cZ()) && (this.T > 2400);
  }
  
  protected void bA()
  {
    super.bA();
    
    a(yt.a).a(10.0D);
    a(yt.d).a(0.30000001192092896D);
  }
  
  public void e(float ☃, float ☃) {}
  
  public void b(dn ☃)
  {
    super.b(☃);
    ☃.a("CatType", dh());
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    l(☃.h("CatType"));
  }
  
  protected nf G()
  {
    if (cZ())
    {
      if (df()) {
        return ng.T;
      }
      if (this.S.nextInt(4) == 0) {
        return ng.U;
      }
      return ng.P;
    }
    return null;
  }
  
  protected nf bR()
  {
    return ng.S;
  }
  
  protected nf bS()
  {
    return ng.Q;
  }
  
  protected float cd()
  {
    return 0.4F;
  }
  
  public boolean B(rr ☃)
  {
    return ☃.a(rc.a(this), 3.0F);
  }
  
  public boolean a(rc ☃, float ☃)
  {
    if (b(☃)) {
      return false;
    }
    if (this.bx != null) {
      this.bx.a(false);
    }
    return super.a(☃, ☃);
  }
  
  protected kk J()
  {
    return azt.J;
  }
  
  public boolean a(zj ☃, qm ☃, adq ☃)
  {
    if (cZ())
    {
      if ((d(☃)) && 
        (!this.l.E) && (!e(☃))) {
        this.bx.a(!db());
      }
    }
    else if (((this.bB == null) || (this.bB.f())) && (☃ != null) && (☃.b() == ads.bb) && (☃.h(this) < 9.0D))
    {
      if (!☃.bJ.d) {
        ☃.b -= 1;
      }
      if (!this.l.E) {
        if (this.S.nextInt(3) == 0)
        {
          p(true);
          l(1 + this.l.r.nextInt(3));
          b(☃.bc());
          o(true);
          this.bx.a(true);
          this.l.a(this, (byte)7);
        }
        else
        {
          o(false);
          this.l.a(this, (byte)6);
        }
      }
      return true;
    }
    return super.a(☃, ☃, ☃);
  }
  
  public wb b(ro ☃)
  {
    wb ☃ = new wb(this.l);
    if (cZ())
    {
      ☃.b(b());
      ☃.p(true);
      ☃.l(dh());
    }
    return ☃;
  }
  
  public boolean e(adq ☃)
  {
    return (☃ != null) && (☃.b() == ads.bb);
  }
  
  public boolean a(vw ☃)
  {
    if (☃ == this) {
      return false;
    }
    if (!cZ()) {
      return false;
    }
    if (!(☃ instanceof wb)) {
      return false;
    }
    wb ☃ = (wb)☃;
    if (!☃.cZ()) {
      return false;
    }
    return (df()) && (☃.df());
  }
  
  public int dh()
  {
    return ((Integer)this.Z.a(bz)).intValue();
  }
  
  public void l(int ☃)
  {
    this.Z.b(bz, Integer.valueOf(☃));
  }
  
  public boolean cF()
  {
    if (this.l.r.nextInt(3) == 0) {
      return false;
    }
    return true;
  }
  
  public boolean cG()
  {
    if ((this.l.a(bl(), this)) && (this.l.a(this, bl()).isEmpty()) && (!this.l.e(bl())))
    {
      cj ☃ = new cj(this.p, bl().b, this.r);
      if (☃.q() < this.l.K()) {
        return false;
      }
      arc ☃ = this.l.o(☃.b());
      ajt ☃ = ☃.t();
      if ((☃ == aju.c) || (☃.a() == axe.j)) {
        return true;
      }
    }
    return false;
  }
  
  public String h_()
  {
    if (o_()) {
      return bf();
    }
    if (cZ()) {
      return di.a("entity.Cat.name");
    }
    return super.h_();
  }
  
  public void p(boolean ☃)
  {
    super.p(☃);
  }
  
  protected void da()
  {
    if (this.bA == null) {
      this.bA = new ta(this, zj.class, 16.0F, 0.8D, 1.33D);
    }
    this.bp.a(this.bA);
    if (!cZ()) {
      this.bp.a(4, this.bA);
    }
  }
  
  public sd a(ql ☃, sd ☃)
  {
    ☃ = super.a(☃, ☃);
    if (this.l.r.nextInt(7) == 0) {
      for (int ☃ = 0; ☃ < 2; ☃++)
      {
        wb ☃ = new wb(this.l);
        ☃.b(this.p, this.q, this.r, this.v, 0.0F);
        ☃.b_(41536);
        this.l.a(☃);
      }
    }
    return ☃;
  }
}
