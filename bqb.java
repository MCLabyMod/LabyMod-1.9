import com.google.common.collect.Lists;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bqb
  implements Runnable
{
  private static final Logger a = ;
  private final bqa b;
  private final bnc c;
  private boolean d;
  
  public bqb(bqa p_i46201_1_)
  {
    this(p_i46201_1_, (bnc)null);
  }
  
  public bqb(bqa chunkRenderDispatcherIn, bnc regionRenderCacheBuilderIn)
  {
    this.d = true;
    this.b = chunkRenderDispatcherIn;
    this.c = regionRenderCacheBuilderIn;
  }
  
  public void run()
  {
    while (this.d) {
      try
      {
        a(this.b.d());
      }
      catch (InterruptedException var3)
      {
        a.debug("Stopping chunk worker due to interrupt");
        return;
      }
      catch (Throwable throwable)
      {
        b crashreport = b.a(throwable, "Batching chunks");
        bcf.z().a(bcf.z().b(crashreport));
        return;
      }
    }
  }
  
  protected void a(final bpz generator)
    throws InterruptedException
  {
    try
    {
      generator.f().lock();
      boolean flag;
      boolean flag1;
      boolean flag2;
      try
      {
        if (generator.a() != bpz.a.a)
        {
          if (!generator.h()) {
            a.warn("Chunk render task was " + generator.a() + " when I expected it to be pending; ignoring task");
          }
          return;
        }
        cj blockpos = new cj(bcf.z().h);
        cj blockpos1 = generator.b().k();
        int i = 16;
        int j = 8;
        int k = 24;
        if (blockpos1.a(8, 8, 8).k(blockpos) > 576.0D)
        {
          aht world = generator.b().p();
          cj.a blockpos$mutableblockpos = new cj.a(blockpos1);
          flag = a(blockpos$mutableblockpos.c(blockpos1.p() - 1, blockpos1.q(), blockpos1.r()), world);
          flag1 = a(blockpos$mutableblockpos.c(blockpos1.p(), blockpos1.q(), blockpos1.r() - 1), world);
          flag2 = a(blockpos$mutableblockpos.c(blockpos1.p() + 16, blockpos1.q(), blockpos1.r()), world);
          boolean flag3 = a(blockpos$mutableblockpos.c(blockpos1.p(), blockpos1.q(), blockpos1.r() + 16), world);
          if ((!flag) || (!flag1) || (!flag2) || (!flag3)) {
            return;
          }
        }
        generator.a(bpz.a.b);
      }
      finally
      {
        generator.f().unlock();
      }
      rr lvt_2_2_ = bcf.z().aa();
      if (lvt_2_2_ == null)
      {
        generator.e();
      }
      else
      {
        generator.a(c());
        float f = (float)lvt_2_2_.p;
        float f1 = (float)lvt_2_2_.q + lvt_2_2_.bn();
        float f2 = (float)lvt_2_2_.r;
        bpz.b chunkcompiletaskgenerator$type = generator.g();
        if (chunkcompiletaskgenerator$type == bpz.b.a) {
          generator.b().b(f, f1, f2, generator);
        } else if (chunkcompiletaskgenerator$type == bpz.b.b) {
          generator.b().a(f, f1, f2, generator);
        }
        generator.f().lock();
        try
        {
          if (generator.a() != bpz.a.b)
          {
            if (!generator.h()) {
              a.warn("Chunk render task was " + generator.a() + " when I expected it to be compiling; aborting task");
            }
            b(generator);
            return;
          }
          generator.a(bpz.a.c);
        }
        finally
        {
          generator.f().unlock();
        }
        final bqc lvt_7_2_ = generator.c();
        ArrayList lvt_8_2_ = Lists.newArrayList();
        if (chunkcompiletaskgenerator$type == bpz.b.a)
        {
          flag = ahm.values();flag1 = flag.length;
          for (flag2 = false; flag2 < flag1; flag2++)
          {
            ahm blockrenderlayer = flag[flag2];
            if (lvt_7_2_.d(blockrenderlayer)) {
              lvt_8_2_.add(this.b.a(blockrenderlayer, generator.d().a(blockrenderlayer), generator.b(), lvt_7_2_, generator.i()));
            }
          }
        }
        else if (chunkcompiletaskgenerator$type == bpz.b.b)
        {
          lvt_8_2_.add(this.b.a(ahm.d, generator.d().a(ahm.d), generator.b(), lvt_7_2_, generator.i()));
        }
        final ListenableFuture<List<Object>> listenablefuture = Futures.allAsList(lvt_8_2_);
        if (listenablefuture == null)
        {
          System.out.println("ERROR: " + getClass().getName() + " NULL!");
          return;
        }
        generator.a(new Runnable()
        {
          public void run()
          {
            listenablefuture.cancel(false);
          }
        });
        Futures.addCallback(listenablefuture, new FutureCallback()
        {
          public void a(List<Object> p_onSuccess_1_)
          {
            bqb.a(bqb.this, generator);
            generator.f().lock();
            try
            {
              if (generator.a() == bpz.a.c)
              {
                generator.a(bpz.a.d);
                
                generator.f().unlock();
                break label132;
              }
              if (!generator.h()) {
                bqb.b().warn("Chunk render task was " + generator.a() + " when I expected it to be uploading; aborting task");
              }
            }
            finally
            {
              generator.f().unlock();
            }
            return;
            label132:
            generator.b().a(lvt_7_2_);
          }
          
          public void onFailure(Throwable p_onFailure_1_)
          {
            bqb.a(bqb.this, generator);
            if ((!(p_onFailure_1_ instanceof CancellationException)) && (!(p_onFailure_1_ instanceof InterruptedException))) {
              bcf.z().a(b.a(p_onFailure_1_, "Rendering chunk"));
            }
          }
        });
      }
    }
    catch (Exception error)
    {
      error.printStackTrace();
    }
  }
  
  private boolean a(cj p_188263_1_, aht p_188263_2_)
  {
    return !p_188263_2_.a(p_188263_1_.p() >> 4, p_188263_1_.r() >> 4).f();
  }
  
  private bnc c()
    throws InterruptedException
  {
    return this.c != null ? this.c : this.b.c();
  }
  
  private void b(bpz taskGenerator)
  {
    if (this.c == null) {
      this.b.a(taskGenerator.d());
    }
  }
  
  public void a()
  {
    this.d = false;
  }
}
