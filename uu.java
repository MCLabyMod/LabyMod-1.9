import java.util.Random;

public class uu
  extends vc
{
  wh a;
  sa b;
  
  public uu(wh ☃)
  {
    super(☃, false, true);
    this.a = ☃;
    a(1);
  }
  
  public boolean a()
  {
    vp ☃ = this.a.o();
    if (☃ == null) {
      return false;
    }
    this.b = ☃.b(this.a);
    if ((this.b instanceof yi)) {
      return false;
    }
    if (!a(this.b, false))
    {
      if (this.e.bF().nextInt(20) == 0)
      {
        this.b = ☃.c(this.a);
        return a(this.b, false);
      }
      return false;
    }
    return true;
  }
  
  public void c()
  {
    this.a.c(this.b);
    super.c();
  }
}
