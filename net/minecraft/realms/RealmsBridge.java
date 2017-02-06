package net.minecraft.realms;

import bcf;
import bdz;
import bfb;
import java.lang.reflect.Constructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RealmsBridge
  extends RealmsScreen
{
  private static final Logger LOGGER = ;
  private bfb previousScreen;
  
  public void switchToRealms(bfb ☃)
  {
    this.previousScreen = ☃;
    try
    {
      Class<?> ☃ = Class.forName("com.mojang.realmsclient.RealmsMainScreen");
      Constructor<?> ☃ = ☃.getDeclaredConstructor(new Class[] { RealmsScreen.class });
      ☃.setAccessible(true);
      Object ☃ = ☃.newInstance(new Object[] { this });
      bcf.z().a(((RealmsScreen)☃).getProxy());
    }
    catch (ClassNotFoundException ☃)
    {
      LOGGER.error("Realms module missing");
    }
    catch (Exception ☃)
    {
      LOGGER.error("Failed to load Realms module", ☃);
    }
  }
  
  public bdz getNotificationScreen(bfb ☃)
  {
    try
    {
      this.previousScreen = ☃;
      Class<?> ☃ = Class.forName("com.mojang.realmsclient.gui.screens.RealmsNotificationsScreen");
      Constructor<?> ☃ = ☃.getDeclaredConstructor(new Class[] { RealmsScreen.class });
      ☃.setAccessible(true);
      Object ☃ = ☃.newInstance(new Object[] { this });
      return ((RealmsScreen)☃).getProxy();
    }
    catch (ClassNotFoundException ☃)
    {
      LOGGER.error("Realms module missing");
    }
    catch (Exception ☃)
    {
      LOGGER.error("Failed to load Realms module", ☃);
    }
    return null;
  }
  
  public void init()
  {
    bcf.z().a(this.previousScreen);
  }
}
