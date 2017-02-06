public class aya
  extends axv
{
  public aya(long ☃, axv ☃)
  {
    super(☃);
    this.a = ☃;
  }
  
  public int[] a(int ☃, int ☃, int ☃, int ☃)
  {
    int ☃ = ☃ - 1;
    int ☃ = ☃ - 1;
    int ☃ = ☃ + 2;
    int ☃ = ☃ + 2;
    int[] ☃ = this.a.a(☃, ☃, ☃, ☃);
    
    int[] ☃ = axt.a(☃ * ☃);
    for (int ☃ = 0; ☃ < ☃; ☃++) {
      for (int ☃ = 0; ☃ < ☃; ☃++)
      {
        int ☃ = c(☃[(☃ + 0 + (☃ + 1) * ☃)]);
        int ☃ = c(☃[(☃ + 2 + (☃ + 1) * ☃)]);
        int ☃ = c(☃[(☃ + 1 + (☃ + 0) * ☃)]);
        int ☃ = c(☃[(☃ + 1 + (☃ + 2) * ☃)]);
        int ☃ = c(☃[(☃ + 1 + (☃ + 1) * ☃)]);
        if ((☃ != ☃) || (☃ != ☃) || (☃ != ☃) || (☃ != ☃)) {
          ☃[(☃ + ☃ * ☃)] = aig.a(ail.i);
        } else {
          ☃[(☃ + ☃ * ☃)] = -1;
        }
      }
    }
    return ☃;
  }
  
  private int c(int ☃)
  {
    if (☃ >= 2) {
      return 2 + (☃ & 0x1);
    }
    return ☃;
  }
}
