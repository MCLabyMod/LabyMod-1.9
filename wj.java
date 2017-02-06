import com.google.common.base.Predicate;
import java.util.Random;
import java.util.UUID;

public class wj
  extends sk
{
  private static final ke<Float> bz = kh.a(wj.class, kg.c);
  private static final ke<Boolean> bA = kh.a(wj.class, kg.h);
  private static final ke<Integer> bB = kh.a(wj.class, kg.b);
  private float bC;
  private float bD;
  private boolean bE;
  private boolean bF;
  private float bG;
  private float bH;
  
  public wj(aht ☃)
  {
    super(☃);
    a(0.6F, 0.85F);
    
    p(false);
  }
  
  protected void r()
  {
    this.bp.a(1, new th(this));
    this.bp.a(2, this.bx = new um(this));
    this.bp.a(3, new to(this, 0.4F));
    this.bp.a(4, new ts(this, 1.0D, true));
    this.bp.a(5, new ti(this, 1.0D, 10.0F, 2.0F));
    this.bp.a(6, new td(this, 1.0D));
    this.bp.a(7, new ug(this, 1.0D));
    this.bp.a(8, new tb(this, 8.0F));
    this.bp.a(9, new tp(this, zj.class, 8.0F));
    this.bp.a(9, new uf(this));
    
    this.bq.a(1, new va(this));
    this.bq.a(2, new vb(this));
    this.bq.a(3, new uv(this, true, new Class[0]));
    this.bq.a(4, new uz(this, vw.class, false, new Predicate()
    {
      public boolean a(rr ☃)
      {
        return ((☃ instanceof we)) || ((☃ instanceof wd));
      }
    }));
    this.bq.a(5, new uy(this, yw.class, false));
  }
  
  protected void bA()
  {
    super.bA();
    
    a(yt.d).a(0.30000001192092896D);
    if (cZ()) {
      a(yt.a).a(20.0D);
    } else {
      a(yt.a).a(8.0D);
    }
    bZ().b(yt.e).a(2.0D);
  }
  
  public void c(sa ☃)
  {
    super.c(☃);
    if (☃ == null) {
      r(false);
    } else if (!cZ()) {
      r(true);
    }
  }
  
  protected void M()
  {
    this.Z.b(bz, Float.valueOf(bQ()));
  }
  
  protected void i()
  {
    super.i();
    this.Z.a(bz, Float.valueOf(bQ()));
    this.Z.a(bA, Boolean.valueOf(false));
    this.Z.a(bB, Integer.valueOf(act.o.b()));
  }
  
  protected void a(cj ☃, ajt ☃)
  {
    a(ng.gR, 0.15F, 1.0F);
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    
    ☃.a("Angry", dj());
    ☃.a("CollarColor", (byte)dk().b());
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    
    r(☃.p("Angry"));
    if (☃.b("CollarColor", 99)) {
      a(act.a(☃.f("CollarColor")));
    }
  }
  
  protected nf G()
  {
    if (dj()) {
      return ng.gM;
    }
    if (this.S.nextInt(3) == 0)
    {
      if ((cZ()) && (((Float)this.Z.a(bz)).floatValue() < 10.0F)) {
        return ng.gS;
      }
      return ng.gP;
    }
    return ng.gK;
  }
  
  protected nf bR()
  {
    return ng.gO;
  }
  
  protected nf bS()
  {
    return ng.gL;
  }
  
  protected float cd()
  {
    return 0.4F;
  }
  
  protected kk J()
  {
    return azt.I;
  }
  
  public void n()
  {
    super.n();
    if ((!this.l.E) && (this.bE) && (!this.bF) && (!cT()) && (this.z))
    {
      this.bF = true;
      this.bG = 0.0F;
      this.bH = 0.0F;
      this.l.a(this, (byte)8);
    }
    if ((!this.l.E) && (A() == null) && (dj())) {
      r(false);
    }
  }
  
  public void m()
  {
    super.m();
    
    this.bD = this.bC;
    if (dl()) {
      this.bC += (1.0F - this.bC) * 0.4F;
    } else {
      this.bC += (0.0F - this.bC) * 0.4F;
    }
    if (ah())
    {
      this.bE = true;
      this.bF = false;
      this.bG = 0.0F;
      this.bH = 0.0F;
    }
    else if (((this.bE) || (this.bF)) && 
      (this.bF))
    {
      if (this.bG == 0.0F) {
        a(ng.gQ, cd(), (this.S.nextFloat() - this.S.nextFloat()) * 0.2F + 1.0F);
      }
      this.bH = this.bG;
      this.bG += 0.05F;
      if (this.bH >= 2.0F)
      {
        this.bE = false;
        this.bF = false;
        this.bH = 0.0F;
        this.bG = 0.0F;
      }
      if (this.bG > 0.4F)
      {
        float ☃ = (float)bl().b;
        int ☃ = (int)(on.a((this.bG - 0.4F) * 3.1415927F) * 7.0F);
        for (int ☃ = 0; ☃ < ☃; ☃++)
        {
          float ☃ = (this.S.nextFloat() * 2.0F - 1.0F) * this.G * 0.5F;
          float ☃ = (this.S.nextFloat() * 2.0F - 1.0F) * this.G * 0.5F;
          this.l.a(cy.f, this.p + ☃, ☃ + 0.8F, this.r + ☃, this.s, this.t, this.u, new int[0]);
        }
      }
    }
  }
  
  public boolean dh()
  {
    return this.bE;
  }
  
  public float r(float ☃)
  {
    return 0.75F + (this.bH + (this.bG - this.bH) * ☃) / 2.0F * 0.25F;
  }
  
  public float i(float ☃, float ☃)
  {
    float ☃ = (this.bH + (this.bG - this.bH) * ☃ + ☃) / 1.8F;
    if (☃ < 0.0F) {
      ☃ = 0.0F;
    } else if (☃ > 1.0F) {
      ☃ = 1.0F;
    }
    return on.a(☃ * 3.1415927F) * on.a(☃ * 3.1415927F * 11.0F) * 0.15F * 3.1415927F;
  }
  
  public float s(float ☃)
  {
    return (this.bD + (this.bC - this.bD) * ☃) * 0.15F * 3.1415927F;
  }
  
  public float bn()
  {
    return this.H * 0.8F;
  }
  
  public int N()
  {
    if (db()) {
      return 20;
    }
    return super.N();
  }
  
  public boolean a(rc ☃, float ☃)
  {
    if (b(☃)) {
      return false;
    }
    rr ☃ = ☃.j();
    if (this.bx != null) {
      this.bx.a(false);
    }
    if ((☃ != null) && (!(☃ instanceof zj)) && (!(☃ instanceof zm))) {
      ☃ = (☃ + 1.0F) / 2.0F;
    }
    return super.a(☃, ☃);
  }
  
  public boolean B(rr ☃)
  {
    boolean ☃ = ☃.a(rc.a(this), (int)a(yt.e).e());
    if (☃) {
      a(this, ☃);
    }
    return ☃;
  }
  
  public void p(boolean ☃)
  {
    super.p(☃);
    if (☃) {
      a(yt.a).a(20.0D);
    } else {
      a(yt.a).a(8.0D);
    }
    a(yt.e).a(4.0D);
  }
  
  public boolean a(zj ☃, qm ☃, adq ☃)
  {
    if (cZ())
    {
      if (☃ != null) {
        if ((☃.b() instanceof adk))
        {
          adk ☃ = (adk)☃.b();
          if ((☃.g()) && (((Float)this.Z.a(bz)).floatValue() < 20.0F))
          {
            if (!☃.bJ.d) {
              ☃.b -= 1;
            }
            b(☃.h(☃));
            return true;
          }
        }
        else if (☃.b() == ads.bd)
        {
          act ☃ = act.a(☃.i());
          if (☃ != dk())
          {
            a(☃);
            if (!☃.bJ.d) {
              ☃.b -= 1;
            }
            return true;
          }
        }
      }
      if ((d(☃)) && 
        (!this.l.E) && (!e(☃)))
      {
        this.bx.a(!db());
        this.bc = false;
        this.h.o();
        c(null);
      }
    }
    else if ((☃ != null) && (☃.b() == ads.be) && (!dj()))
    {
      if (!☃.bJ.d) {
        ☃.b -= 1;
      }
      if (!this.l.E) {
        if (this.S.nextInt(3) == 0)
        {
          p(true);
          this.h.o();
          c(null);
          this.bx.a(true);
          c(20.0F);
          b(☃.bc());
          o(true);
          this.l.a(this, (byte)7);
        }
        else
        {
          o(false);
          this.l.a(this, (byte)6);
        }
      }
      return true;
    }
    return super.a(☃, ☃, ☃);
  }
  
  public void a(byte ☃)
  {
    if (☃ == 8)
    {
      this.bF = true;
      this.bG = 0.0F;
      this.bH = 0.0F;
    }
    else
    {
      super.a(☃);
    }
  }
  
  public float di()
  {
    if (dj()) {
      return 1.5393804F;
    }
    if (cZ()) {
      return (0.55F - (20.0F - ((Float)this.Z.a(bz)).floatValue()) * 0.02F) * 3.1415927F;
    }
    return 0.62831855F;
  }
  
  public boolean e(adq ☃)
  {
    if (☃ == null) {
      return false;
    }
    if (!(☃.b() instanceof adk)) {
      return false;
    }
    return ((adk)☃.b()).g();
  }
  
  public int cJ()
  {
    return 8;
  }
  
  public boolean dj()
  {
    return (((Byte)this.Z.a(bv)).byteValue() & 0x2) != 0;
  }
  
  public void r(boolean ☃)
  {
    byte ☃ = ((Byte)this.Z.a(bv)).byteValue();
    if (☃) {
      this.Z.b(bv, Byte.valueOf((byte)(☃ | 0x2)));
    } else {
      this.Z.b(bv, Byte.valueOf((byte)(☃ & 0xFFFFFFFD)));
    }
  }
  
  public act dk()
  {
    return act.a(((Integer)this.Z.a(bB)).intValue() & 0xF);
  }
  
  public void a(act ☃)
  {
    this.Z.b(bB, Integer.valueOf(☃.b()));
  }
  
  public wj b(ro ☃)
  {
    wj ☃ = new wj(this.l);
    UUID ☃ = b();
    if (☃ != null)
    {
      ☃.b(☃);
      ☃.p(true);
    }
    return ☃;
  }
  
  public void s(boolean ☃)
  {
    this.Z.b(bA, Boolean.valueOf(☃));
  }
  
  public boolean a(vw ☃)
  {
    if (☃ == this) {
      return false;
    }
    if (!cZ()) {
      return false;
    }
    if (!(☃ instanceof wj)) {
      return false;
    }
    wj ☃ = (wj)☃;
    if (!☃.cZ()) {
      return false;
    }
    if (☃.db()) {
      return false;
    }
    return (df()) && (☃.df());
  }
  
  public boolean dl()
  {
    return ((Boolean)this.Z.a(bA)).booleanValue();
  }
  
  protected boolean K()
  {
    return (!cZ()) && (this.T > 2400);
  }
  
  public boolean a(sa ☃, sa ☃)
  {
    if (((☃ instanceof yi)) || ((☃ instanceof ym))) {
      return false;
    }
    if ((☃ instanceof wj))
    {
      wj ☃ = (wj)☃;
      if ((☃.cZ()) && (☃.dc() == ☃)) {
        return false;
      }
    }
    if (((☃ instanceof zj)) && ((☃ instanceof zj)) && (!((zj)☃).a((zj)☃))) {
      return false;
    }
    if (((☃ instanceof wk)) && (((wk)☃).dc())) {
      return false;
    }
    return true;
  }
  
  public boolean a(zj ☃)
  {
    return (!dj()) && (super.a(☃));
  }
}
