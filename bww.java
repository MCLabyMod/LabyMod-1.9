import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class bww
{
  private final db<String, bww.a<? extends bwu>> a = new dd();
  private final GsonBuilder b = new GsonBuilder();
  private Gson c;
  
  public bww()
  {
    this.b.registerTypeHierarchyAdapter(eu.class, new eu.a());
    this.b.registerTypeHierarchyAdapter(ez.class, new ez.a());
    this.b.registerTypeAdapterFactory(new om());
  }
  
  public <T extends bwu> void a(bwv<T> ☃, Class<T> ☃)
  {
    this.a.a(☃.a(), new bww.a(☃, ☃, null));
    this.b.registerTypeAdapter(☃, ☃);
    this.c = null;
  }
  
  public <T extends bwu> T a(String ☃, JsonObject ☃)
  {
    if (☃ == null) {
      throw new IllegalArgumentException("Metadata section name cannot be null");
    }
    if (!☃.has(☃)) {
      return null;
    }
    if (!☃.get(☃).isJsonObject()) {
      throw new IllegalArgumentException("Invalid metadata for '" + ☃ + "' - expected object, found " + ☃.get(☃));
    }
    bww.a<?> ☃ = (bww.a)this.a.c(☃);
    if (☃ == null) {
      throw new IllegalArgumentException("Don't know how to handle metadata section '" + ☃ + "'");
    }
    return (bwu)a().fromJson(☃.getAsJsonObject(☃), ☃.b);
  }
  
  private Gson a()
  {
    if (this.c == null) {
      this.c = this.b.create();
    }
    return this.c;
  }
  
  class a<T extends bwu>
  {
    final bwv<T> a;
    final Class<T> b;
    
    private a(Class<T> ☃)
    {
      this.a = ☃;
      this.b = ☃;
    }
  }
}
