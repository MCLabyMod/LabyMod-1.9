import com.google.common.base.Function;
import java.util.Map.Entry;

final class ara$1
  implements Function<Map.Entry<arr<?>, Comparable<?>>, String>
{
  public String a(Map.Entry<arr<?>, Comparable<?>> p_apply_1_)
  {
    if (p_apply_1_ == null) {
      return "<NULL>";
    }
    arr<?> iproperty = (arr)p_apply_1_.getKey();
    return iproperty.a() + "=" + a(iproperty, (Comparable)p_apply_1_.getValue());
  }
  
  private <T extends Comparable<T>> String a(arr<T> property, Comparable<?> entry)
  {
    return property.a(entry);
  }
}
