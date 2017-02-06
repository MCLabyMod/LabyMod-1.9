package de.labystudio.utils;

import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.ModSettings;

public class Color
{
  public static String c = "§";
  
  public static String cl(String color)
  {
    return c + color + getExtraColor();
  }
  
  public static String clc(String color)
  {
    return c + color;
  }
  
  public static String getExtraColor()
  {
    String s = "";
    if (ConfigManager.settings.bold) {
      s = s + c + "l";
    }
    if (ConfigManager.settings.underline) {
      s = s + c + "n";
    }
    if (ConfigManager.settings.italic) {
      s = s + c + "o";
    }
    return s;
  }
  
  public static String removeColor(String input)
  {
    return input.replace(c, "&").replaceAll("&[a-z0-9]", "");
  }
  
  public static String c(int i)
  {
    if (i == 1) {
      return ConfigManager.settings.color1 + getExtraColor();
    }
    if (i == 2) {
      return ConfigManager.settings.color2 + getExtraColor();
    }
    if (i == 3) {
      return ConfigManager.settings.color3 + getExtraColor();
    }
    if (i == 4) {
      return ConfigManager.settings.color4 + getExtraColor();
    }
    if (i == 5) {
      return ConfigManager.settings.color5 + getExtraColor();
    }
    if (i == 6) {
      return ConfigManager.settings.color6 + getExtraColor();
    }
    if (i == 7) {
      return ConfigManager.settings.color7 + getExtraColor();
    }
    if (i == 8) {
      return ConfigManager.settings.color8 + getExtraColor();
    }
    if (i == 9) {
      return ConfigManager.settings.color9 + getExtraColor();
    }
    if (i == 10) {
      return ConfigManager.settings.color10 + getExtraColor();
    }
    return "";
  }
  
  public static String cc(int i)
  {
    if (i == 1) {
      return ConfigManager.settings.color1;
    }
    if (i == 2) {
      return ConfigManager.settings.color2;
    }
    if (i == 3) {
      return ConfigManager.settings.color3;
    }
    if (i == 4) {
      return ConfigManager.settings.color4;
    }
    if (i == 5) {
      return ConfigManager.settings.color5;
    }
    if (i == 6) {
      return ConfigManager.settings.color6;
    }
    if (i == 7) {
      return ConfigManager.settings.color7;
    }
    if (i == 7) {
      return ConfigManager.settings.color8;
    }
    if (i == 9) {
      return ConfigManager.settings.color9;
    }
    if (i == 10) {
      return ConfigManager.settings.color10;
    }
    return "";
  }
  
  public static int colorToID(String s)
  {
    try
    {
      s = s.replace("" + c + "", "");
      if (s.equals("a")) {
        return 10;
      }
      if (s.equals("b")) {
        return 11;
      }
      if (s.equals("c")) {
        return 12;
      }
      if (s.equals("d")) {
        return 13;
      }
      if (s.equals("e")) {
        return 14;
      }
      if (s.equals("f")) {
        return 15;
      }
      return Integer.parseInt(s);
    }
    catch (Exception error) {}
    return 0;
  }
  
  public static String getC()
  {
    return c;
  }
  
  public static String fix(String i)
  {
    return i.replace("Â", "");
  }
  
  public static String IDToColor(int i)
  {
    return fix(IDToColorOld(i));
  }
  
  public static String booleanToColor(Boolean b)
  {
    if (b.booleanValue()) {
      return fix("" + c + "a");
    }
    return fix("" + c + "c");
  }
  
  public static String IDToColorOld(int i)
  {
    if (i == 10) {
      return "" + c + "a";
    }
    if (i == 11) {
      return "" + c + "b";
    }
    if (i == 12) {
      return "" + c + "c";
    }
    if (i == 13) {
      return "" + c + "d";
    }
    if (i == 14) {
      return "" + c + "e";
    }
    if (i == 15) {
      return "" + c + "f";
    }
    return "" + c + "" + i;
  }
  
  public static int toRGB(int r, int g, int b, int a)
  {
    return (a & 0xFF) << 24 | (r & 0xFF) << 16 | (g & 0xFF) << 8 | (b & 0xFF) << 0;
  }
}
