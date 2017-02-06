package de.labystudio.gommehd;

import cj;
import de.labystudio.hologram.Hologram;
import de.labystudio.hologram.Manager;
import de.labystudio.hologram.SetColor;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.ModSettings;
import de.labystudio.listener.GommeHD;
import de.labystudio.utils.Color;
import de.labystudio.utils.Utils;
import eu;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import sa;

public class GommeHDBed
{
  public static ArrayList<UUID> respawn = new ArrayList();
  public static ArrayList<BedLocation> locations = new ArrayList();
  public static ArrayList<EnumBWTeam> noBeds = new ArrayList();
  public static EnumBWMap playingMap = EnumBWMap.NONE;
  public static int mapHeight = 0;
  
  private static boolean contains(int x, int y, int z)
  {
    for (BedLocation bed : ) {
      if ((bed.x == x) && (bed.y == y) && (bed.z == z)) {
        return true;
      }
    }
    return false;
  }
  
  public static SetColor bindColor(EnumBWTeam team)
  {
    if (team == EnumBWTeam.BLAU) {
      return new SetColor(0.0F, 0.0F, 1.0F, 0.45F);
    }
    if (team == EnumBWTeam.GRÜN) {
      return new SetColor(0.0F, 1.0F, 0.0F, 0.55F);
    }
    if (team == EnumBWTeam.ROT) {
      return new SetColor(1.0F, 0.0F, 0.0F, 0.55F);
    }
    if (team == EnumBWTeam.GELB) {
      return new SetColor(1.0F, 1.0F, 0.0F, 0.45F);
    }
    if (team == EnumBWTeam.SCHWARZ) {
      return new SetColor(0.0F, 0.0F, 0.0F, 0.45F);
    }
    if (team == EnumBWTeam.PINK) {
      return new SetColor(1.5F, 0.5F, 0.6F, 0.45F);
    }
    if (team == EnumBWTeam.TÜRKIS) {
      return new SetColor(0.2F, 1.6F, 3.8F, 0.45F);
    }
    if (team == EnumBWTeam.ORANGE) {
      return new SetColor(1.5F, 0.5F, 0.0F, 0.45F);
    }
    return new SetColor(0.0F, 0.0F, 0.0F, 0.15F);
  }
  
  public static void setPreset(EnumBWMap map)
  {
    reset();
    playingMap = map;
    setBeds();
  }
  
  public static void setBeds()
  {
    if (playingMap == EnumBWMap.FARMLAND)
    {
      add(EnumBWTeam.BLAU, 148, 146, 65108);
      add(EnumBWTeam.ROT, 104, 146, 65072);
      add(EnumBWTeam.GRÜN, 67, 146, 65116);
      add(EnumBWTeam.GELB, 112, 146, 65151);
      setMapHeight(25);
    }
    if (playingMap == EnumBWMap.EMPIRE)
    {
      add(EnumBWTeam.BLAU, -12, 120, 6);
      add(EnumBWTeam.ROT, 46, 120, -61);
      add(EnumBWTeam.GRÜN, -24, 120, -120);
      add(EnumBWTeam.GELB, -80, 120, -52);
      setMapHeight(30);
    }
    if (playingMap == EnumBWMap.ANTIC)
    {
      add(EnumBWTeam.BLAU, -57, 126, 0);
      add(EnumBWTeam.ROT, 0, 126, 57);
      add(EnumBWTeam.GRÜN, 57, 126, 0);
      add(EnumBWTeam.GELB, 0, 126, -57);
      setMapHeight(30);
    }
    if (playingMap == EnumBWMap.VILLAGE)
    {
      add(EnumBWTeam.BLAU, 27, 121, -14);
      add(EnumBWTeam.ROT, -90, 121, -7);
      add(EnumBWTeam.GRÜN, -29, 121, 45);
      add(EnumBWTeam.GELB, -34, 121, -69);
      setMapHeight(30);
    }
    if (playingMap == EnumBWMap.FACTORY)
    {
      add(EnumBWTeam.BLAU, 69, 143, 0);
      add(EnumBWTeam.ROT, -69, 143, 0);
      add(EnumBWTeam.GRÜN, 0, 143, -69);
      add(EnumBWTeam.GELB, 0, 143, 69);
      setMapHeight(15);
    }
    if (playingMap == EnumBWMap.WILDWEST)
    {
      add(EnumBWTeam.BLAU, -7, 80, -70);
      add(EnumBWTeam.ROT, -7, 80, 123);
      add(EnumBWTeam.GRÜN, -104, 80, 26);
      add(EnumBWTeam.GELB, 89, 80, 26);
      setMapHeight(35);
    }
    if (playingMap == EnumBWMap.TIPPIS)
    {
      add(EnumBWTeam.BLAU, 60, 59, -28);
      add(EnumBWTeam.ROT, -64, 59, 24);
      add(EnumBWTeam.GRÜN, -64, 59, -27);
      add(EnumBWTeam.GELB, 60, 59, 23);
      
      add(EnumBWTeam.SCHWARZ, -27, 59, 60);
      add(EnumBWTeam.PINK, 33, 59, -64);
      add(EnumBWTeam.TÜRKIS, -28, 59, -64);
      add(EnumBWTeam.ORANGE, 24, 59, 60);
      setMapHeight(35);
    }
    if (playingMap == EnumBWMap.SHROOM)
    {
      add(EnumBWTeam.BLAU, 0, 90, -85);
      add(EnumBWTeam.ROT, 0, 90, 81);
      add(EnumBWTeam.GRÜN, 83, 90, -2);
      add(EnumBWTeam.GELB, -83, 90, -2);
      setMapHeight(35);
    }
  }
  
  public static int getMapHeight()
  {
    return mapHeight;
  }
  
  public static void setMapHeight(int i)
  {
    mapHeight = i;
  }
  
  public static void add(EnumBWTeam team, int x, int y, int z)
  {
    if ((!contains(x, y, z)) && (playingMap != EnumBWMap.NONE)) {
      locations.add(new BedLocation(team, x, y, z));
    }
  }
  
  public static void reset()
  {
    locations.clear();
    playingMap = EnumBWMap.NONE;
    mapHeight = 20;
    
    ArrayList<Hologram> list = new ArrayList();
    ArrayList<Hologram> rem = new ArrayList();
    list.addAll(Manager.holograms);
    for (Hologram g : list) {
      if ((g.memory instanceof EnumBWTeam)) {
        rem.add(g);
      }
    }
    for (Hologram g : rem) {
      Manager.holograms.remove(g);
    }
    respawn.clear();
  }
  
  public static ArrayList<BedLocation> getLocations()
  {
    ArrayList<BedLocation> l = new ArrayList();
    l.addAll(locations);
    return l;
  }
  
  public static String getSymbol(boolean b)
  {
    if (b) {
      return Color.c + "a✔";
    }
    return Color.c + "4✖";
  }
  
  public static String getBedStatus(EnumBWTeam team)
  {
    if (GommeHD.gommeHDServer_BW_Team.isEmpty()) {
      return "";
    }
    return " " + getSymbol(!noBeds.contains(team));
  }
  
  public static void renderPlayerTag(sa entity, double x, double y, double z)
  {
    Hologram g;
    if ((!GommeHD.gommeHDServer_BW) || (!ConfigManager.settings.showBWTeams.booleanValue()) || (!GommeHD.isGommeHD()))
    {
      if ((!Manager.holograms.isEmpty()) || (!respawn.isEmpty()))
      {
        ArrayList<Hologram> list = new ArrayList();
        ArrayList<Hologram> rem = new ArrayList();
        list.addAll(Manager.holograms);
        for (Hologram g : list) {
          if ((g.memory instanceof EnumBWTeam)) {
            rem.add(g);
          }
        }
        for (??? = rem.iterator(); ???.hasNext();)
        {
          g = (Hologram)???.next();
          Manager.holograms.remove(g);
        }
        respawn.clear();
      }
      return;
    }
    if ((respawn.contains(entity.bc())) && (!entity.F)) {
      try
      {
        respawn.remove(entity.bc());
        String color = entity.i_().d().replace(entity.h_(), "");
        if (color.contains(" ")) {
          color = color.split(" ")[0];
        }
        EnumBWTeam team = null;
        if (color.contains(Color.cl("c"))) {
          team = EnumBWTeam.ROT;
        }
        if (color.contains(Color.cl("e"))) {
          team = EnumBWTeam.GELB;
        }
        if (color.contains(Color.cl("9"))) {
          team = EnumBWTeam.BLAU;
        }
        if (color.contains(Color.cl("2"))) {
          team = EnumBWTeam.GRÜN;
        }
        if (color.contains(Color.cl("0"))) {
          team = EnumBWTeam.SCHWARZ;
        }
        if (color.contains(Color.cl("3"))) {
          team = EnumBWTeam.TÜRKIS;
        }
        if (color.contains(Color.cl("6"))) {
          team = EnumBWTeam.ORANGE;
        }
        if (color.contains(Color.cl("d"))) {
          team = EnumBWTeam.PINK;
        }
        if (team != null)
        {
          boolean contains = false;
          for (Hologram holo : Manager.getHolograms()) {
            if (((holo.memory instanceof EnumBWTeam)) && ((EnumBWTeam)holo.memory == team)) {
              contains = true;
            }
          }
          if (!contains)
          {
            x = entity.c().p();
            y = entity.c().q();
            z = entity.c().r();
            Hologram hl = new Hologram(Utils.normalizeString(team.name()) + getBedStatus(team), (int)x, (int)y + 20, (int)z, bindColor(team));
            hl.memory = team;
            Manager.holograms.add(hl);
          }
        }
      }
      catch (Exception localException) {}
    } else if (entity.F) {
      respawn.add(entity.bc());
    }
  }
  
  public static void updateHolograms()
  {
    for (Hologram holo : Manager.holograms) {
      if ((holo.memory instanceof EnumBWTeam))
      {
        EnumBWTeam team = (EnumBWTeam)holo.memory;
        if (holo != null) {
          holo.text = (Utils.normalizeString(team.name()) + getBedStatus(team));
        }
      }
    }
  }
}
