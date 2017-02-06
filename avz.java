import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class avz
  extends awd
{
  private static final List<aig> a = Arrays.asList(new aig[] { ail.d, ail.s, ail.w, ail.x, ail.h, ail.n, ail.F });
  private List<aig.c> b = Lists.newArrayList();
  private int d = 32;
  private int h = 8;
  
  public avz()
  {
    this.b.add(new aig.c(yz.class, 1, 1, 1));
  }
  
  public avz(Map<String, String> ☃)
  {
    this();
    for (Map.Entry<String, String> ☃ : ☃.entrySet()) {
      if (((String)☃.getKey()).equals("distance")) {
        this.d = on.a((String)☃.getValue(), this.d, this.h + 1);
      }
    }
  }
  
  public String a()
  {
    return "Temple";
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
    Random ☃ = this.g.a(☃, ☃, 14357617);
    ☃ *= this.d;
    ☃ *= this.d;
    ☃ += ☃.nextInt(this.d - this.h);
    ☃ += ☃.nextInt(this.d - this.h);
    ☃ = ☃;
    ☃ = ☃;
    aig ☃;
    if ((☃ == ☃) && (☃ == ☃))
    {
      ☃ = this.g.A().a(new cj(☃ * 16 + 8, 0, ☃ * 16 + 8));
      if (☃ == null) {
        return false;
      }
      for (aig ☃ : a) {
        if (☃ == ☃) {
          return true;
        }
      }
    }
    return false;
  }
  
  protected awh b(int ☃, int ☃)
  {
    return new avz.a(this.g, this.f, ☃, ☃);
  }
  
  public static class a
    extends awh
  {
    public a() {}
    
    public a(aht ☃, Random ☃, int ☃, int ☃)
    {
      this(☃, ☃, ☃, ☃, ☃.b(new cj(☃ * 16 + 8, 0, ☃ * 16 + 8)));
    }
    
    public a(aht ☃, Random ☃, int ☃, int ☃, aig ☃)
    {
      super(☃);
      if ((☃ == ail.w) || (☃ == ail.x))
      {
        awa.c ☃ = new awa.c(☃, ☃ * 16, ☃ * 16);
        this.a.add(☃);
      }
      else if (☃ == ail.h)
      {
        awa.e ☃ = new awa.e(☃, ☃ * 16, ☃ * 16);
        this.a.add(☃);
      }
      else if ((☃ == ail.d) || (☃ == ail.s))
      {
        awa.a ☃ = new awa.a(☃, ☃ * 16, ☃ * 16);
        this.a.add(☃);
      }
      else if ((☃ == ail.n) || (☃ == ail.F))
      {
        awa.b ☃ = new awa.b(☃, ☃ * 16, ☃ * 16);
        this.a.add(☃);
      }
      d();
    }
  }
  
  public boolean a(cj ☃)
  {
    awh ☃ = c(☃);
    if ((☃ == null) || (!(☃ instanceof avz.a)) || (☃.a.isEmpty())) {
      return false;
    }
    awg ☃ = (awg)☃.a.get(0);
    return ☃ instanceof awa.e;
  }
  
  public List<aig.c> b()
  {
    return this.b;
  }
}
