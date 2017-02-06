import java.util.Random;

public class auo
  extends auq
{
  public auo(boolean ☃, int ☃, int ☃, arc ☃, arc ☃)
  {
    super(☃, ☃, ☃, ☃, ☃);
  }
  
  public boolean b(aht ☃, Random ☃, cj ☃)
  {
    int ☃ = a(☃);
    if (!a(☃, ☃, ☃, ☃)) {
      return false;
    }
    c(☃, ☃.b(☃), 2);
    
    int ☃ = ☃.q() + ☃ - 2 - ☃.nextInt(4);
    while (☃ > ☃.q() + ☃ / 2)
    {
      float ☃ = ☃.nextFloat() * 6.2831855F;
      int ☃ = ☃.p() + (int)(0.5F + on.b(☃) * 4.0F);
      int ☃ = ☃.r() + (int)(0.5F + on.a(☃) * 4.0F);
      for (int ☃ = 0; ☃ < 5; ☃++)
      {
        ☃ = ☃.p() + (int)(1.5F + on.b(☃) * ☃);
        ☃ = ☃.r() + (int)(1.5F + on.a(☃) * ☃);
        a(☃, new cj(☃, ☃ - 3 + ☃ / 2, ☃), this.b);
      }
      int ☃ = 1 + ☃.nextInt(2);
      int ☃ = ☃;
      for (int ☃ = ☃ - ☃; ☃ <= ☃; ☃++)
      {
        int ☃ = ☃ - ☃;
        b(☃, new cj(☃, ☃, ☃), 1 - ☃);
      }
      ☃ -= 2 + ☃.nextInt(4);
    }
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      cj ☃ = ☃.b(☃);
      if (a(☃.o(☃).t()))
      {
        a(☃, ☃, this.b);
        if (☃ > 0)
        {
          a(☃, ☃, ☃.e(), apj.c);
          a(☃, ☃, ☃.c(), apj.d);
        }
      }
      if (☃ < ☃ - 1)
      {
        cj ☃ = ☃.f();
        if (a(☃.o(☃).t()))
        {
          a(☃, ☃, this.b);
          if (☃ > 0)
          {
            a(☃, ☃, ☃.f(), apj.e);
            a(☃, ☃, ☃.c(), apj.d);
          }
        }
        cj ☃ = ☃.d().f();
        if (a(☃.o(☃).t()))
        {
          a(☃, ☃, this.b);
          if (☃ > 0)
          {
            a(☃, ☃, ☃.f(), apj.e);
            a(☃, ☃, ☃.d(), apj.b);
          }
        }
        cj ☃ = ☃.d();
        if (a(☃.o(☃).t()))
        {
          a(☃, ☃, this.b);
          if (☃ > 0)
          {
            a(☃, ☃, ☃.e(), apj.c);
            a(☃, ☃, ☃.d(), apj.b);
          }
        }
      }
    }
    return true;
  }
  
  private void a(aht ☃, Random ☃, cj ☃, arn ☃)
  {
    if ((☃.nextInt(3) > 0) && (☃.d(☃))) {
      a(☃, ☃, aju.bn.u().a(☃, Boolean.valueOf(true)));
    }
  }
  
  private void c(aht ☃, cj ☃, int ☃)
  {
    int ☃ = 2;
    for (int ☃ = -☃; ☃ <= 0; ☃++) {
      a(☃, ☃.b(☃), ☃ + 1 - ☃);
    }
  }
}
