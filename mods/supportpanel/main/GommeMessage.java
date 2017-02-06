package mods.supportpanel.main;

import de.labystudio.labymod.LabyMod;
import de.labystudio.utils.DrawUtils;

public class GommeMessage
{
  private String sender;
  private String message;
  private long time;
  private String server;
  private String color;
  int max;
  int range;
  
  public GommeMessage(String sender, String message, String color)
  {
    this.sender = sender;
    this.message = message;
    this.time = System.currentTimeMillis();
    this.server = SupportPanel.getCurrentServer();
    this.color = color;
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public long getTime()
  {
    return this.time;
  }
  
  public String getServer()
  {
    return this.server;
  }
  
  public String getSender()
  {
    return this.sender;
  }
  
  public String getParsedTime()
  {
    return TimeCalculator.parseTime(this.time);
  }
  
  public String getColor()
  {
    return this.color;
  }
  
  public void drawMessage(int x, int y)
  {
    this.max = ((int)(LabyMod.getInstance().draw.getWidth() - 228.57142857142858D));
    String msg = getMessage();
    String next = getFirstStrings(this.max, msg);
    String done = "";
    this.range = getRange(msg);
    for (int i = 0; i <= this.range; i++)
    {
      LabyMod.getInstance().draw.drawString(next, x, y + i * 7, 0.7D);
      done = done + next;
      next = getFirstStrings(this.max, msg.replace(done, ""));
    }
  }
  
  private String getFirstStrings(int pixels, String string)
  {
    int k = 0;
    String result = "";
    for (int i = 0; i < string.length(); i++)
    {
      char c = string.charAt(i);
      k += LabyMod.getInstance().draw.getStringWidth(new String(new char[] { c }));
      if (pixels > k)
      {
        result = result + new String(new char[] { c });
      }
      else
      {
        if (pixels != k) {
          break;
        }
        result = result + c;
        break;
      }
    }
    return result;
  }
  
  public int getRange(String msg)
  {
    int k = 0;
    int range = 0;
    String line = msg;
    for (int i = 0; i <= line.length() - 1; i++)
    {
      char a = line.charAt(i);
      if (k > this.max)
      {
        range++;
        k = 0;
      }
      k = (int)(k + LabyMod.getInstance().draw.getStringWidth("" + a) / 0.7D);
    }
    return range;
  }
}
