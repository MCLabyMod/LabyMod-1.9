import com.google.common.collect.AbstractIterator;

class cj$1$1
  extends AbstractIterator<cj>
{
  private cj b = null;
  
  cj$1$1(cj.1 param1) {}
  
  protected cj a()
  {
    if (this.b == null)
    {
      this.b = this.a.a;
      return this.b;
    }
    if (this.b.equals(this.a.b)) {
      return (cj)endOfData();
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
    this.b = new cj(☃, ☃, ☃);
    return this.b;
  }
}
