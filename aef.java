import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;

public class aef
  extends ado
{
  private static final Map<nf, aef> a = ;
  private final nf b;
  private final String c;
  
  protected aef(String ☃, nf ☃)
  {
    this.c = ("item.record." + ☃ + ".desc");
    this.b = ☃;
    this.j = 1;
    a(acq.f);
    
    a.put(this.b, this);
  }
  
  public qo a(adq ☃, zj ☃, aht ☃, cj ☃, qm ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    arc ☃ = ☃.o(☃);
    if ((☃.t() != aju.aN) || (((Boolean)☃.c(amj.a)).booleanValue())) {
      return qo.b;
    }
    if (!☃.E)
    {
      ((amj)aju.aN).a(☃, ☃, ☃, ☃);
      ☃.a(null, 1010, ☃, ado.a(this));
      ☃.b -= 1;
      ☃.b(nt.Z);
    }
    return qo.a;
  }
  
  public void a(adq ☃, zj ☃, List<String> ☃, boolean ☃)
  {
    ☃.add(g());
  }
  
  public String g()
  {
    return di.a(this.c);
  }
  
  public aee g(adq ☃)
  {
    return aee.c;
  }
  
  public static aef a(nf ☃)
  {
    return (aef)a.get(☃);
  }
  
  public nf h()
  {
    return this.b;
  }
}
