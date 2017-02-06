package de.labystudio.gui;

import bcf;
import bcz;
import bfb;
import de.labystudio.modapi.ModManager;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class GuiCompModSettings
  extends bfb
{
  bfb lastScreen;
  bcz cancel;
  
  public GuiCompModSettings(bfb lastScreen)
  {
    this.lastScreen = lastScreen;
  }
  
  public void b()
  {
    this.cancel = new bcz(0, this.l / 2 - 100, this.m / 6 + 168, "Done");
    this.n.add(this.cancel);
    int id = 1;
    boolean left = true;
    int y = 0;
    for (String mod : ModManager.getSettings().keySet())
    {
      int x = -100;
      int le = 98;
      if (!left) {
        x = 2;
      } else if (ModManager.getSettings().keySet().size() == id) {
        le = 200;
      }
      if (id <= 12)
      {
        this.n.add(new bcz(id, this.l / 2 + x, 70 + y, le, 20, mod));
        if (!left) {
          y += 22;
        }
        left = !left;
        id++;
      }
    }
    super.b();
  }
  
  protected void a(char typedChar, int keyCode)
    throws IOException
  {
    if (keyCode == 1)
    {
      this.j.a(this.lastScreen);
      return;
    }
    super.a(typedChar, keyCode);
  }
  
  protected void a(bcz button)
    throws IOException
  {
    int id = 1;
    for (bfb mod : ModManager.getSettings().values())
    {
      if (button.k == id)
      {
        ModManager.updateLastScreen(this);
        this.j.a(mod);
        break;
      }
      id++;
    }
    if (button.k == 0)
    {
      this.j.a(this.lastScreen);
      return;
    }
    super.a(button);
  }
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    c();
    String s = "s";
    if (ModManager.getSettings().size() == 1) {
      s = "";
    }
    a(this.q, "Mod Settings (" + ModManager.getSettings().size() + " Mod" + s + ")", this.l / 2, 15, 16777215);
    super.a(mouseX, mouseY, partialTicks);
  }
}
