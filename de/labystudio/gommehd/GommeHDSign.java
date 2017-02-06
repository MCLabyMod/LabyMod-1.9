package de.labystudio.gommehd;

import aqn;
import bcf;
import bct;
import bdb;
import bks;
import bni;
import cj;
import cq;
import de.labystudio.labymod.LabyMod;
import de.labystudio.listener.GommeHD;
import de.labystudio.utils.Color;
import eu;
import ix;
import ix.a;
import java.util.ArrayList;
import java.util.List;
import ng;

public class GommeHDSign
{
  public static boolean isGommeSign(ArrayList<String> text)
  {
    if (!GommeHD.isGommeHD()) {
      return false;
    }
    if (text.size() == 0) {
      return false;
    }
    if ((!((String)text.get(0)).contains("- ")) || (!((String)text.get(0)).contains(" -"))) {
      return false;
    }
    if (((String)text.get(0)).contains("---")) {
      return false;
    }
    return true;
  }
  
  public static ArrayList<String> getText(aqn sign)
  {
    ArrayList<String> text = new ArrayList();
    bct var20 = bcf.z().k;
    byte var14 = 0;
    for (int var15 = 0; var15 < sign.a.length; var15++) {
      if (sign.a[var15] != null)
      {
        eu var16 = sign.a[var15];
        List var17 = bdb.a(var16, 90, var20, false, true);
        String var18 = (var17 != null) && (var17.size() > 0) ? ((eu)var17.get(0)).d() : "";
        text.add(var18);
      }
    }
    if (!isGommeSign(text)) {
      return new ArrayList();
    }
    return text;
  }
  
  public static boolean isAvailable(ArrayList<String> text)
  {
    ArrayList<String> t = new ArrayList();
    t.addAll(text);
    if ((t.size() > 2) && (!t.isEmpty())) {
      try
      {
        String a = (String)t.get(1);
        if ((a != null) && 
          (a.contains("aLobby"))) {
          return true;
        }
      }
      catch (Exception localException) {}
    }
    return false;
  }
  
  public static boolean isFull(ArrayList<String> text)
  {
    ArrayList<String> t = new ArrayList();
    t.addAll(text);
    if ((!t.isEmpty()) && 
      (t.size() > 2) && (t.get(1) != null) && 
      (((String)t.get(1)).contains("6Lobby"))) {
      return true;
    }
    return false;
  }
  
  public static String getMap(ArrayList<String> text)
  {
    ArrayList<String> t = new ArrayList();
    t.addAll(text);
    if ((t.size() > 3) && (!t.isEmpty()) && 
      (t.get(2) != null)) {
      return (String)t.get(2);
    }
    return "";
  }
  
  public static boolean size(ArrayList<String> text)
  {
    if (LabyMod.getInstance().gommeHDSeachPartySize == 0) {
      return true;
    }
    ArrayList<String> tt = new ArrayList();
    tt.addAll(text);
    try
    {
      if ((tt.size() > 3) && (!tt.isEmpty()) && 
        (tt.get(3) != null))
      {
        String d = Color.removeColor((String)tt.get(3));
        if (d.contains("/"))
        {
          String[] online = d.split("/");
          if ((Integer.parseInt(online[1]) - Integer.parseInt(online[0]) >= LabyMod.getInstance().gommeHDSeachPartySize) && 
            (tt.get(2) != null))
          {
            String t = Color.removeColor((String)tt.get(2));
            if ((t.contains(" ")) && (t.contains("x")))
            {
              String[] map = t.split(" ");
              String[] maxPlayers = map[1].split("x");
              if (Integer.parseInt(maxPlayers[1]) >= LabyMod.getInstance().gommeHDSeachPartySize) {
                return true;
              }
            }
            else
            {
              return true;
            }
          }
        }
      }
    }
    catch (Exception localException) {}
    return false;
  }
  
  public static boolean search(ArrayList<String> text)
  {
    try
    {
      String map = getMap(text).toLowerCase();
      String[] arrayOfString1;
      int i;
      String str1;
      if ((!LabyMod.getInstance().gommeHDSearchBlacklist.isEmpty()) && (LabyMod.getInstance().gommeHDSearch.isEmpty()))
      {
        arrayOfString1 = LabyMod.getInstance().gommeHDSearchBlacklist.toLowerCase().split(",");i = arrayOfString1.length;
        for (str1 = 0; str1 < i; str1++)
        {
          String l = arrayOfString1[str1];
          if (map.contains(l)) {
            return false;
          }
        }
        return true;
      }
      if (LabyMod.getInstance().gommeHDSearch.isEmpty()) {
        return true;
      }
      String l;
      if (LabyMod.getInstance().gommeHDSearchBlacklist.isEmpty())
      {
        arrayOfString1 = LabyMod.getInstance().gommeHDSearch.toLowerCase().split(",");i = arrayOfString1.length;
        for (str1 = 0; str1 < i; str1++)
        {
          l = arrayOfString1[str1];
          if (map.contains(l)) {
            return true;
          }
        }
      }
      else
      {
        boolean result = false;
        String[] arrayOfString2 = LabyMod.getInstance().gommeHDSearch.toLowerCase().split(",");str1 = arrayOfString2.length;
        for (l = 0; l < str1; l++)
        {
          String l = arrayOfString2[l];
          if (map.contains(l)) {
            result = true;
          }
        }
        arrayOfString2 = LabyMod.getInstance().gommeHDSearchBlacklist.toLowerCase().split(",");String str2 = arrayOfString2.length;
        for (l = 0; l < str2; l++)
        {
          String l = arrayOfString2[l];
          if (map.contains(l)) {
            result = false;
          }
        }
        return result;
      }
    }
    catch (Exception localException) {}
    return false;
  }
  
  public static void green()
  {
    bni.c(0.6F, 23.6F, 0.6F, 0.6F);
  }
  
  public static void red()
  {
    bni.c(10.0F, 0.6F, 0.6F, 0.6F);
  }
  
  public static void orange()
  {
    bni.c(10.0F, 1.6F, 0.6F, 0.6F);
  }
  
  static int noSpam = 0;
  
  public static void sendJoinPacket(cj b)
  {
    if (noSpam > 10) {
      noSpam = 0;
    }
    if (noSpam == 0)
    {
      cq c = cq.b;
      ix packet = new ix(ix.a.a, b, c);
      bcf.z().v().a(packet);
    }
    noSpam += 1;
  }
  
  public static void render(aqn sign)
  {
    if (sign.updateSign > 10) {
      sign.updateSign = 0;
    }
    if ((LabyMod.getInstance().gommeHDSeachAllowed) && 
      (GommeHD.isGommeHD()) && 
      (sign.updateSign == 0))
    {
      sign.setText(new ArrayList());
      sign.setAvailable(sign.getText());
      sign.setFull(sign.getText());
      sign.setSearch(sign.getText());
      sign.setSize(sign.getText());
    }
    sign.updateSign += 1;
    if ((LabyMod.getInstance().gommeHDSeachAllowed) && 
      (GommeHD.isGommeHD()) && 
      (!sign.getText().isEmpty()))
    {
      ArrayList<String> text = sign.getText();
      if (!text.isEmpty()) {
        if (sign.getAvailable())
        {
          if (sign.getSearch())
          {
            if (sign.getSize())
            {
              green();
              if (LabyMod.getInstance().gommeHDAutoJoin) {
                sendJoinPacket(sign.v());
              }
              if ((LabyMod.getInstance().gommeHDSound) && 
                (bcf.I() / 2L % 30L == 0L)) {
                LabyMod.getInstance().playSound(ng.bt, 10.0F, 2.0F, sign.v().p(), sign.v().q(), sign.v().r());
              }
            }
            else
            {
              orange();
            }
          }
          else {
            red();
          }
        }
        else if (sign.isFull())
        {
          if (sign.getSearch()) {
            orange();
          } else {
            red();
          }
        }
        else {
          red();
        }
      }
    }
  }
}
