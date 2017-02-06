import com.google.common.base.Predicate;

class yj$b
  extends uy<zj>
{
  private final yj i;
  private zj j;
  private int k;
  private int l;
  
  public yj$b(yj ☃)
  {
    super(☃, zj.class, false);
    this.i = ☃;
  }
  
  public boolean a()
  {
    double ☃ = f();
    this.j = this.i.l.a(this.i.p, this.i.q, this.i.r, ☃, ☃, null, new Predicate()
    {
      public boolean a(zj ☃)
      {
        return (☃ != null) && (yj.a(yj.b.a(yj.b.this), ☃));
      }
    });
    return this.j != null;
  }
  
  public void c()
  {
    this.k = 5;
    this.l = 0;
  }
  
  public void d()
  {
    this.j = null;
    
    super.d();
  }
  
  public boolean b()
  {
    if (this.j != null)
    {
      if (!yj.a(this.i, this.j)) {
        return false;
      }
      this.i.a(this.j, 10.0F, 10.0F);
      return true;
    }
    if ((this.d != null) && (((zj)this.d).au())) {
      return true;
    }
    return super.b();
  }
  
  public void e()
  {
    if (this.j != null)
    {
      if (--this.k <= 0)
      {
        this.d = this.j;
        this.j = null;
        super.c();
      }
    }
    else
    {
      if (this.d != null) {
        if (yj.a(this.i, (zj)this.d))
        {
          if (((zj)this.d).h(this.i) < 16.0D) {
            this.i.da();
          }
          this.l = 0;
        }
        else if ((((zj)this.d).h(this.i) > 256.0D) && 
          (this.l++ >= 30) && 
          (this.i.a(this.d)))
        {
          this.l = 0;
        }
      }
      super.e();
    }
  }
}
