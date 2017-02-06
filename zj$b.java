public enum zj$b
{
  private static final b[] d;
  private final int e;
  private final String f;
  
  private zj$b(int id, String resourceKey)
  {
    this.e = id;
    this.f = resourceKey;
  }
  
  public int a()
  {
    return this.e;
  }
  
  public static b a(int id)
  {
    return d[(id % d.length)];
  }
  
  public String b()
  {
    return this.f;
  }
  
  static
  {
    d = new b[values().length];
    for (b entityplayer$enumchatvisibility : values()) {
      d[entityplayer$enumchatvisibility.e] = entityplayer$enumchatvisibility;
    }
  }
}
