import java.util.Random;

public class aov
  extends amo
{
  protected aov(axe ☃)
  {
    super(☃);
    
    a(false);
    if (☃ == axe.i) {
      a(true);
    }
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    if (!e(☃, ☃, ☃)) {
      f(☃, ☃, ☃);
    }
  }
  
  private void f(aht ☃, cj ☃, arc ☃)
  {
    akz ☃ = a(this.x);
    ☃.a(☃, ☃.u().a(b, ☃.c(b)), 2);
    ☃.a(☃, ☃, a(☃));
  }
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    if (this.x != axe.i) {
      return;
    }
    if (!☃.U().b("doFireTick")) {
      return;
    }
    int ☃ = ☃.nextInt(3);
    if (☃ > 0)
    {
      cj ☃ = ☃;
      for (int ☃ = 0; ☃ < ☃; ☃++)
      {
        ☃ = ☃.a(☃.nextInt(3) - 1, 1, ☃.nextInt(3) - 1);
        if ((☃.q() >= 0) && (☃.q() < 256) && (!☃.e(☃))) {
          return;
        }
        ajt ☃ = ☃.o(☃).t();
        if (☃.x == axe.a)
        {
          if (c(☃, ☃)) {
            ☃.a(☃, aju.ab.u());
          }
        }
        else if (☃.x.c()) {
          return;
        }
      }
    }
    else
    {
      for (int ☃ = 0; ☃ < 3; ☃++)
      {
        cj ☃ = ☃.a(☃.nextInt(3) - 1, 0, ☃.nextInt(3) - 1);
        if ((☃.q() >= 0) && (☃.q() < 256) && (!☃.e(☃))) {
          return;
        }
        if ((☃.d(☃.a())) && (d(☃, ☃))) {
          ☃.a(☃.a(), aju.ab.u());
        }
      }
    }
  }
  
  protected boolean c(aht ☃, cj ☃)
  {
    for (cq ☃ : ) {
      if (d(☃, ☃.a(☃))) {
        return true;
      }
    }
    return false;
  }
  
  private boolean d(aht ☃, cj ☃)
  {
    if ((☃.q() >= 0) && (☃.q() < 256) && (!☃.e(☃))) {
      return false;
    }
    return ☃.o(☃).a().h();
  }
}
