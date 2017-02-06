public class byf
  implements bza<byf>
{
  private final kk a;
  private final float b;
  private final float c;
  private final int d;
  private final byf.a e;
  private final boolean f;
  
  public byf(String ☃, float ☃, float ☃, int ☃, byf.a ☃, boolean ☃)
  {
    this.a = new kk(☃);
    this.b = ☃;
    this.c = ☃;
    this.d = ☃;
    this.e = ☃;
    this.f = ☃;
  }
  
  public kk a()
  {
    return this.a;
  }
  
  public kk b()
  {
    return new kk(this.a.b(), "sounds/" + this.a.a() + ".ogg");
  }
  
  public float c()
  {
    return this.b;
  }
  
  public float d()
  {
    return this.c;
  }
  
  public int e()
  {
    return this.d;
  }
  
  public byf f()
  {
    return this;
  }
  
  public byf.a g()
  {
    return this.e;
  }
  
  public boolean h()
  {
    return this.f;
  }
  
  public static enum a
  {
    private final String c;
    
    private a(String ☃)
    {
      this.c = ☃;
    }
    
    public static a a(String ☃)
    {
      for (a ☃ : ) {
        if (☃.c.equals(☃)) {
          return ☃;
        }
      }
      return null;
    }
  }
}
