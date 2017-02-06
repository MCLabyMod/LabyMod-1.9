import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Random;

public class anh
  extends ajt
{
  public anh()
  {
    this(axe.e.r());
  }
  
  public anh(axf ☃)
  {
    super(axe.e, ☃);
    a(acq.b);
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    if (this == aju.q) {
      return ads.j;
    }
    if (this == aju.ag) {
      return ads.k;
    }
    if (this == aju.x) {
      return ads.bd;
    }
    if (this == aju.bP) {
      return ads.bY;
    }
    if (this == aju.co) {
      return ads.cq;
    }
    return ado.a(this);
  }
  
  public int a(Random ☃)
  {
    if (this == aju.x) {
      return 4 + ☃.nextInt(5);
    }
    return 1;
  }
  
  public int a(int ☃, Random ☃)
  {
    if ((☃ > 0) && (ado.a(this) != a((arc)t().a().iterator().next(), ☃, ☃)))
    {
      int ☃ = ☃.nextInt(☃ + 2) - 1;
      if (☃ < 0) {
        ☃ = 0;
      }
      return a(☃) * (☃ + 1);
    }
    return a(☃);
  }
  
  public void a(aht ☃, cj ☃, arc ☃, float ☃, int ☃)
  {
    super.a(☃, ☃, ☃, ☃, ☃);
    if (a(☃, ☃.r, ☃) != ado.a(this))
    {
      int ☃ = 0;
      if (this == aju.q) {
        ☃ = on.a(☃.r, 0, 2);
      } else if (this == aju.ag) {
        ☃ = on.a(☃.r, 3, 7);
      } else if (this == aju.bP) {
        ☃ = on.a(☃.r, 3, 7);
      } else if (this == aju.x) {
        ☃ = on.a(☃.r, 2, 5);
      } else if (this == aju.co) {
        ☃ = on.a(☃.r, 2, 5);
      }
      b(☃, ☃, ☃);
    }
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(this);
  }
  
  public int d(arc ☃)
  {
    if (this == aju.x) {
      return act.l.b();
    }
    return 0;
  }
}
