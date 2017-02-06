package org.h2.util;

import org.h2.Driver;

public class OsgiDataSourceFactory
{
  private Driver driver;
  
  public OsgiDataSourceFactory(Driver driver)
  {
    this.driver = driver;
  }
}
