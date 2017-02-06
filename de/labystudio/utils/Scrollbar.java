package de.labystudio.utils;

import bmz;
import bni;
import bnu;
import bvp;
import de.labystudio.labymod.LabyMod;
import org.lwjgl.input.Mouse;

public class Scrollbar
{
  private int listSize;
  private int entryHeight;
  private int scrollY;
  private double barLength;
  private double backLength;
  private int posTop;
  private int posBottom;
  private int left;
  private int top;
  private int right;
  private int speed;
  int clickY;
  boolean hold;
  
  public int getScrollY()
  {
    return this.scrollY;
  }
  
  public void reset()
  {
    this.scrollY = 0;
  }
  
  public void setScrollY(int scrollY)
  {
    this.scrollY = scrollY;
  }
  
  public void setSpeed(int speed)
  {
    this.speed = speed;
  }
  
  public int getSpeed()
  {
    return this.speed;
  }
  
  public void init()
  {
    setDefaultPosition();
  }
  
  public void setEntryHeight(int entryHeight)
  {
    this.entryHeight = entryHeight;
  }
  
  public Scrollbar(int entryHeight)
  {
    this.entryHeight = entryHeight;
    setDefaultPosition();
  }
  
  public void update(int listSize)
  {
    this.listSize = (listSize + 1);
  }
  
  public void setPosition(int left, int top, int right, int bottom)
  {
    this.left = left;
    this.posTop = top;
    this.right = right;
    this.posBottom = bottom;
    calc();
  }
  
  private void calc()
  {
    double s = this.entryHeight;
    double totalPixels = this.listSize * this.entryHeight;
    double backLength = this.posBottom - this.posTop;
    double pixelInView = backLength;
    if (pixelInView >= totalPixels) {
      return;
    }
    double dPixelInView = pixelInView;
    double dTotalPixels = totalPixels;
    double scale = dPixelInView / dTotalPixels;
    double barLength = scale * backLength;
    double scroll = this.scrollY / scale * scale * scale;
    this.top = ((int)-scroll + this.posTop);
    this.barLength = barLength;
    this.backLength = backLength;
  }
  
  public void setDefaultPosition()
  {
    setPosition(LabyMod.getInstance().draw.getWidth() / 2 + 150, 40, LabyMod.getInstance().draw.getWidth() / 2 + 156, LabyMod.getInstance().draw.getHeight() - 40);
  }
  
  public boolean isHidden()
  {
    if (this.listSize == 0) {
      return true;
    }
    if (this.posBottom - this.posTop >= this.listSize * this.entryHeight) {
      return true;
    }
    return false;
  }
  
  public void draw()
  {
    if (isHidden()) {
      return;
    }
    calc();
    bnu tessellator = bnu.a();
    bmz worldrenderer = tessellator.c();
    bni.m();
    bni.a(770, 771, 0, 1);
    bni.d();
    bni.j(7425);
    bni.z();
    worldrenderer.a(7, bvp.i);
    worldrenderer.b(this.left, this.posBottom, 0.0D).a(0.0D, 1.0D).b(0, 0, 0, 255).d();
    worldrenderer.b(this.right, this.posBottom, 0.0D).a(1.0D, 1.0D).b(0, 0, 0, 255).d();
    worldrenderer.b(this.right, this.posTop, 0.0D).a(1.0D, 0.0D).b(0, 0, 0, 255).d();
    worldrenderer.b(this.left, this.posTop, 0.0D).a(0.0D, 0.0D).b(0, 0, 0, 255).d();
    tessellator.b();
    worldrenderer.a(7, bvp.i);
    worldrenderer.b(this.left, this.top + this.barLength, 0.0D).a(0.0D, 1.0D).b(128, 128, 128, 255).d();
    worldrenderer.b(this.right, this.top + this.barLength, 0.0D).a(1.0D, 1.0D).b(128, 128, 128, 255).d();
    worldrenderer.b(this.right, this.top, 0.0D).a(1.0D, 0.0D).b(128, 128, 128, 255).d();
    worldrenderer.b(this.left, this.top, 0.0D).a(0.0D, 0.0D).b(128, 128, 128, 255).d();
    tessellator.b();
    worldrenderer.a(7, bvp.i);
    worldrenderer.b(this.left, this.top + this.barLength - 1.0D, 0.0D).a(0.0D, 1.0D).b(192, 192, 192, 255).d();
    worldrenderer.b(this.right - 1, this.top + this.barLength - 1.0D, 0.0D).a(1.0D, 1.0D).b(192, 192, 192, 255).d();
    worldrenderer.b(this.right - 1, this.top, 0.0D).a(1.0D, 0.0D).b(192, 192, 192, 255).d();
    worldrenderer.b(this.left, this.top, 0.0D).a(0.0D, 0.0D).b(192, 192, 192, 255).d();
    tessellator.b();
    bni.y();
    bni.j(7424);
    bni.e();
    bni.l();
  }
  
  public void mouseAction(int x, int y, boolean drag)
  {
    calc();
    boolean click = false;
    if (!drag)
    {
      this.hold = false;
      if ((x < this.right) && (x > this.left) && (y > this.top) && (y < this.top + this.barLength))
      {
        this.hold = true;
      }
      else if ((x < this.right) && (x > this.left) && (y > this.posTop) && (y < this.posBottom))
      {
        drag = true;
        click = true;
      }
    }
    else if (!this.hold)
    {
      return;
    }
    int b = this.scrollY;
    double scale = this.backLength / (this.listSize * this.entryHeight);
    int calc = (int)(-y / scale);
    if (drag) {
      this.scrollY = (calc - this.clickY);
    } else {
      this.clickY = (calc - this.scrollY);
    }
    if ((this.listSize * this.entryHeight + this.scrollY < this.posBottom - this.posTop) && 
      (!click)) {
      this.scrollY = b;
    }
    if ((this.scrollY > 0) && 
      (!click)) {
      this.scrollY = b;
    }
    if (click)
    {
      if (this.listSize * this.entryHeight + this.scrollY < this.posBottom - this.posTop) {
        this.scrollY += this.posBottom - this.posTop - (this.listSize * this.entryHeight + this.scrollY);
      }
      if (this.scrollY > 0) {
        this.scrollY = 0;
      }
    }
  }
  
  public void mouseInput()
  {
    int wheel = Mouse.getEventDWheel();
    if (wheel > 0)
    {
      if (this.scrollY < 0) {
        this.scrollY += this.speed;
      }
    }
    else if ((wheel < 0) && 
      (this.listSize * this.entryHeight + this.scrollY > this.posBottom - this.posTop)) {
      this.scrollY -= this.speed;
    }
    if (this.listSize * this.entryHeight + this.scrollY < this.posBottom - this.posTop) {
      this.scrollY += this.posBottom - this.posTop - (this.listSize * this.entryHeight + this.scrollY);
    }
    if (this.scrollY > 0) {
      this.scrollY = 0;
    }
  }
}
