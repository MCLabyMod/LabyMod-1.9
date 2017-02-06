import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public class om
  implements TypeAdapterFactory
{
  public <T> TypeAdapter<T> create(Gson ☃, TypeToken<T> ☃)
  {
    Class<T> ☃ = ☃.getRawType();
    if (!☃.isEnum()) {
      return null;
    }
    final Map<String, T> ☃ = Maps.newHashMap();
    for (T ☃ : ☃.getEnumConstants()) {
      ☃.put(a(☃), ☃);
    }
    new TypeAdapter()
    {
      public void write(JsonWriter ☃, T ☃)
        throws IOException
      {
        if (☃ == null) {
          ☃.nullValue();
        } else {
          ☃.value(om.a(om.this, ☃));
        }
      }
      
      public T read(JsonReader ☃)
        throws IOException
      {
        if (☃.peek() == JsonToken.NULL)
        {
          ☃.nextNull();
          return null;
        }
        return (T)☃.get(☃.nextString());
      }
    };
  }
  
  private String a(Object ☃)
  {
    if ((☃ instanceof Enum)) {
      return ((Enum)☃).name().toLowerCase(Locale.US);
    }
    return ☃.toString().toLowerCase(Locale.US);
  }
}
