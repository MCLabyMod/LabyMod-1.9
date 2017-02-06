public class byd
  extends bya
{
  private final zj m;
  private final aah n;
  
  public byd(zj ☃, aah ☃)
  {
    super(ng.du, nh.g);
    
    this.m = ☃;
    this.n = ☃;
    
    this.k = byi.a.a;
    this.i = true;
    this.j = 0;
  }
  
  public void c()
  {
    if ((this.n.F) || (!this.m.aI()) || (this.m.by() != this.n))
    {
      this.l = true;
      return;
    }
    float ☃ = on.a(this.n.s * this.n.s + this.n.u * this.n.u);
    if (☃ >= 0.01D) {
      this.d = (0.0F + on.a(☃, 0.0F, 1.0F) * 0.75F);
    } else {
      this.d = 0.0F;
    }
  }
}
