package de.labystudio.modapi;

import bfb;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.labystudio.chat.Logger;
import de.labystudio.utils.Debug;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.net.URI;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;

public class ModManager
{
  private static ArrayList<Module> modules = new ArrayList();
  private static HashMap<String, bfb> settings = new HashMap();
  private static bfb lastScreen;
  
  public static ArrayList<Module> getModules()
  {
    return modules;
  }
  
  public static void updateLastScreen(bfb screen)
  {
    lastScreen = screen;
  }
  
  public static HashMap<String, bfb> getSettings()
  {
    return settings;
  }
  
  public static void setSettings(HashMap<String, bfb> settings)
  {
    settings = settings;
  }
  
  public static bfb getLastScreen()
  {
    return lastScreen;
  }
  
  public static void pluginMessage(String key, boolean value)
  {
    for (Module module : ) {
      module.pluginMessage(key, value);
    }
  }
  
  public static void loadMods()
  {
    try
    {
      ArrayList<String> list = new ArrayList();
      CodeSource src = ModManager.class.getProtectionDomain().getCodeSource();
      if (src != null)
      {
        URL jar = src.getLocation();
        JarInputStream zip = new JarInputStream(jar.openStream());
        JarEntry ze = null;
        while ((ze = zip.getNextJarEntry()) != null)
        {
          String entryName = ze.getName();
          if (entryName.endsWith(".desc")) {
            list.add(entryName);
          }
        }
      }
      File file = null;
      JarFile jar = null;
      try
      {
        file = new File(src.getLocation().toURI());
        jar = new JarFile(file);
      }
      catch (Exception localException1) {}
      if ((file == null) || (jar == null) || (!file.exists())) {
        return;
      }
      try
      {
        for (String name : list)
        {
          if (Debug.api()) {
            System.out.println("[DEBUG] Loading Mod " + name);
          }
          JarEntry entry = jar.getJarEntry(name);
          InputStream input = jar.getInputStream(entry);
          JsonElement json = new JsonParser().parse(new InputStreamReader(input));
          if ((json instanceof JsonNull))
          {
            Logger.getLogger().info("Skipping " + file.getName() + " because it's " + name + " is empty");
          }
          else
          {
            JsonObject obj = (JsonObject)json;
            String main = obj.get("main").getAsString();
            if (main != null) {
              try
              {
                try
                {
                  URL[] a = { file.toURI().toURL() };
                  ModuleClassLoader cl = new ModuleClassLoader(a);
                  
                  Class<?> clazz = cl.loadClass(main, true);
                  Module mod = (Module)clazz.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                  mod.onEnable();
                  modules.add(mod);
                  Logger.getLogger().info("Loaded Mod " + name);
                }
                catch (NoClassDefFoundError e)
                {
                  e.printStackTrace();
                  Logger.getLogger().info("NoClassDefFoundError: " + main);
                }
              }
              catch (Exception e)
              {
                e.printStackTrace();
                Logger.getLogger().info("Skipping " + file.getName() + " because it's main class doesn't contain a default constructor");
              }
            } else {
              Logger.getLogger().info("Skipping " + file.getName() + " because it's " + name + " doesn't contain a main entry");
            }
          }
        }
      }
      catch (NoSuchMethodError e2)
      {
        e2.printStackTrace();
      }
    }
    catch (Exception e1)
    {
      e1.printStackTrace();
    }
  }
}
