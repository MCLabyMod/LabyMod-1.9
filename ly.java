import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import java.net.InetSocketAddress;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ly
  extends ChannelInboundHandlerAdapter
{
  private static final Logger a = ;
  private ma b;
  
  public ly(ma ☃)
  {
    this.b = ☃;
  }
  
  public void channelRead(ChannelHandlerContext ☃, Object ☃)
    throws Exception
  {
    ByteBuf ☃ = (ByteBuf)☃;
    
    ☃.markReaderIndex();
    
    boolean ☃ = true;
    try
    {
      if (☃.readUnsignedByte() != 254) {
        return;
      }
      InetSocketAddress ☃ = (InetSocketAddress)☃.channel().remoteAddress();
      MinecraftServer ☃ = this.b.d();
      
      int ☃ = ☃.readableBytes();
      switch (☃)
      {
      case 0: 
        a.debug("Ping: (<1.3.x) from {}:{}", new Object[] { ☃.getAddress(), Integer.valueOf(☃.getPort()) });
        
        String ☃ = String.format("%s§%d§%d", new Object[] { ☃.ai(), Integer.valueOf(☃.H()), Integer.valueOf(☃.I()) });
        a(☃, a(☃));
        
        break;
      case 1: 
        if (☃.readUnsignedByte() != 1) {
          return;
        }
        a.debug("Ping: (1.4-1.5.x) from {}:{}", new Object[] { ☃.getAddress(), Integer.valueOf(☃.getPort()) });
        
        String ☃ = String.format("§1\000%d\000%s\000%s\000%d\000%d", new Object[] { Integer.valueOf(127), ☃.G(), ☃.ai(), Integer.valueOf(☃.H()), Integer.valueOf(☃.I()) });
        a(☃, a(☃));
        
        break;
      default: 
        boolean ☃ = ☃.readUnsignedByte() == 1;
        ☃ &= ☃.readUnsignedByte() == 250;
        ☃ &= "MC|PingHost".equals(new String(☃.readBytes(☃.readShort() * 2).array(), Charsets.UTF_16BE));
        int ☃ = ☃.readUnsignedShort();
        ☃ &= ☃.readUnsignedByte() >= 73;
        ☃ &= 3 + ☃.readBytes(☃.readShort() * 2).array().length + 4 == ☃;
        ☃ &= ☃.readInt() <= 65535;
        ☃ &= ☃.readableBytes() == 0;
        if (!☃) {
          return;
        }
        a.debug("Ping: (1.6) from {}:{}", new Object[] { ☃.getAddress(), Integer.valueOf(☃.getPort()) });
        
        String ☃ = String.format("§1\000%d\000%s\000%s\000%d\000%d", new Object[] { Integer.valueOf(127), ☃.G(), ☃.ai(), Integer.valueOf(☃.H()), Integer.valueOf(☃.I()) });
        ByteBuf ☃ = a(☃);
        try
        {
          a(☃, ☃);
        }
        finally
        {
          ☃.release();
        }
      }
      ☃.release();
      ☃ = false;
    }
    catch (RuntimeException localRuntimeException) {}finally
    {
      if (☃)
      {
        ☃.resetReaderIndex();
        ☃.channel().pipeline().remove("legacy_query");
        ☃.fireChannelRead(☃);
      }
    }
  }
  
  private void a(ChannelHandlerContext ☃, ByteBuf ☃)
  {
    ☃.pipeline().firstContext().writeAndFlush(☃).addListener(ChannelFutureListener.CLOSE);
  }
  
  private ByteBuf a(String ☃)
  {
    ByteBuf ☃ = Unpooled.buffer();
    ☃.writeByte(255);
    
    char[] ☃ = ☃.toCharArray();
    ☃.writeShort(☃.length);
    for (char ☃ : ☃) {
      ☃.writeChar(☃);
    }
    return ☃;
  }
}
