import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class mq<K, V extends mp<K>>
{
  protected static final Logger a = ;
  protected final Gson b;
  private final File c;
  private final Map<String, V> d = Maps.newHashMap();
  private boolean e = true;
  
  public mq(File ☃)
  {
    this.c = ☃;
    GsonBuilder ☃ = new GsonBuilder().setPrettyPrinting();
    ☃.registerTypeHierarchyAdapter(mp.class, new mq.a(null));
    this.b = ☃.create();
  }
  
  public boolean b()
  {
    return this.e;
  }
  
  public void a(boolean ☃)
  {
    this.e = ☃;
  }
  
  public void a(V ☃)
  {
    this.d.put(a(☃.f()), ☃);
    try
    {
      f();
    }
    catch (IOException ☃)
    {
      a.warn("Could not save the list after adding a user.", ☃);
    }
  }
  
  public V b(K ☃)
  {
    h();
    return (mp)this.d.get(a(☃));
  }
  
  public void c(K ☃)
  {
    this.d.remove(a(☃));
    try
    {
      f();
    }
    catch (IOException ☃)
    {
      a.warn("Could not save the list after removing a user.", ☃);
    }
  }
  
  public String[] a()
  {
    return (String[])this.d.keySet().toArray(new String[this.d.size()]);
  }
  
  protected String a(K ☃)
  {
    return ☃.toString();
  }
  
  protected boolean d(K ☃)
  {
    return this.d.containsKey(a(☃));
  }
  
  private void h()
  {
    List<K> ☃ = Lists.newArrayList();
    for (V ☃ : this.d.values()) {
      if (☃.e()) {
        ☃.add(☃.f());
      }
    }
    for (K ☃ : ☃) {
      this.d.remove(☃);
    }
  }
  
  protected mp<K> a(JsonObject ☃)
  {
    return new mp((Object)null, ☃);
  }
  
  protected Map<String, V> e()
  {
    return this.d;
  }
  
  public void f()
    throws IOException
  {
    Collection<V> ☃ = this.d.values();
    String ☃ = this.b.toJson(☃);
    BufferedWriter ☃ = null;
    try
    {
      ☃ = Files.newWriter(this.c, Charsets.UTF_8);
      ☃.write(☃);
    }
    finally
    {
      IOUtils.closeQuietly(☃);
    }
  }
  
  class a
    implements JsonDeserializer<mp<K>>, JsonSerializer<mp<K>>
  {
    private a() {}
    
    public JsonElement a(mp<K> ☃, Type ☃, JsonSerializationContext ☃)
    {
      JsonObject ☃ = new JsonObject();
      ☃.a(☃);
      return ☃;
    }
    
    public mp<K> a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
      throws JsonParseException
    {
      if (☃.isJsonObject())
      {
        JsonObject ☃ = ☃.getAsJsonObject();
        mp<K> ☃ = mq.this.a(☃);
        return ☃;
      }
      return null;
    }
  }
  
  private static final ParameterizedType f = new ParameterizedType()
  {
    public Type[] getActualTypeArguments()
    {
      return new Type[] { mp.class };
    }
    
    public Type getRawType()
    {
      return List.class;
    }
    
    public Type getOwnerType()
    {
      return null;
    }
  };
}
