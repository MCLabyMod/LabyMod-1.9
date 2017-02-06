import java.util.List;
import java.util.concurrent.Callable;

class bcf$12
  implements Callable<String>
{
  bcf$12(bcf this$0) {}
  
  public String a()
    throws Exception
  {
    StringBuilder stringbuilder = new StringBuilder();
    for (String s : this.this$0.u.k)
    {
      if (stringbuilder.length() > 0) {
        stringbuilder.append(", ");
      }
      stringbuilder.append(s);
      if (this.this$0.u.l.contains(s)) {
        stringbuilder.append(" (incompatible)");
      }
    }
    return stringbuilder.toString();
  }
}
