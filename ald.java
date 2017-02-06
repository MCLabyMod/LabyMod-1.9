import com.google.common.base.Predicates;
import java.util.List;
import java.util.Random;

public class ald
  extends ajt
{
  public static final aro a = amg.D;
  public static final arn b = arn.a("eye");
  protected static final bbh c = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.8125D, 1.0D);
  protected static final bbh d = new bbh(0.3125D, 0.8125D, 0.3125D, 0.6875D, 1.0D, 0.6875D);
  private static arg e;
  
  public ald()
  {
    super(axe.e, axf.C);
    w(this.A.b().a(a, cq.c).a(b, Boolean.valueOf(false)));
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    return c;
  }
  
  public void a(arc ☃, aht ☃, cj ☃, bbh ☃, List<bbh> ☃, rr ☃)
  {
    a(☃, ☃, ☃, c);
    if (((Boolean)☃.o(☃).c(b)).booleanValue()) {
      a(☃, ☃, ☃, d);
    }
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return null;
  }
  
  public arc a(aht ☃, cj ☃, cq ☃, float ☃, float ☃, float ☃, int ☃, sa ☃)
  {
    return u().a(a, ☃.bi().d()).a(b, Boolean.valueOf(false));
  }
  
  public boolean v(arc ☃)
  {
    return true;
  }
  
  public int d(arc ☃, aht ☃, cj ☃)
  {
    if (((Boolean)☃.c(b)).booleanValue()) {
      return 15;
    }
    return 0;
  }
  
  public arc a(int ☃)
  {
    return u().a(b, Boolean.valueOf((☃ & 0x4) != 0)).a(a, cq.b(☃ & 0x3));
  }
  
  public int e(arc ☃)
  {
    int ☃ = 0;
    
    ☃ |= ((cq)☃.c(a)).b();
    if (((Boolean)☃.c(b)).booleanValue()) {
      ☃ |= 0x4;
    }
    return ☃;
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    return ☃.a(a, ☃.a((cq)☃.c(a)));
  }
  
  public arc a(arc ☃, amr ☃)
  {
    return ☃.a(☃.a((cq)☃.c(a)));
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a, b });
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public static arg e()
  {
    if (e == null) {
      e = arh.a().a(new String[] { "?vvv?", ">   <", ">   <", ">   <", "?^^^?" }).a('?', arf.a(ark.a)).a('^', arf.a(ark.a(aju.bG).a(b, Predicates.equalTo(Boolean.valueOf(true))).a(a, Predicates.equalTo(cq.d)))).a('>', arf.a(ark.a(aju.bG).a(b, Predicates.equalTo(Boolean.valueOf(true))).a(a, Predicates.equalTo(cq.e)))).a('v', arf.a(ark.a(aju.bG).a(b, Predicates.equalTo(Boolean.valueOf(true))).a(a, Predicates.equalTo(cq.c)))).a('<', arf.a(ark.a(aju.bG).a(b, Predicates.equalTo(Boolean.valueOf(true))).a(a, Predicates.equalTo(cq.f)))).a(' ', arf.a(aju.a.u())).b();
    }
    return e;
  }
}
