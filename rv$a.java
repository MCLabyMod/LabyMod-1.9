import com.google.common.base.Predicate;

public class rv$a
  implements Predicate<rr>
{
  private final adq a;
  
  public rv$a(adq ☃)
  {
    this.a = ☃;
  }
  
  public boolean a(rr ☃)
  {
    if (!☃.au()) {
      return false;
    }
    if (!(☃ instanceof sa)) {
      return false;
    }
    sa ☃ = (sa)☃;
    if (☃.a(sb.d(this.a)) != null) {
      return false;
    }
    if ((☃ instanceof sb)) {
      return ((sb)☃).cM();
    }
    if ((☃ instanceof xq)) {
      return true;
    }
    if ((☃ instanceof zj)) {
      return true;
    }
    return false;
  }
}
