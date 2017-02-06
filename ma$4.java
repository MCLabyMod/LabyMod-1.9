import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.ReadTimeoutHandler;
import java.util.List;

class ma$4
  extends ChannelInitializer<Channel>
{
  ma$4(ma paramma) {}
  
  protected void initChannel(Channel ☃)
    throws Exception
  {
    try
    {
      ☃.config().setOption(ChannelOption.TCP_NODELAY, Boolean.valueOf(true));
    }
    catch (ChannelException localChannelException) {}
    ☃.pipeline().addLast("timeout", new ReadTimeoutHandler(30)).addLast("legacy_query", new ly(this.a)).addLast("splitter", new eq()).addLast("decoder", new en(fg.a)).addLast("prepender", new er()).addLast("encoder", new eo(fg.b));
    
    ek ☃ = new ek(fg.a);
    ma.a(this.a).add(☃);
    ☃.pipeline().addLast("packet_handler", ☃);
    ☃.a(new mc(ma.b(this.a), ☃));
  }
}
