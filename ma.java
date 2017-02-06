import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.local.LocalAddress;
import io.netty.channel.local.LocalEventLoopGroup;
import io.netty.channel.local.LocalServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ma
{
  private static final Logger e = ;
  public static final oj<NioEventLoopGroup> a = new oj()
  {
    protected NioEventLoopGroup a()
    {
      return new NioEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Server IO #%d").setDaemon(true).build());
    }
  };
  public static final oj<EpollEventLoopGroup> b = new oj()
  {
    protected EpollEventLoopGroup a()
    {
      return new EpollEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Epoll Server IO #%d").setDaemon(true).build());
    }
  };
  public static final oj<LocalEventLoopGroup> c = new oj()
  {
    protected LocalEventLoopGroup a()
    {
      return new LocalEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Local Server IO #%d").setDaemon(true).build());
    }
  };
  private final MinecraftServer f;
  public volatile boolean d;
  private final List<ChannelFuture> g = Collections.synchronizedList(Lists.newArrayList());
  private final List<ek> h = Collections.synchronizedList(Lists.newArrayList());
  
  public ma(MinecraftServer ☃)
  {
    this.f = ☃;
    this.d = true;
  }
  
  public void a(InetAddress ☃, int ☃)
    throws IOException
  {
    synchronized (this.g)
    {
      Class<? extends ServerSocketChannel> ☃;
      oj<? extends EventLoopGroup> ☃;
      if ((Epoll.isAvailable()) && (this.f.ae()))
      {
        Class<? extends ServerSocketChannel> ☃ = EpollServerSocketChannel.class;
        oj<? extends EventLoopGroup> ☃ = b;
        e.info("Using epoll channel type");
      }
      else
      {
        ☃ = NioServerSocketChannel.class;
        ☃ = a;
        e.info("Using default channel type");
      }
      this.g.add(((ServerBootstrap)((ServerBootstrap)new ServerBootstrap().channel(☃)).childHandler(new ChannelInitializer()
      {
        protected void initChannel(Channel ☃)
          throws Exception
        {
          try
          {
            ☃.config().setOption(ChannelOption.TCP_NODELAY, Boolean.valueOf(true));
          }
          catch (ChannelException localChannelException) {}
          ☃.pipeline().addLast("timeout", new ReadTimeoutHandler(30)).addLast("legacy_query", new ly(ma.this)).addLast("splitter", new eq()).addLast("decoder", new en(fg.a)).addLast("prepender", new er()).addLast("encoder", new eo(fg.b));
          
          ek ☃ = new ek(fg.a);
          ma.a(ma.this).add(☃);
          ☃.pipeline().addLast("packet_handler", ☃);
          ☃.a(new mc(ma.b(ma.this), ☃));
        }
      }).group((EventLoopGroup)☃.c()).localAddress(☃, ☃)).bind().syncUninterruptibly());
    }
  }
  
  public SocketAddress a()
  {
    ChannelFuture ☃;
    synchronized (this.g)
    {
      ☃ = ((ServerBootstrap)((ServerBootstrap)new ServerBootstrap().channel(LocalServerChannel.class)).childHandler(new ChannelInitializer()
      {
        protected void initChannel(Channel ☃)
          throws Exception
        {
          ek ☃ = new ek(fg.a);
          ☃.a(new lz(ma.b(ma.this), ☃));
          ma.a(ma.this).add(☃);
          
          ☃.pipeline().addLast("packet_handler", ☃);
        }
      }).group((EventLoopGroup)a.c()).localAddress(LocalAddress.ANY)).bind().syncUninterruptibly();
      
      this.g.add(☃);
    }
    return ☃.channel().localAddress();
  }
  
  public void b()
  {
    this.d = false;
    for (ChannelFuture ☃ : this.g) {
      try
      {
        ☃.channel().close().sync();
      }
      catch (InterruptedException ☃)
      {
        e.error("Interrupted whilst closing channel");
      }
    }
  }
  
  public void c()
  {
    synchronized (this.h)
    {
      Iterator<ek> ☃ = this.h.iterator();
      while (☃.hasNext())
      {
        final ek ☃ = (ek)☃.next();
        if (!☃.h()) {
          if (☃.g())
          {
            try
            {
              ☃.a();
            }
            catch (Exception ☃)
            {
              if (☃.c())
              {
                b ☃ = b.a(☃, "Ticking memory connection");
                c ☃ = ☃.a("Ticking connection");
                
                ☃.a("Connection", new Callable()
                {
                  public String a()
                    throws Exception
                  {
                    return ☃.toString();
                  }
                });
                throw new e(☃);
              }
              e.warn("Failed to handle packet for " + ☃.b(), ☃);
              final fa ☃ = new fa("Internal server error");
              ☃.a(new gj(☃), new GenericFutureListener()
              {
                public void operationComplete(Future<? super Void> ☃)
                  throws Exception
                {
                  ☃.a(☃);
                }
              }, new GenericFutureListener[0]);
              
              ☃.k();
            }
          }
          else
          {
            ☃.remove();
            ☃.l();
          }
        }
      }
    }
  }
  
  public MinecraftServer d()
  {
    return this.f;
  }
}
