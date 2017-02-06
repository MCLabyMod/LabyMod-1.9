import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.channel.epoll.EpollEventLoopGroup;

final class ma$2
  extends oj<EpollEventLoopGroup>
{
  protected EpollEventLoopGroup a()
  {
    return new EpollEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Epoll Server IO #%d").setDaemon(true).build());
  }
}
