package net.minecraft.realms;

import azj;
import azk;
import azl;
import com.google.common.collect.Lists;
import java.util.List;
import op;

public class RealmsAnvilLevelStorageSource
{
  private azk levelStorageSource;
  
  public RealmsAnvilLevelStorageSource(azk ☃)
  {
    this.levelStorageSource = ☃;
  }
  
  public String getName()
  {
    return this.levelStorageSource.a();
  }
  
  public boolean levelExists(String ☃)
  {
    return this.levelStorageSource.f(☃);
  }
  
  public boolean convertLevel(String ☃, op ☃)
  {
    return this.levelStorageSource.a(☃, ☃);
  }
  
  public boolean requiresConversion(String ☃)
  {
    return this.levelStorageSource.b(☃);
  }
  
  public boolean isNewLevelIdAcceptable(String ☃)
  {
    return this.levelStorageSource.d(☃);
  }
  
  public boolean deleteLevel(String ☃)
  {
    return this.levelStorageSource.e(☃);
  }
  
  public boolean isConvertible(String ☃)
  {
    return this.levelStorageSource.a(☃);
  }
  
  public void renameLevel(String ☃, String ☃)
  {
    this.levelStorageSource.a(☃, ☃);
  }
  
  public void clearAll()
  {
    this.levelStorageSource.d();
  }
  
  public List<RealmsLevelSummary> getLevelList()
    throws azj
  {
    List<RealmsLevelSummary> ☃ = Lists.newArrayList();
    for (azl ☃ : this.levelStorageSource.b()) {
      ☃.add(new RealmsLevelSummary(☃));
    }
    return ☃;
  }
}
