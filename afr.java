public class afr
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
        if (☃.b() == ads.bk)
        {
          if (☃ != null) {
            return false;
          }
          ☃ = ☃;
        }
        else if (☃.b() == ads.cf)
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
        if (☃.b() == ads.bk)
        {
          if (☃ != null) {
            return null;
          }
          ☃ = ☃;
        }
        else if (☃.b() == ads.cf)
        {
          ☃++;
        }
        else
        {
          return null;
        }
      }
    }
    if ((☃ == null) || (☃ < 1)) {
      return null;
    }
    adq ☃ = new adq(ads.bk, ☃ + 1, ☃.i());
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
      if ((☃ != null) && (☃.b().r())) {
        ☃[☃] = new adq(☃.b().q());
      }
    }
    return ☃;
  }
}
