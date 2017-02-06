package org.h2.store;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.h2.message.DbException;
import org.h2.message.Trace;
import org.h2.util.BitField;

public class PageInputStream
  extends InputStream
{
  private final PageStore store;
  private final Trace trace;
  private final int firstTrunkPage;
  private final PageStreamTrunk.Iterator trunkIterator;
  private int dataPage;
  private PageStreamTrunk trunk;
  private int trunkIndex;
  private PageStreamData data;
  private int dataPos;
  private boolean endOfFile;
  private int remaining;
  private final byte[] buffer = { 0 };
  private int logKey;
  
  PageInputStream(PageStore store, int logKey, int firstTrunkPage, int dataPage)
  {
    this.store = store;
    this.trace = store.getTrace();
    
    this.logKey = (logKey - 1);
    this.firstTrunkPage = firstTrunkPage;
    this.trunkIterator = new PageStreamTrunk.Iterator(store, firstTrunkPage);
    this.dataPage = dataPage;
  }
  
  public int read()
    throws IOException
  {
    int len = read(this.buffer);
    return len < 0 ? -1 : this.buffer[0] & 0xFF;
  }
  
  public int read(byte[] b)
    throws IOException
  {
    return read(b, 0, b.length);
  }
  
  public int read(byte[] b, int off, int len)
    throws IOException
  {
    if (len == 0) {
      return 0;
    }
    int read = 0;
    while (len > 0)
    {
      int r = readBlock(b, off, len);
      if (r < 0) {
        break;
      }
      read += r;
      off += r;
      len -= r;
    }
    return read == 0 ? -1 : read;
  }
  
  private int readBlock(byte[] buff, int off, int len)
    throws IOException
  {
    try
    {
      fillBuffer();
      if (this.endOfFile) {
        return -1;
      }
      int l = Math.min(this.remaining, len);
      this.data.read(this.dataPos, buff, off, l);
      this.remaining -= l;
      this.dataPos += l;
      return l;
    }
    catch (DbException e)
    {
      throw new EOFException();
    }
  }
  
  private void fillBuffer()
  {
    if ((this.remaining > 0) || (this.endOfFile)) {
      return;
    }
    int next;
    for (;;)
    {
      if (this.trunk == null)
      {
        this.trunk = this.trunkIterator.next();
        this.trunkIndex = 0;
        this.logKey += 1;
        if ((this.trunk == null) || (this.trunk.getLogKey() != this.logKey))
        {
          this.endOfFile = true;
          return;
        }
      }
      if (this.trunk != null)
      {
        next = this.trunk.getPageData(this.trunkIndex++);
        if (next == -1) {
          this.trunk = null;
        } else if (this.dataPage != -1) {
          if (this.dataPage == next) {
            break;
          }
        }
      }
    }
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("pageIn.readPage " + next);
    }
    this.dataPage = -1;
    this.data = null;
    Page p = this.store.getPage(next);
    if ((p instanceof PageStreamData)) {
      this.data = ((PageStreamData)p);
    }
    if ((this.data == null) || (this.data.getLogKey() != this.logKey))
    {
      this.endOfFile = true;
      return;
    }
    this.dataPos = PageStreamData.getReadStart();
    this.remaining = (this.store.getPageSize() - this.dataPos);
  }
  
  BitField allocateAllPages()
  {
    BitField pages = new BitField();
    int key = this.logKey;
    PageStreamTrunk.Iterator it = new PageStreamTrunk.Iterator(this.store, this.firstTrunkPage);
    for (;;)
    {
      PageStreamTrunk t = it.next();
      key++;
      if (it.canDelete()) {
        this.store.allocatePage(it.getCurrentPageId());
      }
      if ((t == null) || (t.getLogKey() != key)) {
        break;
      }
      pages.set(t.getPos());
      for (int i = 0;; i++)
      {
        int n = t.getPageData(i);
        if (n == -1) {
          break;
        }
        pages.set(n);
        this.store.allocatePage(n);
      }
    }
    return pages;
  }
  
  int getDataPage()
  {
    return this.data.getPos();
  }
  
  public void close() {}
}
