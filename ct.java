import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;

public class ct<T>
  implements cs<T>
{
  private final IdentityHashMap<T, Integer> a;
  private final List<T> b;
  
  public ct()
  {
    this(512);
  }
  
  public ct(int expectedSize)
  {
    this.b = Lists.newArrayListWithExpectedSize(expectedSize);
    this.a = new IdentityHashMap(expectedSize);
  }
  
  public void a(T key, int value)
  {
    this.a.put(key, Integer.valueOf(value));
    while (this.b.size() <= value) {
      this.b.add(null);
    }
    this.b.set(value, key);
  }
  
  public int a(T key)
  {
    Integer integer = (Integer)this.a.get(key);
    return integer == null ? -1 : integer.intValue();
  }
  
  public final T a(int value)
  {
    return (T)((value >= 0) && (value < this.b.size()) ? this.b.get(value) : null);
  }
  
  public Iterator<T> iterator()
  {
    return Iterators.filter(this.b.iterator(), Predicates.notNull());
  }
  
  public int a()
  {
    return this.a.size();
  }
  
  public List getObjectList()
  {
    return this.b;
  }
}
