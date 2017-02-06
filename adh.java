import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;

public class adh
  extends adk
{
  private final boolean b;
  
  public adh(boolean ☃)
  {
    super(0, 0.0F, false);
    
    this.b = ☃;
  }
  
  public int h(adq ☃)
  {
    adh.a ☃ = adh.a.a(☃);
    if ((this.b) && (☃.g())) {
      return ☃.e();
    }
    return ☃.c();
  }
  
  public float i(adq ☃)
  {
    adh.a ☃ = adh.a.a(☃);
    if ((this.b) && (☃.g())) {
      return ☃.f();
    }
    return ☃.d();
  }
  
  protected void a(adq ☃, aht ☃, zj ☃)
  {
    adh.a ☃ = adh.a.a(☃);
    if (☃ == adh.a.d)
    {
      ☃.c(new rl(rm.s, 1200, 3));
      ☃.c(new rl(rm.q, 300, 2));
      ☃.c(new rl(rm.i, 300, 1));
    }
    super.a(☃, ☃, ☃);
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    for (adh.a ☃ : ) {
      if ((!this.b) || (☃.g())) {
        ☃.add(new adq(this, 1, ☃.a()));
      }
    }
  }
  
  public String f_(adq ☃)
  {
    adh.a ☃ = adh.a.a(☃);
    return a() + "." + ☃.b() + "." + ((this.b) && (☃.g()) ? "cooked" : "raw");
  }
  
  public static enum a
  {
    private static final Map<Integer, a> e;
    private final int f;
    private final String g;
    private final int h;
    private final float i;
    private final int j;
    private final float k;
    private boolean l = false;
    
    static
    {
      e = Maps.newHashMap();
      for (a ☃ : values()) {
        e.put(Integer.valueOf(☃.a()), ☃);
      }
    }
    
    private a(int ☃, String ☃, int ☃, float ☃, int ☃, float ☃)
    {
      this.f = ☃;
      this.g = ☃;
      this.h = ☃;
      this.i = ☃;
      this.j = ☃;
      this.k = ☃;
      this.l = true;
    }
    
    private a(int ☃, String ☃, int ☃, float ☃)
    {
      this.f = ☃;
      this.g = ☃;
      this.h = ☃;
      this.i = ☃;
      this.j = 0;
      this.k = 0.0F;
      this.l = false;
    }
    
    public int a()
    {
      return this.f;
    }
    
    public String b()
    {
      return this.g;
    }
    
    public int c()
    {
      return this.h;
    }
    
    public float d()
    {
      return this.i;
    }
    
    public int e()
    {
      return this.j;
    }
    
    public float f()
    {
      return this.k;
    }
    
    public boolean g()
    {
      return this.l;
    }
    
    public static a a(int ☃)
    {
      a ☃ = (a)e.get(Integer.valueOf(☃));
      if (☃ == null) {
        return a;
      }
      return ☃;
    }
    
    public static a a(adq ☃)
    {
      if ((☃.b() instanceof adh)) {
        return a(☃.i());
      }
      return a;
    }
  }
}
