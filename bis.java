public class bis
  extends bjc
{
  private static final int[][] a = { { 4, 3, 2 }, { 6, 4, 5 }, { 3, 3, 1 }, { 1, 2, 1 } };
  private static final int[][] b = { { 0, 0 }, { 0, 5 }, { 0, 14 }, { 0, 18 } };
  private static final int c = a.length;
  private final bkm[] d;
  
  public bis()
  {
    this.d = new bkm[c];
    float ☃ = -3.5F;
    for (int ☃ = 0; ☃ < this.d.length; ☃++)
    {
      this.d[☃] = new bkm(this, b[☃][0], b[☃][1]);
      this.d[☃].a(a[☃][0] * -0.5F, 0.0F, a[☃][2] * -0.5F, a[☃][0], a[☃][1], a[☃][2]);
      this.d[☃].a(0.0F, 24 - a[☃][1], ☃);
      if (☃ < this.d.length - 1) {
        ☃ += (a[☃][2] + a[(☃ + 1)][2]) * 0.5F;
      }
    }
  }
  
  public void a(rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    for (int ☃ = 0; ☃ < this.d.length; ☃++) {
      this.d[☃].a(☃);
    }
  }
  
  public void a(float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, rr ☃)
  {
    for (int ☃ = 0; ☃ < this.d.length; ☃++)
    {
      this.d[☃].g = (on.b(☃ * 0.9F + ☃ * 0.15F * 3.1415927F) * 3.1415927F * 0.01F * (1 + Math.abs(☃ - 2)));
      this.d[☃].c = (on.a(☃ * 0.9F + ☃ * 0.15F * 3.1415927F) * 3.1415927F * 0.1F * Math.abs(☃ - 2));
    }
  }
}
