import de.labystudio.utils.DualHand;

public class brr
  extends brn<xw>
{
  private static final kk a = new kk("textures/particle/particles.png");
  
  public brr(brm renderManagerIn)
  {
    super(renderManagerIn);
  }
  
  public void a(xw entity, double x, double y, double z, float entityYaw, float partialTicks)
  {
    bni.G();
    bni.c((float)x, (float)y, (float)z);
    bni.D();
    bni.b(0.5F, 0.5F, 0.5F);
    d(entity);
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    int i = 1;
    int j = 2;
    float f = 0.0625F;
    float f1 = 0.125F;
    float f2 = 0.125F;
    float f3 = 0.1875F;
    float f4 = 1.0F;
    float f5 = 0.5F;
    float f6 = 0.5F;
    bni.b(180.0F - this.c.e, 0.0F, 1.0F, 0.0F);
    bni.b((this.c.g.ap == 2 ? -1 : 1) * -this.c.f, 1.0F, 0.0F, 0.0F);
    if (this.f)
    {
      bni.h();
      bni.e(c(entity));
    }
    vertexbuffer.a(7, bvp.j);
    vertexbuffer.b(-0.5D, -0.5D, 0.0D).a(0.0625D, 0.1875D).c(0.0F, 1.0F, 0.0F).d();
    vertexbuffer.b(0.5D, -0.5D, 0.0D).a(0.125D, 0.1875D).c(0.0F, 1.0F, 0.0F).d();
    vertexbuffer.b(0.5D, 0.5D, 0.0D).a(0.125D, 0.125D).c(0.0F, 1.0F, 0.0F).d();
    vertexbuffer.b(-0.5D, 0.5D, 0.0D).a(0.0625D, 0.125D).c(0.0F, 1.0F, 0.0F).d();
    tessellator.b();
    if (this.f)
    {
      bni.n();
      bni.i();
    }
    bni.E();
    bni.H();
    if ((entity.a != null) && (!this.f))
    {
      int k = DualHand.getHoldingItem(346) == rz.b ? 1 : -1;
      float f7 = entity.a.m(partialTicks);
      float f8 = on.a(on.c(f7) * 3.1415927F);
      float f9 = (entity.a.aN + (entity.a.aM - entity.a.aN) * partialTicks) * 0.017453292F;
      double d0 = on.a(f9);
      double d1 = on.b(f9);
      double d2 = k * 0.35D;
      double d3 = 0.8D;
      double d7;
      double d4;
      double d5;
      double d6;
      double d7;
      if (((this.c.g == null) || (this.c.g.ap <= 0)) && (entity.a == bcf.z().h))
      {
        bbj vec3d = new bbj(k * -0.36D, -0.05D, 0.4D);
        vec3d = vec3d.a(-(entity.a.y + (entity.a.w - entity.a.y) * partialTicks) * 0.017453292F);
        vec3d = vec3d.b(-(entity.a.x + (entity.a.v - entity.a.x) * partialTicks) * 0.017453292F);
        vec3d = vec3d.b(f8 * 0.5F);
        vec3d = vec3d.a(-f8 * 0.7F);
        double d4 = entity.a.m + (entity.a.p - entity.a.m) * partialTicks + vec3d.b;
        double d5 = entity.a.n + (entity.a.q - entity.a.n) * partialTicks + vec3d.c;
        double d6 = entity.a.o + (entity.a.r - entity.a.o) * partialTicks + vec3d.d;
        d7 = entity.a.bn();
      }
      else
      {
        d4 = entity.a.m + (entity.a.p - entity.a.m) * partialTicks - d1 * d2 - d0 * 0.8D;
        d5 = entity.a.n + entity.a.bn() + (entity.a.q - entity.a.n) * partialTicks - 0.45D;
        d6 = entity.a.o + (entity.a.r - entity.a.o) * partialTicks - d0 * d2 + d1 * 0.8D;
        d7 = entity.a.aK() ? -0.1875D : 0.0D;
      }
      double d13 = entity.m + (entity.p - entity.m) * partialTicks;
      double d8 = entity.n + (entity.q - entity.n) * partialTicks + 0.25D;
      double d9 = entity.o + (entity.r - entity.o) * partialTicks;
      double d10 = (float)(d4 - d13);
      double d11 = (float)(d5 - d8) + d7;
      double d12 = (float)(d6 - d9);
      bni.z();
      bni.g();
      vertexbuffer.a(3, bvp.f);
      int l = 16;
      for (int i1 = 0; i1 <= 16; i1++)
      {
        float f10 = i1 / 16.0F;
        vertexbuffer.b(x + d10 * f10, y + d11 * (f10 * f10 + f10) * 0.5D + 0.25D, z + d12 * f10).b(0, 0, 0, 255).d();
      }
      tessellator.b();
      bni.f();
      bni.y();
      super.a(entity, x, y, z, entityYaw, partialTicks);
    }
  }
  
  protected kk a(xw entity)
  {
    return a;
  }
}
