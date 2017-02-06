import java.nio.FloatBuffer;
import java.util.Random;

public class bpx
  extends bpn<aqr>
{
  private static final kk d = new kk("textures/environment/end_sky.png");
  private static final kk e = new kk("textures/entity/end_portal.png");
  private static final Random f = new Random(31100L);
  
  public void a(aqr ☃, double ☃, double ☃, double ☃, float ☃, int ☃)
  {
    float ☃ = (float)this.c.j;
    float ☃ = (float)this.c.k;
    float ☃ = (float)this.c.l;
    
    bni.g();
    
    f.setSeed(31100L);
    
    float ☃ = 0.75F;
    for (int ☃ = 0; ☃ < 16; ☃++)
    {
      bni.G();
      
      float ☃ = 16 - ☃;
      float ☃ = 0.0625F;
      
      float ☃ = 1.0F / (☃ + 1.0F);
      if (☃ == 0)
      {
        a(d);
        ☃ = 0.1F;
        ☃ = 65.0F;
        ☃ = 0.125F;
        bni.m();
        bni.a(bni.r.l, bni.l.j);
      }
      if (☃ >= 1) {
        a(e);
      }
      if (☃ == 1)
      {
        bni.m();
        bni.a(bni.r.e, bni.l.e);
        ☃ = 0.5F;
      }
      float ☃ = (float)-(☃ + ☃);
      
      float ☃ = ☃ + (float)bca.a().c;
      float ☃ = ☃ + ☃ + (float)bca.a().c;
      float ☃ = ☃ / ☃;
      ☃ = (float)(☃ + ☃) + ☃;
      
      bni.c(☃, ☃, ☃);
      
      bni.a(bni.u.a, 9217);
      bni.a(bni.u.b, 9217);
      bni.a(bni.u.c, 9217);
      bni.a(bni.u.d, 9216);
      bni.a(bni.u.a, 9473, a(1.0F, 0.0F, 0.0F, 0.0F));
      bni.a(bni.u.b, 9473, a(0.0F, 0.0F, 1.0F, 0.0F));
      bni.a(bni.u.c, 9473, a(0.0F, 0.0F, 0.0F, 1.0F));
      bni.a(bni.u.d, 9474, a(0.0F, 1.0F, 0.0F, 0.0F));
      bni.a(bni.u.a);
      bni.a(bni.u.b);
      bni.a(bni.u.c);
      bni.a(bni.u.d);
      
      bni.H();
      bni.n(5890);
      
      bni.G();
      bni.F();
      
      bni.c(0.0F, (float)(bcf.I() % 700000L) / 700000.0F, 0.0F);
      bni.b(☃, ☃, ☃);
      bni.c(0.5F, 0.5F, 0.0F);
      bni.b((☃ * ☃ * 4321 + ☃ * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
      bni.c(-0.5F, -0.5F, 0.0F);
      bni.c(-☃, -☃, -☃);
      float ☃ = ☃ + (float)bca.a().c;
      bni.c((float)bca.a().b * ☃ / ☃, (float)bca.a().d * ☃ / ☃, -☃);
      
      bnu ☃ = bnu.a();
      bmz ☃ = ☃.c();
      
      ☃.a(7, bvp.f);
      
      float ☃ = (f.nextFloat() * 0.5F + 0.1F) * ☃;
      float ☃ = (f.nextFloat() * 0.5F + 0.4F) * ☃;
      float ☃ = (f.nextFloat() * 0.5F + 0.5F) * ☃;
      if (☃ == 0) {
        ☃ = ☃ = ☃ = 1.0F * ☃;
      }
      ☃.b(☃, ☃ + ☃, ☃).a(☃, ☃, ☃, 1.0F).d();
      ☃.b(☃, ☃ + ☃, ☃ + 1.0D).a(☃, ☃, ☃, 1.0F).d();
      ☃.b(☃ + 1.0D, ☃ + ☃, ☃ + 1.0D).a(☃, ☃, ☃, 1.0F).d();
      ☃.b(☃ + 1.0D, ☃ + ☃, ☃).a(☃, ☃, ☃, 1.0F).d();
      ☃.b();
      
      bni.H();
      bni.n(5888);
      a(d);
    }
    bni.l();
    
    bni.b(bni.u.a);
    bni.b(bni.u.b);
    bni.b(bni.u.c);
    bni.b(bni.u.d);
    bni.f();
  }
  
  FloatBuffer a = bce.h(16);
  
  private FloatBuffer a(float ☃, float ☃, float ☃, float ☃)
  {
    this.a.clear();
    this.a.put(☃).put(☃).put(☃).put(☃);
    this.a.flip();
    return this.a;
  }
}
