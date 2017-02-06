package de.labystudio.gui;

import bcf;
import bcv;
import bcz;
import bfb;
import de.labystudio.chat.Client;
import de.labystudio.chat.LabyModPlayer;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.ModSettings;
import de.labystudio.language.L;
import de.labystudio.utils.Color;
import de.labystudio.utils.DrawUtils;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import org.lwjgl.opengl.GL11;

public class GuiMenuScreen
  extends bfb
{
  private bcv parentScreen;
  public bfb childScreen;
  public String id = "";
  
  public GuiMenuScreen(bfb parent)
  {
    this.parentScreen = parent;
  }
  
  public bcv getParent()
  {
    return this.parentScreen;
  }
  
  protected void actionPermformed(bcz button)
    throws IOException
  {
    super.a(button);
    if (button.k == 100)
    {
      LabyMod.getInstance();LabyMod.getInstance().openMenu = null;
      LabyMod.getInstance();LabyMod.getInstance().back();
    }
    if (button.k == 102)
    {
      bfb screen = new GuiOnlineChat();
      LabyMod.getInstance();LabyMod.getInstance().openMenu = screen;
      this.j.a(screen);
    }
    if (button.k == 103)
    {
      bfb screen = new GuiTags();
      LabyMod.getInstance();LabyMod.getInstance().openMenu = screen;
      this.j.a(screen);
    }
    if (button.k == 104)
    {
      bfb screen = new GuiTeamSpeak();
      LabyMod.getInstance();LabyMod.getInstance().openMenu = screen;
      this.j.a(screen);
    }
    if (button.k == 105)
    {
      bfb screen = new GuiGames();
      LabyMod.getInstance();LabyMod.getInstance().openMenu = screen;
      this.j.a(screen);
    }
    if (button.k == 106)
    {
      bfb screen = new GuiStopWatch();
      LabyMod.getInstance();LabyMod.getInstance().openMenu = screen;
      this.j.a(screen);
    }
  }
  
  public void b()
  {
    LabyMod.getInstance();LabyMod.getInstance().chatChange = false;
    LabyMod.getInstance();
    if (LabyMod.getInstance().openMenu != null) {
      if (this.childScreen == null)
      {
        System.out.println("Can't find childScreen");
        LabyMod.getInstance();this.j.a(LabyMod.getInstance().openMenu);
      }
      else
      {
        LabyMod.getInstance();
        if (!this.childScreen.getClass().getSimpleName().equalsIgnoreCase(LabyMod.getInstance().openMenu.getClass().getSimpleName()))
        {
          LabyMod.getInstance();this.j.a(LabyMod.getInstance().openMenu);
          return;
        }
      }
    }
    super.b();
    this.next = 2;
    LabyMod.getInstance();LabyMod.getInstance();addButton(100, LabyMod.getInstance().isInGame() ? L._("tab_menu", new Object[0]) : L._("tab_multiplayer", new Object[0]), LabyMod.getInstance().isInGame() ? "Menu" : "Multiplayer");
    
    addButton(102, L._("tab_chat", new Object[0]), "Chat");
    if (ConfigManager.settings.tags.booleanValue()) {
      addButton(103, L._("tab_tags", new Object[0]), "Tags");
    }
    if (ConfigManager.settings.teamSpeak.booleanValue()) {
      addButton(104, L._("tab_teamspeak", new Object[0]), "TeamSpeak");
    }
    if (ConfigManager.settings.miniGames) {
      addButton(105, L._("tab_games", new Object[0]), "Games");
    }
    if (ConfigManager.settings.stopWatch) {
      addButton(106, L._("tab_stopwatch", new Object[0]), "Stopwatch");
    }
  }
  
  int chat = 0;
  int next = 2;
  
  public void addButton(int id, String title, String uid)
  {
    LabyMod.getInstance();bcz b = new bcz(id, this.next, 4, LabyMod.getInstance().draw.getStringWidth(title) + 10, 20, title);
    if (this.id.toLowerCase().contains(uid.toLowerCase())) {
      b.l = false;
    }
    this.n.add(b);
    LabyMod.getInstance();this.next += LabyMod.getInstance().draw.getStringWidth(title) + 11;
    if (title.toLowerCase().contains(L._("tab_chat", new Object[0]))) {
      this.chat = this.next;
    }
  }
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    super.a(mouseX, mouseY, partialTicks);
    LabyMod.getInstance();LabyMod.getInstance().overlay(mouseX, mouseY);
    if (this.chat != 0) {
      try
      {
        int amount = 0;
        for (LabyModPlayer p : LabyMod.getInstance().getClient().getFriends()) {
          amount += p.messages;
        }
        if (amount != 0)
        {
          GL11.glPushMatrix();
          GL11.glScaled(0.5D, 0.5D, 0.5D);
          LabyMod.getInstance().draw.drawRightString(Color.cl("c") + amount, (this.chat - 5) / 0.5D, 38.0D);
          GL11.glPopMatrix();
        }
      }
      catch (Exception localException) {}
    }
  }
}
