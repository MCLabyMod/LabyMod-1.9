import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.IntBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.BufferUtils;

public class bcj
{
  private static final Logger a = ;
  private static final DateFormat b = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
  private static IntBuffer c;
  private static int[] d;
  
  public static eu a(File ☃, int ☃, int ☃, bnt ☃)
  {
    return a(☃, null, ☃, ☃, ☃);
  }
  
  public static eu a(File ☃, String ☃, int ☃, int ☃, bnt ☃)
  {
    try
    {
      File ☃ = new File(☃, "screenshots");
      ☃.mkdir();
      
      BufferedImage ☃ = a(☃, ☃, ☃);
      File ☃;
      File ☃;
      if (☃ == null) {
        ☃ = a(☃);
      } else {
        ☃ = new File(☃, ☃);
      }
      ImageIO.write(☃, "png", ☃);
      
      eu ☃ = new fa(☃.getName());
      ☃.b().a(new et(et.a.b, ☃.getAbsolutePath()));
      ☃.b().d(Boolean.valueOf(true));
      return new fb("screenshot.success", new Object[] { ☃ });
    }
    catch (Exception ☃)
    {
      a.warn("Couldn't save screenshot", ☃);
    }
    return new fb("screenshot.failure", tmp161_158);
  }
  
  public static BufferedImage a(int ☃, int ☃, bnt ☃)
  {
    if (bzg.j())
    {
      ☃ = ☃.a;
      ☃ = ☃.b;
    }
    int ☃ = ☃ * ☃;
    if ((c == null) || (c.capacity() < ☃))
    {
      c = BufferUtils.createIntBuffer(☃);
      d = new int[☃];
    }
    bni.g(3333, 1);
    bni.g(3317, 1);
    
    c.clear();
    if (bzg.j())
    {
      bni.i(☃.g);
      bni.a(3553, 0, 32993, 33639, c);
    }
    else
    {
      bni.a(0, 0, ☃, ☃, 32993, 33639, c);
    }
    c.get(d);
    
    bvk.a(d, ☃, ☃);
    
    BufferedImage ☃ = null;
    if (bzg.j())
    {
      ☃ = new BufferedImage(☃.c, ☃.d, 1);
      int ☃ = ☃.b - ☃.d;
      for (int ☃ = ☃; ☃ < ☃.b; ☃++) {
        for (int ☃ = 0; ☃ < ☃.c; ☃++) {
          ☃.setRGB(☃, ☃ - ☃, d[(☃ * ☃.a + ☃)]);
        }
      }
    }
    else
    {
      ☃ = new BufferedImage(☃, ☃, 1);
      ☃.setRGB(0, 0, ☃, ☃, d, 0, ☃);
    }
    return ☃;
  }
  
  private static File a(File ☃)
  {
    String ☃ = b.format(new Date()).toString();
    for (int ☃ = 1;; ☃++)
    {
      File ☃ = new File(☃, ☃ + (☃ == 1 ? "" : new StringBuilder().append("_").append(☃).toString()) + ".png");
      if (!☃.exists()) {
        return ☃;
      }
    }
  }
}
