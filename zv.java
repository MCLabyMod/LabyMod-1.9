public class zv
  extends zp
{
  public zv(aht ☃)
  {
    super(☃);
    a(0.3125F, 0.3125F);
  }
  
  public zv(aht ☃, sa ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃, ☃);
    
    a(0.3125F, 0.3125F);
  }
  
  public zv(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    a(0.3125F, 0.3125F);
  }
  
  protected void a(bbi ☃)
  {
    if (!this.l.E)
    {
      if (☃.d != null)
      {
        if (!☃.d.ag())
        {
          boolean ☃ = ☃.d.a(rc.a(this, this.a), 5.0F);
          if (☃)
          {
            a(this.a, ☃.d);
            ☃.d.g(5);
          }
        }
      }
      else
      {
        boolean ☃ = true;
        if ((this.a != null) && ((this.a instanceof sb))) {
          ☃ = this.l.U().b("mobGriefing");
        }
        if (☃)
        {
          cj ☃ = ☃.a().a(☃.b);
          if (this.l.d(☃)) {
            this.l.a(☃, aju.ab.u());
          }
        }
      }
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
}
