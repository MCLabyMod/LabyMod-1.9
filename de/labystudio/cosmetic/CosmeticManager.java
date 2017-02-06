package de.labystudio.cosmetic;

import bcf;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.ModSettings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import rr;

public class CosmeticManager
{
  private HashMap<UUID, ArrayList<Cosmetic>> cosmetics = new HashMap();
  public boolean colorPicker = false;
  public int colorR;
  public int colorG;
  public int colorB;
  
  public HashMap<UUID, ArrayList<Cosmetic>> getCosmetics()
  {
    return this.cosmetics;
  }
  
  public ArrayList<Cosmetic> getCosmetic(rr entityIn)
  {
    if (entityIn == null) {
      return null;
    }
    if (entityIn.bc() == null) {
      return null;
    }
    if (!ConfigManager.settings.cosmetics) {
      return null;
    }
    if (!this.cosmetics.containsKey(entityIn.bc())) {
      return null;
    }
    return (ArrayList)this.cosmetics.get(entityIn.bc());
  }
  
  public boolean hasCosmetic(EnumCosmetic[] types)
  {
    if (!LabyMod.getInstance().isInGame()) {
      return false;
    }
    ArrayList<Cosmetic> all = getCosmetic(bcf.z().h);
    if (all == null) {
      return false;
    }
    for (Cosmetic cos : all) {
      for (EnumCosmetic type : types) {
        if (type == cos.getType()) {
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean hasCosmetic(EnumCosmetic type)
  {
    return hasCosmetic(new EnumCosmetic[] { type });
  }
  
  public double getNameTagHeight(rr entityIn)
  {
    ArrayList<Cosmetic> cos = getCosmetic(entityIn);
    if (cos == null) {
      return 0.0D;
    }
    double out = 0.0D;
    for (Cosmetic cosmetic : cos) {
      if (cosmetic.height > out) {
        out = cosmetic.height;
      }
    }
    return out;
  }
}
