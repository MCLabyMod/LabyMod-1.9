import java.util.List;
import java.util.Random;
import java.util.UUID;

public class yz
  extends yq
  implements ys
{
  private static final UUID a = UUID.fromString("5CD17E52-A79A-43D3-A529-90FDE04B181E");
  private static final sn b = new sn(a, "Drinking speed penalty", -0.25D, 0).a(false);
  private static final ke<Boolean> c = kh.a(yz.class, kg.h);
  private int bv;
  
  public yz(aht ☃)
  {
    super(☃);
    a(0.6F, 1.95F);
  }
  
  protected void r()
  {
    this.bp.a(1, new th(this));
    this.bp.a(2, new uh(this, 1.0D, 60, 10.0F));
    this.bp.a(2, new ug(this, 1.0D));
    this.bp.a(3, new tp(this, zj.class, 8.0F));
    this.bp.a(3, new uf(this));
    
    this.bq.a(1, new uv(this, false, new Class[0]));
    this.bq.a(2, new uy(this, zj.class, true));
  }
  
  protected void i()
  {
    super.i();
    
    R().a(c, Boolean.valueOf(false));
  }
  
  protected nf G()
  {
    return ng.gz;
  }
  
  protected nf bR()
  {
    return ng.gC;
  }
  
  protected nf bS()
  {
    return ng.gA;
  }
  
  public void a(boolean ☃)
  {
    R().b(c, Boolean.valueOf(☃));
  }
  
  public boolean o()
  {
    return ((Boolean)R().a(c)).booleanValue();
  }
  
  protected void bA()
  {
    super.bA();
    
    a(yt.a).a(26.0D);
    a(yt.d).a(0.25D);
  }
  
  public void n()
  {
    if (!this.l.E)
    {
      if (o())
      {
        if (this.bv-- <= 0)
        {
          a(false);
          adq ☃ = cb();
          a(rw.a, null);
          if ((☃ != null) && (☃.b() == ads.bG))
          {
            List<rl> ☃ = afg.a(☃);
            if (☃ != null) {
              for (rl ☃ : ☃) {
                c(new rl(☃));
              }
            }
          }
          a(yt.d).c(b);
        }
      }
      else
      {
        afe ☃ = null;
        if ((this.S.nextFloat() < 0.15F) && (a(axe.h)) && (!a(rm.m))) {
          ☃ = afh.t;
        } else if ((this.S.nextFloat() < 0.15F) && (aH()) && (!a(rm.l))) {
          ☃ = afh.m;
        } else if ((this.S.nextFloat() < 0.05F) && (bQ() < bW())) {
          ☃ = afh.v;
        } else if ((this.S.nextFloat() < 0.5F) && (A() != null) && (!a(rm.a)) && (A().h(this) > 121.0D)) {
          ☃ = afh.o;
        }
        if (☃ != null)
        {
          a(rw.a, afg.a(new adq(ads.bG), ☃));
          this.bv = cb().l();
          a(true);
          this.l.a(null, this.p, this.q, this.r, ng.gB, bz(), 1.0F, 0.8F + this.S.nextFloat() * 0.4F);
          sm ☃ = a(yt.d);
          ☃.c(b);
          ☃.b(b);
        }
      }
      if (this.S.nextFloat() < 7.5E-4F) {
        this.l.a(this, (byte)15);
      }
    }
    super.n();
  }
  
  public void a(byte ☃)
  {
    if (☃ == 15) {
      for (int ☃ = 0; ☃ < this.S.nextInt(35) + 10; ☃++) {
        this.l.a(cy.r, this.p + this.S.nextGaussian() * 0.12999999523162842D, bl().e + 0.5D + this.S.nextGaussian() * 0.12999999523162842D, this.r + this.S.nextGaussian() * 0.12999999523162842D, 0.0D, 0.0D, 0.0D, new int[0]);
      }
    } else {
      super.a(☃);
    }
  }
  
  protected float c(rc ☃, float ☃)
  {
    ☃ = super.c(☃, ☃);
    if (☃.j() == this) {
      ☃ = 0.0F;
    }
    if (☃.s()) {
      ☃ = (float)(☃ * 0.15D);
    }
    return ☃;
  }
  
  protected kk J()
  {
    return azt.n;
  }
  
  public void a(sa ☃, float ☃)
  {
    if (o()) {
      return;
    }
    double ☃ = ☃.q + ☃.bn() - 1.100000023841858D;
    double ☃ = ☃.p + ☃.s - this.p;
    double ☃ = ☃ - this.q;
    double ☃ = ☃.r + ☃.u - this.r;
    float ☃ = on.a(☃ * ☃ + ☃ * ☃);
    
    afe ☃ = afh.x;
    if ((☃ >= 8.0F) && (!☃.a(rm.b))) {
      ☃ = afh.r;
    } else if ((☃.bQ() >= 8.0F) && (!☃.a(rm.s))) {
      ☃ = afh.z;
    } else if ((☃ <= 3.0F) && (!☃.a(rm.r)) && (this.S.nextFloat() < 0.25F)) {
      ☃ = afh.I;
    }
    aac ☃ = new aac(this.l, this, afg.a(new adq(ads.bH), ☃));
    ☃.w -= -20.0F;
    ☃.c(☃, ☃ + ☃ * 0.2F, ☃, 0.75F, 8.0F);
    this.l.a(null, this.p, this.q, this.r, ng.gD, bz(), 1.0F, 0.8F + this.S.nextFloat() * 0.4F);
    
    this.l.a(☃);
  }
  
  public float bn()
  {
    return 1.62F;
  }
}
