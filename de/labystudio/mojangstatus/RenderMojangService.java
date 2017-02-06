package de.labystudio.mojangstatus;

import bcf;
import bni;
import bvi;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.ModSettings;
import de.labystudio.utils.Color;
import de.labystudio.utils.DrawUtils;
import de.labystudio.utils.ServiceStatus;
import de.labystudio.utils.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import kk;

public class RenderMojangService
{
  public static void renderOnMultiplayerGui()
  {
    if (!ConfigManager.settings.mojangStatus) {
      return;
    }
    bni.G();
    int y = 0;
    ArrayList<String> services = new ArrayList(LabyMod.getInstance().mojangStatus.keySet());
    for (String service : services)
    {
      ServiceStatus status = (ServiceStatus)LabyMod.getInstance().mojangStatus.get(service);
      float i = (float)((System.currentTimeMillis() + y * 4) % 1000L * 0.1D) / 10.0F;
      if (i > 5.0F) {
        i = 10.0F - i;
      }
      i = 0.1F * i + 0.5F;
      long time = (System.currentTimeMillis() - status.getCreated()) / 1000L;
      String downTime = time + "s";
      if (time >= 60L) {
        downTime = Utils.parseTimeNormal(time);
      }
      float a = 0.3F;
      if (status.getColor().equals("red")) {
        a = i;
      }
      float b = 0.3F;
      if (status.getColor().equals("green"))
      {
        b = i;
        downTime = "Is back online!";
        if (time > 10L) {
          LabyMod.getInstance().mojangStatus.remove(service);
        }
      }
      float c = 0.3F;
      if (status.getColor().equals("yellow"))
      {
        a = i;
        b = i;
      }
      bni.d(a, b, c);
      LabyMod.getInstance().mc.N().a(new kk("textures/gui/achievement/achievement_background.png"));
      LabyMod.getInstance().draw.b(1, 35 + y, 257, 459, 23, 23);
      LabyMod.getInstance().draw.drawRect(25.0D, 38 + y, (LabyMod.getInstance().draw.getStringWidth(service) + 40) * 0.7D, 45 + y, Color.toRGB(50, 50, 50, 170));
      LabyMod.getInstance().draw.drawRect(25.0D, 48 + y, (LabyMod.getInstance().draw.getStringWidth(downTime) + 49) * 0.6D, 54 + y, Color.toRGB(70, 70, 70, 170));
      LabyMod.getInstance().draw.drawString(Color.cl(status.getChatColor()) + service, 27.0D, 39 + y, 0.7D);
      LabyMod.getInstance().draw.drawString(downTime, 27.0D, 49 + y, 0.6D);
      LabyMod.getInstance().draw.drawCenteredString(status.getStatus(), 13.0D, 45 + y, 0.5D);
      y += 23;
    }
    bni.H();
  }
}
