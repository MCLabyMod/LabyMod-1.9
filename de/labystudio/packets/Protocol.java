package de.labystudio.packets;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Protocol
{
  private static final Protocol INSTANCE = new Protocol();
  private Map<Class<? extends Packet>, EnumConnectionState> protocol;
  private Map<Integer, Class<? extends Packet>> packets;
  
  public static Protocol getProtocol()
  {
    return INSTANCE;
  }
  
  public Protocol()
  {
    this.protocol = new HashMap();
    this.packets = new HashMap();
    
    register(0, PacketHelloPing.class, EnumConnectionState.HELLO);
    register(1, PacketHelloPong.class, EnumConnectionState.HELLO);
    
    register(2, PacketLoginStart.class, EnumConnectionState.LOGIN);
    register(3, PacketLoginData.class, EnumConnectionState.LOGIN);
    register(4, PacketLoginFriend.class, EnumConnectionState.LOGIN);
    register(5, PacketLoginRequest.class, EnumConnectionState.LOGIN);
    register(6, PacketLoginOptions.class, EnumConnectionState.LOGIN);
    register(7, PacketLoginComplete.class, EnumConnectionState.LOGIN);
    register(8, PacketLoginTime.class, EnumConnectionState.LOGIN);
    register(9, PacketLoginVersion.class, EnumConnectionState.LOGIN);
    register(10, PacketEncryptionRequest.class, EnumConnectionState.LOGIN);
    register(11, PacketEncryptionResponse.class, EnumConnectionState.LOGIN);
    
    register(14, PacketPlayPlayerOnline.class, EnumConnectionState.PLAY);
    register(16, PacketPlayRequestAddFriend.class, EnumConnectionState.PLAY);
    register(17, PacketPlayRequestAddFriendResponse.class, EnumConnectionState.PLAY);
    register(18, PacketPlayRequestRemove.class, EnumConnectionState.PLAY);
    register(19, PacketPlayDenyFriendRequest.class, EnumConnectionState.PLAY);
    register(20, PacketPlayFriendRemove.class, EnumConnectionState.PLAY);
    register(21, PacketPlayChangeOptions.class, EnumConnectionState.PLAY);
    register(22, PacketPlayServerStatus.class, EnumConnectionState.PLAY);
    register(23, PacketPlayFriendStatus.class, EnumConnectionState.PLAY);
    register(24, PacketPlayFriendPlayingOn.class, EnumConnectionState.PLAY);
    register(25, PacketPlayTyping.class, EnumConnectionState.PLAY);
    register(26, PacketMojangStatus.class, EnumConnectionState.PLAY);
    
    register(60, PacketDisconnect.class, EnumConnectionState.ALL);
    register(61, PacketKick.class, EnumConnectionState.ALL);
    register(62, PacketPing.class, EnumConnectionState.ALL);
    register(63, PacketPong.class, EnumConnectionState.ALL);
    register(64, PacketServerMessage.class, EnumConnectionState.ALL);
    register(65, PacketMessage.class, EnumConnectionState.ALL);
    register(66, PacketBanned.class, EnumConnectionState.ALL);
    register(67, PacketChatVisibilityChange.class, EnumConnectionState.ALL);
  }
  
  public Map<Integer, Class<? extends Packet>> getPackets()
  {
    return this.packets;
  }
  
  private final void register(int id, Class<? extends Packet> clazz, EnumConnectionState state)
  {
    try
    {
      clazz.newInstance();
      this.packets.put(Integer.valueOf(id), clazz);
      this.protocol.put(clazz, state);
    }
    catch (Exception e)
    {
      System.out.println("Class " + clazz.getSimpleName() + " does not contain a default Constructor, this might break the game :/");
    }
  }
  
  public Packet getPacket(int id)
    throws IllegalAccessException, InstantiationException
  {
    if (!this.packets.containsKey(Integer.valueOf(id))) {
      throw new RuntimeException("Packet with id " + id + " is not registered.");
    }
    return (Packet)((Class)this.packets.get(Integer.valueOf(id))).newInstance();
  }
  
  public int getPacketId(Packet packet)
  {
    for (Map.Entry<Integer, Class<? extends Packet>> entry : this.packets.entrySet())
    {
      Class<? extends Packet> clazz = (Class)entry.getValue();
      if (clazz.isInstance(packet)) {
        return ((Integer)entry.getKey()).intValue();
      }
    }
    throw new RuntimeException("Packet " + packet + " is not registered.");
  }
}
