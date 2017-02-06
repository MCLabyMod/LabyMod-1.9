import com.google.common.collect.AbstractIterator;

class cj$2$1
  extends AbstractIterator<cj.a>
{
  private cj.a b = null;
  
  cj$2$1(cj.2 param2) {}
  
  protected cj.a a()
  {
    if (this.b == null)
    {
      this.b = new cj.a(this.a.a.p(), this.a.a.q(), this.a.a.r());
      return this.b;
    }
    if (this.b.equals(this.a.b)) {
      return (cj.a)endOfData();
    }
    int ☃ = this.b.p();
    int ☃ = this.b.q();
    int ☃ = this.b.r();
    if (☃ < this.a.b.p())
    {
      ☃++;
    }
    else if (☃ < this.a.b.q())
    {
      ☃ = this.a.a.p();
      ☃++;
    }
    else if (☃ < this.a.b.r())
    {
      ☃ = this.a.a.p();
      ☃ = this.a.a.q();
      ☃++;
    }
    cj.a.a(this.b, ☃);
    cj.a.b(this.b, ☃);
    cj.a.c(this.b, ☃);
    return this.b;
  }
}
