import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import org.apache.logging.log4j.Logger;

public class g
{
  public static g.a a()
  {
    String ☃ = System.getProperty("os.name").toLowerCase();
    if (☃.contains("win")) {
      return g.a.c;
    }
    if (☃.contains("mac")) {
      return g.a.d;
    }
    if (☃.contains("solaris")) {
      return g.a.b;
    }
    if (☃.contains("sunos")) {
      return g.a.b;
    }
    if (☃.contains("linux")) {
      return g.a.a;
    }
    if (☃.contains("unix")) {
      return g.a.a;
    }
    return g.a.e;
  }
  
  public static <V> V a(FutureTask<V> ☃, Logger ☃)
  {
    try
    {
      ☃.run();
      return (V)☃.get();
    }
    catch (ExecutionException ☃)
    {
      ☃.fatal("Error executing task", ☃);
    }
    catch (InterruptedException ☃)
    {
      ☃.fatal("Error executing task", ☃);
    }
    return null;
  }
  
  public static <T> T a(List<T> ☃)
  {
    return (T)☃.get(☃.size() - 1);
  }
  
  public static enum a
  {
    private a() {}
  }
}
