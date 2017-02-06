import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;

public class jy
  implements ff<jw>
{
  private static final Gson a = new GsonBuilder().registerTypeAdapter(jz.c.class, new jz.c.a()).registerTypeAdapter(jz.a.class, new jz.a.a()).registerTypeAdapter(jz.class, new jz.b()).registerTypeHierarchyAdapter(eu.class, new eu.a()).registerTypeHierarchyAdapter(ez.class, new ez.a()).registerTypeAdapterFactory(new om()).create();
  private jz b;
  
  public jy() {}
  
  public jy(jz ☃)
  {
    this.b = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.b = ((jz)od.a(a, ☃.c(32767), jz.class));
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.a(a.toJson(this.b));
  }
  
  public void a(jw ☃)
  {
    ☃.a(this);
  }
  
  public jz a()
  {
    return this.b;
  }
}
