class afl$a
  implements afu
{
  public boolean a(abc ☃, aht ☃)
  {
    boolean ☃ = false;
    for (int ☃ = 0; ☃ < ☃.u_(); ☃++)
    {
      adq ☃ = ☃.a(☃);
      if ((☃ != null) && (☃.b() == ads.cO))
      {
        if (☃) {
          return false;
        }
        if (apt.c(☃) >= 6) {
          return false;
        }
        ☃ = true;
      }
    }
    if (!☃) {
      return false;
    }
    return c(☃) != null;
  }
  
  public adq a(abc ☃)
  {
    adq ☃ = null;
    for (int ☃ = 0; ☃ < ☃.u_(); ☃++)
    {
      adq ☃ = ☃.a(☃);
      if (☃ != null) {
        if (☃.b() == ads.cO)
        {
          ☃ = ☃.k();
          ☃.b = 1;
          break;
        }
      }
    }
    apt.a ☃ = c(☃);
    if (☃ != null)
    {
      int ☃ = 0;
      for (int ☃ = 0; ☃ < ☃.u_(); ☃++)
      {
        adq ☃ = ☃.a(☃);
        if ((☃ != null) && (☃.b() == ads.bd))
        {
          ☃ = ☃.i();
          break;
        }
      }
      dn ☃ = ☃.a("BlockEntityTag", true);
      du ☃ = null;
      if (☃.b("Patterns", 9))
      {
        ☃ = ☃.c("Patterns", 10);
      }
      else
      {
        ☃ = new du();
        ☃.a("Patterns", ☃);
      }
      dn ☃ = new dn();
      ☃.a("Pattern", ☃.b());
      ☃.a("Color", ☃);
      ☃.a(☃);
    }
    return ☃;
  }
  
  public int a()
  {
    return 10;
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
  
  private apt.a c(abc ☃)
  {
    for (apt.a ☃ : ) {
      if (☃.d())
      {
        boolean ☃ = true;
        if (☃.e())
        {
          boolean ☃ = false;
          boolean ☃ = false;
          for (int ☃ = 0; (☃ < ☃.u_()) && (☃); ☃++)
          {
            adq ☃ = ☃.a(☃);
            if ((☃ != null) && (☃.b() != ads.cO)) {
              if (☃.b() == ads.bd)
              {
                if (☃)
                {
                  ☃ = false;
                  break;
                }
                ☃ = true;
              }
              else if ((!☃) && (☃.a(☃.f())))
              {
                ☃ = true;
              }
              else
              {
                ☃ = false;
                break;
              }
            }
          }
          if (!☃) {
            ☃ = false;
          }
        }
        else if (☃.u_() == ☃.c().length * ☃.c()[0].length())
        {
          int ☃ = -1;
          for (int ☃ = 0; (☃ < ☃.u_()) && (☃); ☃++)
          {
            int ☃ = ☃ / 3;
            int ☃ = ☃ % 3;
            
            adq ☃ = ☃.a(☃);
            if ((☃ == null) || (☃.b() == ads.cO))
            {
              if (☃.c()[☃].charAt(☃) != ' ')
              {
                ☃ = false;
                break;
              }
            }
            else
            {
              if (☃.b() != ads.bd)
              {
                ☃ = false;
                break;
              }
              if ((☃ != -1) && (☃ != ☃.i()))
              {
                ☃ = false;
                break;
              }
              if (☃.c()[☃].charAt(☃) == ' ')
              {
                ☃ = false;
                break;
              }
              ☃ = ☃.i();
            }
          }
        }
        else
        {
          ☃ = false;
        }
        if (☃) {
          return ☃;
        }
      }
    }
    return null;
  }
}
