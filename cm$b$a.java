import com.google.common.collect.UnmodifiableIterator;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

class cm$b$a<T>
  extends UnmodifiableIterator<T[]>
{
  private int a = -2;
  private final Iterable<? extends T>[] b;
  private final Iterator<? extends T>[] c;
  private final T[] d;
  
  private cm$b$a(Class<T> ☃, Iterable<? extends T>[] ☃)
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
