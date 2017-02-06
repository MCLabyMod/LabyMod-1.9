class bgl$a
  extends bcz
{
  private final boolean o;
  
  public bgl$a(int ☃, int ☃, int ☃, boolean ☃)
  {
    super(☃, ☃, ☃, 12, 19, "");
    this.o = ☃;
  }
  
  public void a(bcf ☃, int ☃, int ☃)
  {
    if (!this.m) {
      return;
    }
    ☃.N().a(bgl.f());
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    
    boolean ☃ = (☃ >= this.h) && (☃ >= this.i) && (☃ < this.h + this.f) && (☃ < this.i + this.g);
    int ☃ = 0;
    int ☃ = 176;
    if (!this.l) {
      ☃ += this.f * 2;
    } else if (☃) {
      ☃ += this.f;
    }
    if (!this.o) {
      ☃ += this.g;
    }
    b(this.h, this.i, ☃, ☃, this.f, this.g);
  }
}
