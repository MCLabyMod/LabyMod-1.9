import com.google.common.collect.AbstractIterator;
import java.util.Iterator;

final class cj$1
  implements Iterable<cj>
{
  cj$1(cj paramcj1, cj paramcj2) {}
  
  public Iterator<cj> iterator()
  {
    new AbstractIterator()
    {
      private cj b = null;
      
      protected cj a()
      {
        if (this.b == null)
        {
          this.b = cj.1.this.a;
          return this.b;
        }
        if (this.b.equals(cj.1.this.b)) {
          return (cj)endOfData();
        }
        int ☃ = this.b.p();
        int ☃ = this.b.q();
        int ☃ = this.b.r();
        if (☃ < cj.1.this.b.p())
        {
          ☃++;
        }
        else if (☃ < cj.1.this.b.q())
        {
          ☃ = cj.1.this.a.p();
          ☃++;
        }
        else if (☃ < cj.1.this.b.r())
        {
          ☃ = cj.1.this.a.p();
          ☃ = cj.1.this.a.q();
          ☃++;
        }
        this.b = new cj(☃, ☃, ☃);
        return this.b;
      }
    };
  }
}
