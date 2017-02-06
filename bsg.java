import shadersmod.client.Shaders;

public abstract class bsg<T extends sb>
  extends bsd<T>
{
  public bsg(brm rendermanagerIn, bjc modelbaseIn, float shadowsizeIn)
  {
    super(rendermanagerIn, modelbaseIn, shadowsizeIn);
  }
  
  protected boolean b(T entity)
  {
    return (super.a(entity)) && ((entity.bh()) || ((entity.o_()) && (entity == this.c.d)));
  }
  
  public boolean a(T livingEntity, bqm camera, double camX, double camY, double camZ)
  {
    if (super.a(livingEntity, camera, camX, camY, camZ)) {
      return true;
    }
    if ((livingEntity.cP()) && (livingEntity.cQ() != null))
    {
      rr entity = livingEntity.cQ();
      return camera.a(entity.bm());
    }
    return false;
  }
  
  public void a(T entity, double x, double y, double z, float entityYaw, float partialTicks)
  {
    super.a(entity, x, y, z, entityYaw, partialTicks);
    if (!this.f) {
      b(entity, x, y, z, entityYaw, partialTicks);
    }
  }
  
  public void a(T entityLivingIn, float partialTicks)
  {
    int i = entityLivingIn.d(partialTicks);
    int j = i % 65536;
    int k = i / 65536;
    bzg.a(bzg.r, j, k);
  }
  
  private double a(double start, double end, double pct)
  {
    return start + (end - start) * pct;
  }
  
  protected void b(T entityLivingIn, double x, double y, double z, float entityYaw, float partialTicks)
  {
    if ((Config.isShaders()) && (Shaders.isShadowPass)) {
      return;
    }
    rr entity = entityLivingIn.cQ();
    if (entity != null)
    {
      y -= (1.6D - entityLivingIn.H) * 0.5D;
      bnu tessellator = bnu.a();
      bmz vertexbuffer = tessellator.c();
      double d0 = a(entity.x, entity.v, partialTicks * 0.5F) * 0.01745329238474369D;
      double d1 = a(entity.y, entity.w, partialTicks * 0.5F) * 0.01745329238474369D;
      double d2 = Math.cos(d0);
      double d3 = Math.sin(d0);
      double d4 = Math.sin(d1);
      if ((entity instanceof xr))
      {
        d2 = 0.0D;
        d3 = 0.0D;
        d4 = -1.0D;
      }
      double d5 = Math.cos(d1);
      double d6 = a(entity.m, entity.p, partialTicks) - d2 * 0.7D - d3 * 0.5D * d5;
      double d7 = a(entity.n + entity.bn() * 0.7D, entity.q + entity.bn() * 0.7D, partialTicks) - d4 * 0.5D - 0.25D;
      double d8 = a(entity.o, entity.r, partialTicks) - d3 * 0.7D + d2 * 0.5D * d5;
      double d9 = a(entityLivingIn.aN, entityLivingIn.aM, partialTicks) * 0.01745329238474369D + 1.5707963267948966D;
      d2 = Math.cos(d9) * entityLivingIn.G * 0.4D;
      d3 = Math.sin(d9) * entityLivingIn.G * 0.4D;
      double d10 = a(entityLivingIn.m, entityLivingIn.p, partialTicks) + d2;
      double d11 = a(entityLivingIn.n, entityLivingIn.q, partialTicks);
      double d12 = a(entityLivingIn.o, entityLivingIn.r, partialTicks) + d3;
      x += d2;
      z += d3;
      double d13 = (float)(d6 - d10);
      double d14 = (float)(d7 - d11);
      double d15 = (float)(d8 - d12);
      bni.z();
      bni.g();
      bni.r();
      if (Config.isShaders()) {
        Shaders.beginLeash();
      }
      int i = 24;
      double d16 = 0.025D;
      vertexbuffer.a(5, bvp.f);
      for (int j = 0; j <= 24; j++)
      {
        float f = 0.5F;
        float f1 = 0.4F;
        float f2 = 0.3F;
        if (j % 2 == 0)
        {
          f *= 0.7F;
          f1 *= 0.7F;
          f2 *= 0.7F;
        }
        float f3 = j / 24.0F;
        vertexbuffer.b(x + d13 * f3 + 0.0D, y + d14 * (f3 * f3 + f3) * 0.5D + ((24.0F - j) / 18.0F + 0.125F), z + d15 * f3).a(f, f1, f2, 1.0F).d();
        vertexbuffer.b(x + d13 * f3 + 0.025D, y + d14 * (f3 * f3 + f3) * 0.5D + ((24.0F - j) / 18.0F + 0.125F) + 0.025D, z + d15 * f3).a(f, f1, f2, 1.0F).d();
      }
      tessellator.b();
      vertexbuffer.a(5, bvp.f);
      for (int k = 0; k <= 24; k++)
      {
        float f4 = 0.5F;
        float f5 = 0.4F;
        float f6 = 0.3F;
        if (k % 2 == 0)
        {
          f4 *= 0.7F;
          f5 *= 0.7F;
          f6 *= 0.7F;
        }
        float f7 = k / 24.0F;
        vertexbuffer.b(x + d13 * f7 + 0.0D, y + d14 * (f7 * f7 + f7) * 0.5D + ((24.0F - k) / 18.0F + 0.125F) + 0.025D, z + d15 * f7).a(f4, f5, f6, 1.0F).d();
        vertexbuffer.b(x + d13 * f7 + 0.025D, y + d14 * (f7 * f7 + f7) * 0.5D + ((24.0F - k) / 18.0F + 0.125F), z + d15 * f7 + 0.025D).a(f4, f5, f6, 1.0F).d();
      }
      tessellator.b();
      if (Config.isShaders()) {
        Shaders.endLeash();
      }
      bni.f();
      bni.y();
      bni.q();
    }
  }
}
