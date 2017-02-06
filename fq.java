import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

public class fq
  implements ff<fi>
{
  private Map<np, Integer> a;
  
  public fq() {}
  
  public fq(Map<np, Integer> ☃)
  {
    this.a = ☃;
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public void a(em ☃)
    throws IOException
  {
    int ☃ = ☃.g();
    this.a = Maps.newHashMap();
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      np ☃ = nt.a(☃.c(32767));
      int ☃ = ☃.g();
      if (☃ != null) {
        this.a.put(☃, Integer.valueOf(☃));
      }
    }
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.b(this.a.size());
    for (Map.Entry<np, Integer> ☃ : this.a.entrySet())
    {
      ☃.a(((np)☃.getKey()).e);
      ☃.b(((Integer)☃.getValue()).intValue());
    }
  }
  
  public Map<np, Integer> a()
  {
    return this.a;
  }
}
