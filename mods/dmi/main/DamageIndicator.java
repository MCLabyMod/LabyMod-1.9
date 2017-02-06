package mods.dmi.main;

import bcf;
import bcu;
import bda;
import de.labystudio.modapi.ModAPI;
import de.labystudio.modapi.Module;
import de.labystudio.utils.Color;
import fa;

public class DamageIndicator
  extends Module
{
  public static DMIListener listener = new DMIListener();
  public static boolean blocked = false;
  
  public void onEnable()
  {
    Settings.loadProperties();
    ModAPI.registerListener(listener);
    ModAPI.addSettingsButton("DamageIndicator", new GuiSettings());
  }
  
  public void onDisable() {}
  
  public void pluginMessage(String key, boolean value)
  {
    if (key.equalsIgnoreCase("damageindicator")) {
      blocked = !value;
    }
  }
  
  public static boolean allowed(String ip)
  {
    ip = ip.toLowerCase();
    if (blocked) {
      return false;
    }
    if (ip.contains("timolia")) {
      return false;
    }
    if (ip.contains("gommehd")) {
      return false;
    }
    if (ip.contains("hypixel")) {
      return false;
    }
    if (ip.contains("rewinside")) {
      return false;
    }
    if (ip.contains("mineplex")) {
      return false;
    }
    if (ip.contains("underthedom")) {
      return false;
    }
    if (ip.contains("wave-mc")) {
      return false;
    }
    if (ip.contains("91.121.83.112")) {
      return false;
    }
    if (ip.contains("deinprojekthost")) {
      return false;
    }
    if (ip.contains("miniminerlps")) {
      return false;
    }
    if (ip.contains("dph-games")) {
      return false;
    }
    return true;
  }
  
  public static void disableMessage(String ip)
  {
    bcf.z().r.d().a(new fa(Color.cl("c") + "Damage Indicator is not allowed on " + ip + "!"));
    bcf.z().r.d().a(new fa(Color.cl("c") + "Your Damage Indicator is now " + Color.cl("n") + "disabled" + Color.cl("c") + "."));
  }
}
