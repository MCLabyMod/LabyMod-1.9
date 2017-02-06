import com.google.common.base.Predicate;

final class o$13
  implements Predicate<rr>
{
  o$13(bbj parambbj, boolean paramBoolean1, double paramDouble1, boolean paramBoolean2, double paramDouble2) {}
  
  public boolean a(rr ☃)
  {
    if (☃ == null) {
      return false;
    }
    double ☃ = this.a.c(☃.p, ☃.q, ☃.r);
    return ((this.b) || (☃ >= this.c)) && ((this.d) || (☃ <= this.e));
  }
}
