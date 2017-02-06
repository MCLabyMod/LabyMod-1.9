public class bkb
  extends bix
{
  public bkb()
  {
    this(0.0F, 0.0F, false);
  }
  
  public bkb(float ☃, float ☃, boolean ☃)
  {
    super(☃, 0.0F, 64, ☃ ? 32 : 64);
    if (☃)
    {
      this.e = new bkm(this, 0, 0);
      this.e.a(-4.0F, -10.0F, -4.0F, 8, 8, 8, ☃);
      this.e.a(0.0F, 0.0F + ☃, 0.0F);
      
      this.g = new bkm(this, 16, 16);
      this.g.a(0.0F, 0.0F + ☃, 0.0F);
      this.g.a(-4.0F, 0.0F, -2.0F, 8, 12, 4, ☃ + 0.1F);
      
      this.j = new bkm(this, 0, 16);
      this.j.a(-2.0F, 12.0F + ☃, 0.0F);
      this.j.a(-2.0F, 0.0F, -2.0F, 4, 12, 4, ☃ + 0.1F);
      
      this.k = new bkm(this, 0, 16);
      this.k.i = true;
      this.k.a(2.0F, 12.0F + ☃, 0.0F);
      this.k.a(-2.0F, 0.0F, -2.0F, 4, 12, 4, ☃ + 0.1F);
    }
    else
    {
      this.e = new bkm(this, 0, 0);
      this.e.a(0.0F, ☃, 0.0F);
      this.e.a(0, 0).a(-4.0F, -10.0F, -4.0F, 8, 10, 8, ☃);
      this.e.a(24, 0).a(-1.0F, -3.0F, -6.0F, 2, 4, 2, ☃);
      
      this.g = new bkm(this, 16, 20);
      this.g.a(0.0F, 0.0F + ☃, 0.0F);
      this.g.a(-4.0F, 0.0F, -3.0F, 8, 12, 6, ☃);
      this.g.a(0, 38).a(-4.0F, 0.0F, -3.0F, 8, 18, 6, ☃ + 0.05F);
      
      this.h = new bkm(this, 44, 38);
      this.h.a(-3.0F, -2.0F, -2.0F, 4, 12, 4, ☃);
      this.h.a(-5.0F, 2.0F + ☃, 0.0F);
      
      this.i = new bkm(this, 44, 38);
      this.i.i = true;
      this.i.a(-1.0F, -2.0F, -2.0F, 4, 12, 4, ☃);
      this.i.a(5.0F, 2.0F + ☃, 0.0F);
      
      this.j = new bkm(this, 0, 22);
      this.j.a(-2.0F, 12.0F + ☃, 0.0F);
      this.j.a(-2.0F, 0.0F, -2.0F, 4, 12, 4, ☃);
      
      this.k = new bkm(this, 0, 22);
      this.k.i = true;
      this.k.a(2.0F, 12.0F + ☃, 0.0F);
      this.k.a(-2.0F, 0.0F, -2.0F, 4, 12, 4, ☃);
    }
  }
  
  public void a(float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, rr ☃)
  {
    super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    
    za ☃ = (za)☃;
    float ☃ = on.a(this.o * 3.1415927F);
    float ☃ = on.a((1.0F - (1.0F - this.o) * (1.0F - this.o)) * 3.1415927F);
    this.h.h = 0.0F;
    this.i.h = 0.0F;
    this.h.g = (-(0.1F - ☃ * 0.6F));
    this.i.g = (0.1F - ☃ * 0.6F);
    
    float ☃ = -3.1415927F / (☃.db() ? 1.5F : 2.25F);
    this.h.f = ☃;
    this.i.f = ☃;
    
    this.h.f += ☃ * 1.2F - ☃ * 0.4F;
    this.i.f += ☃ * 1.2F - ☃ * 0.4F;
    
    this.h.h += on.b(☃ * 0.09F) * 0.05F + 0.05F;
    this.i.h -= on.b(☃ * 0.09F) * 0.05F + 0.05F;
    this.h.f += on.a(☃ * 0.067F) * 0.05F;
    this.i.f -= on.a(☃ * 0.067F) * 0.05F;
  }
}
