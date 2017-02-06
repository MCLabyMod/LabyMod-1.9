package org.h2.result;

import java.util.ArrayList;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.store.Data;
import org.h2.store.FileStore;
import org.h2.util.New;
import org.h2.value.Value;

public class RowList
{
  private final Session session;
  private final ArrayList<Row> list = New.arrayList();
  private int size;
  private int index;
  private int listIndex;
  private FileStore file;
  private Data rowBuff;
  private ArrayList<Value> lobs;
  private final int maxMemory;
  private int memory;
  private boolean written;
  private boolean readUncached;
  
  public RowList(Session session)
  {
    this.session = session;
    if (session.getDatabase().isPersistent()) {
      this.maxMemory = session.getDatabase().getMaxOperationMemory();
    } else {
      this.maxMemory = 0;
    }
  }
  
  private void writeRow(Data buff, Row r)
  {
    buff.checkCapacity(33);
    buff.writeByte((byte)1);
    buff.writeInt(r.getMemory());
    int columnCount = r.getColumnCount();
    buff.writeInt(columnCount);
    buff.writeLong(r.getKey());
    buff.writeInt(r.getVersion());
    buff.writeInt(r.isDeleted() ? 1 : 0);
    buff.writeInt(r.getSessionId());
    for (int i = 0; i < columnCount; i++)
    {
      Value v = r.getValue(i);
      buff.checkCapacity(1);
      if (v == null)
      {
        buff.writeByte((byte)0);
      }
      else
      {
        buff.writeByte((byte)1);
        if ((v.getType() == 16) || (v.getType() == 15)) {
          if ((v.getSmall() == null) && (v.getTableId() == 0))
          {
            if (this.lobs == null) {
              this.lobs = New.arrayList();
            }
            v = v.copyToTemp();
            this.lobs.add(v);
          }
        }
        buff.checkCapacity(buff.getValueLen(v));
        buff.writeValue(v);
      }
    }
  }
  
  private void writeAllRows()
  {
    if (this.file == null)
    {
      Database db = this.session.getDatabase();
      String fileName = db.createTempFile();
      this.file = db.openFile(fileName, "rw", false);
      this.file.setCheckedWriting(false);
      this.file.seek(48L);
      this.rowBuff = Data.create(db, 4096);
      this.file.seek(48L);
    }
    Data buff = this.rowBuff;
    initBuffer(buff);
    int i = 0;
    for (int size = this.list.size(); i < size; i++)
    {
      if ((i > 0) && (buff.length() > 4096))
      {
        flushBuffer(buff);
        initBuffer(buff);
      }
      Row r = (Row)this.list.get(i);
      writeRow(buff, r);
    }
    flushBuffer(buff);
    this.file.autoDelete();
    this.list.clear();
    this.memory = 0;
  }
  
  private static void initBuffer(Data buff)
  {
    buff.reset();
    buff.writeInt(0);
  }
  
  private void flushBuffer(Data buff)
  {
    buff.checkCapacity(1);
    buff.writeByte((byte)0);
    buff.fillAligned();
    buff.setInt(0, buff.length() / 16);
    this.file.write(buff.getBytes(), 0, buff.length());
  }
  
  public void add(Row r)
  {
    this.list.add(r);
    this.memory += r.getMemory() + 8;
    if ((this.maxMemory > 0) && (this.memory > this.maxMemory)) {
      writeAllRows();
    }
    this.size += 1;
  }
  
  public void reset()
  {
    this.index = 0;
    if (this.file != null)
    {
      this.listIndex = 0;
      if (!this.written)
      {
        writeAllRows();
        this.written = true;
      }
      this.list.clear();
      this.file.seek(48L);
    }
  }
  
  public boolean hasNext()
  {
    return this.index < this.size;
  }
  
  private Row readRow(Data buff)
  {
    if (buff.readByte() == 0) {
      return null;
    }
    int mem = buff.readInt();
    int columnCount = buff.readInt();
    long key = buff.readLong();
    int version = buff.readInt();
    if (this.readUncached) {
      key = 0L;
    }
    boolean deleted = buff.readInt() == 1;
    int sessionId = buff.readInt();
    Value[] values = new Value[columnCount];
    for (int i = 0; i < columnCount; i++)
    {
      Value v;
      Value v;
      if (buff.readByte() == 0)
      {
        v = null;
      }
      else
      {
        v = buff.readValue();
        if (v.isLinked()) {
          if (v.getTableId() == 0) {
            this.session.unlinkAtCommit(v);
          }
        }
      }
      values[i] = v;
    }
    Row row = new Row(values, mem);
    row.setKey(key);
    row.setVersion(version);
    row.setDeleted(deleted);
    row.setSessionId(sessionId);
    return row;
  }
  
  public Row next()
  {
    Row r;
    Row r;
    if (this.file == null)
    {
      r = (Row)this.list.get(this.index++);
    }
    else
    {
      if (this.listIndex >= this.list.size())
      {
        this.list.clear();
        this.listIndex = 0;
        Data buff = this.rowBuff;
        buff.reset();
        int min = 16;
        this.file.readFully(buff.getBytes(), 0, min);
        int len = buff.readInt() * 16;
        buff.checkCapacity(len);
        if (len - min > 0) {
          this.file.readFully(buff.getBytes(), min, len - min);
        }
        for (;;)
        {
          Row r = readRow(buff);
          if (r == null) {
            break;
          }
          this.list.add(r);
        }
      }
      this.index += 1;
      r = (Row)this.list.get(this.listIndex++);
    }
    return r;
  }
  
  public int size()
  {
    return this.size;
  }
  
  public void invalidateCache()
  {
    this.readUncached = true;
  }
  
  public void close()
  {
    if (this.file != null)
    {
      this.file.autoDelete();
      this.file.closeAndDeleteSilently();
      this.file = null;
      this.rowBuff = null;
    }
  }
}
