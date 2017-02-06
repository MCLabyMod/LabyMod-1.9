public class asd
  implements asi
{
  private final oa<arc> a;
  private final asj b;
  private final int c;
  
  public asd(int ☃, asj ☃)
  {
    this.c = ☃;
    this.b = ☃;
    this.a = new oa(1 << ☃);
  }
  
  public int a(arc ☃)
  {
    int ☃ = this.a.a(☃);
    if (☃ == -1)
    {
      ☃ = this.a.c(☃);
      if (☃ >= 1 << this.c) {
        ☃ = this.b.a(this.c + 1, ☃);
      }
    }
    return ☃;
  }
  
  public arc a(int ☃)
  {
    return (arc)this.a.a(☃);
  }
  
  public void a(em ☃)
  {
    this.a.a();
    int ☃ = ☃.g();
    for (int ☃ = 0; ☃ < ☃; ☃++) {
      this.a.c(ajt.i.a(☃.g()));
    }
  }
  
  public void b(em ☃)
  {
    int ☃ = this.a.b();
    ☃.b(☃);
    for (int ☃ = 0; ☃ < ☃; ☃++) {
      ☃.b(ajt.i.a(this.a.a(☃)));
    }
  }
  
  public int a()
  {
    int ☃ = em.a(this.a.b());
    for (int ☃ = 0; ☃ < this.a.b(); ☃++) {
      ☃ += em.a(ajt.i.a(this.a.a(☃)));
    }
    return ☃;
  }
}
