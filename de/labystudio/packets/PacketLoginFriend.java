package de.labystudio.packets;

import de.labystudio.chat.LabyModPlayer;
import de.labystudio.handling.PacketHandler;
import java.util.ArrayList;
import java.util.List;

public class PacketLoginFriend
  extends Packet
{
  private List<LabyModPlayer> friends;
  
  public PacketLoginFriend(List<LabyModPlayer> friends)
  {
    this.friends = friends;
  }
  
  public PacketLoginFriend() {}
  
  public void read(PacketBuf buf)
  {
    List<LabyModPlayer> players = new ArrayList();
    int a = buf.readInt();
    for (int i = 0; i < a; i++) {
      players.add(buf.readPlayer());
    }
    this.friends = new ArrayList();
    this.friends.addAll(players);
  }
  
  public void write(PacketBuf buf)
  {
    buf.writeInt(getFriends().size());
    for (int i = 0; i < getFriends().size(); i++)
    {
      LabyModPlayer p = (LabyModPlayer)getFriends().get(i);
      buf.writePlayer(p);
    }
  }
  
  public void handle(PacketHandler packetHandler)
  {
    packetHandler.handle(this);
  }
  
  public List<LabyModPlayer> getFriends()
  {
    return this.friends;
  }
}
