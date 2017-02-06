import java.util.Calendar;
import java.util.Random;

public class vu
  extends vt
{
  private static final ke<Byte> a = kh.a(vu.class, kg.a);
  private cj b;
  
  public vu(aht ☃)
  {
    super(☃);
    
    a(0.5F, 0.9F);
    a(true);
  }
  
  protected void i()
  {
    super.i();
    
    this.Z.a(a, Byte.valueOf((byte)0));
  }
  
  protected float cd()
  {
    return 0.1F;
  }
  
  protected float ce()
  {
    return super.ce() * 0.95F;
  }
  
  protected nf G()
  {
    if ((o()) && (this.S.nextInt(4) != 0)) {
      return null;
    }
    return ng.w;
  }
  
  protected nf bR()
  {
    return ng.y;
  }
  
  protected nf bS()
  {
    return ng.x;
  }
  
  public boolean aq()
  {
    return false;
  }
  
  protected void C(rr ☃) {}
  
  protected void cn() {}
  
  protected void bA()
  {
    super.bA();
    
    a(yt.a).a(6.0D);
  }
  
  public boolean o()
  {
    return (((Byte)this.Z.a(a)).byteValue() & 0x1) != 0;
  }
  
  public void a(boolean ☃)
  {
    byte ☃ = ((Byte)this.Z.a(a)).byteValue();
    if (☃) {
      this.Z.b(a, Byte.valueOf((byte)(☃ | 0x1)));
    } else {
      this.Z.b(a, Byte.valueOf((byte)(☃ & 0xFFFFFFFE)));
    }
  }
  
  public void m()
  {
    super.m();
    if (o())
    {
      this.s = (this.t = this.u = 0.0D);
      this.q = (on.c(this.q) + 1.0D - this.H);
    }
    else
    {
      this.t *= 0.6000000238418579D;
    }
  }
  
  protected void M()
  {
    super.M();
    
    cj ☃ = new cj(this);
    cj ☃ = ☃.a();
    if (o())
    {
      if (!this.l.o(☃).l())
      {
        a(false);
        this.l.a(null, 1025, ☃, 0);
      }
      else
      {
        if (this.S.nextInt(200) == 0) {
          this.aO = this.S.nextInt(360);
        }
        if (this.l.b(this, 4.0D) != null)
        {
          a(false);
          this.l.a(null, 1025, ☃, 0);
        }
      }
    }
    else
    {
      if ((this.b != null) && ((!this.l.d(this.b)) || (this.b.q() < 1))) {
        this.b = null;
      }
      if ((this.b == null) || (this.S.nextInt(30) == 0) || (this.b.e((int)this.p, (int)this.q, (int)this.r) < 4.0D)) {
        this.b = new cj((int)this.p + this.S.nextInt(7) - this.S.nextInt(7), (int)this.q + this.S.nextInt(6) - 2, (int)this.r + this.S.nextInt(7) - this.S.nextInt(7));
      }
      double ☃ = this.b.p() + 0.5D - this.p;
      double ☃ = this.b.q() + 0.1D - this.q;
      double ☃ = this.b.r() + 0.5D - this.r;
      
      this.s += (Math.signum(☃) * 0.5D - this.s) * 0.10000000149011612D;
      this.t += (Math.signum(☃) * 0.699999988079071D - this.t) * 0.10000000149011612D;
      this.u += (Math.signum(☃) * 0.5D - this.u) * 0.10000000149011612D;
      
      float ☃ = (float)(on.b(this.u, this.s) * 57.2957763671875D) - 90.0F;
      float ☃ = on.g(☃ - this.v);
      this.be = 0.5F;
      this.v += ☃;
      if ((this.S.nextInt(100) == 0) && (this.l.o(☃).l())) {
        a(true);
      }
    }
  }
  
  protected boolean ae()
  {
    return false;
  }
  
  public void e(float ☃, float ☃) {}
  
  protected void a(double ☃, boolean ☃, arc ☃, cj ☃) {}
  
  public boolean ba()
  {
    return true;
  }
  
  public boolean a(rc ☃, float ☃)
  {
    if (b(☃)) {
      return false;
    }
    if ((!this.l.E) && 
      (o())) {
      a(false);
    }
    return super.a(☃, ☃);
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    
    this.Z.b(a, Byte.valueOf(☃.f("BatFlags")));
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    
    ☃.a("BatFlags", ((Byte)this.Z.a(a)).byteValue());
  }
  
  public boolean cF()
  {
    cj ☃ = new cj(this.p, bl().b, this.r);
    if (☃.q() >= this.l.K()) {
      return false;
    }
    int ☃ = this.l.k(☃);
    int ☃ = 4;
    if (a(this.l.ac())) {
      ☃ = 7;
    } else if (this.S.nextBoolean()) {
      return false;
    }
    if (☃ > this.S.nextInt(☃)) {
      return false;
    }
    return super.cF();
  }
  
  private boolean a(Calendar ☃)
  {
    return ((☃.get(2) + 1 == 10) && (☃.get(5) >= 20)) || ((☃.get(2) + 1 == 11) && (☃.get(5) <= 3));
  }
  
  public float bn()
  {
    return this.H / 2.0F;
  }
  
  protected kk J()
  {
    return azt.ab;
  }
}
