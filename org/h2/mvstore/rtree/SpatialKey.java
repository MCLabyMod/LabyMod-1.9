package org.h2.mvstore.rtree;

import java.util.Arrays;

public class SpatialKey
{
  private final long id;
  private final float[] minMax;
  
  public SpatialKey(long id, float... minMax)
  {
    this.id = id;
    this.minMax = minMax;
  }
  
  public float min(int dim)
  {
    return this.minMax[(dim + dim)];
  }
  
  public void setMin(int dim, float x)
  {
    this.minMax[(dim + dim)] = x;
  }
  
  public float max(int dim)
  {
    return this.minMax[(dim + dim + 1)];
  }
  
  public void setMax(int dim, float x)
  {
    this.minMax[(dim + dim + 1)] = x;
  }
  
  public long getId()
  {
    return this.id;
  }
  
  public String toString()
  {
    StringBuilder buff = new StringBuilder();
    buff.append(this.id).append(": (");
    for (int i = 0; i < this.minMax.length; i += 2)
    {
      if (i > 0) {
        buff.append(", ");
      }
      buff.append(this.minMax[i]).append('/').append(this.minMax[(i + 1)]);
    }
    return ")";
  }
  
  public int hashCode()
  {
    return (int)(this.id >>> 32 ^ this.id);
  }
  
  public boolean equals(Object other)
  {
    if (other == this) {
      return true;
    }
    if (!(other instanceof SpatialKey)) {
      return false;
    }
    SpatialKey o = (SpatialKey)other;
    if (this.id != o.id) {
      return false;
    }
    return equalsIgnoringId(o);
  }
  
  public boolean equalsIgnoringId(SpatialKey o)
  {
    return Arrays.equals(this.minMax, o.minMax);
  }
}
