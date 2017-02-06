public class bdr
  extends bcz
{
  private float p = 1.0F;
  public boolean o;
  private bch.a q;
  private final float r;
  private final float s;
  
  public bdr(int ☃, int ☃, int ☃, bch.a ☃)
  {
    this(☃, ☃, ☃, ☃, 0.0F, 1.0F);
  }
  
  public bdr(int ☃, int ☃, int ☃, bch.a ☃, float ☃, float ☃)
  {
    super(☃, ☃, ☃, 150, 20, "");
    this.q = ☃;
    this.r = ☃;
    this.s = ☃;
    
    bcf ☃ = bcf.z();
    this.p = ☃.c(☃.u.a(☃));
    this.j = ☃.u.c(☃);
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
    if (this.o)
    {
      this.p = ((☃ - (this.h + 4)) / (this.f - 8));
      this.p = on.a(this.p, 0.0F, 1.0F);
      float ☃ = this.q.d(this.p);
      ☃.u.a(this.q, ☃);
      this.p = this.q.c(☃);
      this.j = ☃.u.c(this.q);
    }
    ☃.N().a(a);
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    b(this.h + (int)(this.p * (this.f - 8)), this.i, 0, 66, 4, 20);
    b(this.h + (int)(this.p * (this.f - 8)) + 4, this.i, 196, 66, 4, 20);
  }
  
  public boolean c(bcf ☃, int ☃, int ☃)
  {
    if (super.c(☃, ☃, ☃))
    {
      this.p = ((☃ - (this.h + 4)) / (this.f - 8));
      this.p = on.a(this.p, 0.0F, 1.0F);
      ☃.u.a(this.q, this.q.d(this.p));
      this.j = ☃.u.c(this.q);
      this.o = true;
      return true;
    }
    return false;
  }
  
  public void a(int ☃, int ☃)
  {
    this.o = false;
  }
}
