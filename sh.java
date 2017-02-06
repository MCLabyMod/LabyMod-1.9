import java.util.UUID;

public abstract class sh
  extends sb
{
  public static final UUID bt = UUID.fromString("E199AD21-BA8A-4C53-8D13-6182D5C69D3A");
  public static final sn bu = new sn(bt, "Fleeing speed bonus", 2.0D, 2).a(false);
  
  public sh(aht ☃)
  {
    super(☃);
    
    this.c = new tw(this, 1.0D);
  }
  
  private cj a = cj.a;
  private float b = -1.0F;
  private tk c;
  private boolean bv;
  private float bw = aym.g.a();
  
  public float a(cj ☃)
  {
    return 0.0F;
  }
  
  public boolean cF()
  {
    return (super.cF()) && (a(new cj(this.p, bl().b, this.r)) >= 0.0F);
  }
  
  public boolean cT()
  {
    return !this.h.n();
  }
  
  public boolean cU()
  {
    return f(new cj(this));
  }
  
  public boolean f(cj ☃)
  {
    if (this.b == -1.0F) {
      return true;
    }
    return this.a.k(☃) < this.b * this.b;
  }
  
  public void a(cj ☃, int ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public cj cV()
  {
    return this.a;
  }
  
  public float cW()
  {
    return this.b;
  }
  
  public void cX()
  {
    this.b = -1.0F;
  }
  
  public boolean cY()
  {
    return this.b != -1.0F;
  }
  
  protected void cO()
  {
    super.cO();
    if ((cP()) && (cQ() != null) && (cQ().l == this.l))
    {
      rr ☃ = cQ();
      a(new cj((int)☃.p, (int)☃.q, (int)☃.r), 5);
      
      float ☃ = g(☃);
      if (((this instanceof sk)) && (((sk)this).db()))
      {
        if (☃ > 10.0F) {
          a(true, true);
        }
        return;
      }
      if (!this.bv)
      {
        this.bp.a(2, this.c);
        if ((x() instanceof ve))
        {
          this.bw = a(aym.g);
          a(aym.g, 0.0F);
        }
        this.bv = true;
      }
      q(☃);
      if (☃ > 4.0F) {
        x().a(☃, 1.0D);
      }
      if (☃ > 6.0F)
      {
        double ☃ = (☃.p - this.p) / ☃;
        double ☃ = (☃.q - this.q) / ☃;
        double ☃ = (☃.r - this.r) / ☃;
        
        this.s += ☃ * Math.abs(☃) * 0.4D;
        this.t += ☃ * Math.abs(☃) * 0.4D;
        this.u += ☃ * Math.abs(☃) * 0.4D;
      }
      if (☃ > 10.0F) {
        a(true, true);
      }
    }
    else if ((!cP()) && (this.bv))
    {
      this.bv = false;
      this.bp.a(this.c);
      if ((x() instanceof ve)) {
        a(aym.g, this.bw);
      }
      cX();
    }
  }
  
  protected void q(float ☃) {}
}
