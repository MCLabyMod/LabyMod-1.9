import java.util.Collection;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class yt
{
  private static final Logger i = ;
  public static final sl a = new ss(null, "generic.maxHealth", 20.0D, 0.0D, 1024.0D).a("Max Health").a(true);
  public static final sl b = new ss(null, "generic.followRange", 32.0D, 0.0D, 2048.0D).a("Follow Range");
  public static final sl c = new ss(null, "generic.knockbackResistance", 0.0D, 0.0D, 1.0D).a("Knockback Resistance");
  public static final sl d = new ss(null, "generic.movementSpeed", 0.699999988079071D, 0.0D, 1024.0D).a("Movement Speed").a(true);
  public static final sl e = new ss(null, "generic.attackDamage", 2.0D, 0.0D, 2048.0D);
  public static final sl f = new ss(null, "generic.attackSpeed", 4.0D, 0.0D, 1024.0D).a(true);
  public static final sl g = new ss(null, "generic.armor", 0.0D, 0.0D, 30.0D).a(true);
  public static final sl h = new ss(null, "generic.luck", 0.0D, -1024.0D, 1024.0D).a(true);
  
  public static du a(sp ☃)
  {
    du ☃ = new du();
    for (sm ☃ : ☃.a()) {
      ☃.a(a(☃));
    }
    return ☃;
  }
  
  private static dn a(sm ☃)
  {
    dn ☃ = new dn();
    sl ☃ = ☃.a();
    
    ☃.a("Name", ☃.a());
    ☃.a("Base", ☃.b());
    
    Collection<sn> ☃ = ☃.c();
    if ((☃ != null) && (!☃.isEmpty()))
    {
      du ☃ = new du();
      for (sn ☃ : ☃) {
        if (☃.e()) {
          ☃.a(a(☃));
        }
      }
      ☃.a("Modifiers", ☃);
    }
    return ☃;
  }
  
  public static dn a(sn ☃)
  {
    dn ☃ = new dn();
    
    ☃.a("Name", ☃.b());
    ☃.a("Amount", ☃.d());
    ☃.a("Operation", ☃.c());
    ☃.a("UUID", ☃.a());
    
    return ☃;
  }
  
  public static void a(sp ☃, du ☃)
  {
    for (int ☃ = 0; ☃ < ☃.c(); ☃++)
    {
      dn ☃ = ☃.b(☃);
      sm ☃ = ☃.a(☃.l("Name"));
      if (☃ != null) {
        a(☃, ☃);
      } else {
        i.warn("Ignoring unknown attribute '" + ☃.l("Name") + "'");
      }
    }
  }
  
  private static void a(sm ☃, dn ☃)
  {
    ☃.a(☃.k("Base"));
    if (☃.b("Modifiers", 9))
    {
      du ☃ = ☃.c("Modifiers", 10);
      for (int ☃ = 0; ☃ < ☃.c(); ☃++)
      {
        sn ☃ = a(☃.b(☃));
        if (☃ != null)
        {
          sn ☃ = ☃.a(☃.a());
          if (☃ != null) {
            ☃.c(☃);
          }
          ☃.b(☃);
        }
      }
    }
  }
  
  public static sn a(dn ☃)
  {
    UUID ☃ = ☃.a("UUID");
    try
    {
      return new sn(☃, ☃.l("Name"), ☃.k("Amount"), ☃.h("Operation"));
    }
    catch (Exception ☃)
    {
      i.warn("Unable to create attribute: " + ☃.getMessage());
    }
    return null;
  }
}
