package org.h2.security;

public class AES
  implements BlockCipher
{
  private static final int[] RCON = new int[10];
  private static final int[] FS = new int['Ā'];
  private static final int[] FT0 = new int['Ā'];
  private static final int[] FT1 = new int['Ā'];
  private static final int[] FT2 = new int['Ā'];
  private static final int[] FT3 = new int['Ā'];
  private static final int[] RS = new int['Ā'];
  private static final int[] RT0 = new int['Ā'];
  private static final int[] RT1 = new int['Ā'];
  private static final int[] RT2 = new int['Ā'];
  private static final int[] RT3 = new int['Ā'];
  private final int[] encKey = new int[44];
  private final int[] decKey = new int[44];
  
  private static int rot8(int x)
  {
    return x >>> 8 | x << 24;
  }
  
  private static int xtime(int x)
  {
    return (x << 1 ^ ((x & 0x80) != 0 ? 27 : 0)) & 0xFF;
  }
  
  private static int mul(int[] pow, int[] log, int x, int y)
  {
    return (x != 0) && (y != 0) ? pow[((log[x] + log[y]) % 255)] : 0;
  }
  
  static
  {
    int[] pow = new int['Ā'];
    int[] log = new int['Ā'];
    int i = 0;
    for (int x = 1; i < 256; x ^= xtime(x))
    {
      pow[i] = x;
      log[x] = i;i++;
    }
    int i = 0;
    for (int x = 1; i < 10; x = xtime(x))
    {
      RCON[i] = (x << 24);i++;
    }
    FS[0] = 99;
    RS[99] = 0;
    for (int i = 1; i < 256; i++)
    {
      int x = pow[(255 - log[i])];int y = x;
      y = (y << 1 | y >> 7) & 0xFF;
      x ^= y;
      y = (y << 1 | y >> 7) & 0xFF;
      x ^= y;
      y = (y << 1 | y >> 7) & 0xFF;
      x ^= y;
      y = (y << 1 | y >> 7) & 0xFF;
      x ^= y ^ 0x63;
      FS[i] = (x & 0xFF);
      RS[x] = (i & 0xFF);
    }
    for (int i = 0; i < 256; i++)
    {
      int x = FS[i];int y = xtime(x);
      FT0[i] = (x ^ y ^ x << 8 ^ x << 16 ^ y << 24);
      FT1[i] = rot8(FT0[i]);
      FT2[i] = rot8(FT1[i]);
      FT3[i] = rot8(FT2[i]);
      y = RS[i];
      RT0[i] = (mul(pow, log, 11, y) ^ mul(pow, log, 13, y) << 8 ^ mul(pow, log, 9, y) << 16 ^ mul(pow, log, 14, y) << 24);
      
      RT1[i] = rot8(RT0[i]);
      RT2[i] = rot8(RT1[i]);
      RT3[i] = rot8(RT2[i]);
    }
  }
  
  private static int getDec(int t)
  {
    return RT0[FS[(t >> 24 & 0xFF)]] ^ RT1[FS[(t >> 16 & 0xFF)]] ^ RT2[FS[(t >> 8 & 0xFF)]] ^ RT3[FS[(t & 0xFF)]];
  }
  
  public void setKey(byte[] key)
  {
    int i = 0;
    for (int j = 0; i < 4; i++) {
      this.encKey[i] = (this.decKey[i] = (key[(j++)] & 0xFF) << 24 | (key[(j++)] & 0xFF) << 16 | (key[(j++)] & 0xFF) << 8 | key[(j++)] & 0xFF);
    }
    int e = 0;
    for (int i = 0; i < 10; e += 4)
    {
      this.encKey[(e + 4)] = (this.encKey[e] ^ RCON[i] ^ FS[(this.encKey[(e + 3)] >> 16 & 0xFF)] << 24 ^ FS[(this.encKey[(e + 3)] >> 8 & 0xFF)] << 16 ^ FS[(this.encKey[(e + 3)] & 0xFF)] << 8 ^ FS[(this.encKey[(e + 3)] >> 24 & 0xFF)]);
      
      this.encKey[(e + 5)] = (this.encKey[(e + 1)] ^ this.encKey[(e + 4)]);
      this.encKey[(e + 6)] = (this.encKey[(e + 2)] ^ this.encKey[(e + 5)]);
      this.encKey[(e + 7)] = (this.encKey[(e + 3)] ^ this.encKey[(e + 6)]);i++;
    }
    int d = 0;
    this.decKey[(d++)] = this.encKey[(e++)];
    this.decKey[(d++)] = this.encKey[(e++)];
    this.decKey[(d++)] = this.encKey[(e++)];
    this.decKey[(d++)] = this.encKey[(e++)];
    for (int i = 1; i < 10; i++)
    {
      e -= 8;
      this.decKey[(d++)] = getDec(this.encKey[(e++)]);
      this.decKey[(d++)] = getDec(this.encKey[(e++)]);
      this.decKey[(d++)] = getDec(this.encKey[(e++)]);
      this.decKey[(d++)] = getDec(this.encKey[(e++)]);
    }
    e -= 8;
    this.decKey[(d++)] = this.encKey[(e++)];
    this.decKey[(d++)] = this.encKey[(e++)];
    this.decKey[(d++)] = this.encKey[(e++)];
    this.decKey[d] = this.encKey[e];
  }
  
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
    int[] k = this.encKey;
    int x0 = (in[off] << 24 | (in[(off + 1)] & 0xFF) << 16 | (in[(off + 2)] & 0xFF) << 8 | in[(off + 3)] & 0xFF) ^ k[0];
    
    int x1 = (in[(off + 4)] << 24 | (in[(off + 5)] & 0xFF) << 16 | (in[(off + 6)] & 0xFF) << 8 | in[(off + 7)] & 0xFF) ^ k[1];
    
    int x2 = (in[(off + 8)] << 24 | (in[(off + 9)] & 0xFF) << 16 | (in[(off + 10)] & 0xFF) << 8 | in[(off + 11)] & 0xFF) ^ k[2];
    
    int x3 = (in[(off + 12)] << 24 | (in[(off + 13)] & 0xFF) << 16 | (in[(off + 14)] & 0xFF) << 8 | in[(off + 15)] & 0xFF) ^ k[3];
    
    int y0 = FT0[(x0 >> 24 & 0xFF)] ^ FT1[(x1 >> 16 & 0xFF)] ^ FT2[(x2 >> 8 & 0xFF)] ^ FT3[(x3 & 0xFF)] ^ k[4];
    
    int y1 = FT0[(x1 >> 24 & 0xFF)] ^ FT1[(x2 >> 16 & 0xFF)] ^ FT2[(x3 >> 8 & 0xFF)] ^ FT3[(x0 & 0xFF)] ^ k[5];
    
    int y2 = FT0[(x2 >> 24 & 0xFF)] ^ FT1[(x3 >> 16 & 0xFF)] ^ FT2[(x0 >> 8 & 0xFF)] ^ FT3[(x1 & 0xFF)] ^ k[6];
    
    int y3 = FT0[(x3 >> 24 & 0xFF)] ^ FT1[(x0 >> 16 & 0xFF)] ^ FT2[(x1 >> 8 & 0xFF)] ^ FT3[(x2 & 0xFF)] ^ k[7];
    
    x0 = FT0[(y0 >> 24 & 0xFF)] ^ FT1[(y1 >> 16 & 0xFF)] ^ FT2[(y2 >> 8 & 0xFF)] ^ FT3[(y3 & 0xFF)] ^ k[8];
    
    x1 = FT0[(y1 >> 24 & 0xFF)] ^ FT1[(y2 >> 16 & 0xFF)] ^ FT2[(y3 >> 8 & 0xFF)] ^ FT3[(y0 & 0xFF)] ^ k[9];
    
    x2 = FT0[(y2 >> 24 & 0xFF)] ^ FT1[(y3 >> 16 & 0xFF)] ^ FT2[(y0 >> 8 & 0xFF)] ^ FT3[(y1 & 0xFF)] ^ k[10];
    
    x3 = FT0[(y3 >> 24 & 0xFF)] ^ FT1[(y0 >> 16 & 0xFF)] ^ FT2[(y1 >> 8 & 0xFF)] ^ FT3[(y2 & 0xFF)] ^ k[11];
    
    y0 = FT0[(x0 >> 24 & 0xFF)] ^ FT1[(x1 >> 16 & 0xFF)] ^ FT2[(x2 >> 8 & 0xFF)] ^ FT3[(x3 & 0xFF)] ^ k[12];
    
    y1 = FT0[(x1 >> 24 & 0xFF)] ^ FT1[(x2 >> 16 & 0xFF)] ^ FT2[(x3 >> 8 & 0xFF)] ^ FT3[(x0 & 0xFF)] ^ k[13];
    
    y2 = FT0[(x2 >> 24 & 0xFF)] ^ FT1[(x3 >> 16 & 0xFF)] ^ FT2[(x0 >> 8 & 0xFF)] ^ FT3[(x1 & 0xFF)] ^ k[14];
    
    y3 = FT0[(x3 >> 24 & 0xFF)] ^ FT1[(x0 >> 16 & 0xFF)] ^ FT2[(x1 >> 8 & 0xFF)] ^ FT3[(x2 & 0xFF)] ^ k[15];
    
    x0 = FT0[(y0 >> 24 & 0xFF)] ^ FT1[(y1 >> 16 & 0xFF)] ^ FT2[(y2 >> 8 & 0xFF)] ^ FT3[(y3 & 0xFF)] ^ k[16];
    
    x1 = FT0[(y1 >> 24 & 0xFF)] ^ FT1[(y2 >> 16 & 0xFF)] ^ FT2[(y3 >> 8 & 0xFF)] ^ FT3[(y0 & 0xFF)] ^ k[17];
    
    x2 = FT0[(y2 >> 24 & 0xFF)] ^ FT1[(y3 >> 16 & 0xFF)] ^ FT2[(y0 >> 8 & 0xFF)] ^ FT3[(y1 & 0xFF)] ^ k[18];
    
    x3 = FT0[(y3 >> 24 & 0xFF)] ^ FT1[(y0 >> 16 & 0xFF)] ^ FT2[(y1 >> 8 & 0xFF)] ^ FT3[(y2 & 0xFF)] ^ k[19];
    
    y0 = FT0[(x0 >> 24 & 0xFF)] ^ FT1[(x1 >> 16 & 0xFF)] ^ FT2[(x2 >> 8 & 0xFF)] ^ FT3[(x3 & 0xFF)] ^ k[20];
    
    y1 = FT0[(x1 >> 24 & 0xFF)] ^ FT1[(x2 >> 16 & 0xFF)] ^ FT2[(x3 >> 8 & 0xFF)] ^ FT3[(x0 & 0xFF)] ^ k[21];
    
    y2 = FT0[(x2 >> 24 & 0xFF)] ^ FT1[(x3 >> 16 & 0xFF)] ^ FT2[(x0 >> 8 & 0xFF)] ^ FT3[(x1 & 0xFF)] ^ k[22];
    
    y3 = FT0[(x3 >> 24 & 0xFF)] ^ FT1[(x0 >> 16 & 0xFF)] ^ FT2[(x1 >> 8 & 0xFF)] ^ FT3[(x2 & 0xFF)] ^ k[23];
    
    x0 = FT0[(y0 >> 24 & 0xFF)] ^ FT1[(y1 >> 16 & 0xFF)] ^ FT2[(y2 >> 8 & 0xFF)] ^ FT3[(y3 & 0xFF)] ^ k[24];
    
    x1 = FT0[(y1 >> 24 & 0xFF)] ^ FT1[(y2 >> 16 & 0xFF)] ^ FT2[(y3 >> 8 & 0xFF)] ^ FT3[(y0 & 0xFF)] ^ k[25];
    
    x2 = FT0[(y2 >> 24 & 0xFF)] ^ FT1[(y3 >> 16 & 0xFF)] ^ FT2[(y0 >> 8 & 0xFF)] ^ FT3[(y1 & 0xFF)] ^ k[26];
    
    x3 = FT0[(y3 >> 24 & 0xFF)] ^ FT1[(y0 >> 16 & 0xFF)] ^ FT2[(y1 >> 8 & 0xFF)] ^ FT3[(y2 & 0xFF)] ^ k[27];
    
    y0 = FT0[(x0 >> 24 & 0xFF)] ^ FT1[(x1 >> 16 & 0xFF)] ^ FT2[(x2 >> 8 & 0xFF)] ^ FT3[(x3 & 0xFF)] ^ k[28];
    
    y1 = FT0[(x1 >> 24 & 0xFF)] ^ FT1[(x2 >> 16 & 0xFF)] ^ FT2[(x3 >> 8 & 0xFF)] ^ FT3[(x0 & 0xFF)] ^ k[29];
    
    y2 = FT0[(x2 >> 24 & 0xFF)] ^ FT1[(x3 >> 16 & 0xFF)] ^ FT2[(x0 >> 8 & 0xFF)] ^ FT3[(x1 & 0xFF)] ^ k[30];
    
    y3 = FT0[(x3 >> 24 & 0xFF)] ^ FT1[(x0 >> 16 & 0xFF)] ^ FT2[(x1 >> 8 & 0xFF)] ^ FT3[(x2 & 0xFF)] ^ k[31];
    
    x0 = FT0[(y0 >> 24 & 0xFF)] ^ FT1[(y1 >> 16 & 0xFF)] ^ FT2[(y2 >> 8 & 0xFF)] ^ FT3[(y3 & 0xFF)] ^ k[32];
    
    x1 = FT0[(y1 >> 24 & 0xFF)] ^ FT1[(y2 >> 16 & 0xFF)] ^ FT2[(y3 >> 8 & 0xFF)] ^ FT3[(y0 & 0xFF)] ^ k[33];
    
    x2 = FT0[(y2 >> 24 & 0xFF)] ^ FT1[(y3 >> 16 & 0xFF)] ^ FT2[(y0 >> 8 & 0xFF)] ^ FT3[(y1 & 0xFF)] ^ k[34];
    
    x3 = FT0[(y3 >> 24 & 0xFF)] ^ FT1[(y0 >> 16 & 0xFF)] ^ FT2[(y1 >> 8 & 0xFF)] ^ FT3[(y2 & 0xFF)] ^ k[35];
    
    y0 = FT0[(x0 >> 24 & 0xFF)] ^ FT1[(x1 >> 16 & 0xFF)] ^ FT2[(x2 >> 8 & 0xFF)] ^ FT3[(x3 & 0xFF)] ^ k[36];
    
    y1 = FT0[(x1 >> 24 & 0xFF)] ^ FT1[(x2 >> 16 & 0xFF)] ^ FT2[(x3 >> 8 & 0xFF)] ^ FT3[(x0 & 0xFF)] ^ k[37];
    
    y2 = FT0[(x2 >> 24 & 0xFF)] ^ FT1[(x3 >> 16 & 0xFF)] ^ FT2[(x0 >> 8 & 0xFF)] ^ FT3[(x1 & 0xFF)] ^ k[38];
    
    y3 = FT0[(x3 >> 24 & 0xFF)] ^ FT1[(x0 >> 16 & 0xFF)] ^ FT2[(x1 >> 8 & 0xFF)] ^ FT3[(x2 & 0xFF)] ^ k[39];
    
    x0 = (FS[(y0 >> 24 & 0xFF)] << 24 | FS[(y1 >> 16 & 0xFF)] << 16 | FS[(y2 >> 8 & 0xFF)] << 8 | FS[(y3 & 0xFF)]) ^ k[40];
    
    x1 = (FS[(y1 >> 24 & 0xFF)] << 24 | FS[(y2 >> 16 & 0xFF)] << 16 | FS[(y3 >> 8 & 0xFF)] << 8 | FS[(y0 & 0xFF)]) ^ k[41];
    
    x2 = (FS[(y2 >> 24 & 0xFF)] << 24 | FS[(y3 >> 16 & 0xFF)] << 16 | FS[(y0 >> 8 & 0xFF)] << 8 | FS[(y1 & 0xFF)]) ^ k[42];
    
    x3 = (FS[(y3 >> 24 & 0xFF)] << 24 | FS[(y0 >> 16 & 0xFF)] << 16 | FS[(y1 >> 8 & 0xFF)] << 8 | FS[(y2 & 0xFF)]) ^ k[43];
    
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
    int[] k = this.decKey;
    int x0 = (in[off] << 24 | (in[(off + 1)] & 0xFF) << 16 | (in[(off + 2)] & 0xFF) << 8 | in[(off + 3)] & 0xFF) ^ k[0];
    
    int x1 = (in[(off + 4)] << 24 | (in[(off + 5)] & 0xFF) << 16 | (in[(off + 6)] & 0xFF) << 8 | in[(off + 7)] & 0xFF) ^ k[1];
    
    int x2 = (in[(off + 8)] << 24 | (in[(off + 9)] & 0xFF) << 16 | (in[(off + 10)] & 0xFF) << 8 | in[(off + 11)] & 0xFF) ^ k[2];
    
    int x3 = (in[(off + 12)] << 24 | (in[(off + 13)] & 0xFF) << 16 | (in[(off + 14)] & 0xFF) << 8 | in[(off + 15)] & 0xFF) ^ k[3];
    
    int y0 = RT0[(x0 >> 24 & 0xFF)] ^ RT1[(x3 >> 16 & 0xFF)] ^ RT2[(x2 >> 8 & 0xFF)] ^ RT3[(x1 & 0xFF)] ^ k[4];
    
    int y1 = RT0[(x1 >> 24 & 0xFF)] ^ RT1[(x0 >> 16 & 0xFF)] ^ RT2[(x3 >> 8 & 0xFF)] ^ RT3[(x2 & 0xFF)] ^ k[5];
    
    int y2 = RT0[(x2 >> 24 & 0xFF)] ^ RT1[(x1 >> 16 & 0xFF)] ^ RT2[(x0 >> 8 & 0xFF)] ^ RT3[(x3 & 0xFF)] ^ k[6];
    
    int y3 = RT0[(x3 >> 24 & 0xFF)] ^ RT1[(x2 >> 16 & 0xFF)] ^ RT2[(x1 >> 8 & 0xFF)] ^ RT3[(x0 & 0xFF)] ^ k[7];
    
    x0 = RT0[(y0 >> 24 & 0xFF)] ^ RT1[(y3 >> 16 & 0xFF)] ^ RT2[(y2 >> 8 & 0xFF)] ^ RT3[(y1 & 0xFF)] ^ k[8];
    
    x1 = RT0[(y1 >> 24 & 0xFF)] ^ RT1[(y0 >> 16 & 0xFF)] ^ RT2[(y3 >> 8 & 0xFF)] ^ RT3[(y2 & 0xFF)] ^ k[9];
    
    x2 = RT0[(y2 >> 24 & 0xFF)] ^ RT1[(y1 >> 16 & 0xFF)] ^ RT2[(y0 >> 8 & 0xFF)] ^ RT3[(y3 & 0xFF)] ^ k[10];
    
    x3 = RT0[(y3 >> 24 & 0xFF)] ^ RT1[(y2 >> 16 & 0xFF)] ^ RT2[(y1 >> 8 & 0xFF)] ^ RT3[(y0 & 0xFF)] ^ k[11];
    
    y0 = RT0[(x0 >> 24 & 0xFF)] ^ RT1[(x3 >> 16 & 0xFF)] ^ RT2[(x2 >> 8 & 0xFF)] ^ RT3[(x1 & 0xFF)] ^ k[12];
    
    y1 = RT0[(x1 >> 24 & 0xFF)] ^ RT1[(x0 >> 16 & 0xFF)] ^ RT2[(x3 >> 8 & 0xFF)] ^ RT3[(x2 & 0xFF)] ^ k[13];
    
    y2 = RT0[(x2 >> 24 & 0xFF)] ^ RT1[(x1 >> 16 & 0xFF)] ^ RT2[(x0 >> 8 & 0xFF)] ^ RT3[(x3 & 0xFF)] ^ k[14];
    
    y3 = RT0[(x3 >> 24 & 0xFF)] ^ RT1[(x2 >> 16 & 0xFF)] ^ RT2[(x1 >> 8 & 0xFF)] ^ RT3[(x0 & 0xFF)] ^ k[15];
    
    x0 = RT0[(y0 >> 24 & 0xFF)] ^ RT1[(y3 >> 16 & 0xFF)] ^ RT2[(y2 >> 8 & 0xFF)] ^ RT3[(y1 & 0xFF)] ^ k[16];
    
    x1 = RT0[(y1 >> 24 & 0xFF)] ^ RT1[(y0 >> 16 & 0xFF)] ^ RT2[(y3 >> 8 & 0xFF)] ^ RT3[(y2 & 0xFF)] ^ k[17];
    
    x2 = RT0[(y2 >> 24 & 0xFF)] ^ RT1[(y1 >> 16 & 0xFF)] ^ RT2[(y0 >> 8 & 0xFF)] ^ RT3[(y3 & 0xFF)] ^ k[18];
    
    x3 = RT0[(y3 >> 24 & 0xFF)] ^ RT1[(y2 >> 16 & 0xFF)] ^ RT2[(y1 >> 8 & 0xFF)] ^ RT3[(y0 & 0xFF)] ^ k[19];
    
    y0 = RT0[(x0 >> 24 & 0xFF)] ^ RT1[(x3 >> 16 & 0xFF)] ^ RT2[(x2 >> 8 & 0xFF)] ^ RT3[(x1 & 0xFF)] ^ k[20];
    
    y1 = RT0[(x1 >> 24 & 0xFF)] ^ RT1[(x0 >> 16 & 0xFF)] ^ RT2[(x3 >> 8 & 0xFF)] ^ RT3[(x2 & 0xFF)] ^ k[21];
    
    y2 = RT0[(x2 >> 24 & 0xFF)] ^ RT1[(x1 >> 16 & 0xFF)] ^ RT2[(x0 >> 8 & 0xFF)] ^ RT3[(x3 & 0xFF)] ^ k[22];
    
    y3 = RT0[(x3 >> 24 & 0xFF)] ^ RT1[(x2 >> 16 & 0xFF)] ^ RT2[(x1 >> 8 & 0xFF)] ^ RT3[(x0 & 0xFF)] ^ k[23];
    
    x0 = RT0[(y0 >> 24 & 0xFF)] ^ RT1[(y3 >> 16 & 0xFF)] ^ RT2[(y2 >> 8 & 0xFF)] ^ RT3[(y1 & 0xFF)] ^ k[24];
    
    x1 = RT0[(y1 >> 24 & 0xFF)] ^ RT1[(y0 >> 16 & 0xFF)] ^ RT2[(y3 >> 8 & 0xFF)] ^ RT3[(y2 & 0xFF)] ^ k[25];
    
    x2 = RT0[(y2 >> 24 & 0xFF)] ^ RT1[(y1 >> 16 & 0xFF)] ^ RT2[(y0 >> 8 & 0xFF)] ^ RT3[(y3 & 0xFF)] ^ k[26];
    
    x3 = RT0[(y3 >> 24 & 0xFF)] ^ RT1[(y2 >> 16 & 0xFF)] ^ RT2[(y1 >> 8 & 0xFF)] ^ RT3[(y0 & 0xFF)] ^ k[27];
    
    y0 = RT0[(x0 >> 24 & 0xFF)] ^ RT1[(x3 >> 16 & 0xFF)] ^ RT2[(x2 >> 8 & 0xFF)] ^ RT3[(x1 & 0xFF)] ^ k[28];
    
    y1 = RT0[(x1 >> 24 & 0xFF)] ^ RT1[(x0 >> 16 & 0xFF)] ^ RT2[(x3 >> 8 & 0xFF)] ^ RT3[(x2 & 0xFF)] ^ k[29];
    
    y2 = RT0[(x2 >> 24 & 0xFF)] ^ RT1[(x1 >> 16 & 0xFF)] ^ RT2[(x0 >> 8 & 0xFF)] ^ RT3[(x3 & 0xFF)] ^ k[30];
    
    y3 = RT0[(x3 >> 24 & 0xFF)] ^ RT1[(x2 >> 16 & 0xFF)] ^ RT2[(x1 >> 8 & 0xFF)] ^ RT3[(x0 & 0xFF)] ^ k[31];
    
    x0 = RT0[(y0 >> 24 & 0xFF)] ^ RT1[(y3 >> 16 & 0xFF)] ^ RT2[(y2 >> 8 & 0xFF)] ^ RT3[(y1 & 0xFF)] ^ k[32];
    
    x1 = RT0[(y1 >> 24 & 0xFF)] ^ RT1[(y0 >> 16 & 0xFF)] ^ RT2[(y3 >> 8 & 0xFF)] ^ RT3[(y2 & 0xFF)] ^ k[33];
    
    x2 = RT0[(y2 >> 24 & 0xFF)] ^ RT1[(y1 >> 16 & 0xFF)] ^ RT2[(y0 >> 8 & 0xFF)] ^ RT3[(y3 & 0xFF)] ^ k[34];
    
    x3 = RT0[(y3 >> 24 & 0xFF)] ^ RT1[(y2 >> 16 & 0xFF)] ^ RT2[(y1 >> 8 & 0xFF)] ^ RT3[(y0 & 0xFF)] ^ k[35];
    
    y0 = RT0[(x0 >> 24 & 0xFF)] ^ RT1[(x3 >> 16 & 0xFF)] ^ RT2[(x2 >> 8 & 0xFF)] ^ RT3[(x1 & 0xFF)] ^ k[36];
    
    y1 = RT0[(x1 >> 24 & 0xFF)] ^ RT1[(x0 >> 16 & 0xFF)] ^ RT2[(x3 >> 8 & 0xFF)] ^ RT3[(x2 & 0xFF)] ^ k[37];
    
    y2 = RT0[(x2 >> 24 & 0xFF)] ^ RT1[(x1 >> 16 & 0xFF)] ^ RT2[(x0 >> 8 & 0xFF)] ^ RT3[(x3 & 0xFF)] ^ k[38];
    
    y3 = RT0[(x3 >> 24 & 0xFF)] ^ RT1[(x2 >> 16 & 0xFF)] ^ RT2[(x1 >> 8 & 0xFF)] ^ RT3[(x0 & 0xFF)] ^ k[39];
    
    x0 = (RS[(y0 >> 24 & 0xFF)] << 24 | RS[(y3 >> 16 & 0xFF)] << 16 | RS[(y2 >> 8 & 0xFF)] << 8 | RS[(y1 & 0xFF)]) ^ k[40];
    
    x1 = (RS[(y1 >> 24 & 0xFF)] << 24 | RS[(y0 >> 16 & 0xFF)] << 16 | RS[(y3 >> 8 & 0xFF)] << 8 | RS[(y2 & 0xFF)]) ^ k[41];
    
    x2 = (RS[(y2 >> 24 & 0xFF)] << 24 | RS[(y1 >> 16 & 0xFF)] << 16 | RS[(y0 >> 8 & 0xFF)] << 8 | RS[(y3 & 0xFF)]) ^ k[42];
    
    x3 = (RS[(y3 >> 24 & 0xFF)] << 24 | RS[(y2 >> 16 & 0xFF)] << 16 | RS[(y1 >> 8 & 0xFF)] << 8 | RS[(y0 & 0xFF)]) ^ k[43];
    
    out[off] = ((byte)(x0 >> 24));
    out[(off + 1)] = ((byte)(x0 >> 16));
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
}
