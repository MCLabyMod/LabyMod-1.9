public class vy
  extends vw
{
  public vy(aht ☃)
  {
    super(☃);
    a(0.9F, 1.4F);
  }
  
  protected void r()
  {
    this.bp.a(0, new th(this));
    this.bp.a(1, new uc(this, 2.0D));
    this.bp.a(2, new td(this, 1.0D));
    this.bp.a(3, new up(this, 1.25D, ads.Q, false));
    this.bp.a(4, new tj(this, 1.25D));
    this.bp.a(5, new ug(this, 1.0D));
    this.bp.a(6, new tp(this, zj.class, 6.0F));
    this.bp.a(7, new uf(this));
  }
  
  protected void bA()
  {
    super.bA();
    
    a(yt.a).a(10.0D);
    a(yt.d).a(0.20000000298023224D);
  }
  
  protected nf G()
  {
    return ng.am;
  }
  
  protected nf bR()
  {
    return ng.ao;
  }
  
  protected nf bS()
  {
    return ng.an;
  }
  
  protected void a(cj ☃, ajt ☃)
  {
    a(ng.aq, 0.15F, 1.0F);
  }
  
  protected float cd()
  {
    return 0.4F;
  }
  
  protected kk J()
  {
    return azt.G;
  }
  
  public boolean a(zj ☃, qm ☃, adq ☃)
  {
    if ((☃ != null) && (☃.b() == ads.ay) && (!☃.bJ.d) && (!m_()))
    {
      ☃.a(ng.ap, 1.0F, 1.0F);
      if (--☃.b == 0) {
        ☃.a(☃, new adq(ads.aN));
      } else if (!☃.br.c(new adq(ads.aN))) {
        ☃.a(new adq(ads.aN), false);
      }
      return true;
    }
    return super.a(☃, ☃, ☃);
  }
  
  public vy b(ro ☃)
  {
    return new vy(this.l);
  }
  
  public float bn()
  {
    if (m_()) {
      return this.H;
    }
    return 1.3F;
  }
}
