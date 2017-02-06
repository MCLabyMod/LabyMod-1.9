import java.util.Comparator;

public class bbn
{
  public static final Comparator<bbn> a = new Comparator()
  {
    public int a(bbn ☃, bbn ☃)
    {
      if (☃.c() > ☃.c()) {
        return 1;
      }
      if (☃.c() < ☃.c()) {
        return -1;
      }
      return ☃.e().compareToIgnoreCase(☃.e());
    }
  };
  private final bbp b;
  private final bbl c;
  private final String d;
  private int e;
  private boolean f;
  private boolean g;
  
  public bbn(bbp ☃, bbl ☃, String ☃)
  {
    this.b = ☃;
    this.c = ☃;
    this.d = ☃;
    this.g = true;
  }
  
  public void a(int ☃)
  {
    if (this.c.c().b()) {
      throw new IllegalStateException("Cannot modify read-only score");
    }
    c(c() + ☃);
  }
  
  public void b(int ☃)
  {
    if (this.c.c().b()) {
      throw new IllegalStateException("Cannot modify read-only score");
    }
    c(c() - ☃);
  }
  
  public void a()
  {
    if (this.c.c().b()) {
      throw new IllegalStateException("Cannot modify read-only score");
    }
    a(1);
  }
  
  public int c()
  {
    return this.e;
  }
  
  public void c(int ☃)
  {
    int ☃ = this.e;
    this.e = ☃;
    if ((☃ != ☃) || (this.g))
    {
      this.g = false;
      f().a(this);
    }
  }
  
  public bbl d()
  {
    return this.c;
  }
  
  public String e()
  {
    return this.d;
  }
  
  public bbp f()
  {
    return this.b;
  }
  
  public boolean g()
  {
    return this.f;
  }
  
  public void a(boolean ☃)
  {
    this.f = ☃;
  }
}
