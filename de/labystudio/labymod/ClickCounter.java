package de.labystudio.labymod;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import org.lwjgl.input.Mouse;

public class ClickCounter
{
  public static boolean click = false;
  public static double clicks = 0.0D;
  public static double clickResult = 0.0D;
  public static int timer = 0;
  public static long update = 0L;
  public static long calcUpdate = 0L;
  public static HashMap<Integer, Integer> clickList = new HashMap();
  
  public static void update()
  {
    if (!ConfigManager.settings.clickTest)
    {
      if (clickResult != 0.0D) {
        clickResult = 0.0D;
      }
      return;
    }
    Timings.start("Clicktest counter");
    int am = (int)clicks;
    key();
    if ((clickList.values().isEmpty()) && (clicks != 0.0D) && (am == 0)) {
      update = System.currentTimeMillis();
    }
    if (update + 1000L < System.currentTimeMillis())
    {
      update = System.currentTimeMillis();
      
      clickList.put(Integer.valueOf(timer), Integer.valueOf((int)clicks));
      if (timer > 5) {
        clickList.remove(Integer.valueOf(timer - 5));
      }
      timer += 1;
      if (clicks == 0.0D)
      {
        timer = 1;
        clickList.clear();
      }
      clicks = 0.0D;
    }
    if (calcUpdate + 40L < System.currentTimeMillis())
    {
      calcUpdate = System.currentTimeMillis();
      try
      {
        double total = 0.0D;
        for (Iterator localIterator = clickList.values().iterator(); localIterator.hasNext();)
        {
          int s = ((Integer)localIterator.next()).intValue();
          total += s;
        }
        if (total != 0.0D)
        {
          double dTimer = clickList.size();
          double res = total / dTimer;
          if (clickResult > res) {
            clickResult -= 0.1D;
          }
          if (clickResult < res) {
            clickResult += 0.1D;
          }
        }
        else if (clickResult > 0.0D)
        {
          clickResult -= 0.1D;
        }
        else
        {
          clickResult = 0.0D;
        }
      }
      catch (Exception error)
      {
        error.printStackTrace();
      }
    }
    Timings.stop("Clicktest counter");
  }
  
  private static void key()
  {
    if (Mouse.isCreated()) {
      if ((Mouse.isButtonDown(0)) || (Mouse.isButtonDown(1)))
      {
        if (!click)
        {
          clicks += 1.0D;
          click = true;
        }
      }
      else {
        click = false;
      }
    }
  }
  
  public static double getClickResult()
  {
    return clickResult;
  }
}
