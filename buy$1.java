import java.awt.image.BufferedImage;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;

class buy$1
  extends Thread
{
  buy$1(buy parambuy, String x0)
  {
    super(x0);
  }
  
  public void run()
  {
    HttpURLConnection httpurlconnection = null;
    buy.f().debug("Downloading http texture from {} to {}", new Object[] { buy.a(this.this$0), buy.b(this.this$0) });
    if (buy.access$300(this.this$0))
    {
      buy.access$400(this.this$0);
      return;
    }
    try
    {
      httpurlconnection = (HttpURLConnection)new URL(buy.a(this.this$0)).openConnection(bcf.z().M());
      httpurlconnection.setDoInput(true);
      httpurlconnection.setDoOutput(false);
      httpurlconnection.connect();
      if (httpurlconnection.getResponseCode() / 100 == 2)
      {
        BufferedImage bufferedimage;
        BufferedImage bufferedimage;
        if (buy.b(this.this$0) != null)
        {
          FileUtils.copyInputStreamToFile(httpurlconnection.getInputStream(), buy.b(this.this$0));
          bufferedimage = ImageIO.read(buy.b(this.this$0));
        }
        else
        {
          bufferedimage = bvk.a(httpurlconnection.getInputStream());
        }
        if (buy.c(this.this$0) != null) {
          bufferedimage = buy.c(this.this$0).a(bufferedimage);
        }
        this.this$0.a(bufferedimage);
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
      this.this$0.imageFound = Boolean.valueOf(buy.access$600(this.this$0) != null);
    }
  }
}
