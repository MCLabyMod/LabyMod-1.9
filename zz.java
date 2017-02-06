import java.util.Random;

public class zz
  extends zy
{
  public zz(aht ☃)
  {
    super(☃);
  }
  
  public zz(aht ☃, sa ☃)
  {
    super(☃, ☃);
  }
  
  public zz(aht ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃);
  }
  
  protected void a(bbi ☃)
  {
    if (☃.d != null) {
      ☃.d.a(rc.a(this, k()), 0.0F);
    }
    if ((!this.l.E) && (this.S.nextInt(8) == 0))
    {
      int ☃ = 1;
      if (this.S.nextInt(32) == 0) {
        ☃ = 4;
      }
      for (int ☃ = 0; ☃ < ☃; ☃++)
      {
        vx ☃ = new vx(this.l);
        ☃.b_(41536);
        
        ☃.b(this.p, this.q, this.r, this.v, 0.0F);
        this.l.a(☃);
      }
    }
    double ☃ = 0.08D;
    for (int ☃ = 0; ☃ < 8; ☃++) {
      this.l.a(cy.K, this.p, this.q, this.r, (this.S.nextFloat() - 0.5D) * 0.08D, (this.S.nextFloat() - 0.5D) * 0.08D, (this.S.nextFloat() - 0.5D) * 0.08D, new int[] { ado.a(ads.aW) });
    }
    if (!this.l.E) {
      T();
    }
  }
}
