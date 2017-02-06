package mods.wordcounter.main;

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

public class Settings
{
  public static File configFile = new File("config/wordcounter_config.json");
  public static CounterSettings settings;
  public static boolean loaded = false;
  public static boolean need_Update = false;
  
  public static void save()
  {
    if (settings != null) {
      try
      {
        if (!configFile.exists()) {
          loadProperties();
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
      System.out.print("WordCounter Settings could not be saved.");
    }
  }
  
  public static void loadProperties()
  {
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
          text = new Gson().toJson(new CounterSettings());
        }
        else
        {
          text = IOUtils.toString(new FileInputStream(configFile));
          text = text.replace("" + Color.c + "l", "" + Color.c + "f").replace("" + Color.c + "k", "" + Color.c + "f").replace("" + Color.c + "m", "" + Color.c + "f").replace("" + Color.c + "n", "" + Color.c + "f").replace("" + Color.c + "r", "" + Color.c + "f").replace("Â", "");
        }
      }
      catch (IOException localIOException) {}
      if ((CounterSettings)new Gson().fromJson(text, CounterSettings.class) == null) {
        text = new Gson().toJson(new CounterSettings());
      }
      settings = (CounterSettings)new Gson().fromJson(text, CounterSettings.class);
      if (settings == null)
      {
        System.out.print("WordCounter Settings could not be loaded.");
        return;
      }
    }
    catch (Exception error)
    {
      System.out.print("WordCounter Settings could not be loaded.");
    }
  }
}
