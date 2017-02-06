import java.awt.Dimension;
import java.awt.image.BufferedImage;

public class PlayerItemModel
{
  private Dimension textureSize = null;
  private boolean usePlayerTexture = false;
  private PlayerItemRenderer[] modelRenderers = new PlayerItemRenderer[0];
  private kk textureLocation = null;
  private BufferedImage textureImage = null;
  private bux texture = null;
  private kk locationMissing = new kk("textures/blocks/wool_colored_red.png");
  public static final int ATTACH_BODY = 0;
  public static final int ATTACH_HEAD = 1;
  public static final int ATTACH_LEFT_ARM = 2;
  public static final int ATTACH_RIGHT_ARM = 3;
  public static final int ATTACH_LEFT_LEG = 4;
  public static final int ATTACH_RIGHT_LEG = 5;
  public static final int ATTACH_CAPE = 6;
  
  public PlayerItemModel(Dimension textureSize, boolean usePlayerTexture, PlayerItemRenderer[] modelRenderers)
  {
    this.textureSize = textureSize;
    this.usePlayerTexture = usePlayerTexture;
    this.modelRenderers = modelRenderers;
  }
  
  public void render(bix modelBiped, bmq player, float scale, float partialTicks)
  {
    bvi textureManager = Config.getTextureManager();
    if (this.usePlayerTexture)
    {
      textureManager.a(player.o());
    }
    else if (this.textureLocation != null)
    {
      if ((this.texture == null) && (this.textureImage != null))
      {
        this.texture = new bux(this.textureImage);
        bcf.z().N().a(this.textureLocation, this.texture);
      }
      textureManager.a(this.textureLocation);
    }
    else
    {
      textureManager.a(this.locationMissing);
    }
    for (int i = 0; i < this.modelRenderers.length; i++)
    {
      PlayerItemRenderer pir = this.modelRenderers[i];
      bni.G();
      if (player.aK()) {
        bni.c(0.0F, 0.2F, 0.0F);
      }
      pir.render(modelBiped, scale);
      
      bni.H();
    }
  }
  
  public static bkm getAttachModel(bix modelBiped, int attachTo)
  {
    switch (attachTo)
    {
    case 0: 
      return modelBiped.g;
    case 1: 
      return modelBiped.e;
    case 2: 
      return modelBiped.i;
    case 3: 
      return modelBiped.h;
    case 4: 
      return modelBiped.k;
    case 5: 
      return modelBiped.j;
    }
    return null;
  }
  
  public BufferedImage getTextureImage()
  {
    return this.textureImage;
  }
  
  public void setTextureImage(BufferedImage textureImage)
  {
    this.textureImage = textureImage;
  }
  
  public bux getTexture()
  {
    return this.texture;
  }
  
  public kk getTextureLocation()
  {
    return this.textureLocation;
  }
  
  public void setTextureLocation(kk textureLocation)
  {
    this.textureLocation = textureLocation;
  }
  
  public boolean isUsePlayerTexture()
  {
    return this.usePlayerTexture;
  }
}
