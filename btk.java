import java.util.List;
import java.util.Random;

public class btk
  implements bty<sa>
{
  private final bsd<?> a;
  
  public btk(bsd<?> p_i46124_1_)
  {
    this.a = p_i46124_1_;
  }
  
  public void a(sa entitylivingbaseIn, float p_177141_2_, float p_177141_3_, float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale)
  {
    int i = entitylivingbaseIn.bX();
    if (i > 0)
    {
      rr entity = new aad(entitylivingbaseIn.l, entitylivingbaseIn.p, entitylivingbaseIn.q, entitylivingbaseIn.r);
      Random random = new Random(entitylivingbaseIn.O());
      bcd.a();
      for (int j = 0; j < i; j++)
      {
        bni.G();
        bkm modelrenderer = this.a.b().a(random);
        if (!modelrenderer.k)
        {
          bkk modelbox = (bkk)modelrenderer.l.get(random.nextInt(modelrenderer.l.size()));
          modelrenderer.c(0.0625F);
          float f = random.nextFloat();
          float f1 = random.nextFloat();
          float f2 = random.nextFloat();
          float f3 = (modelbox.a + (modelbox.d - modelbox.a) * f) / 16.0F;
          float f4 = (modelbox.b + (modelbox.e - modelbox.b) * f1) / 16.0F;
          float f5 = (modelbox.c + (modelbox.f - modelbox.c) * f2) / 16.0F;
          bni.c(f3, f4, f5);
          f = f * 2.0F - 1.0F;
          f1 = f1 * 2.0F - 1.0F;
          f2 = f2 * 2.0F - 1.0F;
          f *= -1.0F;
          f1 *= -1.0F;
          f2 *= -1.0F;
          float f6 = on.c(f * f + f2 * f2);
          entity.x = (entity.v = (float)(Math.atan2(f, f2) * 57.29577951308232D));
          entity.y = (entity.w = (float)(Math.atan2(f1, f6) * 57.29577951308232D));
          double d0 = 0.0D;
          double d1 = 0.0D;
          double d2 = 0.0D;
          this.a.d().a(entity, d0, d1, d2, 0.0F, partialTicks, false);
        }
        bni.H();
      }
      bcd.b();
    }
  }
  
  public boolean a()
  {
    return false;
  }
}
