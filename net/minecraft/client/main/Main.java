package net.minecraft.client.main;

import bcf;
import bcm;
import bib;
import bib.a;
import bib.b;
import bib.c;
import bib.d;
import bib.e;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.authlib.properties.PropertyMap;
import com.mojang.authlib.properties.PropertyMap.Serializer;
import java.io.File;
import java.io.PrintStream;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.List;
import joptsimple.ArgumentAcceptingOptionSpec;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import joptsimple.OptionSpecBuilder;
import od;

public class Main
{
  public static void main(String[] ☃)
  {
    OptionParser ☃ = new OptionParser();
    ☃.allowsUnrecognizedOptions();
    
    ☃.accepts("demo");
    ☃.accepts("fullscreen");
    ☃.accepts("checkGlErrors");
    OptionSpec<String> ☃ = ☃.accepts("server").withRequiredArg();
    OptionSpec<Integer> ☃ = ☃.accepts("port").withRequiredArg().ofType(Integer.class).defaultsTo(Integer.valueOf(25565), new Integer[0]);
    OptionSpec<File> ☃ = ☃.accepts("gameDir").withRequiredArg().ofType(File.class).defaultsTo(new File("."), new File[0]);
    OptionSpec<File> ☃ = ☃.accepts("assetsDir").withRequiredArg().ofType(File.class);
    OptionSpec<File> ☃ = ☃.accepts("resourcePackDir").withRequiredArg().ofType(File.class);
    OptionSpec<String> ☃ = ☃.accepts("proxyHost").withRequiredArg();
    OptionSpec<Integer> ☃ = ☃.accepts("proxyPort").withRequiredArg().defaultsTo("8080", new String[0]).ofType(Integer.class);
    OptionSpec<String> ☃ = ☃.accepts("proxyUser").withRequiredArg();
    OptionSpec<String> ☃ = ☃.accepts("proxyPass").withRequiredArg();
    OptionSpec<String> ☃ = ☃.accepts("username").withRequiredArg().defaultsTo("Player" + bcf.I() % 1000L, new String[0]);
    OptionSpec<String> ☃ = ☃.accepts("uuid").withRequiredArg();
    OptionSpec<String> ☃ = ☃.accepts("accessToken").withRequiredArg().required();
    OptionSpec<String> ☃ = ☃.accepts("version").withRequiredArg().required();
    OptionSpec<Integer> ☃ = ☃.accepts("width").withRequiredArg().ofType(Integer.class).defaultsTo(Integer.valueOf(854), new Integer[0]);
    OptionSpec<Integer> ☃ = ☃.accepts("height").withRequiredArg().ofType(Integer.class).defaultsTo(Integer.valueOf(480), new Integer[0]);
    OptionSpec<String> ☃ = ☃.accepts("userProperties").withRequiredArg().defaultsTo("{}", new String[0]);
    OptionSpec<String> ☃ = ☃.accepts("profileProperties").withRequiredArg().defaultsTo("{}", new String[0]);
    OptionSpec<String> ☃ = ☃.accepts("assetIndex").withRequiredArg();
    OptionSpec<String> ☃ = ☃.accepts("userType").withRequiredArg().defaultsTo("legacy", new String[0]);
    OptionSpec<String> ☃ = ☃.accepts("versionType").withRequiredArg().defaultsTo("release", new String[0]);
    OptionSpec<String> ☃ = ☃.nonOptions();
    
    OptionSet ☃ = ☃.parse(☃);
    List<String> ☃ = ☃.valuesOf(☃);
    if (!☃.isEmpty()) {
      System.out.println("Completely ignored arguments: " + ☃);
    }
    String ☃ = (String)☃.valueOf(☃);
    Proxy ☃ = Proxy.NO_PROXY;
    if (☃ != null) {
      try
      {
        ☃ = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(☃, ((Integer)☃.valueOf(☃)).intValue()));
      }
      catch (Exception localException) {}
    }
    String ☃ = (String)☃.valueOf(☃);
    final String ☃ = (String)☃.valueOf(☃);
    if ((!☃.equals(Proxy.NO_PROXY)) && (a(☃)) && (a(☃))) {
      Authenticator.setDefault(new Authenticator()
      {
        protected PasswordAuthentication getPasswordAuthentication()
        {
          return new PasswordAuthentication(this.a, ☃.toCharArray());
        }
      });
    }
    int ☃ = ((Integer)☃.valueOf(☃)).intValue();
    int ☃ = ((Integer)☃.valueOf(☃)).intValue();
    boolean ☃ = ☃.has("fullscreen");
    boolean ☃ = ☃.has("checkGlErrors");
    boolean ☃ = ☃.has("demo");
    String ☃ = (String)☃.valueOf(☃);
    Gson ☃ = new GsonBuilder().registerTypeAdapter(PropertyMap.class, new PropertyMap.Serializer()).create();
    PropertyMap ☃ = (PropertyMap)od.a(☃, (String)☃.valueOf(☃), PropertyMap.class);
    PropertyMap ☃ = (PropertyMap)od.a(☃, (String)☃.valueOf(☃), PropertyMap.class);
    String ☃ = (String)☃.valueOf(☃);
    
    File ☃ = (File)☃.valueOf(☃);
    File ☃ = ☃.has(☃) ? (File)☃.valueOf(☃) : new File(☃, "assets/");
    File ☃ = ☃.has(☃) ? (File)☃.valueOf(☃) : new File(☃, "resourcepacks/");
    String ☃ = ☃.has(☃) ? (String)☃.value(☃) : (String)☃.value(☃);
    String ☃ = ☃.has(☃) ? (String)☃.value(☃) : null;
    
    String ☃ = (String)☃.valueOf(☃);
    Integer ☃ = (Integer)☃.valueOf(☃);
    
    bcm ☃ = new bcm((String)☃.value(☃), ☃, (String)☃.value(☃), (String)☃.value(☃));
    bib ☃ = new bib(new bib.e(☃, ☃, ☃, ☃), new bib.a(☃, ☃, ☃, ☃), new bib.b(☃, ☃, ☃, ☃), new bib.c(☃, ☃, ☃), new bib.d(☃, ☃.intValue()));
    
    Runtime.getRuntime().addShutdownHook(new Thread("Client Shutdown Thread")
    {
      public void run() {}
    });
    Thread.currentThread().setName("Client thread");
    
    new bcf(☃).a();
  }
  
  private static boolean a(String ☃)
  {
    return (☃ != null) && (!☃.isEmpty());
  }
}
