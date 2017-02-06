import java.util.Random;

public class avr$a
  extends awi
{
  private String d;
  private aoe e;
  private boolean f;
  
  public avr$a() {}
  
  public avr$a(String ☃, cj ☃, aoe ☃, boolean ☃)
  {
    super(0);
    
    this.d = ☃;
    this.e = ☃;
    this.f = ☃;
    
    a(☃);
  }
  
  private void a(cj ☃)
  {
    awo ☃ = avr.a.a(null, new kk("endcity/" + this.d));
    awn ☃;
    awn ☃;
    if (this.f) {
      ☃ = avr.b().a().a(this.e);
    } else {
      ☃ = avr.c().a().a(this.e);
    }
    a(☃, ☃, ☃);
  }
  
  protected void a(dn ☃)
  {
    super.a(☃);
    
    ☃.a("Template", this.d);
    ☃.a("Rot", this.e.name());
    ☃.a("OW", this.f);
  }
  
  protected void b(dn ☃)
  {
    super.b(☃);
    
    this.d = ☃.l("Template");
    this.e = aoe.valueOf(☃.l("Rot"));
    this.f = ☃.p("OW");
    
    a(this.c);
  }
  
  protected void a(String ☃, cj ☃, aht ☃, Random ☃, avp ☃)
  {
    if (☃.startsWith("Chest"))
    {
      cj ☃ = ☃.b();
      if (☃.b(☃))
      {
        apv ☃ = ☃.r(☃);
        if ((☃ instanceof apx)) {
          ((apx)☃).a(azt.c, ☃.nextLong());
        }
      }
    }
    else if (☃.startsWith("Sentry"))
    {
      yu ☃ = new yu(☃);
      ☃.b(☃.p() + 0.5D, ☃.q() + 0.5D, ☃.r() + 0.5D);
      ☃.g(☃);
      ☃.a(☃);
    }
    else if (☃.startsWith("Elytra"))
    {
      xs ☃ = new xs(☃, ☃, this.e.a(cq.d));
      ☃.a(new adq(ads.cR));
      ☃.a(☃);
    }
  }
}
