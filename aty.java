import com.google.common.base.Predicates;
import java.util.Random;

public class aty
  extends aud
{
  private static final ark a = ark.a(aju.m).a(aof.a, Predicates.equalTo(aof.a.a));
  private final arc b = aju.U.u().a(apa.e, apa.a.b).a(alz.a, alz.a.b);
  private final arc c = aju.A.u();
  private final arc d = aju.i.u();
  
  public boolean b(aht ☃, Random ☃, cj ☃)
  {
    while ((☃.d(☃)) && (☃.q() > 2)) {
      ☃ = ☃.b();
    }
    if (!a.a(☃.o(☃))) {
      return false;
    }
    for (int ☃ = -2; ☃ <= 2; ☃++) {
      for (int ☃ = -2; ☃ <= 2; ☃++) {
        if ((☃.d(☃.a(☃, -1, ☃))) && (☃.d(☃.a(☃, -2, ☃)))) {
          return false;
        }
      }
    }
    for (int ☃ = -1; ☃ <= 0; ☃++) {
      for (int ☃ = -2; ☃ <= 2; ☃++) {
        for (int ☃ = -2; ☃ <= 2; ☃++) {
          ☃.a(☃.a(☃, ☃, ☃), this.c, 2);
        }
      }
    }
    ☃.a(☃, this.d, 2);
    for (cq ☃ : cq.c.a) {
      ☃.a(☃.a(☃), this.d, 2);
    }
    for (int ☃ = -2; ☃ <= 2; ☃++) {
      for (int ☃ = -2; ☃ <= 2; ☃++) {
        if ((☃ == -2) || (☃ == 2) || (☃ == -2) || (☃ == 2)) {
          ☃.a(☃.a(☃, 1, ☃), this.c, 2);
        }
      }
    }
    ☃.a(☃.a(2, 1, 0), this.b, 2);
    ☃.a(☃.a(-2, 1, 0), this.b, 2);
    ☃.a(☃.a(0, 1, 2), this.b, 2);
    ☃.a(☃.a(0, 1, -2), this.b, 2);
    for (int ☃ = -1; ☃ <= 1; ☃++) {
      for (int ☃ = -1; ☃ <= 1; ☃++) {
        if ((☃ == 0) && (☃ == 0)) {
          ☃.a(☃.a(☃, 4, ☃), this.c, 2);
        } else {
          ☃.a(☃.a(☃, 4, ☃), this.b, 2);
        }
      }
    }
    for (int ☃ = 1; ☃ <= 3; ☃++)
    {
      ☃.a(☃.a(-1, ☃, -1), this.c, 2);
      ☃.a(☃.a(-1, ☃, 1), this.c, 2);
      ☃.a(☃.a(1, ☃, -1), this.c, 2);
      ☃.a(☃.a(1, ☃, 1), this.c, 2);
    }
    return true;
  }
}
