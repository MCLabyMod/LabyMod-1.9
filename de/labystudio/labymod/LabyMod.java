package de.labystudio.labymod;

import axe;
import bbh;
import bcf;
import bcm;
import bcu;
import bcv;
import bda;
import bei;
import bex;
import bfb;
import bfi;
import bgr;
import bks;
import bku;
import bkv;
import bkx;
import bmt;
import bye;
import byx;
import com.mojang.authlib.GameProfile;
import de.labystudio.capes.CapeManager;
import de.labystudio.chat.ChatHandler;
import de.labystudio.chat.Client;
import de.labystudio.chat.ClientConnection;
import de.labystudio.chat.EnumAlertType;
import de.labystudio.chat.LabyModPlayer;
import de.labystudio.cosmetic.CosmeticManager;
import de.labystudio.downloader.ModInfoDownloader;
import de.labystudio.downloader.UserCapesDownloader;
import de.labystudio.gui.GuiAchievementMod;
import de.labystudio.hologram.Manager;
import de.labystudio.language.L;
import de.labystudio.listener.Games;
import de.labystudio.listener.GommeHD;
import de.labystudio.listener.HiveMC;
import de.labystudio.listener.JumpLeague;
import de.labystudio.listener.KeyListener;
import de.labystudio.listener.Revayd;
import de.labystudio.listener.Timolia;
import de.labystudio.minecraft.GuiIngameMod;
import de.labystudio.modapi.ModAPI;
import de.labystudio.modapi.ModManager;
import de.labystudio.modapi.events.GameTickEvent;
import de.labystudio.modapi.events.JoinedServerEvent;
import de.labystudio.modapi.events.PluginMessageReceivedEvent;
import de.labystudio.packets.EnumConnectionState;
import de.labystudio.packets.PacketPlayServerStatus;
import de.labystudio.spotify.SpotifyManager;
import de.labystudio.utils.Allowed;
import de.labystudio.utils.AutoTextLoader;
import de.labystudio.utils.Color;
import de.labystudio.utils.Debug;
import de.labystudio.utils.DrawUtils;
import de.labystudio.utils.FilterLoader;
import de.labystudio.utils.FriendsLoader;
import de.labystudio.utils.LOGO;
import de.labystudio.utils.ModGui;
import de.labystudio.utils.ServerBroadcast;
import de.labystudio.utils.ServiceStatus;
import de.labystudio.utils.StatsLoader;
import de.labystudio.utils.TextureManager;
import de.zockermaus.ts3.TeamSpeak;
import de.zockermaus.ts3.TeamSpeakController;
import em;
import eu;
import fa;
import io.netty.buffer.Unpooled;
import iq;
import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import kk;
import nf;
import nh;
import org.apache.commons.lang3.SystemUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;
import zj;

public class LabyMod
  extends bcv
{
  public Logger logger = LogManager.getLogger();
  private static Random random = ;
  public String ip = "";
  public int port = 25565;
  public String gameMode = "";
  public bcf mc = null;
  public boolean refresh = false;
  public int scroll = 0;
  public int playerPing = 0;
  public int lavaTime = 0;
  public String nickname = "";
  public eu footer;
  public eu header;
  public float foodSaturationLevel = 0.0F;
  public ArrayList<String> onlinePlayerList = new ArrayList();
  public ArrayList<String> gameTypes = new ArrayList();
  public ArrayList<String> serverMSG = new ArrayList();
  public HashMap<String, String> serverPing = new HashMap();
  public HashMap<String, ServiceStatus> mojangStatus = new HashMap();
  public ArrayList<String> commandQueue = new ArrayList();
  public ArrayList<bkv> onlinePlayers = new ArrayList();
  public boolean chat = true;
  public String gommeHDSearch = "";
  public boolean gommeHDSeachAllowed = false;
  public boolean gommeHDSound = false;
  public int gommeHDSeachPartySize = 0;
  public String gommeHDSearchBlacklist = "";
  public boolean gommeHDAutoJoin = false;
  public GuiAchievementMod achievementGui;
  public String line1 = "";
  public String line2 = "";
  public int animation = 0;
  public bfb lastScreen;
  public boolean joined = false;
  public boolean intentionally;
  public boolean out = false;
  public bfb onlineChat;
  public DrawUtils draw;
  public Client client;
  public boolean newMessage = false;
  public long lastRecon = 0L;
  public String lastKickReason = "";
  public LabyModPlayer selectedPlayer = null;
  public kk texture_img = new kk("img.png");
  public kk texture_status = new kk("status.png");
  public kk texture_mic = new kk("mic.png");
  public kk texture_box = new kk("box.png");
  public kk texture_teamSpeak = new kk("teamspeak.png");
  public ChatHandler handler;
  public TextureManager textureManager;
  public boolean chatVisibility = false;
  public boolean chatChange = false;
  public bfb openMenu = null;
  public boolean isAFK = false;
  public long lastTimeAFK = 0L;
  public long keepTime = 0L;
  public long lastMove = 0L;
  public int autoUpdaterLatestVersionId = 0;
  public int autoUpdaterCurrentVersionId = 0;
  public String latestVersionName = "?";
  public boolean chatPacketUpdate = false;
  private float partialTicks;
  private ServerBroadcast serverBroadcast;
  private CapeManager capeManager;
  private CosmeticManager cosmeticManager;
  private SpotifyManager spotifyManager;
  public static boolean protocolHack = false;
  private static LabyMod instance;
  
  public static int getClientVersion()
  {
    return protocolHack ? 109 : 107;
  }
  
  private static boolean overwrite = false;
  int min;
  
  public static LabyMod getInstance()
  {
    if (instance == null) {
      new LabyMod();
    }
    return instance;
  }
  
  public static void overWrite()
  {
    if (!overwrite)
    {
      overwrite = true;
      bcf.z().r = new GuiIngameMod(bcf.z());
      try
      {
        Display.setTitle("Minecraft " + Source.mcVersion + " | LabyMod " + Source.mod_VersionName);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
  
  public static Random getRandom()
  {
    return random;
  }
  
  public LabyMod()
  {
    instance = this;
    System.out.println("[LabyMod] Loading labymod..");
    L.load();
    Timings.start("Enable LabyMod");
    this.mc = bcf.z();
    this.textureManager = new TextureManager();
    this.draw = new DrawUtils();
    ConfigManager.loadProperties(true);
    this.achievementGui = new GuiAchievementMod(this.mc);
    this.client = new Client();
    this.handler = new ChatHandler();
    this.handler.initDatabase();
    this.client.init();
    runLoop();
    FriendsLoader.loadFriends();
    FilterLoader.loadFilters();
    AutoTextLoader.load();
    StatsLoader.loadstats();
    this.capeManager = new CapeManager();
    this.cosmeticManager = new CosmeticManager();
    if (SystemUtils.IS_OS_WINDOWS) {
      this.spotifyManager = new SpotifyManager();
    }
    if (ConfigManager.settings == null)
    {
      Timings.stop("Enable LabyMod");
      return;
    }
    if (ConfigManager.settings.teamSpeak.booleanValue()) {
      TeamSpeak.enable();
    }
    new ModInfoDownloader();
    new UserCapesDownloader();
    ModManager.loadMods();
    if (!LOGO.isLogo(getPlayerName())) {
      ConfigManager.settings.logomode = false;
    }
    Runtime.getRuntime().addShutdownHook(new Thread()
    {
      public void run()
      {
        try
        {
          System.out.println("[LabyMod] Checking if you are using an outdated LabyMod version..");
          if (LabyMod.getInstance().autoUpdaterLatestVersionId > LabyMod.getInstance().autoUpdaterCurrentVersionId)
          {
            System.out.println("[LabyMod] You are outdated! You are still on Version v" + Source.mod_VersionName + ", the latest version v" + LabyMod.this.latestVersionName + " will now be installed..");
            Runtime.getRuntime().exec("java -jar LabyMod/Updater-" + Source.mcVersion + ".jar");
          }
          else
          {
            System.out.println("[LabyMod] You are using the latest LabyMod version v" + Source.mod_VersionName);
          }
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
    Timings.stop("Enable LabyMod");
    System.out.println("[LabyMod] LabyMod Version " + Source.mod_VersionName + " for Minecraft " + Source.mcVersion + " loaded!");
  }
  
  public SpotifyManager getSpotifyManager()
  {
    return this.spotifyManager;
  }
  
  public CapeManager getCapeManager()
  {
    return this.capeManager;
  }
  
  public CosmeticManager getCosmeticManager()
  {
    return this.cosmeticManager;
  }
  
  public Client getClient()
  {
    return this.client;
  }
  
  public float getPartialTicks()
  {
    return this.partialTicks;
  }
  
  public void setPartialTicks(float partialTicks)
  {
    this.partialTicks = partialTicks;
  }
  
  public ServerBroadcast getServerBroadcast()
  {
    return this.serverBroadcast;
  }
  
  public void setServerBroadcast(ServerBroadcast serverBroadcast)
  {
    this.serverBroadcast = serverBroadcast;
  }
  
  public void playSound(nf soundIn, float pitch)
  {
    bcf.z().U().a(bye.a(soundIn, pitch));
  }
  
  public void playSound(nf sound, float volume, float pitch, int x, int y, int z)
  {
    if (isInGame()) {
      this.mc.f.a((zj)null, x, y, z, sound, nh.e, volume, pitch);
    }
  }
  
  public boolean isUpdateAvailable()
  {
    if (getInstance().autoUpdaterLatestVersionId == 0) {
      return false;
    }
    return getInstance().autoUpdaterLatestVersionId > getInstance().autoUpdaterCurrentVersionId;
  }
  
  public void resetIP()
  {
    if (((this.ip == null) || (!this.ip.replace(" ", "").isEmpty())) && 
      (this.client.getClientConnection().getState() == EnumConnectionState.PLAY)) {
      this.client.getClientConnection().sendPacket(new PacketPlayServerStatus(" ", 0));
    }
    this.ip = "";
    this.gameMode = "";
    this.joined = false;
  }
  
  public void resetMod()
  {
    Timings.start("Reset Mod");
    this.scroll = 0;
    this.lavaTime = 0;
    this.playerPing = 0;
    this.lavaTime = 0;
    this.nickname = "";
    Manager.holograms.clear();
    
    ChatHandler.resetTyping();
    if (this.gameMode == null) {
      this.gameMode = "";
    }
    if (!this.gameMode.isEmpty())
    {
      this.gameMode = "";
      ChatHandler.updateGameMode("");
    }
    JumpLeague.resetJumpLeague();
    GommeHD.resetGommeHD();
    Timolia.resetTimolia();
    ModGui.reset();
    Games.reset();
    Revayd.reset();
    HiveMC.reset();
    Timings.stop("Reset Mod");
  }
  
  public String getHeader()
  {
    if ((this.header == null) || (this.header.c() == null)) {
      return "";
    }
    return this.header.c();
  }
  
  public String getFooter()
  {
    if ((this.footer == null) || (this.footer.c() == null)) {
      return "";
    }
    return this.footer.c();
  }
  
  public void sendCommand(String send)
  {
    if (isInGame()) {
      this.mc.h.g("/" + send);
    }
  }
  
  public void sendChatMessage(String message)
  {
    if (isInGame()) {
      this.mc.h.g(message);
    }
  }
  
  public void displayMessageInChat(String message)
  {
    bcf.z().r.d().a(new fa(message));
  }
  
  public void sendMessage(String prefix, LabyModPlayer player, String message)
  {
    getClient();
    if (Client.isBusy()) {
      return;
    }
    if (!this.client.hasNotifications(player)) {
      return;
    }
    if (ConfigManager.settings.chatAlertType)
    {
      if (ConfigManager.settings.alertsChat) {
        getInstance().displayMessageInChat(ClientConnection.chatPrefix + Color.cl("e") + prefix + player.getName() + Color.cl("7") + " " + message);
      }
    }
    else {
      this.achievementGui.displayMessage(prefix + player.getName(), message, EnumAlertType.CHAT);
    }
  }
  
  public String getPlayerName()
  {
    return this.mc.K().c();
  }
  
  public UUID getPlayerUUID()
  {
    return this.mc.K().e().getId();
  }
  
  public boolean isInGame()
  {
    try
    {
      return (this.mc != null) && (this.mc.h != null) && (this.mc.h != null);
    }
    catch (Exception error) {}
    return false;
  }
  
  public boolean isChatGUI()
  {
    if (this.mc.m != null) {
      return this.mc.m.toString().contains("GuiChat");
    }
    return false;
  }
  
  public void gameTick()
  {
    if (ModAPI.enabled()) {
      ModAPI.callEvent(new GameTickEvent());
    }
  }
  
  public void runLoop()
  {
    new ModThread().start();
  }
  
  public int removeChallenge = 0;
  
  public void secondLoop()
  {
    Timings.start("LabyMod Tick");
    this.min += 1;
    if ((getSpotifyManager() != null) && (ConfigManager.settings.spotfiyTrack)) {
      getSpotifyManager().updateTitle();
    }
    Debug.updateDebugMessages();
    if (isInGame())
    {
      this.onlinePlayers.clear();
      this.onlinePlayers.addAll(this.mc.h.d.d());
      if (ConfigManager.settings.lavaTime) {
        if ((this.mc.f.a(this.mc.h.bl().b(0.0D, -0.4000000059604645D, 0.0D), axe.i, this.mc.h)) && (this.mc.h.aH()))
        {
          this.lavaTime += 1;
          this.removeChallenge = 0;
        }
        else
        {
          this.removeChallenge += 1;
          if (this.removeChallenge > 2)
          {
            this.lavaTime = 0;
            this.removeChallenge = 0;
          }
        }
      }
    }
    else
    {
      this.isAFK = false;
    }
    if (this.mc.m == null) {
      ChatHandler.updateIsWriting(null, "");
    }
    if (this.min >= 60)
    {
      this.min = 0;
      minutesLoop();
    }
    GommeHD.loop();
    if ((ConfigManager.settings.teamSpeak.booleanValue()) && (TeamSpeakController.getInstance() != null)) {
      TeamSpeakController.getInstance().tick();
    }
    Timings.stop("LabyMod Tick");
  }
  
  public void minutesLoop()
  {
    if (getRandom().nextInt(6) == 0) {
      getClient().getClientConnection().reconnect();
    }
  }
  
  public void openWebpage(String urlString)
  {
    try
    {
      if ((!urlString.toLowerCase().startsWith("https://")) && 
        (!urlString.toLowerCase().startsWith("http://"))) {
        urlString = "http://" + urlString;
      }
      Desktop.getDesktop().browse(new URL(urlString).toURI());
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public void connectToServer(String address)
  {
    if (bcf.z().f != null)
    {
      bcf.z().f.M();
      bcf.z().a((bku)null);
    }
    bcf.z().a(new bei(new bfi(), this.mc, new bkx("Server", address, false)));
  }
  
  public Boolean hasFocus()
  {
    if (isInGame()) {
      return Boolean.valueOf(this.mc.x);
    }
    return Boolean.valueOf(false);
  }
  
  public ArrayList<String> toLowerCaseList(ArrayList<String> list)
  {
    ArrayList<String> output = new ArrayList();
    for (String s : list) {
      output.add(s.toLowerCase());
    }
    return output;
  }
  
  public void back()
  {
    if (isInGame()) {
      this.mc.a(new bex());
    } else {
      this.mc.a(new bgr(null));
    }
  }
  
  public void updateServerIP(String address, int port)
  {
    resetMod();
    if (address == null) {
      this.ip = "";
    } else {
      this.ip = address;
    }
    this.port = port;
    ConfigManager.settings.last_Server = address;
    Allowed.update(address);
    if (this.client.getClientConnection().getState() != EnumConnectionState.OFFLINE) {
      this.client.getClientConnection().sendPacket(new PacketPlayServerStatus(address, port));
    }
  }
  
  public boolean createPath(File file)
  {
    if (file.exists()) {
      return false;
    }
    file.getParentFile().mkdirs();
    try
    {
      file.createNewFile();
    }
    catch (IOException e)
    {
      e.printStackTrace();
      return false;
    }
    return true;
  }
  
  public void overlay(int mouseX, int mouseY)
  {
    Timings.start("Overlay LabyMod");
    if ((this.achievementGui != null) && (
      (!ConfigManager.settings.chatAlertType) || (!ConfigManager.settings.teamSpeakAlertTypeChat) || (!ConfigManager.settings.mojangStatusChat))) {
      this.achievementGui.updateAchievementWindow();
    }
    DrawUtils.updateMouse(mouseX, mouseY);
    KeyListener.handle();
    Timings.draw();
    Timings.stop("Overlay LabyMod");
  }
  
  public void onRender()
  {
    if (getInstance().isInGame())
    {
      JumpLeague.isFallingDown();
      ModGui.smoothFPS();
      if (!this.joined)
      {
        this.joined = true;
        onJoin();
      }
    }
  }
  
  public void onJoin()
  {
    if (ModAPI.enabled()) {
      ModAPI.callEvent(new JoinedServerEvent(this.ip, this.port));
    }
    if ((!this.mc.E()) && 
      (!getInstance().commandQueue.isEmpty())) {
      getInstance().sendCommand((String)getInstance().commandQueue.get(0));
    }
    em packetBuffer = new em(Unpooled.buffer());
    packetBuffer.a("LabyMod v" + Source.mod_VersionName);
    this.mc.v().a(new iq("LABYMOD", packetBuffer));
    if (this.chatPacketUpdate) {
      displayMessageInChat(Color.cl("c") + "LabyMod is outdated!" + Color.cl("7") + " Download the latest version " + Color.cl("e") + "v" + this.latestVersionName + Color.cl("7") + " at " + Color.cl("9") + Source.url_Update + "");
    }
  }
  
  public void pluginMessage(String channel, em data)
  {
    if (ModAPI.enabled()) {
      ModAPI.callEvent(new PluginMessageReceivedEvent(channel, data));
    }
    try
    {
      if ((data != null) && (channel != null) && (channel.equals("LABYMOD")))
      {
        ByteArrayInputStream byteIn = new ByteArrayInputStream(data.array());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        list = (Map)in.readObject();
        for (String key : list.keySet())
        {
          Allowed.set(key, ((Boolean)list.get(key)).booleanValue());
          bcf.z().r.d().a(new fa(Color.cl("c") + "The " + Color.cl("e") + key + Color.cl("c") + " function was " + ((Boolean)list.get(key)).toString().replace("true", new StringBuilder().append(Color.cl("a")).append("enabled").toString()).replace("false", new StringBuilder().append(Color.cl("4")).append("disabled").toString()) + Color.cl("c") + " by the server."));
        }
      }
    }
    catch (Exception error)
    {
      Map<String, Boolean> list;
      error.printStackTrace();
    }
  }
}
