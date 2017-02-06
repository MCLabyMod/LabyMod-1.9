import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.ReadTimeoutHandler;

final class ek$5
  extends ChannelInitializer<Channel>
{
  ek$5(ek paramek) {}
  
  protected void initChannel(Channel ☃)
    throws Exception
  {
    try
    {
      ☃.config().setOption(ChannelOption.TCP_NODELAY, Boolean.valueOf(true));
    }
    catch (ChannelException localChannelException) {}
    ☃.pipeline().addLast("timeout", new ReadTimeoutHandler(30)).addLast("splitter", new eq()).addLast("decoder", new en(fg.b)).addLast("prepender", new er()).addLast("encoder", new eo(fg.a)).addLast("packet_handler", this.a);
  }
}
