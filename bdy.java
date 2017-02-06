import net.minecraft.realms.RealmsClickableScrolledSelectionList;
import net.minecraft.realms.Tezzelator;
import org.lwjgl.input.Mouse;

public class bdy
  extends bdq
{
  private final RealmsClickableScrolledSelectionList u;
  
  public bdy(RealmsClickableScrolledSelectionList ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
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
    if ((this.m > 0.0F) && (Mouse.getEventButtonState())) {
      this.u.customMouseEvent(this.d, this.e, this.t, this.n, this.h);
    }
  }
  
  public void a(int ☃, int ☃, int ☃, Tezzelator ☃)
  {
    this.u.renderSelected(☃, ☃, ☃, ☃);
  }
  
  protected void b(int ☃, int ☃, int ☃, int ☃)
  {
    int ☃ = b();
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      int ☃ = ☃ + ☃ * this.h + this.t;
      int ☃ = this.h - 4;
      if ((☃ > this.e) || (☃ + ☃ < this.d)) {
        a(☃, ☃, ☃);
      }
      if ((this.r) && (a(☃))) {
        a(this.b, ☃, ☃, Tezzelator.instance);
      }
      a(☃, ☃, ☃, ☃, ☃, ☃);
    }
  }
}
