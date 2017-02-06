package org.h2.security;

import org.h2.util.Utils;

public class Fog
  implements BlockCipher
{
  private int key;
  
  public void encrypt(byte[] bytes, int off, int len)
  {
    for (int i = off; i < off + len; i += 16) {
      encryptBlock(bytes, bytes, i);
    }
  }
  
  public void decrypt(byte[] bytes, int off, int len)
  {
    for (int i = off; i < off + len; i += 16) {
      decryptBlock(bytes, bytes, i);
    }
  }
  
  private void encryptBlock(byte[] in, byte[] out, int off)
  {
    int x0 = in[off] << 24 | (in[(off + 1)] & 0xFF) << 16 | (in[(off + 2)] & 0xFF) << 8 | in[(off + 3)] & 0xFF;
    
    int x1 = in[(off + 4)] << 24 | (in[(off + 5)] & 0xFF) << 16 | (in[(off + 6)] & 0xFF) << 8 | in[(off + 7)] & 0xFF;
    
    int x2 = in[(off + 8)] << 24 | (in[(off + 9)] & 0xFF) << 16 | (in[(off + 10)] & 0xFF) << 8 | in[(off + 11)] & 0xFF;
    
    int x3 = in[(off + 12)] << 24 | (in[(off + 13)] & 0xFF) << 16 | (in[(off + 14)] & 0xFF) << 8 | in[(off + 15)] & 0xFF;
    
    int k = this.key;
    int s = x1 & 0x1F;
    x0 ^= k;
    x0 = x0 << s | x0 >>> 32 - s;
    x2 ^= k;
    x2 = x2 << s | x2 >>> 32 - s;
    s = x0 & 0x1F;
    x1 ^= k;
    x1 = x1 << s | x1 >>> 32 - s;
    x3 ^= k;
    x3 = x3 << s | x3 >>> 32 - s;
    out[off] = ((byte)(x0 >> 24));out[(off + 1)] = ((byte)(x0 >> 16));
    out[(off + 2)] = ((byte)(x0 >> 8));out[(off + 3)] = ((byte)x0);
    out[(off + 4)] = ((byte)(x1 >> 24));out[(off + 5)] = ((byte)(x1 >> 16));
    out[(off + 6)] = ((byte)(x1 >> 8));out[(off + 7)] = ((byte)x1);
    out[(off + 8)] = ((byte)(x2 >> 24));out[(off + 9)] = ((byte)(x2 >> 16));
    out[(off + 10)] = ((byte)(x2 >> 8));out[(off + 11)] = ((byte)x2);
    out[(off + 12)] = ((byte)(x3 >> 24));out[(off + 13)] = ((byte)(x3 >> 16));
    out[(off + 14)] = ((byte)(x3 >> 8));out[(off + 15)] = ((byte)x3);
  }
  
  private void decryptBlock(byte[] in, byte[] out, int off)
  {
    int x0 = in[off] << 24 | (in[(off + 1)] & 0xFF) << 16 | (in[(off + 2)] & 0xFF) << 8 | in[(off + 3)] & 0xFF;
    
    int x1 = in[(off + 4)] << 24 | (in[(off + 5)] & 0xFF) << 16 | (in[(off + 6)] & 0xFF) << 8 | in[(off + 7)] & 0xFF;
    
    int x2 = in[(off + 8)] << 24 | (in[(off + 9)] & 0xFF) << 16 | (in[(off + 10)] & 0xFF) << 8 | in[(off + 11)] & 0xFF;
    
    int x3 = in[(off + 12)] << 24 | (in[(off + 13)] & 0xFF) << 16 | (in[(off + 14)] & 0xFF) << 8 | in[(off + 15)] & 0xFF;
    
    int k = this.key;
    int s = 32 - (x0 & 0x1F);
    x1 = x1 << s | x1 >>> 32 - s;
    x1 ^= k;
    x3 = x3 << s | x3 >>> 32 - s;
    x3 ^= k;
    s = 32 - (x1 & 0x1F);
    x0 = x0 << s | x0 >>> 32 - s;
    x0 ^= k;
    x2 = x2 << s | x2 >>> 32 - s;
    x2 ^= k;
    out[off] = ((byte)(x0 >> 24));out[(off + 1)] = ((byte)(x0 >> 16));
    out[(off + 2)] = ((byte)(x0 >> 8));out[(off + 3)] = ((byte)x0);
    out[(off + 4)] = ((byte)(x1 >> 24));out[(off + 5)] = ((byte)(x1 >> 16));
    out[(off + 6)] = ((byte)(x1 >> 8));out[(off + 7)] = ((byte)x1);
    out[(off + 8)] = ((byte)(x2 >> 24));out[(off + 9)] = ((byte)(x2 >> 16));
    out[(off + 10)] = ((byte)(x2 >> 8));out[(off + 11)] = ((byte)x2);
    out[(off + 12)] = ((byte)(x3 >> 24));out[(off + 13)] = ((byte)(x3 >> 16));
    out[(off + 14)] = ((byte)(x3 >> 8));out[(off + 15)] = ((byte)x3);
  }
  
  public int getKeyLength()
  {
    return 16;
  }
  
  public void setKey(byte[] key)
  {
    this.key = ((int)Utils.readLong(key, 0));
  }
}
