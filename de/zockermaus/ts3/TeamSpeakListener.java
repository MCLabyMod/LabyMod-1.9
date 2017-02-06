package de.zockermaus.ts3;

import de.labystudio.chat.EnumAlertType;
import de.labystudio.gui.GuiAchievementMod;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.ModSettings;
import de.labystudio.utils.Color;

public class TeamSpeakListener
  implements ControlListener
{
  public void onPokeRecieved(TeamSpeakUser user, String pokeMessage)
  {
    if (user == null) {
      return;
    }
    String m = Color.cl("9") + user.getNickName() + Color.cl("a") + " pokes you.";
    if (!pokeMessage.isEmpty()) {
      m = Color.cl("9") + user.getNickName() + Color.cl("a") + " pokes you: " + pokeMessage;
    }
    TeamSpeak.infoAll(m);
    if (ConfigManager.settings.teamSpeakAlertTypeChat)
    {
      if (ConfigManager.settings.alertsTeamSpeak) {
        LabyMod.getInstance().displayMessageInChat(TeamSpeak.chatPrefix + m);
      }
    }
    else {
      LabyMod.getInstance().achievementGui.displayBroadcast(user.getNickName(), TeamSpeak.url(pokeMessage), EnumAlertType.TEAMSPEAK);
    }
    TeamSpeak.overlayWindows.openInfo(user.getClientId(), "You Have Been Poked", m);
  }
  
  public void onClientDisconnected(TeamSpeakUser user, String reason)
  {
    if (user == null) {
      return;
    }
    TeamSpeak.addChat(null, null, Color.cl("9") + user.getNickName() + Color.cl("7") + " disconnected from the Server (" + reason + ")", EnumTargetMode.SERVER);
    for (Chat chat : TeamSpeak.chats) {
      if ((chat.getChatOwner() != null) && (chat.getTargetMode() == EnumTargetMode.USER) && 
        (chat.getChatOwner().getClientId() == user.getClientId())) {
        chat.addMessage(null, Color.cl("7") + "Your chat partner has disconnected.");
      }
    }
  }
  
  public void onClientTimout(TeamSpeakUser user)
  {
    if (user == null) {
      return;
    }
    TeamSpeak.addChat(null, null, Color.cl("9") + user.getNickName() + Color.cl("7") + " timed out.", EnumTargetMode.SERVER);
  }
  
  public void onClientConnect(TeamSpeakUser user)
  {
    TeamSpeak.addChat(null, null, Color.cl("9") + user.getNickName() + Color.cl("7") + " connected to the Server.", EnumTargetMode.SERVER);
  }
  
  public void onMessageRecieved(TeamSpeakUser target, TeamSpeakUser user, String message)
  {
    if ((user == null) || (target == null)) {
      return;
    }
    TeamSpeak.addChat(target, user, message, EnumTargetMode.USER);
    if (TeamSpeakController.getInstance().me.getClientId() != user.getClientId()) {
      if (ConfigManager.settings.teamSpeakAlertTypeChat)
      {
        if (ConfigManager.settings.alertsTeamSpeak) {
          LabyMod.getInstance().displayMessageInChat(TeamSpeak.chatPrefix + Color.cl("9") + user.getNickName() + Color.cl("7") + ": " + TeamSpeak.url(message));
        }
      }
      else {
        LabyMod.getInstance().achievementGui.displayBroadcast(user.getNickName(), TeamSpeak.url(message), EnumAlertType.TEAMSPEAK);
      }
    }
  }
  
  public void onChannelMessageRecieved(TeamSpeakUser user, String message)
  {
    TeamSpeak.addChat(null, user, message, EnumTargetMode.CHANNEL);
    if ((TeamSpeakController.getInstance().me != null) && (TeamSpeakController.getInstance().me.getClientId() != user.getClientId())) {
      if (ConfigManager.settings.teamSpeakAlertTypeChat)
      {
        if (ConfigManager.settings.alertsTeamSpeak) {
          LabyMod.getInstance().displayMessageInChat(TeamSpeak.chatPrefix + Color.cl("9") + user.getNickName() + Color.cl("7") + ": " + TeamSpeak.url(message));
        }
      }
      else {
        LabyMod.getInstance().achievementGui.displayBroadcast(user.getNickName(), TeamSpeak.url(message), EnumAlertType.TEAMSPEAK);
      }
    }
  }
  
  public void onServerMessageRecieved(TeamSpeakUser user, String message)
  {
    TeamSpeak.addChat(null, user, message, EnumTargetMode.SERVER);
  }
  
  public void onClientStartTyping(TeamSpeakUser user) {}
  
  public void onDisconnect() {}
  
  public void onConnect()
  {
    TeamSpeak.scrollChannel = 0;
  }
  
  public void onError(int errorId, String errorMessage)
  {
    String m = Color.cl("c") + TeamSpeak.fix(errorMessage) + " (Error " + errorId + ")";
    TeamSpeak.error(m);
  }
}
