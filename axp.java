public class axp
  extends axv
{
  private aig[] c = { ail.d, ail.d, ail.d, ail.K, ail.K, ail.c };
  private aig[] d = { ail.f, ail.E, ail.e, ail.c, ail.C, ail.h };
  private aig[] e = { ail.f, ail.e, ail.g, ail.c };
  private aig[] f = { ail.n, ail.n, ail.n, ail.F };
  private final atg g;
  
  public axp(long ☃, axv ☃, ahy ☃, String ☃)
  {
    super(☃);
    this.a = ☃;
    if (☃ == ahy.h)
    {
      this.c = new aig[] { ail.d, ail.f, ail.e, ail.h, ail.c, ail.g };
      
      this.g = null;
    }
    else if (☃ == ahy.f)
    {
      this.g = atg.a.a(☃).b();
    }
    else
    {
      this.g = null;
    }
  }
  
  public int[] a(int ☃, int ☃, int ☃, int ☃)
  {
    int[] ☃ = this.a.a(☃, ☃, ☃, ☃);
    
    int[] ☃ = axt.a(☃ * ☃);
    for (int ☃ = 0; ☃ < ☃; ☃++) {
      for (int ☃ = 0; ☃ < ☃; ☃++)
      {
        a(☃ + ☃, ☃ + ☃);
        int ☃ = ☃[(☃ + ☃ * ☃)];
        int ☃ = (☃ & 0xF00) >> 8;
        ☃ &= 0xF0FF;
        if ((this.g != null) && (this.g.F >= 0)) {
          ☃[(☃ + ☃ * ☃)] = this.g.F;
        } else if (b(☃)) {
          ☃[(☃ + ☃ * ☃)] = ☃;
        } else if (☃ == aig.a(ail.p)) {
          ☃[(☃ + ☃ * ☃)] = ☃;
        } else if (☃ == 1)
        {
          if (☃ > 0)
          {
            if (a(3) == 0) {
              ☃[(☃ + ☃ * ☃)] = aig.a(ail.O);
            } else {
              ☃[(☃ + ☃ * ☃)] = aig.a(ail.N);
            }
          }
          else {
            ☃[(☃ + ☃ * ☃)] = aig.a(this.c[a(this.c.length)]);
          }
        }
        else if (☃ == 2)
        {
          if (☃ > 0) {
            ☃[(☃ + ☃ * ☃)] = aig.a(ail.w);
          } else {
            ☃[(☃ + ☃ * ☃)] = aig.a(this.d[a(this.d.length)]);
          }
        }
        else if (☃ == 3)
        {
          if (☃ > 0) {
            ☃[(☃ + ☃ * ☃)] = aig.a(ail.H);
          } else {
            ☃[(☃ + ☃ * ☃)] = aig.a(this.e[a(this.e.length)]);
          }
        }
        else if (☃ == 4) {
          ☃[(☃ + ☃ * ☃)] = aig.a(this.f[a(this.f.length)]);
        } else {
          ☃[(☃ + ☃ * ☃)] = aig.a(ail.p);
        }
      }
    }
    return ☃;
  }
}
