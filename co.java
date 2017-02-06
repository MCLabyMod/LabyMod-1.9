import java.util.Random;
import org.apache.commons.lang3.Validate;

public class co<K, V>
  extends cx<K, V>
{
  private final K d;
  private V e;
  
  public co(K ☃)
  {
    this.d = ☃;
  }
  
  public void a(int ☃, K ☃, V ☃)
  {
    if (this.d.equals(☃)) {
      this.e = ☃;
    }
    super.a(☃, ☃, ☃);
  }
  
  public void a()
  {
    Validate.notNull(this.e, "Missing default of DefaultedMappedRegistry: " + this.d, new Object[0]);
  }
  
  public int a(V ☃)
  {
    int ☃ = super.a(☃);
    return ☃ == -1 ? super.a(this.e) : ☃;
  }
  
  public K b(V ☃)
  {
    K ☃ = super.b(☃);
    return (K)(☃ == null ? this.d : ☃);
  }
  
  public V c(K ☃)
  {
    V ☃ = super.c(☃);
    return (V)(☃ == null ? this.e : ☃);
  }
  
  public V a(int ☃)
  {
    V ☃ = super.a(☃);
    return (V)(☃ == null ? this.e : ☃);
  }
  
  public V a(Random ☃)
  {
    V ☃ = super.a(☃);
    return (V)(☃ == null ? this.e : ☃);
  }
}
