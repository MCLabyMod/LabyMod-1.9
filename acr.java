import com.google.common.collect.Multimap;
import java.util.Set;

public class acr
  extends ado
{
  private Set<ajt> e;
  protected float a = 4.0F;
  protected float b;
  protected float c;
  protected ado.a d;
  
  protected acr(float ☃, float ☃, ado.a ☃, Set<ajt> ☃)
  {
    this.d = ☃;
    this.e = ☃;
    this.j = 1;
    e(☃.a());
    this.a = ☃.b();
    this.b = (☃ + ☃.c());
    this.c = ☃;
    a(acq.i);
  }
  
  protected acr(ado.a ☃, Set<ajt> ☃)
  {
    this(0.0F, 0.0F, ☃, ☃);
  }
  
  public float a(adq ☃, arc ☃)
  {
    return this.e.contains(☃.t()) ? this.a : 1.0F;
  }
  
  public boolean a(adq ☃, sa ☃, sa ☃)
  {
    ☃.a(2, ☃);
    return true;
  }
  
  public boolean a(adq ☃, aht ☃, arc ☃, cj ☃, sa ☃)
  {
    if (☃.b(☃, ☃) != 0.0D) {
      ☃.a(1, ☃);
    }
    return true;
  }
  
  public boolean A_()
  {
    return true;
  }
  
  public ado.a g()
  {
    return this.d;
  }
  
  public int c()
  {
    return this.d.e();
  }
  
  public String h()
  {
    return this.d.toString();
  }
  
  public boolean a(adq ☃, adq ☃)
  {
    if (this.d.f() == ☃.b()) {
      return true;
    }
    return super.a(☃, ☃);
  }
  
  public Multimap<String, sn> a(rw ☃)
  {
    Multimap<String, sn> ☃ = super.a(☃);
    if (☃ == rw.a)
    {
      ☃.put(yt.e.a(), new sn(g, "Tool modifier", this.b, 0));
      ☃.put(yt.f.a(), new sn(h, "Tool modifier", this.c, 0));
    }
    return ☃;
  }
}
