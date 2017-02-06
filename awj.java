import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class awj
  extends awd
{
  public static final List<aig> a = Arrays.asList(new aig[] { ail.c, ail.d, ail.K });
  private int b;
  private int d = 32;
  private int h = 8;
  
  public awj() {}
  
  public awj(Map<String, String> ☃)
  {
    this();
    for (Map.Entry<String, String> ☃ : ☃.entrySet()) {
      if (((String)☃.getKey()).equals("size")) {
        this.b = on.a((String)☃.getValue(), this.b, 0);
      } else if (((String)☃.getKey()).equals("distance")) {
        this.d = on.a((String)☃.getValue(), this.d, this.h + 1);
      }
    }
  }
  
  public String a()
  {
    return "Village";
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
    Random ☃ = this.g.a(☃, ☃, 10387312);
    ☃ *= this.d;
    ☃ *= this.d;
    ☃ += ☃.nextInt(this.d - this.h);
    ☃ += ☃.nextInt(this.d - this.h);
    ☃ = ☃;
    ☃ = ☃;
    if ((☃ == ☃) && (☃ == ☃))
    {
      boolean ☃ = this.g.A().a(☃ * 16 + 8, ☃ * 16 + 8, 0, a);
      if (☃) {
        return true;
      }
    }
    return false;
  }
  
  protected awh b(int ☃, int ☃)
  {
    return new awj.a(this.g, this.f, ☃, ☃, this.b);
  }
  
  public static class a
    extends awh
  {
    private boolean c;
    
    public a() {}
    
    public a(aht ☃, Random ☃, int ☃, int ☃, int ☃)
    {
      super(☃);
      
      List<awk.e> ☃ = awk.a(☃, ☃);
      
      awk.k ☃ = new awk.k(☃.A(), 0, ☃, (☃ << 4) + 2, (☃ << 4) + 2, ☃, ☃);
      this.a.add(☃);
      ☃.a(☃, this.a, ☃);
      
      List<awg> ☃ = ☃.g;
      List<awg> ☃ = ☃.f;
      while ((!☃.isEmpty()) || (!☃.isEmpty())) {
        if (☃.isEmpty())
        {
          int ☃ = ☃.nextInt(☃.size());
          awg ☃ = (awg)☃.remove(☃);
          ☃.a(☃, this.a, ☃);
        }
        else
        {
          int ☃ = ☃.nextInt(☃.size());
          awg ☃ = (awg)☃.remove(☃);
          ☃.a(☃, this.a, ☃);
        }
      }
      d();
      
      int ☃ = 0;
      for (awg ☃ : this.a) {
        if (!(☃ instanceof awk.o)) {
          ☃++;
        }
      }
      this.c = (☃ > 2);
    }
    
    public boolean a()
    {
      return this.c;
    }
    
    public void a(dn ☃)
    {
      super.a(☃);
      
      ☃.a("Valid", this.c);
    }
    
    public void b(dn ☃)
    {
      super.b(☃);
      this.c = ☃.p("Valid");
    }
  }
}
