package org.h2.jdbcx;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;
import org.h2.Driver;
import org.h2.engine.SysProperties;
import org.h2.message.Trace;
import org.h2.message.TraceSystem;

public class JdbcDataSourceFactory
  implements ObjectFactory
{
  private static TraceSystem cachedTraceSystem;
  private final Trace trace;
  
  static
  {
    Driver.load();
  }
  
  public JdbcDataSourceFactory()
  {
    this.trace = getTraceSystem().getTrace("JDBCX");
  }
  
  public synchronized Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment)
  {
    if (this.trace.isDebugEnabled()) {
      this.trace.debug("getObjectInstance obj={0} name={1} nameCtx={2} environment={3}", new Object[] { obj, name, nameCtx, environment });
    }
    if ((obj instanceof Reference))
    {
      Reference ref = (Reference)obj;
      if (ref.getClassName().equals(JdbcDataSource.class.getName()))
      {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL((String)ref.get("url").getContent());
        dataSource.setUser((String)ref.get("user").getContent());
        dataSource.setPassword((String)ref.get("password").getContent());
        dataSource.setDescription((String)ref.get("description").getContent());
        String s = (String)ref.get("loginTimeout").getContent();
        dataSource.setLoginTimeout(Integer.parseInt(s));
        return dataSource;
      }
    }
    return null;
  }
  
  public static TraceSystem getTraceSystem()
  {
    synchronized (JdbcDataSourceFactory.class)
    {
      if (cachedTraceSystem == null)
      {
        cachedTraceSystem = new TraceSystem(SysProperties.CLIENT_TRACE_DIRECTORY + "h2datasource" + ".trace.db");
        
        cachedTraceSystem.setLevelFile(SysProperties.DATASOURCE_TRACE_LEVEL);
      }
      return cachedTraceSystem;
    }
  }
  
  Trace getTrace()
  {
    return this.trace;
  }
}
