import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class byq
  extends Thread
{
  private static final AtomicInteger a = new AtomicInteger(0);
  private static final Logger b = LogManager.getLogger();
  private final String c;
  private final DatagramSocket d;
  private boolean e = true;
  private final String f;
  
  public byq(String ☃, String ☃)
    throws IOException
  {
    super("LanServerPinger #" + a.incrementAndGet());
    this.c = ☃;
    this.f = ☃;
    setDaemon(true);
    
    this.d = new DatagramSocket();
  }
  
  public void run()
  {
    String ☃ = a(this.c, this.f);
    byte[] ☃ = ☃.getBytes();
    while ((!isInterrupted()) && (this.e))
    {
      try
      {
        InetAddress ☃ = InetAddress.getByName("224.0.2.60");
        
        DatagramPacket ☃ = new DatagramPacket(☃, ☃.length, ☃, 4445);
        this.d.send(☃);
      }
      catch (IOException ☃)
      {
        b.warn("LanServerPinger: " + ☃.getMessage());
        break;
      }
      try
      {
        sleep(1500L);
      }
      catch (InterruptedException localInterruptedException) {}
    }
  }
  
  public void interrupt()
  {
    super.interrupt();
    
    this.e = false;
  }
  
  public static String a(String ☃, String ☃)
  {
    return "[MOTD]" + ☃ + "[/MOTD][AD]" + ☃ + "[/AD]";
  }
  
  public static String a(String ☃)
  {
    int ☃ = ☃.indexOf("[MOTD]");
    if (☃ < 0) {
      return "missing no";
    }
    int ☃ = ☃.indexOf("[/MOTD]", ☃ + "[MOTD]".length());
    if (☃ < ☃) {
      return "missing no";
    }
    return ☃.substring(☃ + "[MOTD]".length(), ☃);
  }
  
  public static String b(String ☃)
  {
    int ☃ = ☃.indexOf("[/MOTD]");
    if (☃ < 0) {
      return null;
    }
    int ☃ = ☃.indexOf("[/MOTD]", ☃ + "[/MOTD]".length());
    if (☃ >= 0) {
      return null;
    }
    int ☃ = ☃.indexOf("[AD]", ☃ + "[/MOTD]".length());
    if (☃ < 0) {
      return null;
    }
    int ☃ = ☃.indexOf("[/AD]", ☃ + "[AD]".length());
    if (☃ < ☃) {
      return null;
    }
    return ☃.substring(☃ + "[AD]".length(), ☃);
  }
}
