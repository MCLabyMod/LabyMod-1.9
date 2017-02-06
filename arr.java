import com.google.common.base.Optional;
import java.util.Collection;

public abstract interface arr<T extends Comparable<T>>
{
  public abstract String a();
  
  public abstract Collection<T> c();
  
  public abstract Class<T> b();
  
  public abstract Optional<T> b(String paramString);
  
  public abstract String a(T paramT);
}
