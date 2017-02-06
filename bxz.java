public abstract class bxz
  implements byi
{
  protected byf a;
  private byz l;
  protected nh b;
  protected kk c;
  protected float d = 1.0F;
  protected float e = 1.0F;
  protected float f;
  protected float g;
  protected float h;
  protected boolean i;
  protected int j;
  protected byi.a k = byi.a.b;
  
  protected bxz(nf ☃, nh ☃)
  {
    this(☃.a(), ☃);
  }
  
  protected bxz(kk ☃, nh ☃)
  {
    this.c = ☃;
    this.b = ☃;
  }
  
  public kk a()
  {
    return this.c;
  }
  
  public byz a(byx ☃)
  {
    this.l = ☃.a(this.c);
    if (this.l == null) {
      this.a = byx.a;
    } else {
      this.a = this.l.a();
    }
    return this.l;
  }
  
  public byf b()
  {
    return this.a;
  }
  
  public nh d()
  {
    return this.b;
  }
  
  public boolean e()
  {
    return this.i;
  }
  
  public int f()
  {
    return this.j;
  }
  
  public float g()
  {
    return this.d * this.a.c();
  }
  
  public float h()
  {
    return this.e * this.a.d();
  }
  
  public float i()
  {
    return this.f;
  }
  
  public float j()
  {
    return this.g;
  }
  
  public float k()
  {
    return this.h;
  }
  
  public byi.a l()
  {
    return this.k;
  }
}
