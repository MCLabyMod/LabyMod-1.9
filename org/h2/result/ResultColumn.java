package org.h2.result;

import java.io.IOException;
import org.h2.value.Transfer;

public class ResultColumn
{
  final String alias;
  final String schemaName;
  final String tableName;
  final String columnName;
  final int columnType;
  final long precision;
  final int scale;
  final int displaySize;
  final boolean autoIncrement;
  final int nullable;
  
  ResultColumn(Transfer in)
    throws IOException
  {
    this.alias = in.readString();
    this.schemaName = in.readString();
    this.tableName = in.readString();
    this.columnName = in.readString();
    this.columnType = in.readInt();
    this.precision = in.readLong();
    this.scale = in.readInt();
    this.displaySize = in.readInt();
    this.autoIncrement = in.readBoolean();
    this.nullable = in.readInt();
  }
  
  public static void writeColumn(Transfer out, ResultInterface result, int i)
    throws IOException
  {
    out.writeString(result.getAlias(i));
    out.writeString(result.getSchemaName(i));
    out.writeString(result.getTableName(i));
    out.writeString(result.getColumnName(i));
    out.writeInt(result.getColumnType(i));
    out.writeLong(result.getColumnPrecision(i));
    out.writeInt(result.getColumnScale(i));
    out.writeInt(result.getDisplaySize(i));
    out.writeBoolean(result.isAutoIncrement(i));
    out.writeInt(result.getNullable(i));
  }
}
