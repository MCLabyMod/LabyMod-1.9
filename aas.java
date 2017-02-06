public class aas
{
  private int a;
  private float b;
  private float c;
  private int d;
  private int e;
  
  public aas()
  {
    this.a = 20;
    this.e = 20;
    this.b = 5.0F;
  }
  
  public void a(int ☃, float ☃)
  {
    this.a = Math.min(☃ + this.a, 20);
    this.b = Math.min(this.b + ☃ * ☃ * 2.0F, this.a);
  }
  
  public void a(adk ☃, adq ☃)
  {
    a(☃.h(☃), ☃.i(☃));
  }
  
  public void a(zj ☃)
  {
    qk ☃ = ☃.l.ae();
    
    this.e = this.a;
    if (this.c > 4.0F)
    {
      this.c -= 4.0F;
      if (this.b > 0.0F) {
        this.b = Math.max(this.b - 1.0F, 0.0F);
      } else if (☃ != qk.a) {
        this.a = Math.max(this.a - 1, 0);
      }
    }
    boolean ☃ = ☃.l.U().b("naturalRegeneration");
    if ((☃) && (this.b > 0.0F) && (☃.cT()) && (this.a >= 20))
    {
      this.d += 1;
      if (this.d >= 10)
      {
        float ☃ = Math.min(this.b, 4.0F);
        ☃.b(☃ / 4.0F);
        a(☃);
        this.d = 0;
      }
    }
    else if ((☃) && (this.a >= 18) && (☃.cT()))
    {
      this.d += 1;
      if (this.d >= 80)
      {
        ☃.b(1.0F);
        a(4.0F);
        this.d = 0;
      }
    }
    else if (this.a <= 0)
    {
      this.d += 1;
      if (this.d >= 80)
      {
        if ((☃.bQ() > 10.0F) || (☃ == qk.d) || ((☃.bQ() > 1.0F) && (☃ == qk.c))) {
          ☃.a(rc.g, 1.0F);
        }
        this.d = 0;
      }
    }
    else
    {
      this.d = 0;
    }
  }
  
  public void a(dn ☃)
  {
    if (☃.b("foodLevel", 99))
    {
      this.a = ☃.h("foodLevel");
      this.d = ☃.h("foodTickTimer");
      this.b = ☃.j("foodSaturationLevel");
      this.c = ☃.j("foodExhaustionLevel");
    }
  }
  
  public void b(dn ☃)
  {
    ☃.a("foodLevel", this.a);
    ☃.a("foodTickTimer", this.d);
    ☃.a("foodSaturationLevel", this.b);
    ☃.a("foodExhaustionLevel", this.c);
  }
  
  public int a()
  {
    return this.a;
  }
  
  public int b()
  {
    return this.e;
  }
  
  public boolean c()
  {
    return this.a < 20;
  }
  
  public void a(float ☃)
  {
    this.c = Math.min(this.c + ☃, 40.0F);
  }
  
  public float e()
  {
    return this.b;
  }
  
  public void a(int ☃)
  {
    this.a = ☃;
  }
  
  public void b(float ☃)
  {
    this.b = ☃;
  }
}
