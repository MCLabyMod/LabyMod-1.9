import java.util.Random;

public class amj
  extends ajn
{
  public static class a
    extends apv
  {
    private adq a;
    
    public void a(dn ☃)
    {
      super.a(☃);
      if (☃.b("RecordItem", 10)) {
        a(adq.a(☃.o("RecordItem")));
      } else if (☃.h("Record") > 0) {
        a(new adq(ado.c(☃.h("Record"))));
      }
    }
    
    public void b(dn ☃)
    {
      super.b(☃);
      if (a() != null) {
        ☃.a("RecordItem", a().b(new dn()));
      }
    }
    
    public adq a()
    {
      return this.a;
    }
    
    public void a(adq ☃)
    {
      this.a = ☃;
      v_();
    }
  }
  
  public static final arn a = arn.a("has_record");
  
  protected amj()
  {
    super(axe.d, axf.l);
    w(this.A.b().a(a, Boolean.valueOf(false)));
    a(acq.c);
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, zj ☃, qm ☃, adq ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if (((Boolean)☃.c(a)).booleanValue())
    {
      e(☃, ☃, ☃);
      
      ☃ = ☃.a(a, Boolean.valueOf(false));
      ☃.a(☃, ☃, 2);
      return true;
    }
    return false;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, adq ☃)
  {
    if (☃.E) {
      return;
    }
    apv ☃ = ☃.r(☃);
    if (!(☃ instanceof amj.a)) {
      return;
    }
    ((amj.a)☃).a(☃.k());
    ☃.a(☃, ☃.a(a, Boolean.valueOf(true)), 2);
  }
  
  private void e(aht ☃, cj ☃, arc ☃)
  {
    if (☃.E) {
      return;
    }
    apv ☃ = ☃.r(☃);
    if (!(☃ instanceof amj.a)) {
      return;
    }
    amj.a ☃ = (amj.a)☃;
    adq ☃ = ☃.a();
    if (☃ == null) {
      return;
    }
    ☃.b(1010, ☃, 0);
    ☃.a(☃, null);
    ☃.a(null);
    
    float ☃ = 0.7F;
    double ☃ = ☃.r.nextFloat() * ☃ + (1.0F - ☃) * 0.5D;
    double ☃ = ☃.r.nextFloat() * ☃ + (1.0F - ☃) * 0.2D + 0.6D;
    double ☃ = ☃.r.nextFloat() * ☃ + (1.0F - ☃) * 0.5D;
    
    adq ☃ = ☃.k();
    
    yd ☃ = new yd(☃, ☃.p() + ☃, ☃.q() + ☃, ☃.r() + ☃, ☃);
    ☃.q();
    ☃.a(☃);
  }
  
  public void b(aht ☃, cj ☃, arc ☃)
  {
    e(☃, ☃, ☃);
    super.b(☃, ☃, ☃);
  }
  
  public void a(aht ☃, cj ☃, arc ☃, float ☃, int ☃)
  {
    if (☃.E) {
      return;
    }
    super.a(☃, ☃, ☃, ☃, 0);
  }
  
  public apv a(aht ☃, int ☃)
  {
    return new amj.a();
  }
  
  public boolean v(arc ☃)
  {
    return true;
  }
  
  public int d(arc ☃, aht ☃, cj ☃)
  {
    apv ☃ = ☃.r(☃);
    if ((☃ instanceof amj.a))
    {
      adq ☃ = ((amj.a)☃).a();
      if (☃ != null) {
        return ado.a(☃.b()) + 1 - ado.a(ads.cA);
      }
    }
    return 0;
  }
  
  public aob a(arc ☃)
  {
    return aob.d;
  }
  
  public arc a(int ☃)
  {
    return u().a(a, Boolean.valueOf(☃ > 0));
  }
  
  public int e(arc ☃)
  {
    return ((Boolean)☃.c(a)).booleanValue() ? 1 : 0;
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a });
  }
}
