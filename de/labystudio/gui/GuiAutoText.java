package de.labystudio.gui;

import bcf;
import bcu;
import bcz;
import bda;
import bdd;
import bee;
import bfb;
import de.labystudio.gui.extras.ModGuiTextField;
import de.labystudio.labymod.LabyMod;
import de.labystudio.utils.AutoTextLoader;
import de.labystudio.utils.Color;
import de.labystudio.utils.DrawUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class GuiAutoText
  extends bfb
{
  private DrawUtils draw;
  private ModGuiTextField commandInput;
  private bdd chatField;
  private bcz addButton;
  private bcz toggle;
  private bcz help;
  private boolean allowScroll;
  private String text;
  private ArrayList<bcz> buttons = new ArrayList();
  
  public GuiAutoText(String text)
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
  
  public void initAutoText()
  {
    this.buttons.clear();
    AutoTextLoader.listening = false;
    AutoTextLoader.key = -1;
    AutoTextLoader.alt = false;
    AutoTextLoader.shift = false;
    AutoTextLoader.ctrl = false;
    Object toRem = null;
    for (String set : AutoTextLoader.autoText.keySet())
    {
      bcz b = new bcz(-3, 0, 0, 20, 20, Color.cl("c") + "X");
      b.run = set;
      this.n.add(b);
      this.buttons.add(b);
    }
  }
  
  public void drawAutoText()
  {
    int i = 25 + this.scroll;
    int a = 0;
    for (String m : AutoTextLoader.autoText.keySet()) {
      if (a < this.buttons.size())
      {
        bcz b = (bcz)this.buttons.get(a);
        b.m = ((6 + i > 15) && (6 + i < 160));
        if (b.m)
        {
          b.h = (this.l - 190);
          b.i = (6 + i);
          DrawUtils.a(this.l - 170, 6 + i, this.l - 7, 6 + i + 20, Integer.MIN_VALUE);
          String extra = "";
          if (AutoTextLoader.isAlt(m)) {
            extra = extra + "Alt+";
          }
          if (AutoTextLoader.isShift(m)) {
            extra = extra + "Shift+";
          }
          if (AutoTextLoader.isCtrl(m)) {
            extra = extra + "Ctrl+";
          }
          this.draw.drawString(Color.cl("7") + "[" + Color.cl("c") + extra + Keyboard.getKeyName(AutoTextLoader.getNormalKey(m)) + Color.cl("7") + "] " + Color.cl("e") + (String)AutoTextLoader.autoText.get(m), this.l - 167, 12 + i);
        }
        this.allowScroll = (6 + i > 160);
        i += 22;
        a++;
      }
    }
    if (AutoTextLoader.autoText.size() < 5) {
      this.scroll = 0;
    }
  }
  
  public void add()
  {
    if (this.addButton.l)
    {
      String buildHotkey = "";
      if (AutoTextLoader.alt) {
        buildHotkey = buildHotkey + "#ALT";
      }
      if (AutoTextLoader.ctrl) {
        buildHotkey = buildHotkey + "#CTRL";
      }
      if (AutoTextLoader.shift) {
        buildHotkey = buildHotkey + "#SHIFT";
      }
      AutoTextLoader.autoText.put(buildHotkey + AutoTextLoader.key, this.commandInput.b());
      initAutoText();
      AutoTextLoader.save();
    }
  }
  
  protected void a(int mouseX, int mouseY, int mouseButton)
    throws IOException
  {
    this.commandInput.a(mouseX, mouseY, mouseButton);
    this.chatField.a(mouseX, mouseY, mouseButton);
    super.a(mouseX, mouseY, mouseButton);
  }
  
  public void a(char typedChar, int keyCode)
    throws IOException
  {
    super.a(typedChar, keyCode);
    if (this.chatField.m())
    {
      this.text = this.chatField.b();
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
            this.chatField.a(typedChar, keyCode);
          }
        }
      }
      else
      {
        String var3 = this.chatField.b().trim();
        if (var3.length() > 0) {
          f(var3);
        }
        this.j.a((bfb)null);
      }
    }
    if ((this.commandInput.m()) && (!AutoTextLoader.listening)) {
      this.commandInput.a(typedChar, keyCode);
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
    this.addButton = new bcz(1, this.l - 192, 4, 30, 20, "");
    this.n.add(this.addButton);
    this.n.add(new bcz(0, this.l - 48, 4, 45, 20, Color.cl("c") + "Close"));
    this.help = new bcz(-1, this.l - 215, 4, 20, 20, Color.cl("b") + "?");
    this.n.add(this.help);
    String a = "✖";
    if (AutoTextLoader.enabled) {
      a = "✔";
    }
    a = Color.booleanToColor(Boolean.valueOf(AutoTextLoader.enabled)) + a;
    this.toggle = new bcz(2, this.l - 215, 27, 20, 20, a);
    this.n.add(this.toggle);
    initAutoText();
    
    Keyboard.enableRepeatEvents(true);
    this.chatField = new bdd(0, this.q, 4, this.m - 12, this.l - 4, 12);
    this.chatField.f(500);
    this.chatField.a(false);
    this.chatField.b(true);
    this.chatField.a(this.text);
    
    this.commandInput = new ModGuiTextField(0, this.q, this.l - 160, 6, 110, 17);
    this.commandInput.f(100);
    this.commandInput.b(false);
  }
  
  protected void a(bcz button)
  {
    switch (button.k)
    {
    case 0: 
      this.j.a(new bee(this.text));
      break;
    case 1: 
      if (!this.commandInput.b().replace(" ", "").isEmpty())
      {
        AutoTextLoader.key = -1;
        AutoTextLoader.listening = true;
        AutoTextLoader.alt = false;
        AutoTextLoader.shift = false;
        AutoTextLoader.ctrl = false;
      }
      break;
    case 2: 
      AutoTextLoader.enabled = !AutoTextLoader.enabled;
      b();
    }
    if (button.k == -5)
    {
      this.chatField.a("&".charAt(0), 0);
      this.chatField.a(button.j.replace(Color.c + "", "").substring(0, 1).charAt(0), 0);
    }
    if (button.run.equals("true")) {
      this.chatField.a(button.j.charAt(0), 0);
    }
    if (button.k == -3)
    {
      AutoTextLoader.autoText.remove(button.run);
      this.buttons.remove(button);
      b();
      AutoTextLoader.save();
    }
  }
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    this.draw = LabyMod.getInstance().draw;
    a(2, this.m - 14, this.l - 2, this.m - 2, Integer.MIN_VALUE);
    a(this.l - 193, 27, this.l - 3, 169, Integer.MIN_VALUE);
    a(this.l - 193, 3, this.l - 3, 25, Integer.MIN_VALUE);
    this.addButton.l = (!this.commandInput.b().replace(" ", "").isEmpty());
    this.chatField.g();
    this.commandInput.g();
    drawAutoText();
    if (AutoTextLoader.listening)
    {
      this.addButton.j = "> <";
      if (AutoTextLoader.key != -1)
      {
        if (!contains(AutoTextLoader.key + "")) {
          add();
        }
        AutoTextLoader.key = -1;
        AutoTextLoader.listening = false;
        AutoTextLoader.alt = false;
        AutoTextLoader.shift = false;
        AutoTextLoader.ctrl = false;
        this.commandInput.a("");
      }
    }
    else
    {
      this.addButton.j = (Color.cl("a") + "Add");
    }
    super.a(mouseX, mouseY, partialTicks);
    if (this.help.a())
    {
      this.draw.drawRightString("First write a sentence then enter your hotkey", mouseX, mouseY);
      this.draw.drawRightString("and by pressing the hotkey during gameplay", mouseX, mouseY + 10);
      this.draw.drawRightString("your sentence will be written in the chat!", mouseX, mouseY + 20);
    }
    if (this.toggle.a()) {
      if (AutoTextLoader.enabled) {
        this.draw.drawRightString(Color.cl("a") + "AutoText enabled", mouseX, mouseY);
      } else {
        this.draw.drawRightString(Color.cl("c") + "AutoText disabled", mouseX, mouseY);
      }
    }
  }
  
  private boolean contains(String i)
  {
    for (String m : AutoTextLoader.autoText.keySet()) {
      if (m.equals(i)) {
        return true;
      }
    }
    return false;
  }
}
