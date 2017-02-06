public class bcl
{
  float a;
  private double f;
  public int b;
  public float c;
  public float d = 1.0F;
  public float e;
  private long g;
  private long h;
  private long i;
  private double j = 1.0D;
  
  public bcl(float ☃)
  {
    this.a = ☃;
    this.g = bcf.I();
    this.h = (System.nanoTime() / 1000000L);
  }
  
  public void a()
  {
    long ☃ = bcf.I();
    long ☃ = ☃ - this.g;
    long ☃ = System.nanoTime() / 1000000L;
    double ☃ = ☃ / 1000.0D;
    if ((☃ > 1000L) || (☃ < 0L))
    {
      this.f = ☃;
    }
    else
    {
      this.i += ☃;
      if (this.i > 1000L)
      {
        long ☃ = ☃ - this.h;
        
        double ☃ = this.i / ☃;
        this.j += (☃ - this.j) * 0.20000000298023224D;
        
        this.h = ☃;
        this.i = 0L;
      }
      if (this.i < 0L) {
        this.h = ☃;
      }
    }
    this.g = ☃;
    
    double ☃ = (☃ - this.f) * this.j;
    this.f = ☃;
    
    ☃ = on.a(☃, 0.0D, 1.0D);
    
    this.e = ((float)(this.e + ☃ * this.d * this.a));
    
    this.b = ((int)this.e);
    this.e -= this.b;
    if (this.b > 10) {
      this.b = 10;
    }
    this.c = this.e;
  }
}
