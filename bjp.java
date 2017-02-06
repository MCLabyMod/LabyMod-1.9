public class bjp
  extends bjc
{
  private bkm[] a;
  private bkm[] b;
  private float[] c = new float[7];
  private static final int[][] d = { { 3, 2, 2 }, { 4, 3, 2 }, { 6, 4, 3 }, { 3, 3, 3 }, { 2, 2, 3 }, { 2, 1, 2 }, { 1, 1, 2 } };
  private static final int[][] e = { { 0, 0 }, { 0, 4 }, { 0, 9 }, { 0, 16 }, { 0, 22 }, { 11, 0 }, { 13, 4 } };
  
  public bjp()
  {
    this.a = new bkm[7];
    float ☃ = -3.5F;
    for (int ☃ = 0; ☃ < this.a.length; ☃++)
    {
      this.a[☃] = new bkm(this, e[☃][0], e[☃][1]);
      this.a[☃].a(d[☃][0] * -0.5F, 0.0F, d[☃][2] * -0.5F, d[☃][0], d[☃][1], d[☃][2]);
      this.a[☃].a(0.0F, 24 - d[☃][1], ☃);
      this.c[☃] = ☃;
      if (☃ < this.a.length - 1) {
        ☃ += (d[☃][2] + d[(☃ + 1)][2]) * 0.5F;
      }
    }
    this.b = new bkm[3];
    this.b[0] = new bkm(this, 20, 0);
    this.b[0].a(-5.0F, 0.0F, d[2][2] * -0.5F, 10, 8, d[2][2]);
    this.b[0].a(0.0F, 16.0F, this.c[2]);
    this.b[1] = new bkm(this, 20, 11);
    this.b[1].a(-3.0F, 0.0F, d[4][2] * -0.5F, 6, 4, d[4][2]);
    this.b[1].a(0.0F, 20.0F, this.c[4]);
    this.b[2] = new bkm(this, 20, 18);
    this.b[2].a(-3.0F, 0.0F, d[4][2] * -0.5F, 6, 5, d[1][2]);
    this.b[2].a(0.0F, 19.0F, this.c[1]);
  }
  
  public void a(rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    for (int ☃ = 0; ☃ < this.a.length; ☃++) {
      this.a[☃].a(☃);
    }
    for (int ☃ = 0; ☃ < this.b.length; ☃++) {
      this.b[☃].a(☃);
    }
  }
  
  public void a(float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, rr ☃)
  {
    for (int ☃ = 0; ☃ < this.a.length; ☃++)
    {
      this.a[☃].g = (on.b(☃ * 0.9F + ☃ * 0.15F * 3.1415927F) * 3.1415927F * 0.05F * (1 + Math.abs(☃ - 2)));
      this.a[☃].c = (on.a(☃ * 0.9F + ☃ * 0.15F * 3.1415927F) * 3.1415927F * 0.2F * Math.abs(☃ - 2));
    }
    this.b[0].g = this.a[2].g;
    this.b[1].g = this.a[4].g;
    this.b[1].c = this.a[4].c;
    this.b[2].g = this.a[1].g;
    this.b[2].c = this.a[1].c;
  }
}
