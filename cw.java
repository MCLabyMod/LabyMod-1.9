import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class cw
{
  public static <K, V> Map<K, V> b(Iterable<K> ☃, Iterable<V> ☃)
  {
    return a(☃, ☃, Maps.newLinkedHashMap());
  }
  
  public static <K, V> Map<K, V> a(Iterable<K> ☃, Iterable<V> ☃, Map<K, V> ☃)
  {
    Iterator<V> ☃ = ☃.iterator();
    for (K ☃ : ☃) {
      ☃.put(☃, ☃.next());
    }
    if (☃.hasNext()) {
      throw new NoSuchElementException();
    }
    return ☃;
  }
}
