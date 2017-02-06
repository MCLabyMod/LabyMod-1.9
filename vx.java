import com.google.common.collect.Sets;
import java.util.Random;
import java.util.Set;

public class vx
  extends vw
{
  private static final Set<ado> bD = Sets.newHashSet(new ado[] { ads.P, ads.bo, ads.bn, ads.cU });
  public float bv;
  public float bw;
  public float bx;
  public float bz;
  public float bA = 1.0F;
  public int bB;
  public boolean bC;
  
  public vx(aht ☃)
  {
    super(☃);
    a(0.4F, 0.7F);
    this.bB = (this.S.nextInt(6000) + 6000);
    
    a(aym.g, 0.0F);
  }
  
  protected void r()
  {
    this.bp.a(0, new th(this));
    this.bp.a(1, new uc(this, 1.4D));
    this.bp.a(2, new td(this, 1.0D));
    this.bp.a(3, new up(this, 1.0D, false, bD));
    this.bp.a(4, new tj(this, 1.1D));
    this.bp.a(5, new ug(this, 1.0D));
    this.bp.a(6, new tp(this, zj.class, 6.0F));
    this.bp.a(7, new uf(this));
  }
  
  public float bn()
  {
    return this.H;
  }
  
  protected void bA()
  {
    super.bA();
    
    a(yt.a).a(4.0D);
    a(yt.d).a(0.25D);
  }
  
  public void n()
  {
    super.n();
    
    this.bz = this.bv;
    this.bx = this.bw;
    
    this.bw = ((float)(this.bw + (this.z ? -1 : 4) * 0.3D));
    this.bw = on.a(this.bw, 0.0F, 1.0F);
    if ((!this.z) && (this.bA < 1.0F)) {
      this.bA = 1.0F;
    }
    this.bA = ((float)(this.bA * 0.9D));
    if ((!this.z) && (this.t < 0.0D)) {
      this.t *= 0.6D;
    }
    this.bv += this.bA * 2.0F;
    if ((!this.l.E) && (!m_()) && (!cZ()) && (--this.bB <= 0))
    {
      a(ng.aa, 1.0F, (this.S.nextFloat() - this.S.nextFloat()) * 0.2F + 1.0F);
      a(ads.aW, 1);
      this.bB = (this.S.nextInt(6000) + 6000);
    }
  }
  
  public void e(float ☃, float ☃) {}
  
  protected nf G()
  {
    return ng.Y;
  }
  
  protected nf bR()
  {
    return ng.ab;
  }
  
  protected nf bS()
  {
    return ng.Z;
  }
  
  protected void a(cj ☃, ajt ☃)
  {
    a(ng.ac, 0.15F, 1.0F);
  }
  
  protected kk J()
  {
    return azt.B;
  }
  
  public vx b(ro ☃)
  {
    return new vx(this.l);
  }
  
  public boolean e(adq ☃)
  {
    return (☃ != null) && (bD.contains(☃.b()));
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    this.bC = ☃.p("IsChickenJockey");
    if (☃.e("EggLayTime")) {
      this.bB = ☃.h("EggLayTime");
    }
  }
  
  protected int b(zj ☃)
  {
    if (cZ()) {
      return 10;
    }
    return super.b(☃);
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    ☃.a("IsChickenJockey", this.bC);
    ☃.a("EggLayTime", this.bB);
  }
  
  protected boolean K()
  {
    return (cZ()) && (!aJ());
  }
  
  public void k(rr ☃)
  {
    super.k(☃);
    float ☃ = on.a(this.aM * 0.017453292F);
    float ☃ = on.b(this.aM * 0.017453292F);
    float ☃ = 0.1F;
    float ☃ = 0.0F;
    
    ☃.b(this.p + ☃ * ☃, this.q + this.H * 0.5F + ☃.ax() + ☃, this.r - ☃ * ☃);
    if ((☃ instanceof sa)) {
      ((sa)☃).aM = this.aM;
    }
  }
  
  public boolean cZ()
  {
    return this.bC;
  }
  
  public void o(boolean ☃)
  {
    this.bC = ☃;
  }
}
