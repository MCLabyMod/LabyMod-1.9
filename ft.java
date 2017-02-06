import java.io.IOException;

public class ft
  implements ff<fi>
{
  private cj a;
  private int b;
  private int c;
  private ajt d;
  
  public ft() {}
  
  public ft(cj ☃, ajt ☃, int ☃, int ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
    this.d = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.e();
    this.b = ☃.readUnsignedByte();
    this.c = ☃.readUnsignedByte();
    this.d = ajt.b(☃.g() & 0xFFF);
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.a(this.a);
    ☃.writeByte(this.b);
    ☃.writeByte(this.c);
    ☃.b(ajt.a(this.d) & 0xFFF);
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public cj a()
  {
    return this.a;
  }
  
  public int b()
  {
    return this.b;
  }
  
  public int c()
  {
    return this.c;
  }
  
  public ajt d()
  {
    return this.d;
  }
}
