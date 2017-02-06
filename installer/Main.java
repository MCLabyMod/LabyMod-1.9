package installer;

import installer.labystudio.frame.FrameMain;
import java.io.File;
import java.util.ArrayList;

public class Main
{
  public static String modVersion = "2.7.7";
  public static String mcVersion = "1.9";
  public static final String mcClientUrl = "https://launcher.mojang.com/mc/game/1.9/client/2f67dfe8953299440d1902f9124f0f2c3a2c940f/client.jar";
  public static File[] mods = null;
  public static String debug = "[DEBUG] ";
  public static String text = "This installer will automatically install LabyMod and if you wish,\nyou can add several mods provided on our website.\n\nIf you've successfully installed LabyMod,\nopen the Minecraft launcher and select the LabyMod profile.\n\nMake sure you've closed Minecraft before the\ninstallation and launch the installer from your desktop.";
  public static ArrayList<ModTemplate> modTempates = new ArrayList();
  
  private static void setupTemplates()
  {
    String dir = "https://www.labymod.net/mods/";
    modTempates.add(new ModTemplate("OptiFine 1.9.0 HD U A0 pre", true, dir + "OptiFine_1.9.0_HD_U_A0_pre.jar", false));
    modTempates.add(new ModTemplate("Toggle Sneak/Sprint", false, dir + "ToggleSneak_v6_mc1.9.zip", true));
    modTempates.add(new ModTemplate("Damage Indicator", false, dir + "DamageIndicator_v3_mc1.9.zip", true));
    
    modTempates.add(new ModTemplate("DirectionHUD", false, dir + "DirectionHud_v4_mc1.9.zip", false));
    modTempates.add(new ModTemplate("ItemPhysics", false, dir + "ItemPhysics_v1_mc1.9.zip", false));
    modTempates.add(new ModTemplate("BattysCoordinates", false, dir + "BattysCoordinates_v1_mc1.9.zip", false));
    modTempates.add(new ModTemplate("CustomCooldown", false, dir + "CustomCooldown_v1_mc1.9.zip", false));
  }
  
  public static void main(String[] args)
  {
    setupTemplates();
    new FrameMain();
  }
}
