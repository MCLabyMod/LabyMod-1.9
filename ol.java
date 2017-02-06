public class ol<V>
{
  private transient ol.a<V>[] a = new ol.a['က'];
  private transient int b;
  private int c;
  private int d = 3072;
  private final float e = 0.75F;
  
  public ol()
  {
    this.c = (this.a.length - 1);
  }
  
  private static int g(long originalKey)
  {
    return (int)(originalKey ^ originalKey >>> 27);
  }
  
  private static int a(int p_76158_0_, int p_76158_1_)
  {
    return p_76158_0_ & p_76158_1_;
  }
  
  public int a()
  {
    return this.b;
  }
  
  public V a(long key)
  {
    int i = g(key);
    for (ol.a<V> entry = this.a[a(i, this.c)]; entry != null; entry = entry.c) {
      if (entry.a == key) {
        return (V)entry.b;
      }
    }
    return (V)null;
  }
  
  public boolean b(long p_76161_1_)
  {
    return c(p_76161_1_) != null;
  }
  
  final ol.a<V> c(long p_76160_1_)
  {
    int i = g(p_76160_1_);
    for (ol.a<V> entry = this.a[a(i, this.c)]; entry != null; entry = entry.c) {
      if (entry.a == p_76160_1_) {
        return entry;
      }
    }
    return null;
  }
  
  public void a(long p_76163_1_, V p_76163_3_)
  {
    int i = g(p_76163_1_);
    int j = a(i, this.c);
    for (ol.a<V> entry = this.a[j]; entry != null; entry = entry.c) {
      if (entry.a == p_76163_1_)
      {
        entry.b = p_76163_3_;
        return;
      }
    }
    a(i, p_76163_1_, p_76163_3_, j);
  }
  
  private void a(int p_76153_1_)
  {
    ol.a<V>[] entry = this.a;
    int i = entry.length;
    if (i == 1073741824)
    {
      this.d = Integer.MAX_VALUE;
    }
    else
    {
      ol.a<V>[] entry1 = new ol.a[p_76153_1_];
      a(entry1);
      this.a = entry1;
      this.c = (this.a.length - 1);
      getClass();this.d = ((int)(p_76153_1_ * 0.75F));
    }
  }
  
  private void a(ol.a<V>[] p_76154_1_)
  {
    ol.a<V>[] entry = this.a;
    int i = p_76154_1_.length;
    for (int j = 0; j < entry.length; j++)
    {
      ol.a<V> entry1 = entry[j];
      if (entry1 != null)
      {
        entry[j] = null;
        for (;;)
        {
          ol.a<V> entry2 = entry1.c;
          int k = a(entry1.d, i - 1);
          entry1.c = p_76154_1_[k];
          p_76154_1_[k] = entry1;
          entry1 = entry2;
          if (entry2 == null) {
            break;
          }
        }
      }
    }
  }
  
  public V d(long p_76159_1_)
  {
    ol.a<V> entry = e(p_76159_1_);
    return entry == null ? null : entry.b;
  }
  
  final ol.a<V> e(long p_76152_1_)
  {
    int i = g(p_76152_1_);
    int j = a(i, this.c);
    ol.a<V> entry = this.a[j];
    ol.a<V> entry2;
    for (ol.a<V> entry1 = entry; entry1 != null; entry1 = entry2)
    {
      entry2 = entry1.c;
      if (entry1.a == p_76152_1_)
      {
        this.b -= 1;
        if (entry == entry1) {
          this.a[j] = entry2;
        } else {
          entry.c = entry2;
        }
        return entry1;
      }
      entry = entry1;
    }
    return entry1;
  }
  
  private void a(int p_76156_1_, long p_76156_2_, V p_76156_4_, int p_76156_5_)
  {
    ol.a<V> entry = this.a[p_76156_5_];
    this.a[p_76156_5_] = new ol.a(p_76156_1_, p_76156_2_, p_76156_4_, entry);
    if (this.b++ >= this.d) {
      a(2 * this.a.length);
    }
  }
  
  static class a<V>
  {
    final long a;
    V b;
    a<V> c;
    final int d;
    
    a(int p_i1553_1_, long p_i1553_2_, V p_i1553_4_, a<V> p_i1553_5_)
    {
      this.b = p_i1553_4_;
      this.c = p_i1553_5_;
      this.a = p_i1553_2_;
      this.d = p_i1553_1_;
    }
    
    public final long a()
    {
      return this.a;
    }
    
    public final V b()
    {
      return (V)this.b;
    }
    
    public final boolean equals(Object p_equals_1_)
    {
      if (!(p_equals_1_ instanceof a)) {
        return false;
      }
      a<V> entry = (a)p_equals_1_;
      if (this.a == entry.a)
      {
        Object object = b();
        Object object1 = entry.b();
        if ((object == object1) || ((object != null) && (object.equals(object1)))) {
          return true;
        }
      }
      return false;
    }
    
    public final int hashCode()
    {
      return ol.f(this.a);
    }
    
    public final String toString()
    {
      return a() + "=" + b();
    }
  }
  
  public double getKeyDistribution()
  {
    int countValid = 0;
    for (int i = 0; i < this.a.length; i++) {
      if (this.a[i] != null) {
        countValid++;
      }
    }
    return 1.0D * countValid / this.b;
  }
}
