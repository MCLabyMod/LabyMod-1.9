import java.util.Random;

public class amh
  extends ajt
{
  public static final arp<amh.a> a = arp.a("variant", amh.a.class);
  private final ajt b;
  
  public amh(axe ☃, axf ☃, ajt ☃)
  {
    super(☃, ☃);
    w(this.A.b().a(a, amh.a.l));
    this.b = ☃;
  }
  
  public int a(Random ☃)
  {
    return Math.max(0, ☃.nextInt(10) - 7);
  }
  
  public axf r(arc ☃)
  {
    switch (amh.1.a[((amh.a)☃.c(a)).ordinal()])
    {
    case 1: 
      return axf.e;
    case 2: 
      return axf.d;
    case 3: 
      return axf.d;
    }
    return super.r(☃);
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return ado.a(this.b);
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(this.b);
  }
  
  public arc a(aht ☃, cj ☃, cq ☃, float ☃, float ☃, float ☃, int ☃, sa ☃)
  {
    return u();
  }
  
  public arc a(int ☃)
  {
    return u().a(a, amh.a.a(☃));
  }
  
  public int e(arc ☃)
  {
    return ((amh.a)☃.c(a)).a();
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    switch (amh.1.b[☃.ordinal()])
    {
    case 1: 
      switch (amh.1.a[((amh.a)☃.c(a)).ordinal()])
      {
      case 4: 
        return ☃.a(a, amh.a.i);
      case 5: 
        return ☃.a(a, amh.a.h);
      case 6: 
        return ☃.a(a, amh.a.g);
      case 7: 
        return ☃.a(a, amh.a.f);
      case 8: 
        return ☃.a(a, amh.a.d);
      case 9: 
        return ☃.a(a, amh.a.c);
      case 10: 
        return ☃.a(a, amh.a.b);
      case 11: 
        return ☃.a(a, amh.a.a);
      case 3: 
        break;
      default: 
        return ☃;
      }
    case 2: 
      switch (amh.1.a[((amh.a)☃.c(a)).ordinal()])
      {
      case 4: 
        return ☃.a(a, amh.a.g);
      case 5: 
        return ☃.a(a, amh.a.d);
      case 6: 
        return ☃.a(a, amh.a.a);
      case 7: 
        return ☃.a(a, amh.a.h);
      case 8: 
        return ☃.a(a, amh.a.b);
      case 9: 
        return ☃.a(a, amh.a.i);
      case 10: 
        return ☃.a(a, amh.a.f);
      case 11: 
        return ☃.a(a, amh.a.c);
      case 3: 
        break;
      default: 
        return ☃;
      }
    case 3: 
      switch (amh.1.a[((amh.a)☃.c(a)).ordinal()])
      {
      case 4: 
        return ☃.a(a, amh.a.c);
      case 5: 
        return ☃.a(a, amh.a.f);
      case 6: 
        return ☃.a(a, amh.a.i);
      case 7: 
        return ☃.a(a, amh.a.b);
      case 8: 
        return ☃.a(a, amh.a.h);
      case 9: 
        return ☃.a(a, amh.a.a);
      case 10: 
        return ☃.a(a, amh.a.d);
      case 11: 
        return ☃.a(a, amh.a.g);
      case 3: 
        break;
      default: 
        return ☃;
      }
      break;
    }
    return ☃;
  }
  
  public arc a(arc ☃, amr ☃)
  {
    amh.a ☃ = (amh.a)☃.c(a);
    switch (amh.1.c[☃.ordinal()])
    {
    case 1: 
      switch (amh.1.a[☃.ordinal()])
      {
      case 4: 
        return ☃.a(a, amh.a.g);
      case 5: 
        return ☃.a(a, amh.a.h);
      case 6: 
        return ☃.a(a, amh.a.i);
      case 9: 
        return ☃.a(a, amh.a.a);
      case 10: 
        return ☃.a(a, amh.a.b);
      case 11: 
        return ☃.a(a, amh.a.c);
      }
      break;
    case 2: 
      switch (amh.1.a[☃.ordinal()])
      {
      case 4: 
        return ☃.a(a, amh.a.c);
      case 6: 
        return ☃.a(a, amh.a.a);
      case 7: 
        return ☃.a(a, amh.a.f);
      case 8: 
        return ☃.a(a, amh.a.d);
      case 9: 
        return ☃.a(a, amh.a.i);
      case 11: 
        return ☃.a(a, amh.a.g);
      }
      break;
    }
    return super.a(☃, ☃);
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a });
  }
  
  public static enum a
    implements or
  {
    private static final a[] n;
    private final int o;
    private final String p;
    
    static
    {
      n = new a[16];
      for (a ☃ : values()) {
        n[☃.a()] = ☃;
      }
    }
    
    private a(int ☃, String ☃)
    {
      this.o = ☃;
      this.p = ☃;
    }
    
    public int a()
    {
      return this.o;
    }
    
    public String toString()
    {
      return this.p;
    }
    
    public static a a(int ☃)
    {
      if ((☃ < 0) || (☃ >= n.length)) {
        ☃ = 0;
      }
      a ☃ = n[☃];
      return ☃ == null ? n[0] : ☃;
    }
    
    public String m()
    {
      return this.p;
    }
  }
}
