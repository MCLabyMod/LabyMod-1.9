import com.google.common.collect.Lists;
import java.util.List;

public class bdb
{
  public static String a(String ☃, boolean ☃)
  {
    if ((☃) || (bcf.z().u.n)) {
      return ☃;
    }
    return a.a(☃);
  }
  
  public static List<eu> a(eu ☃, int ☃, bct ☃, boolean ☃, boolean ☃)
  {
    int ☃ = 0;
    eu ☃ = new fa("");
    List<eu> ☃ = Lists.newArrayList();
    List<eu> ☃ = Lists.newArrayList(☃);
    for (int ☃ = 0; ☃ < ☃.size(); ☃++)
    {
      eu ☃ = (eu)☃.get(☃);
      String ☃ = ☃.e();
      boolean ☃ = false;
      if (☃.contains("\n"))
      {
        int ☃ = ☃.indexOf('\n');
        String ☃ = ☃.substring(☃ + 1);
        ☃ = ☃.substring(0, ☃ + 1);
        fa ☃ = new fa(☃);
        ☃.a(☃.b().m());
        ☃.add(☃ + 1, ☃);
        ☃ = true;
      }
      String ☃ = a(☃.b().k() + ☃, ☃);
      String ☃ = ☃.endsWith("\n") ? ☃.substring(0, ☃.length() - 1) : ☃;
      int ☃ = ☃.a(☃);
      fa ☃ = new fa(☃);
      ☃.a(☃.b().m());
      if (☃ + ☃ > ☃)
      {
        String ☃ = ☃.a(☃, ☃ - ☃, false);
        String ☃ = ☃.length() < ☃.length() ? ☃.substring(☃.length()) : null;
        if ((☃ != null) && (!☃.isEmpty()))
        {
          int ☃ = ☃.lastIndexOf(" ");
          if ((☃ >= 0) && (☃.a(☃.substring(0, ☃)) > 0))
          {
            ☃ = ☃.substring(0, ☃);
            if (☃) {
              ☃++;
            }
            ☃ = ☃.substring(☃);
          }
          else if ((☃ > 0) && (!☃.contains(" ")))
          {
            ☃ = "";
            ☃ = ☃;
          }
          fa ☃ = new fa(☃);
          ☃.a(☃.b().m());
          ☃.add(☃ + 1, ☃);
        }
        ☃ = ☃;
        ☃ = ☃.a(☃);
        ☃ = new fa(☃);
        ☃.a(☃.b().m());
        ☃ = true;
      }
      if (☃ + ☃ <= ☃)
      {
        ☃ += ☃;
        
        ☃.a(☃);
      }
      else
      {
        ☃ = true;
      }
      if (☃)
      {
        ☃.add(☃);
        ☃ = 0;
        ☃ = new fa("");
      }
    }
    ☃.add(☃);
    return ☃;
  }
}
