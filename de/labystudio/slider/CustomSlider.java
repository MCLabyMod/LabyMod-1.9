package de.labystudio.slider;

import bcf;
import bcz;
import bni;
import bvi;
import on;

public class CustomSlider
  extends bcz
{
  private float sliderValue;
  public boolean dragging;
  private SliderOption options;
  
  public CustomSlider(int id, int x, int y, SliderOption options, int width, int height)
  {
    super(id, x, y, width, height, "");
    this.sliderValue = 1.0F;
    this.options = options;
    bcf minecraft = bcf.z();
    this.sliderValue = options.normalizeValue(options.getCallback().getValue());
    this.j = options.getCallback().getTitle(options.getCallback().getValue());
  }
  
  protected int a(boolean mouseOver)
  {
    return 0;
  }
  
  protected void b(bcf mc, int mouseX, int mouseY)
  {
    if (this.m)
    {
      if (this.dragging)
      {
        this.sliderValue = ((mouseX - (this.h + 4)) / (this.f - 8));
        this.sliderValue = on.a(this.sliderValue, 0.0F, 1.0F);
        float f = this.options.denormalizeValue(this.sliderValue);
        this.options.getCallback().setValue((int)f);
        this.sliderValue = this.options.normalizeValue(f);
        this.j = this.options.getCallback().getTitle(this.options.getCallback().getValue());
      }
      mc.N().a(a);
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      b(this.h + (int)(this.sliderValue * (this.f - 8)), this.i, 0, 66, 4, 20);
      b(this.h + (int)(this.sliderValue * (this.f - 8)) + 4, this.i, 196, 66, 4, 20);
    }
  }
  
  public boolean c(bcf mc, int mouseX, int mouseY)
  {
    if (super.c(mc, mouseX, mouseY))
    {
      this.sliderValue = ((mouseX - (this.h + 4)) / (this.f - 8));
      this.sliderValue = on.a(this.sliderValue, 0.0F, 1.0F);
      this.options.getCallback().setValue((int)this.options.denormalizeValue(this.sliderValue));
      this.j = this.options.getCallback().getTitle(this.options.getCallback().getValue());
      this.dragging = true;
      return true;
    }
    return false;
  }
  
  public void a(int mouseX, int mouseY)
  {
    this.dragging = false;
  }
}
