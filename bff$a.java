class bff$a
  extends bcz
{
  private final nh r;
  private final String s;
  public float o = 1.0F;
  public boolean p;
  
  public bff$a(bff parambff, int ☃, int ☃, int ☃, nh ☃, boolean ☃)
  {
    super(☃, ☃, ☃, ☃ ? 310 : 150, 20, "");
    this.r = ☃;
    this.s = bwo.a("soundCategory." + ☃.a(), new Object[0]);
    this.j = (this.s + ": " + parambff.a(☃));
    this.o = bff.a(parambff).a(☃);
  }
  
  protected int a(boolean ☃)
  {
    return 0;
  }
  
  protected void b(bcf ☃, int ☃, int ☃)
  {
    if (!this.m) {
      return;
    }
    if (this.p)
    {
      this.o = ((☃ - (this.h + 4)) / (this.f - 8));
      this.o = on.a(this.o, 0.0F, 1.0F);
      ☃.u.a(this.r, this.o);
      ☃.u.b();
      this.j = (this.s + ": " + this.q.a(this.r));
    }
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    b(this.h + (int)(this.o * (this.f - 8)), this.i, 0, 66, 4, 20);
    b(this.h + (int)(this.o * (this.f - 8)) + 4, this.i, 196, 66, 4, 20);
  }
  
  public boolean c(bcf ☃, int ☃, int ☃)
  {
    if (super.c(☃, ☃, ☃))
    {
      this.o = ((☃ - (this.h + 4)) / (this.f - 8));
      this.o = on.a(this.o, 0.0F, 1.0F);
      ☃.u.a(this.r, this.o);
      ☃.u.b();
      this.j = (this.s + ": " + this.q.a(this.r));
      this.p = true;
      return true;
    }
    return false;
  }
  
  public void a(byx ☃) {}
  
  public void a(int ☃, int ☃)
  {
    if (this.p) {
      this.q.j.U().a(bye.a(ng.go, 1.0F));
    }
    this.p = false;
  }
}
