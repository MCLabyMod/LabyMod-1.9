public class bug
  implements bty<xo>
{
  private static final kk a = new kk("textures/entity/wither/wither_armor.png");
  private final btf b;
  private final bkd c = new bkd(0.5F);
  
  public bug(btf ☃)
  {
    this.b = ☃;
  }
  
  public void a(xo ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    if (!☃.da()) {
      return;
    }
    bni.a(!☃.aN());
    
    this.b.a(a);
    
    bni.n(5890);
    bni.F();
    
    float ☃ = ☃.T + ☃;
    float ☃ = on.b(☃ * 0.02F) * 3.0F;
    float ☃ = ☃ * 0.01F;
    bni.c(☃, ☃, 0.0F);
    
    bni.n(5888);
    bni.m();
    
    float ☃ = 0.5F;
    bni.c(☃, ☃, ☃, 1.0F);
    
    bni.g();
    bni.a(bni.r.e, bni.l.e);
    
    this.c.a(☃, ☃, ☃, ☃);
    this.c.a(this.b.b());
    this.c.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    
    bni.n(5890);
    bni.F();
    bni.n(5888);
    bni.f();
    bni.l();
  }
  
  public boolean a()
  {
    return false;
  }
}
