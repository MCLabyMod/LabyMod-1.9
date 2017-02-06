public class btl
  implements bty<bmq>
{
  private final buk a;
  
  public btl(buk playerRendererIn)
  {
    this.a = playerRendererIn;
  }
  
  public void a(bmq entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
  {
    if ((entitylivingbaseIn.a()) && (!entitylivingbaseIn.aN()) && (entitylivingbaseIn.a(zk.a)) && (entitylivingbaseIn.r() != null))
    {
      adq itemstack = entitylivingbaseIn.a(rw.e);
      if ((itemstack == null) || (itemstack.b() != ads.cR))
      {
        bni.c(1.0F, 1.0F, 1.0F, 1.0F);
        this.a.a(entitylivingbaseIn.r());
        bni.G();
        bni.c(0.0F, 0.0F, 0.125F);
        double d0 = entitylivingbaseIn.bz + (entitylivingbaseIn.bC - entitylivingbaseIn.bz) * partialTicks - (entitylivingbaseIn.m + (entitylivingbaseIn.p - entitylivingbaseIn.m) * partialTicks);
        double d1 = entitylivingbaseIn.bA + (entitylivingbaseIn.bD - entitylivingbaseIn.bA) * partialTicks - (entitylivingbaseIn.n + (entitylivingbaseIn.q - entitylivingbaseIn.n) * partialTicks);
        double d2 = entitylivingbaseIn.bB + (entitylivingbaseIn.bE - entitylivingbaseIn.bB) * partialTicks - (entitylivingbaseIn.o + (entitylivingbaseIn.r - entitylivingbaseIn.o) * partialTicks);
        float f = entitylivingbaseIn.aN + (entitylivingbaseIn.aM - entitylivingbaseIn.aN) * partialTicks;
        double d3 = on.a(f * 0.017453292F);
        double d4 = -on.b(f * 0.017453292F);
        float f1 = (float)d1 * 10.0F;
        f1 = on.a(f1, -6.0F, 32.0F);
        float f2 = (float)(d0 * d3 + d2 * d4) * 100.0F;
        float f3 = (float)(d0 * d4 - d2 * d3) * 100.0F;
        if (f2 < 0.0F) {
          f2 = 0.0F;
        }
        if (f2 > 165.0F) {
          f2 = 165.0F;
        }
        float f4 = entitylivingbaseIn.bw + (entitylivingbaseIn.bx - entitylivingbaseIn.bw) * partialTicks;
        f1 += on.a((entitylivingbaseIn.I + (entitylivingbaseIn.J - entitylivingbaseIn.I) * partialTicks) * 6.0F) * 32.0F * f4;
        if (entitylivingbaseIn.aK())
        {
          f1 += 25.0F;
          
          bni.c(0.0F, 0.142F, -0.0178F);
        }
        bni.b(6.0F + f2 / 2.0F + f1, 1.0F, 0.0F, 0.0F);
        bni.b(f3 / 2.0F, 0.0F, 0.0F, 1.0F);
        bni.b(-f3 / 2.0F, 0.0F, 1.0F, 0.0F);
        bni.b(180.0F, 0.0F, 1.0F, 0.0F);
        this.a.h().b(0.0625F);
        bni.H();
      }
    }
  }
  
  public boolean a()
  {
    return false;
  }
}
