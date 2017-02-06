package de.labystudio.chat;

public abstract interface Callback
{
  public abstract void success();
  
  public abstract void failure();
  
  public abstract void failure(String paramString);
}
