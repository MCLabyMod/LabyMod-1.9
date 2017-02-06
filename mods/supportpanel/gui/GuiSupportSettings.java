package mods.supportpanel.gui;

import bcf;
import bcz;
import de.labystudio.labymod.LabyMod;
import de.labystudio.utils.Color;
import de.labystudio.utils.DrawUtils;
import java.io.IOException;
import java.util.List;
import mods.supportpanel.main.Settings;
import mods.supportpanel.main.SupportPanelSettings;
import org.lwjgl.input.Keyboard;

public class GuiSupportSettings
  extends GuiSupportOverview
{
  int startY = 150 + LabyMod.getInstance().draw.getStringWidth(LabyMod.getInstance().getPlayerName());
  String selected = "";
  
  public void b()
  {
    Keyboard.enableRepeatEvents(true);
    this.n.clear();
    
    super.b();
  }
  
  protected void a(int mouseX, int mouseY, int mouseButton)
    throws IOException
  {
    fields(mouseX, mouseY, false);
    if (this.selected.equals("Show join/leave"))
    {
      Settings.settings.joinLeave = (!Settings.settings.joinLeave);
      Settings.save();
      this.selected = "";
    }
    if (this.selected.equals("Graphics"))
    {
      Settings.settings.graphics = (!Settings.settings.graphics);
      Settings.save();
      this.selected = "";
    }
    super.a(mouseX, mouseY, mouseButton);
  }
  
  protected void a(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick)
  {
    super.a(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
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
    if (this.selected.equals("Hotkey"))
    {
      Settings.settings.key = keyCode;
      this.selected = "";
      Settings.save();
      return;
    }
    super.a(typedChar, keyCode);
  }
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    c();
    
    fields(mouseX, mouseY, true);
    
    super.a(mouseX, mouseY, partialTicks);
  }
  
  private void fields(int mouseX, int mouseY, boolean render)
  {
    String hotKey = "> " + Keyboard.getKeyName(Settings.settings.key) + " <";
    if (this.selected.equals("Hotkey"))
    {
      hotKey = "> _ <";
      if (bcf.I() % 1000L < 500L) {
        hotKey = ">   <";
      }
    }
    String joinLeave = Color.cl("c") + "DISABLED";
    if (Settings.settings.joinLeave) {
      joinLeave = Color.cl("a") + "ENABLED";
    }
    String graphics = Color.cl("c") + "FAST";
    if (Settings.settings.graphics) {
      graphics = Color.cl("a") + "FANCY";
    }
    createField(render, "Hotkey", hotKey, this.startY + 5, 5, (this.l + this.startY - 20) / 2, (this.m - 50 + 5) / 2, mouseX, mouseY);
    createField(render, "Show join/leave", joinLeave, (this.l + this.startY - 20) / 2 + 5, 5, this.l - 20, (this.m - 50 + 5) / 2, mouseX, mouseY);
    createField(render, "Graphics", graphics, this.startY + 5, (this.m - 50 + 5) / 2 + 5, (this.l + this.startY - 20) / 2, this.m - 50, mouseX, mouseY);
    createField(render, "?2", "?", (this.l + this.startY - 20) / 2 + 5, (this.m - 50 + 5) / 2 + 5, this.l - 20, this.m - 50, mouseX, mouseY);
  }
  
  public void createField(boolean render, String title, String subTitle, int left, int top, int right, int bottom, int mouseX, int mouseY)
  {
    if (!render)
    {
      if ((mouseX > left) && (mouseX < right) && (mouseY > top) && (mouseY < bottom)) {
        this.selected = title;
      }
      return;
    }
    int color = Integer.MIN_VALUE;
    if (this.selected.equals(title)) {
      color = Integer.MAX_VALUE;
    }
    DrawUtils.a(left, top, right, bottom, color);
    if ((mouseX > left) && (mouseX < right) && (mouseY > top) && (mouseY < bottom)) {
      DrawUtils.a(left, top, right, bottom, Color.toRGB(10, 50, 91, 60));
    }
    LabyMod.getInstance().draw.drawCenteredString(title, (left + right) / 2, (top + bottom) / 2 - 6, 1.3D);
    LabyMod.getInstance().draw.drawCenteredString(subTitle, (left + right) / 2, (top + bottom) / 2 + 6, 1.0D);
  }
}
