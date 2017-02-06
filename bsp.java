public class bsp
  extends bsg<yu>
{
  private static final kk a = new kk("textures/entity/shulker/endergolem.png");
  private int b;
  
  public bsp(brm ☃, bjn ☃)
  {
    super(☃, ☃, 0.0F);
    
    a(new bsp.a(null));
    
    this.b = ☃.a();
    this.d = 0.0F;
  }
  
  public void a(yu ☃, double ☃, double ☃, double ☃, float ☃, float ☃)
  {
    if (this.b != ((bjn)this.g).a())
    {
      this.g = new bjn();
      this.b = ((bjn)this.g).a();
    }
    int ☃ = ☃.dc();
    if ((☃ > 0) && (☃.de()))
    {
      cj ☃ = ☃.da();
      cj ☃ = ☃.dd();
      
      double ☃ = (☃ - ☃) / 6.0D;
      ☃ *= ☃;
      double ☃ = (☃.p() - ☃.p()) * ☃;
      double ☃ = (☃.q() - ☃.q()) * ☃;
      double ☃ = (☃.r() - ☃.r()) * ☃;
      
      super.a(☃, ☃ - ☃, ☃ - ☃, ☃ - ☃, ☃, ☃);
    }
    else
    {
      super.a(☃, ☃, ☃, ☃, ☃, ☃);
    }
  }
  
  public boolean a(yu ☃, bqm ☃, double ☃, double ☃, double ☃)
  {
    if (super.a(☃, ☃, ☃, ☃, ☃)) {
      return true;
    }
    if ((☃.dc() > 0) && (☃.de()))
    {
      cj ☃ = ☃.dd();
      cj ☃ = ☃.da();
      
      bbj ☃ = new bbj(☃.p(), ☃.q(), ☃.r());
      bbj ☃ = new bbj(☃.p(), ☃.q(), ☃.r());
      if (☃.a(new bbh(☃.b, ☃.c, ☃.d, ☃.b, ☃.c, ☃.d))) {
        return true;
      }
    }
    return false;
  }
  
  protected kk a(yu ☃)
  {
    return a;
  }
  
  protected void a(yu ☃, float ☃, float ☃, float ☃)
  {
    super.a(☃, ☃, ☃, ☃);
    switch (bsp.1.a[☃.cZ().ordinal()])
    {
    case 1: 
    default: 
      break;
    case 2: 
      bni.c(0.5F, 0.5F, 0.0F);
      bni.b(90.0F, 1.0F, 0.0F, 0.0F);
      bni.b(90.0F, 0.0F, 0.0F, 1.0F);
      break;
    case 3: 
      bni.c(-0.5F, 0.5F, 0.0F);
      bni.b(90.0F, 1.0F, 0.0F, 0.0F);
      bni.b(-90.0F, 0.0F, 0.0F, 1.0F);
      break;
    case 4: 
      bni.c(0.0F, 0.5F, -0.5F);
      bni.b(90.0F, 1.0F, 0.0F, 0.0F);
      break;
    case 5: 
      bni.c(0.0F, 0.5F, 0.5F);
      bni.b(90.0F, 1.0F, 0.0F, 0.0F);
      bni.b(180.0F, 0.0F, 0.0F, 1.0F);
      break;
    case 6: 
      bni.c(0.0F, 1.0F, 0.0F);
      bni.b(180.0F, 1.0F, 0.0F, 0.0F);
    }
  }
  
  protected void a(yu ☃, float ☃)
  {
    float ☃ = 0.999F;
    bni.b(0.999F, 0.999F, 0.999F);
  }
  
  class a
    implements bty<yu>
  {
    private a() {}
    
    public void a(yu ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
    {
      
      switch (bsp.1.a[☃.cZ().ordinal()])
      {
      case 1: 
      default: 
        break;
      case 2: 
        bni.b(90.0F, 0.0F, 0.0F, 1.0F);
        bni.b(90.0F, 1.0F, 0.0F, 0.0F);
        bni.c(1.0F, -1.0F, 0.0F);
        bni.b(180.0F, 0.0F, 1.0F, 0.0F);
        break;
      case 3: 
        bni.b(-90.0F, 0.0F, 0.0F, 1.0F);
        bni.b(90.0F, 1.0F, 0.0F, 0.0F);
        bni.c(-1.0F, -1.0F, 0.0F);
        bni.b(180.0F, 0.0F, 1.0F, 0.0F);
        break;
      case 4: 
        bni.b(90.0F, 1.0F, 0.0F, 0.0F);
        bni.c(0.0F, -1.0F, -1.0F);
        break;
      case 5: 
        bni.b(180.0F, 0.0F, 0.0F, 1.0F);
        bni.b(90.0F, 1.0F, 0.0F, 0.0F);
        bni.c(0.0F, -1.0F, 1.0F);
        break;
      case 6: 
        bni.b(180.0F, 1.0F, 0.0F, 0.0F);
        bni.c(0.0F, -2.0F, 0.0F);
      }
      bkm ☃ = ((bjn)bsp.this.b()).a;
      ☃.g = (☃ * 0.017453292F);
      ☃.f = (☃ * 0.017453292F);
      bsp.this.a(bsp.h());
      ☃.a(☃);
      bni.H();
    }
    
    public boolean a()
    {
      return false;
    }
  }
}
