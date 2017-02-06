import java.util.List;

public class ano
  extends ajo
{
  public static final arn d = arn.a("powered");
  private final ano.a e;
  
  protected ano(axe ☃, ano.a ☃)
  {
    super(☃);
    w(this.A.b().a(d, Boolean.valueOf(false)));
    this.e = ☃;
  }
  
  protected int i(arc ☃)
  {
    return ((Boolean)☃.c(d)).booleanValue() ? 15 : 0;
  }
  
  protected arc a(arc ☃, int ☃)
  {
    return ☃.a(d, Boolean.valueOf(☃ > 0));
  }
  
  protected void b(aht ☃, cj ☃)
  {
    if (this.x == axe.d) {
      ☃.a(null, ☃, ng.he, nh.e, 0.3F, 0.8F);
    } else {
      ☃.a(null, ☃, ng.gg, nh.e, 0.3F, 0.6F);
    }
  }
  
  protected void c(aht ☃, cj ☃)
  {
    if (this.x == axe.d) {
      ☃.a(null, ☃, ng.hd, nh.e, 0.3F, 0.7F);
    } else {
      ☃.a(null, ☃, ng.gf, nh.e, 0.3F, 0.5F);
    }
  }
  
  protected int e(aht ☃, cj ☃)
  {
    bbh ☃ = c.a(☃);
    List<? extends rr> ☃;
    switch (ano.1.a[this.e.ordinal()])
    {
    case 1: 
      ☃ = ☃.b(null, ☃);
      break;
    case 2: 
      ☃ = ☃.a(sa.class, ☃);
      break;
    default: 
      return 0;
    }
    if (!☃.isEmpty()) {
      for (rr ☃ : ☃) {
        if (!☃.ba()) {
          return 15;
        }
      }
    }
    return 0;
  }
  
  public arc a(int ☃)
  {
    return u().a(d, Boolean.valueOf(☃ == 1));
  }
  
  public int e(arc ☃)
  {
    return ((Boolean)☃.c(d)).booleanValue() ? 1 : 0;
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { d });
  }
  
  public static enum a
  {
    private a() {}
  }
}
