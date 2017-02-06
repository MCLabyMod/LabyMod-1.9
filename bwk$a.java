import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.IOUtils;

public class bwk$a
{
  private bwi b;
  private bxh c;
  private kk d;
  
  private bwk$a(bwk parambwk, File ☃)
  {
    this(parambwk, ☃.isDirectory() ? new bwb(☃) : new bwa(☃));
  }
  
  private bwk$a(bwk parambwk, bwi ☃)
  {
    this.b = ☃;
  }
  
  public void a()
    throws IOException
  {
    this.c = ((bxh)this.b.a(this.a.b, "pack"));
    b();
  }
  
  public void a(bvi ☃)
  {
    BufferedImage ☃ = null;
    try
    {
      ☃ = this.b.a();
    }
    catch (IOException localIOException1) {}
    if (☃ == null) {
      try
      {
        ☃ = this.a.a.a();
      }
      catch (IOException ☃)
      {
        throw new Error("Couldn't bind resource pack icon", ☃);
      }
    }
    if (this.d == null) {
      this.d = ☃.a("texturepackicon", new bux(☃));
    }
    ☃.a(this.d);
  }
  
  public void b()
  {
    if ((this.b instanceof Closeable)) {
      IOUtils.closeQuietly((Closeable)this.b);
    }
  }
  
  public bwi c()
  {
    return this.b;
  }
  
  public String d()
  {
    return this.b.b();
  }
  
  public String e()
  {
    return this.c == null ? a.m + "Invalid pack.mcmeta (or missing 'pack' section)" : this.c.a().d();
  }
  
  public int f()
  {
    return this.c == null ? 0 : this.c.b();
  }
  
  public boolean equals(Object ☃)
  {
    if (this == ☃) {
      return true;
    }
    if ((☃ instanceof a)) {
      return toString().equals(☃.toString());
    }
    return false;
  }
  
  public int hashCode()
  {
    return toString().hashCode();
  }
  
  public String toString()
  {
    return String.format("%s:%s", new Object[] { this.b.b(), (this.b instanceof bwb) ? "folder" : "zip" });
  }
}
