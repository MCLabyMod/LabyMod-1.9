import java.util.Random;

class aaw$2
  extends abt
{
  aaw$2(aaw paramaaw, qg ☃, int ☃, int ☃, int ☃, aht paramaht, cj paramcj)
  {
    super(☃, ☃, ☃, ☃);
  }
  
  public boolean a(adq ☃)
  {
    return false;
  }
  
  public boolean a(zj ☃)
  {
    return ((☃.bJ.d) || (☃.bK >= this.c.a)) && (this.c.a > 0) && (e());
  }
  
  public void a(zj ☃, adq ☃)
  {
    if (!☃.bJ.d) {
      ☃.a(-this.c.a);
    }
    aaw.a(this.c).a(0, null);
    if (aaw.b(this.c) > 0)
    {
      adq ☃ = aaw.a(this.c).a(1);
      if ((☃ != null) && (☃.b > aaw.b(this.c)))
      {
        ☃.b -= aaw.b(this.c);
        aaw.a(this.c).a(1, ☃);
      }
      else
      {
        aaw.a(this.c).a(1, null);
      }
    }
    else
    {
      aaw.a(this.c).a(1, null);
    }
    this.c.a = 0;
    
    arc ☃ = this.a.o(this.b);
    if ((!☃.bJ.d) && (!this.a.E) && (☃.t() == aju.cf) && (☃.bF().nextFloat() < 0.12F))
    {
      int ☃ = ((Integer)☃.c(ajk.b)).intValue();
      ☃++;
      if (☃ > 2)
      {
        this.a.g(this.b);
        this.a.b(1029, this.b, 0);
      }
      else
      {
        this.a.a(this.b, ☃.a(ajk.b, Integer.valueOf(☃)), 2);
        this.a.b(1030, this.b, 0);
      }
    }
    else if (!this.a.E)
    {
      this.a.b(1030, this.b, 0);
    }
  }
}
