import java.io.IOException;

public class hd
  implements ff<fi>
{
  private int a;
  private rk b;
  
  public hd() {}
  
  public hd(int ☃, rk ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.g();
    this.b = rk.a(☃.readUnsignedByte());
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.b(this.a);
    ☃.writeByte(rk.a(this.b));
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public rr a(aht ☃)
  {
    return ☃.a(this.a);
  }
  
  public rk a()
  {
    return this.b;
  }
}
