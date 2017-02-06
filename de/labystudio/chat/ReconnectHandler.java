package de.labystudio.chat;

import io.netty.channel.socket.nio.NioSocketChannel;

public class ReconnectHandler
  extends Thread
{
  private ClientConnection clientConnection;
  private long lastTry;
  
  public ReconnectHandler(ClientConnection clientConnection)
  {
    this.clientConnection = clientConnection;
    this.lastTry = System.currentTimeMillis();
  }
  
  public void run()
  {
    for (;;)
    {
      if (this.clientConnection.ch.isOpen())
      {
        try
        {
          wait();
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
      }
      else if (this.lastTry + 5000L < System.currentTimeMillis())
      {
        this.lastTry = System.currentTimeMillis();
        this.clientConnection.init();
        this.clientConnection.connect();
      }
    }
  }
}
