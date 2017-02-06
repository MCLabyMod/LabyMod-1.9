package mods.supportpanel.main;

import bcf;
import de.labystudio.labymod.LabyMod;
import de.labystudio.modapi.EventHandler;
import de.labystudio.modapi.Listener;
import de.labystudio.modapi.events.ChatReceivedEvent;
import de.labystudio.modapi.events.GameTickEvent;
import de.labystudio.utils.Color;
import java.util.ArrayList;
import java.util.HashMap;
import org.lwjgl.input.Keyboard;

public class SupportListener
  implements Listener
{
  @EventHandler
  public void onTick(GameTickEvent e)
  {
    if ((Settings.settings != null) && (Settings.settings.enabled) && (Keyboard.isKeyDown(Settings.settings.key)) && (LabyMod.getInstance().isInGame()))
    {
      if (!SupportPanel.keep)
      {
        SupportPanel.keep = true;
        if ((bcf.z().m != null) && (bcf.z().m == SupportPanel.screen)) {
          bcf.z().a(null);
        } else if (bcf.z().m == null) {
          bcf.z().a(SupportPanel.screen);
        }
      }
    }
    else {
      SupportPanel.keep = false;
    }
  }
  
  @EventHandler
  public void onChat(ChatReceivedEvent e)
  {
    try
    {
      String cc = Color.c;
      String message = e.getMsg();
      for (int i = 0; i <= 1; i++)
      {
        String leer = "";
        if (i == 0) {
          leer = " ";
        }
        if ((message.startsWith(cc + "r")) && (message.contains(cc + "r" + cc + "7:" + leer + cc + "r" + cc + "f")))
        {
          String color = Color.cl("a");
          String[] chat = message.split(cc + "r" + cc + "7:" + leer + cc + "r" + cc + "f");
          String player = chat[0];
          if ((player.contains(cc + "r" + cc + "7[" + cc + "r" + cc + "e")) && (player.contains(cc + "r" + cc + "7] "))) {
            player = player.split(cc + "r" + cc + "7] ")[1];
          }
          if (player.startsWith(cc + "r"))
          {
            player.replace(cc + "r", "");
            color = cc + player.charAt(1);
            player = player.replace(color, "");
          }
          String playerMessage = chat[1];
          if (!SupportPanel.chatLog.containsKey(player)) {
            SupportPanel.chatLog.put(player, new ArrayList());
          }
          if (playerMessage.startsWith(" ")) {
            playerMessage = playerMessage.replaceFirst(" ", "");
          }
          ArrayList<GommeMessage> list = (ArrayList)SupportPanel.chatLog.get(player);
          list.add(new GommeMessage(player, playerMessage, color));
          if (list.size() <= 50) {
            break;
          }
          list.remove(list.get(50)); break;
        }
      }
    }
    catch (Exception error)
    {
      error.printStackTrace();
    }
  }
}
