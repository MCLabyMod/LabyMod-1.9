import java.util.Random;

class wg$a
  extends tk
{
  private wg a;
  
  public wg$a(wg ☃)
  {
    this.a = ☃;
  }
  
  public boolean a()
  {
    return true;
  }
  
  public void e()
  {
    int ☃ = this.a.bK();
    if (☃ > 100)
    {
      this.a.b(0.0F, 0.0F, 0.0F);
    }
    else if ((this.a.bF().nextInt(50) == 0) || (!wg.a(this.a)) || (!this.a.o()))
    {
      float ☃ = this.a.bF().nextFloat() * 6.2831855F;
      float ☃ = on.b(☃) * 0.2F;
      float ☃ = -0.1F + this.a.bF().nextFloat() * 0.2F;
      float ☃ = on.a(☃) * 0.2F;
      this.a.b(☃, ☃, ☃);
    }
  }
}
