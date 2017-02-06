public class awn
{
  private amr a;
  private aoe b;
  private boolean c;
  private ajt d;
  private ahn e;
  private avp f;
  private boolean g;
  
  public awn()
  {
    this(amr.a, aoe.a, false, null, null);
  }
  
  public awn(amr ☃, aoe ☃, boolean ☃, ajt ☃, avp ☃)
  {
    this.b = ☃;
    this.a = ☃;
    this.c = ☃;
    this.d = ☃;
    this.e = null;
    this.f = ☃;
    this.g = true;
  }
  
  public awn a()
  {
    return new awn(this.a, this.b, this.c, this.d, this.f).a(this.e).b(this.g);
  }
  
  public awn a(amr ☃)
  {
    this.a = ☃;
    return this;
  }
  
  public awn a(aoe ☃)
  {
    this.b = ☃;
    return this;
  }
  
  public awn a(boolean ☃)
  {
    this.c = ☃;
    return this;
  }
  
  public awn a(ajt ☃)
  {
    this.d = ☃;
    return this;
  }
  
  public awn a(ahn ☃)
  {
    this.e = ☃;
    return this;
  }
  
  public awn a(avp ☃)
  {
    this.f = ☃;
    return this;
  }
  
  public amr b()
  {
    return this.a;
  }
  
  public awn b(boolean ☃)
  {
    this.g = ☃;
    return this;
  }
  
  public aoe c()
  {
    return this.b;
  }
  
  public boolean e()
  {
    return this.c;
  }
  
  public ajt f()
  {
    return this.d;
  }
  
  public avp g()
  {
    if ((this.f == null) && (this.e != null)) {
      i();
    }
    return this.f;
  }
  
  public boolean h()
  {
    return this.g;
  }
  
  void i()
  {
    this.f = b(this.e);
  }
  
  private avp b(ahn ☃)
  {
    if (☃ == null) {
      return null;
    }
    int ☃ = ☃.a * 16;
    int ☃ = ☃.b * 16;
    return new avp(☃, 0, ☃, ☃ + 16 - 1, 255, ☃ + 16 - 1);
  }
}
