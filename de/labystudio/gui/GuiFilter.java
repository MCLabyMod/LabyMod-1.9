package de.labystudio.gui;

import bcf;
import bcu;
import bcz;
import bda;
import bdd;
import bee;
import bfb;
import de.labystudio.labymod.LabyMod;
import de.labystudio.utils.Color;
import de.labystudio.utils.DrawUtils;
import de.labystudio.utils.FilterLoader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class GuiFilter
  extends bfb
{
  private DrawUtils draw;
  private bdd inputField;
  private bdd input;
  private bcz addButton;
  private bcz toggle;
  private bcz help;
  private boolean allowScroll;
  private String text;
  private ArrayList<bcz> buttons = new ArrayList();
  
  public GuiFilter(String text)
  {
    this.text = text;
  }
  
  int z = 0;
  int y = 0;
  int lines = 8;
  int scroll = 0;
  
  public void addSymbol(String symbol)
  {
    bcz button = new bcz(-1, this.l - 4 - 20 - this.z, 4 + this.y, 20, 20, symbol);
    button.run = "true";
    this.n.add(button);
    this.z += 24;
    if (this.z % (24 * this.lines) == 0)
    {
      this.z = 0;
      this.y += 24;
    }
  }
  
  public void initFilters()
  {
    this.buttons.clear();
    for (String filter : FilterLoader.filters)
    {
      bcz b = new bcz(-3, 0, 0, 20, 20, Color.cl("c") + "X");
      b.run = filter;
      this.n.add(b);
      this.buttons.add(b);
    }
  }
  
  public void drawFilters()
  {
    int i = 25 + this.scroll;
    int a = 0;
    for (String filter : FilterLoader.filters) {
      if (a < this.buttons.size())
      {
        bcz b = (bcz)this.buttons.get(a);
        b.m = ((6 + i > 15) && (6 + i < 160));
        if (b.m)
        {
          b.h = (this.l - 190);
          b.i = (6 + i);
          DrawUtils.a(this.l - 170, 6 + i, this.l - 7, 6 + i + 20, Integer.MIN_VALUE);
          this.draw.drawString(filter.replace("%b%", " | " + Color.cl("c")).replace("%k%", Color.cl("6") + "").replace("%s%", Color.cl("b") + " (Sound) "), this.l - 167, 12 + i);
        }
        this.allowScroll = (6 + i > 160);
        i += 22;
        a++;
      }
    }
    if (FilterLoader.filters.size() < 5) {
      this.scroll = 0;
    }
  }
  
  public void add()
  {
    if (this.addButton.l)
    {
      FilterLoader.filters.add(this.input.b());
      this.input.a("");
      initFilters();
      FilterLoader.saveFilters();
    }
  }
  
  protected void a(int mouseX, int mouseY, int mouseButton)
    throws IOException
  {
    this.input.a(mouseX, mouseY, mouseButton);
    this.inputField.a(mouseX, mouseY, mouseButton);
    super.a(mouseX, mouseY, mouseButton);
  }
  
  public void a(char typedChar, int keyCode)
    throws IOException
  {
    super.a(typedChar, keyCode);
    if (this.inputField.m())
    {
      this.text = this.inputField.b();
      if (keyCode == 1)
      {
        this.j.a((bfb)null);
      }
      else if ((keyCode != 28) && (keyCode != 156))
      {
        if ((keyCode != 200) && 
          (keyCode != 208)) {
          if (keyCode == 201) {
            this.j.r.d().b(this.j.r.d().i() - 1);
          } else if (keyCode == 209) {
            this.j.r.d().b(-this.j.r.d().i() + 1);
          } else {
            this.inputField.a(typedChar, keyCode);
          }
        }
      }
      else
      {
        String var3 = this.inputField.b().trim();
        if (var3.length() > 0) {
          f(var3);
        }
        this.j.a((bfb)null);
      }
    }
    if (this.input.m()) {
      if (keyCode == 1) {
        this.j.a((bfb)null);
      } else if (keyCode == 28) {
        add();
      } else {
        this.input.a(typedChar, keyCode);
      }
    }
  }
  
  public void k()
    throws IOException
  {
    super.k();
    int var1 = Mouse.getEventDWheel();
    if (var1 != 0)
    {
      if (var1 > 1) {
        var1 = 1;
      }
      if (var1 < -1) {
        var1 = -1;
      }
      if (var1 > 0)
      {
        if (this.scroll < 0) {
          this.scroll += 22;
        }
      }
      else if (this.allowScroll) {
        this.scroll -= 22;
      }
    }
  }
  
  public void b()
  {
    this.n.clear();
    this.addButton = new bcz(1, this.l - 192, 4, 30, 20, Color.cl("c") + "Add");
    this.n.add(this.addButton);
    this.n.add(new bcz(0, this.l - 48, 4, 45, 20, Color.cl("c") + "Close"));
    this.help = new bcz(-1, this.l - 215, 4, 20, 20, Color.cl("b") + "?");
    this.n.add(this.help);
    String a = "✖";
    if (FilterLoader.enabled) {
      a = "✔";
    }
    a = Color.booleanToColor(Boolean.valueOf(FilterLoader.enabled)) + a;
    this.toggle = new bcz(2, this.l - 215, 27, 20, 20, a);
    this.n.add(this.toggle);
    initFilters();
    
    Keyboard.enableRepeatEvents(true);
    this.inputField = new bdd(0, this.q, 4, this.m - 12, this.l - 4, 12);
    this.inputField.f(500);
    this.inputField.a(false);
    this.inputField.b(true);
    this.inputField.a(this.text);
    
    this.input = new bdd(0, this.q, this.l - 160, 6, 110, 17);
    this.input.f(500);
    this.input.b(false);
  }
  
  protected void a(bcz button)
  {
    switch (button.k)
    {
    case 0: 
      this.j.a(new bee(this.text));
      break;
    case 1: 
      add();
      break;
    case 2: 
      FilterLoader.enabled = !FilterLoader.enabled;
      b();
    }
    if (button.k == -5)
    {
      this.inputField.a("&".charAt(0), 0);
      this.inputField.a(button.j.replace(Color.c + "", "").substring(0, 1).charAt(0), 0);
    }
    if (button.run.equals("true")) {
      this.inputField.a(button.j.charAt(0), 0);
    }
    if (button.k == -3)
    {
      FilterLoader.filters.remove(button.run);
      this.buttons.remove(button);
      b();
      FilterLoader.saveFilters();
    }
  }
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    this.draw = LabyMod.getInstance().draw;
    a(2, this.m - 14, this.l - 2, this.m - 2, Integer.MIN_VALUE);
    a(this.l - 193, 27, this.l - 3, 169, Integer.MIN_VALUE);
    a(this.l - 193, 3, this.l - 3, 25, Integer.MIN_VALUE);
    this.addButton.l = ((!this.input.b().replace(" ", "").isEmpty()) && (!contains(this.input.b())));
    this.inputField.g();
    this.input.g();
    drawFilters();
    super.a(mouseX, mouseY, partialTicks);
    if (this.help.a())
    {
      int i = 0;
      this.draw.drawRightString("If a word of this list is written", mouseX, mouseY);i += 10;
      this.draw.drawRightString("in the chat, it will automatically", mouseX, mouseY + i);i += 10;
      this.draw.drawRightString("be displayed in an extra chat to ", mouseX, mouseY + i);i += 10;
      this.draw.drawRightString("the right of the normal chat.", mouseX, mouseY + i);i += 10;
      this.draw.drawRightString("If you add " + Color.cl("b") + "%k%" + Color.cl("f") + " in front of a specific word,", mouseX, mouseY + i, 0.5D);i += 5;
      this.draw.drawRightString("you want to filter out of the chat " + Color.cl("c") + "(e.g. %k%Test)" + Color.cl("f") + ",", mouseX, mouseY + i, 0.5D);i += 5;
      this.draw.drawRightString("the message will be highlighted and WON'T be shown on the extra Chat.", mouseX, mouseY + i, 0.5D);i += 10;
      this.draw.drawRightString("To avoid specific words from a message,", mouseX, mouseY + i, 0.5D);i += 5;
      this.draw.drawRightString("you can create a blacklist with " + Color.cl("b") + "%b%" + Color.cl("f") + ". " + Color.cl("c") + "(e.g. Test%b%Hello)" + Color.cl("f") + "", mouseX, mouseY + i, 0.5D);i += 5;
      this.draw.drawRightString("Now, the word 'Test' would be filtered, but", mouseX, mouseY + i, 0.5D);i += 5;
      this.draw.drawRightString("if the same message contains 'Hello' it would be ignored.", mouseX, mouseY + i, 0.5D);i += 5;
      this.draw.drawRightString("This you can do also with more Words", mouseX, mouseY + i, 0.5D);i += 5;
      this.draw.drawRightString("" + Color.cl("c") + "(e.g. Test%b%Hello%b%How are you%b%Minecraft)" + Color.cl("f") + "", mouseX, mouseY + i, 0.5D);i += 10;
      this.draw.drawRightString("To get a Sound alert on specific Messages,", mouseX, mouseY + i, 0.5D);i += 5;
      this.draw.drawRightString("just add " + Color.cl("b") + "%s%" + Color.cl("f") + " at the end of the word " + Color.cl("c") + "(e.g. Test%s%)", mouseX, mouseY + i, 0.5D);i += 5;
      this.draw.drawRightString("Now you would hear a sound, when 'Test' is written in the Chat.", mouseX, mouseY + i, 0.5D);i += 10;
      this.draw.drawRightString("The whole thing with " + Color.cl("b") + "%k%" + Color.cl("f") + ", " + Color.cl("b") + "%b%" + Color.cl("f") + " and " + Color.cl("b") + "%s%" + Color.cl("f") + " can also be combined:", mouseX, mouseY + i, 0.5D);i += 5;
      this.draw.drawRightString("" + Color.cl("c") + "%k%Test%b%Hello%s%", mouseX, mouseY + i, 0.5D);i += 5;
      this.draw.drawRightString("->Now, the message would be", mouseX, mouseY + i, 0.5D);i += 5;
      this.draw.drawRightString("highlighted and you can hear a sound alert,", mouseX, mouseY + i, 0.5D);i += 5;
      this.draw.drawRightString("if it isn't containing the word 'Hello'", mouseX, mouseY + i, 0.5D);i += 5;
    }
    if (this.toggle.a()) {
      if (FilterLoader.enabled) {
        this.draw.drawRightString(Color.cl("a") + "Filter enabled", mouseX, mouseY);
      } else {
        this.draw.drawRightString(Color.cl("c") + "Filter disabled", mouseX, mouseY);
      }
    }
  }
  
  private boolean contains(String s)
  {
    for (String filter : FilterLoader.filters) {
      if (s.equalsIgnoreCase(filter)) {
        return true;
      }
    }
    return false;
  }
}
