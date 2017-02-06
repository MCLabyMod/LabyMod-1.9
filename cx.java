import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.util.Iterator;
import java.util.Map;

public class cx<K, V>
  extends dd<K, V>
  implements cs<V>
{
  protected final oa<V> a = new oa(256);
  protected final Map<V, K> b;
  
  public cx()
  {
    this.b = ((BiMap)this.c).inverse();
  }
  
  public void a(int ☃, K ☃, V ☃)
  {
    this.a.a(☃, ☃);
    a(☃, ☃);
  }
  
  protected Map<K, V> b()
  {
    return HashBiMap.create();
  }
  
  public V c(K ☃)
  {
    return (V)super.c(☃);
  }
  
  public K b(V ☃)
  {
    return (K)this.b.get(☃);
  }
  
  public boolean d(K ☃)
  {
    return super.d(☃);
  }
  
  public int a(V ☃)
  {
    return this.a.a(☃);
  }
  
  public V a(int ☃)
  {
    return (V)this.a.a(☃);
  }
  
  public Iterator<V> iterator()
  {
    return this.a.iterator();
  }
}
