public class afm
  implements afu
{
  public boolean a(abc ☃, aht ☃)
  {
    int ☃ = 0;
    adq ☃ = null;
    for (int ☃ = 0; ☃ < ☃.u_(); ☃++)
    {
      adq ☃ = ☃.a(☃);
      if (☃ != null) {
        if (☃.b() == ads.bX)
        {
          if (☃ != null) {
            return false;
          }
          ☃ = ☃;
        }
        else if (☃.b() == ads.bW)
        {
          ☃++;
        }
        else
        {
          return false;
        }
      }
    }
    return (☃ != null) && (☃ > 0);
  }
  
  public adq a(abc ☃)
  {
    int ☃ = 0;
    adq ☃ = null;
    for (int ☃ = 0; ☃ < ☃.u_(); ☃++)
    {
      adq ☃ = ☃.a(☃);
      if (☃ != null) {
        if (☃.b() == ads.bX)
        {
          if (☃ != null) {
            return null;
          }
          ☃ = ☃;
        }
        else if (☃.b() == ads.bW)
        {
          ☃++;
        }
        else
        {
          return null;
        }
      }
    }
    if ((☃ == null) || (☃ < 1) || (afd.h(☃) >= 2)) {
      return null;
    }
    adq ☃ = new adq(ads.bX, ☃);
    ☃.d((dn)☃.o().b());
    ☃.o().a("generation", afd.h(☃) + 1);
    if (☃.s()) {
      ☃.c(☃.q());
    }
    return ☃;
  }
  
  public int a()
  {
    return 9;
  }
  
  public adq b()
  {
    return null;
  }
  
  public adq[] b(abc ☃)
  {
    adq[] ☃ = new adq[☃.u_()];
    for (int ☃ = 0; ☃ < ☃.length; ☃++)
    {
      adq ☃ = ☃.a(☃);
      if ((☃ != null) && ((☃.b() instanceof afd)))
      {
        ☃[☃] = ☃.k();
        ☃[☃].b = 1;
        break;
      }
    }
    return ☃;
  }
}
