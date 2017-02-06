package org.h2.engine;

import java.sql.SQLException;
import org.h2.message.DbException;
import org.h2.result.Row;
import org.h2.store.Data;
import org.h2.store.FileStore;
import org.h2.table.Table;
import org.h2.value.Value;

public class UndoLogRecord
{
  public static final short INSERT = 0;
  public static final short DELETE = 1;
  private static final int IN_MEMORY = 0;
  private static final int STORED = 1;
  private static final int IN_MEMORY_INVALID = 2;
  private Table table;
  private Row row;
  private short operation;
  private short state;
  private int filePos;
  
  UndoLogRecord(Table table, short op, Row row)
  {
    this.table = table;
    this.row = row;
    this.operation = op;
    this.state = 0;
  }
  
  boolean isStored()
  {
    return this.state == 1;
  }
  
  boolean canStore()
  {
    if (this.table.getUniqueIndex() != null) {
      return true;
    }
    return false;
  }
  
  void undo(Session session)
  {
    Database db = session.getDatabase();
    switch (this.operation)
    {
    case 0: 
      if (this.state == 2) {
        this.state = 0;
      }
      if ((db.getLockMode() == 0) && 
        (this.row.isDeleted())) {
        return;
      }
      try
      {
        this.row.setDeleted(false);
        this.table.removeRow(session, this.row);
        this.table.fireAfterRow(session, this.row, null, true);
      }
      catch (DbException e)
      {
        if ((session.getDatabase().getLockMode() != 0) || (e.getErrorCode() != 90112)) {
          throw e;
        }
      }
    case 1: 
      try
      {
        this.table.addRow(session, this.row);
        this.table.fireAfterRow(session, null, this.row, true);
        
        this.row.commit();
      }
      catch (DbException e)
      {
        if ((session.getDatabase().getLockMode() != 0) || (e.getSQLException().getErrorCode() != 23505)) {
          throw e;
        }
      }
    default: 
      DbException.throwInternalError("op=" + this.operation);
    }
  }
  
  void append(Data buff, UndoLog log)
  {
    int p = buff.length();
    buff.writeInt(0);
    buff.writeInt(this.operation);
    buff.writeByte((byte)(this.row.isDeleted() ? 1 : 0));
    buff.writeInt(log.getTableId(this.table));
    buff.writeLong(this.row.getKey());
    buff.writeInt(this.row.getSessionId());
    int count = this.row.getColumnCount();
    buff.writeInt(count);
    for (int i = 0; i < count; i++)
    {
      Value v = this.row.getValue(i);
      buff.checkCapacity(buff.getValueLen(v));
      buff.writeValue(v);
    }
    buff.fillAligned();
    buff.setInt(p, (buff.length() - p) / 16);
  }
  
  void save(Data buff, FileStore file, UndoLog log)
  {
    buff.reset();
    append(buff, log);
    this.filePos = ((int)(file.getFilePointer() / 16L));
    file.write(buff.getBytes(), 0, buff.length());
    this.row = null;
    this.state = 1;
  }
  
  static UndoLogRecord loadFromBuffer(Data buff, UndoLog log)
  {
    UndoLogRecord rec = new UndoLogRecord(null, (short)0, null);
    int pos = buff.length();
    int len = buff.readInt() * 16;
    rec.load(buff, log);
    buff.setPos(pos + len);
    return rec;
  }
  
  void load(Data buff, FileStore file, UndoLog log)
  {
    int min = 16;
    log.seek(this.filePos);
    buff.reset();
    file.readFully(buff.getBytes(), 0, min);
    int len = buff.readInt() * 16;
    buff.checkCapacity(len);
    if (len - min > 0) {
      file.readFully(buff.getBytes(), min, len - min);
    }
    int oldOp = this.operation;
    load(buff, log);
    if ((SysProperties.CHECK) && 
      (this.operation != oldOp)) {
      DbException.throwInternalError("operation=" + this.operation + " op=" + oldOp);
    }
  }
  
  private void load(Data buff, UndoLog log)
  {
    this.operation = ((short)buff.readInt());
    boolean deleted = buff.readByte() == 1;
    this.table = log.getTable(buff.readInt());
    long key = buff.readLong();
    int sessionId = buff.readInt();
    int columnCount = buff.readInt();
    Value[] values = new Value[columnCount];
    for (int i = 0; i < columnCount; i++) {
      values[i] = buff.readValue();
    }
    this.row = new Row(values, -1);
    this.row.setKey(key);
    this.row.setDeleted(deleted);
    this.row.setSessionId(sessionId);
    this.state = 2;
  }
  
  public Table getTable()
  {
    return this.table;
  }
  
  public long getFilePos()
  {
    return this.filePos;
  }
  
  void commit()
  {
    this.table.commit(this.operation, this.row);
  }
  
  public Row getRow()
  {
    return this.row;
  }
  
  void invalidatePos()
  {
    if (this.state == 0) {
      this.state = 2;
    }
  }
}
