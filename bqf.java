import com.google.common.collect.Sets;
import java.nio.FloatBuffer;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import shadersmod.client.SVertexBuilder;

public class bqf
{
  private aht d;
  private final bno e;
  public static int a;
  private cj f;
  public bqc b = bqc.a;
  private final ReentrantLock g = new ReentrantLock();
  private final ReentrantLock h = new ReentrantLock();
  private bpz i = null;
  private final Set<apv> j = Sets.newHashSet();
  private final int k;
  private final FloatBuffer l = bce.h(16);
  private final bvq[] m = new bvq[ahm.values().length];
  public bbh c;
  private int n = -1;
  private boolean o = true;
  private EnumMap<cq, cj> p = null;
  private boolean q = false;
  private cj[] positionOffsets16 = new cj[cq.n.length];
  private static ahm[] ENUM_WORLD_BLOCK_LAYERS = ;
  private ahm[] blockLayersSingle = new ahm[1];
  private boolean isMipmaps = Config.isMipmaps();
  private boolean fixBlockLayer = !Reflector.BetterFoliageClient.exists();
  
  public bqf(aht worldIn, bno renderGlobalIn, cj blockPosIn, int indexIn)
  {
    this.d = worldIn;
    this.e = renderGlobalIn;
    this.k = indexIn;
    if (!blockPosIn.equals(k())) {
      a(blockPosIn);
    }
    if (bzg.f()) {
      for (int i = 0; i < ahm.values().length; i++) {
        this.m[i] = new bvq(bvp.a);
      }
    }
  }
  
  public boolean a(int frameIndexIn)
  {
    if (this.n == frameIndexIn) {
      return false;
    }
    this.n = frameIndexIn;
    return true;
  }
  
  public bvq b(int layer)
  {
    return this.m[layer];
  }
  
  public void a(cj pos)
  {
    i();
    this.f = pos;
    this.c = new bbh(pos, pos.a(16, 16, 16));
    
    q();
    for (int i = 0; i < this.positionOffsets16.length; i++) {
      this.positionOffsets16[i] = null;
    }
  }
  
  public void a(float x, float y, float z, bpz generator)
  {
    bqc compiledchunk = generator.c();
    if ((compiledchunk.c() != null) && (!compiledchunk.b(ahm.d)))
    {
      bmz bufferTranslucent = generator.d().a(ahm.d);
      
      a(bufferTranslucent, this.f);
      
      bufferTranslucent.a(compiledchunk.c());
      
      a(ahm.d, x, y, z, bufferTranslucent, compiledchunk);
    }
  }
  
  public void b(float x, float y, float z, bpz generator)
  {
    bqc compiledchunk = new bqc();
    int i = 1;
    cj blockpos = this.f;
    cj blockpos1 = blockpos.a(15, 15, 15);
    generator.f().lock();
    ahx iblockaccess;
    try
    {
      if (generator.a() != bpz.a.b) {
        return;
      }
      if (this.d == null) {
        return;
      }
      iblockaccess = createRegionRenderCache(this.d, blockpos.a(-1, -1, -1), blockpos1.a(1, 1, 1), 1);
      if (Reflector.MinecraftForgeClient_onRebuildChunk.exists()) {
        Reflector.call(Reflector.MinecraftForgeClient_onRebuildChunk, new Object[] { this.d, this.f, iblockaccess });
      }
      generator.a(compiledchunk);
    }
    finally
    {
      generator.f().unlock();
    }
    bqi lvt_10_1_ = new bqi();
    HashSet lvt_11_1_ = Sets.newHashSet();
    if (!iblockaccess.aa())
    {
      a += 1;
      
      boolean[] aboolean = new boolean[ENUM_WORLD_BLOCK_LAYERS.length];
      boc blockrendererdispatcher = bcf.z().ab();
      
      boolean forgeHasTileEntityExists = Reflector.ForgeBlock_hasTileEntity.exists();
      boolean forgeBlockCanRenderInLayerExists = Reflector.ForgeBlock_canRenderInLayer.exists();
      boolean forgeHooksSetRenderLayerExists = Reflector.ForgeHooksClient_setRenderLayer.exists();
      
      Iterator iterBlocks = BlockPosM.getAllInBoxMutable(blockpos, blockpos1).iterator();
      while (iterBlocks.hasNext())
      {
        BlockPosM blockpos$mutableblockpos = (BlockPosM)iterBlocks.next();
        
        arc iblockstate = iblockaccess.o(blockpos$mutableblockpos);
        ajt block = iblockstate.t();
        if (iblockstate.p()) {
          lvt_10_1_.a(blockpos$mutableblockpos);
        }
        boolean hasTileEntity;
        boolean hasTileEntity;
        if (forgeHasTileEntityExists) {
          hasTileEntity = Reflector.callBoolean(block, Reflector.ForgeBlock_hasTileEntity, new Object[] { iblockstate });
        } else {
          hasTileEntity = block.m();
        }
        if (hasTileEntity)
        {
          apv tileentity = iblockaccess.r(new cj(blockpos$mutableblockpos));
          bpn<apv> tileentityspecialrenderer = bpm.a.a(tileentity);
          if ((tileentity != null) && (tileentityspecialrenderer != null))
          {
            compiledchunk.a(tileentity);
            if (tileentityspecialrenderer.a(tileentity)) {
              lvt_11_1_.add(tileentity);
            }
          }
        }
        ahm[] blockLayers;
        ahm[] blockLayers;
        if (forgeBlockCanRenderInLayerExists)
        {
          blockLayers = ENUM_WORLD_BLOCK_LAYERS;
        }
        else
        {
          blockLayers = this.blockLayersSingle;
          blockLayers[0] = block.f();
        }
        for (int ix = 0; ix < blockLayers.length; ix++)
        {
          ahm blockrenderlayer1 = blockLayers[ix];
          if (forgeBlockCanRenderInLayerExists)
          {
            boolean canRenderInLayer = Reflector.callBoolean(block, Reflector.ForgeBlock_canRenderInLayer, new Object[] { blockrenderlayer1 });
            if (!canRenderInLayer) {}
          }
          else
          {
            if (forgeHooksSetRenderLayerExists) {
              Reflector.callVoid(Reflector.ForgeHooksClient_setRenderLayer, new Object[] { blockrenderlayer1 });
            }
            if (this.fixBlockLayer) {
              blockrenderlayer1 = fixBlockLayer(block, blockrenderlayer1);
            }
            int j = blockrenderlayer1.ordinal();
            if (block.u().i() != aob.a)
            {
              bmz vertexbuffer = generator.d().a(j);
              
              vertexbuffer.setBlockLayer(blockrenderlayer1);
              if (!compiledchunk.d(blockrenderlayer1))
              {
                compiledchunk.c(blockrenderlayer1);
                a(vertexbuffer, blockpos);
              }
              aboolean[j] |= blockrendererdispatcher.a(iblockstate, blockpos$mutableblockpos, iblockaccess, vertexbuffer);
            }
          }
        }
      }
      for (ahm blockrenderlayer : ENUM_WORLD_BLOCK_LAYERS)
      {
        if (aboolean[blockrenderlayer.ordinal()] != 0) {
          compiledchunk.a(blockrenderlayer);
        }
        if (compiledchunk.d(blockrenderlayer))
        {
          if (Config.isShaders()) {
            SVertexBuilder.calcNormalChunkLayer(generator.d().a(blockrenderlayer));
          }
          a(blockrenderlayer, x, y, z, generator.d().a(blockrenderlayer), compiledchunk);
        }
      }
    }
    compiledchunk.a(lvt_10_1_.a());
    this.g.lock();
    try
    {
      Set<apv> set = Sets.newHashSet(lvt_11_1_);
      Set<apv> set1 = Sets.newHashSet(this.j);
      set.removeAll(this.j);
      set1.removeAll(lvt_11_1_);
      this.j.clear();
      this.j.addAll(lvt_11_1_);
      this.e.a(set1, set);
    }
    finally
    {
      this.g.unlock();
    }
  }
  
  protected void b()
  {
    this.g.lock();
    try
    {
      if ((this.i != null) && (this.i.a() != bpz.a.d))
      {
        this.i.e();
        this.i = null;
      }
    }
    finally
    {
      this.g.unlock();
    }
  }
  
  public ReentrantLock c()
  {
    return this.g;
  }
  
  public bpz d()
  {
    this.g.lock();
    bpz chunkcompiletaskgenerator;
    try
    {
      b();
      this.i = new bpz(this, bpz.b.a, f());
      chunkcompiletaskgenerator = this.i;
    }
    finally
    {
      this.g.unlock();
    }
    return chunkcompiletaskgenerator;
  }
  
  public bpz e()
  {
    this.g.lock();
    bpz chunkcompiletaskgenerator;
    try
    {
      if ((this.i == null) || (this.i.a() != bpz.a.a))
      {
        if ((this.i != null) && (this.i.a() != bpz.a.d))
        {
          this.i.e();
          this.i = null;
        }
        this.i = new bpz(this, bpz.b.b, f());
        this.i.a(this.b);
        bpz chunkcompiletaskgenerator = this.i;
        return chunkcompiletaskgenerator;
      }
      chunkcompiletaskgenerator = null;
    }
    finally
    {
      this.g.unlock();
    }
    return chunkcompiletaskgenerator;
  }
  
  protected double f()
  {
    bmt entityplayersp = bcf.z().h;
    double d0 = this.c.a + 8.0D - entityplayersp.p;
    double d1 = this.c.b + 8.0D - entityplayersp.q;
    double d2 = this.c.c + 8.0D - entityplayersp.r;
    return d0 * d0 + d1 * d1 + d2 * d2;
  }
  
  private void a(bmz worldRendererIn, cj pos)
  {
    worldRendererIn.a(7, bvp.a);
    worldRendererIn.c(-pos.p(), -pos.q(), -pos.r());
  }
  
  private void a(ahm layer, float x, float y, float z, bmz worldRendererIn, bqc compiledChunkIn)
  {
    if ((layer == ahm.d) && (!compiledChunkIn.b(layer)))
    {
      worldRendererIn.a(x, y, z);
      compiledChunkIn.a(worldRendererIn.a());
    }
    worldRendererIn.e();
  }
  
  private void q()
  {
    bni.G();
    bni.F();
    float f = 1.000001F;
    bni.c(-8.0F, -8.0F, -8.0F);
    bni.b(f, f, f);
    bni.c(8.0F, 8.0F, 8.0F);
    bni.c(2982, this.l);
    bni.H();
  }
  
  public void g()
  {
    bni.a(this.l);
  }
  
  public bqc h()
  {
    return this.b;
  }
  
  public void a(bqc compiledChunkIn)
  {
    this.h.lock();
    try
    {
      this.b = compiledChunkIn;
    }
    finally
    {
      this.h.unlock();
    }
  }
  
  public void i()
  {
    b();
    this.b = bqc.a;
  }
  
  public void a()
  {
    i();
    this.d = null;
    for (int i = 0; i < ahm.values().length; i++) {
      if (this.m[i] != null) {
        this.m[i].c();
      }
    }
  }
  
  public cj k()
  {
    return this.f;
  }
  
  public void a(boolean needsUpdateIn)
  {
    if (this.o) {
      needsUpdateIn |= this.q;
    }
    this.o = true;
    this.q = needsUpdateIn;
  }
  
  public void m()
  {
    this.o = false;
    this.q = false;
  }
  
  public boolean n()
  {
    return this.o;
  }
  
  public boolean o()
  {
    return (this.o) && (this.q);
  }
  
  public cj a(cq p_181701_1_)
  {
    return getPositionOffset16(p_181701_1_);
  }
  
  public aht p()
  {
    return this.d;
  }
  
  public cj getPositionOffset16(cq facing)
  {
    int index = facing.a();
    cj posOffset = this.positionOffsets16[index];
    if (posOffset == null)
    {
      posOffset = k().a(facing, 16);
      this.positionOffsets16[index] = posOffset;
    }
    return posOffset;
  }
  
  protected bnb createRegionRenderCache(aht world, cj from, cj to, int subtract)
  {
    return new bnb(world, from, to, subtract);
  }
  
  private ahm fixBlockLayer(ajt block, ahm layer)
  {
    if (this.isMipmaps)
    {
      if (layer == ahm.c)
      {
        if ((block instanceof anx)) {
          return layer;
        }
        if ((block instanceof aka)) {
          return layer;
        }
        return ahm.b;
      }
    }
    else if (layer == ahm.b) {
      return ahm.c;
    }
    return layer;
  }
}
