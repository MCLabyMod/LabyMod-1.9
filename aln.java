import java.util.Random;

public class aln
  extends ajn
{
  public static final arq a = arq.a("legacy_data", 0, 15);
  public static final arp<aln.a> b = arp.a("contents", aln.a.class);
  protected static final bbh c = new bbh(0.3125D, 0.0D, 0.3125D, 0.6875D, 0.375D, 0.6875D);
  
  public aln()
  {
    super(axe.q);
    w(this.A.b().a(b, aln.a.a).a(a, Integer.valueOf(0)));
  }
  
  public String c()
  {
    return di.a("item.flowerPot.name");
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    return c;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public aob a(arc ☃)
  {
    return aob.d;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, zj ☃, qm ☃, adq ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if ((☃ == null) || (!(☃.b() instanceof acc))) {
      return false;
    }
    aqf ☃ = c(☃, ☃);
    if (☃ == null) {
      return false;
    }
    if (☃.c() != null) {
      return false;
    }
    ajt ☃ = ajt.a(☃.b());
    if (!a(☃, ☃.i())) {
      return false;
    }
    ☃.a(☃.b(), ☃.i());
    ☃.v_();
    ☃.a(☃, ☃, ☃, 3);
    ☃.b(nt.V);
    if (!☃.bJ.d) {
      ☃.b -= 1;
    }
    return true;
  }
  
  private boolean a(ajt ☃, int ☃)
  {
    if ((☃ == aju.N) || (☃ == aju.O) || (☃ == aju.aK) || (☃ == aju.P) || (☃ == aju.Q) || (☃ == aju.g) || (☃ == aju.I)) {
      return true;
    }
    if ((☃ == aju.H) && (☃ == apc.a.c.a())) {
      return true;
    }
    return false;
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    aqf ☃ = c(☃, ☃);
    if (☃ != null)
    {
      adq ☃ = ☃.b();
      if (☃ != null) {
        return ☃;
      }
    }
    return new adq(ads.ca);
  }
  
  public boolean a(aht ☃, cj ☃)
  {
    return (super.a(☃, ☃)) && (☃.o(☃.b()).q());
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    if (!☃.o(☃.b()).q())
    {
      b(☃, ☃, ☃, 0);
      
      ☃.g(☃);
    }
  }
  
  public void b(aht ☃, cj ☃, arc ☃)
  {
    aqf ☃ = c(☃, ☃);
    if ((☃ != null) && (☃.c() != null)) {
      a(☃, ☃, new adq(☃.c(), 1, ☃.d()));
    }
    super.b(☃, ☃, ☃);
  }
  
  public void a(aht ☃, cj ☃, arc ☃, zj ☃)
  {
    super.a(☃, ☃, ☃, ☃);
    if (☃.bJ.d)
    {
      aqf ☃ = c(☃, ☃);
      if (☃ != null) {
        ☃.a(null, 0);
      }
    }
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return ads.ca;
  }
  
  private aqf c(aht ☃, cj ☃)
  {
    apv ☃ = ☃.r(☃);
    if ((☃ instanceof aqf)) {
      return (aqf)☃;
    }
    return null;
  }
  
  public apv a(aht ☃, int ☃)
  {
    ajt ☃ = null;
    int ☃ = 0;
    switch (☃)
    {
    default: 
      break;
    case 1: 
      ☃ = aju.O;
      ☃ = alm.a.b.b();
      break;
    case 2: 
      ☃ = aju.N;
      break;
    case 3: 
      ☃ = aju.g;
      ☃ = anj.a.a.a();
      break;
    case 4: 
      ☃ = aju.g;
      ☃ = anj.a.b.a();
      break;
    case 5: 
      ☃ = aju.g;
      ☃ = anj.a.c.a();
      break;
    case 6: 
      ☃ = aju.g;
      ☃ = anj.a.d.a();
      break;
    case 12: 
      ☃ = aju.g;
      ☃ = anj.a.e.a();
      break;
    case 13: 
      ☃ = aju.g;
      ☃ = anj.a.f.a();
      break;
    case 7: 
      ☃ = aju.Q;
      break;
    case 8: 
      ☃ = aju.P;
      break;
    case 9: 
      ☃ = aju.aK;
      break;
    case 10: 
      ☃ = aju.I;
      break;
    case 11: 
      ☃ = aju.H;
      ☃ = apc.a.c.a();
    }
    return new aqf(ado.a(☃), ☃);
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { b, a });
  }
  
  public int e(arc ☃)
  {
    return ((Integer)☃.c(a)).intValue();
  }
  
  public arc b(arc ☃, ahx ☃, cj ☃)
  {
    aln.a ☃ = aln.a.a;
    
    apv ☃ = ☃.r(☃);
    if ((☃ instanceof aqf))
    {
      aqf ☃ = (aqf)☃;
      ado ☃ = ☃.c();
      if ((☃ instanceof acc))
      {
        int ☃ = ☃.d();
        ajt ☃ = ajt.a(☃);
        if (☃ == aju.g) {
          switch (aln.1.a[anj.a.a(☃).ordinal()])
          {
          case 1: 
            ☃ = aln.a.l;
            break;
          case 2: 
            ☃ = aln.a.m;
            break;
          case 3: 
            ☃ = aln.a.n;
            break;
          case 4: 
            ☃ = aln.a.o;
            break;
          case 5: 
            ☃ = aln.a.p;
            break;
          case 6: 
            ☃ = aln.a.q;
            break;
          default: 
            ☃ = aln.a.a;break;
          }
        } else if (☃ == aju.H) {
          switch (☃)
          {
          case 0: 
            ☃ = aln.a.t;
            break;
          case 2: 
            ☃ = aln.a.u;
            break;
          default: 
            ☃ = aln.a.a;break;
          }
        } else if (☃ == aju.N) {
          ☃ = aln.a.k;
        } else if (☃ == aju.O) {
          switch (aln.1.b[alm.a.a(alm.b.b, ☃).ordinal()])
          {
          case 1: 
            ☃ = aln.a.b;
            break;
          case 2: 
            ☃ = aln.a.c;
            break;
          case 3: 
            ☃ = aln.a.d;
            break;
          case 4: 
            ☃ = aln.a.e;
            break;
          case 5: 
            ☃ = aln.a.f;
            break;
          case 6: 
            ☃ = aln.a.g;
            break;
          case 7: 
            ☃ = aln.a.h;
            break;
          case 8: 
            ☃ = aln.a.i;
            break;
          case 9: 
            ☃ = aln.a.j;
            break;
          default: 
            ☃ = aln.a.a;break;
          }
        } else if (☃ == aju.Q) {
          ☃ = aln.a.r;
        } else if (☃ == aju.P) {
          ☃ = aln.a.s;
        } else if (☃ == aju.I) {
          ☃ = aln.a.t;
        } else if (☃ == aju.aK) {
          ☃ = aln.a.v;
        }
      }
    }
    return ☃.a(b, ☃);
  }
  
  public static enum a
    implements or
  {
    private final String w;
    
    private a(String ☃)
    {
      this.w = ☃;
    }
    
    public String toString()
    {
      return this.w;
    }
    
    public String m()
    {
      return this.w;
    }
  }
  
  public ahm f()
  {
    return ahm.c;
  }
}
