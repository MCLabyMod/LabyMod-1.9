import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.io.InputStream;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

public class bwr
{
  private static final Splitter b = Splitter.on('=').limit(2);
  private static final Pattern c = Pattern.compile("%(\\d+\\$)?[\\d\\.]*[df]");
  Map<String, String> a = Maps.newHashMap();
  private boolean d;
  
  public synchronized void a(bwg ☃, List<String> ☃)
  {
    this.a.clear();
    for (String ☃ : ☃)
    {
      ☃ = String.format("lang/%s.lang", new Object[] { ☃ });
      for (String ☃ : ☃.a()) {
        try
        {
          a(☃.b(new kk(☃, ☃)));
        }
        catch (IOException localIOException) {}
      }
    }
    String ☃;
    b();
  }
  
  public boolean a()
  {
    return this.d;
  }
  
  private void b()
  {
    this.d = false;
    
    int ☃ = 0;
    int ☃ = 0;
    for (String ☃ : this.a.values())
    {
      int ☃ = ☃.length();
      ☃ += ☃;
      for (int ☃ = 0; ☃ < ☃; ☃++) {
        if (☃.charAt(☃) >= 'Ā') {
          ☃++;
        }
      }
    }
    float ☃ = ☃ / ☃;
    this.d = (☃ > 0.1D);
  }
  
  private void a(List<bwf> ☃)
    throws IOException
  {
    for (bwf ☃ : ☃)
    {
      InputStream ☃ = ☃.b();
      try
      {
        a(☃);
      }
      finally
      {
        IOUtils.closeQuietly(☃);
      }
    }
  }
  
  private void a(InputStream ☃)
    throws IOException
  {
    for (String ☃ : IOUtils.readLines(☃, Charsets.UTF_8)) {
      if ((!☃.isEmpty()) && (☃.charAt(0) != '#'))
      {
        String[] ☃ = (String[])Iterables.toArray(b.split(☃), String.class);
        if ((☃ != null) && (☃.length == 2))
        {
          String ☃ = ☃[0];
          String ☃ = c.matcher(☃[1]).replaceAll("%$1s");
          
          this.a.put(☃, ☃);
        }
      }
    }
  }
  
  private String b(String ☃)
  {
    String ☃ = (String)this.a.get(☃);
    return ☃ == null ? ☃ : ☃;
  }
  
  public String a(String ☃, Object[] ☃)
  {
    String ☃ = b(☃);
    try
    {
      return String.format(☃, ☃);
    }
    catch (IllegalFormatException ☃) {}
    return "Format error: " + ☃;
  }
  
  public boolean a(String ☃)
  {
    return this.a.containsKey(☃);
  }
}
