import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bva
  extends buw
{
  private static final Logger f = ;
  private final kk g;
  private final List<String> h;
  private final List<act> i;
  
  public bva(kk ☃, List<String> ☃, List<act> ☃)
  {
    this.g = ☃;
    this.h = ☃;
    this.i = ☃;
  }
  
  public void a(bwg ☃)
    throws IOException
  {
    c();
    
    bwf ☃ = null;
    BufferedImage ☃;
    try
    {
      ☃ = ☃.a(this.g);
      BufferedImage ☃ = bvk.a(☃.b());
      
      int ☃ = ☃.getType();
      if (☃ == 0) {
        ☃ = 6;
      }
      ☃ = new BufferedImage(☃.getWidth(), ☃.getHeight(), ☃);
      Graphics ☃ = ☃.getGraphics();
      ☃.drawImage(☃, 0, 0, null);
      for (int ☃ = 0; (☃ < 17) && (☃ < this.h.size()) && (☃ < this.i.size()); ☃++)
      {
        bwf ☃ = null;
        try
        {
          String ☃ = (String)this.h.get(☃);
          axf ☃ = ((act)this.i.get(☃)).e();
          if (☃ == null)
          {
            IOUtils.closeQuietly(☃);
          }
          else
          {
            ☃ = ☃.a(new kk(☃));
            BufferedImage ☃ = bvk.a(☃.b());
            if ((☃.getWidth() != ☃.getWidth()) || (☃.getHeight() != ☃.getHeight()) || (☃.getType() != 6))
            {
              IOUtils.closeQuietly(☃);
            }
            else
            {
              for (int ☃ = 0; ☃ < ☃.getHeight(); ☃++) {
                for (int ☃ = 0; ☃ < ☃.getWidth(); ☃++)
                {
                  int ☃ = ☃.getRGB(☃, ☃);
                  if ((☃ & 0xFF000000) != 0)
                  {
                    int ☃ = (☃ & 0xFF0000) << 8 & 0xFF000000;
                    
                    int ☃ = ☃.getRGB(☃, ☃);
                    
                    int ☃ = on.d(☃, ☃.L) & 0xFFFFFF;
                    ☃.setRGB(☃, ☃, ☃ | ☃);
                  }
                }
              }
              ☃.getGraphics().drawImage(☃, 0, 0, null);
            }
          }
        }
        finally {}
      }
    }
    catch (IOException ☃)
    {
      f.error("Couldn't load layered image", ☃); return;
    }
    finally
    {
      IOUtils.closeQuietly(☃);
    }
    bvk.a(b(), ☃);
  }
}
