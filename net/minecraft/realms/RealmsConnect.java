package net.minecraft.realms;

import bcf;
import bch;
import bcm;
import bkr;
import ek;
import el;
import fb;
import java.net.InetAddress;
import java.net.UnknownHostException;
import jj;
import js;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RealmsConnect
{
  private static final Logger LOGGER = ;
  private final RealmsScreen onlineScreen;
  private volatile boolean aborted = false;
  private ek connection;
  
  public RealmsConnect(RealmsScreen ☃)
  {
    this.onlineScreen = ☃;
  }
  
  public void connect(final String ☃, final int ☃)
  {
    Realms.setConnectedToRealms(true);
    
    new Thread("Realms-connect-task")
    {
      public void run()
      {
        InetAddress ☃ = null;
        try
        {
          ☃ = InetAddress.getByName(☃);
          if (RealmsConnect.this.aborted) {
            return;
          }
          RealmsConnect.this.connection = ek.a(☃, ☃, bcf.z().u.f());
          if (RealmsConnect.this.aborted) {
            return;
          }
          RealmsConnect.this.connection.a(new bkr(RealmsConnect.this.connection, bcf.z(), RealmsConnect.this.onlineScreen.getProxy()));
          if (RealmsConnect.this.aborted) {
            return;
          }
          RealmsConnect.this.connection.a(new jj(107, ☃, ☃, el.d));
          if (RealmsConnect.this.aborted) {
            return;
          }
          RealmsConnect.this.connection.a(new js(bcf.z().K().e()));
        }
        catch (UnknownHostException ☃)
        {
          Realms.clearResourcePack();
          if (RealmsConnect.this.aborted) {
            return;
          }
          RealmsConnect.LOGGER.error("Couldn't connect to world", ☃);
          Realms.setScreen(new DisconnectedRealmsScreen(RealmsConnect.this.onlineScreen, "connect.failed", new fb("disconnect.genericReason", new Object[] { "Unknown host '" + ☃ + "'" })));
        }
        catch (Exception ☃)
        {
          Realms.clearResourcePack();
          if (RealmsConnect.this.aborted) {
            return;
          }
          RealmsConnect.LOGGER.error("Couldn't connect to world", ☃);
          String ☃ = ☃.toString();
          if (☃ != null)
          {
            String ☃ = ☃.toString() + ":" + ☃;
            ☃ = ☃.replaceAll(☃, "");
          }
          Realms.setScreen(new DisconnectedRealmsScreen(RealmsConnect.this.onlineScreen, "connect.failed", new fb("disconnect.genericReason", new Object[] { ☃ })));
        }
      }
    }.start();
  }
  
  public void abort()
  {
    this.aborted = true;
    if ((this.connection != null) && 
      (this.connection.g()))
    {
      this.connection.a(new fb("disconnect.genericReason", new Object[0]));
      this.connection.l();
    }
  }
  
  public void tick()
  {
    if (this.connection != null) {
      if (this.connection.g()) {
        this.connection.a();
      } else {
        this.connection.l();
      }
    }
  }
}
