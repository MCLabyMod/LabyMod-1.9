import java.util.Comparator;

final class CustomItems$1
  implements Comparator
{
  public int compare(Object o1, Object o2)
  {
    CustomItemProperties cip1 = (CustomItemProperties)o1;
    CustomItemProperties cip2 = (CustomItemProperties)o2;
    if (cip1.layer != cip2.layer) {
      return cip1.layer - cip2.layer;
    }
    if (cip1.weight != cip2.weight) {
      return cip2.weight - cip1.weight;
    }
    if (!cip1.basePath.equals(cip2.basePath)) {
      return cip1.basePath.compareTo(cip2.basePath);
    }
    return cip1.name.compareTo(cip2.name);
  }
}
