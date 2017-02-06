package org.h2.util;

import java.io.IOException;
import java.net.BindException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.security.CipherFactory;

public class NetUtils
{
  private static final int CACHE_MILLIS = 1000;
  private static InetAddress cachedBindAddress;
  private static String cachedLocalAddress;
  private static long cachedLocalAddressTime;
  
  public static Socket createLoopbackSocket(int port, boolean ssl)
    throws IOException
  {
    InetAddress address = getBindAddress();
    if (address == null) {
      address = InetAddress.getLocalHost();
    }
    try
    {
      return createSocket(getHostAddress(address), port, ssl);
    }
    catch (IOException e)
    {
      try
      {
        return createSocket("localhost", port, ssl);
      }
      catch (IOException e2)
      {
        throw e;
      }
    }
  }
  
  private static String getHostAddress(InetAddress address)
  {
    String host = address.getHostAddress();
    if (((address instanceof Inet6Address)) && 
      (host.indexOf(':') >= 0) && (!host.startsWith("["))) {
      host = "[" + host + "]";
    }
    return host;
  }
  
  public static Socket createSocket(String server, int defaultPort, boolean ssl)
    throws IOException
  {
    int port = defaultPort;
    
    int startIndex = server.startsWith("[") ? server.indexOf(']') : 0;
    int idx = server.indexOf(':', startIndex);
    if (idx >= 0)
    {
      port = Integer.decode(server.substring(idx + 1)).intValue();
      server = server.substring(0, idx);
    }
    InetAddress address = InetAddress.getByName(server);
    return createSocket(address, port, ssl);
  }
  
  public static Socket createSocket(InetAddress address, int port, boolean ssl)
    throws IOException
  {
    long start = System.currentTimeMillis();
    for (int i = 0;; i++) {
      try
      {
        if (ssl) {
          return CipherFactory.createSocket(address, port);
        }
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(address, port), SysProperties.SOCKET_CONNECT_TIMEOUT);
        
        return socket;
      }
      catch (IOException e)
      {
        if (System.currentTimeMillis() - start >= SysProperties.SOCKET_CONNECT_TIMEOUT) {
          throw e;
        }
        if (i >= SysProperties.SOCKET_CONNECT_RETRY) {
          throw e;
        }
        try
        {
          long sleep = Math.min(256, i * i);
          Thread.sleep(sleep);
        }
        catch (InterruptedException e2) {}
      }
    }
  }
  
  public static ServerSocket createServerSocket(int port, boolean ssl)
  {
    try
    {
      return createServerSocketTry(port, ssl);
    }
    catch (Exception e) {}
    return createServerSocketTry(port, ssl);
  }
  
  private static InetAddress getBindAddress()
    throws UnknownHostException
  {
    String host = SysProperties.BIND_ADDRESS;
    if ((host == null) || (host.length() == 0)) {
      return null;
    }
    synchronized (NetUtils.class)
    {
      if (cachedBindAddress == null) {
        cachedBindAddress = InetAddress.getByName(host);
      }
    }
    return cachedBindAddress;
  }
  
  private static ServerSocket createServerSocketTry(int port, boolean ssl)
  {
    try
    {
      InetAddress bindAddress = getBindAddress();
      if (ssl) {
        return CipherFactory.createServerSocket(port, bindAddress);
      }
      if (bindAddress == null) {
        return new ServerSocket(port);
      }
      return new ServerSocket(port, 0, bindAddress);
    }
    catch (BindException be)
    {
      throw DbException.get(90061, be, new String[] { "" + port, be.toString() });
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, "port: " + port + " ssl: " + ssl);
    }
  }
  
  public static boolean isLocalAddress(Socket socket)
    throws UnknownHostException
  {
    InetAddress test = socket.getInetAddress();
    if (test.isLoopbackAddress()) {
      return true;
    }
    InetAddress localhost = InetAddress.getLocalHost();
    
    String host = localhost.getHostAddress();
    for (InetAddress addr : InetAddress.getAllByName(host)) {
      if (test.equals(addr)) {
        return true;
      }
    }
    return false;
  }
  
  public static ServerSocket closeSilently(ServerSocket socket)
  {
    if (socket != null) {
      try
      {
        socket.close();
      }
      catch (IOException e) {}
    }
    return null;
  }
  
  public static synchronized String getLocalAddress()
  {
    long now = System.currentTimeMillis();
    if ((cachedLocalAddress != null) && 
      (cachedLocalAddressTime + 1000L > now)) {
      return cachedLocalAddress;
    }
    InetAddress bind = null;
    boolean useLocalhost = false;
    try
    {
      bind = getBindAddress();
      if (bind == null) {
        useLocalhost = true;
      }
    }
    catch (UnknownHostException e) {}
    if (useLocalhost) {
      try
      {
        bind = InetAddress.getLocalHost();
      }
      catch (UnknownHostException e)
      {
        throw DbException.convert(e);
      }
    }
    String address = bind == null ? "localhost" : getHostAddress(bind);
    if (address.equals("127.0.0.1")) {
      address = "localhost";
    }
    cachedLocalAddress = address;
    cachedLocalAddressTime = now;
    return address;
  }
  
  public static String getHostName(String localAddress)
  {
    try
    {
      InetAddress addr = InetAddress.getByName(localAddress);
      return addr.getHostName();
    }
    catch (Exception e) {}
    return "unknown";
  }
}
