import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

public class byz
  implements bza<byf>
{
  private final List<bza<byf>> a = Lists.newArrayList();
  private final Random b = new Random();
  private final kk c;
  private final eu d;
  
  public byz(kk ☃, String ☃)
  {
    this.c = ☃;
    
    this.d = (☃ == null ? null : new fb(☃, new Object[0]));
  }
  
  public int e()
  {
    int ☃ = 0;
    for (bza<byf> ☃ : this.a) {
      ☃ += ☃.e();
    }
    return ☃;
  }
  
  public byf a()
  {
    int ☃ = e();
    if ((this.a.isEmpty()) || (☃ == 0)) {
      return byx.a;
    }
    int ☃ = this.b.nextInt(☃);
    for (bza<byf> ☃ : this.a)
    {
      ☃ -= ☃.e();
      if (☃ < 0) {
        return (byf)☃.i();
      }
    }
    return byx.a;
  }
  
  public void a(bza<byf> ☃)
  {
    this.a.add(☃);
  }
  
  public kk b()
  {
    return this.c;
  }
  
  public eu c()
  {
    return this.d;
  }
}
