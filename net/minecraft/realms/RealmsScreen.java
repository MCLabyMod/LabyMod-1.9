package net.minecraft.realms;

import adq;
import bcf;
import bcv;
import bdz;
import bmq;
import bvi;
import bvw;
import bwo;
import com.mojang.util.UUIDTypeAdapter;
import java.util.List;
import kk;

public class RealmsScreen
{
  public static final int SKIN_HEAD_U = 8;
  public static final int SKIN_HEAD_V = 8;
  public static final int SKIN_HEAD_WIDTH = 8;
  public static final int SKIN_HEAD_HEIGHT = 8;
  public static final int SKIN_HAT_U = 40;
  public static final int SKIN_HAT_V = 8;
  public static final int SKIN_HAT_WIDTH = 8;
  public static final int SKIN_HAT_HEIGHT = 8;
  public static final int SKIN_TEX_WIDTH = 64;
  public static final int SKIN_TEX_HEIGHT = 64;
  protected bcf minecraft;
  public int width;
  public int height;
  private bdz proxy;
  
  public RealmsScreen()
  {
    this.proxy = new bdz(this);
  }
  
  public bdz getProxy()
  {
    return this.proxy;
  }
  
  public void init() {}
  
  public void init(bcf ☃, int ☃, int ☃) {}
  
  public void drawCenteredString(String ☃, int ☃, int ☃, int ☃)
  {
    this.proxy.a(☃, ☃, ☃, ☃);
  }
  
  public void drawString(String ☃, int ☃, int ☃, int ☃)
  {
    drawString(☃, ☃, ☃, ☃, true);
  }
  
  public void drawString(String ☃, int ☃, int ☃, int ☃, boolean ☃)
  {
    this.proxy.a(☃, ☃, ☃, ☃, false);
  }
  
  public void blit(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    this.proxy.b(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public static void blit(int ☃, int ☃, float ☃, float ☃, int ☃, int ☃, int ☃, int ☃, float ☃, float ☃)
  {
    bcv.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public static void blit(int ☃, int ☃, float ☃, float ☃, int ☃, int ☃, float ☃, float ☃)
  {
    bcv.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public void fillGradient(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    this.proxy.a(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public void renderBackground()
  {
    this.proxy.c();
  }
  
  public boolean isPauseScreen()
  {
    return this.proxy.d();
  }
  
  public void renderBackground(int ☃)
  {
    this.proxy.d_(☃);
  }
  
  public void render(int ☃, int ☃, float ☃)
  {
    for (int ☃ = 0; ☃ < this.proxy.j().size(); ☃++) {
      ((RealmsButton)this.proxy.j().get(☃)).render(☃, ☃);
    }
  }
  
  public void renderTooltip(adq ☃, int ☃, int ☃)
  {
    this.proxy.a(☃, ☃, ☃);
  }
  
  public void renderTooltip(String ☃, int ☃, int ☃)
  {
    this.proxy.a(☃, ☃, ☃);
  }
  
  public void renderTooltip(List<String> ☃, int ☃, int ☃)
  {
    this.proxy.a(☃, ☃, ☃);
  }
  
  public static void bindFace(String ☃, String ☃)
  {
    kk ☃ = bmq.e(☃);
    if (☃ == null) {
      ☃ = bvw.a(UUIDTypeAdapter.fromString(☃));
    }
    bmq.a(☃, ☃);
    bcf.z().N().a(☃);
  }
  
  public static void bind(String ☃)
  {
    kk ☃ = new kk(☃);
    bcf.z().N().a(☃);
  }
  
  public void tick() {}
  
  public int width()
  {
    return this.proxy.l;
  }
  
  public int height()
  {
    return this.proxy.m;
  }
  
  public int fontLineHeight()
  {
    return this.proxy.h();
  }
  
  public int fontWidth(String ☃)
  {
    return this.proxy.c(☃);
  }
  
  public void fontDrawShadow(String ☃, int ☃, int ☃, int ☃)
  {
    this.proxy.b(☃, ☃, ☃, ☃);
  }
  
  public List<String> fontSplit(String ☃, int ☃)
  {
    return this.proxy.a(☃, ☃);
  }
  
  public void buttonClicked(RealmsButton ☃) {}
  
  public static RealmsButton newButton(int ☃, int ☃, int ☃, String ☃)
  {
    return new RealmsButton(☃, ☃, ☃, ☃);
  }
  
  public static RealmsButton newButton(int ☃, int ☃, int ☃, int ☃, int ☃, String ☃)
  {
    return new RealmsButton(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public void buttonsClear()
  {
    this.proxy.i();
  }
  
  public void buttonsAdd(RealmsButton ☃)
  {
    this.proxy.a(☃);
  }
  
  public List<RealmsButton> buttons()
  {
    return this.proxy.j();
  }
  
  public void buttonsRemove(RealmsButton ☃)
  {
    this.proxy.b(☃);
  }
  
  public RealmsEditBox newEditBox(int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    return new RealmsEditBox(☃, ☃, ☃, ☃, ☃);
  }
  
  public void mouseClicked(int ☃, int ☃, int ☃) {}
  
  public void mouseEvent() {}
  
  public void keyboardEvent() {}
  
  public void mouseReleased(int ☃, int ☃, int ☃) {}
  
  public void mouseDragged(int ☃, int ☃, int ☃, long ☃) {}
  
  public void keyPressed(char ☃, int ☃) {}
  
  public void confirmResult(boolean ☃, int ☃) {}
  
  public static String getLocalizedString(String ☃)
  {
    return bwo.a(☃, new Object[0]);
  }
  
  public static String getLocalizedString(String ☃, Object... ☃)
  {
    return bwo.a(☃, ☃);
  }
  
  public RealmsAnvilLevelStorageSource getLevelStorageSource()
  {
    return new RealmsAnvilLevelStorageSource(bcf.z().g());
  }
  
  public void removed() {}
}
