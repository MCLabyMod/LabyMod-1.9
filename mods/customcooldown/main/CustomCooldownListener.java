package mods.customcooldown.main;

import bcf;
import bmt;
import de.labystudio.labymod.LabyMod;
import de.labystudio.modapi.EventHandler;
import de.labystudio.modapi.Listener;
import de.labystudio.modapi.events.RenderOverlayEvent;
import de.labystudio.utils.DrawUtils;

public class CustomCooldownListener
  implements Listener
{
  @EventHandler
  public void onInitScreen(RenderOverlayEvent e)
  {
    if (bcf.z().h == null) {
      return;
    }
    if (!CCSettingsManager.settings.enabled) {
      return;
    }
    DrawUtils draw = LabyMod.getInstance().draw;
    float cooldown = bcf.z().h.o(0.0F);
    float percent = cooldown * 100.0F * CCSettingsManager.settings.amount / 100.0F;
    int scale = CCSettingsManager.settings.size;
    if (percent != CCSettingsManager.settings.amount) {
      for (int i = CCSettingsManager.settings.amount / 2 * -1; i <= CCSettingsManager.settings.amount / 2; i++) {
        if (i < CCSettingsManager.settings.amount - percent - CCSettingsManager.settings.amount / 2)
        {
          int color = CCSettingsManager.settings.color;
          for (int r = 0; r < 2; r++)
          {
            if (r == 0) {
              color = Integer.MIN_VALUE;
            }
            DrawUtils.a(draw.getWidth() / 2 - scale - i * CCSettingsManager.settings.space, draw
              .getHeight() / 2 - scale + CCSettingsManager.settings.y, draw.getWidth() / 2 + scale - i * CCSettingsManager.settings.space, draw
              
              .getHeight() / 2 + scale + CCSettingsManager.settings.y, color);
          }
        }
        else
        {
          DrawUtils.a(draw.getWidth() / 2 - scale - i * CCSettingsManager.settings.space, draw
            .getHeight() / 2 - scale + CCSettingsManager.settings.y, draw.getWidth() / 2 + scale - i * CCSettingsManager.settings.space, draw
            
            .getHeight() / 2 + scale + CCSettingsManager.settings.y, CCSettingsManager.settings.color);
        }
      }
    }
  }
}
