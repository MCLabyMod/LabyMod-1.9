import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import java.util.Arrays;
import java.util.Iterator;

public class oa<K>
  implements cs<K>, Iterable<K>
{
  private static final Object a = null;
  private K[] b;
  private int[] c;
  private K[] d;
  private int e;
  private int f;
  
  public oa(int ☃)
  {
    ☃ = (int)(☃ / 0.8F);
    this.b = ((Object[])new Object[☃]);
    this.c = new int[☃];
    this.d = ((Object[])new Object[☃]);
  }
  
  public int a(K ☃)
  {
    return c(b(☃, d(☃)));
  }
  
  public K a(int ☃)
  {
    if ((☃ < 0) || (☃ >= this.d.length)) {
      return null;
    }
    return (K)this.d[☃];
  }
  
  private int c(int ☃)
  {
    if (☃ == -1) {
      return -1;
    }
    return this.c[☃];
  }
  
  public int c(K ☃)
  {
    int ☃ = c();
    
    a(☃, ☃);
    
    return ☃;
  }
  
  private int c()
  {
    while ((this.e < this.d.length) && (this.d[this.e] != null)) {
      this.e += 1;
    }
    return this.e++;
  }
  
  private void d(int ☃)
  {
    K[] ☃ = this.b;
    int[] ☃ = this.c;
    
    this.b = ((Object[])new Object[☃]);
    this.c = new int[☃];
    this.d = ((Object[])new Object[☃]);
    this.e = 0;
    this.f = 0;
    for (int ☃ = 0; ☃ < ☃.length; ☃++) {
      if (☃[☃] != null) {
        a(☃[☃], ☃[☃]);
      }
    }
  }
  
  public void a(K ☃, int ☃)
  {
    int ☃ = Math.max(☃, ++this.f);
    if (☃ >= this.b.length * 0.8F)
    {
      int ☃ = this.b.length << 1;
      while (☃ < ☃) {
        ☃ <<= 1;
      }
      d(☃);
    }
    int ☃ = e(d(☃));
    this.b[☃] = ☃;
    this.c[☃] = ☃;
    this.d[☃] = ☃;
  }
  
  private int d(K ☃)
  {
    return (on.f(System.identityHashCode(☃)) & 0x7FFFFFFF) % this.b.length;
  }
  
  private int b(K ☃, int ☃)
  {
    for (int ☃ = ☃; ☃ < this.b.length; ☃++)
    {
      if (this.b[☃] == ☃) {
        return ☃;
      }
      if (this.b[☃] == a) {
        return -1;
      }
    }
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      if (this.b[☃] == ☃) {
        return ☃;
      }
      if (this.b[☃] == a) {
        return -1;
      }
    }
    return -1;
  }
  
  private int e(int ☃)
  {
    StringBuilder ☃ = new StringBuilder("");
    for (int ☃ = ☃; ☃ < this.b.length; ☃++)
    {
      if (this.b[☃] == a) {
        return ☃;
      }
      ☃.append(☃).append(' ');
    }
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      if (this.b[☃] == a) {
        return ☃;
      }
      ☃.append(☃).append(' ');
    }
    throw new RuntimeException("Overflowed :(");
  }
  
  public Iterator<K> iterator()
  {
    return Iterators.filter(Iterators.forArray(this.d), Predicates.notNull());
  }
  
  public void a()
  {
    Arrays.fill(this.b, null);
    Arrays.fill(this.d, null);
    this.e = 0;
    this.f = 0;
  }
  
  public int b()
  {
    return this.f + 1;
  }
}
