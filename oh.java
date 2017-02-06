public class oh<V>
{
  private transient oh.a<V>[] a;
  private transient int b;
  private int c;
  private final float d;
  
  public oh()
  {
    this.d = 0.75F;
    this.c = 12;
    this.a = new oh.a[16];
  }
  
  private static int g(int ☃)
  {
    ☃ ^= ☃ >>> 20 ^ ☃ >>> 12;
    return ☃ ^ ☃ >>> 7 ^ ☃ >>> 4;
  }
  
  private static int a(int ☃, int ☃)
  {
    return ☃ & ☃ - 1;
  }
  
  public V a(int ☃)
  {
    int ☃ = g(☃);
    for (oh.a<V> ☃ = this.a[a(☃, this.a.length)]; ☃ != null; ☃ = ☃.c) {
      if (☃.a == ☃) {
        return (V)☃.b;
      }
    }
    return null;
  }
  
  public boolean b(int ☃)
  {
    return c(☃) != null;
  }
  
  final oh.a<V> c(int ☃)
  {
    int ☃ = g(☃);
    for (oh.a<V> ☃ = this.a[a(☃, this.a.length)]; ☃ != null; ☃ = ☃.c) {
      if (☃.a == ☃) {
        return ☃;
      }
    }
    return null;
  }
  
  public void a(int ☃, V ☃)
  {
    int ☃ = g(☃);
    int ☃ = a(☃, this.a.length);
    for (oh.a<V> ☃ = this.a[☃]; ☃ != null; ☃ = ☃.c) {
      if (☃.a == ☃)
      {
        ☃.b = ☃;
        return;
      }
    }
    a(☃, ☃, ☃, ☃);
  }
  
  private void h(int ☃)
  {
    oh.a<V>[] ☃ = this.a;
    int ☃ = ☃.length;
    if (☃ == 1073741824)
    {
      this.c = Integer.MAX_VALUE;
      return;
    }
    oh.a<V>[] ☃ = new oh.a[☃];
    a(☃);
    this.a = ☃;
    this.c = ((int)(☃ * this.d));
  }
  
  private void a(oh.a<V>[] ☃)
  {
    oh.a<V>[] ☃ = this.a;
    int ☃ = ☃.length;
    for (int ☃ = 0; ☃ < ☃.length; ☃++)
    {
      oh.a<V> ☃ = ☃[☃];
      if (☃ != null)
      {
        ☃[☃] = null;
        do
        {
          oh.a<V> ☃ = ☃.c;
          int ☃ = a(☃.d, ☃);
          ☃.c = ☃[☃];
          ☃[☃] = ☃;
          ☃ = ☃;
        } while (☃ != null);
      }
    }
  }
  
  public V d(int ☃)
  {
    oh.a<V> ☃ = e(☃);
    return ☃ == null ? null : ☃.b;
  }
  
  final oh.a<V> e(int ☃)
  {
    int ☃ = g(☃);
    int ☃ = a(☃, this.a.length);
    oh.a<V> ☃ = this.a[☃];
    oh.a<V> ☃ = ☃;
    while (☃ != null)
    {
      oh.a<V> ☃ = ☃.c;
      if (☃.a == ☃)
      {
        this.b -= 1;
        if (☃ == ☃) {
          this.a[☃] = ☃;
        } else {
          ☃.c = ☃;
        }
        return ☃;
      }
      ☃ = ☃;
      ☃ = ☃;
    }
    return ☃;
  }
  
  public void c()
  {
    oh.a<V>[] ☃ = this.a;
    for (int ☃ = 0; ☃ < ☃.length; ☃++) {
      ☃[☃] = null;
    }
    this.b = 0;
  }
  
  static class a<V>
  {
    final int a;
    V b;
    a<V> c;
    final int d;
    
    a(int ☃, int ☃, V ☃, a<V> ☃)
    {
      this.b = ☃;
      this.c = ☃;
      this.a = ☃;
      this.d = ☃;
    }
    
    public final int a()
    {
      return this.a;
    }
    
    public final V b()
    {
      return (V)this.b;
    }
    
    public final boolean equals(Object ☃)
    {
      if (!(☃ instanceof a)) {
        return false;
      }
      a<V> ☃ = (a)☃;
      if (this.a == ☃.a)
      {
        Object ☃ = b();
        Object ☃ = ☃.b();
        if ((☃ == ☃) || ((☃ != null) && (☃.equals(☃)))) {
          return true;
        }
      }
      return false;
    }
    
    public final int hashCode()
    {
      return oh.f(this.a);
    }
    
    public final String toString()
    {
      return a() + "=" + b();
    }
  }
  
  private void a(int ☃, int ☃, V ☃, int ☃)
  {
    oh.a<V> ☃ = this.a[☃];
    this.a[☃] = new oh.a(☃, ☃, ☃, ☃);
    if (this.b++ >= this.c) {
      h(2 * this.a.length);
    }
  }
}
