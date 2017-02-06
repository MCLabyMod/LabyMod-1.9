import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import io.netty.util.internal.ThreadLocalRandom;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import paulscode.sound.Library;
import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException;
import paulscode.sound.SoundSystemLogger;
import paulscode.sound.Source;
import paulscode.sound.codecs.CodecJOrbis;
import paulscode.sound.libraries.LibraryLWJGLOpenAL;

public class byv
{
  private static final Marker a = MarkerManager.getMarker("SOUNDS");
  private static final Logger b = LogManager.getLogger();
  private static final Set<kk> c = Sets.newHashSet();
  private final byx d;
  private final bch e;
  private byv.a f;
  private boolean g;
  private int h = 0;
  private final Map<String, byi> i = HashBiMap.create();
  private final Map<byi, String> j = ((BiMap)this.i).inverse();
  private final Multimap<nh, String> k = HashMultimap.create();
  private final List<byj> l = Lists.newArrayList();
  private final Map<byi, Integer> m = Maps.newHashMap();
  private final Map<String, Integer> n = Maps.newHashMap();
  private final List<byw> o = Lists.newArrayList();
  private final List<String> p = Lists.newArrayList();
  
  public byv(byx ☃, bch ☃)
  {
    this.d = ☃;
    this.e = ☃;
    try
    {
      SoundSystemConfig.addLibrary(LibraryLWJGLOpenAL.class);
      SoundSystemConfig.setCodec("ogg", CodecJOrbis.class);
    }
    catch (SoundSystemException ☃)
    {
      b.error(a, "Error linking with the LibraryJavaSound plug-in", ☃);
    }
  }
  
  public void a()
  {
    c.clear();
    for (nf ☃ : nf.a)
    {
      kk ☃ = ☃.a();
      if (this.d.a(☃) == null)
      {
        b.warn("Missing sound for event: " + nf.a.b(☃));
        c.add(☃);
      }
    }
    b();
    i();
  }
  
  private synchronized void i()
  {
    if (this.g) {
      return;
    }
    try
    {
      new Thread(new Runnable()
      {
        public void run()
        {
          SoundSystemConfig.setLogger(new SoundSystemLogger()
          {
            public void message(String ☃, int ☃)
            {
              if (!☃.isEmpty()) {
                byv.g().info(☃);
              }
            }
            
            public void importantMessage(String ☃, int ☃)
            {
              if (!☃.isEmpty()) {
                byv.g().warn(☃);
              }
            }
            
            public void errorMessage(String ☃, String ☃, int ☃)
            {
              if (!☃.isEmpty())
              {
                byv.g().error("Error in class '" + ☃ + "'");
                byv.g().error(☃);
              }
            }
          });
          byv.a(byv.this, new byv.a(byv.this, null));
          byv.a(byv.this, true);
          byv.b(byv.this).setMasterVolume(byv.a(byv.this).a(nh.a));
          byv.g().info(byv.h(), "Sound engine started");
        }
      }, "Sound Library Loader").start();
    }
    catch (RuntimeException ☃)
    {
      b.error(a, "Error starting SoundSystem. Turning off sounds & music", ☃);
      
      this.e.a(nh.a, 0.0F);
      this.e.b();
    }
  }
  
  private float a(nh ☃)
  {
    if ((☃ == null) || (☃ == nh.a)) {
      return 1.0F;
    }
    return this.e.a(☃);
  }
  
  public void a(nh ☃, float ☃)
  {
    if (!this.g) {
      return;
    }
    if (☃ == nh.a)
    {
      this.f.setMasterVolume(☃);
      return;
    }
    for (String ☃ : this.k.get(☃))
    {
      byi ☃ = (byi)this.i.get(☃);
      float ☃ = e(☃);
      if (☃ <= 0.0F) {
        b(☃);
      } else {
        this.f.setVolume(☃, ☃);
      }
    }
  }
  
  public void b()
  {
    if (this.g)
    {
      c();
      
      this.f.cleanup();
      this.g = false;
    }
  }
  
  public void c()
  {
    if (this.g)
    {
      for (String ☃ : this.i.keySet()) {
        this.f.stop(☃);
      }
      this.i.clear();
      this.m.clear();
      this.l.clear();
      this.k.clear();
      this.n.clear();
    }
  }
  
  public void a(byw ☃)
  {
    this.o.add(☃);
  }
  
  public void b(byw ☃)
  {
    this.o.remove(☃);
  }
  
  public void d()
  {
    this.h += 1;
    for (byj ☃ : this.l)
    {
      ☃.c();
      if (☃.m())
      {
        b(☃);
      }
      else
      {
        String ☃ = (String)this.j.get(☃);
        
        this.f.setVolume(☃, e(☃));
        this.f.setPitch(☃, d(☃));
        this.f.setPosition(☃, ☃.i(), ☃.j(), ☃.k());
      }
    }
    Iterator<Map.Entry<String, byi>> ☃ = this.i.entrySet().iterator();
    while (☃.hasNext())
    {
      Map.Entry<String, byi> ☃ = (Map.Entry)☃.next();
      
      String ☃ = (String)☃.getKey();
      byi ☃ = (byi)☃.getValue();
      if (!this.f.playing(☃))
      {
        int ☃ = ((Integer)this.n.get(☃)).intValue();
        if (☃ <= this.h)
        {
          int ☃ = ☃.f();
          if ((☃.e()) && (☃ > 0)) {
            this.m.put(☃, Integer.valueOf(this.h + ☃));
          }
          ☃.remove();
          b.debug(a, "Removed channel {} because it's not playing anymore", new Object[] { ☃ });
          this.f.removeSource(☃);
          this.n.remove(☃);
          try
          {
            this.k.remove(☃.d(), ☃);
          }
          catch (RuntimeException localRuntimeException) {}
          if ((☃ instanceof byj)) {
            this.l.remove(☃);
          }
        }
      }
    }
    Iterator<Map.Entry<byi, Integer>> ☃ = this.m.entrySet().iterator();
    while (☃.hasNext())
    {
      Map.Entry<byi, Integer> ☃ = (Map.Entry)☃.next();
      if (this.h >= ((Integer)☃.getValue()).intValue())
      {
        byi ☃ = (byi)☃.getKey();
        if ((☃ instanceof byj)) {
          ((byj)☃).c();
        }
        c(☃);
        ☃.remove();
      }
    }
  }
  
  public boolean a(byi ☃)
  {
    if (!this.g) {
      return false;
    }
    String ☃ = (String)this.j.get(☃);
    if (☃ == null) {
      return false;
    }
    return (this.f.playing(☃)) || ((this.n.containsKey(☃)) && (((Integer)this.n.get(☃)).intValue() <= this.h));
  }
  
  public void b(byi ☃)
  {
    if (!this.g) {
      return;
    }
    String ☃ = (String)this.j.get(☃);
    if (☃ != null) {
      this.f.stop(☃);
    }
  }
  
  public void c(byi ☃)
  {
    if (!this.g) {
      return;
    }
    byz ☃ = ☃.a(this.d);
    kk ☃ = ☃.a();
    if (☃ == null)
    {
      if (c.add(☃)) {
        b.warn(a, "Unable to play unknown soundEvent: {}", new Object[] { ☃ });
      }
      return;
    }
    if (!this.o.isEmpty()) {
      for (byw ☃ : this.o) {
        ☃.a(☃, ☃);
      }
    }
    if (this.f.getMasterVolume() <= 0.0F)
    {
      b.debug(a, "Skipped playing soundEvent: {}, master volume was zero", new Object[] { ☃ });
      return;
    }
    byf ☃ = ☃.b();
    if (☃ == byx.a)
    {
      if (c.add(☃)) {
        b.warn(a, "Unable to play empty soundEvent: {}", new Object[] { ☃ });
      }
      return;
    }
    float ☃ = ☃.g();
    float ☃ = 16.0F;
    if (☃ > 1.0F) {
      ☃ *= ☃;
    }
    nh ☃ = ☃.d();
    float ☃ = e(☃);
    float ☃ = d(☃);
    if (☃ == 0.0F)
    {
      b.debug(a, "Skipped playing sound {}, volume was zero.", new Object[] { ☃.a() });
      return;
    }
    boolean ☃ = (☃.e()) && (☃.f() == 0);
    
    String ☃ = on.a(ThreadLocalRandom.current()).toString();
    
    kk ☃ = ☃.b();
    if (☃.h()) {
      this.f.newStreamingSource(false, ☃, a(☃), ☃.toString(), ☃, ☃.i(), ☃.j(), ☃.k(), ☃.l().a(), ☃);
    } else {
      this.f.newSource(false, ☃, a(☃), ☃.toString(), ☃, ☃.i(), ☃.j(), ☃.k(), ☃.l().a(), ☃);
    }
    b.debug(a, "Playing sound {} for event {} as channel {}", new Object[] { ☃.a(), ☃, ☃ });
    
    this.f.setPitch(☃, ☃);
    this.f.setVolume(☃, ☃);
    this.f.play(☃);
    
    this.n.put(☃, Integer.valueOf(this.h + 20));
    this.i.put(☃, ☃);
    if (☃ != nh.a) {
      this.k.put(☃, ☃);
    }
    if ((☃ instanceof byj)) {
      this.l.add((byj)☃);
    }
  }
  
  private float d(byi ☃)
  {
    return on.a(☃.h(), 0.5F, 2.0F);
  }
  
  private float e(byi ☃)
  {
    return on.a(☃.g() * a(☃.d()), 0.0F, 1.0F);
  }
  
  public void e()
  {
    for (Map.Entry<String, byi> ☃ : this.i.entrySet())
    {
      String ☃ = (String)☃.getKey();
      boolean ☃ = a((byi)☃.getValue());
      if (☃)
      {
        b.debug(a, "Pausing channel {}", new Object[] { ☃ });
        this.f.pause(☃);
        this.p.add(☃);
      }
    }
  }
  
  public void f()
  {
    for (String ☃ : this.p)
    {
      b.debug(a, "Resuming channel {}", new Object[] { ☃ });
      this.f.play(☃);
    }
    this.p.clear();
  }
  
  public void a(byi ☃, int ☃)
  {
    this.m.put(☃, Integer.valueOf(this.h + ☃));
  }
  
  private static URL a(kk ☃)
  {
    String ☃ = String.format("%s:%s:%s", new Object[] { "mcsounddomain", ☃.b(), ☃.a() });
    
    URLStreamHandler ☃ = new URLStreamHandler()
    {
      protected URLConnection openConnection(URL ☃)
      {
        new URLConnection(☃)
        {
          public void connect()
            throws IOException
          {}
          
          public InputStream getInputStream()
            throws IOException
          {
            return bcf.z().O().a(byv.2.this.a).b();
          }
        };
      }
    };
    try
    {
      return new URL(null, ☃, ☃);
    }
    catch (MalformedURLException ☃)
    {
      throw new Error("TODO: Sanely handle url exception! :D");
    }
  }
  
  public void a(zj ☃, float ☃)
  {
    if ((!this.g) || (☃ == null)) {
      return;
    }
    float ☃ = ☃.y + (☃.w - ☃.y) * ☃;
    float ☃ = ☃.x + (☃.v - ☃.x) * ☃;
    
    double ☃ = ☃.m + (☃.p - ☃.m) * ☃;
    double ☃ = ☃.n + (☃.q - ☃.n) * ☃ + ☃.bn();
    double ☃ = ☃.o + (☃.r - ☃.o) * ☃;
    
    float ☃ = on.b((☃ + 90.0F) * 0.017453292F);
    float ☃ = on.a((☃ + 90.0F) * 0.017453292F);
    
    float ☃ = on.b(-☃ * 0.017453292F);
    float ☃ = on.a(-☃ * 0.017453292F);
    
    float ☃ = on.b((-☃ + 90.0F) * 0.017453292F);
    float ☃ = on.a((-☃ + 90.0F) * 0.017453292F);
    
    float ☃ = ☃ * ☃;
    float ☃ = ☃;
    float ☃ = ☃ * ☃;
    
    float ☃ = ☃ * ☃;
    float ☃ = ☃;
    float ☃ = ☃ * ☃;
    
    this.f.setListenerPosition((float)☃, (float)☃, (float)☃);
    this.f.setListenerOrientation(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  class a
    extends SoundSystem
  {
    private a() {}
    
    public boolean playing(String ☃)
    {
      synchronized (SoundSystemConfig.THREAD_SYNC)
      {
        if (this.soundLibrary == null) {
          return false;
        }
        Source ☃ = (Source)this.soundLibrary.getSources().get(☃);
        if (☃ == null) {
          return false;
        }
        return (☃.playing()) || (☃.paused()) || (☃.preLoad);
      }
    }
  }
}
