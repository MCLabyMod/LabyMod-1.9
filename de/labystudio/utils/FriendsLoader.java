package de.labystudio.utils;

import de.labystudio.labymod.Source;
import de.labystudio.labymod.Timings;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;

public class FriendsLoader
{
  public static Map<String, String> friends = new HashMap();
  
  public static void loadFriends()
  {
    Timings.start("Load Friends Config");
    if (!friends.isEmpty()) {
      return;
    }
    friends.clear();
    String json = "";
    create();
    try
    {
      json = IOUtils.toString(new FileInputStream(Source.file_friendTags), Charset.forName("UTF-8"));
    }
    catch (FileNotFoundException localFileNotFoundException) {}catch (IOException localIOException) {}
    friends = (Map)Utils.ConvertJsonToObject.getFromJSON(json, Map.class);
    if (friends == null) {
      friends = new HashMap();
    }
    Timings.stop("Load Friends Config");
  }
  
  public static void create()
  {
    if (!new File(Source.file_friendTags).exists()) {
      try
      {
        if (!new File(Source.file_friendTags).getParentFile().exists()) {
          new File(Source.file_friendTags).getParentFile().mkdirs();
        }
        new File(Source.file_friendTags).createNewFile();
      }
      catch (IOException localIOException) {}
    }
  }
  
  public static String getNick(String name, String blank)
  {
    if (!Allowed.nick()) {
      return name;
    }
    if ((friends.containsKey(blank)) && 
      (!((String)friends.get(blank)).replace(" ", "").isEmpty())) {
      return ((String)friends.get(blank)).replace("&", Color.c);
    }
    return name;
  }
  
  public static void saveFriends()
  {
    create();
    String json = Utils.ConvertJsonToObject.toJSON(friends);
    try
    {
      PrintWriter w = new PrintWriter(new FileOutputStream(Source.file_friendTags));
      w.print(json);
      w.flush();
      w.close();
    }
    catch (FileNotFoundException localFileNotFoundException) {}
  }
}
