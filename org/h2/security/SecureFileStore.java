package org.h2.security;

import org.h2.store.DataHandler;
import org.h2.store.FileStore;
import org.h2.util.MathUtils;

public class SecureFileStore
  extends FileStore
{
  private byte[] key;
  private final BlockCipher cipher;
  private final BlockCipher cipherForInitVector;
  private byte[] buffer = new byte[4];
  private long pos;
  private final byte[] bufferForInitVector;
  private final int keyIterations;
  
  public SecureFileStore(DataHandler handler, String name, String mode, String cipher, byte[] key, int keyIterations)
  {
    super(handler, name, mode);
    this.key = key;
    this.cipher = CipherFactory.getBlockCipher(cipher);
    this.cipherForInitVector = CipherFactory.getBlockCipher(cipher);
    this.keyIterations = keyIterations;
    this.bufferForInitVector = new byte[16];
  }
  
  protected byte[] generateSalt()
  {
    return MathUtils.secureRandomBytes(16);
  }
  
  protected void initKey(byte[] salt)
  {
    this.key = SHA256.getHashWithSalt(this.key, salt);
    for (int i = 0; i < this.keyIterations; i++) {
      this.key = SHA256.getHash(this.key, true);
    }
    this.cipher.setKey(this.key);
    this.key = SHA256.getHash(this.key, true);
    this.cipherForInitVector.setKey(this.key);
  }
  
  protected void writeDirect(byte[] b, int off, int len)
  {
    super.write(b, off, len);
    this.pos += len;
  }
  
  public void write(byte[] b, int off, int len)
  {
    if (this.buffer.length < b.length) {
      this.buffer = new byte[len];
    }
    System.arraycopy(b, off, this.buffer, 0, len);
    xorInitVector(this.buffer, 0, len, this.pos);
    this.cipher.encrypt(this.buffer, 0, len);
    super.write(this.buffer, 0, len);
    this.pos += len;
  }
  
  protected void readFullyDirect(byte[] b, int off, int len)
  {
    super.readFully(b, off, len);
    this.pos += len;
  }
  
  public void readFully(byte[] b, int off, int len)
  {
    super.readFully(b, off, len);
    for (int i = 0; i < len; i++) {
      if (b[i] != 0)
      {
        this.cipher.decrypt(b, off, len);
        xorInitVector(b, off, len, this.pos);
        break;
      }
    }
    this.pos += len;
  }
  
  public void seek(long x)
  {
    this.pos = x;
    super.seek(x);
  }
  
  private void xorInitVector(byte[] b, int off, int len, long p)
  {
    byte[] iv = this.bufferForInitVector;
    while (len > 0)
    {
      for (int i = 0; i < 16; i += 8)
      {
        long block = p + i >>> 3;
        iv[i] = ((byte)(int)(block >> 56));
        iv[(i + 1)] = ((byte)(int)(block >> 48));
        iv[(i + 2)] = ((byte)(int)(block >> 40));
        iv[(i + 3)] = ((byte)(int)(block >> 32));
        iv[(i + 4)] = ((byte)(int)(block >> 24));
        iv[(i + 5)] = ((byte)(int)(block >> 16));
        iv[(i + 6)] = ((byte)(int)(block >> 8));
        iv[(i + 7)] = ((byte)(int)block);
      }
      this.cipherForInitVector.encrypt(iv, 0, 16);
      for (int i = 0; i < 16; i++)
      {
        int tmp174_173 = (off + i); byte[] tmp174_169 = b;tmp174_169[tmp174_173] = ((byte)(tmp174_169[tmp174_173] ^ iv[i]));
      }
      p += 16L;
      off += 16;
      len -= 16;
    }
  }
}
