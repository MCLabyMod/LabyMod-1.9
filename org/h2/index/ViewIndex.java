package org.h2.index;

import java.util.ArrayList;
import org.h2.command.dml.Query;
import org.h2.command.dml.SelectUnion;
import org.h2.engine.Session;
import org.h2.expression.Parameter;
import org.h2.message.DbException;
import org.h2.result.LocalResult;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.result.SortOrder;
import org.h2.table.Column;
import org.h2.table.IndexColumn;
import org.h2.table.Table;
import org.h2.table.TableFilter;
import org.h2.table.TableView;
import org.h2.util.IntArray;
import org.h2.util.New;
import org.h2.util.SmallLRUCache;
import org.h2.util.SynchronizedVerifier;
import org.h2.util.Utils;
import org.h2.value.Value;

public class ViewIndex
  extends BaseIndex
  implements SpatialIndex
{
  private final TableView view;
  private final String querySQL;
  private final ArrayList<Parameter> originalParameters;
  private final SmallLRUCache<IntArray, CostElement> costCache = SmallLRUCache.newInstance(64);
  private boolean recursive;
  private final int[] indexMasks;
  private Query query;
  private final Session createSession;
  
  public ViewIndex(TableView view, String querySQL, ArrayList<Parameter> originalParameters, boolean recursive)
  {
    initBaseIndex(view, 0, null, null, IndexType.createNonUnique(false));
    this.view = view;
    this.querySQL = querySQL;
    this.originalParameters = originalParameters;
    this.recursive = recursive;
    this.columns = new Column[0];
    this.createSession = null;
    this.indexMasks = null;
  }
  
  public ViewIndex(TableView view, ViewIndex index, Session session, int[] masks)
  {
    initBaseIndex(view, 0, null, null, IndexType.createNonUnique(false));
    this.view = view;
    this.querySQL = index.querySQL;
    this.originalParameters = index.originalParameters;
    this.recursive = index.recursive;
    this.indexMasks = masks;
    this.createSession = session;
    this.columns = new Column[0];
    if (!this.recursive) {
      this.query = getQuery(session, masks);
    }
  }
  
  public Session getSession()
  {
    return this.createSession;
  }
  
  public String getPlanSQL()
  {
    return this.query == null ? null : this.query.getPlanSQL();
  }
  
  public void close(Session session) {}
  
  public void add(Session session, Row row)
  {
    throw DbException.getUnsupportedException("VIEW");
  }
  
  public void remove(Session session, Row row)
  {
    throw DbException.getUnsupportedException("VIEW");
  }
  
  public synchronized double getCost(Session session, int[] masks, TableFilter filter, SortOrder sortOrder)
  {
    if (this.recursive) {
      return 1000.0D;
    }
    IntArray masksArray = new IntArray(masks == null ? Utils.EMPTY_INT_ARRAY : masks);
    
    SynchronizedVerifier.check(this.costCache);
    CostElement cachedCost = (CostElement)this.costCache.get(masksArray);
    if (cachedCost != null)
    {
      long time = System.currentTimeMillis();
      if (time < cachedCost.evaluatedAt + 10000L) {
        return cachedCost.cost;
      }
    }
    Query q = (Query)session.prepare(this.querySQL, true);
    if (masks != null)
    {
      IntArray paramIndex = new IntArray();
      for (int i = 0; i < masks.length; i++)
      {
        int mask = masks[i];
        if (mask != 0) {
          paramIndex.add(i);
        }
      }
      int len = paramIndex.size();
      for (int i = 0; i < len; i++)
      {
        int idx = paramIndex.get(i);
        int mask = masks[idx];
        int nextParamIndex = q.getParameters().size() + this.view.getParameterOffset();
        if ((mask & 0x1) != 0)
        {
          Parameter param = new Parameter(nextParamIndex);
          q.addGlobalCondition(param, idx, 16);
        }
        else if ((mask & 0x10) != 0)
        {
          Parameter param = new Parameter(nextParamIndex);
          q.addGlobalCondition(param, idx, 11);
        }
        else
        {
          if ((mask & 0x2) != 0)
          {
            Parameter param = new Parameter(nextParamIndex);
            q.addGlobalCondition(param, idx, 1);
          }
          if ((mask & 0x4) != 0)
          {
            Parameter param = new Parameter(nextParamIndex);
            q.addGlobalCondition(param, idx, 3);
          }
        }
      }
      String sql = q.getPlanSQL();
      q = (Query)session.prepare(sql, true);
    }
    double cost = q.getCost();
    cachedCost = new CostElement();
    cachedCost.evaluatedAt = System.currentTimeMillis();
    cachedCost.cost = cost;
    this.costCache.put(masksArray, cachedCost);
    return cost;
  }
  
  public Cursor find(Session session, SearchRow first, SearchRow last)
  {
    return find(session, first, last, null);
  }
  
  public Cursor findByGeometry(TableFilter filter, SearchRow intersection)
  {
    return find(filter.getSession(), null, null, intersection);
  }
  
  private Cursor find(Session session, SearchRow first, SearchRow last, SearchRow intersection)
  {
    if (this.recursive)
    {
      LocalResult recResult = this.view.getRecursiveResult();
      if (recResult != null)
      {
        recResult.reset();
        return new ViewCursor(this, recResult, first, last);
      }
      if (this.query == null) {
        this.query = ((Query)this.createSession.prepare(this.querySQL, true));
      }
      if (!(this.query instanceof SelectUnion)) {
        throw DbException.get(42001, "recursive queries without UNION ALL");
      }
      SelectUnion union = (SelectUnion)this.query;
      if (union.getUnionType() != 1) {
        throw DbException.get(42001, "recursive queries without UNION ALL");
      }
      Query left = union.getLeft();
      
      left.disableCache();
      LocalResult r = left.query(0);
      LocalResult result = union.getEmptyResult();
      
      result.setMaxMemoryRows(Integer.MAX_VALUE);
      while (r.next()) {
        result.addRow(r.currentRow());
      }
      Query right = union.getRight();
      r.reset();
      this.view.setRecursiveResult(r);
      
      right.disableCache();
      for (;;)
      {
        r = right.query(0);
        if (r.getRowCount() == 0) {
          break;
        }
        while (r.next()) {
          result.addRow(r.currentRow());
        }
        r.reset();
        this.view.setRecursiveResult(r);
      }
      this.view.setRecursiveResult(null);
      result.done();
      return new ViewCursor(this, result, first, last);
    }
    ArrayList<Parameter> paramList = this.query.getParameters();
    if (this.originalParameters != null)
    {
      int i = 0;
      for (int size = this.originalParameters.size(); i < size; i++)
      {
        Parameter orig = (Parameter)this.originalParameters.get(i);
        int idx = orig.getIndex();
        Value value = orig.getValue(session);
        setParameter(paramList, idx, value);
      }
    }
    int len;
    int len;
    if (first != null)
    {
      len = first.getColumnCount();
    }
    else
    {
      int len;
      if (last != null)
      {
        len = last.getColumnCount();
      }
      else
      {
        int len;
        if (intersection != null) {
          len = intersection.getColumnCount();
        } else {
          len = 0;
        }
      }
    }
    int idx = this.originalParameters == null ? 0 : this.originalParameters.size();
    idx += this.view.getParameterOffset();
    for (int i = 0; i < len; i++)
    {
      int mask = this.indexMasks[i];
      if ((mask & 0x1) != 0) {
        setParameter(paramList, idx++, first.getValue(i));
      }
      if ((mask & 0x2) != 0) {
        setParameter(paramList, idx++, first.getValue(i));
      }
      if ((mask & 0x4) != 0) {
        setParameter(paramList, idx++, last.getValue(i));
      }
      if ((mask & 0x10) != 0) {
        setParameter(paramList, idx++, intersection.getValue(i));
      }
    }
    LocalResult result = this.query.query(0);
    return new ViewCursor(this, result, first, last);
  }
  
  private static void setParameter(ArrayList<Parameter> paramList, int x, Value v)
  {
    if (x >= paramList.size()) {
      return;
    }
    Parameter param = (Parameter)paramList.get(x);
    param.setValue(v);
  }
  
  private Query getQuery(Session session, int[] masks)
  {
    Query q = (Query)session.prepare(this.querySQL, true);
    if (masks == null) {
      return q;
    }
    if (!q.allowGlobalConditions()) {
      return q;
    }
    int firstIndexParam = this.originalParameters == null ? 0 : this.originalParameters.size();
    
    firstIndexParam += this.view.getParameterOffset();
    IntArray paramIndex = new IntArray();
    int indexColumnCount = 0;
    for (int i = 0; i < masks.length; i++)
    {
      int mask = masks[i];
      if (mask != 0)
      {
        indexColumnCount++;
        paramIndex.add(i);
        if (Integer.bitCount(mask) > 1) {
          paramIndex.add(i);
        }
      }
    }
    int len = paramIndex.size();
    ArrayList<Column> columnList = New.arrayList();
    for (int i = 0; i < len;)
    {
      int idx = paramIndex.get(i);
      columnList.add(this.table.getColumn(idx));
      int mask = masks[idx];
      if ((mask & 0x1) != 0)
      {
        Parameter param = new Parameter(firstIndexParam + i);
        q.addGlobalCondition(param, idx, 16);
        i++;
      }
      if ((mask & 0x2) != 0)
      {
        Parameter param = new Parameter(firstIndexParam + i);
        q.addGlobalCondition(param, idx, 1);
        i++;
      }
      if ((mask & 0x4) != 0)
      {
        Parameter param = new Parameter(firstIndexParam + i);
        q.addGlobalCondition(param, idx, 3);
        i++;
      }
      if ((mask & 0x10) != 0)
      {
        Parameter param = new Parameter(firstIndexParam + i);
        q.addGlobalCondition(param, idx, 11);
        i++;
      }
    }
    this.columns = new Column[columnList.size()];
    columnList.toArray(this.columns);
    
    this.indexColumns = new IndexColumn[indexColumnCount];
    this.columnIds = new int[indexColumnCount];
    int type = 0;
    for (int indexColumnId = 0; type < 2; type++) {
      for (int i = 0; i < masks.length; i++)
      {
        int mask = masks[i];
        if (mask != 0) {
          if (type == 0 ? 
            (mask & 0x1) != 0 : 
            
            (mask & 0x1) == 0)
          {
            IndexColumn c = new IndexColumn();
            c.column = this.table.getColumn(i);
            this.indexColumns[indexColumnId] = c;
            this.columnIds[indexColumnId] = c.column.getColumnId();
            indexColumnId++;
          }
        }
      }
    }
    String sql = q.getPlanSQL();
    q = (Query)session.prepare(sql, true);
    return q;
  }
  
  public void remove(Session session)
  {
    throw DbException.getUnsupportedException("VIEW");
  }
  
  public void truncate(Session session)
  {
    throw DbException.getUnsupportedException("VIEW");
  }
  
  public void checkRename()
  {
    throw DbException.getUnsupportedException("VIEW");
  }
  
  public boolean needRebuild()
  {
    return false;
  }
  
  public boolean canGetFirstOrLast()
  {
    return false;
  }
  
  public Cursor findFirstOrLast(Session session, boolean first)
  {
    throw DbException.getUnsupportedException("VIEW");
  }
  
  public void setRecursive(boolean value)
  {
    this.recursive = value;
  }
  
  public long getRowCount(Session session)
  {
    return 0L;
  }
  
  public long getRowCountApproximation()
  {
    return 0L;
  }
  
  public long getDiskSpaceUsed()
  {
    return 0L;
  }
  
  public boolean isRecursive()
  {
    return this.recursive;
  }
  
  static class CostElement
  {
    long evaluatedAt;
    double cost;
  }
}
