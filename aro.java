import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import java.util.Collection;

public class aro
  extends arp<cq>
{
  protected aro(String ☃, Collection<cq> ☃)
  {
    super(☃, cq.class, ☃);
  }
  
  public static aro a(String ☃)
  {
    return a(☃, Predicates.alwaysTrue());
  }
  
  public static aro a(String ☃, Predicate<cq> ☃)
  {
    return a(☃, Collections2.filter(Lists.newArrayList(cq.values()), ☃));
  }
  
  public static aro a(String ☃, Collection<cq> ☃)
  {
    return new aro(☃, ☃);
  }
}
