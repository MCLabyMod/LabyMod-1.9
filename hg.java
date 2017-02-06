import java.io.IOException;

public class hg
  implements ff<fi>
{
  private int a;
  private byte b;
  
  public hg() {}
  
  public hg(rr ☃, byte ☃)
  {
    this.a = ☃.O();
    this.b = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.g();
    this.b = ☃.readByte();
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
  
  public rr a(aht ☃)
  {
    return ☃.a(this.a);
  }
  
  public byte a()
  {
    return this.b;
  }
}
