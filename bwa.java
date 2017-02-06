import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class bwa
  extends bvu
  implements Closeable
{
  public static final Splitter b = Splitter.on('/').omitEmptyStrings().limit(3);
  private ZipFile c;
  
  public bwa(File ☃)
  {
    super(☃);
  }
  
  private ZipFile d()
    throws IOException
  {
    if (this.c == null) {
      this.c = new ZipFile(this.a);
    }
    return this.c;
  }
  
  protected InputStream a(String ☃)
    throws IOException
  {
    ZipFile ☃ = d();
    ZipEntry ☃ = ☃.getEntry(☃);
    if (☃ == null) {
      throw new bwj(this.a, ☃);
    }
    return ☃.getInputStream(☃);
  }
  
  public boolean b(String ☃)
  {
    try
    {
      return d().getEntry(☃) != null;
    }
    catch (IOException ☃) {}
    return false;
  }
  
  public Set<String> c()
  {
    ZipFile ☃;
    try
    {
      ☃ = d();
    }
    catch (IOException ☃)
    {
      return Collections.emptySet();
    }
    Enumeration<? extends ZipEntry> ☃ = ☃.entries();
    
    Set<String> ☃ = Sets.newHashSet();
    while (☃.hasMoreElements())
    {
      ZipEntry ☃ = (ZipEntry)☃.nextElement();
      
      String ☃ = ☃.getName();
      if (☃.startsWith("assets/"))
      {
        List<String> ☃ = Lists.newArrayList(b.split(☃));
        if (☃.size() > 1)
        {
          String ☃ = (String)☃.get(1);
          if (!☃.equals(☃.toLowerCase())) {
            c(☃);
          } else {
            ☃.add(☃);
          }
        }
      }
    }
    return ☃;
  }
  
  protected void finalize()
    throws Throwable
  {
    close();
    super.finalize();
  }
  
  public void close()
    throws IOException
  {
    if (this.c != null)
    {
      this.c.close();
      this.c = null;
    }
  }
}
