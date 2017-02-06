package org.h2.jdbc;

import java.sql.ParameterMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import org.h2.command.CommandInterface;
import org.h2.expression.ParameterInterface;
import org.h2.message.DbException;
import org.h2.message.Trace;
import org.h2.message.TraceObject;
import org.h2.util.MathUtils;
import org.h2.value.DataType;

public class JdbcParameterMetaData
  extends TraceObject
  implements ParameterMetaData
{
  private final JdbcPreparedStatement prep;
  private final int paramCount;
  private final ArrayList<? extends ParameterInterface> parameters;
  
  JdbcParameterMetaData(Trace trace, JdbcPreparedStatement prep, CommandInterface command, int id)
  {
    setTrace(trace, 11, id);
    this.prep = prep;
    this.parameters = command.getParameters();
    this.paramCount = this.parameters.size();
  }
  
  public int getParameterCount()
    throws SQLException
  {
    try
    {
      debugCodeCall("getParameterCount");
      checkClosed();
      return this.paramCount;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int getParameterMode(int param)
    throws SQLException
  {
    try
    {
      debugCodeCall("getParameterMode", param);
      getParameter(param);
      return 1;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int getParameterType(int param)
    throws SQLException
  {
    try
    {
      debugCodeCall("getParameterType", param);
      ParameterInterface p = getParameter(param);
      int type = p.getType();
      if (type == -1) {
        type = 13;
      }
      return DataType.getDataType(type).sqlType;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int getPrecision(int param)
    throws SQLException
  {
    try
    {
      debugCodeCall("getPrecision", param);
      ParameterInterface p = getParameter(param);
      return MathUtils.convertLongToInt(p.getPrecision());
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int getScale(int param)
    throws SQLException
  {
    try
    {
      debugCodeCall("getScale", param);
      ParameterInterface p = getParameter(param);
      return p.getScale();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public int isNullable(int param)
    throws SQLException
  {
    try
    {
      debugCodeCall("isNullable", param);
      return getParameter(param).getNullable();
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public boolean isSigned(int param)
    throws SQLException
  {
    try
    {
      debugCodeCall("isSigned", param);
      getParameter(param);
      return true;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public String getParameterClassName(int param)
    throws SQLException
  {
    try
    {
      debugCodeCall("getParameterClassName", param);
      ParameterInterface p = getParameter(param);
      int type = p.getType();
      if (type == -1) {
        type = 13;
      }
      return DataType.getTypeClassName(type);
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  public String getParameterTypeName(int param)
    throws SQLException
  {
    try
    {
      debugCodeCall("getParameterTypeName", param);
      ParameterInterface p = getParameter(param);
      int type = p.getType();
      if (type == -1) {
        type = 13;
      }
      return DataType.getDataType(type).name;
    }
    catch (Exception e)
    {
      throw logAndConvert(e);
    }
  }
  
  private ParameterInterface getParameter(int param)
  {
    checkClosed();
    if ((param < 1) || (param > this.paramCount)) {
      throw DbException.getInvalidValueException("param", Integer.valueOf(param));
    }
    return (ParameterInterface)this.parameters.get(param - 1);
  }
  
  private void checkClosed()
  {
    this.prep.checkClosed();
  }
  
  public <T> T unwrap(Class<T> iface)
    throws SQLException
  {
    if (isWrapperFor(iface)) {
      return this;
    }
    throw DbException.getInvalidValueException("iface", iface);
  }
  
  public boolean isWrapperFor(Class<?> iface)
    throws SQLException
  {
    return (iface != null) && (iface.isAssignableFrom(getClass()));
  }
  
  public String toString()
  {
    return getTraceObjectName() + ": parameterCount=" + this.paramCount;
  }
}
