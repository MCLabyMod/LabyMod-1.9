import java.io.IOException;

public class gk
  implements ff<fi>
{
  private int a;
  private byte b;
  
  public gk() {}
  
  public gk(rr ☃, byte ☃)
  {
    this.a = ☃.O();
    this.b = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.readInt();
    this.b = ☃.readByte();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeInt(this.a);
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
