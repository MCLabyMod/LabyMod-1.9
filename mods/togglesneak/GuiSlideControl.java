package mods.togglesneak;

import bcf;
import bcz;
import java.text.DecimalFormat;
import org.lwjgl.opengl.GL11;

public class GuiSlideControl
  extends bcz
{
  public String label;
  public float curValue;
  public float minValue;
  public float maxValue;
  public boolean isSliding;
  public boolean useIntegers;
  private static DecimalFormat numFormat = new DecimalFormat("#.00");
  
  public GuiSlideControl(int id, int x, int y, int width, int height, String displayString, float minVal, float maxVal, float curVal, boolean useInts)
  {
    super(id, x, y, width, height, displayString + numFormat.format(curVal));
    
    this.label = displayString;
    this.minValue = minVal;
    this.maxValue = maxVal;
    this.curValue = ((curVal - minVal) / (maxVal - minVal));
    this.useIntegers = useInts;
  }
  
  public float GetValueAsFloat()
  {
    return (this.maxValue - this.minValue) * this.curValue + this.minValue;
  }
  
  public int GetValueAsInt()
  {
    return (int)((this.maxValue - this.minValue) * this.curValue + this.minValue);
  }
  
  protected float roundValue(float value)
  {
    value = 0.01F * Math.round(value / 0.01F);
    return value;
  }
  
  public String GetLabel()
  {
    if (this.useIntegers) {
      return this.label + GetValueAsInt();
    }
    return this.label + numFormat.format(GetValueAsFloat());
  }
  
  protected void SetLabel()
  {
    this.j = GetLabel();
  }
  
  protected int a(boolean isMouseOver)
  {
    return 0;
  }
  
  protected void b(bcf mc, int mousePosX, int mousePosY)
  {
    if (this.m)
    {
      if (this.isSliding)
      {
        this.curValue = roundValue((mousePosX - (this.h + 4)) / (this.f - 8));
        if (this.curValue < 0.0F) {
          this.curValue = 0.0F;
        }
        if (this.curValue > 1.0F) {
          this.curValue = 1.0F;
        }
        SetLabel();
      }
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      b(this.h + (int)(this.curValue * (this.f - 8)), this.i, 0, 66, 4, 20);
      b(this.h + (int)(this.curValue * (this.f - 8)) + 4, this.i, 196, 66, 4, 20);
    }
  }
  
  public boolean c(bcf mc, int mousePosX, int mousePosY)
  {
    if (super.c(mc, mousePosX, mousePosY))
    {
      this.curValue = roundValue((mousePosX - (this.h + 4)) / (this.f - 8));
      if (this.curValue < 0.0F) {
        this.curValue = 0.0F;
      }
      if (this.curValue > 1.0F) {
        this.curValue = 1.0F;
      }
      SetLabel();
      this.isSliding = true;
      return true;
    }
    return false;
  }
  
  public void a(int mousePosX, int mousePosY)
  {
    this.isSliding = false;
  }
}
