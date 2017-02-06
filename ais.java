import java.util.List;
import java.util.Random;

public class ais
  extends aig
{
  private boolean y;
  private static final arc z = aju.r.u().a(ang.b, anj.a.d);
  private static final arc A = aju.t.u().a(anf.e, anj.a.d).a(aml.b, Boolean.valueOf(false));
  private static final arc B = aju.t.u().a(anf.e, anj.a.a).a(aml.b, Boolean.valueOf(false));
  
  public ais(boolean ☃, aig.a ☃)
  {
    super(☃);
    this.y = ☃;
    if (☃) {
      this.t.z = 2;
    } else {
      this.t.z = 50;
    }
    this.t.B = 25;
    this.t.A = 4;
    if (!☃) {
      this.u.add(new aig.c(wb.class, 2, 1, 1));
    }
    this.v.add(new aig.c(vx.class, 10, 4, 4));
  }
  
  public atp a(Random ☃)
  {
    if (☃.nextInt(10) == 0) {
      return o;
    }
    if (☃.nextInt(2) == 0) {
      return new auf(z, B);
    }
    if ((!this.y) && (☃.nextInt(3) == 0)) {
      return new auo(false, 10, 20, z, A);
    }
    return new avg(false, 4 + ☃.nextInt(7), z, A, true);
  }
  
  public aud b(Random ☃)
  {
    if (☃.nextInt(4) == 0) {
      return new avf(apc.a.c);
    }
    return new avf(apc.a.b);
  }
  
  public void a(aht ☃, Random ☃, cj ☃)
  {
    super.a(☃, ☃, ☃);
    
    int ☃ = ☃.nextInt(16) + 8;
    int ☃ = ☃.nextInt(16) + 8;
    int ☃ = ☃.nextInt(☃.l(☃.a(☃, 0, ☃)).q() * 2);
    new aur().b(☃, ☃, ☃.a(☃, ☃, ☃));
    
    avh ☃ = new avh();
    for (int ☃ = 0; ☃ < 50; ☃++)
    {
      int ☃ = ☃.nextInt(16) + 8;
      int ☃ = 128;
      int ☃ = ☃.nextInt(16) + 8;
      
      ☃.b(☃, ☃, ☃.a(☃, 128, ☃));
    }
  }
}
