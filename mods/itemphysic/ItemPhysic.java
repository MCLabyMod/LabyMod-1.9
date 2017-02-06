package mods.itemphysic;

import bcf;
import bcz;
import bfb;
import de.labystudio.modapi.ModAPI;
import de.labystudio.modapi.Module;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mods.itemphysic.config.Configuration;
import mods.itemphysic.config.Property;

public class ItemPhysic
  extends Module
{
  public static float rotationSpeed = 1.0F;
  private static Configuration config;
  
  public void onEnable()
  {
    Map<String, Object> defaultValues = new HashMap();
    defaultValues.put("rotationSpeed", Float.valueOf(rotationSpeed));
    
    config = new Configuration("ItemPhysics_LabyMod", defaultValues);
    rotationSpeed = config.get("rotationSpeed").getFloat();
    
    ModAPI.addSettingsButton("ItemPhysic", new ItemPhysicGUI());
  }
  
  public static class ItemPhysicGUI
    extends bfb
  {
    private GuiSlideControl rotationSpeedControl;
    
    public void b()
    {
      this.n.clear();
      this.n.add(this.rotationSpeedControl = new GuiSlideControl(1, this.l / 2 - 50, this.m / 2 - 10, 100, 20, "Rotation Speed: ", 0.0F, 1.0F, ItemPhysic.rotationSpeed, true));
      
      this.n.add(new bcz(2, this.l / 2 - 30, this.m / 2 + 20, 60, 20, "Save"));
      
      super.b();
    }
    
    public void a(int mouseX, int mouseY, float partialTicks)
    {
      super.c();
      super.a(mouseX, mouseY, partialTicks);
    }
    
    protected void a(int mouseX, int mouseY, int mouseButton)
      throws IOException
    {
      super.a(mouseX, mouseY, mouseButton);
      
      ItemPhysic.rotationSpeed = this.rotationSpeedControl.GetValueAsFloat();
      ItemPhysic.config.set("rotationSpeed", new Property(String.valueOf(ItemPhysic.rotationSpeed)));
      ItemPhysic.config.save();
    }
    
    protected void b(int mouseX, int mouseY, int state)
    {
      super.b(mouseX, mouseY, state);
      
      ItemPhysic.rotationSpeed = this.rotationSpeedControl.GetValueAsFloat();
      ItemPhysic.config.set("rotationSpeed", new Property(String.valueOf(ItemPhysic.rotationSpeed)));
      ItemPhysic.config.save();
    }
    
    protected void a(bcz button)
      throws IOException
    {
      if (button.k == 2) {
        this.j.a(ModAPI.getLastScreen());
      }
      super.a(button);
    }
  }
}
