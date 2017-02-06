import java.util.Random;

public abstract class auq
  extends atp
{
  protected final int a;
  protected final arc b;
  protected final arc c;
  protected int d;
  
  public auq(boolean ☃, int ☃, int ☃, arc ☃, arc ☃)
  {
    super(☃);
    this.a = ☃;
    this.d = ☃;
    
    this.b = ☃;
    this.c = ☃;
  }
  
  protected int a(Random ☃)
  {
    int ☃ = ☃.nextInt(3) + this.a;
    if (this.d > 1) {
      ☃ += ☃.nextInt(this.d);
    }
    return ☃;
  }
  
  private boolean c(aht ☃, cj ☃, int ☃)
  {
    boolean ☃ = true;
    if ((☃.q() < 1) || (☃.q() + ☃ + 1 > 256)) {
      return false;
    }
    for (int ☃ = 0; ☃ <= 1 + ☃; ☃++)
    {
      int ☃ = 2;
      if (☃ == 0) {
        ☃ = 1;
      } else if (☃ >= 1 + ☃ - 2) {
        ☃ = 2;
      }
      for (int ☃ = -☃; (☃ <= ☃) && (☃); ☃++) {
        for (int ☃ = -☃; (☃ <= ☃) && (☃); ☃++) {
          if ((☃.q() + ☃ < 0) || (☃.q() + ☃ >= 256) || 
            (!a(☃.o(☃.a(☃, ☃, ☃)).t()))) {
            ☃ = false;
          }
        }
      }
    }
    return ☃;
  }
  
  private boolean a(cj ☃, aht ☃)
  {
    cj ☃ = ☃.b();
    ajt ☃ = ☃.o(☃).t();
    if (((☃ != aju.c) && (☃ != aju.d)) || (☃.q() < 2)) {
      return false;
    }
    a(☃, ☃);
    a(☃, ☃.f());
    a(☃, ☃.d());
    a(☃, ☃.d().f());
    
    return true;
  }
  
  protected boolean a(aht ☃, Random ☃, cj ☃, int ☃)
  {
    return (c(☃, ☃, ☃)) && (a(☃, ☃));
  }
  
  protected void a(aht ☃, cj ☃, int ☃)
  {
    int ☃ = ☃ * ☃;
    for (int ☃ = -☃; ☃ <= ☃ + 1; ☃++) {
      for (int ☃ = -☃; ☃ <= ☃ + 1; ☃++)
      {
        int ☃ = ☃ - 1;
        int ☃ = ☃ - 1;
        if ((☃ * ☃ + ☃ * ☃ <= ☃) || (☃ * ☃ + ☃ * ☃ <= ☃) || (☃ * ☃ + ☃ * ☃ <= ☃) || (☃ * ☃ + ☃ * ☃ <= ☃))
        {
          cj ☃ = ☃.a(☃, 0, ☃);
          axe ☃ = ☃.o(☃).a();
          if ((☃ == axe.a) || (☃ == axe.j)) {
            a(☃, ☃, this.c);
          }
        }
      }
    }
  }
  
  protected void b(aht ☃, cj ☃, int ☃)
  {
    int ☃ = ☃ * ☃;
    for (int ☃ = -☃; ☃ <= ☃; ☃++) {
      for (int ☃ = -☃; ☃ <= ☃; ☃++) {
        if (☃ * ☃ + ☃ * ☃ <= ☃)
        {
          cj ☃ = ☃.a(☃, 0, ☃);
          axe ☃ = ☃.o(☃).a();
          if ((☃ == axe.a) || (☃ == axe.j)) {
            a(☃, ☃, this.c);
          }
        }
      }
    }
  }
}
