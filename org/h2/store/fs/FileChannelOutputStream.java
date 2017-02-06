package org.h2.store.fs;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelOutputStream
  extends OutputStream
{
  private final FileChannel channel;
  private final byte[] buffer = { 0 };
  
  public FileChannelOutputStream(FileChannel channel, boolean append)
    throws IOException
  {
    this.channel = channel;
    if (append)
    {
      channel.position(channel.size());
    }
    else
    {
      channel.position(0L);
      channel.truncate(0L);
    }
  }
  
  public void write(int b)
    throws IOException
  {
    this.buffer[0] = ((byte)b);
    FileUtils.writeFully(this.channel, ByteBuffer.wrap(this.buffer));
  }
  
  public void write(byte[] b)
    throws IOException
  {
    FileUtils.writeFully(this.channel, ByteBuffer.wrap(b));
  }
  
  public void write(byte[] b, int off, int len)
    throws IOException
  {
    FileUtils.writeFully(this.channel, ByteBuffer.wrap(b, off, len));
  }
  
  public void close()
    throws IOException
  {
    this.channel.close();
  }
}
