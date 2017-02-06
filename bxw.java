import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;

public class bxw
  implements bxo
{
  private final int a;
  private final List<bxw.b> b;
  private final bxo c;
  
  public bxw(List<bxw.b> ☃)
  {
    this.b = ☃;
    this.a = ov.a(☃);
    this.c = ((bxw.b)☃.get(0)).b;
  }
  
  private bxo a(long ☃)
  {
    return ((bxw.b)ov.a(this.b, Math.abs((int)☃ >> 16) % this.a)).b;
  }
  
  public List<bof> a(arc ☃, cq ☃, long ☃)
  {
    return a(☃).a(☃, ☃, ☃);
  }
  
  public boolean a()
  {
    return this.c.a();
  }
  
  public boolean b()
  {
    return this.c.b();
  }
  
  public boolean c()
  {
    return this.c.c();
  }
  
  public bvh d()
  {
    return this.c.d();
  }
  
  public bos e()
  {
    return this.c.e();
  }
  
  public boq f()
  {
    return this.c.f();
  }
  
  public static class a
  {
    private List<bxw.b> a = Lists.newArrayList();
    
    public a a(bxo ☃, int ☃)
    {
      this.a.add(new bxw.b(☃, ☃));
      
      return this;
    }
    
    public bxw a()
    {
      Collections.sort(this.a);
      return new bxw(this.a);
    }
    
    public bxo b()
    {
      return ((bxw.b)this.a.get(0)).b;
    }
  }
  
  static class b
    extends ov.a
    implements Comparable<b>
  {
    protected final bxo b;
    
    public b(bxo ☃, int ☃)
    {
      super();
      this.b = ☃;
    }
    
    public int a(b ☃)
    {
      return ComparisonChain.start().compare(☃.a, this.a).result();
    }
    
    public String toString()
    {
      return "MyWeighedRandomItem{weight=" + this.a + ", model=" + this.b + '}';
    }
  }
}
