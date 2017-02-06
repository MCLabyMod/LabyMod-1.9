package de.labystudio.gui;

import bcf;
import bcz;
import bdd;
import bex;
import bfb;
import bfi;
import bgr;
import de.labystudio.labymod.LabyMod;
import de.labystudio.utils.Color;
import de.labystudio.utils.DrawUtils;
import java.io.IOException;
import java.util.List;
import org.lwjgl.input.Keyboard;

public class GuiGommeHDSearch
  extends bfb
{
  private DrawUtils draw;
  private bdd field_146302_g;
  private bdd field_146302_g2;
  private bdd field_146302_g3;
  bcz gommeSeachAllowedButton;
  bcz gommeAutoJoinButton;
  bcz gommeSoundButton;
  bcz buttonClear;
  
  public void b()
  {
    Keyboard.enableRepeatEvents(true);
    this.draw = LabyMod.getInstance().draw;
    this.n.clear();
    boolean var2 = true;
    
    this.buttonClear = new bcz(0, this.l / 2 + 81, this.m / 2 - 53, 20, 20, Color.c + "4" + "X");
    this.n.add(this.buttonClear);
    
    this.field_146302_g = new bdd(2, this.draw.fontRenderer, this.l / 2 - 99, this.m / 2 - 53, 178, 20);
    this.field_146302_g.b(true);
    this.field_146302_g.a(LabyMod.getInstance().gommeHDSearch);
    this.field_146302_g.f(400);
    this.buttonClear.l = ((this.field_146302_g.b().length() > 0) && (this.field_146302_g.b().split(":").length > 0));
    
    this.field_146302_g2 = new bdd(2, this.draw.fontRenderer, this.l / 2 + 10, this.m / 2 - 12, 90, 20);
    this.field_146302_g2.a(LabyMod.getInstance().gommeHDSeachPartySize + "");
    this.field_146302_g2.f(2);
    
    this.field_146302_g3 = new bdd(8, this.draw.fontRenderer, this.l / 2 + 10, this.m / 2 + 29, 90, 20);
    this.field_146302_g3.a(LabyMod.getInstance().gommeHDSearchBlacklist + "");
    this.field_146302_g3.f(400);
    
    this.gommeSeachAllowedButton = new bcz(1, this.l / 2 - 100, this.m / 2 - 12, 90, 20, getSymbol(LabyMod.getInstance().gommeHDSeachAllowed));
    this.n.add(this.gommeSeachAllowedButton);
    
    this.gommeAutoJoinButton = new bcz(2, this.l / 2 - 100, this.m / 2 + 28, 90, 20, getSymbol(LabyMod.getInstance().gommeHDAutoJoin));
    this.n.add(this.gommeAutoJoinButton);
    
    this.gommeSoundButton = new bcz(3, this.l / 2 - 100, this.m / 2 + 65, 90, 20, getSymbol(LabyMod.getInstance().gommeHDSound));
    this.n.add(this.gommeSoundButton);
    
    bcz b = new bcz(-1, this.l - 53, this.m - 23, 50, 20, "Search");
    b.l = false;
    this.n.add(b);
  }
  
  public String getSymbol(boolean b)
  {
    if (b) {
      return Color.c + "a✔ Enabled";
    }
    return Color.c + "4✖ Disabled";
  }
  
  protected void a(bcz button)
  {
    switch (button.k)
    {
    case 0: 
      this.field_146302_g.a("");
      this.buttonClear.l = false;
      break;
    case 1: 
      LabyMod.getInstance().gommeHDSeachAllowed = (!LabyMod.getInstance().gommeHDSeachAllowed);
      if (!LabyMod.getInstance().gommeHDSeachAllowed)
      {
        LabyMod.getInstance().gommeHDAutoJoin = false;
        LabyMod.getInstance().gommeHDSound = false;
      }
      button.j = getSymbol(LabyMod.getInstance().gommeHDSeachAllowed);
      this.gommeAutoJoinButton.j = getSymbol(LabyMod.getInstance().gommeHDAutoJoin);
      
      button.j = getSymbol(LabyMod.getInstance().gommeHDSeachAllowed);
      this.gommeSoundButton.j = getSymbol(LabyMod.getInstance().gommeHDSound);
      break;
    case 2: 
      LabyMod.getInstance().gommeHDAutoJoin = (!LabyMod.getInstance().gommeHDAutoJoin);
      button.j = getSymbol(LabyMod.getInstance().gommeHDAutoJoin);
      break;
    case 3: 
      LabyMod.getInstance().gommeHDSound = (!LabyMod.getInstance().gommeHDSound);
      button.j = getSymbol(LabyMod.getInstance().gommeHDSound);
    }
    save();
  }
  
  public void back()
  {
    save();
    if (LabyMod.getInstance().isInGame()) {
      this.j.a(new bex());
    } else {
      this.j.a(new bgr(new bfi()));
    }
  }
  
  public void save()
  {
    LabyMod.getInstance().gommeHDSearch = this.field_146302_g.b();
    LabyMod.getInstance().gommeHDSearchBlacklist = this.field_146302_g3.b();
    if (!this.field_146302_g2.b().isEmpty())
    {
      if (isNumeric(this.field_146302_g2.b())) {
        LabyMod.getInstance().gommeHDSeachPartySize = Integer.parseInt(this.field_146302_g2.b());
      } else {
        this.field_146302_g2.a("0");
      }
    }
    else {
      LabyMod.getInstance().gommeHDSeachPartySize = 0;
    }
  }
  
  public static boolean isNumeric(String str)
  {
    for (char c : str.toCharArray()) {
      if (!Character.isDigit(c)) {
        return false;
      }
    }
    return true;
  }
  
  protected void a(char typedChar, int keyCode)
  {
    if (this.field_146302_g.a(typedChar, keyCode))
    {
      this.buttonClear.l = ((this.field_146302_g.b().length() > 0) && (this.field_146302_g.b().split(":").length > 0));
      save();
    }
    if (this.field_146302_g3.a(typedChar, keyCode)) {
      save();
    }
    if ((isNumeric(typedChar + "")) || (keyCode == 14))
    {
      if (this.field_146302_g2.a(typedChar, keyCode)) {
        save();
      }
    }
    else if (keyCode == 1) {
      this.j.a(null);
    }
  }
  
  protected void a(int mouseX, int mouseY, int mouseButton)
    throws IOException
  {
    super.a(mouseX, mouseY, mouseButton);
    this.field_146302_g.a(mouseX, mouseY, mouseButton);
    this.field_146302_g2.a(mouseX, mouseY, mouseButton);
    this.field_146302_g3.a(mouseX, mouseY, mouseButton);
  }
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    c();
    
    this.gommeAutoJoinButton.l = LabyMod.getInstance().gommeHDSeachAllowed;
    this.gommeSoundButton.l = LabyMod.getInstance().gommeHDSeachAllowed;
    
    this.draw.drawCenteredString("" + Color.c + "fGommeHD Map Search", this.l / 2, this.m / 2 - 70);
    this.draw.drawString("" + Color.c + "fColored Signs:", this.l / 2 - 99, this.m / 2 - 24);
    this.draw.drawString("" + Color.c + "fParty size:", this.l / 2 + 10, this.m / 2 - 24);
    
    this.draw.drawString("" + Color.c + "fAutojoin:", this.l / 2 - 99, this.m / 2 + 17);
    this.draw.drawString("" + Color.c + "fBlacklist:", this.l / 2 + 10, this.m / 2 + 17);
    
    this.draw.drawString("" + Color.c + "fSounds:", this.l / 2 - 99, this.m / 2 + 55);
    
    this.draw.drawBox(this.l / 2 + 80, this.m / 2 - 54, this.l / 2 - 99 + 201, this.m / 2 - 53 + 21);
    
    this.field_146302_g.g();
    this.field_146302_g2.g();
    this.field_146302_g3.g();
    if ((!LabyMod.getInstance().gommeHDSearch.isEmpty()) && 
      (LabyMod.getInstance().gommeHDSearch.toLowerCase().contains(LabyMod.getInstance().gommeHDSearchBlacklist.toLowerCase())))
    {
      if (LabyMod.getInstance().gommeHDSearchBlacklist.length() < 13) {
        this.draw.drawString(Color.c + "c" + LabyMod.getInstance().gommeHDSearchBlacklist, this.l / 2 + 14, this.m / 2 + 35);
      }
      if (LabyMod.getInstance().gommeHDSearch.length() < 22) {
        this.draw.drawString(LabyMod.getInstance().gommeHDSearch.replace(LabyMod.getInstance().gommeHDSearchBlacklist, Color.c + "c" + LabyMod.getInstance().gommeHDSearchBlacklist + Color.c + "f"), this.l / 2 - 95, this.m / 2 - 47);
      }
    }
    super.a(mouseX, mouseY, partialTicks);
  }
}
