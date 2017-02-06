import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import org.apache.commons.io.FilenameUtils;

public class CapeUtils
{
  public static void downloadCape(bmq player)
  {
    String username = player.getNameClear();
    if ((username != null) && (!username.isEmpty()))
    {
      String ofCapeUrl = "http://s.optifine.net/capes/" + username + ".png";
      
      String mptHash = FilenameUtils.getBaseName(ofCapeUrl);
      final kk rl = new kk("capeof/" + mptHash);
      bvi textureManager = bcf.z().N();
      
      bvj tex = textureManager.b(rl);
      if (tex != null) {
        if ((tex instanceof buy))
        {
          buy tdid = (buy)tex;
          if (tdid.imageFound != null)
          {
            if (tdid.imageFound.booleanValue()) {
              player.setLocationOfCape(rl);
            }
            return;
          }
        }
      }
      bmq thePlayer = player;
      bnj iib = new bnj()
      {
        bnp ibd = new bnp();
        
        public BufferedImage a(BufferedImage var1)
        {
          return CapeUtils.parseCape(var1);
        }
        
        public void a()
        {
          this.val$thePlayer.setLocationOfCape(rl);
        }
      };
      buy textureCape = new buy(null, ofCapeUrl, null, iib);
      textureCape.pipeline = true;
      textureManager.a(rl, textureCape);
    }
  }
  
  public static BufferedImage parseCape(BufferedImage img)
  {
    int imageWidth = 64;
    int imageHeight = 32;
    
    BufferedImage srcImg = img;
    int srcWidth = srcImg.getWidth();
    int srcHeight = srcImg.getHeight();
    while ((imageWidth < srcWidth) || (imageHeight < srcHeight))
    {
      imageWidth *= 2;
      imageHeight *= 2;
    }
    BufferedImage imgNew = new BufferedImage(imageWidth, imageHeight, 2);
    Graphics g = imgNew.getGraphics();
    g.drawImage(img, 0, 0, (ImageObserver)null);
    g.dispose();
    return imgNew;
  }
}
