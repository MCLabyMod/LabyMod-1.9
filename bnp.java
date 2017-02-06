import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.ImageObserver;
import java.awt.image.WritableRaster;

public class bnp
  implements bnj
{
  private int[] a;
  private int b;
  private int c;
  
  public BufferedImage a(BufferedImage image)
  {
    if (image == null) {
      return null;
    }
    this.b = 64;
    this.c = 64;
    
    BufferedImage srcImage = image;
    int srcWidth = srcImage.getWidth();
    int srcHeight = srcImage.getHeight();
    
    int k = 1;
    while ((this.b < srcWidth) || (this.c < srcHeight))
    {
      this.b *= 2;
      this.c *= 2;
      k *= 2;
    }
    BufferedImage bufferedimage = new BufferedImage(this.b, this.c, 2);
    Graphics graphics = bufferedimage.getGraphics();
    graphics.drawImage(image, 0, 0, (ImageObserver)null);
    if (image.getHeight() == 32 * k)
    {
      graphics.drawImage(bufferedimage, 24 * k, 48 * k, 20 * k, 52 * k, 4 * k, 16 * k, 8 * k, 20 * k, (ImageObserver)null);
      graphics.drawImage(bufferedimage, 28 * k, 48 * k, 24 * k, 52 * k, 8 * k, 16 * k, 12 * k, 20 * k, (ImageObserver)null);
      graphics.drawImage(bufferedimage, 20 * k, 52 * k, 16 * k, 64 * k, 8 * k, 20 * k, 12 * k, 32 * k, (ImageObserver)null);
      graphics.drawImage(bufferedimage, 24 * k, 52 * k, 20 * k, 64 * k, 4 * k, 20 * k, 8 * k, 32 * k, (ImageObserver)null);
      graphics.drawImage(bufferedimage, 28 * k, 52 * k, 24 * k, 64 * k, 0 * k, 20 * k, 4 * k, 32 * k, (ImageObserver)null);
      graphics.drawImage(bufferedimage, 32 * k, 52 * k, 28 * k, 64 * k, 12 * k, 20 * k, 16 * k, 32 * k, (ImageObserver)null);
      graphics.drawImage(bufferedimage, 40 * k, 48 * k, 36 * k, 52 * k, 44 * k, 16 * k, 48 * k, 20 * k, (ImageObserver)null);
      graphics.drawImage(bufferedimage, 44 * k, 48 * k, 40 * k, 52 * k, 48 * k, 16 * k, 52 * k, 20 * k, (ImageObserver)null);
      graphics.drawImage(bufferedimage, 36 * k, 52 * k, 32 * k, 64 * k, 48 * k, 20 * k, 52 * k, 32 * k, (ImageObserver)null);
      graphics.drawImage(bufferedimage, 40 * k, 52 * k, 36 * k, 64 * k, 44 * k, 20 * k, 48 * k, 32 * k, (ImageObserver)null);
      graphics.drawImage(bufferedimage, 44 * k, 52 * k, 40 * k, 64 * k, 40 * k, 20 * k, 44 * k, 32 * k, (ImageObserver)null);
      graphics.drawImage(bufferedimage, 48 * k, 52 * k, 44 * k, 64 * k, 52 * k, 20 * k, 56 * k, 32 * k, (ImageObserver)null);
    }
    graphics.dispose();
    this.a = ((DataBufferInt)bufferedimage.getRaster().getDataBuffer()).getData();
    
    b(0 * k, 0 * k, 32 * k, 16 * k);
    a(32 * k, 0 * k, 64 * k, 32 * k);
    b(0 * k, 16 * k, 64 * k, 32 * k);
    a(0 * k, 32 * k, 16 * k, 48 * k);
    a(16 * k, 32 * k, 40 * k, 48 * k);
    a(40 * k, 32 * k, 56 * k, 48 * k);
    a(0 * k, 48 * k, 16 * k, 64 * k);
    b(16 * k, 48 * k, 48 * k, 64 * k);
    a(48 * k, 48 * k, 64 * k, 64 * k);
    return bufferedimage;
  }
  
  public void a() {}
  
  private void a(int x, int y, int width, int height)
  {
    if (!c(x, y, width, height)) {
      for (int i = x; i < width; i++) {
        for (int j = y; j < height; j++) {
          this.a[(i + j * this.b)] &= 0xFFFFFF;
        }
      }
    }
  }
  
  private void b(int x, int y, int width, int height)
  {
    for (int i = x; i < width; i++) {
      for (int j = y; j < height; j++) {
        this.a[(i + j * this.b)] |= 0xFF000000;
      }
    }
  }
  
  private boolean c(int x, int y, int width, int height)
  {
    for (int i = x; i < width; i++) {
      for (int j = y; j < height; j++)
      {
        int k = this.a[(i + j * this.b)];
        if ((k >> 24 & 0xFF) < 128) {
          return true;
        }
      }
    }
    return false;
  }
}
