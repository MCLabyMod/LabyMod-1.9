package org.h2.index;

import org.h2.result.SearchRow;
import org.h2.table.TableFilter;

public abstract interface SpatialIndex
  extends Index
{
  public abstract Cursor findByGeometry(TableFilter paramTableFilter, SearchRow paramSearchRow);
}
