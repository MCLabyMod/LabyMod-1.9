import java.util.BitSet;
import java.util.Iterator;
import java.util.Set;

public class bqj
{
  private static final int a = cq.values().length;
  private final BitSet b = new BitSet(a * a);
  
  public void a(Set<cq> ☃)
  {
    for (Iterator ☃ = ☃.iterator(); ☃.hasNext();)
    {
      ☃ = (cq)☃.next();
      for (cq ☃ : ☃) {
        a(☃, ☃, true);
      }
    }
    cq ☃;
  }
  
  public void a(cq ☃, cq ☃, boolean ☃)
  {
    this.b.set(☃.ordinal() + ☃.ordinal() * a, ☃);
    this.b.set(☃.ordinal() + ☃.ordinal() * a, ☃);
  }
  
  public void a(boolean ☃)
  {
    this.b.set(0, this.b.size(), ☃);
  }
  
  public boolean a(cq ☃, cq ☃)
  {
    return this.b.get(☃.ordinal() + ☃.ordinal() * a);
  }
  
  public String toString()
  {
    StringBuilder ☃ = new StringBuilder();
    ☃.append(' ');
    for (cq ☃ : cq.values()) {
      ☃.append(' ').append(☃.toString().toUpperCase().charAt(0));
    }
    ☃.append('\n');
    for (cq ☃ : cq.values())
    {
      ☃.append(☃.toString().toUpperCase().charAt(0));
      for (cq ☃ : cq.values()) {
        if (☃ == ☃)
        {
          ☃.append("  ");
        }
        else
        {
          boolean ☃ = a(☃, ☃);
          ☃.append(' ').append(☃ ? 'Y' : 'n');
        }
      }
      ☃.append('\n');
    }
    return ☃.toString();
  }
}
