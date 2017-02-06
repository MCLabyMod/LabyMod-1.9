import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bvi
  implements bvl, bwh
{
  private static final Logger a = ;
  private final Map<kk, bvj> b = Maps.newHashMap();
  private final List<bvl> c = Lists.newArrayList();
  private final Map<String, Integer> d = Maps.newHashMap();
  private bwg e;
  private HashMap<String, bvj> storedTextures = new HashMap();
  
  public bvi(bwg resourceManager)
  {
    this.e = resourceManager;
  }
  
  public void a(kk resource)
  {
    bvj itextureobject = (bvj)this.b.get(resource);
    if ((itextureobject != null) && (itextureobject != bvk.a) && (!this.storedTextures.containsKey(resource.a()))) {
      this.storedTextures.put(resource.a(), itextureobject);
    } else if ((itextureobject == null) || (itextureobject == bvk.a)) {
      if (this.storedTextures.containsKey(resource.a()))
      {
        bvj texure = (bvj)this.storedTextures.get(resource.a());
        if ((texure != null) && (texure != bvk.a))
        {
          bcf.z().N().a(resource, texure);
          System.out.println("[TextureManager] Reload texture " + resource.a());
        }
      }
    }
    if (itextureobject == null)
    {
      itextureobject = new bvd(resource);
      a(resource, itextureobject);
    }
    bvk.b(itextureobject.b());
  }
  
  public boolean a(kk textureLocation, bvm textureObj)
  {
    if (a(textureLocation, textureObj))
    {
      this.c.add(textureObj);
      return true;
    }
    return false;
  }
  
  public boolean a(kk textureLocation, bvj textureObj)
  {
    boolean flag = true;
    try
    {
      textureObj.a(this.e);
    }
    catch (IOException ioexception)
    {
      a.warn("Failed to load texture: " + textureLocation, ioexception);
      textureObj = bvk.a;
      this.b.put(textureLocation, textureObj);
      flag = false;
    }
    catch (Throwable throwable)
    {
      final bvj textureObjf = textureObj;
      b crashreport = b.a(throwable, "Registering texture");
      c crashreportcategory = crashreport.a("Resource location being registered");
      crashreportcategory.a("Resource location", textureLocation);
      crashreportcategory.a("Texture object class", new Callable()
      {
        public String a()
          throws Exception
        {
          return textureObjf.getClass().getName();
        }
      });
      throw new e(crashreport);
    }
    this.b.put(textureLocation, textureObj);
    return flag;
  }
  
  public bvj b(kk textureLocation)
  {
    return (bvj)this.b.get(textureLocation);
  }
  
  public kk a(String name, bux texture)
  {
    Integer integer = (Integer)this.d.get(name);
    if (integer == null) {
      integer = Integer.valueOf(1);
    } else {
      integer = Integer.valueOf(integer.intValue() + 1);
    }
    this.d.put(name, integer);
    kk resourcelocation = new kk(String.format("dynamic/%s_%d", new Object[] { name, integer }));
    a(resourcelocation, texture);
    return resourcelocation;
  }
  
  public void e()
  {
    for (bvl itickable : this.c) {
      itickable.e();
    }
  }
  
  public void c(kk textureLocation)
  {
    bvj itextureobject = b(textureLocation);
    if (itextureobject != null) {
      bvk.a(itextureobject.b());
    }
  }
  
  public void a(bwg resourceManager)
  {
    Iterator<Map.Entry<kk, bvj>> iterator = this.b.entrySet().iterator();
    while (iterator.hasNext())
    {
      Map.Entry<kk, bvj> next = (Map.Entry)iterator.next();
      a((kk)next.getKey(), (bvj)next.getValue());
    }
  }
}
