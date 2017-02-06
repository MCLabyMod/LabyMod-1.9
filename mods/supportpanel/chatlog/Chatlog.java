package mods.supportpanel.chatlog;

import de.labystudio.labymod.LabyMod;
import de.labystudio.utils.DrawUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import mods.supportpanel.main.TimeCalculator;

public class Chatlog
{
  private String type = "";
  private String time = "";
  private String name = "";
  private String message = "";
  private long longTime = -1L;
  int max;
  int range;
  
  public String getMessage()
  {
    return this.message;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public EnumChatlogMessageType getType()
  {
    return EnumChatlogMessageType.valueOf(this.type.toUpperCase());
  }
  
  public String getTime()
  {
    return this.time;
  }
  
  public long getLongTime()
  {
    return this.longTime;
  }
  
  public String getParsedTime()
  {
    return TimeCalculator.parseTime(this.longTime);
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
  
  public void format()
  {
    try
    {
      SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'.000Z'");
      Date d = f.parse(this.time);
      this.longTime = d.getTime();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    if (getType() == EnumChatlogMessageType.TERMINAL) {
      this.message = ("Der Befehl '/chatlog " + getName() + "' wurde abgesetzt");
    }
    if (getType() == EnumChatlogMessageType.JOIN) {
      this.message = "ist dem Spiel beigetreten";
    }
    if (getType() == EnumChatlogMessageType.LEAVE) {
      this.message = "hat das Spiel verlassen";
    }
  }
}
