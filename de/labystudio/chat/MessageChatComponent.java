package de.labystudio.chat;

import de.labystudio.labymod.LabyMod;
import de.labystudio.utils.Color;
import de.labystudio.utils.DrawUtils;
import de.labystudio.utils.Utils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class MessageChatComponent
{
  public static final SimpleDateFormat format = new SimpleDateFormat("HH:mm");
  protected String message;
  private String sender;
  private long sentTime;
  private int range = 0;
  int max;
  private SingleChat chat;
  protected boolean downloaded = true;
  long id = 0L;
  int gx;
  int gy;
  
  public MessageChatComponent(String sender, long sentTime, String message)
  {
    this.message = message;
    this.sender = sender;
    this.sentTime = sentTime;
    this.id = LabyMod.getRandom().nextLong();
    if (this.id == 0L) {
      this.id += 1L;
    }
  }
  
  public void setChat(SingleChat chat)
  {
    this.chat = chat;
  }
  
  public SingleChat getChat()
  {
    return this.chat;
  }
  
  public boolean isDownloaded()
  {
    return this.downloaded;
  }
  
  public long getId()
  {
    return this.id;
  }
  
  public void draw(int x, int y)
  {
    this.gx = x;
    this.gy = y;
    this.max = (LabyMod.getInstance().draw.getWidth() - 150);
    String prefix = Color.cl("7");
    if (getSender().equals(LabyMod.getInstance().getPlayerName())) {
      prefix = Color.cl("f");
    }
    String msg = Color.cl("6") + "[" + format.format(new Date(getSentTime())) + "] " + prefix + getSender() + ": " + Color.cl("f") + getMessage().replace("´", "'");
    this.range = getRange(msg);
    y -= this.range * 12;
    
    String next = getFirstStrings(this.max, msg);
    String done = "";
    for (int i = 0; i <= this.range; i++)
    {
      LabyMod.getInstance().draw.drawString(next, x, y + i * 12);
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
  
  private int getRange(String msg)
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
      k += LabyMod.getInstance().draw.getStringWidth("" + a);
    }
    return range;
  }
  
  public int getYSize()
  {
    return (this.range + 1) * 12;
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public String getSender()
  {
    return this.sender;
  }
  
  public long getSentTime()
  {
    return this.sentTime;
  }
  
  public void click(int mouseX, int mouseY, int mouseButton)
  {
    this.range = getRange(this.message);
    if ((mouseX > this.gx) && (mouseX < this.gx + LabyMod.getInstance().draw.getStringWidth(this.message) + 100) && (mouseY < this.gy + 12) && (mouseY > this.gy - getYSize() + 12))
    {
      ArrayList<String> urls = Utils.extractDomains(this.message);
      if (!urls.isEmpty()) {
        LabyMod.getInstance().openWebpage((String)urls.get(0));
      }
    }
  }
  
  public void drawOpen() {}
  
  public void handleMouseInput() {}
  
  public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {}
  
  public void mouseRelease(int mouseX, int mouseY, int state) {}
}
