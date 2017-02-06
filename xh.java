public class xh
  extends wx
{
  private int b;
  
  public xh(wu ☃)
  {
    super(☃);
  }
  
  public void c()
  {
    this.b += 1;
    sa ☃ = this.a.l.a(this.a, 20.0D, 10.0D);
    if (☃ != null)
    {
      if (this.b > 25)
      {
        this.a.cT().a(xk.h);
      }
      else
      {
        bbj ☃ = new bbj(☃.p - this.a.p, 0.0D, ☃.r - this.a.r).a();
        bbj ☃ = new bbj(on.a(this.a.v * 0.017453292F), 0.0D, -on.b(this.a.v * 0.017453292F)).a();
        float ☃ = (float)☃.b(☃);
        float ☃ = (float)(Math.acos(☃) * 57.2957763671875D) + 0.5F;
        if ((☃ < 0.0F) || (☃ > 10.0F))
        {
          double ☃ = ☃.p - this.a.bu.p;
          double ☃ = ☃.r - this.a.bu.r;
          double ☃ = on.a(on.g(180.0D - on.b(☃, ☃) * 57.2957763671875D - this.a.v), -100.0D, 100.0D);
          
          this.a.bf *= 0.8F;
          
          float ☃ = on.a(☃ * ☃ + ☃ * ☃) + 1.0F;
          float ☃ = ☃;
          if (☃ > 40.0F) {
            ☃ = 40.0F;
          }
          wu tmp300_297 = this.a;tmp300_297.bf = ((float)(tmp300_297.bf + ☃ * (0.7F / ☃ / ☃)));
          this.a.v += this.a.bf;
        }
      }
    }
    else if (this.b >= 100)
    {
      ☃ = this.a.l.a(this.a, 150.0D, 150.0D);
      this.a.cT().a(xk.e);
      if (☃ != null)
      {
        this.a.cT().a(xk.i);
        ((wy)this.a.cT().b(xk.i)).a(new bbj(☃.p, ☃.q, ☃.r));
      }
    }
  }
  
  public void d()
  {
    this.b = 0;
  }
  
  public xk<xh> i()
  {
    return xk.g;
  }
}
