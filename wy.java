import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class wy
  extends ww
{
  private static final Logger b = ;
  private bbj c;
  private int d = 0;
  
  public wy(wu ☃)
  {
    super(☃);
  }
  
  public void c()
  {
    if (this.c == null)
    {
      b.warn("Aborting charge player as no target was set.");
      this.a.cT().a(xk.a);
      return;
    }
    if ((this.d > 0) && 
      (this.d++ >= 10))
    {
      this.a.cT().a(xk.a);
      return;
    }
    double ☃ = this.c.c(this.a.p, this.a.q, this.a.r);
    if ((☃ < 100.0D) || (☃ > 22500.0D) || (this.a.A) || (this.a.B)) {
      this.d += 1;
    }
  }
  
  public void d()
  {
    this.c = null;
    this.d = 0;
  }
  
  public void a(bbj ☃)
  {
    this.c = ☃;
  }
  
  public float f()
  {
    return 3.0F;
  }
  
  public bbj g()
  {
    return this.c;
  }
  
  public xk<wy> i()
  {
    return xk.i;
  }
}
