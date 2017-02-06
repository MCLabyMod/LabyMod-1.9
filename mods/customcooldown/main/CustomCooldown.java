package mods.customcooldown.main;

import de.labystudio.modapi.ModAPI;
import de.labystudio.modapi.Module;

public class CustomCooldown
  extends Module
{
  public static CustomCooldownListener listener = new CustomCooldownListener();
  
  public void onEnable()
  {
    CCSettingsManager.loadProperties();
    ModAPI.registerListener(listener);
    ModAPI.addSettingsButton("CustomCooldown", new CustomCooldownSettingsGui());
  }
}
