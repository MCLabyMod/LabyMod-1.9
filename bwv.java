import com.google.gson.JsonDeserializer;

public abstract interface bwv<T extends bwu>
  extends JsonDeserializer<T>
{
  public abstract String a();
}
