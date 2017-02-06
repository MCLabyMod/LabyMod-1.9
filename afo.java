import com.google.common.collect.Lists;
import java.util.List;

public class afo
  implements afu
{
  private adq a;
  
  public boolean a(abc ☃, aht ☃)
  {
    this.a = null;
    
    int ☃ = 0;
    int ☃ = 0;
    int ☃ = 0;
    int ☃ = 0;
    int ☃ = 0;
    int ☃ = 0;
    for (int ☃ = 0; ☃ < ☃.u_(); ☃++)
    {
      adq ☃ = ☃.a(☃);
      if (☃ != null) {
        if (☃.b() == ads.J) {
          ☃++;
        } else if (☃.b() == ads.cm) {
          ☃++;
        } else if (☃.b() == ads.bd) {
          ☃++;
        } else if (☃.b() == ads.aR) {
          ☃++;
        } else if (☃.b() == ads.ba) {
          ☃++;
        } else if (☃.b() == ads.k) {
          ☃++;
        } else if (☃.b() == ads.bV) {
          ☃++;
        } else if (☃.b() == ads.I) {
          ☃++;
        } else if (☃.b() == ads.bE) {
          ☃++;
        } else if (☃.b() == ads.ch) {
          ☃++;
        } else {
          return false;
        }
      }
    }
    ☃ += ☃ + ☃;
    if ((☃ > 3) || (☃ > 1)) {
      return false;
    }
    if ((☃ >= 1) && (☃ == 1) && (☃ == 0))
    {
      this.a = new adq(ads.cl);
      if (☃ > 0)
      {
        dn ☃ = new dn();
        dn ☃ = new dn();
        du ☃ = new du();
        for (int ☃ = 0; ☃ < ☃.u_(); ☃++)
        {
          adq ☃ = ☃.a(☃);
          if ((☃ != null) && (☃.b() == ads.cm)) {
            if ((☃.n()) && (☃.o().b("Explosion", 10))) {
              ☃.a(☃.o().o("Explosion"));
            }
          }
        }
        ☃.a("Explosions", ☃);
        ☃.a("Flight", (byte)☃);
        ☃.a("Fireworks", ☃);
        this.a.d(☃);
      }
      return true;
    }
    if ((☃ == 1) && (☃ == 0) && (☃ == 0) && (☃ > 0) && (☃ <= 1))
    {
      this.a = new adq(ads.cm);
      dn ☃ = new dn();
      dn ☃ = new dn();
      
      byte ☃ = 0;
      
      List<Integer> ☃ = Lists.newArrayList();
      for (int ☃ = 0; ☃ < ☃.u_(); ☃++)
      {
        adq ☃ = ☃.a(☃);
        if (☃ != null) {
          if (☃.b() == ads.bd) {
            ☃.add(Integer.valueOf(acu.a[(☃.i() & 0xF)]));
          } else if (☃.b() == ads.ba) {
            ☃.a("Flicker", true);
          } else if (☃.b() == ads.k) {
            ☃.a("Trail", true);
          } else if (☃.b() == ads.bV) {
            ☃ = 1;
          } else if (☃.b() == ads.I) {
            ☃ = 4;
          } else if (☃.b() == ads.bE) {
            ☃ = 2;
          } else if (☃.b() == ads.ch) {
            ☃ = 3;
          }
        }
      }
      int[] ☃ = new int[☃.size()];
      for (int ☃ = 0; ☃ < ☃.length; ☃++) {
        ☃[☃] = ((Integer)☃.get(☃)).intValue();
      }
      ☃.a("Colors", ☃);
      ☃.a("Type", ☃);
      ☃.a("Explosion", ☃);
      this.a.d(☃);
      return true;
    }
    if ((☃ == 0) && (☃ == 0) && (☃ == 1) && (☃ > 0) && (☃ == ☃))
    {
      List<Integer> ☃ = Lists.newArrayList();
      for (int ☃ = 0; ☃ < ☃.u_(); ☃++)
      {
        adq ☃ = ☃.a(☃);
        if (☃ != null) {
          if (☃.b() == ads.bd)
          {
            ☃.add(Integer.valueOf(acu.a[(☃.i() & 0xF)]));
          }
          else if (☃.b() == ads.cm)
          {
            this.a = ☃.k();
            this.a.b = 1;
          }
        }
      }
      int[] ☃ = new int[☃.size()];
      for (int ☃ = 0; ☃ < ☃.length; ☃++) {
        ☃[☃] = ((Integer)☃.get(☃)).intValue();
      }
      if ((this.a != null) && (this.a.n()))
      {
        dn ☃ = this.a.o().o("Explosion");
        if (☃ == null) {
          return false;
        }
        ☃.a("FadeColors", ☃);
      }
      else
      {
        return false;
      }
      return true;
    }
    return false;
  }
  
  public adq a(abc ☃)
  {
    return this.a.k();
  }
  
  public int a()
  {
    return 10;
  }
  
  public adq b()
  {
    return this.a;
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
