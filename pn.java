import com.google.gson.Gson;
import com.google.gson.JsonParseException;

public class pn
  implements ox
{
  public int a()
  {
    return 165;
  }
  
  public dn a(dn ☃)
  {
    if ("minecraft:written_book".equals(☃.l("id")))
    {
      dn ☃ = ☃.o("tag");
      if (☃.b("pages", 9))
      {
        du ☃ = ☃.c("pages", 8);
        for (int ☃ = 0; ☃ < ☃.c(); ☃++)
        {
          String ☃ = ☃.g(☃);
          
          eu ☃ = null;
          if (("null".equals(☃)) || (os.b(☃)))
          {
            ☃ = new fa("");
          }
          else if (((☃.charAt(0) == '"') && (☃.charAt(☃.length() - 1) == '"')) || ((☃.charAt(0) == '{') && (☃.charAt(☃.length() - 1) == '}')))
          {
            try
            {
              ☃ = (eu)pr.a.fromJson(☃, eu.class);
              if (☃ == null) {
                ☃ = new fa("");
              }
            }
            catch (JsonParseException localJsonParseException) {}
            if (☃ == null) {
              try
              {
                ☃ = eu.a.a(☃);
              }
              catch (JsonParseException localJsonParseException1) {}
            }
            if (☃ == null) {
              try
              {
                ☃ = eu.a.b(☃);
              }
              catch (JsonParseException localJsonParseException2) {}
            }
            if (☃ == null) {
              ☃ = new fa(☃);
            }
          }
          else
          {
            ☃ = new fa(☃);
          }
          ☃.a(☃, new ea(eu.a.a(☃)));
        }
        ☃.a("pages", ☃);
      }
    }
    return ☃;
  }
}
