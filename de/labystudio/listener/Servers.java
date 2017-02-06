package de.labystudio.listener;

import de.labystudio.labymod.LabyMod;

public class Servers
{
  private static boolean isDeinProjektHost = false;
  private static boolean isMineVerse = false;
  
  public static void updateDeinProjektHost()
  {
    String ip = LabyMod.getInstance().ip.toLowerCase();
    isDeinProjektHost = (ip.contains("deinprojekthost")) || (ip.contains("miniminerlps.de")) || (ip.contains("dph-games.de"));
  }
  
  public static void updateMineVerse()
  {
    String ip = LabyMod.getInstance().ip.toLowerCase();
    isDeinProjektHost = ip.contains("mineverse");
  }
  
  public static boolean isMineVerse()
  {
    return isMineVerse;
  }
  
  public static boolean isDeinProjektHost()
  {
    return isDeinProjektHost;
  }
}
