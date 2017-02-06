import java.nio.FloatBuffer;
import java.util.Random;

public class bpw
  extends bpn<aqq>
{
  private static final kk d = new kk("textures/environment/end_sky.png");
  private static final kk e = new kk("textures/entity/end_portal.png");
  private static final kk f = new kk("textures/entity/end_gateway_beam.png");
  private static final Random g = new Random(31100L);
  private static final FloatBuffer h = bce.h(16);
  private static final FloatBuffer i = bce.h(16);
  
  public void a(aqq ☃, double ☃, double ☃, double ☃, float ☃, int ☃)
  {
    
    if ((☃.b()) || (☃.d()))
    {
      bni.a(516, 0.1F);
      a(f);
      float ☃ = ☃.b() ? ☃.e() : ☃.g();
      double ☃ = ☃.b() ? 256.0D - ☃ : 25.0D;
      ☃ = on.a(☃ * 3.1415927F);
      int ☃ = on.c(☃ * ☃);
      float[] ☃ = we.a(☃.b() ? act.c : act.e);
      bpl.a(☃, ☃, ☃, ☃, ☃, ☃.D().P(), 0, ☃, ☃, 0.15D, 0.175D);
      bpl.a(☃, ☃, ☃, ☃, ☃, ☃.D().P(), 0, -☃, ☃, 0.15D, 0.175D);
    }
    bni.g();
    
    g.setSeed(31100L);
    
    bni.c(2982, h);
    bni.c(2983, i);
    
    double ☃ = ☃ * ☃ + ☃ * ☃ + ☃ * ☃;
    int ☃;
    int ☃;
    if (☃ > 36864.0D)
    {
      ☃ = 2;
    }
    else
    {
      int ☃;
      if (☃ > 25600.0D)
      {
        ☃ = 4;
      }
      else
      {
        int ☃;
        if (☃ > 16384.0D)
        {
          ☃ = 6;
        }
        else
        {
          int ☃;
          if (☃ > 9216.0D)
          {
            ☃ = 8;
          }
          else
          {
            int ☃;
            if (☃ > 4096.0D)
            {
              ☃ = 10;
            }
            else
            {
              int ☃;
              if (☃ > 1024.0D)
              {
                ☃ = 12;
              }
              else
              {
                int ☃;
                if (☃ > 576.0D)
                {
                  ☃ = 14;
                }
                else
                {
                  int ☃;
                  if (☃ > 256.0D) {
                    ☃ = 15;
                  } else {
                    ☃ = 16;
                  }
                }
              }
            }
          }
        }
      }
    }
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      bni.G();
      
      float ☃ = 2.0F / (18 - ☃);
      if (☃ == 0)
      {
        a(d);
        ☃ = 0.15F;
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
      }
      bni.a(bni.u.a, 9216);
      bni.a(bni.u.b, 9216);
      bni.a(bni.u.c, 9216);
      bni.a(bni.u.a, 9474, a(1.0F, 0.0F, 0.0F, 0.0F));
      bni.a(bni.u.b, 9474, a(0.0F, 1.0F, 0.0F, 0.0F));
      bni.a(bni.u.c, 9474, a(0.0F, 0.0F, 1.0F, 0.0F));
      bni.a(bni.u.a);
      bni.a(bni.u.b);
      bni.a(bni.u.c);
      
      bni.H();
      bni.n(5890);
      
      bni.G();
      bni.F();
      
      bni.c(0.5F, 0.5F, 0.0F);
      bni.b(0.5F, 0.5F, 1.0F);
      
      float ☃ = ☃ + 1;
      bni.c(17.0F / ☃, (2.0F + ☃ / 1.5F) * ((float)bcf.I() % 800000.0F / 800000.0F), 0.0F);
      bni.b((☃ * ☃ * 4321.0F + ☃ * 9.0F) * 2.0F, 0.0F, 0.0F, 1.0F);
      bni.b(4.5F - ☃ / 4.0F, 4.5F - ☃ / 4.0F, 1.0F);
      
      bni.a(i);
      bni.a(h);
      
      bnu ☃ = bnu.a();
      bmz ☃ = ☃.c();
      
      ☃.a(7, bvp.f);
      
      float ☃ = (g.nextFloat() * 0.5F + 0.1F) * ☃;
      float ☃ = (g.nextFloat() * 0.5F + 0.4F) * ☃;
      float ☃ = (g.nextFloat() * 0.5F + 0.5F) * ☃;
      if (☃ == 0) {
        ☃ = ☃ = ☃ = 1.0F * ☃;
      }
      if (☃.a(cq.d))
      {
        ☃.b(☃, ☃, ☃ + 1.0D).a(☃, ☃, ☃, 1.0F).d();
        ☃.b(☃ + 1.0D, ☃, ☃ + 1.0D).a(☃, ☃, ☃, 1.0F).d();
        ☃.b(☃ + 1.0D, ☃ + 1.0D, ☃ + 1.0D).a(☃, ☃, ☃, 1.0F).d();
        ☃.b(☃, ☃ + 1.0D, ☃ + 1.0D).a(☃, ☃, ☃, 1.0F).d();
      }
      if (☃.a(cq.c))
      {
        ☃.b(☃, ☃ + 1.0D, ☃).a(☃, ☃, ☃, 1.0F).d();
        ☃.b(☃ + 1.0D, ☃ + 1.0D, ☃).a(☃, ☃, ☃, 1.0F).d();
        ☃.b(☃ + 1.0D, ☃, ☃).a(☃, ☃, ☃, 1.0F).d();
        ☃.b(☃, ☃, ☃).a(☃, ☃, ☃, 1.0F).d();
      }
      if (☃.a(cq.f))
      {
        ☃.b(☃ + 1.0D, ☃ + 1.0D, ☃).a(☃, ☃, ☃, 1.0F).d();
        ☃.b(☃ + 1.0D, ☃ + 1.0D, ☃ + 1.0D).a(☃, ☃, ☃, 1.0F).d();
        ☃.b(☃ + 1.0D, ☃, ☃ + 1.0D).a(☃, ☃, ☃, 1.0F).d();
        ☃.b(☃ + 1.0D, ☃, ☃).a(☃, ☃, ☃, 1.0F).d();
      }
      if (☃.a(cq.e))
      {
        ☃.b(☃, ☃, ☃).a(☃, ☃, ☃, 1.0F).d();
        ☃.b(☃, ☃, ☃ + 1.0D).a(☃, ☃, ☃, 1.0F).d();
        ☃.b(☃, ☃ + 1.0D, ☃ + 1.0D).a(☃, ☃, ☃, 1.0F).d();
        ☃.b(☃, ☃ + 1.0D, ☃).a(☃, ☃, ☃, 1.0F).d();
      }
      if (☃.a(cq.a))
      {
        ☃.b(☃, ☃, ☃).a(☃, ☃, ☃, 1.0F).d();
        ☃.b(☃ + 1.0D, ☃, ☃).a(☃, ☃, ☃, 1.0F).d();
        ☃.b(☃ + 1.0D, ☃, ☃ + 1.0D).a(☃, ☃, ☃, 1.0F).d();
        ☃.b(☃, ☃, ☃ + 1.0D).a(☃, ☃, ☃, 1.0F).d();
      }
      if (☃.a(cq.b))
      {
        ☃.b(☃, ☃ + 1.0D, ☃ + 1.0D).a(☃, ☃, ☃, 1.0F).d();
        ☃.b(☃ + 1.0D, ☃ + 1.0D, ☃ + 1.0D).a(☃, ☃, ☃, 1.0F).d();
        ☃.b(☃ + 1.0D, ☃ + 1.0D, ☃).a(☃, ☃, ☃, 1.0F).d();
        ☃.b(☃, ☃ + 1.0D, ☃).a(☃, ☃, ☃, 1.0F).d();
      }
      ☃.b();
      
      bni.H();
      bni.n(5888);
      a(d);
    }
    bni.l();
    
    bni.b(bni.u.a);
    bni.b(bni.u.b);
    bni.b(bni.u.c);
    bni.f();
    bni.o();
  }
  
  FloatBuffer a = bce.h(16);
  
  private FloatBuffer a(float ☃, float ☃, float ☃, float ☃)
  {
    this.a.clear();
    this.a.put(☃).put(☃).put(☃).put(☃);
    this.a.flip();
    return this.a;
  }
  
  public boolean a(aqq ☃)
  {
    return (☃.b()) || (☃.d());
  }
}
