import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class no
  extends nu
{
  private static final Logger b = ;
  private final MinecraftServer c;
  private final File d;
  private final Set<np> e = Sets.newHashSet();
  private int f = 65236;
  private boolean g = false;
  
  public no(MinecraftServer ☃, File ☃)
  {
    this.c = ☃;
    this.d = ☃;
  }
  
  public void a()
  {
    if (this.d.isFile()) {
      try
      {
        this.a.clear();
        this.a.putAll(a(FileUtils.readFileToString(this.d)));
      }
      catch (IOException ☃)
      {
        b.error("Couldn't read statistics file " + this.d, ☃);
      }
      catch (JsonParseException ☃)
      {
        b.error("Couldn't parse statistics file " + this.d, ☃);
      }
    }
  }
  
  public void b()
  {
    try
    {
      FileUtils.writeStringToFile(this.d, a(this.a));
    }
    catch (IOException ☃)
    {
      b.error("Couldn't save stats", ☃);
    }
  }
  
  public void a(zj ☃, np ☃, int ☃)
  {
    int ☃ = ☃.d() ? a(☃) : 0;
    super.a(☃, ☃, ☃);
    this.e.add(☃);
    if ((☃.d()) && (☃ == 0) && (☃ > 0))
    {
      this.g = true;
      if (this.c.ax()) {
        this.c.al().a(new fb("chat.type.achievement", new Object[] { ☃.i_(), ☃.j() }));
      }
    }
    if ((☃.d()) && (☃ > 0) && (☃ == 0))
    {
      this.g = true;
      if (this.c.ax()) {
        this.c.al().a(new fb("chat.type.achievement.taken", new Object[] { ☃.i_(), ☃.j() }));
      }
    }
  }
  
  public Set<np> c()
  {
    Set<np> ☃ = Sets.newHashSet(this.e);
    this.e.clear();
    this.g = false;
    return ☃;
  }
  
  public Map<np, nr> a(String ☃)
  {
    JsonElement ☃ = new JsonParser().parse(☃);
    if (!☃.isJsonObject()) {
      return Maps.newHashMap();
    }
    JsonObject ☃ = ☃.getAsJsonObject();
    Map<np, nr> ☃ = Maps.newHashMap();
    for (Map.Entry<String, JsonElement> ☃ : ☃.entrySet())
    {
      np ☃ = nt.a((String)☃.getKey());
      if (☃ != null)
      {
        nr ☃ = new nr();
        if ((((JsonElement)☃.getValue()).isJsonPrimitive()) && (((JsonElement)☃.getValue()).getAsJsonPrimitive().isNumber()))
        {
          ☃.a(((JsonElement)☃.getValue()).getAsInt());
        }
        else if (((JsonElement)☃.getValue()).isJsonObject())
        {
          JsonObject ☃ = ((JsonElement)☃.getValue()).getAsJsonObject();
          if ((☃.has("value")) && (☃.get("value").isJsonPrimitive()) && (☃.get("value").getAsJsonPrimitive().isNumber())) {
            ☃.a(☃.getAsJsonPrimitive("value").getAsInt());
          }
          if ((☃.has("progress")) && (☃.l() != null)) {
            try
            {
              Constructor<? extends ns> ☃ = ☃.l().getConstructor(new Class[0]);
              ns ☃ = (ns)☃.newInstance(new Object[0]);
              ☃.a(☃.get("progress"));
              ☃.a(☃);
            }
            catch (Throwable ☃)
            {
              b.warn("Invalid statistic progress in " + this.d, ☃);
            }
          }
        }
        ☃.put(☃, ☃);
      }
      else
      {
        b.warn("Invalid statistic in " + this.d + ": Don't know what " + (String)☃.getKey() + " is");
      }
    }
    return ☃;
  }
  
  public static String a(Map<np, nr> ☃)
  {
    JsonObject ☃ = new JsonObject();
    for (Map.Entry<np, nr> ☃ : ☃.entrySet()) {
      if (((nr)☃.getValue()).b() != null)
      {
        JsonObject ☃ = new JsonObject();
        
        ☃.addProperty("value", Integer.valueOf(((nr)☃.getValue()).a()));
        try
        {
          ☃.add("progress", ((nr)☃.getValue()).b().a());
        }
        catch (Throwable ☃)
        {
          b.warn("Couldn't save statistic " + ((np)☃.getKey()).e() + ": error serializing progress", ☃);
        }
        ☃.add(((np)☃.getKey()).e, ☃);
      }
      else
      {
        ☃.addProperty(((np)☃.getKey()).e, Integer.valueOf(((nr)☃.getValue()).a()));
      }
    }
    return ☃.toString();
  }
  
  public void d()
  {
    for (np ☃ : this.a.keySet()) {
      this.e.add(☃);
    }
  }
  
  public void a(lr ☃)
  {
    int ☃ = this.c.ap();
    Map<np, Integer> ☃ = Maps.newHashMap();
    if ((this.g) || (☃ - this.f > 300))
    {
      this.f = ☃;
      for (np ☃ : c()) {
        ☃.put(☃, Integer.valueOf(a(☃)));
      }
    }
    ☃.a.a(new fq(☃));
  }
  
  public void b(lr ☃)
  {
    Map<np, Integer> ☃ = Maps.newHashMap();
    for (nj ☃ : nk.e) {
      if (a(☃))
      {
        ☃.put(☃, Integer.valueOf(a(☃)));
        this.e.remove(☃);
      }
    }
    ☃.a.a(new fq(☃));
  }
  
  public boolean e()
  {
    return this.g;
  }
}
