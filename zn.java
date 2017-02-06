import java.util.List;

public class zn
  extends zp
{
  public zn(aht ☃)
  {
    super(☃);
    a(0.3125F, 0.3125F);
  }
  
  public zn(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    a(0.3125F, 0.3125F);
  }
  
  public zn(aht ☃, sa ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃, ☃);
    a(0.3125F, 0.3125F);
  }
  
  protected void a(bbi ☃)
  {
    if (!this.l.E)
    {
      List<sa> ☃ = this.l.a(sa.class, bl().b(4.0D, 2.0D, 4.0D));
      
      rp ☃ = new rp(this.l, this.p, this.q, this.r);
      ☃.a(this.a);
      ☃.a(cy.Q);
      ☃.a(3.0F);
      ☃.b(2400);
      ☃.c((7.0F - ☃.j()) / ☃.o());
      ☃.a(new rl(rm.g, 1, 1));
      if (!☃.isEmpty()) {
        for (sa ☃ : ☃)
        {
          double ☃ = h(☃);
          if (☃ < 16.0D)
          {
            ☃.b(☃.p, ☃.q, ☃.r);
            break;
          }
        }
      }
      this.l.b(2006, new cj(this.p, this.q, this.r), 0);
      this.l.a(☃);
      
      T();
    }
  }
  
  public boolean ap()
  {
    return false;
  }
  
  public boolean a(rc ☃, float ☃)
  {
    return false;
  }
  
  protected cy j()
  {
    return cy.Q;
  }
  
  protected boolean k()
  {
    return false;
  }
}
