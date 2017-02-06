import java.util.Random;

public class alo
  extends ami
{
  public static final arq a = arq.a("age", 0, 3);
  
  public alo()
  {
    w(this.A.b().a(a, Integer.valueOf(0)));
  }
  
  public int e(arc ☃)
  {
    return ((Integer)☃.c(a)).intValue();
  }
  
  public arc a(int ☃)
  {
    return u().a(a, Integer.valueOf(on.a(☃, 0, 3)));
  }
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    if (((☃.nextInt(3) == 0) || (c(☃, ☃) < 4)) && (☃.k(☃) > 11 - ((Integer)☃.c(a)).intValue() - ☃.c())) {
      a(☃, ☃, ☃, ☃, true);
    } else {
      ☃.a(☃, this, on.a(☃, 20, 40));
    }
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    if (☃ == this)
    {
      int ☃ = c(☃, ☃);
      if (☃ < 2) {
        b(☃, ☃);
      }
    }
  }
  
  private int c(aht ☃, cj ☃)
  {
    int ☃ = 0;
    for (cq ☃ : cq.values()) {
      if (☃.o(☃.a(☃)).t() == this)
      {
        ☃++;
        if (☃ >= 4) {
          return ☃;
        }
      }
    }
    return ☃;
  }
  
  protected void a(aht ☃, cj ☃, arc ☃, Random ☃, boolean ☃)
  {
    int ☃ = ((Integer)☃.c(a)).intValue();
    if (☃ < 3)
    {
      ☃.a(☃, ☃.a(a, Integer.valueOf(☃ + 1)), 2);
      ☃.a(☃, this, on.a(☃, 20, 40));
    }
    else
    {
      b(☃, ☃);
      if (☃) {
        for (cq ☃ : cq.values())
        {
          cj ☃ = ☃.a(☃);
          arc ☃ = ☃.o(☃);
          if (☃.t() == this) {
            a(☃, ☃, ☃, ☃, false);
          }
        }
      }
    }
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a });
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return null;
  }
}
