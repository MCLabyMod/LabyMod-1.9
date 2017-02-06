import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class tl
{
  private static final Logger a = ;
  
  class a
  {
    public final tk a;
    public final int b;
    public boolean c;
    
    public a(int ☃, tk ☃)
    {
      this.b = ☃;
      this.a = ☃;
    }
    
    public boolean equals(Object ☃)
    {
      if (this == ☃) {
        return true;
      }
      if ((☃ == null) || (getClass() != ☃.getClass())) {
        return false;
      }
      return this.a.equals(((a)☃).a);
    }
    
    public int hashCode()
    {
      return this.a.hashCode();
    }
  }
  
  private final Set<tl.a> b = Sets.newLinkedHashSet();
  private final Set<tl.a> c = Sets.newLinkedHashSet();
  private final oo d;
  private int e;
  private int f = 3;
  private int g = 0;
  
  public tl(oo ☃)
  {
    this.d = ☃;
  }
  
  public void a(int ☃, tk ☃)
  {
    this.b.add(new tl.a(☃, ☃));
  }
  
  public void a(tk ☃)
  {
    Iterator<tl.a> ☃ = this.b.iterator();
    while (☃.hasNext())
    {
      tl.a ☃ = (tl.a)☃.next();
      tk ☃ = ☃.a;
      if (☃ == ☃)
      {
        if (☃.c)
        {
          ☃.c = false;
          ☃.a.d();
          this.c.remove(☃);
        }
        ☃.remove();
        return;
      }
    }
  }
  
  public void a()
  {
    this.d.a("goalSetup");
    Iterator<tl.a> ☃;
    if (this.e++ % this.f == 0) {
      for (tl.a ☃ : this.b) {
        if (☃.c)
        {
          if ((!b(☃)) || (!a(☃)))
          {
            ☃.c = false;
            ☃.a.d();
            this.c.remove(☃);
          }
        }
        else if ((b(☃)) && (☃.a.a()))
        {
          ☃.c = true;
          ☃.a.c();
          this.c.add(☃);
        }
      }
    } else {
      for (☃ = this.c.iterator(); ☃.hasNext();)
      {
        tl.a ☃ = (tl.a)☃.next();
        if (!a(☃))
        {
          ☃.c = false;
          ☃.a.d();
          ☃.remove();
        }
      }
    }
    this.d.b();
    if (!this.c.isEmpty())
    {
      this.d.a("goalTick");
      for (tl.a ☃ : this.c) {
        ☃.a.e();
      }
      this.d.b();
    }
  }
  
  private boolean a(tl.a ☃)
  {
    return ☃.a.b();
  }
  
  private boolean b(tl.a ☃)
  {
    if (this.c.isEmpty()) {
      return true;
    }
    if (b(☃.a.h())) {
      return false;
    }
    for (tl.a ☃ : this.c) {
      if (☃ != ☃) {
        if (☃.b >= ☃.b)
        {
          if (!a(☃, ☃)) {
            return false;
          }
        }
        else if (!☃.a.g()) {
          return false;
        }
      }
    }
    return true;
  }
  
  private boolean a(tl.a ☃, tl.a ☃)
  {
    return (☃.a.h() & ☃.a.h()) == 0;
  }
  
  public boolean b(int ☃)
  {
    return (this.g & ☃) > 0;
  }
  
  public void c(int ☃)
  {
    this.g |= ☃;
  }
  
  public void d(int ☃)
  {
    this.g &= (☃ ^ 0xFFFFFFFF);
  }
  
  public void a(int ☃, boolean ☃)
  {
    if (☃) {
      d(☃);
    } else {
      c(☃);
    }
  }
}
