public class agy
  extends agm
{
  public final agy.a a;
  
  public static enum a
  {
    private final String f;
    private final int g;
    private final int h;
    private final int i;
    
    private a(String ☃, int ☃, int ☃, int ☃)
    {
      this.f = ☃;
      this.g = ☃;
      this.h = ☃;
      this.i = ☃;
    }
    
    public String a()
    {
      return this.f;
    }
    
    public int b()
    {
      return this.g;
    }
    
    public int c()
    {
      return this.h;
    }
  }
  
  public agy(agm.a ☃, agy.a ☃, rw... ☃)
  {
    super(☃, agn.b, ☃);
    this.a = ☃;
    if (☃ == agy.a.c) {
      this.c = agn.c;
    }
  }
  
  public int a(int ☃)
  {
    return this.a.b() + (☃ - 1) * this.a.c();
  }
  
  public int b(int ☃)
  {
    return a(☃) + this.a.c();
  }
  
  public int b()
  {
    return 4;
  }
  
  public int a(int ☃, rc ☃)
  {
    if (☃.g()) {
      return 0;
    }
    if (this.a == agy.a.a) {
      return ☃;
    }
    if ((this.a == agy.a.b) && (☃.o())) {
      return ☃ * 2;
    }
    if ((this.a == agy.a.c) && (☃ == rc.i)) {
      return ☃ * 3;
    }
    if ((this.a == agy.a.d) && (☃.c())) {
      return ☃ * 2;
    }
    if ((this.a == agy.a.e) && (☃.a())) {
      return ☃ * 2;
    }
    return 0;
  }
  
  public String a()
  {
    return "enchantment.protect." + this.a.a();
  }
  
  public boolean a(agm ☃)
  {
    if ((☃ instanceof agy))
    {
      agy ☃ = (agy)☃;
      if (this.a == ☃.a) {
        return false;
      }
      if ((this.a == agy.a.c) || (☃.a == agy.a.c)) {
        return true;
      }
      return false;
    }
    return super.a(☃);
  }
  
  public static int a(sa ☃, int ☃)
  {
    int ☃ = ago.a(agq.b, ☃);
    if (☃ > 0) {
      ☃ -= on.d(☃ * (☃ * 0.15F));
    }
    return ☃;
  }
  
  public static double a(sa ☃, double ☃)
  {
    int ☃ = ago.a(agq.d, ☃);
    if (☃ > 0) {
      ☃ -= on.c(☃ * (☃ * 0.15F));
    }
    return ☃;
  }
}
