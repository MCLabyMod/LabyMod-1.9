import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class buo
{
  private static final Logger a = ;
  private static final bun b = new bun();
  private static buo c = null;
  private static int d = -1;
  private static boolean e = true;
  private final Map<String, Object> f = Maps.newHashMap();
  private final List<String> g = Lists.newArrayList();
  private final List<Integer> h = Lists.newArrayList();
  private final List<but> i = Lists.newArrayList();
  private final List<Integer> j = Lists.newArrayList();
  private final Map<String, but> k = Maps.newHashMap();
  private final int l;
  private final String m;
  private final boolean n;
  private boolean o;
  private final bum p;
  private final List<Integer> q;
  private final List<String> r;
  private final bur s;
  private final bur t;
  
  public buo(bwg ☃, String ☃)
    throws IOException
  {
    JsonParser ☃ = new JsonParser();
    
    kk ☃ = new kk("shaders/program/" + ☃ + ".json");
    this.m = ☃;
    
    bwf ☃ = null;
    try
    {
      ☃ = ☃.a(☃);
      JsonObject ☃ = ☃.parse(IOUtils.toString(☃.b(), Charsets.UTF_8)).getAsJsonObject();
      
      String ☃ = od.h(☃, "vertex");
      String ☃ = od.h(☃, "fragment");
      
      JsonArray ☃ = od.a(☃, "samplers", null);
      int ☃;
      if (☃ != null)
      {
        ☃ = 0;
        for (JsonElement ☃ : ☃)
        {
          try
          {
            a(☃);
          }
          catch (Exception ☃)
          {
            ko ☃ = ko.a(☃);
            ☃.a("samplers[" + ☃ + "]");
            throw ☃;
          }
          ☃++;
        }
      }
      JsonArray ☃ = od.a(☃, "attributes", null);
      int ☃;
      if (☃ != null)
      {
        ☃ = 0;
        this.q = Lists.newArrayListWithCapacity(☃.size());
        this.r = Lists.newArrayListWithCapacity(☃.size());
        for (JsonElement ☃ : ☃)
        {
          try
          {
            this.r.add(od.a(☃, "attribute"));
          }
          catch (Exception ☃)
          {
            ko ☃ = ko.a(☃);
            ☃.a("attributes[" + ☃ + "]");
            throw ☃;
          }
          ☃++;
        }
      }
      else
      {
        this.q = null;
        this.r = null;
      }
      JsonArray ☃ = od.a(☃, "uniforms", null);
      int ☃;
      if (☃ != null)
      {
        ☃ = 0;
        for (JsonElement ☃ : ☃)
        {
          try
          {
            b(☃);
          }
          catch (Exception ☃)
          {
            ko ☃ = ko.a(☃);
            ☃.a("uniforms[" + ☃ + "]");
            throw ☃;
          }
          ☃++;
        }
      }
      this.p = bum.a(od.a(☃, "blend", null));
      this.n = od.a(☃, "cull", true);
      
      this.s = bur.a(☃, bur.a.a, ☃);
      this.t = bur.a(☃, bur.a.b, ☃);
      
      this.l = bus.b().c();
      bus.b().b(this);
      
      i();
      if (this.r != null) {
        for (String ☃ : this.r)
        {
          int ☃ = bzg.b(this.l, ☃);
          this.q.add(Integer.valueOf(☃));
        }
      }
    }
    catch (Exception ☃)
    {
      ko ☃ = ko.a(☃);
      ☃.b(☃.a());
      throw ☃;
    }
    finally
    {
      IOUtils.closeQuietly(☃);
    }
    d();
  }
  
  public void a()
  {
    bus.b().a(this);
  }
  
  public void b()
  {
    bzg.d(0);
    d = -1;
    c = null;
    e = true;
    for (int ☃ = 0; ☃ < this.h.size(); ☃++) {
      if (this.f.get(this.g.get(☃)) != null)
      {
        bni.g(bzg.q + ☃);
        bni.i(0);
      }
    }
  }
  
  public void c()
  {
    this.o = false;
    
    c = this;
    
    this.p.a();
    if (this.l != d)
    {
      bzg.d(this.l);
      d = this.l;
    }
    if (this.n) {
      bni.q();
    } else {
      bni.r();
    }
    for (int ☃ = 0; ☃ < this.h.size(); ☃++) {
      if (this.f.get(this.g.get(☃)) != null)
      {
        bni.g(bzg.q + ☃);
        bni.y();
        Object ☃ = this.f.get(this.g.get(☃));
        int ☃ = -1;
        if ((☃ instanceof bnt)) {
          ☃ = ((bnt)☃).g;
        } else if ((☃ instanceof bvj)) {
          ☃ = ((bvj)☃).b();
        } else if ((☃ instanceof Integer)) {
          ☃ = ((Integer)☃).intValue();
        }
        if (☃ != -1)
        {
          bni.i(☃);
          
          bzg.f(bzg.a(this.l, (CharSequence)this.g.get(☃)), ☃);
        }
      }
    }
    for (but ☃ : this.i) {
      ☃.b();
    }
  }
  
  public void d()
  {
    this.o = true;
  }
  
  public but a(String ☃)
  {
    if (this.k.containsKey(☃)) {
      return (but)this.k.get(☃);
    }
    return null;
  }
  
  public but b(String ☃)
  {
    if (this.k.containsKey(☃)) {
      return (but)this.k.get(☃);
    }
    return b;
  }
  
  private void i()
  {
    int ☃ = 0;
    for (int ☃ = 0; ☃ < this.g.size(); ☃++)
    {
      String ☃ = (String)this.g.get(☃);
      int ☃ = bzg.a(this.l, ☃);
      if (☃ == -1)
      {
        a.warn("Shader " + this.m + "could not find sampler named " + ☃ + " in the specified shader program.");
        
        this.f.remove(☃);
        this.g.remove(☃);
        ☃--;
      }
      else
      {
        this.h.add(Integer.valueOf(☃));
      }
      ☃++;
    }
    for (but ☃ : this.i)
    {
      String ☃ = ☃.a();
      int ☃ = bzg.a(this.l, ☃);
      if (☃ == -1)
      {
        a.warn("Could not find uniform named " + ☃ + " in the specified" + " shader program.");
      }
      else
      {
        this.j.add(Integer.valueOf(☃));
        ☃.b(☃);
        this.k.put(☃, ☃);
      }
    }
  }
  
  private void a(JsonElement ☃)
    throws ko
  {
    JsonObject ☃ = od.m(☃, "sampler");
    String ☃ = od.h(☃, "name");
    if (!od.a(☃, "file"))
    {
      this.f.put(☃, null);
      this.g.add(☃);
      return;
    }
    this.g.add(☃);
  }
  
  public void a(String ☃, Object ☃)
  {
    if (this.f.containsKey(☃)) {
      this.f.remove(☃);
    }
    this.f.put(☃, ☃);
    d();
  }
  
  private void b(JsonElement ☃)
    throws ko
  {
    JsonObject ☃ = od.m(☃, "uniform");
    String ☃ = od.h(☃, "name");
    int ☃ = but.a(od.h(☃, "type"));
    int ☃ = od.n(☃, "count");
    float[] ☃ = new float[Math.max(☃, 16)];
    
    JsonArray ☃ = od.u(☃, "values");
    if ((☃.size() != ☃) && (☃.size() > 1)) {
      throw new ko("Invalid amount of values specified (expected " + ☃ + ", found " + ☃.size() + ")");
    }
    int ☃ = 0;
    for (JsonElement ☃ : ☃)
    {
      try
      {
        ☃[☃] = od.e(☃, "value");
      }
      catch (Exception ☃)
      {
        ko ☃ = ko.a(☃);
        ☃.a("values[" + ☃ + "]");
        throw ☃;
      }
      ☃++;
    }
    if ((☃ > 1) && (☃.size() == 1)) {
      for (; ☃ < ☃; ☃++) {
        ☃[☃] = ☃[0];
      }
    }
    int ☃ = (☃ > 1) && (☃ <= 4) && (☃ < 8) ? ☃ - 1 : 0;
    but ☃ = new but(☃, ☃ + ☃, ☃, this);
    if (☃ <= 3) {
      ☃.a((int)☃[0], (int)☃[1], (int)☃[2], (int)☃[3]);
    } else if (☃ <= 7) {
      ☃.b(☃[0], ☃[1], ☃[2], ☃[3]);
    } else {
      ☃.a(☃);
    }
    this.i.add(☃);
  }
  
  public bur e()
  {
    return this.s;
  }
  
  public bur f()
  {
    return this.t;
  }
  
  public int h()
  {
    return this.l;
  }
}
