package org.h2.engine;

import java.util.ArrayList;
import java.util.HashMap;
import org.h2.message.DbException;
import org.h2.store.Data;
import org.h2.store.FileStore;
import org.h2.table.Table;
import org.h2.util.New;

public class UndoLog
{
  private final Database database;
  private final ArrayList<Long> storedEntriesPos = New.arrayList();
  private final ArrayList<UndoLogRecord> records = New.arrayList();
  private FileStore file;
  private Data rowBuff;
  private int memoryUndo;
  private int storedEntries;
  private HashMap<Integer, Table> tables;
  private final boolean largeTransactions;
  
  UndoLog(Session session)
  {
    this.database = session.getDatabase();
    this.largeTransactions = this.database.getSettings().largeTransactions;
  }
  
  int size()
  {
    if (this.largeTransactions) {
      return this.storedEntries + this.records.size();
    }
    if ((SysProperties.CHECK) && (this.memoryUndo > this.records.size())) {
      DbException.throwInternalError();
    }
    return this.records.size();
  }
  
  void clear()
  {
    this.records.clear();
    this.storedEntries = 0;
    this.storedEntriesPos.clear();
    this.memoryUndo = 0;
    if (this.file != null)
    {
      this.file.closeAndDeleteSilently();
      this.file = null;
      this.rowBuff = null;
    }
  }
  
  public UndoLogRecord getLast()
  {
    int i = this.records.size() - 1;
    if (this.largeTransactions)
    {
      if ((i < 0) && (this.storedEntries > 0))
      {
        int last = this.storedEntriesPos.size() - 1;
        long pos = ((Long)this.storedEntriesPos.get(last)).longValue();
        this.storedEntriesPos.remove(last);
        long end = this.file.length();
        int bufferLength = (int)(end - pos);
        Data buff = Data.create(this.database, bufferLength);
        this.file.seek(pos);
        this.file.readFully(buff.getBytes(), 0, bufferLength);
        while (buff.length() < bufferLength)
        {
          UndoLogRecord e = UndoLogRecord.loadFromBuffer(buff, this);
          this.records.add(e);
          this.memoryUndo += 1;
        }
        this.storedEntries -= this.records.size();
        this.file.setLength(pos);
        this.file.seek(pos);
      }
      i = this.records.size() - 1;
    }
    UndoLogRecord entry = (UndoLogRecord)this.records.get(i);
    if (entry.isStored())
    {
      int start = Math.max(0, i - this.database.getMaxMemoryUndo() / 2);
      UndoLogRecord first = null;
      for (int j = start; j <= i; j++)
      {
        UndoLogRecord e = (UndoLogRecord)this.records.get(j);
        if (e.isStored())
        {
          e.load(this.rowBuff, this.file, this);
          this.memoryUndo += 1;
          if (first == null) {
            first = e;
          }
        }
      }
      for (int k = 0; k < i; k++)
      {
        UndoLogRecord e = (UndoLogRecord)this.records.get(k);
        e.invalidatePos();
      }
      seek(first.getFilePos());
    }
    return entry;
  }
  
  void seek(long filePos)
  {
    this.file.seek(filePos * 16L);
  }
  
  void removeLast(boolean trimToSize)
  {
    int i = this.records.size() - 1;
    UndoLogRecord r = (UndoLogRecord)this.records.remove(i);
    if (!r.isStored()) {
      this.memoryUndo -= 1;
    }
    if ((trimToSize) && (i > 1024) && ((i & 0x3FF) == 0)) {
      this.records.trimToSize();
    }
  }
  
  void add(UndoLogRecord entry)
  {
    this.records.add(entry);
    if (this.largeTransactions)
    {
      this.memoryUndo += 1;
      if ((this.memoryUndo > this.database.getMaxMemoryUndo()) && (this.database.isPersistent()) && (!this.database.isMultiVersion()))
      {
        if (this.file == null)
        {
          String fileName = this.database.createTempFile();
          this.file = this.database.openFile(fileName, "rw", false);
          this.file.setCheckedWriting(false);
          this.file.setLength(48L);
        }
        Data buff = Data.create(this.database, 4096);
        for (int i = 0; i < this.records.size(); i++)
        {
          UndoLogRecord r = (UndoLogRecord)this.records.get(i);
          buff.checkCapacity(4096);
          r.append(buff, this);
          if ((i == this.records.size() - 1) || (buff.length() > 1048576))
          {
            this.storedEntriesPos.add(Long.valueOf(this.file.getFilePointer()));
            this.file.write(buff.getBytes(), 0, buff.length());
            buff.reset();
          }
        }
        this.storedEntries += this.records.size();
        this.memoryUndo = 0;
        this.records.clear();
        this.file.autoDelete();
      }
    }
    else
    {
      if (!entry.isStored()) {
        this.memoryUndo += 1;
      }
      if ((this.memoryUndo > this.database.getMaxMemoryUndo()) && (this.database.isPersistent()) && (!this.database.isMultiVersion()))
      {
        if (this.file == null)
        {
          String fileName = this.database.createTempFile();
          this.file = this.database.openFile(fileName, "rw", false);
          this.file.setCheckedWriting(false);
          this.file.seek(48L);
          this.rowBuff = Data.create(this.database, 4096);
          Data buff = this.rowBuff;
          for (int i = 0; i < this.records.size(); i++)
          {
            UndoLogRecord r = (UndoLogRecord)this.records.get(i);
            saveIfPossible(r, buff);
          }
        }
        else
        {
          saveIfPossible(entry, this.rowBuff);
        }
        this.file.autoDelete();
      }
    }
  }
  
  private void saveIfPossible(UndoLogRecord r, Data buff)
  {
    if ((!r.isStored()) && (r.canStore()))
    {
      r.save(buff, this.file, this);
      this.memoryUndo -= 1;
    }
  }
  
  int getTableId(Table table)
  {
    int id = table.getId();
    if (this.tables == null) {
      this.tables = New.hashMap();
    }
    this.tables.put(Integer.valueOf(id), table);
    return id;
  }
  
  Table getTable(int id)
  {
    return (Table)this.tables.get(Integer.valueOf(id));
  }
}
