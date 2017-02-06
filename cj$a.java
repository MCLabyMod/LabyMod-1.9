public final class cj$a
  extends cj
{
  private int c;
  private int d;
  private int e;
  
  public cj$a()
  {
    this(0, 0, 0);
  }
  
  public cj$a(cj ☃)
  {
    this(☃.p(), ☃.q(), ☃.r());
  }
  
  public cj$a(int ☃, int ☃, int ☃)
  {
    super(0, 0, 0);
    this.c = ☃;
    this.d = ☃;
    this.e = ☃;
  }
  
  public int p()
  {
    return this.c;
  }
  
  public int q()
  {
    return this.d;
  }
  
  public int r()
  {
    return this.e;
  }
  
  public a c(int ☃, int ☃, int ☃)
  {
    this.c = ☃;
    this.d = ☃;
    this.e = ☃;
    return this;
  }
  
  public void c(cq ☃)
  {
    this.c += ☃.g();
    this.d += ☃.h();
    this.e += ☃.i();
  }
  
  public void p(int ☃)
  {
    this.d = ☃;
  }
  
  public cj h()
  {
    return new cj(this);
  }
}
