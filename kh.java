import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.EncoderException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.commons.lang3.ObjectUtils;

public class kh
{
  private static final Map<Class<? extends rr>, Integer> a = ;
  private final rr b;
  private final Map<Integer, kh.a<?>> c = Maps.newHashMap();
  private final ReadWriteLock d = new ReentrantReadWriteLock();
  private boolean e = true;
  private boolean f;
  
  public kh(rr ☃)
  {
    this.b = ☃;
  }
  
  public static <T> ke<T> a(Class<? extends rr> ☃, kf<T> ☃)
  {
    int ☃;
    int ☃;
    if (a.containsKey(☃))
    {
      ☃ = ((Integer)a.get(☃)).intValue() + 1;
    }
    else
    {
      int ☃ = 0;
      Class<?> ☃ = ☃;
      while (☃ != rr.class)
      {
        ☃ = ☃.getSuperclass();
        if (a.containsKey(☃)) {
          ☃ = ((Integer)a.get(☃)).intValue() + 1;
        }
      }
      ☃ = ☃;
    }
    if (☃ > 254) {
      throw new IllegalArgumentException("Data value id is too big with " + ☃ + "! (Max is " + 254 + ")");
    }
    a.put(☃, Integer.valueOf(☃));
    return ☃.a(☃);
  }
  
  public <T> void a(ke<T> ☃, T ☃)
  {
    int ☃ = ☃.a();
    if (☃ > 254) {
      throw new IllegalArgumentException("Data value id is too big with " + ☃ + "! (Max is " + 254 + ")");
    }
    if (this.c.containsKey(Integer.valueOf(☃))) {
      throw new IllegalArgumentException("Duplicate id value for " + ☃ + "!");
    }
    if (kg.b(☃.b()) < 0) {
      throw new IllegalArgumentException("Unregistered serializer " + ☃.b() + " for " + ☃ + "!");
    }
    c(☃, ☃);
  }
  
  private <T> void c(ke<T> ☃, T ☃)
  {
    kh.a<T> ☃ = new kh.a(☃, ☃);
    this.d.writeLock().lock();
    this.c.put(Integer.valueOf(☃.a()), ☃);
    this.e = false;
    this.d.writeLock().unlock();
  }
  
  private <T> kh.a<T> c(ke<T> ☃)
  {
    this.d.readLock().lock();
    kh.a<T> ☃;
    try
    {
      ☃ = (kh.a)this.c.get(Integer.valueOf(☃.a()));
    }
    catch (Throwable ☃)
    {
      b ☃ = b.a(☃, "Getting synched entity data");
      c ☃ = ☃.a("Synched entity data");
      
      ☃.a("Data ID", ☃);
      throw new e(☃);
    }
    this.d.readLock().unlock();
    return ☃;
  }
  
  public <T> T a(ke<T> ☃)
  {
    return (T)c(☃).b();
  }
  
  public <T> void b(ke<T> ☃, T ☃)
  {
    kh.a<T> ☃ = c(☃);
    if (ObjectUtils.notEqual(☃, ☃.b()))
    {
      ☃.a(☃);
      this.b.a(☃);
      ☃.a(true);
      this.f = true;
    }
  }
  
  public <T> void b(ke<T> ☃)
  {
    kh.a.a(c(☃), true);
    this.f = true;
  }
  
  public boolean a()
  {
    return this.f;
  }
  
  public static void a(List<kh.a<?>> ☃, em ☃)
    throws IOException
  {
    if (☃ != null)
    {
      int ☃ = 0;
      for (int ☃ = ☃.size(); ☃ < ☃; ☃++)
      {
        kh.a<?> ☃ = (kh.a)☃.get(☃);
        a(☃, ☃);
      }
    }
    ☃.writeByte(255);
  }
  
  public List<kh.a<?>> b()
  {
    List<kh.a<?>> ☃ = null;
    if (this.f)
    {
      this.d.readLock().lock();
      for (kh.a<?> ☃ : this.c.values()) {
        if (☃.c())
        {
          ☃.a(false);
          if (☃ == null) {
            ☃ = Lists.newArrayList();
          }
          ☃.add(☃);
        }
      }
      this.d.readLock().unlock();
    }
    this.f = false;
    
    return ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.d.readLock().lock();
    for (kh.a<?> ☃ : this.c.values()) {
      a(☃, ☃);
    }
    this.d.readLock().unlock();
    
    ☃.writeByte(255);
  }
  
  public List<kh.a<?>> c()
  {
    List<kh.a<?>> ☃ = null;
    
    this.d.readLock().lock();
    for (kh.a<?> ☃ : this.c.values())
    {
      if (☃ == null) {
        ☃ = Lists.newArrayList();
      }
      ☃.add(☃);
    }
    this.d.readLock().unlock();
    
    return ☃;
  }
  
  private static <T> void a(em ☃, kh.a<T> ☃)
    throws IOException
  {
    ke<T> ☃ = ☃.a();
    int ☃ = kg.b(☃.b());
    if (☃ < 0) {
      throw new EncoderException("Unknown serializer type " + ☃.b());
    }
    ☃.writeByte(☃.a());
    ☃.b(☃);
    ☃.b().a(☃, ☃.b());
  }
  
  public static List<kh.a<?>> b(em ☃)
    throws IOException
  {
    List<kh.a<?>> ☃ = null;
    int ☃;
    while ((☃ = ☃.readUnsignedByte()) != 255)
    {
      if (☃ == null) {
        ☃ = Lists.newArrayList();
      }
      int ☃ = ☃.readUnsignedByte();
      kf<?> ☃ = kg.a(☃);
      if (☃ == null) {
        throw new DecoderException("Unknown serializer type " + ☃);
      }
      ☃.add(new kh.a(☃.a(☃), ☃.a(☃)));
    }
    return ☃;
  }
  
  public void a(List<kh.a<?>> ☃)
  {
    this.d.writeLock().lock();
    for (kh.a<?> ☃ : ☃)
    {
      kh.a<?> ☃ = (kh.a)this.c.get(Integer.valueOf(☃.a().a()));
      if (☃ != null)
      {
        a(☃, ☃);
        this.b.a(☃.a());
      }
    }
    this.d.writeLock().unlock();
    
    this.f = true;
  }
  
  protected <T> void a(kh.a<T> ☃, kh.a<?> ☃)
  {
    ☃.a(☃.b());
  }
  
  public boolean d()
  {
    return this.e;
  }
  
  public void e()
  {
    this.f = false;
  }
  
  public static class a<T>
  {
    private final ke<T> a;
    private T b;
    private boolean c;
    
    public a(ke<T> ☃, T ☃)
    {
      this.a = ☃;
      this.b = ☃;
      this.c = true;
    }
    
    public ke<T> a()
    {
      return this.a;
    }
    
    public void a(T ☃)
    {
      this.b = ☃;
    }
    
    public T b()
    {
      return (T)this.b;
    }
    
    public boolean c()
    {
      return this.c;
    }
    
    public void a(boolean ☃)
    {
      this.c = ☃;
    }
  }
}
