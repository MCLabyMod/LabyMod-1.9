import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class dd<K, V>
  implements db<K, V>
{
  private static final Logger a = ;
  protected final Map<K, V> c = b();
  private Object[] b;
  
  protected Map<K, V> b()
  {
    return Maps.newHashMap();
  }
  
  public V c(K ☃)
  {
    return (V)this.c.get(☃);
  }
  
  public void a(K ☃, V ☃)
  {
    Validate.notNull(☃);
    Validate.notNull(☃);
    
    this.b = null;
    if (this.c.containsKey(☃)) {
      a.debug("Adding duplicate key '" + ☃ + "' to registry");
    }
    this.c.put(☃, ☃);
  }
  
  public Set<K> c()
  {
    return Collections.unmodifiableSet(this.c.keySet());
  }
  
  public V a(Random ☃)
  {
    if (this.b == null)
    {
      Collection<?> ☃ = this.c.values();
      if (☃.isEmpty()) {
        return null;
      }
      this.b = ☃.toArray(new Object[☃.size()]);
    }
    return (V)this.b[☃.nextInt(this.b.length)];
  }
  
  public boolean d(K ☃)
  {
    return this.c.containsKey(☃);
  }
  
  public Iterator<V> iterator()
  {
    return this.c.values().iterator();
  }
}
