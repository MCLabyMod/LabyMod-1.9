import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class adp
{
  private final Map<ado, adp.a> a;
  private int b;
  
  public adp()
  {
    this.a = Maps.newHashMap();
  }
  
  public boolean a(ado ☃)
  {
    return a(☃, 0.0F) > 0.0F;
  }
  
  public float a(ado ☃, float ☃)
  {
    adp.a ☃ = (adp.a)this.a.get(☃);
    if (☃ != null)
    {
      float ☃ = ☃.b - ☃.a;
      float ☃ = ☃.b - (this.b + ☃);
      return on.a(☃ / ☃, 0.0F, 1.0F);
    }
    return 0.0F;
  }
  
  public void a()
  {
    this.b += 1;
    Iterator<Map.Entry<ado, adp.a>> ☃;
    if (!this.a.isEmpty()) {
      for (☃ = this.a.entrySet().iterator(); ☃.hasNext();)
      {
        Map.Entry<ado, adp.a> ☃ = (Map.Entry)☃.next();
        if (((adp.a)☃.getValue()).b <= this.b)
        {
          ☃.remove();
          c((ado)☃.getKey());
        }
      }
    }
  }
  
  public void a(ado ☃, int ☃)
  {
    this.a.put(☃, new adp.a(this.b, this.b + ☃, null));
    b(☃, ☃);
  }
  
  public void b(ado ☃)
  {
    this.a.remove(☃);
    c(☃);
  }
  
  protected void b(ado ☃, int ☃) {}
  
  protected void c(ado ☃) {}
  
  class a
  {
    final int a;
    final int b;
    
    private a(int ☃, int ☃)
    {
      this.a = ☃;
      this.b = ☃;
    }
  }
}
