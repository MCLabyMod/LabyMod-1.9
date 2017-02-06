package mods.dmi.main;

import bcf;
import bcz;
import bfb;
import de.labystudio.labymod.LabyMod;
import de.labystudio.modapi.ModAPI;
import de.labystudio.utils.Color;
import de.labystudio.utils.DrawUtils;
import java.io.IOException;
import java.util.List;

public class GuiSettings
  extends bfb
{
  bcz a;
  bcz b;
  SliderDMI slider;
  
  public void b()
  {
    this.n.add(new bcz(0, this.l / 2 - 100, this.m / 6 + 150, "Done"));
    this.n.add(this.a = new bcz(1, this.l / 2 - 100, this.m / 6 + 30, ""));
    this.n.add(this.b = new bcz(2, this.l / 2 - 100, this.m / 6 + 70, ""));
    
    this.n.add(this.slider = new SliderDMI(3, this.l / 2 - 100, this.m / 6 + 110, 197));
    
    refresh();
  }
  
  public void refresh()
  {
    String s = Color.cl("a") + "Enabled";
    if (!Settings.settings.enabled) {
      s = Color.cl("c") + "Disabled";
    }
    this.a.j = s;
    
    s = "Hearts";
    if (!Settings.settings.DMILayout) {
      s = "Number";
    }
    this.b.j = s;
  }
  
  protected void a(bcz button)
    throws IOException
  {
    if (button.k == 0)
    {
      Settings.save();
      this.j.a(ModAPI.getLastScreen());
    }
    if (button.k == 1)
    {
      Settings.settings.enabled = (!Settings.settings.enabled);
      refresh();
    }
    if (button.k == 2)
    {
      Settings.settings.DMILayout = (!Settings.settings.DMILayout);
      refresh();
    }
    super.a(button);
  }
  
  protected void a(char typedChar, int keyCode)
    throws IOException
  {
    if (keyCode == 1)
    {
      Settings.save();
      this.j.a(ModAPI.getLastScreen());
    }
  }
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    c();
    
    LabyMod.getInstance().draw.drawString("Damage Indicator:", this.l / 2 - 100, this.m / 6 + 20);
    LabyMod.getInstance().draw.drawString("Layout:", this.l / 2 - 100, this.m / 6 + 60);
    LabyMod.getInstance().draw.drawString("Scale:", this.l / 2 - 100, this.m / 6 + 100);
    
    super.a(mouseX, mouseY, partialTicks);
  }
}
