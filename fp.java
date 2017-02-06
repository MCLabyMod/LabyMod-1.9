import java.io.IOException;

public class fp
  implements ff<fi>
{
  private int a;
  private int b;
  
  public fp() {}
  
  public fp(rr ☃, int ☃)
  {
    this.a = ☃.O();
    this.b = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.g();
    this.b = ☃.readUnsignedByte();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.b(this.a);
    ☃.writeByte(this.b);
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public int a()
  {
    return this.a;
  }
  
  public int b()
  {
    return this.b;
  }
}
