package net.minecraft.realms;

import bdy;

public class RealmsClickableScrolledSelectionList
{
  private final bdy proxy;
  
  public RealmsClickableScrolledSelectionList(int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    this.proxy = new bdy(this, ☃, ☃, ☃, ☃, ☃);
  }
  
  public void render(int ☃, int ☃, float ☃)
  {
    this.proxy.a(☃, ☃, ☃);
  }
  
  public int width()
  {
    return this.proxy.e();
  }
  
  public int ym()
  {
    return this.proxy.f();
  }
  
  public int xm()
  {
    return this.proxy.g();
  }
  
  protected void renderItem(int ☃, int ☃, int ☃, int ☃, Tezzelator ☃, int ☃, int ☃) {}
  
  public void renderItem(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    renderItem(☃, ☃, ☃, ☃, Tezzelator.instance, ☃, ☃);
  }
  
  public int getItemCount()
  {
    return 0;
  }
  
  public void selectItem(int ☃, boolean ☃, int ☃, int ☃) {}
  
  public boolean isSelectedItem(int ☃)
  {
    return false;
  }
  
  public void renderBackground() {}
  
  public int getMaxPosition()
  {
    return 0;
  }
  
  public int getScrollbarPosition()
  {
    return this.proxy.e() / 2 + 124;
  }
  
  public void mouseEvent()
  {
    this.proxy.p();
  }
  
  public void customMouseEvent(int ☃, int ☃, int ☃, float ☃, int ☃) {}
  
  public void scroll(int ☃)
  {
    this.proxy.h(☃);
  }
  
  public int getScroll()
  {
    return this.proxy.n();
  }
  
  protected void renderList(int ☃, int ☃, int ☃, int ☃) {}
  
  public void itemClicked(int ☃, int ☃, int ☃, int ☃, int ☃) {}
  
  public void renderSelected(int ☃, int ☃, int ☃, Tezzelator ☃) {}
  
  public void setLeftPos(int ☃)
  {
    this.proxy.i(☃);
  }
}
