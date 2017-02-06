import java.util.List;
import java.util.Random;

public class aiy
  extends aig
{
  protected boolean y;
  
  protected aiy(boolean ☃, aig.a ☃)
  {
    super(☃);
    this.y = ☃;
    
    this.v.add(new aig.c(wk.class, 5, 2, 6));
    
    this.t.z = 64537;
    this.t.A = 4;
    this.t.B = 10;
  }
  
  public alm.a a(Random ☃, cj ☃)
  {
    double ☃ = l.a(☃.p() / 200.0D, ☃.r() / 200.0D);
    if (☃ < -0.8D)
    {
      int ☃ = ☃.nextInt(4);
      switch (☃)
      {
      case 0: 
        return alm.a.g;
      case 1: 
        return alm.a.f;
      case 2: 
        return alm.a.i;
      }
      return alm.a.h;
    }
    if (☃.nextInt(3) > 0)
    {
      int ☃ = ☃.nextInt(3);
      if (☃ == 0) {
        return alm.a.b;
      }
      if (☃ == 1) {
        return alm.a.e;
      }
      return alm.a.j;
    }
    return alm.a.a;
  }
  
  public void a(aht ☃, Random ☃, cj ☃)
  {
    double ☃ = l.a((☃.p() + 8) / 200.0D, (☃.r() + 8) / 200.0D);
    if (☃ < -0.8D)
    {
      this.t.A = 15;
      this.t.B = 5;
    }
    else
    {
      this.t.A = 4;
      this.t.B = 10;
      
      m.a(akw.b.c);
      for (int ☃ = 0; ☃ < 7; ☃++)
      {
        int ☃ = ☃.nextInt(16) + 8;
        int ☃ = ☃.nextInt(16) + 8;
        int ☃ = ☃.nextInt(☃.l(☃.a(☃, 0, ☃)).q() + 32);
        m.b(☃, ☃, ☃.a(☃, ☃, ☃));
      }
    }
    if (this.y)
    {
      m.a(akw.b.a);
      for (int ☃ = 0; ☃ < 10; ☃++)
      {
        int ☃ = ☃.nextInt(16) + 8;
        int ☃ = ☃.nextInt(16) + 8;
        int ☃ = ☃.nextInt(☃.l(☃.a(☃, 0, ☃)).q() + 32);
        m.b(☃, ☃, ☃.a(☃, ☃, ☃));
      }
    }
    super.a(☃, ☃, ☃);
  }
}
