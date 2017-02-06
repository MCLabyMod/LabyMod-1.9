package mods.customcooldown.main;

import bcf;
import bcz;
import bdd;
import bfb;
import de.labystudio.labymod.LabyMod;
import de.labystudio.modapi.ModAPI;
import de.labystudio.slider.CustomSlider;
import de.labystudio.slider.SliderCallback;
import de.labystudio.slider.SliderOption;
import de.labystudio.utils.DrawUtils;
import java.io.IOException;
import java.util.List;

public class CustomCooldownSettingsGui
  extends bfb
{
  bcz buttonDone;
  bcz buttonEnabled;
  bdd fieldRed;
  bdd fieldGreen;
  bdd fieldBlue;
  
  public void b()
  {
    int ySlider = 5;
    int size = 70;
    int space = 35;
    
    this.n.add(this.buttonDone = new bcz(0, this.l - size - 5, this.m - 22, size, 20, "Done"));
    
    this.n.add(this.buttonEnabled = new bcz(1, this.l - size - 5, ySlider, size, 20, "Enabled"));
    
    ySlider += 35;
    
    SliderOption option = new SliderOption(0.0F, 70.0F, new SliderCallback()
    {
      public void setValue(int i)
      {
        CCSettingsManager.settings.amount = i;
      }
      
      public int getValue()
      {
        return CCSettingsManager.settings.amount;
      }
      
      public String getTitle(int value)
      {
        return value + "";
      }
    });
    this.n.add(new CustomSlider(-1, this.l - size - 5, ySlider, option, size, 20));
    
    ySlider += space;
    
    option = new SliderOption(0.0F, 70.0F, new SliderCallback()
    {
      public void setValue(int i)
      {
        CCSettingsManager.settings.size = i;
      }
      
      public int getValue()
      {
        return CCSettingsManager.settings.size;
      }
      
      public String getTitle(int value)
      {
        return value + "";
      }
    });
    this.n.add(new CustomSlider(-1, this.l - size - 5, ySlider, option, size, 20));
    
    ySlider += space;
    
    option = new SliderOption(0.0F, 70.0F, new SliderCallback()
    {
      public void setValue(int i)
      {
        CCSettingsManager.settings.space = i;
      }
      
      public int getValue()
      {
        return CCSettingsManager.settings.space;
      }
      
      public String getTitle(int value)
      {
        return value + "";
      }
    });
    this.n.add(new CustomSlider(-1, this.l - size - 5, ySlider, option, size, 20));
    
    ySlider += space;
    
    option = new SliderOption(-150.0F, 150.0F, new SliderCallback()
    {
      public void setValue(int i)
      {
        CCSettingsManager.settings.y = i;
      }
      
      public int getValue()
      {
        return CCSettingsManager.settings.y;
      }
      
      public String getTitle(int value)
      {
        return value + "";
      }
    });
    this.n.add(new CustomSlider(-1, this.l - size - 5, ySlider, option, size, 20));
    
    ySlider += space;
    
    java.awt.Color color = new java.awt.Color(CCSettingsManager.settings.color);
    
    this.fieldRed = new bdd(-1, this.j.k, this.l - size - 7, ySlider + 2, 26, 14);
    this.fieldRed.f(3);
    this.fieldRed.a(color.getRed() + "");
    this.fieldGreen = new bdd(-1, this.j.k, this.l - size - 7 + 25, ySlider + 2, 26, 14);
    this.fieldGreen.f(3);
    this.fieldGreen.a(color.getGreen() + "");
    this.fieldBlue = new bdd(-1, this.j.k, this.l - size - 7 + 50, ySlider + 2, 26, 14);
    this.fieldBlue.f(3);
    this.fieldBlue.a(color.getBlue() + "");
    
    refresh();
  }
  
  public void refresh()
  {
    String s = de.labystudio.utils.Color.cl("a") + "Enabled";
    if (!CCSettingsManager.settings.enabled) {
      s = de.labystudio.utils.Color.cl("c") + "Disabled";
    }
    this.buttonEnabled.j = s;
  }
  
  protected void a(bcz button)
    throws IOException
  {
    if (button.k == 0)
    {
      CCSettingsManager.save();
      this.j.a(ModAPI.getLastScreen());
    }
    if (button.k == 1)
    {
      CCSettingsManager.settings.enabled = (!CCSettingsManager.settings.enabled);
      refresh();
    }
    super.a(button);
  }
  
  protected void a(int mouseX, int mouseY, int mouseButton)
    throws IOException
  {
    this.fieldRed.a(mouseX, mouseY, mouseButton);
    this.fieldGreen.a(mouseX, mouseY, mouseButton);
    this.fieldBlue.a(mouseX, mouseY, mouseButton);
    super.a(mouseX, mouseY, mouseButton);
  }
  
  protected void a(char typedChar, int keyCode)
    throws IOException
  {
    if (keyCode == 1)
    {
      CCSettingsManager.save();
      this.j.a(ModAPI.getLastScreen());
    }
    String backRed = this.fieldRed.b();
    String backGreen = this.fieldGreen.b();
    String backBlue = this.fieldBlue.b();
    if (this.fieldRed.a(typedChar, keyCode)) {
      try
      {
        String s = this.fieldRed.b();
        if (s.isEmpty()) {
          s = "0";
        }
        int i = Integer.parseInt(s);
        if (i > 255)
        {
          i = 255;
          this.fieldRed.a(i + "");
        }
        if (i <= 0)
        {
          i = 0;
          this.fieldRed.a(i + "");
        }
        CCSettingsManager.settings.color = de.labystudio.utils.Color.toRGB(i, 
          Integer.parseInt(this.fieldGreen.b()), 
          Integer.parseInt(this.fieldBlue.b()), 200);
      }
      catch (Exception error)
      {
        this.fieldRed.a(backRed);
      }
    }
    if (this.fieldGreen.a(typedChar, keyCode)) {
      try
      {
        String s = this.fieldGreen.b();
        if (s.isEmpty()) {
          s = "0";
        }
        int i = Integer.parseInt(s);
        if (i > 255)
        {
          i = 255;
          this.fieldGreen.a(i + "");
        }
        if (i <= 0)
        {
          i = 0;
          this.fieldGreen.a(i + "");
        }
        CCSettingsManager.settings.color = de.labystudio.utils.Color.toRGB(Integer.parseInt(this.fieldRed.b()), i, 
        
          Integer.parseInt(this.fieldBlue.b()), 200);
      }
      catch (Exception error)
      {
        this.fieldGreen.a(backGreen);
      }
    }
    if (this.fieldBlue.a(typedChar, keyCode)) {
      try
      {
        String s = this.fieldBlue.b();
        if (s.isEmpty()) {
          s = "0";
        }
        int i = Integer.parseInt(s);
        if (i > 255)
        {
          i = 255;
          this.fieldBlue.a(i + "");
        }
        if (i <= 0)
        {
          i = 0;
          this.fieldBlue.a(i + "");
        }
        CCSettingsManager.settings.color = de.labystudio.utils.Color.toRGB(Integer.parseInt(this.fieldRed.b()), 
          Integer.parseInt(this.fieldGreen.b()), i, 200);
      }
      catch (Exception error)
      {
        this.fieldBlue.a(backBlue);
      }
    }
    super.a(typedChar, keyCode);
  }
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    c();
    
    LabyMod.getInstance().draw.drawString(de.labystudio.utils.Color.cl("n") + "CustomCooldown Settings", 5.0D, 5.0D);
    
    DrawUtils draw = LabyMod.getInstance().draw;
    if (CCSettingsManager.settings.enabled)
    {
      float cooldown = (float)(System.currentTimeMillis() % 1400L) / 1000.0F;
      float percent = cooldown * 100.0F * CCSettingsManager.settings.amount / 100.0F;
      int scale = CCSettingsManager.settings.size;
      if (percent != CCSettingsManager.settings.amount) {
        for (int i = CCSettingsManager.settings.amount / 2 * -1; i <= CCSettingsManager.settings.amount / 2; i++) {
          if (i < CCSettingsManager.settings.amount - percent - CCSettingsManager.settings.amount / 2)
          {
            int color = CCSettingsManager.settings.color;
            for (int r = 0; r < 2; r++)
            {
              if (r == 0) {
                color = Integer.MIN_VALUE;
              }
              DrawUtils.a(draw.getWidth() / 2 - scale - i * CCSettingsManager.settings.space, draw
                .getHeight() / 2 - scale + CCSettingsManager.settings.y, draw.getWidth() / 2 + scale - i * CCSettingsManager.settings.space, draw
                
                .getHeight() / 2 + scale + CCSettingsManager.settings.y, color);
            }
          }
          else
          {
            DrawUtils.a(draw.getWidth() / 2 - scale - i * CCSettingsManager.settings.space, draw
              .getHeight() / 2 - scale + CCSettingsManager.settings.y, draw.getWidth() / 2 + scale - i * CCSettingsManager.settings.space, draw
              
              .getHeight() / 2 + scale + CCSettingsManager.settings.y, CCSettingsManager.settings.color);
          }
        }
      }
    }
    else
    {
      double scale = CCSettingsManager.settings.size * 0.1D;
      if (scale < 0.4D) {
        scale = 0.4D;
      }
      if (scale > 2.4D) {
        scale = 2.4D;
      }
      LabyMod.getInstance().draw.drawCenteredString(de.labystudio.utils.Color.cl("c") + "This feature is disabled.", (this.l - 70) / 2, this.m / 2 + CCSettingsManager.settings.y, scale);
    }
    int ySlider = 40;
    int size = 70;
    int space = 35;
    
    draw.overlayBackground(this.l - size - 10, 0, this.l, this.m);
    DrawUtils.a(this.l - size - 11, 0, this.l - size - 10, this.m, Integer.MIN_VALUE);
    
    LabyMod.getInstance().draw.drawString(de.labystudio.utils.Color.cl("b") + "Dots amount:", this.l - size - 4, ySlider - 10);
    ySlider += space;
    LabyMod.getInstance().draw.drawString(de.labystudio.utils.Color.cl("b") + "Dots scale:", this.l - size - 4, ySlider - 10);
    ySlider += space;
    LabyMod.getInstance().draw.drawString(de.labystudio.utils.Color.cl("b") + "Dots spacing:", this.l - size - 4, ySlider - 10);
    ySlider += space;
    LabyMod.getInstance().draw.drawString(de.labystudio.utils.Color.cl("b") + "Positon y:", this.l - size - 4, ySlider - 10);
    ySlider += space;
    LabyMod.getInstance().draw.drawString(de.labystudio.utils.Color.cl("b") + "Dots color:", this.l - size - 4, ySlider - 10);
    
    this.fieldRed.g();
    this.fieldGreen.g();
    this.fieldBlue.g();
    
    super.a(mouseX, mouseY, partialTicks);
  }
}
