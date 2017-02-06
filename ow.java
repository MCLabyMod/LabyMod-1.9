import java.util.List;
import java.util.Random;

public class ow
  extends ov.a
{
  private adq b;
  private int c;
  private int d;
  
  public ow(ado ☃, int ☃, int ☃, int ☃, int ☃)
  {
    super(☃);
    this.b = new adq(☃, 1, ☃);
    this.c = ☃;
    this.d = ☃;
  }
  
  public static void a(Random ☃, List<ow> ☃, aqb ☃, int ☃)
  {
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      ow ☃ = (ow)ov.a(☃, ☃);
      int ☃ = ☃.c + ☃.nextInt(☃.d - ☃.c + 1);
      if (☃.b.c() >= ☃)
      {
        adq ☃ = ☃.b.k();
        ☃.b = ☃;
        ☃.a(☃.nextInt(☃.u_()), ☃);
      }
      else
      {
        for (int ☃ = 0; ☃ < ☃; ☃++)
        {
          adq ☃ = ☃.b.k();
          ☃.b = 1;
          ☃.a(☃.nextInt(☃.u_()), ☃);
        }
      }
    }
  }
}
