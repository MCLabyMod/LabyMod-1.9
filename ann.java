import com.google.common.base.Predicate;

public class ann
  extends ajp
{
  public static final arp<ajp.b> d = arp.a("shape", ajp.b.class, new Predicate()
  {
    public boolean a(ajp.b ☃)
    {
      return (☃ != ajp.b.j) && (☃ != ajp.b.i) && (☃ != ajp.b.g) && (☃ != ajp.b.h);
    }
  });
  public static final arn e = arn.a("powered");
  
  protected ann()
  {
    super(true);
    w(this.A.b().a(d, ajp.b.a).a(e, Boolean.valueOf(false)));
  }
  
  protected boolean a(aht ☃, cj ☃, arc ☃, boolean ☃, int ☃)
  {
    if (☃ >= 8) {
      return false;
    }
    int ☃ = ☃.p();
    int ☃ = ☃.q();
    int ☃ = ☃.r();
    
    boolean ☃ = true;
    ajp.b ☃ = (ajp.b)☃.c(d);
    switch (ann.2.a[☃.ordinal()])
    {
    case 1: 
      if (☃) {
        ☃++;
      } else {
        ☃--;
      }
      break;
    case 2: 
      if (☃) {
        ☃--;
      } else {
        ☃++;
      }
      break;
    case 3: 
      if (☃)
      {
        ☃--;
      }
      else
      {
        ☃++;
        ☃++;
        ☃ = false;
      }
      ☃ = ajp.b.b;
      break;
    case 4: 
      if (☃)
      {
        ☃--;
        ☃++;
        ☃ = false;
      }
      else
      {
        ☃++;
      }
      ☃ = ajp.b.b;
      break;
    case 5: 
      if (☃)
      {
        ☃++;
      }
      else
      {
        ☃--;
        ☃++;
        ☃ = false;
      }
      ☃ = ajp.b.a;
      break;
    case 6: 
      if (☃)
      {
        ☃++;
        ☃++;
        ☃ = false;
      }
      else
      {
        ☃--;
      }
      ☃ = ajp.b.a;
    }
    if (a(☃, new cj(☃, ☃, ☃), ☃, ☃, ☃)) {
      return true;
    }
    if ((☃) && (a(☃, new cj(☃, ☃ - 1, ☃), ☃, ☃, ☃))) {
      return true;
    }
    return false;
  }
  
  protected boolean a(aht ☃, cj ☃, boolean ☃, int ☃, ajp.b ☃)
  {
    arc ☃ = ☃.o(☃);
    if (☃.t() != this) {
      return false;
    }
    ajp.b ☃ = (ajp.b)☃.c(d);
    if ((☃ == ajp.b.b) && ((☃ == ajp.b.a) || (☃ == ajp.b.e) || (☃ == ajp.b.f))) {
      return false;
    }
    if ((☃ == ajp.b.a) && ((☃ == ajp.b.b) || (☃ == ajp.b.c) || (☃ == ajp.b.d))) {
      return false;
    }
    if (((Boolean)☃.c(e)).booleanValue())
    {
      if (☃.y(☃)) {
        return true;
      }
      return a(☃, ☃, ☃, ☃, ☃ + 1);
    }
    return false;
  }
  
  protected void b(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    boolean ☃ = ((Boolean)☃.c(e)).booleanValue();
    boolean ☃ = (☃.y(☃)) || (a(☃, ☃, ☃, true, 0)) || (a(☃, ☃, ☃, false, 0));
    if (☃ != ☃)
    {
      ☃.a(☃, ☃.a(e, Boolean.valueOf(☃)), 3);
      
      ☃.d(☃.b(), this);
      if (((ajp.b)☃.c(d)).c()) {
        ☃.d(☃.a(), this);
      }
    }
  }
  
  public arr<ajp.b> g()
  {
    return d;
  }
  
  public arc a(int ☃)
  {
    return u().a(d, ajp.b.a(☃ & 0x7)).a(e, Boolean.valueOf((☃ & 0x8) > 0));
  }
  
  public int e(arc ☃)
  {
    int ☃ = 0;
    
    ☃ |= ((ajp.b)☃.c(d)).a();
    if (((Boolean)☃.c(e)).booleanValue()) {
      ☃ |= 0x8;
    }
    return ☃;
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    switch (ann.2.b[☃.ordinal()])
    {
    case 1: 
      switch (ann.2.a[((ajp.b)☃.c(d)).ordinal()])
      {
      case 3: 
        return ☃.a(d, ajp.b.d);
      case 4: 
        return ☃.a(d, ajp.b.c);
      case 5: 
        return ☃.a(d, ajp.b.f);
      case 6: 
        return ☃.a(d, ajp.b.e);
      case 7: 
        return ☃.a(d, ajp.b.i);
      case 8: 
        return ☃.a(d, ajp.b.j);
      case 9: 
        return ☃.a(d, ajp.b.g);
      case 10: 
        return ☃.a(d, ajp.b.h);
      }
    case 2: 
      switch (ann.2.a[((ajp.b)☃.c(d)).ordinal()])
      {
      case 1: 
        return ☃.a(d, ajp.b.b);
      case 2: 
        return ☃.a(d, ajp.b.a);
      case 3: 
        return ☃.a(d, ajp.b.e);
      case 4: 
        return ☃.a(d, ajp.b.f);
      case 5: 
        return ☃.a(d, ajp.b.d);
      case 6: 
        return ☃.a(d, ajp.b.c);
      case 7: 
        return ☃.a(d, ajp.b.j);
      case 8: 
        return ☃.a(d, ajp.b.g);
      case 9: 
        return ☃.a(d, ajp.b.h);
      case 10: 
        return ☃.a(d, ajp.b.i);
      }
    case 3: 
      switch (ann.2.a[((ajp.b)☃.c(d)).ordinal()])
      {
      case 1: 
        return ☃.a(d, ajp.b.b);
      case 2: 
        return ☃.a(d, ajp.b.a);
      case 3: 
        return ☃.a(d, ajp.b.f);
      case 4: 
        return ☃.a(d, ajp.b.e);
      case 5: 
        return ☃.a(d, ajp.b.c);
      case 6: 
        return ☃.a(d, ajp.b.d);
      case 7: 
        return ☃.a(d, ajp.b.h);
      case 8: 
        return ☃.a(d, ajp.b.i);
      case 9: 
        return ☃.a(d, ajp.b.j);
      case 10: 
        return ☃.a(d, ajp.b.g);
      }
      break;
    }
    return ☃;
  }
  
  public arc a(arc ☃, amr ☃)
  {
    ajp.b ☃ = (ajp.b)☃.c(d);
    switch (ann.2.c[☃.ordinal()])
    {
    case 1: 
      switch (ann.2.a[☃.ordinal()])
      {
      case 5: 
        return ☃.a(d, ajp.b.f);
      case 6: 
        return ☃.a(d, ajp.b.e);
      case 7: 
        return ☃.a(d, ajp.b.j);
      case 8: 
        return ☃.a(d, ajp.b.i);
      case 9: 
        return ☃.a(d, ajp.b.h);
      case 10: 
        return ☃.a(d, ajp.b.g);
      }
      break;
    case 2: 
      switch (ann.2.a[☃.ordinal()])
      {
      case 3: 
        return ☃.a(d, ajp.b.d);
      case 4: 
        return ☃.a(d, ajp.b.c);
      case 7: 
        return ☃.a(d, ajp.b.h);
      case 8: 
        return ☃.a(d, ajp.b.g);
      case 9: 
        return ☃.a(d, ajp.b.j);
      case 10: 
        return ☃.a(d, ajp.b.i);
      }
      break;
    }
    return super.a(☃, ☃);
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { d, e });
  }
}
