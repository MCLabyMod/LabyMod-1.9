package org.h2.expression;

import java.io.IOException;
import org.h2.message.DbException;
import org.h2.value.Transfer;
import org.h2.value.Value;

public class ParameterRemote
  implements ParameterInterface
{
  private Value value;
  private final int index;
  private int dataType = -1;
  private long precision;
  private int scale;
  private int nullable = 2;
  
  public ParameterRemote(int index)
  {
    this.index = index;
  }
  
  public void setValue(Value newValue, boolean closeOld)
  {
    if ((closeOld) && (this.value != null)) {
      this.value.close();
    }
    this.value = newValue;
  }
  
  public Value getParamValue()
  {
    return this.value;
  }
  
  public void checkSet()
  {
    if (this.value == null) {
      throw DbException.get(90012, "#" + (this.index + 1));
    }
  }
  
  public boolean isValueSet()
  {
    return this.value != null;
  }
  
  public int getType()
  {
    return this.value == null ? this.dataType : this.value.getType();
  }
  
  public long getPrecision()
  {
    return this.value == null ? this.precision : this.value.getPrecision();
  }
  
  public int getScale()
  {
    return this.value == null ? this.scale : this.value.getScale();
  }
  
  public int getNullable()
  {
    return this.nullable;
  }
  
  public void readMetaData(Transfer transfer)
    throws IOException
  {
    this.dataType = transfer.readInt();
    this.precision = transfer.readLong();
    this.scale = transfer.readInt();
    this.nullable = transfer.readInt();
  }
  
  public static void writeMetaData(Transfer transfer, ParameterInterface p)
    throws IOException
  {
    transfer.writeInt(p.getType());
    transfer.writeLong(p.getPrecision());
    transfer.writeInt(p.getScale());
    transfer.writeInt(p.getNullable());
  }
}
