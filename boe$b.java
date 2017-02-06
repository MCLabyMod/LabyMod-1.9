import java.util.BitSet;

public class boe$b
{
  private final float[] b = new float[4];
  private final int[] c = new int[4];
  
  public void a(ahx worldIn, arc state, cj centerPos, cq direction, float[] faceShape, BitSet shapeState)
  {
    cj blockpos = shapeState.get(0) ? centerPos.a(direction) : centerPos;
    cj.b blockpos$pooledmutableblockpos = cj.b.s();
    boe.a blockmodelrenderer$enumneighborinfo = boe.a.a(direction);
    cj.b blockpos$pooledmutableblockpos1 = cj.b.g(blockpos).c(blockmodelrenderer$enumneighborinfo.g[0]);
    cj.b blockpos$pooledmutableblockpos2 = cj.b.g(blockpos).c(blockmodelrenderer$enumneighborinfo.g[1]);
    cj.b blockpos$pooledmutableblockpos3 = cj.b.g(blockpos).c(blockmodelrenderer$enumneighborinfo.g[2]);
    cj.b blockpos$pooledmutableblockpos4 = cj.b.g(blockpos).c(blockmodelrenderer$enumneighborinfo.g[3]);
    int i = state.a(worldIn, blockpos$pooledmutableblockpos1);
    int j = state.a(worldIn, blockpos$pooledmutableblockpos2);
    int k = state.a(worldIn, blockpos$pooledmutableblockpos3);
    int l = state.a(worldIn, blockpos$pooledmutableblockpos4);
    
    float f = boe.fixAoLightValue(worldIn.o(blockpos$pooledmutableblockpos1).j());
    float f1 = boe.fixAoLightValue(worldIn.o(blockpos$pooledmutableblockpos2).j());
    float f2 = boe.fixAoLightValue(worldIn.o(blockpos$pooledmutableblockpos3).j());
    float f3 = boe.fixAoLightValue(worldIn.o(blockpos$pooledmutableblockpos4).j());
    
    boolean flag = worldIn.o(blockpos$pooledmutableblockpos.h(blockpos$pooledmutableblockpos1).c(direction)).e();
    boolean flag1 = worldIn.o(blockpos$pooledmutableblockpos.h(blockpos$pooledmutableblockpos2).c(direction)).e();
    boolean flag2 = worldIn.o(blockpos$pooledmutableblockpos.h(blockpos$pooledmutableblockpos3).c(direction)).e();
    boolean flag3 = worldIn.o(blockpos$pooledmutableblockpos.h(blockpos$pooledmutableblockpos4).c(direction)).e();
    int i1;
    float f4;
    int i1;
    if ((!flag2) && (!flag))
    {
      float f4 = f;
      i1 = i;
    }
    else
    {
      cj blockpos1 = blockpos$pooledmutableblockpos.h(blockpos$pooledmutableblockpos1).c(blockmodelrenderer$enumneighborinfo.g[2]);
      
      f4 = boe.fixAoLightValue(worldIn.o(blockpos1).j());
      i1 = state.a(worldIn, blockpos1);
    }
    int j1;
    float f5;
    int j1;
    if ((!flag3) && (!flag))
    {
      float f5 = f;
      j1 = i;
    }
    else
    {
      cj blockpos2 = blockpos$pooledmutableblockpos.h(blockpos$pooledmutableblockpos1).c(blockmodelrenderer$enumneighborinfo.g[3]);
      
      f5 = boe.fixAoLightValue(worldIn.o(blockpos2).j());
      j1 = state.a(worldIn, blockpos2);
    }
    int k1;
    float f6;
    int k1;
    if ((!flag2) && (!flag1))
    {
      float f6 = f1;
      k1 = j;
    }
    else
    {
      cj blockpos3 = blockpos$pooledmutableblockpos.h(blockpos$pooledmutableblockpos2).c(blockmodelrenderer$enumneighborinfo.g[2]);
      
      f6 = boe.fixAoLightValue(worldIn.o(blockpos3).j());
      k1 = state.a(worldIn, blockpos3);
    }
    int l1;
    float f7;
    int l1;
    if ((!flag3) && (!flag1))
    {
      float f7 = f1;
      l1 = j;
    }
    else
    {
      cj blockpos4 = blockpos$pooledmutableblockpos.h(blockpos$pooledmutableblockpos2).c(blockmodelrenderer$enumneighborinfo.g[3]);
      
      f7 = boe.fixAoLightValue(worldIn.o(blockpos4).j());
      l1 = state.a(worldIn, blockpos4);
    }
    int i3 = state.a(worldIn, centerPos);
    if ((shapeState.get(0)) || (!worldIn.o(centerPos.a(direction)).p())) {
      i3 = state.a(worldIn, centerPos.a(direction));
    }
    float f8 = shapeState.get(0) ? worldIn.o(blockpos).j() : worldIn.o(centerPos).j();
    
    f8 = boe.fixAoLightValue(f8);
    
    boe.c blockmodelrenderer$vertextranslations = boe.c.a(direction);
    blockpos$pooledmutableblockpos.t();
    blockpos$pooledmutableblockpos1.t();
    blockpos$pooledmutableblockpos2.t();
    blockpos$pooledmutableblockpos3.t();
    blockpos$pooledmutableblockpos4.t();
    if ((shapeState.get(1)) && (blockmodelrenderer$enumneighborinfo.i))
    {
      float f29 = (f3 + f + f5 + f8) * 0.25F;
      float f30 = (f2 + f + f4 + f8) * 0.25F;
      float f31 = (f2 + f1 + f6 + f8) * 0.25F;
      float f32 = (f3 + f1 + f7 + f8) * 0.25F;
      float f13 = faceShape[blockmodelrenderer$enumneighborinfo.j[0].m] * faceShape[blockmodelrenderer$enumneighborinfo.j[1].m];
      float f14 = faceShape[blockmodelrenderer$enumneighborinfo.j[2].m] * faceShape[blockmodelrenderer$enumneighborinfo.j[3].m];
      float f15 = faceShape[blockmodelrenderer$enumneighborinfo.j[4].m] * faceShape[blockmodelrenderer$enumneighborinfo.j[5].m];
      float f16 = faceShape[blockmodelrenderer$enumneighborinfo.j[6].m] * faceShape[blockmodelrenderer$enumneighborinfo.j[7].m];
      float f17 = faceShape[blockmodelrenderer$enumneighborinfo.k[0].m] * faceShape[blockmodelrenderer$enumneighborinfo.k[1].m];
      float f18 = faceShape[blockmodelrenderer$enumneighborinfo.k[2].m] * faceShape[blockmodelrenderer$enumneighborinfo.k[3].m];
      float f19 = faceShape[blockmodelrenderer$enumneighborinfo.k[4].m] * faceShape[blockmodelrenderer$enumneighborinfo.k[5].m];
      float f20 = faceShape[blockmodelrenderer$enumneighborinfo.k[6].m] * faceShape[blockmodelrenderer$enumneighborinfo.k[7].m];
      float f21 = faceShape[blockmodelrenderer$enumneighborinfo.l[0].m] * faceShape[blockmodelrenderer$enumneighborinfo.l[1].m];
      float f22 = faceShape[blockmodelrenderer$enumneighborinfo.l[2].m] * faceShape[blockmodelrenderer$enumneighborinfo.l[3].m];
      float f23 = faceShape[blockmodelrenderer$enumneighborinfo.l[4].m] * faceShape[blockmodelrenderer$enumneighborinfo.l[5].m];
      float f24 = faceShape[blockmodelrenderer$enumneighborinfo.l[6].m] * faceShape[blockmodelrenderer$enumneighborinfo.l[7].m];
      float f25 = faceShape[blockmodelrenderer$enumneighborinfo.m[0].m] * faceShape[blockmodelrenderer$enumneighborinfo.m[1].m];
      float f26 = faceShape[blockmodelrenderer$enumneighborinfo.m[2].m] * faceShape[blockmodelrenderer$enumneighborinfo.m[3].m];
      float f27 = faceShape[blockmodelrenderer$enumneighborinfo.m[4].m] * faceShape[blockmodelrenderer$enumneighborinfo.m[5].m];
      float f28 = faceShape[blockmodelrenderer$enumneighborinfo.m[6].m] * faceShape[blockmodelrenderer$enumneighborinfo.m[7].m];
      this.b[boe.c.a(blockmodelrenderer$vertextranslations)] = (f29 * f13 + f30 * f14 + f31 * f15 + f32 * f16);
      this.b[boe.c.b(blockmodelrenderer$vertextranslations)] = (f29 * f17 + f30 * f18 + f31 * f19 + f32 * f20);
      this.b[boe.c.c(blockmodelrenderer$vertextranslations)] = (f29 * f21 + f30 * f22 + f31 * f23 + f32 * f24);
      this.b[boe.c.d(blockmodelrenderer$vertextranslations)] = (f29 * f25 + f30 * f26 + f31 * f27 + f32 * f28);
      int i2 = a(l, i, j1, i3);
      int j2 = a(k, i, i1, i3);
      int k2 = a(k, j, k1, i3);
      int l2 = a(l, j, l1, i3);
      this.c[boe.c.a(blockmodelrenderer$vertextranslations)] = a(i2, j2, k2, l2, f13, f14, f15, f16);
      this.c[boe.c.b(blockmodelrenderer$vertextranslations)] = a(i2, j2, k2, l2, f17, f18, f19, f20);
      this.c[boe.c.c(blockmodelrenderer$vertextranslations)] = a(i2, j2, k2, l2, f21, f22, f23, f24);
      this.c[boe.c.d(blockmodelrenderer$vertextranslations)] = a(i2, j2, k2, l2, f25, f26, f27, f28);
    }
    else
    {
      float f9 = (f3 + f + f5 + f8) * 0.25F;
      float f10 = (f2 + f + f4 + f8) * 0.25F;
      float f11 = (f2 + f1 + f6 + f8) * 0.25F;
      float f12 = (f3 + f1 + f7 + f8) * 0.25F;
      this.c[boe.c.a(blockmodelrenderer$vertextranslations)] = a(l, i, j1, i3);
      this.c[boe.c.b(blockmodelrenderer$vertextranslations)] = a(k, i, i1, i3);
      this.c[boe.c.c(blockmodelrenderer$vertextranslations)] = a(k, j, k1, i3);
      this.c[boe.c.d(blockmodelrenderer$vertextranslations)] = a(l, j, l1, i3);
      this.b[boe.c.a(blockmodelrenderer$vertextranslations)] = f9;
      this.b[boe.c.b(blockmodelrenderer$vertextranslations)] = f10;
      this.b[boe.c.c(blockmodelrenderer$vertextranslations)] = f11;
      this.b[boe.c.d(blockmodelrenderer$vertextranslations)] = f12;
    }
  }
  
  private int a(int br1, int br2, int br3, int br4)
  {
    if (br1 == 0) {
      br1 = br4;
    }
    if (br2 == 0) {
      br2 = br4;
    }
    if (br3 == 0) {
      br3 = br4;
    }
    return br1 + br2 + br3 + br4 >> 2 & 0xFF00FF;
  }
  
  private int a(int p_178203_1_, int p_178203_2_, int p_178203_3_, int p_178203_4_, float p_178203_5_, float p_178203_6_, float p_178203_7_, float p_178203_8_)
  {
    int i = (int)((p_178203_1_ >> 16 & 0xFF) * p_178203_5_ + (p_178203_2_ >> 16 & 0xFF) * p_178203_6_ + (p_178203_3_ >> 16 & 0xFF) * p_178203_7_ + (p_178203_4_ >> 16 & 0xFF) * p_178203_8_) & 0xFF;
    int j = (int)((p_178203_1_ & 0xFF) * p_178203_5_ + (p_178203_2_ & 0xFF) * p_178203_6_ + (p_178203_3_ & 0xFF) * p_178203_7_ + (p_178203_4_ & 0xFF) * p_178203_8_) & 0xFF;
    return i << 16 | j;
  }
}
