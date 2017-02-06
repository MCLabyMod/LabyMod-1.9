import io.netty.buffer.Unpooled;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bgl
  extends bft
{
  private static final Logger u = ;
  private static final kk v = new kk("textures/gui/container/villager.png");
  private ahf w;
  private bgl.a x;
  private bgl.a y;
  private int z;
  private eu A;
  
  public bgl(zi ☃, ahf ☃, aht ☃)
  {
    super(new abo(☃, ☃, ☃));
    this.w = ☃;
    this.A = ☃.i_();
  }
  
  public void b()
  {
    super.b();
    
    int ☃ = (this.l - this.f) / 2;
    int ☃ = (this.m - this.g) / 2;
    
    this.n.add(this.x = new bgl.a(1, ☃ + 120 + 27, ☃ + 24 - 1, true));
    this.n.add(this.y = new bgl.a(2, ☃ + 36 - 19, ☃ + 24 - 1, false));
    
    this.x.l = false;
    this.y.l = false;
  }
  
  protected void b(int ☃, int ☃)
  {
    String ☃ = this.A.c();
    this.q.a(☃, this.f / 2 - this.q.a(☃) / 2, 6, 4210752);
    this.q.a(bwo.a("container.inventory", new Object[0]), 8, this.g - 96 + 2, 4210752);
  }
  
  public void e()
  {
    super.e();
    
    ahh ☃ = this.w.b_(this.j.h);
    if (☃ != null)
    {
      this.x.l = (this.z < ☃.size() - 1);
      this.y.l = (this.z > 0);
    }
  }
  
  protected void a(bcz ☃)
  {
    boolean ☃ = false;
    if (☃ == this.x)
    {
      this.z += 1;
      ahh ☃ = this.w.b_(this.j.h);
      if ((☃ != null) && (this.z >= ☃.size())) {
        this.z = (☃.size() - 1);
      }
      ☃ = true;
    }
    else if (☃ == this.y)
    {
      this.z -= 1;
      if (this.z < 0) {
        this.z = 0;
      }
      ☃ = true;
    }
    if (☃)
    {
      ((abo)this.h).d(this.z);
      
      em ☃ = new em(Unpooled.buffer());
      ☃.writeInt(this.z);
      this.j.v().a(new iq("MC|TrSel", ☃));
    }
  }
  
  protected void a(float ☃, int ☃, int ☃)
  {
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    this.j.N().a(v);
    int ☃ = (this.l - this.f) / 2;
    int ☃ = (this.m - this.g) / 2;
    b(☃, ☃, 0, 0, this.f, this.g);
    
    ahh ☃ = this.w.b_(this.j.h);
    if ((☃ != null) && (!☃.isEmpty()))
    {
      int ☃ = this.z;
      if ((☃ < 0) || (☃ >= ☃.size())) {
        return;
      }
      ahg ☃ = (ahg)☃.get(☃);
      if (☃.h())
      {
        this.j.N().a(v);
        bni.c(1.0F, 1.0F, 1.0F, 1.0F);
        bni.g();
        b(this.i + 83, this.r + 21, 212, 0, 28, 21);
        b(this.i + 83, this.r + 51, 212, 0, 28, 21);
      }
    }
  }
  
  public void a(int ☃, int ☃, float ☃)
  {
    super.a(☃, ☃, ☃);
    
    ahh ☃ = this.w.b_(this.j.h);
    if ((☃ != null) && (!☃.isEmpty()))
    {
      int ☃ = (this.l - this.f) / 2;
      int ☃ = (this.m - this.g) / 2;
      
      int ☃ = this.z;
      ahg ☃ = (ahg)☃.get(☃);
      
      adq ☃ = ☃.a();
      adq ☃ = ☃.b();
      adq ☃ = ☃.d();
      
      bni.G();
      
      bcd.c();
      bni.g();
      bni.D();
      bni.h();
      bni.f();
      
      this.k.a = 100.0F;
      this.k.b(☃, ☃ + 36, ☃ + 24);
      this.k.a(this.q, ☃, ☃ + 36, ☃ + 24);
      if (☃ != null)
      {
        this.k.b(☃, ☃ + 62, ☃ + 24);
        this.k.a(this.q, ☃, ☃ + 62, ☃ + 24);
      }
      this.k.b(☃, ☃ + 120, ☃ + 24);
      this.k.a(this.q, ☃, ☃ + 120, ☃ + 24);
      this.k.a = 0.0F;
      
      bni.g();
      if ((c(36, 24, 16, 16, ☃, ☃)) && (☃ != null)) {
        a(☃, ☃, ☃);
      } else if ((☃ != null) && (c(62, 24, 16, 16, ☃, ☃)) && (☃ != null)) {
        a(☃, ☃, ☃);
      } else if ((☃ != null) && (c(120, 24, 16, 16, ☃, ☃)) && (☃ != null)) {
        a(☃, ☃, ☃);
      } else if ((☃.h()) && ((c(83, 21, 28, 21, ☃, ☃)) || (c(83, 51, 28, 21, ☃, ☃)))) {
        a(bwo.a("merchant.deprecated", new Object[0]), ☃, ☃);
      }
      bni.H();
      
      bni.f();
      bni.k();
      bcd.b();
    }
  }
  
  public ahf a()
  {
    return this.w;
  }
  
  static class a
    extends bcz
  {
    private final boolean o;
    
    public a(int ☃, int ☃, int ☃, boolean ☃)
    {
      super(☃, ☃, 12, 19, "");
      this.o = ☃;
    }
    
    public void a(bcf ☃, int ☃, int ☃)
    {
      if (!this.m) {
        return;
      }
      ☃.N().a(bgl.f());
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      
      boolean ☃ = (☃ >= this.h) && (☃ >= this.i) && (☃ < this.h + this.f) && (☃ < this.i + this.g);
      int ☃ = 0;
      int ☃ = 176;
      if (!this.l) {
        ☃ += this.f * 2;
      } else if (☃) {
        ☃ += this.f;
      }
      if (!this.o) {
        ☃ += this.g;
      }
      b(this.h, this.i, ☃, ☃, this.f, this.g);
    }
  }
}
