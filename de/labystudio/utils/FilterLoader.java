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
import org.apache.commons.io.IOUtils;

public class FilterLoader
{
  public static ArrayList<String> filters = new ArrayList();
  public static boolean enabled = true;
  
  public static void loadFilters()
  {
    Timings.start("Load Filter Config");
    if (!filters.isEmpty()) {
      return;
    }
    filters.clear();
    String json = "";
    create();
    try
    {
      json = IOUtils.toString(new FileInputStream(Source.file_filters));
    }
    catch (FileNotFoundException localFileNotFoundException) {}catch (IOException localIOException) {}
    filters = (ArrayList)Utils.ConvertJsonToObject.getFromJSON(json, ArrayList.class);
    if (filters == null) {
      filters = new ArrayList();
    }
    Timings.stop("Load Filter Config");
  }
  
  public static void create()
  {
    if (!new File(Source.file_filters).exists()) {
      try
      {
        if (!new File(Source.file_filters).getParentFile().exists()) {
          new File(Source.file_filters).getParentFile().mkdirs();
        }
        new File(Source.file_filters).createNewFile();
      }
      catch (IOException localIOException) {}
    }
  }
  
  public static void saveFilters()
  {
    FriendsLoader.create();
    String json = Utils.ConvertJsonToObject.toJSON(filters);
    try
    {
      PrintWriter w = new PrintWriter(new FileOutputStream(Source.file_filters));
      w.print(json);
      w.flush();
      w.close();
    }
    catch (FileNotFoundException localFileNotFoundException) {}
  }
}
