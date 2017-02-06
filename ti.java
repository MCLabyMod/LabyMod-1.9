public class ti
  extends tk
{
  private sk d;
  private sa e;
  aht a;
  private double f;
  private vf g;
  private int h;
  float b;
  float c;
  private float i;
  
  public ti(sk ☃, double ☃, float ☃, float ☃)
  {
    this.d = ☃;
    this.a = ☃.l;
    this.f = ☃;
    this.g = ☃.x();
    this.c = ☃;
    this.b = ☃;
    a(3);
    if (!(☃.x() instanceof ve)) {
      throw new IllegalArgumentException("Unsupported mob type for FollowOwnerGoal");
    }
  }
  
  public boolean a()
  {
    sa ☃ = this.d.dc();
    if (☃ == null) {
      return false;
    }
    if (((☃ instanceof zj)) && (((zj)☃).y())) {
      return false;
    }
    if (this.d.db()) {
      return false;
    }
    if (this.d.h(☃) < this.c * this.c) {
      return false;
    }
    this.e = ☃;
    return true;
  }
  
  public boolean b()
  {
    return (!this.g.n()) && (this.d.h(this.e) > this.b * this.b) && (!this.d.db());
  }
  
  public void c()
  {
    this.h = 0;
    this.i = this.d.a(aym.g);
    this.d.a(aym.g, 0.0F);
  }
  
  public void d()
  {
    this.e = null;
    this.g.o();
    this.d.a(aym.g, this.i);
  }
  
  private boolean a(cj ☃)
  {
    arc ☃ = this.a.o(☃);
    ajt ☃ = ☃.t();
    if (☃ == aju.a) {
      return true;
    }
    return !☃.h();
  }
  
  public void e()
  {
    this.d.t().a(this.e, 10.0F, this.d.N());
    if (this.d.db()) {
      return;
    }
    if (--this.h > 0) {
      return;
    }
    this.h = 10;
    if (this.g.a(this.e, this.f)) {
      return;
    }
    if (this.d.cP()) {
      return;
    }
    if (this.d.h(this.e) < 144.0D) {
      return;
    }
    int ☃ = on.c(this.e.p) - 2;
    int ☃ = on.c(this.e.r) - 2;
    int ☃ = on.c(this.e.bl().b);
    for (int ☃ = 0; ☃ <= 4; ☃++) {
      for (int ☃ = 0; ☃ <= 4; ☃++) {
        if ((☃ < 1) || (☃ < 1) || (☃ > 3) || (☃ > 3)) {
          if ((this.a.o(new cj(☃ + ☃, ☃ - 1, ☃ + ☃)).q()) && (a(new cj(☃ + ☃, ☃, ☃ + ☃))) && (a(new cj(☃ + ☃, ☃ + 1, ☃ + ☃))))
          {
            this.d.b(☃ + ☃ + 0.5F, ☃, ☃ + ☃ + 0.5F, this.d.v, this.d.w);
            this.g.o();
            return;
          }
        }
      }
    }
  }
}
