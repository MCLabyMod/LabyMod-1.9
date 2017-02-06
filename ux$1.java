import com.google.common.base.Predicate;

class ux$1
  implements Predicate<rr>
{
  ux$1(ux paramux) {}
  
  public boolean a(rr ☃)
  {
    if (!(☃ instanceof zj)) {
      return false;
    }
    if (((zj)☃).bJ.a) {
      return false;
    }
    double ☃ = this.a.f();
    if (☃.aK()) {
      ☃ *= 0.800000011920929D;
    }
    if (☃.aN())
    {
      float ☃ = ((zj)☃).cG();
      if (☃ < 0.1F) {
        ☃ = 0.1F;
      }
      ☃ *= 0.7F * ☃;
    }
    if (☃.g(ux.a(this.a)) > ☃) {
      return false;
    }
    return vc.a(ux.a(this.a), (sa)☃, false, true);
  }
}
