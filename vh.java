public class vh
  extends ve
{
  private cj f;
  
  public vh(sb ☃, aht ☃)
  {
    super(☃, ☃);
  }
  
  public ayp a(cj ☃)
  {
    this.f = ☃;
    return super.a(☃);
  }
  
  public ayp a(rr ☃)
  {
    this.f = new cj(☃);
    return super.a(☃);
  }
  
  public boolean a(rr ☃, double ☃)
  {
    ayp ☃ = a(☃);
    if (☃ != null) {
      return a(☃, ☃);
    }
    this.f = new cj(☃);
    this.d = ☃;
    return true;
  }
  
  public void l()
  {
    if (n())
    {
      if (this.f != null)
      {
        double ☃ = this.a.G * this.a.G;
        if ((this.a.d(this.f) < ☃) || ((this.a.q > this.f.q()) && (this.a.d(new cj(this.f.p(), on.c(this.a.q), this.f.r())) < ☃))) {
          this.f = null;
        } else {
          this.a.u().a(this.f.p(), this.f.q(), this.f.r(), this.d);
        }
      }
      return;
    }
    super.l();
  }
}
