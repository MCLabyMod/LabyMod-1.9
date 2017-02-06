package net.minecraft.realms;

import bkw;

public class RealmsServerAddress
{
  private final String host;
  private final int port;
  
  protected RealmsServerAddress(String ☃, int ☃)
  {
    this.host = ☃;
    this.port = ☃;
  }
  
  public String getHost()
  {
    return this.host;
  }
  
  public int getPort()
  {
    return this.port;
  }
  
  public static RealmsServerAddress parseString(String ☃)
  {
    bkw ☃ = bkw.a(☃);
    return new RealmsServerAddress(☃.a(), ☃.b());
  }
}
