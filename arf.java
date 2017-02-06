import com.google.common.base.Predicate;

public class arf
{
  private final aht a;
  private final cj b;
  private final boolean c;
  private arc d;
  private apv e;
  private boolean f;
  
  public arf(aht ☃, cj ☃, boolean ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
  }
  
  public arc a()
  {
    if ((this.d == null) && ((this.c) || (this.a.e(this.b)))) {
      this.d = this.a.o(this.b);
    }
    return this.d;
  }
  
  public apv b()
  {
    if ((this.e == null) && (!this.f))
    {
      this.e = this.a.r(this.b);
      this.f = true;
    }
    return this.e;
  }
  
  public cj d()
  {
    return this.b;
  }
  
  public static Predicate<arf> a(Predicate<arc> ☃)
  {
    new Predicate()
    {
      public boolean a(arf ☃)
      {
        return (☃ != null) && (this.a.apply(☃.a()));
      }
    };
  }
  
  public static Predicate<arf> a(arc ☃)
  {
    new Predicate()
    {
      public boolean a(arf ☃)
      {
        return (☃ != null) && (☃.a().equals(this.a));
      }
    };
  }
}
