import java.io.IOException;

public class hx
  implements ff<fi>
{
  private hx.a a;
  private eu b;
  private int c;
  private int d;
  private int e;
  
  public hx() {}
  
  public hx(hx.a ☃, eu ☃)
  {
    this(☃, ☃, -1, -1, -1);
  }
  
  public hx(int ☃, int ☃, int ☃)
  {
    this(hx.a.c, null, ☃, ☃, ☃);
  }
  
  public hx(hx.a ☃, eu ☃, int ☃, int ☃, int ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
    this.d = ☃;
    this.e = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ((hx.a)☃.a(hx.a.class));
    if ((this.a == hx.a.a) || (this.a == hx.a.b)) {
      this.b = ☃.f();
    }
    if (this.a == hx.a.c)
    {
      this.c = ☃.readInt();
      this.d = ☃.readInt();
      this.e = ☃.readInt();
    }
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.a(this.a);
    if ((this.a == hx.a.a) || (this.a == hx.a.b)) {
      ☃.a(this.b);
    }
    if (this.a == hx.a.c)
    {
      ☃.writeInt(this.c);
      ☃.writeInt(this.d);
      ☃.writeInt(this.e);
    }
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public hx.a a()
  {
    return this.a;
  }
  
  public eu b()
  {
    return this.b;
  }
  
  public int c()
  {
    return this.c;
  }
  
  public int d()
  {
    return this.d;
  }
  
  public int e()
  {
    return this.e;
  }
  
  public static enum a
  {
    private a() {}
    
    public static a a(String ☃)
    {
      for (a ☃ : ) {
        if (☃.name().equalsIgnoreCase(☃)) {
          return ☃;
        }
      }
      return a;
    }
    
    public static String[] a()
    {
      String[] ☃ = new String[values().length];
      int ☃ = 0;
      for (a ☃ : values()) {
        ☃[(☃++)] = ☃.name().toLowerCase();
      }
      return ☃;
    }
  }
}
