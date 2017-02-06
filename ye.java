public class ye
  extends rr
{
  private static final ke<Integer> a = kh.a(ye.class, kg.b);
  private sa b;
  private int c = 80;
  
  public ye(aht ☃)
  {
    super(☃);
    this.i = true;
    a(0.98F, 0.98F);
  }
  
  public ye(aht ☃, double ☃, double ☃, double ☃, sa ☃)
  {
    this(☃);
    
    b(☃, ☃, ☃);
    
    float ☃ = (float)(Math.random() * 6.2831854820251465D);
    this.s = (-(float)Math.sin(☃) * 0.02F);
    this.t = 0.20000000298023224D;
    this.u = (-(float)Math.cos(☃) * 0.02F);
    
    a(80);
    
    this.m = ☃;
    this.n = ☃;
    this.o = ☃;
    this.b = ☃;
  }
  
  protected void i()
  {
    this.Z.a(a, Integer.valueOf(80));
  }
  
  protected boolean ae()
  {
    return false;
  }
  
  public boolean ap()
  {
    return !this.F;
  }
  
  public void m()
  {
    this.m = this.p;
    this.n = this.q;
    this.o = this.r;
    
    this.t -= 0.03999999910593033D;
    d(this.s, this.t, this.u);
    this.s *= 0.9800000190734863D;
    this.t *= 0.9800000190734863D;
    this.u *= 0.9800000190734863D;
    if (this.z)
    {
      this.s *= 0.699999988079071D;
      this.u *= 0.699999988079071D;
      this.t *= -0.5D;
    }
    this.c -= 1;
    if (this.c <= 0)
    {
      T();
      if (!this.l.E) {
        n();
      }
    }
    else
    {
      aj();
      this.l.a(cy.l, this.p, this.q + 0.5D, this.r, 0.0D, 0.0D, 0.0D, new int[0]);
    }
  }
  
  private void n()
  {
    float ☃ = 4.0F;
    this.l.a(this, this.p, this.q + this.H / 16.0F, this.r, ☃, true);
  }
  
  protected void b(dn ☃)
  {
    ☃.a("Fuse", (short)l());
  }
  
  protected void a(dn ☃)
  {
    a(☃.g("Fuse"));
  }
  
  public sa j()
  {
    return this.b;
  }
  
  public float bn()
  {
    return 0.0F;
  }
  
  public void a(int ☃)
  {
    this.Z.b(a, Integer.valueOf(☃));
    this.c = ☃;
  }
  
  public void a(ke<?> ☃)
  {
    if (a.equals(☃)) {
      this.c = k();
    }
  }
  
  public int k()
  {
    return ((Integer)this.Z.a(a)).intValue();
  }
  
  public int l()
  {
    return this.c;
  }
}
