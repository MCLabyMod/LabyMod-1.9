import com.google.common.base.Predicate;
import java.util.Set;

final class o$12
  implements Predicate<rr>
{
  o$12(String paramString, boolean paramBoolean) {}
  
  public boolean a(rr ☃)
  {
    if (☃ == null) {
      return false;
    }
    if ("".equals(this.a)) {
      return ☃.P().isEmpty() != this.b;
    }
    return ☃.P().contains(this.a) != this.b;
  }
}
