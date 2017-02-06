public class btn
  implements bty<yi>
{
  private static final kk a = new kk("textures/entity/creeper/creeper_armor.png");
  private final brf b;
  private final bip c = new bip(2.0F);
  
  public btn(brf ☃)
  {
    this.b = ☃;
  }
  
  public void a(yi ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    if (!☃.o()) {
      return;
    }
    boolean ☃ = ☃.aN();
    bni.a(!☃);
    
    this.b.a(a);
    
    bni.n(5890);
    bni.F();
    
    float ☃ = ☃.T + ☃;
    bni.c(☃ * 0.01F, ☃ * 0.01F, 0.0F);
    
    bni.n(5888);
    bni.m();
    float ☃ = 0.5F;
    bni.c(☃, ☃, ☃, 1.0F);
    bni.g();
    bni.a(bni.r.e, bni.l.e);
    
    this.c.a(this.b.b());
    this.c.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    
    bni.n(5890);
    bni.F();
    bni.n(5888);
    bni.f();
    bni.l();
    
    bni.a(☃);
  }
  
  public boolean a()
  {
    return false;
  }
}
