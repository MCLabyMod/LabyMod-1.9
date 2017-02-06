import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.ServerSocket;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class oe
{
  public static final ListeningExecutorService a = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool(new ThreadFactoryBuilder().setDaemon(true).setNameFormat("Downloader %d").build()));
  private static final AtomicInteger b = new AtomicInteger(0);
  private static final Logger c = LogManager.getLogger();
  
  public static String a(Map<String, Object> ☃)
  {
    StringBuilder ☃ = new StringBuilder();
    for (Map.Entry<String, Object> ☃ : ☃.entrySet())
    {
      if (☃.length() > 0) {
        ☃.append('&');
      }
      try
      {
        ☃.append(URLEncoder.encode((String)☃.getKey(), "UTF-8"));
      }
      catch (UnsupportedEncodingException ☃)
      {
        ☃.printStackTrace();
      }
      if (☃.getValue() != null)
      {
        ☃.append('=');
        try
        {
          ☃.append(URLEncoder.encode(☃.getValue().toString(), "UTF-8"));
        }
        catch (UnsupportedEncodingException ☃)
        {
          ☃.printStackTrace();
        }
      }
    }
    return ☃.toString();
  }
  
  public static String a(URL ☃, Map<String, Object> ☃, boolean ☃, Proxy ☃)
  {
    return a(☃, a(☃), ☃, ☃);
  }
  
  private static String a(URL ☃, String ☃, boolean ☃, Proxy ☃)
  {
    try
    {
      if (☃ == null) {
        ☃ = Proxy.NO_PROXY;
      }
      HttpURLConnection ☃ = (HttpURLConnection)☃.openConnection(☃);
      ☃.setRequestMethod("POST");
      ☃.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      
      ☃.setRequestProperty("Content-Length", "" + ☃.getBytes().length);
      ☃.setRequestProperty("Content-Language", "en-US");
      
      ☃.setUseCaches(false);
      ☃.setDoInput(true);
      ☃.setDoOutput(true);
      
      DataOutputStream ☃ = new DataOutputStream(☃.getOutputStream());
      ☃.writeBytes(☃);
      ☃.flush();
      ☃.close();
      
      BufferedReader ☃ = new BufferedReader(new InputStreamReader(☃.getInputStream()));
      
      StringBuffer ☃ = new StringBuffer();
      String ☃;
      while ((☃ = ☃.readLine()) != null)
      {
        ☃.append(☃);
        ☃.append('\r');
      }
      ☃.close();
      return ☃.toString();
    }
    catch (Exception ☃)
    {
      if (!☃) {
        c.error("Could not post to " + ☃, ☃);
      }
    }
    return "";
  }
  
  public static ListenableFuture<Object> a(final File ☃, final String ☃, final Map<String, String> ☃, final int ☃, op ☃, final Proxy ☃)
  {
    ListenableFuture<?> ☃ = a.submit(new Runnable()
    {
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
          URL ☃ = new URL(☃);
          ☃ = (HttpURLConnection)☃.openConnection(☃);
          float ☃ = 0.0F;
          float ☃ = ☃.entrySet().size();
          for (Map.Entry<String, String> ☃ : ☃.entrySet())
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
          if (☃.exists())
          {
            long ☃ = ☃.length();
            if (☃ == ☃)
            {
              if (this.a != null) {
                this.a.a();
              }
              return;
            }
            oe.b().warn("Deleting " + ☃ + " as it does not match what we currently have (" + ☃ + " vs our " + ☃ + ").");
            FileUtils.deleteQuietly(☃);
          }
          else if (☃.getParentFile() != null)
          {
            ☃.getParentFile().mkdirs();
          }
          ☃ = new DataOutputStream(new FileOutputStream(☃));
          if ((☃ > 0) && (☃ > ☃))
          {
            if (this.a != null) {
              this.a.a();
            }
            throw new IOException("Filesize is bigger than maximum allowed (file is " + ☃ + ", limit is " + ☃ + ")");
          }
          int ☃ = 0;
          while ((☃ = ☃.read(☃)) >= 0)
          {
            ☃ += ☃;
            if (this.a != null) {
              this.a.a((int)(☃ / ☃ * 100.0F));
            }
            if ((☃ > 0) && (☃ > ☃))
            {
              if (this.a != null) {
                this.a.a();
              }
              throw new IOException("Filesize was bigger than maximum allowed (got >= " + ☃ + ", limit was " + ☃ + ")");
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
    });
    return ☃;
  }
  
  public static int a()
    throws IOException
  {
    ServerSocket ☃ = null;
    ☃ = -1;
    try
    {
      ☃ = new ServerSocket(0);
      return ☃.getLocalPort();
    }
    finally
    {
      try
      {
        if (☃ != null) {
          ☃.close();
        }
      }
      catch (IOException localIOException1) {}
    }
  }
}
