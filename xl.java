import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class xl
{
  private static final Logger a = ;
  private final wu b;
  private final xe[] c = new xe[xk.c()];
  private xe d;
  
  public xl(wu ☃)
  {
    this.b = ☃;
    
    a(xk.k);
  }
  
  public void a(xk<?> ☃)
  {
    if ((this.d != null) && (☃ == this.d.i())) {
      return;
    }
    if (this.d != null) {
      this.d.e();
    }
    this.d = b(☃);
    if (!this.b.l.E) {
      this.b.R().b(wu.a, Integer.valueOf(☃.b()));
    }
    a.debug("Dragon is now in phase {} on the {}", new Object[] { ☃, this.b.l.E ? "client" : "server" });
    
    this.d.d();
  }
  
  public xe a()
  {
    return this.d;
  }
  
  public <T extends xe> T b(xk<T> ☃)
  {
    int ☃ = ☃.b();
    if (this.c[☃] == null) {
      this.c[☃] = ☃.a(this.b);
    }
    return this.c[☃];
  }
}
