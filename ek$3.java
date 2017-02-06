import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.channel.local.LocalEventLoopGroup;

final class ek$3
  extends oj<LocalEventLoopGroup>
{
  protected LocalEventLoopGroup a()
  {
    return new LocalEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Local Client IO #%d").setDaemon(true).build());
  }
}
