import java.util.Set;
import java.util.TreeMap;

public class ahr
{
  private TreeMap<String, ahr.a> a = new TreeMap();
  
  public ahr()
  {
    a("doFireTick", "true", ahr.b.b);
    a("mobGriefing", "true", ahr.b.b);
    a("keepInventory", "false", ahr.b.b);
    a("doMobSpawning", "true", ahr.b.b);
    a("doMobLoot", "true", ahr.b.b);
    a("doTileDrops", "true", ahr.b.b);
    a("doEntityDrops", "true", ahr.b.b);
    a("commandBlockOutput", "true", ahr.b.b);
    a("naturalRegeneration", "true", ahr.b.b);
    a("doDaylightCycle", "true", ahr.b.b);
    a("logAdminCommands", "true", ahr.b.b);
    a("showDeathMessages", "true", ahr.b.b);
    a("randomTickSpeed", "3", ahr.b.c);
    a("sendCommandFeedback", "true", ahr.b.b);
    a("reducedDebugInfo", "false", ahr.b.b);
    a("spectatorsGenerateChunks", "true", ahr.b.b);
    a("spawnRadius", "10", ahr.b.c);
    a("disableElytraMovementCheck", "false", ahr.b.b);
  }
  
  public void a(String key, String value, ahr.b type)
  {
    this.a.put(key, new ahr.a(value, type));
  }
  
  public void a(String key, String ruleValue)
  {
    ahr.a gamerules$value = (ahr.a)this.a.get(key);
    if (gamerules$value != null) {
      gamerules$value.a(ruleValue);
    } else {
      a(key, ruleValue, ahr.b.a);
    }
  }
  
  public String a(String name)
  {
    ahr.a gamerules$value = (ahr.a)this.a.get(name);
    return gamerules$value != null ? gamerules$value.a() : "";
  }
  
  public boolean b(String name)
  {
    ahr.a gamerules$value = (ahr.a)this.a.get(name);
    return gamerules$value != null ? gamerules$value.b() : false;
  }
  
  public int c(String name)
  {
    ahr.a gamerules$value = (ahr.a)this.a.get(name);
    return gamerules$value != null ? gamerules$value.c() : 0;
  }
  
  public dn a()
  {
    dn nbttagcompound = new dn();
    for (String s : this.a.keySet())
    {
      ahr.a gamerules$value = (ahr.a)this.a.get(s);
      nbttagcompound.a(s, gamerules$value.a());
    }
    return nbttagcompound;
  }
  
  public void a(dn nbt)
  {
    for (String s : nbt.c())
    {
      String s1 = nbt.l(s);
      a(s, s1);
    }
  }
  
  public String[] b()
  {
    Set<String> set = this.a.keySet();
    return (String[])set.toArray(new String[set.size()]);
  }
  
  public boolean e(String name)
  {
    return this.a.containsKey(name);
  }
  
  public boolean a(String key, ahr.b otherValue)
  {
    ahr.a gamerules$value = (ahr.a)this.a.get(key);
    return (gamerules$value != null) && ((gamerules$value.e() == otherValue) || (otherValue == ahr.b.a));
  }
  
  public static enum b
  {
    private b() {}
  }
  
  static class a
  {
    private String a;
    private boolean b;
    private int c;
    private double d;
    private final ahr.b e;
    
    public a(String value, ahr.b type)
    {
      this.e = type;
      a(value);
    }
    
    public void a(String value)
    {
      this.a = value;
      if (value != null)
      {
        if (value.equals("false"))
        {
          this.b = false;
          return;
        }
        if (value.equals("true"))
        {
          this.b = true;
          return;
        }
      }
      this.b = Boolean.parseBoolean(value);
      this.c = (this.b ? 1 : 0);
      try
      {
        this.c = Integer.parseInt(value);
      }
      catch (NumberFormatException var4) {}
      try
      {
        this.d = Double.parseDouble(value);
      }
      catch (NumberFormatException var3) {}
    }
    
    public String a()
    {
      return this.a;
    }
    
    public boolean b()
    {
      return this.b;
    }
    
    public int c()
    {
      return this.c;
    }
    
    public ahr.b e()
    {
      return this.e;
    }
  }
}
