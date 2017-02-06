public class bde
  extends bcz
{
  private float p = 1.0F;
  public boolean o;
  private String q;
  private final float r;
  private final float s;
  private final bdo.b t;
  private bde.a u;
  
  public bde(bdo.b ☃, int ☃, int ☃, int ☃, String ☃, float ☃, float ☃, float ☃, bde.a ☃)
  {
    super(☃, ☃, ☃, 150, 20, "");
    this.q = ☃;
    this.r = ☃;
    this.s = ☃;
    this.p = ((☃ - ☃) / (☃ - ☃));
    this.u = ☃;
    this.t = ☃;
    this.j = e();
  }
  
  public float c()
  {
    return this.r + (this.s - this.r) * this.p;
  }
  
  public void a(float ☃, boolean ☃)
  {
    this.p = ((☃ - this.r) / (this.s - this.r));
    this.j = e();
    if (☃) {
      this.t.a(this.k, c());
    }
  }
  
  public float d()
  {
    return this.p;
  }
  
  private String e()
  {
    if (this.u == null) {
      return bwo.a(this.q, new Object[0]) + ": " + c();
    }
    return this.u.a(this.k, bwo.a(this.q, new Object[0]), c());
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
      if (this.p < 0.0F) {
        this.p = 0.0F;
      }
      if (this.p > 1.0F) {
        this.p = 1.0F;
      }
      this.j = e();
      this.t.a(this.k, c());
    }
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    b(this.h + (int)(this.p * (this.f - 8)), this.i, 0, 66, 4, 20);
    b(this.h + (int)(this.p * (this.f - 8)) + 4, this.i, 196, 66, 4, 20);
  }
  
  public void a(float ☃)
  {
    this.p = ☃;
    this.j = e();
    this.t.a(this.k, c());
  }
  
  public boolean c(bcf ☃, int ☃, int ☃)
  {
    if (super.c(☃, ☃, ☃))
    {
      this.p = ((☃ - (this.h + 4)) / (this.f - 8));
      if (this.p < 0.0F) {
        this.p = 0.0F;
      }
      if (this.p > 1.0F) {
        this.p = 1.0F;
      }
      this.j = e();
      this.t.a(this.k, c());
      this.o = true;
      return true;
    }
    return false;
  }
  
  public void a(int ☃, int ☃)
  {
    this.o = false;
  }
  
  public static abstract interface a
  {
    public abstract String a(int paramInt, String paramString, float paramFloat);
  }
}
