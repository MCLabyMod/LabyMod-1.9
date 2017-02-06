import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mojang.authlib.GameProfile;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

class mi$b
  implements JsonDeserializer<mi.a>, JsonSerializer<mi.a>
{
  private mi$b(mi parammi) {}
  
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
      mi.a ☃ = new mi.a(this.a, new GameProfile(☃, ☃), ☃, null);
      return ☃;
    }
    return null;
  }
}
