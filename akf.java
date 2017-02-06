import java.util.Random;

public class akf
  extends ajt
{
  public static final arq a = arq.a("age", 0, 5);
  
  protected akf()
  {
    super(axe.k);
    w(this.A.b().a(a, Integer.valueOf(0)));
    a(acq.c);
    a(true);
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return null;
  }
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    if (!b(☃, ☃))
    {
      ☃.b(☃, true);
      return;
    }
    cj ☃ = ☃.a();
    if ((!☃.d(☃)) || (☃.q() >= 256)) {
      return;
    }
    int ☃ = ((Integer)☃.c(a)).intValue();
    if ((☃ >= 5) || (☃.nextInt(1) != 0)) {
      return;
    }
    boolean ☃ = false;
    boolean ☃ = false;
    
    ajt ☃ = ☃.o(☃.b()).t();
    if (☃ == aju.bH)
    {
      ☃ = true;
    }
    else if (☃ == aju.cR)
    {
      int ☃ = 1;
      for (int ☃ = 0; ☃ < 4; ☃++)
      {
        ajt ☃ = ☃.o(☃.c(☃ + 1)).t();
        if (☃ == aju.cR)
        {
          ☃++;
        }
        else
        {
          if (☃ != aju.bH) {
            break;
          }
          ☃ = true; break;
        }
      }
      int ☃ = 4;
      if (☃) {
        ☃++;
      }
      if ((☃ < 2) || (☃.nextInt(☃) >= ☃)) {
        ☃ = true;
      }
    }
    else if (☃ == aju.a)
    {
      ☃ = true;
    }
    if ((☃) && (a(☃, ☃, null)) && (☃.d(☃.b(2))))
    {
      ☃.a(☃, aju.cR.u(), 2);
      a(☃, ☃, ☃);
    }
    else if (☃ < 4)
    {
      int ☃ = ☃.nextInt(4);
      boolean ☃ = false;
      if (☃) {
        ☃++;
      }
      for (int ☃ = 0; ☃ < ☃; ☃++)
      {
        cq ☃ = cq.c.a.a(☃);
        cj ☃ = ☃.a(☃);
        if ((☃.d(☃)) && (☃.d(☃.b())) && (a(☃, ☃, ☃.d())))
        {
          a(☃, ☃, ☃ + 1);
          ☃ = true;
        }
      }
      if (☃) {
        ☃.a(☃, aju.cR.u(), 2);
      } else {
        c(☃, ☃);
      }
    }
    else if (☃ == 4)
    {
      c(☃, ☃);
    }
  }
  
  private void a(aht ☃, cj ☃, int ☃)
  {
    ☃.a(☃, u().a(a, Integer.valueOf(☃)), 2);
    ☃.b(1033, ☃, 0);
  }
  
  private void c(aht ☃, cj ☃)
  {
    ☃.a(☃, u().a(a, Integer.valueOf(5)), 2);
    ☃.b(1034, ☃, 0);
  }
  
  private static boolean a(aht ☃, cj ☃, cq ☃)
  {
    for (cq ☃ : cq.c.a) {
      if ((☃ != ☃) && (!☃.d(☃.a(☃)))) {
        return false;
      }
    }
    return true;
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
    return (super.a(☃, ☃)) && (b(☃, ☃));
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    if (!b(☃, ☃)) {
      ☃.a(☃, this, 1);
    }
  }
  
  public boolean b(aht ☃, cj ☃)
  {
    ajt ☃ = ☃.o(☃.b()).t();
    if ((☃ == aju.cR) || (☃ == aju.bH)) {
      return true;
    }
    if (☃ == aju.a)
    {
      int ☃ = 0;
      for (cq ☃ : cq.c.a)
      {
        ajt ☃ = ☃.o(☃.a(☃)).t();
        if (☃ == aju.cR) {
          ☃++;
        } else if (☃ != aju.a) {
          return false;
        }
      }
      return ☃ == 1;
    }
    return false;
  }
  
  public void a(aht ☃, zj ☃, cj ☃, arc ☃, apv ☃, adq ☃)
  {
    super.a(☃, ☃, ☃, ☃, ☃, ☃);
    
    a(☃, ☃, new adq(ado.a(this)));
  }
  
  protected adq u(arc ☃)
  {
    return null;
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
  
  public void c(aht ☃, cj ☃, arc ☃)
  {
    super.c(☃, ☃, ☃);
  }
  
  public static void a(aht ☃, cj ☃, Random ☃, int ☃)
  {
    ☃.a(☃, aju.cR.u(), 2);
    a(☃, ☃, ☃, ☃, ☃, 0);
  }
  
  private static void a(aht ☃, cj ☃, Random ☃, cj ☃, int ☃, int ☃)
  {
    int ☃ = ☃.nextInt(4) + 1;
    if (☃ == 0) {
      ☃++;
    }
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      cj ☃ = ☃.b(☃ + 1);
      if (!a(☃, ☃, null)) {
        return;
      }
      ☃.a(☃, aju.cR.u(), 2);
    }
    boolean ☃ = false;
    if (☃ < 4)
    {
      int ☃ = ☃.nextInt(4);
      if (☃ == 0) {
        ☃++;
      }
      for (int ☃ = 0; ☃ < ☃; ☃++)
      {
        cq ☃ = cq.c.a.a(☃);
        cj ☃ = ☃.b(☃).a(☃);
        if ((Math.abs(☃.p() - ☃.p()) < ☃) && (Math.abs(☃.r() - ☃.r()) < ☃)) {
          if ((☃.d(☃)) && (☃.d(☃.b())) && (a(☃, ☃, ☃.d())))
          {
            ☃ = true;
            ☃.a(☃, aju.cR.u(), 2);
            a(☃, ☃, ☃, ☃, ☃, ☃ + 1);
          }
        }
      }
    }
    if (!☃) {
      ☃.a(☃.b(☃), aju.cS.u().a(a, Integer.valueOf(5)), 2);
    }
  }
}
