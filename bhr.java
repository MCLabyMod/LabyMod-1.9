import com.google.common.collect.Lists;
import java.util.List;

public class bhr
  implements bht
{
  private final List<bhu> a = Lists.newArrayList();
  
  public bhr()
  {
    this.a.add(new bhx());
    this.a.add(new bhy());
  }
  
  public List<bhu> a()
  {
    return this.a;
  }
  
  public eu b()
  {
    return new fa("Press a key to select a command, and again to use it.");
  }
}
