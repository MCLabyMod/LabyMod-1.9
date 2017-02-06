package org.h2.security;

import java.util.Arrays;

public class SHA256
{
  private static final int[] K = { 1116352408, 1899447441, -1245643825, -373957723, 961987163, 1508970993, -1841331548, -1424204075, -670586216, 310598401, 607225278, 1426881987, 1925078388, -2132889090, -1680079193, -1046744716, -459576895, -272742522, 264347078, 604807628, 770255983, 1249150122, 1555081692, 1996064986, -1740746414, -1473132947, -1341970488, -1084653625, -958395405, -710438585, 113926993, 338241895, 666307205, 773529912, 1294757372, 1396182291, 1695183700, 1986661051, -2117940946, -1838011259, -1564481375, -1474664885, -1035236496, -949202525, -778901479, -694614492, -200395387, 275423344, 430227734, 506948616, 659060556, 883997877, 958139571, 1322822218, 1537002063, 1747873779, 1955562222, 2024104815, -2067236844, -1933114872, -1866530822, -1538233109, -1090935817, -965641998 };
  private static final int[] HH = { 1779033703, -1150833019, 1013904242, -1521486534, 1359893119, -1694144372, 528734635, 1541459225 };
  private final byte[] result = new byte[32];
  private final int[] w = new int[64];
  private final int[] hh = new int[8];
  
  public static byte[] getHashWithSalt(byte[] data, byte[] salt)
  {
    byte[] buff = new byte[data.length + salt.length];
    System.arraycopy(data, 0, buff, 0, data.length);
    System.arraycopy(salt, 0, buff, data.length, salt.length);
    return getHash(buff, true);
  }
  
  public static byte[] getKeyPasswordHash(String userName, char[] password)
  {
    String user = userName + "@";
    byte[] buff = new byte[2 * (user.length() + password.length)];
    int n = 0;
    int i = 0;
    for (int length = user.length(); i < length; i++)
    {
      char c = user.charAt(i);
      buff[(n++)] = ((byte)(c >> '\b'));
      buff[(n++)] = ((byte)c);
    }
    for (char c : password)
    {
      buff[(n++)] = ((byte)(c >> '\b'));
      buff[(n++)] = ((byte)c);
    }
    Arrays.fill(password, '\000');
    return getHash(buff, true);
  }
  
  public static byte[] getHMAC(byte[] key, byte[] message)
  {
    key = normalizeKeyForHMAC(key);
    int len = message.length;
    int byteLen = 64 + Math.max(32, len);
    int intLen = getIntCount(byteLen);
    byte[] byteBuff = new byte[intLen * 4];
    int[] intBuff = new int[intLen];
    SHA256 sha = new SHA256();
    byte[] iKey = new byte[64 + len];
    byte[] oKey = new byte[96];
    sha.calculateHMAC(key, message, len, iKey, oKey, byteBuff, intBuff);
    return sha.result;
  }
  
  private void calculateHMAC(byte[] key, byte[] message, int len, byte[] iKey, byte[] oKey, byte[] byteBuff, int[] intBuff)
  {
    Arrays.fill(iKey, 0, 64, (byte)54);
    xor(iKey, key, 64);
    System.arraycopy(message, 0, iKey, 64, len);
    calculateHash(iKey, 64 + len, byteBuff, intBuff);
    Arrays.fill(oKey, 0, 64, (byte)92);
    xor(oKey, key, 64);
    System.arraycopy(this.result, 0, oKey, 64, 32);
    calculateHash(oKey, 96, byteBuff, intBuff);
  }
  
  private static byte[] normalizeKeyForHMAC(byte[] key)
  {
    if (key.length > 64) {
      key = getHash(key, false);
    }
    if (key.length < 64) {
      key = Arrays.copyOf(key, 64);
    }
    return key;
  }
  
  private static void xor(byte[] target, byte[] data, int len)
  {
    for (int i = 0; i < len; i++)
    {
      int tmp9_8 = i;target[tmp9_8] = ((byte)(target[tmp9_8] ^ data[i]));
    }
  }
  
  public static byte[] getPBKDF2(byte[] password, byte[] salt, int iterations, int resultLen)
  {
    byte[] result = new byte[resultLen];
    byte[] key = normalizeKeyForHMAC(password);
    SHA256 sha = new SHA256();
    int len = 64 + Math.max(32, salt.length + 4);
    byte[] message = new byte[len];
    int intLen = getIntCount(len);
    byte[] byteBuff = new byte[intLen * 4];
    int[] intBuff = new int[intLen];
    byte[] iKey = new byte[64 + len];
    byte[] oKey = new byte[96];
    int k = 1;
    for (int offset = 0; offset < resultLen; offset += 32)
    {
      for (int i = 0; i < iterations; i++)
      {
        if (i == 0)
        {
          System.arraycopy(salt, 0, message, 0, salt.length);
          writeInt(message, salt.length, k);
          len = salt.length + 4;
        }
        else
        {
          System.arraycopy(sha.result, 0, message, 0, 32);
          len = 32;
        }
        sha.calculateHMAC(key, message, len, iKey, oKey, byteBuff, intBuff);
        for (int j = 0; (j < 32) && (j + offset < resultLen); j++)
        {
          int tmp193_192 = (j + offset); byte[] tmp193_186 = result;tmp193_186[tmp193_192] = ((byte)(tmp193_186[tmp193_192] ^ sha.result[j]));
        }
      }
      k++;
    }
    Arrays.fill(password, (byte)0);
    Arrays.fill(key, (byte)0);
    return result;
  }
  
  public static byte[] getHash(byte[] data, boolean nullData)
  {
    int len = data.length;
    int intLen = getIntCount(len);
    byte[] byteBuff = new byte[intLen * 4];
    int[] intBuff = new int[intLen];
    SHA256 sha = new SHA256();
    sha.calculateHash(data, len, byteBuff, intBuff);
    if (nullData)
    {
      sha.fillWithNull();
      Arrays.fill(intBuff, 0);
      Arrays.fill(byteBuff, (byte)0);
      Arrays.fill(data, (byte)0);
    }
    return sha.result;
  }
  
  private static int getIntCount(int byteCount)
  {
    return (byteCount + 9 + 63) / 64 * 16;
  }
  
  private void fillWithNull()
  {
    Arrays.fill(this.w, 0);
    Arrays.fill(this.hh, 0);
  }
  
  private void calculateHash(byte[] data, int len, byte[] byteBuff, int[] intBuff)
  {
    int[] w = this.w;
    int[] hh = this.hh;
    byte[] result = this.result;
    int intLen = getIntCount(len);
    System.arraycopy(data, 0, byteBuff, 0, len);
    byteBuff[len] = Byte.MIN_VALUE;
    Arrays.fill(byteBuff, len + 1, intLen * 4, (byte)0);
    int i = 0;
    for (int j = 0; j < intLen; j++)
    {
      intBuff[j] = readInt(byteBuff, i);i += 4;
    }
    intBuff[(intLen - 2)] = (len >>> 29);
    intBuff[(intLen - 1)] = (len << 3);
    System.arraycopy(HH, 0, hh, 0, 8);
    for (int block = 0; block < intLen; block += 16)
    {
      for (int i = 0; i < 16; i++) {
        w[i] = intBuff[(block + i)];
      }
      for (int i = 16; i < 64; i++)
      {
        int x = w[(i - 2)];
        int theta1 = rot(x, 17) ^ rot(x, 19) ^ x >>> 10;
        x = w[(i - 15)];
        int theta0 = rot(x, 7) ^ rot(x, 18) ^ x >>> 3;
        w[i] = (theta1 + w[(i - 7)] + theta0 + w[(i - 16)]);
      }
      int a = hh[0];int b = hh[1];int c = hh[2];int d = hh[3];
      int e = hh[4];int f = hh[5];int g = hh[6];int h = hh[7];
      for (int i = 0; i < 64; i++)
      {
        int t1 = h + (rot(e, 6) ^ rot(e, 11) ^ rot(e, 25)) + (e & f ^ (e ^ 0xFFFFFFFF) & g) + K[i] + w[i];
        
        int t2 = (rot(a, 2) ^ rot(a, 13) ^ rot(a, 22)) + (a & b ^ a & c ^ b & c);
        
        h = g;
        g = f;
        f = e;
        e = d + t1;
        d = c;
        c = b;
        b = a;
        a = t1 + t2;
      }
      hh[0] += a;
      hh[1] += b;
      hh[2] += c;
      hh[3] += d;
      hh[4] += e;
      hh[5] += f;
      hh[6] += g;
      hh[7] += h;
    }
    for (int i = 0; i < 8; i++) {
      writeInt(result, i * 4, hh[i]);
    }
  }
  
  private static int rot(int i, int count)
  {
    return Integer.rotateRight(i, count);
  }
  
  private static int readInt(byte[] b, int i)
  {
    return ((b[i] & 0xFF) << 24) + ((b[(i + 1)] & 0xFF) << 16) + ((b[(i + 2)] & 0xFF) << 8) + (b[(i + 3)] & 0xFF);
  }
  
  private static void writeInt(byte[] b, int i, int value)
  {
    b[i] = ((byte)(value >> 24));
    b[(i + 1)] = ((byte)(value >> 16));
    b[(i + 2)] = ((byte)(value >> 8));
    b[(i + 3)] = ((byte)value);
  }
}
