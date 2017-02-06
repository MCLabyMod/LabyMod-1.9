import java.util.concurrent.Callable;
import net.minecraft.client.ClientBrandRetriever;

class bcf$10
  implements Callable<String>
{
  bcf$10(bcf this$0) {}
  
  public String call()
    throws Exception
  {
    String s = ClientBrandRetriever.getClientModName();
    return bcf.class.getSigners() == null ? "Very likely; Jar signature invalidated" : !s.equals("vanilla") ? "Definitely; Client brand changed to '" + s + "'" : "Probably not. Jar signature remains and client brand is untouched.";
  }
}
