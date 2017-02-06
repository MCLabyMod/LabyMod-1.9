import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Random;

public class all
  extends ajt
{
  public static final arq a = arq.a("age", 0, 15);
  public static final arn b = arn.a("north");
  public static final arn c = arn.a("east");
  public static final arn d = arn.a("south");
  public static final arn e = arn.a("west");
  public static final arn f = arn.a("up");
  
  public arc b(arc ☃, ahx ☃, cj ☃)
  {
    if ((☃.o(☃.b()).q()) || (aju.ab.c(☃, ☃.b()))) {
      return u();
    }
    return ☃.a(b, Boolean.valueOf(c(☃, ☃.c()))).a(c, Boolean.valueOf(c(☃, ☃.f()))).a(d, Boolean.valueOf(c(☃, ☃.d()))).a(e, Boolean.valueOf(c(☃, ☃.e()))).a(f, Boolean.valueOf(c(☃, ☃.a())));
  }
  
  private final Map<ajt, Integer> g = Maps.newIdentityHashMap();
  private final Map<ajt, Integer> B = Maps.newIdentityHashMap();
  
  protected all()
  {
    super(axe.o);
    w(this.A.b().a(a, Integer.valueOf(0)).a(b, Boolean.valueOf(false)).a(c, Boolean.valueOf(false)).a(d, Boolean.valueOf(false)).a(e, Boolean.valueOf(false)).a(f, Boolean.valueOf(false)));
    a(true);
  }
  
  public static void e()
  {
    aju.ab.a(aju.f, 5, 20);
    aju.ab.a(aju.bL, 5, 20);
    aju.ab.a(aju.bM, 5, 20);
    aju.ab.a(aju.bo, 5, 20);
    aju.ab.a(aju.bp, 5, 20);
    aju.ab.a(aju.bq, 5, 20);
    aju.ab.a(aju.br, 5, 20);
    aju.ab.a(aju.bs, 5, 20);
    aju.ab.a(aju.bt, 5, 20);
    aju.ab.a(aju.aO, 5, 20);
    aju.ab.a(aju.aP, 5, 20);
    aju.ab.a(aju.aQ, 5, 20);
    aju.ab.a(aju.aR, 5, 20);
    aju.ab.a(aju.aS, 5, 20);
    aju.ab.a(aju.aT, 5, 20);
    aju.ab.a(aju.ad, 5, 20);
    aju.ab.a(aju.bV, 5, 20);
    aju.ab.a(aju.bU, 5, 20);
    aju.ab.a(aju.bW, 5, 20);
    aju.ab.a(aju.cC, 5, 20);
    aju.ab.a(aju.cD, 5, 20);
    aju.ab.a(aju.r, 5, 5);
    aju.ab.a(aju.s, 5, 5);
    aju.ab.a(aju.t, 30, 60);
    aju.ab.a(aju.u, 30, 60);
    aju.ab.a(aju.X, 30, 20);
    aju.ab.a(aju.W, 15, 100);
    aju.ab.a(aju.H, 60, 100);
    aju.ab.a(aju.cF, 60, 100);
    aju.ab.a(aju.N, 60, 100);
    aju.ab.a(aju.O, 60, 100);
    aju.ab.a(aju.I, 60, 100);
    aju.ab.a(aju.L, 30, 60);
    aju.ab.a(aju.bn, 15, 100);
    aju.ab.a(aju.cA, 5, 5);
    aju.ab.a(aju.cx, 60, 20);
    aju.ab.a(aju.cy, 60, 20);
  }
  
  public void a(ajt ☃, int ☃, int ☃)
  {
    this.g.put(☃, Integer.valueOf(☃));
    this.B.put(☃, Integer.valueOf(☃));
  }
  
  public bbh a(arc ☃, aht ☃, cj ☃)
  {
    return k;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public int a(Random ☃)
  {
    return 0;
  }
  
  public int a(aht ☃)
  {
    return 30;
  }
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    if (!☃.U().b("doFireTick")) {
      return;
    }
    if (!a(☃, ☃)) {
      ☃.g(☃);
    }
    ajt ☃ = ☃.o(☃.b()).t();
    boolean ☃ = ☃ == aju.aV;
    if (((☃.s instanceof atb)) && (☃ == aju.h)) {
      ☃ = true;
    }
    int ☃ = ((Integer)☃.c(a)).intValue();
    if ((!☃) && (☃.W()) && (b(☃, ☃)) && (☃.nextFloat() < 0.2F + ☃ * 0.03F))
    {
      ☃.g(☃);
      return;
    }
    if (☃ < 15)
    {
      ☃ = ☃.a(a, Integer.valueOf(☃ + ☃.nextInt(3) / 2));
      ☃.a(☃, ☃, 4);
    }
    ☃.a(☃, this, a(☃) + ☃.nextInt(10));
    if (!☃)
    {
      if (!c(☃, ☃))
      {
        if ((!☃.o(☃.b()).q()) || (☃ > 3)) {
          ☃.g(☃);
        }
        return;
      }
      if ((!c(☃, ☃.b())) && (☃ == 15) && (☃.nextInt(4) == 0))
      {
        ☃.g(☃);
        return;
      }
    }
    boolean ☃ = ☃.C(☃);
    int ☃ = 0;
    if (☃) {
      ☃ = -50;
    }
    a(☃, ☃.f(), 300 + ☃, ☃, ☃);
    a(☃, ☃.e(), 300 + ☃, ☃, ☃);
    a(☃, ☃.b(), 250 + ☃, ☃, ☃);
    a(☃, ☃.a(), 250 + ☃, ☃, ☃);
    a(☃, ☃.c(), 300 + ☃, ☃, ☃);
    a(☃, ☃.d(), 300 + ☃, ☃, ☃);
    for (int ☃ = -1; ☃ <= 1; ☃++) {
      for (int ☃ = -1; ☃ <= 1; ☃++) {
        for (int ☃ = -1; ☃ <= 4; ☃++) {
          if ((☃ != 0) || (☃ != 0) || (☃ != 0))
          {
            int ☃ = 100;
            if (☃ > 1) {
              ☃ += (☃ - 1) * 100;
            }
            cj ☃ = ☃.a(☃, ☃, ☃);
            int ☃ = d(☃, ☃);
            if (☃ > 0)
            {
              int ☃ = (☃ + 40 + ☃.ae().a() * 7) / (☃ + 30);
              if (☃) {
                ☃ /= 2;
              }
              if ((☃ > 0) && (☃.nextInt(☃) <= ☃) && (
                (!☃.W()) || (!b(☃, ☃))))
              {
                int ☃ = ☃ + ☃.nextInt(5) / 4;
                if (☃ > 15) {
                  ☃ = 15;
                }
                ☃.a(☃, ☃.a(a, Integer.valueOf(☃)), 3);
              }
            }
          }
        }
      }
    }
  }
  
  protected boolean b(aht ☃, cj ☃)
  {
    return (☃.B(☃)) || (☃.B(☃.e())) || (☃.B(☃.f())) || (☃.B(☃.c())) || (☃.B(☃.d()));
  }
  
  public boolean s()
  {
    return false;
  }
  
  private int c(ajt ☃)
  {
    Integer ☃ = (Integer)this.B.get(☃);
    return ☃ == null ? 0 : ☃.intValue();
  }
  
  private int d(ajt ☃)
  {
    Integer ☃ = (Integer)this.g.get(☃);
    return ☃ == null ? 0 : ☃.intValue();
  }
  
  private void a(aht ☃, cj ☃, int ☃, Random ☃, int ☃)
  {
    int ☃ = c(☃.o(☃).t());
    if (☃.nextInt(☃) < ☃)
    {
      arc ☃ = ☃.o(☃);
      if ((☃.nextInt(☃ + 10) < 5) && (!☃.B(☃)))
      {
        int ☃ = ☃ + ☃.nextInt(5) / 4;
        if (☃ > 15) {
          ☃ = 15;
        }
        ☃.a(☃, u().a(a, Integer.valueOf(☃)), 3);
      }
      else
      {
        ☃.g(☃);
      }
      if (☃.t() == aju.W) {
        aju.W.d(☃, ☃, ☃.a(ape.a, Boolean.valueOf(true)));
      }
    }
  }
  
  private boolean c(aht ☃, cj ☃)
  {
    for (cq ☃ : ) {
      if (c(☃, ☃.a(☃))) {
        return true;
      }
    }
    return false;
  }
  
  private int d(aht ☃, cj ☃)
  {
    if (!☃.d(☃)) {
      return 0;
    }
    int ☃ = 0;
    for (cq ☃ : cq.values()) {
      ☃ = Math.max(d(☃.o(☃.a(☃)).t()), ☃);
    }
    return ☃;
  }
  
  public boolean n()
  {
    return false;
  }
  
  public boolean c(ahx ☃, cj ☃)
  {
    return d(☃.o(☃).t()) > 0;
  }
  
  public boolean a(aht ☃, cj ☃)
  {
    return (☃.o(☃.b()).q()) || (c(☃, ☃));
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    if ((!☃.o(☃.b()).q()) && (!c(☃, ☃))) {
      ☃.g(☃);
    }
  }
  
  public void c(aht ☃, cj ☃, arc ☃)
  {
    if ((☃.s.p().a() <= 0) && 
      (aju.aY.b(☃, ☃))) {
      return;
    }
    if ((!☃.o(☃.b()).q()) && (!c(☃, ☃)))
    {
      ☃.g(☃);
      return;
    }
    ☃.a(☃, this, a(☃) + ☃.r.nextInt(10));
  }
  
  public void a(arc ☃, aht ☃, cj ☃, Random ☃)
  {
    if (☃.nextInt(24) == 0) {
      ☃.a(☃.p() + 0.5F, ☃.q() + 0.5F, ☃.r() + 0.5F, ng.bu, nh.e, 1.0F + ☃.nextFloat(), ☃.nextFloat() * 0.7F + 0.3F, false);
    }
    if ((☃.o(☃.b()).q()) || (aju.ab.c(☃, ☃.b())))
    {
      for (int ☃ = 0; ☃ < 3; ☃++)
      {
        double ☃ = ☃.p() + ☃.nextDouble();
        double ☃ = ☃.q() + ☃.nextDouble() * 0.5D + 0.5D;
        double ☃ = ☃.r() + ☃.nextDouble();
        ☃.a(cy.m, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D, new int[0]);
      }
    }
    else
    {
      if (aju.ab.c(☃, ☃.e())) {
        for (int ☃ = 0; ☃ < 2; ☃++)
        {
          double ☃ = ☃.p() + ☃.nextDouble() * 0.10000000149011612D;
          double ☃ = ☃.q() + ☃.nextDouble();
          double ☃ = ☃.r() + ☃.nextDouble();
          ☃.a(cy.m, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D, new int[0]);
        }
      }
      if (aju.ab.c(☃, ☃.f())) {
        for (int ☃ = 0; ☃ < 2; ☃++)
        {
          double ☃ = ☃.p() + 1 - ☃.nextDouble() * 0.10000000149011612D;
          double ☃ = ☃.q() + ☃.nextDouble();
          double ☃ = ☃.r() + ☃.nextDouble();
          ☃.a(cy.m, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D, new int[0]);
        }
      }
      if (aju.ab.c(☃, ☃.c())) {
        for (int ☃ = 0; ☃ < 2; ☃++)
        {
          double ☃ = ☃.p() + ☃.nextDouble();
          double ☃ = ☃.q() + ☃.nextDouble();
          double ☃ = ☃.r() + ☃.nextDouble() * 0.10000000149011612D;
          ☃.a(cy.m, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D, new int[0]);
        }
      }
      if (aju.ab.c(☃, ☃.d())) {
        for (int ☃ = 0; ☃ < 2; ☃++)
        {
          double ☃ = ☃.p() + ☃.nextDouble();
          double ☃ = ☃.q() + ☃.nextDouble();
          double ☃ = ☃.r() + 1 - ☃.nextDouble() * 0.10000000149011612D;
          ☃.a(cy.m, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D, new int[0]);
        }
      }
      if (aju.ab.c(☃, ☃.a())) {
        for (int ☃ = 0; ☃ < 2; ☃++)
        {
          double ☃ = ☃.p() + ☃.nextDouble();
          double ☃ = ☃.q() + 1 - ☃.nextDouble() * 0.10000000149011612D;
          double ☃ = ☃.r() + ☃.nextDouble();
          ☃.a(cy.m, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D, new int[0]);
        }
      }
    }
  }
  
  public axf r(arc ☃)
  {
    return axf.f;
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
    return new ard(this, new arr[] { a, b, c, d, e, f });
  }
}
