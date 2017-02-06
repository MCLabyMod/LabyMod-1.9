package optifine;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.minecraft.launchwrapper.IClassTransformer;

public class OptiFineClassTransformer
  implements IClassTransformer
{
  private ZipFile ofZipFile = null;
  
  public OptiFineClassTransformer()
  {
    try
    {
      URLClassLoader ucl = (URLClassLoader)OptiFineClassTransformer.class.getClassLoader();
      
      URL[] urls = ucl.getURLs();
      for (int i = 0; i < urls.length; i++)
      {
        URL url = urls[i];
        
        ZipFile zipFile = getOptiFineZipFile(url);
        if (zipFile != null)
        {
          this.ofZipFile = zipFile;
          dbg("OptiFine ClassTransformer");
          dbg("OptiFine URL: " + url);
          dbg("OptiFine ZIP file: " + zipFile);
          break;
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    if (this.ofZipFile == null)
    {
      dbg("*** Can not find the OptiFine JAR in the classpath ***");
      dbg("*** OptiFine will not be loaded! ***");
    }
  }
  
  private static ZipFile getOptiFineZipFile(URL url)
  {
    try
    {
      URI uri = url.toURI();
      
      File file = new File(uri);
      
      ZipFile zipFile = new ZipFile(file);
      if (zipFile.getEntry("optifine/OptiFineClassTransformer.class") == null)
      {
        zipFile.close();
        return null;
      }
      return zipFile;
    }
    catch (Exception localException) {}
    return null;
  }
  
  public byte[] transform(String name, String transformedName, byte[] bytes)
  {
    byte[] ofBytes = getOptiFineClass(name);
    if (ofBytes != null) {
      return ofBytes;
    }
    return bytes;
  }
  
  private byte[] getOptiFineClass(String name)
  {
    if (this.ofZipFile == null) {
      return null;
    }
    String fullName = name + ".class";
    ZipEntry ze = this.ofZipFile.getEntry(fullName);
    if (ze == null) {
      return null;
    }
    try
    {
      InputStream in = this.ofZipFile.getInputStream(ze);
      byte[] bytes = readAll(in);
      if (bytes.length != ze.getSize())
      {
        dbg("Invalid size for " + fullName + ": " + bytes.length + ", should be: " + ze.getSize());
        return null;
      }
      return bytes;
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public static byte[] readAll(InputStream is)
    throws IOException
  {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    byte[] buf = new byte['Ѐ'];
    for (;;)
    {
      int len = is.read(buf);
      if (len < 0) {
        break;
      }
      baos.write(buf, 0, len);
    }
    is.close();
    
    byte[] bytes = baos.toByteArray();
    
    return bytes;
  }
  
  private static void dbg(String str)
  {
    System.out.println(str);
  }
}
