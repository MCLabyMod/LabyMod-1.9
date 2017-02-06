import java.util.Random;

public class avq$a
  extends awh
{
  private boolean c;
  
  public avq$a() {}
  
  public avq$a(aht ☃, ato ☃, Random ☃, int ☃, int ☃)
  {
    super(☃, ☃);
    a(☃, ☃, ☃, ☃, ☃);
  }
  
  private void a(aht ☃, ato ☃, Random ☃, int ☃, int ☃)
  {
    aoe ☃ = aoe.values()[☃.nextInt(aoe.values().length)];
    
    atf ☃ = new atf();
    ☃.a(☃, ☃, ☃);
    
    int ☃ = 5;
    int ☃ = 5;
    if (☃ == aoe.b)
    {
      ☃ = -5;
    }
    else if (☃ == aoe.c)
    {
      ☃ = -5;
      ☃ = -5;
    }
    else if (☃ == aoe.d)
    {
      ☃ = -5;
    }
    int ☃ = ☃.a(7, 7);
    int ☃ = ☃.a(7, 7 + ☃);
    int ☃ = ☃.a(7 + ☃, 7);
    int ☃ = ☃.a(7 + ☃, 7 + ☃);
    int ☃ = Math.min(Math.min(☃, ☃), Math.min(☃, ☃));
    if (☃ < 60)
    {
      this.c = false;
      return;
    }
    cj ☃ = new cj(☃ * 16 + 8, ☃, ☃ * 16 + 8);
    avr.a(☃, ☃, this.a, ☃);
    
    d();
    
    this.c = true;
  }
  
  public boolean a()
  {
    return this.c;
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
  }
}
