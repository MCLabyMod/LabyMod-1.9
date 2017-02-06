import java.util.List;

public class xt
  extends xr
{
  public xt(aht ☃)
  {
    super(☃);
  }
  
  public xt(aht ☃, cj ☃)
  {
    super(☃, ☃);
    b(☃.p() + 0.5D, ☃.q() + 0.5D, ☃.r() + 0.5D);
    float ☃ = 0.125F;
    float ☃ = 0.1875F;
    float ☃ = 0.25F;
    a(new bbh(this.p - 0.1875D, this.q - 0.25D + 0.125D, this.r - 0.1875D, this.p + 0.1875D, this.q + 0.25D + 0.125D, this.r + 0.1875D));
  }
  
  public void b(double ☃, double ☃, double ☃)
  {
    super.b(on.c(☃) + 0.5D, on.c(☃) + 0.5D, on.c(☃) + 0.5D);
  }
  
  protected void j()
  {
    this.p = (this.a.p() + 0.5D);
    this.q = (this.a.q() + 0.5D);
    this.r = (this.a.r() + 0.5D);
  }
  
  public void a(cq ☃) {}
  
  public int l()
  {
    return 9;
  }
  
  public int n()
  {
    return 9;
  }
  
  public float bn()
  {
    return -0.0625F;
  }
  
  public boolean a(double ☃)
  {
    return ☃ < 1024.0D;
  }
  
  public void a(rr ☃)
  {
    a(ng.dd, 1.0F, 1.0F);
  }
  
  public boolean d(dn ☃)
  {
    return false;
  }
  
  public void b(dn ☃) {}
  
  public void a(dn ☃) {}
  
  public boolean a(zj ☃, adq ☃, qm ☃)
  {
    if (this.l.E) {
      return true;
    }
    boolean ☃ = false;
    if ((☃ != null) && (☃.b() == ads.cx))
    {
      double ☃ = 7.0D;
      List<sb> ☃ = this.l.a(sb.class, new bbh(this.p - ☃, this.q - ☃, this.r - ☃, this.p + ☃, this.q + ☃, this.r + ☃));
      for (sb ☃ : ☃) {
        if ((☃.cP()) && (☃.cQ() == ☃))
        {
          ☃.b(this, true);
          ☃ = true;
        }
      }
    }
    if (!☃)
    {
      T();
      if (☃.bJ.d)
      {
        double ☃ = 7.0D;
        List<sb> ☃ = this.l.a(sb.class, new bbh(this.p - ☃, this.q - ☃, this.r - ☃, this.p + ☃, this.q + ☃, this.r + ☃));
        for (sb ☃ : ☃) {
          if ((☃.cP()) && (☃.cQ() == this)) {
            ☃.a(true, false);
          }
        }
      }
    }
    return true;
  }
  
  public boolean k()
  {
    return this.l.o(this.a).t() instanceof alj;
  }
  
  public static xt a(aht ☃, cj ☃)
  {
    xt ☃ = new xt(☃, ☃);
    ☃.k = true;
    ☃.a(☃);
    ☃.o();
    return ☃;
  }
  
  public static xt b(aht ☃, cj ☃)
  {
    int ☃ = ☃.p();
    int ☃ = ☃.q();
    int ☃ = ☃.r();
    
    List<xt> ☃ = ☃.a(xt.class, new bbh(☃ - 1.0D, ☃ - 1.0D, ☃ - 1.0D, ☃ + 1.0D, ☃ + 1.0D, ☃ + 1.0D));
    for (xt ☃ : ☃) {
      if (☃.q().equals(☃)) {
        return ☃;
      }
    }
    return null;
  }
  
  public void o()
  {
    a(ng.de, 1.0F, 1.0F);
  }
}
