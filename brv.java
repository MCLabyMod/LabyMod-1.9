import com.google.common.collect.Maps;
import java.util.Map;

public class brv
  extends bsg<wk>
{
  private static final Map<String, kk> a = ;
  
  public brv(brm ☃, biv ☃, float ☃)
  {
    super(☃, ☃, ☃);
  }
  
  protected void a(wk ☃, float ☃)
  {
    float ☃ = 1.0F;
    
    wm ☃ = ☃.cZ();
    if (☃ == wm.b) {
      ☃ *= 0.87F;
    } else if (☃ == wm.c) {
      ☃ *= 0.92F;
    }
    bni.b(☃, ☃, ☃);
    super.a(☃, ☃);
  }
  
  protected kk a(wk ☃)
  {
    if (!☃.dx()) {
      return ☃.cZ().e();
    }
    return b(☃);
  }
  
  private kk b(wk ☃)
  {
    String ☃ = ☃.dz();
    if (!☃.dy()) {
      return null;
    }
    kk ☃ = (kk)a.get(☃);
    if (☃ == null)
    {
      ☃ = new kk(☃);
      bcf.z().N().a(☃, new bvb(☃.dA()));
      
      a.put(☃, ☃);
    }
    return ☃;
  }
}
