import com.google.common.base.Predicate;

public class arj
  implements Predicate<arc>
{
  private final ajt a;
  
  private arj(ajt ☃)
  {
    this.a = ☃;
  }
  
  public static arj a(ajt ☃)
  {
    return new arj(☃);
  }
  
  public boolean a(arc ☃)
  {
    if ((☃ == null) || (☃.t() != this.a)) {
      return false;
    }
    return true;
  }
}
