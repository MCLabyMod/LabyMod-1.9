import java.util.Random;

public class ajl
  extends ajn
{
  public static final aro a = amg.D;
  public static final arq b = arq.a("rotation", 0, 15);
  protected static final bbh c = new bbh(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
  
  protected ajl()
  {
    super(axe.d);
  }
  
  public String c()
  {
    return di.a("item.banner.white.name");
  }
  
  public bbh a(arc ☃, aht ☃, cj ☃)
  {
    return k;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public boolean b(ahx ☃, cj ☃)
  {
    return true;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public boolean d()
  {
    return true;
  }
  
  public apv a(aht ☃, int ☃)
  {
    return new apt();
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return ads.cO;
  }
  
  private adq e(aht ☃, cj ☃, arc ☃)
  {
    apv ☃ = ☃.r(☃);
    if ((☃ instanceof apt))
    {
      adq ☃ = new adq(ads.cO, 1, ((apt)☃).b());
      
      dn ☃ = new dn();
      ☃.b(☃);
      ☃.q("x");
      ☃.q("y");
      ☃.q("z");
      ☃.q("id");
      ☃.a("BlockEntityTag", ☃);
      
      return ☃;
    }
    return null;
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    adq ☃ = e(☃, ☃, ☃);
    return ☃ != null ? ☃ : new adq(ads.cO);
  }
  
  public void a(aht ☃, cj ☃, arc ☃, float ☃, int ☃)
  {
    adq ☃ = e(☃, ☃, ☃);
    if (☃ != null) {
      a(☃, ☃, ☃);
    } else {
      super.a(☃, ☃, ☃, ☃, ☃);
    }
  }
  
  public boolean a(aht ☃, cj ☃)
  {
    return (!b(☃, ☃)) && (super.a(☃, ☃));
  }
  
  public void a(aht ☃, zj ☃, cj ☃, arc ☃, apv ☃, adq ☃)
  {
    if ((☃ instanceof apt))
    {
      apt ☃ = (apt)☃;
      adq ☃ = new adq(ads.cO, 1, ((apt)☃).b());
      
      dn ☃ = new dn();
      apt.a(☃, ☃.b(), ☃.d());
      ☃.a("BlockEntityTag", ☃);
      
      a(☃, ☃, ☃);
    }
    else
    {
      super.a(☃, ☃, ☃, ☃, null, ☃);
    }
  }
  
  public static class b
    extends ajl
  {
    protected static final bbh d = new bbh(0.0D, 0.0D, 0.875D, 1.0D, 0.78125D, 1.0D);
    protected static final bbh e = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.78125D, 0.125D);
    protected static final bbh f = new bbh(0.875D, 0.0D, 0.0D, 1.0D, 0.78125D, 1.0D);
    protected static final bbh g = new bbh(0.0D, 0.0D, 0.0D, 0.125D, 0.78125D, 1.0D);
    
    public b()
    {
      w(this.A.b().a(a, cq.c));
    }
    
    public arc a(arc ☃, aoe ☃)
    {
      return ☃.a(a, ☃.a((cq)☃.c(a)));
    }
    
    public arc a(arc ☃, amr ☃)
    {
      return ☃.a(☃.a((cq)☃.c(a)));
    }
    
    public bbh a(arc ☃, ahx ☃, cj ☃)
    {
      switch (ajl.1.a[((cq)☃.c(a)).ordinal()])
      {
      case 1: 
      default: 
        return d;
      case 2: 
        return e;
      case 3: 
        return f;
      }
      return g;
    }
    
    public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
    {
      cq ☃ = (cq)☃.c(a);
      if (!☃.o(☃.a(☃.d())).a().a())
      {
        b(☃, ☃, ☃, 0);
        ☃.g(☃);
      }
      super.a(☃, ☃, ☃, ☃);
    }
    
    public arc a(int ☃)
    {
      cq ☃ = cq.a(☃);
      if (☃.k() == cq.a.b) {
        ☃ = cq.c;
      }
      return u().a(a, ☃);
    }
    
    public int e(arc ☃)
    {
      return ((cq)☃.c(a)).a();
    }
    
    protected ard b()
    {
      return new ard(this, new arr[] { a });
    }
  }
  
  public static class a
    extends ajl
  {
    public a()
    {
      w(this.A.b().a(b, Integer.valueOf(0)));
    }
    
    public bbh a(arc ☃, ahx ☃, cj ☃)
    {
      return c;
    }
    
    public arc a(arc ☃, aoe ☃)
    {
      return ☃.a(b, Integer.valueOf(☃.a(((Integer)☃.c(b)).intValue(), 16)));
    }
    
    public arc a(arc ☃, amr ☃)
    {
      return ☃.a(b, Integer.valueOf(☃.a(((Integer)☃.c(b)).intValue(), 16)));
    }
    
    public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
    {
      if (!☃.o(☃.b()).a().a())
      {
        b(☃, ☃, ☃, 0);
        ☃.g(☃);
      }
      super.a(☃, ☃, ☃, ☃);
    }
    
    public arc a(int ☃)
    {
      return u().a(b, Integer.valueOf(☃));
    }
    
    public int e(arc ☃)
    {
      return ((Integer)☃.c(b)).intValue();
    }
    
    protected ard b()
    {
      return new ard(this, new arr[] { b });
    }
  }
}
