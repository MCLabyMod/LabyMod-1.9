package de.labystudio.modapi;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class ModuleClassLoader
  extends URLClassLoader
{
  private static final Set<ModuleClassLoader> allLoaders = new CopyOnWriteArraySet();
  
  static
  {
    ClassLoader.registerAsParallelCapable();
  }
  
  public ModuleClassLoader(URL[] urls)
  {
    super(urls);
    allLoaders.add(this);
  }
  
  public Class<?> loadClass(String name, boolean resolve)
    throws ClassNotFoundException
  {
    return loadClass0(name, resolve, true);
  }
  
  private Class<?> loadClass0(String name, boolean resolve, boolean checkOther)
    throws ClassNotFoundException
  {
    try
    {
      return super.loadClass(name, resolve);
    }
    catch (ClassNotFoundException ex)
    {
      if (checkOther) {
        for (ModuleClassLoader loader : allLoaders) {
          if (loader != this) {
            try
            {
              return loader.loadClass0(name, resolve, false);
            }
            catch (ClassNotFoundException localClassNotFoundException1) {}
          }
        }
      }
      throw new ClassNotFoundException(name);
    }
  }
}
