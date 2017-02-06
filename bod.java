public class bod
{
  private final bco a;
  private bvh[] b = new bvh[2];
  private bvh[] c = new bvh[2];
  private bvh d;
  
  public bod(bco blockColorsIn)
  {
    this.a = blockColorsIn;
    a();
  }
  
  protected void a()
  {
    bvg texturemap = bcf.z().R();
    this.b[0] = texturemap.a("minecraft:blocks/lava_still");
    this.b[1] = texturemap.a("minecraft:blocks/lava_flow");
    this.c[0] = texturemap.a("minecraft:blocks/water_still");
    this.c[1] = texturemap.a("minecraft:blocks/water_flow");
    this.d = texturemap.a("minecraft:blocks/water_overlay");
  }
  
  public boolean a(ahx blockAccess, arc blockStateIn, cj blockPosIn, bmz worldRendererIn)
  {
    amo blockliquid = (amo)blockStateIn.t();
    boolean flag = blockStateIn.a() == axe.i;
    bvh[] atextureatlassprite = flag ? this.b : this.c;
    
    RenderEnv renderEnv = RenderEnv.getInstance(blockAccess, blockStateIn, blockPosIn);
    
    int i = CustomColors.getFluidColor(blockAccess, blockStateIn, blockPosIn, renderEnv);
    
    float f = (i >> 16 & 0xFF) / 255.0F;
    float f1 = (i >> 8 & 0xFF) / 255.0F;
    float f2 = (i & 0xFF) / 255.0F;
    boolean flag1 = blockStateIn.c(blockAccess, blockPosIn, cq.b);
    boolean flag2 = blockStateIn.c(blockAccess, blockPosIn, cq.a);
    
    boolean[] aboolean = renderEnv.getBorderFlags();
    aboolean[0] = blockStateIn.c(blockAccess, blockPosIn, cq.c);
    aboolean[1] = blockStateIn.c(blockAccess, blockPosIn, cq.d);
    aboolean[2] = blockStateIn.c(blockAccess, blockPosIn, cq.e);
    aboolean[3] = blockStateIn.c(blockAccess, blockPosIn, cq.f);
    if ((!flag1) && (!flag2) && (aboolean[0] == 0) && (aboolean[1] == 0) && (aboolean[2] == 0) && (aboolean[3] == 0)) {
      return false;
    }
    boolean flag3 = false;
    float f3 = 0.5F;
    float f4 = 1.0F;
    float f5 = 0.8F;
    float f6 = 0.6F;
    axe material = blockStateIn.a();
    float f7 = a(blockAccess, blockPosIn, material);
    float f8 = a(blockAccess, blockPosIn.d(), material);
    float f9 = a(blockAccess, blockPosIn.f().d(), material);
    float f10 = a(blockAccess, blockPosIn.f(), material);
    double d0 = blockPosIn.p();
    double d1 = blockPosIn.q();
    double d2 = blockPosIn.r();
    float f11 = 0.001F;
    if (flag1)
    {
      flag3 = true;
      float f12 = amo.a(blockAccess, blockPosIn, material);
      bvh textureatlassprite = f12 > -999.0F ? atextureatlassprite[1] : atextureatlassprite[0];
      
      worldRendererIn.setSprite(textureatlassprite);
      
      f7 -= f11;
      f8 -= f11;
      f9 -= f11;
      f10 -= f11;
      float f20;
      float f13;
      float f17;
      float f14;
      float f18;
      float f15;
      float f19;
      float f16;
      float f20;
      if (f12 < -999.0F)
      {
        float f13 = textureatlassprite.a(0.0D);
        float f17 = textureatlassprite.b(0.0D);
        float f14 = f13;
        float f18 = textureatlassprite.b(16.0D);
        float f15 = textureatlassprite.a(16.0D);
        float f19 = f18;
        float f16 = f15;
        f20 = f17;
      }
      else
      {
        float f21 = on.a(f12) * 0.25F;
        float f22 = on.b(f12) * 0.25F;
        float f23 = 8.0F;
        f13 = textureatlassprite.a(8.0F + (-f22 - f21) * 16.0F);
        f17 = textureatlassprite.b(8.0F + (-f22 + f21) * 16.0F);
        f14 = textureatlassprite.a(8.0F + (-f22 + f21) * 16.0F);
        f18 = textureatlassprite.b(8.0F + (f22 + f21) * 16.0F);
        f15 = textureatlassprite.a(8.0F + (f22 + f21) * 16.0F);
        f19 = textureatlassprite.b(8.0F + (f22 - f21) * 16.0F);
        f16 = textureatlassprite.a(8.0F + (f22 - f21) * 16.0F);
        f20 = textureatlassprite.b(8.0F + (-f22 - f21) * 16.0F);
      }
      int k2 = blockStateIn.a(blockAccess, blockPosIn);
      int l2 = k2 >> 16 & 0xFFFF;
      int i3 = k2 & 0xFFFF;
      float f24 = f4 * f;
      float f25 = f4 * f1;
      float f26 = f4 * f2;
      worldRendererIn.b(d0 + 0.0D, d1 + f7, d2 + 0.0D).a(f24, f25, f26, 1.0F).a(f13, f17).a(l2, i3).d();
      worldRendererIn.b(d0 + 0.0D, d1 + f8, d2 + 1.0D).a(f24, f25, f26, 1.0F).a(f14, f18).a(l2, i3).d();
      worldRendererIn.b(d0 + 1.0D, d1 + f9, d2 + 1.0D).a(f24, f25, f26, 1.0F).a(f15, f19).a(l2, i3).d();
      worldRendererIn.b(d0 + 1.0D, d1 + f10, d2 + 0.0D).a(f24, f25, f26, 1.0F).a(f16, f20).a(l2, i3).d();
      if (blockliquid.e(blockAccess, blockPosIn.a()))
      {
        worldRendererIn.b(d0 + 0.0D, d1 + f7, d2 + 0.0D).a(f24, f25, f26, 1.0F).a(f13, f17).a(l2, i3).d();
        worldRendererIn.b(d0 + 1.0D, d1 + f10, d2 + 0.0D).a(f24, f25, f26, 1.0F).a(f16, f20).a(l2, i3).d();
        worldRendererIn.b(d0 + 1.0D, d1 + f9, d2 + 1.0D).a(f24, f25, f26, 1.0F).a(f15, f19).a(l2, i3).d();
        worldRendererIn.b(d0 + 0.0D, d1 + f8, d2 + 1.0D).a(f24, f25, f26, 1.0F).a(f14, f18).a(l2, i3).d();
      }
    }
    if (flag2)
    {
      float f35 = atextureatlassprite[0].e();
      float f36 = atextureatlassprite[0].f();
      float f37 = atextureatlassprite[0].g();
      float f38 = atextureatlassprite[0].h();
      int l1 = blockStateIn.a(blockAccess, blockPosIn.b());
      int i2 = l1 >> 16 & 0xFFFF;
      int j2 = l1 & 0xFFFF;
      worldRendererIn.b(d0, d1, d2 + 1.0D).a(f3, f3, f3, 1.0F).a(f35, f38).a(i2, j2).d();
      worldRendererIn.b(d0, d1, d2).a(f3, f3, f3, 1.0F).a(f35, f37).a(i2, j2).d();
      worldRendererIn.b(d0 + 1.0D, d1, d2).a(f3, f3, f3, 1.0F).a(f36, f37).a(i2, j2).d();
      worldRendererIn.b(d0 + 1.0D, d1, d2 + 1.0D).a(f3, f3, f3, 1.0F).a(f36, f38).a(i2, j2).d();
      flag3 = true;
    }
    for (int i1 = 0; i1 < 4; i1++)
    {
      int j1 = 0;
      int k1 = 0;
      if (i1 == 0) {
        k1--;
      }
      if (i1 == 1) {
        k1++;
      }
      if (i1 == 2) {
        j1--;
      }
      if (i1 == 3) {
        j1++;
      }
      cj blockpos = blockPosIn.a(j1, 0, k1);
      bvh textureatlassprite1 = atextureatlassprite[1];
      
      worldRendererIn.setSprite(textureatlassprite1);
      if (!flag)
      {
        ajt block = blockAccess.o(blockpos).t();
        if ((block == aju.w) || (block == aju.cG)) {
          textureatlassprite1 = this.d;
        }
      }
      if (aboolean[i1] != 0)
      {
        double d6;
        float f39;
        float f40;
        double d3;
        double d5;
        double d4;
        double d6;
        if (i1 == 0)
        {
          float f39 = f7;
          float f40 = f10;
          double d3 = d0;
          double d5 = d0 + 1.0D;
          double d4 = d2 + f11;
          d6 = d2 + f11;
        }
        else
        {
          double d6;
          if (i1 == 1)
          {
            float f39 = f9;
            float f40 = f8;
            double d3 = d0 + 1.0D;
            double d5 = d0;
            double d4 = d2 + 1.0D - f11;
            d6 = d2 + 1.0D - f11;
          }
          else
          {
            double d6;
            if (i1 == 2)
            {
              float f39 = f8;
              float f40 = f7;
              double d3 = d0 + f11;
              double d5 = d0 + f11;
              double d4 = d2 + 1.0D;
              d6 = d2;
            }
            else
            {
              f39 = f10;
              f40 = f9;
              d3 = d0 + 1.0D - f11;
              d5 = d0 + 1.0D - f11;
              d4 = d2;
              d6 = d2 + 1.0D;
            }
          }
        }
        flag3 = true;
        float f41 = textureatlassprite1.a(0.0D);
        float f27 = textureatlassprite1.a(8.0D);
        float f28 = textureatlassprite1.b((1.0F - f39) * 16.0F * 0.5F);
        float f29 = textureatlassprite1.b((1.0F - f40) * 16.0F * 0.5F);
        float f30 = textureatlassprite1.b(8.0D);
        int j = blockStateIn.a(blockAccess, blockpos);
        int k = j >> 16 & 0xFFFF;
        int l = j & 0xFFFF;
        float f31 = i1 < 2 ? f5 : f6;
        float f32 = f4 * f31 * f;
        float f33 = f4 * f31 * f1;
        float f34 = f4 * f31 * f2;
        worldRendererIn.b(d3, d1 + f39, d4).a(f32, f33, f34, 1.0F).a(f41, f28).a(k, l).d();
        worldRendererIn.b(d5, d1 + f40, d6).a(f32, f33, f34, 1.0F).a(f27, f29).a(k, l).d();
        worldRendererIn.b(d5, d1 + 0.0D, d6).a(f32, f33, f34, 1.0F).a(f27, f30).a(k, l).d();
        worldRendererIn.b(d3, d1 + 0.0D, d4).a(f32, f33, f34, 1.0F).a(f41, f30).a(k, l).d();
        if (textureatlassprite1 != this.d)
        {
          worldRendererIn.b(d3, d1 + 0.0D, d4).a(f32, f33, f34, 1.0F).a(f41, f30).a(k, l).d();
          worldRendererIn.b(d5, d1 + 0.0D, d6).a(f32, f33, f34, 1.0F).a(f27, f30).a(k, l).d();
          worldRendererIn.b(d5, d1 + f40, d6).a(f32, f33, f34, 1.0F).a(f27, f29).a(k, l).d();
          worldRendererIn.b(d3, d1 + f39, d4).a(f32, f33, f34, 1.0F).a(f41, f28).a(k, l).d();
        }
      }
    }
    worldRendererIn.setSprite(null);
    
    return flag3;
  }
  
  private float a(ahx blockAccess, cj blockPosIn, axe blockMaterial)
  {
    int i = 0;
    float f = 0.0F;
    for (int j = 0; j < 4; j++)
    {
      cj blockpos = blockPosIn.a(-(j & 0x1), 0, -(j >> 1 & 0x1));
      if (blockAccess.o(blockpos.a()).a() == blockMaterial) {
        return 1.0F;
      }
      arc iblockstate = blockAccess.o(blockpos);
      axe material = iblockstate.a();
      if (material != blockMaterial)
      {
        if (!material.a())
        {
          f += 1.0F;
          i++;
        }
      }
      else
      {
        int k = ((Integer)iblockstate.c(amo.b)).intValue();
        if ((k >= 8) || (k == 0))
        {
          f += amo.e(k) * 10.0F;
          i += 10;
        }
        f += amo.e(k);
        i++;
      }
    }
    return 1.0F - f / i;
  }
}
