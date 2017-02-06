import java.util.BitSet;
import java.util.List;

public class boe
{
  private final bco a;
  private static float aoLightValueOpaque = 0.2F;
  
  public static void updateAoLightValue()
  {
    aoLightValueOpaque = 1.0F - Config.getAmbientOcclusionLevel() * 0.8F;
  }
  
  public boe(bco blockColorsIn)
  {
    this.a = blockColorsIn;
  }
  
  public boolean a(ahx blockAccessIn, bxo modelIn, arc blockStateIn, cj blockPosIn, bmz buffer, boolean checkSides)
  {
    return a(blockAccessIn, modelIn, blockStateIn, blockPosIn, buffer, checkSides, on.a(blockPosIn));
  }
  
  public boolean a(ahx worldIn, bxo modelIn, arc stateIn, cj posIn, bmz buffer, boolean checkSides, long rand)
  {
    boolean flag = (bcf.y()) && (stateIn.d() == 0) && (modelIn.a());
    try
    {
      if ((Config.isTreesSmart()) && ((stateIn.t() instanceof aml))) {
        modelIn = SmartLeaves.getLeavesModel(modelIn);
      }
      return flag ? b(worldIn, modelIn, stateIn, posIn, buffer, checkSides, rand) : c(worldIn, modelIn, stateIn, posIn, buffer, checkSides, rand);
    }
    catch (Throwable throwable)
    {
      b crashreport = b.a(throwable, "Tesselating block model");
      c crashreportcategory = crashreport.a("Block model being tesselated");
      c.a(crashreportcategory, posIn, stateIn);
      crashreportcategory.a("Using AO", Boolean.valueOf(flag));
      throw new e(crashreport);
    }
  }
  
  public boolean b(ahx worldIn, bxo modelIn, arc stateIn, cj posIn, bmz buffer, boolean checkSides, long rand)
  {
    boolean flag = false;
    
    RenderEnv renderEnv = null;
    for (cq enumfacing : cq.n)
    {
      List<bof> list = modelIn.a(stateIn, enumfacing, rand);
      if ((!list.isEmpty()) && ((!checkSides) || (stateIn.c(worldIn, posIn, enumfacing))))
      {
        if (renderEnv == null) {
          renderEnv = RenderEnv.getInstance(worldIn, stateIn, posIn);
        }
        if (!renderEnv.isBreakingAnimation(list)) {
          if (Config.isBetterGrass()) {
            list = BetterGrass.getFaceQuads(worldIn, stateIn, posIn, enumfacing, list);
          }
        }
        renderQuadsSmooth(worldIn, stateIn, posIn, buffer, list, renderEnv);
        flag = true;
      }
    }
    List<bof> list1 = modelIn.a(stateIn, (cq)null, rand);
    if (!list1.isEmpty())
    {
      if (renderEnv == null) {
        renderEnv = RenderEnv.getInstance(worldIn, stateIn, posIn);
      }
      renderQuadsSmooth(worldIn, stateIn, posIn, buffer, list1, renderEnv);
      flag = true;
    }
    if ((renderEnv != null) && (Config.isBetterSnow()) && (!renderEnv.isBreakingAnimation())) {
      if (BetterSnow.shouldRender(worldIn, stateIn, posIn))
      {
        bxo modelSnow = BetterSnow.getModelSnowLayer();
        arc stateSnow = BetterSnow.getStateSnowLayer();
        b(worldIn, modelSnow, stateSnow, posIn, buffer, true, rand);
      }
    }
    return flag;
  }
  
  public boolean c(ahx worldIn, bxo modelIn, arc stateIn, cj posIn, bmz buffer, boolean checkSides, long rand)
  {
    boolean flag = false;
    
    RenderEnv renderEnv = null;
    for (cq enumfacing : cq.n)
    {
      List<bof> list = modelIn.a(stateIn, enumfacing, rand);
      if ((!list.isEmpty()) && ((!checkSides) || (stateIn.c(worldIn, posIn, enumfacing))))
      {
        if (renderEnv == null) {
          renderEnv = RenderEnv.getInstance(worldIn, stateIn, posIn);
        }
        if (!renderEnv.isBreakingAnimation(list)) {
          if (Config.isBetterGrass()) {
            list = BetterGrass.getFaceQuads(worldIn, stateIn, posIn, enumfacing, list);
          }
        }
        int i = stateIn.a(worldIn, posIn.a(enumfacing));
        
        renderQuadsFlat(worldIn, stateIn, posIn, i, false, buffer, list, renderEnv);
        flag = true;
      }
    }
    List<bof> list1 = modelIn.a(stateIn, (cq)null, rand);
    if (!list1.isEmpty())
    {
      if (renderEnv == null) {
        renderEnv = RenderEnv.getInstance(worldIn, stateIn, posIn);
      }
      renderQuadsFlat(worldIn, stateIn, posIn, -1, true, buffer, list1, renderEnv);
      flag = true;
    }
    if ((renderEnv != null) && (Config.isBetterSnow()) && (!renderEnv.isBreakingAnimation())) {
      if (BetterSnow.shouldRender(worldIn, stateIn, posIn))
      {
        bxo modelSnow = BetterSnow.getModelSnowLayer();
        arc stateSnow = BetterSnow.getStateSnowLayer();
        c(worldIn, modelSnow, stateSnow, posIn, buffer, true, rand);
      }
    }
    return flag;
  }
  
  private void renderQuadsSmooth(ahx blockAccessIn, arc stateIn, cj posIn, bmz buffer, List<bof> list, RenderEnv renderEnv)
  {
    float[] quadBounds = renderEnv.getQuadBounds();
    BitSet boundsFlags = renderEnv.getBoundsFlags();
    boe.b aoFaceIn = renderEnv.getAoFace();
    
    double d0 = posIn.p();
    double d1 = posIn.q();
    double d2 = posIn.r();
    ajt block = stateIn.t();
    ajt.a block$enumoffsettype = block.v();
    if (block$enumoffsettype != ajt.a.a)
    {
      long i = on.a(posIn);
      d0 += ((float)(i >> 16 & 0xF) / 15.0F - 0.5D) * 0.5D;
      d2 += ((float)(i >> 24 & 0xF) / 15.0F - 0.5D) * 0.5D;
      if (block$enumoffsettype == ajt.a.c) {
        d1 += ((float)(i >> 20 & 0xF) / 15.0F - 1.0D) * 0.2D;
      }
    }
    int l = 0;
    for (int j = list.size(); l < j; l++)
    {
      bof bakedquad = (bof)list.get(l);
      if (!renderEnv.isBreakingAnimation(bakedquad))
      {
        bof quad = bakedquad;
        if (Config.isConnectedTextures()) {
          bakedquad = ConnectedTextures.getConnectedTexture(blockAccessIn, stateIn, posIn, bakedquad, renderEnv);
        }
        if ((bakedquad == quad) && (Config.isNaturalTextures())) {
          bakedquad = NaturalTextures.getNaturalTexture(posIn, bakedquad);
        }
      }
      a(stateIn, bakedquad.b(), bakedquad.e(), quadBounds, boundsFlags);
      aoFaceIn.a(blockAccessIn, stateIn, posIn, bakedquad.e(), quadBounds, boundsFlags);
      if (buffer.isMultiTexture())
      {
        buffer.a(bakedquad.getVertexDataSingle());
        buffer.putSprite(bakedquad.a());
      }
      else
      {
        buffer.a(bakedquad.b());
      }
      buffer.a(boe.b.a(aoFaceIn)[0], boe.b.a(aoFaceIn)[1], boe.b.a(aoFaceIn)[2], boe.b.a(aoFaceIn)[3]);
      
      int colorMultiplier = CustomColors.getColorMultiplier(bakedquad, stateIn, blockAccessIn, posIn, renderEnv);
      if ((bakedquad.c()) || (colorMultiplier != -1))
      {
        int k;
        int k;
        if (colorMultiplier != -1) {
          k = colorMultiplier;
        } else {
          k = this.a.a(stateIn, blockAccessIn, posIn, bakedquad.d());
        }
        if (bng.a) {
          k = bvk.c(k);
        }
        float f = (k >> 16 & 0xFF) / 255.0F;
        float f1 = (k >> 8 & 0xFF) / 255.0F;
        float f2 = (k & 0xFF) / 255.0F;
        buffer.a(boe.b.b(aoFaceIn)[0] * f, boe.b.b(aoFaceIn)[0] * f1, boe.b.b(aoFaceIn)[0] * f2, 4);
        buffer.a(boe.b.b(aoFaceIn)[1] * f, boe.b.b(aoFaceIn)[1] * f1, boe.b.b(aoFaceIn)[1] * f2, 3);
        buffer.a(boe.b.b(aoFaceIn)[2] * f, boe.b.b(aoFaceIn)[2] * f1, boe.b.b(aoFaceIn)[2] * f2, 2);
        buffer.a(boe.b.b(aoFaceIn)[3] * f, boe.b.b(aoFaceIn)[3] * f1, boe.b.b(aoFaceIn)[3] * f2, 1);
      }
      else
      {
        buffer.a(boe.b.b(aoFaceIn)[0], boe.b.b(aoFaceIn)[0], boe.b.b(aoFaceIn)[0], 4);
        buffer.a(boe.b.b(aoFaceIn)[1], boe.b.b(aoFaceIn)[1], boe.b.b(aoFaceIn)[1], 3);
        buffer.a(boe.b.b(aoFaceIn)[2], boe.b.b(aoFaceIn)[2], boe.b.b(aoFaceIn)[2], 2);
        buffer.a(boe.b.b(aoFaceIn)[3], boe.b.b(aoFaceIn)[3], boe.b.b(aoFaceIn)[3], 1);
      }
      buffer.a(d0, d1, d2);
    }
  }
  
  private void a(arc stateIn, int[] vertexData, cq face, float[] quadBounds, BitSet boundsFlags)
  {
    float f = 32.0F;
    float f1 = 32.0F;
    float f2 = 32.0F;
    float f3 = -32.0F;
    float f4 = -32.0F;
    float f5 = -32.0F;
    
    int step = vertexData.length / 4;
    for (int i = 0; i < 4; i++)
    {
      float f6 = Float.intBitsToFloat(vertexData[(i * step)]);
      float f7 = Float.intBitsToFloat(vertexData[(i * step + 1)]);
      float f8 = Float.intBitsToFloat(vertexData[(i * step + 2)]);
      
      f = Math.min(f, f6);
      f1 = Math.min(f1, f7);
      f2 = Math.min(f2, f8);
      f3 = Math.max(f3, f6);
      f4 = Math.max(f4, f7);
      f5 = Math.max(f5, f8);
    }
    if (quadBounds != null)
    {
      quadBounds[cq.e.a()] = f;
      quadBounds[cq.f.a()] = f3;
      quadBounds[cq.a.a()] = f1;
      quadBounds[cq.b.a()] = f4;
      quadBounds[cq.c.a()] = f2;
      quadBounds[cq.d.a()] = f5;
      
      int facingValuesLength = cq.n.length;
      quadBounds[(cq.e.a() + facingValuesLength)] = (1.0F - f);
      quadBounds[(cq.f.a() + facingValuesLength)] = (1.0F - f3);
      quadBounds[(cq.a.a() + facingValuesLength)] = (1.0F - f1);
      quadBounds[(cq.b.a() + facingValuesLength)] = (1.0F - f4);
      quadBounds[(cq.c.a() + facingValuesLength)] = (1.0F - f2);
      quadBounds[(cq.d.a() + facingValuesLength)] = (1.0F - f5);
    }
    float f9 = 1.0E-4F;
    float f10 = 0.9999F;
    switch (face)
    {
    case a: 
      boundsFlags.set(1, (f >= 1.0E-4F) || (f2 >= 1.0E-4F) || (f3 <= 0.9999F) || (f5 <= 0.9999F));
      boundsFlags.set(0, ((f1 < 1.0E-4F) || (stateIn.h())) && (f1 == f4));
      break;
    case b: 
      boundsFlags.set(1, (f >= 1.0E-4F) || (f2 >= 1.0E-4F) || (f3 <= 0.9999F) || (f5 <= 0.9999F));
      boundsFlags.set(0, ((f4 > 0.9999F) || (stateIn.h())) && (f1 == f4));
      break;
    case c: 
      boundsFlags.set(1, (f >= 1.0E-4F) || (f1 >= 1.0E-4F) || (f3 <= 0.9999F) || (f4 <= 0.9999F));
      boundsFlags.set(0, ((f2 < 1.0E-4F) || (stateIn.h())) && (f2 == f5));
      break;
    case d: 
      boundsFlags.set(1, (f >= 1.0E-4F) || (f1 >= 1.0E-4F) || (f3 <= 0.9999F) || (f4 <= 0.9999F));
      boundsFlags.set(0, ((f5 > 0.9999F) || (stateIn.h())) && (f2 == f5));
      break;
    case e: 
      boundsFlags.set(1, (f1 >= 1.0E-4F) || (f2 >= 1.0E-4F) || (f4 <= 0.9999F) || (f5 <= 0.9999F));
      boundsFlags.set(0, ((f < 1.0E-4F) || (stateIn.h())) && (f == f3));
      break;
    case f: 
      boundsFlags.set(1, (f1 >= 1.0E-4F) || (f2 >= 1.0E-4F) || (f4 <= 0.9999F) || (f5 <= 0.9999F));
      boundsFlags.set(0, ((f3 > 0.9999F) || (stateIn.h())) && (f == f3));
    }
  }
  
  private void renderQuadsFlat(ahx blockAccessIn, arc stateIn, cj posIn, int brightnessIn, boolean ownBrightness, bmz buffer, List<bof> list, RenderEnv renderEnv)
  {
    BitSet boundsFlags = renderEnv.getBoundsFlags();
    
    double d0 = posIn.p();
    double d1 = posIn.q();
    double d2 = posIn.r();
    ajt block = stateIn.t();
    ajt.a block$enumoffsettype = block.v();
    if (block$enumoffsettype != ajt.a.a)
    {
      int i = posIn.p();
      int j = posIn.r();
      long k = i * 3129871 ^ j * 116129781L;
      k = k * k * 42317861L + k * 11L;
      d0 += ((float)(k >> 16 & 0xF) / 15.0F - 0.5D) * 0.5D;
      d2 += ((float)(k >> 24 & 0xF) / 15.0F - 0.5D) * 0.5D;
      if (block$enumoffsettype == ajt.a.c) {
        d1 += ((float)(k >> 20 & 0xF) / 15.0F - 1.0D) * 0.2D;
      }
    }
    int i1 = 0;
    for (int j1 = list.size(); i1 < j1; i1++)
    {
      bof bakedquad = (bof)list.get(i1);
      if (!renderEnv.isBreakingAnimation(bakedquad))
      {
        bof quad = bakedquad;
        if (Config.isConnectedTextures()) {
          bakedquad = ConnectedTextures.getConnectedTexture(blockAccessIn, stateIn, posIn, bakedquad, renderEnv);
        }
        if ((bakedquad == quad) && (Config.isNaturalTextures())) {
          bakedquad = NaturalTextures.getNaturalTexture(posIn, bakedquad);
        }
      }
      if (ownBrightness)
      {
        a(stateIn, bakedquad.b(), bakedquad.e(), (float[])null, boundsFlags);
        brightnessIn = boundsFlags.get(0) ? stateIn.a(blockAccessIn, posIn.a(bakedquad.e())) : stateIn.a(blockAccessIn, posIn);
      }
      if (buffer.isMultiTexture())
      {
        buffer.a(bakedquad.getVertexDataSingle());
        buffer.putSprite(bakedquad.a());
      }
      else
      {
        buffer.a(bakedquad.b());
      }
      buffer.a(brightnessIn, brightnessIn, brightnessIn, brightnessIn);
      
      int colorMultiplier = CustomColors.getColorMultiplier(bakedquad, stateIn, blockAccessIn, posIn, renderEnv);
      if ((bakedquad.c()) || (colorMultiplier != -1))
      {
        int l;
        int l;
        if (colorMultiplier != -1) {
          l = colorMultiplier;
        } else {
          l = this.a.a(stateIn, blockAccessIn, posIn, bakedquad.d());
        }
        if (bng.a) {
          l = bvk.c(l);
        }
        float f = (l >> 16 & 0xFF) / 255.0F;
        float f1 = (l >> 8 & 0xFF) / 255.0F;
        float f2 = (l & 0xFF) / 255.0F;
        buffer.a(f, f1, f2, 4);
        buffer.a(f, f1, f2, 3);
        buffer.a(f, f1, f2, 2);
        buffer.a(f, f1, f2, 1);
      }
      buffer.a(d0, d1, d2);
    }
  }
  
  public void a(bxo bakedModel, float p_178262_2_, float red, float green, float blue)
  {
    a((arc)null, bakedModel, p_178262_2_, red, green, blue);
  }
  
  public void a(arc p_187495_1_, bxo p_187495_2_, float p_187495_3_, float p_187495_4_, float p_187495_5_, float p_187495_6_)
  {
    for (cq enumfacing : cq.n) {
      a(p_187495_3_, p_187495_4_, p_187495_5_, p_187495_6_, p_187495_2_.a(p_187495_1_, enumfacing, 0L));
    }
    a(p_187495_3_, p_187495_4_, p_187495_5_, p_187495_6_, p_187495_2_.a(p_187495_1_, (cq)null, 0L));
  }
  
  public void a(bxo model, arc p_178266_2_, float brightness, boolean p_178266_4_)
  {
    ajt block = p_178266_2_.t();
    bni.b(90.0F, 0.0F, 1.0F, 0.0F);
    int i = this.a.a(p_178266_2_, (ahx)null, (cj)null, 0);
    if (bng.a) {
      i = bvk.c(i);
    }
    float f = (i >> 16 & 0xFF) / 255.0F;
    float f1 = (i >> 8 & 0xFF) / 255.0F;
    float f2 = (i & 0xFF) / 255.0F;
    if (!p_178266_4_) {
      bni.c(brightness, brightness, brightness, 1.0F);
    }
    a(p_178266_2_, model, brightness, f, f1, f2);
  }
  
  private void a(float brightness, float red, float green, float blue, List<bof> listQuads)
  {
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    int i = 0;
    for (int j = listQuads.size(); i < j; i++)
    {
      bof bakedquad = (bof)listQuads.get(i);
      vertexbuffer.a(7, bvp.b);
      vertexbuffer.a(bakedquad.b());
      if (bakedquad.c()) {
        vertexbuffer.d(red * brightness, green * brightness, blue * brightness);
      } else {
        vertexbuffer.d(brightness, brightness, brightness);
      }
      df vec3i = bakedquad.e().n();
      vertexbuffer.b(vec3i.p(), vec3i.q(), vec3i.r());
      tessellator.b();
    }
  }
  
  public static float fixAoLightValue(float val)
  {
    if (val == 0.2F) {
      return aoLightValueOpaque;
    }
    return val;
  }
  
  public static class b
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
  
  public static enum a
  {
    protected final cq[] g;
    protected final float h;
    protected final boolean i;
    protected final boe.d[] j;
    protected final boe.d[] k;
    protected final boe.d[] l;
    protected final boe.d[] m;
    private static final a[] n;
    
    private a(cq[] p_i46236_3_, float p_i46236_4_, boolean p_i46236_5_, boe.d[] p_i46236_6_, boe.d[] p_i46236_7_, boe.d[] p_i46236_8_, boe.d[] p_i46236_9_)
    {
      this.g = p_i46236_3_;
      this.h = p_i46236_4_;
      this.i = p_i46236_5_;
      this.j = p_i46236_6_;
      this.k = p_i46236_7_;
      this.l = p_i46236_8_;
      this.m = p_i46236_9_;
    }
    
    public static a a(cq p_178273_0_)
    {
      return n[p_178273_0_.a()];
    }
    
    static
    {
      n = new a[6];
      
      n[cq.a.a()] = a;
      n[cq.b.a()] = b;
      n[cq.c.a()] = c;
      n[cq.d.a()] = d;
      n[cq.e.a()] = e;
      n[cq.f.a()] = f;
    }
  }
  
  public static enum d
  {
    protected final int m;
    
    private d(cq p_i46233_3_, boolean p_i46233_4_)
    {
      this.m = (p_i46233_3_.a() + (p_i46233_4_ ? cq.values().length : 0));
    }
  }
  
  static enum c
  {
    private final int g;
    private final int h;
    private final int i;
    private final int j;
    private static final c[] k;
    
    private c(int p_i46234_3_, int p_i46234_4_, int p_i46234_5_, int p_i46234_6_)
    {
      this.g = p_i46234_3_;
      this.h = p_i46234_4_;
      this.i = p_i46234_5_;
      this.j = p_i46234_6_;
    }
    
    public static c a(cq p_178184_0_)
    {
      return k[p_178184_0_.a()];
    }
    
    static
    {
      k = new c[6];
      
      k[cq.a.a()] = a;
      k[cq.b.a()] = b;
      k[cq.c.a()] = c;
      k[cq.d.a()] = d;
      k[cq.e.a()] = e;
      k[cq.f.a()] = f;
    }
  }
}
