import com.google.common.base.Predicate;
import java.util.Random;

public class wh
  extends vz
{
  protected static final ke<Byte> a = kh.a(wh.class, kg.a);
  private int c;
  vp b;
  private int bv;
  private int bw;
  
  public wh(aht ☃)
  {
    super(☃);
    a(1.4F, 2.7F);
  }
  
  protected void r()
  {
    this.bp.a(1, new ts(this, 1.0D, true));
    this.bp.a(2, new tx(this, 0.9D, 32.0F));
    this.bp.a(3, new tu(this, 0.6D, true));
    this.bp.a(4, new tw(this, 1.0D));
    this.bp.a(5, new ua(this));
    this.bp.a(6, new ug(this, 0.6D));
    this.bp.a(7, new tp(this, zj.class, 6.0F));
    this.bp.a(8, new uf(this));
    
    this.bq.a(1, new uu(this));
    this.bq.a(2, new uv(this, false, new Class[0]));
    this.bq.a(3, new uy(this, sb.class, 10, false, true, new Predicate()
    {
      public boolean a(sb ☃)
      {
        return (☃ != null) && (yl.e.apply(☃)) && (!(☃ instanceof yi));
      }
    }));
  }
  
  protected void i()
  {
    super.i();
    this.Z.a(a, Byte.valueOf((byte)0));
  }
  
  protected void M()
  {
    if (--this.c <= 0)
    {
      this.c = (70 + this.S.nextInt(50));
      this.b = this.l.ai().a(new cj(this), 32);
      if (this.b == null)
      {
        cX();
      }
      else
      {
        cj ☃ = this.b.a();
        a(☃, (int)(this.b.b() * 0.6F));
      }
    }
    super.M();
  }
  
  protected void bA()
  {
    super.bA();
    
    a(yt.a).a(100.0D);
    a(yt.d).a(0.25D);
    a(yt.c).a(1.0D);
  }
  
  protected int d(int ☃)
  {
    return ☃;
  }
  
  protected void C(rr ☃)
  {
    if (((☃ instanceof yl)) && (!(☃ instanceof yi)) && 
      (bF().nextInt(20) == 0)) {
      c((sa)☃);
    }
    super.C(☃);
  }
  
  public void n()
  {
    super.n();
    if (this.bv > 0) {
      this.bv -= 1;
    }
    if (this.bw > 0) {
      this.bw -= 1;
    }
    if ((this.s * this.s + this.u * this.u > 2.500000277905201E-7D) && (this.S.nextInt(5) == 0))
    {
      int ☃ = on.c(this.p);
      int ☃ = on.c(this.q - 0.20000000298023224D);
      int ☃ = on.c(this.r);
      arc ☃ = this.l.o(new cj(☃, ☃, ☃));
      if (☃.a() != axe.a) {
        this.l.a(cy.L, this.p + (this.S.nextFloat() - 0.5D) * this.G, bl().b + 0.1D, this.r + (this.S.nextFloat() - 0.5D) * this.G, 4.0D * (this.S.nextFloat() - 0.5D), 0.5D, (this.S.nextFloat() - 0.5D) * 4.0D, new int[] { ajt.j(☃) });
      }
    }
  }
  
  public boolean d(Class<? extends sa> ☃)
  {
    if ((db()) && (zj.class.isAssignableFrom(☃))) {
      return false;
    }
    if (☃ == yi.class) {
      return false;
    }
    return super.d(☃);
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    ☃.a("PlayerCreated", db());
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    o(☃.p("PlayerCreated"));
  }
  
  public boolean B(rr ☃)
  {
    this.bv = 10;
    this.l.a(this, (byte)4);
    boolean ☃ = ☃.a(rc.a(this), 7 + this.S.nextInt(15));
    if (☃)
    {
      ☃.t += 0.4000000059604645D;
      a(this, ☃);
    }
    a(ng.cG, 1.0F, 1.0F);
    return ☃;
  }
  
  public void a(byte ☃)
  {
    if (☃ == 4)
    {
      this.bv = 10;
      a(ng.cG, 1.0F, 1.0F);
    }
    else if (☃ == 11)
    {
      this.bw = 400;
    }
    else
    {
      super.a(☃);
    }
  }
  
  public vp o()
  {
    return this.b;
  }
  
  public int cZ()
  {
    return this.bv;
  }
  
  public void a(boolean ☃)
  {
    this.bw = (☃ ? 400 : 0);
    this.l.a(this, (byte)11);
  }
  
  protected nf bR()
  {
    return ng.cI;
  }
  
  protected nf bS()
  {
    return ng.cH;
  }
  
  protected void a(cj ☃, ajt ☃)
  {
    a(ng.cJ, 1.0F, 1.0F);
  }
  
  protected kk J()
  {
    return azt.y;
  }
  
  public int da()
  {
    return this.bw;
  }
  
  public boolean db()
  {
    return (((Byte)this.Z.a(a)).byteValue() & 0x1) != 0;
  }
  
  public void o(boolean ☃)
  {
    byte ☃ = ((Byte)this.Z.a(a)).byteValue();
    if (☃) {
      this.Z.b(a, Byte.valueOf((byte)(☃ | 0x1)));
    } else {
      this.Z.b(a, Byte.valueOf((byte)(☃ & 0xFFFFFFFE)));
    }
  }
  
  public void a(rc ☃)
  {
    if ((!db()) && (this.aR != null) && (this.b != null)) {
      this.b.a(this.aR.h_(), -5);
    }
    super.a(☃);
  }
}
