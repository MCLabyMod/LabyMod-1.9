import net.minecraft.realms.RealmsSimpleScrolledSelectionList;

public class beb
  extends bdq
{
  private final RealmsSimpleScrolledSelectionList u;
  
  public beb(RealmsSimpleScrolledSelectionList ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    super(bcf.z(), ☃, ☃, ☃, ☃, ☃);
    this.u = ☃;
  }
  
  protected int b()
  {
    return this.u.getItemCount();
  }
  
  protected void a(int ☃, boolean ☃, int ☃, int ☃)
  {
    this.u.selectItem(☃, ☃, ☃, ☃);
  }
  
  protected boolean a(int ☃)
  {
    return this.u.isSelectedItem(☃);
  }
  
  protected void a()
  {
    this.u.renderBackground();
  }
  
  protected void a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    this.u.renderItem(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public int e()
  {
    return this.b;
  }
  
  public int f()
  {
    return this.j;
  }
  
  public int g()
  {
    return this.i;
  }
  
  protected int k()
  {
    return this.u.getMaxPosition();
  }
  
  protected int d()
  {
    return this.u.getScrollbarPosition();
  }
  
  public void p()
  {
    super.p();
  }
  
  public void a(int ☃, int ☃, float ☃)
  {
    if (!this.q) {
      return;
    }
    this.i = ☃;
    this.j = ☃;
    a();
    
    int ☃ = d();
    int ☃ = ☃ + 6;
    
    l();
    
    bni.g();
    bni.p();
    bnu ☃ = bnu.a();
    bmz ☃ = ☃.c();
    
    int ☃ = this.g + this.b / 2 - c() / 2 + 2;
    int ☃ = this.d + 4 - (int)this.n;
    if (this.s) {
      a(☃, ☃, ☃);
    }
    b(☃, ☃, ☃, ☃);
    
    bni.j();
    
    int ☃ = 4;
    
    c(0, this.d, 255, 255);
    c(this.e, this.c, 255, 255);
    
    bni.m();
    bni.a(bni.r.l, bni.l.j, bni.r.o, bni.l.e);
    bni.d();
    bni.j(7425);
    
    bni.z();
    
    int ☃ = m();
    if (☃ > 0)
    {
      int ☃ = (this.e - this.d) * (this.e - this.d) / k();
      ☃ = on.a(☃, 32, this.e - this.d - 8);
      
      int ☃ = (int)this.n * (this.e - this.d - ☃) / ☃ + this.d;
      if (☃ < this.d) {
        ☃ = this.d;
      }
      ☃.a(7, bvp.i);
      ☃.b(☃, this.e, 0.0D).a(0.0D, 1.0D).b(0, 0, 0, 255).d();
      ☃.b(☃, this.e, 0.0D).a(1.0D, 1.0D).b(0, 0, 0, 255).d();
      ☃.b(☃, this.d, 0.0D).a(1.0D, 0.0D).b(0, 0, 0, 255).d();
      ☃.b(☃, this.d, 0.0D).a(0.0D, 0.0D).b(0, 0, 0, 255).d();
      ☃.b();
      
      ☃.a(7, bvp.i);
      ☃.b(☃, ☃ + ☃, 0.0D).a(0.0D, 1.0D).b(128, 128, 128, 255).d();
      ☃.b(☃, ☃ + ☃, 0.0D).a(1.0D, 1.0D).b(128, 128, 128, 255).d();
      ☃.b(☃, ☃, 0.0D).a(1.0D, 0.0D).b(128, 128, 128, 255).d();
      ☃.b(☃, ☃, 0.0D).a(0.0D, 0.0D).b(128, 128, 128, 255).d();
      ☃.b();
      
      ☃.a(7, bvp.i);
      ☃.b(☃, ☃ + ☃ - 1, 0.0D).a(0.0D, 1.0D).b(192, 192, 192, 255).d();
      ☃.b(☃ - 1, ☃ + ☃ - 1, 0.0D).a(1.0D, 1.0D).b(192, 192, 192, 255).d();
      ☃.b(☃ - 1, ☃, 0.0D).a(1.0D, 0.0D).b(192, 192, 192, 255).d();
      ☃.b(☃, ☃, 0.0D).a(0.0D, 0.0D).b(192, 192, 192, 255).d();
      ☃.b();
    }
    b(☃, ☃);
    
    bni.y();
    
    bni.j(7424);
    bni.e();
    bni.l();
  }
}
