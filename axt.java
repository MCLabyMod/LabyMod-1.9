import com.google.common.collect.Lists;
import java.util.List;

public class axt
{
  private static int a = 256;
  private static List<int[]> b = Lists.newArrayList();
  private static List<int[]> c = Lists.newArrayList();
  private static List<int[]> d = Lists.newArrayList();
  private static List<int[]> e = Lists.newArrayList();
  
  public static synchronized int[] a(int ☃)
  {
    if (☃ <= 256)
    {
      if (b.isEmpty())
      {
        int[] ☃ = new int['Ā'];
        c.add(☃);
        return ☃;
      }
      int[] ☃ = (int[])b.remove(b.size() - 1);
      c.add(☃);
      return ☃;
    }
    if (☃ > a)
    {
      a = ☃;
      
      d.clear();
      e.clear();
      
      int[] ☃ = new int[a];
      e.add(☃);
      return ☃;
    }
    if (d.isEmpty())
    {
      int[] ☃ = new int[a];
      e.add(☃);
      return ☃;
    }
    int[] ☃ = (int[])d.remove(d.size() - 1);
    e.add(☃);
    return ☃;
  }
  
  public static synchronized void a()
  {
    if (!d.isEmpty()) {
      d.remove(d.size() - 1);
    }
    if (!b.isEmpty()) {
      b.remove(b.size() - 1);
    }
    d.addAll(e);
    b.addAll(c);
    
    e.clear();
    c.clear();
  }
  
  public static synchronized String b()
  {
    return "cache: " + d.size() + ", tcache: " + b.size() + ", allocated: " + e.size() + ", tallocated: " + c.size();
  }
}
