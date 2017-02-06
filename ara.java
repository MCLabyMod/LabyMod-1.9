import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Iterables;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;

public abstract class ara
  implements arc
{
  private static final Joiner a = Joiner.on(',');
  private static final Function<Map.Entry<arr<?>, Comparable<?>>, String> b = new Function()
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
  };
  private int blockId = -1;
  private int blockStateId = -1;
  private int metadata = -1;
  private kk blockLocation = null;
  
  public int getBlockId()
  {
    if (this.blockId < 0) {
      this.blockId = ajt.a(t());
    }
    return this.blockId;
  }
  
  public int getBlockStateId()
  {
    if (this.blockStateId < 0) {
      this.blockStateId = ajt.j(this);
    }
    return this.blockStateId;
  }
  
  public int getMetadata()
  {
    if (this.metadata < 0) {
      this.metadata = t().e(this);
    }
    return this.metadata;
  }
  
  public kk getBlockLocation()
  {
    if (this.blockLocation == null) {
      this.blockLocation = ((kk)ajt.h.b(t()));
    }
    return this.blockLocation;
  }
  
  public ImmutableTable<arr, Comparable, arc> getPropertyValueTable()
  {
    return null;
  }
  
  public <T extends Comparable<T>> arc a(arr<T> property)
  {
    return a(property, (Comparable)a(property.c(), c(property)));
  }
  
  protected static <T> T a(Collection<T> values, T currentValue)
  {
    Iterator<T> iterator = values.iterator();
    while (iterator.hasNext()) {
      if (iterator.next().equals(currentValue))
      {
        if (iterator.hasNext()) {
          return (T)iterator.next();
        }
        return (T)values.iterator().next();
      }
    }
    return (T)iterator.next();
  }
  
  public String toString()
  {
    StringBuilder stringbuilder = new StringBuilder();
    stringbuilder.append(ajt.h.b(t()));
    if (!s().isEmpty())
    {
      stringbuilder.append("[");
      a.appendTo(stringbuilder, Iterables.transform(s().entrySet(), b));
      stringbuilder.append("]");
    }
    return stringbuilder.toString();
  }
}
