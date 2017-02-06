package org.h2.result;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.h2.jdbc.JdbcConnection;
import org.h2.message.DbException;
import org.h2.util.New;
import org.h2.util.StatementBuilder;
import org.h2.util.StringUtils;
import org.h2.value.DataType;
import org.h2.value.Value;
import org.h2.value.ValueNull;

public class UpdatableRow
{
  private final JdbcConnection conn;
  private final ResultInterface result;
  private final int columnCount;
  private String schemaName;
  private String tableName;
  private ArrayList<String> key;
  private boolean isUpdatable;
  
  public UpdatableRow(JdbcConnection conn, ResultInterface result)
    throws SQLException
  {
    this.conn = conn;
    this.result = result;
    this.columnCount = result.getVisibleColumnCount();
    for (int i = 0; i < this.columnCount; i++)
    {
      String t = result.getTableName(i);
      String s = result.getSchemaName(i);
      if ((t == null) || (s == null)) {
        return;
      }
      if (this.tableName == null) {
        this.tableName = t;
      } else if (!this.tableName.equals(t)) {
        return;
      }
      if (this.schemaName == null) {
        this.schemaName = s;
      } else if (!this.schemaName.equals(s)) {
        return;
      }
    }
    DatabaseMetaData meta = conn.getMetaData();
    ResultSet rs = meta.getTables(null, StringUtils.escapeMetaDataPattern(this.schemaName), StringUtils.escapeMetaDataPattern(this.tableName), new String[] { "TABLE" });
    if (!rs.next()) {
      return;
    }
    if (rs.getString("SQL") == null) {
      return;
    }
    String table = rs.getString("TABLE_NAME");
    
    boolean toUpper = (!table.equals(this.tableName)) && (table.equalsIgnoreCase(this.tableName));
    this.key = New.arrayList();
    rs = meta.getPrimaryKeys(null, StringUtils.escapeMetaDataPattern(this.schemaName), this.tableName);
    while (rs.next())
    {
      String c = rs.getString("COLUMN_NAME");
      this.key.add(toUpper ? StringUtils.toUpperEnglish(c) : c);
    }
    if (isIndexUsable(this.key))
    {
      this.isUpdatable = true;
      return;
    }
    this.key.clear();
    rs = meta.getIndexInfo(null, StringUtils.escapeMetaDataPattern(this.schemaName), this.tableName, true, true);
    while (rs.next())
    {
      int pos = rs.getShort("ORDINAL_POSITION");
      if (pos == 1)
      {
        if (isIndexUsable(this.key))
        {
          this.isUpdatable = true;
          return;
        }
        this.key.clear();
      }
      String c = rs.getString("COLUMN_NAME");
      this.key.add(toUpper ? StringUtils.toUpperEnglish(c) : c);
    }
    if (isIndexUsable(this.key))
    {
      this.isUpdatable = true;
      return;
    }
    this.key = null;
  }
  
  private boolean isIndexUsable(ArrayList<String> indexColumns)
  {
    if (indexColumns.size() == 0) {
      return false;
    }
    for (String c : indexColumns) {
      if (findColumnIndex(c) < 0) {
        return false;
      }
    }
    return true;
  }
  
  public boolean isUpdatable()
  {
    return this.isUpdatable;
  }
  
  private int findColumnIndex(String columnName)
  {
    for (int i = 0; i < this.columnCount; i++)
    {
      String col = this.result.getColumnName(i);
      if (col.equals(columnName)) {
        return i;
      }
    }
    return -1;
  }
  
  private int getColumnIndex(String columnName)
  {
    int index = findColumnIndex(columnName);
    if (index < 0) {
      throw DbException.get(42122, columnName);
    }
    return index;
  }
  
  private void appendColumnList(StatementBuilder buff, boolean set)
  {
    buff.resetCount();
    for (int i = 0; i < this.columnCount; i++)
    {
      buff.appendExceptFirst(",");
      String col = this.result.getColumnName(i);
      buff.append(StringUtils.quoteIdentifier(col));
      if (set) {
        buff.append("=? ");
      }
    }
  }
  
  private void appendKeyCondition(StatementBuilder buff)
  {
    buff.append(" WHERE ");
    buff.resetCount();
    for (String k : this.key)
    {
      buff.appendExceptFirst(" AND ");
      buff.append(StringUtils.quoteIdentifier(k)).append("=?");
    }
  }
  
  private void setKey(PreparedStatement prep, int start, Value[] current)
    throws SQLException
  {
    int i = 0;
    for (int size = this.key.size(); i < size; i++)
    {
      String col = (String)this.key.get(i);
      int idx = getColumnIndex(col);
      Value v = current[idx];
      if ((v == null) || (v == ValueNull.INSTANCE)) {
        throw DbException.get(2000);
      }
      v.set(prep, start + i);
    }
  }
  
  private void appendTableName(StatementBuilder buff)
  {
    if ((this.schemaName != null) && (this.schemaName.length() > 0)) {
      buff.append(StringUtils.quoteIdentifier(this.schemaName)).append('.');
    }
    buff.append(StringUtils.quoteIdentifier(this.tableName));
  }
  
  public Value[] readRow(Value[] row)
    throws SQLException
  {
    StatementBuilder buff = new StatementBuilder("SELECT ");
    appendColumnList(buff, false);
    buff.append(" FROM ");
    appendTableName(buff);
    appendKeyCondition(buff);
    PreparedStatement prep = this.conn.prepareStatement(buff.toString());
    setKey(prep, 1, row);
    ResultSet rs = prep.executeQuery();
    if (!rs.next()) {
      throw DbException.get(2000);
    }
    Value[] newRow = new Value[this.columnCount];
    for (int i = 0; i < this.columnCount; i++)
    {
      int type = this.result.getColumnType(i);
      newRow[i] = DataType.readValue(this.conn.getSession(), rs, i + 1, type);
    }
    return newRow;
  }
  
  public void deleteRow(Value[] current)
    throws SQLException
  {
    StatementBuilder buff = new StatementBuilder("DELETE FROM ");
    appendTableName(buff);
    appendKeyCondition(buff);
    PreparedStatement prep = this.conn.prepareStatement(buff.toString());
    setKey(prep, 1, current);
    int count = prep.executeUpdate();
    if (count != 1) {
      throw DbException.get(2000);
    }
  }
  
  public void updateRow(Value[] current, Value[] updateRow)
    throws SQLException
  {
    StatementBuilder buff = new StatementBuilder("UPDATE ");
    appendTableName(buff);
    buff.append(" SET ");
    appendColumnList(buff, true);
    
    appendKeyCondition(buff);
    PreparedStatement prep = this.conn.prepareStatement(buff.toString());
    int j = 1;
    for (int i = 0; i < this.columnCount; i++)
    {
      Value v = updateRow[i];
      if (v == null) {
        v = current[i];
      }
      v.set(prep, j++);
    }
    setKey(prep, j, current);
    int count = prep.executeUpdate();
    if (count != 1) {
      throw DbException.get(2000);
    }
  }
  
  public void insertRow(Value[] row)
    throws SQLException
  {
    StatementBuilder buff = new StatementBuilder("INSERT INTO ");
    appendTableName(buff);
    buff.append('(');
    appendColumnList(buff, false);
    buff.append(")VALUES(");
    buff.resetCount();
    for (int i = 0; i < this.columnCount; i++)
    {
      buff.appendExceptFirst(",");
      Value v = row[i];
      if (v == null) {
        buff.append("DEFAULT");
      } else {
        buff.append('?');
      }
    }
    buff.append(')');
    PreparedStatement prep = this.conn.prepareStatement(buff.toString());
    int i = 0;
    for (int j = 0; i < this.columnCount; i++)
    {
      Value v = row[i];
      if (v != null) {
        v.set(prep, j++ + 1);
      }
    }
    int count = prep.executeUpdate();
    if (count != 1) {
      throw DbException.get(2000);
    }
  }
}
