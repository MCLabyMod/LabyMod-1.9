import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class mh<T>
  extends mp<T>
{
  public static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
  protected final Date b;
  protected final String c;
  protected final Date d;
  protected final String e;
  
  public mh(T ☃, Date ☃, String ☃, Date ☃, String ☃)
  {
    super(☃);
    this.b = (☃ == null ? new Date() : ☃);
    this.c = (☃ == null ? "(Unknown)" : ☃);
    this.d = ☃;
    this.e = (☃ == null ? "Banned by an operator." : ☃);
  }
  
  protected mh(T ☃, JsonObject ☃)
  {
    super(☃, ☃);
    Date ☃;
    try
    {
      ☃ = ☃.has("created") ? a.parse(☃.get("created").getAsString()) : new Date();
    }
    catch (ParseException ☃)
    {
      ☃ = new Date();
    }
    this.b = ☃;
    this.c = (☃.has("source") ? ☃.get("source").getAsString() : "(Unknown)");
    Date ☃;
    try
    {
      ☃ = ☃.has("expires") ? a.parse(☃.get("expires").getAsString()) : null;
    }
    catch (ParseException ☃)
    {
      ☃ = null;
    }
    this.d = ☃;
    this.e = (☃.has("reason") ? ☃.get("reason").getAsString() : "Banned by an operator.");
  }
  
  public Date c()
  {
    return this.d;
  }
  
  public String d()
  {
    return this.e;
  }
  
  boolean e()
  {
    if (this.d == null) {
      return false;
    }
    return this.d.before(new Date());
  }
  
  protected void a(JsonObject ☃)
  {
    ☃.addProperty("created", a.format(this.b));
    ☃.addProperty("source", this.c);
    ☃.addProperty("expires", this.d == null ? "forever" : a.format(this.d));
    ☃.addProperty("reason", this.e);
  }
}
