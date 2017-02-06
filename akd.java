import java.util.List;
import java.util.Random;

public class akd
  extends ajt
{
  public static final arq a = arq.a("level", 0, 3);
  protected static final bbh b = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D);
  protected static final bbh c = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.125D);
  protected static final bbh d = new bbh(0.0D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);
  protected static final bbh e = new bbh(0.875D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
  protected static final bbh f = new bbh(0.0D, 0.0D, 0.0D, 0.125D, 1.0D, 1.0D);
  
  public akd()
  {
    super(axe.f, axf.m);
    w(this.A.b().a(a, Integer.valueOf(0)));
  }
  
  public void a(arc ☃, aht ☃, cj ☃, bbh ☃, List<bbh> ☃, rr ☃)
  {
    a(☃, ☃, ☃, b);
    a(☃, ☃, ☃, f);
    a(☃, ☃, ☃, c);
    a(☃, ☃, ☃, e);
    a(☃, ☃, ☃, d);
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    return j;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, rr ☃)
  {
    int ☃ = ((Integer)☃.c(a)).intValue();
    float ☃ = ☃.q() + (6.0F + 3 * ☃) / 16.0F;
    if ((!☃.E) && (☃.aH()) && (☃ > 0) && (☃.bl().b <= ☃))
    {
      ☃.X();
      
      a(☃, ☃, ☃, ☃ - 1);
    }
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, zj ☃, qm ☃, adq ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if (☃ == null) {
      return true;
    }
    int ☃ = ((Integer)☃.c(a)).intValue();
    ado ☃ = ☃.b();
    if (☃ == ads.az)
    {
      if ((☃ < 3) && (!☃.E))
      {
        if (!☃.bJ.d) {
          ☃.a(☃, new adq(ads.ay));
        }
        ☃.b(nt.K);
        
        a(☃, ☃, ☃, 3);
      }
      return true;
    }
    if (☃ == ads.ay)
    {
      if ((☃ == 3) && (!☃.E))
      {
        if (!☃.bJ.d)
        {
          ☃.b -= 1;
          if (☃.b == 0) {
            ☃.a(☃, new adq(ads.az));
          } else if (!☃.br.c(new adq(ads.az))) {
            ☃.a(new adq(ads.az), false);
          }
        }
        ☃.b(nt.L);
        
        a(☃, ☃, ☃, 0);
      }
      return true;
    }
    if (☃ == ads.bJ)
    {
      if ((☃ > 0) && (!☃.E))
      {
        if (!☃.bJ.d)
        {
          adq ☃ = afg.a(new adq(ads.bG), afh.b);
          ☃.b(nt.L);
          if (--☃.b == 0) {
            ☃.a(☃, ☃);
          } else if (!☃.br.c(☃)) {
            ☃.a(☃, false);
          } else if ((☃ instanceof lr)) {
            ((lr)☃).a(☃.bs);
          }
        }
        a(☃, ☃, ☃, ☃ - 1);
      }
      return true;
    }
    if ((☃ > 0) && ((☃ instanceof abw)))
    {
      abw ☃ = (abw)☃;
      if ((☃.d() == abw.a.a) && (☃.e_(☃)) && (!☃.E))
      {
        ☃.c(☃);
        a(☃, ☃, ☃, ☃ - 1);
        ☃.b(nt.M);
        return true;
      }
    }
    if ((☃ > 0) && ((☃ instanceof aca)))
    {
      if ((apt.c(☃) > 0) && (!☃.E))
      {
        adq ☃ = ☃.k();
        ☃.b = 1;
        apt.e(☃);
        ☃.b(nt.N);
        if (!☃.bJ.d) {
          ☃.b -= 1;
        }
        if (☃.b == 0) {
          ☃.a(☃, ☃);
        } else if (!☃.br.c(☃)) {
          ☃.a(☃, false);
        } else if ((☃ instanceof lr)) {
          ((lr)☃).a(☃.bs);
        }
        if (!☃.bJ.d) {
          a(☃, ☃, ☃, ☃ - 1);
        }
      }
      return true;
    }
    return false;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, int ☃)
  {
    ☃.a(☃, ☃.a(a, Integer.valueOf(on.a(☃, 0, 3))), 2);
    ☃.f(☃, this);
  }
  
  public void h(aht ☃, cj ☃)
  {
    if (☃.r.nextInt(20) != 1) {
      return;
    }
    float ☃ = ☃.b(☃).a(☃);
    if (☃.A().a(☃, ☃.q()) < 0.15F) {
      return;
    }
    arc ☃ = ☃.o(☃);
    if (((Integer)☃.c(a)).intValue() < 3) {
      ☃.a(☃, ☃.a(a), 2);
    }
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return ads.bQ;
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(ads.bQ);
  }
  
  public boolean v(arc ☃)
  {
    return true;
  }
  
  public int d(arc ☃, aht ☃, cj ☃)
  {
    return ((Integer)☃.c(a)).intValue();
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
  
  public boolean b(ahx ☃, cj ☃)
  {
    return true;
  }
}
