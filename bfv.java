import io.netty.buffer.Unpooled;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bfv
  extends bft
{
  private static final Logger u = ;
  private static final kk v = new kk("textures/gui/container/beacon.png");
  private qg w;
  private bfv.b x;
  private boolean y;
  
  public bfv(zi ☃, qg ☃)
  {
    super(new aax(☃, ☃));
    this.w = ☃;
    
    this.f = 230;
    this.g = 219;
  }
  
  public void b()
  {
    super.b();
    
    this.n.add(this.x = new bfv.b(-1, this.i + 164, this.r + 107));
    this.n.add(new bfv.a(-2, this.i + 190, this.r + 107));
    
    this.y = true;
    
    this.x.l = false;
  }
  
  public void e()
  {
    super.e();
    
    int ☃ = this.w.c_(0);
    rk ☃ = rk.a(this.w.c_(1));
    rk ☃ = rk.a(this.w.c_(2));
    if ((this.y) && (☃ >= 0))
    {
      this.y = false;
      
      int ☃ = 100;
      for (int ☃ = 0; ☃ <= 2; ☃++)
      {
        int ☃ = apu.a[☃].length;
        int ☃ = ☃ * 22 + (☃ - 1) * 2;
        for (int ☃ = 0; ☃ < ☃; ☃++)
        {
          rk ☃ = apu.a[☃][☃];
          bfv.c ☃ = new bfv.c(☃++, this.i + 76 + ☃ * 24 - ☃ / 2, this.r + 22 + ☃ * 25, ☃, ☃);
          this.n.add(☃);
          if (☃ >= ☃) {
            ☃.l = false;
          } else if (☃ == ☃) {
            ☃.b(true);
          }
        }
      }
      int ☃ = 3;
      
      int ☃ = apu.a[☃].length + 1;
      int ☃ = ☃ * 22 + (☃ - 1) * 2;
      for (int ☃ = 0; ☃ < ☃ - 1; ☃++)
      {
        rk ☃ = apu.a[☃][☃];
        bfv.c ☃ = new bfv.c(☃++, this.i + 167 + ☃ * 24 - ☃ / 2, this.r + 47, ☃, ☃);
        this.n.add(☃);
        if (☃ >= ☃) {
          ☃.l = false;
        } else if (☃ == ☃) {
          ☃.b(true);
        }
      }
      if (☃ != null)
      {
        bfv.c ☃ = new bfv.c(☃++, this.i + 167 + (☃ - 1) * 24 - ☃ / 2, this.r + 47, ☃, ☃);
        this.n.add(☃);
        if (☃ >= ☃) {
          ☃.l = false;
        } else if (☃ == ☃) {
          ☃.b(true);
        }
      }
    }
    this.x.l = ((this.w.a(0) != null) && (☃ != null));
  }
  
  protected void a(bcz ☃)
  {
    if (☃.k == -2)
    {
      this.j.h.d.a(new ip(this.j.h.bt.d));
      this.j.a(null);
    }
    else if (☃.k == -1)
    {
      String ☃ = "MC|Beacon";
      
      em ☃ = new em(Unpooled.buffer());
      
      ☃.writeInt(this.w.c_(1));
      ☃.writeInt(this.w.c_(2));
      this.j.v().a(new iq(☃, ☃));
      this.j.h.d.a(new ip(this.j.h.bt.d));
      this.j.a(null);
    }
    else if ((☃ instanceof bfv.c))
    {
      bfv.c ☃ = (bfv.c)☃;
      if (☃.c()) {
        return;
      }
      int ☃ = rk.a(bfv.c.a(☃));
      if (bfv.c.b(☃) < 3) {
        this.w.b(1, ☃);
      } else {
        this.w.b(2, ☃);
      }
      this.n.clear();
      b();
      e();
    }
  }
  
  protected void b(int ☃, int ☃)
  {
    bcd.a();
    a(this.q, bwo.a("tile.beacon.primary", new Object[0]), 62, 10, 14737632);
    a(this.q, bwo.a("tile.beacon.secondary", new Object[0]), 169, 10, 14737632);
    for (bcz ☃ : this.n) {
      if (☃.a())
      {
        ☃.b(☃ - this.i, ☃ - this.r);
        break;
      }
    }
    bcd.c();
  }
  
  protected void a(float ☃, int ☃, int ☃)
  {
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    this.j.N().a(v);
    int ☃ = (this.l - this.f) / 2;
    int ☃ = (this.m - this.g) / 2;
    b(☃, ☃, 0, 0, this.f, this.g);
    
    this.k.a = 100.0F;
    this.k.b(new adq(ads.bY), ☃ + 42, ☃ + 109);
    this.k.b(new adq(ads.k), ☃ + 42 + 22, ☃ + 109);
    this.k.b(new adq(ads.m), ☃ + 42 + 44, ☃ + 109);
    this.k.b(new adq(ads.l), ☃ + 42 + 66, ☃ + 109);
    this.k.a = 0.0F;
  }
  
  static class d
    extends bcz
  {
    private final kk o;
    private final int p;
    private final int q;
    private boolean r;
    
    protected d(int ☃, int ☃, int ☃, kk ☃, int ☃, int ☃)
    {
      super(☃, ☃, 22, 22, "");
      this.o = ☃;
      this.p = ☃;
      this.q = ☃;
    }
    
    public void a(bcf ☃, int ☃, int ☃)
    {
      if (!this.m) {
        return;
      }
      ☃.N().a(bfv.a());
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      
      this.n = ((☃ >= this.h) && (☃ >= this.i) && (☃ < this.h + this.f) && (☃ < this.i + this.g));
      int ☃ = 219;
      int ☃ = 0;
      if (!this.l) {
        ☃ += this.f * 2;
      } else if (this.r) {
        ☃ += this.f * 1;
      } else if (this.n) {
        ☃ += this.f * 3;
      }
      b(this.h, this.i, ☃, ☃, this.f, this.g);
      if (!bfv.a().equals(this.o)) {
        ☃.N().a(this.o);
      }
      b(this.h + 2, this.i + 2, this.p, this.q, 18, 18);
    }
    
    public boolean c()
    {
      return this.r;
    }
    
    public void b(boolean ☃)
    {
      this.r = ☃;
    }
  }
  
  class c
    extends bfv.d
  {
    private final rk p;
    private final int q;
    
    public c(int ☃, int ☃, int ☃, rk ☃, int ☃)
    {
      super(☃, ☃, bft.a, ☃.d() % 8 * 18, 198 + ☃.d() / 8 * 18);
      this.p = ☃;
      this.q = ☃;
    }
    
    public void b(int ☃, int ☃)
    {
      String ☃ = bwo.a(this.p.a(), new Object[0]);
      if ((this.q >= 3) && (this.p != rm.j)) {
        ☃ = ☃ + " II";
      }
      bfv.a(bfv.this, ☃, ☃, ☃);
    }
  }
  
  class b
    extends bfv.d
  {
    public b(int ☃, int ☃, int ☃)
    {
      super(☃, ☃, bfv.a(), 90, 220);
    }
    
    public void b(int ☃, int ☃)
    {
      bfv.b(bfv.this, bwo.a("gui.done", new Object[0]), ☃, ☃);
    }
  }
  
  class a
    extends bfv.d
  {
    public a(int ☃, int ☃, int ☃)
    {
      super(☃, ☃, bfv.a(), 112, 220);
    }
    
    public void b(int ☃, int ☃)
    {
      bfv.c(bfv.this, bwo.a("gui.cancel", new Object[0]), ☃, ☃);
    }
  }
}
