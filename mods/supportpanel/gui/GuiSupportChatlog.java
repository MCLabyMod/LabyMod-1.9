package mods.supportpanel.gui;

import bcf;
import bcz;
import bdd;
import bni;
import com.google.gson.Gson;
import de.labystudio.labymod.LabyMod;
import de.labystudio.utils.DrawUtils;
import de.labystudio.utils.Scrollbar;
import de.labystudio.utils.TextureManager;
import de.labystudio.utils.Utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import mods.supportpanel.chatlog.Chatlog;
import mods.supportpanel.chatlog.EnumChatlogMessageType;
import mods.supportpanel.main.GommeMessage;
import mods.supportpanel.main.Settings;
import mods.supportpanel.main.SupportPanel;
import mods.supportpanel.main.SupportPanelSettings;
import mods.supportpanel.main.TimeCalculator;
import org.lwjgl.input.Keyboard;

public class GuiSupportChatlog
  extends GuiSupportOverview
{
  int startY = 150 + LabyMod.getInstance().draw.getStringWidth(LabyMod.getInstance().getPlayerName());
  static ArrayList<GommeMessage> serverLog = new ArrayList();
  static ArrayList<Chatlog> chatLog = new ArrayList();
  static long lastUpdate;
  bdd chatLogInput;
  bcz liveChat;
  bcz downloadChatlog;
  Scrollbar scrollbar = new Scrollbar(25);
  static String arg = "";
  static String open = "";
  static boolean live = true;
  static String invalid = null;
  static boolean changed = true;
  static int cache = 0;
  static boolean extend = false;
  
  public void b()
  {
    Keyboard.enableRepeatEvents(true);
    this.n.clear();
    this.chatLogInput = new bdd(-1, this.j.k, this.startY + 6, 7, this.l - this.startY - 140, 17);
    this.chatLogInput.f(100);
    this.chatLogInput.a(arg);
    
    this.n.add(this.liveChat = new bcz(1, this.l - 70, 5, 50, 20, "Live"));
    this.n.add(this.downloadChatlog = new bcz(2, this.l - 125, 5, 50, 20, "Open"));
    
    this.scrollbar.setPosition(this.l - 15, 30, this.l - 10, this.m - 50);
    this.scrollbar.setSpeed(9);
    
    lastUpdate = 0L;
    
    refreshButtons();
    
    super.b();
  }
  
  public void refreshButtons()
  {
    if ((this.liveChat != null) && (this.downloadChatlog != null) && (this.chatLogInput != null))
    {
      this.liveChat.l = (!live);
      this.downloadChatlog.l = ((!this.chatLogInput.b().replace(" ", "").isEmpty()) && (changed));
    }
  }
  
  protected void a(int mouseX, int mouseY, int mouseButton)
    throws IOException
  {
    this.scrollbar.mouseAction(mouseX, mouseY, false);
    if (this.chatLogInput != null) {
      this.chatLogInput.a(mouseX, mouseY, mouseButton);
    }
    super.a(mouseX, mouseY, mouseButton);
  }
  
  protected void a(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick)
  {
    this.scrollbar.mouseAction(mouseX, mouseY, true);
    super.a(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
  }
  
  protected void a(bcz button)
    throws IOException
  {
    if (button.k == 1)
    {
      live = true;
      open = "";
      invalid = null;
      changed = true;
      refreshButtons();
    }
    if (button.k == 2)
    {
      live = false;
      open = arg;
      changed = false;
      refreshButtons();
      if (open.startsWith("chatlog:live"))
      {
        live = true;
        open = "";
        invalid = null;
        refreshButtons();
        return;
      }
      if (open.startsWith("chatlog:")) {
        open = open.replace("chatlog:", "");
      }
      if (open.toLowerCase().contains("chatlog/")) {
        open = open.split("chatlog/")[1].toUpperCase();
      }
      if (open.toLowerCase().contains("chatlog.gommehd.net/")) {
        open = open.split("chatlog.gommehd.net/")[1].toUpperCase();
      }
      this.chatLogInput.a("chatlog:" + open);
      arg = this.chatLogInput.b();
      chatLog.clear();
      extend = false;
      String json = Utils.getContentString("https://chatlog.gommehd.net/api/chatlog/" + open);
      if (json.startsWith("[{")) {
        try
        {
          Gson gson = new Gson();
          json = json.replace("[{\"", "").replace("\"}]", "");
          if (json.contains("\"},{\"")) {
            for (String sJson : json.replace("\"},{\"", "a%%-F33-S").split("a%%-F33-S"))
            {
              sJson = "{\"" + sJson + "\"}";
              Chatlog log = (Chatlog)gson.fromJson(sJson, Chatlog.class);
              log.format();
              chatLog.add(log);
            }
          }
          invalid = null;
        }
        catch (Exception error)
        {
          invalid = error.getMessage();
          error.printStackTrace();
        }
      } else if (json.contains("INVALID_CHATLOG_ID")) {
        invalid = "INVALID_CHATLOG_ID";
      } else {
        invalid = "Unknown error";
      }
    }
    super.a(button);
  }
  
  public void k()
    throws IOException
  {
    this.scrollbar.mouseInput();
    super.k();
  }
  
  protected void a(char typedChar, int keyCode)
    throws IOException
  {
    if ((this.chatLogInput != null) && 
      (this.chatLogInput.a(typedChar, keyCode)))
    {
      arg = this.chatLogInput.b();
      changed = true;
      refreshButtons();
    }
    super.a(typedChar, keyCode);
  }
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    c();
    if (lastUpdate + 5000L < System.currentTimeMillis())
    {
      refreshServerLog();
      lastUpdate = System.currentTimeMillis();
    }
    DrawUtils.a(this.startY + 5, 28, this.l - 20, this.m - 50, -de.labystudio.utils.Color.toRGB(5, 5, 5, 400));
    
    int listSize = 0;
    int renderSize = 0;
    int messages = 0;
    int terminals = 0;
    int y;
    int y;
    if (live)
    {
      y = 32 + this.scrollbar.getScrollY();
      for (GommeMessage gommeMessage : serverLog)
      {
        int range = gommeMessage.getRange(gommeMessage.getMessage()) * 7;
        if ((y < this.m - 70) && (y > 10))
        {
          int color = Integer.MIN_VALUE;
          if (Settings.settings.graphics)
          {
            DrawUtils.a(this.startY + 34, y + 5, this.startY + 36, y + 15 + range, color);
            DrawUtils.a(this.startY + 36, y + 2, this.startY + 38, y + 18 + range, color);
            DrawUtils.a(this.l - 30, y + 2, this.l - 28, y + 18 + range, color);
            DrawUtils.a(this.l - 28, y + 5, this.l - 26, y + 15 + range, color);
          }
          DrawUtils.a(this.startY + 38, y, this.l - 30, y + 20 + range, color);
          
          bni.d(1.0F, 1.0F, 1.0F);
          LabyMod.getInstance().draw.drawString(gommeMessage.getSender(), this.startY + 40, y + 2, 1.0D);
          String m = gommeMessage.getMessage();
          gommeMessage.drawMessage(this.startY + 41, y + 13);
          LabyMod.getInstance().textureManager.drawPlayerHead(gommeMessage.getSender(), this.startY + 10, y + 2 + range / 2, 0.7D);
          LabyMod.getInstance().draw.drawRightString(gommeMessage.getParsedTime() + "", this.l - 32, y + 3, 0.7D);
        }
        y += 26 + range;
        listSize++;
        renderSize += 26 + range;
      }
    }
    else if (invalid != null)
    {
      String error = invalid;
      if (invalid.equals("INVALID_CHATLOG_ID")) {
        invalid = "Invalid chatlog ID";
      }
      LabyMod.getInstance().draw.drawCenteredString(de.labystudio.utils.Color.cl("c") + error, (this.l + this.startY - 15) / 2, (this.m - 20) / 2, 1.0D);
    }
    else
    {
      y = 32 + this.scrollbar.getScrollY();
      for (Chatlog gommeMessage : chatLog) {
        if ((Settings.settings.joinLeave) || ((gommeMessage.getType() != EnumChatlogMessageType.JOIN) && (gommeMessage.getType() != EnumChatlogMessageType.LEAVE)))
        {
          if (gommeMessage.getType() == EnumChatlogMessageType.USER) {
            messages++;
          }
          if (gommeMessage.getType() == EnumChatlogMessageType.TERMINAL) {
            terminals++;
          }
          int range = gommeMessage.getRange(gommeMessage.getMessage()) * 7;
          if (!extend) {
            range = 0;
          }
          if ((y + range < this.m - 70) && (y > 10))
          {
            if (gommeMessage.getType() == EnumChatlogMessageType.USER)
            {
              int color = Integer.MIN_VALUE;
              if (Settings.settings.graphics)
              {
                DrawUtils.a(this.startY + 34, y + 5, this.startY + 36, y + 15 + range, color);
                DrawUtils.a(this.startY + 36, y + 2, this.startY + 38, y + 18 + range, color);
                DrawUtils.a(this.l - 30, y + 2, this.l - 28, y + 18 + range, color);
                DrawUtils.a(this.l - 28, y + 5, this.l - 26, y + 15 + range, color);
              }
              DrawUtils.a(this.startY + 38, y, this.l - 30, y + 20 + range, color);
              bni.d(1.0F, 1.0F, 1.0F);
              LabyMod.getInstance().draw.drawString(de.labystudio.utils.Color.cl("b") + gommeMessage.getName(), this.startY + 40, y + 2, 1.0D);
              String m = gommeMessage.getMessage();
              gommeMessage.drawMessage(this.startY + 41, y + 13);
              LabyMod.getInstance().textureManager.drawPlayerHead(gommeMessage.getName(), this.startY + 10, y + 2 + range / 2, 0.7D);
              LabyMod.getInstance().draw.drawRightString(gommeMessage.getParsedTime() + "", this.l - 32, y + 3, 0.7D);
            }
            if (gommeMessage.getType() == EnumChatlogMessageType.TERMINAL)
            {
              int color = Integer.MAX_VALUE;
              if (Settings.settings.graphics)
              {
                DrawUtils.a(this.startY + 34, y + 5, this.startY + 36, y + 15 + range, color);
                DrawUtils.a(this.startY + 36, y + 2, this.startY + 38, y + 18 + range, color);
                DrawUtils.a(this.l - 30, y + 2, this.l - 28, y + 18 + range, color);
                DrawUtils.a(this.l - 28, y + 5, this.l - 26, y + 15 + range, color);
              }
              DrawUtils.a(this.startY + 38, y, this.l - 30, y + 20 + range, color);
              bni.d(1.0F, 1.0F, 1.0F);
              LabyMod.getInstance().draw.drawString(de.labystudio.utils.Color.cl("6") + gommeMessage.getName(), this.startY + 40, y + 2, 1.0D);
              String m = gommeMessage.getMessage();
              gommeMessage.drawMessage(this.startY + 41, y + 13);
              LabyMod.getInstance().textureManager.drawPlayerHead("MHF_Exclamation", this.startY + 10, y + 2 + range / 2, 0.7D);
              LabyMod.getInstance().draw.drawRightString(gommeMessage.getParsedTime() + "", this.l - 32, y + 3, 0.7D);
            }
            if ((gommeMessage.getType() == EnumChatlogMessageType.JOIN) || (gommeMessage.getType() == EnumChatlogMessageType.LEAVE))
            {
              int color = de.labystudio.utils.Color.toRGB(400, 20, 30, 100);
              String cc = de.labystudio.utils.Color.cl("c");
              if (gommeMessage.getType() == EnumChatlogMessageType.JOIN)
              {
                color = de.labystudio.utils.Color.toRGB(30, 150, 30, 100);
                cc = de.labystudio.utils.Color.cl("a");
              }
              if (Settings.settings.graphics)
              {
                DrawUtils.a(this.startY + 34, y + 5, this.startY + 36, y + 15 + range, color);
                DrawUtils.a(this.startY + 36, y + 2, this.startY + 38, y + 18 + range, color);
                DrawUtils.a(this.l - 30, y + 2, this.l - 28, y + 18 + range, color);
                DrawUtils.a(this.l - 28, y + 5, this.l - 26, y + 15 + range, color);
              }
              DrawUtils.a(this.startY + 38, y, this.l - 30, y + 20 + range, color);
              bni.d(1.0F, 1.0F, 1.0F);
              LabyMod.getInstance().draw.drawString(cc + gommeMessage.getName(), this.startY + 40, y + 2, 1.0D);
              String m = gommeMessage.getMessage();
              gommeMessage.drawMessage(this.startY + 41, y + 13);
              
              LabyMod.getInstance().textureManager.drawPlayerHead(gommeMessage.getName(), this.startY + 10, y + 2 + range / 2, 0.7D);
              LabyMod.getInstance().draw.drawRightString(gommeMessage.getParsedTime() + "", this.l - 32, y + 3, 0.7D);
            }
          }
          y += 26 + range;
          listSize++;
          renderSize += 26 + range;
          if ((!extend) && 
            (gommeMessage.getRange(gommeMessage.getMessage()) * 7 != 0)) {
            extend = true;
          }
          LabyMod.getInstance().draw.drawString("" + range, 20.0D, y);
        }
      }
    }
    DrawUtils.a(this.startY + 5, 5, this.l - 20, 28, java.awt.Color.black.getRGB());
    DrawUtils.a(this.startY + 5, this.m - 70, this.l - 20, this.m - 50, java.awt.Color.black.getRGB());
    if (!live)
    {
      LabyMod.getInstance().draw.drawString(messages + " Nachricht" + TimeCalculator.mdf(messages, "en"), this.startY + 10, this.m - 64);
      LabyMod.getInstance().draw.drawRightString(terminals + " Chatlog Befehl" + TimeCalculator.mdf(terminals, "e"), this.l - 23, this.m - 64);
    }
    else
    {
      LabyMod.getInstance().draw.drawString("Cached " + cache + " message" + TimeCalculator.mdf(cache, "s"), this.startY + 10, this.m - 64);
    }
    if (this.chatLogInput != null) {
      this.chatLogInput.g();
    }
    if ((renderSize > 0) && (listSize > 0)) {
      this.scrollbar.setEntryHeight(renderSize / listSize);
    }
    this.scrollbar.update(listSize);
    
    this.scrollbar.draw();
    
    super.a(mouseX, mouseY, partialTicks);
  }
  
  public void refreshServerLog()
  {
    serverLog.clear();
    int total = 0;
    for (String player : SupportPanel.chatLog.keySet())
    {
      ArrayList<GommeMessage> messages = (ArrayList)SupportPanel.chatLog.get(player);
      for (GommeMessage gommeMessage : messages) {
        if (gommeMessage.getServer().equals(SupportPanel.getCurrentServer()))
        {
          if (total < 20) {
            serverLog.add(gommeMessage);
          }
          total++;
        }
      }
    }
    cache = total;
    Collections.sort(serverLog, new Comparator()
    {
      public int compare(GommeMessage o1, GommeMessage o2)
      {
        return (int)(o2.getTime() - o1.getTime());
      }
    });
  }
}
