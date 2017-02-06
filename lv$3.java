import com.google.common.collect.AbstractIterator;
import java.util.Iterator;

class lv$3
  extends AbstractIterator<ase>
{
  lv$3(lv paramlv, Iterator paramIterator) {}
  
  protected ase a()
  {
    while (this.a.hasNext())
    {
      lu ☃ = (lu)this.a.next();
      ase ☃ = ☃.f();
      if (☃ != null)
      {
        if ((!☃.v()) && (☃.u())) {
          return ☃;
        }
        if (!☃.j()) {
          return ☃;
        }
        if (☃.a(128.0D, lv.d())) {
          return ☃;
        }
      }
    }
    return (ase)endOfData();
  }
}
