import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;

public class bph$a
{
  private arr<?> a;
  private String b;
  private final List<arr<?>> c = Lists.newArrayList();
  
  public a a(arr<?> ☃)
  {
    this.a = ☃;
    return this;
  }
  
  public a a(String ☃)
  {
    this.b = ☃;
    return this;
  }
  
  public a a(arr<?>... ☃)
  {
    Collections.addAll(this.c, ☃);
    return this;
  }
  
  public bph a()
  {
    return new bph(this.a, this.b, this.c, null);
  }
}
