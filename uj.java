public class uj
  extends tk
{
  private sh a;
  private vo b;
  
  public uj(sh ☃)
  {
    this.a = ☃;
    if (!(☃.x() instanceof ve)) {
      throw new IllegalArgumentException("Unsupported mob type for RestrictOpenDoorGoal");
    }
  }
  
  public boolean a()
  {
    if (this.a.l.B()) {
      return false;
    }
    cj ☃ = new cj(this.a);
    
    vp ☃ = this.a.l.ai().a(☃, 16);
    if (☃ == null) {
      return false;
    }
    this.b = ☃.b(☃);
    if (this.b == null) {
      return false;
    }
    return this.b.b(☃) < 2.25D;
  }
  
  public boolean b()
  {
    if (this.a.l.B()) {
      return false;
    }
    return (!this.b.i()) && (this.b.c(new cj(this.a)));
  }
  
  public void c()
  {
    ((ve)this.a.x()).a(false);
    ((ve)this.a.x()).b(false);
  }
  
  public void d()
  {
    ((ve)this.a.x()).a(true);
    ((ve)this.a.x()).b(true);
    this.b = null;
  }
  
  public void e()
  {
    this.b.b();
  }
}
