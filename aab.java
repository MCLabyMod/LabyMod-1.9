import java.util.Random;

public class aab
  extends zy
{
  public aab(aht ☃)
  {
    super(☃);
  }
  
  public aab(aht ☃, sa ☃)
  {
    super(☃, ☃);
  }
  
  public aab(aht ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃);
  }
  
  protected float j()
  {
    return 0.07F;
  }
  
  protected void a(bbi ☃)
  {
    if (!this.l.E)
    {
      this.l.b(2002, new cj(this), 0);
      
      int ☃ = 3 + this.l.r.nextInt(5) + this.l.r.nextInt(5);
      while (☃ > 0)
      {
        int ☃ = rx.a(☃);
        ☃ -= ☃;
        this.l.a(new rx(this.l, this.p, this.q, this.r, ☃));
      }
      T();
    }
  }
}
