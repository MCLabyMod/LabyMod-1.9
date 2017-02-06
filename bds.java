import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;

public class bds
  extends bcv
  implements byw
{
  private final bcf a;
  private final List<bds.a> f = Lists.newArrayList();
  private boolean g;
  
  public bds(bcf ☃)
  {
    this.a = ☃;
  }
  
  public void a(bcx ☃)
  {
    if ((!this.g) && (this.a.u.N))
    {
      this.a.U().a(this);
      this.g = true;
    }
    else if ((this.g) && (!this.a.u.N))
    {
      this.a.U().b(this);
      this.g = false;
    }
    if ((!this.g) || (this.f.isEmpty())) {
      return;
    }
    bni.G();
    bni.m();
    bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
    
    bbj ☃ = new bbj(this.a.h.p, this.a.h.q + this.a.h.bn(), this.a.h.r);
    bbj ☃ = new bbj(0.0D, 0.0D, -1.0D).a(-this.a.h.w * 0.017453292F).b(-this.a.h.v * 0.017453292F);
    bbj ☃ = new bbj(0.0D, 1.0D, 0.0D).a(-this.a.h.w * 0.017453292F).b(-this.a.h.v * 0.017453292F);
    bbj ☃ = ☃.c(☃);
    int ☃ = 0;
    int ☃ = 0;
    for (Iterator<bds.a> ☃ = this.f.iterator(); ☃.hasNext();)
    {
      bds.a ☃ = (bds.a)☃.next();
      if (☃.b() + 3000L <= bcf.I()) {
        ☃.remove();
      } else {
        ☃ = Math.max(☃, this.a.k.a(☃.a()));
      }
    }
    ☃ += this.a.k.a("<") + this.a.k.a(" ") + this.a.k.a(">") + this.a.k.a(" ");
    for (bds.a ☃ : this.f)
    {
      int ☃ = 255;
      String ☃ = ☃.a();
      
      bbj ☃ = ☃.c().d(☃).a();
      double ☃ = -☃.b(☃);
      double ☃ = -☃.b(☃);
      boolean ☃ = ☃ > 0.5D;
      
      int ☃ = ☃ / 2;
      int ☃ = this.a.k.a;
      int ☃ = ☃ / 2;
      float ☃ = 1.0F;
      int ☃ = this.a.k.a(☃);
      int ☃ = on.c(on.b(255.0D, 75.0D, (float)(bcf.I() - ☃.b()) / 3000.0F));
      int ☃ = ☃ << 16 | ☃ << 8 | ☃;
      
      bni.G();
      bni.c(☃.a() - ☃ * ☃ - 2.0F, ☃.b() - 30 - ☃ * (☃ + 1) * ☃, 0.0F);
      bni.b(☃, ☃, ☃);
      a(-☃ - 1, -☃ - 1, ☃ + 1, ☃ + 1, (int)(☃ * 0.8D) << 24);
      bni.m();
      if (!☃) {
        if (☃ > 0.0D) {
          this.a.k.a(">", ☃ - this.a.k.a(">"), -☃, ☃ + (☃ << 24 & 0xFF000000));
        } else if (☃ < 0.0D) {
          this.a.k.a("<", -☃, -☃, ☃ + (☃ << 24 & 0xFF000000));
        }
      }
      this.a.k.a(☃, -☃ / 2, -☃, ☃ + (☃ << 24 & 0xFF000000));
      
      bni.H();
      ☃++;
    }
    bni.l();
    bni.H();
  }
  
  public void a(byi ☃, byz ☃)
  {
    if (☃.c() == null) {
      return;
    }
    String ☃ = ☃.c().d();
    if (!this.f.isEmpty()) {
      for (bds.a ☃ : this.f) {
        if (☃.a().equals(☃))
        {
          ☃.a(new bbj(☃.i(), ☃.j(), ☃.k()));
          return;
        }
      }
    }
    this.f.add(new bds.a(☃, new bbj(☃.i(), ☃.j(), ☃.k())));
  }
  
  public class a
  {
    private final String b;
    private long c;
    private bbj d;
    
    public a(String ☃, bbj ☃)
    {
      this.b = ☃;
      this.d = ☃;
      this.c = bcf.I();
    }
    
    public String a()
    {
      return this.b;
    }
    
    public long b()
    {
      return this.c;
    }
    
    public bbj c()
    {
      return this.d;
    }
    
    public void a(bbj ☃)
    {
      this.d = ☃;
      this.c = bcf.I();
    }
  }
}
