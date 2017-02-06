package org.h2.index;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.h2.engine.Session;
import org.h2.message.DbException;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.table.Column;
import org.h2.table.TableLink;
import org.h2.value.DataType;
import org.h2.value.Value;

public class LinkedCursor
  implements Cursor
{
  private final TableLink tableLink;
  private final PreparedStatement prep;
  private final String sql;
  private final Session session;
  private final ResultSet rs;
  private Row current;
  
  LinkedCursor(TableLink tableLink, ResultSet rs, Session session, String sql, PreparedStatement prep)
  {
    this.session = session;
    this.tableLink = tableLink;
    this.rs = rs;
    this.sql = sql;
    this.prep = prep;
  }
  
  public Row get()
  {
    return this.current;
  }
  
  public SearchRow getSearchRow()
  {
    return this.current;
  }
  
  public boolean next()
  {
    try
    {
      boolean result = this.rs.next();
      if (!result)
      {
        this.rs.close();
        this.tableLink.reusePreparedStatement(this.prep, this.sql);
        this.current = null;
        return false;
      }
    }
    catch (SQLException e)
    {
      throw DbException.convert(e);
    }
    this.current = this.tableLink.getTemplateRow();
    for (int i = 0; i < this.current.getColumnCount(); i++)
    {
      Column col = this.tableLink.getColumn(i);
      Value v = DataType.readValue(this.session, this.rs, i + 1, col.getType());
      this.current.setValue(i, v);
    }
    return true;
  }
  
  public boolean previous()
  {
    throw DbException.throwInternalError();
  }
}
