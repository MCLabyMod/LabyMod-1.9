package org.h2.value;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import org.h2.engine.Constants;
import org.h2.engine.SessionInterface;
import org.h2.message.DbException;
import org.h2.mvstore.DataUtils;
import org.h2.security.SHA256;
import org.h2.store.Data;
import org.h2.store.DataHandler;
import org.h2.store.DataReader;
import org.h2.store.LobStorageInterface;
import org.h2.tools.SimpleResultSet;
import org.h2.util.DateTimeUtils;
import org.h2.util.IOUtils;
import org.h2.util.JdbcUtils;
import org.h2.util.MathUtils;
import org.h2.util.NetUtils;
import org.h2.util.StringUtils;
import org.h2.util.Utils;

public class Transfer
{
  private static final int BUFFER_SIZE = 65536;
  private static final int LOB_MAGIC = 4660;
  private static final int LOB_MAC_SALT_LENGTH = 16;
  private Socket socket;
  private DataInputStream in;
  private DataOutputStream out;
  private SessionInterface session;
  private boolean ssl;
  private int version;
  private byte[] lobMacSalt;
  
  public Transfer(SessionInterface session)
  {
    this.session = session;
  }
  
  public void setSocket(Socket s)
  {
    this.socket = s;
  }
  
  public synchronized void init()
    throws IOException
  {
    if (this.socket != null)
    {
      this.in = new DataInputStream(new BufferedInputStream(this.socket.getInputStream(), 65536));
      
      this.out = new DataOutputStream(new BufferedOutputStream(this.socket.getOutputStream(), 65536));
    }
  }
  
  public void flush()
    throws IOException
  {
    this.out.flush();
  }
  
  public Transfer writeBoolean(boolean x)
    throws IOException
  {
    this.out.writeByte((byte)(x ? 1 : 0));
    return this;
  }
  
  public boolean readBoolean()
    throws IOException
  {
    return this.in.readByte() == 1;
  }
  
  private Transfer writeByte(byte x)
    throws IOException
  {
    this.out.writeByte(x);
    return this;
  }
  
  private byte readByte()
    throws IOException
  {
    return this.in.readByte();
  }
  
  public Transfer writeInt(int x)
    throws IOException
  {
    this.out.writeInt(x);
    return this;
  }
  
  public int readInt()
    throws IOException
  {
    return this.in.readInt();
  }
  
  public Transfer writeLong(long x)
    throws IOException
  {
    this.out.writeLong(x);
    return this;
  }
  
  public long readLong()
    throws IOException
  {
    return this.in.readLong();
  }
  
  private Transfer writeDouble(double i)
    throws IOException
  {
    this.out.writeDouble(i);
    return this;
  }
  
  private Transfer writeFloat(float i)
    throws IOException
  {
    this.out.writeFloat(i);
    return this;
  }
  
  private double readDouble()
    throws IOException
  {
    return this.in.readDouble();
  }
  
  private float readFloat()
    throws IOException
  {
    return this.in.readFloat();
  }
  
  public Transfer writeString(String s)
    throws IOException
  {
    if (s == null)
    {
      this.out.writeInt(-1);
    }
    else
    {
      int len = s.length();
      this.out.writeInt(len);
      for (int i = 0; i < len; i++) {
        this.out.writeChar(s.charAt(i));
      }
    }
    return this;
  }
  
  public String readString()
    throws IOException
  {
    int len = this.in.readInt();
    if (len == -1) {
      return null;
    }
    StringBuilder buff = new StringBuilder(len);
    for (int i = 0; i < len; i++) {
      buff.append(this.in.readChar());
    }
    String s = buff.toString();
    s = StringUtils.cache(s);
    return s;
  }
  
  public Transfer writeBytes(byte[] data)
    throws IOException
  {
    if (data == null)
    {
      writeInt(-1);
    }
    else
    {
      writeInt(data.length);
      this.out.write(data);
    }
    return this;
  }
  
  public Transfer writeBytes(byte[] buff, int off, int len)
    throws IOException
  {
    this.out.write(buff, off, len);
    return this;
  }
  
  public byte[] readBytes()
    throws IOException
  {
    int len = readInt();
    if (len == -1) {
      return null;
    }
    byte[] b = DataUtils.newBytes(len);
    this.in.readFully(b);
    return b;
  }
  
  public void readBytes(byte[] buff, int off, int len)
    throws IOException
  {
    this.in.readFully(buff, off, len);
  }
  
  public synchronized void close()
  {
    if (this.socket != null) {
      try
      {
        if (this.out != null) {
          this.out.flush();
        }
        if (this.socket != null) {
          this.socket.close();
        }
      }
      catch (IOException e)
      {
        DbException.traceThrowable(e);
      }
      finally
      {
        this.socket = null;
      }
    }
  }
  
  public void writeValue(Value v)
    throws IOException
  {
    int type = v.getType();
    writeInt(type);
    switch (type)
    {
    case 0: 
      break;
    case 12: 
    case 19: 
      writeBytes(v.getBytesNoCopy());
      break;
    case 20: 
      ValueUuid uuid = (ValueUuid)v;
      writeLong(uuid.getHigh());
      writeLong(uuid.getLow());
      break;
    case 1: 
      writeBoolean(v.getBoolean().booleanValue());
      break;
    case 2: 
      writeByte(v.getByte());
      break;
    case 9: 
      if (this.version >= 9) {
        writeLong(((ValueTime)v).getNanos());
      } else if (this.version >= 7) {
        writeLong(DateTimeUtils.getTimeLocalWithoutDst(v.getTime()));
      } else {
        writeLong(v.getTime().getTime());
      }
      break;
    case 10: 
      if (this.version >= 9) {
        writeLong(((ValueDate)v).getDateValue());
      } else if (this.version >= 7) {
        writeLong(DateTimeUtils.getTimeLocalWithoutDst(v.getDate()));
      } else {
        writeLong(v.getDate().getTime());
      }
      break;
    case 11: 
      if (this.version >= 9)
      {
        ValueTimestamp ts = (ValueTimestamp)v;
        writeLong(ts.getDateValue());
        writeLong(ts.getTimeNanos());
      }
      else if (this.version >= 7)
      {
        Timestamp ts = v.getTimestamp();
        writeLong(DateTimeUtils.getTimeLocalWithoutDst(ts));
        writeInt(ts.getNanos() % 1000000);
      }
      else
      {
        Timestamp ts = v.getTimestamp();
        writeLong(ts.getTime());
        writeInt(ts.getNanos() % 1000000);
      }
      break;
    case 6: 
      writeString(v.getString());
      break;
    case 7: 
      writeDouble(v.getDouble());
      break;
    case 8: 
      writeFloat(v.getFloat());
      break;
    case 4: 
      writeInt(v.getInt());
      break;
    case 5: 
      writeLong(v.getLong());
      break;
    case 3: 
      writeInt(v.getShort());
      break;
    case 13: 
    case 14: 
    case 21: 
      writeString(v.getString());
      break;
    case 15: 
      if ((this.version >= 11) && 
        ((v instanceof ValueLobDb)))
      {
        ValueLobDb lob = (ValueLobDb)v;
        if (lob.isStored())
        {
          writeLong(-1L);
          writeInt(lob.getTableId());
          writeLong(lob.getLobId());
          if (this.version >= 12) {
            writeBytes(calculateLobMac(lob.getLobId()));
          }
          writeLong(lob.getPrecision());
          break;
        }
      }
      long length = v.getPrecision();
      if (length < 0L) {
        throw DbException.get(90067, "length=" + length);
      }
      writeLong(length);
      long written = IOUtils.copyAndCloseInput(v.getInputStream(), this.out);
      if (written != length) {
        throw DbException.get(90067, "length:" + length + " written:" + written);
      }
      writeInt(4660);
      break;
    case 16: 
      if ((this.version >= 11) && 
        ((v instanceof ValueLobDb)))
      {
        ValueLobDb lob = (ValueLobDb)v;
        if (lob.isStored())
        {
          writeLong(-1L);
          writeInt(lob.getTableId());
          writeLong(lob.getLobId());
          if (this.version >= 12) {
            writeBytes(calculateLobMac(lob.getLobId()));
          }
          writeLong(lob.getPrecision());
          break;
        }
      }
      long length = v.getPrecision();
      if (length < 0L) {
        throw DbException.get(90067, "length=" + length);
      }
      writeLong(length);
      Reader reader = v.getReader();
      Data.copyString(reader, this.out);
      writeInt(4660);
      break;
    case 17: 
      ValueArray va = (ValueArray)v;
      Value[] list = va.getList();
      int len = list.length;
      Class<?> componentType = va.getComponentType();
      if (componentType == Object.class)
      {
        writeInt(len);
      }
      else
      {
        writeInt(-(len + 1));
        writeString(componentType.getName());
      }
      for (Value value : list) {
        writeValue(value);
      }
      break;
    case 18: 
      try
      {
        ResultSet rs = ((ValueResultSet)v).getResultSet();
        rs.beforeFirst();
        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();
        writeInt(columnCount);
        for (int i = 0; i < columnCount; i++)
        {
          writeString(meta.getColumnName(i + 1));
          writeInt(meta.getColumnType(i + 1));
          writeInt(meta.getPrecision(i + 1));
          writeInt(meta.getScale(i + 1));
        }
        while (rs.next())
        {
          writeBoolean(true);
          for (int i = 0; i < columnCount; i++)
          {
            int t = DataType.getValueTypeFromResultSet(meta, i + 1);
            Value val = DataType.readValue(this.session, rs, i + 1, t);
            writeValue(val);
          }
        }
        writeBoolean(false);
        rs.beforeFirst();
      }
      catch (SQLException e)
      {
        throw DbException.convertToIOException(e);
      }
    case 22: 
      if (this.version >= 14) {
        writeBytes(v.getBytesNoCopy());
      } else {
        writeString(v.getString());
      }
      break;
    default: 
      throw DbException.get(90067, "type=" + type);
    }
  }
  
  public Value readValue()
    throws IOException
  {
    int type = readInt();
    switch (type)
    {
    case 0: 
      return ValueNull.INSTANCE;
    case 12: 
      return ValueBytes.getNoCopy(readBytes());
    case 20: 
      return ValueUuid.get(readLong(), readLong());
    case 19: 
      return ValueJavaObject.getNoCopy(null, readBytes(), this.session.getDataHandler());
    case 1: 
      return ValueBoolean.get(readBoolean());
    case 2: 
      return ValueByte.get(readByte());
    case 10: 
      if (this.version >= 9) {
        return ValueDate.fromDateValue(readLong());
      }
      if (this.version >= 7) {
        return ValueDate.fromMillis(DateTimeUtils.getTimeUTCWithoutDst(readLong()));
      }
      return ValueDate.fromMillis(readLong());
    case 9: 
      if (this.version >= 9) {
        return ValueTime.fromNanos(readLong());
      }
      if (this.version >= 7) {
        return ValueTime.fromMillis(DateTimeUtils.getTimeUTCWithoutDst(readLong()));
      }
      return ValueTime.fromMillis(readLong());
    case 11: 
      if (this.version >= 9) {
        return ValueTimestamp.fromDateValueAndNanos(readLong(), readLong());
      }
      if (this.version >= 7) {
        return ValueTimestamp.fromMillisNanos(DateTimeUtils.getTimeUTCWithoutDst(readLong()), readInt() % 1000000);
      }
      return ValueTimestamp.fromMillisNanos(readLong(), readInt() % 1000000);
    case 6: 
      return ValueDecimal.get(new BigDecimal(readString()));
    case 7: 
      return ValueDouble.get(readDouble());
    case 8: 
      return ValueFloat.get(readFloat());
    case 4: 
      return ValueInt.get(readInt());
    case 5: 
      return ValueLong.get(readLong());
    case 3: 
      return ValueShort.get((short)readInt());
    case 13: 
      return ValueString.get(readString());
    case 14: 
      return ValueStringIgnoreCase.get(readString());
    case 21: 
      return ValueStringFixed.get(readString());
    case 15: 
      long length = readLong();
      if (this.version >= 11)
      {
        if (length == -1L)
        {
          int tableId = readInt();
          long id = readLong();
          byte[] hmac;
          byte[] hmac;
          if (this.version >= 12) {
            hmac = readBytes();
          } else {
            hmac = null;
          }
          long precision = readLong();
          return ValueLobDb.create(15, this.session.getDataHandler(), tableId, id, hmac, precision);
        }
        int len = (int)length;
        byte[] small = new byte[len];
        IOUtils.readFully(this.in, small, len);
        int magic = readInt();
        if (magic != 4660) {
          throw DbException.get(90067, "magic=" + magic);
        }
        return ValueLobDb.createSmallLob(15, small, length);
      }
      Value v = this.session.getDataHandler().getLobStorage().createBlob(this.in, length);
      int magic = readInt();
      if (magic != 4660) {
        throw DbException.get(90067, "magic=" + magic);
      }
      return v;
    case 16: 
      long length = readLong();
      if (this.version >= 11)
      {
        if (length == -1L)
        {
          int tableId = readInt();
          long id = readLong();
          byte[] hmac;
          byte[] hmac;
          if (this.version >= 12) {
            hmac = readBytes();
          } else {
            hmac = null;
          }
          long precision = readLong();
          return ValueLobDb.create(16, this.session.getDataHandler(), tableId, id, hmac, precision);
        }
        DataReader reader = new DataReader(this.in);
        int len = (int)length;
        char[] buff = new char[len];
        IOUtils.readFully(reader, buff, len);
        int magic = readInt();
        if (magic != 4660) {
          throw DbException.get(90067, "magic=" + magic);
        }
        byte[] small = new String(buff).getBytes(Constants.UTF8);
        return ValueLobDb.createSmallLob(16, small, length);
      }
      Value v = this.session.getDataHandler().getLobStorage().createClob(new DataReader(this.in), length);
      
      int magic = readInt();
      if (magic != 4660) {
        throw DbException.get(90067, "magic=" + magic);
      }
      return v;
    case 17: 
      int len = readInt();
      Class<?> componentType = Object.class;
      if (len < 0)
      {
        len = -(len + 1);
        componentType = JdbcUtils.loadUserClass(readString());
      }
      Value[] list = new Value[len];
      for (int i = 0; i < len; i++) {
        list[i] = readValue();
      }
      return ValueArray.get(componentType, list);
    case 18: 
      SimpleResultSet rs = new SimpleResultSet();
      rs.setAutoClose(false);
      int columns = readInt();
      for (int i = 0; i < columns; i++) {
        rs.addColumn(readString(), readInt(), readInt(), readInt());
      }
      while (readBoolean())
      {
        Object[] o = new Object[columns];
        for (int i = 0; i < columns; i++) {
          o[i] = readValue().getObject();
        }
        rs.addRow(o);
      }
      return ValueResultSet.get(rs);
    case 22: 
      if (this.version >= 14) {
        return ValueGeometry.get(readBytes());
      }
      break;
    }
    throw DbException.get(90067, "type=" + type);
  }
  
  public Socket getSocket()
  {
    return this.socket;
  }
  
  public void setSession(SessionInterface session)
  {
    this.session = session;
  }
  
  public void setSSL(boolean ssl)
  {
    this.ssl = ssl;
  }
  
  public Transfer openNewConnection()
    throws IOException
  {
    InetAddress address = this.socket.getInetAddress();
    int port = this.socket.getPort();
    Socket s2 = NetUtils.createSocket(address, port, this.ssl);
    Transfer trans = new Transfer(null);
    trans.setSocket(s2);
    trans.setSSL(this.ssl);
    return trans;
  }
  
  public void setVersion(int version)
  {
    this.version = version;
  }
  
  public synchronized boolean isClosed()
  {
    return (this.socket == null) || (this.socket.isClosed());
  }
  
  public void verifyLobMac(byte[] hmac, long lobId)
  {
    byte[] result = calculateLobMac(lobId);
    if (!Utils.compareSecure(hmac, result)) {
      throw DbException.get(90067, "Invalid lob hmac; possibly the connection was re-opened internally");
    }
  }
  
  private byte[] calculateLobMac(long lobId)
  {
    if (this.lobMacSalt == null) {
      this.lobMacSalt = MathUtils.secureRandomBytes(16);
    }
    byte[] data = new byte[8];
    Utils.writeLong(data, 0, lobId);
    byte[] hmacData = SHA256.getHashWithSalt(data, this.lobMacSalt);
    return hmacData;
  }
}
