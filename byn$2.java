import java.util.concurrent.Callable;
import net.minecraft.client.ClientBrandRetriever;

class byn$2
  implements Callable<String>
{
  byn$2(byn parambyn) {}
  
  public String a()
    throws Exception
  {
    String s = ClientBrandRetriever.getClientModName();
    if (!s.equals("vanilla")) {
      return "Definitely; Client brand changed to '" + s + "'";
    }
    s = this.this$0.getServerModName();
    return bcf.class.getSigners() == null ? "Very likely; Jar signature invalidated" : !s.equals("vanilla") ? "Definitely; Server brand changed to '" + s + "'" : "Probably not. Jar signature remains and both client + server brands are untouched.";
  }
}
