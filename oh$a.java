class oh$a<V>
{
  final int a;
  V b;
  a<V> c;
  final int d;
  
  oh$a(int ☃, int ☃, V ☃, a<V> ☃)
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
