import java.awt.image.BufferedImage;
import java.io.IOException;
import shadersmod.client.ShadersTex;

public class bux
  extends buw
{
  private final int[] f;
  private final int g;
  private final int h;
  private boolean shadersInitialized = false;
  
  public bux(BufferedImage bufferedImage)
  {
    this(bufferedImage.getWidth(), bufferedImage.getHeight());
    bufferedImage.getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), this.f, 0, bufferedImage.getWidth());
    d();
  }
  
  public bux(int textureWidth, int textureHeight)
  {
    this.g = textureWidth;
    this.h = textureHeight;
    
    this.f = new int[textureWidth * textureHeight * 3];
    if (Config.isShaders())
    {
      ShadersTex.initDynamicTexture(b(), textureWidth, textureHeight, this);
      this.shadersInitialized = true;
    }
    else
    {
      bvk.a(b(), textureWidth, textureHeight);
    }
  }
  
  public void a(bwg resourceManager)
    throws IOException
  {}
  
  public void d()
  {
    if (Config.isShaders())
    {
      if (!this.shadersInitialized)
      {
        ShadersTex.initDynamicTexture(b(), this.g, this.h, this);
        this.shadersInitialized = true;
      }
      ShadersTex.updateDynamicTexture(b(), this.f, this.g, this.h, this);
    }
    else
    {
      bvk.a(b(), this.f, this.g, this.h);
    }
  }
  
  public int[] e()
  {
    return this.f;
  }
}
