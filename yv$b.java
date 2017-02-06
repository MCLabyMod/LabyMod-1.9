import java.util.Random;

class yv$b
  extends tk
{
  private yv a;
  private int b;
  
  public yv$b(yv ☃)
  {
    this.a = ☃;
  }
  
  public void f()
  {
    if (this.b == 0) {
      this.b = 20;
    }
  }
  
  public boolean a()
  {
    return this.b > 0;
  }
  
  public void e()
  {
    this.b -= 1;
    if (this.b <= 0)
    {
      aht ☃ = this.a.l;
      Random ☃ = this.a.bF();
      
      cj ☃ = new cj(this.a);
      for (int ☃ = 0; (☃ <= 5) && (☃ >= -5); ☃ = ☃ <= 0 ? 1 - ☃ : 0 - ☃) {
        for (int ☃ = 0; (☃ <= 10) && (☃ >= -10); ☃ = ☃ <= 0 ? 1 - ☃ : 0 - ☃) {
          for (int ☃ = 0; (☃ <= 10) && (☃ >= -10); ☃ = ☃ <= 0 ? 1 - ☃ : 0 - ☃)
          {
            cj ☃ = ☃.a(☃, ☃, ☃);
            arc ☃ = ☃.o(☃);
            if (☃.t() == aju.be)
            {
              if (☃.U().b("mobGriefing")) {
                ☃.b(☃, true);
              } else {
                ☃.a(☃, ((amt.a)☃.c(amt.a)).d(), 3);
              }
              if (☃.nextBoolean()) {
                return;
              }
            }
          }
        }
      }
    }
  }
}
