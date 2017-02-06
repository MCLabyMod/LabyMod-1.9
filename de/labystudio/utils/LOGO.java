package de.labystudio.utils;

public class LOGO
{
  public static boolean isLogisch(String player)
  {
    if (player.equals("LOGO")) {
      return false;
    }
    if (player.toLowerCase().contains("logo")) {
      return true;
    }
    if (player.toLowerCase().contains("logi")) {
      return true;
    }
    if (player.toLowerCase().contains("log0")) {
      return true;
    }
    if (player.toLowerCase().contains("l0g0")) {
      return true;
    }
    if (player.toLowerCase().contains("l0go")) {
      return true;
    }
    if (player.toLowerCase().contains("l0gi")) {
      return true;
    }
    if (player.equals("LabyStudio")) {
      return true;
    }
    if (player.equals("Buttspencer")) {
      return true;
    }
    if (player.equals("CraftingPat")) {
      return true;
    }
    return false;
  }
  
  public static boolean isLogo(String player)
  {
    if (player.equals("LOGO")) {
      return true;
    }
    return false;
  }
}
