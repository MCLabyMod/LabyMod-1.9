public class amn
  extends ajt
{
  public static final arp<amn.a> a = arp.a("facing", amn.a.class);
  public static final arn b = arn.a("powered");
  protected static final bbh c = new bbh(0.3125D, 0.20000000298023224D, 0.625D, 0.6875D, 0.800000011920929D, 1.0D);
  protected static final bbh d = new bbh(0.3125D, 0.20000000298023224D, 0.0D, 0.6875D, 0.800000011920929D, 0.375D);
  protected static final bbh e = new bbh(0.625D, 0.20000000298023224D, 0.3125D, 1.0D, 0.800000011920929D, 0.6875D);
  protected static final bbh f = new bbh(0.0D, 0.20000000298023224D, 0.3125D, 0.375D, 0.800000011920929D, 0.6875D);
  protected static final bbh g = new bbh(0.25D, 0.0D, 0.25D, 0.75D, 0.6000000238418579D, 0.75D);
  protected static final bbh B = new bbh(0.25D, 0.4000000059604645D, 0.25D, 0.75D, 1.0D, 0.75D);
  
  protected amn()
  {
    super(axe.q);
    w(this.A.b().a(a, amn.a.e).a(b, Boolean.valueOf(false)));
    a(acq.d);
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
  
  public boolean b(aht ☃, cj ☃, cq ☃)
  {
    return a(☃, ☃, ☃.d());
  }
  
  public boolean a(aht ☃, cj ☃)
  {
    for (cq ☃ : ) {
      if (a(☃, ☃, ☃)) {
        return true;
      }
    }
    return false;
  }
  
  protected static boolean a(aht ☃, cj ☃, cq ☃)
  {
    return ajz.a(☃, ☃, ☃);
  }
  
  public arc a(aht ☃, cj ☃, cq ☃, float ☃, float ☃, float ☃, int ☃, sa ☃)
  {
    arc ☃ = u().a(b, Boolean.valueOf(false));
    if (a(☃, ☃, ☃.d())) {
      return ☃.a(a, amn.a.a(☃, ☃.bi()));
    }
    for (cq ☃ : cq.c.a) {
      if ((☃ != ☃) && (a(☃, ☃, ☃.d()))) {
        return ☃.a(a, amn.a.a(☃, ☃.bi()));
      }
    }
    if (☃.o(☃.b()).q()) {
      return ☃.a(a, amn.a.a(cq.b, ☃.bi()));
    }
    return ☃;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    if ((e(☃, ☃, ☃)) && 
      (!a(☃, ☃, ((amn.a)☃.c(a)).c().d())))
    {
      b(☃, ☃, ☃, 0);
      ☃.g(☃);
    }
  }
  
  private boolean e(aht ☃, cj ☃, arc ☃)
  {
    if (a(☃, ☃)) {
      return true;
    }
    b(☃, ☃, ☃, 0);
    ☃.g(☃);
    return false;
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    switch (amn.1.b[((amn.a)☃.c(a)).ordinal()])
    {
    case 1: 
    default: 
      return f;
    case 2: 
      return e;
    case 3: 
      return d;
    case 4: 
      return c;
    case 5: 
    case 6: 
      return g;
    }
    return B;
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, zj ☃, qm ☃, adq ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if (☃.E) {
      return true;
    }
    ☃ = ☃.a(b);
    ☃.a(☃, ☃, 3);
    float ☃ = ((Boolean)☃.c(b)).booleanValue() ? 0.6F : 0.5F;
    ☃.a(null, ☃, ng.df, nh.e, 0.3F, ☃);
    ☃.d(☃, this);
    
    cq ☃ = ((amn.a)☃.c(a)).c();
    ☃.d(☃.a(☃.d()), this);
    
    return true;
  }
  
  public void b(aht ☃, cj ☃, arc ☃)
  {
    if (((Boolean)☃.c(b)).booleanValue())
    {
      ☃.d(☃, this);
      cq ☃ = ((amn.a)☃.c(a)).c();
      ☃.d(☃.a(☃.d()), this);
    }
    super.b(☃, ☃, ☃);
  }
  
  public int b(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    return ((Boolean)☃.c(b)).booleanValue() ? 15 : 0;
  }
  
  public int c(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    if (!((Boolean)☃.c(b)).booleanValue()) {
      return 0;
    }
    if (((amn.a)☃.c(a)).c() == ☃) {
      return 15;
    }
    return 0;
  }
  
  public boolean g(arc ☃)
  {
    return true;
  }
  
  public arc a(int ☃)
  {
    return u().a(a, amn.a.a(☃ & 0x7)).a(b, Boolean.valueOf((☃ & 0x8) > 0));
  }
  
  public int e(arc ☃)
  {
    int ☃ = 0;
    
    ☃ |= ((amn.a)☃.c(a)).a();
    if (((Boolean)☃.c(b)).booleanValue()) {
      ☃ |= 0x8;
    }
    return ☃;
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    switch (amn.1.c[☃.ordinal()])
    {
    case 1: 
      switch (amn.1.b[((amn.a)☃.c(a)).ordinal()])
      {
      case 1: 
        return ☃.a(a, amn.a.c);
      case 2: 
        return ☃.a(a, amn.a.b);
      case 3: 
        return ☃.a(a, amn.a.e);
      case 4: 
        return ☃.a(a, amn.a.d);
      }
      return ☃;
    case 2: 
      switch (amn.1.b[((amn.a)☃.c(a)).ordinal()])
      {
      case 1: 
        return ☃.a(a, amn.a.e);
      case 2: 
        return ☃.a(a, amn.a.d);
      case 3: 
        return ☃.a(a, amn.a.b);
      case 4: 
        return ☃.a(a, amn.a.c);
      case 5: 
        return ☃.a(a, amn.a.g);
      case 6: 
        return ☃.a(a, amn.a.f);
      case 7: 
        return ☃.a(a, amn.a.h);
      case 8: 
        return ☃.a(a, amn.a.a);
      }
    case 3: 
      switch (amn.1.b[((amn.a)☃.c(a)).ordinal()])
      {
      case 1: 
        return ☃.a(a, amn.a.d);
      case 2: 
        return ☃.a(a, amn.a.e);
      case 3: 
        return ☃.a(a, amn.a.c);
      case 4: 
        return ☃.a(a, amn.a.b);
      case 5: 
        return ☃.a(a, amn.a.g);
      case 6: 
        return ☃.a(a, amn.a.f);
      case 7: 
        return ☃.a(a, amn.a.h);
      case 8: 
        return ☃.a(a, amn.a.a);
      }
      break;
    }
    return ☃;
  }
  
  public arc a(arc ☃, amr ☃)
  {
    return ☃.a(☃.a(((amn.a)☃.c(a)).c()));
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a, b });
  }
  
  public static enum a
    implements or
  {
    private static final a[] i;
    private final int j;
    private final String k;
    private final cq l;
    
    static
    {
      i = new a[values().length];
      for (a ☃ : values()) {
        i[☃.a()] = ☃;
      }
    }
    
    private a(int ☃, String ☃, cq ☃)
    {
      this.j = ☃;
      this.k = ☃;
      this.l = ☃;
    }
    
    public int a()
    {
      return this.j;
    }
    
    public cq c()
    {
      return this.l;
    }
    
    public String toString()
    {
      return this.k;
    }
    
    public static a a(int ☃)
    {
      if ((☃ < 0) || (☃ >= i.length)) {
        ☃ = 0;
      }
      return i[☃];
    }
    
    public static a a(cq ☃, cq ☃)
    {
      switch (amn.1.a[☃.ordinal()])
      {
      case 1: 
        switch (amn.1.d[☃.k().ordinal()])
        {
        case 1: 
          return a;
        case 2: 
          return h;
        }
        throw new IllegalArgumentException("Invalid entityFacing " + ☃ + " for facing " + ☃);
      case 2: 
        switch (amn.1.d[☃.k().ordinal()])
        {
        case 1: 
          return g;
        case 2: 
          return f;
        }
        throw new IllegalArgumentException("Invalid entityFacing " + ☃ + " for facing " + ☃);
      case 3: 
        return e;
      case 4: 
        return d;
      case 5: 
        return c;
      case 6: 
        return b;
      }
      throw new IllegalArgumentException("Invalid facing: " + ☃);
    }
    
    public String m()
    {
      return this.k;
    }
  }
}
