import java.util.concurrent.Callable;
import org.lwjgl.Sys;

class bcf$6
  implements Callable<String>
{
  bcf$6(bcf this$0) {}
  
  public String a()
  {
    return Sys.getVersion();
  }
}
