import java.util.Map;

class bcw$a
{
  private final ayz b;
  private final bux c;
  private final kk d;
  private final int[] e;
  
  private bcw$a(bcw arg1, ayz ☃)
  {
    this.b = ☃;
    this.c = new bux(128, 128);
    this.e = this.c.e();
    this.d = bcw.a(???).a("map/" + ☃.a, this.c);
    for (int ☃ = 0; ☃ < this.e.length; ☃++) {
      this.e[☃] = 0;
    }
  }
  
  private void a()
  {
    for (int ☃ = 0; ☃ < 16384; ☃++)
    {
      int ☃ = this.b.g[☃] & 0xFF;
      if (☃ / 4 == 0) {
        this.e[☃] = ((☃ + ☃ / 128 & 0x1) * 8 + 16 << 24);
      } else {
        this.e[☃] = axf.a[(☃ / 4)].a(☃ & 0x3);
      }
    }
    this.c.d();
  }
  
  private void a(boolean ☃)
  {
    int ☃ = 0;
    int ☃ = 0;
    bnu ☃ = bnu.a();
    bmz ☃ = ☃.c();
    
    float ☃ = 0.0F;
    
    bcw.a(this.a).a(this.d);
    bni.m();
    bni.a(bni.r.e, bni.l.j, bni.r.o, bni.l.e);
    bni.d();
    ☃.a(7, bvp.g);
    ☃.b(☃ + 0 + ☃, ☃ + 128 - ☃, -0.009999999776482582D).a(0.0D, 1.0D).d();
    ☃.b(☃ + 128 - ☃, ☃ + 128 - ☃, -0.009999999776482582D).a(1.0D, 1.0D).d();
    ☃.b(☃ + 128 - ☃, ☃ + 0 + ☃, -0.009999999776482582D).a(1.0D, 0.0D).d();
    ☃.b(☃ + 0 + ☃, ☃ + 0 + ☃, -0.009999999776482582D).a(0.0D, 0.0D).d();
    ☃.b();
    bni.e();
    bni.l();
    
    bcw.a(this.a).a(bcw.b());
    int ☃ = 0;
    for (ayy ☃ : this.b.i.values()) {
      if ((!☃) || (☃.a() == 1))
      {
        bni.G();
        bni.c(☃ + ☃.b() / 2.0F + 64.0F, ☃ + ☃.c() / 2.0F + 64.0F, -0.02F);
        bni.b(☃.d() * 360 / 16.0F, 0.0F, 0.0F, 1.0F);
        bni.b(4.0F, 4.0F, 3.0F);
        bni.c(-0.125F, 0.125F, 0.0F);
        
        byte ☃ = ☃.a();
        float ☃ = (☃ % 4 + 0) / 4.0F;
        float ☃ = (☃ / 4 + 0) / 4.0F;
        float ☃ = (☃ % 4 + 1) / 4.0F;
        float ☃ = (☃ / 4 + 1) / 4.0F;
        
        ☃.a(7, bvp.g);
        float ☃ = -0.001F;
        ☃.b(-1.0D, 1.0D, ☃ * -0.001F).a(☃, ☃).d();
        ☃.b(1.0D, 1.0D, ☃ * -0.001F).a(☃, ☃).d();
        ☃.b(1.0D, -1.0D, ☃ * -0.001F).a(☃, ☃).d();
        ☃.b(-1.0D, -1.0D, ☃ * -0.001F).a(☃, ☃).d();
        ☃.b();
        bni.H();
        ☃++;
      }
    }
    bni.G();
    bni.c(0.0F, 0.0F, -0.04F);
    bni.b(1.0F, 1.0F, 1.0F);
    bni.H();
  }
}
