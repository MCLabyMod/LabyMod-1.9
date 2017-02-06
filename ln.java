import com.google.common.base.Objects;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class ln
  extends qe
{
  private final Set<lr> h = Sets.newHashSet();
  private final Set<lr> i = Collections.unmodifiableSet(this.h);
  private boolean j = true;
  
  public ln(eu ☃, qe.a ☃, qe.b ☃)
  {
    super(on.a(), ☃, ☃, ☃);
  }
  
  public void a(float ☃)
  {
    if (☃ != this.b)
    {
      super.a(☃);
      a(fv.a.c);
    }
  }
  
  public void a(qe.a ☃)
  {
    if (☃ != this.c)
    {
      super.a(☃);
      a(fv.a.e);
    }
  }
  
  public void a(qe.b ☃)
  {
    if (☃ != this.d)
    {
      super.a(☃);
      a(fv.a.e);
    }
  }
  
  public qe a(boolean ☃)
  {
    if (☃ != this.e)
    {
      super.a(☃);
      a(fv.a.f);
    }
    return this;
  }
  
  public qe b(boolean ☃)
  {
    if (☃ != this.f)
    {
      super.b(☃);
      a(fv.a.f);
    }
    return this;
  }
  
  public qe c(boolean ☃)
  {
    if (☃ != this.g)
    {
      super.c(☃);
      a(fv.a.f);
    }
    return this;
  }
  
  public void a(eu ☃)
  {
    if (!Objects.equal(☃, this.a))
    {
      super.a(☃);
      a(fv.a.d);
    }
  }
  
  private void a(fv.a ☃)
  {
    fv ☃;
    if (this.j)
    {
      ☃ = new fv(☃, this);
      for (lr ☃ : this.h) {
        ☃.a.a(☃);
      }
    }
  }
  
  public void a(lr ☃)
  {
    if ((this.h.add(☃)) && (this.j)) {
      ☃.a.a(new fv(fv.a.a, this));
    }
  }
  
  public void b(lr ☃)
  {
    if ((this.h.remove(☃)) && (this.j)) {
      ☃.a.a(new fv(fv.a.b, this));
    }
  }
  
  public void d(boolean ☃)
  {
    if (☃ != this.j)
    {
      this.j = ☃;
      for (lr ☃ : this.h) {
        ☃.a.a(new fv(☃ ? fv.a.a : fv.a.b, this));
      }
    }
  }
  
  public Collection<lr> c()
  {
    return this.i;
  }
}
