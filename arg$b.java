import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;
import com.google.common.cache.LoadingCache;

public class arg$b
{
  private final cj a;
  private final cq b;
  private final cq c;
  private final LoadingCache<cj, arf> d;
  private final int e;
  private final int f;
  private final int g;
  
  public arg$b(cj ☃, cq ☃, cq ☃, LoadingCache<cj, arf> ☃, int ☃, int ☃, int ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
    this.d = ☃;
    this.e = ☃;
    this.f = ☃;
    this.g = ☃;
  }
  
  public cj a()
  {
    return this.a;
  }
  
  public cq b()
  {
    return this.b;
  }
  
  public cq c()
  {
    return this.c;
  }
  
  public int d()
  {
    return this.e;
  }
  
  public int e()
  {
    return this.f;
  }
  
  public arf a(int ☃, int ☃, int ☃)
  {
    return (arf)this.d.getUnchecked(arg.a(this.a, b(), c(), ☃, ☃, ☃));
  }
  
  public String toString()
  {
    return Objects.toStringHelper(this).add("up", this.c).add("forwards", this.b).add("frontTopLeft", this.a).toString();
  }
}
