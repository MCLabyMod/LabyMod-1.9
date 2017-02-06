import java.util.List;
import java.util.Random;

abstract class awc$p
  extends awg
{
  protected awc.p.a d = awc.p.a.a;
  
  public awc$p() {}
  
  protected awc$p(int ☃)
  {
    super(☃);
  }
  
  protected void a(dn ☃)
  {
    ☃.a("EntryDoor", this.d.name());
  }
  
  protected void b(dn ☃)
  {
    this.d = awc.p.a.valueOf(☃.l("EntryDoor"));
  }
  
  protected void a(aht ☃, Random ☃, avp ☃, awc.p.a ☃, int ☃, int ☃, int ☃)
  {
    switch (awc.3.a[☃.ordinal()])
    {
    case 1: 
    default: 
      a(☃, ☃, ☃, ☃, ☃, ☃ + 3 - 1, ☃ + 3 - 1, ☃, aju.a.u(), aju.a.u(), false);
      break;
    case 2: 
      a(☃, aju.bf.u(), ☃, ☃, ☃, ☃);
      a(☃, aju.bf.u(), ☃, ☃ + 1, ☃, ☃);
      a(☃, aju.bf.u(), ☃, ☃ + 2, ☃, ☃);
      a(☃, aju.bf.u(), ☃ + 1, ☃ + 2, ☃, ☃);
      a(☃, aju.bf.u(), ☃ + 2, ☃ + 2, ☃, ☃);
      a(☃, aju.bf.u(), ☃ + 2, ☃ + 1, ☃, ☃);
      a(☃, aju.bf.u(), ☃ + 2, ☃, ☃, ☃);
      a(☃, aju.ao.u(), ☃ + 1, ☃, ☃, ☃);
      a(☃, aju.ao.u().a(akv.e, akv.a.a), ☃ + 1, ☃ + 1, ☃, ☃);
      break;
    case 3: 
      a(☃, aju.a.u(), ☃ + 1, ☃, ☃, ☃);
      a(☃, aju.a.u(), ☃ + 1, ☃ + 1, ☃, ☃);
      a(☃, aju.bi.u(), ☃, ☃, ☃, ☃);
      a(☃, aju.bi.u(), ☃, ☃ + 1, ☃, ☃);
      a(☃, aju.bi.u(), ☃, ☃ + 2, ☃, ☃);
      a(☃, aju.bi.u(), ☃ + 1, ☃ + 2, ☃, ☃);
      a(☃, aju.bi.u(), ☃ + 2, ☃ + 2, ☃, ☃);
      a(☃, aju.bi.u(), ☃ + 2, ☃ + 1, ☃, ☃);
      a(☃, aju.bi.u(), ☃ + 2, ☃, ☃, ☃);
      break;
    case 4: 
      a(☃, aju.bf.u(), ☃, ☃, ☃, ☃);
      a(☃, aju.bf.u(), ☃, ☃ + 1, ☃, ☃);
      a(☃, aju.bf.u(), ☃, ☃ + 2, ☃, ☃);
      a(☃, aju.bf.u(), ☃ + 1, ☃ + 2, ☃, ☃);
      a(☃, aju.bf.u(), ☃ + 2, ☃ + 2, ☃, ☃);
      a(☃, aju.bf.u(), ☃ + 2, ☃ + 1, ☃, ☃);
      a(☃, aju.bf.u(), ☃ + 2, ☃, ☃, ☃);
      a(☃, aju.aA.u(), ☃ + 1, ☃, ☃, ☃);
      a(☃, aju.aA.u().a(akv.e, akv.a.a), ☃ + 1, ☃ + 1, ☃, ☃);
      a(☃, aju.aG.u().a(ajz.H, cq.c), ☃ + 2, ☃ + 1, ☃ + 1, ☃);
      a(☃, aju.aG.u().a(ajz.H, cq.d), ☃ + 2, ☃ + 1, ☃ - 1, ☃);
    }
  }
  
  protected awc.p.a a(Random ☃)
  {
    int ☃ = ☃.nextInt(5);
    switch (☃)
    {
    case 0: 
    case 1: 
    default: 
      return awc.p.a.a;
    case 2: 
      return awc.p.a.b;
    case 3: 
      return awc.p.a.c;
    }
    return awc.p.a.d;
  }
  
  protected awg a(awc.m ☃, List<awg> ☃, Random ☃, int ☃, int ☃)
  {
    cq ☃ = e();
    if (☃ != null) {
      switch (awc.3.b[☃.ordinal()])
      {
      case 1: 
        return awc.a(☃, ☃, ☃, this.l.a + ☃, this.l.b + ☃, this.l.c - 1, ☃, d());
      case 2: 
        return awc.a(☃, ☃, ☃, this.l.a + ☃, this.l.b + ☃, this.l.f + 1, ☃, d());
      case 3: 
        return awc.a(☃, ☃, ☃, this.l.a - 1, this.l.b + ☃, this.l.c + ☃, ☃, d());
      case 4: 
        return awc.a(☃, ☃, ☃, this.l.d + 1, this.l.b + ☃, this.l.c + ☃, ☃, d());
      }
    }
    return null;
  }
  
  protected awg b(awc.m ☃, List<awg> ☃, Random ☃, int ☃, int ☃)
  {
    cq ☃ = e();
    if (☃ != null) {
      switch (awc.3.b[☃.ordinal()])
      {
      case 1: 
        return awc.a(☃, ☃, ☃, this.l.a - 1, this.l.b + ☃, this.l.c + ☃, cq.e, d());
      case 2: 
        return awc.a(☃, ☃, ☃, this.l.a - 1, this.l.b + ☃, this.l.c + ☃, cq.e, d());
      case 3: 
        return awc.a(☃, ☃, ☃, this.l.a + ☃, this.l.b + ☃, this.l.c - 1, cq.c, d());
      case 4: 
        return awc.a(☃, ☃, ☃, this.l.a + ☃, this.l.b + ☃, this.l.c - 1, cq.c, d());
      }
    }
    return null;
  }
  
  protected awg c(awc.m ☃, List<awg> ☃, Random ☃, int ☃, int ☃)
  {
    cq ☃ = e();
    if (☃ != null) {
      switch (awc.3.b[☃.ordinal()])
      {
      case 1: 
        return awc.a(☃, ☃, ☃, this.l.d + 1, this.l.b + ☃, this.l.c + ☃, cq.f, d());
      case 2: 
        return awc.a(☃, ☃, ☃, this.l.d + 1, this.l.b + ☃, this.l.c + ☃, cq.f, d());
      case 3: 
        return awc.a(☃, ☃, ☃, this.l.a + ☃, this.l.b + ☃, this.l.f + 1, cq.d, d());
      case 4: 
        return awc.a(☃, ☃, ☃, this.l.a + ☃, this.l.b + ☃, this.l.f + 1, cq.d, d());
      }
    }
    return null;
  }
  
  protected static boolean a(avp ☃)
  {
    return (☃ != null) && (☃.b > 10);
  }
  
  public static enum a
  {
    private a() {}
  }
}
