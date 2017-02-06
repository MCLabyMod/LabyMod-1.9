import java.io.OutputStream;
import java.io.PrintStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ks
  extends PrintStream
{
  private static final Logger a = ;
  private final String b;
  
  public ks(String ☃, OutputStream ☃)
  {
    super(☃);
    this.b = ☃;
  }
  
  public void println(String ☃)
  {
    a(☃);
  }
  
  public void println(Object ☃)
  {
    a(String.valueOf(☃));
  }
  
  private void a(String ☃)
  {
    StackTraceElement[] ☃ = Thread.currentThread().getStackTrace();
    StackTraceElement ☃ = ☃[Math.min(3, ☃.length)];
    a.info("[{}]@.({}:{}): {}", new Object[] { this.b, ☃.getFileName(), Integer.valueOf(☃.getLineNumber()), ☃ });
  }
}
