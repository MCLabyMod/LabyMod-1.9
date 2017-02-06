class afl$b
  implements afu
{
  public boolean a(abc ☃, aht ☃)
  {
    adq ☃ = null;
    adq ☃ = null;
    for (int ☃ = 0; ☃ < ☃.u_(); ☃++)
    {
      adq ☃ = ☃.a(☃);
      if (☃ != null)
      {
        if (☃.b() != ads.cO) {
          return false;
        }
        if ((☃ != null) && (☃ != null)) {
          return false;
        }
        int ☃ = apt.b(☃);
        boolean ☃ = apt.c(☃) > 0;
        if (☃ != null)
        {
          if (☃) {
            return false;
          }
          if (☃ != apt.b(☃)) {
            return false;
          }
          ☃ = ☃;
        }
        else if (☃ != null)
        {
          if (!☃) {
            return false;
          }
          if (☃ != apt.b(☃)) {
            return false;
          }
          ☃ = ☃;
        }
        else if (☃)
        {
          ☃ = ☃;
        }
        else
        {
          ☃ = ☃;
        }
      }
    }
    return (☃ != null) && (☃ != null);
  }
  
  public adq a(abc ☃)
  {
    for (int ☃ = 0; ☃ < ☃.u_(); ☃++)
    {
      adq ☃ = ☃.a(☃);
      if (☃ != null) {
        if (apt.c(☃) > 0)
        {
          adq ☃ = ☃.k();
          ☃.b = 1;
          return ☃;
        }
      }
    }
    return null;
  }
  
  public int a()
  {
    return 2;
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
      if (☃ != null) {
        if (☃.b().r())
        {
          ☃[☃] = new adq(☃.b().q());
        }
        else if ((☃.n()) && 
          (apt.c(☃) > 0))
        {
          ☃[☃] = ☃.k();
          ☃[☃].b = 1;
        }
      }
    }
    return ☃;
  }
}
