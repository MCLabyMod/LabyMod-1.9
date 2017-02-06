package org.h2.store;

import org.h2.message.DbException;
import org.h2.message.Trace;
import org.h2.util.BitField;
import org.h2.util.IntArray;

public class PageOutputStream
{
  private PageStore store;
  private final Trace trace;
  private final BitField exclude;
  private final boolean atEnd;
  private final int minPageId;
  private int trunkPageId;
  private int trunkNext;
  private IntArray reservedPages = new IntArray();
  private PageStreamTrunk trunk;
  private int trunkIndex;
  private PageStreamData data;
  private int reserved;
  private boolean needFlush;
  private boolean writing;
  private int pageCount;
  private int logKey;
  
  public PageOutputStream(PageStore store, int trunkPage, BitField exclude, int logKey, boolean atEnd)
  {
    this.trace = store.getTrace();
    this.store = store;
    this.trunkPageId = trunkPage;
    this.exclude = exclude;
    
    this.logKey = (logKey - 1);
    this.atEnd = atEnd;
    this.minPageId = (atEnd ? trunkPage : 0);
  }
  
  void reserve(int minBuffer)
  {
    if (this.reserved < minBuffer)
    {
      int pageSize = this.store.getPageSize();
      int capacityPerPage = PageStreamData.getCapacity(pageSize);
      int pages = PageStreamTrunk.getPagesAddressed(pageSize);
      int pagesToAllocate = 0;int totalCapacity = 0;
      do
      {
        pagesToAllocate += pages + 1;
        totalCapacity += pages * capacityPerPage;
      } while (totalCapacity < minBuffer);
      int firstPageToUse = this.atEnd ? this.trunkPageId : 0;
      this.store.allocatePages(this.reservedPages, pagesToAllocate, this.exclude, firstPageToUse);
      this.reserved += totalCapacity;
      if (this.data == null) {
        initNextData();
      }
    }
  }
  
  private void initNextData()
  {
    int nextData = this.trunk == null ? -1 : this.trunk.getPageData(this.trunkIndex++);
    if (nextData == -1)
    {
      int parent = this.trunkPageId;
      if (this.trunkNext != 0) {
        this.trunkPageId = this.trunkNext;
      }
      int len = PageStreamTrunk.getPagesAddressed(this.store.getPageSize());
      int[] pageIds = new int[len];
      for (int i = 0; i < len; i++) {
        pageIds[i] = this.reservedPages.get(i);
      }
      this.trunkNext = this.reservedPages.get(len);
      this.logKey += 1;
      this.trunk = PageStreamTrunk.create(this.store, parent, this.trunkPageId, this.trunkNext, this.logKey, pageIds);
      
      this.trunkIndex = 0;
      this.pageCount += 1;
      this.trunk.write();
      this.reservedPages.removeRange(0, len + 1);
      nextData = this.trunk.getPageData(this.trunkIndex++);
    }
    this.data = PageStreamData.create(this.store, nextData, this.trunk.getPos(), this.logKey);
    this.pageCount += 1;
    this.data.initWrite();
  }
  
  public void write(byte[] b, int off, int len)
  {
    if (len <= 0) {
      return;
    }
    if (this.writing) {
      DbException.throwInternalError("writing while still writing");
    }
    try
    {
      reserve(len);
      this.writing = true;
      while (len > 0)
      {
        int l = this.data.write(b, off, len);
        if (l < len)
        {
          storePage();
          initNextData();
        }
        this.reserved -= l;
        off += l;
        len -= l;
      }
      this.needFlush = true;
    }
    finally
    {
      this.writing = false;
    }
  }
  
  private void storePage()
  {
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("pageOut.storePage " + this.data);
    }
    this.data.write();
  }
  
  public void flush()
  {
    if (this.needFlush)
    {
      storePage();
      this.needFlush = false;
    }
  }
  
  public void close()
  {
    this.store = null;
  }
  
  int getCurrentDataPageId()
  {
    return this.data.getPos();
  }
  
  void fillPage()
  {
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("pageOut.storePage fill " + this.data.getPos());
    }
    reserve(this.data.getRemaining() + 1);
    this.reserved -= this.data.getRemaining();
    this.data.write();
    initNextData();
  }
  
  long getSize()
  {
    return this.pageCount * this.store.getPageSize();
  }
  
  void free(PageStreamTrunk t)
  {
    this.pageCount -= t.free(0);
  }
  
  void freeReserved()
  {
    if (this.reservedPages.size() > 0)
    {
      int[] array = new int[this.reservedPages.size()];
      this.reservedPages.toArray(array);
      this.reservedPages = new IntArray();
      this.reserved = 0;
      for (int p : array) {
        this.store.free(p, false);
      }
    }
  }
  
  int getMinPageId()
  {
    return this.minPageId;
  }
}
