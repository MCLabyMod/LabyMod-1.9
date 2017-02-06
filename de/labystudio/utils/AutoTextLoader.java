package de.labystudio.utils;

import bcf;
import bks;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.ModSettings;
import de.labystudio.labymod.Source;
import de.labystudio.labymod.Timings;
import iy;
import iy.a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import org.apache.commons.io.IOUtils;
import org.lwjgl.input.Keyboard;

public class AutoTextLoader
{
  public static HashMap<String, String> autoText = new HashMap();
  public static boolean enabled = true;
  public static boolean listening = false;
  public static int key = -1;
  public static boolean ctrl = false;
  public static boolean shift = false;
  public static boolean alt = false;
  
  public static void load()
  {
    Timings.start("Load AutoText Config");
    if (!autoText.isEmpty()) {
      return;
    }
    autoText.clear();
    String json = "";
    create();
    try
    {
      json = IOUtils.toString(new FileInputStream(Source.file_autoText));
    }
    catch (FileNotFoundException localFileNotFoundException) {}catch (IOException localIOException) {}
    autoText = (HashMap)Utils.ConvertJsonToObject.getFromJSON(json, HashMap.class);
    if (autoText == null) {
      autoText = new HashMap();
    }
    Timings.stop("Load AutoText Config");
  }
  
  public static void create()
  {
    if (!new File(Source.file_autoText).exists()) {
      try
      {
        if (!new File(Source.file_autoText).getParentFile().exists()) {
          new File(Source.file_autoText).getParentFile().mkdirs();
        }
        new File(Source.file_autoText).createNewFile();
      }
      catch (IOException localIOException) {}
    }
  }
  
  public static void save()
  {
    FriendsLoader.create();
    String json = Utils.ConvertJsonToObject.toJSON(autoText);
    try
    {
      PrintWriter w = new PrintWriter(new FileOutputStream(Source.file_autoText));
      w.print(json);
      w.flush();
      w.close();
    }
    catch (FileNotFoundException localFileNotFoundException) {}
  }
  
  static boolean repeat = false;
  
  public static void handleKeyboardInput(int key)
  {
    if (listening)
    {
      if ((key != 29) && (key != 42) && (key != 56)) {
        key = key;
      }
      if (Keyboard.isKeyDown(29)) {
        ctrl = true;
      }
      if (Keyboard.isKeyDown(42)) {
        shift = true;
      }
      if (Keyboard.isKeyDown(56)) {
        alt = true;
      }
      return;
    }
    if (key == -1)
    {
      repeat = false;
      return;
    }
    if ((LabyMod.getInstance().mc.m == null) && (ConfigManager.settings.autoText) && (enabled) && (!repeat)) {
      for (String set : autoText.keySet()) {
        try
        {
          if (Keyboard.isKeyDown(getNormalKey(set)))
          {
            if (((isShift(set)) && (!Keyboard.isKeyDown(42))) || 
            
              ((isCtrl(set)) && (!Keyboard.isKeyDown(29))) || (
              
              (isAlt(set)) && (!Keyboard.isKeyDown(56)))) {
              continue;
            }
            repeat = true;
            if (((String)autoText.get(set)).toLowerCase().contains("%elytra%"))
            {
              if (LabyMod.getInstance().isInGame()) {
                bcf.z().v().a(new iy(bcf.z().h, iy.a.i));
              }
            }
            else {
              LabyMod.getInstance().sendChatMessage((String)autoText.get(set));
            }
          }
        }
        catch (Exception localException) {}
      }
    }
  }
  
  public static boolean isShift(String hotKeyCode)
  {
    return hotKeyCode.contains("#SHIFT");
  }
  
  public static boolean isAlt(String hotKeyCode)
  {
    return hotKeyCode.contains("#ALT");
  }
  
  public static boolean isCtrl(String hotKeyCode)
  {
    return hotKeyCode.contains("#CTRL");
  }
  
  public static int getNormalKey(String hotKeyCode)
  {
    try
    {
      return Integer.parseInt(hotKeyCode.replace("#SHIFT", "").replace("#ALT", "").replace("#CTRL", ""));
    }
    catch (Exception error) {}
    return -1;
  }
}
