class ol$a<V>
{
  final long a;
  V b;
  a<V> c;
  final int d;
  
  ol$a(int p_i1553_1_, long p_i1553_2_, V p_i1553_4_, a<V> p_i1553_5_)
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
