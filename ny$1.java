import com.google.common.collect.Iterators;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class ny$1
  implements Iterable<S>
{
  ny$1(ny paramny, Class paramClass) {}
  
  public Iterator<S> iterator()
  {
    List<T> ☃ = (List)ny.a(this.b).get(this.b.b(this.a));
    if (☃ == null) {
      return Iterators.emptyIterator();
    }
    Iterator<T> ☃ = ☃.iterator();
    return Iterators.filter(☃, this.a);
  }
}
