package mods.dmi.main;

import bcf;
import de.labystudio.modapi.EventHandler;
import de.labystudio.modapi.Listener;
import de.labystudio.modapi.events.InitScreenEvent;
import de.labystudio.modapi.events.JoinedServerEvent;
import de.labystudio.modapi.events.RenderNametagEvent;

public class DMIListener
  implements Listener
{
  @EventHandler
  public void onInitScreen(InitScreenEvent e)
  {
    if ((DamageIndicator.blocked) && (bcf.z().h == null)) {
      DamageIndicator.blocked = false;
    }
  }
  
  @EventHandler
  public void onRenderNameTag(RenderNametagEvent e)
  {
    RenderEntity.renderNameTag(e.getEntity(), e.getX(), e.getY(), e.getZ());
  }
  
  @EventHandler
  public void onJoinedServer(JoinedServerEvent e)
  {
    if (!DamageIndicator.allowed(e.getIp())) {
      DamageIndicator.disableMessage(e.getIp());
    }
  }
}
