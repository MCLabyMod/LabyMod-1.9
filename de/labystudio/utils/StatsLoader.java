package de.labystudio.utils;

import de.labystudio.labymod.Source;
import de.labystudio.labymod.Timings;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.commons.io.IOUtils;
import zj;

public class StatsLoader
{
  public static HashMap<String, ArrayList<String>> stats = new HashMap();
  
  public static void loadstats()
  {
    Timings.start("Load Game stats config");
    if (!stats.isEmpty()) {
      return;
    }
    stats.clear();
    String json = "";
    create();
    try
    {
      json = IOUtils.toString(new FileInputStream(Source.file_stats));
    }
    catch (FileNotFoundException localFileNotFoundException) {}catch (IOException localIOException) {}
    zj.syncPlayerScore();
    stats = (HashMap)Utils.ConvertJsonToObject.getFromJSON(json, HashMap.class);
    if (stats == null) {
      stats = new HashMap();
    }
    Timings.stop("Load Game stats config");
  }
  
  public static void create()
  {
    if (!new File(Source.file_stats).exists()) {
      try
      {
        if (!new File(Source.file_stats).getParentFile().exists()) {
          new File(Source.file_stats).getParentFile().mkdirs();
        }
        new File(Source.file_stats).createNewFile();
      }
      catch (IOException localIOException) {}
    }
  }
  
  public static void savestats()
  {
    create();
    String json = Utils.ConvertJsonToObject.toJSON(stats);
    try
    {
      PrintWriter w = new PrintWriter(new FileOutputStream(Source.file_stats));
      w.print(json);
      w.flush();
      w.close();
    }
    catch (FileNotFoundException localFileNotFoundException) {}
  }
  
  public static boolean isHighScore(int i, ArrayList<String> list)
  {
    if (list.isEmpty()) {
      return true;
    }
    for (int m = 0; m < list.size(); m++)
    {
      String string = (String)list.get(m);
      try
      {
        int l = Integer.parseInt(string);
        if (i <= l) {
          return false;
        }
      }
      catch (Exception localException) {}
    }
    return true;
  }
}
