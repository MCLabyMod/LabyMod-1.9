import com.google.common.collect.Lists;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bvr
{
  private static final Logger a = ;
  private final List<bvs> b = Lists.newArrayList();
  private final List<Integer> c = Lists.newArrayList();
  private int d = 0;
  private int e = -1;
  private List<Integer> f = Lists.newArrayList();
  private int g = -1;
  
  public bvr(bvr ☃)
  {
    this();
    for (int ☃ = 0; ☃ < ☃.i(); ☃++) {
      a(☃.c(☃));
    }
    this.d = ☃.g();
  }
  
  public bvr() {}
  
  public void a()
  {
    this.b.clear();
    this.c.clear();
    this.e = -1;
    this.f.clear();
    this.g = -1;
    this.d = 0;
  }
  
  public bvr a(bvs ☃)
  {
    if ((☃.f()) && (j()))
    {
      a.warn("VertexFormat error: Trying to add a position VertexFormatElement when one already exists, ignoring.");
      return this;
    }
    this.b.add(☃);
    this.c.add(Integer.valueOf(this.d));
    switch (bvr.1.a[☃.b().ordinal()])
    {
    case 1: 
      this.g = this.d;
      break;
    case 2: 
      this.e = this.d;
      break;
    case 3: 
      this.f.add(☃.d(), Integer.valueOf(this.d));
      break;
    }
    this.d += ☃.e();
    
    return this;
  }
  
  public boolean b()
  {
    return this.g >= 0;
  }
  
  public int c()
  {
    return this.g;
  }
  
  public boolean d()
  {
    return this.e >= 0;
  }
  
  public int e()
  {
    return this.e;
  }
  
  public boolean a(int ☃)
  {
    return this.f.size() - 1 >= ☃;
  }
  
  public int b(int ☃)
  {
    return ((Integer)this.f.get(☃)).intValue();
  }
  
  public String toString()
  {
    String ☃ = "format: " + this.b.size() + " elements: ";
    for (int ☃ = 0; ☃ < this.b.size(); ☃++)
    {
      ☃ = ☃ + ((bvs)this.b.get(☃)).toString();
      if (☃ != this.b.size() - 1) {
        ☃ = ☃ + " ";
      }
    }
    return ☃;
  }
  
  private boolean j()
  {
    int ☃ = 0;
    for (int ☃ = this.b.size(); ☃ < ☃; ☃++)
    {
      bvs ☃ = (bvs)this.b.get(☃);
      if (☃.f()) {
        return true;
      }
    }
    return false;
  }
  
  public int f()
  {
    return g() / 4;
  }
  
  public int g()
  {
    return this.d;
  }
  
  public List<bvs> h()
  {
    return this.b;
  }
  
  public int i()
  {
    return this.b.size();
  }
  
  public bvs c(int ☃)
  {
    return (bvs)this.b.get(☃);
  }
  
  public int d(int ☃)
  {
    return ((Integer)this.c.get(☃)).intValue();
  }
  
  public boolean equals(Object ☃)
  {
    if (this == ☃) {
      return true;
    }
    if ((☃ == null) || (getClass() != ☃.getClass())) {
      return false;
    }
    bvr ☃ = (bvr)☃;
    if (this.d != ☃.d) {
      return false;
    }
    if (!this.b.equals(☃.b)) {
      return false;
    }
    if (!this.c.equals(☃.c)) {
      return false;
    }
    return true;
  }
  
  public int hashCode()
  {
    int ☃ = this.b.hashCode();
    ☃ = 31 * ☃ + this.c.hashCode();
    ☃ = 31 * ☃ + this.d;
    return ☃;
  }
}
