import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.Attribute;
import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class en
  extends ByteToMessageDecoder
{
  private static final Logger a = ;
  private static final Marker b = MarkerManager.getMarker("PACKET_RECEIVED", ek.b);
  private final fg c;
  
  public en(fg ☃)
  {
    this.c = ☃;
  }
  
  protected void decode(ChannelHandlerContext ☃, ByteBuf ☃, List<Object> ☃)
    throws Exception
  {
    if (☃.readableBytes() == 0) {
      return;
    }
    em ☃ = new em(☃);
    int ☃ = ☃.g();
    ff<?> ☃ = ((el)☃.channel().attr(ek.c).get()).a(this.c, ☃);
    if (☃ == null) {
      throw new IOException("Bad packet id " + ☃);
    }
    ☃.a(☃);
    if (☃.readableBytes() > 0) {
      throw new IOException("Packet " + ((el)☃.channel().attr(ek.c).get()).a() + "/" + ☃ + " (" + ☃.getClass().getSimpleName() + ") was larger than I expected, found " + ☃.readableBytes() + " bytes extra whilst reading packet " + ☃);
    }
    ☃.add(☃);
    if (a.isDebugEnabled()) {
      a.debug(b, " IN: [{}:{}] {}", new Object[] { ☃.channel().attr(ek.c).get(), Integer.valueOf(☃), ☃.getClass().getName() });
    }
  }
}
