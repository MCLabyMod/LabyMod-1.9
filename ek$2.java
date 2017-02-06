import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.channel.epoll.EpollEventLoopGroup;

final class ek$2
  extends oj<EpollEventLoopGroup>
{
  protected EpollEventLoopGroup a()
  {
    return new EpollEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Epoll Client IO #%d").setDaemon(true).build());
  }
}
