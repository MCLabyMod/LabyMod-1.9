package de.labystudio.capes;

import bcf;
import bku;
import bmq;
import bnj;
import bnp;
import bvi;
import bvj;
import de.labystudio.capes.downloader.ThreadDownloadCapeData;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.ModSettings;
import de.labystudio.labymod.Source;
import de.labystudio.utils.Debug;
import de.labystudio.utils.Debug.EnumDebugMode;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.UUID;
import kk;
import org.apache.commons.io.FilenameUtils;
import zj;

public class CapeManager
{
  private ArrayList<String> userCapes = new ArrayList();
  
  public void downloadCape(final bmq player, boolean refresh, final boolean invert)
  {
    if (player == null) {
      return;
    }
    String username = player.getNameClear();
    if ((username != null) && (!username.isEmpty()) && (player.bc() != null))
    {
      final EnumCapePriority pr = getCapePriority();
      
      String url = null;
      if ((pr == EnumCapePriority.OPTIFINE) || ((invert) && (pr == EnumCapePriority.LABYMOD))) {
        url = Source.url_cape_optifine + username + ".png";
      }
      if (((pr == EnumCapePriority.LABYMOD) || ((invert) && (pr == EnumCapePriority.OPTIFINE))) && 
        (isWhitelisted(player.bc()))) {
        url = Source.url_cape_labymod + player.bc();
      }
      if (url == null)
      {
        if (invert) {
          return;
        }
        downloadCape(player, false, true);
        return;
      }
      String mptHash = FilenameUtils.getBaseName(url);
      final kk rl = new kk("capes/" + mptHash);
      bvi textureManager = bcf.z().N();
      
      bvj tex = textureManager.b(rl);
      if ((tex != null) && (!refresh) && 
        ((tex instanceof ThreadDownloadCapeData)))
      {
        ThreadDownloadCapeData tdid = (ThreadDownloadCapeData)tex;
        if (tdid.imageFound != null)
        {
          if (tdid.imageFound.booleanValue()) {
            player.setLocationOfCape(rl, pr);
          }
          return;
        }
      }
      final bmq thePlayer = player;
      bnj iib = new bnj()
      {
        bnp ibd = new bnp();
        
        public BufferedImage a(BufferedImage var1)
        {
          return CapeManager.parseCape(var1);
        }
        
        public void a()
        {
          thePlayer.setLocationOfCape(rl, pr);
        }
      };
      CapeCallback callBack = new CapeCallback()
      {
        public void failed(String error)
        {
          if (invert) {
            return;
          }
          CapeManager.this.downloadCape(player, false, true);
        }
        
        public void done() {}
      };
      ThreadDownloadCapeData textureCape = new ThreadDownloadCapeData(null, url, null, iib, callBack);
      textureManager.a(rl, textureCape);
    }
  }
  
  public static BufferedImage parseCape(BufferedImage img)
  {
    int imageWidth = 64;
    int imageHeight = 32;
    
    BufferedImage srcImg = img;
    int srcWidth = srcImg.getWidth();
    int srcHeight = srcImg.getHeight();
    while ((imageWidth < srcWidth) || (imageHeight < srcHeight))
    {
      imageWidth *= 2;
      imageHeight *= 2;
    }
    BufferedImage imgNew = new BufferedImage(imageWidth, imageHeight, 2);
    Graphics g = imgNew.getGraphics();
    g.drawImage(img, 0, 0, (ImageObserver)null);
    g.dispose();
    return imgNew;
  }
  
  public void setUserCapes(ArrayList<String> userCapes)
  {
    this.userCapes = userCapes;
  }
  
  public boolean isWhitelisted(UUID uuid)
  {
    Debug.debug(Debug.EnumDebugMode.CAPES, "Is whitelisted? " + uuid);
    for (String user : this.userCapes) {
      if ((user != null) && (uuid.toString().startsWith(user)) && (!user.isEmpty()))
      {
        Debug.debug(Debug.EnumDebugMode.CAPES, "IS WHITELISTED because " + uuid.toString() + " starts with " + user);
        return true;
      }
    }
    Debug.debug(Debug.EnumDebugMode.CAPES, "skip..");
    return false;
  }
  
  public void refresh()
  {
    if (!LabyMod.getInstance().isInGame()) {
      return;
    }
    int amount = 0;
    ArrayList<zj> list = new ArrayList();
    list.addAll(bcf.z().f.i);
    for (zj player : list) {
      if ((player != null) && ((player instanceof bmq)))
      {
        LabyMod.getInstance().getCapeManager().downloadCape((bmq)player, true, false);
        amount++;
      }
    }
    System.out.println("[LabyMod] Refreshed " + amount + " mod capes");
  }
  
  public EnumCapePriority getCapePriority()
  {
    if (ConfigManager.settings.capePriority.equals("of")) {
      return EnumCapePriority.OPTIFINE;
    }
    if (ConfigManager.settings.capePriority.equals("original")) {
      return EnumCapePriority.ORIGINAL;
    }
    return EnumCapePriority.LABYMOD;
  }
}
