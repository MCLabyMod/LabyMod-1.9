package de.labystudio.handling;

import de.labystudio.chat.Logger;
import de.labystudio.packets.Packet;
import de.labystudio.packets.PacketBuf;
import de.labystudio.packets.Protocol;
import de.labystudio.utils.Debug;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder
  extends MessageToByteEncoder<Packet>
{
  protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf)
    throws Exception
  {
    PacketBuf packetBuffer = new PacketBuf(byteBuf);
    int id = Protocol.getProtocol().getPacketId(packet);
    if ((id != 62) && (id != 63)) {
      if (Debug.chat()) {
        Logger.getLogger().info("[OUT] " + id + " " + packet.getClass().getSimpleName());
      }
    }
    packetBuffer.writeVarIntToBuffer(Protocol.getProtocol().getPacketId(packet));
    packet.write(packetBuffer);
  }
}
