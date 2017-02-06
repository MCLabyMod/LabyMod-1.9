import java.util.Map;
import java.util.Random;

public abstract class awi
  extends awg
{
  private static final awn d = new awn();
  protected awo a;
  protected awn b = d.a(true).a(aju.a);
  protected cj c;
  
  public awi() {}
  
  public awi(int ☃)
  {
    super(☃);
  }
  
  protected void a(awo ☃, cj ☃, awn ☃)
  {
    this.a = ☃;
    a(cq.c);
    this.c = ☃;
    this.b = ☃;
    
    h();
  }
  
  protected void a(dn ☃)
  {
    ☃.a("TPX", this.c.p());
    ☃.a("TPY", this.c.q());
    ☃.a("TPZ", this.c.r());
  }
  
  protected void b(dn ☃)
  {
    this.c = new cj(☃.h("TPX"), ☃.h("TPY"), ☃.h("TPZ"));
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    this.b.a(☃);
    
    this.a.b(☃, this.c, this.b);
    
    Map<cj, String> ☃ = this.a.a(this.c, this.b);
    for (cj ☃ : ☃.keySet())
    {
      String ☃ = (String)☃.get(☃);
      a(☃, ☃, ☃, ☃, ☃);
    }
    return true;
  }
  
  protected abstract void a(String paramString, cj paramcj, aht paramaht, Random paramRandom, avp paramavp);
  
  private void h()
  {
    aoe ☃ = this.b.c();
    cj ☃ = this.a.a(☃);
    this.l = new avp(0, 0, 0, ☃.p(), ☃.q() - 1, ☃.r());
    switch (awi.1.a[☃.ordinal()])
    {
    case 1: 
    default: 
      break;
    case 2: 
      this.l.a(-☃.p(), 0, 0);
      break;
    case 3: 
      this.l.a(0, 0, -☃.r());
      break;
    case 4: 
      this.l.a(-☃.p(), 0, -☃.r());
    }
    this.l.a(this.c.p(), this.c.q(), this.c.r());
  }
  
  public void a(int ☃, int ☃, int ☃)
  {
    super.a(☃, ☃, ☃);
    this.c = this.c.a(☃, ☃, ☃);
  }
}
