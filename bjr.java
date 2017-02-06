public class bjr
  extends bix
{
  public bjr()
  {
    this(0.0F, false);
  }
  
  public bjr(float ☃, boolean ☃)
  {
    super(☃, 0.0F, 64, 32);
    if (!☃)
    {
      this.h = new bkm(this, 40, 16);
      this.h.a(-1.0F, -2.0F, -1.0F, 2, 12, 2, ☃);
      this.h.a(-5.0F, 2.0F, 0.0F);
      
      this.i = new bkm(this, 40, 16);
      this.i.i = true;
      this.i.a(-1.0F, -2.0F, -1.0F, 2, 12, 2, ☃);
      this.i.a(5.0F, 2.0F, 0.0F);
      
      this.j = new bkm(this, 0, 16);
      this.j.a(-1.0F, 0.0F, -1.0F, 2, 12, 2, ☃);
      this.j.a(-2.0F, 12.0F, 0.0F);
      
      this.k = new bkm(this, 0, 16);
      this.k.i = true;
      this.k.a(-1.0F, 0.0F, -1.0F, 2, 12, 2, ☃);
      this.k.a(2.0F, 12.0F, 0.0F);
    }
  }
  
  public void a(sa ☃, float ☃, float ☃, float ☃)
  {
    this.m = bix.a.a;
    this.l = bix.a.a;
    
    adq ☃ = ☃.b(qm.a);
    if ((☃ != null) && (☃.b() == ads.f) && (((yw)☃).db())) {
      if (☃.cr() == rz.b) {
        this.m = bix.a.d;
      } else {
        this.l = bix.a.d;
      }
    }
    super.a(☃, ☃, ☃, ☃);
  }
  
  public void a(float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, rr ☃)
  {
    super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    
    adq ☃ = ((sa)☃).cb();
    yw ☃ = (yw)☃;
    if ((☃.db()) && ((☃ == null) || (☃.b() != ads.f)))
    {
      float ☃ = on.a(this.o * 3.1415927F);
      float ☃ = on.a((1.0F - (1.0F - this.o) * (1.0F - this.o)) * 3.1415927F);
      this.h.h = 0.0F;
      this.i.h = 0.0F;
      this.h.g = (-(0.1F - ☃ * 0.6F));
      this.i.g = (0.1F - ☃ * 0.6F);
      this.h.f = -1.5707964F;
      this.i.f = -1.5707964F;
      this.h.f -= ☃ * 1.2F - ☃ * 0.4F;
      this.i.f -= ☃ * 1.2F - ☃ * 0.4F;
      
      this.h.h += on.b(☃ * 0.09F) * 0.05F + 0.05F;
      this.i.h -= on.b(☃ * 0.09F) * 0.05F + 0.05F;
      this.h.f += on.a(☃ * 0.067F) * 0.05F;
      this.i.f -= on.a(☃ * 0.067F) * 0.05F;
    }
  }
  
  public void a(float ☃, rz ☃)
  {
    float ☃ = ☃ == rz.b ? 1.0F : -1.0F;
    bkm ☃ = a(☃);
    ☃.c += ☃;
    ☃.c(☃);
    ☃.c -= ☃;
  }
}
