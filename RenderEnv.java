import java.util.BitSet;
import java.util.List;

public class RenderEnv
{
  private ahx blockAccess;
  private arc blockState;
  private cj blockPos;
  private bch gameSettings;
  private int blockId = -1;
  private int metadata = -1;
  private int breakingAnimation = -1;
  private float[] quadBounds = new float[cq.n.length * 2];
  private BitSet boundsFlags = new BitSet(3);
  private boe.b aoFace = new boe.b();
  private BlockPosM colorizerBlockPosM = null;
  private boolean[] borderFlags = null;
  private static ThreadLocal threadLocalInstance = new ThreadLocal();
  
  private RenderEnv(ahx blockAccess, arc blockState, cj blockPos)
  {
    this.blockAccess = blockAccess;
    this.blockState = blockState;
    this.blockPos = blockPos;
    this.gameSettings = Config.getGameSettings();
  }
  
  public static RenderEnv getInstance(ahx blockAccessIn, arc blockStateIn, cj blockPosIn)
  {
    RenderEnv re = (RenderEnv)threadLocalInstance.get();
    if (re == null)
    {
      re = new RenderEnv(blockAccessIn, blockStateIn, blockPosIn);
      threadLocalInstance.set(re);
      return re;
    }
    re.reset(blockAccessIn, blockStateIn, blockPosIn);
    return re;
  }
  
  private void reset(ahx blockAccessIn, arc blockStateIn, cj blockPosIn)
  {
    this.blockAccess = blockAccessIn;
    this.blockState = blockStateIn;
    this.blockPos = blockPosIn;
    
    this.blockId = -1;
    this.metadata = -1;
    this.breakingAnimation = -1;
    this.boundsFlags.clear();
  }
  
  public int getBlockId()
  {
    if (this.blockId < 0) {
      if ((this.blockState instanceof ara))
      {
        ara bsb = (ara)this.blockState;
        this.blockId = bsb.getBlockId();
      }
      else
      {
        this.blockId = ajt.a(this.blockState.t());
      }
    }
    return this.blockId;
  }
  
  public int getMetadata()
  {
    if (this.metadata < 0) {
      if ((this.blockState instanceof ara))
      {
        ara bsb = (ara)this.blockState;
        this.metadata = bsb.getMetadata();
      }
      else
      {
        this.metadata = this.blockState.t().e(this.blockState);
      }
    }
    return this.metadata;
  }
  
  public float[] getQuadBounds()
  {
    return this.quadBounds;
  }
  
  public BitSet getBoundsFlags()
  {
    return this.boundsFlags;
  }
  
  public boe.b getAoFace()
  {
    return this.aoFace;
  }
  
  public boolean isBreakingAnimation(List listQuads)
  {
    if (this.breakingAnimation < 0) {
      if (listQuads.size() > 0) {
        if ((listQuads.get(0) instanceof bom)) {
          this.breakingAnimation = 1;
        } else {
          this.breakingAnimation = 0;
        }
      }
    }
    return this.breakingAnimation == 1;
  }
  
  public boolean isBreakingAnimation(bof quad)
  {
    if (this.breakingAnimation < 0) {
      if ((quad instanceof bom)) {
        this.breakingAnimation = 1;
      } else {
        this.breakingAnimation = 0;
      }
    }
    return this.breakingAnimation == 1;
  }
  
  public boolean isBreakingAnimation()
  {
    return this.breakingAnimation == 1;
  }
  
  public arc getBlockState()
  {
    return this.blockState;
  }
  
  public BlockPosM getColorizerBlockPosM()
  {
    if (this.colorizerBlockPosM == null) {
      this.colorizerBlockPosM = new BlockPosM(0, 0, 0);
    }
    return this.colorizerBlockPosM;
  }
  
  public boolean[] getBorderFlags()
  {
    if (this.borderFlags == null) {
      this.borderFlags = new boolean[4];
    }
    return this.borderFlags;
  }
}
