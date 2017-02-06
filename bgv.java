import com.google.common.collect.Lists;
import java.util.List;

public class bgv
  extends bdl
{
  private final bgr u;
  private final List<bgu> v = Lists.newArrayList();
  private final List<bgt> w = Lists.newArrayList();
  private final bdl.a x = new bgs();
  private int y = -1;
  
  public bgv(bgr ☃, bcf ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    super(☃, ☃, ☃, ☃, ☃, ☃);
    this.u = ☃;
  }
  
  public bdl.a b(int ☃)
  {
    if (☃ < this.v.size()) {
      return (bdl.a)this.v.get(☃);
    }
    ☃ -= this.v.size();
    if (☃ == 0) {
      return this.x;
    }
    ☃--;
    
    return (bdl.a)this.w.get(☃);
  }
  
  protected int b()
  {
    return this.v.size() + 1 + this.w.size();
  }
  
  public void c(int ☃)
  {
    this.y = ☃;
  }
  
  protected boolean a(int ☃)
  {
    return ☃ == this.y;
  }
  
  public int e()
  {
    return this.y;
  }
  
  public void a(bky ☃)
  {
    this.v.clear();
    for (int ☃ = 0; ☃ < ☃.c(); ☃++) {
      this.v.add(new bgu(this.u, ☃.a(☃)));
    }
  }
  
  public void a(List<byp.a> ☃)
  {
    this.w.clear();
    for (byp.a ☃ : ☃) {
      this.w.add(new bgt(this.u, ☃));
    }
  }
  
  protected int d()
  {
    return super.d() + 30;
  }
  
  public int c()
  {
    return super.c() + 85;
  }
}
