import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.List;

public class bot
{
  private final List<bou> a;
  
  public bot(List<bou> ☃)
  {
    this.a = ☃;
  }
  
  public List<bou> a()
  {
    return this.a;
  }
  
  public boolean equals(Object ☃)
  {
    if (this == ☃) {
      return true;
    }
    if ((☃ instanceof bot))
    {
      bot ☃ = (bot)☃;
      
      return this.a.equals(☃.a);
    }
    return false;
  }
  
  public int hashCode()
  {
    return this.a.hashCode();
  }
  
  public static class a
    implements JsonDeserializer<bot>
  {
    public bot a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
      throws JsonParseException
    {
      List<bou> ☃ = Lists.newArrayList();
      if (☃.isJsonArray())
      {
        JsonArray ☃ = ☃.getAsJsonArray();
        if (☃.size() == 0) {
          throw new JsonParseException("Empty variant array");
        }
        for (JsonElement ☃ : ☃) {
          ☃.add((bou)☃.deserialize(☃, bou.class));
        }
      }
      else
      {
        ☃.add((bou)☃.deserialize(☃, bou.class));
      }
      return new bot(☃);
    }
  }
}
