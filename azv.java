import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Collection;
import java.util.Random;

public class azv
  extends azx
{
  protected final ado a;
  protected final baf[] b;
  
  public azv(ado ☃, int ☃, int ☃, baf[] ☃, bar[] ☃)
  {
    super(☃, ☃, ☃);
    this.a = ☃;
    this.b = ☃;
  }
  
  public void a(Collection<adq> ☃, Random ☃, azz ☃)
  {
    adq ☃ = new adq(this.a);
    int ☃ = 0;
    for (int ☃ = this.b.length; ☃ < ☃; ☃++)
    {
      baf ☃ = this.b[☃];
      if (bas.a(☃.a(), ☃, ☃)) {
        ☃ = ☃.a(☃, ☃, ☃);
      }
    }
    if (☃.b > 0) {
      if (☃.b < this.a.j())
      {
        ☃.add(☃);
      }
      else
      {
        int ☃ = ☃.b;
        while (☃ > 0)
        {
          adq ☃ = ☃.k();
          ☃.b = Math.min(☃.c(), ☃);
          ☃ -= ☃.b;
          ☃.add(☃);
        }
      }
    }
  }
  
  protected void a(JsonObject ☃, JsonSerializationContext ☃)
  {
    if ((this.b != null) && (this.b.length > 0)) {
      ☃.add("functions", ☃.serialize(this.b));
    }
    kk ☃ = (kk)ado.f.b(this.a);
    if (☃ == null) {
      throw new IllegalArgumentException("Can't serialize unknown item " + this.a);
    }
    ☃.addProperty("name", ☃.toString());
  }
  
  public static azv a(JsonObject ☃, JsonDeserializationContext ☃, int ☃, int ☃, bar[] ☃)
  {
    ado ☃ = od.i(☃, "name");
    baf[] ☃;
    baf[] ☃;
    if (☃.has("functions")) {
      ☃ = (baf[])od.a(☃, "functions", ☃, baf[].class);
    } else {
      ☃ = new baf[0];
    }
    return new azv(☃, ☃, ☃, ☃, ☃);
  }
}
