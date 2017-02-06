package org.h2.compress;

import java.nio.ByteBuffer;

public final class CompressLZF
  implements Compressor
{
  private static final int HASH_SIZE = 16384;
  private static final int MAX_LITERAL = 32;
  private static final int MAX_OFF = 8192;
  private static final int MAX_REF = 264;
  private int[] cachedHashTable;
  
  public void setOptions(String options) {}
  
  private static int first(byte[] in, int inPos)
  {
    return in[inPos] << 8 | in[(inPos + 1)] & 0xFF;
  }
  
  private static int first(ByteBuffer in, int inPos)
  {
    return in.get(inPos) << 8 | in.get(inPos + 1) & 0xFF;
  }
  
  private static int next(int v, byte[] in, int inPos)
  {
    return v << 8 | in[(inPos + 2)] & 0xFF;
  }
  
  private static int next(int v, ByteBuffer in, int inPos)
  {
    return v << 8 | in.get(inPos + 2) & 0xFF;
  }
  
  private static int hash(int h)
  {
    return h * 2777 >> 9 & 0x3FFF;
  }
  
  public int compress(byte[] in, int inLen, byte[] out, int outPos)
  {
    int inPos = 0;
    if (this.cachedHashTable == null) {
      this.cachedHashTable = new int['䀀'];
    }
    int[] hashTab = this.cachedHashTable;
    int literals = 0;
    outPos++;
    int future = first(in, 0);
    while (inPos < inLen - 4)
    {
      byte p2 = in[(inPos + 2)];
      
      future = (future << 8) + (p2 & 0xFF);
      int off = hash(future);
      int ref = hashTab[off];
      hashTab[off] = inPos;
      if ((ref < inPos) && (ref > 0) && ((off = inPos - ref - 1) < 8192) && (in[(ref + 2)] == p2) && (in[(ref + 1)] == (byte)(future >> 8)) && (in[ref] == (byte)(future >> 16)))
      {
        int maxLen = inLen - inPos - 2;
        if (maxLen > 264) {
          maxLen = 264;
        }
        if (literals == 0)
        {
          outPos--;
        }
        else
        {
          out[(outPos - literals - 1)] = ((byte)(literals - 1));
          literals = 0;
        }
        int len = 3;
        while ((len < maxLen) && (in[(ref + len)] == in[(inPos + len)])) {
          len++;
        }
        len -= 2;
        if (len < 7)
        {
          out[(outPos++)] = ((byte)((off >> 8) + (len << 5)));
        }
        else
        {
          out[(outPos++)] = ((byte)((off >> 8) + 224));
          out[(outPos++)] = ((byte)(len - 7));
        }
        out[(outPos++)] = ((byte)off);
        
        outPos++;
        inPos += len;
        
        future = first(in, inPos);
        future = next(future, in, inPos);
        hashTab[hash(future)] = (inPos++);
        future = next(future, in, inPos);
        hashTab[hash(future)] = (inPos++);
      }
      else
      {
        out[(outPos++)] = in[(inPos++)];
        literals++;
        if (literals == 32)
        {
          out[(outPos - literals - 1)] = ((byte)(literals - 1));
          literals = 0;
          
          outPos++;
        }
      }
    }
    while (inPos < inLen)
    {
      out[(outPos++)] = in[(inPos++)];
      literals++;
      if (literals == 32)
      {
        out[(outPos - literals - 1)] = ((byte)(literals - 1));
        literals = 0;
        outPos++;
      }
    }
    out[(outPos - literals - 1)] = ((byte)(literals - 1));
    if (literals == 0) {
      outPos--;
    }
    return outPos;
  }
  
  public int compress(ByteBuffer in, int inPos, byte[] out, int outPos)
  {
    int inLen = in.capacity() - inPos;
    if (this.cachedHashTable == null) {
      this.cachedHashTable = new int['䀀'];
    }
    int[] hashTab = this.cachedHashTable;
    int literals = 0;
    outPos++;
    int future = first(in, 0);
    while (inPos < inLen - 4)
    {
      byte p2 = in.get(inPos + 2);
      
      future = (future << 8) + (p2 & 0xFF);
      int off = hash(future);
      int ref = hashTab[off];
      hashTab[off] = inPos;
      if ((ref < inPos) && (ref > 0) && ((off = inPos - ref - 1) < 8192) && (in.get(ref + 2) == p2) && (in.get(ref + 1) == (byte)(future >> 8)) && (in.get(ref) == (byte)(future >> 16)))
      {
        int maxLen = inLen - inPos - 2;
        if (maxLen > 264) {
          maxLen = 264;
        }
        if (literals == 0)
        {
          outPos--;
        }
        else
        {
          out[(outPos - literals - 1)] = ((byte)(literals - 1));
          literals = 0;
        }
        int len = 3;
        while ((len < maxLen) && (in.get(ref + len) == in.get(inPos + len))) {
          len++;
        }
        len -= 2;
        if (len < 7)
        {
          out[(outPos++)] = ((byte)((off >> 8) + (len << 5)));
        }
        else
        {
          out[(outPos++)] = ((byte)((off >> 8) + 224));
          out[(outPos++)] = ((byte)(len - 7));
        }
        out[(outPos++)] = ((byte)off);
        
        outPos++;
        inPos += len;
        
        future = first(in, inPos);
        future = next(future, in, inPos);
        hashTab[hash(future)] = (inPos++);
        future = next(future, in, inPos);
        hashTab[hash(future)] = (inPos++);
      }
      else
      {
        out[(outPos++)] = in.get(inPos++);
        literals++;
        if (literals == 32)
        {
          out[(outPos - literals - 1)] = ((byte)(literals - 1));
          literals = 0;
          
          outPos++;
        }
      }
    }
    while (inPos < inLen)
    {
      out[(outPos++)] = in.get(inPos++);
      literals++;
      if (literals == 32)
      {
        out[(outPos - literals - 1)] = ((byte)(literals - 1));
        literals = 0;
        outPos++;
      }
    }
    out[(outPos - literals - 1)] = ((byte)(literals - 1));
    if (literals == 0) {
      outPos--;
    }
    return outPos;
  }
  
  public void expand(byte[] in, int inPos, int inLen, byte[] out, int outPos, int outLen)
  {
    if ((inPos < 0) || (outPos < 0) || (outLen < 0)) {
      throw new IllegalArgumentException();
    }
    do
    {
      int ctrl = in[(inPos++)] & 0xFF;
      if (ctrl < 32)
      {
        ctrl++;
        
        System.arraycopy(in, inPos, out, outPos, ctrl);
        outPos += ctrl;
        inPos += ctrl;
      }
      else
      {
        int len = ctrl >> 5;
        if (len == 7) {
          len += (in[(inPos++)] & 0xFF);
        }
        len += 2;
        
        ctrl = -((ctrl & 0x1F) << 8) - 1;
        
        ctrl -= (in[(inPos++)] & 0xFF);
        
        ctrl += outPos;
        if (outPos + len >= out.length) {
          throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = 0; i < len; i++) {
          out[(outPos++)] = out[(ctrl++)];
        }
      }
    } while (outPos < outLen);
  }
  
  public static void expand(ByteBuffer in, ByteBuffer out)
  {
    do
    {
      int ctrl = in.get() & 0xFF;
      if (ctrl < 32)
      {
        ctrl++;
        for (int i = 0; i < ctrl; i++) {
          out.put(in.get());
        }
      }
      else
      {
        int len = ctrl >> 5;
        if (len == 7) {
          len += (in.get() & 0xFF);
        }
        len += 2;
        
        ctrl = -((ctrl & 0x1F) << 8) - 1;
        
        ctrl -= (in.get() & 0xFF);
        
        ctrl += out.position();
        for (int i = 0; i < len; i++) {
          out.put(out.get(ctrl++));
        }
      }
    } while (out.position() < out.capacity());
  }
  
  public int getAlgorithm()
  {
    return 1;
  }
}
