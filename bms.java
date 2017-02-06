public class bms
  extends bmr
{
  private final bch i;
  
  public bms(bch ☃)
  {
    this.i = ☃;
  }
  
  public void a()
  {
    this.a = 0.0F;
    this.b = 0.0F;
    if (this.i.P.e())
    {
      this.b += 1.0F;
      this.c = true;
    }
    else
    {
      this.c = false;
    }
    if (this.i.R.e())
    {
      this.b -= 1.0F;
      this.d = true;
    }
    else
    {
      this.d = false;
    }
    if (this.i.Q.e())
    {
      this.a += 1.0F;
      this.e = true;
    }
    else
    {
      this.e = false;
    }
    if (this.i.S.e())
    {
      this.a -= 1.0F;
      this.f = true;
    }
    else
    {
      this.f = false;
    }
    this.g = this.i.T.e();
    this.h = this.i.U.e();
    if (this.h)
    {
      this.a = ((float)(this.a * 0.3D));
      this.b = ((float)(this.b * 0.3D));
    }
  }
}
