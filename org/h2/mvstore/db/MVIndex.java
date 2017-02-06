package org.h2.mvstore.db;

import java.util.List;
import org.h2.index.Index;
import org.h2.result.Row;

public abstract interface MVIndex
  extends Index
{
  public abstract void addRowsToBuffer(List<Row> paramList, String paramString);
  
  public abstract void addBufferedRows(List<String> paramList);
}
