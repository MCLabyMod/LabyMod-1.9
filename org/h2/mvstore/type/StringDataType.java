package org.h2.mvstore.type;

import java.nio.ByteBuffer;
import org.h2.mvstore.DataUtils;
import org.h2.mvstore.WriteBuffer;

public class StringDataType
  implements DataType
{
  public static final StringDataType INSTANCE = new StringDataType();
  
  public int compare(Object a, Object b)
  {
    return a.toString().compareTo(b.toString());
  }
  
  public int getMemory(Object obj)
  {
    return 24 + 2 * obj.toString().length();
  }
  
  public void read(ByteBuffer buff, Object[] obj, int len, boolean key)
  {
    for (int i = 0; i < len; i++) {
      obj[i] = read(buff);
    }
  }
  
  public void write(WriteBuffer buff, Object[] obj, int len, boolean key)
  {
    for (int i = 0; i < len; i++) {
      write(buff, obj[i]);
    }
  }
  
  public String read(ByteBuffer buff)
  {
    int len = DataUtils.readVarInt(buff);
    return DataUtils.readString(buff, len);
  }
  
  public void write(WriteBuffer buff, Object obj)
  {
    String s = obj.toString();
    int len = s.length();
    buff.putVarInt(len).putStringData(s, len);
  }
}
