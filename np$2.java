import java.text.DecimalFormat;

final class np$2
  implements nq
{
  public String a(int ☃)
  {
    double ☃ = ☃ / 20.0D;
    double ☃ = ☃ / 60.0D;
    double ☃ = ☃ / 60.0D;
    double ☃ = ☃ / 24.0D;
    double ☃ = ☃ / 365.0D;
    if (☃ > 0.5D) {
      return np.n().format(☃) + " y";
    }
    if (☃ > 0.5D) {
      return np.n().format(☃) + " d";
    }
    if (☃ > 0.5D) {
      return np.n().format(☃) + " h";
    }
    if (☃ > 0.5D) {
      return np.n().format(☃) + " m";
    }
    return ☃ + " s";
  }
}
