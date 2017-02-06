import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.Attribute;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class eo
  extends MessageToByteEncoder<ff<?>>
{
  private static final Logger a = ;
  private static final Marker b = MarkerManager.getMarker("PACKET_SENT", ek.b);
  private final fg c;
  
  public eo(fg ☃)
  {
    this.c = ☃;
  }
  
  protected void a(ChannelHandlerContext ☃, ff<?> ☃, ByteBuf ☃)
    throws Exception
  {
    Integer ☃ = ((el)☃.channel().attr(ek.c).get()).a(this.c, ☃);
    if (a.isDebugEnabled()) {
      a.debug(b, "OUT: [{}:{}] {}", new Object[] { ☃.channel().attr(ek.c).get(), ☃, ☃.getClass().getName() });
    }
    if (☃ == null) {
      throw new IOException("Can't serialize unregistered packet");
    }
    em ☃ = new em(☃);
    ☃.b(☃.intValue());
    try
    {
      ☃.b(☃);
    }
    catch (Throwable ☃)
    {
      a.error(☃);
    }
  }
}
