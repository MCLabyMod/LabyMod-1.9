import com.google.common.base.Predicate;
import java.util.List;
import java.util.Random;

public class amz
  extends aml
{
  public static final arp<anj.a> e = arp.a("variant", anj.a.class, new Predicate()
  {
    public boolean a(anj.a ☃)
    {
      return ☃.a() >= 4;
    }
  });
  
  public amz()
  {
    w(this.A.b().a(e, anj.a.e).a(b, Boolean.valueOf(true)).a(a, Boolean.valueOf(true)));
  }
  
  protected void a(aht ☃, cj ☃, arc ☃, int ☃)
  {
    if ((☃.c(e) == anj.a.f) && (☃.r.nextInt(☃) == 0)) {
      a(☃, ☃, new adq(ads.e));
    }
  }
  
  public int d(arc ☃)
  {
    return ((anj.a)☃.c(e)).a();
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(this, 1, ☃.t().e(☃) & 0x3);
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    ☃.add(new adq(☃, 1, 0));
    ☃.add(new adq(☃, 1, 1));
  }
  
  protected adq u(arc ☃)
  {
    return new adq(ado.a(this), 1, ((anj.a)☃.c(e)).a() - 4);
  }
  
  public arc a(int ☃)
  {
    return u().a(e, e(☃)).a(a, Boolean.valueOf((☃ & 0x4) == 0)).a(b, Boolean.valueOf((☃ & 0x8) > 0));
  }
  
  public int e(arc ☃)
  {
    int ☃ = 0;
    
    ☃ |= ((anj.a)☃.c(e)).a() - 4;
    if (!((Boolean)☃.c(a)).booleanValue()) {
      ☃ |= 0x4;
    }
    if (((Boolean)☃.c(b)).booleanValue()) {
      ☃ |= 0x8;
    }
    return ☃;
  }
  
  public anj.a e(int ☃)
  {
    return anj.a.a((☃ & 0x3) + 4);
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { e, b, a });
  }
  
  public void a(aht ☃, zj ☃, cj ☃, arc ☃, apv ☃, adq ☃)
  {
    if ((!☃.E) && (☃ != null) && (☃.b() == ads.bl))
    {
      ☃.b(nt.a(this));
      
      a(☃, ☃, new adq(ado.a(this), 1, ((anj.a)☃.c(e)).a() - 4));
      return;
    }
    super.a(☃, ☃, ☃, ☃, ☃, ☃);
  }
}
