public class zr
  extends zp
{
  public int e = 1;
  
  public zr(aht ☃)
  {
    super(☃);
  }
  
  public zr(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public zr(aht ☃, sa ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃, ☃);
  }
  
  protected void a(bbi ☃)
  {
    if (!this.l.E)
    {
      if (☃.d != null)
      {
        ☃.d.a(rc.a(this, this.a), 6.0F);
        a(this.a, ☃.d);
      }
      boolean ☃ = this.l.U().b("mobGriefing");
      this.l.a(null, this.p, this.q, this.r, this.e, ☃, ☃);
      T();
    }
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    ☃.a("ExplosionPower", this.e);
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    if (☃.b("ExplosionPower", 99)) {
      this.e = ☃.h("ExplosionPower");
    }
  }
}
