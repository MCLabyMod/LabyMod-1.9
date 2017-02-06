package de.labystudio.gui;

import bcf;
import bcz;
import bfb;
import bni;
import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.Source;
import de.labystudio.utils.Color;
import de.labystudio.utils.DrawUtils;
import de.labystudio.utils.Scrollbar;
import de.labystudio.utils.Utils;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GuiLabyModMenu
  extends bfb
{
  private DrawUtils draw;
  public static ArrayList<String> changelog = null;
  public static ArrayList<String> news = null;
  public static ArrayList<String> about = null;
  private bfb lastScreen;
  private Scrollbar scrollbar;
  HashMap<String, ArrayList<String>> navi = new HashMap();
  private ArrayList<String> tabs = new ArrayList();
  private String selectedTab = "";
  
  public GuiLabyModMenu(bfb lastScreen)
  {
    this.lastScreen = lastScreen;
    this.scrollbar = new Scrollbar(10);
    this.scrollbar.update(0);
    
    this.tabs.add("Changelog");
    this.tabs.add("News");
    this.tabs.add("About");
    this.selectedTab = ((String)this.tabs.get(0));
  }
  
  public void b()
  {
    this.draw = LabyMod.getInstance().draw;
    this.n.clear();
    this.n.add(new bcz(2, this.l - 115, this.m - 30 - 44, 110, 20, "Join our TeamSpeak"));
    this.n.add(new bcz(0, this.l - 115, this.m - 30 - 22, 110, 20, "Open website"));
    this.n.add(new bcz(1, this.l - 115, this.m - 30, 110, 20, "Close"));
    this.scrollbar.setPosition(this.l - 128, 20, this.l - 120, this.m - 10);
    if ((changelog == null) && (this.selectedTab.equals("Changelog")))
    {
      Thread checkThread = new Thread()
      {
        public void run()
        {
          GuiLabyModMenu.changelog = new ArrayList();
          ArrayList<String> list = Utils.getContentList("http://www.labymod.net/files/changelog.php");
          if (!list.isEmpty()) {
            for (String log : list) {
              if (log != null) {
                GuiLabyModMenu.changelog.add(GuiLabyModMenu.this.convertHtml(log));
              }
            }
          }
        }
      };
      checkThread.setPriority(1);
      checkThread.start();
    }
    if ((news == null) && (this.selectedTab.equals("News")))
    {
      Thread checkThread = new Thread()
      {
        public void run()
        {
          GuiLabyModMenu.news = new ArrayList();
          ArrayList<String> list = Utils.getContentList("http://www.labymod.net/files/news.php");
          if (!list.isEmpty()) {
            for (String log : list) {
              if (log != null) {
                GuiLabyModMenu.news.add(GuiLabyModMenu.this.convertHtml(log));
              }
            }
          }
        }
      };
      checkThread.setPriority(1);
      checkThread.start();
    }
    if ((about == null) && (this.selectedTab.equals("About")))
    {
      Thread checkThread = new Thread()
      {
        public void run()
        {
          GuiLabyModMenu.about = new ArrayList();
          ArrayList<String> list = Utils.getContentList("http://www.labymod.net/files/about.php");
          if (!list.isEmpty()) {
            for (String log : list) {
              if (log != null) {
                GuiLabyModMenu.about.add(GuiLabyModMenu.this.convertHtml(log));
              }
            }
          }
        }
      };
      checkThread.setPriority(1);
      checkThread.start();
    }
    this.scrollbar.reset();
    
    this.navi.clear();
    ArrayList<String> list = new ArrayList();
    list.add("@LabyMod");
    list.add("@LabyStudio");
    list.add("@TrueZockermaus");
    this.navi.put("Twitter", list);
    list = new ArrayList();
    list.add("ts.LabyMod.net");
    this.navi.put("TeamSpeak", list);
    list = new ArrayList();
    list.add("www.LabyMod.net");
    this.navi.put("Website", list);
  }
  
  private String convertHtml(String html)
  {
    return html.replace("</br>", "").replace("<br>", "").replace("<strong>", Color.cl("l") + Color.cl("n")).replace("</strong>", Color.cl("r")).replace("<hr>", "").replace("+ ", Color.cl("7") + Color.cl("o") + "+ ").replace("- ", Color.cl("7") + Color.cl("o") + "- ").replace("</p>", "");
  }
  
  protected void a(bcz button)
  {
    switch (button.k)
    {
    case 0: 
      LabyMod.getInstance().openWebpage(Source.url_Update);
      break;
    case 1: 
      this.j.a(this.lastScreen);
      break;
    case 2: 
      try
      {
        Desktop.getDesktop().browse(new URI(Source.ts_labymod));
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
  
  protected void a(char typedChar, int keyCode)
  {
    if (keyCode == 1) {
      LabyMod.getInstance().back();
    }
  }
  
  public void k()
    throws IOException
  {
    super.k();
    this.scrollbar.mouseInput();
  }
  
  protected void a(int mouseX, int mouseY, int mouseButton)
    throws IOException
  {
    this.scrollbar.mouseAction(mouseX, mouseY, false);
    
    int tabNext = 0;
    for (String tab : this.tabs)
    {
      if ((mouseX > this.l - 120 - tabNext - this.draw.getStringWidth(tab)) && (mouseX < this.l - 120 - tabNext - this.draw.getStringWidth(tab) + this.draw.getStringWidth(tab)) && (mouseY > 7) && (mouseY < 17))
      {
        this.selectedTab = tab;
        b();
        break;
      }
      tabNext += this.draw.getStringWidth(tab) + 5;
    }
    super.a(mouseX, mouseY, mouseButton);
  }
  
  protected void a(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick)
  {
    this.scrollbar.mouseAction(mouseX, mouseY, true);
    super.a(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
  }
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    if (LabyMod.getInstance().isInGame())
    {
      bni.m();
      this.draw.drawTransparentBackground(10, 20, this.l - 120, this.m - 10);
    }
    else
    {
      c();
      this.draw.drawChatBackground(10, 20, this.l - 120, this.m - 10);
    }
    bni.l();
    ArrayList<String> selectedLog;
    try
    {
      y = 25 + this.scrollbar.getScrollY();
      
      selectedLog = null;
      if (this.selectedTab.equals("Changelog")) {
        selectedLog = changelog;
      }
      if (this.selectedTab.equals("News")) {
        selectedLog = news;
      }
      if (this.selectedTab.equals("About")) {
        selectedLog = about;
      }
      if (selectedLog == null)
      {
        this.draw.drawCenteredString(Color.cl("c") + "Loading..", (this.l - 120) / 2, this.m / 2);
        this.scrollbar.update(0);
      }
      else
      {
        for (String log : selectedLog)
        {
          this.draw.drawString(log, 15.0D, y);
          y += 10;
        }
        this.scrollbar.update(selectedLog.size());
      }
      this.draw.overlayBackground(this.l - 120, 0, this.l, this.m);
      y = 25;
      for (String title : this.navi.keySet())
      {
        this.draw.drawString(Color.cl("3") + title, this.l - 116, y, 1.1D);
        ArrayList<String> e = (ArrayList)this.navi.get(title);
        for (String entry : e)
        {
          y += 10;
          this.draw.drawString(Color.cl("7") + entry, this.l - 115, y);
        }
        y += 20;
      }
    }
    catch (Exception error)
    {
      int y;
      error.printStackTrace();
    }
    this.draw.overlayBackground(0, 0, this.l - 120, 20);
    this.draw.overlayBackground(0, this.m - 10, this.l - 120, this.m);
    this.draw.overlayBackground(0, 0, 10, this.m);
    
    int tabNext = 0;
    for (String tab : this.tabs)
    {
      String hover = Color.cl("f");
      if (this.selectedTab == tab)
      {
        DrawUtils.a(this.l - 120 - tabNext - this.draw.getStringWidth(tab) - 2, 2, this.l - 120 - tabNext - this.draw.getStringWidth(tab) + this.draw.getStringWidth(tab) + 2, 19, Integer.MIN_VALUE);
        hover = Color.cl("7");
      }
      if ((mouseX > this.l - 120 - tabNext - this.draw.getStringWidth(tab)) && (mouseX < this.l - 120 - tabNext - this.draw.getStringWidth(tab) + this.draw.getStringWidth(tab)) && (mouseY > 7) && (mouseY < 17)) {
        hover = Color.cl("7");
      }
      this.draw.drawString(hover + tab, this.l - 120 - tabNext - this.draw.getStringWidth(tab), 7.0D);
      tabNext += this.draw.getStringWidth(tab) + 5;
    }
    this.draw.drawString("LabyMod | " + this.selectedTab, 15.0D, 5.0D, 1.5D);
    this.draw.drawString("Official links:", this.l - 115, 5.0D, 1.5D);
    
    this.scrollbar.draw();
    
    super.a(mouseX, mouseY, partialTicks);
  }
}
