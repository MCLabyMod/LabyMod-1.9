import java.util.Set;

public abstract interface db<K, V>
  extends Iterable<V>
{
  public abstract V c(K paramK);
  
  public abstract void a(K paramK, V paramV);
  
  public abstract Set<K> c();
}
