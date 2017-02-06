public class wa
  extends vy
{
  public wa(aht ☃)
  {
    super(☃);
    a(0.9F, 1.4F);
    this.by = aju.bw;
  }
  
  public boolean a(zj ☃, qm ☃, adq ☃)
  {
    if ((☃ != null) && (☃.b() == ads.B) && (l() >= 0) && (!☃.bJ.d))
    {
      if (--☃.b == 0) {
        ☃.a(☃, new adq(ads.C));
      } else if (!☃.br.c(new adq(ads.C))) {
        ☃.a(new adq(ads.C), false);
      }
      return true;
    }
    if ((☃ != null) && (☃.b() == ads.bl) && (l() >= 0))
    {
      T();
      this.l.a(cy.b, this.p, this.q + this.H / 2.0F, this.r, 0.0D, 0.0D, 0.0D, new int[0]);
      if (!this.l.E)
      {
        vy ☃ = new vy(this.l);
        ☃.b(this.p, this.q, this.r, this.v, this.w);
        ☃.c(bQ());
        ☃.aM = this.aM;
        if (o_()) {
          ☃.c(bf());
        }
        this.l.a(☃);
        for (int ☃ = 0; ☃ < 5; ☃++) {
          this.l.a(new yd(this.l, this.p, this.q + this.H, this.r, new adq(aju.Q)));
        }
        ☃.a(1, ☃);
        a(ng.dw, 1.0F, 1.0F);
      }
      return true;
    }
    return super.a(☃, ☃, ☃);
  }
  
  public wa c(ro ☃)
  {
    return new wa(this.l);
  }
  
  protected kk J()
  {
    return azt.H;
  }
}
