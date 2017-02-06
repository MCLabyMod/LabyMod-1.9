import java.util.List;
import java.util.Random;

abstract class avw$m
  extends awg
{
  public avw$m() {}
  
  protected avw$m(int ☃)
  {
    super(☃);
  }
  
  protected void b(dn ☃) {}
  
  protected void a(dn ☃) {}
  
  private int a(List<avw.n> ☃)
  {
    boolean ☃ = false;
    int ☃ = 0;
    for (avw.n ☃ : ☃)
    {
      if ((☃.d > 0) && (☃.c < ☃.d)) {
        ☃ = true;
      }
      ☃ += ☃.b;
    }
    return ☃ ? ☃ : -1;
  }
  
  private m a(avw.q ☃, List<avw.n> ☃, List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
  {
    int ☃ = a(☃);
    boolean ☃ = (☃ > 0) && (☃ <= 30);
    
    int ☃ = 0;
    int ☃;
    while ((☃ < 5) && (☃))
    {
      ☃++;
      
      ☃ = ☃.nextInt(☃);
      for (avw.n ☃ : ☃)
      {
        ☃ -= ☃.b;
        if (☃ < 0)
        {
          if ((!☃.a(☃)) || ((☃ == ☃.a) && (!☃.e))) {
            break;
          }
          m ☃ = avw.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
          if (☃ != null)
          {
            ☃.c += 1;
            ☃.a = ☃;
            if (!☃.a()) {
              ☃.remove(☃);
            }
            return ☃;
          }
        }
      }
    }
    return avw.b.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  private awg a(avw.q ☃, List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃, boolean ☃)
  {
    if ((Math.abs(☃ - ☃.c().a) > 112) || (Math.abs(☃ - ☃.c().c) > 112)) {
      return avw.b.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    }
    List<avw.n> ☃ = ☃.b;
    if (☃) {
      ☃ = ☃.c;
    }
    awg ☃ = a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃ + 1);
    if (☃ != null)
    {
      ☃.add(☃);
      ☃.d.add(☃);
    }
    return ☃;
  }
  
  protected awg a(avw.q ☃, List<awg> ☃, Random ☃, int ☃, int ☃, boolean ☃)
  {
    cq ☃ = e();
    if (☃ != null) {
      switch (avw.1.a[☃.ordinal()])
      {
      case 1: 
        return a(☃, ☃, ☃, this.l.a + ☃, this.l.b + ☃, this.l.c - 1, ☃, d(), ☃);
      case 2: 
        return a(☃, ☃, ☃, this.l.a + ☃, this.l.b + ☃, this.l.f + 1, ☃, d(), ☃);
      case 3: 
        return a(☃, ☃, ☃, this.l.a - 1, this.l.b + ☃, this.l.c + ☃, ☃, d(), ☃);
      case 4: 
        return a(☃, ☃, ☃, this.l.d + 1, this.l.b + ☃, this.l.c + ☃, ☃, d(), ☃);
      }
    }
    return null;
  }
  
  protected awg b(avw.q ☃, List<awg> ☃, Random ☃, int ☃, int ☃, boolean ☃)
  {
    cq ☃ = e();
    if (☃ != null) {
      switch (avw.1.a[☃.ordinal()])
      {
      case 1: 
        return a(☃, ☃, ☃, this.l.a - 1, this.l.b + ☃, this.l.c + ☃, cq.e, d(), ☃);
      case 2: 
        return a(☃, ☃, ☃, this.l.a - 1, this.l.b + ☃, this.l.c + ☃, cq.e, d(), ☃);
      case 3: 
        return a(☃, ☃, ☃, this.l.a + ☃, this.l.b + ☃, this.l.c - 1, cq.c, d(), ☃);
      case 4: 
        return a(☃, ☃, ☃, this.l.a + ☃, this.l.b + ☃, this.l.c - 1, cq.c, d(), ☃);
      }
    }
    return null;
  }
  
  protected awg c(avw.q ☃, List<awg> ☃, Random ☃, int ☃, int ☃, boolean ☃)
  {
    cq ☃ = e();
    if (☃ != null) {
      switch (avw.1.a[☃.ordinal()])
      {
      case 1: 
        return a(☃, ☃, ☃, this.l.d + 1, this.l.b + ☃, this.l.c + ☃, cq.f, d(), ☃);
      case 2: 
        return a(☃, ☃, ☃, this.l.d + 1, this.l.b + ☃, this.l.c + ☃, cq.f, d(), ☃);
      case 3: 
        return a(☃, ☃, ☃, this.l.a + ☃, this.l.b + ☃, this.l.f + 1, cq.d, d(), ☃);
      case 4: 
        return a(☃, ☃, ☃, this.l.a + ☃, this.l.b + ☃, this.l.f + 1, cq.d, d(), ☃);
      }
    }
    return null;
  }
  
  protected static boolean a(avp ☃)
  {
    return (☃ != null) && (☃.b > 10);
  }
}
