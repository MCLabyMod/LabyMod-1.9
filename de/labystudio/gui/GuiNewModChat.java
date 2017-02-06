package de.labystudio.gui;

import bcb;
import bcf;
import bch;
import bct;
import bcu;
import bcx;
import bdb;
import bee;
import bni;
import com.google.common.collect.Lists;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.ModSettings;
import de.labystudio.listener.ChatListener;
import de.labystudio.utils.DrawUtils;
import eu;
import fa;
import java.util.Iterator;
import java.util.List;
import on;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import zj.b;

public class GuiNewModChat
{
  public static final Logger logger = ;
  public static DrawUtils draw;
  public static final List sentMessages = Lists.newArrayList();
  public static final List chatLines = Lists.newArrayList();
  public static final List field_146253_i = Lists.newArrayList();
  public static int scrollPos;
  public static boolean isScrolled;
  public static final String __OBFID = "CL_00000669";
  
  public static void drawChat(int p_146230_1_)
  {
    if (bcf.z().u.m != zj.b.c)
    {
      if (!getChatOpen()) {
        resetScroll();
      }
      if ((ConfigManager.settings.extraChat.booleanValue()) || (ConfigManager.settings.chatFilter.booleanValue()) || (ConfigManager.settings.chatPositionRight))
      {
        int var2 = getLineCount();
        boolean var3 = false;
        int var4 = 0;
        int var5 = field_146253_i.size();
        float var6 = bcf.z().u.q * 0.9F + 0.1F;
        if (var5 > 0)
        {
          if (getChatOpen()) {
            var3 = true;
          }
          float var7 = getChatScale();
          int var8 = on.f(getModChatWidth() / var7 + 2.0F);
          bni.G();
          bni.c(2.0F, 20.0F, 0.0F);
          bni.b(var7, var7, 1.0F);
          
          int sort = 0;
          for (int var9 = 0; (var9 + scrollPos < field_146253_i.size()) && (var9 < var2); var9++)
          {
            bcb var10 = (bcb)field_146253_i.get(var9 + scrollPos);
            if (var10 != null)
            {
              int var11 = p_146230_1_ - var10.b();
              if ((var11 < 200) || (var3))
              {
                double var12 = var11 / 200.0D;
                var12 = 1.0D - var12;
                var12 *= 10.0D;
                var12 = on.a(var12, 0.0D, 1.0D);
                var12 *= var12;
                int var14 = (int)(255.0D * var12);
                if (var3) {
                  var14 = 255;
                }
                var14 = (int)(var14 * var6);
                var4++;
                if (var14 > 3)
                {
                  byte var15 = 0;
                  int var16 = -sort * 9 - 30;
                  sort++;
                  String var17 = var10.a().d();
                  var17 = ChatListener.replaceMessage(var10.a().d(), var10.a().c());
                  var8 = on.f(getChatWidth() / var7 + 2.0F);
                  DrawUtils.a(var8 + 5, var16 - 9, 3000, var16, var14 / 2 << 24);
                  bni.m();
                  bcf.z().k.a(var17, var8 + 8, var16 - 8, 16777215 + (var14 << 24));
                  bni.d();
                  bni.l();
                }
              }
            }
          }
          if (var3)
          {
            var9 = bcf.z().k.a;
            bni.c(-3.0F, 0.0F, 0.0F);
            int var18 = var5 * var9 + var5;
            int var11 = var4 * var9 + var4;
            int var19 = scrollPos * var11 / var5 + 30;
            int var13 = var11 * var11 / var18;
            if (var18 != var11)
            {
              int var14 = var19 > 0 ? 170 : 96;
              int var20 = isScrolled ? 13382451 : 3355562;
              DrawUtils.a(on.f(getChatWidth() / var7 + 9.0F), -var19, on.f(getChatWidth() / var7 + 10.0F), -var19 - var13, var20 + (var14 << 24));
              DrawUtils.a(on.f(getChatWidth() / var7 + 10.0F), -var19, on.f(getChatWidth() / var7 + 9.0F), -var19 - var13, 13421772 + (var14 << 24));
            }
          }
          bni.H();
        }
      }
    }
  }
  
  public static void clearChatMessages()
  {
    field_146253_i.clear();
    chatLines.clear();
    sentMessages.clear();
  }
  
  public static void printChatMessage(eu p_146227_1_)
  {
    printChatMessageWithOptionalDeletion(p_146227_1_, 0);
  }
  
  public static void printChatMessageWithOptionalDeletion(eu p_146234_1_, int p_146234_2_)
  {
    if (ChatListener.allowedToPrint(p_146234_1_.d(), p_146234_1_.c()))
    {
      setChatLine(p_146234_1_, p_146234_2_, bcf.z().r.e(), false);
      logger.info("[CHAT] " + p_146234_1_.c());
    }
  }
  
  public static void setChatLine(eu p_146237_1_, int p_146237_2_, int p_146237_3_, boolean p_146237_4_)
  {
    if (p_146237_2_ != 0) {
      deleteChatLine(p_146237_2_);
    }
    int var5 = on.d(getModChatWidth() / getChatScale());
    List var6 = bdb.a(p_146237_1_, var5, bcf.z().k, false, false);
    boolean var7 = getChatOpen();
    eu var9;
    for (Iterator var8 = var6.iterator(); var8.hasNext(); field_146253_i.add(0, new bcb(p_146237_3_, var9, p_146237_2_)))
    {
      var9 = (eu)var8.next();
      if ((var7) && (scrollPos > 0))
      {
        isScrolled = true;
        scroll(1);
      }
    }
    while (field_146253_i.size() > 100) {
      field_146253_i.remove(field_146253_i.size() - 1);
    }
    if (!p_146237_4_)
    {
      chatLines.add(0, new bcb(p_146237_3_, p_146237_1_, p_146237_2_));
      while (chatLines.size() > 100) {
        chatLines.remove(chatLines.size() - 1);
      }
    }
  }
  
  public static void refreshChat()
  {
    ChatListener.init = bcf.I();
    field_146253_i.clear();
    resetScroll();
    for (int var1 = chatLines.size() - 1; var1 >= 0; var1--)
    {
      bcb var2 = (bcb)chatLines.get(var1);
      setChatLine(var2.a(), var2.c(), var2.b(), true);
    }
  }
  
  public List getSentMessages()
  {
    return sentMessages;
  }
  
  public void addToSentMessages(String p_146239_1_)
  {
    if ((sentMessages.isEmpty()) || (!((String)sentMessages.get(sentMessages.size() - 1)).equals(p_146239_1_))) {
      sentMessages.add(p_146239_1_);
    }
  }
  
  public static void resetScroll()
  {
    scrollPos = 0;
    isScrolled = false;
  }
  
  public static void scroll(int p_146229_1_)
  {
    if (DrawUtils.getMouseX() < getChatWidth()) {
      return;
    }
    scrollPos += p_146229_1_;
    int var2 = field_146253_i.size();
    if (scrollPos > var2 - getLineCount()) {
      scrollPos = var2 - getLineCount();
    }
    if (scrollPos <= 0)
    {
      scrollPos = 0;
      isScrolled = false;
    }
  }
  
  public static eu getChatComponent(int p_146236_1_, int p_146236_2_)
  {
    if (!getChatOpen()) {
      return null;
    }
    bcx var3 = new bcx(bcf.z());
    int var4 = var3.e();
    float var5 = getChatScale();
    int var6 = p_146236_1_ / var4 - 3;
    int var7 = p_146236_2_ / var4 - 27;
    var6 = on.d(var6 / var5);
    var7 = on.d(var7 / var5);
    
    var6 -= on.d(getChatWidth() / getChatScale()) + 10;
    var7 -= 30;
    if ((var6 >= 0) && (var7 >= 0))
    {
      int var8 = Math.min(getLineCount(), field_146253_i.size());
      if ((var6 <= on.d(getChatWidth() / getChatScale())) && (var7 < bcf.z().k.a * var8 + var8))
      {
        int var9 = var7 / bcf.z().k.a + scrollPos;
        if ((var9 >= 0) && (var9 < field_146253_i.size()))
        {
          bcb var10 = (bcb)field_146253_i.get(var9);
          int var11 = 0;
          Iterator var12 = var10.a().iterator();
          while (var12.hasNext())
          {
            eu var13 = (eu)var12.next();
            if ((var13 instanceof fa))
            {
              var11 += bcf.z().k.a(bdb.a(((fa)var13).g(), false));
              if (var11 > var6) {
                return var13;
              }
            }
          }
        }
        return null;
      }
      return null;
    }
    return null;
  }
  
  public static boolean getChatOpen()
  {
    return ((bcf.z().m instanceof bee)) || ((bcf.z().m instanceof GuiSymbolSelector));
  }
  
  public static void deleteChatLine(int p_146242_1_)
  {
    Iterator var2 = field_146253_i.iterator();
    while (var2.hasNext())
    {
      bcb var3 = (bcb)var2.next();
      if (var3.c() == p_146242_1_) {
        var2.remove();
      }
    }
    var2 = chatLines.iterator();
    while (var2.hasNext())
    {
      bcb var3 = (bcb)var2.next();
      if (var3.c() == p_146242_1_) {
        var2.remove();
      }
    }
  }
  
  public static int getChatWidth()
  {
    return calculateChatboxWidth(bcf.z().u.F);
  }
  
  public static int getModChatWidth()
  {
    return LabyMod.getInstance().draw.getWidth() - calculateChatboxWidth(bcf.z().u.F) - 10;
  }
  
  public static int getChatHeight()
  {
    return calculateChatboxHeight(getChatOpen() ? bcf.z().u.H : bcf.z().u.G);
  }
  
  public static float getChatScale()
  {
    return bcf.z().u.E;
  }
  
  public static int calculateChatboxWidth(float p_146233_0_)
  {
    short var1 = 320;
    byte var2 = 40;
    return on.d(p_146233_0_ * (var1 - var2) + var2);
  }
  
  public static int calculateChatboxHeight(float p_146243_0_)
  {
    short var1 = 180;
    byte var2 = 20;
    return on.d(p_146243_0_ * (var1 - var2) + var2);
  }
  
  public static int getLineCount()
  {
    return getChatHeight() / 9;
  }
}
