package de.labystudio.chat;

import de.labystudio.labymod.LabyMod;
import de.labystudio.utils.Color;
import de.labystudio.utils.DrawUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TitleChatComponent
  extends MessageChatComponent
{
  String title;
  
  public TitleChatComponent(String sender, long sentTime, String message)
  {
    super(sender, sentTime, "<title>" + message + "</title>");
    this.title = message;
  }
  
  SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
  
  public String buildDate()
  {
    SimpleDateFormat time = new SimpleDateFormat("EEEE, MMMM d, yyyy");
    String sentTime = this.date.format(Long.valueOf(getSentTime()));
    String thisDay = this.date.format(new Date());
    if (sentTime.equals(thisDay)) {
      return Color.c(1) + "Today " + Color.c(2) + "| " + Color.c(3) + time.format(Long.valueOf(getSentTime()));
    }
    new GregorianCalendar();Calendar c = GregorianCalendar.getInstance();
    c.add(5, -1);
    String yesterDay = format.format(c.getTime());
    if (sentTime.equals(yesterDay)) {
      return Color.c(1) + "Yesterday " + Color.c(2) + "| " + Color.c(3) + time.format(Long.valueOf(getSentTime()));
    }
    return Color.c(3) + time.format(Long.valueOf(getSentTime()));
  }
  
  public void draw(int x, int y)
  {
    DrawUtils.a(140, y - 10, LabyMod.getInstance().draw.getWidth() - 5, y + 10, Integer.MIN_VALUE);
    LabyMod.getInstance().draw.drawCenteredString(Color.c(3) + buildDate(), (LabyMod.getInstance().draw.getWidth() - 140) / 2 + 5 + 132, y - 5);
  }
  
  public int getYSize()
  {
    return 20;
  }
}
