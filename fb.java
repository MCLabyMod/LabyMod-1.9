import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class fb
  extends es
{
  private final String d;
  private final Object[] e;
  private final Object f = new Object();
  private long g = -1L;
  List<eu> b = Lists.newArrayList();
  public static final Pattern c = Pattern.compile("%(?:(\\d+)\\$)?([A-Za-z%]|$)");
  
  public fb(String ☃, Object... ☃)
  {
    this.d = ☃;
    this.e = ☃;
    for (Object ☃ : ☃) {
      if ((☃ instanceof eu)) {
        ((eu)☃).b().a(b());
      }
    }
  }
  
  synchronized void g()
  {
    synchronized (this.f)
    {
      long ☃ = di.a();
      if (☃ == this.g) {
        return;
      }
      this.g = ☃;
      this.b.clear();
    }
    try
    {
      b(di.a(this.d));
    }
    catch (fc ☃)
    {
      this.b.clear();
      try
      {
        b(di.b(this.d));
      }
      catch (fc ☃)
      {
        throw ☃;
      }
    }
  }
  
  protected void b(String ☃)
  {
    boolean ☃ = false;
    Matcher ☃ = c.matcher(☃);
    
    int ☃ = 0;
    int ☃ = 0;
    try
    {
      while (☃.find(☃))
      {
        int ☃ = ☃.start();
        int ☃ = ☃.end();
        if (☃ > ☃)
        {
          fa ☃ = new fa(String.format(☃.substring(☃, ☃), new Object[0]));
          ☃.b().a(b());
          this.b.add(☃);
        }
        String ☃ = ☃.group(2);
        String ☃ = ☃.substring(☃, ☃);
        if (("%".equals(☃)) && ("%%".equals(☃)))
        {
          fa ☃ = new fa("%");
          ☃.b().a(b());
          this.b.add(☃);
        }
        else if ("s".equals(☃))
        {
          String ☃ = ☃.group(1);
          int ☃ = ☃ != null ? Integer.parseInt(☃) - 1 : ☃++;
          if (☃ < this.e.length) {
            this.b.add(a(☃));
          }
        }
        else
        {
          throw new fc(this, "Unsupported format: '" + ☃ + "'");
        }
        ☃ = ☃;
      }
      if (☃ < ☃.length())
      {
        fa ☃ = new fa(String.format(☃.substring(☃), new Object[0]));
        ☃.b().a(b());
        this.b.add(☃);
      }
    }
    catch (IllegalFormatException ☃)
    {
      throw new fc(this, ☃);
    }
  }
  
  private eu a(int ☃)
  {
    if (☃ >= this.e.length) {
      throw new fc(this, ☃);
    }
    Object ☃ = this.e[☃];
    eu ☃;
    eu ☃;
    if ((☃ instanceof eu))
    {
      ☃ = (eu)☃;
    }
    else
    {
      ☃ = new fa(☃ == null ? "null" : ☃.toString());
      ☃.b().a(b());
    }
    return ☃;
  }
  
  public eu a(ez ☃)
  {
    super.a(☃);
    for (Object ☃ : this.e) {
      if ((☃ instanceof eu)) {
        ((eu)☃).b().a(b());
      }
    }
    if (this.g > -1L) {
      for (eu ☃ : this.b) {
        ☃.b().a(☃);
      }
    }
    return this;
  }
  
  public Iterator<eu> iterator()
  {
    g();
    
    return Iterators.concat(a(this.b), a(this.a));
  }
  
  public String e()
  {
    g();
    
    StringBuilder ☃ = new StringBuilder();
    for (eu ☃ : this.b) {
      ☃.append(☃.e());
    }
    return ☃.toString();
  }
  
  public fb h()
  {
    Object[] ☃ = new Object[this.e.length];
    for (int ☃ = 0; ☃ < this.e.length; ☃++) {
      if ((this.e[☃] instanceof eu)) {
        ☃[☃] = ((eu)this.e[☃]).f();
      } else {
        ☃[☃] = this.e[☃];
      }
    }
    fb ☃ = new fb(this.d, ☃);
    ☃.a(b().m());
    for (eu ☃ : a()) {
      ☃.a(☃.f());
    }
    return ☃;
  }
  
  public boolean equals(Object ☃)
  {
    if (this == ☃) {
      return true;
    }
    if ((☃ instanceof fb))
    {
      fb ☃ = (fb)☃;
      return (Arrays.equals(this.e, ☃.e)) && (this.d.equals(☃.d)) && (super.equals(☃));
    }
    return false;
  }
  
  public int hashCode()
  {
    int ☃ = super.hashCode();
    ☃ = 31 * ☃ + this.d.hashCode();
    ☃ = 31 * ☃ + Arrays.hashCode(this.e);
    return ☃;
  }
  
  public String toString()
  {
    return "TranslatableComponent{key='" + this.d + '\'' + ", args=" + Arrays.toString(this.e) + ", siblings=" + this.a + ", style=" + b() + '}';
  }
  
  public String i()
  {
    return this.d;
  }
  
  public Object[] j()
  {
    return this.e;
  }
}
