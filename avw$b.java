import java.util.List;
import java.util.Random;

public class avw$b
  extends avw.m
{
  private int a;
  
  public avw$b() {}
  
  public avw$b(int ☃, Random ☃, avp ☃, cq ☃)
  {
    super(☃);
    
    a(☃);
    this.l = ☃;
    this.a = ☃.nextInt();
  }
  
  public static b a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
  {
    avp ☃ = avp.a(☃, ☃, ☃, -1, -3, 0, 5, 10, 8, ☃);
    if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
      return null;
    }
    return new b(☃, ☃, ☃, ☃);
  }
  
  protected void b(dn ☃)
  {
    super.b(☃);
    
    this.a = ☃.h("Seed");
  }
  
  protected void a(dn ☃)
  {
    super.a(☃);
    
    ☃.a("Seed", this.a);
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    Random ☃ = new Random(this.a);
    for (int ☃ = 0; ☃ <= 4; ☃++) {
      for (int ☃ = 3; ☃ <= 4; ☃++)
      {
        int ☃ = ☃.nextInt(8);
        a(☃, ☃, ☃, ☃, 0, ☃, ☃, ☃, aju.by.u(), aju.by.u(), false);
      }
    }
    int ☃ = ☃.nextInt(8);
    a(☃, ☃, 0, 5, 0, 0, 5, ☃, aju.by.u(), aju.by.u(), false);
    
    int ☃ = ☃.nextInt(8);
    a(☃, ☃, 4, 5, 0, 4, 5, ☃, aju.by.u(), aju.by.u(), false);
    for (int ☃ = 0; ☃ <= 4; ☃++)
    {
      int ☃ = ☃.nextInt(5);
      a(☃, ☃, ☃, 2, 0, ☃, 2, ☃, aju.by.u(), aju.by.u(), false);
    }
    for (int ☃ = 0; ☃ <= 4; ☃++) {
      for (int ☃ = 0; ☃ <= 1; ☃++)
      {
        int ☃ = ☃.nextInt(3);
        a(☃, ☃, ☃, ☃, 0, ☃, ☃, ☃, aju.by.u(), aju.by.u(), false);
      }
    }
    return true;
  }
}
