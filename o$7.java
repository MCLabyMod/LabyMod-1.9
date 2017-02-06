import com.google.common.base.Predicate;

final class o$7
  implements Predicate<rr>
{
  o$7(int paramInt1, int paramInt2) {}
  
  public boolean a(rr ☃)
  {
    if (!(☃ instanceof lr)) {
      return false;
    }
    lr ☃ = (lr)☃;
    return ((this.a <= -1) || (☃.bK >= this.a)) && ((this.b <= -1) || (☃.bK <= this.b));
  }
}
