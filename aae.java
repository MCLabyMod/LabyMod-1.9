public class aae
  extends zp
{
  private static final ke<Boolean> e = kh.a(aae.class, kg.h);
  
  public aae(aht ☃)
  {
    super(☃);
    a(0.3125F, 0.3125F);
  }
  
  public aae(aht ☃, sa ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃, ☃);
    
    a(0.3125F, 0.3125F);
  }
  
  protected float l()
  {
    return n() ? 0.73F : super.l();
  }
  
  public aae(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    a(0.3125F, 0.3125F);
  }
  
  public boolean aH()
  {
    return false;
  }
  
  public float a(ahp ☃, aht ☃, cj ☃, arc ☃)
  {
    float ☃ = super.a(☃, ☃, ☃, ☃);
    
    ajt ☃ = ☃.t();
    if ((n()) && (xo.a(☃))) {
      ☃ = Math.min(0.8F, ☃);
    }
    return ☃;
  }
  
  protected void a(bbi ☃)
  {
    if (!this.l.E)
    {
      if (☃.d != null)
      {
        if (this.a != null)
        {
          if (☃.d.a(rc.a(this.a), 8.0F)) {
            if (!☃.d.au()) {
              this.a.b(5.0F);
            } else {
              a(this.a, ☃.d);
            }
          }
        }
        else {
          ☃.d.a(rc.m, 5.0F);
        }
        if ((☃.d instanceof sa))
        {
          int ☃ = 0;
          if (this.l.ae() == qk.c) {
            ☃ = 10;
          } else if (this.l.ae() == qk.d) {
            ☃ = 40;
          }
          if (☃ > 0) {
            ((sa)☃.d).c(new rl(rm.t, 20 * ☃, 1));
          }
        }
      }
      this.l.a(this, this.p, this.q, this.r, 1.0F, false, this.l.U().b("mobGriefing"));
      T();
    }
  }
  
  public boolean ap()
  {
    return false;
  }
  
  public boolean a(rc ☃, float ☃)
  {
    return false;
  }
  
  protected void i()
  {
    this.Z.a(e, Boolean.valueOf(false));
  }
  
  public boolean n()
  {
    return ((Boolean)this.Z.a(e)).booleanValue();
  }
  
  public void a(boolean ☃)
  {
    this.Z.b(e, Boolean.valueOf(☃));
  }
  
  protected boolean k()
  {
    return false;
  }
}
