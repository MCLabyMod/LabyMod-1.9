import java.util.List;
import java.util.Random;

public class ako
  extends ajn
{
  public static final arq a = arq.a("power", 0, 15);
  protected static final bbh b = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D);
  private final boolean c;
  
  public ako(boolean ☃)
  {
    super(axe.d);
    this.c = ☃;
    
    w(this.A.b().a(a, Integer.valueOf(0)));
    a(acq.d);
    
    c(0.2F);
    a(aop.a);
    c("daylightDetector");
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    return b;
  }
  
  public int b(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    return ((Integer)☃.c(a)).intValue();
  }
  
  public void c(aht ☃, cj ☃)
  {
    if (☃.s.m()) {
      return;
    }
    arc ☃ = ☃.o(☃);
    int ☃ = ☃.b(ahz.a, ☃) - ☃.af();
    float ☃ = ☃.d(1.0F);
    if (this.c) {
      ☃ = 15 - ☃;
    }
    if ((☃ > 0) && (!this.c))
    {
      float ☃ = ☃ < 3.1415927F ? 0.0F : 6.2831855F;
      ☃ += (☃ - ☃) * 0.2F;
      
      ☃ = Math.round(☃ * on.b(☃));
    }
    ☃ = on.a(☃, 0, 15);
    if (((Integer)☃.c(a)).intValue() != ☃) {
      ☃.a(☃, ☃.a(a, Integer.valueOf(☃)), 3);
    }
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, zj ☃, qm ☃, adq ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if (☃.cU())
    {
      if (☃.E) {
        return true;
      }
      if (this.c)
      {
        ☃.a(☃, aju.cl.u().a(a, ☃.c(a)), 4);
        aju.cl.c(☃, ☃);
      }
      else
      {
        ☃.a(☃, aju.cm.u().a(a, ☃.c(a)), 4);
        aju.cm.c(☃, ☃);
      }
      return true;
    }
    return super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return ado.a(aju.cl);
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(aju.cl);
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public aob a(arc ☃)
  {
    return aob.d;
  }
  
  public boolean g(arc ☃)
  {
    return true;
  }
  
  public apv a(aht ☃, int ☃)
  {
    return new aqa();
  }
  
  public arc a(int ☃)
  {
    return u().a(a, Integer.valueOf(☃));
  }
  
  public int e(arc ☃)
  {
    return ((Integer)☃.c(a)).intValue();
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a });
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    if (!this.c) {
      super.a(☃, ☃, ☃);
    }
  }
}
