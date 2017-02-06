package de.labystudio.listener;

import bcf;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.ModSettings;
import de.labystudio.utils.Allowed;
import de.labystudio.utils.Color;
import de.labystudio.utils.FilterLoader;
import de.labystudio.utils.FriendsLoader;
import java.util.Map;
import ng;

public class ChatListener
{
  public static long init = 0L;
  
  public static void serverChat(String raw, String clean)
  {
    GommeHD.serverGommeHDChat(clean, raw);
    JumpLeague.serverPlayMinityChat(clean, raw);
    Timolia.serverTimoliaChat(clean, raw);
    Revayd.serverRevaydChat(clean, raw);
    HiveMC.serverHiveChat(clean, raw);
  }
  
  public static boolean allowedToPrint(String raw, String clean)
  {
    if (raw == null) {
      return true;
    }
    serverChat(raw, clean);
    return true;
  }
  
  public static String replaceMessage(String raw, String clean)
  {
    if (Allowed.nick())
    {
      boolean clear = false;
      for (String friends : FriendsLoader.friends.keySet()) {
        if ((FriendsLoader.friends.get(friends) != null) && (!((String)FriendsLoader.friends.get(friends)).replace(" ", "").isEmpty())) {
          if (clean.contains(friends))
          {
            if ((raw.contains(":")) && (raw.split(":")[0].contains(friends))) {
              return raw.replaceFirst(friends, ((String)FriendsLoader.friends.get(friends)).replace("&", "§") + "§r");
            }
            if ((raw.contains(">")) && (raw.split(":")[0].contains(friends))) {
              return raw.replaceFirst(friends, ((String)FriendsLoader.friends.get(friends)).replace("&", "§") + "§r");
            }
            if ((raw.contains(":")) && (raw.split(":")[1].contains(friends))) {
              return raw;
            }
            if ((raw.contains(">")) && (raw.split(":")[1].contains(friends))) {
              return raw;
            }
            try
            {
              return raw.replaceFirst(friends, ((String)FriendsLoader.friends.get(friends)).replace("&", "§") + "§r");
            }
            catch (Exception error)
            {
              clear = true;
            }
          }
        }
      }
      if (clear)
      {
        FriendsLoader.friends.clear();
        FriendsLoader.saveFriends();
      }
    }
    return raw;
  }
  
  public static boolean isServerMSG(String msg)
  {
    if ((ConfigManager.settings.chatFilter.booleanValue()) && (FilterLoader.enabled)) {
      for (String filter : FilterLoader.filters)
      {
        String m = filter.toLowerCase();
        if ((contains(msg, m)) && (!m.contains("%k%")) && (
          (!m.contains("%s%")) || (m.contains("%k%")))) {
          return true;
        }
      }
    }
    if (ConfigManager.settings.extraChat.booleanValue())
    {
      if ((LabyMod.getInstance().ip == null) || (LabyMod.getInstance().ip.isEmpty())) {
        return false;
      }
      if (((LabyMod.getInstance().ip.toLowerCase().contains("gommehd.net")) || (LabyMod.getInstance().ip.toLowerCase().contains("minekits"))) && 
        ((msg.toLowerCase().contains(LabyMod.getInstance().getPlayerName().toLowerCase())) || (msg.contains("Du")) || (msg.contains("Dir"))) && 
        (msg.startsWith("[F")) && ((msg.contains(" -> ")) || (msg.contains(" <- ")))) {
        return true;
      }
      if (((LabyMod.getInstance().ip.toLowerCase().contains("infect")) || (LabyMod.getInstance().ip.toLowerCase().contains("kitpvp"))) && 
        (msg.contains("me")) && ((msg.contains(" -> ")) || (msg.contains(" <- ")))) {
        return true;
      }
      if ((LabyMod.getInstance().ip.toLowerCase().contains("timolia")) && 
        (msg.startsWith(Color.c + "1")) && (msg.contains("MSG")) && (msg.contains("Du"))) {
        return true;
      }
      if (((LabyMod.getInstance().ip.toLowerCase().contains("brawl")) || (LabyMod.getInstance().ip.toLowerCase().contains("mcpvp")) || (LabyMod.getInstance().ip.toLowerCase().contains("mc-hg"))) && (
        (msg.startsWith("(To ")) || (msg.startsWith("(From ")))) {
        return true;
      }
      if ((LabyMod.getInstance().ip.toLowerCase().contains("hivemc")) && 
        (msg.toLowerCase().contains(LabyMod.getInstance().getPlayerName().toLowerCase())) && (msg.contains("PRIVATE"))) {
        return true;
      }
      if (((LabyMod.getInstance().ip.toLowerCase().contains("mineplex")) || (LabyMod.getInstance().ip.toLowerCase().contains("playminity"))) && 
        (msg.toLowerCase().contains(LabyMod.getInstance().getPlayerName().toLowerCase())) && ((msg.contains(" > ")) || (msg.contains(" < ")))) {
        return true;
      }
    }
    return false;
  }
  
  private static boolean contains(String msg, String m)
  {
    if (m.contains("%b%"))
    {
      String[] list = m.replace("%s%", "").replace("%k%", "").split("%b%");
      boolean output = false;
      boolean first = true;
      for (String value : list) {
        if (!value.isEmpty()) {
          if (first)
          {
            if (msg.toLowerCase().contains(value))
            {
              output = true;
            }
            else
            {
              output = false;
              break;
            }
            first = false;
          }
          else if (!msg.toLowerCase().contains(value))
          {
            output = true;
          }
          else
          {
            output = false;
            break;
          }
        }
      }
      if (output)
      {
        playSound(m);
        return true;
      }
    }
    else if (msg.toLowerCase().contains(m.replace("%s%", "").replace("%k%", "")))
    {
      playSound(m);
      return true;
    }
    return false;
  }
  
  private static void playSound(String m)
  {
    if ((m.contains("%s%")) && (init + 1000L < bcf.I()) && (LabyMod.getInstance().isInGame())) {
      LabyMod.getInstance().playSound(ng.dI, 1.0F);
    }
  }
  
  public static boolean isMarked(String msg)
  {
    for (String filter : FilterLoader.filters)
    {
      String m = filter.toLowerCase();
      if ((m.startsWith("%k%")) && (contains(msg, m.replace("%k%", "").replace("%s%", "")))) {
        return true;
      }
    }
    return false;
  }
}
