import java.util.Collection;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bbq
  extends ayx
{
  private static final Logger b = ;
  private bbp c;
  private dn d;
  
  public bbq()
  {
    this("scoreboard");
  }
  
  public bbq(String ☃)
  {
    super(☃);
  }
  
  public void a(bbp ☃)
  {
    this.c = ☃;
    if (this.d != null) {
      a(this.d);
    }
  }
  
  public void a(dn ☃)
  {
    if (this.c == null)
    {
      this.d = ☃;
      return;
    }
    b(☃.c("Objectives", 10));
    c(☃.c("PlayerScores", 10));
    if (☃.b("DisplaySlots", 10)) {
      c(☃.o("DisplaySlots"));
    }
    if (☃.b("Teams", 9)) {
      a(☃.c("Teams", 10));
    }
  }
  
  protected void a(du ☃)
  {
    for (int ☃ = 0; ☃ < ☃.c(); ☃++)
    {
      dn ☃ = ☃.b(☃);
      
      String ☃ = ☃.l("Name");
      if (☃.length() > 16) {
        ☃ = ☃.substring(0, 16);
      }
      bbm ☃ = this.c.e(☃);
      String ☃ = ☃.l("DisplayName");
      if (☃.length() > 32) {
        ☃ = ☃.substring(0, 32);
      }
      ☃.a(☃);
      if (☃.b("TeamColor", 8)) {
        ☃.a(a.b(☃.l("TeamColor")));
      }
      ☃.b(☃.l("Prefix"));
      ☃.c(☃.l("Suffix"));
      if (☃.b("AllowFriendlyFire", 99)) {
        ☃.a(☃.p("AllowFriendlyFire"));
      }
      if (☃.b("SeeFriendlyInvisibles", 99)) {
        ☃.b(☃.p("SeeFriendlyInvisibles"));
      }
      if (☃.b("NameTagVisibility", 8))
      {
        bbr.b ☃ = bbr.b.a(☃.l("NameTagVisibility"));
        if (☃ != null) {
          ☃.a(☃);
        }
      }
      if (☃.b("DeathMessageVisibility", 8))
      {
        bbr.b ☃ = bbr.b.a(☃.l("DeathMessageVisibility"));
        if (☃ != null) {
          ☃.b(☃);
        }
      }
      if (☃.b("CollisionRule", 8))
      {
        bbr.a ☃ = bbr.a.a(☃.l("CollisionRule"));
        if (☃ != null) {
          ☃.a(☃);
        }
      }
      a(☃, ☃.c("Players", 8));
    }
  }
  
  protected void a(bbm ☃, du ☃)
  {
    for (int ☃ = 0; ☃ < ☃.c(); ☃++) {
      this.c.a(☃.g(☃), ☃.b());
    }
  }
  
  protected void c(dn ☃)
  {
    for (int ☃ = 0; ☃ < 19; ☃++) {
      if (☃.b("slot_" + ☃, 8))
      {
        String ☃ = ☃.l("slot_" + ☃);
        bbl ☃ = this.c.b(☃);
        this.c.a(☃, ☃);
      }
    }
  }
  
  protected void b(du ☃)
  {
    for (int ☃ = 0; ☃ < ☃.c(); ☃++)
    {
      dn ☃ = ☃.b(☃);
      
      bbv ☃ = (bbv)bbv.a.get(☃.l("CriteriaName"));
      if (☃ != null)
      {
        String ☃ = ☃.l("Name");
        if (☃.length() > 16) {
          ☃ = ☃.substring(0, 16);
        }
        bbl ☃ = this.c.a(☃, ☃);
        ☃.a(☃.l("DisplayName"));
        ☃.a(bbv.a.a(☃.l("RenderType")));
      }
    }
  }
  
  protected void c(du ☃)
  {
    for (int ☃ = 0; ☃ < ☃.c(); ☃++)
    {
      dn ☃ = ☃.b(☃);
      
      bbl ☃ = this.c.b(☃.l("Objective"));
      String ☃ = ☃.l("Name");
      if (☃.length() > 40) {
        ☃ = ☃.substring(0, 40);
      }
      bbn ☃ = this.c.c(☃, ☃);
      ☃.c(☃.h("Score"));
      if (☃.e("Locked")) {
        ☃.a(☃.p("Locked"));
      }
    }
  }
  
  public void b(dn ☃)
  {
    if (this.c == null)
    {
      b.warn("Tried to save scoreboard without having a scoreboard...");
      return;
    }
    ☃.a("Objectives", b());
    ☃.a("PlayerScores", e());
    ☃.a("Teams", a());
    
    d(☃);
  }
  
  protected du a()
  {
    du ☃ = new du();
    Collection<bbm> ☃ = this.c.g();
    for (bbm ☃ : ☃)
    {
      dn ☃ = new dn();
      
      ☃.a("Name", ☃.b());
      ☃.a("DisplayName", ☃.c());
      if (☃.m().b() >= 0) {
        ☃.a("TeamColor", ☃.m().e());
      }
      ☃.a("Prefix", ☃.e());
      ☃.a("Suffix", ☃.f());
      ☃.a("AllowFriendlyFire", ☃.g());
      ☃.a("SeeFriendlyInvisibles", ☃.h());
      ☃.a("NameTagVisibility", ☃.i().e);
      ☃.a("DeathMessageVisibility", ☃.j().e);
      ☃.a("CollisionRule", ☃.k().e);
      
      du ☃ = new du();
      for (String ☃ : ☃.d()) {
        ☃.a(new ea(☃));
      }
      ☃.a("Players", ☃);
      
      ☃.a(☃);
    }
    return ☃;
  }
  
  protected void d(dn ☃)
  {
    dn ☃ = new dn();
    boolean ☃ = false;
    for (int ☃ = 0; ☃ < 19; ☃++)
    {
      bbl ☃ = this.c.a(☃);
      if (☃ != null)
      {
        ☃.a("slot_" + ☃, ☃.b());
        ☃ = true;
      }
    }
    if (☃) {
      ☃.a("DisplaySlots", ☃);
    }
  }
  
  protected du b()
  {
    du ☃ = new du();
    Collection<bbl> ☃ = this.c.c();
    for (bbl ☃ : ☃) {
      if (☃.c() != null)
      {
        dn ☃ = new dn();
        ☃.a("Name", ☃.b());
        ☃.a("CriteriaName", ☃.c().a());
        ☃.a("DisplayName", ☃.d());
        ☃.a("RenderType", ☃.e().a());
        
        ☃.a(☃);
      }
    }
    return ☃;
  }
  
  protected du e()
  {
    du ☃ = new du();
    Collection<bbn> ☃ = this.c.e();
    for (bbn ☃ : ☃) {
      if (☃.d() != null)
      {
        dn ☃ = new dn();
        ☃.a("Name", ☃.e());
        ☃.a("Objective", ☃.d().b());
        ☃.a("Score", ☃.c());
        ☃.a("Locked", ☃.g());
        
        ☃.a(☃);
      }
    }
    return ☃;
  }
}
