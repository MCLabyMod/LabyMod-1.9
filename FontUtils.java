import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class FontUtils
{
  public static void readCustomCharWidths(kk locationFontTexture, float[] charWidth)
  {
    String fontFileName = locationFontTexture.a();
    
    String suffix = ".png";
    if (!fontFileName.endsWith(suffix)) {
      return;
    }
    String fileName = fontFileName.substring(0, fontFileName.length() - suffix.length()) + ".properties";
    try
    {
      kk locProp = new kk(locationFontTexture.b(), fileName);
      InputStream in = Config.getResourceStream(Config.getResourceManager(), locProp);
      if (in == null) {
        return;
      }
      Config.log("Loading " + fileName);
      props = new Properties();
      props.load(in);
      Set keySet = props.keySet();
      for (iter = keySet.iterator(); iter.hasNext();)
      {
        String key = (String)iter.next();
        
        String prefix = "width.";
        if (key.startsWith(prefix))
        {
          String numStr = key.substring(prefix.length());
          int num = Config.parseInt(numStr, -1);
          if ((num >= 0) && (num < charWidth.length))
          {
            String value = props.getProperty(key);
            float width = Config.parseFloat(value, -1.0F);
            if (width >= 0.0F) {
              charWidth[num] = width;
            }
          }
        }
      }
    }
    catch (FileNotFoundException e) {}catch (IOException e)
    {
      Properties props;
      Iterator iter;
      e.printStackTrace();
    }
  }
  
  public static kk getHdFontLocation(kk fontLoc)
  {
    if (!Config.isCustomFonts()) {
      return fontLoc;
    }
    if (fontLoc == null) {
      return fontLoc;
    }
    String fontName = fontLoc.a();
    String texturesStr = "textures/";
    String mcpatcherStr = "mcpatcher/";
    if (!fontName.startsWith(texturesStr)) {
      return fontLoc;
    }
    fontName = fontName.substring(texturesStr.length());
    fontName = mcpatcherStr + fontName;
    
    kk fontLocHD = new kk(fontLoc.b(), fontName);
    if (Config.hasResource(Config.getResourceManager(), fontLocHD)) {
      return fontLocHD;
    }
    return fontLoc;
  }
}
