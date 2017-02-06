import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

final class ek$6
  extends ChannelInitializer<Channel>
{
  ek$6(ek paramek) {}
  
  protected void initChannel(Channel ☃)
    throws Exception
  {
    ☃.pipeline().addLast("packet_handler", this.a);
  }
}
