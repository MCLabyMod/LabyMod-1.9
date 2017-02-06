import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class bpz
  implements Comparable<bpz>
{
  private final bqf a;
  private final ReentrantLock b = new ReentrantLock();
  private final List<Runnable> c = Lists.newArrayList();
  private final bpz.b d;
  private final double e;
  private bnc f;
  private bqc g;
  private bpz.a h = bpz.a.a;
  private boolean i;
  
  public bpz(bqf ☃, bpz.b ☃, double ☃)
  {
    this.a = ☃;
    this.d = ☃;
    this.e = ☃;
  }
  
  public bpz.a a()
  {
    return this.h;
  }
  
  public bqf b()
  {
    return this.a;
  }
  
  public bqc c()
  {
    return this.g;
  }
  
  public void a(bqc ☃)
  {
    this.g = ☃;
  }
  
  public bnc d()
  {
    return this.f;
  }
  
  public void a(bnc ☃)
  {
    this.f = ☃;
  }
  
  public void a(bpz.a ☃)
  {
    this.b.lock();
    try
    {
      this.h = ☃;
    }
    finally
    {
      this.b.unlock();
    }
  }
  
  public void e()
  {
    this.b.lock();
    try
    {
      if ((this.d == bpz.b.a) && (this.h != bpz.a.d)) {
        this.a.a(false);
      }
      this.i = true;
      this.h = bpz.a.d;
      for (Runnable ☃ : this.c) {
        ☃.run();
      }
    }
    finally
    {
      this.b.unlock();
    }
  }
  
  public void a(Runnable ☃)
  {
    this.b.lock();
    try
    {
      this.c.add(☃);
      if (this.i) {
        ☃.run();
      }
    }
    finally
    {
      this.b.unlock();
    }
  }
  
  public ReentrantLock f()
  {
    return this.b;
  }
  
  public bpz.b g()
  {
    return this.d;
  }
  
  public boolean h()
  {
    return this.i;
  }
  
  public int a(bpz ☃)
  {
    return Doubles.compare(this.e, ☃.e);
  }
  
  public double i()
  {
    return this.e;
  }
  
  public static enum a
  {
    private a() {}
  }
  
  public static enum b
  {
    private b() {}
  }
}
