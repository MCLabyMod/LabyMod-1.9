import java.util.List;
import java.util.Random;
import java.util.UUID;

public class yr
  extends za
{
  private static final UUID b = UUID.fromString("49455A49-7EC5-45BA-B886-3B90B23A1718");
  private static final sn c = new sn(b, "Attacking speed boost", 0.05D, 0).a(false);
  private int bv;
  private int bw;
  private UUID bx;
  
  public yr(aht ☃)
  {
    super(☃);
    this.Y = true;
  }
  
  public void a(sa ☃)
  {
    super.a(☃);
    if (☃ != null) {
      this.bx = ☃.bc();
    }
  }
  
  protected void o()
  {
    this.bq.a(1, new yr.b(this));
    this.bq.a(2, new yr.a(this));
  }
  
  protected void bA()
  {
    super.bA();
    
    a(a).a(0.0D);
    a(yt.d).a(0.23000000417232513D);
    a(yt.e).a(5.0D);
  }
  
  public void m()
  {
    super.m();
  }
  
  protected void M()
  {
    sm ☃ = a(yt.d);
    if (da())
    {
      if ((!m_()) && (!☃.a(c))) {
        ☃.b(c);
      }
      this.bv -= 1;
    }
    else if (☃.a(c))
    {
      ☃.c(c);
    }
    if ((this.bw > 0) && 
      (--this.bw == 0)) {
      a(ng.hr, cd() * 2.0F, ((this.S.nextFloat() - this.S.nextFloat()) * 0.2F + 1.0F) * 1.8F);
    }
    if ((this.bv > 0) && 
      (this.bx != null) && (bG() == null))
    {
      zj ☃ = this.l.b(this.bx);
      a(☃);
      this.aR = ☃;
      this.aS = bH();
    }
    super.M();
  }
  
  public boolean cF()
  {
    return this.l.ae() != qk.a;
  }
  
  public boolean cG()
  {
    return (this.l.a(bl(), this)) && (this.l.a(this, bl()).isEmpty()) && (!this.l.e(bl()));
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    ☃.a("Anger", (short)this.bv);
    if (this.bx != null) {
      ☃.a("HurtBy", this.bx.toString());
    } else {
      ☃.a("HurtBy", "");
    }
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    this.bv = ☃.g("Anger");
    String ☃ = ☃.l("HurtBy");
    if (!☃.isEmpty())
    {
      this.bx = UUID.fromString(☃);
      
      zj ☃ = this.l.b(this.bx);
      a(☃);
      if (☃ != null)
      {
        this.aR = ☃;
        this.aS = bH();
      }
    }
  }
  
  public boolean a(rc ☃, float ☃)
  {
    if (b(☃)) {
      return false;
    }
    rr ☃ = ☃.j();
    if ((☃ instanceof zj)) {
      a(☃);
    }
    return super.a(☃, ☃);
  }
  
  private void a(rr ☃)
  {
    this.bv = (400 + this.S.nextInt(400));
    this.bw = this.S.nextInt(40);
    if ((☃ instanceof sa)) {
      a((sa)☃);
    }
  }
  
  public boolean da()
  {
    return this.bv > 0;
  }
  
  protected nf G()
  {
    return ng.hq;
  }
  
  protected nf bR()
  {
    return ng.ht;
  }
  
  protected nf bS()
  {
    return ng.hs;
  }
  
  protected kk J()
  {
    return azt.ai;
  }
  
  public boolean a(zj ☃, qm ☃, adq ☃)
  {
    return false;
  }
  
  protected void a(ql ☃)
  {
    a(rw.a, new adq(ads.D));
  }
  
  public sd a(ql ☃, sd ☃)
  {
    super.a(☃, ☃);
    df();
    return ☃;
  }
  
  static class b
    extends uv
  {
    public b(yr ☃)
    {
      super(true, new Class[0]);
    }
    
    protected void a(sh ☃, sa ☃)
    {
      super.a(☃, ☃);
      if ((☃ instanceof yr)) {
        yr.a((yr)☃, ☃);
      }
    }
  }
  
  static class a
    extends uy<zj>
  {
    public a(yr ☃)
    {
      super(zj.class, true);
    }
    
    public boolean a()
    {
      return (((yr)this.e).da()) && (super.a());
    }
  }
}
