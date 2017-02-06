package de.labystudio.utils;

import de.zockermaus.serverpinger.ServerData;
import java.util.ArrayList;

public class ServerManager
{
  public static ArrayList<ServerData> pendingServers = new ArrayList();
  
  public static ArrayList<ServerData> getPendingServers()
  {
    return pendingServers;
  }
  
  public static void add(ServerData data)
  {
    ServerData remove = null;
    for (ServerData d : getPendingServers()) {
      if ((d != null) && (data != null) && (d.serverName != null) && (data.serverName != null) && (d.serverName == data.serverName)) {
        remove = d;
      }
    }
    if (remove != null) {
      getPendingServers().remove(remove);
    }
    getPendingServers().add(data);
  }
  
  public static boolean contains(String ip)
  {
    for (ServerData d : ) {
      if ((d != null) && (d.serverName != null) && (d.serverName.equals(ip))) {
        return true;
      }
    }
    return false;
  }
  
  public static ServerData get(String ip)
  {
    for (ServerData d : ) {
      if ((d != null) && (d.serverName != null) && (d.serverName.equals(ip))) {
        return d;
      }
    }
    return null;
  }
  
  public static void remove(String ip)
  {
    ServerData rem = null;
    for (ServerData d : getPendingServers()) {
      if ((d != null) && (d.serverName == ip)) {
        rem = d;
      }
    }
    getPendingServers().remove(rem);
  }
}
