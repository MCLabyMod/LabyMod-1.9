package org.h2.store.fs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.h2.util.MathUtils;
import org.h2.util.New;

public abstract class FilePath
{
  private static FilePath defaultProvider;
  private static Map<String, FilePath> providers;
  private static String tempRandom;
  private static long tempSequence;
  protected String name;
  
  public static FilePath get(String path)
  {
    path = path.replace('\\', '/');
    int index = path.indexOf(':');
    registerDefaultProviders();
    if (index < 2) {
      return defaultProvider.getPath(path);
    }
    String scheme = path.substring(0, index);
    FilePath p = (FilePath)providers.get(scheme);
    if (p == null) {
      p = defaultProvider;
    }
    return p.getPath(path);
  }
  
  private static void registerDefaultProviders()
  {
    if ((providers == null) || (defaultProvider == null))
    {
      Map<String, FilePath> map = Collections.synchronizedMap(New.hashMap());
      for (String c : new String[] { "org.h2.store.fs.FilePathDisk", "org.h2.store.fs.FilePathMem", "org.h2.store.fs.FilePathMemLZF", "org.h2.store.fs.FilePathNioMem", "org.h2.store.fs.FilePathNioMemLZF", "org.h2.store.fs.FilePathSplit", "org.h2.store.fs.FilePathNio", "org.h2.store.fs.FilePathNioMapped", "org.h2.store.fs.FilePathZip", "org.h2.store.fs.FilePathRetryOnInterrupt" }) {
        try
        {
          FilePath p = (FilePath)Class.forName(c).newInstance();
          map.put(p.getScheme(), p);
          if (defaultProvider == null) {
            defaultProvider = p;
          }
        }
        catch (Exception e) {}
      }
      providers = map;
    }
  }
  
  public static void register(FilePath provider)
  {
    registerDefaultProviders();
    providers.put(provider.getScheme(), provider);
  }
  
  public static void unregister(FilePath provider)
  {
    registerDefaultProviders();
    providers.remove(provider.getScheme());
  }
  
  public abstract long size();
  
  public abstract void moveTo(FilePath paramFilePath, boolean paramBoolean);
  
  public abstract boolean createFile();
  
  public abstract boolean exists();
  
  public abstract void delete();
  
  public abstract List<FilePath> newDirectoryStream();
  
  public abstract FilePath toRealPath();
  
  public abstract FilePath getParent();
  
  public abstract boolean isDirectory();
  
  public abstract boolean isAbsolute();
  
  public abstract long lastModified();
  
  public abstract boolean canWrite();
  
  public abstract void createDirectory();
  
  public String getName()
  {
    int idx = Math.max(this.name.indexOf(':'), this.name.lastIndexOf('/'));
    return idx < 0 ? this.name : this.name.substring(idx + 1);
  }
  
  public abstract OutputStream newOutputStream(boolean paramBoolean)
    throws IOException;
  
  public abstract FileChannel open(String paramString)
    throws IOException;
  
  public abstract InputStream newInputStream()
    throws IOException;
  
  public abstract boolean setReadOnly();
  
  public FilePath createTempFile(String suffix, boolean deleteOnExit, boolean inTempDir)
    throws IOException
  {
    FilePath p;
    for (;;)
    {
      p = getPath(this.name + getNextTempFileNamePart(false) + suffix);
      if ((!p.exists()) && (p.createFile())) {
        break;
      }
      getNextTempFileNamePart(true);
    }
    p.open("rw").close();
    return p;
  }
  
  protected static synchronized String getNextTempFileNamePart(boolean newRandom)
  {
    if ((newRandom) || (tempRandom == null)) {
      tempRandom = MathUtils.randomInt(Integer.MAX_VALUE) + ".";
    }
    return tempRandom + tempSequence++;
  }
  
  public String toString()
  {
    return this.name;
  }
  
  public abstract String getScheme();
  
  public abstract FilePath getPath(String paramString);
  
  public FilePath unwrap()
  {
    return this;
  }
}
