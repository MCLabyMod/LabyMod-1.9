package de.labystudio.gui;

import bcf;
import bch;
import bcz;
import bfb;
import bmq;
import bwo;
import de.labystudio.capes.CapeCallback;
import de.labystudio.capes.CapeManager;
import de.labystudio.capes.CapeMover;
import de.labystudio.capes.CapeUploader;
import de.labystudio.capes.EnumCapePriority;
import de.labystudio.cosmetic.CosmeticManager;
import de.labystudio.cosmetic.EnumCosmetic;
import de.labystudio.downloader.ModInfoDownloader;
import de.labystudio.downloader.UserCapesDownloader;
import de.labystudio.gui.extras.ModGuiTextField;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.ModSettings;
import de.labystudio.labymod.Source;
import de.labystudio.utils.Color;
import de.labystudio.utils.DrawUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.lwjgl.input.Keyboard;
import zk;

public class GuiCapeSettings
  extends bfb
{
  private bfb lastScreen;
  String error = "";
  private static long cdPriority = 0L;
  private static long cdRefresh = 0L;
  boolean hasCape = false;
  boolean moveCape = false;
  boolean accept = false;
  EnumCapePriority capeType;
  bcz a;
  bcz b;
  bcz c;
  bcz d;
  ModGuiTextField e;
  bcz f;
  bcz g;
  bcz h;
  bcz i;
  bcz j;
  
  public GuiCapeSettings(bfb lastScreen)
  {
    Keyboard.enableRepeatEvents(true);
    this.lastScreen = lastScreen;
    checkCape();
  }
  
  public void checkCape()
  {
    this.hasCape = ((bcf.z() != null) && (bcf.z().h != null) && ((bcf.z().h instanceof bmq)) && (bcf.z().h.capeType != null));
    if (this.hasCape) {
      this.capeType = bcf.z().h.capeType;
    }
  }
  
  public void b()
  {
    this.accept = false;
    this.moveCape = false;
    this.n.clear();
    
    this.n.add(this.a = new bcz(1, this.l / 2 - 110, this.m / 2 - 28 - 20, 80, 20, ""));
    this.n.add(this.b = new bcz(2, this.l / 2 - 110, this.m / 2 + 11 - 20, 80, 20, ""));
    this.n.add(this.c = new bcz(3, this.l / 2 - 110, this.m / 2 + 35 - 20, 80, 20, ""));
    this.n.add(this.d = new bcz(4, this.l / 2 - 110, this.m / 2 + 59 - 20, 80, 20, ""));
    this.n.add(this.h = new bcz(7, 2, this.m - 22, 60, 20, ""));
    this.e = new ModGuiTextField(0, this.jdField_j_of_type_Bcf.k, this.l / 2 - 100, this.m / 4 - 30, 200, 20);
    this.e.setBlacklistWord(" ");
    this.n.add(this.f = new bcz(5, this.l / 2 - 100, this.m / 4 - 5, 200, 20, ""));
    this.n.add(this.g = new bcz(6, 2, 2, 40, 20, ""));
    
    this.n.add(this.jdField_j_of_type_Bcz = new bcz(9, this.l / 2 - 110, this.m / 2 + 82 - 20, 80, 20, ""));
    this.n.add(new bcz(200, this.l / 2 - 100, this.m / 2 + 90, bwo.a("gui.done", new Object[0])));
    
    this.n.add(this.i = new bcz(8, this.l - 20 - 2, 2, 20, 20, "C"));
    this.i.m = LabyMod.getInstance().getCosmeticManager().hasCosmetic(new EnumCosmetic[] { EnumCosmetic.WINGS, EnumCosmetic.BLAZE, EnumCosmetic.HAT });
    
    refreshButtons();
  }
  
  public void refreshButtons()
  {
    if (ConfigManager.settings.capePriority.equals("of")) {
      this.a.j = "OptiFine";
    } else if (ConfigManager.settings.capePriority.equals("original")) {
      this.a.j = "Original";
    } else {
      this.a.j = "LabyMod";
    }
    this.b.j = getStatus("Cape", zk.a);
    this.d.j = "Move";
    if (this.accept) {
      this.f.j = (Color.cl("c") + Color.cl("l") + "Press again to accept");
    } else {
      this.f.j = "Move";
    }
    this.g.j = (Color.cl("6") + "Donate");
    this.h.j = "Refresh";
    if (LabyMod.getInstance().getCosmeticManager().colorPicker) {
      this.i.j = (Color.cl("a") + "C");
    } else {
      this.i.j = (Color.cl("c") + "C");
    }
    if (ConfigManager.settings.capes.booleanValue()) {
      this.jdField_j_of_type_Bcz.j = ("Capes: " + Color.cl("a") + "ON");
    } else {
      this.jdField_j_of_type_Bcz.j = ("Capes: " + Color.cl("c") + "OFF");
    }
  }
  
  public String getStatus(String name, zk part)
  {
    String var2;
    String var2;
    if (this.jdField_j_of_type_Bcf.u.d().contains(part)) {
      var2 = Color.cl("a") + "SHOWN";
    } else {
      var2 = Color.cl("C") + "HIDDEN";
    }
    if (!this.hasCape) {
      var2 = "NO CAPE";
    }
    return var2 + Color.cl("f") + "";
  }
  
  protected void a(bcz button)
    throws IOException
  {
    if (button.l) {
      if (button.k == 1)
      {
        if (ConfigManager.settings.capePriority.equals("of")) {
          ConfigManager.settings.capePriority = "labymod";
        } else if (ConfigManager.settings.capePriority.equals("labymod")) {
          ConfigManager.settings.capePriority = "original";
        } else {
          ConfigManager.settings.capePriority = "of";
        }
        cdPriority = System.currentTimeMillis();
        LabyMod.getInstance().getCapeManager().refresh();
        refreshButtons();
        ConfigManager.save();
      }
      else if (button.k == 2)
      {
        this.jdField_j_of_type_Bcf.u.a(zk.a);
        refreshButtons();
      }
      else if (button.k == 3)
      {
        new CapeUploader(new CapeCallback()
        {
          public void failed(String error)
          {
            GuiCapeSettings.this.error = error;
          }
          
          public void done()
          {
            GuiCapeSettings.this.error = (Color.cl("a") + "Cape uploaded!");
          }
        })
        
          .start();
      }
      else if (button.k == 4)
      {
        this.moveCape = true;
        LabyMod.getInstance().getCosmeticManager().colorPicker = false;
        refreshButtons();
      }
      else if (button.k == 5)
      {
        if ((!this.accept) && (this.e.b().length() != 0))
        {
          this.accept = true;
          refreshButtons();
        }
        else
        {
          button.l = false;
          new CapeMover(this.e.b(), new CapeCallback()
          {
            public void done()
            {
              LabyMod.getInstance().getCapeManager().refresh();
              GuiCapeSettings.this.checkCape();
              GuiCapeSettings.this.b();
            }
            
            public void failed(String error)
            {
              GuiCapeSettings.this.b();
              GuiCapeSettings.this.error = error;
            }
          })
          
            .start();
        }
      }
      else if (button.k == 6)
      {
        LabyMod.getInstance().openWebpage(Source.url_donate);
      }
      else if (button.k == 200)
      {
        this.jdField_j_of_type_Bcf.u.b();
        this.jdField_j_of_type_Bcf.a(this.lastScreen);
      }
      else if (button.k == 7)
      {
        cdRefresh = System.currentTimeMillis();
        LabyMod.getInstance().getCapeManager().refresh();
        LabyMod.getInstance().getCosmeticManager().getCosmetics().clear();
        new ModInfoDownloader();
        new UserCapesDownloader();
        refreshButtons();
      }
      else if (button.k == 8)
      {
        this.moveCape = false;
        LabyMod.getInstance().getCosmeticManager().colorPicker = (!LabyMod.getInstance().getCosmeticManager().colorPicker);
        refreshButtons();
      }
      else if (button.k == 9)
      {
        ConfigManager.settings.capes = Boolean.valueOf(!ConfigManager.settings.capes.booleanValue());
        refreshButtons();
      }
    }
  }
  
  protected void a(char typedChar, int keyCode)
    throws IOException
  {
    if (keyCode == 1)
    {
      this.jdField_j_of_type_Bcf.u.b();
      this.jdField_j_of_type_Bcf.a(this.lastScreen);
    }
    if ((this.moveCape) && 
      (this.e.a(typedChar, keyCode)) && 
      (this.e.b().length() > 16)) {
      this.e.a(this.e.b().substring(0, 16));
    }
    super.a(typedChar, keyCode);
  }
  
  protected void a(int mouseX, int mouseY, int mouseButton)
    throws IOException
  {
    if (this.moveCape) {
      this.e.a(mouseX, mouseY, mouseButton);
    }
    super.a(mouseX, mouseY, mouseButton);
  }
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    if (!LabyMod.getInstance().isInGame())
    {
      this.jdField_j_of_type_Bcf.a(this.lastScreen);
      return;
    }
    c();
    a(this.q, "Cape Settings", this.l / 2, 20, 16777215);
    if (!this.error.isEmpty())
    {
      int l = LabyMod.getInstance().draw.getStringWidth(this.error) / 2 + 20;
      LabyMod.getInstance();LabyMod.a(this.l / 2 - l, 2, this.l / 2 + l, 16, Integer.MIN_VALUE);
      if (this.error.contains("Cape uploaded")) {
        LabyMod.getInstance().draw.drawCenteredString(Color.cl("a") + this.error, this.l / 2, 5);
      } else {
        LabyMod.getInstance().draw.drawCenteredString(Color.cl("4") + "Error: " + Color.cl("c") + this.error, this.l / 2, 5);
      }
    }
    boolean z = this.a.l;
    this.a.l = (cdPriority + 2000L < System.currentTimeMillis());
    if ((this.a.l != z) && (this.a.l)) {
      refreshButtons();
    }
    if (!this.a.l) {
      this.a.j = ("Switch.. " + getLoading());
    }
    boolean t = this.h.l;
    this.h.l = (cdRefresh + 10000L < System.currentTimeMillis());
    if ((this.h.l != t) && (this.h.l)) {
      refreshButtons();
    }
    if (!this.h.l) {
      this.h.j = ("Refresh.. " + getLoading());
    }
    this.c.l = ((!CapeUploader.openUpload) && (!CapeUploader.upload));
    if (CapeUploader.upload) {
      this.c.j = ("Upload.. " + getLoading());
    } else {
      this.c.j = "Upload..";
    }
    if (CapeMover.moving) {
      this.f.j = ("Moving.. " + getLoading());
    }
    if (this.jdField_j_of_type_Bcf.h == null)
    {
      DrawUtils.a(this.l / 2 - 20, this.m / 2 - 30, this.l / 2 + 100, this.m / 2 + 78, 1129010000);
      LabyMod.getInstance().draw.drawCenteredString("Preview not available.", this.l / 2 + 40, this.m / 2 + 17);
      LabyMod.getInstance().draw.drawCenteredString(Color.cl("c") + "You are not ingame!", this.l / 2 + 40, this.m / 2 + 27);
    }
    this.d.l = ((!this.moveCape) && (!CapeMover.moving));
    this.f.l = ((!this.e.b().isEmpty()) && (!CapeMover.moving));
    this.f.m = this.moveCape;
    
    boolean old = this.hasCape;
    checkCape();
    if (old != this.hasCape) {
      this.b.j = getStatus("Cape", zk.a);
    }
    if (!this.hasCape)
    {
      this.b.l = false;
      this.c.l = false;
      this.d.l = false;
      this.moveCape = false;
    }
    else if ((this.capeType != null) && (this.capeType != EnumCapePriority.LABYMOD))
    {
      this.c.l = false;
      this.d.l = false;
      this.moveCape = false;
    }
    else
    {
      this.c.l = true;
      this.d.l = true;
    }
    this.g.m = (!this.hasCape);
    
    boolean info = (this.e.b().isEmpty()) && (!this.e.m());
    if (this.moveCape)
    {
      this.e.g();
      if (info)
      {
        LabyMod.getInstance().draw.drawString(Color.cl("7") + "Enter the name of the new owner..", this.e.a + 5, this.e.f + 6);
      }
      else
      {
        for (int i = 0; i <= 2; i++)
        {
          LabyMod.getInstance();LabyMod.a(this.l / 2 - 120, this.m / 4 + 20, this.l / 2 + 120, this.m / 2 + 85, Integer.MIN_VALUE);
        }
        int i = 25;
        LabyMod.getInstance().draw.drawCenteredString(Color.cl("4") + "Caution!", this.l / 2, this.m / 4 + i);i += 10;
        LabyMod.getInstance().draw.drawCenteredString(Color.cl("c") + "Yow won't be the owner of your cape", this.l / 2, this.m / 4 + i);i += 10;
        LabyMod.getInstance().draw.drawCenteredString(Color.cl("c") + "after moving it to another account.", this.l / 2, this.m / 4 + i);i += 20;
        if (this.e.b().isEmpty())
        {
          LabyMod.getInstance().draw.drawString("-> The new owner of the cape will", this.l / 2 - 110, this.m / 4 + i);i += 10;
          LabyMod.getInstance().draw.drawString("then be changed", this.l / 2 - 95, this.m / 4 + i);i += 20;
          LabyMod.getInstance().draw.drawString("-> That cannot be undone unless ", this.l / 2 - 110, this.m / 4 + i);i += 10;
          LabyMod.getInstance().draw.drawString("the new owner moves their", this.l / 2 - 95, this.m / 4 + i);i += 10;
          LabyMod.getInstance().draw.drawString("cape back to your account.", this.l / 2 - 95, this.m / 4 + i);i += 10;
        }
        else
        {
          LabyMod.getInstance().draw.drawString("-> The new owner of the cape will", this.l / 2 - 110, this.m / 4 + i);i += 10;
          LabyMod.getInstance().draw.drawString("then be " + Color.cl("e") + this.e.b(), this.l / 2 - 95, this.m / 4 + i);i += 20;
          LabyMod.getInstance().draw.drawString("-> That cannot be undone unless ", this.l / 2 - 110, this.m / 4 + i);i += 10;
          LabyMod.getInstance().draw.drawString("" + Color.cl("e") + this.e.b() + Color.cl("f") + " moves their", this.l / 2 - 95, this.m / 4 + i);i += 10;
          LabyMod.getInstance().draw.drawString("cape back to your account.", this.l / 2 - 95, this.m / 4 + i);i += 10;
        }
      }
    }
    this.a.m = info;
    this.b.m = info;
    this.c.m = info;
    this.d.m = info;
    if (info)
    {
      LabyMod.getInstance().draw.drawString("Priority:", this.l / 2 - 110, this.m / 2 - 40 - 20);
      LabyMod.getInstance().draw.drawString("My cape:", this.l / 2 - 110, this.m / 2 + 0 - 20);
      if (this.jdField_j_of_type_Bcf.h != null) {
        DrawUtils.drawEntityOnScreen(this.l / 2 + 40, this.m / 2 + 80, 30, (this.l / 2 + 35 - mouseX) * -1.0F, this.m / 2 - 20 - mouseY, 180, this.jdField_j_of_type_Bcf.h);
      }
    }
    if (LabyMod.getInstance().getCosmeticManager().colorPicker) {
      if (this.m < 280)
      {
        LabyMod.getInstance().draw.drawCenteredString(Color.cl("c") + "Your screen scale is to small to display the color picker", this.l / 2, 40);
      }
      else
      {
        int colorPickerPositionY = 40;
        a(this.l / 2 - 100, colorPickerPositionY, this.l / 2 + 100, colorPickerPositionY + 10, Integer.MIN_VALUE);
        float percent = LabyMod.getInstance().getCosmeticManager().colorR / 255.0F * 200.0F;
        a((int)percent + this.l / 2 - 101, colorPickerPositionY - 3, (int)percent + 2 + this.l / 2 - 99, colorPickerPositionY + 10 + 3, Integer.MAX_VALUE);
        
        a((int)percent + this.l / 2 - 100, colorPickerPositionY - 2, (int)percent + 2 + this.l / 2 - 100, colorPickerPositionY + 10 + 2, 
          Color.toRGB(LabyMod.getInstance().getCosmeticManager().colorR, 0, 0, 200));
        LabyMod.getInstance().draw.drawCenteredString(Color.cl("c") + LabyMod.getInstance().getCosmeticManager().colorR + "", this.l / 2, colorPickerPositionY + 1);
        
        colorPickerPositionY = 60;
        a(this.l / 2 - 100, colorPickerPositionY, this.l / 2 + 100, colorPickerPositionY + 10, Integer.MIN_VALUE);
        percent = LabyMod.getInstance().getCosmeticManager().colorG / 255.0F * 200.0F;
        a((int)percent + this.l / 2 - 101, colorPickerPositionY - 3, (int)percent + 2 + this.l / 2 - 99, colorPickerPositionY + 10 + 3, Integer.MAX_VALUE);
        
        a((int)percent + this.l / 2 - 100, colorPickerPositionY - 2, (int)percent + 2 + this.l / 2 - 100, colorPickerPositionY + 10 + 2, 
          Color.toRGB(0, LabyMod.getInstance().getCosmeticManager().colorG, 0, 200));
        LabyMod.getInstance().draw.drawCenteredString(Color.cl("2") + LabyMod.getInstance().getCosmeticManager().colorG + "", this.l / 2, colorPickerPositionY + 1);
        
        colorPickerPositionY = 80;
        a(this.l / 2 - 100, colorPickerPositionY, this.l / 2 + 100, colorPickerPositionY + 10, Integer.MIN_VALUE);
        percent = LabyMod.getInstance().getCosmeticManager().colorB / 255.0F * 200.0F;
        a((int)percent + this.l / 2 - 101, colorPickerPositionY - 3, (int)percent + 2 + this.l / 2 - 99, colorPickerPositionY + 10 + 3, Integer.MAX_VALUE);
        
        a((int)percent + this.l / 2 - 100, colorPickerPositionY - 2, (int)percent + 2 + this.l / 2 - 100, colorPickerPositionY + 10 + 2, 
          Color.toRGB(0, 0, LabyMod.getInstance().getCosmeticManager().colorB, 200));
        LabyMod.getInstance().draw.drawCenteredString(Color.cl("9") + LabyMod.getInstance().getCosmeticManager().colorB + "", this.l / 2, colorPickerPositionY + 1);
      }
    }
    super.a(mouseX, mouseY, partialTicks);
    LabyMod.getInstance().overlay(mouseX, mouseY);
  }
  
  protected void a(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick)
  {
    int colorPickerPositionY = 40;
    if ((mouseX > this.l / 2 - 100) && (mouseX < this.l / 2 + 100) && 
      (mouseY > colorPickerPositionY) && (mouseY < colorPickerPositionY + 10)) {
      LabyMod.getInstance().getCosmeticManager().colorR = ((int)((mouseX - (this.l / 2 - 100)) * 1.285D));
    }
    colorPickerPositionY = 60;
    if ((mouseX > this.l / 2 - 100) && (mouseX < this.l / 2 + 100) && 
      (mouseY > colorPickerPositionY) && (mouseY < colorPickerPositionY + 10)) {
      LabyMod.getInstance().getCosmeticManager().colorG = ((int)((mouseX - (this.l / 2 - 100)) * 1.285D));
    }
    colorPickerPositionY = 80;
    if ((mouseX > this.l / 2 - 100) && (mouseX < this.l / 2 + 100) && 
      (mouseY > colorPickerPositionY) && (mouseY < colorPickerPositionY + 10)) {
      LabyMod.getInstance().getCosmeticManager().colorB = ((int)((mouseX - (this.l / 2 - 100)) * 1.285D));
    }
    super.a(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
  }
  
  private String getLoading()
  {
    String m = "";
    int i = (int)(System.currentTimeMillis() / 100L % 3L);
    switch (i)
    {
    case 0: 
      m = "\\";
      break;
    case 1: 
      m = "-";
      break;
    case 2: 
      m = "/";
      break;
    case 3: 
      m = "|";
    }
    return m;
  }
}
