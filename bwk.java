import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bwk
{
  private static final Logger c = ;
  private static final FileFilter d = new FileFilter()
  {
    public boolean accept(File ☃)
    {
      boolean ☃ = (☃.isFile()) && (☃.getName().endsWith(".zip"));
      boolean ☃ = (☃.isDirectory()) && (new File(☃, "pack.mcmeta").isFile());
      
      return (☃) || (☃);
    }
  };
  private final File e;
  public final bwi a;
  private final File f;
  public final bww b;
  private bwi g;
  private final ReentrantLock h = new ReentrantLock();
  private ListenableFuture<Object> i;
  private List<bwk.a> j = Lists.newArrayList();
  private List<bwk.a> k = Lists.newArrayList();
  
  public bwk(File ☃, File ☃, bwi ☃, bww ☃, bch ☃)
  {
    this.e = ☃;
    this.f = ☃;
    this.a = ☃;
    this.b = ☃;
    
    h();
    
    a();
    for (Iterator<String> ☃ = ☃.k.iterator(); ☃.hasNext();)
    {
      ☃ = (String)☃.next();
      for (bwk.a ☃ : this.j) {
        if (☃.d().equals(☃))
        {
          if ((☃.f() == 2) || (☃.l.contains(☃.d())))
          {
            this.k.add(☃);
            break;
          }
          ☃.remove();
          c.warn("Removed selected resource pack {} because it's no longer compatible", new Object[] { ☃.d() });
        }
      }
    }
    String ☃;
  }
  
  private void h()
  {
    if (this.e.exists())
    {
      if ((!this.e.isDirectory()) && ((!this.e.delete()) || (!this.e.mkdirs()))) {
        c.warn("Unable to recreate resourcepack folder, it exists but is not a directory: " + this.e);
      }
    }
    else if (!this.e.mkdirs()) {
      c.warn("Unable to create resourcepack folder: " + this.e);
    }
  }
  
  private List<File> i()
  {
    if (this.e.isDirectory()) {
      return Arrays.asList(this.e.listFiles(d));
    }
    return Collections.emptyList();
  }
  
  public void a()
  {
    List<bwk.a> ☃ = Lists.newArrayList();
    for (File ☃ : i())
    {
      bwk.a ☃ = new bwk.a(☃, null);
      if (!this.j.contains(☃))
      {
        try
        {
          ☃.a();
          ☃.add(☃);
        }
        catch (Exception ☃)
        {
          ☃.remove(☃);
        }
      }
      else
      {
        int ☃ = this.j.indexOf(☃);
        if ((☃ > -1) && (☃ < this.j.size())) {
          ☃.add(this.j.get(☃));
        }
      }
    }
    this.j.removeAll(☃);
    for (bwk.a ☃ : this.j) {
      ☃.b();
    }
    this.j = ☃;
  }
  
  public bwk.a b()
  {
    if (this.g != null)
    {
      bwk.a ☃ = new bwk.a(this.g, null);
      try
      {
        ☃.a();
        return ☃;
      }
      catch (IOException localIOException) {}
    }
    return null;
  }
  
  public List<bwk.a> c()
  {
    return ImmutableList.copyOf(this.j);
  }
  
  public List<bwk.a> d()
  {
    return ImmutableList.copyOf(this.k);
  }
  
  public void a(List<bwk.a> ☃)
  {
    this.k.clear();
    this.k.addAll(☃);
  }
  
  public File e()
  {
    return this.e;
  }
  
  public ListenableFuture<Object> a(String ☃, String ☃)
  {
    String ☃ = DigestUtils.sha1Hex(☃);
    String ☃ = ☃.matches("^[a-f0-9]{40}$") ? ☃ : "";
    
    final File ☃ = new File(this.f, ☃);
    
    this.h.lock();
    try
    {
      g();
      if (☃.exists()) {
        try
        {
          String ☃ = DigestUtils.sha1Hex(new FileInputStream(☃));
          ListenableFuture localListenableFuture1;
          if ((!☃.equals("")) && (☃.equals(☃)))
          {
            c.info("Found file " + ☃ + " matching requested hash " + ☃);
            return a(☃);
          }
          if ((!☃.equals("")) && (!☃.equals(☃)))
          {
            c.warn("File " + ☃ + " had wrong hash (expected " + ☃ + ", found " + ☃ + "). Deleting it.");
            FileUtils.deleteQuietly(☃);
          }
          else if (☃.equals(""))
          {
            c.info("Found file " + ☃ + " without verification hash");
            return a(☃);
          }
        }
        catch (IOException ☃)
        {
          c.warn("File " + ☃ + " couldn't be hashed. Deleting it.", ☃);
          FileUtils.deleteQuietly(☃);
        }
      }
      j();
      
      final bez ☃ = new bez();
      Object ☃ = bcf.ah();
      
      final bcf ☃ = bcf.z();
      Futures.getUnchecked(☃.a(new Runnable()
      {
        public void run()
        {
          ☃.a(☃);
        }
      }));
      final SettableFuture<Object> ☃ = SettableFuture.create();
      this.i = oe.a(☃, ☃, (Map)☃, 52428800, ☃, ☃.M());
      Futures.addCallback(this.i, new FutureCallback()
      {
        public void onSuccess(Object ☃)
        {
          bwk.this.a(☃);
          ☃.set(null);
        }
        
        public void onFailure(Throwable ☃)
        {
          ☃.setException(☃);
        }
      });
      return this.i;
    }
    finally
    {
      this.h.unlock();
    }
  }
  
  private void j()
  {
    try
    {
      List<File> ☃ = Lists.newArrayList(FileUtils.listFiles(this.f, TrueFileFilter.TRUE, null));
      Collections.sort(☃, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
      ☃ = 0;
      for (File ☃ : ☃) {
        if (☃++ >= 10)
        {
          c.info("Deleting old server resource pack " + ☃.getName());
          FileUtils.deleteQuietly(☃);
        }
      }
    }
    catch (IllegalArgumentException ☃)
    {
      int ☃;
      c.error("Error while deleting old server resource pack : " + ☃.getMessage());
    }
  }
  
  public ListenableFuture<Object> a(File ☃)
  {
    this.g = new bwa(☃);
    return bcf.z().A();
  }
  
  public bwi f()
  {
    return this.g;
  }
  
  public void g()
  {
    this.h.lock();
    try
    {
      if (this.i != null) {
        this.i.cancel(true);
      }
      this.i = null;
      if (this.g != null)
      {
        this.g = null;
        bcf.z().A();
      }
    }
    finally
    {
      this.h.unlock();
    }
  }
  
  public class a
  {
    private bwi b;
    private bxh c;
    private kk d;
    
    private a(File ☃)
    {
      this(☃.isDirectory() ? new bwb(☃) : new bwa(☃));
    }
    
    private a(bwi ☃)
    {
      this.b = ☃;
    }
    
    public void a()
      throws IOException
    {
      this.c = ((bxh)this.b.a(bwk.this.b, "pack"));
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
          ☃ = bwk.this.a.a();
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
}
