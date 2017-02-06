import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.Logger;

final class oe$1
  implements Runnable
{
  oe$1(op paramop, String paramString, Proxy paramProxy, Map paramMap, File paramFile, int paramInt) {}
  
  public void run()
  {
    HttpURLConnection ☃ = null;
    InputStream ☃ = null;
    OutputStream ☃ = null;
    if (this.a != null)
    {
      this.a.b("Downloading Resource Pack");
      this.a.c("Making Request...");
    }
    try
    {
      byte[] ☃ = new byte['က'];
      URL ☃ = new URL(this.b);
      ☃ = (HttpURLConnection)☃.openConnection(this.c);
      float ☃ = 0.0F;
      float ☃ = this.d.entrySet().size();
      for (Map.Entry<String, String> ☃ : this.d.entrySet())
      {
        ☃.setRequestProperty((String)☃.getKey(), (String)☃.getValue());
        if (this.a != null) {
          this.a.a((int)(++☃ / ☃ * 100.0F));
        }
      }
      ☃ = ☃.getInputStream();
      ☃ = ☃.getContentLength();
      int ☃ = ☃.getContentLength();
      if (this.a != null) {
        this.a.c(String.format("Downloading file (%.2f MB)...", new Object[] { Float.valueOf(☃ / 1000.0F / 1000.0F) }));
      }
      if (this.e.exists())
      {
        long ☃ = this.e.length();
        if (☃ == ☃)
        {
          if (this.a != null) {
            this.a.a();
          }
          return;
        }
        oe.b().warn("Deleting " + this.e + " as it does not match what we currently have (" + ☃ + " vs our " + ☃ + ").");
        FileUtils.deleteQuietly(this.e);
      }
      else if (this.e.getParentFile() != null)
      {
        this.e.getParentFile().mkdirs();
      }
      ☃ = new DataOutputStream(new FileOutputStream(this.e));
      if ((this.f > 0) && (☃ > this.f))
      {
        if (this.a != null) {
          this.a.a();
        }
        throw new IOException("Filesize is bigger than maximum allowed (file is " + ☃ + ", limit is " + this.f + ")");
      }
      int ☃ = 0;
      while ((☃ = ☃.read(☃)) >= 0)
      {
        ☃ += ☃;
        if (this.a != null) {
          this.a.a((int)(☃ / ☃ * 100.0F));
        }
        if ((this.f > 0) && (☃ > this.f))
        {
          if (this.a != null) {
            this.a.a();
          }
          throw new IOException("Filesize was bigger than maximum allowed (got >= " + ☃ + ", limit was " + this.f + ")");
        }
        if (Thread.interrupted())
        {
          oe.b().error("INTERRUPTED");
          if (this.a != null) {
            this.a.a();
          }
          return;
        }
        ☃.write(☃, 0, ☃);
      }
      if (this.a != null) {
        this.a.a();
      }
    }
    catch (Throwable ☃)
    {
      ☃.printStackTrace();
      if (☃ != null)
      {
        InputStream ☃ = ☃.getErrorStream();
        try
        {
          oe.b().error(IOUtils.toString(☃));
        }
        catch (IOException ☃)
        {
          ☃.printStackTrace();
        }
      }
      if (this.a != null) {
        this.a.a();
      }
    }
    finally
    {
      IOUtils.closeQuietly(☃);
      IOUtils.closeQuietly(☃);
    }
  }
}
