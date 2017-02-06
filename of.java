import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class of<V>
  implements Map<String, V>
{
  private final Map<String, V> a = Maps.newLinkedHashMap();
  
  public int size()
  {
    return this.a.size();
  }
  
  public boolean isEmpty()
  {
    return this.a.isEmpty();
  }
  
  public boolean containsKey(Object ☃)
  {
    return this.a.containsKey(☃.toString().toLowerCase());
  }
  
  public boolean containsValue(Object ☃)
  {
    return this.a.containsKey(☃);
  }
  
  public V get(Object ☃)
  {
    return (V)this.a.get(☃.toString().toLowerCase());
  }
  
  public V a(String ☃, V ☃)
  {
    return (V)this.a.put(☃.toLowerCase(), ☃);
  }
  
  public V remove(Object ☃)
  {
    return (V)this.a.remove(☃.toString().toLowerCase());
  }
  
  public void putAll(Map<? extends String, ? extends V> ☃)
  {
    for (Map.Entry<? extends String, ? extends V> ☃ : ☃.entrySet()) {
      a((String)☃.getKey(), ☃.getValue());
    }
  }
  
  public void clear()
  {
    this.a.clear();
  }
  
  public Set<String> keySet()
  {
    return this.a.keySet();
  }
  
  public Collection<V> values()
  {
    return this.a.values();
  }
  
  public Set<Map.Entry<String, V>> entrySet()
  {
    return this.a.entrySet();
  }
}
