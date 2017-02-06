import com.google.common.collect.ImmutableMap;
import java.util.Collection;

public abstract interface arc
  extends arb
{
  public abstract Collection<arr<?>> r();
  
  public abstract <T extends Comparable<T>> T c(arr<T> paramarr);
  
  public abstract <T extends Comparable<T>, V extends T> arc a(arr<T> paramarr, V paramV);
  
  public abstract <T extends Comparable<T>> arc a(arr<T> paramarr);
  
  public abstract ImmutableMap<arr<?>, Comparable<?>> s();
  
  public abstract ajt t();
}
