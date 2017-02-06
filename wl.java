public enum wl
{
  private final String e;
  private final String f;
  private final int g;
  
  private wl(int ☃)
  {
    this.g = ☃;
    this.e = null;
    this.f = "";
  }
  
  private wl(int ☃, String ☃, String ☃)
  {
    this.g = ☃;
    this.e = ("textures/entity/horse/armor/horse_armor_" + ☃ + ".png");
    this.f = ☃;
  }
  
  public int a()
  {
    return ordinal();
  }
  
  public String b()
  {
    return this.f;
  }
  
  public int c()
  {
    return this.g;
  }
  
  public String d()
  {
    return this.e;
  }
  
  public static wl a(int ☃)
  {
    return values()[☃];
  }
  
  public static wl a(adq ☃)
  {
    return ☃ == null ? a : a(☃.b());
  }
  
  public static wl a(ado ☃)
  {
    if (☃ == ads.cu) {
      return b;
    }
    if (☃ == ads.cv) {
      return c;
    }
    if (☃ == ads.cw) {
      return d;
    }
    return a;
  }
  
  public static boolean b(ado ☃)
  {
    return a(☃) != a;
  }
}
