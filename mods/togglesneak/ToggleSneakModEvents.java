package mods.togglesneak;

import bcf;
import bct;
import bcx;
import bee;
import de.labystudio.modapi.EventHandler;
import de.labystudio.modapi.Listener;
import de.labystudio.modapi.events.RenderOverlayEvent;

public class ToggleSneakModEvents
  implements Listener
{
  private static String hudText = "ToggleSneak";
  
  public static void SetHUDText(String text)
  {
    hudText = text;
  }
  
  @EventHandler
  public void onRender(RenderOverlayEvent e)
  {
    if (ToggleSneakMod.optionShowHUDText)
    {
      bcx resolution = new bcx(bcf.z());
      switch (PositionMode.getByName(ToggleSneakMod.optionPositionMode))
      {
      case CUSTOM: 
        bcf.z().k.a(hudText, ToggleSneakMod.optionHUDTextPosX, ToggleSneakMod.optionHUDTextPosY, 16777215);
        break;
      case BOTTOMRIGHT: 
        bcf.z().k.a(hudText, resolution.a() - bcf.z().k.a(hudText) - 2, resolution.b() - 10, 16777215);
        break;
      case UNDERCHAT: 
        if (!(bcf.z().m instanceof bee)) {
          bcf.z().k.a(hudText, 2, resolution.b() - 10, 16777215);
        }
        break;
      case TOPCENTER: 
        bcf.z().k.a(hudText, resolution.a() / 2 - bcf.z().k.a(hudText) / 2, 2, 16777215);
      }
    }
  }
}
