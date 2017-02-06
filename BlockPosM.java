import com.google.common.collect.AbstractIterator;
import java.util.Iterator;

public class BlockPosM
  extends cj
{
  private int mx;
  private int my;
  private int mz;
  private int level;
  private BlockPosM[] facings;
  private boolean needsUpdate;
  
  public BlockPosM(int x, int y, int z)
  {
    this(x, y, z, 0);
  }
  
  public BlockPosM(double xIn, double yIn, double zIn)
  {
    this(on.c(xIn), on.c(yIn), on.c(zIn));
  }
  
  public BlockPosM(int x, int y, int z, int level)
  {
    super(0, 0, 0);
    this.mx = x;
    this.my = y;
    this.mz = z;
    this.level = level;
  }
  
  public int p()
  {
    return this.mx;
  }
  
  public int q()
  {
    return this.my;
  }
  
  public int r()
  {
    return this.mz;
  }
  
  public void setXyz(int x, int y, int z)
  {
    this.mx = x;
    this.my = y;
    this.mz = z;
    this.needsUpdate = true;
  }
  
  public void setXyz(double xIn, double yIn, double zIn)
  {
    setXyz(on.c(xIn), on.c(yIn), on.c(zIn));
  }
  
  public cj a(cq facing)
  {
    if (this.level <= 0) {
      return super.a(facing, 1);
    }
    if (this.facings == null) {
      this.facings = new BlockPosM[cq.n.length];
    }
    if (this.needsUpdate) {
      update();
    }
    int index = facing.a();
    BlockPosM bpm = this.facings[index];
    if (bpm == null)
    {
      int nx = this.mx + facing.g();
      int ny = this.my + facing.h();
      int nz = this.mz + facing.i();
      
      bpm = new BlockPosM(nx, ny, nz, this.level - 1);
      this.facings[index] = bpm;
    }
    return bpm;
  }
  
  public cj a(cq facing, int n)
  {
    if (n == 1) {
      return a(facing);
    }
    return super.a(facing, n);
  }
  
  private void update()
  {
    for (int i = 0; i < 6; i++)
    {
      BlockPosM bpm = this.facings[i];
      if (bpm != null)
      {
        cq facing = cq.n[i];
        int nx = this.mx + facing.g();
        int ny = this.my + facing.h();
        int nz = this.mz + facing.i();
        bpm.setXyz(nx, ny, nz);
      }
    }
    this.needsUpdate = false;
  }
  
  public cj h()
  {
    return new cj(this.mx, this.my, this.mz);
  }
  
  public static Iterable getAllInBoxMutable(cj from, cj to)
  {
    cj posFrom = new cj(Math.min(from.p(), to.p()), Math.min(from.q(), to.q()), Math.min(from.r(), to.r()));
    final cj posTo = new cj(Math.max(from.p(), to.p()), Math.max(from.q(), to.q()), Math.max(from.r(), to.r()));
    new Iterable()
    {
      public Iterator iterator()
      {
        new AbstractIterator()
        {
          private BlockPosM theBlockPosM = null;
          
          protected BlockPosM computeNext0()
          {
            if (this.theBlockPosM == null)
            {
              this.theBlockPosM = new BlockPosM(BlockPosM.1.this.val$posFrom.p(), BlockPosM.1.this.val$posFrom.q(), BlockPosM.1.this.val$posFrom.r(), 3);
              return this.theBlockPosM;
            }
            if (this.theBlockPosM.equals(BlockPosM.1.this.val$posTo)) {
              return (BlockPosM)endOfData();
            }
            int bx = this.theBlockPosM.p();
            int by = this.theBlockPosM.q();
            int bz = this.theBlockPosM.r();
            if (bx < BlockPosM.1.this.val$posTo.p())
            {
              bx++;
            }
            else if (by < BlockPosM.1.this.val$posTo.q())
            {
              bx = BlockPosM.1.this.val$posFrom.p();
              by++;
            }
            else if (bz < BlockPosM.1.this.val$posTo.r())
            {
              bx = BlockPosM.1.this.val$posFrom.p();
              by = BlockPosM.1.this.val$posFrom.q();
              bz++;
            }
            this.theBlockPosM.setXyz(bx, by, bz);
            return this.theBlockPosM;
          }
          
          protected Object computeNext()
          {
            return computeNext0();
          }
        };
      }
    };
  }
  
  public cj getImmutable()
  {
    return new cj(p(), q(), r());
  }
}
