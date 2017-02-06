import java.util.Random;

public class ajh
  extends aij
{
  public void a(aht ☃, Random ☃, aig ☃, cj ☃)
  {
    cj ☃ = ☃.R();
    
    int ☃ = 16;
    
    double ☃ = ☃.k(☃.a(8, ☃.q(), 8));
    if (☃ > 1024.0D) {
      return;
    }
    cj ☃ = new cj(☃.p() - 16, ☃.q() - 1, ☃.r() - 16);
    cj ☃ = new cj(☃.p() + 16, ☃.q() - 1, ☃.r() + 16);
    cj.a ☃ = new cj.a(☃);
    for (int ☃ = ☃.r(); ☃ < ☃.r() + 16; ☃++) {
      for (int ☃ = ☃.p(); ☃ < ☃.p() + 16; ☃++) {
        if ((☃ >= ☃.r()) && (☃ <= ☃.r()) && (☃ >= ☃.p()) && (☃ <= ☃.p()))
        {
          ☃.c(☃, ☃.q(), ☃);
          if ((☃.p() == ☃) && (☃.r() == ☃)) {
            ☃.a(☃, aju.e.u(), 2);
          } else {
            ☃.a(☃, aju.b.u(), 2);
          }
        }
      }
    }
  }
}
