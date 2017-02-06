import com.google.common.collect.Lists;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bvb
  extends buw
{
  private static final Logger g = ;
  public final List<String> f;
  
  public bvb(String... ☃)
  {
    this.f = Lists.newArrayList(☃);
  }
  
  public void a(bwg ☃)
    throws IOException
  {
    c();
    
    BufferedImage ☃ = null;
    for (String ☃ : this.f)
    {
      bwf ☃ = null;
      try
      {
        if (☃ == null)
        {
          IOUtils.closeQuietly(☃);
        }
        else
        {
          ☃ = ☃.a(new kk(☃));
          BufferedImage ☃ = bvk.a(☃.b());
          if (☃ == null) {
            ☃ = new BufferedImage(☃.getWidth(), ☃.getHeight(), 2);
          }
          ☃.getGraphics().drawImage(☃, 0, 0, null);
        }
      }
      catch (IOException ☃)
      {
        g.error("Couldn't load layered image", ☃); return;
      }
      finally
      {
        IOUtils.closeQuietly(☃);
      }
    }
    bvk.a(b(), ☃);
  }
}
