package de.labystudio.chat;

import bcf;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.ModSettings;
import de.labystudio.labymod.Source;
import de.labystudio.labymod.Timings;
import de.labystudio.packets.PacketPlayServerStatus;
import de.labystudio.packets.PacketPlayTyping;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.engine.Database;

public class ChatHandler
{
  private Database database;
  private Connection connection;
  private List<SingleChat> chats;
  private static ChatHandler instance;
  
  public static ChatHandler getHandler()
  {
    return instance;
  }
  
  public static boolean getServerStatus()
  {
    return false;
  }
  
  public ChatHandler()
  {
    Timings.start("ChatHandler");
    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          ChatHandler.this.connection.close();
        }
        catch (SQLException e)
        {
          e.printStackTrace();
        }
      }
    }));
    instance = this;
    this.chats = new ArrayList();
    Timings.stop("ChatHandler");
  }
  
  public SingleChat getChat(String player)
  {
    for (SingleChat chat : this.chats) {
      if (chat.getFriend().getName().equalsIgnoreCase(player)) {
        return chat;
      }
    }
    return null;
  }
  
  public SingleChat getChat(LabyModPlayer player)
  {
    for (SingleChat chat : this.chats)
    {
      if (chat.getFriend().getId().equals(player.getId())) {
        return chat;
      }
      if (chat.getFriend().getName().equalsIgnoreCase(player.getName())) {
        return chat;
      }
    }
    return createSingleChat(player);
  }
  
  public SingleChat getOnlyChat(LabyModPlayer player)
  {
    for (SingleChat chat : this.chats)
    {
      if (chat.getFriend().getId().equals(player.getId())) {
        return chat;
      }
      if (chat.getFriend().getName().equalsIgnoreCase(player.getName())) {
        return chat;
      }
    }
    return null;
  }
  
  public static LabyModPlayer getInfo(String name)
  {
    if (name.equalsIgnoreCase(LabyMod.getInstance().getPlayerName()))
    {
      LabyMod.getInstance().getClient();return new LabyModPlayer(LabyMod.getInstance().getPlayerName(), LabyMod.getInstance().getPlayerUUID(), ConfigManager.settings.motd, Client.getOnlineStatus());
    }
    List<LabyModPlayer> a = new ArrayList();
    a.addAll(LabyMod.getInstance().client.friends);
    for (LabyModPlayer p : a) {
      if (p.getName().equalsIgnoreCase(name)) {
        return p;
      }
    }
    return null;
  }
  
  public static List<LabyModPlayer> getMyFriends()
  {
    List<LabyModPlayer> list = new ArrayList();
    list.addAll(LabyMod.getInstance().client.requests);
    
    List<LabyModPlayer> f = new ArrayList();
    f.addAll(LabyMod.getInstance().client.friends);
    if (ConfigManager.settings.showSettingsFriend == 3)
    {
      list.addAll(f);
      Collections.sort(list, new Comparator()
      {
        public int compare(LabyModPlayer a, LabyModPlayer b)
        {
          return (int)(a.getLastMessage() - b.getLastMessage());
        }
      });
    }
    else
    {
      for (LabyModPlayer p : f) {
        if (p.isOnline()) {
          list.add(p);
        }
      }
      for (LabyModPlayer p : f) {
        if (!p.isOnline()) {
          list.add(p);
        }
      }
    }
    return list;
  }
  
  public static File getLogFile(int i)
  {
    String multiple = "";
    if (i > 0) {
      multiple = "_" + i;
    }
    File file = new File(Source.file_Chatlog + "/" + LabyMod.getInstance().getPlayerName() + "/chatlog" + multiple);
    if (!file.exists()) {
      file.getParentFile().mkdirs();
    }
    return file;
  }
  
  public void shutdown()
  {
    if (this.connection == null) {
      return;
    }
    try
    {
      this.connection.close();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
  
  public Connection getConnection()
  {
    try
    {
      if ((this.connection == null) || (this.connection.isClosed())) {
        initConnection();
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return this.connection;
  }
  
  public void initConnection()
  {
    for (int amount = 0; amount <= 10; amount++) {
      try
      {
        Class.forName("org.h2.Driver").newInstance();
        this.connection = DriverManager.getConnection("jdbc:h2:" + getLogFile(amount).getAbsolutePath());
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
  
  public void initDatabase()
  {
    Timings.start("Chat initDatabase");
    try
    {
      initConnection();
      if (this.connection == null)
      {
        File configFile = new File(Source.file_Chatlog);
        if (configFile.exists()) {
          configFile.delete();
        }
        initConnection();
      }
      if ((this.connection == null) || (this.connection.isClosed())) {
        return;
      }
      this.connection.prepareStatement("CREATE TABLE IF NOT EXISTS friends (id INT AUTO_INCREMENT PRIMARY KEY, friend_id VARCHAR(60), showAlerts BOOLEAN)").executeUpdate();
      
      this.connection.prepareStatement("CREATE TABLE IF NOT EXISTS single_chats (id INT AUTO_INCREMENT PRIMARY KEY, friend_name VARCHAR(16), friend_uuid VARCHAR(60))").executeUpdate();
      
      this.connection.prepareStatement("CREATE TABLE IF NOT EXISTS single_chat_messages (id INT AUTO_INCREMENT PRIMARY KEY, single_chats_id INT, sender VARCHAR(20), sender_message VARCHAR(200), sent_time LONG)").executeUpdate();
      initChatlogs();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    Timings.stop("Chat initDatabase");
  }
  
  public void updateChats(LabyModPlayer player)
  {
    for (int i = 0; i < this.chats.size(); i++)
    {
      SingleChat chat = (SingleChat)this.chats.get(i);
      if (chat.getFriend().getName().equalsIgnoreCase(player.getName())) {
        chat.updateFriend(player);
      }
    }
  }
  
  public void initChatlogs()
  {
    int i = 0;
    try
    {
      ResultSet r = this.connection.prepareStatement("SELECT * FROM single_chats").executeQuery();
      while (r.next())
      {
        SingleChat chat = new SingleChat(r.getInt("id"), new LabyModPlayer(r.getString("friend_name"), UUID.fromString(r.getString("friend_uuid")), "* Offline *", LabyModPlayer.OnlineStatus.OFFLINE), loadChatlog(r.getInt("id")));
        this.chats.add(chat);
        i++;
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    LogManager.getLogger().info("Loaded {} Chats!", new Object[] { Integer.valueOf(i) });
  }
  
  public SingleChat createSingleChat(LabyModPlayer friend)
  {
    if (getOnlyChat(friend) != null) {
      return getOnlyChat(friend);
    }
    try
    {
      if (this.connection == null) {
        LogManager.getLogger().error("FileSQL Connection is NULL");
      }
      this.connection.prepareStatement("INSERT INTO single_chats (friend_name, friend_uuid) VALUES ('" + friend.getName() + "', '" + friend.getId().toString() + "')").executeUpdate();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    int id = this.chats.size();
    try
    {
      ResultSet idr = this.connection.prepareStatement("SELECT id FROM single_chats WHERE friend_uuid='" + friend.getId().toString() + "'").executeQuery();
      if (idr.next()) {
        id = idr.getInt("id");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    SingleChat chat = new SingleChat(id, friend, new ArrayList());
    this.chats.add(chat);
    
    return chat;
  }
  
  public List<MessageChatComponent> loadChatlog(int single_chat_id)
    throws SQLException
  {
    List<MessageChatComponent> logos = new ArrayList();
    ResultSet r = this.connection.prepareStatement("SELECT * FROM single_chat_messages WHERE single_chats_id=" + single_chat_id + " ORDER BY sent_time DESC LIMIT 100").executeQuery();
    while (r.next()) {
      if ((r.getString("sender_message").startsWith("<title>")) && (r.getString("sender_message").endsWith("</title>")))
      {
        TitleChatComponent comp = new TitleChatComponent(r.getString("sender"), r.getLong("sent_time"), r.getString("sender_message").replace("<title>", "").replace("</title>", ""));
        logos.add(comp);
      }
      else
      {
        MessageChatComponent comp = new MessageChatComponent(r.getString("sender"), r.getLong("sent_time"), r.getString("sender_message"));
        logos.add(comp);
      }
    }
    return logos;
  }
  
  static void addNewMessageInfo(String sender)
  {
    int newMessages = 0;
    for (LabyModPlayer p : LabyMod.getInstance().client.getFriends()) {
      if (p.getName().equals(sender))
      {
        p.messages += 1;
        p.setLastMessage(System.currentTimeMillis());
      }
    }
  }
  
  public static void setAFK(boolean isAFK)
  {
    if (LabyMod.getInstance().getClient() == null) {
      return;
    }
    if (isAFK)
    {
      LabyMod.getInstance().getClient();
      if (Client.getOnlineStatus() == LabyModPlayer.OnlineStatus.ONLINE)
      {
        LabyMod.getInstance().getClient().setOnlineStatus(LabyModPlayer.OnlineStatus.AWAY);
        playerStatus = 1; return;
      }
    }
    LabyMod.getInstance().getClient();
    if (Client.getOnlineStatus() == LabyModPlayer.OnlineStatus.AWAY)
    {
      LabyMod.getInstance().getClient().setOnlineStatus(LabyModPlayer.OnlineStatus.ONLINE);
      playerStatus = 0;
    }
  }
  
  public static int playerStatus = 0;
  
  public static void setStatus(int i)
  {
    if (playerStatus != i)
    {
      playerStatus = i;
      LabyMod.getInstance().getClient().setOnlineStatus(LabyModPlayer.OnlineStatus.fromPacketId(i));
    }
  }
  
  public static void updateGameMode(String lobby)
  {
    if (lobby.isEmpty()) {
      lobby = null;
    }
    if (!LabyMod.getInstance().gameMode.equals(lobby)) {
      if ((lobby == null) && (!LabyMod.getInstance().gameMode.isEmpty()))
      {
        LabyMod.getInstance().gameMode = "";
        LabyMod.getInstance().client.getClientConnection().sendPacket(new PacketPlayServerStatus("", 0, null));
      }
      else
      {
        LabyMod.getInstance().gameMode = lobby;
        LabyMod.getInstance().client.getClientConnection().sendPacket(new PacketPlayServerStatus(LabyMod.getInstance().ip, LabyMod.getInstance().port, LabyMod.getInstance().gameMode));
        LabyMod.getInstance().gommeHDAutoJoin = false;
      }
    }
    if (LabyMod.getInstance().gameMode == null) {
      LabyMod.getInstance().gameMode = "";
    }
  }
  
  public void newAccount()
  {
    this.chats.clear();
    shutdown();
    initConnection();
    initDatabase();
  }
  
  public static boolean isTyping = false;
  public static ArrayList<LabyModPlayer> typingPartner = new ArrayList();
  
  public static void resetTyping()
  {
    if (isTyping)
    {
      isTyping = false;
      for (LabyModPlayer partner : typingPartner) {
        LabyMod.getInstance().getClient().getClientConnection().sendPacket(new PacketPlayTyping(LabyMod.getInstance().client.build(), partner, true));
      }
    }
  }
  
  public static void updateIsWriting(LabyModPlayer selectedPlayer, String textField)
  {
    if ((!isTyping) && (!textField.replace(" ", "").isEmpty()) && (selectedPlayer != null))
    {
      isTyping = true;
      typingPartner.add(selectedPlayer);
      LabyMod.getInstance().getClient().getClientConnection().sendPacket(new PacketPlayTyping(LabyMod.getInstance().client.build(), selectedPlayer, false));
    }
    if (((isTyping) && (textField.replace(" ", "").isEmpty())) || (selectedPlayer == null) || (LabyMod.getInstance().mc.m == null))
    {
      isTyping = false;
      resetTyping();
    }
  }
}
