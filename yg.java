import java.util.Random;

public class yg
  extends yq
{
  private float a = 0.5F;
  private int b;
  private static final ke<Byte> c = kh.a(yg.class, kg.a);
  
  public yg(aht ☃)
  {
    super(☃);
    
    a(aym.g, -1.0F);
    a(aym.f, 8.0F);
    a(aym.i, 0.0F);
    a(aym.j, 0.0F);
    this.Y = true;
    this.b_ = 10;
  }
  
  protected void r()
  {
    this.bp.a(4, new yg.a(this));
    this.bp.a(5, new tw(this, 1.0D));
    this.bp.a(7, new ug(this, 1.0D));
    this.bp.a(8, new tp(this, zj.class, 8.0F));
    this.bp.a(8, new uf(this));
    
    this.bq.a(1, new uv(this, true, new Class[0]));
    this.bq.a(2, new uy(this, zj.class, true));
  }
  
  protected void bA()
  {
    super.bA();
    a(yt.e).a(6.0D);
    a(yt.d).a(0.23000000417232513D);
    a(yt.b).a(48.0D);
  }
  
  protected void i()
  {
    super.i();
    
    this.Z.a(c, Byte.valueOf((byte)0));
  }
  
  protected nf G()
  {
    return ng.B;
  }
  
  protected nf bR()
  {
    return ng.E;
  }
  
  protected nf bS()
  {
    return ng.D;
  }
  
  public int d(float ☃)
  {
    return 15728880;
  }
  
  public float e(float ☃)
  {
    return 1.0F;
  }
  
  public void n()
  {
    if ((!this.z) && (this.t < 0.0D)) {
      this.t *= 0.6D;
    }
    if (this.l.E)
    {
      if ((this.S.nextInt(24) == 0) && (!ad())) {
        this.l.a(this.p + 0.5D, this.q + 0.5D, this.r + 0.5D, ng.C, bz(), 1.0F + this.S.nextFloat(), this.S.nextFloat() * 0.7F + 0.3F, false);
      }
      for (int ☃ = 0; ☃ < 2; ☃++) {
        this.l.a(cy.m, this.p + (this.S.nextDouble() - 0.5D) * this.G, this.q + this.S.nextDouble() * this.H, this.r + (this.S.nextDouble() - 0.5D) * this.G, 0.0D, 0.0D, 0.0D, new int[0]);
      }
    }
    super.n();
  }
  
  protected void M()
  {
    if (ah()) {
      a(rc.f, 1.0F);
    }
    this.b -= 1;
    if (this.b <= 0)
    {
      this.b = 100;
      this.a = (0.5F + (float)this.S.nextGaussian() * 3.0F);
    }
    sa ☃ = A();
    if ((☃ != null) && (☃.q + ☃.bn() > this.q + bn() + this.a))
    {
      this.t += (0.30000001192092896D - this.t) * 0.30000001192092896D;
      this.ai = true;
    }
    super.M();
  }
  
  public void e(float ☃, float ☃) {}
  
  public boolean aH()
  {
    return o();
  }
  
  protected kk J()
  {
    return azt.o;
  }
  
  public boolean o()
  {
    return (((Byte)this.Z.a(c)).byteValue() & 0x1) != 0;
  }
  
  public void a(boolean ☃)
  {
    byte ☃ = ((Byte)this.Z.a(c)).byteValue();
    if (☃) {
      ☃ = (byte)(☃ | 0x1);
    } else {
      ☃ = (byte)(☃ & 0xFFFFFFFE);
    }
    this.Z.b(c, Byte.valueOf(☃));
  }
  
  protected boolean s_()
  {
    return true;
  }
  
  static class a
    extends tk
  {
    private yg a;
    private int b;
    private int c;
    
    public a(yg ☃)
    {
      this.a = ☃;
      
      a(3);
    }
    
    public boolean a()
    {
      sa ☃ = this.a.A();
      if ((☃ == null) || (!☃.au())) {
        return false;
      }
      return true;
    }
    
    public void c()
    {
      this.b = 0;
    }
    
    public void d()
    {
      this.a.a(false);
    }
    
    public void e()
    {
      this.c -= 1;
      
      sa ☃ = this.a.A();
      
      double ☃ = this.a.h(☃);
      if (☃ < 4.0D)
      {
        if (this.c <= 0)
        {
          this.c = 20;
          this.a.B(☃);
        }
        this.a.u().a(☃.p, ☃.q, ☃.r, 1.0D);
      }
      else if (☃ < 256.0D)
      {
        double ☃ = ☃.p - this.a.p;
        double ☃ = ☃.bl().b + ☃.H / 2.0F - (this.a.q + this.a.H / 2.0F);
        double ☃ = ☃.r - this.a.r;
        if (this.c <= 0)
        {
          this.b += 1;
          if (this.b == 1)
          {
            this.c = 60;
            this.a.a(true);
          }
          else if (this.b <= 4)
          {
            this.c = 6;
          }
          else
          {
            this.c = 100;
            this.b = 0;
            this.a.a(false);
          }
          if (this.b > 1)
          {
            float ☃ = on.c(on.a(☃)) * 0.5F;
            
            this.a.l.a(null, 1018, new cj((int)this.a.p, (int)this.a.q, (int)this.a.r), 0);
            for (int ☃ = 0; ☃ < 1; ☃++)
            {
              zv ☃ = new zv(this.a.l, this.a, ☃ + this.a.bF().nextGaussian() * ☃, ☃, ☃ + this.a.bF().nextGaussian() * ☃);
              ☃.q = (this.a.q + this.a.H / 2.0F + 0.5D);
              this.a.l.a(☃);
            }
          }
        }
        this.a.t().a(☃, 10.0F, 10.0F);
      }
      else
      {
        this.a.x().o();
        this.a.u().a(☃.p, ☃.q, ☃.r, 1.0D);
      }
      super.e();
    }
  }
}
