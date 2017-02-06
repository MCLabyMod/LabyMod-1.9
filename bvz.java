import com.google.common.collect.Lists;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bvz
  implements bwg
{
  private static final Logger b = ;
  protected final List<bwi> a = Lists.newArrayList();
  private final bww c;
  
  public bvz(bww ☃)
  {
    this.c = ☃;
  }
  
  public void a(bwi ☃)
  {
    this.a.add(☃);
  }
  
  public Set<String> a()
  {
    return null;
  }
  
  public bwf a(kk ☃)
    throws IOException
  {
    d(☃);
    
    bwi ☃ = null;
    kk ☃ = c(☃);
    for (int ☃ = this.a.size() - 1; ☃ >= 0; ☃--)
    {
      bwi ☃ = (bwi)this.a.get(☃);
      if ((☃ == null) && (☃.b(☃))) {
        ☃ = ☃;
      }
      if (☃.b(☃))
      {
        InputStream ☃ = null;
        if (☃ != null) {
          ☃ = a(☃, ☃);
        }
        return new bwm(☃.b(), ☃, a(☃, ☃), ☃, this.c);
      }
    }
    throw new FileNotFoundException(☃.toString());
  }
  
  protected InputStream a(kk ☃, bwi ☃)
    throws IOException
  {
    InputStream ☃ = ☃.a(☃);
    return b.isDebugEnabled() ? new bvz.a(☃, ☃, ☃.b()) : ☃;
  }
  
  private void d(kk ☃)
    throws IOException
  {
    if (☃.a().contains("..")) {
      throw new IOException("Invalid relative path to resource: " + ☃);
    }
  }
  
  static class a
    extends InputStream
  {
    private final InputStream a;
    private final String b;
    private boolean c = false;
    
    public a(InputStream ☃, kk ☃, String ☃)
    {
      this.a = ☃;
      ByteArrayOutputStream ☃ = new ByteArrayOutputStream();
      new Exception().printStackTrace(new PrintStream(☃));
      this.b = ("Leaked resource: '" + ☃ + "' loaded from pack: '" + ☃ + "'\n" + ☃.toString());
    }
    
    public void close()
      throws IOException
    {
      this.a.close();
      
      this.c = true;
    }
    
    protected void finalize()
      throws Throwable
    {
      if (!this.c) {
        bvz.b().warn(this.b);
      }
      super.finalize();
    }
    
    public int read()
      throws IOException
    {
      return this.a.read();
    }
  }
  
  public List<bwf> b(kk ☃)
    throws IOException
  {
    d(☃);
    
    List<bwf> ☃ = Lists.newArrayList();
    kk ☃ = c(☃);
    for (bwi ☃ : this.a) {
      if (☃.b(☃))
      {
        InputStream ☃ = ☃.b(☃) ? a(☃, ☃) : null;
        ☃.add(new bwm(☃.b(), ☃, a(☃, ☃), ☃, this.c));
      }
    }
    if (☃.isEmpty()) {
      throw new FileNotFoundException(☃.toString());
    }
    return ☃;
  }
  
  static kk c(kk ☃)
  {
    return new kk(☃.b(), ☃.a() + ".mcmeta");
  }
}
