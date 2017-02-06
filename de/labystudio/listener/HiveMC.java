package de.labystudio.listener;

import de.labystudio.chat.ChatHandler;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.ModSettings;
import de.labystudio.utils.Color;
import de.labystudio.utils.ModGui;
import ng;

public class HiveMC
{
  public static int kills = 0;
  public static int points = 0;
  public static int displayPoints = 0;
  public static String server;
  public static int plus = 0;
  public static int minus = 0;
  public static boolean isHive = false;
  
  public static void updateHiveMC()
  {
    isHive = (LabyMod.getInstance().ip.toLowerCase().contains("hivemc")) || (LabyMod.getInstance().ip.toLowerCase().contains("hive.sexy"));
  }
  
  public static boolean isHive()
  {
    return (isHive) && (ConfigManager.settings.gameHiveMC.booleanValue());
  }
  
  public static void serverHiveChat(String clean, String raw)
  {
    if (!isHive()) {
      return;
    }
    boolean prefix = false;
    prefix = (clean.length() > 18) && (clean.substring(1).startsWith(" SurvivalGames "));
    if (prefix) {
      clean = clean.substring(18);
    }
    if ((prefix) && (clean.startsWith("The round has started!"))) {
      LabyMod.getInstance().playSound(ng.gJ, 1.0F);
    }
    if ((prefix) && (clean.startsWith("You gained ")) && (clean.contains(" global points for killing ")) && (clean.endsWith(".")))
    {
      kills += 1;
      try
      {
        int i = Integer.parseInt(clean.replace("You gained ", "").split(" global points for killing ")[0]);
        points += i;
        plus += i;
      }
      catch (Exception error)
      {
        error.printStackTrace();
      }
    }
    if ((prefix) && (clean.startsWith("You lost ")) && (clean.contains(" global points for being killed by ")) && (clean.endsWith("."))) {
      try
      {
        int i = Integer.parseInt(clean.replace("You lost ", "").split(" global points for being killed by ")[0]);
        points -= i;
        minus += i;
      }
      catch (Exception error)
      {
        error.printStackTrace();
      }
    }
    if ((clean.startsWith("Points: ")) && (points == 0))
    {
      try
      {
        points = Integer.parseInt(clean.replace("Points: ", ""));
      }
      catch (Exception error)
      {
        error.printStackTrace();
      }
      if (ConfigManager.settings.hiveAutoScramble) {
        LabyMod.getInstance().sendCommand("scramble");
      }
    }
    if (clean.startsWith(LabyMod.getInstance().getPlayerName() + " has joined the server."))
    {
      points = 0;
      kills = 0;
      plus = 0;
      minus = 0;
      LabyMod.getInstance().sendCommand("records");
      LabyMod.getInstance().sendCommand("whereami");
    }
    if ((clean.startsWith("[HiveMC] You are playing on ")) && (clean.endsWith("."))) {
      try
      {
        server = clean.replace("[HiveMC] You are playing on ", "").replace(".", "");
        ChatHandler.updateGameMode(server);
      }
      catch (Exception error)
      {
        error.printStackTrace();
      }
    }
  }
  
  public static void drawHiveGui()
  {
    if (!isHive()) {
      return;
    }
    if (points != 0)
    {
      String c = "";
      if (displayPoints == 0) {
        displayPoints = points;
      }
      if (displayPoints > points)
      {
        displayPoints -= 1;
        c = Color.cl("c");
      }
      if (displayPoints < points)
      {
        displayPoints += 1;
        c = Color.cl("a");
      }
      String info = "";
      if (plus != 0) {
        info = info + Color.cl("f") + " | " + Color.cl("a") + "+" + plus;
      }
      if (minus != 0) {
        info = info + Color.cl("f") + " | " + Color.cl("c") + "-" + minus;
      }
      ModGui.addMainLabel("Points", c + displayPoints + "" + info, ModGui.mainList);
    }
    if (kills != 0) {
      ModGui.addMainLabel("Kills", kills + "", ModGui.mainList);
    }
    if (server != null) {
      ModGui.addMainLabel("Server", server, ModGui.mainList);
    }
  }
  
  public static void reset()
  {
    kills = 0;
    points = 0;
    plus = 0;
    minus = 0;
    server = null;
  }
}
