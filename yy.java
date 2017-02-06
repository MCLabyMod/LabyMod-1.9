import java.util.Random;

public class yy
  extends yq
{
  private static final ke<Byte> a = kh.a(yy.class, kg.a);
  
  public yy(aht ☃)
  {
    super(☃);
    a(1.4F, 0.9F);
  }
  
  protected void r()
  {
    this.bp.a(1, new th(this));
    
    this.bp.a(3, new to(this, 0.4F));
    this.bp.a(4, new yy.a(this));
    
    this.bp.a(5, new ug(this, 0.8D));
    this.bp.a(6, new tp(this, zj.class, 8.0F));
    this.bp.a(6, new uf(this));
    
    this.bq.a(1, new uv(this, false, new Class[0]));
    this.bq.a(2, new yy.c(this, zj.class));
    this.bq.a(3, new yy.c(this, wh.class));
  }
  
  public double ay()
  {
    return this.H * 0.5F;
  }
  
  protected vf b(aht ☃)
  {
    return new vh(this, ☃);
  }
  
  protected void i()
  {
    super.i();
    
    this.Z.a(a, Byte.valueOf((byte)0));
  }
  
  public void m()
  {
    super.m();
    if (!this.l.E) {
      a(this.A);
    }
  }
  
  protected void bA()
  {
    super.bA();
    
    a(yt.a).a(16.0D);
    a(yt.d).a(0.30000001192092896D);
  }
  
  protected nf G()
  {
    return ng.fQ;
  }
  
  protected nf bR()
  {
    return ng.fS;
  }
  
  protected nf bS()
  {
    return ng.fR;
  }
  
  protected void a(cj ☃, ajt ☃)
  {
    a(ng.fT, 0.15F, 1.0F);
  }
  
  protected kk J()
  {
    return azt.q;
  }
  
  public boolean n_()
  {
    return o();
  }
  
  public void aQ() {}
  
  public sf ca()
  {
    return sf.c;
  }
  
  public boolean d(rl ☃)
  {
    if (☃.a() == rm.s) {
      return false;
    }
    return super.d(☃);
  }
  
  public boolean o()
  {
    return (((Byte)this.Z.a(a)).byteValue() & 0x1) != 0;
  }
  
  public void a(boolean ☃)
  {
    byte ☃ = ((Byte)this.Z.a(a)).byteValue();
    if (☃) {
      ☃ = (byte)(☃ | 0x1);
    } else {
      ☃ = (byte)(☃ & 0xFFFFFFFE);
    }
    this.Z.b(a, Byte.valueOf(☃));
  }
  
  public sd a(ql ☃, sd ☃)
  {
    ☃ = super.a(☃, ☃);
    if (this.l.r.nextInt(100) == 0)
    {
      yw ☃ = new yw(this.l);
      ☃.b(this.p, this.q, this.r, this.v, 0.0F);
      ☃.a(☃, null);
      this.l.a(☃);
      ☃.m(this);
    }
    if (☃ == null)
    {
      ☃ = new yy.b();
      if ((this.l.ae() == qk.d) && (this.l.r.nextFloat() < 0.1F * ☃.c())) {
        ((yy.b)☃).a(this.l.r);
      }
    }
    if ((☃ instanceof yy.b))
    {
      rk ☃ = ((yy.b)☃).a;
      if (☃ != null) {
        c(new rl(☃, Integer.MAX_VALUE));
      }
    }
    return ☃;
  }
  
  public float bn()
  {
    return 0.65F;
  }
  
  public static class b
    implements sd
  {
    public rk a;
    
    public void a(Random ☃)
    {
      int ☃ = ☃.nextInt(5);
      if (☃ <= 1) {
        this.a = rm.a;
      } else if (☃ <= 2) {
        this.a = rm.e;
      } else if (☃ <= 3) {
        this.a = rm.j;
      } else if (☃ <= 4) {
        this.a = rm.n;
      }
    }
  }
  
  static class a
    extends ts
  {
    public a(yy ☃)
    {
      super(1.0D, true);
    }
    
    public boolean b()
    {
      float ☃ = this.b.e(1.0F);
      if ((☃ >= 0.5F) && (this.b.bF().nextInt(100) == 0))
      {
        this.b.c(null);
        return false;
      }
      return super.b();
    }
    
    protected double a(sa ☃)
    {
      return 4.0F + ☃.G;
    }
  }
  
  static class c<T extends sa>
    extends uy<T>
  {
    public c(yy ☃, Class<T> ☃)
    {
      super(☃, true);
    }
    
    public boolean a()
    {
      float ☃ = this.e.e(1.0F);
      if (☃ >= 0.5F) {
        return false;
      }
      return super.a();
    }
  }
}
