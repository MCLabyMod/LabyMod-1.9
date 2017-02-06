package de.labystudio.handling;

import de.labystudio.labymod.Timings;
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
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public abstract class PacketHandler
  extends SimpleChannelInboundHandler<Object>
{
  protected void channelRead0(ChannelHandlerContext ctx, Object packet)
    throws Exception
  {
    handlePacket((Packet)packet);
  }
  
  private void handlePacket(Packet packet)
  {
    Timings.start("HandlePacket " + packet.getClass().getSimpleName());
    packet.handle(this);
    Timings.stop("HandlePacket " + packet.getClass().getSimpleName());
  }
  
  public abstract void handle(PacketLoginData paramPacketLoginData);
  
  public abstract void handle(PacketHelloPing paramPacketHelloPing);
  
  public abstract void handle(PacketHelloPong paramPacketHelloPong);
  
  public abstract void handle(PacketPlayPlayerOnline paramPacketPlayPlayerOnline);
  
  public abstract void handle(PacketLoginComplete paramPacketLoginComplete);
  
  public abstract void handle(PacketChatVisibilityChange paramPacketChatVisibilityChange);
  
  public abstract void handle(PacketKick paramPacketKick);
  
  public abstract void handle(PacketDisconnect paramPacketDisconnect);
  
  public abstract void handle(PacketPlayRequestAddFriend paramPacketPlayRequestAddFriend);
  
  public abstract void handle(PacketLoginFriend paramPacketLoginFriend);
  
  public abstract void handle(PacketLoginRequest paramPacketLoginRequest);
  
  public abstract void handle(PacketBanned paramPacketBanned);
  
  public abstract void handle(PacketPing paramPacketPing);
  
  public abstract void handle(PacketPong paramPacketPong);
  
  public abstract void handle(PacketServerMessage paramPacketServerMessage);
  
  public abstract void handle(PacketMessage paramPacketMessage);
  
  public abstract void handle(PacketPlayTyping paramPacketPlayTyping);
  
  public abstract void handle(PacketPlayRequestAddFriendResponse paramPacketPlayRequestAddFriendResponse);
  
  public abstract void handle(PacketPlayAcceptFriendRequest paramPacketPlayAcceptFriendRequest);
  
  public abstract void handle(PacketPlayRequestRemove paramPacketPlayRequestRemove);
  
  public abstract void handle(PacketPlayDenyFriendRequest paramPacketPlayDenyFriendRequest);
  
  public abstract void handle(PacketPlayFriendRemove paramPacketPlayFriendRemove);
  
  public abstract void handle(PacketLoginOptions paramPacketLoginOptions);
  
  public abstract void handle(PacketPlayServerStatus paramPacketPlayServerStatus);
  
  public abstract void handle(PacketPlayFriendStatus paramPacketPlayFriendStatus);
  
  public abstract void handle(PacketPlayFriendPlayingOn paramPacketPlayFriendPlayingOn);
  
  public abstract void handle(PacketPlayChangeOptions paramPacketPlayChangeOptions);
  
  public abstract void handle(PacketLoginTime paramPacketLoginTime);
  
  public abstract void handle(PacketLoginVersion paramPacketLoginVersion);
  
  public abstract void handle(PacketEncryptionRequest paramPacketEncryptionRequest);
  
  public abstract void handle(PacketEncryptionResponse paramPacketEncryptionResponse);
  
  public abstract void handle(PacketMojangStatus paramPacketMojangStatus);
}
