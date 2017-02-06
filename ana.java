import com.google.common.base.Predicate;
import java.util.List;

public class ana
  extends amp
{
  public static final arp<anj.a> b = arp.a("variant", anj.a.class, new Predicate()
  {
    public boolean a(anj.a ☃)
    {
      return ☃.a() >= 4;
    }
  });
  
  public ana()
  {
    w(this.A.b().a(b, anj.a.e).a(a, amp.a.b));
  }
  
  public axf r(arc ☃)
  {
    anj.a ☃ = (anj.a)☃.c(b);
    switch (ana.2.b[((amp.a)☃.c(a)).ordinal()])
    {
    case 1: 
    case 2: 
    case 3: 
    default: 
      switch (ana.2.a[☃.ordinal()])
      {
      case 1: 
      default: 
        return axf.m;
      }
      return anj.a.f.c();
    }
    return ☃.c();
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    ☃.add(new adq(☃, 1, anj.a.e.a() - 4));
    ☃.add(new adq(☃, 1, anj.a.f.a() - 4));
  }
  
  public arc a(int ☃)
  {
    arc ☃ = u().a(b, anj.a.a((☃ & 0x3) + 4));
    switch (☃ & 0xC)
    {
    case 0: 
      ☃ = ☃.a(a, amp.a.b);
      break;
    case 4: 
      ☃ = ☃.a(a, amp.a.a);
      break;
    case 8: 
      ☃ = ☃.a(a, amp.a.c);
      break;
    default: 
      ☃ = ☃.a(a, amp.a.d);
    }
    return ☃;
  }
  
  public int e(arc ☃)
  {
    int ☃ = 0;
    
    ☃ |= ((anj.a)☃.c(b)).a() - 4;
    switch (ana.2.b[((amp.a)☃.c(a)).ordinal()])
    {
    case 1: 
      ☃ |= 0x4;
      break;
    case 2: 
      ☃ |= 0x8;
      break;
    case 3: 
      ☃ |= 0xC;
    }
    return ☃;
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { b, a });
  }
  
  protected adq u(arc ☃)
  {
    return new adq(ado.a(this), 1, ((anj.a)☃.c(b)).a() - 4);
  }
  
  public int d(arc ☃)
  {
    return ((anj.a)☃.c(b)).a() - 4;
  }
}
