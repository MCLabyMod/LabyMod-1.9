import com.google.common.collect.Lists;
import java.util.List;

public class afy
  implements afu
{
  private final adq a;
  private final List<adq> b;
  
  public afy(adq ☃, List<adq> ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public adq b()
  {
    return this.a;
  }
  
  public adq[] b(abc ☃)
  {
    adq[] ☃ = new adq[☃.u_()];
    for (int ☃ = 0; ☃ < ☃.length; ☃++)
    {
      adq ☃ = ☃.a(☃);
      if ((☃ != null) && (☃.b().r())) {
        ☃[☃] = new adq(☃.b().q());
      }
    }
    return ☃;
  }
  
  public boolean a(abc ☃, aht ☃)
  {
    List<adq> ☃ = Lists.newArrayList(this.b);
    for (int ☃ = 0; ☃ < ☃.h(); ☃++) {
      for (int ☃ = 0; ☃ < ☃.i(); ☃++)
      {
        adq ☃ = ☃.c(☃, ☃);
        if (☃ != null)
        {
          boolean ☃ = false;
          for (adq ☃ : ☃) {
            if ((☃.b() == ☃.b()) && ((☃.i() == 32767) || (☃.i() == ☃.i())))
            {
              ☃ = true;
              ☃.remove(☃);
              break;
            }
          }
          if (!☃) {
            return false;
          }
        }
      }
    }
    return ☃.isEmpty();
  }
  
  public adq a(abc ☃)
  {
    return this.a.k();
  }
  
  public int a()
  {
    return this.b.size();
  }
}
