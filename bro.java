public class bro
  extends brn<rx>
{
  private static final kk a = new kk("textures/entity/experience_orb.png");
  
  public bro(brm renderManagerIn)
  {
    super(renderManagerIn);
    this.d = 0.15F;
    this.e = 0.75F;
  }
  
  public void a(rx entity, double x, double y, double z, float entityYaw, float partialTicks)
  {
    if (!this.f)
    {
      bni.G();
      bni.c((float)x, (float)y, (float)z);
      d(entity);
      int i = entity.k();
      float f = (i % 4 * 16 + 0) / 64.0F;
      float f1 = (i % 4 * 16 + 16) / 64.0F;
      float f2 = (i / 4 * 16 + 0) / 64.0F;
      float f3 = (i / 4 * 16 + 16) / 64.0F;
      float f4 = 1.0F;
      float f5 = 0.5F;
      float f6 = 0.25F;
      int j = entity.d(partialTicks);
      int k = j % 65536;
      int l = j / 65536;
      bzg.a(bzg.r, k, l);
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      float f8 = 255.0F;
      float f9 = (entity.a + partialTicks) / 2.0F;
      l = (int)((on.a(f9 + 0.0F) + 1.0F) * 0.5F * 255.0F);
      int i1 = 255;
      int j1 = (int)((on.a(f9 + 4.1887903F) + 1.0F) * 0.1F * 255.0F);
      bni.c(0.0F, 0.1F, 0.0F);
      bni.b(180.0F - this.c.e, 0.0F, 1.0F, 0.0F);
      bni.b((this.c.g.ap == 2 ? -1 : 1) * -this.c.f, 1.0F, 0.0F, 0.0F);
      float f7 = 0.3F;
      bni.b(0.3F, 0.3F, 0.3F);
      bnu tessellator = bnu.a();
      bmz vertexbuffer = tessellator.c();
      vertexbuffer.a(7, bvp.l);
      
      int red = l;
      int green = 255;
      int blue = j1;
      if (Config.isCustomColors())
      {
        int col = CustomColors.getXpOrbColor(f9);
        if (col >= 0)
        {
          red = col >> 16 & 0xFF;
          green = col >> 8 & 0xFF;
          blue = col >> 0 & 0xFF;
        }
      }
      vertexbuffer.b(0.0F - f5, 0.0F - f6, 0.0D).a(f, f3).b(red, green, blue, 128).c(0.0F, 1.0F, 0.0F).d();
      vertexbuffer.b(f4 - f5, 0.0F - f6, 0.0D).a(f1, f3).b(red, green, blue, 128).c(0.0F, 1.0F, 0.0F).d();
      vertexbuffer.b(f4 - f5, 1.0F - f6, 0.0D).a(f1, f2).b(red, green, blue, 128).c(0.0F, 1.0F, 0.0F).d();
      vertexbuffer.b(0.0F - f5, 1.0F - f6, 0.0D).a(f, f2).b(red, green, blue, 128).c(0.0F, 1.0F, 0.0F).d();
      tessellator.b();
      bni.l();
      bni.E();
      bni.H();
      super.a(entity, x, y, z, entityYaw, partialTicks);
    }
  }
  
  protected kk a(rx entity)
  {
    return a;
  }
}
