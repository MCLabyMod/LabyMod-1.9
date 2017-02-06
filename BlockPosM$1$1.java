import com.google.common.collect.AbstractIterator;

class BlockPosM$1$1
  extends AbstractIterator
{
  private BlockPosM theBlockPosM = null;
  
  BlockPosM$1$1(BlockPosM.1 param1) {}
  
  protected BlockPosM computeNext0()
  {
    if (this.theBlockPosM == null)
    {
      this.theBlockPosM = new BlockPosM(this.this$0.val$posFrom.p(), this.this$0.val$posFrom.q(), this.this$0.val$posFrom.r(), 3);
      return this.theBlockPosM;
    }
    if (this.theBlockPosM.equals(this.this$0.val$posTo)) {
      return (BlockPosM)endOfData();
    }
    int bx = this.theBlockPosM.p();
    int by = this.theBlockPosM.q();
    int bz = this.theBlockPosM.r();
    if (bx < this.this$0.val$posTo.p())
    {
      bx++;
    }
    else if (by < this.this$0.val$posTo.q())
    {
      bx = this.this$0.val$posFrom.p();
      by++;
    }
    else if (bz < this.this$0.val$posTo.r())
    {
      bx = this.this$0.val$posFrom.p();
      by = this.this$0.val$posFrom.q();
      bz++;
    }
    this.theBlockPosM.setXyz(bx, by, bz);
    return this.theBlockPosM;
  }
  
  protected Object computeNext()
  {
    return computeNext0();
  }
}
