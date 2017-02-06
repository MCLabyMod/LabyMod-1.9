import java.io.PrintStream;

public class asg
  implements asi
{
  private final arc[] a;
  private final asj b;
  private final int c;
  private int d;
  
  public asg(int ☃, asj ☃)
  {
    this.a = new arc[1 << ☃];
    this.c = ☃;
    this.b = ☃;
  }
  
  public int a(arc ☃)
  {
    for (int ☃ = 0; ☃ < this.d; ☃++) {
      if (this.a[☃] == ☃) {
        return ☃;
      }
    }
    int ☃ = this.d++;
    if (☃ < this.a.length)
    {
      this.a[☃] = ☃;
      if (☃ == 16) {
        System.out.println("");
      }
      return ☃;
    }
    return this.b.a(this.c + 1, ☃);
  }
  
  public arc a(int ☃)
  {
    if ((☃ > 0) && (☃ < this.d)) {
      return this.a[☃];
    }
    return null;
  }
  
  public void a(em ☃)
  {
    this.d = ☃.g();
    for (int ☃ = 0; ☃ < this.d; ☃++) {
      this.a[☃] = ((arc)ajt.i.a(☃.g()));
    }
  }
  
  public void b(em ☃)
  {
    ☃.b(this.d);
    for (int ☃ = 0; ☃ < this.d; ☃++) {
      ☃.b(ajt.i.a(this.a[☃]));
    }
  }
  
  public int a()
  {
    int ☃ = em.a(this.d);
    for (int ☃ = 0; ☃ < this.d; ☃++) {
      ☃ += em.a(ajt.i.a(this.a[☃]));
    }
    return ☃;
  }
}
