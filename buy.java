import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class buy
  extends bvd
{
  private static final Logger g = ;
  private static final AtomicInteger h = new AtomicInteger(0);
  private final File i;
  private final String j;
  private final bnj k;
  private BufferedImage l;
  private Thread m;
  private boolean n;
  public Boolean imageFound = null;
  public boolean pipeline = false;
  
  public buy(File cacheFileIn, String imageUrlIn, kk textureResourceLocation, bnj imageBufferIn)
  {
    super(textureResourceLocation);
    this.i = cacheFileIn;
    this.j = imageUrlIn;
    this.k = imageBufferIn;
  }
  
  private void g()
  {
    if (!this.n) {
      if (this.l != null)
      {
        this.n = true;
        if (this.f != null) {
          c();
        }
        bvk.a(super.b(), this.l);
      }
    }
  }
  
  public int b()
  {
    g();
    return super.b();
  }
  
  public void a(BufferedImage bufferedImageIn)
  {
    this.l = bufferedImageIn;
    if (this.k != null) {
      this.k.a();
    }
    this.imageFound = Boolean.valueOf(this.l != null);
  }
  
  public void a(bwg resourceManager)
    throws IOException
  {
    if ((this.l == null) && (this.f != null)) {
      super.a(resourceManager);
    }
    if (this.m == null) {
      if ((this.i != null) && (this.i.isFile()))
      {
        g.debug("Loading http texture from local cache ({})", new Object[] { this.i });
        try
        {
          this.l = ImageIO.read(this.i);
          if (this.k != null) {
            a(this.k.a(this.l));
          }
          this.imageFound = Boolean.valueOf(this.l != null);
        }
        catch (IOException ioexception)
        {
          g.error("Couldn't load skin " + this.i, ioexception);
          d();
        }
      }
      else
      {
        d();
      }
    }
  }
  
  protected void d()
  {
    this.m = new Thread("Texture Downloader #" + h.incrementAndGet())
    {
      public void run()
      {
        HttpURLConnection httpurlconnection = null;
        buy.f().debug("Downloading http texture from {} to {}", new Object[] { buy.a(buy.this), buy.b(buy.this) });
        if (buy.this.shouldPipeline())
        {
          buy.this.loadPipelined();
          return;
        }
        try
        {
          httpurlconnection = (HttpURLConnection)new URL(buy.a(buy.this)).openConnection(bcf.z().M());
          httpurlconnection.setDoInput(true);
          httpurlconnection.setDoOutput(false);
          httpurlconnection.connect();
          if (httpurlconnection.getResponseCode() / 100 == 2)
          {
            BufferedImage bufferedimage;
            BufferedImage bufferedimage;
            if (buy.b(buy.this) != null)
            {
              FileUtils.copyInputStreamToFile(httpurlconnection.getInputStream(), buy.b(buy.this));
              bufferedimage = ImageIO.read(buy.b(buy.this));
            }
            else
            {
              bufferedimage = bvk.a(httpurlconnection.getInputStream());
            }
            if (buy.c(buy.this) != null) {
              bufferedimage = buy.c(buy.this).a(bufferedimage);
            }
            buy.this.a(bufferedimage);
          }
          else if (httpurlconnection.getErrorStream() != null)
          {
            Config.readAll(httpurlconnection.getErrorStream());
          }
        }
        catch (Exception exception)
        {
          buy.f().error("Couldn't download http texture: " + exception.getClass().getName() + ": " + exception.getMessage());
        }
        finally
        {
          if (httpurlconnection != null) {
            httpurlconnection.disconnect();
          }
          buy.this.imageFound = Boolean.valueOf(buy.this.l != null);
        }
      }
    };
    this.m.setDaemon(true);
    this.m.start();
  }
  
  private boolean shouldPipeline()
  {
    if (!this.pipeline) {
      return false;
    }
    Proxy proxy = bcf.z().M();
    if ((proxy.type() != Proxy.Type.DIRECT) && (proxy.type() != Proxy.Type.SOCKS)) {
      return false;
    }
    if (!this.j.startsWith("http://")) {
      return false;
    }
    return true;
  }
  
  private void loadPipelined()
  {
    try
    {
      HttpRequest req = HttpPipeline.makeRequest(this.j, bcf.z().M());
      HttpResponse resp = HttpPipeline.executeRequest(req);
      if (resp.getStatus() / 100 == 2)
      {
        byte[] body = resp.getBody();
        ByteArrayInputStream bais = new ByteArrayInputStream(body);
        BufferedImage var2;
        BufferedImage var2;
        if (this.i != null)
        {
          FileUtils.copyInputStreamToFile(bais, this.i);
          var2 = ImageIO.read(this.i);
        }
        else
        {
          var2 = bvk.a(bais);
        }
        if (this.k != null) {
          var2 = this.k.a(var2);
        }
        a(var2);
      }
    }
    catch (Exception var6)
    {
      g.error("Couldn't download http texture: " + var6.getClass().getName() + ": " + var6.getMessage());
    }
    finally
    {
      this.imageFound = Boolean.valueOf(this.l != null);
    }
  }
}
