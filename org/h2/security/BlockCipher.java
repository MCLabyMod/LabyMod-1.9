package org.h2.security;

public abstract interface BlockCipher
{
  public static final int ALIGN = 16;
  
  public abstract void setKey(byte[] paramArrayOfByte);
  
  public abstract void encrypt(byte[] paramArrayOfByte, int paramInt1, int paramInt2);
  
  public abstract void decrypt(byte[] paramArrayOfByte, int paramInt1, int paramInt2);
  
  public abstract int getKeyLength();
}
