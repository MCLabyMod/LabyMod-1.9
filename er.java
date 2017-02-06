import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class er
  extends MessageToByteEncoder<ByteBuf>
{
  protected void a(ChannelHandlerContext ☃, ByteBuf ☃, ByteBuf ☃)
    throws Exception
  {
    int ☃ = ☃.readableBytes();
    int ☃ = em.a(☃);
    if (☃ > 3) {
      throw new IllegalArgumentException("unable to fit " + ☃ + " into " + 3);
    }
    em ☃ = new em(☃);
    
    ☃.ensureWritable(☃ + ☃);
    
    ☃.b(☃);
    ☃.writeBytes(☃, ☃.readerIndex(), ☃);
  }
}
