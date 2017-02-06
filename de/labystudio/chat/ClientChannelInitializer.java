package de.labystudio.chat;

import de.labystudio.handling.PacketDecoder;
import de.labystudio.handling.PacketEncoder;
import de.labystudio.handling.PacketPrepender;
import de.labystudio.handling.PacketSplitter;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import java.util.concurrent.TimeUnit;

public class ClientChannelInitializer
  extends ChannelInitializer<NioSocketChannel>
{
  private ClientConnection clientConnection;
  
  public ClientChannelInitializer(ClientConnection clientConnection)
  {
    this.clientConnection = clientConnection;
  }
  
  protected void initChannel(NioSocketChannel channel)
    throws Exception
  {
    getClientConnection().ch = channel;
    channel.pipeline().addLast("timeout", new ReadTimeoutHandler(120L, TimeUnit.SECONDS)).addLast("splitter", new PacketPrepender()).addLast("decoder", new PacketDecoder()).addLast("prepender", new PacketSplitter()).addLast("encoder", new PacketEncoder()).addLast(new ChannelHandler[] { getClientConnection() });
  }
  
  public ClientConnection getClientConnection()
  {
    return this.clientConnection;
  }
}
