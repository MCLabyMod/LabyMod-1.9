import java.util.Random;

public class wf
  extends vz
  implements ys
{
  private static final ke<Byte> a = kh.a(wf.class, kg.a);
  
  public wf(aht ☃)
  {
    super(☃);
    a(0.7F, 1.9F);
  }
  
  protected void r()
  {
    this.bp.a(1, new uh(this, 1.25D, 20, 10.0F));
    this.bp.a(2, new ug(this, 1.0D));
    this.bp.a(3, new tp(this, zj.class, 6.0F));
    this.bp.a(4, new uf(this));
    
    this.bq.a(1, new uy(this, sb.class, 10, true, false, yl.d));
  }
  
  protected void bA()
  {
    super.bA();
    
    a(yt.a).a(4.0D);
    a(yt.d).a(0.20000000298023224D);
  }
  
  protected void i()
  {
    super.i();
    this.Z.a(a, Byte.valueOf((byte)0));
  }
  
  public void n()
  {
    super.n();
    if (!this.l.E)
    {
      int ☃ = on.c(this.p);
      int ☃ = on.c(this.q);
      int ☃ = on.c(this.r);
      if (ah()) {
        a(rc.f, 1.0F);
      }
      if (this.l.b(new cj(☃, 0, ☃)).a(new cj(☃, ☃, ☃)) > 1.0F) {
        a(rc.c, 1.0F);
      }
      if (!this.l.U().b("mobGriefing")) {
        return;
      }
      for (int ☃ = 0; ☃ < 4; ☃++)
      {
        ☃ = on.c(this.p + (☃ % 2 * 2 - 1) * 0.25F);
        ☃ = on.c(this.q);
        ☃ = on.c(this.r + (☃ / 2 % 2 * 2 - 1) * 0.25F);
        cj ☃ = new cj(☃, ☃, ☃);
        if ((this.l.o(☃).a() == axe.a) && 
          (this.l.b(new cj(☃, 0, ☃)).a(☃) < 0.8F) && 
          (aju.aH.a(this.l, ☃))) {
          this.l.a(☃, aju.aH.u());
        }
      }
    }
  }
  
  protected kk J()
  {
    return azt.z;
  }
  
  public void a(sa ☃, float ☃)
  {
    zw ☃ = new zw(this.l, this);
    double ☃ = ☃.q + ☃.bn() - 1.100000023841858D;
    double ☃ = ☃.p - this.p;
    double ☃ = ☃ - ☃.q;
    double ☃ = ☃.r - this.r;
    float ☃ = on.a(☃ * ☃ + ☃ * ☃) * 0.2F;
    ☃.c(☃, ☃ + ☃, ☃, 1.6F, 12.0F);
    
    a(ng.fK, 1.0F, 1.0F / (bF().nextFloat() * 0.4F + 0.8F));
    this.l.a(☃);
  }
  
  public float bn()
  {
    return 1.7F;
  }
  
  protected boolean a(zj ☃, qm ☃, adq ☃)
  {
    if ((☃ != null) && (☃.b() == ads.bl) && (!o()) && 
      (!this.l.E))
    {
      a(true);
      
      ☃.a(1, ☃);
    }
    return super.a(☃, ☃, ☃);
  }
  
  public boolean o()
  {
    return (((Byte)this.Z.a(a)).byteValue() & 0x10) != 0;
  }
  
  public void a(boolean ☃)
  {
    byte ☃ = ((Byte)this.Z.a(a)).byteValue();
    if (☃) {
      this.Z.b(a, Byte.valueOf((byte)(☃ | 0x10)));
    } else {
      this.Z.b(a, Byte.valueOf((byte)(☃ & 0xFFFFFFEF)));
    }
  }
  
  protected nf G()
  {
    return ng.fH;
  }
  
  protected nf bR()
  {
    return ng.fJ;
  }
  
  protected nf bS()
  {
    return ng.fI;
  }
}
