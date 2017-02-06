package org.h2.index;

import org.h2.result.Row;
import org.h2.result.SearchRow;

public class TreeCursor
  implements Cursor
{
  private final TreeIndex tree;
  private TreeNode node;
  private boolean beforeFirst;
  private final SearchRow first;
  private final SearchRow last;
  
  TreeCursor(TreeIndex tree, TreeNode node, SearchRow first, SearchRow last)
  {
    this.tree = tree;
    this.node = node;
    this.first = first;
    this.last = last;
    this.beforeFirst = true;
  }
  
  public Row get()
  {
    return this.node == null ? null : this.node.row;
  }
  
  public SearchRow getSearchRow()
  {
    return get();
  }
  
  public boolean next()
  {
    if (this.beforeFirst)
    {
      this.beforeFirst = false;
      if (this.node == null) {
        return false;
      }
      if ((this.first != null) && (this.tree.compareRows(this.node.row, this.first) < 0)) {
        this.node = next(this.node);
      }
    }
    else
    {
      this.node = next(this.node);
    }
    if ((this.node != null) && (this.last != null) && 
      (this.tree.compareRows(this.node.row, this.last) > 0)) {
      this.node = null;
    }
    return this.node != null;
  }
  
  public boolean previous()
  {
    this.node = previous(this.node);
    return this.node != null;
  }
  
  private static TreeNode next(TreeNode x)
  {
    if (x == null) {
      return null;
    }
    TreeNode r = x.right;
    if (r != null)
    {
      x = r;
      TreeNode l = x.left;
      while (l != null)
      {
        x = l;
        l = x.left;
      }
      return x;
    }
    TreeNode ch = x;
    x = x.parent;
    while ((x != null) && (ch == x.right))
    {
      ch = x;
      x = x.parent;
    }
    return x;
  }
  
  private static TreeNode previous(TreeNode x)
  {
    if (x == null) {
      return null;
    }
    TreeNode l = x.left;
    if (l != null)
    {
      x = l;
      TreeNode r = x.right;
      while (r != null)
      {
        x = r;
        r = x.right;
      }
      return x;
    }
    TreeNode ch = x;
    x = x.parent;
    while ((x != null) && (ch == x.left))
    {
      ch = x;
      x = x.parent;
    }
    return x;
  }
}
