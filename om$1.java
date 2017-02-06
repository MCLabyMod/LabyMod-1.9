import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Map;

class om$1
  extends TypeAdapter<T>
{
  om$1(om paramom, Map paramMap) {}
  
  public void write(JsonWriter ☃, T ☃)
    throws IOException
  {
    if (☃ == null) {
      ☃.nullValue();
    } else {
      ☃.value(om.a(this.b, ☃));
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
    return (T)this.a.get(☃.nextString());
  }
}
