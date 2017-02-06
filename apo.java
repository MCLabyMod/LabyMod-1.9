import java.util.List;

public class apo
  extends ajo
{
  public static final arq d = arq.a("power", 0, 15);
  private final int e;
  
  protected apo(axe ☃, int ☃)
  {
    this(☃, ☃, ☃.r());
  }
  
  protected apo(axe ☃, int ☃, axf ☃)
  {
    super(☃, ☃);
    w(this.A.b().a(d, Integer.valueOf(0)));
    this.e = ☃;
  }
  
  protected int e(aht ☃, cj ☃)
  {
    int ☃ = Math.min(☃.a(rr.class, c.a(☃)).size(), this.e);
    if (☃ > 0)
    {
      float ☃ = Math.min(this.e, ☃) / this.e;
      return on.f(☃ * 15.0F);
    }
    return 0;
  }
  
  protected void b(aht ☃, cj ☃)
  {
    ☃.a(null, ☃, ng.ds, nh.e, 0.3F, 0.90000004F);
  }
  
  protected void c(aht ☃, cj ☃)
  {
    ☃.a(null, ☃, ng.dr, nh.e, 0.3F, 0.75F);
  }
  
  protected int i(arc ☃)
  {
    return ((Integer)☃.c(d)).intValue();
  }
  
  protected arc a(arc ☃, int ☃)
  {
    return ☃.a(d, Integer.valueOf(☃));
  }
  
  public int a(aht ☃)
  {
    return 10;
  }
  
  public arc a(int ☃)
  {
    return u().a(d, Integer.valueOf(☃));
  }
  
  public int e(arc ☃)
  {
    return ((Integer)☃.c(d)).intValue();
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { d });
  }
}
