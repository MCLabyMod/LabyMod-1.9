import java.util.Random;

public abstract class yq
  extends sh
  implements yl
{
  public yq(aht ☃)
  {
    super(☃);
    this.b_ = 5;
  }
  
  public nh bz()
  {
    return nh.f;
  }
  
  public void n()
  {
    bY();
    float ☃ = e(1.0F);
    if (☃ > 0.5F) {
      this.aU += 2;
    }
    super.n();
  }
  
  public void m()
  {
    super.m();
    if ((!this.l.E) && (this.l.ae() == qk.a)) {
      T();
    }
  }
  
  protected nf aa()
  {
    return ng.cF;
  }
  
  protected nf ab()
  {
    return ng.cE;
  }
  
  public boolean a(rc ☃, float ☃)
  {
    if (b(☃)) {
      return false;
    }
    return super.a(☃, ☃);
  }
  
  protected nf bR()
  {
    return ng.cC;
  }
  
  protected nf bS()
  {
    return ng.cB;
  }
  
  protected nf e(int ☃)
  {
    if (☃ > 4) {
      return ng.cA;
    }
    return ng.cD;
  }
  
  public boolean B(rr ☃)
  {
    float ☃ = (float)a(yt.e).e();
    int ☃ = 0;
    if ((☃ instanceof sa))
    {
      ☃ += ago.a(cb(), ((sa)☃).ca());
      ☃ += ago.a(this);
    }
    boolean ☃ = ☃.a(rc.a(this), ☃);
    if (☃)
    {
      if ((☃ > 0) && ((☃ instanceof sa)))
      {
        ((sa)☃).a(this, ☃ * 0.5F, on.a(this.v * 0.017453292F), -on.b(this.v * 0.017453292F));
        
        this.s *= 0.6D;
        this.u *= 0.6D;
      }
      int ☃ = ago.b(this);
      if (☃ > 0) {
        ☃.g(☃ * 4);
      }
      if ((☃ instanceof zj))
      {
        zj ☃ = (zj)☃;
        adq ☃ = cb();
        adq ☃ = ☃.cs() ? ☃.cv() : null;
        if ((☃ != null) && (☃ != null) && ((☃.b() instanceof abz)) && (☃.b() == ads.cQ))
        {
          float ☃ = 0.25F + ago.e(this) * 0.05F;
          if (this.S.nextFloat() < ☃)
          {
            ☃.da().a(ads.cQ, 100);
            this.l.a(☃, (byte)30);
          }
        }
      }
      a(this, ☃);
    }
    return ☃;
  }
  
  public float a(cj ☃)
  {
    return 0.5F - this.l.n(☃);
  }
  
  protected boolean s_()
  {
    cj ☃ = new cj(this.p, bl().b, this.r);
    if (this.l.b(ahz.a, ☃) > this.S.nextInt(32)) {
      return false;
    }
    int ☃ = this.l.k(☃);
    if (this.l.V())
    {
      int ☃ = this.l.af();
      this.l.c(10);
      ☃ = this.l.k(☃);
      this.l.c(☃);
    }
    return ☃ <= this.S.nextInt(8);
  }
  
  public boolean cF()
  {
    return (this.l.ae() != qk.a) && (s_()) && (super.cF());
  }
  
  protected void bA()
  {
    super.bA();
    
    bZ().b(yt.e);
  }
  
  protected boolean bD()
  {
    return true;
  }
}
