package org.h2.compress;

public abstract interface Compressor
{
  public static final int NO = 0;
  public static final int LZF = 1;
  public static final int DEFLATE = 2;
  
  public abstract int getAlgorithm();
  
  public abstract int compress(byte[] paramArrayOfByte1, int paramInt1, byte[] paramArrayOfByte2, int paramInt2);
  
  public abstract void expand(byte[] paramArrayOfByte1, int paramInt1, int paramInt2, byte[] paramArrayOfByte2, int paramInt3, int paramInt4);
  
  public abstract void setOptions(String paramString);
}
