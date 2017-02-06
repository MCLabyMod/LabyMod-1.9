package org.h2.jmx;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.NotCompliantMBeanException;
import javax.management.StandardMBean;
import org.h2.util.Utils;

public class DocumentedMBean
  extends StandardMBean
{
  private final String interfaceName;
  private Properties resources;
  
  public <T> DocumentedMBean(T impl, Class<T> mbeanInterface)
    throws NotCompliantMBeanException
  {
    super(impl, mbeanInterface);
    this.interfaceName = (impl.getClass().getName() + "MBean");
  }
  
  private Properties getResources()
  {
    if (this.resources == null)
    {
      this.resources = new Properties();
      String resourceName = "/org/h2/res/javadoc.properties";
      try
      {
        byte[] buff = Utils.getResource(resourceName);
        if (buff != null) {
          this.resources.load(new ByteArrayInputStream(buff));
        }
      }
      catch (IOException e) {}
    }
    return this.resources;
  }
  
  protected String getDescription(MBeanInfo info)
  {
    String s = getResources().getProperty(this.interfaceName);
    return s == null ? super.getDescription(info) : s;
  }
  
  protected String getDescription(MBeanOperationInfo op)
  {
    String s = getResources().getProperty(this.interfaceName + "." + op.getName());
    return s == null ? super.getDescription(op) : s;
  }
  
  protected String getDescription(MBeanAttributeInfo info)
  {
    String prefix = info.isIs() ? "is" : "get";
    String s = getResources().getProperty(this.interfaceName + "." + prefix + info.getName());
    
    return s == null ? super.getDescription(info) : s;
  }
  
  protected int getImpact(MBeanOperationInfo info)
  {
    if (info.getName().startsWith("list")) {
      return 0;
    }
    return 1;
  }
}
