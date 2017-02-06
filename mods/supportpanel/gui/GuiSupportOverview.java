package mods.supportpanel.gui;

import bcf;
import bcz;
import bfb;
import bni;
import de.labystudio.labymod.LabyMod;
import de.labystudio.utils.Color;
import de.labystudio.utils.DrawUtils;
import de.labystudio.utils.TextureManager;
import java.io.IOException;
import java.util.HashMap;
import mods.supportpanel.main.SupportPanel;

public class GuiSupportOverview
  extends bfb
{
  public void b()
  {
    super.b();
  }
  
  protected void a(int mouseX, int mouseY, int mouseButton)
    throws IOException
  {
    int y = 75;
    for (String name : SupportPanel.menu.keySet())
    {
      int p = (this.m - 120) / SupportPanel.menu.size() - 5;
      if ((mouseX > 10) && (mouseX < 150 + LabyMod.getInstance().draw.getStringWidth(LabyMod.getInstance().getPlayerName())) && (mouseY > y) && (mouseY < y + p))
      {
        SupportPanel.screen = (bfb)SupportPanel.menu.get(name);
        this.j.a(SupportPanel.screen);
      }
      y += (this.m - 120) / SupportPanel.menu.size();
    }
    super.a(mouseX, mouseY, mouseButton);
  }
  
  protected void a(bcz button)
    throws IOException
  {
    super.a(button);
  }
  
  public void k()
    throws IOException
  {
    super.k();
  }
  
  protected void a(char typedChar, int keyCode)
    throws IOException
  {
    super.a(typedChar, keyCode);
  }
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    DrawUtils.a(10, 5, 150 + LabyMod.getInstance().draw.getStringWidth(LabyMod.getInstance().getPlayerName()), 70, Integer.MIN_VALUE);
    
    bni.d(1.0F, 1.0F, 1.0F);
    
    LabyMod.getInstance().textureManager.drawPlayerHead(LabyMod.getInstance().getPlayerName(), 15.0D, 15.0D, 1.5D);
    LabyMod.getInstance().draw.drawString(LabyMod.getInstance().getPlayerName(), 68.0D, 19.0D, 2.0D);
    LabyMod.getInstance().draw.drawString("Supportpanel", 68.0D, 39.0D, 1.0D);
    
    int y = 75;
    for (String name : SupportPanel.menu.keySet())
    {
      int p = (this.m - 120) / SupportPanel.menu.size() - 5;
      if (((GuiSupportOverview)SupportPanel.menu.get(name)).getClass().getSimpleName().equals(this.j.m.getClass().getSimpleName())) {
        DrawUtils.a(10, y, 150 + LabyMod.getInstance().draw.getStringWidth(LabyMod.getInstance().getPlayerName()), y + p, Integer.MAX_VALUE);
      } else {
        DrawUtils.a(10, y, 150 + LabyMod.getInstance().draw.getStringWidth(LabyMod.getInstance().getPlayerName()), y + p, Integer.MIN_VALUE);
      }
      if ((mouseX > 10) && (mouseX < 150 + LabyMod.getInstance().draw.getStringWidth(LabyMod.getInstance().getPlayerName())) && (mouseY > y) && (mouseY < y + p)) {
        DrawUtils.a(10, y, 150 + LabyMod.getInstance().draw.getStringWidth(LabyMod.getInstance().getPlayerName()), y + p, Color.toRGB(10, 50, 91, 60));
      }
      LabyMod.getInstance().draw.drawCenteredString(name, (150 + LabyMod.getInstance().draw.getStringWidth(LabyMod.getInstance().getPlayerName())) / 2 + 5, p / 2 + y - 10, 2.0D);
      y += (this.m - 120) / SupportPanel.menu.size();
    }
    super.a(mouseX, mouseY, partialTicks);
  }
}
