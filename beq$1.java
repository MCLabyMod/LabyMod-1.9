import com.google.common.base.Predicate;
import java.net.IDN;

class beq$1
  implements Predicate<String>
{
  beq$1(beq this$0) {}
  
  public boolean a(String p_apply_1_)
  {
    if (os.b(p_apply_1_)) {
      return true;
    }
    String[] astring = p_apply_1_.split(":");
    if (astring.length == 0) {
      return true;
    }
    try
    {
      String s = IDN.toASCII(astring[0]);
      return true;
    }
    catch (IllegalArgumentException var4) {}
    return false;
  }
}
