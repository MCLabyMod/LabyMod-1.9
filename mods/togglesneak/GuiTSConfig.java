package mods.togglesneak;

import bcf;
import bct;
import bcz;
import bfb;
import de.labystudio.modapi.ModAPI;
import java.util.List;

public class GuiTSConfig
  extends bfb
{
  private bcz btnToggleSneak;
  private bcz btnToggleSprint;
  private bcz btnShowHUDText;
  private bcz btnDoubleTap;
  private bcz btnFlyBoost;
  private bcz btnSaveSettings;
  private bcz btnPositionMode;
  private GuiSlideControl sliderHUDTextPosX;
  private GuiSlideControl sliderHUDTextPosY;
  private GuiSlideControl sliderFlyBoostAmount;
  private bcz lastPressed;
  private boolean changedShowHUD;
  private boolean changedToggleSprint;
  private int headerPos;
  private int footerPos;
  private byte byte0 = -16;
  
  public void b()
  {
    this.n.clear();
    
    this.headerPos = (this.m / 4 - 52);
    this.footerPos = (this.m - 29);
    
    this.btnToggleSneak = new bcz(1, this.l / 2 - 98, getRowPos(1), 60, 20, String.valueOf(ToggleSneakMod.optionToggleSneak));
    this.btnToggleSprint = new bcz(2, this.l / 2 + 102, getRowPos(1), 60, 20, String.valueOf(ToggleSneakMod.optionToggleSprint));
    this.btnShowHUDText = new bcz(3, this.l / 2 + 2, getRowPos(2), 60, 20, String.valueOf(ToggleSneakMod.optionShowHUDText));
    this.btnPositionMode = new bcz(133, this.l / 2 + 2, getRowPos(3), 60, 20, ToggleSneakMod.optionPositionMode);
    
    this.sliderHUDTextPosX = new GuiSlideControl(50, this.l / 2 + 2, getRowPos(4), 150, 20, "X Pos: ", 1.0F, this.l - 25, ToggleSneakMod.optionHUDTextPosX, true);
    this.sliderHUDTextPosY = new GuiSlideControl(60, this.l / 2 + 2, getRowPos(5), 150, 20, "Y Pos: ", 1.0F, this.m - 8, ToggleSneakMod.optionHUDTextPosY, true);
    
    this.btnDoubleTap = new bcz(4, this.l / 2 + 2, getRowPos(6), 60, 20, String.valueOf(ToggleSneakMod.optionDoubleTap));
    this.btnFlyBoost = new bcz(5, this.l / 2 - 113, getRowPos(7), 60, 20, String.valueOf(ToggleSneakMod.optionEnableFlyBoost));
    
    this.sliderFlyBoostAmount = new GuiSlideControl(70, this.l / 2 + 57, getRowPos(7), 150, 20, "x", 0.0F, 10.0F, (float)ToggleSneakMod.optionFlyBoostAmount, false);
    
    this.btnSaveSettings = new bcz(100, this.l / 2 - 30, this.footerPos, 60, 20, "Done");
    
    this.n.add(this.btnToggleSneak);
    this.n.add(this.btnToggleSprint);
    this.n.add(this.btnShowHUDText);
    this.n.add(this.btnPositionMode);
    if (ToggleSneakMod.optionPositionMode.equals("CUSTOM"))
    {
      this.n.add(this.sliderHUDTextPosX);
      this.n.add(this.sliderHUDTextPosY);
    }
    this.n.add(this.btnDoubleTap);
    this.n.add(this.btnFlyBoost);
    this.n.add(this.sliderFlyBoostAmount);
    
    this.n.add(this.btnSaveSettings);
  }
  
  public int getRowPos(int rowNumber)
  {
    return rowNumber > 3 ? this.m / 4 + 0 + (24 * (rowNumber - (ToggleSneakMod.optionPositionMode.equals("CUSTOM") ? 0 : 2)) - 24) + this.byte0 : this.m / 4 + 0 + (24 * rowNumber - 24) + this.byte0;
  }
  
  protected void a(int mouseX, int mouseY, int mouseButton)
  {
    if (mouseButton == 0) {
      for (int l = 0; l < this.n.size(); l++)
      {
        bcz guibutton = (bcz)this.n.get(l);
        if (guibutton.c(this.j, mouseX, mouseY))
        {
          this.lastPressed = guibutton;
          a(guibutton);
        }
      }
    }
  }
  
  protected void b(int mouseX, int mouseY, int which)
  {
    if ((this.lastPressed != null) && (which == 0))
    {
      this.lastPressed.a(mouseX, mouseY);
      
      actionPerformed_MouseUp(this.lastPressed);
      
      this.lastPressed = null;
    }
  }
  
  protected void actionPerformed_MouseUp(bcz button)
  {
    if ((button instanceof GuiSlideControl)) {
      a(button);
    }
  }
  
  protected void a(bcz button)
  {
    switch (button.k)
    {
    case 1: 
      ToggleSneakMod.optionToggleSneak = !ToggleSneakMod.optionToggleSneak;
      this.btnToggleSneak.j = String.valueOf(ToggleSneakMod.optionToggleSneak);
      break;
    case 2: 
      ToggleSneakMod.optionToggleSprint = !ToggleSneakMod.optionToggleSprint;
      this.btnToggleSprint.j = String.valueOf(ToggleSneakMod.optionToggleSprint);
      this.changedToggleSprint = true;
      break;
    case 3: 
      ToggleSneakMod.optionShowHUDText = !ToggleSneakMod.optionShowHUDText;
      this.btnShowHUDText.j = String.valueOf(ToggleSneakMod.optionShowHUDText);
      this.changedShowHUD = true;
      break;
    case 133: 
      String nextMode = PositionMode.getNext(ToggleSneakMod.optionPositionMode).name();
      ToggleSneakMod.optionPositionMode = nextMode;
      this.btnPositionMode.j = nextMode;
      b();
      break;
    case 4: 
      ToggleSneakMod.optionDoubleTap = !ToggleSneakMod.optionDoubleTap;
      this.btnDoubleTap.j = String.valueOf(ToggleSneakMod.optionDoubleTap);
      break;
    case 5: 
      ToggleSneakMod.optionEnableFlyBoost = !ToggleSneakMod.optionEnableFlyBoost;
      this.btnFlyBoost.j = String.valueOf(ToggleSneakMod.optionEnableFlyBoost);
      break;
    case 50: 
      ToggleSneakMod.optionHUDTextPosX = this.sliderHUDTextPosX.GetValueAsInt();
      break;
    case 60: 
      ToggleSneakMod.optionHUDTextPosY = this.sliderHUDTextPosY.GetValueAsInt();
      break;
    case 70: 
      ToggleSneakMod.optionFlyBoostAmount = this.sliderFlyBoostAmount.GetValueAsFloat();
      break;
    case 100: 
      this.j.a(ModAPI.getLastScreen());
    }
    saveAll();
  }
  
  private void saveAll()
  {
    if (this.changedShowHUD) {
      ToggleSneakModEvents.SetHUDText("");
    }
    if ((this.changedToggleSprint) && (this.j.f != null)) {
      ToggleSneakMod.wasSprintDisabled = true;
    }
    ToggleSneakMod.saveConfig();
    
    this.changedShowHUD = false;
    this.changedToggleSprint = false;
  }
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    String lblToggleSneak = "Enable ToggleSneak";
    String lblToggleSprint = "Enable ToggleSprint";
    String lblShowHUDText = "Show status on HUD";
    String lblHUDTextPosX = "Horizontal HUD Location";
    String lblHUDTextPosY = "Vertical HUD Location";
    String lblDoubleTap = "Enable Double-Tapping";
    String lblFlyBoost = "Enable Fly Boost";
    String lblFlyBoostAmount = "Fly Boost Multiplier";
    String lblPositionMode = "HUD Position-Mode";
    
    c();
    
    a(this.q, "ToggleSneak Settings", this.l / 2, this.headerPos, 16777215);
    
    c(this.q, lblToggleSneak, this.l / 2 - 100 - this.q.a(lblToggleSneak), getRowPos(1) + 6, 16777215);
    c(this.q, lblToggleSprint, this.l / 2 + 100 - this.q.a(lblToggleSprint), getRowPos(1) + 6, 16777215);
    c(this.q, lblShowHUDText, this.l / 2 - 3 - this.q.a(lblShowHUDText), getRowPos(2) + 6, 16777215);
    c(this.q, lblPositionMode, this.l / 2 - 3 - this.q.a(lblPositionMode), getRowPos(3) + 6, 16777215);
    if (ToggleSneakMod.optionPositionMode.equals("CUSTOM"))
    {
      c(this.q, lblHUDTextPosX, this.l / 2 - 3 - this.q.a(lblHUDTextPosX), getRowPos(4) + 6, 16777215);
      c(this.q, lblHUDTextPosY, this.l / 2 - 3 - this.q.a(lblHUDTextPosY), getRowPos(5) + 6, 16777215);
    }
    c(this.q, lblDoubleTap, this.l / 2 - 3 - this.q.a(lblDoubleTap), getRowPos(6) + 6, 16777215);
    c(this.q, lblFlyBoost, this.l / 2 - 115 - this.q.a(lblFlyBoost), getRowPos(7) + 6, 16777215);
    c(this.q, lblFlyBoostAmount, this.l / 2 + 50 - this.q.a(lblFlyBoostAmount), getRowPos(7) + 6, 16777215);
    
    super.a(mouseX, mouseY, partialTicks);
  }
}
