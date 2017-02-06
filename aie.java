public class aie
  implements Comparable<aie>
{
  private static long d;
  private final ajt e;
  public final cj a;
  public long b;
  public int c;
  private long f = d++;
  
  public aie(cj ☃, ajt ☃)
  {
    this.a = ☃;
    this.e = ☃;
  }
  
  public boolean equals(Object ☃)
  {
    if ((☃ instanceof aie))
    {
      aie ☃ = (aie)☃;
      return (this.a.equals(☃.a)) && (ajt.a(this.e, ☃.e));
    }
    return false;
  }
  
  public int hashCode()
  {
    return this.a.hashCode();
  }
  
  public aie a(long ☃)
  {
    this.b = ☃;
    return this;
  }
  
  public void a(int ☃)
  {
    this.c = ☃;
  }
  
  public int a(aie ☃)
  {
    if (this.b < ☃.b) {
      return -1;
    }
    if (this.b > ☃.b) {
      return 1;
    }
    if (this.c != ☃.c) {
      return this.c - ☃.c;
    }
    if (this.f < ☃.f) {
      return -1;
    }
    if (this.f > ☃.f) {
      return 1;
    }
    return 0;
  }
  
  public String toString()
  {
    return ajt.a(this.e) + ": " + this.a + ", " + this.b + ", " + this.c + ", " + this.f;
  }
  
  public ajt a()
  {
    return this.e;
  }
}
