package mods.supportpanel.main;

import bfb;
import de.labystudio.labymod.LabyMod;
import de.labystudio.modapi.ModAPI;
import de.labystudio.modapi.Module;
import eu;
import java.util.ArrayList;
import java.util.HashMap;
import mods.supportpanel.gui.GuiSupportChatlog;
import mods.supportpanel.gui.GuiSupportOverview;
import mods.supportpanel.gui.GuiSupportSettings;

public class SupportPanel
  extends Module
{
  public static boolean keep = false;
  public static bfb screen = new GuiSupportChatlog();
  public static HashMap<String, GuiSupportOverview> menu = new HashMap();
  public static HashMap<String, ArrayList<GommeMessage>> chatLog = new HashMap();
  
  public void onEnable()
  {
    ModAPI.registerListener(new SupportListener());
    ModAPI.addSettingsButton("SupportPanel", screen);
    Settings.loadProperties();
    
    menu.put("Settings", new GuiSupportSettings());
    menu.put("Chatlog", new GuiSupportChatlog());
  }
  
  public static String getCurrentServer()
  {
    if (LabyMod.getInstance().header != null) {
      return LabyMod.getInstance().header.d();
    }
    return "?";
  }
}
