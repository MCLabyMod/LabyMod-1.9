public abstract class te
  extends tk
{
  protected sb a;
  protected cj b = cj.a;
  protected akv c;
  boolean d;
  float e;
  float f;
  
  public te(sb ☃)
  {
    this.a = ☃;
    if (!(☃.x() instanceof ve)) {
      throw new IllegalArgumentException("Unsupported mob type for DoorInteractGoal");
    }
  }
  
  public boolean a()
  {
    if (!this.a.A) {
      return false;
    }
    ve ☃ = (ve)this.a.x();
    ayp ☃ = ☃.k();
    if ((☃ == null) || (☃.b()) || (!☃.f())) {
      return false;
    }
    for (int ☃ = 0; ☃ < Math.min(☃.e() + 2, ☃.d()); ☃++)
    {
      ayn ☃ = ☃.a(☃);
      this.b = new cj(☃.a, ☃.b + 1, ☃.c);
      if (this.a.e(this.b.p(), this.a.q, this.b.r()) <= 2.25D)
      {
        this.c = a(this.b);
        if (this.c != null) {
          return true;
        }
      }
    }
    this.b = new cj(this.a).a();
    this.c = a(this.b);
    return this.c != null;
  }
  
  public boolean b()
  {
    return !this.d;
  }
  
  public void c()
  {
    this.d = false;
    this.e = ((float)(this.b.p() + 0.5F - this.a.p));
    this.f = ((float)(this.b.r() + 0.5F - this.a.r));
  }
  
  public void e()
  {
    float ☃ = (float)(this.b.p() + 0.5F - this.a.p);
    float ☃ = (float)(this.b.r() + 0.5F - this.a.r);
    float ☃ = this.e * ☃ + this.f * ☃;
    if (☃ < 0.0F) {
      this.d = true;
    }
  }
  
  private akv a(cj ☃)
  {
    arc ☃ = this.a.l.o(☃);
    ajt ☃ = ☃.t();
    if (((☃ instanceof akv)) && (☃.a() == axe.d)) {
      return (akv)☃;
    }
    return null;
  }
}
