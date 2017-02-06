import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.lwjgl.util.vector.Matrix4f;

public class bup
{
  private bnt a;
  private bwg b;
  private String c;
  private final List<buq> d = Lists.newArrayList();
  private final Map<String, bnt> e = Maps.newHashMap();
  private final List<bnt> f = Lists.newArrayList();
  private Matrix4f g;
  private int h;
  private int i;
  private float j;
  private float k;
  
  public bup(bvi ☃, bwg ☃, bnt ☃, kk ☃)
    throws IOException, JsonSyntaxException
  {
    this.b = ☃;
    this.a = ☃;
    this.j = 0.0F;
    this.k = 0.0F;
    this.h = ☃.c;
    this.i = ☃.d;
    this.c = ☃.toString();
    c();
    
    a(☃, ☃);
  }
  
  public void a(bvi ☃, kk ☃)
    throws IOException, JsonSyntaxException
  {
    JsonParser ☃ = new JsonParser();
    
    bwf ☃ = null;
    try
    {
      ☃ = this.b.a(☃);
      JsonObject ☃ = ☃.parse(IOUtils.toString(☃.b(), Charsets.UTF_8)).getAsJsonObject();
      int ☃;
      if (od.d(☃, "targets"))
      {
        JsonArray ☃ = ☃.getAsJsonArray("targets");
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
            ☃.a("targets[" + ☃ + "]");
            throw ☃;
          }
          ☃++;
        }
      }
      if (od.d(☃, "passes"))
      {
        JsonArray ☃ = ☃.getAsJsonArray("passes");
        ☃ = 0;
        for (JsonElement ☃ : ☃)
        {
          try
          {
            a(☃, ☃);
          }
          catch (Exception ☃)
          {
            ko ☃ = ko.a(☃);
            ☃.a("passes[" + ☃ + "]");
            throw ☃;
          }
          ☃++;
        }
      }
    }
    catch (Exception ☃)
    {
      int ☃;
      ko ☃ = ko.a(☃);
      ☃.b(☃.a());
      throw ☃;
    }
    finally
    {
      IOUtils.closeQuietly(☃);
    }
  }
  
  private void a(JsonElement ☃)
    throws ko
  {
    if (od.a(☃))
    {
      a(☃.getAsString(), this.h, this.i);
    }
    else
    {
      JsonObject ☃ = od.m(☃, "target");
      
      String ☃ = od.h(☃, "name");
      int ☃ = od.a(☃, "width", this.h);
      int ☃ = od.a(☃, "height", this.i);
      if (this.e.containsKey(☃)) {
        throw new ko(☃ + " is already defined");
      }
      a(☃, ☃, ☃);
    }
  }
  
  private void a(bvi ☃, JsonElement ☃)
    throws IOException
  {
    JsonObject ☃ = od.m(☃, "pass");
    
    String ☃ = od.h(☃, "name");
    String ☃ = od.h(☃, "intarget");
    String ☃ = od.h(☃, "outtarget");
    bnt ☃ = b(☃);
    bnt ☃ = b(☃);
    if (☃ == null) {
      throw new ko("Input target '" + ☃ + "' does not exist");
    }
    if (☃ == null) {
      throw new ko("Output target '" + ☃ + "' does not exist");
    }
    buq ☃ = a(☃, ☃, ☃);
    
    JsonArray ☃ = od.a(☃, "auxtargets", null);
    int ☃;
    if (☃ != null)
    {
      ☃ = 0;
      for (JsonElement ☃ : ☃)
      {
        try
        {
          JsonObject ☃ = od.m(☃, "auxtarget");
          String ☃ = od.h(☃, "name");
          String ☃ = od.h(☃, "id");
          bnt ☃ = b(☃);
          if (☃ == null)
          {
            kk ☃ = new kk("textures/effect/" + ☃ + ".png");
            bwf ☃ = null;
            try
            {
              ☃ = this.b.a(☃);
            }
            catch (FileNotFoundException ☃)
            {
              throw new ko("Render target or texture '" + ☃ + "' does not exist");
            }
            finally
            {
              IOUtils.closeQuietly(☃);
            }
            ☃.a(☃);
            bvj ☃ = ☃.b(☃);
            int ☃ = od.n(☃, "width");
            int ☃ = od.n(☃, "height");
            boolean ☃ = od.j(☃, "bilinear");
            if (☃)
            {
              bni.b(3553, 10241, 9729);
              bni.b(3553, 10240, 9729);
            }
            else
            {
              bni.b(3553, 10241, 9728);
              bni.b(3553, 10240, 9728);
            }
            ☃.a(☃, Integer.valueOf(☃.b()), ☃, ☃);
          }
          else
          {
            ☃.a(☃, ☃, ☃.a, ☃.b);
          }
        }
        catch (Exception ☃)
        {
          ko ☃ = ko.a(☃);
          ☃.a("auxtargets[" + ☃ + "]");
          throw ☃;
        }
        ☃++;
      }
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
  }
  
  private void b(JsonElement ☃)
    throws ko
  {
    JsonObject ☃ = od.m(☃, "uniform");
    String ☃ = od.h(☃, "name");
    but ☃ = ((buq)this.d.get(this.d.size() - 1)).c().a(☃);
    if (☃ == null) {
      throw new ko("Uniform '" + ☃ + "' does not exist");
    }
    float[] ☃ = new float[4];
    int ☃ = 0;
    JsonArray ☃ = od.u(☃, "values");
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
    switch (☃)
    {
    case 0: 
      break;
    case 1: 
      ☃.a(☃[0]);
      break;
    case 2: 
      ☃.a(☃[0], ☃[1]);
      break;
    case 3: 
      ☃.a(☃[0], ☃[1], ☃[2]);
      break;
    case 4: 
      ☃.a(☃[0], ☃[1], ☃[2], ☃[3]);
    }
  }
  
  public bnt a(String ☃)
  {
    return (bnt)this.e.get(☃);
  }
  
  public void a(String ☃, int ☃, int ☃)
  {
    bnt ☃ = new bnt(☃, ☃, true);
    ☃.a(0.0F, 0.0F, 0.0F, 0.0F);
    this.e.put(☃, ☃);
    if ((☃ == this.h) && (☃ == this.i)) {
      this.f.add(☃);
    }
  }
  
  public void a()
  {
    for (bnt ☃ : this.e.values()) {
      ☃.a();
    }
    for (buq ☃ : this.d) {
      ☃.b();
    }
    this.d.clear();
  }
  
  public buq a(String ☃, bnt ☃, bnt ☃)
    throws IOException
  {
    buq ☃ = new buq(this.b, ☃, ☃, ☃);
    this.d.add(this.d.size(), ☃);
    return ☃;
  }
  
  private void c()
  {
    this.g = new Matrix4f();
    this.g.setIdentity();
    this.g.m00 = (2.0F / this.a.a);
    this.g.m11 = (2.0F / -this.a.b);
    this.g.m22 = -0.0020001999F;
    this.g.m33 = 1.0F;
    this.g.m03 = -1.0F;
    this.g.m13 = 1.0F;
    this.g.m23 = -1.0001999F;
  }
  
  public void a(int ☃, int ☃)
  {
    this.h = this.a.a;
    this.i = this.a.b;
    c();
    for (buq ☃ : this.d) {
      ☃.a(this.g);
    }
    for (bnt ☃ : this.f) {
      ☃.a(☃, ☃);
    }
  }
  
  public void a(float ☃)
  {
    if (☃ < this.k)
    {
      this.j += 1.0F - this.k;
      this.j += ☃;
    }
    else
    {
      this.j += ☃ - this.k;
    }
    this.k = ☃;
    while (this.j > 20.0F) {
      this.j -= 20.0F;
    }
    for (buq ☃ : this.d) {
      ☃.a(this.j / 20.0F);
    }
  }
  
  public final String b()
  {
    return this.c;
  }
  
  private bnt b(String ☃)
  {
    if (☃ == null) {
      return null;
    }
    if (☃.equals("minecraft:main")) {
      return this.a;
    }
    return (bnt)this.e.get(☃);
  }
}
