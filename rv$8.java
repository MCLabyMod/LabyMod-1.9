import com.google.common.base.Predicate;

final class rv$8
  implements Predicate<rr>
{
  rv$8(rr paramrr, bbr parambbr, bbr.a parama) {}
  
  public boolean a(rr ☃)
  {
    if (!☃.aq()) {
      return false;
    }
    if ((this.a.l.E) && ((!(☃ instanceof zj)) || (!((zj)☃).cJ()))) {
      return false;
    }
    bbr ☃ = ☃.aO();
    bbr.a ☃ = ☃ == null ? bbr.a.a : ☃.k();
    if (☃ == bbr.a.b) {
      return false;
    }
    boolean ☃ = (this.b != null) && (this.b.a(☃));
    if (((this.c == bbr.a.d) || (☃ == bbr.a.d)) && (☃)) {
      return false;
    }
    if (((this.c == bbr.a.c) || (☃ == bbr.a.c)) && (!☃)) {
      return false;
    }
    return true;
  }
}
