import com.google.common.collect.Lists;
import java.util.List;

public abstract class bnd
{
  private double c;
  private double d;
  private double e;
  protected List<bqf> a = Lists.newArrayListWithCapacity(17424);
  protected boolean b;
  
  public void a(double ☃, double ☃, double ☃)
  {
    this.b = true;
    this.a.clear();
    
    this.c = ☃;
    this.d = ☃;
    this.e = ☃;
  }
  
  public void a(bqf ☃)
  {
    cj ☃ = ☃.k();
    bni.c((float)(☃.p() - this.c), (float)(☃.q() - this.d), (float)(☃.r() - this.e));
  }
  
  public void a(bqf ☃, ahm ☃)
  {
    this.a.add(☃);
  }
  
  public abstract void a(ahm paramahm);
}
