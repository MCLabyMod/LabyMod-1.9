import java.util.Random;

public class yk
  extends yq
{
  private int a = 0;
  private boolean b = false;
  
  public yk(aht ☃)
  {
    super(☃);
    this.b_ = 3;
    
    a(0.4F, 0.3F);
  }
  
  protected void r()
  {
    this.bp.a(1, new th(this));
    this.bp.a(2, new ts(this, 1.0D, false));
    this.bp.a(3, new ug(this, 1.0D));
    this.bp.a(7, new tp(this, zj.class, 8.0F));
    this.bp.a(8, new uf(this));
    
    this.bq.a(1, new uv(this, true, new Class[0]));
    this.bq.a(2, new uy(this, zj.class, true));
  }
  
  public float bn()
  {
    return 0.1F;
  }
  
  protected void bA()
  {
    super.bA();
    
    a(yt.a).a(8.0D);
    a(yt.d).a(0.25D);
    a(yt.e).a(2.0D);
  }
  
  protected boolean ae()
  {
    return false;
  }
  
  protected nf G()
  {
    return ng.ba;
  }
  
  protected nf bR()
  {
    return ng.bc;
  }
  
  protected nf bS()
  {
    return ng.bb;
  }
  
  protected void a(cj ☃, ajt ☃)
  {
    a(ng.bd, 0.15F, 1.0F);
  }
  
  protected kk J()
  {
    return azt.ag;
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    this.a = ☃.h("Lifetime");
    this.b = ☃.p("PlayerSpawned");
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    ☃.a("Lifetime", this.a);
    ☃.a("PlayerSpawned", this.b);
  }
  
  public void m()
  {
    this.aM = this.v;
    
    super.m();
  }
  
  public double ax()
  {
    return 0.3D;
  }
  
  public boolean o()
  {
    return this.b;
  }
  
  public void a(boolean ☃)
  {
    this.b = ☃;
  }
  
  public void n()
  {
    super.n();
    if (this.l.E)
    {
      for (int ☃ = 0; ☃ < 2; ☃++) {
        this.l.a(cy.y, this.p + (this.S.nextDouble() - 0.5D) * this.G, this.q + this.S.nextDouble() * this.H, this.r + (this.S.nextDouble() - 0.5D) * this.G, (this.S.nextDouble() - 0.5D) * 2.0D, -this.S.nextDouble(), (this.S.nextDouble() - 0.5D) * 2.0D, new int[0]);
      }
    }
    else
    {
      if (!cN()) {
        this.a += 1;
      }
      if (this.a >= 2400) {
        T();
      }
    }
  }
  
  protected boolean s_()
  {
    return true;
  }
  
  public boolean cF()
  {
    if (super.cF())
    {
      zj ☃ = this.l.a(this, 5.0D);
      return ☃ == null;
    }
    return false;
  }
  
  public sf ca()
  {
    return sf.c;
  }
}
