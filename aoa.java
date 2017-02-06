import java.util.Random;

public class aoa
  extends ajt
{
  public static final arq a = arq.a("age", 0, 15);
  protected static final bbh b = new bbh(0.125D, 0.0D, 0.125D, 0.875D, 1.0D, 0.875D);
  
  protected aoa()
  {
    super(axe.k);
    w(this.A.b().a(a, Integer.valueOf(0)));
    a(true);
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    return b;
  }
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    if ((☃.o(☃.b()).t() != aju.aM) && (!e(☃, ☃, ☃))) {
      return;
    }
    if (☃.d(☃.a()))
    {
      int ☃ = 1;
      while (☃.o(☃.c(☃)).t() == this) {
        ☃++;
      }
      if (☃ < 3)
      {
        int ☃ = ((Integer)☃.c(a)).intValue();
        if (☃ == 15)
        {
          ☃.a(☃.a(), u());
          ☃.a(☃, ☃.a(a, Integer.valueOf(0)), 4);
        }
        else
        {
          ☃.a(☃, ☃.a(a, Integer.valueOf(☃ + 1)), 4);
        }
      }
    }
  }
  
  public boolean a(aht ☃, cj ☃)
  {
    ajt ☃ = ☃.o(☃.b()).t();
    if (☃ == this) {
      return true;
    }
    if ((☃ == aju.c) || (☃ == aju.d) || (☃ == aju.m))
    {
      cj ☃ = ☃.b();
      for (cq ☃ : cq.c.a)
      {
        arc ☃ = ☃.o(☃.a(☃));
        if ((☃.a() == axe.h) || (☃.t() == aju.de)) {
          return true;
        }
      }
      return false;
    }
    return false;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    e(☃, ☃, ☃);
  }
  
  protected final boolean e(aht ☃, cj ☃, arc ☃)
  {
    if (b(☃, ☃)) {
      return true;
    }
    b(☃, ☃, ☃, 0);
    ☃.g(☃);
    return false;
  }
  
  public boolean b(aht ☃, cj ☃)
  {
    return a(☃, ☃);
  }
  
  public bbh a(arc ☃, aht ☃, cj ☃)
  {
    return k;
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return ads.aQ;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(ads.aQ);
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
