import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Map.Entry;

public class bnl
{
  private final Map<Integer, bxt> a = Maps.newHashMap();
  private final Map<Integer, bxo> b = Maps.newHashMap();
  private final Map<ado, bnm> c = Maps.newHashMap();
  private final bxs d;
  
  public bnl(bxs ☃)
  {
    this.d = ☃;
  }
  
  public bvh a(ado ☃)
  {
    return a(☃, 0);
  }
  
  public bvh a(ado ☃, int ☃)
  {
    return a(new adq(☃, 1, ☃)).d();
  }
  
  public bxo a(adq ☃)
  {
    ado ☃ = ☃.b();
    
    bxo ☃ = b(☃, b(☃));
    if (☃ == null)
    {
      bnm ☃ = (bnm)this.c.get(☃);
      if (☃ != null) {
        ☃ = this.d.a(☃.a(☃));
      }
    }
    if (☃ == null) {
      ☃ = this.d.a();
    }
    return ☃;
  }
  
  protected int b(adq ☃)
  {
    return ☃.j() > 0 ? 0 : ☃.i();
  }
  
  protected bxo b(ado ☃, int ☃)
  {
    return (bxo)this.b.get(Integer.valueOf(c(☃, ☃)));
  }
  
  private int c(ado ☃, int ☃)
  {
    return ado.a(☃) << 16 | ☃;
  }
  
  public void a(ado ☃, int ☃, bxt ☃)
  {
    this.a.put(Integer.valueOf(c(☃, ☃)), ☃);
    this.b.put(Integer.valueOf(c(☃, ☃)), this.d.a(☃));
  }
  
  public void a(ado ☃, bnm ☃)
  {
    this.c.put(☃, ☃);
  }
  
  public bxs a()
  {
    return this.d;
  }
  
  public void b()
  {
    this.b.clear();
    for (Map.Entry<Integer, bxt> ☃ : this.a.entrySet()) {
      this.b.put(☃.getKey(), this.d.a((bxt)☃.getValue()));
    }
  }
}
