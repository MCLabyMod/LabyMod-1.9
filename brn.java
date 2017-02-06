import shadersmod.client.Shaders;

public abstract class brn<T extends rr>
{
  private static final kk a = new kk("textures/misc/shadow.png");
  protected final brm c;
  protected float d;
  protected float e = 1.0F;
  protected boolean f = false;
  
  protected brn(brm renderManager)
  {
    this.c = renderManager;
  }
  
  public void a(boolean renderOutlinesIn)
  {
    this.f = renderOutlinesIn;
  }
  
  public boolean a(T livingEntity, bqm camera, double camX, double camY, double camZ)
  {
    bbh axisalignedbb = livingEntity.bm().g(0.5D);
    if ((axisalignedbb.b()) || (axisalignedbb.a() == 0.0D)) {
      axisalignedbb = new bbh(livingEntity.p - 2.0D, livingEntity.q - 2.0D, livingEntity.r - 2.0D, livingEntity.p + 2.0D, livingEntity.q + 2.0D, livingEntity.r + 2.0D);
    }
    return (livingEntity.h(camX, camY, camZ)) && ((livingEntity.ah) || (camera.a(axisalignedbb)));
  }
  
  public void a(T entity, double x, double y, double z, float entityYaw, float partialTicks)
  {
    if (!this.f) {
      a(entity, x, y, z);
    }
  }
  
  protected int c(T p_188298_1_)
  {
    int i = 16777215;
    bbm scoreplayerteam = (bbm)p_188298_1_.aO();
    if (scoreplayerteam != null)
    {
      String s = bct.b(scoreplayerteam.e());
      if (s.length() >= 2) {
        i = c().b(s.charAt(1));
      }
    }
    return i;
  }
  
  protected void a(T entity, double x, double y, double z)
  {
    if (b(entity)) {
      a(entity, entity.i_().d(), x, y, z, 64);
    }
  }
  
  protected boolean b(T entity)
  {
    return (entity.bh()) && (entity.o_());
  }
  
  protected void a(T p_188296_1_, double p_188296_2_, double p_188296_4_, double p_188296_6_, String p_188296_8_, double p_188296_9_)
  {
    a(p_188296_1_, p_188296_8_, p_188296_2_, p_188296_4_, p_188296_6_, 64);
  }
  
  protected abstract kk a(T paramT);
  
  protected boolean d(T entity)
  {
    kk resourcelocation = a(entity);
    if (resourcelocation == null) {
      return false;
    }
    a(resourcelocation);
    return true;
  }
  
  public void a(kk location)
  {
    this.c.a.a(location);
  }
  
  private void a(rr entity, double x, double y, double z, float partialTicks)
  {
    bni.g();
    bvg texturemap = bcf.z().R();
    bvh textureatlassprite = texturemap.a("minecraft:blocks/fire_layer_0");
    bvh textureatlassprite1 = texturemap.a("minecraft:blocks/fire_layer_1");
    bni.G();
    bni.c((float)x, (float)y, (float)z);
    float f = entity.G * 1.4F;
    bni.b(f, f, f);
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    float f1 = 0.5F;
    float f2 = 0.0F;
    float f3 = entity.H / f;
    float f4 = (float)(entity.q - entity.bl().b);
    bni.b(-this.c.e, 0.0F, 1.0F, 0.0F);
    bni.c(0.0F, 0.0F, -0.3F + (int)f3 * 0.02F);
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    float f5 = 0.0F;
    int i = 0;
    vertexbuffer.a(7, bvp.g);
    while (f3 > 0.0F)
    {
      bvh textureatlassprite2 = i % 2 == 0 ? textureatlassprite : textureatlassprite1;
      a(bvg.g);
      float f6 = textureatlassprite2.e();
      float f7 = textureatlassprite2.g();
      float f8 = textureatlassprite2.f();
      float f9 = textureatlassprite2.h();
      if (i / 2 % 2 == 0)
      {
        float f10 = f8;
        f8 = f6;
        f6 = f10;
      }
      vertexbuffer.b(f1 - f2, 0.0F - f4, f5).a(f8, f9).d();
      vertexbuffer.b(-f1 - f2, 0.0F - f4, f5).a(f6, f9).d();
      vertexbuffer.b(-f1 - f2, 1.4F - f4, f5).a(f6, f7).d();
      vertexbuffer.b(f1 - f2, 1.4F - f4, f5).a(f8, f7).d();
      f3 -= 0.45F;
      f4 -= 0.45F;
      f1 *= 0.9F;
      f5 += 0.03F;
      i++;
    }
    tessellator.b();
    bni.H();
    bni.f();
  }
  
  private void d(rr entityIn, double x, double y, double z, float shadowAlpha, float partialTicks)
  {
    if ((Config.isShaders()) && (Shaders.shouldSkipDefaultShadow)) {
      return;
    }
    bni.m();
    bni.a(bni.r.l, bni.l.j);
    this.c.a.a(a);
    aht world = b();
    bni.a(false);
    float f = this.d;
    if ((entityIn instanceof sb))
    {
      sb entityliving = (sb)entityIn;
      f *= entityliving.cH();
      if (entityliving.m_()) {
        f *= 0.5F;
      }
    }
    double d5 = entityIn.M + (entityIn.p - entityIn.M) * partialTicks;
    double d0 = entityIn.N + (entityIn.q - entityIn.N) * partialTicks;
    double d1 = entityIn.O + (entityIn.r - entityIn.O) * partialTicks;
    int i = on.c(d5 - f);
    int j = on.c(d5 + f);
    int k = on.c(d0 - f);
    int l = on.c(d0);
    int i1 = on.c(d1 - f);
    int j1 = on.c(d1 + f);
    double d2 = x - d5;
    double d3 = y - d0;
    double d4 = z - d1;
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    vertexbuffer.a(7, bvp.i);
    for (cj blockpos : cj.b(new cj(i, k, i1), new cj(j, l, j1)))
    {
      arc iblockstate = world.o(blockpos.b());
      if ((iblockstate.i() != aob.a) && (world.k(blockpos) > 3)) {
        a(iblockstate, x, y, z, blockpos, shadowAlpha, f, d2, d3, d4);
      }
    }
    tessellator.b();
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    bni.l();
    bni.a(true);
  }
  
  private aht b()
  {
    return this.c.b;
  }
  
  private void a(arc state, double p_188299_2_, double p_188299_4_, double p_188299_6_, cj p_188299_8_, float p_188299_9_, float p_188299_10_, double p_188299_11_, double p_188299_13_, double p_188299_15_)
  {
    if (state.h())
    {
      bnu tessellator = bnu.a();
      bmz vertexbuffer = tessellator.c();
      double d0 = (p_188299_9_ - (p_188299_4_ - (p_188299_8_.q() + p_188299_13_)) / 2.0D) * 0.5D * b().n(p_188299_8_);
      if (d0 >= 0.0D)
      {
        if (d0 > 1.0D) {
          d0 = 1.0D;
        }
        bbh axisalignedbb = state.c(b(), p_188299_8_);
        double d1 = p_188299_8_.p() + axisalignedbb.a + p_188299_11_;
        double d2 = p_188299_8_.p() + axisalignedbb.d + p_188299_11_;
        double d3 = p_188299_8_.q() + axisalignedbb.b + p_188299_13_ + 0.015625D;
        double d4 = p_188299_8_.r() + axisalignedbb.c + p_188299_15_;
        double d5 = p_188299_8_.r() + axisalignedbb.f + p_188299_15_;
        float f = (float)((p_188299_2_ - d1) / 2.0D / p_188299_10_ + 0.5D);
        float f1 = (float)((p_188299_2_ - d2) / 2.0D / p_188299_10_ + 0.5D);
        float f2 = (float)((p_188299_6_ - d4) / 2.0D / p_188299_10_ + 0.5D);
        float f3 = (float)((p_188299_6_ - d5) / 2.0D / p_188299_10_ + 0.5D);
        vertexbuffer.b(d1, d3, d4).a(f, f2).a(1.0F, 1.0F, 1.0F, (float)d0).d();
        vertexbuffer.b(d1, d3, d5).a(f, f3).a(1.0F, 1.0F, 1.0F, (float)d0).d();
        vertexbuffer.b(d2, d3, d5).a(f1, f3).a(1.0F, 1.0F, 1.0F, (float)d0).d();
        vertexbuffer.b(d2, d3, d4).a(f1, f2).a(1.0F, 1.0F, 1.0F, (float)d0).d();
      }
    }
  }
  
  public static void a(bbh boundingBox, double x, double y, double z)
  {
    bni.z();
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    vertexbuffer.c(x, y, z);
    vertexbuffer.a(7, bvp.h);
    vertexbuffer.b(boundingBox.a, boundingBox.e, boundingBox.c).c(0.0F, 0.0F, -1.0F).d();
    vertexbuffer.b(boundingBox.d, boundingBox.e, boundingBox.c).c(0.0F, 0.0F, -1.0F).d();
    vertexbuffer.b(boundingBox.d, boundingBox.b, boundingBox.c).c(0.0F, 0.0F, -1.0F).d();
    vertexbuffer.b(boundingBox.a, boundingBox.b, boundingBox.c).c(0.0F, 0.0F, -1.0F).d();
    vertexbuffer.b(boundingBox.a, boundingBox.b, boundingBox.f).c(0.0F, 0.0F, 1.0F).d();
    vertexbuffer.b(boundingBox.d, boundingBox.b, boundingBox.f).c(0.0F, 0.0F, 1.0F).d();
    vertexbuffer.b(boundingBox.d, boundingBox.e, boundingBox.f).c(0.0F, 0.0F, 1.0F).d();
    vertexbuffer.b(boundingBox.a, boundingBox.e, boundingBox.f).c(0.0F, 0.0F, 1.0F).d();
    vertexbuffer.b(boundingBox.a, boundingBox.b, boundingBox.c).c(0.0F, -1.0F, 0.0F).d();
    vertexbuffer.b(boundingBox.d, boundingBox.b, boundingBox.c).c(0.0F, -1.0F, 0.0F).d();
    vertexbuffer.b(boundingBox.d, boundingBox.b, boundingBox.f).c(0.0F, -1.0F, 0.0F).d();
    vertexbuffer.b(boundingBox.a, boundingBox.b, boundingBox.f).c(0.0F, -1.0F, 0.0F).d();
    vertexbuffer.b(boundingBox.a, boundingBox.e, boundingBox.f).c(0.0F, 1.0F, 0.0F).d();
    vertexbuffer.b(boundingBox.d, boundingBox.e, boundingBox.f).c(0.0F, 1.0F, 0.0F).d();
    vertexbuffer.b(boundingBox.d, boundingBox.e, boundingBox.c).c(0.0F, 1.0F, 0.0F).d();
    vertexbuffer.b(boundingBox.a, boundingBox.e, boundingBox.c).c(0.0F, 1.0F, 0.0F).d();
    vertexbuffer.b(boundingBox.a, boundingBox.b, boundingBox.f).c(-1.0F, 0.0F, 0.0F).d();
    vertexbuffer.b(boundingBox.a, boundingBox.e, boundingBox.f).c(-1.0F, 0.0F, 0.0F).d();
    vertexbuffer.b(boundingBox.a, boundingBox.e, boundingBox.c).c(-1.0F, 0.0F, 0.0F).d();
    vertexbuffer.b(boundingBox.a, boundingBox.b, boundingBox.c).c(-1.0F, 0.0F, 0.0F).d();
    vertexbuffer.b(boundingBox.d, boundingBox.b, boundingBox.c).c(1.0F, 0.0F, 0.0F).d();
    vertexbuffer.b(boundingBox.d, boundingBox.e, boundingBox.c).c(1.0F, 0.0F, 0.0F).d();
    vertexbuffer.b(boundingBox.d, boundingBox.e, boundingBox.f).c(1.0F, 0.0F, 0.0F).d();
    vertexbuffer.b(boundingBox.d, boundingBox.b, boundingBox.f).c(1.0F, 0.0F, 0.0F).d();
    tessellator.b();
    vertexbuffer.c(0.0D, 0.0D, 0.0D);
    bni.y();
  }
  
  public void c(rr entityIn, double x, double y, double z, float yaw, float partialTicks)
  {
    if (this.c.g != null)
    {
      if ((this.c.g.L) && (this.d > 0.0F) && (!entityIn.aN()) && (this.c.a()))
      {
        double d0 = this.c.b(entityIn.p, entityIn.q, entityIn.r);
        float f = (float)((1.0D - d0 / 256.0D) * this.e);
        if (f > 0.0F) {
          d(entityIn, x, y, z, f, partialTicks);
        }
      }
      if ((entityIn.bb()) && ((!(entityIn instanceof zj)) || (!((zj)entityIn).y()))) {
        a(entityIn, x, y, z, partialTicks);
      }
    }
  }
  
  public bct c()
  {
    return this.c.c();
  }
  
  protected void a(T entityIn, String str, double x, double y, double z, int maxDistance)
  {
    double d0 = entityIn.h(this.c.c);
    if (d0 <= maxDistance * maxDistance)
    {
      boolean flag = entityIn.aK();
      bni.G();
      float f = flag ? 0.25F : 0.0F;
      bni.c((float)x, (float)y + entityIn.H + 0.5F - f, (float)z);
      bni.a(0.0F, 1.0F, 0.0F);
      bni.b(-this.c.e, 0.0F, 1.0F, 0.0F);
      bni.b((this.c.g.ap == 2 ? -1 : 1) * this.c.f, 1.0F, 0.0F, 0.0F);
      bni.b(-0.025F, -0.025F, 0.025F);
      bni.g();
      bni.a(false);
      if (!flag) {
        bni.j();
      }
      bni.m();
      bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
      int i = str.equals("deadmau5") ? -10 : 0;
      bct fontrenderer = c();
      int j = fontrenderer.a(str) / 2;
      bni.z();
      bnu tessellator = bnu.a();
      bmz vertexbuffer = tessellator.c();
      vertexbuffer.a(7, bvp.f);
      vertexbuffer.b(-j - 1, -1 + i, 0.0D).a(0.0F, 0.0F, 0.0F, 0.25F).d();
      vertexbuffer.b(-j - 1, 8 + i, 0.0D).a(0.0F, 0.0F, 0.0F, 0.25F).d();
      vertexbuffer.b(j + 1, 8 + i, 0.0D).a(0.0F, 0.0F, 0.0F, 0.25F).d();
      vertexbuffer.b(j + 1, -1 + i, 0.0D).a(0.0F, 0.0F, 0.0F, 0.25F).d();
      tessellator.b();
      bni.y();
      if (!flag)
      {
        fontrenderer.a(str, -fontrenderer.a(str) / 2, i, 553648127);
        bni.k();
      }
      bni.a(true);
      fontrenderer.a(str, -fontrenderer.a(str) / 2, i, flag ? 553648127 : -1);
      bni.f();
      bni.l();
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      bni.H();
    }
  }
  
  public brm d()
  {
    return this.c;
  }
  
  public boolean H_()
  {
    return false;
  }
  
  public void b(T p_188300_1_, double p_188300_2_, double p_188300_4_, double p_188300_6_, float p_188300_8_, float p_188300_9_) {}
}
