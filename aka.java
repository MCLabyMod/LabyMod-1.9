import java.util.Random;

public class aka
  extends ajt
{
  public static final arq a = arq.a("age", 0, 15);
  protected static final bbh b = new bbh(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.9375D, 0.9375D);
  protected static final bbh c = new bbh(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D);
  
  protected aka()
  {
    super(axe.A);
    w(this.A.b().a(a, Integer.valueOf(0)));
    a(true);
    a(acq.c);
  }
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    cj ☃ = ☃.a();
    if (!☃.d(☃)) {
      return;
    }
    int ☃ = 1;
    while (☃.o(☃.c(☃)).t() == this) {
      ☃++;
    }
    if (☃ >= 3) {
      return;
    }
    int ☃ = ((Integer)☃.c(a)).intValue();
    if (☃ == 15)
    {
      ☃.a(☃, u());
      arc ☃ = ☃.a(a, Integer.valueOf(0));
      ☃.a(☃, ☃, 4);
      a(☃, ☃, ☃, this);
    }
    else
    {
      ☃.a(☃, ☃.a(a, Integer.valueOf(☃ + 1)), 4);
    }
  }
  
  public bbh a(arc ☃, aht ☃, cj ☃)
  {
    return b;
  }
  
  public bbh c(arc ☃, aht ☃, cj ☃)
  {
    return c.a(☃);
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public boolean a(aht ☃, cj ☃)
  {
    if (super.a(☃, ☃)) {
      return b(☃, ☃);
    }
    return false;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    if (!b(☃, ☃)) {
      ☃.b(☃, true);
    }
  }
  
  public boolean b(aht ☃, cj ☃)
  {
    for (cq ☃ : cq.c.a)
    {
      axe ☃ = ☃.o(☃.a(☃)).a();
      if ((☃.a()) || (☃ == axe.i)) {
        return false;
      }
    }
    ajt ☃ = ☃.o(☃.b()).t();
    return (☃ == aju.aK) || ((☃ == aju.m) && (!☃.o(☃.a()).a().d()));
  }
  
  public void a(aht ☃, cj ☃, arc ☃, rr ☃)
  {
    ☃.a(rc.h, 1.0F);
  }
  
  public ahm f()
  {
    return ahm.c;
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
}
