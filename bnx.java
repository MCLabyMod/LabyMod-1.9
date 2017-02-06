public class bnx
{
  protected final bno a;
  protected final aht b;
  protected int c;
  protected int d;
  protected int e;
  public bqf[] f;
  
  public bnx(aht ☃, int ☃, bno ☃, bqg ☃)
  {
    this.a = ☃;
    this.b = ☃;
    
    a(☃);
    a(☃);
  }
  
  protected void a(bqg ☃)
  {
    int ☃ = this.d * this.c * this.e;
    this.f = new bqf[☃];
    
    int ☃ = 0;
    for (int ☃ = 0; ☃ < this.d; ☃++) {
      for (int ☃ = 0; ☃ < this.c; ☃++) {
        for (int ☃ = 0; ☃ < this.e; ☃++)
        {
          int ☃ = (☃ * this.c + ☃) * this.d + ☃;
          cj ☃ = new cj(☃ * 16, ☃ * 16, ☃ * 16);
          this.f[☃] = ☃.a(this.b, this.a, ☃, ☃++);
        }
      }
    }
  }
  
  public void a()
  {
    for (bqf ☃ : this.f) {
      ☃.a();
    }
  }
  
  protected void a(int ☃)
  {
    int ☃ = ☃ * 2 + 1;
    this.d = ☃;
    this.c = 16;
    this.e = ☃;
  }
  
  public void a(double ☃, double ☃)
  {
    int ☃ = on.c(☃) - 8;
    int ☃ = on.c(☃) - 8;
    
    int ☃ = this.d * 16;
    for (int ☃ = 0; ☃ < this.d; ☃++)
    {
      int ☃ = a(☃, ☃, ☃);
      for (int ☃ = 0; ☃ < this.e; ☃++)
      {
        int ☃ = a(☃, ☃, ☃);
        for (int ☃ = 0; ☃ < this.c; ☃++)
        {
          int ☃ = ☃ * 16;
          
          bqf ☃ = this.f[((☃ * this.c + ☃) * this.d + ☃)];
          cj ☃ = new cj(☃, ☃, ☃);
          if (!☃.equals(☃.k())) {
            ☃.a(☃);
          }
        }
      }
    }
  }
  
  private int a(int ☃, int ☃, int ☃)
  {
    int ☃ = ☃ * 16;
    int ☃ = ☃ - ☃ + ☃ / 2;
    if (☃ < 0) {
      ☃ -= ☃ - 1;
    }
    return ☃ - ☃ / ☃ * ☃;
  }
  
  public void a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, boolean ☃)
  {
    int ☃ = on.a(☃, 16);
    int ☃ = on.a(☃, 16);
    int ☃ = on.a(☃, 16);
    int ☃ = on.a(☃, 16);
    int ☃ = on.a(☃, 16);
    int ☃ = on.a(☃, 16);
    for (int ☃ = ☃; ☃ <= ☃; ☃++)
    {
      int ☃ = ☃ % this.d;
      if (☃ < 0) {
        ☃ += this.d;
      }
      for (int ☃ = ☃; ☃ <= ☃; ☃++)
      {
        int ☃ = ☃ % this.c;
        if (☃ < 0) {
          ☃ += this.c;
        }
        for (int ☃ = ☃; ☃ <= ☃; ☃++)
        {
          int ☃ = ☃ % this.e;
          if (☃ < 0) {
            ☃ += this.e;
          }
          int ☃ = (☃ * this.c + ☃) * this.d + ☃;
          bqf ☃ = this.f[☃];
          ☃.a(☃);
        }
      }
    }
  }
  
  protected bqf a(cj ☃)
  {
    int ☃ = on.a(☃.p(), 16);
    int ☃ = on.a(☃.q(), 16);
    int ☃ = on.a(☃.r(), 16);
    if ((☃ < 0) || (☃ >= this.c)) {
      return null;
    }
    ☃ %= this.d;
    if (☃ < 0) {
      ☃ += this.d;
    }
    ☃ %= this.e;
    if (☃ < 0) {
      ☃ += this.e;
    }
    int ☃ = (☃ * this.c + ☃) * this.d + ☃;
    return this.f[☃];
  }
}
