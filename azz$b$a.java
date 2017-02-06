import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public class azz$b$a
  extends TypeAdapter<azz.b>
{
  public void a(JsonWriter ☃, azz.b ☃)
    throws IOException
  {
    ☃.value(azz.b.a(☃));
  }
  
  public azz.b a(JsonReader ☃)
    throws IOException
  {
    return azz.b.a(☃.nextString());
  }
}
