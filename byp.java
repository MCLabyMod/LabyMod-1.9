import com.google.common.collect.Lists;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class byp
{
  private static final AtomicInteger a = new AtomicInteger(0);
  private static final Logger b = LogManager.getLogger();
  
  public static class c
  {
    private List<byp.a> b = Lists.newArrayList();
    boolean a;
    
    public synchronized boolean a()
    {
      return this.a;
    }
    
    public synchronized void b()
    {
      this.a = false;
    }
    
    public synchronized List<byp.a> c()
    {
      return Collections.unmodifiableList(this.b);
    }
    
    public synchronized void a(String ☃, InetAddress ☃)
    {
      String ☃ = byq.a(☃);
      String ☃ = byq.b(☃);
      if (☃ == null) {
        return;
      }
      ☃ = ☃.getHostAddress() + ":" + ☃;
      
      boolean ☃ = false;
      for (byp.a ☃ : this.b) {
        if (☃.b().equals(☃))
        {
          ☃.c();
          ☃ = true;
          break;
        }
      }
      if (!☃)
      {
        this.b.add(new byp.a(☃, ☃));
        this.a = true;
      }
    }
  }
  
  public static class a
  {
    private String a;
    private String b;
    private long c;
    
    public a(String ☃, String ☃)
    {
      this.a = ☃;
      this.b = ☃;
      this.c = bcf.I();
    }
    
    public String a()
    {
      return this.a;
    }
    
    public String b()
    {
      return this.b;
    }
    
    public void c()
    {
      this.c = bcf.I();
    }
  }
  
  public static class b
    extends Thread
  {
    private final byp.c a;
    private final InetAddress b;
    private final MulticastSocket c;
    
    public b(byp.c ☃)
      throws IOException
    {
      super();
      this.a = ☃;
      setDaemon(true);
      
      this.c = new MulticastSocket(4445);
      this.b = InetAddress.getByName("224.0.2.60");
      this.c.setSoTimeout(5000);
      this.c.joinGroup(this.b);
    }
    
    public void run()
    {
      byte[] ☃ = new byte['Ѐ'];
      while (!isInterrupted())
      {
        DatagramPacket ☃ = new DatagramPacket(☃, ☃.length);
        try
        {
          this.c.receive(☃);
        }
        catch (SocketTimeoutException ☃)
        {
          continue;
        }
        catch (IOException ☃)
        {
          byp.b().error("Couldn't ping server", ☃);
          break;
        }
        String ☃ = new String(☃.getData(), ☃.getOffset(), ☃.getLength());
        byp.b().debug(☃.getAddress() + ": " + ☃);
        this.a.a(☃, ☃.getAddress());
      }
      try
      {
        this.c.leaveGroup(this.b);
      }
      catch (IOException localIOException1) {}
      this.c.close();
    }
  }
}
