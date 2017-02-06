public class ke<T>
{
  private final int a;
  private final kf<T> b;
  
  public ke(int ☃, kf<T> ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public int a()
  {
    return this.a;
  }
  
  public kf<T> b()
  {
    return this.b;
  }
  
  public boolean equals(Object ☃)
  {
    if (this == ☃) {
      return true;
    }
    if ((☃ == null) || (getClass() != ☃.getClass())) {
      return false;
    }
    ke<?> ☃ = (ke)☃;
    
    return this.a == ☃.a;
  }
  
  public int hashCode()
  {
    return this.a;
  }
}
