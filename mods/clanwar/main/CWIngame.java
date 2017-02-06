package mods.clanwar.main;

import bcf;
import bee;
import bni;
import bvi;
import de.labystudio.labymod.LabyMod;
import de.labystudio.utils.DrawUtils;
import de.labystudio.utils.ModGui;
import de.labystudio.utils.TextureManager;
import java.util.ArrayList;
import java.util.Iterator;
import kk;
import org.lwjgl.opengl.GL11;

public class CWIngame
{
  public static DrawUtils draw = LabyMod.getInstance().draw;
  public static long lastTimeChange = 0L;
  public static ArrayList<String> chat = new ArrayList();
  public static kk texture_bed = new kk("bed.png");
  public static boolean isClanWars = false;
  public static boolean isSpec = false;
  public static Clan clan1 = new Clan("Bronkos", "BR", de.labystudio.utils.Color.cl("9"));
  public static Clan clan2 = new Clan("GommeHDNet", "Gomme", de.labystudio.utils.Color.cl("c"));
  
  static
  {
    clan1.addPlayer("LabyStudio");
    clan1.addPlayer("Rudi__Ruessel");
    clan1.addPlayer("hooeyLou");
    clan1.addPlayer("Pycta");
    
    clan2.addPlayer("GommeHD");
    clan2.addPlayer("Snoxh");
    clan2.addPlayer("Clexxer");
    clan2.addPlayer("Klaus");
  }
  
  public static int time = 0;
  
  public static boolean renderScreen()
  {
    isClanWars = false;
    if ((!isClanWars) || ((bcf.z().m instanceof bee))) {
      return false;
    }
    DrawUtils.a(draw.getWidth() / 2 - 190, 12, draw.getWidth() / 2 - 70, 27, Integer.MAX_VALUE);
    DrawUtils.a(draw.getWidth() / 2 + 190, 12, draw.getWidth() / 2 + 70, 27, Integer.MAX_VALUE);
    
    DrawUtils.a(0, 0, draw.getWidth(), 9, Integer.MIN_VALUE);
    DrawUtils.a(draw.getWidth() / 2 - 70, 9, draw.getWidth() / 2 + 70, 34, Integer.MIN_VALUE);
    draw.drawCenteredString(ModGui.translateTimer(time), draw.getWidth() / 2, 14.0D, 2.0D);
    
    draw.drawCenteredString(de.labystudio.utils.Color.cl("9") + clan1.getName() + de.labystudio.utils.Color.cl("7") + " [" + de.labystudio.utils.Color.cl("e") + clan1.getTag() + de.labystudio.utils.Color.cl("7") + "]", draw.getWidth() / 2 - 130, 15.0D, 1.0D);
    draw.drawCenteredString(de.labystudio.utils.Color.cl("c") + clan2.getName() + de.labystudio.utils.Color.cl("7") + " [" + de.labystudio.utils.Color.cl("e") + clan2.getTag() + de.labystudio.utils.Color.cl("7") + "]", draw.getWidth() / 2 + 130, 15.0D, 1.0D);
    
    double k = 2.7D;
    GL11.glPushMatrix();
    GL11.glScaled(k, k, k);
    
    bni.d(3.0F, 3.0F, 3.0F);
    bcf.z().N().a(texture_bed);
    int bedX = draw.getWidth() / 2 - 58;
    int bedY = 6;
    double xl = 0.0D;
    int yl = 9;
    if (!clan1.getBed()) {
      xl = 10.0D;
    }
    draw.drawTexturedModalRect(bedX / k, bedY / k, xl, yl, 10.0D, 9.0D);
    bedX = draw.getWidth() / 2 + 36;
    bedY = 6;
    xl = 0.0D;
    yl = 0;
    if (!clan2.getBed()) {
      xl = 10.0D;
    }
    draw.drawTexturedModalRect(bedX / k, bedY / k, xl, yl, 10.0D, 9.0D);
    
    GL11.glPopMatrix();
    
    draw.drawCenteredString("GommeHD.net ClanWar", draw.getWidth() / 2, 4.0D, 0.7D);
    
    double y = (int)(draw.getHeight() / 2 - clan1.getPlayers().size() * 11.4D) + 7;
    for (BWPlayer player : clan1.getPlayers())
    {
      bni.d(1.0F, 1.0F, 1.0F);
      String name = player.getName();
      int color = 2032040443;
      if (player.dead)
      {
        name = "MHF_Skeleton";
        color = java.awt.Color.darkGray.getRGB();
      }
      LabyMod.getInstance().textureManager.drawPlayerHead(name, 3.0D, y, 0.5D);
      draw.drawRect(19.0D, y, 67.0D, y + 10.0D, color);
      draw.drawString(player.getName(), 23.0D, y + 3.0D, 0.5D);
      y += 22.5D;
    }
    y = (int)(draw.getHeight() / 2 - clan2.getPlayers().size() * 11.4D) + 7;
    for (BWPlayer player : clan2.getPlayers())
    {
      bni.d(1.0F, 1.0F, 1.0F);
      String name = player.getName();
      color = de.labystudio.utils.Color.toRGB(255, 62, 69, 162);
      if (player.dead)
      {
        name = "MHF_Skeleton";
        color = java.awt.Color.darkGray.getRGB();
      }
      LabyMod.getInstance().textureManager.drawPlayerHead(name, draw.getWidth() - 19, y, 0.5D);
      draw.drawRect(draw.getWidth() - 19, y, draw.getWidth() - 67, y + 10.0D, color);
      draw.drawRightString(player.getName(), draw.getWidth() - 23, y + 3.0D, 0.5D);
      y += 22.5D;
    }
    int max = 0;
    int id = 0;
    int last = 0;
    for (int color = chat.iterator(); color.hasNext();)
    {
      chatLine = (String)color.next();
      int next = draw.getStringWidth(chatLine);
      if (next > last) {
        max = next;
      }
      if (id > 4) {
        break;
      }
      id++;
    }
    String chatLine;
    int chatY = 0;
    id = 0;
    for (String chatLine : chat)
    {
      DrawUtils.a(3, draw.getHeight() - 15 - chatY, max + 7, draw.getHeight() - 5 - chatY, Integer.MIN_VALUE);
      draw.drawString(chatLine, 5.0D, draw.getHeight() - 5 - chatY - 9);
      if (id > 4) {
        break;
      }
      chatY += 10;
      id++;
    }
    if (lastTimeChange < System.currentTimeMillis())
    {
      lastTimeChange = System.currentTimeMillis() + 1000L;
      time += 1;
    }
    return true;
  }
  
  public static class Clan
  {
    private String name;
    private String tag;
    private boolean bed;
    private String color;
    private ArrayList<CWIngame.BWPlayer> players = new ArrayList();
    
    public Clan(String name, String tag, String color)
    {
      this.name = name;
      this.tag = tag;
      this.bed = true;
      this.color = color;
    }
    
    public String getColor()
    {
      return this.color;
    }
    
    public void addPlayer(String player)
    {
      this.players.add(new CWIngame.BWPlayer(player));
    }
    
    public void removePlayer(String player)
    {
      ArrayList<CWIngame.BWPlayer> temp = new ArrayList();
      temp.addAll(getPlayers());
      CWIngame.BWPlayer rem = null;
      for (CWIngame.BWPlayer p : temp) {
        if (p.getName().equals(player)) {
          rem = p;
        }
      }
      if (rem != null) {
        this.players.remove(rem);
      }
    }
    
    public void setBed(boolean bed)
    {
      this.bed = bed;
    }
    
    public String getName()
    {
      return this.name;
    }
    
    public String getTag()
    {
      return this.tag;
    }
    
    public boolean getBed()
    {
      return this.bed;
    }
    
    public ArrayList<CWIngame.BWPlayer> getPlayers()
    {
      return this.players;
    }
  }
  
  public static class BWPlayer
  {
    String name;
    boolean dead;
    
    public BWPlayer(String name)
    {
      this.name = name;
      this.dead = false;
    }
    
    public boolean isDead()
    {
      return this.dead;
    }
    
    public String getName()
    {
      return this.name;
    }
    
    public void setDead(boolean dead)
    {
      this.dead = dead;
    }
  }
}
