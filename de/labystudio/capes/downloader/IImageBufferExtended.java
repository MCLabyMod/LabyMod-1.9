package de.labystudio.capes.downloader;

import de.labystudio.capes.EnumCapePriority;
import java.awt.image.BufferedImage;
import kk;

public abstract interface IImageBufferExtended
{
  public abstract BufferedImage parseUserCape(BufferedImage paramBufferedImage);
  
  public abstract void capeAvailable(EnumCapePriority paramEnumCapePriority);
  
  public abstract kk capeNotFound(int paramInt);
}
