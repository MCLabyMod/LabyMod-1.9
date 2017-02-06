import java.util.List;
import java.util.Random;

public class uo
  extends tk
{
  private ze a;
  private wh b;
  private int c;
  private boolean d;
  
  public uo(ze ☃)
  {
    this.a = ☃;
    a(3);
  }
  
  public boolean a()
  {
    if (this.a.l() >= 0) {
      return false;
    }
    if (!this.a.l.B()) {
      return false;
    }
    List<wh> ☃ = this.a.l.a(wh.class, this.a.bl().b(6.0D, 2.0D, 6.0D));
    if (☃.isEmpty()) {
      return false;
    }
    for (wh ☃ : ☃) {
      if (☃.da() > 0)
      {
        this.b = ☃;
        break;
      }
    }
    return this.b != null;
  }
  
  public boolean b()
  {
    return this.b.da() > 0;
  }
  
  public void c()
  {
    this.c = this.a.bF().nextInt(320);
    this.d = false;
    this.b.x().o();
  }
  
  public void d()
  {
    this.b = null;
    this.a.x().o();
  }
  
  public void e()
  {
    this.a.t().a(this.b, 30.0F, 30.0F);
    if (this.b.da() == this.c)
    {
      this.a.x().a(this.b, 0.5D);
      this.d = true;
    }
    if ((this.d) && 
      (this.a.h(this.b) < 4.0D))
    {
      this.b.a(false);
      this.a.x().o();
    }
  }
}
