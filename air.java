import java.util.List;
import java.util.Random;

public class air
  extends aig
{
  private boolean y;
  private aul z = new aul();
  private auk A = new auk(4);
  
  public air(boolean ☃, aig.a ☃)
  {
    super(☃);
    this.y = ☃;
    if (☃) {
      this.r = aju.aJ.u();
    }
    this.v.clear();
    this.v.add(new aig.c(wd.class, 4, 2, 3));
  }
  
  public void a(aht ☃, Random ☃, cj ☃)
  {
    if (this.y)
    {
      for (int ☃ = 0; ☃ < 3; ☃++)
      {
        int ☃ = ☃.nextInt(16) + 8;
        int ☃ = ☃.nextInt(16) + 8;
        this.z.b(☃, ☃, ☃.l(☃.a(☃, 0, ☃)));
      }
      for (int ☃ = 0; ☃ < 2; ☃++)
      {
        int ☃ = ☃.nextInt(16) + 8;
        int ☃ = ☃.nextInt(16) + 8;
        this.A.b(☃, ☃, ☃.l(☃.a(☃, 0, ☃)));
      }
    }
    super.a(☃, ☃, ☃);
  }
  
  public atp a(Random ☃)
  {
    return new avd(false);
  }
}
