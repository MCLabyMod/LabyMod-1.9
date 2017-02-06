import java.util.List;
import java.util.Random;

public abstract class apq
  extends alz
{
  public static final arp<anj.a> d = arp.a("variant", anj.a.class);
  
  public apq()
  {
    super(axe.d);
    arc ☃ = this.A.b();
    if (!e()) {
      ☃ = ☃.a(a, alz.a.b);
    }
    w(☃.a(d, anj.a.a));
    a(acq.b);
  }
  
  public axf r(arc ☃)
  {
    return ((anj.a)☃.c(d)).c();
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return ado.a(aju.bM);
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(aju.bM, 1, ((anj.a)☃.c(d)).a());
  }
  
  public String e(int ☃)
  {
    return super.a() + "." + anj.a.a(☃).d();
  }
  
  public arr<?> g()
  {
    return d;
  }
  
  public Comparable<?> a(adq ☃)
  {
    return anj.a.a(☃.i() & 0x7);
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    if (☃ == ado.a(aju.bL)) {
      return;
    }
    for (anj.a ☃ : anj.a.values()) {
      ☃.add(new adq(☃, 1, ☃.a()));
    }
  }
  
  public arc a(int ☃)
  {
    arc ☃ = u().a(d, anj.a.a(☃ & 0x7));
    if (!e()) {
      ☃ = ☃.a(a, (☃ & 0x8) == 0 ? alz.a.b : alz.a.a);
    }
    return ☃;
  }
  
  public int e(arc ☃)
  {
    int ☃ = 0;
    
    ☃ |= ((anj.a)☃.c(d)).a();
    if ((!e()) && (☃.c(a) == alz.a.a)) {
      ☃ |= 0x8;
    }
    return ☃;
  }
  
  protected ard b()
  {
    if (e()) {
      return new ard(this, new arr[] { d });
    }
    return new ard(this, new arr[] { a, d });
  }
  
  public int d(arc ☃)
  {
    return ((anj.a)☃.c(d)).a();
  }
}
