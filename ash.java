public class ash
{
  public final byte[] a;
  private final int b;
  private final int c;
  
  public ash(byte[] ☃, int ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = (☃ + 4);
  }
  
  public int a(int ☃, int ☃, int ☃)
  {
    int ☃ = ☃ << this.c | ☃ << this.b | ☃;
    int ☃ = ☃ >> 1;
    int ☃ = ☃ & 0x1;
    if (☃ == 0) {
      return this.a[☃] & 0xF;
    }
    return this.a[☃] >> 4 & 0xF;
  }
}
