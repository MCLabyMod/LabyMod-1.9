import java.util.Random;

public class wg
  extends wi
{
  public float a;
  public float b;
  public float c;
  public float bt;
  public float bu;
  public float bv;
  public float bw;
  public float bx;
  private float by;
  private float bz;
  private float bA;
  private float bB;
  private float bC;
  private float bD;
  
  public wg(aht ☃)
  {
    super(☃);
    a(0.8F, 0.8F);
    this.S.setSeed(1 + O());
    this.bz = (1.0F / (this.S.nextFloat() + 1.0F) * 0.2F);
  }
  
  protected void r()
  {
    this.bp.a(0, new wg.a(this));
  }
  
  protected void bA()
  {
    super.bA();
    
    a(yt.a).a(10.0D);
  }
  
  public float bn()
  {
    return this.H * 0.5F;
  }
  
  protected nf G()
  {
    return ng.fW;
  }
  
  protected nf bR()
  {
    return ng.fY;
  }
  
  protected nf bS()
  {
    return ng.fX;
  }
  
  protected float cd()
  {
    return 0.4F;
  }
  
  protected boolean ae()
  {
    return false;
  }
  
  protected kk J()
  {
    return azt.af;
  }
  
  public boolean ai()
  {
    return super.ai();
  }
  
  public void n()
  {
    super.n();
    
    this.b = this.a;
    this.bt = this.c;
    
    this.bv = this.bu;
    this.bx = this.bw;
    
    this.bu += this.bz;
    if (this.bu > 6.283185307179586D) {
      if (this.l.E)
      {
        this.bu = 6.2831855F;
      }
      else
      {
        this.bu = ((float)(this.bu - 6.283185307179586D));
        if (this.S.nextInt(10) == 0) {
          this.bz = (1.0F / (this.S.nextFloat() + 1.0F) * 0.2F);
        }
        this.l.a(this, (byte)19);
      }
    }
    if (this.V)
    {
      if (this.bu < 3.1415927F)
      {
        float ☃ = this.bu / 3.1415927F;
        this.bw = (on.a(☃ * ☃ * 3.1415927F) * 3.1415927F * 0.25F);
        if (☃ > 0.75D)
        {
          this.by = 1.0F;
          this.bA = 1.0F;
        }
        else
        {
          this.bA *= 0.8F;
        }
      }
      else
      {
        this.bw = 0.0F;
        this.by *= 0.9F;
        this.bA *= 0.99F;
      }
      if (!this.l.E)
      {
        this.s = (this.bB * this.by);
        this.t = (this.bC * this.by);
        this.u = (this.bD * this.by);
      }
      float ☃ = on.a(this.s * this.s + this.u * this.u);
      
      this.aM += (-(float)on.b(this.s, this.u) * 57.295776F - this.aM) * 0.1F;
      this.v = this.aM;
      this.c = ((float)(this.c + 3.141592653589793D * this.bA * 1.5D));
      this.a += (-(float)on.b(☃, this.t) * 57.295776F - this.a) * 0.1F;
    }
    else
    {
      this.bw = (on.e(on.a(this.bu)) * 3.1415927F * 0.25F);
      if (!this.l.E)
      {
        this.s = 0.0D;
        this.u = 0.0D;
        if (a(rm.y)) {
          this.t += 0.05D * (b(rm.y).c() + 1) - this.t;
        } else {
          this.t -= 0.08D;
        }
        this.t *= 0.9800000190734863D;
      }
      this.a = ((float)(this.a + (-90.0F - this.a) * 0.02D));
    }
  }
  
  public void g(float ☃, float ☃)
  {
    d(this.s, this.t, this.u);
  }
  
  public boolean cF()
  {
    return (this.q > 45.0D) && (this.q < this.l.K()) && (super.cF());
  }
  
  public void a(byte ☃)
  {
    if (☃ == 19) {
      this.bu = 0.0F;
    } else {
      super.a(☃);
    }
  }
  
  public void b(float ☃, float ☃, float ☃)
  {
    this.bB = ☃;
    this.bC = ☃;
    this.bD = ☃;
  }
  
  public boolean o()
  {
    return (this.bB != 0.0F) || (this.bC != 0.0F) || (this.bD != 0.0F);
  }
  
  static class a
    extends tk
  {
    private wg a;
    
    public a(wg ☃)
    {
      this.a = ☃;
    }
    
    public boolean a()
    {
      return true;
    }
    
    public void e()
    {
      int ☃ = this.a.bK();
      if (☃ > 100)
      {
        this.a.b(0.0F, 0.0F, 0.0F);
      }
      else if ((this.a.bF().nextInt(50) == 0) || (!wg.a(this.a)) || (!this.a.o()))
      {
        float ☃ = this.a.bF().nextFloat() * 6.2831855F;
        float ☃ = on.b(☃) * 0.2F;
        float ☃ = -0.1F + this.a.bF().nextFloat() * 0.2F;
        float ☃ = on.a(☃) * 0.2F;
        this.a.b(☃, ☃, ☃);
      }
    }
  }
}
