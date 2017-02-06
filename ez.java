import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class ez
{
  private ez a;
  private a b;
  private Boolean c;
  private Boolean d;
  private Boolean e;
  private Boolean f;
  private Boolean g;
  private et h;
  private ew i;
  private String j;
  
  public a a()
  {
    return this.b == null ? o().a() : this.b;
  }
  
  public boolean b()
  {
    return this.c == null ? o().b() : this.c.booleanValue();
  }
  
  public boolean c()
  {
    return this.d == null ? o().c() : this.d.booleanValue();
  }
  
  public boolean d()
  {
    return this.f == null ? o().d() : this.f.booleanValue();
  }
  
  public boolean e()
  {
    return this.e == null ? o().e() : this.e.booleanValue();
  }
  
  public boolean f()
  {
    return this.g == null ? o().f() : this.g.booleanValue();
  }
  
  public boolean g()
  {
    return (this.c == null) && (this.d == null) && (this.f == null) && (this.e == null) && (this.g == null) && (this.b == null) && (this.h == null) && (this.i == null);
  }
  
  public et h()
  {
    return this.h == null ? o().h() : this.h;
  }
  
  public ew i()
  {
    return this.i == null ? o().i() : this.i;
  }
  
  public String j()
  {
    return this.j == null ? o().j() : this.j;
  }
  
  public ez a(a ☃)
  {
    this.b = ☃;
    return this;
  }
  
  public ez a(Boolean ☃)
  {
    this.c = ☃;
    return this;
  }
  
  public ez b(Boolean ☃)
  {
    this.d = ☃;
    return this;
  }
  
  public ez c(Boolean ☃)
  {
    this.f = ☃;
    return this;
  }
  
  public ez d(Boolean ☃)
  {
    this.e = ☃;
    return this;
  }
  
  public ez e(Boolean ☃)
  {
    this.g = ☃;
    return this;
  }
  
  public ez a(et ☃)
  {
    this.h = ☃;
    return this;
  }
  
  public ez a(ew ☃)
  {
    this.i = ☃;
    return this;
  }
  
  public ez a(String ☃)
  {
    this.j = ☃;
    return this;
  }
  
  public ez a(ez ☃)
  {
    this.a = ☃;
    return this;
  }
  
  public String k()
  {
    if (g())
    {
      if (this.a != null) {
        return this.a.k();
      }
      return "";
    }
    StringBuilder ☃ = new StringBuilder();
    if (a() != null) {
      ☃.append(a());
    }
    if (b()) {
      ☃.append(a.r);
    }
    if (c()) {
      ☃.append(a.u);
    }
    if (e()) {
      ☃.append(a.t);
    }
    if (f()) {
      ☃.append(a.q);
    }
    if (d()) {
      ☃.append(a.s);
    }
    return ☃.toString();
  }
  
  private ez o()
  {
    return this.a == null ? k : this.a;
  }
  
  public String toString()
  {
    return "Style{hasParent=" + (this.a != null) + ", color=" + this.b + ", bold=" + this.c + ", italic=" + this.d + ", underlined=" + this.e + ", obfuscated=" + this.g + ", clickEvent=" + h() + ", hoverEvent=" + i() + ", insertion=" + j() + '}';
  }
  
  public boolean equals(Object ☃)
  {
    if (this == ☃) {
      return true;
    }
    if ((☃ instanceof ez))
    {
      ez ☃ = (ez)☃;
      
      return (b() == ☃.b()) && (a() == ☃.a()) && (c() == ☃.c()) && (f() == ☃.f()) && (d() == ☃.d()) && (e() == ☃.e()) && (h() != null ? h().equals(☃.h()) : ☃.h() == null) && (i() != null ? i().equals(☃.i()) : ☃.i() == null) && (j() != null ? j().equals(☃.j()) : ☃.j() == null);
    }
    return false;
  }
  
  public int hashCode()
  {
    int ☃ = this.b.hashCode();
    ☃ = 31 * ☃ + this.c.hashCode();
    ☃ = 31 * ☃ + this.d.hashCode();
    ☃ = 31 * ☃ + this.e.hashCode();
    ☃ = 31 * ☃ + this.f.hashCode();
    ☃ = 31 * ☃ + this.g.hashCode();
    ☃ = 31 * ☃ + this.h.hashCode();
    ☃ = 31 * ☃ + this.i.hashCode();
    ☃ = 31 * ☃ + this.j.hashCode();
    return ☃;
  }
  
  private static final ez k = new ez()
  {
    public a a()
    {
      return null;
    }
    
    public boolean b()
    {
      return false;
    }
    
    public boolean c()
    {
      return false;
    }
    
    public boolean d()
    {
      return false;
    }
    
    public boolean e()
    {
      return false;
    }
    
    public boolean f()
    {
      return false;
    }
    
    public et h()
    {
      return null;
    }
    
    public ew i()
    {
      return null;
    }
    
    public String j()
    {
      return null;
    }
    
    public ez a(a ☃)
    {
      throw new UnsupportedOperationException();
    }
    
    public ez a(Boolean ☃)
    {
      throw new UnsupportedOperationException();
    }
    
    public ez b(Boolean ☃)
    {
      throw new UnsupportedOperationException();
    }
    
    public ez c(Boolean ☃)
    {
      throw new UnsupportedOperationException();
    }
    
    public ez d(Boolean ☃)
    {
      throw new UnsupportedOperationException();
    }
    
    public ez e(Boolean ☃)
    {
      throw new UnsupportedOperationException();
    }
    
    public ez a(et ☃)
    {
      throw new UnsupportedOperationException();
    }
    
    public ez a(ew ☃)
    {
      throw new UnsupportedOperationException();
    }
    
    public ez a(ez ☃)
    {
      throw new UnsupportedOperationException();
    }
    
    public String toString()
    {
      return "Style.ROOT";
    }
    
    public ez m()
    {
      return this;
    }
    
    public ez n()
    {
      return this;
    }
    
    public String k()
    {
      return "";
    }
  };
  
  public ez m()
  {
    ez ☃ = new ez();
    ☃.c = this.c;
    ☃.d = this.d;
    ☃.f = this.f;
    ☃.e = this.e;
    ☃.g = this.g;
    ☃.b = this.b;
    ☃.h = this.h;
    ☃.i = this.i;
    ☃.a = this.a;
    ☃.j = this.j;
    return ☃;
  }
  
  public ez n()
  {
    ez ☃ = new ez();
    
    ☃.a(Boolean.valueOf(b()));
    ☃.b(Boolean.valueOf(c()));
    ☃.c(Boolean.valueOf(d()));
    ☃.d(Boolean.valueOf(e()));
    ☃.e(Boolean.valueOf(f()));
    ☃.a(a());
    ☃.a(h());
    ☃.a(i());
    ☃.a(j());
    
    return ☃;
  }
  
  public static class a
    implements JsonDeserializer<ez>, JsonSerializer<ez>
  {
    public ez a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
      throws JsonParseException
    {
      if (☃.isJsonObject())
      {
        ez ☃ = new ez();
        JsonObject ☃ = ☃.getAsJsonObject();
        if (☃ == null) {
          return null;
        }
        if (☃.has("bold")) {
          ez.a(☃, Boolean.valueOf(☃.get("bold").getAsBoolean()));
        }
        if (☃.has("italic")) {
          ez.b(☃, Boolean.valueOf(☃.get("italic").getAsBoolean()));
        }
        if (☃.has("underlined")) {
          ez.c(☃, Boolean.valueOf(☃.get("underlined").getAsBoolean()));
        }
        if (☃.has("strikethrough")) {
          ez.d(☃, Boolean.valueOf(☃.get("strikethrough").getAsBoolean()));
        }
        if (☃.has("obfuscated")) {
          ez.e(☃, Boolean.valueOf(☃.get("obfuscated").getAsBoolean()));
        }
        if (☃.has("color")) {
          ez.a(☃, (a)☃.deserialize(☃.get("color"), a.class));
        }
        if (☃.has("insertion")) {
          ez.a(☃, ☃.get("insertion").getAsString());
        }
        if (☃.has("clickEvent"))
        {
          JsonObject ☃ = ☃.getAsJsonObject("clickEvent");
          if (☃ != null)
          {
            JsonPrimitive ☃ = ☃.getAsJsonPrimitive("action");
            et.a ☃ = ☃ == null ? null : et.a.a(☃.getAsString());
            
            JsonPrimitive ☃ = ☃.getAsJsonPrimitive("value");
            String ☃ = ☃ == null ? null : ☃.getAsString();
            if ((☃ != null) && (☃ != null) && (☃.a())) {
              ez.a(☃, new et(☃, ☃));
            }
          }
        }
        if (☃.has("hoverEvent"))
        {
          JsonObject ☃ = ☃.getAsJsonObject("hoverEvent");
          if (☃ != null)
          {
            JsonPrimitive ☃ = ☃.getAsJsonPrimitive("action");
            ew.a ☃ = ☃ == null ? null : ew.a.a(☃.getAsString());
            
            eu ☃ = (eu)☃.deserialize(☃.get("value"), eu.class);
            if ((☃ != null) && (☃ != null) && (☃.a())) {
              ez.a(☃, new ew(☃, ☃));
            }
          }
        }
        return ☃;
      }
      return null;
    }
    
    public JsonElement a(ez ☃, Type ☃, JsonSerializationContext ☃)
    {
      if (☃.g()) {
        return null;
      }
      JsonObject ☃ = new JsonObject();
      if (ez.b(☃) != null) {
        ☃.addProperty("bold", ez.b(☃));
      }
      if (ez.c(☃) != null) {
        ☃.addProperty("italic", ez.c(☃));
      }
      if (ez.d(☃) != null) {
        ☃.addProperty("underlined", ez.d(☃));
      }
      if (ez.e(☃) != null) {
        ☃.addProperty("strikethrough", ez.e(☃));
      }
      if (ez.f(☃) != null) {
        ☃.addProperty("obfuscated", ez.f(☃));
      }
      if (ez.g(☃) != null) {
        ☃.add("color", ☃.serialize(ez.g(☃)));
      }
      if (ez.h(☃) != null) {
        ☃.add("insertion", ☃.serialize(ez.h(☃)));
      }
      if (ez.i(☃) != null)
      {
        JsonObject ☃ = new JsonObject();
        ☃.addProperty("action", ez.i(☃).a().b());
        ☃.addProperty("value", ez.i(☃).b());
        ☃.add("clickEvent", ☃);
      }
      if (ez.j(☃) != null)
      {
        JsonObject ☃ = new JsonObject();
        ☃.addProperty("action", ez.j(☃).a().b());
        ☃.add("value", ☃.serialize(ez.j(☃).b()));
        ☃.add("hoverEvent", ☃);
      }
      return ☃;
    }
  }
}
