package de.labystudio.minecraft;

import aht;
import bcf;
import bks;
import bmt;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.ModSettings;
import de.labystudio.utils.Allowed;
import nu;

public class ThePlayerMod
  extends bmt
{
  public ThePlayerMod(bcf mcIn, aht worldIn, bks netHandler, nu statFile)
  {
    super(mcIn, worldIn, netHandler, statFile);
  }
  
  public boolean cs()
  {
    if ((ConfigManager.settings.oldBlockBuild) && (Allowed.blockBuild()) && (Thread.currentThread() != null) && 
      (Thread.currentThread().getStackTrace() != null) && (Thread.currentThread().getStackTrace().length > 2))
    {
      String name = Thread.currentThread().getStackTrace()[2].getMethodName();
      if ((name.equals("sendClickBlockToController")) || (name.equals("b"))) {
        return false;
      }
    }
    return super.cs();
  }
}
