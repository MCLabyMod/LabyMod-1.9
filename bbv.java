import com.google.common.collect.Maps;
import java.util.Map;

public abstract interface bbv
{
  public static final Map<String, bbv> a = ;
  public static final bbv b = new bbt("dummy");
  public static final bbv c = new bbt("trigger");
  public static final bbv d = new bbt("deathCount");
  public static final bbv e = new bbt("playerKillCount");
  public static final bbv f = new bbt("totalKillCount");
  public static final bbv g = new bbu("health");
  public static final bbv h = new bbw("food");
  public static final bbv i = new bbw("air");
  public static final bbv j = new bbw("armor");
  public static final bbv k = new bbw("xp");
  public static final bbv l = new bbw("level");
  public static final bbv[] m = { new bbs("teamkill.", a.a), new bbs("teamkill.", a.b), new bbs("teamkill.", a.c), new bbs("teamkill.", a.d), new bbs("teamkill.", a.e), new bbs("teamkill.", a.f), new bbs("teamkill.", a.g), new bbs("teamkill.", a.h), new bbs("teamkill.", a.i), new bbs("teamkill.", a.j), new bbs("teamkill.", a.k), new bbs("teamkill.", a.l), new bbs("teamkill.", a.m), new bbs("teamkill.", a.n), new bbs("teamkill.", a.o), new bbs("teamkill.", a.p) };
  public static final bbv[] n = { new bbs("killedByTeam.", a.a), new bbs("killedByTeam.", a.b), new bbs("killedByTeam.", a.c), new bbs("killedByTeam.", a.d), new bbs("killedByTeam.", a.e), new bbs("killedByTeam.", a.f), new bbs("killedByTeam.", a.g), new bbs("killedByTeam.", a.h), new bbs("killedByTeam.", a.i), new bbs("killedByTeam.", a.j), new bbs("killedByTeam.", a.k), new bbs("killedByTeam.", a.l), new bbs("killedByTeam.", a.m), new bbs("killedByTeam.", a.n), new bbs("killedByTeam.", a.o), new bbs("killedByTeam.", a.p) };
  
  public abstract String a();
  
  public abstract boolean b();
  
  public abstract bbv.a c();
  
  public static enum a
  {
    private static final Map<String, a> c;
    private final String d;
    
    static
    {
      c = Maps.newHashMap();
      for (a ☃ : values()) {
        c.put(☃.a(), ☃);
      }
    }
    
    private a(String ☃)
    {
      this.d = ☃;
    }
    
    public String a()
    {
      return this.d;
    }
    
    public static a a(String ☃)
    {
      a ☃ = (a)c.get(☃);
      return ☃ == null ? a : ☃;
    }
  }
}
