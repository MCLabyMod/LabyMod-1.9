package de.labystudio.handling;

import de.labystudio.chat.Logger;
import de.labystudio.packets.Packet;
import de.labystudio.packets.PacketBuf;
import de.labystudio.packets.Protocol;
import de.labystudio.utils.Debug;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.io.IOException;
import java.util.List;

public class PacketDecoder
  extends ByteToMessageDecoder
{
  protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> objects)
    throws Exception
  {
    PacketBuf packetBuffer = new PacketBuf(byteBuf);
    if (packetBuffer.readableBytes() < 1) {
      return;
    }
    int id = packetBuffer.readVarIntFromBuffer();
    
    Packet packet = Protocol.getProtocol().getPacket(id);
    if ((id != 62) && (id != 63)) {
      if (Debug.chat()) {
        Logger.getLogger().info("[IN] " + id + " " + packet.getClass().getSimpleName());
      }
    }
    packet.read(packetBuffer);
    if (packetBuffer.readableBytes() > 0) {
      throw new IOException("Packet  (" + packet.getClass().getSimpleName() + ") was larger than I expected, found " + packetBuffer.readableBytes() + " bytes extra whilst reading packet " + packet);
    }
    objects.add(packet);
  }
}
