import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;
import javax.crypto.Cipher;

public class eg
  extends MessageToMessageDecoder<ByteBuf>
{
  private final ef a;
  
  public eg(Cipher ☃)
  {
    this.a = new ef(☃);
  }
  
  protected void a(ChannelHandlerContext ☃, ByteBuf ☃, List<Object> ☃)
    throws Exception
  {
    ☃.add(this.a.a(☃, ☃));
  }
}
