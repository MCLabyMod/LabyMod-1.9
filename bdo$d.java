public class bdo$d
  implements bdl.a
{
  private final bcf a;
  private final bcv b;
  private final bcv c;
  private bcv d;
  
  public bdo$d(bcv ☃, bcv ☃)
  {
    this.a = bcf.z();
    this.b = ☃;
    this.c = ☃;
  }
  
  public bcv a()
  {
    return this.b;
  }
  
  public bcv b()
  {
    return this.c;
  }
  
  public void a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, boolean ☃)
  {
    a(this.b, ☃, ☃, ☃, false);
    a(this.c, ☃, ☃, ☃, false);
  }
  
  private void a(bcv ☃, int ☃, int ☃, int ☃, boolean ☃)
  {
    if (☃ == null) {
      return;
    }
    if ((☃ instanceof bcz)) {
      a((bcz)☃, ☃, ☃, ☃, ☃);
    } else if ((☃ instanceof bdd)) {
      a((bdd)☃, ☃, ☃);
    } else if ((☃ instanceof bdf)) {
      a((bdf)☃, ☃, ☃, ☃, ☃);
    }
  }
  
  private void a(bcz ☃, int ☃, int ☃, int ☃, boolean ☃)
  {
    ☃.i = ☃;
    if (!☃) {
      ☃.a(this.a, ☃, ☃);
    }
  }
  
  private void a(bdd ☃, int ☃, boolean ☃)
  {
    ☃.f = ☃;
    if (!☃) {
      ☃.g();
    }
  }
  
  private void a(bdf ☃, int ☃, int ☃, int ☃, boolean ☃)
  {
    ☃.h = ☃;
    if (!☃) {
      ☃.a(this.a, ☃, ☃);
    }
  }
  
  public void a(int ☃, int ☃, int ☃)
  {
    a(this.b, ☃, 0, 0, true);
    a(this.c, ☃, 0, 0, true);
  }
  
  public boolean a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    boolean ☃ = a(this.b, ☃, ☃, ☃);
    boolean ☃ = a(this.c, ☃, ☃, ☃);
    return (☃) || (☃);
  }
  
  private boolean a(bcv ☃, int ☃, int ☃, int ☃)
  {
    if (☃ == null) {
      return false;
    }
    if ((☃ instanceof bcz)) {
      return a((bcz)☃, ☃, ☃, ☃);
    }
    if ((☃ instanceof bdd)) {
      a((bdd)☃, ☃, ☃, ☃);
    }
    return false;
  }
  
  private boolean a(bcz ☃, int ☃, int ☃, int ☃)
  {
    boolean ☃ = ☃.c(this.a, ☃, ☃);
    if (☃) {
      this.d = ☃;
    }
    return ☃;
  }
  
  private void a(bdd ☃, int ☃, int ☃, int ☃)
  {
    ☃.a(☃, ☃, ☃);
    if (☃.m()) {
      this.d = ☃;
    }
  }
  
  public void b(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    b(this.b, ☃, ☃, ☃);
    b(this.c, ☃, ☃, ☃);
  }
  
  private void b(bcv ☃, int ☃, int ☃, int ☃)
  {
    if (☃ == null) {
      return;
    }
    if ((☃ instanceof bcz)) {
      b((bcz)☃, ☃, ☃, ☃);
    }
  }
  
  private void b(bcz ☃, int ☃, int ☃, int ☃)
  {
    ☃.a(☃, ☃);
  }
}
