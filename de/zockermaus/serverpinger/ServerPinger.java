package de.zockermaus.serverpinger;

import de.labystudio.utils.ServerManager;

public class ServerPinger
  extends Thread
{
  private ServerData currentData;
  private int failed = 0;
  
  public ServerPinger(String serverName, int port)
  {
    this.currentData = new ServerData();
    this.currentData.port = port;
    this.currentData.serverName = serverName;
  }
  
  public void run()
  {
    if (this.failed == 5) {
      this.failed = 0;
    }
    try
    {
      PingUtils.ServerListPing ping = new PingUtils.ServerListPing();
      ping.setHost1(this.currentData.serverName);
      ping.setPort(this.currentData.port);
      ping.setTimeout(30000);
      PingUtils.StatusResponse response = ping.fetchData();
      this.currentData.maxPlayers = response.getPlayers().getMax();
      this.currentData.players = response.getPlayers().getOnline();
      this.currentData.motd = response.getDescription();
      this.currentData.ms = response.getMs();
      ServerManager.add(this.currentData);
    }
    catch (Exception e)
    {
      this.failed += 1;
    }
  }
  
  public ServerData getCurrentData()
  {
    return this.currentData;
  }
}
