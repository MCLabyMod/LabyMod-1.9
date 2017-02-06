package org.h2.store.fs;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import org.h2.util.New;

public class FileUtils
{
  public static boolean exists(String fileName)
  {
    return FilePath.get(fileName).exists();
  }
  
  public static void createDirectory(String directoryName)
  {
    FilePath.get(directoryName).createDirectory();
  }
  
  public static boolean createFile(String fileName)
  {
    return FilePath.get(fileName).createFile();
  }
  
  public static void delete(String path)
  {
    FilePath.get(path).delete();
  }
  
  public static String toRealPath(String fileName)
  {
    return FilePath.get(fileName).toRealPath().toString();
  }
  
  public static String getParent(String fileName)
  {
    FilePath p = FilePath.get(fileName).getParent();
    return p == null ? null : p.toString();
  }
  
  public static boolean isAbsolute(String fileName)
  {
    return FilePath.get(fileName).isAbsolute();
  }
  
  public static void move(String source, String target)
  {
    FilePath.get(source).moveTo(FilePath.get(target), false);
  }
  
  public static void moveAtomicReplace(String source, String target)
  {
    FilePath.get(source).moveTo(FilePath.get(target), true);
  }
  
  public static String getName(String path)
  {
    return FilePath.get(path).getName();
  }
  
  public static List<String> newDirectoryStream(String path)
  {
    List<FilePath> list = FilePath.get(path).newDirectoryStream();
    int len = list.size();
    List<String> result = New.arrayList(len);
    for (int i = 0; i < len; i++) {
      result.add(((FilePath)list.get(i)).toString());
    }
    return result;
  }
  
  public static long lastModified(String fileName)
  {
    return FilePath.get(fileName).lastModified();
  }
  
  public static long size(String fileName)
  {
    return FilePath.get(fileName).size();
  }
  
  public static boolean isDirectory(String fileName)
  {
    return FilePath.get(fileName).isDirectory();
  }
  
  public static FileChannel open(String fileName, String mode)
    throws IOException
  {
    return FilePath.get(fileName).open(mode);
  }
  
  public static InputStream newInputStream(String fileName)
    throws IOException
  {
    return FilePath.get(fileName).newInputStream();
  }
  
  public static OutputStream newOutputStream(String fileName, boolean append)
    throws IOException
  {
    return FilePath.get(fileName).newOutputStream(append);
  }
  
  public static boolean canWrite(String fileName)
  {
    return FilePath.get(fileName).canWrite();
  }
  
  public static boolean setReadOnly(String fileName)
  {
    return FilePath.get(fileName).setReadOnly();
  }
  
  public static String unwrap(String fileName)
  {
    return FilePath.get(fileName).unwrap().toString();
  }
  
  public static void deleteRecursive(String path, boolean tryOnly)
  {
    if (exists(path))
    {
      if (isDirectory(path)) {
        for (String s : newDirectoryStream(path)) {
          deleteRecursive(s, tryOnly);
        }
      }
      if (tryOnly) {
        tryDelete(path);
      } else {
        delete(path);
      }
    }
  }
  
  public static void createDirectories(String dir)
  {
    if (dir != null) {
      if (exists(dir))
      {
        if (!isDirectory(dir)) {
          createDirectory(dir);
        }
      }
      else
      {
        String parent = getParent(dir);
        createDirectories(parent);
        createDirectory(dir);
      }
    }
  }
  
  public static boolean tryDelete(String fileName)
  {
    try
    {
      FilePath.get(fileName).delete();
      return true;
    }
    catch (Exception e) {}
    return false;
  }
  
  public static String createTempFile(String prefix, String suffix, boolean deleteOnExit, boolean inTempDir)
    throws IOException
  {
    return FilePath.get(prefix).createTempFile(suffix, deleteOnExit, inTempDir).toString();
  }
  
  public static void readFully(FileChannel channel, ByteBuffer dst)
    throws IOException
  {
    do
    {
      int r = channel.read(dst);
      if (r < 0) {
        throw new EOFException();
      }
    } while (dst.remaining() > 0);
  }
  
  public static void writeFully(FileChannel channel, ByteBuffer src)
    throws IOException
  {
    do
    {
      channel.write(src);
    } while (src.remaining() > 0);
  }
}
