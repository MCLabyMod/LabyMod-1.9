import com.google.common.collect.Sets;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import org.apache.commons.io.filefilter.DirectoryFileFilter;

public class bwb
  extends bvu
{
  public bwb(File ☃)
  {
    super(☃);
  }
  
  protected InputStream a(String ☃)
    throws IOException
  {
    return new BufferedInputStream(new FileInputStream(new File(this.a, ☃)));
  }
  
  protected boolean b(String ☃)
  {
    return new File(this.a, ☃).isFile();
  }
  
  public Set<String> c()
  {
    Set<String> ☃ = Sets.newHashSet();
    File ☃ = new File(this.a, "assets/");
    if (☃.isDirectory()) {
      for (File ☃ : ☃.listFiles(DirectoryFileFilter.DIRECTORY))
      {
        String ☃ = a(☃, ☃);
        if (!☃.equals(☃.toLowerCase())) {
          c(☃);
        } else {
          ☃.add(☃.substring(0, ☃.length() - 1));
        }
      }
    }
    return ☃;
  }
}
