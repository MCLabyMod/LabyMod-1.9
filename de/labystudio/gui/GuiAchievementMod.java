package de.labystudio.gui;

import bcf;
import bct;
import bcv;
import bcx;
import bni;
import bvi;
import de.labystudio.chat.EnumAlertType;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.ModSettings;
import de.labystudio.labymod.Timings;
import de.labystudio.utils.DrawUtils;
import de.labystudio.utils.ModGui;
import de.labystudio.utils.TextureManager;
import java.util.ArrayList;
import kk;

public class GuiAchievementMod
  extends bcv
{
  private static final kk achievementBg = new kk("textures/gui/achievement/achievement_background.png");
  private bcf mc;
  private int width;
  private int height;
  private boolean large = false;
  private static final String __OBFID = "CL_00000721";
  ArrayList<String> title = new ArrayList();
  ArrayList<String> message = new ArrayList();
  public ArrayList<Long> time = new ArrayList();
  public ArrayList<Boolean> out = new ArrayList();
  public ArrayList<Integer> type = new ArrayList();
  
  public GuiAchievementMod(bcf mc)
  {
    this.mc = mc;
  }
  
  public void displayBroadcast(String title, String message, EnumAlertType type)
  {
    if ((type == EnumAlertType.CHAT) && (!ConfigManager.settings.alertsChat)) {
      return;
    }
    if ((type == EnumAlertType.TEAMSPEAK) && (!ConfigManager.settings.alertsTeamSpeak)) {
      return;
    }
    this.title.add(title);
    this.message.add(message);
    this.time.add(Long.valueOf(bcf.I()));
    this.out.add(Boolean.valueOf(false));
    this.type.add(Integer.valueOf(0));
    single();
  }
  
  boolean single = true;
  
  private void single()
  {
    this.single = true;
  }
  
  public void displayMessage(String player, String message, EnumAlertType type)
  {
    if ((type == EnumAlertType.CHAT) && (!ConfigManager.settings.alertsChat)) {
      return;
    }
    if ((type == EnumAlertType.TEAMSPEAK) && (!ConfigManager.settings.alertsTeamSpeak)) {
      return;
    }
    if (this.title.contains(player))
    {
      int i = this.title.indexOf(player);
      this.message.set(i, message);
      this.out.set(i, Boolean.valueOf(false));
      this.time.set(i, Long.valueOf(bcf.I() - 150L));
      return;
    }
    this.title.add(player);
    this.message.add(message);
    this.time.add(Long.valueOf(bcf.I()));
    this.out.add(Boolean.valueOf(false));
    this.type.add(Integer.valueOf(1));
    single();
  }
  
  private void updateAchievementWindowScale()
  {
    bni.b(0, 0, this.mc.d, this.mc.e);
    bni.n(5889);
    bni.F();
    bni.n(5888);
    bni.F();
    this.width = this.mc.d;
    this.height = this.mc.e;
    bcx var1 = new bcx(this.mc);
    this.width = var1.a();
    this.height = var1.b();
    bni.m(256);
    bni.n(5889);
    bni.F();
    bni.a(0.0D, this.width, this.height, 0.0D, 1000.0D, 3000.0D);
    bni.n(5888);
    bni.F();
    bni.c(0.0F, 0.0F, -2000.0F);
    if (this.time.size() >= 3) {
      this.single = false;
    }
  }
  
  public void updateAchievementWindow()
  {
    int y = 0;
    Timings.start("UpdateAchievementWindow");
    for (int stack = this.time.size() - 1; stack > 0; stack--)
    {
      String title = (String)this.title.get(stack);
      String message = (String)this.message.get(stack);
      long time = ((Long)this.time.get(stack)).longValue();
      message = ModGui.shortString(message, LabyMod.getInstance().draw.getWidth() / 8);
      double var1 = (bcf.I() - time) / 3000.0D;
      
      double backup = var1;
      if ((var1 < 0.0D) || (var1 > 1.0D)) {
        this.out.set(stack, Boolean.valueOf(true));
      }
      if (var1 > 0.5D) {
        var1 = 0.5D;
      }
      updateAchievementWindowScale();
      double var3 = var1 * 2.0D;
      if (var3 > 1.0D) {
        var3 = 2.0D - var3;
      }
      var3 *= 4.0D;
      var3 = 1.0D - var3;
      if (var3 < 0.0D) {
        var3 = 0.0D;
      }
      var3 *= var3;
      var3 *= var3;
      int var5 = this.width - 160;
      int var6 = 0 - (int)(var3 * 36.0D);
      int x = 0;
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      if (((Boolean)this.out.get(stack)).booleanValue())
      {
        int var62 = (int)(var6 - backup * 300.0D + 300.0D);
        if (var62 + 72 < 0.0D)
        {
          this.title.remove(stack);
          this.message.remove(stack);
          this.time.remove(stack);
          this.out.remove(stack);
          this.type.remove(stack);
          continue;
        }
        if (!this.single) {
          x = var62 * 4 * -1;
        } else {
          var6 = var62;
        }
      }
      var6 += y;
      y = var6 + 32;
      int type = ((Integer)this.type.get(stack)).intValue();
      bni.y();
      this.mc.N().a(achievementBg);
      bni.g();
      draw(title, message, var6, type, x);
    }
    Timings.stop("UpdateAchievementWindow");
  }
  
  private void draw(String title, String message, int y, int type, int xx)
  {
    int length = LabyMod.getInstance().draw.getStringWidth(message);
    if (length < 160) {
      length = 160;
    }
    if (length > 160) {
      length += 10;
    }
    if (type == 1) {
      length += 26;
    }
    length += 10;
    int x = LabyMod.getInstance().draw.getWidth() - length + xx;
    if (length > 160)
    {
      for (int i = length; i >= 0; i--) {
        if (i <= length - 10) {
          b(x + i, y, 96, 202, 7, 32);
        }
      }
      b(x + length - 10, y, 246, 202, 10, 32);
    }
    else
    {
      b(x, y, 96, 202, 160, 32);
    }
    if (type == 0)
    {
      this.mc.k.a(title, x + 6, y + 7, 65280);
      this.mc.k.a(message, x + 6, y + 18, -1);
    }
    if (type == 1)
    {
      this.mc.k.a(title, x + 6 + 24, y + 7, 65280);
      this.mc.k.a(message, x + 6 + 24, y + 18, -1);
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      LabyMod.getInstance().textureManager.drawPlayerHead(title, x + 5, y + 8, 0.7D);
    }
  }
}
