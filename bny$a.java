import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class bny$a
{
  private final Map<String, bny.b> a = Maps.newLinkedHashMap();
  private final kk b;
  private final String c;
  private String d;
  
  public bny$a(String ☃, kk ☃, String ☃)
  {
    this.d = ☃;
    this.b = ☃;
    this.c = ☃;
  }
  
  public kk a(String ☃, List<apt.a> ☃, List<act> ☃)
  {
    if (☃.isEmpty()) {
      return null;
    }
    ☃ = this.d + ☃;
    
    bny.b ☃ = (bny.b)this.a.get(☃);
    if (☃ == null)
    {
      if ((this.a.size() >= 256) && (!a())) {
        return bny.d;
      }
      List<String> ☃ = Lists.newArrayList();
      for (apt.a ☃ : ☃) {
        ☃.add(this.c + ☃.a() + ".png");
      }
      ☃ = new bny.b(null);
      ☃.b = new kk(☃);
      bcf.z().N().a(☃.b, new bva(this.b, ☃, ☃));
      this.a.put(☃, ☃);
    }
    ☃.a = System.currentTimeMillis();
    return ☃.b;
  }
  
  private boolean a()
  {
    long ☃ = System.currentTimeMillis();
    Iterator<String> ☃ = this.a.keySet().iterator();
    while (☃.hasNext())
    {
      String ☃ = (String)☃.next();
      bny.b ☃ = (bny.b)this.a.get(☃);
      if (☃ - ☃.a > 5000L)
      {
        bcf.z().N().c(☃.b);
        ☃.remove();
        return true;
      }
    }
    return this.a.size() < 256;
  }
}
