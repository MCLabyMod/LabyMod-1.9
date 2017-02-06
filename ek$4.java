import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.util.concurrent.GenericFutureListener;

class ek$4
  implements Runnable
{
  ek$4(ek paramek, el paramel1, el paramel2, ff paramff, GenericFutureListener[] paramArrayOfGenericFutureListener) {}
  
  public void run()
  {
    if (this.a != this.b) {
      this.e.a(this.a);
    }
    ChannelFuture ☃ = ek.a(this.e).writeAndFlush(this.c);
    if (this.d != null) {
      ☃.addListeners(this.d);
    }
    ☃.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
  }
}
