public class byc
  extends bya
{
  private final aah m;
  private float n = 0.0F;
  
  public byc(aah ☃)
  {
    super(ng.dv, nh.g);
    
    this.m = ☃;
    this.i = true;
    this.j = 0;
  }
  
  public void c()
  {
    if (this.m.F)
    {
      this.l = true;
      return;
    }
    this.f = ((float)this.m.p);
    this.g = ((float)this.m.q);
    this.h = ((float)this.m.r);
    
    float ☃ = on.a(this.m.s * this.m.s + this.m.u * this.m.u);
    if (☃ >= 0.01D)
    {
      this.n = on.a(this.n + 0.0025F, 0.0F, 1.0F);
      
      this.d = (0.0F + on.a(☃, 0.0F, 0.5F) * 0.7F);
    }
    else
    {
      this.n = 0.0F;
      this.d = 0.0F;
    }
  }
}
