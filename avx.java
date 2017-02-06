import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class avx
  extends awd
{
  private int d = 32;
  private int h = 5;
  public static final List<aig> a = Arrays.asList(new aig[] { ail.a, ail.z, ail.i, ail.l, ail.m });
  public static final List<aig> b = Arrays.asList(new aig[] { ail.z });
  private static final List<aig.c> i = Lists.newArrayList();
  
  static
  {
    i.add(new aig.c(yo.class, 1, 2, 4));
  }
  
  public avx(Map<String, String> ☃)
  {
    this();
    for (Map.Entry<String, String> ☃ : ☃.entrySet()) {
      if (((String)☃.getKey()).equals("spacing")) {
        this.d = on.a((String)☃.getValue(), this.d, 1);
      } else if (((String)☃.getKey()).equals("separation")) {
        this.h = on.a((String)☃.getValue(), this.h, 1);
      }
    }
  }
  
  public String a()
  {
    return "Monument";
  }
  
  protected boolean a(int ☃, int ☃)
  {
    int ☃ = ☃;
    int ☃ = ☃;
    if (☃ < 0) {
      ☃ -= this.d - 1;
    }
    if (☃ < 0) {
      ☃ -= this.d - 1;
    }
    int ☃ = ☃ / this.d;
    int ☃ = ☃ / this.d;
    
    Random ☃ = this.g.a(☃, ☃, 10387313);
    ☃ *= this.d;
    ☃ *= this.d;
    ☃ += (☃.nextInt(this.d - this.h) + ☃.nextInt(this.d - this.h)) / 2;
    ☃ += (☃.nextInt(this.d - this.h) + ☃.nextInt(this.d - this.h)) / 2;
    
    ☃ = ☃;
    ☃ = ☃;
    if ((☃ == ☃) && (☃ == ☃))
    {
      if (!this.g.A().a(☃ * 16 + 8, ☃ * 16 + 8, 16, b)) {
        return false;
      }
      boolean ☃ = this.g.A().a(☃ * 16 + 8, ☃ * 16 + 8, 29, a);
      if (☃) {
        return true;
      }
    }
    return false;
  }
  
  protected awh b(int ☃, int ☃)
  {
    return new avx.a(this.g, this.f, ☃, ☃);
  }
  
  public static class a
    extends awh
  {
    private Set<ahn> c = Sets.newHashSet();
    private boolean d;
    
    public a() {}
    
    public a(aht ☃, Random ☃, int ☃, int ☃)
    {
      super(☃);
      b(☃, ☃, ☃, ☃);
    }
    
    private void b(aht ☃, Random ☃, int ☃, int ☃)
    {
      ☃.setSeed(☃.O());
      long ☃ = ☃.nextLong();
      long ☃ = ☃.nextLong();
      long ☃ = ☃ * ☃;
      long ☃ = ☃ * ☃;
      ☃.setSeed(☃ ^ ☃ ^ ☃.O());
      
      int ☃ = ☃ * 16 + 8 - 29;
      int ☃ = ☃ * 16 + 8 - 29;
      cq ☃ = cq.c.a.a(☃);
      
      this.a.add(new avy.h(☃, ☃, ☃, ☃));
      d();
      
      this.d = true;
    }
    
    public void a(aht ☃, Random ☃, avp ☃)
    {
      if (!this.d)
      {
        this.a.clear();
        b(☃, ☃, e(), f());
      }
      super.a(☃, ☃, ☃);
    }
    
    public boolean a(ahn ☃)
    {
      if (this.c.contains(☃)) {
        return false;
      }
      return super.a(☃);
    }
    
    public void b(ahn ☃)
    {
      super.b(☃);
      
      this.c.add(☃);
    }
    
    public void a(dn ☃)
    {
      super.a(☃);
      
      du ☃ = new du();
      for (ahn ☃ : this.c)
      {
        dn ☃ = new dn();
        ☃.a("X", ☃.a);
        ☃.a("Z", ☃.b);
        ☃.a(☃);
      }
      ☃.a("Processed", ☃);
    }
    
    public void b(dn ☃)
    {
      super.b(☃);
      if (☃.b("Processed", 9))
      {
        du ☃ = ☃.c("Processed", 10);
        for (int ☃ = 0; ☃ < ☃.c(); ☃++)
        {
          dn ☃ = ☃.b(☃);
          this.c.add(new ahn(☃.h("X"), ☃.h("Z")));
        }
      }
    }
  }
  
  public List<aig.c> b()
  {
    return i;
  }
  
  public avx() {}
}
