public class bru
  extends bsg<yo>
{
  private static final kk b = new kk("textures/entity/guardian.png");
  private static final kk k = new kk("textures/entity/guardian_elder.png");
  private static final kk l = new kk("textures/entity/guardian_beam.png");
  int a;
  
  public bru(brm ☃)
  {
    super(☃, new biu(), 0.5F);
    
    this.a = ((biu)this.g).a();
  }
  
  public boolean a(yo ☃, bqm ☃, double ☃, double ☃, double ☃)
  {
    if (super.a(☃, ☃, ☃, ☃, ☃)) {
      return true;
    }
    if (☃.dd())
    {
      sa ☃ = ☃.de();
      if (☃ != null)
      {
        bbj ☃ = a(☃, ☃.H * 0.5D, 1.0F);
        bbj ☃ = a(☃, ☃.bn(), 1.0F);
        if (☃.a(new bbh(☃.b, ☃.c, ☃.d, ☃.b, ☃.c, ☃.d))) {
          return true;
        }
      }
    }
    return false;
  }
  
  private bbj a(sa ☃, double ☃, float ☃)
  {
    double ☃ = ☃.M + (☃.p - ☃.M) * ☃;
    double ☃ = ☃ + ☃.N + (☃.q - ☃.N) * ☃;
    double ☃ = ☃.O + (☃.r - ☃.O) * ☃;
    return new bbj(☃, ☃, ☃);
  }
  
  public void a(yo ☃, double ☃, double ☃, double ☃, float ☃, float ☃)
  {
    if (this.a != ((biu)this.g).a())
    {
      this.g = new biu();
      this.a = ((biu)this.g).a();
    }
    super.a(☃, ☃, ☃, ☃, ☃, ☃);
    
    sa ☃ = ☃.de();
    if (☃ != null)
    {
      float ☃ = ☃.s(☃);
      
      bnu ☃ = bnu.a();
      bmz ☃ = ☃.c();
      
      a(l);
      bni.b(3553, 10242, 10497);
      bni.b(3553, 10243, 10497);
      bni.g();
      bni.r();
      bni.l();
      bni.a(true);
      float ☃ = 240.0F;
      bzg.a(bzg.r, ☃, ☃);
      bni.a(bni.r.l, bni.l.e, bni.r.e, bni.l.n);
      
      float ☃ = (float)☃.l.P() + ☃;
      float ☃ = ☃ * 0.5F % 1.0F;
      float ☃ = ☃.bn();
      
      bni.G();
      bni.c((float)☃, (float)☃ + ☃, (float)☃);
      
      bbj ☃ = a(☃, ☃.H * 0.5D, ☃);
      bbj ☃ = a(☃, ☃, ☃);
      
      bbj ☃ = ☃.d(☃);
      double ☃ = ☃.b() + 1.0D;
      ☃ = ☃.a();
      
      float ☃ = (float)Math.acos(☃.c);
      float ☃ = (float)Math.atan2(☃.d, ☃.b);
      bni.b((1.5707964F + -☃) * 57.295776F, 0.0F, 1.0F, 0.0F);
      bni.b(☃ * 57.295776F, 1.0F, 0.0F, 0.0F);
      
      int ☃ = 1;
      
      double ☃ = ☃ * 0.05D * (1.0D - (☃ & 0x1) * 2.5D);
      
      ☃.a(7, bvp.i);
      
      float ☃ = ☃ * ☃;
      int ☃ = 64 + (int)(☃ * 191.0F);
      int ☃ = 32 + (int)(☃ * 191.0F);
      int ☃ = 128 - (int)(☃ * 64.0F);
      
      double ☃ = ☃ * 0.2D;
      double ☃ = ☃ * 1.41D;
      
      double ☃ = 0.0D + Math.cos(☃ + 2.356194490192345D) * ☃;
      double ☃ = 0.0D + Math.sin(☃ + 2.356194490192345D) * ☃;
      double ☃ = 0.0D + Math.cos(☃ + 0.7853981633974483D) * ☃;
      double ☃ = 0.0D + Math.sin(☃ + 0.7853981633974483D) * ☃;
      double ☃ = 0.0D + Math.cos(☃ + 3.9269908169872414D) * ☃;
      double ☃ = 0.0D + Math.sin(☃ + 3.9269908169872414D) * ☃;
      double ☃ = 0.0D + Math.cos(☃ + 5.497787143782138D) * ☃;
      double ☃ = 0.0D + Math.sin(☃ + 5.497787143782138D) * ☃;
      
      double ☃ = 0.0D + Math.cos(☃ + 3.141592653589793D) * ☃;
      double ☃ = 0.0D + Math.sin(☃ + 3.141592653589793D) * ☃;
      double ☃ = 0.0D + Math.cos(☃ + 0.0D) * ☃;
      double ☃ = 0.0D + Math.sin(☃ + 0.0D) * ☃;
      
      double ☃ = 0.0D + Math.cos(☃ + 1.5707963267948966D) * ☃;
      double ☃ = 0.0D + Math.sin(☃ + 1.5707963267948966D) * ☃;
      double ☃ = 0.0D + Math.cos(☃ + 4.71238898038469D) * ☃;
      double ☃ = 0.0D + Math.sin(☃ + 4.71238898038469D) * ☃;
      
      double ☃ = ☃;
      
      double ☃ = 0.0D;
      double ☃ = 0.4999D;
      double ☃ = -1.0F + ☃;
      double ☃ = ☃ * (0.5D / ☃) + ☃;
      
      ☃.b(☃, ☃, ☃).a(0.4999D, ☃).b(☃, ☃, ☃, 255).d();
      ☃.b(☃, 0.0D, ☃).a(0.4999D, ☃).b(☃, ☃, ☃, 255).d();
      ☃.b(☃, 0.0D, ☃).a(0.0D, ☃).b(☃, ☃, ☃, 255).d();
      ☃.b(☃, ☃, ☃).a(0.0D, ☃).b(☃, ☃, ☃, 255).d();
      
      ☃.b(☃, ☃, ☃).a(0.4999D, ☃).b(☃, ☃, ☃, 255).d();
      ☃.b(☃, 0.0D, ☃).a(0.4999D, ☃).b(☃, ☃, ☃, 255).d();
      ☃.b(☃, 0.0D, ☃).a(0.0D, ☃).b(☃, ☃, ☃, 255).d();
      ☃.b(☃, ☃, ☃).a(0.0D, ☃).b(☃, ☃, ☃, 255).d();
      
      double ☃ = 0.0D;
      if (☃.T % 2 == 0) {
        ☃ = 0.5D;
      }
      ☃.b(☃, ☃, ☃).a(0.5D, ☃ + 0.5D).b(☃, ☃, ☃, 255).d();
      ☃.b(☃, ☃, ☃).a(1.0D, ☃ + 0.5D).b(☃, ☃, ☃, 255).d();
      ☃.b(☃, ☃, ☃).a(1.0D, ☃).b(☃, ☃, ☃, 255).d();
      ☃.b(☃, ☃, ☃).a(0.5D, ☃).b(☃, ☃, ☃, 255).d();
      
      ☃.b();
      
      bni.H();
    }
  }
  
  protected void a(yo ☃, float ☃)
  {
    if (☃.db()) {
      bni.b(2.35F, 2.35F, 2.35F);
    }
  }
  
  protected kk a(yo ☃)
  {
    return ☃.db() ? k : b;
  }
}
