import com.google.common.base.Predicate;
import java.util.List;
import java.util.Random;

public class akq
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
  
  public akq()
  {
    super(true);
    w(this.A.b().a(e, Boolean.valueOf(false)).a(d, ajp.b.a));
    a(true);
  }
  
  public int a(aht ☃)
  {
    return 20;
  }
  
  public boolean g(arc ☃)
  {
    return true;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, rr ☃)
  {
    if (☃.E) {
      return;
    }
    if (((Boolean)☃.c(e)).booleanValue()) {
      return;
    }
    e(☃, ☃, ☃);
  }
  
  public void a(aht ☃, cj ☃, arc ☃, Random ☃) {}
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    if ((☃.E) || (!((Boolean)☃.c(e)).booleanValue())) {
      return;
    }
    e(☃, ☃, ☃);
  }
  
  public int b(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    return ((Boolean)☃.c(e)).booleanValue() ? 15 : 0;
  }
  
  public int c(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    if (!((Boolean)☃.c(e)).booleanValue()) {
      return 0;
    }
    return ☃ == cq.b ? 15 : 0;
  }
  
  private void e(aht ☃, cj ☃, arc ☃)
  {
    boolean ☃ = ((Boolean)☃.c(e)).booleanValue();
    boolean ☃ = false;
    
    List<aah> ☃ = a(☃, ☃, aah.class, new Predicate[0]);
    if (!☃.isEmpty()) {
      ☃ = true;
    }
    if ((☃) && (!☃))
    {
      ☃.a(☃, ☃.a(e, Boolean.valueOf(true)), 3);
      b(☃, ☃, ☃, true);
      ☃.d(☃, this);
      ☃.d(☃.b(), this);
      ☃.b(☃, ☃);
    }
    if ((!☃) && (☃))
    {
      ☃.a(☃, ☃.a(e, Boolean.valueOf(false)), 3);
      b(☃, ☃, ☃, false);
      ☃.d(☃, this);
      ☃.d(☃.b(), this);
      ☃.b(☃, ☃);
    }
    if (☃) {
      ☃.a(new cj(☃), this, a(☃));
    }
    ☃.f(☃, this);
  }
  
  protected void b(aht ☃, cj ☃, arc ☃, boolean ☃)
  {
    ajp.a ☃ = new ajp.a(this, ☃, ☃, ☃);
    List<cj> ☃ = ☃.a();
    for (cj ☃ : ☃)
    {
      arc ☃ = ☃.o(☃);
      if (☃ != null) {
        ☃.t().a(☃, ☃, ☃, ☃.t());
      }
    }
  }
  
  public void c(aht ☃, cj ☃, arc ☃)
  {
    super.c(☃, ☃, ☃);
    e(☃, ☃, ☃);
  }
  
  public arr<ajp.b> g()
  {
    return d;
  }
  
  public boolean v(arc ☃)
  {
    return true;
  }
  
  public int d(arc ☃, aht ☃, cj ☃)
  {
    if (((Boolean)☃.c(e)).booleanValue())
    {
      List<aaj> ☃ = a(☃, ☃, aaj.class, new Predicate[0]);
      if (!☃.isEmpty()) {
        return ((aaj)☃.get(0)).j().k();
      }
      List<aah> ☃ = a(☃, ☃, aah.class, new Predicate[] { rv.c });
      if (!☃.isEmpty()) {
        return aau.b((qg)☃.get(0));
      }
    }
    return 0;
  }
  
  protected <T extends aah> List<T> a(aht ☃, cj ☃, Class<T> ☃, Predicate<rr>... ☃)
  {
    bbh ☃ = a(☃);
    if (☃.length != 1) {
      return ☃.a(☃, ☃);
    }
    return ☃.a(☃, ☃, ☃[0]);
  }
  
  private bbh a(cj ☃)
  {
    float ☃ = 0.2F;
    
    return new bbh(☃.p() + 0.2F, ☃.q(), ☃.r() + 0.2F, ☃.p() + 1 - 0.2F, ☃.q() + 1 - 0.2F, ☃.r() + 1 - 0.2F);
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
    switch (akq.2.b[☃.ordinal()])
    {
    case 1: 
      switch (akq.2.a[((ajp.b)☃.c(d)).ordinal()])
      {
      case 1: 
        return ☃.a(d, ajp.b.d);
      case 2: 
        return ☃.a(d, ajp.b.c);
      case 3: 
        return ☃.a(d, ajp.b.f);
      case 4: 
        return ☃.a(d, ajp.b.e);
      case 5: 
        return ☃.a(d, ajp.b.i);
      case 6: 
        return ☃.a(d, ajp.b.j);
      case 7: 
        return ☃.a(d, ajp.b.g);
      case 8: 
        return ☃.a(d, ajp.b.h);
      }
    case 2: 
      switch (akq.2.a[((ajp.b)☃.c(d)).ordinal()])
      {
      case 9: 
        return ☃.a(d, ajp.b.b);
      case 10: 
        return ☃.a(d, ajp.b.a);
      case 1: 
        return ☃.a(d, ajp.b.e);
      case 2: 
        return ☃.a(d, ajp.b.f);
      case 3: 
        return ☃.a(d, ajp.b.d);
      case 4: 
        return ☃.a(d, ajp.b.c);
      case 5: 
        return ☃.a(d, ajp.b.j);
      case 6: 
        return ☃.a(d, ajp.b.g);
      case 7: 
        return ☃.a(d, ajp.b.h);
      case 8: 
        return ☃.a(d, ajp.b.i);
      }
    case 3: 
      switch (akq.2.a[((ajp.b)☃.c(d)).ordinal()])
      {
      case 9: 
        return ☃.a(d, ajp.b.b);
      case 10: 
        return ☃.a(d, ajp.b.a);
      case 1: 
        return ☃.a(d, ajp.b.f);
      case 2: 
        return ☃.a(d, ajp.b.e);
      case 3: 
        return ☃.a(d, ajp.b.c);
      case 4: 
        return ☃.a(d, ajp.b.d);
      case 5: 
        return ☃.a(d, ajp.b.h);
      case 6: 
        return ☃.a(d, ajp.b.i);
      case 7: 
        return ☃.a(d, ajp.b.j);
      case 8: 
        return ☃.a(d, ajp.b.g);
      }
      break;
    }
    return ☃;
  }
  
  public arc a(arc ☃, amr ☃)
  {
    ajp.b ☃ = (ajp.b)☃.c(d);
    switch (akq.2.c[☃.ordinal()])
    {
    case 1: 
      switch (akq.2.a[☃.ordinal()])
      {
      case 3: 
        return ☃.a(d, ajp.b.f);
      case 4: 
        return ☃.a(d, ajp.b.e);
      case 5: 
        return ☃.a(d, ajp.b.j);
      case 6: 
        return ☃.a(d, ajp.b.i);
      case 7: 
        return ☃.a(d, ajp.b.h);
      case 8: 
        return ☃.a(d, ajp.b.g);
      }
      break;
    case 2: 
      switch (akq.2.a[☃.ordinal()])
      {
      case 1: 
        return ☃.a(d, ajp.b.d);
      case 2: 
        return ☃.a(d, ajp.b.c);
      case 5: 
        return ☃.a(d, ajp.b.h);
      case 6: 
        return ☃.a(d, ajp.b.g);
      case 7: 
        return ☃.a(d, ajp.b.j);
      case 8: 
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
