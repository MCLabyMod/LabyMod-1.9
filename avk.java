import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class avk
{
  private final List<avl> a = Lists.newArrayList();
  private final Map<String, Map<String, String>> b = Maps.newHashMap();
  private int c;
  
  public int a()
  {
    return this.c;
  }
  
  public void a(int ☃)
  {
    this.c = ☃;
  }
  
  public Map<String, Map<String, String>> b()
  {
    return this.b;
  }
  
  public List<avl> c()
  {
    return this.a;
  }
  
  public void d()
  {
    int ☃ = 0;
    for (avl ☃ : this.a)
    {
      ☃.b(☃);
      ☃ += ☃.b();
    }
  }
  
  public String toString()
  {
    StringBuilder ☃ = new StringBuilder();
    
    ☃.append(3);
    ☃.append(";");
    for (int ☃ = 0; ☃ < this.a.size(); ☃++)
    {
      if (☃ > 0) {
        ☃.append(",");
      }
      ☃.append(((avl)this.a.get(☃)).toString());
    }
    ☃.append(";");
    ☃.append(this.c);
    int ☃;
    if (!this.b.isEmpty())
    {
      ☃.append(";");
      ☃ = 0;
      for (Map.Entry<String, Map<String, String>> ☃ : this.b.entrySet())
      {
        if (☃++ > 0) {
          ☃.append(",");
        }
        ☃.append(((String)☃.getKey()).toLowerCase());
        
        Map<String, String> ☃ = (Map)☃.getValue();
        if (!☃.isEmpty())
        {
          ☃.append("(");
          int ☃ = 0;
          for (Map.Entry<String, String> ☃ : ☃.entrySet())
          {
            if (☃++ > 0) {
              ☃.append(" ");
            }
            ☃.append((String)☃.getKey());
            ☃.append("=");
            ☃.append((String)☃.getValue());
          }
          ☃.append(")");
        }
      }
    }
    else
    {
      ☃.append(";");
    }
    return ☃.toString();
  }
  
  private static avl a(int ☃, String ☃, int ☃)
  {
    String[] ☃ = ☃ >= 3 ? ☃.split("\\*", 2) : ☃.split("x", 2);
    int ☃ = 1;
    int ☃ = 0;
    if (☃.length == 2) {
      try
      {
        ☃ = Integer.parseInt(☃[0]);
        if (☃ + ☃ >= 256) {
          ☃ = 256 - ☃;
        }
        if (☃ < 0) {
          ☃ = 0;
        }
      }
      catch (Throwable ☃)
      {
        return null;
      }
    }
    ajt ☃ = null;
    try
    {
      String ☃ = ☃[(☃.length - 1)];
      if (☃ < 3)
      {
        ☃ = ☃.split(":", 2);
        if (☃.length > 1) {
          ☃ = Integer.parseInt(☃[1]);
        }
        ☃ = ajt.b(Integer.parseInt(☃[0]));
      }
      else
      {
        ☃ = ☃.split(":", 3);
        ☃ = ☃.length > 1 ? ajt.b(☃[0] + ":" + ☃[1]) : null;
        if (☃ != null)
        {
          ☃ = ☃.length > 2 ? Integer.parseInt(☃[2]) : 0;
        }
        else
        {
          ☃ = ajt.b(☃[0]);
          if (☃ != null) {
            ☃ = ☃.length > 1 ? Integer.parseInt(☃[1]) : 0;
          }
        }
        if (☃ == null) {
          return null;
        }
      }
      if (☃ == aju.a) {
        ☃ = 0;
      }
      if ((☃ < 0) || (☃ > 15)) {
        ☃ = 0;
      }
    }
    catch (Throwable ☃)
    {
      return null;
    }
    avl ☃ = new avl(☃, ☃, ☃, ☃);
    ☃.b(☃);
    return ☃;
  }
  
  private static List<avl> a(int ☃, String ☃)
  {
    if ((☃ == null) || (☃.length() < 1)) {
      return null;
    }
    List<avl> ☃ = Lists.newArrayList();
    String[] ☃ = ☃.split(",");
    int ☃ = 0;
    for (String ☃ : ☃)
    {
      avl ☃ = a(☃, ☃, ☃);
      if (☃ == null) {
        return null;
      }
      ☃.add(☃);
      ☃ += ☃.b();
    }
    return ☃;
  }
  
  public static avk a(String ☃)
  {
    if (☃ == null) {
      return e();
    }
    String[] ☃ = ☃.split(";", -1);
    int ☃ = ☃.length == 1 ? 0 : on.a(☃[0], 0);
    if ((☃ < 0) || (☃ > 3)) {
      return e();
    }
    avk ☃ = new avk();
    int ☃ = ☃.length == 1 ? 0 : 1;
    List<avl> ☃ = a(☃, ☃[(☃++)]);
    if ((☃ == null) || (☃.isEmpty())) {
      return e();
    }
    ☃.c().addAll(☃);
    ☃.d();
    
    int ☃ = aig.a(ail.c);
    if ((☃ > 0) && (☃.length > ☃)) {
      ☃ = on.a(☃[(☃++)], ☃);
    }
    ☃.a(☃);
    if ((☃ > 0) && (☃.length > ☃))
    {
      String[] ☃ = ☃[(☃++)].toLowerCase().split(",");
      for (String ☃ : ☃)
      {
        String[] ☃ = ☃.split("\\(", 2);
        Map<String, String> ☃ = Maps.newHashMap();
        if (!☃[0].isEmpty())
        {
          ☃.b().put(☃[0], ☃);
          if ((☃.length > 1) && (☃[1].endsWith(")")) && (☃[1].length() > 1))
          {
            String[] ☃ = ☃[1].substring(0, ☃[1].length() - 1).split(" ");
            for (int ☃ = 0; ☃ < ☃.length; ☃++)
            {
              String[] ☃ = ☃[☃].split("=", 2);
              if (☃.length == 2) {
                ☃.put(☃[0], ☃[1]);
              }
            }
          }
        }
      }
    }
    else
    {
      ☃.b().put("village", Maps.newHashMap());
    }
    return ☃;
  }
  
  public static avk e()
  {
    avk ☃ = new avk();
    
    ☃.a(aig.a(ail.c));
    ☃.c().add(new avl(1, aju.h));
    ☃.c().add(new avl(2, aju.d));
    ☃.c().add(new avl(1, aju.c));
    ☃.d();
    ☃.b().put("village", Maps.newHashMap());
    
    return ☃;
  }
}
