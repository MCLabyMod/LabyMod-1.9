package de.labystudio.gui.extras;

import bcf;
import bcz;
import bni;
import bvi;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.ModSettings;
import de.labystudio.utils.Color;
import on;

public class SliderColor
  extends bcz
{
  private float sliderValue;
  public boolean dragging;
  private final float field_146132_r;
  private final float field_146131_s;
  private final float size;
  private int valueMin;
  private int color;
  private int colorID;
  private float valueMax;
  private float valueStep;
  private static final String __OBFID = "CL_00000680";
  
  public SliderColor(int id, int x, int y, int size, int color)
  {
    super(id, x, y, size + 5, 20, "");
    this.sliderValue = 1.0F;
    this.field_146132_r = 0.0F;
    this.field_146131_s = 1.0F;
    this.size = (size - 5);
    this.colorID = color;
    this.color = getColor();
    bcf var7 = bcf.z();
    updateText();
    this.valueMin = 0;
    this.valueMax = 15.0F;
    this.valueStep = 1.0F;
  }
  
  public void setColor(int newColor)
  {
    switch (this.colorID)
    {
    case 1: 
      ConfigManager.settings.color1 = Color.IDToColor(newColor);
      break;
    case 2: 
      ConfigManager.settings.color2 = Color.IDToColor(newColor);
      break;
    case 3: 
      ConfigManager.settings.color3 = Color.IDToColor(newColor);
      break;
    case 4: 
      ConfigManager.settings.color4 = Color.IDToColor(newColor);
      break;
    case 5: 
      ConfigManager.settings.color5 = Color.IDToColor(newColor);
      break;
    case 6: 
      ConfigManager.settings.color6 = Color.IDToColor(newColor);
      break;
    case 7: 
      ConfigManager.settings.color7 = Color.IDToColor(newColor);
      break;
    case 8: 
      ConfigManager.settings.color8 = Color.IDToColor(newColor);
      break;
    case 9: 
      ConfigManager.settings.color9 = Color.IDToColor(newColor);
      break;
    case 10: 
      ConfigManager.settings.color10 = Color.IDToColor(newColor);
    }
  }
  
  public int getColor()
  {
    switch (this.colorID)
    {
    case 1: 
      return Color.colorToID(ConfigManager.settings.color1);
    case 2: 
      return Color.colorToID(ConfigManager.settings.color2);
    case 3: 
      return Color.colorToID(ConfigManager.settings.color3);
    case 4: 
      return Color.colorToID(ConfigManager.settings.color4);
    case 5: 
      return Color.colorToID(ConfigManager.settings.color5);
    case 6: 
      return Color.colorToID(ConfigManager.settings.color6);
    case 7: 
      return Color.colorToID(ConfigManager.settings.color7);
    case 8: 
      return Color.colorToID(ConfigManager.settings.color8);
    case 9: 
      return Color.colorToID(ConfigManager.settings.color9);
    case 10: 
      return Color.colorToID(ConfigManager.settings.color10);
    }
    return 0;
  }
  
  public int getX()
  {
    return this.h;
  }
  
  public int getY()
  {
    return this.i;
  }
  
  protected int a(boolean mouseOver)
  {
    return 0;
  }
  
  public float denormalizeValue(float p_148262_1_)
  {
    return snapToStepClamp(this.valueMin + (this.valueMax - this.valueMin) * on.a(p_148262_1_, 0.0F, 1.0F));
  }
  
  public float snapToStepClamp(float p_148268_1_)
  {
    p_148268_1_ = snapToStep(p_148268_1_);
    return on.a(p_148268_1_, this.valueMin, this.valueMax);
  }
  
  protected float snapToStep(float p_148264_1_)
  {
    if (this.valueStep > 0.0F) {
      p_148264_1_ = this.valueStep * Math.round(p_148264_1_ / this.valueStep);
    }
    return p_148264_1_;
  }
  
  public void updateText()
  {
    this.color = getColor();
    this.j = "";
  }
  
  protected void b(bcf mc, int mouseX, int mouseY)
  {
    if (this.m)
    {
      if (this.dragging)
      {
        this.sliderValue = ((mouseX - (getX() + 4)) / (this.f - 8));
        this.sliderValue = on.a(this.sliderValue, 0.0F, 1.0F);
        this.sliderValue = ((mouseX - (this.h + 4)) / (this.f - 8));
        this.sliderValue = on.a(this.sliderValue, 0.0F, 1.0F);
        this.sliderValue = denormalizeValue(this.sliderValue);
        setColor((int)this.sliderValue);
        updateText();
      }
      mc.N().a(a);
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      for (int a = 0; a <= 2; a++) {
        for (int i = 0; i <= 15; i++) {
          if (i == getColor()) {
            c(mc.k, Color.IDToColor(i) + Color.clc("l") + "▌", getX() + (int)(i * 100 / this.valueMax * 0.76D) + 2, getY() + a * 5 + 1, 0);
          } else {
            c(mc.k, Color.IDToColor(i) + "*", getX() + (int)(i * 100 / this.valueMax * 0.76D) + 2, getY() + 5 + 1, 0);
          }
        }
      }
    }
  }
  
  public boolean c(bcf mc, int mouseX, int mouseY)
  {
    if (super.c(mc, mouseX, mouseY))
    {
      this.sliderValue = ((mouseX - (getX() + 4)) / (this.f - 8));
      this.sliderValue = on.a(this.sliderValue, 0.0F, 1.0F);
      setColor((int)this.sliderValue);
      updateText();
      this.dragging = true;
      return true;
    }
    return false;
  }
  
  public void a(int mouseX, int mouseY)
  {
    this.dragging = false;
    ConfigManager.save();
  }
}
