public class bri
  extends brn<wt>
{
  private static final kk a = new kk("textures/entity/endercrystal/endercrystal.png");
  private final bjc b = new bki(0.0F, true);
  private final bjc g = new bki(0.0F, false);
  
  public bri(brm ☃)
  {
    super(☃);
    this.d = 0.5F;
  }
  
  public void a(wt ☃, double ☃, double ☃, double ☃, float ☃, float ☃)
  {
    float ☃ = ☃.a + ☃;
    bni.G();
    bni.c((float)☃, (float)☃, (float)☃);
    
    a(a);
    float ☃ = on.a(☃ * 0.2F) / 2.0F + 0.5F;
    ☃ = ☃ * ☃ + ☃;
    if (this.f)
    {
      bni.h();
      bni.e(c(☃));
    }
    if (☃.k()) {
      this.b.a(☃, 0.0F, ☃ * 3.0F, ☃ * 0.2F, 0.0F, 0.0F, 0.0625F);
    } else {
      this.g.a(☃, 0.0F, ☃ * 3.0F, ☃ * 0.2F, 0.0F, 0.0F, 0.0625F);
    }
    if (this.f)
    {
      bni.n();
      bni.i();
    }
    bni.H();
    
    cj ☃ = ☃.j();
    if (☃ != null)
    {
      a(brj.a);
      float ☃ = ☃.p() + 0.5F;
      float ☃ = ☃.q() + 0.5F;
      float ☃ = ☃.r() + 0.5F;
      brj.a(☃, ☃ - 1.2999999523162842D + ☃ * 0.4F, ☃, ☃, ☃.p, ☃.q, ☃.r, ☃.a, ☃, ☃, ☃);
    }
    super.a(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  protected kk a(wt ☃)
  {
    return a;
  }
  
  public boolean a(wt ☃, bqm ☃, double ☃, double ☃, double ☃)
  {
    return (super.a(☃, ☃, ☃, ☃, ☃)) || (☃.j() != null);
  }
}
