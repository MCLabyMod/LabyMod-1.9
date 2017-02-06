import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;

public class bwy
  implements bwu
{
  private final List<bwx> a;
  private final int b;
  private final int c;
  private final int d;
  private final boolean e;
  
  public bwy(List<bwx> ☃, int ☃, int ☃, int ☃, boolean ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
    this.d = ☃;
    this.e = ☃;
  }
  
  public int a()
  {
    return this.c;
  }
  
  public int b()
  {
    return this.b;
  }
  
  public int c()
  {
    return this.a.size();
  }
  
  public int d()
  {
    return this.d;
  }
  
  public boolean e()
  {
    return this.e;
  }
  
  private bwx d(int ☃)
  {
    return (bwx)this.a.get(☃);
  }
  
  public int a(int ☃)
  {
    bwx ☃ = d(☃);
    if (☃.a()) {
      return this.d;
    }
    return ☃.b();
  }
  
  public boolean b(int ☃)
  {
    return !((bwx)this.a.get(☃)).a();
  }
  
  public int c(int ☃)
  {
    return ((bwx)this.a.get(☃)).c();
  }
  
  public Set<Integer> f()
  {
    Set<Integer> ☃ = Sets.newHashSet();
    for (bwx ☃ : this.a) {
      ☃.add(Integer.valueOf(☃.c()));
    }
    return ☃;
  }
}
