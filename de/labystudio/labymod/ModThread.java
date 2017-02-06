package de.labystudio.labymod;

import java.io.PrintStream;

public class ModThread
  extends Thread
{
  long time = 0L;
  
  public void run()
  {
    int tick = 0;
    try
    {
      for (;;)
      {
        if (!ConfigManager.settings.clickTest)
        {
          LabyMod.getInstance().secondLoop();
          synchronized (this)
          {
            try
            {
              wait(1000L);
            }
            catch (Exception e)
            {
              System.out.println("Failed to wait (LabyMod)");
            }
          }
        }
        else
        {
          ClickCounter.update();
          if (this.time + 1000L < System.currentTimeMillis())
          {
            this.time = System.currentTimeMillis();
            LabyMod.getInstance().secondLoop();
          }
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      System.out.println("Failed to loop (LabyMod)");
    }
  }
}
