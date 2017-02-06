package de.labystudio.labymod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.labystudio.utils.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.Logger;

public class ConfigManager
{
  public static File configFile = new File(Source.file_Config);
  public static ModSettings settings;
  public static boolean loaded = false;
  
  public static void save()
  {
    if (settings != null) {
      try
      {
        if (!configFile.exists()) {
          loadProperties(true);
        }
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        
        PrintWriter w = new PrintWriter(new FileOutputStream(configFile));
        w.print(gson.toJson(settings));
        w.flush();
        w.close();
      }
      catch (FileNotFoundException localFileNotFoundException) {}
    } else {
      LabyMod.getInstance().logger.info("Settings could not be saved.");
    }
  }
  
  public static void loadProperties(boolean loop)
  {
    Timings.start("Load Config");
    try
    {
      loaded = true;
      String text = "{}";
      try
      {
        if (!configFile.exists())
        {
          configFile.getParentFile().mkdir();
          configFile.createNewFile();
          text = new Gson().toJson(new ModSettings());
        }
        else
        {
          text = IOUtils.toString(new FileInputStream(configFile));
          text = text.replace("" + Color.c + "l", "" + Color.c + "f").replace("" + Color.c + "k", "" + Color.c + "f").replace("" + Color.c + "m", "" + Color.c + "f").replace("" + Color.c + "n", "" + Color.c + "f").replace("" + Color.c + "r", "" + Color.c + "f").replace("Â", "");
        }
      }
      catch (IOException e)
      {
        e.printStackTrace();
        if (loop) {
          delete();
        }
        return;
      }
      if ((ModSettings)new Gson().fromJson(text, ModSettings.class) == null) {
        text = new Gson().toJson(new ModSettings());
      }
      settings = (ModSettings)new Gson().fromJson(text, ModSettings.class);
      if (settings == null)
      {
        if (loop) {
          delete();
        }
        return;
      }
    }
    catch (Exception error)
    {
      error.printStackTrace();
      if (loop) {
        delete();
      }
      return;
    }
    Timings.stop("Load Config");
  }
  
  private static void delete()
  {
    LabyMod.getInstance().logger.info("Settings could not be loaded.");
    if (configFile.exists())
    {
      System.out.println("Delete broken config file..");
      settings = (ModSettings)new Gson().fromJson(new Gson().toJson(new ModSettings()), ModSettings.class);
      save();
    }
    else
    {
      System.out.println("Can't delete the config file?! @ " + configFile.getAbsolutePath());
    }
    loadProperties(false);
  }
}
