package org.h2.table;

public class IndexColumn
{
  public String columnName;
  public Column column;
  public int sortType = 0;
  
  public String getSQL()
  {
    StringBuilder buff = new StringBuilder(this.column.getSQL());
    if ((this.sortType & 0x1) != 0) {
      buff.append(" DESC");
    }
    if ((this.sortType & 0x2) != 0) {
      buff.append(" NULLS FIRST");
    } else if ((this.sortType & 0x4) != 0) {
      buff.append(" NULLS LAST");
    }
    return buff.toString();
  }
  
  public static IndexColumn[] wrap(Column[] columns)
  {
    IndexColumn[] list = new IndexColumn[columns.length];
    for (int i = 0; i < list.length; i++)
    {
      list[i] = new IndexColumn();
      list[i].column = columns[i];
    }
    return list;
  }
  
  public static void mapColumns(IndexColumn[] indexColumns, Table table)
  {
    for (IndexColumn col : indexColumns) {
      col.column = table.getColumn(col.columnName);
    }
  }
}
