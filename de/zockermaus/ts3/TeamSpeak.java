package de.zockermaus.ts3;

import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.Timings;
import de.labystudio.utils.Color;
import de.labystudio.utils.DrawUtils;
import de.labystudio.utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TeamSpeak
{
  private static final Logger logger = LogManager.getLogger("TeamSpeak");
  public static String chatPrefix = Color.cl("8") + "[" + Color.cl("5") + Color.cl("l") + "TeamSpeak" + Color.cl("8") + "] " + Color.cl("7");
  public static TeamSpeakOverlayWindow overlayWindows;
  
  public static void enable()
  {
    Timings.start("Enable TeamSpeak");
    setupChat();
    new TeamSpeakController(new TeamSpeakListener());
    overlayWindows = new TeamSpeakOverlayWindow();
    Timings.stop("Enable TeamSpeak");
  }
  
  public static void setupChat()
  {
    chats.clear();
    chats.add(new Chat(-2, EnumTargetMode.SERVER));
    chats.add(new Chat(-1, EnumTargetMode.CHANNEL));
  }
  
  public static String inputString = "";
  public static ArrayList<Chat> chats = new ArrayList();
  public static int selectedChat = -1;
  public static int selectedChannel = -1;
  public static int selectedUser = -1;
  public static boolean defaultScreen = false;
  public static int ySplit;
  public static int xSplit;
  public static int scrollChat = 0;
  public static int scrollChannel = 0;
  public static boolean callBack = false;
  public static int callBackClient = 0;
  public static boolean teamSpeakGroupPrefix = false;
  
  public static void addChat(TeamSpeakUser target, TeamSpeakUser sender, String message, EnumTargetMode mode)
  {
    if (message != null)
    {
      message = fix(message);
      message = colors(message);
      message = url(message);
    }
    Chat openChat;
    if (mode == EnumTargetMode.USER)
    {
      TeamSpeakUser chatOwner = sender;
      if (sender == null) {
        chatOwner = target;
      } else if (sender.getClientId() == TeamSpeakController.getInstance().me().getClientId()) {
        chatOwner = target;
      }
      if (chatOwner == null)
      {
        error("User is offline");
        return;
      }
      openChat = null;
      for (Chat chat : chats) {
        if (chat.getSlotId() == chatOwner.getClientId())
        {
          openChat = chat;
          break;
        }
      }
      if (sender == null)
      {
        if (openChat != null) {
          openChat.addMessage(null, message);
        }
      }
      else if (openChat == null)
      {
        if (message == null) {
          chats.add(new Chat(chatOwner, sender, mode));
        } else {
          chats.add(new Chat(chatOwner, sender, mode, message));
        }
      }
      else if (message == null) {
        selectedChat = chatOwner.getClientId();
      } else {
        openChat.addMessage(sender, message);
      }
    }
    if (mode == EnumTargetMode.CHANNEL)
    {
      Chat openChat = null;
      for (Chat chat : chats) {
        if (chat.getSlotId() == -1)
        {
          openChat = chat;
          break;
        }
      }
      if (openChat != null) {
        openChat.addMessage(sender, message);
      }
    }
    if (mode == EnumTargetMode.SERVER)
    {
      Chat openChat = null;
      for (Chat chat : chats) {
        if (chat.getSlotId() == -2)
        {
          openChat = chat;
          break;
        }
      }
      if (openChat != null) {
        openChat.addMessage(sender, message);
      }
    }
  }
  
  public static String replaceColor(String message, String name, String code)
  {
    return message.replaceAll("(?i)Color=" + name, Color.cl(code)).replace("[" + Color.cl(code) + "]", Color.cl(code));
  }
  
  public static String replaceHtml(String message, String tag, String toPrefix, String toSuffix)
  {
    return message.replaceAll("(?i)" + tag, toPrefix).replace("[" + toPrefix + "]", toPrefix).replace("[/" + toPrefix + "]", toSuffix);
  }
  
  public static String colors(String message)
  {
    message = replaceColor(message, "RED", "c");
    message = replaceColor(message, "BLUE", "9");
    message = replaceColor(message, "GREEN", "2");
    message = replaceColor(message, "YELLOW", "e");
    message = replaceColor(message, "GOLD", "6");
    message = replaceColor(message, "AQUA", "3");
    message = replaceColor(message, "WHITE", "f");
    message = replaceColor(message, "BLACK", "0");
    message = message.replaceAll("(?i)/Color", Color.cl("7")).replace("[" + Color.cl("7") + "]", Color.cl("7"));
    return message;
  }
  
  public static String url(String message)
  {
    message = replaceHtml(message, "URL", Color.cl("9") + Color.cl("n"), Color.cl("7"));
    return message;
  }
  
  public static String toUrl(String message)
  {
    ArrayList<String> urls = Utils.extractDomains(message);
    if (!urls.isEmpty()) {
      for (String url : urls) {
        message = message.replace(url, "[URL]" + url + "[/URL]");
      }
    }
    return message;
  }
  
  public static String fix(String message)
  {
    message = message.replace("\\/", "/");
    message = message.replace("\\p", "|");
    message = message.replace("\\s", " ");
    message = message.replace("\\\\", "\\");
    message = message.replace("\\n", " ");
    return message;
  }
  
  public static String unFix(String message)
  {
    message = message.replace("\\", "\\\\");
    message = message.replace("/", "\\/");
    message = message.replace("|", "\\p");
    message = message.replace(" ", "\\s");
    return message;
  }
  
  public static String toStarSpacer(String channelName, int xSplit)
  {
    String s = channelName.split("]", 2)[1];
    String output = "";
    int max = xSplit / 10;
    for (int i = 0; i <= max; i++) {
      output = output + s;
    }
    if (output.length() > max) {
      output = output.substring(0, max);
    }
    return output;
  }
  
  public static String toCenterSpacer(String channelName)
  {
    return channelName.split("]", 2)[1];
  }
  
  public static boolean isSpacer(String channelName)
  {
    if ((channelName.toLowerCase().startsWith("[spacer")) && (channelName.contains("]"))) {
      return true;
    }
    return false;
  }
  
  public static boolean isStarSpacer(String channelName)
  {
    if ((channelName.toLowerCase().startsWith("[*spacer")) && (channelName.contains("]"))) {
      return true;
    }
    return false;
  }
  
  public static boolean isCenterSpacer(String channelName)
  {
    if ((channelName.toLowerCase().startsWith("[cspacer")) && (channelName.contains("]"))) {
      return true;
    }
    return false;
  }
  
  public static String country(String country)
  {
    if (country == null) {
      return "Unknown";
    }
    if (country.equalsIgnoreCase("DE")) {
      return "Germany";
    }
    if (country.equalsIgnoreCase("AT")) {
      return "Austria";
    }
    if (country.equalsIgnoreCase("TR")) {
      return "Turkey";
    }
    return country;
  }
  
  public static String status(boolean status, String on, String off)
  {
    if (status) {
      return on;
    }
    return off;
  }
  
  public static TeamSpeakChannel getFromOrder(int channelOrder)
  {
    for (TeamSpeakChannel channel : ) {
      if (channel.getChannelOrder() == channelOrder) {
        return channel;
      }
    }
    return null;
  }
  
  public static ArrayList<Integer> outOfView = new ArrayList();
  
  public static boolean isChannelNotInView(int channelId)
  {
    for (Iterator localIterator = outOfView.iterator(); localIterator.hasNext();)
    {
      int ch = ((Integer)localIterator.next()).intValue();
      if (ch == channelId) {
        return true;
      }
    }
    return false;
  }
  
  public static void updateScroll(int channelId, boolean extend)
  {
    if (isChannelNotInView(channelId)) {
      if (extend) {
        scrollChannel -= 10;
      } else {
        scrollChannel += 10;
      }
    }
  }
  
  public static void setDefaultScreen()
  {
    if (!defaultScreen)
    {
      defaultScreen = true;
      xSplit = LabyMod.getInstance().draw.getWidth() / 3 * 2;
      ySplit = LabyMod.getInstance().draw.getHeight() / 4 * 3;
    }
  }
  
  public static int booleanToInteger(boolean input)
  {
    if (input) {
      return 1;
    }
    return 0;
  }
  
  public static void print(String msg)
  {
    logger.info(msg);
  }
  
  public static void error(String errorMessage)
  {
    String m = Color.cl("c") + errorMessage;
    if (selectedChat == -1)
    {
      addChat(null, null, m, EnumTargetMode.CHANNEL);
    }
    else if (selectedChat == -2)
    {
      addChat(null, null, m, EnumTargetMode.SERVER);
    }
    else
    {
      TeamSpeakUser user = TeamSpeakController.getInstance().getUser(selectedChat);
      if (user != null) {
        addChat(user, null, m, EnumTargetMode.USER);
      } else {
        addChat(null, null, m, EnumTargetMode.SERVER);
      }
    }
  }
  
  public static void info(String message)
  {
    String m = Color.cl("c") + message;
    if (selectedChat == -1)
    {
      addChat(null, null, m, EnumTargetMode.CHANNEL);
    }
    else if (selectedChat == -2)
    {
      addChat(null, null, m, EnumTargetMode.SERVER);
    }
    else
    {
      TeamSpeakUser user = TeamSpeakController.getInstance().getUser(selectedChat);
      if (user != null) {
        addChat(user, null, m, EnumTargetMode.USER);
      } else {
        addChat(null, null, m, EnumTargetMode.SERVER);
      }
    }
  }
  
  public static void infoAll(String message)
  {
    String m = Color.cl("c") + message;
    addChat(null, null, m, EnumTargetMode.CHANNEL);
    addChat(null, null, m, EnumTargetMode.SERVER);
    if (selectedChat >= 0)
    {
      TeamSpeakUser user = TeamSpeakController.getInstance().getUser(selectedChat);
      if (user != null) {
        addChat(user, null, m, EnumTargetMode.USER);
      } else {
        addChat(null, null, m, EnumTargetMode.SERVER);
      }
    }
  }
  
  public static String getTalkColor(TeamSpeakUser user)
  {
    String talkColor = Color.c(6);
    if (user == null) {
      return talkColor;
    }
    if (user.isChannelCommander()) {
      talkColor = Color.cl("6");
    }
    if (user.isTalking()) {
      if (user.isChannelCommander()) {
        talkColor = Color.cl("e");
      } else {
        talkColor = Color.c(7);
      }
    }
    if (!user.hasClientInputHardware()) {
      talkColor = Color.c(8);
    }
    if (user.hasClientInputMuted()) {
      talkColor = Color.c(8);
    }
    if (!user.hasClientOutputHardware()) {
      talkColor = Color.c(9);
    }
    if (user.hasClientOutputMuted()) {
      talkColor = Color.c(9);
    }
    if (user.isAway()) {
      talkColor = Color.cl("7");
    }
    return talkColor;
  }
  
  public static String getAway(TeamSpeakUser user)
  {
    String away = "";
    if (user == null) {
      return away;
    }
    if ((user.isAway()) && 
      (!user.getAwayMessage().isEmpty())) {
      away = Color.cl("7") + " [" + user.getAwayMessage() + "]";
    }
    return away;
  }
}
