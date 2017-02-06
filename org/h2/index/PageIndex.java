package org.h2.index;

public abstract class PageIndex
  extends BaseIndex
{
  protected int rootPageId;
  private boolean sortedInsertMode;
  
  public int getRootPageId()
  {
    return this.rootPageId;
  }
  
  public abstract void writeRowCount();
  
  public void setSortedInsertMode(boolean sortedInsertMode)
  {
    this.sortedInsertMode = sortedInsertMode;
  }
  
  boolean isSortedInsertMode()
  {
    return this.sortedInsertMode;
  }
}
