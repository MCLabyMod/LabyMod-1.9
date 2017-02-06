package net.minecraft.realms;

import bcf;
import bcz;
import bdx;
import kk;

public class RealmsButton
{
  protected static final kk WIDGETS_LOCATION = new kk("textures/gui/widgets.png");
  private bdx proxy;
  
  public RealmsButton(int ☃, int ☃, int ☃, String ☃)
  {
    this.proxy = new bdx(this, ☃, ☃, ☃, ☃);
  }
  
  public RealmsButton(int ☃, int ☃, int ☃, int ☃, int ☃, String ☃)
  {
    this.proxy = new bdx(this, ☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public bcz getProxy()
  {
    return this.proxy;
  }
  
  public int id()
  {
    return this.proxy.c();
  }
  
  public boolean active()
  {
    return this.proxy.d();
  }
  
  public void active(boolean ☃)
  {
    this.proxy.b(☃);
  }
  
  public void msg(String ☃)
  {
    this.proxy.a(☃);
  }
  
  public int getWidth()
  {
    return this.proxy.b();
  }
  
  public int getHeight()
  {
    return this.proxy.g();
  }
  
  public int y()
  {
    return this.proxy.e();
  }
  
  public void render(int ☃, int ☃)
  {
    this.proxy.a(bcf.z(), ☃, ☃);
  }
  
  public void clicked(int ☃, int ☃) {}
  
  public void released(int ☃, int ☃) {}
  
  public void blit(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    this.proxy.b(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public void renderBg(int ☃, int ☃) {}
  
  public int getYImage(boolean ☃)
  {
    return this.proxy.c(☃);
  }
}
