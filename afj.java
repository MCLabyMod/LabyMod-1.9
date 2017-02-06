import com.google.common.collect.Lists;
import java.util.List;

public class afj
  implements afu
{
  public boolean a(abc ☃, aht ☃)
  {
    adq ☃ = null;
    List<adq> ☃ = Lists.newArrayList();
    for (int ☃ = 0; ☃ < ☃.u_(); ☃++)
    {
      adq ☃ = ☃.a(☃);
      if (☃ != null) {
        if ((☃.b() instanceof abw))
        {
          abw ☃ = (abw)☃.b();
          if ((☃.d() == abw.a.a) && (☃ == null)) {
            ☃ = ☃;
          } else {
            return false;
          }
        }
        else if (☃.b() == ads.bd)
        {
          ☃.add(☃);
        }
        else
        {
          return false;
        }
      }
    }
    return (☃ != null) && (!☃.isEmpty());
  }
  
  public adq a(abc ☃)
  {
    adq ☃ = null;
    int[] ☃ = new int[3];
    int ☃ = 0;
    int ☃ = 0;
    abw ☃ = null;
    for (int ☃ = 0; ☃ < ☃.u_(); ☃++)
    {
      adq ☃ = ☃.a(☃);
      if (☃ != null) {
        if ((☃.b() instanceof abw))
        {
          ☃ = (abw)☃.b();
          if ((☃.d() == abw.a.a) && (☃ == null))
          {
            ☃ = ☃.k();
            ☃.b = 1;
            if (☃.e_(☃))
            {
              int ☃ = ☃.b(☃);
              float ☃ = (☃ >> 16 & 0xFF) / 255.0F;
              float ☃ = (☃ >> 8 & 0xFF) / 255.0F;
              float ☃ = (☃ & 0xFF) / 255.0F;
              
              ☃ = (int)(☃ + Math.max(☃, Math.max(☃, ☃)) * 255.0F); int 
              
                tmp174_173 = 0; int[] tmp174_172 = ☃;tmp174_172[tmp174_173] = ((int)(tmp174_172[tmp174_173] + ☃ * 255.0F)); int 
                tmp187_186 = 1; int[] tmp187_185 = ☃;tmp187_185[tmp187_186] = ((int)(tmp187_185[tmp187_186] + ☃ * 255.0F)); int 
                tmp200_199 = 2; int[] tmp200_198 = ☃;tmp200_198[tmp200_199] = ((int)(tmp200_198[tmp200_199] + ☃ * 255.0F));
              ☃++;
            }
          }
          else
          {
            return null;
          }
        }
        else if (☃.b() == ads.bd)
        {
          float[] ☃ = we.a(act.a(☃.i()));
          int ☃ = (int)(☃[0] * 255.0F);
          int ☃ = (int)(☃[1] * 255.0F);
          int ☃ = (int)(☃[2] * 255.0F);
          
          ☃ += Math.max(☃, Math.max(☃, ☃));
          
          ☃[0] += ☃;
          ☃[1] += ☃;
          ☃[2] += ☃;
          ☃++;
        }
        else
        {
          return null;
        }
      }
    }
    if (☃ == null) {
      return null;
    }
    int ☃ = ☃[0] / ☃;
    int ☃ = ☃[1] / ☃;
    int ☃ = ☃[2] / ☃;
    
    float ☃ = ☃ / ☃;
    float ☃ = Math.max(☃, Math.max(☃, ☃));
    
    ☃ = (int)(☃ * ☃ / ☃);
    ☃ = (int)(☃ * ☃ / ☃);
    ☃ = (int)(☃ * ☃ / ☃);
    
    int ☃ = ☃;
    ☃ = (☃ << 8) + ☃;
    ☃ = (☃ << 8) + ☃;
    
    ☃.a(☃, ☃);
    return ☃;
  }
  
  public int a()
  {
    return 10;
  }
  
  public adq b()
  {
    return null;
  }
  
  public adq[] b(abc ☃)
  {
    adq[] ☃ = new adq[☃.u_()];
    for (int ☃ = 0; ☃ < ☃.length; ☃++)
    {
      adq ☃ = ☃.a(☃);
      if ((☃ != null) && (☃.b().r())) {
        ☃[☃] = new adq(☃.b().q());
      }
    }
    return ☃;
  }
}
