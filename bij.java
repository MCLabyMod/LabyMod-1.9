public class bij
  extends bjc
{
  private bkm[] a;
  private bkm b;
  
  public bij()
  {
    this.a = new bkm[12];
    for (int ☃ = 0; ☃ < this.a.length; ☃++)
    {
      this.a[☃] = new bkm(this, 0, 16);
      this.a[☃].a(0.0F, 0.0F, 0.0F, 2, 8, 2);
    }
    this.b = new bkm(this, 0, 0);
    this.b.a(-4.0F, -4.0F, -4.0F, 8, 8, 8);
  }
  
  public void a(rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    
    this.b.a(☃);
    for (int ☃ = 0; ☃ < this.a.length; ☃++) {
      this.a[☃].a(☃);
    }
  }
  
  public void a(float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, rr ☃)
  {
    float ☃ = ☃ * 3.1415927F * -0.1F;
    for (int ☃ = 0; ☃ < 4; ☃++)
    {
      this.a[☃].d = (-2.0F + on.b((☃ * 2 + ☃) * 0.25F));
      this.a[☃].c = (on.b(☃) * 9.0F);
      this.a[☃].e = (on.a(☃) * 9.0F);
      ☃ += 1.5707964F;
    }
    ☃ = 0.7853982F + ☃ * 3.1415927F * 0.03F;
    for (int ☃ = 4; ☃ < 8; ☃++)
    {
      this.a[☃].d = (2.0F + on.b((☃ * 2 + ☃) * 0.25F));
      this.a[☃].c = (on.b(☃) * 7.0F);
      this.a[☃].e = (on.a(☃) * 7.0F);
      ☃ += 1.5707964F;
    }
    ☃ = 0.47123894F + ☃ * 3.1415927F * -0.05F;
    for (int ☃ = 8; ☃ < 12; ☃++)
    {
      this.a[☃].d = (11.0F + on.b((☃ * 1.5F + ☃) * 0.5F));
      this.a[☃].c = (on.b(☃) * 5.0F);
      this.a[☃].e = (on.a(☃) * 5.0F);
      ☃ += 1.5707964F;
    }
    this.b.g = (☃ * 0.017453292F);
    this.b.f = (☃ * 0.017453292F);
  }
}
