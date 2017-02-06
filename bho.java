import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bho
  extends bdl
{
  private static final Logger u = ;
  private final bhm v;
  private final List<bhn> w = Lists.newArrayList();
  private int x = -1;
  
  public bho(bhm ☃, bcf ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    super(☃, ☃, ☃, ☃, ☃, ☃);
    this.v = ☃;
    
    e();
  }
  
  public void e()
  {
    azk ☃ = this.a.g();
    List<azl> ☃;
    try
    {
      ☃ = ☃.b();
    }
    catch (azj ☃)
    {
      u.error("Couldn't load level list", ☃);
      this.a.a(new ber("Unable to load worlds", ☃.getMessage()));
      return;
    }
    Collections.sort(☃);
    for (azl ☃ : ☃) {
      this.w.add(new bhn(this, ☃, this.a.g()));
    }
  }
  
  public bhn c(int ☃)
  {
    return (bhn)this.w.get(☃);
  }
  
  protected int b()
  {
    return this.w.size();
  }
  
  protected int d()
  {
    return super.d() + 20;
  }
  
  public int c()
  {
    return super.c() + 50;
  }
  
  public void d(int ☃)
  {
    this.x = ☃;
    this.v.a(f());
  }
  
  protected boolean a(int ☃)
  {
    return ☃ == this.x;
  }
  
  public bhn f()
  {
    return (this.x < 0) || (this.x >= b()) ? null : c(this.x);
  }
  
  public bhm g()
  {
    return this.v;
  }
}
