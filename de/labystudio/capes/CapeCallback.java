package de.labystudio.capes;

public abstract interface CapeCallback
{
  public abstract void done();
  
  public abstract void failed(String paramString);
}
