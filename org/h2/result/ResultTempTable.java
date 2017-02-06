package org.h2.result;

import java.util.ArrayList;
import java.util.Arrays;
import org.h2.command.ddl.CreateTableData;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.expression.Expression;
import org.h2.index.Cursor;
import org.h2.index.Index;
import org.h2.index.IndexType;
import org.h2.schema.Schema;
import org.h2.table.Column;
import org.h2.table.IndexColumn;
import org.h2.table.Table;
import org.h2.value.Value;
import org.h2.value.ValueNull;

public class ResultTempTable
  implements ResultExternal
{
  private static final String COLUMN_NAME = "DATA";
  private final boolean distinct;
  private final SortOrder sort;
  private Index index;
  private Session session;
  private Table table;
  private Cursor resultCursor;
  private int rowCount;
  private int columnCount;
  private final ResultTempTable parent;
  private boolean closed;
  private int childCount;
  private boolean containsLob;
  
  ResultTempTable(Session session, Expression[] expressions, boolean distinct, SortOrder sort)
  {
    this.session = session;
    this.distinct = distinct;
    this.sort = sort;
    this.columnCount = expressions.length;
    Schema schema = session.getDatabase().getSchema("PUBLIC");
    CreateTableData data = new CreateTableData();
    for (int i = 0; i < expressions.length; i++)
    {
      int type = expressions[i].getType();
      Column col = new Column("DATA" + i, type);
      if ((type == 16) || (type == 15)) {
        this.containsLob = true;
      }
      data.columns.add(col);
    }
    data.id = session.getDatabase().allocateObjectId();
    data.tableName = ("TEMP_RESULT_SET_" + data.id);
    data.temporary = true;
    data.persistIndexes = false;
    data.persistData = true;
    data.create = true;
    data.session = session;
    this.table = schema.createTable(data);
    if ((sort != null) || (distinct)) {
      createIndex();
    }
    this.parent = null;
  }
  
  private ResultTempTable(ResultTempTable parent)
  {
    this.parent = parent;
    this.columnCount = parent.columnCount;
    this.distinct = parent.distinct;
    this.session = parent.session;
    this.table = parent.table;
    this.index = parent.index;
    this.rowCount = parent.rowCount;
    this.sort = parent.sort;
    this.containsLob = parent.containsLob;
    reset();
  }
  
  private void createIndex()
  {
    IndexColumn[] indexCols = null;
    if (this.sort != null)
    {
      int[] colIndex = this.sort.getQueryColumnIndexes();
      indexCols = new IndexColumn[colIndex.length];
      for (int i = 0; i < colIndex.length; i++)
      {
        IndexColumn indexColumn = new IndexColumn();
        indexColumn.column = this.table.getColumn(colIndex[i]);
        indexColumn.sortType = this.sort.getSortTypes()[i];
        indexColumn.columnName = ("DATA" + i);
        indexCols[i] = indexColumn;
      }
    }
    else
    {
      indexCols = new IndexColumn[this.columnCount];
      for (int i = 0; i < this.columnCount; i++)
      {
        IndexColumn indexColumn = new IndexColumn();
        indexColumn.column = this.table.getColumn(i);
        indexColumn.columnName = ("DATA" + i);
        indexCols[i] = indexColumn;
      }
    }
    String indexName = this.table.getSchema().getUniqueIndexName(this.session, this.table, "INDEX_");
    
    int indexId = this.session.getDatabase().allocateObjectId();
    IndexType indexType = IndexType.createNonUnique(true);
    this.index = this.table.addIndex(this.session, indexName, indexId, indexCols, indexType, true, null);
  }
  
  public synchronized ResultExternal createShallowCopy()
  {
    if (this.parent != null) {
      return this.parent.createShallowCopy();
    }
    if (this.closed) {
      return null;
    }
    this.childCount += 1;
    return new ResultTempTable(this);
  }
  
  public int removeRow(Value[] values)
  {
    Row row = convertToRow(values);
    Cursor cursor = find(row);
    if (cursor != null)
    {
      row = cursor.get();
      this.table.removeRow(this.session, row);
      this.rowCount -= 1;
    }
    return this.rowCount;
  }
  
  public boolean contains(Value[] values)
  {
    return find(convertToRow(values)) != null;
  }
  
  public int addRow(Value[] values)
  {
    Row row = convertToRow(values);
    if (this.distinct)
    {
      Cursor cursor = find(row);
      if (cursor == null)
      {
        this.table.addRow(this.session, row);
        this.rowCount += 1;
      }
    }
    else
    {
      this.table.addRow(this.session, row);
      this.rowCount += 1;
    }
    return this.rowCount;
  }
  
  public int addRows(ArrayList<Value[]> rows)
  {
    if (this.sort != null) {
      this.sort.sort(rows);
    }
    for (Value[] values : rows) {
      addRow(values);
    }
    return this.rowCount;
  }
  
  private synchronized void closeChild()
  {
    if ((--this.childCount == 0) && (this.closed)) {
      dropTable();
    }
  }
  
  public synchronized void close()
  {
    if (this.closed) {
      return;
    }
    this.closed = true;
    if (this.parent != null) {
      this.parent.closeChild();
    } else if (this.childCount == 0) {
      dropTable();
    }
  }
  
  private void dropTable()
  {
    if (this.table == null) {
      return;
    }
    if (this.containsLob) {
      return;
    }
    try
    {
      Database database = this.session.getDatabase();
      synchronized (this.session)
      {
        synchronized (database)
        {
          this.table.truncate(this.session);
        }
      }
      if (!database.isSysTableLocked())
      {
        Session sysSession = database.getSystemSession();
        this.table.removeChildrenAndResources(sysSession);
        if (this.index != null) {
          this.session.removeLocalTempTableIndex(this.index);
        }
        synchronized (this.session)
        {
          synchronized (sysSession)
          {
            synchronized (database)
            {
              sysSession.commit(false);
            }
          }
        }
      }
    }
    finally
    {
      this.table = null;
    }
  }
  
  public void done() {}
  
  public Value[] next()
  {
    if (this.resultCursor == null)
    {
      Index idx;
      Index idx;
      if ((this.distinct) || (this.sort != null)) {
        idx = this.index;
      } else {
        idx = this.table.getScanIndex(this.session);
      }
      if (this.session.getDatabase().getMvStore() != null)
      {
        if ((idx.getRowCount(this.session) == 0L) && (this.rowCount > 0)) {
          this.resultCursor = idx.find((Session)null, null, null);
        } else {
          this.resultCursor = idx.find(this.session, null, null);
        }
      }
      else {
        this.resultCursor = idx.find(this.session, null, null);
      }
    }
    if (!this.resultCursor.next()) {
      return null;
    }
    Row row = this.resultCursor.get();
    return row.getValueList();
  }
  
  public void reset()
  {
    this.resultCursor = null;
  }
  
  private Row convertToRow(Value[] values)
  {
    if (values.length < this.columnCount)
    {
      Value[] v2 = (Value[])Arrays.copyOf(values, this.columnCount);
      for (int i = values.length; i < this.columnCount; i++) {
        v2[i] = ValueNull.INSTANCE;
      }
      values = v2;
    }
    return new Row(values, -1);
  }
  
  private Cursor find(Row row)
  {
    if (this.index == null) {
      createIndex();
    }
    Cursor cursor = this.index.find(this.session, row, row);
    while (cursor.next())
    {
      SearchRow found = cursor.getSearchRow();
      boolean ok = true;
      Database db = this.session.getDatabase();
      for (int i = 0; i < row.getColumnCount(); i++) {
        if (!db.areEqual(row.getValue(i), found.getValue(i)))
        {
          ok = false;
          break;
        }
      }
      if (ok) {
        return cursor;
      }
    }
    return null;
  }
}
