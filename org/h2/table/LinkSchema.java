package org.h2.table;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.h2.message.DbException;
import org.h2.tools.SimpleResultSet;
import org.h2.util.JdbcUtils;
import org.h2.util.StringUtils;

public class LinkSchema
{
  public static ResultSet linkSchema(Connection conn, String targetSchema, String driver, String url, String user, String password, String sourceSchema)
  {
    Connection c2 = null;
    Statement stat = null;
    ResultSet rs = null;
    SimpleResultSet result = new SimpleResultSet();
    result.setAutoClose(false);
    result.addColumn("TABLE_NAME", 12, Integer.MAX_VALUE, 0);
    try
    {
      c2 = JdbcUtils.getConnection(driver, url, user, password);
      stat = conn.createStatement();
      stat.execute("CREATE SCHEMA IF NOT EXISTS " + StringUtils.quoteIdentifier(targetSchema));
      
      rs = c2.getMetaData().getTables(null, sourceSchema, null, null);
      while (rs.next())
      {
        String table = rs.getString("TABLE_NAME");
        StringBuilder buff = new StringBuilder();
        buff.append("DROP TABLE IF EXISTS ").append(StringUtils.quoteIdentifier(targetSchema)).append('.').append(StringUtils.quoteIdentifier(table));
        
        stat.execute(buff.toString());
        buff = new StringBuilder();
        buff.append("CREATE LINKED TABLE ").append(StringUtils.quoteIdentifier(targetSchema)).append('.').append(StringUtils.quoteIdentifier(table)).append('(').append(StringUtils.quoteStringSQL(driver)).append(", ").append(StringUtils.quoteStringSQL(url)).append(", ").append(StringUtils.quoteStringSQL(user)).append(", ").append(StringUtils.quoteStringSQL(password)).append(", ").append(StringUtils.quoteStringSQL(table)).append(')');
        
        stat.execute(buff.toString());
        result.addRow(new Object[] { table });
      }
    }
    catch (SQLException e)
    {
      throw DbException.convert(e);
    }
    finally
    {
      JdbcUtils.closeSilently(rs);
      JdbcUtils.closeSilently(c2);
      JdbcUtils.closeSilently(stat);
    }
    return result;
  }
}
