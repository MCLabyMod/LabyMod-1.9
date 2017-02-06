import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PlayerItemsLayer
  implements bty
{
  private buk renderPlayer = null;
  
  public PlayerItemsLayer(buk renderPlayer)
  {
    this.renderPlayer = renderPlayer;
  }
  
  public void a(sa entityLiving, float limbSwing, float limbSwingAmount, float partialTicks, float ticksExisted, float headYaw, float rotationPitch, float scale)
  {
    renderEquippedItems(entityLiving, scale, partialTicks);
  }
  
  protected void renderEquippedItems(sa entityLiving, float scale, float partialTicks)
  {
    if (!Config.isShowCapes()) {
      return;
    }
    if (!(entityLiving instanceof bmq)) {
      return;
    }
    bmq player = (bmq)entityLiving;
    
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    bni.E();
    
    bix modelBipedMain = this.renderPlayer.h();
    PlayerConfigurations.renderPlayerItems(modelBipedMain, player, scale, partialTicks);
  }
  
  public boolean a()
  {
    return false;
  }
  
  public static void register(Map renderPlayerMap)
  {
    Set keys = renderPlayerMap.keySet();
    boolean registered = false;
    for (Iterator it = keys.iterator(); it.hasNext();)
    {
      Object key = it.next();
      Object renderer = renderPlayerMap.get(key);
      if ((renderer instanceof buk))
      {
        buk renderPlayer = (buk)renderer;
        renderPlayer.a(new PlayerItemsLayer(renderPlayer));
        registered = true;
      }
    }
    if (!registered) {
      Config.warn("PlayerItemsLayer not registered");
    }
  }
}
