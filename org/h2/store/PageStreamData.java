package org.h2.store;

import org.h2.engine.Session;

public class PageStreamData
  extends Page
{
  private static final int DATA_START = 11;
  private final PageStore store;
  private int trunk;
  private int logKey;
  private Data data;
  private int remaining;
  
  private PageStreamData(PageStore store, int pageId, int trunk, int logKey)
  {
    setPos(pageId);
    this.store = store;
    this.trunk = trunk;
    this.logKey = logKey;
  }
  
  static PageStreamData read(PageStore store, Data data, int pageId)
  {
    PageStreamData p = new PageStreamData(store, pageId, 0, 0);
    p.data = data;
    p.read();
    return p;
  }
  
  static PageStreamData create(PageStore store, int pageId, int trunk, int logKey)
  {
    return new PageStreamData(store, pageId, trunk, logKey);
  }
  
  private void read()
  {
    this.data.reset();
    this.data.readByte();
    this.data.readShortInt();
    this.trunk = this.data.readInt();
    this.logKey = this.data.readInt();
  }
  
  void initWrite()
  {
    this.data = this.store.createData();
    this.data.writeByte((byte)8);
    this.data.writeShortInt(0);
    this.data.writeInt(this.trunk);
    this.data.writeInt(this.logKey);
    this.remaining = (this.store.getPageSize() - this.data.length());
  }
  
  int write(byte[] buff, int offset, int len)
  {
    int max = Math.min(this.remaining, len);
    this.data.write(buff, offset, max);
    this.remaining -= max;
    return max;
  }
  
  public void write()
  {
    this.store.writePage(getPos(), this.data);
  }
  
  static int getCapacity(int pageSize)
  {
    return pageSize - 11;
  }
  
  void read(int startPos, byte[] buff, int off, int len)
  {
    System.arraycopy(this.data.getBytes(), startPos, buff, off, len);
  }
  
  int getRemaining()
  {
    return this.remaining;
  }
  
  public int getMemory()
  {
    return this.store.getPageSize() >> 2;
  }
  
  public void moveTo(Session session, int newPos) {}
  
  int getLogKey()
  {
    return this.logKey;
  }
  
  public String toString()
  {
    return "[" + getPos() + "] stream data key:" + this.logKey + " pos:" + this.data.length() + " remaining:" + this.remaining;
  }
  
  public boolean canRemove()
  {
    return true;
  }
  
  public static int getReadStart()
  {
    return 11;
  }
  
  public boolean canMove()
  {
    return false;
  }
}
