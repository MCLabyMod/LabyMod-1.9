import java.awt.image.BufferedImage;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shadersmod.client.ShadersTex;

public class bvd
  extends buw
{
  private static final Logger g = ;
  protected final kk f;
  
  public bvd(kk textureResourceLocation)
  {
    this.f = textureResourceLocation;
  }
  
  public void a(bwg resourceManager)
    throws IOException
  {
    c();
    bwf iresource = null;
    try
    {
      iresource = resourceManager.a(this.f);
      BufferedImage bufferedimage = bvk.a(iresource.b());
      boolean flag = false;
      boolean flag1 = false;
      if (iresource.c()) {
        try
        {
          bxl texturemetadatasection = (bxl)iresource.a("texture");
          if (texturemetadatasection != null)
          {
            flag = texturemetadatasection.a();
            flag1 = texturemetadatasection.b();
          }
        }
        catch (RuntimeException runtimeexception)
        {
          g.warn("Failed reading metadata of: " + this.f, runtimeexception);
        }
      }
      if (Config.isShaders()) {
        ShadersTex.loadSimpleTexture(b(), bufferedimage, flag, flag1, resourceManager, this.f, getMultiTexID());
      } else {
        bvk.a(b(), bufferedimage, flag, flag1);
      }
    }
    finally
    {
      IOUtils.closeQuietly(iresource);
    }
  }
}
