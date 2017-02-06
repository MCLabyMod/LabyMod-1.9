import com.google.common.base.Charsets;
import com.google.common.collect.Iterators;
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
import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.ProfileLookupCallback;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.io.IOUtils;

public class mi
{
  public static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
  private static boolean c;
  private final Map<String, mi.a> d = Maps.newHashMap();
  private final Map<UUID, mi.a> e = Maps.newHashMap();
  private final LinkedList<GameProfile> f = Lists.newLinkedList();
  private final GameProfileRepository g;
  protected final Gson b;
  private final File h;
  
  public mi(GameProfileRepository ☃, File ☃)
  {
    this.g = ☃;
    this.h = ☃;
    
    GsonBuilder ☃ = new GsonBuilder();
    ☃.registerTypeHierarchyAdapter(mi.a.class, new mi.b(null));
    this.b = ☃.create();
    
    b();
  }
  
  private static GameProfile a(GameProfileRepository ☃, String ☃)
  {
    GameProfile[] ☃ = new GameProfile[1];
    ProfileLookupCallback ☃ = new ProfileLookupCallback()
    {
      public void onProfileLookupSucceeded(GameProfile ☃)
      {
        this.a[0] = ☃;
      }
      
      public void onProfileLookupFailed(GameProfile ☃, Exception ☃)
      {
        this.a[0] = null;
      }
    };
    ☃.findProfilesByNames(new String[] { ☃ }, Agent.MINECRAFT, ☃);
    if ((!d()) && (☃[0] == null))
    {
      UUID ☃ = zj.a(new GameProfile(null, ☃));
      GameProfile ☃ = new GameProfile(☃, ☃);
      ☃.onProfileLookupSucceeded(☃);
    }
    return ☃[0];
  }
  
  public static void a(boolean ☃)
  {
    c = ☃;
  }
  
  private static boolean d()
  {
    return c;
  }
  
  public void a(GameProfile ☃)
  {
    a(☃, null);
  }
  
  private void a(GameProfile ☃, Date ☃)
  {
    UUID ☃ = ☃.getId();
    if (☃ == null)
    {
      Calendar ☃ = Calendar.getInstance();
      ☃.setTime(new Date());
      ☃.add(2, 1);
      ☃ = ☃.getTime();
    }
    String ☃ = ☃.getName().toLowerCase(Locale.ROOT);
    mi.a ☃ = new mi.a(☃, ☃, null);
    if (this.e.containsKey(☃))
    {
      mi.a ☃ = (mi.a)this.e.get(☃);
      this.d.remove(☃.a().getName().toLowerCase(Locale.ROOT));
      this.f.remove(☃);
    }
    this.d.put(☃.getName().toLowerCase(Locale.ROOT), ☃);
    this.e.put(☃, ☃);
    this.f.addFirst(☃);
    c();
  }
  
  public GameProfile a(String ☃)
  {
    String ☃ = ☃.toLowerCase(Locale.ROOT);
    mi.a ☃ = (mi.a)this.d.get(☃);
    if ((☃ != null) && (new Date().getTime() >= mi.a.a(☃).getTime()))
    {
      this.e.remove(☃.a().getId());
      this.d.remove(☃.a().getName().toLowerCase(Locale.ROOT));
      this.f.remove(☃.a());
      ☃ = null;
    }
    if (☃ != null)
    {
      GameProfile ☃ = ☃.a();
      this.f.remove(☃);
      this.f.addFirst(☃);
    }
    else
    {
      GameProfile ☃ = a(this.g, ☃);
      if (☃ != null)
      {
        a(☃);
        ☃ = (mi.a)this.d.get(☃);
      }
    }
    c();
    return ☃ == null ? null : ☃.a();
  }
  
  public String[] a()
  {
    List<String> ☃ = Lists.newArrayList(this.d.keySet());
    return (String[])☃.toArray(new String[☃.size()]);
  }
  
  public GameProfile a(UUID ☃)
  {
    mi.a ☃ = (mi.a)this.e.get(☃);
    return ☃ == null ? null : ☃.a();
  }
  
  private mi.a b(UUID ☃)
  {
    mi.a ☃ = (mi.a)this.e.get(☃);
    if (☃ != null)
    {
      GameProfile ☃ = ☃.a();
      this.f.remove(☃);
      this.f.addFirst(☃);
    }
    return ☃;
  }
  
  public void b()
  {
    BufferedReader ☃ = null;
    try
    {
      ☃ = Files.newReader(this.h, Charsets.UTF_8);
      List<mi.a> ☃ = (List)this.b.fromJson(☃, i);
      
      this.d.clear();
      this.e.clear();
      this.f.clear();
      if (☃ != null) {
        for (mi.a ☃ : Lists.reverse(☃)) {
          if (☃ != null) {
            a(☃.a(), ☃.b());
          }
        }
      }
    }
    catch (FileNotFoundException localFileNotFoundException) {}catch (JsonParseException localJsonParseException) {}finally
    {
      IOUtils.closeQuietly(☃);
    }
  }
  
  public void c()
  {
    String ☃ = this.b.toJson(a(1000));
    BufferedWriter ☃ = null;
    try
    {
      ☃ = Files.newWriter(this.h, Charsets.UTF_8);
      ☃.write(☃);
    }
    catch (FileNotFoundException ☃) {}catch (IOException ☃) {}finally
    {
      IOUtils.closeQuietly(☃);
    }
  }
  
  private List<mi.a> a(int ☃)
  {
    ArrayList<mi.a> ☃ = Lists.newArrayList();
    List<GameProfile> ☃ = Lists.newArrayList(Iterators.limit(this.f.iterator(), ☃));
    for (GameProfile ☃ : ☃)
    {
      mi.a ☃ = b(☃.getId());
      if (☃ != null) {
        ☃.add(☃);
      }
    }
    return ☃;
  }
  
  class b
    implements JsonDeserializer<mi.a>, JsonSerializer<mi.a>
  {
    private b() {}
    
    public JsonElement a(mi.a ☃, Type ☃, JsonSerializationContext ☃)
    {
      JsonObject ☃ = new JsonObject();
      ☃.addProperty("name", ☃.a().getName());
      UUID ☃ = ☃.a().getId();
      ☃.addProperty("uuid", ☃ == null ? "" : ☃.toString());
      ☃.addProperty("expiresOn", mi.a.format(☃.b()));
      return ☃;
    }
    
    public mi.a a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
      throws JsonParseException
    {
      if (☃.isJsonObject())
      {
        JsonObject ☃ = ☃.getAsJsonObject();
        JsonElement ☃ = ☃.get("name");
        JsonElement ☃ = ☃.get("uuid");
        JsonElement ☃ = ☃.get("expiresOn");
        if ((☃ == null) || (☃ == null)) {
          return null;
        }
        String ☃ = ☃.getAsString();
        String ☃ = ☃.getAsString();
        Date ☃ = null;
        if (☃ != null) {
          try
          {
            ☃ = mi.a.parse(☃.getAsString());
          }
          catch (ParseException ☃)
          {
            ☃ = null;
          }
        }
        if ((☃ == null) || (☃ == null)) {
          return null;
        }
        UUID ☃;
        try
        {
          ☃ = UUID.fromString(☃);
        }
        catch (Throwable ☃)
        {
          return null;
        }
        mi.a ☃ = new mi.a(mi.this, new GameProfile(☃, ☃), ☃, null);
        return ☃;
      }
      return null;
    }
  }
  
  class a
  {
    private final GameProfile b;
    private final Date c;
    
    private a(GameProfile ☃, Date ☃)
    {
      this.b = ☃;
      this.c = ☃;
    }
    
    public GameProfile a()
    {
      return this.b;
    }
    
    public Date b()
    {
      return this.c;
    }
  }
  
  private static final ParameterizedType i = new ParameterizedType()
  {
    public Type[] getActualTypeArguments()
    {
      return new Type[] { mi.a.class };
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
