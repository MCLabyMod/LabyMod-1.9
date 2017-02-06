import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.google.common.primitives.Doubles;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bqa
{
  private static final Logger a = ;
  private static final ThreadFactory b = new ThreadFactoryBuilder().setNameFormat("Chunk Batcher %d").setDaemon(true).build();
  private final int c;
  private final List<Thread> d = Lists.newArrayList();
  private final List<bqb> e = Lists.newArrayList();
  private final PriorityBlockingQueue<bpz> f = Queues.newPriorityBlockingQueue();
  private final BlockingQueue<bnc> g;
  private final bna h = new bna();
  private final bnw i = new bnw();
  private final Queue<bqa.a> j = Queues.newPriorityQueue();
  private final bqb k;
  
  public bqa()
  {
    int i = Math.max(1, (int)(Runtime.getRuntime().maxMemory() * 0.3D) / 10485760);
    int j = Math.max(1, on.a(Runtime.getRuntime().availableProcessors(), 1, i / 5));
    
    this.c = on.a(j, 1, i);
    if (j > 1) {
      for (int k = 0; k < j; k++)
      {
        bqb chunkrenderworker = new bqb(this);
        Thread thread = b.newThread(chunkrenderworker);
        thread.start();
        this.e.add(chunkrenderworker);
        this.d.add(thread);
      }
    }
    this.g = Queues.newArrayBlockingQueue(this.c);
    for (int l = 0; l < this.c; l++) {
      this.g.add(new bnc());
    }
    this.k = new bqb(this, new bnc());
  }
  
  public String a()
  {
    return this.d.isEmpty() ? String.format("pC: %03d, single-threaded", new Object[] { Integer.valueOf(this.f.size()) }) : String.format("pC: %03d, pU: %1d, aB: %1d", new Object[] { Integer.valueOf(this.f.size()), Integer.valueOf(this.j.size()), Integer.valueOf(this.g.size()) });
  }
  
  public boolean a(long p_178516_1_)
  {
    boolean flag = false;
    for (;;)
    {
      boolean flag1 = false;
      if (this.d.isEmpty())
      {
        bpz chunkcompiletaskgenerator = (bpz)this.f.poll();
        if (chunkcompiletaskgenerator != null) {
          try
          {
            this.k.a(chunkcompiletaskgenerator);
            flag1 = true;
          }
          catch (InterruptedException var8)
          {
            a.warn("Skipped task due to interrupt");
          }
        }
      }
      synchronized (this.j)
      {
        if (!this.j.isEmpty())
        {
          bqa.a.b((bqa.a)this.j.poll()).run();
          flag1 = true;
          flag = true;
        }
      }
      if ((p_178516_1_ == 0L) || (!flag1) || (p_178516_1_ < System.nanoTime())) {
        break;
      }
    }
    return flag;
  }
  
  public boolean a(bqf chunkRenderer)
  {
    chunkRenderer.c().lock();
    boolean flag1;
    try
    {
      final bpz chunkcompiletaskgenerator = chunkRenderer.d();
      chunkcompiletaskgenerator.a(new Runnable()
      {
        public void run()
        {
          bqa.a(bqa.this).remove(chunkcompiletaskgenerator);
        }
      });
      boolean flag = this.f.offer(chunkcompiletaskgenerator);
      if (!flag) {
        chunkcompiletaskgenerator.e();
      }
      flag1 = flag;
    }
    finally
    {
      chunkRenderer.c().unlock();
    }
    return flag1;
  }
  
  public boolean b(bqf chunkRenderer)
  {
    chunkRenderer.c().lock();
    boolean flag;
    try
    {
      bpz chunkcompiletaskgenerator = chunkRenderer.d();
      try
      {
        this.k.a(chunkcompiletaskgenerator);
      }
      catch (InterruptedException var7) {}
      flag = true;
    }
    finally
    {
      chunkRenderer.c().unlock();
    }
    return flag;
  }
  
  public void b()
  {
    e();
    List<bnc> list = Lists.newArrayList();
    while (list.size() != this.c)
    {
      a(Long.MAX_VALUE);
      try
      {
        list.add(c());
      }
      catch (InterruptedException var3) {}
    }
    this.g.addAll(list);
  }
  
  public void a(bnc p_178512_1_)
  {
    this.g.add(p_178512_1_);
  }
  
  public bnc c()
    throws InterruptedException
  {
    return (bnc)this.g.take();
  }
  
  public bpz d()
    throws InterruptedException
  {
    return (bpz)this.f.take();
  }
  
  public boolean c(bqf chunkRenderer)
  {
    chunkRenderer.c().lock();
    boolean flag;
    try
    {
      final bpz chunkcompiletaskgenerator = chunkRenderer.e();
      if (chunkcompiletaskgenerator == null)
      {
        boolean flag = true;
        return flag;
      }
      chunkcompiletaskgenerator.a(new Runnable()
      {
        public void run()
        {
          bqa.a(bqa.this).remove(chunkcompiletaskgenerator);
        }
      });
      flag = this.f.offer(chunkcompiletaskgenerator);
    }
    finally
    {
      chunkRenderer.c().unlock();
    }
    return flag;
  }
  
  public ListenableFuture<Object> a(final ahm p_188245_1_, final bmz p_188245_2_, final bqf p_188245_3_, final bqc p_188245_4_, final double p_188245_5_)
  {
    if (bcf.z().aE())
    {
      if (bzg.f()) {
        a(p_188245_2_, p_188245_3_.b(p_188245_1_.ordinal()));
      } else {
        a(p_188245_2_, ((bqe)p_188245_3_).a(p_188245_1_, p_188245_4_), p_188245_3_);
      }
      p_188245_2_.c(0.0D, 0.0D, 0.0D);
      return Futures.immediateFuture((Object)null);
    }
    ListenableFutureTask<Object> listenablefuturetask = ListenableFutureTask.create(new Runnable()
    {
      public void run()
      {
        bqa.this.a(p_188245_1_, p_188245_2_, p_188245_3_, p_188245_4_, p_188245_5_);
      }
    }, (Object)null);
    synchronized (this.j)
    {
      this.j.add(new bqa.a(listenablefuturetask, p_188245_5_));
      return listenablefuturetask;
    }
  }
  
  private void a(bmz p_178510_1_, int p_178510_2_, bqf chunkRenderer)
  {
    bni.f(p_178510_2_, 4864);
    bni.G();
    chunkRenderer.g();
    this.h.a(p_178510_1_);
    bni.H();
    bni.K();
  }
  
  private void a(bmz p_178506_1_, bvq vertexBufferIn)
  {
    this.i.a(vertexBufferIn);
    this.i.a(p_178506_1_);
  }
  
  public void e()
  {
    while (!this.f.isEmpty())
    {
      bpz chunkcompiletaskgenerator = (bpz)this.f.poll();
      if (chunkcompiletaskgenerator != null) {
        chunkcompiletaskgenerator.e();
      }
    }
  }
  
  public boolean f()
  {
    return (this.f.isEmpty()) && (this.j.isEmpty());
  }
  
  public void g()
  {
    e();
    for (bqb chunkrenderworker : this.e) {
      chunkrenderworker.a();
    }
    for (Thread thread : this.d) {
      try
      {
        thread.interrupt();
        thread.join();
      }
      catch (InterruptedException interruptedexception)
      {
        a.warn("Interrupted whilst waiting for worker to die", interruptedexception);
      }
    }
    this.g.clear();
  }
  
  public boolean h()
  {
    return this.g.size() == 0;
  }
  
  class a
    implements Comparable<a>
  {
    private final ListenableFutureTask<Object> b;
    private final double c;
    
    public a(double p_i46994_2_)
    {
      this.b = p_i46994_2_;
      this.c = p_i46994_3_;
    }
    
    public int a(a p_compareTo_1_)
    {
      return Doubles.compare(this.c, p_compareTo_1_.c);
    }
  }
}
