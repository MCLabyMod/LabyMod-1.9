package de.labystudio.capes.downloader;

import bcf;
import bnj;
import bvd;
import bvk;
import bwg;
import de.labystudio.capes.CapeCallback;
import de.labystudio.utils.Debug;
import de.labystudio.utils.Debug.EnumDebugMode;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;
import javax.imageio.ImageIO;
import kk;
import org.apache.commons.io.FileUtils;

public class ThreadDownloadCapeData
  extends bvd
{
  private static final AtomicInteger threadDownloadCounter = new AtomicInteger(0);
  private final File cacheFile;
  private final String imageUrl;
  private final bnj imageBuffer;
  private BufferedImage bufferedImage;
  private Thread imageThread;
  private boolean textureUploaded;
  private static final String __OBFID = "CL_00001049";
  public Boolean imageFound = null;
  public boolean pipeline = false;
  private CapeCallback callBack;
  
  public ThreadDownloadCapeData(File p_i1049_1_, String p_i1049_2_, kk p_i1049_3_, bnj p_i1049_4_, CapeCallback callBack)
  {
    super(p_i1049_3_);
    this.cacheFile = p_i1049_1_;
    this.imageUrl = p_i1049_2_;
    this.imageBuffer = p_i1049_4_;
    this.callBack = callBack;
  }
  
  private void checkTextureUploaded()
  {
    if ((!this.textureUploaded) && 
      (this.bufferedImage != null))
    {
      this.textureUploaded = true;
      if (this.f != null) {
        c();
      }
      bvk.a(super.b(), this.bufferedImage);
    }
  }
  
  public int b()
  {
    checkTextureUploaded();
    return super.b();
  }
  
  public void setBufferedImage(BufferedImage p_147641_1_)
  {
    this.bufferedImage = p_147641_1_;
    if (this.imageBuffer != null) {
      this.imageBuffer.a();
    }
    this.imageFound = Boolean.valueOf(this.bufferedImage != null);
  }
  
  public void a(bwg p_110551_1_)
    throws IOException
  {
    if ((this.bufferedImage == null) && (this.f != null)) {
      super.a(p_110551_1_);
    }
    if (this.imageThread == null) {
      if ((this.cacheFile != null) && (this.cacheFile.isFile()))
      {
        Debug.debug(Debug.EnumDebugMode.CAPES, "Loading http texture from local cache (" + this.cacheFile + ")");
        try
        {
          this.bufferedImage = ImageIO.read(this.cacheFile);
          if (this.imageBuffer != null) {
            setBufferedImage(this.imageBuffer.a(this.bufferedImage));
          }
          this.imageFound = Boolean.valueOf(this.bufferedImage != null);
        }
        catch (IOException var3)
        {
          Debug.debug(Debug.EnumDebugMode.CAPES, "Couldn't load skin " + this.cacheFile);
          loadTextureFromServer();
        }
      }
      else
      {
        loadTextureFromServer();
      }
    }
  }
  
  protected void loadTextureFromServer()
  {
    this.imageThread = new Thread("Texture Downloader #" + threadDownloadCounter.incrementAndGet())
    {
      private static final String __OBFID = "CL_00001050";
      
      public void run()
      {
        HttpURLConnection var1 = null;
        Debug.debug(Debug.EnumDebugMode.CAPES, "Downloading http texture from " + ThreadDownloadCapeData.this.imageUrl + " to " + ThreadDownloadCapeData.this.cacheFile);
        try
        {
          var1 = (HttpURLConnection)new URL(ThreadDownloadCapeData.this.imageUrl).openConnection(bcf.z().M());
          var1.setDoInput(true);
          var1.setDoOutput(false);
          var1.connect();
          if (var1.getResponseCode() / 100 == 2)
          {
            BufferedImage var2;
            BufferedImage var2;
            if (ThreadDownloadCapeData.this.cacheFile != null)
            {
              FileUtils.copyInputStreamToFile(var1.getInputStream(), ThreadDownloadCapeData.this.cacheFile);
              var2 = ImageIO.read(ThreadDownloadCapeData.this.cacheFile);
            }
            else
            {
              var2 = bvk.a(var1.getInputStream());
            }
            if (ThreadDownloadCapeData.this.imageBuffer != null) {
              var2 = ThreadDownloadCapeData.this.imageBuffer.a(var2);
            }
            ThreadDownloadCapeData.this.setBufferedImage(var2);
          }
          else if (var1.getErrorStream() != null)
          {
            Debug.debug(Debug.EnumDebugMode.CAPES, var1.getErrorStream().toString());
          }
        }
        catch (Exception var6)
        {
          Debug.debug(Debug.EnumDebugMode.CAPES, "Couldn't download http texture: " + var6.getClass().getName() + ": " + var6.getMessage());
        }
        finally
        {
          if (var1 != null) {
            var1.disconnect();
          }
          ThreadDownloadCapeData.this.imageFound = Boolean.valueOf(ThreadDownloadCapeData.this.bufferedImage != null);
          if (ThreadDownloadCapeData.this.imageFound.booleanValue()) {
            ThreadDownloadCapeData.this.callBack.done();
          } else {
            ThreadDownloadCapeData.this.callBack.failed("Texture not found");
          }
        }
      }
    };
    this.imageThread.setDaemon(true);
    this.imageThread.start();
  }
}
