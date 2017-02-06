public class atb
  extends asv
{
  private ata g = null;
  
  public void b()
  {
    this.c = new aio(ail.k);
    this.e = true;
    dn ☃ = this.b.T().a(asw.c);
    this.g = ((this.b instanceof lp) ? new ata((lp)this.b, ☃.o("DragonFight")) : null);
  }
  
  public ary c()
  {
    return new ato(this.b, this.b.T().r(), this.b.O());
  }
  
  public float a(long ☃, float ☃)
  {
    return 0.0F;
  }
  
  public float[] a(float ☃, float ☃)
  {
    return null;
  }
  
  public bbj b(float ☃, float ☃)
  {
    int ☃ = 10518688;
    float ☃ = on.b(☃ * 6.2831855F) * 2.0F + 0.5F;
    ☃ = on.a(☃, 0.0F, 1.0F);
    
    float ☃ = (☃ >> 16 & 0xFF) / 255.0F;
    float ☃ = (☃ >> 8 & 0xFF) / 255.0F;
    float ☃ = (☃ & 0xFF) / 255.0F;
    ☃ *= (☃ * 0.0F + 0.15F);
    ☃ *= (☃ * 0.0F + 0.15F);
    ☃ *= (☃ * 0.0F + 0.15F);
    
    return new bbj(☃, ☃, ☃);
  }
  
  public boolean g()
  {
    return false;
  }
  
  public boolean e()
  {
    return false;
  }
  
  public boolean d()
  {
    return false;
  }
  
  public float f()
  {
    return 8.0F;
  }
  
  public boolean a(int ☃, int ☃)
  {
    return this.b.c(new cj(☃, 0, ☃)).a().c();
  }
  
  public cj h()
  {
    return new cj(100, 50, 0);
  }
  
  public int i()
  {
    return 50;
  }
  
  public boolean b(int ☃, int ☃)
  {
    return false;
  }
  
  public asw p()
  {
    return asw.c;
  }
  
  public void q()
  {
    dn ☃ = new dn();
    if (this.g != null) {
      ☃.a("DragonFight", this.g.a());
    }
    this.b.T().a(asw.c, ☃);
  }
  
  public void r()
  {
    if (this.g != null) {
      this.g.b();
    }
  }
  
  public ata s()
  {
    return this.g;
  }
}
