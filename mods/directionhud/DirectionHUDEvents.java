package mods.directionhud;

import bcf;
import bch;
import bct;
import bcx;
import bee;
import bmt;
import bmz;
import bnu;
import bvi;
import bvp;
import de.labystudio.modapi.EventHandler;
import de.labystudio.modapi.Listener;
import de.labystudio.modapi.events.RenderOverlayEvent;
import kk;
import on;
import org.lwjgl.opengl.GL11;

public class DirectionHUDEvents
  implements Listener
{
  private static bcf mc = ;
  private static bcx scaledResolution;
  
  @EventHandler
  public void onRender(RenderOverlayEvent e)
  {
    if (!DirectionHUD.optionEnable) {
      return;
    }
    if (mc.u.aq) {
      return;
    }
    if ((!mc.x) && (mc.m != null) && ((!(mc.m instanceof bee)) || (!DirectionHUD.showWhileChat))) {
      return;
    }
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    scaledResolution = new bcx(mc);
    displayHUD(mc);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
  }
  
  private static void displayHUD(bcf mc)
  {
    int direction = on.c(mc.h.v * 256.0F / 360.0F + 0.5D) & 0xFF;
    
    int yBase = getY(1, 12);
    int xBase = getX(65);
    
    mc.N().a(new kk("directionhud/textures/gui/compass.png"));
    if (direction < 128) {
      drawTexturedModalRect(xBase, yBase, direction, 0, 65, 12, -100.0F);
    } else {
      drawTexturedModalRect(xBase, yBase, direction - 128, 12, 65, 12, -100.0F);
    }
    mc.k.a("§" + DirectionHUD.optionMarkerColor
      .toLowerCase() + "|", xBase + 32, yBase + 1, 16777215);
    
    mc.k.a("§" + DirectionHUD.optionMarkerColor
      .toLowerCase() + "|§r", xBase + 32, yBase + 5, 16777215);
  }
  
  private static int getX(int width)
  {
    PositionMode positionMode = PositionMode.getByName(DirectionHUD.optionPositionMode);
    if (positionMode.name().endsWith("CENTER")) {
      return scaledResolution.a() / 2 - width / 2;
    }
    if (positionMode.name().endsWith("RIGHT")) {
      return scaledResolution.a() - width - 2;
    }
    return DirectionHUD.optionCustomX;
  }
  
  private static int getY(int rowCount, int height)
  {
    PositionMode positionMode = PositionMode.getByName(DirectionHUD.optionPositionMode);
    if (positionMode.name().startsWith("MIDDLE")) {
      return scaledResolution.b() / 2 - rowCount * height / 2;
    }
    if ((positionMode == PositionMode.BOTTOMLEFT) || (positionMode == PositionMode.BOTTOMRIGHT)) {
      return scaledResolution.b() - rowCount * height - 2;
    }
    if (positionMode == PositionMode.BOTTOMCENTER) {
      return scaledResolution.b() - rowCount * height - 41;
    }
    return DirectionHUD.optionCustomY;
  }
  
  public static void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height, float zLevel)
  {
    float f = 0.00390625F;
    float f1 = 0.00390625F;
    bnu tessellator = bnu.a();
    bmz worldrenderer = tessellator.c();
    worldrenderer.a(7, bvp.g);
    worldrenderer
      .b(x + 0, y + height, zLevel)
      .a((textureX + 0) * f, (textureY + height) * f1)
      
      .d();
    worldrenderer
      .b(x + width, y + height, zLevel)
      
      .a((textureX + width) * f, (textureY + height) * f1)
      
      .d();
    worldrenderer
      .b(x + width, y + 0, zLevel)
      .a((textureX + width) * f, (textureY + 0) * f1)
      .d();
    worldrenderer
      .b(x + 0, y + 0, zLevel)
      .a((textureX + 0) * f, (textureY + 0) * f1)
      .d();
    tessellator.b();
  }
}
