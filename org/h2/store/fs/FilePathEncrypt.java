package org.h2.store.fs;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Arrays;
import org.h2.engine.Constants;
import org.h2.mvstore.DataUtils;
import org.h2.security.AES;
import org.h2.security.BlockCipher;
import org.h2.security.SHA256;
import org.h2.util.MathUtils;

public class FilePathEncrypt
  extends FilePathWrapper
{
  private static final String SCHEME = "encrypt";
  
  public static void register()
  {
    FilePath.register(new FilePathEncrypt());
  }
  
  public FileChannel open(String mode)
    throws IOException
  {
    String[] parsed = parse(this.name);
    FileChannel file = FileUtils.open(parsed[1], mode);
    byte[] passwordBytes = parsed[0].getBytes(Constants.UTF8);
    return new FileEncrypt(this.name, passwordBytes, file);
  }
  
  public String getScheme()
  {
    return "encrypt";
  }
  
  protected String getPrefix()
  {
    String[] parsed = parse(this.name);
    return getScheme() + ":" + parsed[0] + ":";
  }
  
  public FilePath unwrap(String fileName)
  {
    return FilePath.get(parse(fileName)[1]);
  }
  
  public long size()
  {
    long size = getBase().size() - 4096L;
    size = Math.max(0L, size);
    if ((size & 0xFFF) != 0L) {
      size -= 4096L;
    }
    return size;
  }
  
  public OutputStream newOutputStream(boolean append)
    throws IOException
  {
    return new FileChannelOutputStream(open("rw"), append);
  }
  
  public InputStream newInputStream()
    throws IOException
  {
    return new FileChannelInputStream(open("r"), true);
  }
  
  private String[] parse(String fileName)
  {
    if (!fileName.startsWith(getScheme())) {
      throw new IllegalArgumentException(fileName + " doesn't start with " + getScheme());
    }
    fileName = fileName.substring(getScheme().length() + 1);
    int idx = fileName.indexOf(':');
    if (idx < 0) {
      throw new IllegalArgumentException(fileName + " doesn't contain encryption algorithm and password");
    }
    String password = fileName.substring(0, idx);
    fileName = fileName.substring(idx + 1);
    return new String[] { password, fileName };
  }
  
  public static byte[] getPasswordBytes(char[] passwordChars)
  {
    int len = passwordChars.length;
    byte[] password = new byte[len * 2];
    for (int i = 0; i < len; i++)
    {
      char c = passwordChars[i];
      password[(i + i)] = ((byte)(c >>> '\b'));
      password[(i + i + 1)] = ((byte)c);
    }
    return password;
  }
  
  public static class FileEncrypt
    extends FileBase
  {
    static final int BLOCK_SIZE = 4096;
    static final int BLOCK_SIZE_MASK = 4095;
    static final int HEADER_LENGTH = 4096;
    private static final byte[] HEADER = "H2encrypt\n".getBytes();
    private static final int SALT_POS = HEADER.length;
    private static final int SALT_LENGTH = 8;
    private static final int HASH_ITERATIONS = 10;
    private final FileChannel base;
    private long pos;
    private long size;
    private final String name;
    private FilePathEncrypt.XTS xts;
    private byte[] encryptionKey;
    
    public FileEncrypt(String name, byte[] encryptionKey, FileChannel base)
    {
      this.name = name;
      this.base = base;
      this.encryptionKey = encryptionKey;
    }
    
    private void init()
      throws IOException
    {
      if (this.xts != null) {
        return;
      }
      this.size = (this.base.size() - 4096L);
      boolean newFile = this.size < 0L;
      byte[] salt;
      if (newFile)
      {
        byte[] header = Arrays.copyOf(HEADER, 4096);
        byte[] salt = MathUtils.secureRandomBytes(8);
        System.arraycopy(salt, 0, header, SALT_POS, salt.length);
        DataUtils.writeFully(this.base, 0L, ByteBuffer.wrap(header));
        this.size = 0L;
      }
      else
      {
        salt = new byte[8];
        DataUtils.readFully(this.base, SALT_POS, ByteBuffer.wrap(salt));
        if ((this.size & 0xFFF) != 0L) {
          this.size -= 4096L;
        }
      }
      AES cipher = new AES();
      cipher.setKey(SHA256.getPBKDF2(this.encryptionKey, salt, 10, 16));
      
      this.encryptionKey = null;
      this.xts = new FilePathEncrypt.XTS(cipher);
    }
    
    protected void implCloseChannel()
      throws IOException
    {
      this.base.close();
    }
    
    public FileChannel position(long newPosition)
      throws IOException
    {
      this.pos = newPosition;
      return this;
    }
    
    public long position()
      throws IOException
    {
      return this.pos;
    }
    
    public int read(ByteBuffer dst)
      throws IOException
    {
      int len = read(dst, this.pos);
      if (len > 0) {
        this.pos += len;
      }
      return len;
    }
    
    public int read(ByteBuffer dst, long position)
      throws IOException
    {
      int len = dst.remaining();
      if (len == 0) {
        return 0;
      }
      init();
      len = (int)Math.min(len, this.size - position);
      if (position >= this.size) {
        return -1;
      }
      if (position < 0L) {
        throw new IllegalArgumentException("pos: " + position);
      }
      if (((position & 0xFFF) != 0L) || ((len & 0xFFF) != 0))
      {
        long p = position / 4096L * 4096L;
        int offset = (int)(position - p);
        int l = (len + offset + 4096 - 1) / 4096 * 4096;
        ByteBuffer temp = ByteBuffer.allocate(l);
        readInternal(temp, p, l);
        temp.flip();
        temp.limit(offset + len);
        temp.position(offset);
        dst.put(temp);
        return len;
      }
      readInternal(dst, position, len);
      return len;
    }
    
    private void readInternal(ByteBuffer dst, long position, int len)
      throws IOException
    {
      int x = dst.position();
      readFully(this.base, position + 4096L, dst);
      long block = position / 4096L;
      while (len > 0)
      {
        this.xts.decrypt(block++, 4096, dst.array(), dst.arrayOffset() + x);
        x += 4096;
        len -= 4096;
      }
    }
    
    private static void readFully(FileChannel file, long pos, ByteBuffer dst)
      throws IOException
    {
      do
      {
        int len = file.read(dst, pos);
        if (len < 0) {
          throw new EOFException();
        }
        pos += len;
      } while (dst.remaining() > 0);
    }
    
    public int write(ByteBuffer src, long position)
      throws IOException
    {
      init();
      int len = src.remaining();
      if (((position & 0xFFF) != 0L) || ((len & 0xFFF) != 0))
      {
        long p = position / 4096L * 4096L;
        int offset = (int)(position - p);
        int l = (len + offset + 4096 - 1) / 4096 * 4096;
        ByteBuffer temp = ByteBuffer.allocate(l);
        int available = (int)(this.size - p + 4096L - 1L) / 4096 * 4096;
        int readLen = Math.min(l, available);
        if (readLen > 0)
        {
          readInternal(temp, p, readLen);
          temp.rewind();
        }
        temp.limit(offset + len);
        temp.position(offset);
        temp.put(src);
        temp.limit(l);
        temp.rewind();
        writeInternal(temp, p, l);
        long p2 = position + len;
        this.size = Math.max(this.size, p2);
        int plus = (int)(this.size & 0xFFF);
        if (plus > 0)
        {
          temp = ByteBuffer.allocate(plus);
          DataUtils.writeFully(this.base, p + 4096L + l, temp);
        }
        return len;
      }
      writeInternal(src, position, len);
      long p2 = position + len;
      this.size = Math.max(this.size, p2);
      return len;
    }
    
    private void writeInternal(ByteBuffer src, long position, int len)
      throws IOException
    {
      ByteBuffer crypt = ByteBuffer.allocate(len);
      crypt.put(src);
      crypt.flip();
      long block = position / 4096L;
      int x = 0;int l = len;
      while (l > 0)
      {
        this.xts.encrypt(block++, 4096, crypt.array(), crypt.arrayOffset() + x);
        x += 4096;
        l -= 4096;
      }
      writeFully(this.base, position + 4096L, crypt);
    }
    
    private static void writeFully(FileChannel file, long pos, ByteBuffer src)
      throws IOException
    {
      int off = 0;
      do
      {
        int len = file.write(src, pos + off);
        off += len;
      } while (src.remaining() > 0);
    }
    
    public int write(ByteBuffer src)
      throws IOException
    {
      int len = write(src, this.pos);
      if (len > 0) {
        this.pos += len;
      }
      return len;
    }
    
    public long size()
      throws IOException
    {
      init();
      return this.size;
    }
    
    public FileChannel truncate(long newSize)
      throws IOException
    {
      init();
      if (newSize > this.size) {
        return this;
      }
      if (newSize < 0L) {
        throw new IllegalArgumentException("newSize: " + newSize);
      }
      int offset = (int)(newSize & 0xFFF);
      if (offset > 0) {
        this.base.truncate(newSize + 4096L + 4096L);
      } else {
        this.base.truncate(newSize + 4096L);
      }
      this.size = newSize;
      this.pos = Math.min(this.pos, this.size);
      return this;
    }
    
    public void force(boolean metaData)
      throws IOException
    {
      this.base.force(metaData);
    }
    
    public FileLock tryLock(long position, long size, boolean shared)
      throws IOException
    {
      return this.base.tryLock(position, size, shared);
    }
    
    public String toString()
    {
      return this.name;
    }
  }
  
  static class XTS
  {
    private static final int GF_128_FEEDBACK = 135;
    private static final int CIPHER_BLOCK_SIZE = 16;
    private final BlockCipher cipher;
    
    XTS(BlockCipher cipher)
    {
      this.cipher = cipher;
    }
    
    void encrypt(long id, int len, byte[] data, int offset)
    {
      byte[] tweak = initTweak(id);
      for (int i = 0; i + 16 <= len; i += 16)
      {
        if (i > 0) {
          updateTweak(tweak);
        }
        xorTweak(data, i + offset, tweak);
        this.cipher.encrypt(data, i + offset, 16);
        xorTweak(data, i + offset, tweak);
      }
      if (i < len)
      {
        updateTweak(tweak);
        swap(data, i + offset, i - 16 + offset, len - i);
        xorTweak(data, i - 16 + offset, tweak);
        this.cipher.encrypt(data, i - 16 + offset, 16);
        xorTweak(data, i - 16 + offset, tweak);
      }
    }
    
    void decrypt(long id, int len, byte[] data, int offset)
    {
      byte[] tweak = initTweak(id);byte[] tweakEnd = tweak;
      for (int i = 0; i + 16 <= len; i += 16)
      {
        if (i > 0)
        {
          updateTweak(tweak);
          if ((i + 16 + 16 > len) && (i + 16 < len))
          {
            tweakEnd = Arrays.copyOf(tweak, 16);
            updateTweak(tweak);
          }
        }
        xorTweak(data, i + offset, tweak);
        this.cipher.decrypt(data, i + offset, 16);
        xorTweak(data, i + offset, tweak);
      }
      if (i < len)
      {
        swap(data, i, i - 16 + offset, len - i + offset);
        xorTweak(data, i - 16 + offset, tweakEnd);
        this.cipher.decrypt(data, i - 16 + offset, 16);
        xorTweak(data, i - 16 + offset, tweakEnd);
      }
    }
    
    private byte[] initTweak(long id)
    {
      byte[] tweak = new byte[16];
      for (int j = 0; j < 16; id >>>= 8)
      {
        tweak[j] = ((byte)(int)(id & 0xFF));j++;
      }
      this.cipher.encrypt(tweak, 0, 16);
      return tweak;
    }
    
    private static void xorTweak(byte[] data, int pos, byte[] tweak)
    {
      for (int i = 0; i < 16; i++)
      {
        int tmp12_11 = (pos + i);data[tmp12_11] = ((byte)(data[tmp12_11] ^ tweak[i]));
      }
    }
    
    private static void updateTweak(byte[] tweak)
    {
      byte ci = 0;byte co = 0;
      for (int i = 0; i < 16; i++)
      {
        co = (byte)(tweak[i] >> 7 & 0x1);
        tweak[i] = ((byte)((tweak[i] << 1) + ci & 0xFF));
        ci = co;
      }
      if (co != 0)
      {
        int tmp51_50 = 0;tweak[tmp51_50] = ((byte)(tweak[tmp51_50] ^ 0x87));
      }
    }
    
    private static void swap(byte[] data, int source, int target, int len)
    {
      for (int i = 0; i < len; i++)
      {
        byte temp = data[(source + i)];
        data[(source + i)] = data[(target + i)];
        data[(target + i)] = temp;
      }
    }
  }
}
