import com.google.common.collect.AbstractIterator;
import java.util.Iterator;

final class BlockPosM$1
  implements Iterable
{
  BlockPosM$1(cj paramcj1, cj paramcj2) {}
  
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
}
