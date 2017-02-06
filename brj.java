public class brj
  extends bsg<wu>
{
  public static final kk a = new kk("textures/entity/endercrystal/endercrystal_beam.png");
  private static final kk k = new kk("textures/entity/enderdragon/dragon_exploding.png");
  private static final kk l = new kk("textures/entity/enderdragon/dragon.png");
  protected bkh b;
  
  public brj(brm ☃)
  {
    super(☃, new bkh(0.0F), 0.5F);
    
    this.b = ((bkh)this.g);
    
    a(new bts(this));
    a(new btr());
  }
  
  protected void a(wu ☃, float ☃, float ☃, float ☃)
  {
    float ☃ = (float)☃.a(7, ☃)[0];
    float ☃ = (float)(☃.a(5, ☃)[1] - ☃.a(10, ☃)[1]);
    bni.b(-☃, 0.0F, 1.0F, 0.0F);
    bni.b(☃ * 10.0F, 1.0F, 0.0F, 0.0F);
    bni.c(0.0F, 0.0F, 1.0F);
    if (☃.aA > 0)
    {
      float ☃ = (☃.aA + ☃ - 1.0F) / 20.0F * 1.6F;
      ☃ = on.c(☃);
      if (☃ > 1.0F) {
        ☃ = 1.0F;
      }
      bni.b(☃ * b(☃), 0.0F, 0.0F, 1.0F);
    }
  }
  
  protected void a(wu ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    if (☃.bF > 0)
    {
      float ☃ = ☃.bF / 200.0F;
      bni.c(515);
      bni.e();
      bni.a(516, ☃);
      a(k);
      this.g.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
      bni.a(516, 0.1F);
      
      bni.c(514);
    }
    d(☃);
    this.g.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    if (☃.ax > 0)
    {
      bni.c(514);
      bni.z();
      bni.m();
      bni.a(bni.r.l, bni.l.j);
      bni.c(1.0F, 0.0F, 0.0F, 0.5F);
      this.g.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
      bni.y();
      bni.l();
      bni.c(515);
    }
  }
  
  public void a(wu ☃, double ☃, double ☃, double ☃, float ☃, float ☃)
  {
    super.a(☃, ☃, ☃, ☃, ☃, ☃);
    if (☃.bG != null)
    {
      a(a);
      float ☃ = on.a((☃.bG.T + ☃) * 0.2F) / 2.0F + 0.5F;
      ☃ = (☃ * ☃ + ☃) * 0.2F;
      a(☃, ☃, ☃, ☃, ☃.p + (☃.m - ☃.p) * (1.0F - ☃), ☃.q + (☃.n - ☃.q) * (1.0F - ☃), ☃.r + (☃.o - ☃.r) * (1.0F - ☃), ☃.T, ☃.bG.p, ☃ + ☃.bG.q, ☃.bG.r);
    }
  }
  
  public static void a(double ☃, double ☃, double ☃, float ☃, double ☃, double ☃, double ☃, int ☃, double ☃, double ☃, double ☃)
  {
    float ☃ = (float)(☃ - ☃);
    float ☃ = (float)(☃ - 1.0D - ☃);
    float ☃ = (float)(☃ - ☃);
    
    float ☃ = on.c(☃ * ☃ + ☃ * ☃);
    float ☃ = on.c(☃ * ☃ + ☃ * ☃ + ☃ * ☃);
    
    bni.G();
    bni.c((float)☃, (float)☃ + 2.0F, (float)☃);
    bni.b((float)-Math.atan2(☃, ☃) * 57.295776F - 90.0F, 0.0F, 1.0F, 0.0F);
    bni.b((float)-Math.atan2(☃, ☃) * 57.295776F - 90.0F, 1.0F, 0.0F, 0.0F);
    
    bnu ☃ = bnu.a();
    bmz ☃ = ☃.c();
    
    bcd.a();
    bni.r();
    
    bni.j(7425);
    
    float ☃ = 0.0F - (☃ + ☃) * 0.01F;
    float ☃ = on.c(☃ * ☃ + ☃ * ☃ + ☃ * ☃) / 32.0F - (☃ + ☃) * 0.01F;
    
    ☃.a(5, bvp.i);
    
    int ☃ = 8;
    for (int ☃ = 0; ☃ <= 8; ☃++)
    {
      float ☃ = on.a(☃ % 8 * 6.2831855F / 8.0F) * 0.75F;
      float ☃ = on.b(☃ % 8 * 6.2831855F / 8.0F) * 0.75F;
      float ☃ = ☃ % 8 / 8.0F;
      ☃.b(☃ * 0.2F, ☃ * 0.2F, 0.0D).a(☃, ☃).b(0, 0, 0, 255).d();
      ☃.b(☃, ☃, ☃).a(☃, ☃).b(255, 255, 255, 255).d();
    }
    ☃.b();
    bni.q();
    bni.j(7424);
    
    bcd.b();
    bni.H();
  }
  
  protected kk a(wu ☃)
  {
    return l;
  }
}
