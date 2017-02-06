package de.labystudio.chat;

import bcf;
import bcm;
import com.google.common.collect.Queues;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import com.mojang.authlib.exceptions.InvalidCredentialsException;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import de.labystudio.gui.GuiAchievementMod;
import de.labystudio.handling.PacketHandler;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.ModSettings;
import de.labystudio.labymod.Source;
import de.labystudio.packets.EnumConnectionState;
import de.labystudio.packets.Packet;
import de.labystudio.packets.PacketBanned;
import de.labystudio.packets.PacketChatVisibilityChange;
import de.labystudio.packets.PacketDisconnect;
import de.labystudio.packets.PacketEncryptionRequest;
import de.labystudio.packets.PacketEncryptionResponse;
import de.labystudio.packets.PacketHelloPing;
import de.labystudio.packets.PacketHelloPong;
import de.labystudio.packets.PacketKick;
import de.labystudio.packets.PacketLoginComplete;
import de.labystudio.packets.PacketLoginData;
import de.labystudio.packets.PacketLoginFriend;
import de.labystudio.packets.PacketLoginOptions;
import de.labystudio.packets.PacketLoginRequest;
import de.labystudio.packets.PacketLoginTime;
import de.labystudio.packets.PacketLoginVersion;
import de.labystudio.packets.PacketMessage;
import de.labystudio.packets.PacketMojangStatus;
import de.labystudio.packets.PacketPing;
import de.labystudio.packets.PacketPlayAcceptFriendRequest;
import de.labystudio.packets.PacketPlayChangeOptions;
import de.labystudio.packets.PacketPlayDenyFriendRequest;
import de.labystudio.packets.PacketPlayFriendPlayingOn;
import de.labystudio.packets.PacketPlayFriendRemove;
import de.labystudio.packets.PacketPlayFriendStatus;
import de.labystudio.packets.PacketPlayPlayerOnline;
import de.labystudio.packets.PacketPlayRequestAddFriend;
import de.labystudio.packets.PacketPlayRequestAddFriendResponse;
import de.labystudio.packets.PacketPlayRequestRemove;
import de.labystudio.packets.PacketPlayServerStatus;
import de.labystudio.packets.PacketPlayTyping;
import de.labystudio.packets.PacketPong;
import de.labystudio.packets.PacketServerMessage;
import de.labystudio.utils.Color;
import de.labystudio.utils.Debug;
import de.labystudio.utils.LOGO;
import de.labystudio.utils.MojangService;
import de.labystudio.utils.ServiceStatus;
import de.labystudio.utils.Utils;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.net.Proxy;
import java.nio.channels.UnresolvedAddressException;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.crypto.SecretKey;
import ob;
import org.apache.logging.log4j.LogManager;

@ChannelHandler.Sharable
public class ClientConnection
  extends PacketHandler
{
  public static NioEventLoopGroup g;
  public static ExecutorService executor = Executors.newFixedThreadPool(2, new ThreadFactoryBuilder().setNameFormat("LabyMod Helper Thread %d").build());
  private EnumConnectionState state;
  private Client client;
  private Bootstrap b;
  protected NioSocketChannel ch;
  private Queue<Packet> packets;
  private HashMap<String, String> sentFiles;
  private static String capeKey = null;
  public static String chatPrefix = Color.cl("8") + "[" + Color.cl("c") + Color.cl("l") + "Chat" + Color.cl("8") + "] " + Color.cl("7");
  
  public ClientConnection(Client client)
  {
    g = new NioEventLoopGroup(1, new ThreadFactoryBuilder().setNameFormat("LabyMod Netty IO Thread #%d").build());
    this.client = client;
    this.sentFiles = new HashMap();
    this.packets = Queues.newConcurrentLinkedQueue();
    setConnectionState(EnumConnectionState.OFFLINE);
  }
  
  public void init()
  {
    if ((this.ch != null) && (this.ch.isOpen()))
    {
      this.ch.close();
      this.ch = null;
    }
    setIntentionally(false);
    this.state = EnumConnectionState.HELLO;
    this.b = new Bootstrap();
    this.b.group(g);
    this.b.channel(NioSocketChannel.class);
    this.b.handler(new ClientChannelInitializer(this));
    executor.execute(new Runnable()
    {
      public void run()
      {
        try
        {
          if (Debug.server())
          {
            LabyMod.getInstance().chatVisibility = true;
            ClientConnection.this.b.connect(Source.url_serverIP, 13337).syncUninterruptibly();
          }
          else
          {
            ClientConnection.this.b.connect(Source.url_serverIP, Source.url_serverPort).syncUninterruptibly();
          }
        }
        catch (UnresolvedAddressException error)
        {
          ClientConnection.this.setConnectionState(EnumConnectionState.OFFLINE);
          LabyMod.getInstance().lastKickReason = error.getMessage();
          if (LabyMod.getInstance().lastKickReason == null) {
            LabyMod.getInstance().lastKickReason = "Unknown error";
          }
          System.out.println("UnresolvedAddressException: " + error.getMessage());
          error.printStackTrace();
        }
        catch (Throwable throwable)
        {
          ClientConnection.this.setConnectionState(EnumConnectionState.OFFLINE);
          LabyMod.getInstance().lastKickReason = throwable.getMessage();
          if (LabyMod.getInstance().lastKickReason == null) {
            LabyMod.getInstance().lastKickReason = "Unknown error";
          }
          System.out.println("Throwable: " + throwable.getMessage());
          if (throwable.getMessage().contains("no further information")) {
            LabyMod.getInstance().lastKickReason = "The Chat is temporarily offline.";
          }
          throwable.printStackTrace();
        }
      }
    });
  }
  
  public void setIntentionally(boolean status)
  {
    LabyMod.getInstance().intentionally = status;
  }
  
  public void reconnect()
  {
    if (getState() == EnumConnectionState.OFFLINE)
    {
      if (!LabyMod.getInstance().intentionally)
      {
        LogManager.getLogger().info("Reconnecting to server..");
        init();
      }
    }
    else if (getState() != EnumConnectionState.PLAY) {
      LabyMod.getInstance().intentionally = false;
    }
  }
  
  public static String getCapeKey()
  {
    return capeKey;
  }
  
  public Client getClient()
  {
    return this.client;
  }
  
  public void handle(PacketLoginData packet) {}
  
  public EnumConnectionState getState()
  {
    return this.state;
  }
  
  public void handle(PacketHelloPong packet)
  {
    setConnectionState(EnumConnectionState.LOGIN);
    sendPacket(new PacketLoginData(LabyMod.getInstance().getPlayerUUID(), LabyMod.getInstance().getPlayerName(), ConfigManager.settings.motd));
    getClient();sendPacket(new PacketLoginOptions(ConfigManager.settings.showConntectedIP, Client.getOnlineStatus(), getClient().getTimeZone()));
    sendPacket(new PacketLoginVersion(Source.mod_VersionId, Source.mod_VersionName));
  }
  
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
    throws Exception
  {
    sendPacket(new PacketDisconnect(cause.getMessage()));
    if (!(cause instanceof IOException))
    {
      cause.printStackTrace();
      ctx.close();
    }
  }
  
  public void handle(PacketHelloPing packet) {}
  
  public void sendPacket(Packet packet)
  {
    flushPacket(packet);
  }
  
  public boolean isChannelOpen()
  {
    return (this.ch != null) && (this.ch.isOpen());
  }
  
  public void flushQueue()
  {
    if (isChannelOpen()) {
      while (!this.packets.isEmpty())
      {
        Packet p = (Packet)this.packets.poll();
        flushPacket(p);
      }
    }
  }
  
  public void flushPacket(final Packet packet)
  {
    if (this.ch != null) {
      if ((this.ch.isOpen()) && (this.ch.isWritable()) && (getState() != EnumConnectionState.OFFLINE)) {
        if (this.ch.eventLoop().inEventLoop()) {
          this.ch.writeAndFlush(packet).addListeners(new GenericFutureListener[] { ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE });
        } else {
          this.ch.eventLoop().execute(new Runnable()
          {
            public void run()
            {
              ClientConnection.this.ch.writeAndFlush(packet).addListeners(new GenericFutureListener[] { ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE });
            }
          });
        }
      }
    }
  }
  
  public void connect()
  {
    setConnectionState(EnumConnectionState.HELLO);
    sendPacket(new PacketHelloPing(System.currentTimeMillis()));
  }
  
  public void channelActive(ChannelHandlerContext ctx)
    throws Exception
  {
    connect();
  }
  
  public void handle(PacketKick packet)
  {
    LabyMod.getInstance().intentionally = true;
    closeChannel();
    LabyMod.getInstance().lastKickReason = packet.getReason();
    if (LabyMod.getInstance().lastKickReason == null) {
      LabyMod.getInstance().lastKickReason = "Unknown error";
    }
    System.out.println("PacketKick: " + packet.getReason());
    if (ConfigManager.settings.chatAlertType)
    {
      if (ConfigManager.settings.alertsChat) {
        LabyMod.getInstance().displayMessageInChat(chatPrefix + Color.cl("4") + "DISCONNECTED" + Color.cl("7") + ": " + packet.getReason());
      }
    }
    else {
      LabyMod.getInstance().achievementGui.displayBroadcast(BroadcastType.DISCONNECTED, packet.getReason(), EnumAlertType.CHAT);
    }
  }
  
  public void closeChannel()
  {
    try
    {
      this.ch.close().await();
      setConnectionState(EnumConnectionState.OFFLINE);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }
  
  public void handle(PacketPlayPlayerOnline packet)
  {
    Iterator<LabyModPlayer> friends = getClient().friends.iterator();
    while (friends.hasNext())
    {
      LabyModPlayer player = (LabyModPlayer)friends.next();
      if (player.getId().toString().equalsIgnoreCase(packet.getPlayer().getId().toString()))
      {
        player.setOnline(packet.getPlayer().getStatus());
        player.updateMotd(packet.getPlayer().getMotd());
        ChatHandler.getHandler().getChat(player).updateFriend(packet.getPlayer());
      }
    }
    if (packet.getPlayer().isOnline()) {
      LabyMod.getInstance().sendMessage("", packet.getPlayer(), Color.cl("a") + "is now online!");
    } else {
      LabyMod.getInstance().sendMessage("", packet.getPlayer(), Color.cl("c") + "is now offline!");
    }
  }
  
  public void handle(PacketLoginComplete packet)
  {
    capeKey = packet.getString();
    setConnectionState(EnumConnectionState.PLAY);
  }
  
  public void handle(PacketDisconnect packet)
  {
    if (getState() != EnumConnectionState.OFFLINE)
    {
      closeChannel();
      LabyMod.getInstance().lastKickReason = packet.getReason();
      if (LabyMod.getInstance().lastKickReason == null) {
        LabyMod.getInstance().lastKickReason = "Unknown error";
      }
      System.out.println("PacketDisconnect: " + packet.getReason());
      if (LabyMod.getInstance().mc.m != null) {
        if (ConfigManager.settings.chatAlertType)
        {
          if (ConfigManager.settings.alertsChat) {
            LabyMod.getInstance().displayMessageInChat(chatPrefix + Color.cl("4") + "DISCONNECTED" + Color.cl("7") + ": " + packet.getReason());
          }
        }
        else {
          LabyMod.getInstance().achievementGui.displayBroadcast(BroadcastType.DISCONNECTED, packet.getReason(), EnumAlertType.CHAT);
        }
      }
      System.out.println("Reason: " + packet.getReason());
      if (packet.getReason().contains("Bad Login")) {
        setIntentionally(true);
      }
    }
  }
  
  protected void setConnectionState(EnumConnectionState newConnectionState)
  {
    if (newConnectionState == EnumConnectionState.OFFLINE)
    {
      if ((getClient() != null) && (getClient().requests != null) && (getClient().friends != null))
      {
        getClient().requests.clear();
        getClient().friends.clear();
        LabyMod.getInstance().mojangStatus.clear();
      }
      if (isChannelOpen()) {
        this.ch.close();
      }
    }
    if (newConnectionState == EnumConnectionState.PLAY) {
      LabyMod.getInstance().lastKickReason = "";
    }
    if ((this.state != newConnectionState) && (Debug.chat())) {
      Logger.getLogger().info("Set connectionstate to " + newConnectionState.name());
    }
    this.state = newConnectionState;
  }
  
  public void handle(PacketPlayRequestAddFriend packet) {}
  
  public void handle(PacketLoginFriend packet)
  {
    for (LabyModPlayer player : packet.getFriends()) {
      if ((ConfigManager.settings.logomode) && (LOGO.isLogo(LabyMod.getInstance().getPlayerName())) && (!LOGO.isLogisch(player.getName())))
      {
        LabyMod.getInstance().client.getClientConnection().sendPacket(new PacketPlayFriendRemove(player));
      }
      else
      {
        getClient().friends.add(player);
        SingleChat chat = ChatHandler.getHandler().getChat(player);
        if (chat != null) {
          chat.updateFriend(player);
        } else {
          ChatHandler.getHandler().createSingleChat(player);
        }
        final LabyModPlayer p = player;
        executor.execute(new Runnable()
        {
          public void run()
          {
            try
            {
              ResultSet r = ChatHandler.getHandler().getConnection().prepareStatement("SELECT * FROM friends WHERE friend_id='" + p.getId().toString() + "'").executeQuery();
              if (r.next())
              {
                p.setNotify(r.getBoolean("showAlerts"));
              }
              else
              {
                p.setNotify(true);
                ChatHandler.getHandler().getConnection().prepareStatement("INSERT INTO friends (friend_id, showAlerts) VALUES ('" + p.getId().toString() + "', true)").executeUpdate();
              }
            }
            catch (SQLException e)
            {
              e.printStackTrace();
            }
          }
        });
      }
    }
  }
  
  public void handle(PacketLoginRequest packet)
  {
    if ((ConfigManager.settings.logomode) && (LOGO.isLogo(LabyMod.getInstance().getPlayerName())))
    {
      for (LabyModPlayerRequester p : packet.getRequests()) {
        if (LOGO.isLogisch(p.getName())) {
          LabyMod.getInstance().client.getClientConnection().sendPacket(new PacketPlayRequestAddFriend(p.getName()));
        } else {
          LabyMod.getInstance().client.getClientConnection().sendPacket(new PacketPlayDenyFriendRequest(p));
        }
      }
    }
    else if (ConfigManager.settings.ignoreRequests)
    {
      for (LabyModPlayerRequester p : packet.getRequests()) {
        LabyMod.getInstance().client.getClientConnection().sendPacket(new PacketPlayDenyFriendRequest(p));
      }
    }
    else
    {
      getClient().requests.addAll(packet.getRequests());
      for (LabyModPlayerRequester p : getClient().requests) {
        LabyMod.getInstance().sendMessage(p.getName(), p, Color.cl("f") + "Wants to be your friend");
      }
    }
  }
  
  public void channelInactive(ChannelHandlerContext ctx)
    throws Exception
  {
    sendPacket(new PacketDisconnect("Timed out!"));
    closeChannel();
  }
  
  public void handle(PacketBanned packet)
  {
    LabyMod.getInstance().chatVisibility = false;
    LabyMod.getInstance().chatChange = true;
    closeChannel();
  }
  
  public void handle(PacketPing packet)
  {
    sendPacket(new PacketPong());
  }
  
  public void handle(PacketPong packet) {}
  
  public void handle(PacketServerMessage packet)
  {
    if (ConfigManager.settings.chatAlertType)
    {
      if (ConfigManager.settings.alertsChat) {
        LabyMod.getInstance().displayMessageInChat(chatPrefix + Color.cl("9") + "Message" + Color.cl("7") + ": " + packet.getMessage());
      }
    }
    else {
      LabyMod.getInstance().achievementGui.displayBroadcast(BroadcastType.MESSAGE, packet.getMessage(), EnumAlertType.CHAT);
    }
  }
  
  public void handle(PacketMessage packet)
  {
    getClient().setTyping(packet.getSender(), false);
    if (isNextDay(ChatHandler.getHandler().getChat(packet.getSender()).getMessages())) {
      ChatHandler.getHandler().getChat(packet.getSender()).addMessage(new TitleChatComponent(LabyMod.getInstance().getPlayerName(), System.currentTimeMillis(), getThisDay()));
    }
    LabyMod.getInstance().sendMessage(Color.cl("a"), packet.getSender(), packet.getMessage());
    ChatHandler.getHandler().getChat(packet.getSender()).addMessage(new MessageChatComponent(packet.getSender().getName(), System.currentTimeMillis(), packet.getMessage()));
    
    ChatHandler.addNewMessageInfo(packet.getSender().getName());
  }
  
  public String getThisDay()
  {
    return "DATE";
  }
  
  SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyy");
  
  public boolean isNextDay(List<MessageChatComponent> messages)
  {
    if (messages.size() == 0) {
      return true;
    }
    if (!this.date.format(new Date(((MessageChatComponent)messages.get(0)).getSentTime())).equals(this.date.format(new Date()))) {
      return true;
    }
    return false;
  }
  
  public void handle(PacketPlayTyping packet)
  {
    if (packet.getPlayer() != null) {
      getClient().setTyping(packet.getPlayer(), !packet.isTyping());
    }
  }
  
  public void handle(PacketPlayRequestAddFriendResponse packet)
  {
    if (packet.isRequestSent())
    {
      for (LabyModPlayer p : getClient().requests) {
        if (p.getName().equalsIgnoreCase(packet.getSearched()))
        {
          if (ConfigManager.settings.chatAlertType)
          {
            if (ConfigManager.settings.alertsChat) {
              LabyMod.getInstance().displayMessageInChat(chatPrefix + Color.cl("e") + packet.getSearched() + " has been added to your contacts!");
            }
          }
          else {
            LabyMod.getInstance().achievementGui.displayBroadcast(BroadcastType.INFO, packet.getSearched() + " has been added to your contacts!", EnumAlertType.CHAT);
          }
          return;
        }
      }
      if (ConfigManager.settings.chatAlertType)
      {
        if (ConfigManager.settings.alertsChat) {
          LabyMod.getInstance().displayMessageInChat(chatPrefix + Color.cl("e") + "A request has been sent to " + packet.getSearched());
        }
      }
      else {
        LabyMod.getInstance().achievementGui.displayBroadcast(BroadcastType.INFO, "A request has been sent to " + packet.getSearched(), EnumAlertType.CHAT);
      }
    }
    else if (ConfigManager.settings.chatAlertType)
    {
      if (ConfigManager.settings.alertsChat) {
        LabyMod.getInstance().displayMessageInChat(chatPrefix + Color.cl("4") + "ERROR" + Color.cl("7") + ": " + packet.getReason());
      }
    }
    else
    {
      LabyMod.getInstance().achievementGui.displayBroadcast(BroadcastType.ERROR, packet.getReason(), EnumAlertType.CHAT);
    }
  }
  
  public void handle(PacketPlayAcceptFriendRequest packet) {}
  
  public void handle(PacketPlayRequestRemove packet)
  {
    List<LabyModPlayerRequester> list = getClient().requests;
    List<LabyModPlayerRequester> remove = new ArrayList();
    Iterator<LabyModPlayerRequester> iter = list.iterator();
    while (iter.hasNext())
    {
      LabyModPlayerRequester requester = (LabyModPlayerRequester)iter.next();
      if (requester.getName().equalsIgnoreCase(packet.getPlayerName())) {
        remove.add(requester);
      }
    }
    list.removeAll(remove);
    getClient().requests = list;
  }
  
  public void handle(PacketPlayDenyFriendRequest packet) {}
  
  public void handle(PacketPlayFriendRemove packet)
  {
    Iterator<LabyModPlayer> iter = getClient().friends.iterator();
    List<LabyModPlayer> a = new ArrayList();
    while (iter.hasNext())
    {
      LabyModPlayer player = (LabyModPlayer)iter.next();
      if (player.getId().equals(packet.getToRemove().getId())) {
        a.add(player);
      }
    }
    getClient().friends.removeAll(a);
  }
  
  public void handle(PacketLoginOptions packet) {}
  
  public void handle(PacketPlayServerStatus packet) {}
  
  public void handle(PacketPlayFriendStatus packet)
  {
    packet.getPlayer().updateServer(packet.getPlayerInfo());
    getClient().updatePlayer(packet.getPlayer());
  }
  
  public void handle(PacketPlayFriendPlayingOn packet)
  {
    if ((ConfigManager.settings.alertsPlayingOn) && 
      (!packet.getGameModeName().replace(" ", "").isEmpty())) {
      if (packet.getGameModeName().contains(".")) {
        LabyMod.getInstance().sendMessage("", packet.getPlayer(), "Is now playing on " + Color.cl("e") + packet.getGameModeName());
      } else {
        LabyMod.getInstance().sendMessage("", packet.getPlayer(), "Is now playing " + Color.cl("e") + packet.getGameModeName());
      }
    }
  }
  
  public void handle(PacketPlayChangeOptions packet) {}
  
  public void handle(PacketLoginTime packet)
  {
    getClient().firstJoined = packet.getDateJoined();
    getClient().lastOnline = packet.getLastOnline();
  }
  
  public void handle(PacketLoginVersion packet)
  {
    if (Source.mod_VersionId < packet.getVersionID())
    {
      LabyMod.getInstance().chatPacketUpdate = true;
      LabyMod.getInstance().latestVersionName = packet.getVersionName();
      LabyMod.getInstance().lastKickReason = ("Please update LabyMod to v" + packet.getVersionName());
    }
  }
  
  public void handle(PacketChatVisibilityChange packet)
  {
    LabyMod.getInstance().chatVisibility = packet.isVisible();
    LabyMod.getInstance().chatChange = true;
  }
  
  public void handle(PacketEncryptionRequest packet)
  {
    SecretKey secret = ob.a();
    String serverId = packet.getServerId();
    PublicKey publicKey = ob.a(packet.getPublicKey());
    String hash = new BigInteger(ob.a(serverId, publicKey, secret)).toString(16);
    if (bcf.z().K().e().getId() == null)
    {
      LabyMod.getInstance().lastKickReason = "Invalid session";
      System.out.println("[LabyMod] No Session, aborting");
      if (ConfigManager.settings.chatAlertType)
      {
        if (ConfigManager.settings.alertsChat) {
          LabyMod.getInstance().displayMessageInChat(chatPrefix + Color.cl("4") + "Error" + Color.cl("7") + ": Invalid Session");
        }
      }
      else {
        LabyMod.getInstance().achievementGui.displayBroadcast(Color.cl("c") + "Error", "Invalid Session", EnumAlertType.CHAT);
      }
      setIntentionally(true);
      return;
    }
    try
    {
      getMinecraftSessionServer().joinServer(bcf.z().K().e(), bcf.z().K().d(), hash);
    }
    catch (AuthenticationUnavailableException e1)
    {
      LabyMod.getInstance().lastKickReason = "Authentication Unavaileable";
      System.out.println("Authentication Unavaileable");
      return;
    }
    catch (InvalidCredentialsException e2)
    {
      LabyMod.getInstance().lastKickReason = "Invalid session";
      System.out.println("Invalid Session");
      return;
    }
    catch (AuthenticationException e3)
    {
      LabyMod.getInstance().lastKickReason = "Login failed";
      System.out.println("Login failed");
      return;
    }
    sendPacket(new PacketEncryptionResponse(secret, publicKey, packet.getVerifyToken()));
  }
  
  private MinecraftSessionService getMinecraftSessionServer()
  {
    return new YggdrasilAuthenticationService(Proxy.NO_PROXY, UUID.randomUUID().toString()).createMinecraftSessionService();
  }
  
  public void handle(PacketEncryptionResponse packet) {}
  
  public void handle(PacketMojangStatus packet)
  {
    ServiceStatus status = new ServiceStatus(packet.getStatus());
    
    String m = status.getColor();
    if (ConfigManager.settings.mojangStatus) {
      if (ConfigManager.settings.mojangStatusChat)
      {
        if (status.getColor().equals("yellow")) {
          m = Color.cl("e") + packet.getMojangService().getName() + Color.cl("7") + " is running slowly";
        }
        if (status.getColor().equals("red")) {
          m = Color.cl("c") + packet.getMojangService().getName() + Color.cl("7") + " is offline";
        }
        if (status.getColor().equals("green")) {
          if ((LabyMod.getInstance().mojangStatus.containsKey(packet.getMojangService().getName())) && (((ServiceStatus)LabyMod.getInstance().mojangStatus.get(packet.getMojangService().getName())).getCreated() / 1000L != System.currentTimeMillis() / 1000L)) {
            m = Color.cl("a") + packet.getMojangService().getName() + Color.cl("7") + " is back online " + Color.cl("c") + "(" + Utils.parseTimeNormal((System.currentTimeMillis() - ((ServiceStatus)LabyMod.getInstance().mojangStatus.get(packet.getMojangService().getName())).getCreated()) / 1000L) + " downtime)";
          } else {
            m = Color.cl("a") + packet.getMojangService().getName() + Color.cl("7") + " is back online";
          }
        }
        LabyMod.getInstance().displayMessageInChat(Color.cl("8") + "[" + Color.cl("5") + Color.cl("l") + "Mojang" + Color.cl("8") + "] " + Color.cl("7") + m);
      }
      else
      {
        if (status.getColor().equals("yellow")) {
          m = Color.cl("e") + "Is running slowly";
        }
        if (status.getColor().equals("red")) {
          m = Color.cl("c") + "Is offline";
        }
        if (status.getColor().equals("green")) {
          if ((LabyMod.getInstance().mojangStatus.containsKey(packet.getMojangService().getName())) && (((ServiceStatus)LabyMod.getInstance().mojangStatus.get(packet.getMojangService().getName())).getCreated() / 1000L != System.currentTimeMillis() / 1000L)) {
            m = Color.cl("a") + "Is back online " + Color.cl("c") + "(" + Utils.parseTimeNormal((System.currentTimeMillis() - ((ServiceStatus)LabyMod.getInstance().mojangStatus.get(packet.getMojangService().getName())).getCreated()) / 1000L) + " downtime)";
          } else {
            m = Color.cl("a") + "Is back online";
          }
        }
        LabyMod.getInstance().achievementGui.displayBroadcast(Color.cl("5") + packet.getMojangService().getName(), m, EnumAlertType.LABYMOD);
      }
    }
    LabyMod.getInstance().mojangStatus.put(packet.getMojangService().getName(), status);
  }
}
