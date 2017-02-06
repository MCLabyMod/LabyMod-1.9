import java.util.Random;

public class agj
  extends agm
{
  private static final String[] e = { "all", "undead", "arthropods" };
  private static final int[] f = { 1, 5, 5 };
  private static final int[] g = { 11, 8, 8 };
  private static final int[] h = { 20, 20, 20 };
  public final int a;
  
  public agj(agm.a ☃, int ☃, rw... ☃)
  {
    super(☃, agn.g, ☃);
    this.a = ☃;
  }
  
  public int a(int ☃)
  {
    return f[this.a] + (☃ - 1) * g[this.a];
  }
  
  public int b(int ☃)
  {
    return a(☃) + h[this.a];
  }
  
  public int b()
  {
    return 5;
  }
  
  public float a(int ☃, sf ☃)
  {
    if (this.a == 0) {
      return 1.0F + Math.max(0, ☃ - 1) * 0.5F;
    }
    if ((this.a == 1) && (☃ == sf.b)) {
      return ☃ * 2.5F;
    }
    if ((this.a == 2) && (☃ == sf.c)) {
      return ☃ * 2.5F;
    }
    return 0.0F;
  }
  
  public String a()
  {
    return "enchantment.damage." + e[this.a];
  }
  
  public boolean a(agm ☃)
  {
    return !(☃ instanceof agj);
  }
  
  public boolean a(adq ☃)
  {
    if ((☃.b() instanceof abz)) {
      return true;
    }
    return super.a(☃);
  }
  
  public void a(sa ☃, rr ☃, int ☃)
  {
    if ((☃ instanceof sa))
    {
      sa ☃ = (sa)☃;
      if ((this.a == 2) && (☃.ca() == sf.c))
      {
        int ☃ = 20 + ☃.bF().nextInt(10 * ☃);
        ☃.c(new rl(rm.b, ☃, 3));
      }
    }
  }
}
