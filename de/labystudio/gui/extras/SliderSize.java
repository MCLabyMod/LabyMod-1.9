package de.labystudio.gui.extras;

import bcf;
import bcz;
import bni;
import bvi;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.ModSettings;
import de.labystudio.utils.Color;
import on;

public class SliderSize
  extends bcz
{
  private float sliderValue;
  public boolean dragging;
  private final float field_146132_r;
  private final float field_146131_s;
  private final float size;
  private int valueMin;
  private float valueMax;
  private float valueStep;
  private static final String __OBFID = "CL_00000680";
  
  public SliderSize(int id, int x, int y, int size)
  {
    super(id, x, y, size + 3, 20, "");
    this.sliderValue = 1.0F;
    this.field_146132_r = 0.0F;
    this.field_146131_s = 1.0F;
    this.size = size;
    bcf var7 = bcf.z();
    updateText();
    this.valueMin = 0;
    this.valueMax = 100.0F;
    this.valueStep = 1.0F;
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
    if (ConfigManager.settings.size == 50) {
      this.j = ("" + Color.c + "a" + (ConfigManager.settings.size + 50) + "%");
    } else {
      this.j = (ConfigManager.settings.size + 50 + "%");
    }
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
        ConfigManager.settings.size = ((int)this.sliderValue);
        updateText();
      }
      mc.N().a(a);
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      b(getX() + (int)(ConfigManager.settings.size * 100 / this.valueMax * 0.72D), getY(), 0, 66, 4, 20);
      b(getX() + (int)(ConfigManager.settings.size * 100 / this.valueMax * 0.72D) + 4, getY(), 196, 66, 4, 20);
    }
  }
  
  public boolean c(bcf mc, int mouseX, int mouseY)
  {
    if (super.c(mc, mouseX, mouseY))
    {
      this.sliderValue = ((mouseX - (getX() + 4)) / (this.f - 8));
      this.sliderValue = on.a(this.sliderValue, 0.0F, 1.0F);
      ConfigManager.settings.size = ((int)this.sliderValue);
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
