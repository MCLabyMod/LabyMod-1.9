package mods.directionhud;

import de.labystudio.modapi.ModAPI;
import de.labystudio.modapi.Module;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectionHUD
  extends Module
{
  public static Configuration config = null;
  public static File configFile = null;
  public static boolean optionEnable = true;
  public static String optionPositionMode = "TOPCENTER";
  public static String optionMarkerColor = "c";
  public static boolean showWhileChat = true;
  public static int optionCustomX = 1;
  public static int optionCustomY = 1;
  
  public void onEnable()
  {
    updateConfig(new File("ToggleSneak_directionhud.cfg"), true);
    
    ModAPI.registerListener(new DirectionHUDEvents());
    ModAPI.addSettingsButton("DirectionHUD", new GuiDHConfig());
  }
  
  public static void reloadConfig()
  {
    updateConfig(configFile, true);
  }
  
  public static void saveConfig()
  {
    updateConfig(configFile, false);
  }
  
  public static void updateConfig(File cfgFile, boolean isLoading)
  {
    if (isLoading)
    {
      config = new Configuration(cfgFile);
      config.load();
      configFile = cfgFile;
    }
    Property property = config.get("optionEnable", Boolean.valueOf(optionEnable));
    if (isLoading) {
      optionEnable = property.getBoolean();
    } else {
      property.set(optionEnable + "");
    }
    property = config.get("showWhileChat", Boolean.valueOf(showWhileChat));
    if (isLoading) {
      showWhileChat = property.getBoolean();
    } else {
      property.set(showWhileChat + "");
    }
    property = config.get("optionPositionMode", optionPositionMode);
    if (isLoading) {
      optionPositionMode = property.getString();
    } else {
      property.set(optionPositionMode + "");
    }
    property = config.get("optionMarkerColor", optionMarkerColor);
    if (isLoading) {
      optionMarkerColor = property.getString();
    } else {
      property.set(optionMarkerColor + "");
    }
    property = config.get("optionCustomX", Integer.valueOf(optionCustomX));
    if (isLoading) {
      optionCustomX = property.getInt();
    } else {
      property.set(optionCustomX + "");
    }
    property = config.get("optionCustomY", Integer.valueOf(optionCustomY));
    if (isLoading) {
      optionCustomY = property.getInt();
    } else {
      property.set(optionCustomY + "");
    }
    config.save();
  }
  
  private static final List<String> allColors = new ArrayList();
  
  static
  {
    for (int i = 0; i <= 9; i++) {
      allColors.add(i + "");
    }
    char[] alphabet = "abcdef".toCharArray();
    for (char alphabetChar : alphabet) {
      allColors.add(alphabetChar + "");
    }
  }
  
  public static String getNextColor(String color)
  {
    boolean takeNext = false;
    String returnedColor = null;
    for (String colors : allColors)
    {
      if (takeNext)
      {
        returnedColor = colors;
        break;
      }
      if (color.equals(colors)) {
        takeNext = true;
      }
    }
    if ((takeNext) && (returnedColor == null)) {
      returnedColor = (String)allColors.get(0);
    }
    return returnedColor;
  }
}
