import com.google.common.base.Optional;
import java.util.Map;
import java.util.Random;

public class xs
  extends xr
{
  private static final ke<Optional<adq>> c = kh.a(xs.class, kg.f);
  private static final ke<Integer> d = kh.a(xs.class, kg.b);
  private float e = 1.0F;
  
  public xs(aht ☃)
  {
    super(☃);
  }
  
  public xs(aht ☃, cj ☃, cq ☃)
  {
    super(☃, ☃);
    a(☃);
  }
  
  protected void i()
  {
    R().a(c, Optional.absent());
    R().a(d, Integer.valueOf(0));
  }
  
  public float aA()
  {
    return 0.0F;
  }
  
  public boolean a(rc ☃, float ☃)
  {
    if (b(☃)) {
      return false;
    }
    if ((!☃.c()) && (r() != null))
    {
      if (!this.l.E)
      {
        b(☃.j(), false);
        a(ng.cR, 1.0F, 1.0F);
        a(null);
      }
      return true;
    }
    return super.a(☃, ☃);
  }
  
  public int l()
  {
    return 12;
  }
  
  public int n()
  {
    return 12;
  }
  
  public boolean a(double ☃)
  {
    double ☃ = 16.0D;
    ☃ *= 64.0D * be();
    return ☃ < ☃ * ☃;
  }
  
  public void a(rr ☃)
  {
    a(ng.cP, 1.0F, 1.0F);
    b(☃, true);
  }
  
  public void o()
  {
    a(ng.cQ, 1.0F, 1.0F);
  }
  
  public void b(rr ☃, boolean ☃)
  {
    if (!this.l.U().b("doEntityDrops")) {
      return;
    }
    adq ☃ = r();
    if ((☃ instanceof zj))
    {
      zj ☃ = (zj)☃;
      if (☃.bJ.d)
      {
        b(☃);
        return;
      }
    }
    if (☃) {
      a(new adq(ads.bZ), 0.0F);
    }
    if ((☃ != null) && (this.S.nextFloat() < this.e))
    {
      ☃ = ☃.k();
      b(☃);
      a(☃, 0.0F);
    }
  }
  
  private void b(adq ☃)
  {
    if (☃ == null) {
      return;
    }
    if (☃.b() == ads.bk)
    {
      ayz ☃ = ((adw)☃.b()).a(☃, this.l);
      ☃.i.remove("frame-" + O());
    }
    ☃.a(null);
  }
  
  public adq r()
  {
    return (adq)((Optional)R().a(c)).orNull();
  }
  
  public void a(adq ☃)
  {
    a(☃, true);
  }
  
  private void a(adq ☃, boolean ☃)
  {
    if (☃ != null)
    {
      ☃ = ☃.k();
      ☃.b = 1;
      ☃.a(this);
    }
    R().b(c, Optional.fromNullable(☃));
    R().b(c);
    if (☃ != null) {
      a(ng.cO, 1.0F, 1.0F);
    }
    if ((☃) && (this.a != null)) {
      this.l.f(this.a, aju.a);
    }
  }
  
  public void a(ke<?> ☃)
  {
    if (☃.equals(c))
    {
      adq ☃ = r();
      if ((☃ != null) && (☃.z() != this)) {
        ☃.a(this);
      }
    }
  }
  
  public int s()
  {
    return ((Integer)R().a(d)).intValue();
  }
  
  public void a(int ☃)
  {
    a(☃, true);
  }
  
  private void a(int ☃, boolean ☃)
  {
    R().b(d, Integer.valueOf(☃ % 8));
    if ((☃) && (this.a != null)) {
      this.l.f(this.a, aju.a);
    }
  }
  
  public void b(dn ☃)
  {
    if (r() != null)
    {
      ☃.a("Item", r().b(new dn()));
      ☃.a("ItemRotation", (byte)s());
      ☃.a("ItemDropChance", this.e);
    }
    super.b(☃);
  }
  
  public void a(dn ☃)
  {
    dn ☃ = ☃.o("Item");
    if ((☃ != null) && (!☃.c_()))
    {
      a(adq.a(☃), false);
      a(☃.f("ItemRotation"), false);
      if (☃.b("ItemDropChance", 99)) {
        this.e = ☃.j("ItemDropChance");
      }
    }
    super.a(☃);
  }
  
  public boolean a(zj ☃, adq ☃, qm ☃)
  {
    if (r() == null)
    {
      if ((☃ != null) && 
        (!this.l.E))
      {
        a(☃);
        if (!☃.bJ.d) {
          ☃.b -= 1;
        }
      }
    }
    else if (!this.l.E)
    {
      a(ng.cS, 1.0F, 1.0F);
      a(s() + 1);
    }
    return true;
  }
  
  public int t()
  {
    if (r() == null) {
      return 0;
    }
    return s() % 8 + 1;
  }
}
