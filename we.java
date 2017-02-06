import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Random;

public class we
  extends vw
{
  private static final ke<Byte> bv = kh.a(we.class, kg.a);
  private final abc bw = new abc(new aau()
  {
    public boolean a(zj ☃)
    {
      return false;
    }
  }, 2, 1);
  private static final Map<act, float[]> bx = Maps.newEnumMap(act.class);
  private int bz;
  private tf bA;
  
  static
  {
    bx.put(act.a, new float[] { 1.0F, 1.0F, 1.0F });
    bx.put(act.b, new float[] { 0.85F, 0.5F, 0.2F });
    bx.put(act.c, new float[] { 0.7F, 0.3F, 0.85F });
    bx.put(act.d, new float[] { 0.4F, 0.6F, 0.85F });
    bx.put(act.e, new float[] { 0.9F, 0.9F, 0.2F });
    bx.put(act.f, new float[] { 0.5F, 0.8F, 0.1F });
    bx.put(act.g, new float[] { 0.95F, 0.5F, 0.65F });
    bx.put(act.h, new float[] { 0.3F, 0.3F, 0.3F });
    bx.put(act.i, new float[] { 0.6F, 0.6F, 0.6F });
    bx.put(act.j, new float[] { 0.3F, 0.5F, 0.6F });
    bx.put(act.k, new float[] { 0.5F, 0.25F, 0.7F });
    bx.put(act.l, new float[] { 0.2F, 0.3F, 0.7F });
    bx.put(act.m, new float[] { 0.4F, 0.3F, 0.2F });
    bx.put(act.n, new float[] { 0.4F, 0.5F, 0.2F });
    bx.put(act.o, new float[] { 0.6F, 0.2F, 0.2F });
    bx.put(act.p, new float[] { 0.1F, 0.1F, 0.1F });
  }
  
  public static float[] a(act ☃)
  {
    return (float[])bx.get(☃);
  }
  
  public we(aht ☃)
  {
    super(☃);
    a(0.9F, 1.3F);
    
    this.bw.a(0, new adq(ads.bd));
    this.bw.a(1, new adq(ads.bd));
  }
  
  protected void r()
  {
    this.bp.a(0, new th(this));
    this.bp.a(1, new uc(this, 1.25D));
    this.bp.a(2, new td(this, 1.0D));
    this.bp.a(3, new up(this, 1.1D, ads.Q, false));
    this.bp.a(4, new tj(this, 1.1D));
    this.bp.a(5, this.bA = new tf(this));
    this.bp.a(6, new ug(this, 1.0D));
    this.bp.a(7, new tp(this, zj.class, 6.0F));
    this.bp.a(8, new uf(this));
  }
  
  protected void M()
  {
    this.bz = this.bA.f();
    super.M();
  }
  
  public void n()
  {
    if (this.l.E) {
      this.bz = Math.max(0, this.bz - 1);
    }
    super.n();
  }
  
  protected void bA()
  {
    super.bA();
    
    a(yt.a).a(8.0D);
    a(yt.d).a(0.23000000417232513D);
  }
  
  protected void i()
  {
    super.i();
    
    this.Z.a(bv, Byte.valueOf((byte)0));
  }
  
  protected kk J()
  {
    if (da()) {
      return azt.K;
    }
    switch (we.2.a[cZ().ordinal()])
    {
    case 1: 
    default: 
      return azt.L;
    case 2: 
      return azt.M;
    case 3: 
      return azt.N;
    case 4: 
      return azt.O;
    case 5: 
      return azt.P;
    case 6: 
      return azt.Q;
    case 7: 
      return azt.R;
    case 8: 
      return azt.S;
    case 9: 
      return azt.T;
    case 10: 
      return azt.U;
    case 11: 
      return azt.V;
    case 12: 
      return azt.W;
    case 13: 
      return azt.X;
    case 14: 
      return azt.Y;
    case 15: 
      return azt.Z;
    }
    return azt.aa;
  }
  
  public void a(byte ☃)
  {
    if (☃ == 10) {
      this.bz = 40;
    } else {
      super.a(☃);
    }
  }
  
  public float r(float ☃)
  {
    if (this.bz <= 0) {
      return 0.0F;
    }
    if ((this.bz >= 4) && (this.bz <= 36)) {
      return 1.0F;
    }
    if (this.bz < 4) {
      return (this.bz - ☃) / 4.0F;
    }
    return -(this.bz - 40 - ☃) / 4.0F;
  }
  
  public float s(float ☃)
  {
    if ((this.bz > 4) && (this.bz <= 36))
    {
      float ☃ = (this.bz - 4 - ☃) / 32.0F;
      return 0.62831855F + 0.21991149F * on.a(☃ * 28.7F);
    }
    if (this.bz > 0) {
      return 0.62831855F;
    }
    return this.w * 0.017453292F;
  }
  
  public boolean a(zj ☃, qm ☃, adq ☃)
  {
    if ((☃ != null) && (☃.b() == ads.bl) && (!da()) && (!m_()))
    {
      if (!this.l.E)
      {
        o(true);
        int ☃ = 1 + this.S.nextInt(3);
        for (int ☃ = 0; ☃ < ☃; ☃++)
        {
          yd ☃ = a(new adq(ado.a(aju.L), 1, cZ().a()), 1.0F);
          ☃.t += this.S.nextFloat() * 0.05F;
          ☃.s += (this.S.nextFloat() - this.S.nextFloat()) * 0.1F;
          ☃.u += (this.S.nextFloat() - this.S.nextFloat()) * 0.1F;
        }
      }
      ☃.a(1, ☃);
      a(ng.eO, 1.0F, 1.0F);
    }
    return super.a(☃, ☃, ☃);
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    ☃.a("Sheared", da());
    ☃.a("Color", (byte)cZ().a());
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    o(☃.p("Sheared"));
    b(act.b(☃.f("Color")));
  }
  
  protected nf G()
  {
    return ng.eL;
  }
  
  protected nf bR()
  {
    return ng.eN;
  }
  
  protected nf bS()
  {
    return ng.eM;
  }
  
  protected void a(cj ☃, ajt ☃)
  {
    a(ng.eP, 0.15F, 1.0F);
  }
  
  public act cZ()
  {
    return act.b(((Byte)this.Z.a(bv)).byteValue() & 0xF);
  }
  
  public void b(act ☃)
  {
    byte ☃ = ((Byte)this.Z.a(bv)).byteValue();
    this.Z.b(bv, Byte.valueOf((byte)(☃ & 0xF0 | ☃.a() & 0xF)));
  }
  
  public boolean da()
  {
    return (((Byte)this.Z.a(bv)).byteValue() & 0x10) != 0;
  }
  
  public void o(boolean ☃)
  {
    byte ☃ = ((Byte)this.Z.a(bv)).byteValue();
    if (☃) {
      this.Z.b(bv, Byte.valueOf((byte)(☃ | 0x10)));
    } else {
      this.Z.b(bv, Byte.valueOf((byte)(☃ & 0xFFFFFFEF)));
    }
  }
  
  public static act a(Random ☃)
  {
    int ☃ = ☃.nextInt(100);
    if (☃ < 5) {
      return act.p;
    }
    if (☃ < 10) {
      return act.h;
    }
    if (☃ < 15) {
      return act.i;
    }
    if (☃ < 18) {
      return act.m;
    }
    if (☃.nextInt(500) == 0) {
      return act.g;
    }
    return act.a;
  }
  
  public we b(ro ☃)
  {
    we ☃ = (we)☃;
    we ☃ = new we(this.l);
    
    ☃.b(a(this, ☃));
    
    return ☃;
  }
  
  public void B()
  {
    o(false);
    if (m_()) {
      a(60);
    }
  }
  
  public sd a(ql ☃, sd ☃)
  {
    ☃ = super.a(☃, ☃);
    
    b(a(this.l.r));
    return ☃;
  }
  
  private act a(vw ☃, vw ☃)
  {
    int ☃ = ((we)☃).cZ().b();
    int ☃ = ((we)☃).cZ().b();
    
    this.bw.a(0).b(☃);
    this.bw.a(1).b(☃);
    
    adq ☃ = afv.a().a(this.bw, ((we)☃).l);
    int ☃;
    int ☃;
    if ((☃ != null) && (☃.b() == ads.bd)) {
      ☃ = ☃.i();
    } else {
      ☃ = this.l.r.nextBoolean() ? ☃ : ☃;
    }
    return act.a(☃);
  }
  
  public float bn()
  {
    return 0.95F * this.H;
  }
}
