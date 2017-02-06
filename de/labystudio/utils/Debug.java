package de.labystudio.utils;

import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.ModSettings;
import java.io.PrintStream;

public class Debug
{
  private static EnumDebugMode aktiveMode = EnumDebugMode.NONE;
  private static String lastDebugMessage = "";
  private static int stackingMessages = 0;
  private static long checkSpam = 0L;
  private static EnumDebugMode lastDebugMode = EnumDebugMode.NONE;
  
  public static EnumDebugMode getAktiveMode()
  {
    return aktiveMode;
  }
  
  public static boolean debug(EnumDebugMode mode, String message, int stackSize, boolean aktive)
  {
    if ((mode == getAktiveMode()) || (aktive))
    {
      if ((lastDebugMessage.equals(message)) && (stackingMessages <= stackSize))
      {
        stackingMessages += 1;
        lastDebugMode = mode;
        checkSpam = System.currentTimeMillis();
        return true;
      }
      if (stackingMessages != 0)
      {
        stackingMessages += 1;
        outStack(lastDebugMode.name(), stackingMessages, lastDebugMessage);
        stackingMessages = 0;
        checkSpam = 0L;
        lastDebugMode = EnumDebugMode.NONE;
      }
      if (!lastDebugMessage.equals(message)) {
        out(mode.name(), message);
      }
      lastDebugMessage = message;
      return true;
    }
    return false;
  }
  
  public static boolean debug(EnumDebugMode mode, String message)
  {
    return debug(mode, message, 10, false);
  }
  
  public static boolean debug(String message)
  {
    return debug(EnumDebugMode.NONE, message, 10, true);
  }
  
  public static boolean debug(String message, int stackSize)
  {
    return debug(EnumDebugMode.NONE, message, stackSize, true);
  }
  
  private static void out(String prefix, String message)
  {
    outStack(prefix, 1, message);
  }
  
  private static void outStack(String prefix, int amount, String message)
  {
    String debugPrefix = "[Debug]";
    if (!prefix.equals("NONE")) {
      debugPrefix = debugPrefix + "[" + prefix + "]";
    }
    if (amount > 1) {
      System.out.println(debugPrefix + "[" + amount + "x] " + message);
    } else {
      System.out.println(debugPrefix + " " + message);
    }
  }
  
  public static void updateDebugMessages()
  {
    if ((stackingMessages != 0) && (checkSpam + 1000L < System.currentTimeMillis()))
    {
      out(lastDebugMode.name(), lastDebugMessage);
      stackingMessages = 0;
      checkSpam = 0L;
      lastDebugMode = EnumDebugMode.NONE;
    }
    if (api()) {
      aktiveMode = EnumDebugMode.API;
    } else if (capes()) {
      aktiveMode = EnumDebugMode.CAPES;
    } else if (chat()) {
      aktiveMode = EnumDebugMode.CHAT;
    } else if (server()) {
      aktiveMode = EnumDebugMode.SERVER;
    } else if (teamspeak()) {
      aktiveMode = EnumDebugMode.TEAMSPEAK;
    } else if (timings()) {
      aktiveMode = EnumDebugMode.TIMINGS;
    } else {
      aktiveMode = EnumDebugMode.NONE;
    }
  }
  
  public static boolean chat()
  {
    if (ConfigManager.settings == null) {
      return false;
    }
    return ConfigManager.settings.motd.startsWith("/debug chat");
  }
  
  public static boolean teamspeak()
  {
    if (ConfigManager.settings == null) {
      return false;
    }
    return ConfigManager.settings.motd.equals("/debug ts");
  }
  
  public static boolean timings()
  {
    if (ConfigManager.settings == null) {
      return false;
    }
    return ConfigManager.settings.motd.equals("/debug timings");
  }
  
  public static boolean server()
  {
    if (ConfigManager.settings == null) {
      return false;
    }
    return ConfigManager.settings.motd.equals("/debug chat-setport");
  }
  
  public static boolean capes()
  {
    if (ConfigManager.settings == null) {
      return false;
    }
    return ConfigManager.settings.motd.equals("/debug capes");
  }
  
  public static boolean api()
  {
    if (ConfigManager.settings == null) {
      return false;
    }
    return ConfigManager.settings.motd.equals("/debug api");
  }
  
  public static enum EnumDebugMode
  {
    API,  CAPES,  SERVER,  TIMINGS,  TEAMSPEAK,  CHAT,  NONE;
    
    private EnumDebugMode() {}
  }
}
