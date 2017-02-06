package org.h2.tools;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.h2.util.New;
import org.h2.util.StringUtils;

public class MultiDimension
  implements Comparator<long[]>
{
  private static final MultiDimension INSTANCE = new MultiDimension();
  
  public static MultiDimension getInstance()
  {
    return INSTANCE;
  }
  
  public int normalize(int dimensions, double value, double min, double max)
  {
    if ((value < min) || (value > max)) {
      throw new IllegalArgumentException(min + "<" + value + "<" + max);
    }
    double x = (value - min) / (max - min);
    return (int)(x * getMaxValue(dimensions));
  }
  
  public int getMaxValue(int dimensions)
  {
    if ((dimensions < 2) || (dimensions > 32)) {
      throw new IllegalArgumentException("" + dimensions);
    }
    int bitsPerValue = getBitsPerValue(dimensions);
    return (int)((1L << bitsPerValue) - 1L);
  }
  
  private static int getBitsPerValue(int dimensions)
  {
    return Math.min(31, 64 / dimensions);
  }
  
  public long interleave(int... values)
  {
    int dimensions = values.length;
    long max = getMaxValue(dimensions);
    int bitsPerValue = getBitsPerValue(dimensions);
    long x = 0L;
    for (int i = 0; i < dimensions; i++)
    {
      long k = values[i];
      if ((k < 0L) || (k > max)) {
        throw new IllegalArgumentException("0<" + k + "<" + max);
      }
      for (int b = 0; b < bitsPerValue; b++) {
        x |= (k & 1L << b) << i + (dimensions - 1) * b;
      }
    }
    return x;
  }
  
  public long interleave(int x, int y)
  {
    if (x < 0) {
      throw new IllegalArgumentException("0<" + x);
    }
    if (y < 0) {
      throw new IllegalArgumentException("0<" + y);
    }
    long z = 0L;
    for (int i = 0; i < 32; i++)
    {
      z |= (x & 1L << i) << i;
      z |= (y & 1L << i) << i + 1;
    }
    return z;
  }
  
  public int deinterleave(int dimensions, long scalar, int dim)
  {
    int bitsPerValue = getBitsPerValue(dimensions);
    int value = 0;
    for (int i = 0; i < bitsPerValue; i++) {
      value = (int)(value | scalar >> dim + (dimensions - 1) * i & 1L << i);
    }
    return value;
  }
  
  public String generatePreparedQuery(String table, String scalarColumn, String[] columns)
  {
    StringBuilder buff = new StringBuilder("SELECT D.* FROM ");
    buff.append(StringUtils.quoteIdentifier(table)).append(" D, TABLE(_FROM_ BIGINT=?, _TO_ BIGINT=?) WHERE ").append(StringUtils.quoteIdentifier(scalarColumn)).append(" BETWEEN _FROM_ AND _TO_");
    for (String col : columns) {
      buff.append(" AND ").append(StringUtils.quoteIdentifier(col)).append("+1 BETWEEN ?+1 AND ?+1");
    }
    return buff.toString();
  }
  
  public ResultSet getResult(PreparedStatement prep, int[] min, int[] max)
    throws SQLException
  {
    long[][] ranges = getMortonRanges(min, max);
    int len = ranges.length;
    Long[] from = new Long[len];
    Long[] to = new Long[len];
    for (int i = 0; i < len; i++)
    {
      from[i] = Long.valueOf(ranges[i][0]);
      to[i] = Long.valueOf(ranges[i][1]);
    }
    prep.setObject(1, from);
    prep.setObject(2, to);
    len = min.length;
    int i = 0;
    for (int idx = 3; i < len; i++)
    {
      prep.setInt(idx++, min[i]);
      prep.setInt(idx++, max[i]);
    }
    return prep.executeQuery();
  }
  
  private long[][] getMortonRanges(int[] min, int[] max)
  {
    int len = min.length;
    if (max.length != len) {
      throw new IllegalArgumentException(len + "=" + max.length);
    }
    for (int i = 0; i < len; i++) {
      if (min[i] > max[i])
      {
        int temp = min[i];
        min[i] = max[i];
        max[i] = temp;
      }
    }
    int total = getSize(min, max, len);
    ArrayList<long[]> list = New.arrayList();
    addMortonRanges(list, min, max, len, 0);
    combineEntries(list, total);
    long[][] ranges = new long[list.size()][2];
    list.toArray(ranges);
    return ranges;
  }
  
  private static int getSize(int[] min, int[] max, int len)
  {
    int size = 1;
    for (int i = 0; i < len; i++)
    {
      int diff = max[i] - min[i];
      size *= (diff + 1);
    }
    return size;
  }
  
  private void combineEntries(ArrayList<long[]> list, int total)
  {
    Collections.sort(list, this);
    for (int minGap = 10; minGap < total; minGap += minGap / 2)
    {
      for (int i = 0; i < list.size() - 1; i++)
      {
        long[] current = (long[])list.get(i);
        long[] next = (long[])list.get(i + 1);
        if (current[1] + minGap >= next[0])
        {
          current[1] = next[1];
          list.remove(i + 1);
          i--;
        }
      }
      int searched = 0;
      for (long[] range : list) {
        searched = (int)(searched + (range[1] - range[0] + 1L));
      }
      if ((searched > 2 * total) || (list.size() < 100)) {
        break;
      }
    }
  }
  
  public int compare(long[] a, long[] b)
  {
    return a[0] > b[0] ? 1 : -1;
  }
  
  private void addMortonRanges(ArrayList<long[]> list, int[] min, int[] max, int len, int level)
  {
    if (level > 100) {
      throw new IllegalArgumentException("" + level);
    }
    int largest = 0;int largestDiff = 0;
    long size = 1L;
    for (int i = 0; i < len; i++)
    {
      int diff = max[i] - min[i];
      if (diff < 0) {
        throw new IllegalArgumentException("" + diff);
      }
      size *= (diff + 1);
      if (size < 0L) {
        throw new IllegalArgumentException("" + size);
      }
      if (diff > largestDiff)
      {
        largestDiff = diff;
        largest = i;
      }
    }
    long low = interleave(min);long high = interleave(max);
    if (high < low) {
      throw new IllegalArgumentException(high + "<" + low);
    }
    long range = high - low + 1L;
    if (range == size)
    {
      long[] item = { low, high };
      list.add(item);
    }
    else
    {
      int middle = findMiddle(min[largest], max[largest]);
      int temp = max[largest];
      max[largest] = middle;
      addMortonRanges(list, min, max, len, level + 1);
      max[largest] = temp;
      temp = min[largest];
      min[largest] = (middle + 1);
      addMortonRanges(list, min, max, len, level + 1);
      min[largest] = temp;
    }
  }
  
  private static int roundUp(int x, int blockSizePowerOf2)
  {
    return x + blockSizePowerOf2 - 1 & -blockSizePowerOf2;
  }
  
  private static int findMiddle(int a, int b)
  {
    int diff = b - a - 1;
    if (diff == 0) {
      return a;
    }
    if (diff == 1) {
      return a + 1;
    }
    int scale = 0;
    while (1 << scale < diff) {
      scale++;
    }
    scale--;
    int m = roundUp(a + 2, 1 << scale) - 1;
    if ((m <= a) || (m >= b)) {
      throw new IllegalArgumentException(a + "<" + m + "<" + b);
    }
    return m;
  }
}
