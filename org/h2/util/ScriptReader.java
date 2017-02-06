package org.h2.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import org.h2.message.DbException;

public class ScriptReader
  implements Closeable
{
  private final Reader reader;
  private char[] buffer;
  private int bufferPos;
  private int bufferStart = -1;
  private int bufferEnd;
  private boolean endOfFile;
  private boolean insideRemark;
  private boolean blockRemark;
  private boolean skipRemarks;
  private int remarkStart;
  
  public ScriptReader(Reader reader)
  {
    this.reader = reader;
    this.buffer = new char[' '];
  }
  
  public void close()
  {
    try
    {
      this.reader.close();
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, null);
    }
  }
  
  public String readStatement()
  {
    if (this.endOfFile) {
      return null;
    }
    try
    {
      return readStatementLoop();
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, null);
    }
  }
  
  private String readStatementLoop()
    throws IOException
  {
    this.bufferStart = this.bufferPos;
    int c = read();
    for (;;)
    {
      if (c < 0)
      {
        this.endOfFile = true;
        if (this.bufferPos - 1 != this.bufferStart) {
          break;
        }
        return null;
      }
      if (c == 59) {
        break;
      }
      switch (c)
      {
      case 36: 
        c = read();
        if ((c == 36) && ((this.bufferPos - this.bufferStart < 3) || (this.buffer[(this.bufferPos - 3)] <= ' ')))
        {
          for (;;)
          {
            c = read();
            if (c >= 0) {
              if (c == 36)
              {
                c = read();
                if (c >= 0) {
                  if (c == 36) {
                    break;
                  }
                }
              }
            }
          }
          c = read();
        }
        break;
      case 39: 
        for (;;)
        {
          c = read();
          if (c >= 0) {
            if (c == 39) {
              break;
            }
          }
        }
        c = read();
        break;
      case 34: 
        for (;;)
        {
          c = read();
          if (c >= 0) {
            if (c == 34) {
              break;
            }
          }
        }
        c = read();
        break;
      case 47: 
        c = read();
        if (c == 42)
        {
          startRemark(true);
          do
          {
            do
            {
              c = read();
              if (c < 0) {
                break;
              }
            } while (c != 42);
            c = read();
            if (c < 0)
            {
              clearRemark();
              break;
            }
          } while (c != 47);
          endRemark();
          
          c = read();
        }
        else if (c == 47)
        {
          startRemark(false);
          do
          {
            c = read();
            if (c < 0)
            {
              clearRemark();
              break;
            }
          } while ((c != 13) && (c != 10));
          endRemark();
          
          c = read();
        }
        break;
      case 45: 
        c = read();
        if (c == 45)
        {
          startRemark(false);
          do
          {
            c = read();
            if (c < 0)
            {
              clearRemark();
              break;
            }
          } while ((c != 13) && (c != 10));
          endRemark();
          
          c = read();
        }
        break;
      case 35: 
      case 37: 
      case 38: 
      case 40: 
      case 41: 
      case 42: 
      case 43: 
      case 44: 
      case 46: 
      default: 
        c = read();
      }
    }
    return new String(this.buffer, this.bufferStart, this.bufferPos - 1 - this.bufferStart);
  }
  
  private void startRemark(boolean block)
  {
    this.blockRemark = block;
    this.remarkStart = (this.bufferPos - 2);
    this.insideRemark = true;
  }
  
  private void endRemark()
  {
    clearRemark();
    this.insideRemark = false;
  }
  
  private void clearRemark()
  {
    if (this.skipRemarks) {
      Arrays.fill(this.buffer, this.remarkStart, this.bufferPos, ' ');
    }
  }
  
  private int read()
    throws IOException
  {
    if (this.bufferPos >= this.bufferEnd) {
      return readBuffer();
    }
    return this.buffer[(this.bufferPos++)];
  }
  
  private int readBuffer()
    throws IOException
  {
    if (this.endOfFile) {
      return -1;
    }
    int keep = this.bufferPos - this.bufferStart;
    if (keep > 0)
    {
      char[] src = this.buffer;
      if (keep + 4096 > src.length)
      {
        if (src.length >= 1073741823) {
          throw new IOException("Error in parsing script, statement size exceeds 1G, first 80 characters of statement looks like: " + new String(this.buffer, this.bufferStart, 80));
        }
        this.buffer = new char[src.length * 2];
      }
      System.arraycopy(src, this.bufferStart, this.buffer, 0, keep);
    }
    this.remarkStart -= this.bufferStart;
    this.bufferStart = 0;
    this.bufferPos = keep;
    int len = this.reader.read(this.buffer, keep, 4096);
    if (len == -1)
    {
      this.bufferEnd = 64512;
      this.endOfFile = true;
      
      this.bufferPos += 1;
      return -1;
    }
    this.bufferEnd = (keep + len);
    return this.buffer[(this.bufferPos++)];
  }
  
  public boolean isInsideRemark()
  {
    return this.insideRemark;
  }
  
  public boolean isBlockRemark()
  {
    return this.blockRemark;
  }
  
  public void setSkipRemarks(boolean skipRemarks)
  {
    this.skipRemarks = skipRemarks;
  }
}
