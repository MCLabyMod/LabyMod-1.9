package de.labystudio.account;

import bcf;
import bcm;
import bcm.a;
import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.HttpAuthenticationService;
import com.mojang.authlib.UserAuthentication;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import de.labystudio.chat.Client;
import de.labystudio.labymod.LabyMod;
import java.lang.reflect.Field;
import java.net.Proxy;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccountManager
{
  private static bcm tempSession = null;
  public static String accountManagerUsername = bcf.z().K().e().getName();
  public static String accountManagerPassword = "";
  
  public static String login(String username, String password)
  {
    try
    {
      tempSession = authenticate(username, password);
    }
    catch (AuthenticationException e)
    {
      if (password.startsWith("@")) {
        tempSession = new bcm(username, password.replace("@", "").replace("-", ""), "", bcm.a.b.toString());
      } else {
        return e.getMessage();
      }
    }
    if (tempSession != null) {
      return setSession(tempSession);
    }
    return "Error?";
  }
  
  public static String setSession(bcm tempSession)
  {
    try
    {
      bcf mc = bcf.z();
      Field f = mc.getClass().getDeclaredField("ag");
      f.setAccessible(true);
      f.set(mc, tempSession);
    }
    catch (Exception ex)
    {
      try
      {
        bcf mc = bcf.z();
        Field f = mc.getClass().getDeclaredField("session");
        f.setAccessible(true);
        f.set(mc, tempSession);
      }
      catch (Exception ex2)
      {
        tempSession = null;
        ex2.printStackTrace();
        return ex.getMessage();
      }
    }
    LabyMod.getInstance().client.newAccount();
    LogManager.getLogger().info("AccountManager: You are now playing with " + tempSession.e().getName() + ".");
    return "";
  }
  
  public static void removeFinal(Field field)
    throws Exception
  {
    field.setAccessible(true);
    int modData = field.getModifiers();
    Field fieldModification = field.getClass().getDeclaredField("modifiers");
    modData &= 0xFFFFFFEF;
    fieldModification.setAccessible(true);
    fieldModification.setInt(field, modData);
  }
  
  public static bcm authenticate(String username, String password)
    throws AuthenticationException
  {
    HttpAuthenticationService auth = new YggdrasilAuthenticationService(Proxy.NO_PROXY, bcf.z().K().d());
    UserAuthentication authentification = auth.createUserAuthentication(Agent.MINECRAFT);
    authentification.setUsername(username);
    authentification.setPassword(password);
    authentification.logIn();
    if (authentification.canPlayOnline())
    {
      bcm session = new bcm(authentification.getSelectedProfile().getName(), authentification.getSelectedProfile().getId().toString(), authentification.getAuthenticatedToken(), bcm.a.b.toString());
      return session;
    }
    LogManager.getLogger().info("AccountManager: Could not log in!");
    return null;
  }
}
