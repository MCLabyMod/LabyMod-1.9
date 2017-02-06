import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import javax.crypto.Cipher;

public class eh
  extends MessageToByteEncoder<ByteBuf>
{
  private final ef a;
  
  public eh(Cipher ☃)
  {
    this.a = new ef(☃);
  }
  
  protected void a(ChannelHandlerContext ☃, ByteBuf ☃, ByteBuf ☃)
    throws Exception
  {
    this.a.a(☃, ☃);
  }
}
