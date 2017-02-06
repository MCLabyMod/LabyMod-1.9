import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

class bkz$2$1
  extends SimpleChannelInboundHandler<ByteBuf>
{
  bkz$2$1(bkz.2 this$1) {}
  
  public void channelActive(ChannelHandlerContext p_channelActive_1_)
    throws Exception
  {
    super.channelActive(p_channelActive_1_);
    ByteBuf bytebuf = Unpooled.buffer();
    try
    {
      bytebuf.writeByte(254);
      bytebuf.writeByte(1);
      bytebuf.writeByte(250);
      char[] achar = "MC|PingHost".toCharArray();
      bytebuf.writeShort(achar.length);
      for (char c0 : achar) {
        bytebuf.writeChar(c0);
      }
      bytebuf.writeShort(7 + 2 * this.this$1.val$serveraddress.a().length());
      bytebuf.writeByte(127);
      achar = this.this$1.val$serveraddress.a().toCharArray();
      bytebuf.writeShort(achar.length);
      for (char c1 : achar) {
        bytebuf.writeChar(c1);
      }
      bytebuf.writeInt(this.this$1.val$serveraddress.b());
      p_channelActive_1_.channel().writeAndFlush(bytebuf).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
    }
    finally
    {
      bytebuf.release();
    }
  }
  
  protected void a(ChannelHandlerContext p_channelRead0_1_, ByteBuf p_channelRead0_2_)
    throws Exception
  {
    short short1 = p_channelRead0_2_.readUnsignedByte();
    if (short1 == 255)
    {
      String s = new String(p_channelRead0_2_.readBytes(p_channelRead0_2_.readShort() * 2).array(), Charsets.UTF_16BE);
      String[] astring = (String[])Iterables.toArray(bkz.d().split(s), String.class);
      if ("§1".equals(astring[0]))
      {
        int i = on.a(astring[1], 0);
        String s1 = astring[2];
        String s2 = astring[3];
        int j = on.a(astring[4], -1);
        int k = on.a(astring[5], -1);
        this.this$1.val$server.f = -1;
        this.this$1.val$server.g = s1;
        this.this$1.val$server.d = s2;
        this.this$1.val$server.c = (a.h + "" + j + "" + a.i + "/" + a.h + k);
      }
    }
    p_channelRead0_1_.close();
  }
  
  public void exceptionCaught(ChannelHandlerContext p_exceptionCaught_1_, Throwable p_exceptionCaught_2_)
    throws Exception
  {
    p_exceptionCaught_1_.close();
  }
}
