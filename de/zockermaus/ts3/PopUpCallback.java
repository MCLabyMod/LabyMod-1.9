package de.zockermaus.ts3;

public abstract interface PopUpCallback
{
  public abstract void cancel();
  
  public abstract void ok();
  
  public abstract void ok(int paramInt, String paramString);
  
  public abstract boolean tick(int paramInt);
}
