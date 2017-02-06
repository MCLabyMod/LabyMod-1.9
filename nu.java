import com.google.common.collect.Maps;
import java.util.Map;

public class nu
{
  protected final Map<np, nr> a = Maps.newConcurrentMap();
  
  public boolean a(nj ☃)
  {
    return a(☃) > 0;
  }
  
  public boolean b(nj ☃)
  {
    return (☃.c == null) || (a(☃.c));
  }
  
  public int c(nj ☃)
  {
    if (a(☃)) {
      return 0;
    }
    int ☃ = 0;
    nj ☃ = ☃.c;
    while ((☃ != null) && (!a(☃)))
    {
      ☃ = ☃.c;
      ☃++;
    }
    return ☃;
  }
  
  public void b(zj ☃, np ☃, int ☃)
  {
    if ((☃.d()) && (!b((nj)☃))) {
      return;
    }
    a(☃, ☃, a(☃) + ☃);
  }
  
  public void a(zj ☃, np ☃, int ☃)
  {
    nr ☃ = (nr)this.a.get(☃);
    if (☃ == null)
    {
      ☃ = new nr();
      this.a.put(☃, ☃);
    }
    ☃.a(☃);
  }
  
  public int a(np ☃)
  {
    nr ☃ = (nr)this.a.get(☃);
    return ☃ == null ? 0 : ☃.a();
  }
  
  public <T extends ns> T b(np ☃)
  {
    nr ☃ = (nr)this.a.get(☃);
    if (☃ != null) {
      return ☃.b();
    }
    return null;
  }
  
  public <T extends ns> T a(np ☃, T ☃)
  {
    nr ☃ = (nr)this.a.get(☃);
    if (☃ == null)
    {
      ☃ = new nr();
      this.a.put(☃, ☃);
    }
    ☃.a(☃);
    
    return ☃;
  }
}
