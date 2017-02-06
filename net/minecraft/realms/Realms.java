package net.minecraft.realms;

import ahw.a;
import bcf;
import bch;
import bch.a;
import bcm;
import bfi;
import bwk;
import com.google.common.util.concurrent.ListenableFuture;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.util.UUIDTypeAdapter;
import java.io.File;
import java.net.Proxy;

public class Realms
{
  public static boolean isTouchScreen()
  {
    return bcf.z().u.z;
  }
  
  public static Proxy getProxy()
  {
    return bcf.z().M();
  }
  
  public static String sessionId()
  {
    bcm ☃ = bcf.z().K();
    if (☃ == null) {
      return null;
    }
    return ☃.a();
  }
  
  public static String userName()
  {
    bcm ☃ = bcf.z().K();
    if (☃ == null) {
      return null;
    }
    return ☃.c();
  }
  
  public static long currentTimeMillis()
  {
    return bcf.I();
  }
  
  public static String getSessionId()
  {
    return bcf.z().K().a();
  }
  
  public static String getUUID()
  {
    return bcf.z().K().b();
  }
  
  public static String getName()
  {
    return bcf.z().K().c();
  }
  
  public static String uuidToName(String ☃)
  {
    return bcf.z().X().fillProfileProperties(new GameProfile(UUIDTypeAdapter.fromString(☃), null), false).getName();
  }
  
  public static void setScreen(RealmsScreen ☃)
  {
    bcf.z().a(☃.getProxy());
  }
  
  public static String getGameDirectoryPath()
  {
    return bcf.z().w.getAbsolutePath();
  }
  
  public static int survivalId()
  {
    return ahw.a.b.a();
  }
  
  public static int creativeId()
  {
    return ahw.a.c.a();
  }
  
  public static int adventureId()
  {
    return ahw.a.d.a();
  }
  
  public static int spectatorId()
  {
    return ahw.a.e.a();
  }
  
  public static void setConnectedToRealms(boolean ☃)
  {
    bcf.z().a(☃);
  }
  
  public static ListenableFuture<Object> downloadResourcePack(String ☃, String ☃)
  {
    ListenableFuture<Object> ☃ = bcf.z().P().a(☃, ☃);
    
    return ☃;
  }
  
  public static void clearResourcePack()
  {
    bcf.z().P().g();
  }
  
  public static boolean getRealmsNotificationsEnabled()
  {
    return bcf.z().u.b(bch.a.K);
  }
  
  public static boolean inTitleScreen()
  {
    return (bcf.z().m != null) && ((bcf.z().m instanceof bfi));
  }
}
