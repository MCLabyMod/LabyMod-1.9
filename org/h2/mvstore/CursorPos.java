package org.h2.mvstore;

public class CursorPos
{
  public Page page;
  public int index;
  public final CursorPos parent;
  
  public CursorPos(Page page, int index, CursorPos parent)
  {
    this.page = page;
    this.index = index;
    this.parent = parent;
  }
}
