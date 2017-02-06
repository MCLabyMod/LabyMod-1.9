package de.labystudio.chat;

import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.ModSettings;
import de.labystudio.labymod.Timings;
import de.labystudio.packets.EnumConnectionState;
import de.labystudio.packets.PacketDisconnect;
import de.labystudio.packets.PacketLoginOptions.Options;
import de.labystudio.packets.PacketPlayChangeOptions;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

public class Client
  implements Runnable
{
  private Thread primaryThread;
  private ClientConnection clientConnection;
  private boolean running;
  protected List<LabyModPlayer> friends;
  protected List<LabyModPlayerRequester> requests;
  protected long lastOnline;
  protected long firstJoined;
  
  public Client()
  {
    this.clientConnection = new ClientConnection(this);
    this.friends = new ArrayList();
    this.requests = new ArrayList();
  }
  
  public PacketLoginOptions.Options getOptions()
  {
    return new PacketLoginOptions.Options(isShowServer(), getOnlineStatus(), getTimeZone());
  }
  
  public static LabyModPlayer.OnlineStatus getOnlineStatus()
  {
    return LabyModPlayer.OnlineStatus.fromPacketId(ConfigManager.settings.onlineStatus);
  }
  
  public boolean isShowServer()
  {
    return ConfigManager.settings.showConntectedIP;
  }
  
  public void setOnlineStatus(LabyModPlayer.OnlineStatus status)
  {
    ConfigManager.settings.onlineStatus = status.getPacketId();
    getClientConnection().sendPacket(new PacketPlayChangeOptions(isShowServer(), status, getTimeZone()));
  }
  
  public static boolean isBusy()
  {
    return getOnlineStatus() == LabyModPlayer.OnlineStatus.BUSY;
  }
  
  public void init()
  {
    Timings.start("Client Init");
    this.clientConnection.init();
    this.running = true;
    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable()
    {
      public void run()
      {
        if (Client.this.clientConnection.getState() != EnumConnectionState.OFFLINE) {
          Client.this.clientConnection.sendPacket(new PacketDisconnect("Shutdown"));
        }
      }
    }));
    Timings.stop("Client Init");
  }
  
  public void run() {}
  
  public boolean isOnline()
  {
    return this.clientConnection.getState() == EnumConnectionState.PLAY;
  }
  
  public ClientConnection getClientConnection()
  {
    return this.clientConnection;
  }
  
  public List<LabyModPlayer> getFriends()
  {
    return this.friends;
  }
  
  public List<LabyModPlayerRequester> getRequests()
  {
    return this.requests;
  }
  
  public LabyModPlayer build()
  {
    LabyModPlayer a = null;
    LabyMod.getInstance().getClient();a = new LabyModPlayer(LabyMod.getInstance().getPlayerName(), LabyMod.getInstance().getPlayerUUID(), ConfigManager.settings.motd, getOnlineStatus(), "", System.currentTimeMillis(), this.firstJoined, getFriends().size());
    a.updateServer(new ServerInfo(" ", 0));
    return a;
  }
  
  public void reconnect()
  {
    disconnect();
    connect();
  }
  
  public void disconnect()
  {
    if (getClientConnection().getState() != EnumConnectionState.OFFLINE) {
      ClientConnection.executor.execute(new Runnable()
      {
        public void run()
        {
          Client.this.getClientConnection().setConnectionState(EnumConnectionState.OFFLINE);
          if (Client.this.getClientConnection().ch == null)
          {
            LabyMod.getInstance().getClient().requests.clear();
            return;
          }
          Client.this.getClientConnection().ch.writeAndFlush(new PacketDisconnect("Logout")).addListener(new ChannelFutureListener()
          {
            public void operationComplete(ChannelFuture arg0)
              throws Exception
            {
              Client.this.getClientConnection().ch.close();
              Iterator<LabyModPlayer> iter = Client.this.getFriends().iterator();
              while (iter.hasNext()) {
                ((LabyModPlayer)iter.next()).setOnline(LabyModPlayer.OnlineStatus.OFFLINE);
              }
              LabyMod.getInstance().getClient().requests.clear();
              Client.this.clientConnection = new ClientConnection(Client.this);
            }
          });
        }
      });
    }
  }
  
  public void connect()
  {
    if (getClientConnection().getState() != EnumConnectionState.PLAY)
    {
      getClientConnection().init();
      getClientConnection().connect();
    }
  }
  
  public void updatePlayer(LabyModPlayer toUpdate)
  {
    Iterator<LabyModPlayer> iter = getFriends().iterator();
    while (iter.hasNext())
    {
      LabyModPlayer player = (LabyModPlayer)iter.next();
      if (player.getId().equals(toUpdate.getId())) {
        player.set(toUpdate);
      }
    }
  }
  
  public void newAccount()
  {
    this.friends.clear();
    this.requests.clear();
    reconnect();
    ChatHandler.getHandler().newAccount();
  }
  
  public void setNotifecationStatus(LabyModPlayer selectedPlayer, boolean b)
  {
    Iterator<LabyModPlayer> iter = getFriends().iterator();
    while (iter.hasNext())
    {
      LabyModPlayer player = (LabyModPlayer)iter.next();
      if (player.getId().equals(selectedPlayer.getId())) {
        if (b != player.isNotify())
        {
          player.setNotify(b);
          final LabyModPlayer p = selectedPlayer;
          final boolean bb = b;
          ClientConnection.executor.execute(new Runnable()
          {
            public void run()
            {
              try
              {
                ChatHandler.getHandler().getConnection().prepareStatement("UPDATE friends SET showAlerts=" + bb + " WHERE friend_id='" + p.getId().toString() + "'").executeUpdate();
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
  }
  
  public boolean hasNotifications(LabyModPlayer friend)
  {
    Iterator<LabyModPlayer> iter = getFriends().iterator();
    while (iter.hasNext())
    {
      LabyModPlayer player = (LabyModPlayer)iter.next();
      if (player.getId().equals(friend.getId())) {
        return player.isNotify();
      }
    }
    return false;
  }
  
  public void setTyping(LabyModPlayer player, boolean typing)
  {
    Iterator<LabyModPlayer> iter = getFriends().iterator();
    while (iter.hasNext())
    {
      LabyModPlayer p = (LabyModPlayer)iter.next();
      if (p.getId().equals(player.getId())) {
        p.updateTyping(typing);
      }
    }
  }
  
  public boolean isTyping(LabyModPlayer player)
  {
    Iterator<LabyModPlayer> iter = getFriends().iterator();
    while (iter.hasNext())
    {
      LabyModPlayer p = (LabyModPlayer)iter.next();
      if (p.getId().equals(player.getId())) {
        return p.isTyping();
      }
    }
    return false;
  }
  
  public TimeZone getTimeZone()
  {
    return Calendar.getInstance().getTimeZone();
  }
}
