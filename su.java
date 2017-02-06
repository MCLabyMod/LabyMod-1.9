import java.util.List;

public class su
{
  private sa a;
  private int b;
  private float c;
  
  public su(sa ☃)
  {
    this.a = ☃;
  }
  
  public void a()
  {
    double ☃ = this.a.p - this.a.m;
    double ☃ = this.a.r - this.a.o;
    if (☃ * ☃ + ☃ * ☃ > 2.500000277905201E-7D)
    {
      this.a.aM = this.a.v;
      this.a.aO = a(this.a.aM, this.a.aO, 75.0F);
      this.c = this.a.aO;
      this.b = 0;
      return;
    }
    if ((this.a.bu().isEmpty()) || (!(this.a.bu().get(0) instanceof sb)))
    {
      float ☃ = 75.0F;
      if (Math.abs(this.a.aO - this.c) > 15.0F)
      {
        this.b = 0;
        this.c = this.a.aO;
      }
      else
      {
        this.b += 1;
        int ☃ = 10;
        if (this.b > 10) {
          ☃ = Math.max(1.0F - (this.b - 10) / 10.0F, 0.0F) * 75.0F;
        }
      }
      this.a.aM = a(this.a.aO, this.a.aM, ☃);
    }
  }
  
  private float a(float ☃, float ☃, float ☃)
  {
    float ☃ = on.g(☃ - ☃);
    if (☃ < -☃) {
      ☃ = -☃;
    }
    if (☃ >= ☃) {
      ☃ = ☃;
    }
    return ☃ - ☃;
  }
}
