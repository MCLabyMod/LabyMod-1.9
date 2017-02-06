import java.util.List;
import java.util.Random;

public class aip
  extends aig
{
  protected static final atr y = new atr(false, true);
  protected static final atr z = new atr(false, false);
  protected static final auy A = new auy(false);
  private aip.a B;
  
  public aip(aip.a ☃, aig.a ☃)
  {
    super(☃);
    this.B = ☃;
    this.t.z = 10;
    this.t.B = 2;
    if (this.B == aip.a.b)
    {
      this.t.z = 6;
      this.t.A = 100;
      this.t.B = 1;
      this.v.add(new aig.c(wd.class, 4, 2, 3));
    }
    if (this.B == aip.a.a) {
      this.v.add(new aig.c(wj.class, 5, 4, 4));
    }
    if (this.B == aip.a.d) {
      this.t.z = 64537;
    }
  }
  
  public atp a(Random ☃)
  {
    if ((this.B == aip.a.d) && (☃.nextInt(3) > 0)) {
      return A;
    }
    if ((this.B == aip.a.c) || (☃.nextInt(5) == 0)) {
      return z;
    }
    if (☃.nextInt(10) == 0) {
      return o;
    }
    return n;
  }
  
  public alm.a a(Random ☃, cj ☃)
  {
    if (this.B == aip.a.b)
    {
      double ☃ = on.a((1.0D + l.a(☃.p() / 48.0D, ☃.r() / 48.0D)) / 2.0D, 0.0D, 0.9999D);
      alm.a ☃ = alm.a.values()[((int)(☃ * alm.a.values().length))];
      if (☃ == alm.a.c) {
        return alm.a.b;
      }
      return ☃;
    }
    return super.a(☃, ☃);
  }
  
  public void a(aht ☃, Random ☃, cj ☃)
  {
    if (this.B == aip.a.d) {
      b(☃, ☃, ☃);
    }
    int ☃ = ☃.nextInt(5) - 3;
    if (this.B == aip.a.b) {
      ☃ += 2;
    }
    a(☃, ☃, ☃, ☃);
    super.a(☃, ☃, ☃);
  }
  
  protected void b(aht ☃, Random ☃, cj ☃)
  {
    for (int ☃ = 0; ☃ < 4; ☃++) {
      for (int ☃ = 0; ☃ < 4; ☃++)
      {
        int ☃ = ☃ * 4 + 1 + 8 + ☃.nextInt(3);
        int ☃ = ☃ * 4 + 1 + 8 + ☃.nextInt(3);
        
        cj ☃ = ☃.l(☃.a(☃, 0, ☃));
        if (☃.nextInt(20) == 0)
        {
          auj ☃ = new auj();
          ☃.b(☃, ☃, ☃);
        }
        else
        {
          atp ☃ = a(☃);
          ☃.e();
          if (☃.b(☃, ☃, ☃)) {
            ☃.a(☃, ☃, ☃);
          }
        }
      }
    }
  }
  
  protected void a(aht ☃, Random ☃, cj ☃, int ☃)
  {
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      int ☃ = ☃.nextInt(3);
      if (☃ == 0) {
        m.a(akw.b.b);
      } else if (☃ == 1) {
        m.a(akw.b.e);
      } else if (☃ == 2) {
        m.a(akw.b.f);
      }
      for (int ☃ = 0; ☃ < 5; ☃++)
      {
        int ☃ = ☃.nextInt(16) + 8;
        int ☃ = ☃.nextInt(16) + 8;
        int ☃ = ☃.nextInt(☃.l(☃.a(☃, 0, ☃)).q() + 32);
        if (m.b(☃, ☃, new cj(☃.p() + ☃, ☃, ☃.r() + ☃))) {
          break;
        }
      }
    }
  }
  
  public Class<? extends aig> g()
  {
    return aip.class;
  }
  
  public int b(cj ☃)
  {
    int ☃ = super.b(☃);
    if (this.B == aip.a.d) {
      return (☃ & 0xFEFEFE) + 2634762 >> 1;
    }
    return ☃;
  }
  
  public static enum a
  {
    private a() {}
  }
}
