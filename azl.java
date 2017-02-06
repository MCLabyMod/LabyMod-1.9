public class azl
  implements Comparable<azl>
{
  private final String a;
  private final String b;
  private final long c;
  private final long d;
  private final boolean e;
  private final ahw.a f;
  private final boolean g;
  private final boolean h;
  private final String i;
  private final int j;
  private final boolean k;
  
  public azl(azh ☃, String ☃, String ☃, long ☃, boolean ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃.l();
    this.d = ☃;
    this.f = ☃.q();
    this.e = ☃;
    this.g = ☃.s();
    this.h = ☃.u();
    this.i = ☃.M();
    this.j = ☃.K();
    this.k = ☃.L();
  }
  
  public String a()
  {
    return this.a;
  }
  
  public String b()
  {
    return this.b;
  }
  
  public long c()
  {
    return this.d;
  }
  
  public boolean d()
  {
    return this.e;
  }
  
  public long e()
  {
    return this.c;
  }
  
  public int a(azl ☃)
  {
    if (this.c < ☃.c) {
      return 1;
    }
    if (this.c > ☃.c) {
      return -1;
    }
    return this.a.compareTo(☃.a);
  }
  
  public ahw.a f()
  {
    return this.f;
  }
  
  public boolean g()
  {
    return this.g;
  }
  
  public boolean h()
  {
    return this.h;
  }
  
  public String i()
  {
    if (os.b(this.i)) {
      return di.a("selectWorld.versionUnknown");
    }
    return this.i;
  }
  
  public boolean l()
  {
    return m();
  }
  
  public boolean m()
  {
    return this.j > 169;
  }
}
