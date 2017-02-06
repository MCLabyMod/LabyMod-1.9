package org.h2.mvstore.rtree;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.h2.mvstore.DataUtils;
import org.h2.mvstore.WriteBuffer;
import org.h2.mvstore.type.DataType;

public class SpatialDataType
  implements DataType
{
  private final int dimensions;
  
  public SpatialDataType(int dimensions)
  {
    DataUtils.checkArgument((dimensions >= 1) && (dimensions < 32), "Dimensions must be between 1 and 31, is {0}", new Object[] { Integer.valueOf(dimensions) });
    
    this.dimensions = dimensions;
  }
  
  public int compare(Object a, Object b)
  {
    if (a == b) {
      return 0;
    }
    if (a == null) {
      return -1;
    }
    if (b == null) {
      return 1;
    }
    long la = ((SpatialKey)a).getId();
    long lb = ((SpatialKey)b).getId();
    return la > lb ? 1 : la < lb ? -1 : 0;
  }
  
  public boolean equals(Object a, Object b)
  {
    if (a == b) {
      return true;
    }
    if ((a == null) || (b == null)) {
      return false;
    }
    long la = ((SpatialKey)a).getId();
    long lb = ((SpatialKey)b).getId();
    return la == lb;
  }
  
  public int getMemory(Object obj)
  {
    return 40 + this.dimensions * 4;
  }
  
  public void read(ByteBuffer buff, Object[] obj, int len, boolean key)
  {
    for (int i = 0; i < len; i++) {
      obj[i] = read(buff);
    }
  }
  
  public void write(WriteBuffer buff, Object[] obj, int len, boolean key)
  {
    for (int i = 0; i < len; i++) {
      write(buff, obj[i]);
    }
  }
  
  public void write(WriteBuffer buff, Object obj)
  {
    if (obj == null)
    {
      buff.putVarInt(-1);
      return;
    }
    SpatialKey k = (SpatialKey)obj;
    int flags = 0;
    for (int i = 0; i < this.dimensions; i++) {
      if (k.min(i) == k.max(i)) {
        flags |= 1 << i;
      }
    }
    buff.putVarInt(flags);
    for (int i = 0; i < this.dimensions; i++)
    {
      buff.putFloat(k.min(i));
      if ((flags & 1 << i) == 0) {
        buff.putFloat(k.max(i));
      }
    }
    buff.putVarLong(k.getId());
  }
  
  public Object read(ByteBuffer buff)
  {
    int flags = DataUtils.readVarInt(buff);
    if (flags == -1) {
      return null;
    }
    float[] minMax = new float[this.dimensions * 2];
    for (int i = 0; i < this.dimensions; i++)
    {
      float min = buff.getFloat();
      float max;
      float max;
      if ((flags & 1 << i) != 0) {
        max = min;
      } else {
        max = buff.getFloat();
      }
      minMax[(i + i)] = min;
      minMax[(i + i + 1)] = max;
    }
    long id = DataUtils.readVarLong(buff);
    return new SpatialKey(id, minMax);
  }
  
  public boolean isOverlap(Object objA, Object objB)
  {
    SpatialKey a = (SpatialKey)objA;
    SpatialKey b = (SpatialKey)objB;
    for (int i = 0; i < this.dimensions; i++) {
      if ((a.max(i) < b.min(i)) || (a.min(i) > b.max(i))) {
        return false;
      }
    }
    return true;
  }
  
  public void increaseBounds(Object bounds, Object add)
  {
    SpatialKey b = (SpatialKey)bounds;
    SpatialKey a = (SpatialKey)add;
    for (int i = 0; i < this.dimensions; i++)
    {
      b.setMin(i, Math.min(b.min(i), a.min(i)));
      b.setMax(i, Math.max(b.max(i), a.max(i)));
    }
  }
  
  public float getAreaIncrease(Object objA, Object objB)
  {
    SpatialKey a = (SpatialKey)objA;
    SpatialKey b = (SpatialKey)objB;
    float min = a.min(0);
    float max = a.max(0);
    float areaOld = max - min;
    min = Math.min(min, b.min(0));
    max = Math.max(max, b.max(0));
    float areaNew = max - min;
    for (int i = 1; i < this.dimensions; i++)
    {
      min = a.min(i);
      max = a.max(i);
      areaOld *= (max - min);
      min = Math.min(min, b.min(i));
      max = Math.max(max, b.max(i));
      areaNew *= (max - min);
    }
    return areaNew - areaOld;
  }
  
  float getCombinedArea(Object objA, Object objB)
  {
    SpatialKey a = (SpatialKey)objA;
    SpatialKey b = (SpatialKey)objB;
    float area = 1.0F;
    for (int i = 0; i < this.dimensions; i++)
    {
      float min = Math.min(a.min(i), b.min(i));
      float max = Math.max(a.max(i), b.max(i));
      area *= (max - min);
    }
    return area;
  }
  
  public boolean contains(Object objA, Object objB)
  {
    SpatialKey a = (SpatialKey)objA;
    SpatialKey b = (SpatialKey)objB;
    for (int i = 0; i < this.dimensions; i++) {
      if ((a.min(i) > b.min(i)) || (a.max(i) < b.max(i))) {
        return false;
      }
    }
    return true;
  }
  
  public boolean isInside(Object objA, Object objB)
  {
    SpatialKey a = (SpatialKey)objA;
    SpatialKey b = (SpatialKey)objB;
    for (int i = 0; i < this.dimensions; i++) {
      if ((a.min(i) <= b.min(i)) || (a.max(i) >= b.max(i))) {
        return false;
      }
    }
    return true;
  }
  
  Object createBoundingBox(Object objA)
  {
    float[] minMax = new float[this.dimensions * 2];
    SpatialKey a = (SpatialKey)objA;
    for (int i = 0; i < this.dimensions; i++)
    {
      minMax[(i + i)] = a.min(i);
      minMax[(i + i + 1)] = a.max(i);
    }
    return new SpatialKey(0L, minMax);
  }
  
  public int[] getExtremes(ArrayList<Object> list)
  {
    SpatialKey bounds = (SpatialKey)createBoundingBox(list.get(0));
    SpatialKey boundsInner = (SpatialKey)createBoundingBox(bounds);
    for (int i = 0; i < this.dimensions; i++)
    {
      float t = boundsInner.min(i);
      boundsInner.setMin(i, boundsInner.max(i));
      boundsInner.setMax(i, t);
    }
    for (int i = 0; i < list.size(); i++)
    {
      Object o = list.get(i);
      increaseBounds(bounds, o);
      increaseMaxInnerBounds(boundsInner, o);
    }
    double best = 0.0D;
    int bestDim = 0;
    for (int i = 0; i < this.dimensions; i++)
    {
      float inner = boundsInner.max(i) - boundsInner.min(i);
      if (inner >= 0.0F)
      {
        float outer = bounds.max(i) - bounds.min(i);
        float d = inner / outer;
        if (d > best)
        {
          best = d;
          bestDim = i;
        }
      }
    }
    if (best <= 0.0D) {
      return null;
    }
    float min = boundsInner.min(bestDim);
    float max = boundsInner.max(bestDim);
    int firstIndex = -1;int lastIndex = -1;
    for (int i = 0; (i < list.size()) && ((firstIndex < 0) || (lastIndex < 0)); i++)
    {
      SpatialKey o = (SpatialKey)list.get(i);
      if ((firstIndex < 0) && (o.max(bestDim) == min)) {
        firstIndex = i;
      } else if ((lastIndex < 0) && (o.min(bestDim) == max)) {
        lastIndex = i;
      }
    }
    return new int[] { firstIndex, lastIndex };
  }
  
  private void increaseMaxInnerBounds(Object bounds, Object add)
  {
    SpatialKey b = (SpatialKey)bounds;
    SpatialKey a = (SpatialKey)add;
    for (int i = 0; i < this.dimensions; i++)
    {
      b.setMin(i, Math.min(b.min(i), a.max(i)));
      b.setMax(i, Math.max(b.max(i), a.min(i)));
    }
  }
}
