package de.labystudio.slider;

public abstract interface SliderCallback
{
  public abstract void setValue(int paramInt);
  
  public abstract int getValue();
  
  public abstract String getTitle(int paramInt);
}
