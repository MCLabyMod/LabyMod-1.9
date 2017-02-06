import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class cm
{
  public static <T> Iterable<T[]> a(Class<T> ☃, Iterable<? extends Iterable<? extends T>> ☃)
  {
    return new cm.b(☃, (Iterable[])b(Iterable.class, ☃), null);
  }
  
  public static <T> Iterable<List<T>> a(Iterable<? extends Iterable<? extends T>> ☃)
  {
    return b(a(Object.class, ☃));
  }
  
  private static <T> Iterable<List<T>> b(Iterable<Object[]> ☃)
  {
    return Iterables.transform(☃, new cm.a(null));
  }
  
  private static <T> T[] b(Class<? super T> ☃, Iterable<? extends T> ☃)
  {
    List<T> ☃ = Lists.newArrayList();
    for (T ☃ : ☃) {
      ☃.add(☃);
    }
    return (Object[])☃.toArray(b(☃, ☃.size()));
  }
  
  private static <T> T[] b(Class<? super T> ☃, int ☃)
  {
    return (Object[])Array.newInstance(☃, ☃);
  }
  
  static class a<T>
    implements Function<Object[], List<T>>
  {
    public List<T> a(Object[] ☃)
    {
      return Arrays.asList((Object[])☃);
    }
  }
  
  static class b<T>
    implements Iterable<T[]>
  {
    private final Class<T> a;
    private final Iterable<? extends T>[] b;
    
    private b(Class<T> ☃, Iterable<? extends T>[] ☃)
    {
      this.a = ☃;
      this.b = ☃;
    }
    
    public Iterator<T[]> iterator()
    {
      if (this.b.length <= 0) {
        return Collections.singletonList((Object[])cm.a(this.a, 0)).iterator();
      }
      return new cm.b.a(this.a, this.b, null);
    }
    
    static class a<T>
      extends UnmodifiableIterator<T[]>
    {
      private int a = -2;
      private final Iterable<? extends T>[] b;
      private final Iterator<? extends T>[] c;
      private final T[] d;
      
      private a(Class<T> ☃, Iterable<? extends T>[] ☃)
      {
        this.b = ☃;
        this.c = ((Iterator[])cm.a(Iterator.class, this.b.length));
        for (int ☃ = 0; ☃ < this.b.length; ☃++) {
          this.c[☃] = ☃[☃].iterator();
        }
        this.d = cm.a(☃, this.c.length);
      }
      
      private void b()
      {
        this.a = -1;
        
        Arrays.fill(this.c, null);
        Arrays.fill(this.d, null);
      }
      
      public boolean hasNext()
      {
        if (this.a == -2)
        {
          this.a = 0;
          for (Iterator<? extends T> ☃ : this.c) {
            if (!☃.hasNext())
            {
              b();
              break;
            }
          }
          return true;
        }
        if (this.a >= this.c.length) {
          for (this.a = (this.c.length - 1); this.a >= 0; this.a -= 1)
          {
            Iterator<? extends T> ☃ = this.c[this.a];
            if (☃.hasNext()) {
              break;
            }
            if (this.a == 0)
            {
              b();
              break;
            }
            ☃ = this.b[this.a].iterator();
            this.c[this.a] = ☃;
            if (!☃.hasNext())
            {
              b();
              break;
            }
          }
        }
        return this.a >= 0;
      }
      
      public T[] a()
      {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        for (; this.a < this.c.length; this.a += 1) {
          this.d[this.a] = this.c[this.a].next();
        }
        return (Object[])this.d.clone();
      }
    }
  }
}
