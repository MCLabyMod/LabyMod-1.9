package org.h2.util;

import org.h2.message.DbException;

public class Permutations<T>
{
  private final T[] in;
  private final T[] out;
  private final int n;
  private final int m;
  private final int[] index;
  private boolean hasNext = true;
  
  private Permutations(T[] in, T[] out, int m)
  {
    this.n = in.length;
    this.m = m;
    if ((this.n < m) || (m < 0)) {
      DbException.throwInternalError("n < m or m < 0");
    }
    this.in = in;
    this.out = out;
    this.index = new int[this.n];
    for (int i = 0; i < this.n; i++) {
      this.index[i] = i;
    }
    reverseAfter(m - 1);
  }
  
  public static <T> Permutations<T> create(T[] in, T[] out)
  {
    return new Permutations(in, out, in.length);
  }
  
  public static <T> Permutations<T> create(T[] in, T[] out, int m)
  {
    return new Permutations(in, out, m);
  }
  
  private void moveIndex()
  {
    int i = rightmostDip();
    if (i < 0)
    {
      this.hasNext = false;
      return;
    }
    int leastToRightIndex = i + 1;
    for (int j = i + 2; j < this.n; j++) {
      if ((this.index[j] < this.index[leastToRightIndex]) && (this.index[j] > this.index[i])) {
        leastToRightIndex = j;
      }
    }
    int t = this.index[i];
    this.index[i] = this.index[leastToRightIndex];
    this.index[leastToRightIndex] = t;
    if (this.m - 1 > i)
    {
      reverseAfter(i);
      
      reverseAfter(this.m - 1);
    }
  }
  
  private int rightmostDip()
  {
    for (int i = this.n - 2; i >= 0; i--) {
      if (this.index[i] < this.index[(i + 1)]) {
        return i;
      }
    }
    return -1;
  }
  
  private void reverseAfter(int i)
  {
    int start = i + 1;
    int end = this.n - 1;
    while (start < end)
    {
      int t = this.index[start];
      this.index[start] = this.index[end];
      this.index[end] = t;
      start++;
      end--;
    }
  }
  
  public boolean next()
  {
    if (!this.hasNext) {
      return false;
    }
    for (int i = 0; i < this.m; i++) {
      this.out[i] = this.in[this.index[i]];
    }
    moveIndex();
    return true;
  }
}
