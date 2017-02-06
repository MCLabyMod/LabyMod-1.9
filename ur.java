public class ur
  extends tn
{
  private int e;
  private ze f;
  
  public ur(ze ☃)
  {
    super(☃, ze.class, 3.0F, 0.02F);
    this.f = ☃;
  }
  
  public void c()
  {
    super.c();
    if ((this.f.dg()) && ((this.b instanceof ze)) && (((ze)this.b).dh())) {
      this.e = 10;
    } else {
      this.e = 0;
    }
  }
  
  public void e()
  {
    super.e();
    if (this.e > 0)
    {
      this.e -= 1;
      if (this.e == 0)
      {
        qv ☃ = this.f.de();
        for (int ☃ = 0; ☃ < ☃.u_(); ☃++)
        {
          adq ☃ = ☃.a(☃);
          adq ☃ = null;
          if (☃ != null)
          {
            ado ☃ = ☃.b();
            if (((☃ == ads.R) || (☃ == ads.cc) || (☃ == ads.cb) || (☃ == ads.cV)) && (☃.b > 3))
            {
              int ☃ = ☃.b / 2;
              ☃.b -= ☃;
              ☃ = new adq(☃, ☃, ☃.i());
            }
            else if ((☃ == ads.Q) && (☃.b > 5))
            {
              int ☃ = ☃.b / 2 / 3 * 3;
              int ☃ = ☃ / 3;
              ☃.b -= ☃;
              ☃ = new adq(ads.R, ☃, 0);
            }
            if (☃.b <= 0) {
              ☃.a(☃, null);
            }
          }
          if (☃ != null)
          {
            double ☃ = this.f.q - 0.30000001192092896D + this.f.bn();
            yd ☃ = new yd(this.f.l, this.f.p, ☃, this.f.r, ☃);
            float ☃ = 0.3F;
            float ☃ = this.f.aO;
            float ☃ = this.f.w;
            ☃.s = (-on.a(☃ * 0.017453292F) * on.b(☃ * 0.017453292F) * ☃);
            ☃.u = (on.b(☃ * 0.017453292F) * on.b(☃ * 0.017453292F) * ☃);
            ☃.t = (-on.a(☃ * 0.017453292F) * ☃ + 0.1F);
            ☃.q();
            
            this.f.l.a(☃);
            
            break;
          }
        }
      }
    }
  }
}
