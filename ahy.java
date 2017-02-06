public class ahy
{
  public static final ahy[] a = new ahy[16];
  public static final ahy b = new ahy(0, "default", 1).i();
  public static final ahy c = new ahy(1, "flat");
  public static final ahy d = new ahy(2, "largeBiomes");
  public static final ahy e = new ahy(3, "amplified").j();
  public static final ahy f = new ahy(4, "customized");
  public static final ahy g = new ahy(5, "debug_all_block_states");
  public static final ahy h = new ahy(8, "default_1_1", 0).a(false);
  private final int i;
  private final String j;
  private final int k;
  private boolean l;
  private boolean m;
  private boolean n;
  
  private ahy(int ☃, String ☃)
  {
    this(☃, ☃, 0);
  }
  
  private ahy(int ☃, String ☃, int ☃)
  {
    this.j = ☃;
    this.k = ☃;
    this.l = true;
    this.i = ☃;
    a[☃] = this;
  }
  
  public String a()
  {
    return this.j;
  }
  
  public String b()
  {
    return "generator." + this.j;
  }
  
  public String c()
  {
    return b() + ".info";
  }
  
  public int d()
  {
    return this.k;
  }
  
  public ahy a(int ☃)
  {
    if ((this == b) && (☃ == 0)) {
      return h;
    }
    return this;
  }
  
  private ahy a(boolean ☃)
  {
    this.l = ☃;
    return this;
  }
  
  public boolean e()
  {
    return this.l;
  }
  
  private ahy i()
  {
    this.m = true;
    return this;
  }
  
  public boolean f()
  {
    return this.m;
  }
  
  public static ahy a(String ☃)
  {
    for (int ☃ = 0; ☃ < a.length; ☃++) {
      if ((a[☃] != null) && (a[☃].j.equalsIgnoreCase(☃))) {
        return a[☃];
      }
    }
    return null;
  }
  
  public int g()
  {
    return this.i;
  }
  
  public boolean h()
  {
    return this.n;
  }
  
  private ahy j()
  {
    this.n = true;
    return this;
  }
}
