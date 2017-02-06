import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public enum azz$b
{
  private final String d;
  
  private azz$b(String ☃)
  {
    this.d = ☃;
  }
  
  public static b a(String ☃)
  {
    for (b ☃ : ) {
      if (☃.d.equals(☃)) {
        return ☃;
      }
    }
    throw new IllegalArgumentException("Invalid entity target " + ☃);
  }
  
  public static class a
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
}
