package mods.wordcounter.main;

import de.labystudio.labymod.LabyMod;
import de.labystudio.modapi.EventHandler;
import de.labystudio.modapi.Listener;
import de.labystudio.modapi.ModAPI;
import de.labystudio.modapi.Module;
import de.labystudio.modapi.events.ChatReceivedEvent;
import de.labystudio.modapi.events.RenderOverlayEvent;
import de.labystudio.utils.Color;
import de.labystudio.utils.DrawUtils;
import java.util.HashMap;

public class WordCounterManager
  extends Module
  implements Listener
{
  public void onEnable()
  {
    ModAPI.addSettingsButton("WordCounter", new GuiWordCounter());
    ModAPI.registerListener(this);
    Settings.loadProperties();
  }
  
  @EventHandler
  public void onScreen(RenderOverlayEvent e)
  {
    if (!Settings.settings.enabled) {
      return;
    }
    if (Settings.settings.words.containsKey(Settings.settings.selected))
    {
      LabyMod.getInstance().draw.drawRightString(Color.cl("a") + Settings.settings.words.get(Settings.settings.selected) + "x", LabyMod.getInstance().draw.getWidth() - 2, 3.0D, 2.0D);
      LabyMod.getInstance().draw.drawRightString(Color.cl("f") + "\"" + Settings.settings.selected + "\"", LabyMod.getInstance().draw.getWidth() - 2, 19.0D);
    }
  }
  
  @EventHandler
  public void onScreen(ChatReceivedEvent e)
  {
    if (!Settings.settings.enabled) {
      return;
    }
    String plus = null;
    for (String word : Settings.settings.words.keySet()) {
      if (e.getCleanMsg().toLowerCase().contains(word)) {
        plus = word;
      }
    }
    if (plus != null)
    {
      Settings.settings.words.put(plus, Integer.valueOf(((Integer)Settings.settings.words.get(plus)).intValue() + 1));
      Settings.save();
    }
  }
}
