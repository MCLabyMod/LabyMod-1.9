package net.minecraft.realms;

import bcf;
import bcz;
import bni;
import bvi;
import on;

public class RealmsSliderButton
  extends RealmsButton
{
  public float value = 1.0F;
  public boolean sliding;
  private final float minValue;
  private final float maxValue;
  private int steps;
  
  public RealmsSliderButton(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    this(☃, ☃, ☃, ☃, ☃, 0, 1.0F, ☃);
  }
  
  public RealmsSliderButton(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, float ☃, float ☃)
  {
    super(☃, ☃, ☃, ☃, 20, "");
    this.minValue = ☃;
    this.maxValue = ☃;
    
    this.value = toPct(☃);
    getProxy().j = getMessage();
  }
  
  public String getMessage()
  {
    return "";
  }
  
  public float toPct(float ☃)
  {
    return on.a((clamp(☃) - this.minValue) / (this.maxValue - this.minValue), 0.0F, 1.0F);
  }
  
  public float toValue(float ☃)
  {
    return clamp(this.minValue + (this.maxValue - this.minValue) * on.a(☃, 0.0F, 1.0F));
  }
  
  public float clamp(float ☃)
  {
    ☃ = clampSteps(☃);
    return on.a(☃, this.minValue, this.maxValue);
  }
  
  protected float clampSteps(float ☃)
  {
    if (this.steps > 0) {
      ☃ = this.steps * Math.round(☃ / this.steps);
    }
    return ☃;
  }
  
  public int getYImage(boolean ☃)
  {
    return 0;
  }
  
  public void renderBg(int ☃, int ☃)
  {
    if (!getProxy().m) {
      return;
    }
    if (this.sliding)
    {
      this.value = ((☃ - (getProxy().h + 4)) / (getProxy().b() - 8));
      this.value = on.a(this.value, 0.0F, 1.0F);
      float ☃ = toValue(this.value);
      clicked(☃);
      this.value = toPct(☃);
      getProxy().j = getMessage();
    }
    bcf.z().N().a(WIDGETS_LOCATION);
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    blit(getProxy().h + (int)(this.value * (getProxy().b() - 8)), getProxy().i, 0, 66, 4, 20);
    blit(getProxy().h + (int)(this.value * (getProxy().b() - 8)) + 4, getProxy().i, 196, 66, 4, 20);
  }
  
  public void clicked(int ☃, int ☃)
  {
    this.value = ((☃ - (getProxy().h + 4)) / (getProxy().b() - 8));
    this.value = on.a(this.value, 0.0F, 1.0F);
    clicked(toValue(this.value));
    getProxy().j = getMessage();
    this.sliding = true;
  }
  
  public void clicked(float ☃) {}
  
  public void released(int ☃, int ☃)
  {
    this.sliding = false;
  }
}
