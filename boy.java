import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

public class boy
{
  private final List<bpa> a;
  private ard b;
  
  public boy(List<bpa> ☃)
  {
    this.a = ☃;
  }
  
  public List<bpa> a()
  {
    return this.a;
  }
  
  public Set<bot> b()
  {
    Set<bot> ☃ = Sets.newHashSet();
    for (bpa ☃ : this.a) {
      ☃.add(☃.a());
    }
    return ☃;
  }
  
  public void a(ard ☃)
  {
    this.b = ☃;
  }
  
  public ard c()
  {
    return this.b;
  }
  
  public boolean equals(Object ☃)
  {
    if (this == ☃) {
      return true;
    }
    if ((☃ instanceof boy))
    {
      boy ☃ = (boy)☃;
      if (this.a.equals(☃.a))
      {
        if (this.b == null) {
          return ☃.b == null;
        }
        return this.b.equals(☃.b);
      }
    }
    return false;
  }
  
  public int hashCode()
  {
    return 31 * this.a.hashCode() + (this.b == null ? 0 : this.b.hashCode());
  }
  
  public static class a
    implements JsonDeserializer<boy>
  {
    public boy a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
      throws JsonParseException
    {
      return new boy(a(☃, ☃.getAsJsonArray()));
    }
    
    private List<bpa> a(JsonDeserializationContext ☃, JsonArray ☃)
    {
      List<bpa> ☃ = Lists.newArrayList();
      for (JsonElement ☃ : ☃) {
        ☃.add((bpa)☃.deserialize(☃, bpa.class));
      }
      return ☃;
    }
  }
}
