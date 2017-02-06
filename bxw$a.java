import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;

public class bxw$a
{
  private List<bxw.b> a = Lists.newArrayList();
  
  public a a(bxo ☃, int ☃)
  {
    this.a.add(new bxw.b(☃, ☃));
    
    return this;
  }
  
  public bxw a()
  {
    Collections.sort(this.a);
    return new bxw(this.a);
  }
  
  public bxo b()
  {
    return ((bxw.b)this.a.get(0)).b;
  }
}
