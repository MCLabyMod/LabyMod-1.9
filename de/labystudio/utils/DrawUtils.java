package de.labystudio.utils;

import adq;
import bcd;
import bcf;
import bct;
import bcv;
import bcx;
import bmz;
import bni;
import bnu;
import brm;
import brz;
import bvi;
import bvp;
import bzg;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.ModSettings;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.GL11;
import sa;

public class DrawUtils
  extends bcv
{
  private bcf mc;
  private final brz itemRenderer;
  public bct fontRenderer;
  private static int mouseX = 0;
  private static int mouseY = 0;
  
  public DrawUtils()
  {
    this.mc = bcf.z();
    this.itemRenderer = this.mc.ad();
    this.fontRenderer = this.mc.k;
  }
  
  public brz getItemRenderer()
  {
    return this.itemRenderer;
  }
  
  public bct getFontRenderer()
  {
    return this.fontRenderer;
  }
  
  public static void updateMouse(int x, int y)
  {
    if ((x == 0) && (y == 0)) {
      return;
    }
    mouseX = x;
    mouseY = y;
  }
  
  public static int getMouseX()
  {
    return mouseX;
  }
  
  public static int getMouseY()
  {
    return mouseY;
  }
  
  public void addRightLabel(String prefix, String text, int slot)
  {
    drawRightString(ModGui.createLabel(prefix, text), (int)((getWidth() - 2) / getScale(ConfigManager.settings.size)), slot);
  }
  
  public void addLabel(String prefix, String text, int slot)
  {
    drawString(ModGui.createLabel(prefix, text), 2.0D, slot);
  }
  
  public void addNoScaleLabel(String prefix, String text, int slot)
  {
    if (ConfigManager.settings.guiPositionRight.booleanValue()) {
      drawRightString(ModGui.createLabel(prefix, text), getWidth() - 2, slot);
    } else {
      drawString(ModGui.createLabel(prefix, text), 2.0D, slot);
    }
  }
  
  public void addString(String text, int slot)
  {
    if (ConfigManager.settings.guiPositionRight.booleanValue()) {
      drawRightString(text, (int)((getWidth() - 2) / getScale(ConfigManager.settings.size)), slot);
    } else {
      drawString(text, 2.0D, slot);
    }
  }
  
  public void addRightString(String text, int slot)
  {
    if (!ConfigManager.settings.guiPositionRight.booleanValue()) {
      drawRightString(text, (int)((getWidth() - 2) / getScale(ConfigManager.settings.size)), slot);
    } else {
      drawString(text, 2.0D, slot);
    }
  }
  
  public void addNoScaleString(String text, int slot)
  {
    if (ConfigManager.settings.guiPositionRight.booleanValue()) {
      drawRightString(text, getWidth() - 2, slot);
    } else {
      drawString(text, 2.0D, slot);
    }
  }
  
  public void drawRightString(String text, double x, double y)
  {
    drawString(text, x - getStringWidth(text), y);
  }
  
  public void drawString(String text, double x, double y)
  {
    c(this.fontRenderer, text, (int)x, (int)y, 16777215);
  }
  
  public void drawString(String text, double x, double y, double size)
  {
    GL11.glPushMatrix();
    GL11.glScaled(size, size, size);
    c(this.fontRenderer, text, (int)(x / size), (int)(y / size), 16777215);
    GL11.glPopMatrix();
  }
  
  public void drawCenteredString(String text, int x, int y)
  {
    a(this.fontRenderer, text, x, y, 16777215);
  }
  
  public void drawCenteredString(String text, double x, double y, double size)
  {
    GL11.glPushMatrix();
    GL11.glScaled(size, size, size);
    a(this.fontRenderer, text, (int)(x / size), (int)(y / size), 16777215);
    GL11.glPopMatrix();
  }
  
  public void drawRightString(String text, double x, double y, double size)
  {
    GL11.glPushMatrix();
    GL11.glScaled(size, size, size);
    c(this.fontRenderer, text, (int)(x / size - getStringWidth(text)), (int)(y / size), 16777215);
    GL11.glPopMatrix();
  }
  
  public void drawCenteredStringWithoutShadow(String text, double x, double y, double size)
  {
    GL11.glPushMatrix();
    GL11.glScaled(size, size, size);
    this.fontRenderer.a(text, (int)(x / size - this.fontRenderer.a(text) / 2), (int)(y / size), 16777215);
    GL11.glPopMatrix();
  }
  
  public void drawItem(adq item, int x, int y)
  {
    bcd.c();
    this.itemRenderer.b(item, x, y);
    this.itemRenderer.a(this.fontRenderer, item, x, y, "");
    this.itemRenderer.a(this.fontRenderer, item, x, y);
    bni.g();
  }
  
  public void drawRightItem(adq item, int x, int y)
  {
    GL11.glPushMatrix();
    GL11.glTranslated((100 - ConfigManager.settings.size) / 5, 0.0D, 0.0D);
    bcd.c();
    this.itemRenderer.b(item, x, y);
    this.itemRenderer.a(this.fontRenderer, item, x, y, "");
    this.itemRenderer.a(this.fontRenderer, item, x, y);
    bni.g();
    GL11.glPopMatrix();
  }
  
  public void drawItem(adq item, double x, double y, String s)
  {
    bcd.c();
    this.itemRenderer.b(item, (int)x, (int)y);
    this.itemRenderer.a(this.fontRenderer, item, (int)x, (int)y, s);
    bni.g();
  }
  
  public void bindRightString(String text, int y)
  {
    drawString(text, getWidth() - getStringWidth(text) - 2, y);
  }
  
  public int getHeight()
  {
    bcx res = new bcx(this.mc);
    return res.b();
  }
  
  public int getWidth()
  {
    bcx res = new bcx(this.mc);
    return res.a();
  }
  
  public int getRight(String text)
  {
    return this.fontRenderer.a(text) - 2;
  }
  
  public void addRightString(String prefix, String text, int slot)
  {
    c(this.fontRenderer, ModGui.createLabel(prefix, text), this.mc.d - getRight(ModGui.createLabel(prefix, text)) - 2, slot, 16777215);
  }
  
  public List<String> splitEqually(String text, int size)
  {
    List<String> ret = new ArrayList((text.length() + size - 1) / size);
    for (int start = 0; start < text.length(); start += size) {
      ret.add(text.substring(start, Math.min(text.length(), start + size)));
    }
    return ret;
  }
  
  public int getRightScreen(int settings)
  {
    String s = "";
    for (int u = 0; u <= settings; u++) {
      s = s + " ";
    }
    bcx res = new bcx(this.mc);
    return res.a() - this.fontRenderer.a(s) - 5;
  }
  
  public int getMiddleScreen(int settings)
  {
    String s = "";
    for (int u = 0; u <= settings; u++) {
      s = s + " ";
    }
    bcx res = new bcx(this.mc);
    return res.a() / 2 - this.fontRenderer.a(s);
  }
  
  public int getStringWidth(String text)
  {
    return this.fontRenderer.a(text);
  }
  
  public double getScale(int scale)
  {
    int s = scale;
    if (s < 50) {
      s = 25 + s / 2;
    }
    return s * 0.02D;
  }
  
  public void drawRect(double left, double top, double right, double bottom, int color)
  {
    a((int)left, (int)top, (int)right, (int)bottom, color);
  }
  
  public void drawBox(int left, int top, int right, int bottom)
  {
    bni.d(1.0F, 1.0F, 1.0F);
    GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.9F);
    a(left, top, right, bottom, Color.WHITE.getRGB());
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.9F);
    a(left, top, right, top + 1, Color.WHITE.getRGB());
    a(left, top, left + 1, bottom, Color.WHITE.getRGB());
    a(right - 1, top, right, bottom, Color.WHITE.getRGB());
    a(left, bottom - 1, right, bottom, Color.WHITE.getRGB());
  }
  
  public void drawBackground(int tint)
  {
    bni.g();
    bni.p();
    bnu tessellator = bnu.a();
    bmz worldrenderer = tessellator.c();
    this.mc.N().a(b);
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    float f = 32.0F;
    worldrenderer.a(7, bvp.i);
    worldrenderer.b(0.0D, getHeight(), 0.0D).a(0.0D, getHeight() / 32.0F + tint).b(64, 64, 64, 255).d();
    worldrenderer.b(getWidth(), getHeight(), 0.0D).a(getWidth() / 32.0F, getHeight() / 32.0F + tint).b(64, 64, 64, 255).d();
    worldrenderer.b(getWidth(), 0.0D, 0.0D).a(getWidth() / 32.0F, tint).b(64, 64, 64, 255).d();
    worldrenderer.b(0.0D, 0.0D, 0.0D).a(0.0D, tint).b(64, 64, 64, 255).d();
    tessellator.b();
  }
  
  public void drawChatBackground(int left, int top, int right, int bottom)
  {
    bni.g();
    bni.p();
    bnu tessellator = bnu.a();
    bmz worldrenderer = tessellator.c();
    this.mc.N().a(bcv.b);
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    float f = 32.0F;
    int amountScrolled = 0;
    worldrenderer.a(7, bvp.i);
    worldrenderer.b(left, bottom, 0.0D).a(left / f, (bottom + amountScrolled) / f).b(32, 32, 32, 255).d();
    worldrenderer.b(right, bottom, 0.0D).a(right / f, (bottom + amountScrolled) / f).b(32, 32, 32, 255).d();
    worldrenderer.b(right, top, 0.0D).a(right / f, (top + amountScrolled) / f).b(32, 32, 32, 255).d();
    worldrenderer.b(left, top, 0.0D).a(left / f, (top + amountScrolled) / f).b(32, 32, 32, 255).d();
    tessellator.b();
    bni.m();
    bni.a(770, 771, 0, 1);
    bni.d();
    bni.j(7425);
    bni.j();
    int i1 = 4;
    bni.m();
    bni.a(770, 771, 0, 1);
    bni.d();
    bni.j(7425);
    bni.y();
    worldrenderer.a(7, bvp.i);
    worldrenderer.b(left, top + i1, 0.0D).a(0.0D, 1.0D).b(0, 0, 0, 0).d();
    worldrenderer.b(right, top + i1, 0.0D).a(1.0D, 1.0D).b(0, 0, 0, 0).d();
    worldrenderer.b(right, top, 0.0D).a(1.0D, 0.0D).b(0, 0, 0, 255).d();
    worldrenderer.b(left, top, 0.0D).a(0.0D, 0.0D).b(0, 0, 0, 255).d();
    tessellator.b();
    worldrenderer.a(7, bvp.i);
    worldrenderer.b(left, bottom, 0.0D).a(0.0D, 1.0D).b(0, 0, 0, 255).d();
    worldrenderer.b(right, bottom, 0.0D).a(1.0D, 1.0D).b(0, 0, 0, 255).d();
    worldrenderer.b(right, bottom - i1, 0.0D).a(1.0D, 0.0D).b(0, 0, 0, 0).d();
    worldrenderer.b(left, bottom - i1, 0.0D).a(0.0D, 0.0D).b(0, 0, 0, 0).d();
    tessellator.b();
    bni.j(7424);
    bni.e();
    bni.l();
  }
  
  public void drawTransparentBackground(int left, int top, int right, int bottom)
  {
    try
    {
      bni.g();
      bni.p();
      bnu tessellator = bnu.a();
      bmz worldrenderer = tessellator.c();
      this.mc.N().a(bcv.b);
      float f = 32.0F;
      int amountScrolled = 0;
      a(left, top, right, bottom, Integer.MIN_VALUE);
      
      bni.m();
      bni.a(770, 771, 0, 1);
      bni.d();
      bni.j(7425);
      bni.j();
      int i1 = 4;
      bni.m();
      bni.a(770, 771, 0, 1);
      bni.d();
      bni.j(7425);
      bni.y();
      worldrenderer.a(7, bvp.i);
      worldrenderer.b(left, top + i1, 0.0D).a(0.0D, 1.0D).b(0, 0, 0, 0).d();
      worldrenderer.b(right, top + i1, 0.0D).a(1.0D, 1.0D).b(0, 0, 0, 0).d();
      worldrenderer.b(right, top, 0.0D).a(1.0D, 0.0D).b(0, 0, 0, 255).d();
      worldrenderer.b(left, top, 0.0D).a(0.0D, 0.0D).b(0, 0, 0, 255).d();
      tessellator.b();
      worldrenderer.a(7, bvp.i);
      worldrenderer.b(left, bottom, 0.0D).a(0.0D, 1.0D).b(0, 0, 0, 255).d();
      worldrenderer.b(right, bottom, 0.0D).a(1.0D, 1.0D).b(0, 0, 0, 255).d();
      worldrenderer.b(right, bottom - i1, 0.0D).a(1.0D, 0.0D).b(0, 0, 0, 0).d();
      worldrenderer.b(left, bottom - i1, 0.0D).a(0.0D, 0.0D).b(0, 0, 0, 0).d();
      tessellator.b();
      bni.j(7424);
      bni.e();
    }
    catch (Exception error)
    {
      error.printStackTrace();
    }
  }
  
  public void overlayBackground(int startY, int endY)
  {
    int endAlpha = 255;
    int startAlpha = 255;
    bnu tessellator = bnu.a();
    bmz worldrenderer = tessellator.c();
    this.mc.N().a(bcv.b);
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    float f = 32.0F;
    worldrenderer.a(7, bvp.i);
    worldrenderer.b(0.0D, endY, 0.0D).a(0.0D, endY / 32.0F).b(64, 64, 64, endAlpha).d();
    worldrenderer.b(0 + getWidth(), endY, 0.0D).a(getWidth() / 32.0F, endY / 32.0F).b(64, 64, 64, endAlpha).d();
    worldrenderer.b(0 + getWidth(), startY, 0.0D).a(getWidth() / 32.0F, startY / 32.0F).b(64, 64, 64, startAlpha).d();
    worldrenderer.b(0.0D, startY, 0.0D).a(0.0D, startY / 32.0F).b(64, 64, 64, startAlpha).d();
    tessellator.b();
  }
  
  public void overlayBackground(int startX, int startY, int endX, int endY)
  {
    int endAlpha = 255;
    int startAlpha = 255;
    bnu tessellator = bnu.a();
    bmz worldrenderer = tessellator.c();
    this.mc.N().a(bcv.b);
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    float f = 32.0F;
    worldrenderer.a(7, bvp.i);
    worldrenderer.b(startX, endY, 0.0D).a(0.0D, endY / 32.0F).b(64, 64, 64, endAlpha).d();
    worldrenderer.b(startX + endX, endY, 0.0D).a(endX / 32.0F, endY / 32.0F).b(64, 64, 64, endAlpha).d();
    worldrenderer.b(startX + endX, startY, 0.0D).a(endX / 32.0F, startY / 32.0F).b(64, 64, 64, startAlpha).d();
    worldrenderer.b(startX, startY, 0.0D).a(0.0D, startY / 32.0F).b(64, 64, 64, startAlpha).d();
    tessellator.b();
  }
  
  public void drawTexturedModalRect(double x, double y, double textureX, double textureY, double width, double height)
  {
    b((int)x, (int)y, (int)textureX, (int)textureY, (int)width, (int)height);
  }
  
  public void drawTexturedModalRect(double left, double top, double right, double bottom)
  {
    double textureX = 0.0D;
    double textureY = 0.0D;
    double x = left;
    double y = top;
    double width = right - left;
    double height = bottom - top;
    float f = 0.00390625F;
    float f1 = 0.00390625F;
    bnu tessellator = bnu.a();
    bmz worldrenderer = tessellator.c();
    worldrenderer.a(7, bvp.g);
    worldrenderer.b(x + 0.0D, y + height, this.e).a((float)(textureX + 0.0D) * f, (float)(textureY + height) * f1).d();
    worldrenderer.b(x + width, y + height, this.e).a((float)(textureX + width) * f, (float)(textureY + height) * f1).d();
    worldrenderer.b(x + width, y + 0.0D, this.e).a((float)(textureX + width) * f, (float)(textureY + 0.0D) * f1).d();
    worldrenderer.b(x + 0.0D, y + 0.0D, this.e).a((float)(textureX + 0.0D) * f, (float)(textureY + 0.0D) * f1).d();
    tessellator.b();
  }
  
  public static void drawEntityOnScreen(int x, int y, int size, float mouseX, float mouseY, int rotation, sa entity)
  {
    bni.h();
    bni.G();
    bni.c(x, y, 50.0F);
    bni.b(-size - 30.0F, size + 30.0F, size);
    bni.b(180.0F, 0.0F, 0.0F, 1.0F);
    float var6 = entity.aM;
    float var7 = entity.v;
    float var8 = entity.w;
    float var9 = entity.aP;
    float var10 = entity.aO;
    bni.b(135.0F, 0.0F, 1.0F, 0.0F);
    bcd.b();
    bni.b(-135.0F + rotation, 0.0F, 1.0F, 0.0F);
    bni.b(-(float)Math.atan(mouseY / 40.0F) * 20.0F, 1.0F, 0.0F, 0.0F);
    entity.aM = ((float)Math.atan(mouseX / 40.0F) * 20.0F);
    entity.v = ((float)Math.atan(mouseX / 40.0F) * 40.0F);
    entity.w = (-(float)Math.atan(mouseY / 40.0F) * 20.0F);
    entity.aO = entity.v;
    entity.aP = entity.v;
    bni.c(0.0F, 0.0F, 0.0F);
    brm var11 = bcf.z().ac();
    var11.a(180.0F);
    var11.a(false);
    var11.a(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
    var11.a(true);
    entity.aM = var6;
    entity.v = var7;
    entity.w = var8;
    entity.aP = var9;
    entity.aO = var10;
    bni.H();
    bcd.a();
    bni.E();
    bni.g(bzg.r);
    bni.z();
    bni.g(bzg.q);
  }
}
