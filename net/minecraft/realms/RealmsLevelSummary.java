package net.minecraft.realms;

import ahw.a;
import azl;

public class RealmsLevelSummary
  implements Comparable<RealmsLevelSummary>
{
  private azl levelSummary;
  
  public RealmsLevelSummary(azl ☃)
  {
    this.levelSummary = ☃;
  }
  
  public int getGameMode()
  {
    return this.levelSummary.f().a();
  }
  
  public String getLevelId()
  {
    return this.levelSummary.a();
  }
  
  public boolean hasCheats()
  {
    return this.levelSummary.h();
  }
  
  public boolean isHardcore()
  {
    return this.levelSummary.g();
  }
  
  public boolean isRequiresConversion()
  {
    return this.levelSummary.d();
  }
  
  public String getLevelName()
  {
    return this.levelSummary.b();
  }
  
  public long getLastPlayed()
  {
    return this.levelSummary.e();
  }
  
  public int compareTo(azl ☃)
  {
    return this.levelSummary.a(☃);
  }
  
  public long getSizeOnDisk()
  {
    return this.levelSummary.c();
  }
  
  public int compareTo(RealmsLevelSummary ☃)
  {
    if (this.levelSummary.e() < ☃.getLastPlayed()) {
      return 1;
    }
    if (this.levelSummary.e() > ☃.getLastPlayed()) {
      return -1;
    }
    return this.levelSummary.a().compareTo(☃.getLevelId());
  }
}
