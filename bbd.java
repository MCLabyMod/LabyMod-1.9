import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;

public class bbd
  implements Runnable
{
  private static final bbd a = new bbd();
  private List<bbe> b = Collections.synchronizedList(Lists.newArrayList());
  private volatile long c;
  private volatile long d;
  private volatile boolean e;
  
  private bbd()
  {
    Thread ☃ = new Thread(this, "File IO Thread");
    ☃.setPriority(1);
    ☃.start();
  }
  
  public static bbd a()
  {
    return a;
  }
  
  public void run()
  {
    for (;;)
    {
      c();
    }
  }
  
  private void c()
  {
    for (int ☃ = 0; ☃ < this.b.size(); ☃++)
    {
      bbe ☃ = (bbe)this.b.get(☃);
      boolean ☃ = ☃.c();
      if (!☃)
      {
        this.b.remove(☃--);
        this.d += 1L;
      }
      try
      {
        Thread.sleep(this.e ? 0L : 10L);
      }
      catch (InterruptedException ☃)
      {
        ☃.printStackTrace();
      }
    }
    if (this.b.isEmpty()) {
      try
      {
        Thread.sleep(25L);
      }
      catch (InterruptedException ☃)
      {
        ☃.printStackTrace();
      }
    }
  }
  
  public void a(bbe ☃)
  {
    if (this.b.contains(☃)) {
      return;
    }
    this.c += 1L;
    this.b.add(☃);
  }
  
  public void b()
    throws InterruptedException
  {
    this.e = true;
    while (this.c != this.d) {
      Thread.sleep(10L);
    }
    this.e = false;
  }
}
