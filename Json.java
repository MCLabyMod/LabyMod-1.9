import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class Json
{
  public static float getFloat(JsonObject obj, String field, float def)
  {
    JsonElement elem = obj.get(field);
    if (elem == null) {
      return def;
    }
    return elem.getAsFloat();
  }
  
  public static boolean getBoolean(JsonObject obj, String field, boolean def)
  {
    JsonElement elem = obj.get(field);
    if (elem == null) {
      return def;
    }
    return elem.getAsBoolean();
  }
  
  public static String getString(JsonObject jsonObj, String field)
  {
    return getString(jsonObj, field, null);
  }
  
  public static String getString(JsonObject jsonObj, String field, String def)
  {
    JsonElement jsonElement = jsonObj.get(field);
    if (jsonElement == null) {
      return def;
    }
    return jsonElement.getAsString();
  }
  
  public static float[] parseFloatArray(JsonElement jsonElement, int len)
  {
    return parseFloatArray(jsonElement, len, null);
  }
  
  public static float[] parseFloatArray(JsonElement jsonElement, int len, float[] def)
  {
    if (jsonElement == null) {
      return def;
    }
    JsonArray arr = jsonElement.getAsJsonArray();
    if (arr.size() != len) {
      throw new JsonParseException("Wrong array length: " + arr.size() + ", should be: " + len + ", array: " + arr);
    }
    float[] floatArr = new float[arr.size()];
    for (int i = 0; i < floatArr.length; i++) {
      floatArr[i] = arr.get(i).getAsFloat();
    }
    return floatArr;
  }
  
  public static int[] parseIntArray(JsonElement jsonElement, int len)
  {
    return parseIntArray(jsonElement, len, null);
  }
  
  public static int[] parseIntArray(JsonElement jsonElement, int len, int[] def)
  {
    if (jsonElement == null) {
      return def;
    }
    JsonArray arr = jsonElement.getAsJsonArray();
    if (arr.size() != len) {
      throw new JsonParseException("Wrong array length: " + arr.size() + ", should be: " + len + ", array: " + arr);
    }
    int[] intArr = new int[arr.size()];
    for (int i = 0; i < intArr.length; i++) {
      intArr[i] = arr.get(i).getAsInt();
    }
    return intArr;
  }
}
