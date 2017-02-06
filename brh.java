public class brh
  extends brn<zn>
{
  private static final kk a = new kk("textures/entity/enderdragon/dragon_fireball.png");
  
  public brh(brm ☃)
  {
    super(☃);
  }
  
  public void a(zn ☃, double ☃, double ☃, double ☃, float ☃, float ☃)
  {
    bni.G();
    
    d(☃);
    
    bni.c((float)☃, (float)☃, (float)☃);
    bni.D();
    bni.b(2.0F, 2.0F, 2.0F);
    
    bnu ☃ = bnu.a();
    bmz ☃ = ☃.c();
    
    float ☃ = 1.0F;
    float ☃ = 0.5F;
    float ☃ = 0.25F;
    
    bni.b(180.0F - this.c.e, 0.0F, 1.0F, 0.0F);
    bni.b((this.c.g.ap == 2 ? -1 : 1) * -this.c.f, 1.0F, 0.0F, 0.0F);
    if (this.f)
    {
      bni.h();
      bni.e(c(☃));
    }
    ☃.a(7, bvp.j);
    ☃.b(-0.5D, -0.25D, 0.0D).a(0.0D, 1.0D).c(0.0F, 1.0F, 0.0F).d();
    ☃.b(0.5D, -0.25D, 0.0D).a(1.0D, 1.0D).c(0.0F, 1.0F, 0.0F).d();
    ☃.b(0.5D, 0.75D, 0.0D).a(1.0D, 0.0D).c(0.0F, 1.0F, 0.0F).d();
    ☃.b(-0.5D, 0.75D, 0.0D).a(0.0D, 0.0D).c(0.0F, 1.0F, 0.0F).d();
    ☃.b();
    if (this.f)
    {
      bni.n();
      bni.i();
    }
    bni.E();
    bni.H();
    
    super.a(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  protected kk a(zn ☃)
  {
    return a;
  }
}
