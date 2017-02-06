import java.util.List;
import java.util.Random;

public class aja
  extends aig
{
  private static final ava y = new ava(false);
  
  protected aja(aig.a ☃)
  {
    super(☃);
    this.v.add(new aig.c(wk.class, 1, 2, 6));
    
    this.t.z = 1;
    this.t.A = 4;
    this.t.B = 20;
  }
  
  public atp a(Random ☃)
  {
    if (☃.nextInt(5) > 0) {
      return y;
    }
    return n;
  }
  
  public void a(aht ☃, Random ☃, cj ☃)
  {
    m.a(akw.b.c);
    for (int ☃ = 0; ☃ < 7; ☃++)
    {
      int ☃ = ☃.nextInt(16) + 8;
      int ☃ = ☃.nextInt(16) + 8;
      int ☃ = ☃.nextInt(☃.l(☃.a(☃, 0, ☃)).q() + 32);
      m.b(☃, ☃, ☃.a(☃, ☃, ☃));
    }
    super.a(☃, ☃, ☃);
  }
  
  public Class<? extends aig> g()
  {
    return aja.class;
  }
}
