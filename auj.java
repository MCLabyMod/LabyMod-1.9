import java.util.Random;

public class auj
  extends aud
{
  private final ajt a;
  
  public auj(ajt ☃)
  {
    super(true);
    this.a = ☃;
  }
  
  public auj()
  {
    super(false);
    this.a = null;
  }
  
  public boolean b(aht ☃, Random ☃, cj ☃)
  {
    ajt ☃ = this.a;
    if (☃ == null) {
      ☃ = ☃.nextBoolean() ? aju.bg : aju.bh;
    }
    int ☃ = ☃.nextInt(3) + 4;
    
    boolean ☃ = true;
    if ((☃.q() < 1) || (☃.q() + ☃ + 1 >= 256)) {
      return false;
    }
    for (int ☃ = ☃.q(); ☃ <= ☃.q() + 1 + ☃; ☃++)
    {
      int ☃ = 3;
      if (☃ <= ☃.q() + 3) {
        ☃ = 0;
      }
      cj.a ☃ = new cj.a();
      for (int ☃ = ☃.p() - ☃; (☃ <= ☃.p() + ☃) && (☃); ☃++) {
        for (int ☃ = ☃.r() - ☃; (☃ <= ☃.r() + ☃) && (☃); ☃++) {
          if ((☃ >= 0) && (☃ < 256))
          {
            axe ☃ = ☃.o(☃.c(☃, ☃, ☃)).a();
            if ((☃ != axe.a) && (☃ != axe.j)) {
              ☃ = false;
            }
          }
          else
          {
            ☃ = false;
          }
        }
      }
    }
    if (!☃) {
      return false;
    }
    ajt ☃ = ☃.o(☃.b()).t();
    if ((☃ != aju.d) && (☃ != aju.c) && (☃ != aju.bw)) {
      return false;
    }
    int ☃ = ☃.q() + ☃;
    if (☃ == aju.bh) {
      ☃ = ☃.q() + ☃ - 3;
    }
    for (int ☃ = ☃; ☃ <= ☃.q() + ☃; ☃++)
    {
      int ☃ = 1;
      if (☃ < ☃.q() + ☃) {
        ☃++;
      }
      if (☃ == aju.bg) {
        ☃ = 3;
      }
      int ☃ = ☃.p() - ☃;
      int ☃ = ☃.p() + ☃;
      int ☃ = ☃.r() - ☃;
      int ☃ = ☃.r() + ☃;
      for (int ☃ = ☃; ☃ <= ☃; ☃++) {
        for (int ☃ = ☃; ☃ <= ☃; ☃++)
        {
          int ☃ = 5;
          if (☃ == ☃) {
            ☃--;
          } else if (☃ == ☃) {
            ☃++;
          }
          if (☃ == ☃) {
            ☃ -= 3;
          } else if (☃ == ☃) {
            ☃ += 3;
          }
          amh.a ☃ = amh.a.a(☃);
          if ((☃ == aju.bg) || (☃ < ☃.q() + ☃))
          {
            if (((☃ == ☃) || (☃ == ☃)) && ((☃ == ☃) || (☃ == ☃))) {
              continue;
            }
            if ((☃ == ☃.p() - (☃ - 1)) && (☃ == ☃)) {
              ☃ = amh.a.a;
            }
            if ((☃ == ☃) && (☃ == ☃.r() - (☃ - 1))) {
              ☃ = amh.a.a;
            }
            if ((☃ == ☃.p() + (☃ - 1)) && (☃ == ☃)) {
              ☃ = amh.a.c;
            }
            if ((☃ == ☃) && (☃ == ☃.r() - (☃ - 1))) {
              ☃ = amh.a.c;
            }
            if ((☃ == ☃.p() - (☃ - 1)) && (☃ == ☃)) {
              ☃ = amh.a.g;
            }
            if ((☃ == ☃) && (☃ == ☃.r() + (☃ - 1))) {
              ☃ = amh.a.g;
            }
            if ((☃ == ☃.p() + (☃ - 1)) && (☃ == ☃)) {
              ☃ = amh.a.i;
            }
            if ((☃ == ☃) && (☃ == ☃.r() + (☃ - 1))) {
              ☃ = amh.a.i;
            }
          }
          if ((☃ == amh.a.e) && (☃ < ☃.q() + ☃)) {
            ☃ = amh.a.k;
          }
          if ((☃.q() >= ☃.q() + ☃ - 1) || (☃ != amh.a.k))
          {
            cj ☃ = new cj(☃, ☃, ☃);
            if (!☃.o(☃).b()) {
              a(☃, ☃, ☃.u().a(amh.a, ☃));
            }
          }
        }
      }
    }
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      arc ☃ = ☃.o(☃.b(☃));
      if (!☃.b()) {
        a(☃, ☃.b(☃), ☃.u().a(amh.a, amh.a.j));
      }
    }
    return true;
  }
}
