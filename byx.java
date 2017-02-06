import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class byx
  implements bwh, ky
{
  public static final byf a = new byf("meta:missing_sound", 1.0F, 1.0F, 1, byf.a.a, false);
  private static final Logger b = LogManager.getLogger();
  private static final Gson c = new GsonBuilder().registerTypeHierarchyAdapter(eu.class, new eu.a()).registerTypeAdapter(byg.class, new byh()).create();
  private static final ParameterizedType d = new ParameterizedType()
  {
    public Type[] getActualTypeArguments()
    {
      return new Type[] { String.class, byg.class };
    }
    
    public Type getRawType()
    {
      return Map.class;
    }
    
    public Type getOwnerType()
    {
      return null;
    }
  };
  private final byy e = new byy();
  private final byv f;
  private final bwg g;
  
  public byx(bwg ☃, bch ☃)
  {
    this.g = ☃;
    this.f = new byv(this, ☃);
  }
  
  public void a(bwg ☃)
  {
    this.e.a();
    for (String ☃ : ☃.a()) {
      try
      {
        List<bwf> ☃ = ☃.b(new kk(☃, "sounds.json"));
        for (bwf ☃ : ☃) {
          try
          {
            Map<String, byg> ☃ = a(☃.b());
            for (Map.Entry<String, byg> ☃ : ☃.entrySet()) {
              a(new kk(☃, (String)☃.getKey()), (byg)☃.getValue());
            }
          }
          catch (RuntimeException ☃)
          {
            b.warn("Invalid sounds.json", ☃);
          }
        }
      }
      catch (IOException localIOException) {}
    }
    for (kk ☃ : this.e.c())
    {
      byz ☃ = (byz)this.e.c(☃);
      if ((☃.c() instanceof fb))
      {
        String ☃ = ((fb)☃.c()).i();
        if (!bwo.a(☃)) {
          b.debug("Missing subtitle {} for event: {}", new Object[] { ☃, ☃ });
        }
      }
    }
    for (kk ☃ : this.e.c()) {
      if (nf.a.c(☃) == null) {
        b.debug("Not having sound event for: {}", new Object[] { ☃ });
      }
    }
    this.f.a();
  }
  
  protected Map<String, byg> a(InputStream ☃)
  {
    try
    {
      return (Map)c.fromJson(new InputStreamReader(☃), d);
    }
    finally
    {
      IOUtils.closeQuietly(☃);
    }
  }
  
  private void a(kk ☃, byg ☃)
  {
    byz ☃ = (byz)this.e.c(☃);
    boolean ☃ = ☃ == null;
    if ((☃) || (☃.b()))
    {
      if (!☃) {
        b.debug("Replaced sound event location {}", new Object[] { ☃ });
      }
      ☃ = new byz(☃, ☃.c());
      this.e.a(☃);
    }
    for (byf ☃ : ☃.a())
    {
      final kk ☃ = ☃.a();
      bza<byf> ☃;
      switch (byx.3.a[☃.g().ordinal()])
      {
      case 1: 
        if (a(☃, ☃)) {
          ☃ = ☃;
        }
        break;
      case 2: 
        ☃ = new bza()
        {
          public int e()
          {
            byz ☃ = (byz)byx.a(byx.this).c(☃);
            return ☃ == null ? 0 : ☃.e();
          }
          
          public byf a()
          {
            byz ☃ = (byz)byx.a(byx.this).c(☃);
            return ☃ == null ? byx.a : ☃.a();
          }
        };
        break;
      default: 
        throw new IllegalStateException("Unknown SoundEventRegistration type: " + ☃.g());
        
        ☃.a(☃);
      }
    }
  }
  
  private boolean a(byf ☃, kk ☃)
  {
    kk ☃ = ☃.b();
    bwf ☃ = null;
    try
    {
      ☃ = this.g.a(☃);
      ☃.b();
    }
    catch (FileNotFoundException ☃)
    {
      b.warn("File {} does not exist, cannot add it to event {}", new Object[] { ☃, ☃ });
      return false;
    }
    catch (IOException ☃)
    {
      boolean bool;
      b.warn("Could not load sound file " + ☃ + ", cannot add it to event " + ☃, ☃);
      return false;
    }
    finally
    {
      IOUtils.closeQuietly(☃);
    }
    return true;
  }
  
  public byz a(kk ☃)
  {
    return (byz)this.e.c(☃);
  }
  
  public void a(byi ☃)
  {
    this.f.c(☃);
  }
  
  public void a(byi ☃, int ☃)
  {
    this.f.a(☃, ☃);
  }
  
  public void a(zj ☃, float ☃)
  {
    this.f.a(☃, ☃);
  }
  
  public void a()
  {
    this.f.e();
  }
  
  public void b()
  {
    this.f.c();
  }
  
  public void d()
  {
    this.f.b();
  }
  
  public void c()
  {
    this.f.d();
  }
  
  public void e()
  {
    this.f.f();
  }
  
  public void a(nh ☃, float ☃)
  {
    if ((☃ == nh.a) && (☃ <= 0.0F)) {
      b();
    }
    this.f.a(☃, ☃);
  }
  
  public void b(byi ☃)
  {
    this.f.b(☃);
  }
  
  public boolean c(byi ☃)
  {
    return this.f.a(☃);
  }
  
  public void a(byw ☃)
  {
    this.f.a(☃);
  }
  
  public void b(byw ☃)
  {
    this.f.b(☃);
  }
}
