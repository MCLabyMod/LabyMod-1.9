import com.google.common.base.Predicate;
import java.util.List;

public class ang
  extends amp
{
  public static final arp<anj.a> b = arp.a("variant", anj.a.class, new Predicate()
  {
    public boolean a(anj.a ☃)
    {
      return ☃.a() < 4;
    }
  });
  
  public ang()
  {
    w(this.A.b().a(b, anj.a.a).a(a, amp.a.b));
  }
  
  public axf r(arc ☃)
  {
    anj.a ☃ = (anj.a)☃.c(b);
    switch (ang.2.b[((amp.a)☃.c(a)).ordinal()])
    {
    case 1: 
    case 2: 
    case 3: 
    default: 
      switch (ang.2.a[☃.ordinal()])
      {
      case 1: 
      default: 
        return anj.a.b.c();
      case 2: 
        return anj.a.f.c();
      case 3: 
        return axf.p;
      }
      return anj.a.b.c();
    }
    return ☃.c();
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    ☃.add(new adq(☃, 1, anj.a.a.a()));
    ☃.add(new adq(☃, 1, anj.a.b.a()));
    ☃.add(new adq(☃, 1, anj.a.c.a()));
    ☃.add(new adq(☃, 1, anj.a.d.a()));
  }
  
  public arc a(int ☃)
  {
    arc ☃ = u().a(b, anj.a.a((☃ & 0x3) % 4));
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
    
    ☃ |= ((anj.a)☃.c(b)).a();
    switch (ang.2.b[((amp.a)☃.c(a)).ordinal()])
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
    return new adq(ado.a(this), 1, ((anj.a)☃.c(b)).a());
  }
  
  public int d(arc ☃)
  {
    return ((anj.a)☃.c(b)).a();
  }
}
