package mods.togglesneak;

import aas;
import bcc;
import bcf;
import bch;
import bms;
import bmt;
import java.text.DecimalFormat;
import zh;

public class CustomMovementInput
{
  public boolean isDisabled;
  public boolean canDoubleTap;
  public boolean sprint = false;
  public boolean sprintHeldAndReleased = false;
  public boolean sprintDoubleTapped = false;
  private long lastPressed;
  private long lastSprintPressed;
  private boolean handledSneakPress;
  private boolean handledSprintPress;
  private boolean wasRiding;
  
  public void update(bcf mc, bms options, bmt thisPlayer)
  {
    options.a = 0.0F;
    options.b = 0.0F;
    
    bch settings = mc.u;
    if (settings.P.e()) {
      options.b += 1.0F;
    }
    if (settings.R.e()) {
      options.b -= 1.0F;
    }
    if (settings.Q.e()) {
      options.a += 1.0F;
    }
    if (settings.S.e()) {
      options.a -= 1.0F;
    }
    options.g = settings.T.e();
    if (ToggleSneakMod.optionToggleSneak)
    {
      if ((settings.U.e()) && (!this.handledSneakPress))
      {
        if ((thisPlayer.aI()) || (thisPlayer.bJ.b))
        {
          options.h = true;
          this.wasRiding = thisPlayer.aI();
        }
        else
        {
          options.h = (!options.h);
        }
        this.lastPressed = System.currentTimeMillis();
        this.handledSneakPress = true;
      }
      if ((!settings.U.e()) && (this.handledSneakPress))
      {
        if ((thisPlayer.bJ.b) || (this.wasRiding))
        {
          options.h = false;
          this.wasRiding = false;
        }
        else if (System.currentTimeMillis() - this.lastPressed > 300L)
        {
          options.h = false;
        }
        this.handledSneakPress = false;
      }
    }
    else
    {
      options.h = settings.U.e();
    }
    if ((options.h) && (mc.m != null)) {
      options.h = false;
    }
    if (options.h)
    {
      options.a = ((float)(options.a * 0.3D));
      options.b = ((float)(options.b * 0.3D));
    }
    boolean enoughHunger = (thisPlayer.cS().a() > 6.0F) || (thisPlayer.bJ.b);
    boolean canSprint = (!options.h) && (!thisPlayer.aI()) && (!thisPlayer.bJ.b) && (enoughHunger);
    
    this.isDisabled = (!ToggleSneakMod.optionToggleSprint);
    this.canDoubleTap = ToggleSneakMod.optionDoubleTap;
    if (((canSprint) || (this.isDisabled)) && (settings.V.e()) && (!this.handledSprintPress)) {
      if (!this.isDisabled)
      {
        this.sprint = (!this.sprint);
        this.lastSprintPressed = System.currentTimeMillis();
        this.handledSprintPress = true;
        this.sprintHeldAndReleased = false;
      }
    }
    if (((canSprint) || (this.isDisabled)) && (!settings.V.e()) && (this.handledSprintPress))
    {
      if (System.currentTimeMillis() - this.lastSprintPressed > 300L) {
        this.sprintHeldAndReleased = true;
      }
      this.handledSprintPress = false;
    }
    UpdateStatus(options, thisPlayer, settings);
  }
  
  public void UpdateSprint(boolean newValue, boolean doubleTapped)
  {
    this.sprint = newValue;
    this.sprintDoubleTapped = doubleTapped;
  }
  
  private void UpdateStatus(bms options, bmt thisPlayer, bch settings)
  {
    if (ToggleSneakMod.optionShowHUDText)
    {
      String output = "";
      
      boolean isFlying = thisPlayer.bJ.b;
      boolean isRiding = thisPlayer.aI();
      boolean isHoldingSneak = settings.U.e();
      boolean isHoldingSprint = settings.V.e();
      if (isFlying)
      {
        DecimalFormat numFormat = new DecimalFormat("#.00");
        if ((ToggleSneakMod.optionEnableFlyBoost) && (isHoldingSprint)) {
          output = output + "[Flying (" + numFormat.format(ToggleSneakMod.optionFlyBoostAmount) + "x boost)]  ";
        } else {
          output = output + "[Flying]  ";
        }
      }
      if (isRiding) {
        output = output + "[Riding]  ";
      }
      if (options.h)
      {
        if (isFlying) {
          output = output + "[Descending]  ";
        } else if (isRiding) {
          output = output + "[Dismounting]  ";
        } else if (isHoldingSneak) {
          output = output + "[Sneaking (Key Held)]  ";
        } else {
          output = output + "[Sneaking (Toggled)]  ";
        }
      }
      else if (this.sprint) {
        if ((!isFlying) && (!isRiding))
        {
          boolean isVanilla = (this.sprintHeldAndReleased) || (this.isDisabled) || (this.sprintDoubleTapped);
          if (isHoldingSprint) {
            output = output + "[Sprinting (Key Held)]";
          } else if (isVanilla) {
            output = output + "[Sprinting (Vanilla)]";
          } else {
            output = output + "[Sprinting (Toggled)]";
          }
        }
      }
      ToggleSneakModEvents.SetHUDText(output);
    }
  }
}
