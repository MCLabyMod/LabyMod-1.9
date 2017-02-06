public class aol
  extends amb
{
  public aol()
  {
    super(axe.B, false, axf.c);
    a(acq.c);
    this.z = 0.8F;
  }
  
  public ahm f()
  {
    return ahm.d;
  }
  
  public void a(aht ☃, cj ☃, rr ☃, float ☃)
  {
    if (☃.aK()) {
      super.a(☃, ☃, ☃, ☃);
    } else {
      ☃.e(☃, 0.0F);
    }
  }
  
  public void a(aht ☃, rr ☃)
  {
    if (☃.aK())
    {
      super.a(☃, ☃);
    }
    else if (☃.t < 0.0D)
    {
      ☃.t = (-☃.t);
      if (!(☃ instanceof sa)) {
        ☃.t *= 0.8D;
      }
    }
  }
  
  public void a(aht ☃, cj ☃, rr ☃)
  {
    if ((Math.abs(☃.t) < 0.1D) && (!☃.aK()))
    {
      double ☃ = 0.4D + Math.abs(☃.t) * 0.2D;
      ☃.s *= ☃;
      ☃.u *= ☃;
    }
    super.a(☃, ☃, ☃);
  }
}
