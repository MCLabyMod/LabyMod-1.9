package de.labystudio.utils;

import aig;
import bbh;
import bbi;
import bbj;
import bcd;
import bcf;
import bku;
import bmt;
import bni;
import brm;
import bzg;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.ModSettings;
import de.labystudio.labymod.Timings;
import java.math.BigDecimal;
import java.util.List;
import on;
import rr;
import sa;
import xs;
import zi;

public class ModGui
{
  public static int mainList = 0;
  public static int offList = 2;
  public static rr pointedEntity = null;
  public static int frames = 0;
  public static int fps = 0;
  public static long frameTimer = 0L;
  
  public static String translateTimer(int time)
  {
    Timings.start("Translate Timer");
    String formata = time / 60 < 10 ? "0" + time / 60 : Integer.toString(time / 60);
    String formatb = time % 60 < 10 ? "0" + time % 60 : Integer.toString(time % 60);
    Timings.stop("Translate Timer");
    return formata + ":" + formatb;
  }
  
  public static String shortString(String s, int i)
  {
    if (s.length() > i) {
      s = s.substring(0, i);
    }
    return s;
  }
  
  static int smoothFPS = 0;
  
  public static void smoothFPS()
  {
    Timings.start("Smooth FPS");
    if (!ConfigManager.settings.smoothFPS) {
      return;
    }
    try
    {
      int fps = getRealFPS();
      if (smoothFPS == 0) {
        smoothFPS = fps;
      }
      if (fps > smoothFPS) {
        smoothFPS += 1;
      }
      if (fps < smoothFPS) {
        smoothFPS -= 1;
      }
    }
    catch (Exception error)
    {
      smoothFPS = 0;
    }
    Timings.stop("Smooth FPS");
  }
  
  public static boolean isNotEmpty(int slot)
  {
    if (slot == -1) {
      return bcf.z().h.br.h() != null;
    }
    return bcf.z().h.br.g(slot) != null;
  }
  
  public static int getFPS()
  {
    if (ConfigManager.settings.smoothFPS) {
      return smoothFPS;
    }
    return getRealFPS();
  }
  
  public static String getF()
  {
    Timings.start("Calculate F Direction");
    if (!LabyMod.getInstance().isInGame()) {
      return "0.0 ";
    }
    double f = on.g(bcf.z().h.v);
    if (f <= 0.0D) {
      f += 360.0D;
    }
    f /= 8.0D;
    f /= 11.0D;
    String output = "" + String.valueOf(f).charAt(0) + String.valueOf(f).charAt(1) + String.valueOf(f).charAt(2);
    if ((output.equals("4.0")) || (output.startsWith("9"))) {
      output = "0.0";
    }
    Timings.stop("Calculate F Direction");
    return output + " ";
  }
  
  public static String getD()
  {
    Timings.start("Calculate F Direction String");
    String XZD = getXZD();
    if (XZD.contains("Z-")) {
      return "North ";
    }
    if (XZD.contains("X+")) {
      return "East ";
    }
    if (XZD.contains("Z+")) {
      return "South ";
    }
    if (XZD.contains("X-")) {
      return "West ";
    }
    Timings.stop("Calculate F Direction String");
    return "";
  }
  
  public static String getXZD()
  {
    double f = Double.parseDouble(getF());
    String a = "[";
    String b = ", ";
    String c = "]";
    String xP = "X+";
    String xN = "X-";
    String zP = "Z+";
    String zN = "Z-";
    if (ConfigManager.settings.layout == 1)
    {
      a = "";
      b = " ";
      c = "";
    }
    if (ConfigManager.settings.layout == 2)
    {
      a = "[";
      b = ", ";
      c = "]";
    }
    if (ConfigManager.settings.layout == 3)
    {
      a = "<";
      b = ", ";
      c = ">";
    }
    if (ConfigManager.settings.layout == 4)
    {
      a = "(";
      b = ", ";
      c = ")";
    }
    a = Color.c(2) + a + Color.c(1);
    b = Color.c(2) + b + Color.c(1);
    c = Color.c(2) + c;
    if ((f >= 0.0D) && (f < 0.3D)) {
      return a + zP + c;
    }
    if ((f > 0.2D) && (f < 0.8D)) {
      return a + xN + b + zP + c;
    }
    if ((f > 0.7D) && (f < 1.4D)) {
      return a + xN + c;
    }
    if ((f > 1.3D) && (f < 1.8D)) {
      return a + xN + b + zN + c;
    }
    if ((f > 1.7D) && (f < 2.4D)) {
      return a + zN + c;
    }
    if ((f > 2.3D) && (f < 2.8D)) {
      return a + xP + b + zN + c;
    }
    if ((f > 2.7D) && (f < 3.4D)) {
      return a + xP + c;
    }
    if ((f > 3.3D) && (f < 3.8D)) {
      return a + xP + b + zP + c;
    }
    if ((f > 3.7D) && (f <= 4.0D)) {
      return a + zP + c;
    }
    return "";
  }
  
  public static int getRealFPS()
  {
    return fps;
  }
  
  public static String getBiom()
  {
    if (bcf.z().f == null) {
      return "?";
    }
    if (bcf.z().h == null) {
      return "?";
    }
    if (bcf.z().h.c() == null) {
      return "?";
    }
    aig gen = bcf.z().f.b(bcf.z().h.c());
    if (gen == null) {
      return "?";
    }
    return gen.l();
  }
  
  public static String getX()
  {
    if (!LabyMod.getInstance().isInGame()) {
      return "?";
    }
    return truncateCoords(bcf.z().h.p);
  }
  
  public static String getY()
  {
    if (!LabyMod.getInstance().isInGame()) {
      return "?";
    }
    return truncateCoords(bcf.z().h.q);
  }
  
  public static String getZ()
  {
    if (!LabyMod.getInstance().isInGame()) {
      return "?";
    }
    return truncateCoords(bcf.z().h.r);
  }
  
  public static String truncateCoords(double i)
  {
    Timings.start("Truncate Coords");
    String a = "" + (int)i;
    if (ConfigManager.settings.truncateCoords != 0) {
      a = "" + truncateDecimal(i, ConfigManager.settings.truncateCoords);
    }
    Timings.stop("Truncate Coords");
    return a;
  }
  
  private static BigDecimal truncateDecimal(double x, int numberofDecimals)
  {
    try
    {
      if (x > 0.0D) {
        return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, 3);
      }
      return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, 2);
    }
    catch (Exception error) {}
    return new BigDecimal(0);
  }
  
  public static double truncateTo(double number, int amount)
  {
    int truncatedNumberInt = (int)(number * Math.pow(10.0D, amount));
    double truncatedNumber = truncatedNumberInt / Math.pow(10.0D, amount);
    return truncatedNumber;
  }
  
  public static String createLabel(String get, String set)
  {
    if (ConfigManager.settings.layout == 0) {
      return "";
    }
    if (ConfigManager.settings.layout == 1) {
      return Color.c(1) + get + Color.c(2) + ": " + Color.c(3) + set;
    }
    if (ConfigManager.settings.layout == 2) {
      return Color.c(2) + "[" + Color.c(1) + get + Color.c(2) + "] " + Color.c(3) + set;
    }
    if (ConfigManager.settings.layout == 3) {
      return Color.c(1) + get + Color.c(2) + "> " + Color.c(3) + set;
    }
    if (ConfigManager.settings.layout == 4) {
      return Color.c(2) + "(" + Color.c(1) + get + Color.c(2) + ") " + Color.c(3) + set;
    }
    return "Error";
  }
  
  public static void addMainLabel(String prefix, String text, int y)
  {
    if (isSwitch()) {
      LabyMod.getInstance().draw.addRightLabel(prefix, text, y);
    } else {
      LabyMod.getInstance().draw.addLabel(prefix, text, y);
    }
    mainListNext();
  }
  
  public static void addOffLabel(String prefix, String text, int y)
  {
    if (isSwitch()) {
      LabyMod.getInstance().draw.addLabel(prefix, text, y);
    } else {
      LabyMod.getInstance().draw.addRightLabel(prefix, text, y);
    }
    offListNext();
  }
  
  public static void addBoxLabel(String prefix, String text, int y)
  {
    if (isSwitch()) {
      LabyMod.getInstance().draw.drawCenteredString(prefix + text, LabyMod.getInstance().draw.getWidth() - 60, y);
    } else {
      LabyMod.getInstance().draw.drawCenteredString(prefix + text, 60, y);
    }
    mainListNext();
  }
  
  public static void addDoubleBoxLabel(String prefix, String text, int y)
  {
    if (isSwitch())
    {
      LabyMod.getInstance().draw.drawCenteredString(prefix, LabyMod.getInstance().draw.getWidth() - 60 - 4, y);
      mainListNext();
      LabyMod.getInstance().draw.drawCenteredString(text, LabyMod.getInstance().draw.getWidth() - 60 - 4, y + 10);
      mainListNext();
    }
    else
    {
      LabyMod.getInstance().draw.drawCenteredString(prefix, 64, y);
      mainListNext();
      LabyMod.getInstance().draw.drawCenteredString(text, 64, y + 10);
      mainListNext();
    }
  }
  
  public static void reset()
  {
    offList = 0;
    mainList = 0;
  }
  
  public static void offListNext()
  {
    offList += 10;
  }
  
  public static void offListNext(int i)
  {
    offList += i;
  }
  
  public static void mainListNext()
  {
    mainList += 10;
  }
  
  public static void mainListNext(int i)
  {
    mainList += i;
  }
  
  public static boolean isSwitch()
  {
    return ConfigManager.settings.guiPositionRight.booleanValue();
  }
  
  public static void drawEntityOnScreen(double x, double y, double size, sa entity)
  {
    if (entity == null) {
      return;
    }
    bni.h();
    bni.G();
    bni.c((float)x, (float)y, 50.0F);
    bni.b((float)-size - 25.0F, (float)size + 25.0F, (float)size);
    bni.b(180.0F, 0.0F, 0.0F, 1.0F);
    float var6 = entity.aM;
    float var7 = entity.v;
    float var8 = entity.w;
    float var9 = entity.aP;
    float var10 = entity.aO;
    bni.b(135.0F, 0.0F, 1.0F, 0.0F);
    
    bni.b(-135.0F, 0.0F, 1.0F, 0.0F);
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
  
  public static void getMouseOver(float p_78473_1_)
  {
    rr var2 = LabyMod.getInstance().mc.aa();
    if (var2 != null) {
      if (LabyMod.getInstance().mc.f != null)
      {
        LabyMod.getInstance().mc.i = null;
        double var3 = 30.0D;
        LabyMod.getInstance().mc.t = var2.a(var3, p_78473_1_);
        double var5 = var3;
        bbj var7 = var2.f(p_78473_1_);
        if (LabyMod.getInstance().mc.t != null) {
          var5 = LabyMod.getInstance().mc.t.c.f(var7);
        }
        bbj var8 = var2.f(p_78473_1_);
        bbj var9 = var7.b(var8.b * var3, var8.c * var3, var8.d * var3);
        pointedEntity = null;
        bbj var10 = null;
        float var11 = 1.0F;
        List var12 = LabyMod.getInstance().mc.f.b(var2, var2.bl().a(var8.b * var3, var8.c * var3, var8.d * var3).b(var11, var11, var11));
        double var13 = var5;
        for (int var15 = 0; var15 < var12.size(); var15++)
        {
          rr var16 = (rr)var12.get(var15);
          if (var16.ap())
          {
            float var17 = var16.aA();
            bbh var18 = var16.bl().b(var17, var17, var17);
            bbi var19 = var18.a(var7, var9);
            if (var18.a(var7))
            {
              if ((0.0D < var13) || (var13 == 0.0D))
              {
                pointedEntity = var16;
                var10 = var19 == null ? var7 : var19.c;
                var13 = 0.0D;
              }
            }
            else if (var19 != null)
            {
              double var20 = var7.f(var19.c);
              if ((var20 < var13) || (var13 == 0.0D)) {
                if (var16 == var2.by())
                {
                  if (var13 == 0.0D)
                  {
                    pointedEntity = var16;
                    var10 = var19.c;
                  }
                }
                else
                {
                  pointedEntity = var16;
                  var10 = var19.c;
                  var13 = var20;
                }
              }
            }
          }
        }
        if ((pointedEntity != null) && ((var13 < var5) || (LabyMod.getInstance().mc.t == null)))
        {
          LabyMod.getInstance().mc.t = new bbi(pointedEntity, var10);
          if (((pointedEntity instanceof sa)) || ((pointedEntity instanceof xs))) {
            LabyMod.getInstance().mc.i = pointedEntity;
          }
        }
      }
    }
  }
}
