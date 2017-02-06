import java.util.Random;

class yx$d
  extends sy
{
  private float i;
  private int j;
  private yx k;
  private boolean l;
  
  public yx$d(yx ☃)
  {
    super(☃);
    this.k = ☃;
    this.i = (180.0F * ☃.v / 3.1415927F);
  }
  
  public void a(float ☃, boolean ☃)
  {
    this.i = ☃;
    this.l = ☃;
  }
  
  public void a(double ☃)
  {
    this.e = ☃;
    this.h = sy.a.b;
  }
  
  public void c()
  {
    this.a.v = a(this.a.v, this.i, 90.0F);
    this.a.aO = this.a.v;
    this.a.aM = this.a.v;
    if (this.h != sy.a.b)
    {
      this.a.o(0.0F);
      return;
    }
    this.h = sy.a.a;
    if (this.a.z)
    {
      this.a.l((float)(this.e * this.a.a(yt.d).e()));
      if (this.j-- <= 0)
      {
        this.j = this.k.cU();
        if (this.l) {
          this.j /= 3;
        }
        this.k.w().a();
        if (this.k.dc()) {
          this.k.a(this.k.cZ(), this.k.cd(), ((this.k.bF().nextFloat() - this.k.bF().nextFloat()) * 0.2F + 1.0F) * 0.8F);
        }
      }
      else
      {
        this.k.bd = (this.k.be = 0.0F);
        this.a.l(0.0F);
      }
    }
    else
    {
      this.a.l((float)(this.e * this.a.a(yt.d).e()));
    }
  }
}
