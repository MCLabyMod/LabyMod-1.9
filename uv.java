import java.util.List;

public class uv
  extends vc
{
  private final boolean a;
  private int b;
  private final Class<?>[] c;
  
  public uv(sh ☃, boolean ☃, Class<?>... ☃)
  {
    super(☃, true);
    this.a = ☃;
    this.c = ☃;
    a(1);
  }
  
  public boolean a()
  {
    int ☃ = this.e.bH();
    sa ☃ = this.e.bG();
    return (☃ != this.b) && (☃ != null) && (a(☃, false));
  }
  
  public void c()
  {
    this.e.c(this.e.bG());
    this.g = this.e.A();
    this.b = this.e.bH();
    this.h = 300;
    if (this.a)
    {
      double ☃ = f();
      List<sh> ☃ = this.e.l.a(this.e.getClass(), new bbh(this.e.p, this.e.q, this.e.r, this.e.p + 1.0D, this.e.q + 1.0D, this.e.r + 1.0D).b(☃, 10.0D, ☃));
      for (sh ☃ : ☃) {
        if ((this.e != ☃) && 
        
          (☃.A() == null) && 
          
          ((!(this.e instanceof sk)) || (((sk)this.e).dc() == ((sk)☃).dc())) && 
          
          (!☃.r(this.e.bG())))
        {
          boolean ☃ = false;
          for (Class<?> ☃ : this.c) {
            if (☃.getClass() == ☃)
            {
              ☃ = true;
              break;
            }
          }
          if (!☃) {
            a(☃, this.e.bG());
          }
        }
      }
    }
    super.c();
  }
  
  protected void a(sh ☃, sa ☃)
  {
    ☃.c(☃);
  }
}
