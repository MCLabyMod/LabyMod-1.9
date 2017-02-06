import java.io.IOException;

public class fy
  implements ff<fi>
{
  private eu a;
  private byte b;
  
  public fy() {}
  
  public fy(eu ☃)
  {
    this(☃, (byte)1);
  }
  
  public fy(eu ☃, byte ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.f();
    this.b = ☃.readByte();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.a(this.a);
    ☃.writeByte(this.b);
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public eu a()
  {
    return this.a;
  }
  
  public boolean b()
  {
    return (this.b == 1) || (this.b == 2);
  }
  
  public byte c()
  {
    return this.b;
  }
}
