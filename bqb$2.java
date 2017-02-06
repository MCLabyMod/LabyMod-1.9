import com.google.common.util.concurrent.FutureCallback;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.logging.log4j.Logger;

class bqb$2
  implements FutureCallback<List<Object>>
{
  bqb$2(bqb this$0, bpz parambpz, bqc parambqc) {}
  
  public void a(List<Object> p_onSuccess_1_)
  {
    bqb.a(this.this$0, this.val$generator);
    this.val$generator.f().lock();
    try
    {
      if (this.val$generator.a() == bpz.a.c)
      {
        this.val$generator.a(bpz.a.d);
        
        this.val$generator.f().unlock();
        break label132;
      }
      if (!this.val$generator.h()) {
        bqb.b().warn("Chunk render task was " + this.val$generator.a() + " when I expected it to be uploading; aborting task");
      }
    }
    finally
    {
      this.val$generator.f().unlock();
    }
    return;
    label132:
    this.val$generator.b().a(this.val$lvt_7_2_);
  }
  
  public void onFailure(Throwable p_onFailure_1_)
  {
    bqb.a(this.this$0, this.val$generator);
    if ((!(p_onFailure_1_ instanceof CancellationException)) && (!(p_onFailure_1_ instanceof InterruptedException))) {
      bcf.z().a(b.a(p_onFailure_1_, "Rendering chunk"));
    }
  }
}
