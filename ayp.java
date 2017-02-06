public class ayp
{
  private final ayn[] a;
  private ayn[] b = new ayn[0];
  private ayn[] c = new ayn[0];
  private ayn d;
  private int e;
  private int f;
  
  public ayp(ayn[] ☃)
  {
    this.a = ☃;
    this.f = ☃.length;
  }
  
  public void a()
  {
    this.e += 1;
  }
  
  public boolean b()
  {
    return this.e >= this.f;
  }
  
  public ayn c()
  {
    if (this.f > 0) {
      return this.a[(this.f - 1)];
    }
    return null;
  }
  
  public ayn a(int ☃)
  {
    return this.a[☃];
  }
  
  public void a(int ☃, ayn ☃)
  {
    this.a[☃] = ☃;
  }
  
  public int d()
  {
    return this.f;
  }
  
  public void b(int ☃)
  {
    this.f = ☃;
  }
  
  public int e()
  {
    return this.e;
  }
  
  public void c(int ☃)
  {
    this.e = ☃;
  }
  
  public bbj a(rr ☃, int ☃)
  {
    double ☃ = this.a[☃].a + (int)(☃.G + 1.0F) * 0.5D;
    double ☃ = this.a[☃].b;
    double ☃ = this.a[☃].c + (int)(☃.G + 1.0F) * 0.5D;
    return new bbj(☃, ☃, ☃);
  }
  
  public bbj a(rr ☃)
  {
    return a(☃, this.e);
  }
  
  public bbj f()
  {
    ayn ☃ = this.a[this.e];
    return new bbj(☃.a, ☃.b, ☃.c);
  }
  
  public boolean a(ayp ☃)
  {
    if (☃ == null) {
      return false;
    }
    if (☃.a.length != this.a.length) {
      return false;
    }
    for (int ☃ = 0; ☃ < this.a.length; ☃++) {
      if ((this.a[☃].a != ☃.a[☃].a) || (this.a[☃].b != ☃.a[☃].b) || (this.a[☃].c != ☃.a[☃].c)) {
        return false;
      }
    }
    return true;
  }
  
  public boolean b(bbj ☃)
  {
    ayn ☃ = c();
    if (☃ == null) {
      return false;
    }
    return (☃.a == (int)☃.b) && (☃.c == (int)☃.d);
  }
  
  public static ayp b(em ☃)
  {
    int ☃ = ☃.readInt();
    ayn ☃ = ayn.b(☃);
    
    ayn[] ☃ = new ayn[☃.readInt()];
    for (int ☃ = 0; ☃ < ☃.length; ☃++) {
      ☃[☃] = ayn.b(☃);
    }
    ayn[] ☃ = new ayn[☃.readInt()];
    for (int ☃ = 0; ☃ < ☃.length; ☃++) {
      ☃[☃] = ayn.b(☃);
    }
    ayn[] ☃ = new ayn[☃.readInt()];
    for (int ☃ = 0; ☃ < ☃.length; ☃++) {
      ☃[☃] = ayn.b(☃);
    }
    ayp ☃ = new ayp(☃);
    ☃.b = ☃;
    ☃.c = ☃;
    ☃.d = ☃;
    ☃.e = ☃;
    
    return ☃;
  }
}
