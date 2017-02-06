import com.google.common.collect.AbstractIterator;
import java.util.Iterator;

final class cj$2
  implements Iterable<cj.a>
{
  cj$2(cj paramcj1, cj paramcj2) {}
  
  public Iterator<cj.a> iterator()
  {
    new AbstractIterator()
    {
      private cj.a b = null;
      
      protected cj.a a()
      {
        if (this.b == null)
        {
          this.b = new cj.a(cj.2.this.a.p(), cj.2.this.a.q(), cj.2.this.a.r());
          return this.b;
        }
        if (this.b.equals(cj.2.this.b)) {
          return (cj.a)endOfData();
        }
        int ☃ = this.b.p();
        int ☃ = this.b.q();
        int ☃ = this.b.r();
        if (☃ < cj.2.this.b.p())
        {
          ☃++;
        }
        else if (☃ < cj.2.this.b.q())
        {
          ☃ = cj.2.this.a.p();
          ☃++;
        }
        else if (☃ < cj.2.this.b.r())
        {
          ☃ = cj.2.this.a.p();
          ☃ = cj.2.this.a.q();
          ☃++;
        }
        cj.a.a(this.b, ☃);
        cj.a.b(this.b, ☃);
        cj.a.c(this.b, ☃);
        return this.b;
      }
    };
  }
}
