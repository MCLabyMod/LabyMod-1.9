import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.apache.commons.lang3.StringUtils;

public class od
{
  public static boolean a(JsonObject ☃, String ☃)
  {
    if (!f(☃, ☃)) {
      return false;
    }
    return ☃.getAsJsonPrimitive(☃).isString();
  }
  
  public static boolean a(JsonElement ☃)
  {
    if (!☃.isJsonPrimitive()) {
      return false;
    }
    return ☃.getAsJsonPrimitive().isString();
  }
  
  public static boolean b(JsonElement ☃)
  {
    if (!☃.isJsonPrimitive()) {
      return false;
    }
    return ☃.getAsJsonPrimitive().isNumber();
  }
  
  public static boolean c(JsonObject ☃, String ☃)
  {
    if (!f(☃, ☃)) {
      return false;
    }
    return ☃.getAsJsonPrimitive(☃).isBoolean();
  }
  
  public static boolean d(JsonObject ☃, String ☃)
  {
    if (!g(☃, ☃)) {
      return false;
    }
    if (!☃.get(☃).isJsonArray()) {
      return false;
    }
    return true;
  }
  
  public static boolean f(JsonObject ☃, String ☃)
  {
    if (!g(☃, ☃)) {
      return false;
    }
    if (!☃.get(☃).isJsonPrimitive()) {
      return false;
    }
    return true;
  }
  
  public static boolean g(JsonObject ☃, String ☃)
  {
    if (☃ == null) {
      return false;
    }
    if (☃.get(☃) == null) {
      return false;
    }
    return true;
  }
  
  public static String a(JsonElement ☃, String ☃)
  {
    if (☃.isJsonPrimitive()) {
      return ☃.getAsString();
    }
    throw new JsonSyntaxException("Expected " + ☃ + " to be a string, was " + d(☃));
  }
  
  public static String h(JsonObject ☃, String ☃)
  {
    if (☃.has(☃)) {
      return a(☃.get(☃), ☃);
    }
    throw new JsonSyntaxException("Missing " + ☃ + ", expected to find a string");
  }
  
  public static String a(JsonObject ☃, String ☃, String ☃)
  {
    if (☃.has(☃)) {
      return a(☃.get(☃), ☃);
    }
    return ☃;
  }
  
  public static ado b(JsonElement ☃, String ☃)
  {
    if (☃.isJsonPrimitive())
    {
      String ☃ = ☃.getAsString();
      ado ☃ = ado.d(☃);
      if (☃ == null) {
        throw new JsonSyntaxException("Expected " + ☃ + " to be an item, was unknown string '" + ☃ + "'");
      }
      return ☃;
    }
    throw new JsonSyntaxException("Expected " + ☃ + " to be an item, was " + d(☃));
  }
  
  public static ado i(JsonObject ☃, String ☃)
  {
    if (☃.has(☃)) {
      return b(☃.get(☃), ☃);
    }
    throw new JsonSyntaxException("Missing " + ☃ + ", expected to find an item");
  }
  
  public static boolean c(JsonElement ☃, String ☃)
  {
    if (☃.isJsonPrimitive()) {
      return ☃.getAsBoolean();
    }
    throw new JsonSyntaxException("Expected " + ☃ + " to be a Boolean, was " + d(☃));
  }
  
  public static boolean j(JsonObject ☃, String ☃)
  {
    if (☃.has(☃)) {
      return c(☃.get(☃), ☃);
    }
    throw new JsonSyntaxException("Missing " + ☃ + ", expected to find a Boolean");
  }
  
  public static boolean a(JsonObject ☃, String ☃, boolean ☃)
  {
    if (☃.has(☃)) {
      return c(☃.get(☃), ☃);
    }
    return ☃;
  }
  
  public static float e(JsonElement ☃, String ☃)
  {
    if ((☃.isJsonPrimitive()) && (☃.getAsJsonPrimitive().isNumber())) {
      return ☃.getAsFloat();
    }
    throw new JsonSyntaxException("Expected " + ☃ + " to be a Float, was " + d(☃));
  }
  
  public static float l(JsonObject ☃, String ☃)
  {
    if (☃.has(☃)) {
      return e(☃.get(☃), ☃);
    }
    throw new JsonSyntaxException("Missing " + ☃ + ", expected to find a Float");
  }
  
  public static float a(JsonObject ☃, String ☃, float ☃)
  {
    if (☃.has(☃)) {
      return e(☃.get(☃), ☃);
    }
    return ☃;
  }
  
  public static int g(JsonElement ☃, String ☃)
  {
    if ((☃.isJsonPrimitive()) && (☃.getAsJsonPrimitive().isNumber())) {
      return ☃.getAsInt();
    }
    throw new JsonSyntaxException("Expected " + ☃ + " to be a Int, was " + d(☃));
  }
  
  public static int n(JsonObject ☃, String ☃)
  {
    if (☃.has(☃)) {
      return g(☃.get(☃), ☃);
    }
    throw new JsonSyntaxException("Missing " + ☃ + ", expected to find a Int");
  }
  
  public static int a(JsonObject ☃, String ☃, int ☃)
  {
    if (☃.has(☃)) {
      return g(☃.get(☃), ☃);
    }
    return ☃;
  }
  
  public static JsonObject m(JsonElement ☃, String ☃)
  {
    if (☃.isJsonObject()) {
      return ☃.getAsJsonObject();
    }
    throw new JsonSyntaxException("Expected " + ☃ + " to be a JsonObject, was " + d(☃));
  }
  
  public static JsonObject t(JsonObject ☃, String ☃)
  {
    if (☃.has(☃)) {
      return m(☃.get(☃), ☃);
    }
    throw new JsonSyntaxException("Missing " + ☃ + ", expected to find a JsonObject");
  }
  
  public static JsonObject a(JsonObject ☃, String ☃, JsonObject ☃)
  {
    if (☃.has(☃)) {
      return m(☃.get(☃), ☃);
    }
    return ☃;
  }
  
  public static JsonArray n(JsonElement ☃, String ☃)
  {
    if (☃.isJsonArray()) {
      return ☃.getAsJsonArray();
    }
    throw new JsonSyntaxException("Expected " + ☃ + " to be a JsonArray, was " + d(☃));
  }
  
  public static JsonArray u(JsonObject ☃, String ☃)
  {
    if (☃.has(☃)) {
      return n(☃.get(☃), ☃);
    }
    throw new JsonSyntaxException("Missing " + ☃ + ", expected to find a JsonArray");
  }
  
  public static JsonArray a(JsonObject ☃, String ☃, JsonArray ☃)
  {
    if (☃.has(☃)) {
      return n(☃.get(☃), ☃);
    }
    return ☃;
  }
  
  public static <T> T a(JsonElement ☃, String ☃, JsonDeserializationContext ☃, Class<? extends T> ☃)
  {
    if (☃ != null) {
      return (T)☃.deserialize(☃, ☃);
    }
    throw new JsonSyntaxException("Missing " + ☃);
  }
  
  public static <T> T a(JsonObject ☃, String ☃, JsonDeserializationContext ☃, Class<? extends T> ☃)
  {
    if (☃.has(☃)) {
      return (T)a(☃.get(☃), ☃, ☃, ☃);
    }
    throw new JsonSyntaxException("Missing " + ☃);
  }
  
  public static <T> T a(JsonObject ☃, String ☃, T ☃, JsonDeserializationContext ☃, Class<? extends T> ☃)
  {
    if (☃.has(☃)) {
      return (T)a(☃.get(☃), ☃, ☃, ☃);
    }
    return ☃;
  }
  
  public static String d(JsonElement ☃)
  {
    String ☃ = StringUtils.abbreviateMiddle(String.valueOf(☃), "...", 10);
    if (☃ == null) {
      return "null (missing)";
    }
    if (☃.isJsonNull()) {
      return "null (json)";
    }
    if (☃.isJsonArray()) {
      return "an array (" + ☃ + ")";
    }
    if (☃.isJsonObject()) {
      return "an object (" + ☃ + ")";
    }
    if (☃.isJsonPrimitive())
    {
      JsonPrimitive ☃ = ☃.getAsJsonPrimitive();
      if (☃.isNumber()) {
        return "a number (" + ☃ + ")";
      }
      if (☃.isBoolean()) {
        return "a boolean (" + ☃ + ")";
      }
    }
    return ☃;
  }
  
  public static <T> T a(Gson ☃, Reader ☃, Class<T> ☃, boolean ☃)
  {
    try
    {
      JsonReader ☃ = new JsonReader(☃);
      ☃.setLenient(☃);
      return (T)☃.getAdapter(☃).read(☃);
    }
    catch (IOException ☃)
    {
      throw new JsonParseException(☃);
    }
  }
  
  public static <T> T a(Gson ☃, String ☃, Class<T> ☃)
  {
    return (T)a(☃, ☃, ☃, false);
  }
  
  public static <T> T a(Gson ☃, String ☃, Class<T> ☃, boolean ☃)
  {
    return (T)a(☃, new StringReader(☃), ☃, ☃);
  }
}
