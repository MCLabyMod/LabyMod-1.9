package de.labystudio.labymod;

import de.labystudio.utils.Color;
import de.labystudio.utils.Debug;
import de.labystudio.utils.DrawUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

public class Timings
{
  public static HashMap<String, Debug> result = new HashMap();
  public static ArrayList<Debug> cache = new ArrayList();
  static HashMap<String, Long> open = new HashMap();
  static HashMap<String, Long> lost = new HashMap();
  static long lastUpdate = 0L;
  
  public static void start(String title)
  {
    if (!isTiming()) {
      return;
    }
    open.put(title, Long.valueOf(System.currentTimeMillis()));
  }
  
  public static void stop(String title)
  {
    if (!isTiming()) {
      return;
    }
    if (open.containsKey(title))
    {
      Long start = (Long)open.get(title);
      open.remove(title);
      if (System.currentTimeMillis() - start.longValue() != 0L) {
        result.put(title, new Debug(title, (int)(System.currentTimeMillis() - start.longValue()), System.currentTimeMillis()));
      }
    }
  }
  
  public static class Debug
  {
    String path = "";
    int time;
    long created;
    
    public Debug(String path, int time, long created)
    {
      this.path = path;
      this.time = time;
      this.created = created;
    }
  }
  
  public static void draw()
  {
    start("Draw Timings");
    if (!isTiming()) {
      return;
    }
    if (lastUpdate + 1000L < System.currentTimeMillis()) {
      try
      {
        lastUpdate = System.currentTimeMillis();
        ArrayList<String> rem = new ArrayList();
        for (String k : result.keySet())
        {
          Debug d = (Debug)result.get(k);
          if (d.created + 1000L < System.currentTimeMillis()) {
            rem.add(k);
          }
        }
        for (String k : rem) {
          result.remove(k);
        }
        cache = new ArrayList(result.values());
        Collections.sort(cache, new Comparator()
        {
          public int compare(Timings.Debug o1, Timings.Debug o2)
          {
            return o2.time - o1.time;
          }
        });
        lost.clear();
        lost.putAll(open);
      }
      catch (Exception localException) {}
    }
    DrawUtils.a(LabyMod.getInstance().draw.getWidth() - 190, 5, LabyMod.getInstance().draw.getWidth() - 151, 10 + cache.size() * 5 + 2 + lost.size() * 5 + 10, Color.toRGB(20, 20, 20, 100));
    
    DrawUtils.a(LabyMod.getInstance().draw.getWidth() - 150, 5, LabyMod.getInstance().draw.getWidth() - 5, 10 + cache.size() * 5, Color.toRGB(20, 20, 20, 100));
    int i = 0;
    for (??? = cache.iterator(); ???.hasNext();)
    {
      debug = (Debug)???.next();
      String color = "a";
      int ms = debug.time;
      if (ms > 2) {
        color = "e";
      }
      if (ms > 5) {
        color = "6";
      }
      if (ms > 10) {
        color = "c";
      }
      if (ms > 15) {
        color = "4";
      }
      String out = Color.cl(color) + ms + "ms " + Color.cl("7") + " [" + Color.cl("c") + debug.path + Color.cl("7") + "]";
      LabyMod.getInstance().draw.drawString(out, LabyMod.getInstance().draw.getWidth() - 148, 7 + i, 0.5D);
      i += 5;
    }
    Debug debug;
    int missing = 0;
    DrawUtils.a(LabyMod.getInstance().draw.getWidth() - 150, 10 + cache.size() * 5 + 2, LabyMod.getInstance().draw.getWidth() - 5, 10 + cache.size() * 5 + 2 + lost.size() * 5 + 10, Color.toRGB(20, 20, 20, 100));
    for (String l : lost.keySet())
    {
      Long ms = Long.valueOf(System.currentTimeMillis() - ((Long)lost.get(l)).longValue());
      if (ms.longValue() > 1000L)
      {
        LabyMod.getInstance().draw.drawString(l + " (" + ms + "ms)", LabyMod.getInstance().draw.getWidth() - 148, 15 + i, 0.5D);
        i += 5;
        missing++;
      }
    }
    LabyMod.getInstance().draw.drawRightString(result.size() + " timings", LabyMod.getInstance().draw.getWidth() - 153, 7.0D, 0.5D);
    LabyMod.getInstance().draw.drawRightString(cache.size() + " cached", LabyMod.getInstance().draw.getWidth() - 153, 12.0D, 0.5D);
    LabyMod.getInstance().draw.drawRightString(missing + " missing", LabyMod.getInstance().draw.getWidth() - 153, 17.0D, 0.5D);
    
    stop("Draw Timings");
  }
  
  public static boolean isTiming()
  {
    if (ConfigManager.settings == null) {
      return false;
    }
    return Debug.timings();
  }
}
