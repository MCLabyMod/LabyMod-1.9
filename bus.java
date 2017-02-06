import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bus
{
  private static final Logger a = ;
  private static bus b;
  
  public static void a()
  {
    b = new bus();
  }
  
  public static bus b()
  {
    return b;
  }
  
  public void a(buo ☃)
  {
    ☃.f().b(☃);
    ☃.e().b(☃);
    
    bzg.e(☃.h());
  }
  
  public int c()
    throws ko
  {
    int ☃ = bzg.d();
    if (☃ <= 0) {
      throw new ko("Could not create shader program (returned program ID " + ☃ + ")");
    }
    return ☃;
  }
  
  public void b(buo ☃)
    throws IOException
  {
    ☃.f().a(☃);
    ☃.e().a(☃);
    
    bzg.f(☃.h());
    int ☃ = bzg.a(☃.h(), bzg.m);
    if (☃ == 0)
    {
      a.warn("Error encountered when linking program containing VS " + ☃.e().a() + " and FS " + ☃.f().a() + ". Log output:");
      a.warn(bzg.e(☃.h(), 32768));
    }
  }
}
