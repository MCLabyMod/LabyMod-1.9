package mods.wordcounter.main;

import bcf;
import bcz;
import bdd;
import bfb;
import de.labystudio.labymod.LabyMod;
import de.labystudio.modapi.ModAPI;
import de.labystudio.utils.Color;
import de.labystudio.utils.DrawUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.lwjgl.input.Keyboard;

public class GuiWordCounter
  extends bfb
{
  bcz toggle;
  bdd input;
  bcz add;
  
  public void b()
  {
    this.n.clear();
    Keyboard.enableRepeatEvents(true);
    this.n.add(new bcz(0, this.l / 2 - 58, this.m / 2 + 95, 180, 20, "Done"));
    this.n.add(this.add = new bcz(3, this.l / 2 + 98, this.m / 2 - 98, 20, 20, "+"));
    this.n.add(this.toggle = new bcz(1, this.l / 2 - 120, this.m / 2 + 95, 60, 20, ""));
    this.input = new bdd(2, this.j.k, this.l / 2 - 118, this.m / 2 - 98, 209, 20);
    this.input.f(20);
    super.b();
    refresh();
  }
  
  public void refresh()
  {
    if (this.toggle != null) {
      if (Settings.settings.enabled) {
        this.toggle.j = (Color.cl("a") + "Enabled");
      } else {
        this.toggle.j = (Color.cl("c") + "Disabled");
      }
    }
  }
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    c();
    
    DrawUtils.a(this.l / 2 - 120, this.m / 2 - 73, this.l / 2 + 120, this.m / 2 + 90, Integer.MIN_VALUE);
    if (this.input != null) {
      this.input.g();
    }
    if ((this.add != null) && (this.input != null)) {
      this.add.l = ((!this.input.b().replace(" ", "").isEmpty()) && (!Settings.settings.words.containsKey(this.input.b())) && (Settings.settings.words.size() < 12));
    }
    DrawUtils.a(this.l / 2 - 110, this.m / 2 - 65, this.l / 2 + 110, this.m / 2 - 65 + 10, Color.toRGB(60, 17, 25, 220));
    if ((mouseX > this.l / 2 - 110) && (mouseX < this.l / 2 + 98) && (mouseY > this.m / 2 - 65) && (mouseY < this.m / 2 - 65 + 10)) {
      DrawUtils.a(this.l / 2 - 110, this.m / 2 - 65, this.l / 2 + 110, this.m / 2 - 65 + 10, Color.toRGB(60, 7, 6, 440));
    }
    int y = 11;
    for (String word : Settings.settings.words.keySet())
    {
      DrawUtils.a(this.l / 2 - 110, this.m / 2 - 65 + y, this.l / 2 + 110, this.m / 2 - 65 + y + 10, 1131575677);
      if (Settings.settings.selected.equals(word)) {
        DrawUtils.a(this.l / 2 - 110, this.m / 2 - 65 + y, this.l / 2 + 13, this.m / 2 - 65 + y + 10, Color.toRGB(90, 67, 56, 180));
      }
      LabyMod.getInstance().draw.drawString(word, this.l / 2 - 110, this.m / 2 - 65 + y);
      LabyMod.getInstance().draw.drawString("-", this.l / 2 + 101, this.m / 2 - 64 + y);
      DrawUtils.a(this.l / 2 + 98, this.m / 2 - 65 + y, this.l / 2 + 110, this.m / 2 - 65 + 10 + y, Color.toRGB(120, 1, 1, 50));
      
      LabyMod.getInstance().draw.drawString("RESET", this.l / 2 + 101 - 39 - 3, this.m / 2 - 64 + y + 1);
      DrawUtils.a(this.l / 2 + 98 - 42, this.m / 2 - 65 + y, this.l / 2 + 110 - 19, this.m / 2 - 65 + 10 + y, Color.toRGB(65, 67, 6, 400));
      
      DrawUtils.a(this.l / 2 + 98 - 45 - 40, this.m / 2 - 65 + y, this.l / 2 + 110 - 20 - 40, this.m / 2 - 65 + 10 + y, Color.toRGB(5, 37, 56, 400));
      LabyMod.getInstance().draw.drawString(Settings.settings.words.get(word) + "x", this.l / 2 + 101 - 39 - 5 - 40, this.m / 2 - 64 + y + 1);
      if ((mouseX > this.l / 2 + 98) && (mouseX < this.l / 2 + 110) && (mouseY > this.m / 2 - 65 + y) && (mouseY < this.m / 2 - 65 + 10 + y))
      {
        DrawUtils.a(this.l / 2 + 98, this.m / 2 - 65 + y, this.l / 2 + 110, this.m / 2 - 65 + 10 + y, Color.toRGB(60, 7, 6, 440));
        LabyMod.getInstance().draw.drawString(Color.cl("4") + "-", this.l / 2 + 101, this.m / 2 - 64 + y);
      }
      if ((mouseX > this.l / 2 - 110) && (mouseX < this.l / 2 + 98) && (mouseY > this.m / 2 - 65 + y) && (mouseY < this.m / 2 - 65 + 10 + y)) {
        DrawUtils.a(this.l / 2 - 110, this.m / 2 - 65 + y, this.l / 2 + 13, this.m / 2 - 65 + y + 10, Color.toRGB(6, 7, 6, 40));
      }
      if ((mouseX > this.l / 2 + 98 - 45) && (mouseX < this.l / 2 + 110 - 20) && (mouseY > this.m / 2 - 65 + y) && (mouseY < this.m / 2 - 65 + 10 + y))
      {
        DrawUtils.a(this.l / 2 + 98 - 42, this.m / 2 - 65 + y, this.l / 2 + 110 - 19, this.m / 2 - 65 + 10 + y, Color.toRGB(65, 67, 6, 100));
        LabyMod.getInstance().draw.drawString(Color.cl("e") + "RESET", this.l / 2 + 101 - 39 - 3, this.m / 2 - 64 + y + 1);
      }
      y += 11;
      if (y > 132) {
        break;
      }
    }
    super.a(mouseX, mouseY, partialTicks);
  }
  
  protected void a(bcz button)
    throws IOException
  {
    if ((this.toggle != null) && (button.k == 1))
    {
      Settings.settings.enabled = (!Settings.settings.enabled);
      refresh();
    }
    if (button.k == 0)
    {
      Settings.save();
      this.j.a(ModAPI.getLastScreen());
    }
    if ((button.k == 3) && (this.input != null) && (button.l))
    {
      Settings.settings.words.put(this.input.b(), Integer.valueOf(0));
      this.input.a("");
    }
    super.a(button);
  }
  
  protected void a(char typedChar, int keyCode)
    throws IOException
  {
    if (this.input != null) {
      this.input.a(typedChar, keyCode);
    }
    if ((keyCode == 28) && 
      (this.add != null) && (this.add.l))
    {
      Settings.settings.words.put(this.input.b(), Integer.valueOf(0));
      this.input.a("");
    }
    if (keyCode == 1)
    {
      Settings.save();
      this.j.a(ModAPI.getLastScreen());
      return;
    }
    super.a(typedChar, keyCode);
  }
  
  protected void a(int mouseX, int mouseY, int mouseButton)
    throws IOException
  {
    if (this.input != null) {
      this.input.a(mouseX, mouseY, mouseButton);
    }
    String rem = null;
    String res = null;
    if ((mouseX > this.l / 2 - 110) && (mouseX < this.l / 2 + 98) && (mouseY > this.m / 2 - 65) && (mouseY < this.m / 2 - 65 + 10)) {
      Settings.settings.selected = "";
    }
    int y = 11;
    for (String word : Settings.settings.words.keySet())
    {
      if ((mouseX > this.l / 2 + 98) && (mouseX < this.l / 2 + 110) && (mouseY > this.m / 2 - 65 + y) && (mouseY < this.m / 2 - 65 + 10 + y)) {
        rem = word;
      }
      if ((mouseX > this.l / 2 + 98 - 45) && (mouseX < this.l / 2 + 110 - 20) && (mouseY > this.m / 2 - 65 + y) && (mouseY < this.m / 2 - 65 + 10 + y)) {
        res = word;
      }
      if ((mouseX > this.l / 2 - 110) && (mouseX < this.l / 2 + 98) && (mouseY > this.m / 2 - 65 + y) && (mouseY < this.m / 2 - 65 + 10 + y)) {
        Settings.settings.selected = word;
      }
      y += 11;
      if (y > 132) {
        break;
      }
    }
    if (rem != null) {
      Settings.settings.words.remove(rem);
    }
    if (res != null) {
      Settings.settings.words.put(res, Integer.valueOf(0));
    }
    super.a(mouseX, mouseY, mouseButton);
  }
}
