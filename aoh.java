import java.util.List;
import java.util.Random;

public class aoh
  extends ajy
  implements ajv
{
  public static final arp<anj.a> a = arp.a("type", anj.a.class);
  public static final arq c = arq.a("stage", 0, 1);
  protected static final bbh d = new bbh(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
  
  protected aoh()
  {
    w(this.A.b().a(a, anj.a.a).a(c, Integer.valueOf(0)));
    a(acq.c);
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    return d;
  }
  
  public String c()
  {
    return di.a(a() + "." + anj.a.a.d() + ".name");
  }
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    if (☃.E) {
      return;
    }
    super.b(☃, ☃, ☃, ☃);
    if ((☃.k(☃.a()) >= 9) && 
      (☃.nextInt(7) == 0)) {
      c(☃, ☃, ☃, ☃);
    }
  }
  
  public void c(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    if (((Integer)☃.c(c)).intValue() == 0) {
      ☃.a(☃, ☃.a(c), 4);
    } else {
      d(☃, ☃, ☃, ☃);
    }
  }
  
  public void d(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    aud ☃ = ☃.nextInt(10) == 0 ? new atq(true) : new avg(true);
    
    int ☃ = 0;
    int ☃ = 0;
    boolean ☃ = false;
    switch (aoh.1.a[((anj.a)☃.c(a)).ordinal()])
    {
    case 1: 
      for (☃ = 0; ☃ >= -1; ☃--) {
        for (☃ = 0; ☃ >= -1; ☃--) {
          if (a(☃, ☃, ☃, ☃, anj.a.b))
          {
            ☃ = new aup(false, ☃.nextBoolean());
            ☃ = true;
            break label163;
          }
        }
      }
      if (!☃)
      {
        ☃ = ☃ = 0;
        ☃ = new avd(true);
      }
      break;
    case 2: 
      ☃ = new atr(true, false);
      break;
    case 3: 
      arc ☃ = aju.r.u().a(ang.b, anj.a.d);
      arc ☃ = aju.t.u().a(anf.e, anj.a.d).a(aml.b, Boolean.valueOf(false));
      for (☃ = 0; ☃ >= -1; ☃--) {
        for (☃ = 0; ☃ >= -1; ☃--) {
          if (a(☃, ☃, ☃, ☃, anj.a.d))
          {
            ☃ = new auo(true, 10, 20, ☃, ☃);
            ☃ = true;
            break label321;
          }
        }
      }
      if (!☃)
      {
        ☃ = ☃ = 0;
        ☃ = new avg(true, 4 + ☃.nextInt(7), ☃, ☃, false);
      }
      break;
    case 4: 
      ☃ = new ava(true);
      
      break;
    case 5: 
      label163:
      label321:
      for (☃ = 0; ☃ >= -1; ☃--) {
        for (☃ = 0; ☃ >= -1; ☃--) {
          if (a(☃, ☃, ☃, ☃, anj.a.f))
          {
            ☃ = new auy(true);
            ☃ = true;
            break label434;
          }
        }
      }
      label434:
      if (!☃) {
        return;
      }
      break;
    }
    arc ☃ = aju.a.u();
    if (☃)
    {
      ☃.a(☃.a(☃, 0, ☃), ☃, 4);
      ☃.a(☃.a(☃ + 1, 0, ☃), ☃, 4);
      ☃.a(☃.a(☃, 0, ☃ + 1), ☃, 4);
      ☃.a(☃.a(☃ + 1, 0, ☃ + 1), ☃, 4);
    }
    else
    {
      ☃.a(☃, ☃, 4);
    }
    if (!☃.b(☃, ☃, ☃.a(☃, 0, ☃))) {
      if (☃)
      {
        ☃.a(☃.a(☃, 0, ☃), ☃, 4);
        ☃.a(☃.a(☃ + 1, 0, ☃), ☃, 4);
        ☃.a(☃.a(☃, 0, ☃ + 1), ☃, 4);
        ☃.a(☃.a(☃ + 1, 0, ☃ + 1), ☃, 4);
      }
      else
      {
        ☃.a(☃, ☃, 4);
      }
    }
  }
  
  private boolean a(aht ☃, cj ☃, int ☃, int ☃, anj.a ☃)
  {
    return (a(☃, ☃.a(☃, 0, ☃), ☃)) && (a(☃, ☃.a(☃ + 1, 0, ☃), ☃)) && (a(☃, ☃.a(☃, 0, ☃ + 1), ☃)) && (a(☃, ☃.a(☃ + 1, 0, ☃ + 1), ☃));
  }
  
  public boolean a(aht ☃, cj ☃, anj.a ☃)
  {
    arc ☃ = ☃.o(☃);
    return (☃.t() == this) && (☃.c(a) == ☃);
  }
  
  public int d(arc ☃)
  {
    return ((anj.a)☃.c(a)).a();
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    for (anj.a ☃ : ) {
      ☃.add(new adq(☃, 1, ☃.a()));
    }
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, boolean ☃)
  {
    return true;
  }
  
  public boolean a(aht ☃, Random ☃, cj ☃, arc ☃)
  {
    return ☃.r.nextFloat() < 0.45D;
  }
  
  public void b(aht ☃, Random ☃, cj ☃, arc ☃)
  {
    c(☃, ☃, ☃, ☃);
  }
  
  public arc a(int ☃)
  {
    return u().a(a, anj.a.a(☃ & 0x7)).a(c, Integer.valueOf((☃ & 0x8) >> 3));
  }
  
  public int e(arc ☃)
  {
    int ☃ = 0;
    
    ☃ |= ((anj.a)☃.c(a)).a();
    ☃ |= ((Integer)☃.c(c)).intValue() << 3;
    
    return ☃;
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a, c });
  }
}
