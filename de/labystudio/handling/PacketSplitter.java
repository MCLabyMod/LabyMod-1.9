package de.labystudio.handling;

import de.labystudio.packets.PacketBuf;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketSplitter
  extends MessageToByteEncoder<ByteBuf>
{
  protected void encode(ChannelHandlerContext ctx, ByteBuf buffer, ByteBuf byteBuf)
  {
    int var4 = buffer.readableBytes();
    int var5 = PacketBuf.getVarIntSize(var4);
    if (var5 > 3) {
      throw new IllegalArgumentException("unable to fit " + var4 + " into " + 3);
    }
    PacketBuf packetBuffer = new PacketBuf(byteBuf);
    packetBuffer.ensureWritable(var5 + var4);
    packetBuffer.writeVarIntToBuffer(var4);
    packetBuffer.writeBytes(buffer, buffer.readerIndex(), var4);
  }
}
