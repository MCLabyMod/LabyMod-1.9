import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import java.util.List;

class ma$5
  extends ChannelInitializer<Channel>
{
  ma$5(ma paramma) {}
  
  protected void initChannel(Channel ☃)
    throws Exception
  {
    ek ☃ = new ek(fg.a);
    ☃.a(new lz(ma.b(this.a), ☃));
    ma.a(this.a).add(☃);
    
    ☃.pipeline().addLast("packet_handler", ☃);
  }
}
