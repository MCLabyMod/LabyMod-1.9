import java.io.IOException;

public class gc
  implements ff<fi>
{
  private int a;
  private String b;
  private eu c;
  private int d;
  private int e;
  
  public gc() {}
  
  public gc(int ☃, String ☃, eu ☃)
  {
    this(☃, ☃, ☃, 0);
  }
  
  public gc(int ☃, String ☃, eu ☃, int ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
    this.d = ☃;
  }
  
  public gc(int ☃, String ☃, eu ☃, int ☃, int ☃)
  {
    this(☃, ☃, ☃, ☃);
    this.e = ☃;
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.readUnsignedByte();
    this.b = ☃.c(32);
    this.c = ☃.f();
    this.d = ☃.readUnsignedByte();
    if (this.b.equals("EntityHorse")) {
      this.e = ☃.readInt();
    }
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeByte(this.a);
    ☃.a(this.b);
    ☃.a(this.c);
    ☃.writeByte(this.d);
    if (this.b.equals("EntityHorse")) {
      ☃.writeInt(this.e);
    }
  }
  
  public int a()
  {
    return this.a;
  }
  
  public String b()
  {
    return this.b;
  }
  
  public eu c()
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
  
  public boolean f()
  {
    return this.d > 0;
  }
}
