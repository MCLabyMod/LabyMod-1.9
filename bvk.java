import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.IntBuffer;
import javax.imageio.ImageIO;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bvk
{
  private static final Logger c = ;
  private static final IntBuffer d = bce.f(4194304);
  public static final bux a = new bux(16, 16);
  public static final int[] b = a.e();
  private static final float[] e;
  
  private static float d(int p_188543_0_)
  {
    return e[(p_188543_0_ & 0xFF)];
  }
  
  public static int a()
  {
    return bni.A();
  }
  
  public static void a(int textureId)
  {
    bni.h(textureId);
  }
  
  public static int a(int p_110987_0_, BufferedImage p_110987_1_)
  {
    return a(p_110987_0_, p_110987_1_, false, false);
  }
  
  public static void a(int textureId, int[] p_110988_1_, int p_110988_2_, int p_110988_3_)
  {
    b(textureId);
    a(0, p_110988_1_, p_110988_2_, p_110988_3_, 0, 0, false, false, false);
  }
  
  public static int[][] a(int p_147949_0_, int p_147949_1_, int[][] p_147949_2_)
  {
    int[][] aint = new int[p_147949_0_ + 1][];
    aint[0] = p_147949_2_[0];
    if (p_147949_0_ > 0)
    {
      boolean flag = false;
      for (int i = 0; i < p_147949_2_.length; i++) {
        if (p_147949_2_[0][i] >> 24 == 0)
        {
          flag = true;
          break;
        }
      }
      for (int l1 = 1; l1 <= p_147949_0_; l1++) {
        if (p_147949_2_[l1] != null)
        {
          aint[l1] = p_147949_2_[l1];
        }
        else
        {
          int[] aint1 = aint[(l1 - 1)];
          int[] aint2 = new int[aint1.length >> 2];
          int j = p_147949_1_ >> l1;
          int k = aint2.length / j;
          int l = j << 1;
          for (int i1 = 0; i1 < j; i1++) {
            for (int j1 = 0; j1 < k; j1++)
            {
              int k1 = 2 * (i1 + j1 * l);
              aint2[(i1 + j1 * j)] = a(aint1[(k1 + 0)], aint1[(k1 + 1)], aint1[(k1 + 0 + l)], aint1[(k1 + 1 + l)], flag);
            }
          }
          aint[l1] = aint2;
        }
      }
    }
    return aint;
  }
  
  private static int a(int p_147943_0_, int p_147943_1_, int p_147943_2_, int p_147943_3_, boolean p_147943_4_)
  {
    return Mipmaps.alphaBlend(p_147943_0_, p_147943_1_, p_147943_2_, p_147943_3_);
  }
  
  private static int a(int p_147944_0_, int p_147944_1_, int p_147944_2_, int p_147944_3_, int p_147944_4_)
  {
    float f = d(p_147944_0_ >> p_147944_4_);
    float f1 = d(p_147944_1_ >> p_147944_4_);
    float f2 = d(p_147944_2_ >> p_147944_4_);
    float f3 = d(p_147944_3_ >> p_147944_4_);
    float f4 = (float)(float)Math.pow((f + f1 + f2 + f3) * 0.25D, 0.45454545454545453D);
    return (int)(f4 * 255.0D);
  }
  
  public static void a(int[][] p_147955_0_, int p_147955_1_, int p_147955_2_, int p_147955_3_, int p_147955_4_, boolean p_147955_5_, boolean p_147955_6_)
  {
    for (int i = 0; i < p_147955_0_.length; i++)
    {
      int[] aint = p_147955_0_[i];
      a(i, aint, p_147955_1_ >> i, p_147955_2_ >> i, p_147955_3_ >> i, p_147955_4_ >> i, p_147955_5_, p_147955_6_, p_147955_0_.length > 1);
    }
  }
  
  private static void a(int p_147947_0_, int[] p_147947_1_, int p_147947_2_, int p_147947_3_, int p_147947_4_, int p_147947_5_, boolean p_147947_6_, boolean p_147947_7_, boolean p_147947_8_)
  {
    int i = 4194304 / p_147947_2_;
    a(p_147947_6_, p_147947_8_);
    a(p_147947_7_);
    int l;
    for (int j = 0; j < p_147947_2_ * p_147947_3_; j += p_147947_2_ * l)
    {
      int k = j / p_147947_2_;
      l = Math.min(i, p_147947_3_ - k);
      int i1 = p_147947_2_ * l;
      b(p_147947_1_, j, i1);
      bni.b(3553, p_147947_0_, p_147947_4_, p_147947_5_ + k, p_147947_2_, l, 32993, 33639, d);
    }
  }
  
  public static int a(int p_110989_0_, BufferedImage p_110989_1_, boolean p_110989_2_, boolean p_110989_3_)
  {
    a(p_110989_0_, p_110989_1_.getWidth(), p_110989_1_.getHeight());
    return a(p_110989_0_, p_110989_1_, 0, 0, p_110989_2_, p_110989_3_);
  }
  
  public static void a(int p_110991_0_, int p_110991_1_, int p_110991_2_)
  {
    a(p_110991_0_, 0, p_110991_1_, p_110991_2_);
  }
  
  public static void a(int p_180600_0_, int p_180600_1_, int p_180600_2_, int p_180600_3_)
  {
    a(p_180600_0_);
    b(p_180600_0_);
    if (p_180600_1_ >= 0)
    {
      bni.b(3553, 33085, p_180600_1_);
      bni.b(3553, 33082, 0);
      bni.b(3553, 33083, p_180600_1_);
      bni.b(3553, 34049, 0.0F);
    }
    for (int i = 0; i <= p_180600_1_; i++) {
      bni.a(3553, i, 6408, p_180600_2_ >> i, p_180600_3_ >> i, 0, 32993, 33639, (IntBuffer)null);
    }
  }
  
  public static int a(int textureId, BufferedImage p_110995_1_, int p_110995_2_, int p_110995_3_, boolean p_110995_4_, boolean p_110995_5_)
  {
    b(textureId);
    a(p_110995_1_, p_110995_2_, p_110995_3_, p_110995_4_, p_110995_5_);
    return textureId;
  }
  
  private static void a(BufferedImage p_110993_0_, int p_110993_1_, int p_110993_2_, boolean p_110993_3_, boolean p_110993_4_)
  {
    int i = p_110993_0_.getWidth();
    int j = p_110993_0_.getHeight();
    int k = 4194304 / i;
    int[] aint = new int[k * i];
    b(p_110993_3_);
    a(p_110993_4_);
    for (int l = 0; l < i * j; l += i * k)
    {
      int i1 = l / i;
      int j1 = Math.min(k, j - i1);
      int k1 = i * j1;
      p_110993_0_.getRGB(0, i1, i, j1, aint, 0, i);
      a(aint, k1);
      bni.b(3553, 0, p_110993_1_, p_110993_2_ + i1, i, j1, 32993, 33639, d);
    }
  }
  
  public static void a(boolean p_110997_0_)
  {
    if (p_110997_0_)
    {
      bni.b(3553, 10242, 10496);
      bni.b(3553, 10243, 10496);
    }
    else
    {
      bni.b(3553, 10242, 10497);
      bni.b(3553, 10243, 10497);
    }
  }
  
  private static void b(boolean p_147951_0_)
  {
    a(p_147951_0_, false);
  }
  
  public static void a(boolean p_147954_0_, boolean p_147954_1_)
  {
    if (p_147954_0_)
    {
      bni.b(3553, 10241, p_147954_1_ ? 9987 : 9729);
      bni.b(3553, 10240, 9729);
    }
    else
    {
      bni.b(3553, 10241, p_147954_1_ ? 9986 : 9728);
      bni.b(3553, 10240, 9728);
    }
  }
  
  private static void a(int[] p_110990_0_, int p_110990_1_)
  {
    b(p_110990_0_, 0, p_110990_1_);
  }
  
  private static void b(int[] p_110994_0_, int p_110994_1_, int p_110994_2_)
  {
    int[] aint = p_110994_0_;
    if (bcf.z().u.e) {
      aint = a(p_110994_0_);
    }
    d.clear();
    d.put(aint, p_110994_1_, p_110994_2_);
    d.position(0).limit(p_110994_2_);
  }
  
  static void b(int p_94277_0_)
  {
    bni.i(p_94277_0_);
  }
  
  public static int[] a(bwg resourceManager, kk imageLocation)
    throws IOException
  {
    bwf iresource = null;
    int[] aint1;
    try
    {
      iresource = resourceManager.a(imageLocation);
      BufferedImage bufferedimage = a(iresource.b());
      int i = bufferedimage.getWidth();
      int j = bufferedimage.getHeight();
      int[] aint = new int[i * j];
      bufferedimage.getRGB(0, 0, i, j, aint, 0, i);
      aint1 = aint;
    }
    finally
    {
      IOUtils.closeQuietly(iresource);
    }
    return aint1;
  }
  
  public static BufferedImage a(InputStream imageStream)
    throws IOException
  {
    BufferedImage bufferedimage;
    try
    {
      bufferedimage = ImageIO.read(imageStream);
    }
    finally
    {
      IOUtils.closeQuietly(imageStream);
    }
    return bufferedimage;
  }
  
  public static int[] a(int[] p_110985_0_)
  {
    int[] aint = new int[p_110985_0_.length];
    for (int i = 0; i < p_110985_0_.length; i++) {
      aint[i] = c(p_110985_0_[i]);
    }
    return aint;
  }
  
  public static int c(int p_177054_0_)
  {
    int i = p_177054_0_ >> 24 & 0xFF;
    int j = p_177054_0_ >> 16 & 0xFF;
    int k = p_177054_0_ >> 8 & 0xFF;
    int l = p_177054_0_ & 0xFF;
    int i1 = (j * 30 + k * 59 + l * 11) / 100;
    int j1 = (j * 30 + k * 70) / 100;
    int k1 = (j * 30 + l * 70) / 100;
    return i << 24 | i1 << 16 | j1 << 8 | k1;
  }
  
  public static void a(int[] p_147953_0_, int p_147953_1_, int p_147953_2_)
  {
    int[] aint = new int[p_147953_1_];
    int i = p_147953_2_ / 2;
    for (int j = 0; j < i; j++)
    {
      System.arraycopy(p_147953_0_, j * p_147953_1_, aint, 0, p_147953_1_);
      System.arraycopy(p_147953_0_, (p_147953_2_ - 1 - j) * p_147953_1_, p_147953_0_, j * p_147953_1_, p_147953_1_);
      System.arraycopy(aint, 0, p_147953_0_, (p_147953_2_ - 1 - j) * p_147953_1_, p_147953_1_);
    }
  }
  
  static
  {
    int i = -16777216;
    int j = -524040;
    int[] aint = { -524040, -524040, -524040, -524040, -524040, -524040, -524040, -524040 };
    int[] aint1 = { -16777216, -16777216, -16777216, -16777216, -16777216, -16777216, -16777216, -16777216 };
    int k = aint.length;
    for (int l = 0; l < 16; l++)
    {
      System.arraycopy(l < k ? aint : aint1, 0, b, 16 * l, k);
      System.arraycopy(l < k ? aint1 : aint, 0, b, 16 * l + k, k);
    }
    a.d();
    e = new float['Ā'];
    for (i = 0; i < e.length; i++) {
      e[i] = ((float)Math.pow(i / 255.0F, 2.2D));
    }
  }
  
  private static final int[] f = new int[4];
}
