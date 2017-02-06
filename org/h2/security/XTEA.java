package org.h2.security;

import org.h2.engine.SysProperties;
import org.h2.message.DbException;

public class XTEA
  implements BlockCipher
{
  private static final int DELTA = -1640531527;
  private int k0;
  private int k1;
  private int k2;
  private int k3;
  private int k4;
  private int k5;
  private int k6;
  private int k7;
  private int k8;
  private int k9;
  private int k10;
  private int k11;
  private int k12;
  private int k13;
  private int k14;
  private int k15;
  private int k16;
  private int k17;
  private int k18;
  private int k19;
  private int k20;
  private int k21;
  private int k22;
  private int k23;
  private int k24;
  private int k25;
  private int k26;
  private int k27;
  private int k28;
  private int k29;
  private int k30;
  private int k31;
  
  public void setKey(byte[] b)
  {
    int[] key = new int[4];
    for (int i = 0; i < 16;) {
      key[(i / 4)] = ((b[(i++)] << 24) + ((b[(i++)] & 0xFF) << 16) + ((b[(i++)] & 0xFF) << 8) + (b[(i++)] & 0xFF));
    }
    int[] r = new int[32];
    int i = 0;
    for (int sum = 0; i < 32;)
    {
      r[(i++)] = (sum + key[(sum & 0x3)]);
      sum -= 1640531527;
      r[(i++)] = (sum + key[(sum >>> 11 & 0x3)]);
    }
    this.k0 = r[0];this.k1 = r[1];this.k2 = r[2];this.k3 = r[3];
    this.k4 = r[4];this.k5 = r[5];this.k6 = r[6];this.k7 = r[7];
    this.k8 = r[8];this.k9 = r[9];this.k10 = r[10];this.k11 = r[11];
    this.k12 = r[12];this.k13 = r[13];this.k14 = r[14];this.k15 = r[15];
    this.k16 = r[16];this.k17 = r[17];this.k18 = r[18];this.k19 = r[19];
    this.k20 = r[20];this.k21 = r[21];this.k22 = r[22];this.k23 = r[23];
    this.k24 = r[24];this.k25 = r[25];this.k26 = r[26];this.k27 = r[27];
    this.k28 = r[28];this.k29 = r[29];this.k30 = r[30];this.k31 = r[31];
  }
  
  public void encrypt(byte[] bytes, int off, int len)
  {
    if ((SysProperties.CHECK) && 
      (len % 16 != 0)) {
      DbException.throwInternalError("unaligned len " + len);
    }
    for (int i = off; i < off + len; i += 8) {
      encryptBlock(bytes, bytes, i);
    }
  }
  
  public void decrypt(byte[] bytes, int off, int len)
  {
    if ((SysProperties.CHECK) && 
      (len % 16 != 0)) {
      DbException.throwInternalError("unaligned len " + len);
    }
    for (int i = off; i < off + len; i += 8) {
      decryptBlock(bytes, bytes, i);
    }
  }
  
  private void encryptBlock(byte[] in, byte[] out, int off)
  {
    int y = in[off] << 24 | (in[(off + 1)] & 0xFF) << 16 | (in[(off + 2)] & 0xFF) << 8 | in[(off + 3)] & 0xFF;
    
    int z = in[(off + 4)] << 24 | (in[(off + 5)] & 0xFF) << 16 | (in[(off + 6)] & 0xFF) << 8 | in[(off + 7)] & 0xFF;
    
    y += ((z << 4 ^ z >>> 5) + z ^ this.k0);
    z += ((y >>> 5 ^ y << 4) + y ^ this.k1);
    y += ((z << 4 ^ z >>> 5) + z ^ this.k2);
    z += ((y >>> 5 ^ y << 4) + y ^ this.k3);
    y += ((z << 4 ^ z >>> 5) + z ^ this.k4);
    z += ((y >>> 5 ^ y << 4) + y ^ this.k5);
    y += ((z << 4 ^ z >>> 5) + z ^ this.k6);
    z += ((y >>> 5 ^ y << 4) + y ^ this.k7);
    y += ((z << 4 ^ z >>> 5) + z ^ this.k8);
    z += ((y >>> 5 ^ y << 4) + y ^ this.k9);
    y += ((z << 4 ^ z >>> 5) + z ^ this.k10);
    z += ((y >>> 5 ^ y << 4) + y ^ this.k11);
    y += ((z << 4 ^ z >>> 5) + z ^ this.k12);
    z += ((y >>> 5 ^ y << 4) + y ^ this.k13);
    y += ((z << 4 ^ z >>> 5) + z ^ this.k14);
    z += ((y >>> 5 ^ y << 4) + y ^ this.k15);
    y += ((z << 4 ^ z >>> 5) + z ^ this.k16);
    z += ((y >>> 5 ^ y << 4) + y ^ this.k17);
    y += ((z << 4 ^ z >>> 5) + z ^ this.k18);
    z += ((y >>> 5 ^ y << 4) + y ^ this.k19);
    y += ((z << 4 ^ z >>> 5) + z ^ this.k20);
    z += ((y >>> 5 ^ y << 4) + y ^ this.k21);
    y += ((z << 4 ^ z >>> 5) + z ^ this.k22);
    z += ((y >>> 5 ^ y << 4) + y ^ this.k23);
    y += ((z << 4 ^ z >>> 5) + z ^ this.k24);
    z += ((y >>> 5 ^ y << 4) + y ^ this.k25);
    y += ((z << 4 ^ z >>> 5) + z ^ this.k26);
    z += ((y >>> 5 ^ y << 4) + y ^ this.k27);
    y += ((z << 4 ^ z >>> 5) + z ^ this.k28);
    z += ((y >>> 5 ^ y << 4) + y ^ this.k29);
    y += ((z << 4 ^ z >>> 5) + z ^ this.k30);
    z += ((y >>> 5 ^ y << 4) + y ^ this.k31);
    out[off] = ((byte)(y >> 24));
    out[(off + 1)] = ((byte)(y >> 16));
    out[(off + 2)] = ((byte)(y >> 8));
    out[(off + 3)] = ((byte)y);
    out[(off + 4)] = ((byte)(z >> 24));
    out[(off + 5)] = ((byte)(z >> 16));
    out[(off + 6)] = ((byte)(z >> 8));
    out[(off + 7)] = ((byte)z);
  }
  
  private void decryptBlock(byte[] in, byte[] out, int off)
  {
    int y = in[off] << 24 | (in[(off + 1)] & 0xFF) << 16 | (in[(off + 2)] & 0xFF) << 8 | in[(off + 3)] & 0xFF;
    
    int z = in[(off + 4)] << 24 | (in[(off + 5)] & 0xFF) << 16 | (in[(off + 6)] & 0xFF) << 8 | in[(off + 7)] & 0xFF;
    
    z -= ((y >>> 5 ^ y << 4) + y ^ this.k31);
    y -= ((z << 4 ^ z >>> 5) + z ^ this.k30);
    z -= ((y >>> 5 ^ y << 4) + y ^ this.k29);
    y -= ((z << 4 ^ z >>> 5) + z ^ this.k28);
    z -= ((y >>> 5 ^ y << 4) + y ^ this.k27);
    y -= ((z << 4 ^ z >>> 5) + z ^ this.k26);
    z -= ((y >>> 5 ^ y << 4) + y ^ this.k25);
    y -= ((z << 4 ^ z >>> 5) + z ^ this.k24);
    z -= ((y >>> 5 ^ y << 4) + y ^ this.k23);
    y -= ((z << 4 ^ z >>> 5) + z ^ this.k22);
    z -= ((y >>> 5 ^ y << 4) + y ^ this.k21);
    y -= ((z << 4 ^ z >>> 5) + z ^ this.k20);
    z -= ((y >>> 5 ^ y << 4) + y ^ this.k19);
    y -= ((z << 4 ^ z >>> 5) + z ^ this.k18);
    z -= ((y >>> 5 ^ y << 4) + y ^ this.k17);
    y -= ((z << 4 ^ z >>> 5) + z ^ this.k16);
    z -= ((y >>> 5 ^ y << 4) + y ^ this.k15);
    y -= ((z << 4 ^ z >>> 5) + z ^ this.k14);
    z -= ((y >>> 5 ^ y << 4) + y ^ this.k13);
    y -= ((z << 4 ^ z >>> 5) + z ^ this.k12);
    z -= ((y >>> 5 ^ y << 4) + y ^ this.k11);
    y -= ((z << 4 ^ z >>> 5) + z ^ this.k10);
    z -= ((y >>> 5 ^ y << 4) + y ^ this.k9);
    y -= ((z << 4 ^ z >>> 5) + z ^ this.k8);
    z -= ((y >>> 5 ^ y << 4) + y ^ this.k7);
    y -= ((z << 4 ^ z >>> 5) + z ^ this.k6);
    z -= ((y >>> 5 ^ y << 4) + y ^ this.k5);
    y -= ((z << 4 ^ z >>> 5) + z ^ this.k4);
    z -= ((y >>> 5 ^ y << 4) + y ^ this.k3);
    y -= ((z << 4 ^ z >>> 5) + z ^ this.k2);
    z -= ((y >>> 5 ^ y << 4) + y ^ this.k1);
    y -= ((z << 4 ^ z >>> 5) + z ^ this.k0);
    out[off] = ((byte)(y >> 24));
    out[(off + 1)] = ((byte)(y >> 16));
    out[(off + 2)] = ((byte)(y >> 8));
    out[(off + 3)] = ((byte)y);
    out[(off + 4)] = ((byte)(z >> 24));
    out[(off + 5)] = ((byte)(z >> 16));
    out[(off + 6)] = ((byte)(z >> 8));
    out[(off + 7)] = ((byte)z);
  }
  
  public int getKeyLength()
  {
    return 16;
  }
}
