package org.h2.store.fs;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelInputStream
  extends InputStream
{
  private final FileChannel channel;
  private final boolean closeChannel;
  private ByteBuffer buffer;
  private long pos;
  
  public FileChannelInputStream(FileChannel channel, boolean closeChannel)
  {
    this.channel = channel;
    this.closeChannel = closeChannel;
  }
  
  public int read()
    throws IOException
  {
    if (this.buffer == null) {
      this.buffer = ByteBuffer.allocate(1);
    }
    this.buffer.rewind();
    int len = this.channel.read(this.buffer, this.pos++);
    if (len < 0) {
      return -1;
    }
    return this.buffer.get(0) & 0xFF;
  }
  
  public int read(byte[] b)
    throws IOException
  {
    return read(b, 0, b.length);
  }
  
  public int read(byte[] b, int off, int len)
    throws IOException
  {
    ByteBuffer buff = ByteBuffer.wrap(b, off, len);
    int read = this.channel.read(buff, this.pos);
    if (read == -1) {
      return -1;
    }
    this.pos += read;
    return read;
  }
  
  public void close()
    throws IOException
  {
    if (this.closeChannel) {
      this.channel.close();
    }
  }
}
