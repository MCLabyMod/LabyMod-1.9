import java.util.List;

public class aam
  extends aak
  implements aqh
{
  private boolean a = true;
  private int b = -1;
  private cj c = cj.a;
  
  public aam(aht ☃)
  {
    super(☃);
  }
  
  public aam(aht ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃);
  }
  
  public aah.a v()
  {
    return aah.a.f;
  }
  
  public arc x()
  {
    return aju.cp.u();
  }
  
  public int A()
  {
    return 1;
  }
  
  public int u_()
  {
    return 5;
  }
  
  public boolean a(zj ☃, adq ☃, qm ☃)
  {
    if (!this.l.E) {
      ☃.a(this);
    }
    return true;
  }
  
  public void a(int ☃, int ☃, int ☃, boolean ☃)
  {
    boolean ☃ = !☃;
    if (☃ != C()) {
      k(☃);
    }
  }
  
  public boolean C()
  {
    return this.a;
  }
  
  public void k(boolean ☃)
  {
    this.a = ☃;
  }
  
  public aht D()
  {
    return this.l;
  }
  
  public double E()
  {
    return this.p;
  }
  
  public double F()
  {
    return this.q + 0.5D;
  }
  
  public double G()
  {
    return this.r;
  }
  
  public void m()
  {
    super.m();
    if ((!this.l.E) && (au()) && (C()))
    {
      cj ☃ = new cj(this);
      if (☃.equals(this.c)) {
        this.b -= 1;
      } else {
        l(0);
      }
      if (!J())
      {
        l(0);
        if (I())
        {
          l(4);
          v_();
        }
      }
    }
  }
  
  public boolean I()
  {
    if (aqi.a(this)) {
      return true;
    }
    List<yd> ☃ = this.l.a(yd.class, bl().b(0.25D, 0.0D, 0.25D), rv.a);
    if (!☃.isEmpty()) {
      aqi.a(this, (yd)☃.get(0));
    }
    return false;
  }
  
  public void a(rc ☃)
  {
    super.a(☃);
    if (this.l.U().b("doEntityDrops")) {
      a(ado.a(aju.cp), 1, 0.0F);
    }
  }
  
  protected void b(dn ☃)
  {
    super.b(☃);
    ☃.a("TransferCooldown", this.b);
    ☃.a("Enabled", this.a);
  }
  
  protected void a(dn ☃)
  {
    super.a(☃);
    this.b = ☃.h("TransferCooldown");
    this.a = (☃.e("Enabled") ? ☃.p("Enabled") : true);
  }
  
  public void l(int ☃)
  {
    this.b = ☃;
  }
  
  public boolean J()
  {
    return this.b > 0;
  }
  
  public String k()
  {
    return "minecraft:hopper";
  }
  
  public aau a(zi ☃, zj ☃)
  {
    return new abj(☃, this, ☃);
  }
}
