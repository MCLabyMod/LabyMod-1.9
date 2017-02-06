public class bjz
  extends bjc
{
  public bkm a;
  public bkm b;
  public bkm c;
  public bkm d;
  public bkm e;
  public bkm f;
  
  public bjz()
  {
    this(0.0F);
  }
  
  public bjz(float ☃)
  {
    this(☃, -7.0F);
  }
  
  public bjz(float ☃, float ☃)
  {
    int ☃ = 128;
    int ☃ = 128;
    
    this.a = new bkm(this).b(☃, ☃);
    this.a.a(0.0F, 0.0F + ☃, -2.0F);
    this.a.a(0, 0).a(-4.0F, -12.0F, -5.5F, 8, 10, 8, ☃);
    this.a.a(24, 0).a(-1.0F, -5.0F, -7.5F, 2, 4, 2, ☃);
    
    this.b = new bkm(this).b(☃, ☃);
    this.b.a(0.0F, 0.0F + ☃, 0.0F);
    this.b.a(0, 40).a(-9.0F, -2.0F, -6.0F, 18, 12, 11, ☃);
    this.b.a(0, 70).a(-4.5F, 10.0F, -3.0F, 9, 5, 6, ☃ + 0.5F);
    
    this.c = new bkm(this).b(☃, ☃);
    this.c.a(0.0F, -7.0F, 0.0F);
    this.c.a(60, 21).a(-13.0F, -2.5F, -3.0F, 4, 30, 6, ☃);
    
    this.d = new bkm(this).b(☃, ☃);
    this.d.a(0.0F, -7.0F, 0.0F);
    this.d.a(60, 58).a(9.0F, -2.5F, -3.0F, 4, 30, 6, ☃);
    
    this.e = new bkm(this, 0, 22).b(☃, ☃);
    this.e.a(-4.0F, 18.0F + ☃, 0.0F);
    this.e.a(37, 0).a(-3.5F, -3.0F, -3.0F, 6, 16, 5, ☃);
    
    this.f = new bkm(this, 0, 22).b(☃, ☃);
    this.f.i = true;
    this.f.a(60, 0).a(5.0F, 18.0F + ☃, 0.0F);
    this.f.a(-3.5F, -3.0F, -3.0F, 6, 16, 5, ☃);
  }
  
  public void a(rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    
    this.a.a(☃);
    this.b.a(☃);
    this.e.a(☃);
    this.f.a(☃);
    this.c.a(☃);
    this.d.a(☃);
  }
  
  public void a(float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, rr ☃)
  {
    this.a.g = (☃ * 0.017453292F);
    this.a.f = (☃ * 0.017453292F);
    
    this.e.f = (-1.5F * a(☃, 13.0F) * ☃);
    this.f.f = (1.5F * a(☃, 13.0F) * ☃);
    this.e.g = 0.0F;
    this.f.g = 0.0F;
  }
  
  public void a(sa ☃, float ☃, float ☃, float ☃)
  {
    wh ☃ = (wh)☃;
    
    int ☃ = ☃.cZ();
    if (☃ > 0)
    {
      this.c.f = (-2.0F + 1.5F * a(☃ - ☃, 10.0F));
      this.d.f = (-2.0F + 1.5F * a(☃ - ☃, 10.0F));
    }
    else
    {
      int ☃ = ☃.da();
      if (☃ > 0)
      {
        this.c.f = (-0.8F + 0.025F * a(☃, 70.0F));
        this.d.f = 0.0F;
      }
      else
      {
        this.c.f = ((-0.2F + 1.5F * a(☃, 13.0F)) * ☃);
        this.d.f = ((-0.2F - 1.5F * a(☃, 13.0F)) * ☃);
      }
    }
  }
  
  private float a(float ☃, float ☃)
  {
    return (Math.abs(☃ % ☃ - ☃ * 0.5F) - ☃ * 0.25F) / (☃ * 0.25F);
  }
}
