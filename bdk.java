public class bdk
  extends bcz
{
  private boolean o = false;
  
  public bdk(int ☃, int ☃, int ☃)
  {
    super(☃, ☃, ☃, 20, 20, "");
  }
  
  public boolean c()
  {
    return this.o;
  }
  
  public void b(boolean ☃)
  {
    this.o = ☃;
  }
  
  public void a(bcf ☃, int ☃, int ☃)
  {
    if (!this.m) {
      return;
    }
    ☃.N().a(bcz.a);
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    
    boolean ☃ = (☃ >= this.h) && (☃ >= this.i) && (☃ < this.h + this.f) && (☃ < this.i + this.g);
    bdk.a ☃;
    bdk.a ☃;
    if (this.o)
    {
      bdk.a ☃;
      if (!this.l)
      {
        ☃ = bdk.a.c;
      }
      else
      {
        bdk.a ☃;
        if (☃) {
          ☃ = bdk.a.b;
        } else {
          ☃ = bdk.a.a;
        }
      }
    }
    else
    {
      bdk.a ☃;
      if (!this.l)
      {
        ☃ = bdk.a.f;
      }
      else
      {
        bdk.a ☃;
        if (☃) {
          ☃ = bdk.a.e;
        } else {
          ☃ = bdk.a.d;
        }
      }
    }
    b(this.h, this.i, ☃.a(), ☃.b(), this.f, this.g);
  }
  
  static enum a
  {
    private final int g;
    private final int h;
    
    private a(int ☃, int ☃)
    {
      this.g = ☃;
      this.h = ☃;
    }
    
    public int a()
    {
      return this.g;
    }
    
    public int b()
    {
      return this.h;
    }
  }
}
